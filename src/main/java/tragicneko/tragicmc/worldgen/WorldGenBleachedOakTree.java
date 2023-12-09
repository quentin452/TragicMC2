package tragicneko.tragicmc.worldgen;

import static net.minecraft.init.Blocks.dirt;
import static tragicneko.tragicmc.TragicBlocks.BleachedLeaves;
import static tragicneko.tragicmc.TragicBlocks.BleachedWood;
import static tragicneko.tragicmc.TragicBlocks.DeadDirt;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import tragicneko.tragicmc.blocks.BlockGenericGrass;
import tragicneko.tragicmc.util.WorldHelper;

public class WorldGenBleachedOakTree extends WorldGenAbstractTree {

    private boolean isLargeTree;

    public WorldGenBleachedOakTree(boolean flag, boolean flag2) {
        super(flag);
        this.isLargeTree = flag;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        int[] coords;
        Block block;

        block = world.getBlock(x, y, z);
        if (!(block instanceof BlockGenericGrass) && !(block instanceof BlockGrass)
            && block != dirt
            && block != DeadDirt
            && !(block instanceof BlockBush)) return false;

        double scale = this.isLargeTree ? rand.nextDouble() * 4.25D + 1.65D : rand.nextDouble() * 3.25D + 1.15D;
        if (scale < 2.0) return false;

        ArrayList<int[]> list = WorldHelper
            .getBlocksInSphericalRange(world, scale * 0.5D, x, y + 1 + scale * 3.0 / 4.0, z);

        for (int y1 = 0; y1 < 1 + scale * 3.0 / 4.0; y1++) {
            block = world.getBlock(x, y + y1, z);
            if (block.canBeReplacedByLeaves(world, x, y + y1, z)) world.setBlock(x, y + y1, z, BleachedWood);
        }

        for (int i = 0; i < list.size(); i++) {
            coords = list.get(i);
            block = world.getBlock(coords[0], coords[1], coords[2]);

            if (block.canBeReplacedByLeaves(world, coords[0], coords[1], coords[2])
                && block.getMaterial() != Material.wood)
                world.setBlock(coords[0], coords[1], coords[2], BleachedLeaves);
        }

        if (this.isLargeTree && scale > 3.0) {
            double dif = rand.nextDouble() * 3.75D * (rand.nextBoolean() ? -1 : 1);
            list = WorldHelper.getBlocksInSphericalRange(world, scale * 0.5D, x + dif, y + 1 + scale * 3.0 / 4.0, z);

            for (int i = 0; i < list.size(); i++) {
                coords = list.get(i);
                block = world.getBlock(coords[0], coords[1], coords[2]);

                if (block.canBeReplacedByLeaves(world, coords[0], coords[1], coords[2])
                    && block.getMaterial() != Material.wood)
                    world.setBlock(coords[0], coords[1], coords[2], BleachedLeaves);
            }

            world.setBlock((int) (x + dif), (int) (y + 1 + scale * 2.0 / 3.0D), z, BleachedWood);

            list = WorldHelper
                .getBlocksInSphericalRange(world, scale * 0.5D, x - dif, y + 1 + scale * 3.0 / 4.0, z - dif);

            for (int i = 0; i < list.size(); i++) {
                coords = list.get(i);
                block = world.getBlock(coords[0], coords[1], coords[2]);

                if (block.canBeReplacedByLeaves(world, coords[0], coords[1], coords[2])
                    && block.getMaterial() != Material.wood)
                    world.setBlock(coords[0], coords[1], coords[2], BleachedLeaves);
            }

            world.setBlock((int) (x - dif), (int) (y + 1 + scale * 2.0 / 3.0D), (int) (z - dif), BleachedWood);

            list = WorldHelper.getBlocksInSphericalRange(
                world,
                scale * 0.5D,
                x + dif * 1.5D,
                y + 1 + scale * 3.0 / 4.0,
                z - dif * 0.5D);

            for (int i = 0; i < list.size(); i++) {
                coords = list.get(i);
                block = world.getBlock(coords[0], coords[1], coords[2]);

                if (block.canBeReplacedByLeaves(world, coords[0], coords[1], coords[2])
                    && block.getMaterial() != Material.wood)
                    world.setBlock(coords[0], coords[1], coords[2], BleachedLeaves);
            }

            world.setBlock(
                (int) (x + dif * 1.5D),
                (int) (y + 1 + scale * 2.0 / 3.0D),
                (int) (z - dif * 0.5D),
                BleachedWood);
        }

        return true;
    }

}
