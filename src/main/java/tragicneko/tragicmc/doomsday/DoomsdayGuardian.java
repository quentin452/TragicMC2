package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;

import tragicneko.tragicmc.entity.projectile.EntityGuardianShield;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayGuardian extends Doomsday {

    public DoomsdayGuardian(int id) {
        super(id, EnumDoomType.CRISIS);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

        float f = Math.max(1 / (this.getCrisis(player)) * 20F, 40F);
        double d0 = 0.0D;
        if (crucMoment) player.heal(player.getHealth() / 2);

        EntityGuardianShield shield = new EntityGuardianShield(player.worldObj, player, d0, d0, d0)
            .setShieldMaxHealth(f);
        shield.setPosition(player.posX + 2.25, player.posY + 0.3, player.posZ);
        shield.setShieldOffsets(2.25, 0);
        player.worldObj.spawnEntityInWorld(shield);

        EntityGuardianShield shield2 = new EntityGuardianShield(player.worldObj, player, d0, d0, d0)
            .setShieldMaxHealth(f);
        shield2.setPosition(player.posX - 2.25, player.posY + 0.3, player.posZ);
        shield2.setShieldOffsets(-2.25, 0);
        player.worldObj.spawnEntityInWorld(shield2);

        EntityGuardianShield shield3 = new EntityGuardianShield(player.worldObj, player, d0, d0, d0)
            .setShieldMaxHealth(f);
        shield3.setPosition(player.posX, player.posY + 0.3, player.posZ + 2.25);
        shield3.setShieldOffsets(0, 2.25);
        player.worldObj.spawnEntityInWorld(shield3);

        EntityGuardianShield shield4 = new EntityGuardianShield(player.worldObj, player, d0, d0, d0)
            .setShieldMaxHealth(f);
        shield4.setPosition(player.posX, player.posY + 0.3, player.posZ - 2.25);
        shield4.setShieldOffsets(0, -2.25);
        player.worldObj.spawnEntityInWorld(shield4);
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {}

}
