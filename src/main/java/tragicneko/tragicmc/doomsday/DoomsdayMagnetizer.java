package tragicneko.tragicmc.doomsday;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.chunk.Chunk;

import tragicneko.tragicmc.blocks.BlockGenericOre;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.Tuple;
import tragicneko.tragicmc.util.WorldHelper;

public class DoomsdayMagnetizer extends Doomsday {

    public DoomsdayMagnetizer(int id) {
        super(id, EnumDoomType.WORLDSHAPER);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        List<int[]> list2 = WorldHelper.getBlocksInSphericalRange(
            player.worldObj,
            crucMoment ? 7.25 : 4.25,
            player.posX + rand.nextInt(8) - rand.nextInt(8),
            player.posY + rand.nextInt(8),
            player.posZ + rand.nextInt(8) - rand.nextInt(8));
        List<int[]> cands = new ArrayList();
        Block block;

        for (int[] coords : list2) {
            block = player.worldObj.getBlock(coords[0], coords[1], coords[2]);
            if (block.isAir(player.worldObj, coords[0], coords[1], coords[2])) {
                cands.add(coords);
            }
        }

        int amt = 0;
        Chunk chk = player.worldObj.getChunkFromBlockCoords((int) player.posX, (int) player.posZ);
        List<Tuple<Block, Integer>> ores = new ArrayList();
        for (int x1 = 0; x1 < 16; x1++) {
            for (int z1 = 0; z1 < 16; z1++) {
                for (int y1 = 0; y1 < 255; y1++) {
                    block = chk.getBlock(x1, y1, z1);
                    if (block instanceof BlockOre || block instanceof BlockGenericOre) {
                        ores.add(new Tuple(block, chk.getBlockMetadata(x1, y1, z1)));
                        player.worldObj.setBlockToAir((chk.xPosition * 16) + x1, y1, (chk.zPosition * 16) + z1);
                        if (amt++ >= cands.size()) break;
                    }
                }
            }
        }

        Collections.shuffle(ores);
        Collections.shuffle(cands);

        for (int i = 0; i < ores.size() && i < cands.size(); i++) {
            int[] coords = cands.get(i);
            Tuple<Block, Integer> tup = ores.get(i);
            player.worldObj.setBlock(coords[0], coords[1], coords[2], tup.getLeft(), tup.getRight(), 3);
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

    }
}
