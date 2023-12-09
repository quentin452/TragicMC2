package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.util.WorldHelper;

public class HangingCoralWorldGen extends WorldGenerator {

    public final int width;
    public final int height;
    public final int iterations;
    public final Block block;
    public final byte meta;

    public HangingCoralWorldGen(int width, int height, int iterations, Block block, byte meta) {
        this.width = width;
        this.height = height;
        this.iterations = iterations;
        this.block = block;
        this.meta = meta;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        if (!world.isAirBlock(x, y, z)) {
            return false;
        } else if (world.getBlock(x, y + 1, z) != TragicBlocks.CircuitBlock) {
            return false;
        } else {
            world.setBlock(x, y, z, block, meta, 2);

            for (int l = 0; l < this.iterations; ++l) {
                int i1 = x + rand.nextInt(width) - rand.nextInt(width); // 4
                int j1 = y - rand.nextInt(height); // 32
                int k1 = z + rand.nextInt(width) - rand.nextInt(width);

                if (world.getBlock(i1, j1, k1)
                    .getMaterial() == Material.air) {
                    byte b = 0;
                    ArrayList<int[]> list = WorldHelper.getBlocksAdjacent(new int[] { i1, j1, k1 });
                    for (int[] coords : list) {
                        if (world.getBlock(coords[0], coords[1], coords[2]) == block) b++;
                    }

                    if (b == 1) world.setBlock(i1, j1, k1, block, meta, 2);
                }
            }

            return true;
        }
    }
}
