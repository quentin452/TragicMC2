package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;

import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.entity.projectile.EntityNekoRocket;
import tragicneko.tragicmc.entity.projectile.EntityNekoStickyBomb;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class DoomsdaySuicidalTendencies extends Doomsday implements IExtendedDoomsday {

    public DoomsdaySuicidalTendencies(int id) {
        super(id, EnumDoomType.WORLDSHAPER);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        Vec3 vec = WorldHelper.getVecFromEntity(player, 30.0);
        if (vec == null) return;

        double d4 = vec.xCoord - player.posX;
        double d5 = vec.yCoord - (player.posY + player.height / 2.0F);
        double d6 = vec.zCoord - player.posZ;

        for (int i = 0; i < 3; i++) {
            EntityNekoRocket rocket = new EntityNekoRocket(player.worldObj, player, d4, d5, d6);
            rocket.posX = player.posX + (d4 * 0.115D + rand.nextDouble() - rand.nextDouble());
            rocket.posY = player.posY + player.getEyeHeight();
            rocket.posZ = player.posZ + (d6 * 0.115D + rand.nextDouble() - rand.nextDouble());
            player.worldObj.spawnEntityInWorld(rocket);
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
        EntityNekoStickyBomb bomb = new EntityNekoStickyBomb(player.worldObj, player);
        bomb.setPosition(player.posX, player.posY, player.posZ);
        player.worldObj.spawnEntityInWorld(bomb);
    }

    @Override
    public Doomsday getCombination() {
        return Doomsday.Ravage;
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
