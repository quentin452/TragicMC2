package tragicneko.tragicmc.doomsday;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;

import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayShotgun extends Doomsday implements IExtendedDoomsday {

    public DoomsdayShotgun(int id) {
        super(id, EnumDoomType.COMBINATION);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

        if (crucMoment) addCrucialMessage(player);

        for (int i = 0; i < 6; i++) {
            float f = 1.0F;
            EntityArrow entityarrow = new EntityArrow(player.worldObj, player, f * 2.0F);
            ItemStack stack = player.getCurrentEquippedItem();
            double damage = 3.0;

            if (crucMoment) damage += 3.0;

            entityarrow.setDamage(entityarrow.getDamage() + damage);
            entityarrow.motionX *= 1.3;
            entityarrow.motionZ *= 1.3;
            entityarrow.motionY *= 1.1;

            if (f == 1.0F) entityarrow.setIsCritical(true);

            int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);
            if (l > 0) entityarrow.setKnockbackStrength(l);

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack) > 0) entityarrow.setFire(200);

            player.worldObj.playSoundAtEntity(
                player,
                "random.bow",
                1.0F,
                1.0F / (Doomsday.rand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
            entityarrow.canBePickedUp = 2;

            entityarrow.setPosition(
                player.posX + rand.nextDouble() - rand.nextDouble(),
                player.posY + 1.2D,
                player.posZ + rand.nextDouble() - rand.nextDouble());
            player.worldObj.spawnEntityInWorld(entityarrow);
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

    }

    @Override
    public int getWaitTime() {
        return 10;
    }

    @Override
    public int getMaxIterations() {
        return 20;
    }

}
