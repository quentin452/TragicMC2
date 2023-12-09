package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Vec3;

import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class DoomsdayLaserCutter extends Doomsday implements IExtendedDoomsday {

    public DoomsdayLaserCutter(int id) {
        super(id, EnumDoomType.WORLDSHAPER);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        double d = 20.0;
        boolean flag = false;
        for (double d0 = 1.5; d0 < d && !flag; d0 += 0.25D) {
            Vec3 vec = WorldHelper.getVecFromEntity(player, d0);
            List<int[]> list = WorldHelper.getBlocksInSphericalRange(
                player.worldObj,
                crucMoment ? 1.25 : 0.85,
                vec.xCoord,
                vec.yCoord,
                vec.zCoord);

            for (int[] coords : list) {
                float f = player.worldObj.getBlock(coords[0], coords[1], coords[2])
                    .getBlockHardness(player.worldObj, coords[0], coords[1], coords[2]);
                if (f > 0F && f < 16F && player.worldObj.canMineBlock(player, coords[0], coords[1], coords[2])) {
                    player.worldObj.func_147480_a(coords[0], coords[1], coords[2], true);
                    flag = true;
                }
            }
        }

        if (crucMoment && flag) addCrucialMessage(player);
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
        player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 260, 1));
    }

    @Override
    public int getWaitTime() {
        return 10;
    }

    @Override
    public int getMaxIterations() {
        return 60;
    }

}
