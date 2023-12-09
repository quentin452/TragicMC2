package tragicneko.tragicmc.doomsday;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdaySnipe extends Doomsday {

    public DoomsdaySnipe(int id) {
        super(id, EnumDoomType.CRISIS);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        float crisis = this.getCrisis(player);
        float f = 1.0F;
        EntityArrow entityarrow = new EntityArrow(player.worldObj, player, f * 2.0F);
        ItemStack stack = player.getCurrentEquippedItem();
        double damage = 20.0;
        damage += (1 / crisis) * 5.0D;
        if (crucMoment) damage += 5.0;

        entityarrow.setDamage(entityarrow.getDamage() + damage);
        entityarrow.motionX *= 1.3;
        entityarrow.motionZ *= 1.3;
        entityarrow.motionY *= 1.1;

        if (f == 1.0F) entityarrow.setIsCritical(true);

        int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);

        if (l > 0) entityarrow.setKnockbackStrength(l);

        if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack) > 0) entityarrow.setFire(100);

        player.worldObj
            .playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (Doomsday.rand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
        entityarrow.canBePickedUp = 2;

        player.worldObj.spawnEntityInWorld(entityarrow);
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
        if (player.inventory.hasItem(Items.arrow)) player.inventory.consumeInventoryItem(Items.arrow);
    }

    @Override
    public Doomsday getCombination() {
        return Doomsday.Shotgun;
    }
}
