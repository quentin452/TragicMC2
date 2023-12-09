package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.util.WorldHelper;

public class WorldGenBleachedTree extends WorldGenAbstractTree {

    private boolean isLargeTree;

    public WorldGenBleachedTree(boolean p_i45448_1_, boolean isLargeTree) {
        super(p_i45448_1_);
        this.isLargeTree = isLargeTree;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {

        // if (!WorldHelper.validBlocksForDimension.contains(world.getBlock(x, y, z)) || random.nextInt(3) != 0) return
        // false;
        double size = random.nextDouble() * 2.0D + 3.5D;
        if (this.isLargeTree) size = random.nextDouble() * 3.0D + 6.5D;

        ArrayList<int[]> list = WorldHelper
            .getBlocksInSphericalRange(world, size / 2.0D * 3.0D, x, y + (size * 0.785D), z);
        int[] coords;
        Block block;

        for (int i = 0; i < list.size(); i++) // creates a dome shaped leaf coverage with less chance for leaves at
                                              // lower heights to be placed
        {
            coords = list.get(i);
            block = world.getBlock(coords[0], coords[1], coords[2]);

            if (block.canBeReplacedByLeaves(world, coords[0], coords[1], coords[2])
                && coords[1] >= (y + (size * 0.555D))) {
                world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.BleachedLeaves);
            }
        }

        list = WorldHelper.getBlocksInSphericalRange(world, size / 2.0D * 3.0D, x, y + (size * 0.795D) - 2.0D, z);

        for (int i = 0; i < list.size(); i++) // removes extra leaves that are too low
        {
            coords = list.get(i);
            block = world.getBlock(coords[0], coords[1], coords[2]);

            if (block == TragicBlocks.BleachedLeaves) {
                world.setBlockToAir(coords[0], coords[1], coords[2]);
            }
        }

        if (size > 8.0D) {
            list = WorldHelper.getBlocksInSphericalRange(world, size / 3.0D, x + 5, y + size + 2.5D, z);

            for (int i = 0; i < list.size(); i++) {
                coords = list.get(i);
                block = world.getBlock(coords[0], coords[1], coords[2]);

                if (block.canBeReplacedByLeaves(world, coords[0], coords[1], coords[2])) {
                    world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.BleachedWood);
                }
            }

            list = WorldHelper.getBlocksInSphericalRange(world, size / 3.0D, x - 5, y + size + 2.5D, z);

            for (int i = 0; i < list.size(); i++) {
                coords = list.get(i);
                block = world.getBlock(coords[0], coords[1], coords[2]);

                if (block.canBeReplacedByLeaves(world, coords[0], coords[1], coords[2])) {
                    world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.BleachedWood);
                }
            }

            list = WorldHelper.getBlocksInSphericalRange(world, size / 3.0D, x, y + size + 2.5D, z + 5);

            for (int i = 0; i < list.size(); i++) {
                coords = list.get(i);
                block = world.getBlock(coords[0], coords[1], coords[2]);

                if (block.canBeReplacedByLeaves(world, coords[0], coords[1], coords[2])) {
                    world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.BleachedWood);
                }
            }

            list = WorldHelper.getBlocksInSphericalRange(world, size / 3.0D, x + 5, y + size + 2.5D, z - 5);

            for (int i = 0; i < list.size(); i++) {
                coords = list.get(i);
                block = world.getBlock(coords[0], coords[1], coords[2]);

                if (block.canBeReplacedByLeaves(world, coords[0], coords[1], coords[2])) {
                    world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.BleachedWood);
                }
            }
        }

        list = WorldHelper.getBlocksInSphericalRange(world, size / 2.0D, x, y + size + 1.0D, z);

        for (int i = 0; i < list.size(); i++) {
            coords = list.get(i);
            block = world.getBlock(coords[0], coords[1], coords[2]);

            if (block.canBeReplacedByLeaves(world, coords[0], coords[1], coords[2])) {
                world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.BleachedWood);
            }
        }

        list = WorldHelper.getBlocksInSphericalRange(world, size / 4.0D, x, y + (size / 2.0D), z);

        for (int i = 0; i < list.size(); i++) {
            coords = list.get(i);
            block = world.getBlock(coords[0], coords[1], coords[2]);

            if (block.canBeReplacedByLeaves(world, coords[0], coords[1], coords[2])) {
                world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.BleachedWood);
            }
        }

        list = WorldHelper.getBlocksInSphericalRange(world, size / 2.0D, x, y, z);

        for (int i = 0; i < list.size(); i++) {
            coords = list.get(i);
            block = world.getBlock(coords[0], coords[1], coords[2]);

            if (block.canBeReplacedByLeaves(world, coords[0], coords[1], coords[2])) {
                world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.BleachedWood);
            }
        }

        list = WorldHelper.getBlocksInCircularRange(world, size / 3.0D * 4.0D, x, y, z); // to make the roots appear
                                                                                         // more widespread

        for (int i = 0; i < list.size(); i++) {
            coords = list.get(i);
            block = world.getBlock(coords[0], coords[1], coords[2]);
            if (block.canBeReplacedByLeaves(world, coords[0], coords[1], coords[2]) && random.nextInt(4) == 0)
                world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.BleachedWood);
        }

        return true;
    }

}
