package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.entity.projectile.EntityPumpkinbomb;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayReaperLaugh extends Doomsday implements IExtendedDoomsday {

    public DoomsdayReaperLaugh(int id) {
        super(id, EnumDoomType.OVERFLOW);
    }

    @Override
    public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        super.doInitialEffects(effect, doom, player, crucMoment);
        if (TragicConfig.allowImmunity) player.addPotionEffect(new PotionEffect(TragicPotion.Immunity.id, 300, 0));
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        for (int l = 0; l < 3; l++) {
            double d1 = rand.nextDouble() - rand.nextDouble();
            double d2 = (rand.nextDouble() * 6.0D + rand.nextDouble() * 6.0D) + 2.0D;
            double d3 = rand.nextDouble() - rand.nextDouble();

            EntityPumpkinbomb bomb = new EntityPumpkinbomb(player.worldObj, player);
            bomb.setPosition(player.posX + (d1 * 0.115), player.posY + 0.6D, player.posZ + (d3 * 0.115));
            bomb.motionX = d1;
            bomb.motionY = d2;
            bomb.motionZ = d3;
            player.worldObj.spawnEntityInWorld(bomb);
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
        return 30;
    }
}
