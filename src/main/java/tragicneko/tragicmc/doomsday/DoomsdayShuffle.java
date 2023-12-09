package tragicneko.tragicmc.doomsday;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;

import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class DoomsdayShuffle extends Doomsday implements IExtendedDoomsday {

    public DoomsdayShuffle(int id) {
        super(id);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        List<int[]> list = WorldHelper.getBlocksInSphericalRange(
            player.worldObj,
            crucMoment ? 8.25 : 5.25,
            player.posX,
            player.posY,
            player.posZ);
        List<int[]> cands = new ArrayList<int[]>();

        for (int[] coords : list) {
            Block block = player.worldObj.getBlock(coords[0], coords[1], coords[2]);
            float f = block.getBlockHardness(player.worldObj, coords[0], coords[1], coords[2]);
            if (!block.isAir(player.worldObj, coords[0], coords[1], coords[2]) && !block.getMaterial()
                .isLiquid() && f > 0F && f < 48F) cands.add(coords);
        }

        for (int i = 0; i < 16; i++) {
            int[] coords = cands.get(rand.nextInt(cands.size()));
            Block block = player.worldObj.getBlock(coords[0], coords[1], coords[2]);
            int[] coord2 = cands.get(rand.nextInt(cands.size()));
            Block block2 = player.worldObj.getBlock(coord2[0], coord2[1], coord2[2]);

            player.worldObj.setBlock(coords[0], coords[1], coords[2], block2);
            player.worldObj.setBlock(coord2[0], coord2[1], coord2[2], block);
            Collections.shuffle(cands);
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

    }

    @Override
    public Doomsday getCombination() {
        return Doomsday.Blink;
    }

    @Override
    public int getWaitTime() {
        return 20;
    }

    @Override
    public int getMaxIterations() {
        return 20;
    }
}
