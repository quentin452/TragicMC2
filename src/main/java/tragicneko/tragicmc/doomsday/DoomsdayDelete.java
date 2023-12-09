package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;

import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class DoomsdayDelete extends Doomsday {

    public DoomsdayDelete(int id) {
        super(id, EnumDoomType.WORLDSHAPER);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

        double d = crucMoment ? 40.0 : 30.0;
        for (double d0 = 1.0; d0 < d; d0 += 0.5D) {
            Vec3 vec = WorldHelper.getVecFromEntity(player, d0);
            List<int[]> list = WorldHelper.getBlocksInSphericalRange(
                player.worldObj,
                crucMoment ? 5.0 : 2.00,
                vec.xCoord,
                vec.yCoord,
                vec.zCoord);

            for (int[] coords : list) {
                float f = player.worldObj.getBlock(coords[0], coords[1], coords[2])
                    .getBlockHardness(player.worldObj, coords[0], coords[1], coords[2]);
                if (f > 0F && f < 16F && player.worldObj.canMineBlock(player, coords[0], coords[1], coords[2])) {
                    player.worldObj.setBlockToAir(coords[0], coords[1], coords[2]);
                }
            }
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

    }

}
