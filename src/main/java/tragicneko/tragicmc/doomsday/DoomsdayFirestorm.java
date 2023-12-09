package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class DoomsdayFirestorm extends Doomsday implements IExtendedDoomsday {

    public DoomsdayFirestorm(int id) {
        super(id, EnumDoomType.COMBINATION);
    }

    @Override
    public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        super.doInitialEffects(effect, doom, player, crucMoment);

        if (TragicConfig.allowFlight) {
            player.addPotionEffect(new PotionEffect(TragicPotion.Flight.id, 6000, 0));
        } else {
            player.addPotionEffect(new PotionEffect(Potion.jump.id, 600, 3));
        }
        player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 600));

        if (crucMoment) {
            List<Entity> list = player.worldObj
                .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(12.0D, 12.0D, 12.0D));
            EntityLivingBase entity;

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof EntityLivingBase) {
                    entity = (EntityLivingBase) list.get(i);
                    if (TragicConfig.allowStun && crucMoment) {
                        entity.addPotionEffect(new PotionEffect(TragicPotion.Stun.id, 120, 1));
                    }

                    entity.addPotionEffect(new PotionEffect(Potion.weakness.id, 300, 15));
                }
            }
        }
        // Because the wait time is so high, we'll do it on the initial use to reduce the awkward pause after using it
        double d1 = (rand.nextDouble() - rand.nextDouble()) * 4.0D;
        double d2 = (rand.nextDouble() - rand.nextDouble()) * 4.0D + 4.0D;
        double d3 = (rand.nextDouble() - rand.nextDouble()) * 4.0D;

        double y = player.posY - WorldHelper.getDistanceToGround(player);

        for (int l = 0; l < 12; l++) {
            EntityLargeFireball fireball = new EntityLargeFireball(player.worldObj, player, -d1, -d2, -d3);
            fireball.setPosition(
                player.posX + rand.nextInt(16) - rand.nextInt(16),
                y + 24 + rand.nextInt(16),
                player.posZ + rand.nextInt(16) - rand.nextInt(16));
            player.worldObj.spawnEntityInWorld(fireball);
        }

        for (int l = 0; l < 24; l++) {
            EntityLargeFireball fireball = new EntityLargeFireball(player.worldObj, player, -d1, -d2, -d3);
            fireball.setPosition(
                player.posX + rand.nextInt(8) - rand.nextInt(8),
                y + 24 + rand.nextInt(16),
                player.posZ + rand.nextInt(8) - rand.nextInt(8));
            player.worldObj.spawnEntityInWorld(fireball);
        }

        if (rand.nextInt(4) == 0) {
            double d4 = d1 + rand.nextInt(32) - rand.nextInt(32);
            double d5 = d3 + rand.nextInt(32) - rand.nextInt(32);
            player.worldObj.addWeatherEffect(
                new EntityLightningBolt(
                    player.worldObj,
                    d4,
                    player.worldObj.getTopSolidOrLiquidBlock((int) d4, (int) d5),
                    d5));
        }
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

        double d1 = (rand.nextDouble() - rand.nextDouble()) * 4.0D;
        double d2 = (rand.nextDouble() - rand.nextDouble()) * 4.0D + 4.0D;
        double d3 = (rand.nextDouble() - rand.nextDouble()) * 4.0D;

        double y = player.posY - WorldHelper.getDistanceToGround(player);

        for (int l = 0; l < 12; l++) {
            EntityLargeFireball fireball = new EntityLargeFireball(player.worldObj, player, -d1, -d2, -d3);
            fireball.setPosition(
                player.posX + rand.nextInt(16) - rand.nextInt(16),
                y + 24 + rand.nextInt(16),
                player.posZ + rand.nextInt(16) - rand.nextInt(16));
            player.worldObj.spawnEntityInWorld(fireball);
        }

        for (int l = 0; l < 24; l++) {
            EntityLargeFireball fireball = new EntityLargeFireball(player.worldObj, player, -d1, -d2, -d3);
            fireball.setPosition(
                player.posX + rand.nextInt(8) - rand.nextInt(8),
                y + 24 + rand.nextInt(16),
                player.posZ + rand.nextInt(8) - rand.nextInt(8));
            player.worldObj.spawnEntityInWorld(fireball);
        }

        if (rand.nextInt(4) == 0) {
            double d4 = d1 + rand.nextInt(32) - rand.nextInt(32);
            double d5 = d3 + rand.nextInt(32) - rand.nextInt(32);
            player.worldObj.addWeatherEffect(
                new EntityLightningBolt(
                    player.worldObj,
                    d4,
                    player.worldObj.getTopSolidOrLiquidBlock((int) d4, (int) d5),
                    d5));
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

    }

    @Override
    public int getWaitTime() {
        return 20;
    }

    @Override
    public int getMaxIterations() {
        return 30;
    }

}
