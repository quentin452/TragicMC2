package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.entity.EntityDirectedLightning;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayTitanfall extends Doomsday implements IExtendedDoomsday {

    public DoomsdayTitanfall(int id) {
        super(id, EnumDoomType.OVERFLOW);
    }

    @Override
    public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        super.doInitialEffects(effect, doom, player, crucMoment);
        if (TragicConfig.allowInvulnerability)
            player.addPotionEffect(new PotionEffect(TragicPotion.Invulnerability.id, 400, 0));
        if (crucMoment) player.worldObj
            .createExplosion(player, player.posX, player.posY, player.posZ, 5.0f * rand.nextFloat(), false);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

        double x = player.posX;
        double y = player.posY;
        double z = player.posZ;

        if (effect.utilityInt > 6) effect.utilityInt = 0;
        effect.utilityInt++;

        float f = crucMoment ? 5.0F : 2.0F;
        double base = 4.0D;

        if (rand.nextBoolean()) {
            player.worldObj.spawnEntityInWorld(
                new EntityDirectedLightning(player.worldObj, x + base + effect.utilityInt, y, z, player));
            player.worldObj.createExplosion(player, x + base + effect.utilityInt, y, z, f * rand.nextFloat(), false);
            player.worldObj.spawnEntityInWorld(
                new EntityDirectedLightning(player.worldObj, x, y, z + base + effect.utilityInt, player));
            player.worldObj.createExplosion(player, x, y, z + base + effect.utilityInt, f * rand.nextFloat(), false);
            player.worldObj.spawnEntityInWorld(
                new EntityDirectedLightning(player.worldObj, x - base - effect.utilityInt, y, z, player));
            player.worldObj.createExplosion(player, x - base - effect.utilityInt, y, z, f * rand.nextFloat(), false);
            player.worldObj.spawnEntityInWorld(
                new EntityDirectedLightning(player.worldObj, x, y, z - base - effect.utilityInt, player));
            player.worldObj.createExplosion(player, x, y, z - base - effect.utilityInt, f * rand.nextFloat(), false);
        } else {
            player.worldObj.spawnEntityInWorld(
                new EntityDirectedLightning(
                    player.worldObj,
                    x + base + effect.utilityInt,
                    y,
                    z + base + effect.utilityInt,
                    player));
            player.worldObj.createExplosion(
                player,
                x + base + effect.utilityInt,
                y,
                z + base + effect.utilityInt,
                f * rand.nextFloat(),
                false);
            player.worldObj.spawnEntityInWorld(
                new EntityDirectedLightning(
                    player.worldObj,
                    x - base - effect.utilityInt,
                    y,
                    z + base + effect.utilityInt,
                    player));
            player.worldObj.createExplosion(
                player,
                x - base - effect.utilityInt,
                y,
                z + base + effect.utilityInt,
                f * rand.nextFloat(),
                false);
            player.worldObj.spawnEntityInWorld(
                new EntityDirectedLightning(
                    player.worldObj,
                    x + base + effect.utilityInt,
                    y,
                    z - base - effect.utilityInt,
                    player));
            player.worldObj.createExplosion(
                player,
                x + base + effect.utilityInt,
                y,
                z - base - effect.utilityInt,
                f * rand.nextFloat(),
                false);
            player.worldObj.spawnEntityInWorld(
                new EntityDirectedLightning(
                    player.worldObj,
                    x - base - effect.utilityInt,
                    y,
                    z - base - effect.utilityInt,
                    player));
            player.worldObj.createExplosion(
                player,
                x - base - effect.utilityInt,
                y,
                z - base - effect.utilityInt,
                f * rand.nextFloat(),
                false);
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
        for (int i = 0; i < 4; i++) {
            EntityCreeper creeper = new EntityCreeper(player.worldObj);
            creeper.setPosition(
                player.posX + rand.nextDouble() - rand.nextDouble(),
                player.posY,
                player.posZ + rand.nextDouble() - rand.nextDouble());
            player.worldObj.spawnEntityInWorld(creeper);
            creeper.getDataWatcher()
                .updateObject(17, 1);
        }
    }

    @Override
    public Doomsday getCombination() {
        return Doomsday.LightShove;
    }

    @Override
    public int getWaitTime() {
        return 10;
    }

    @Override
    public int getMaxIterations() {
        return 40;
    }
}
