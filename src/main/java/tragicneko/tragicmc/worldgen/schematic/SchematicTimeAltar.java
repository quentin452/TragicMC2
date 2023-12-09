package tragicneko.tragicmc.worldgen.schematic;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.util.WorldHelper;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SchematicTimeAltar extends Schematic {

    private static Block quartz = Blocks.quartz_block;
    private static Block crystal = TragicBlocks.StarCrystal;
    private static Block summon = TragicBlocks.SummonBlock;
    private static Block chest = Blocks.chest;

    public SchematicTimeAltar() {
        super(5, 10, 10);
    }

    @Override
    public boolean generateStructure(int variant, World world, Random rand, int x, int y, int z) {
        for (int y1 = 0; y1 < 6; y1++) {
            for (int x1 = -7; x1 < 8; x1++) {
                for (int z1 = -7; z1 < 8; z1++) {
                    world.setBlockToAir(x + x1, y + y1, z + z1);
                }
            }
        }

        ArrayList<int[]> list;
        int[] coords;
        Block block;

        list = WorldHelper.getBlocksInCircularRange(world, 8.0D, x, y, z);

        for (int i = 0; i < list.size(); i++) // creates a giant circle of quartz with star crystal inside of it
                                              // sparingly
        {
            coords = list.get(i);
            block = world.getBlock(coords[0], coords[1], coords[2]);
            if (Structure.validBlocks.contains(block)) world.setBlock(coords[0], coords[1], coords[2], quartz, 0, 2);
        }

        list = WorldHelper.getBlocksInCircularRange(world, 4.446D, x, y, z);

        for (int i = 0; i < list.size(); i++) // creates a smaller hemisphere of star crystal to provide lighting
        {
            coords = list.get(i);
            block = world.getBlock(coords[0], coords[1], coords[2]);
            if (block == quartz) world.setBlock(coords[0], coords[1], coords[2], crystal, variant, 2);
        }

        // Creates a structure in the middle that you can use to create another time controller, it comes with a summon
        // block on top though
        world.setBlock(x, y + 1, z, quartz);
        world.setBlock(x, y + 2, z, quartz);
        world.setBlock(x, y + 2, z + 1, quartz);
        world.setBlock(x, y + 2, z - 1, quartz);
        world.setBlock(x + 1, y + 2, z, quartz);
        world.setBlock(x - 1, y + 2, z, quartz);

        world.setBlock(x, y + 3, z, summon, 7, 2);

        world.setBlock(x, y, z, chest, 0, 2);
        this.applyChestContents(world, rand, x, y, z, TragicItems.AwesomeChestHook);

        world.setBlock(x + 1, y, z, quartz); // blocks to ensure the chest is concealed
        world.setBlock(x - 1, y, z, quartz);
        world.setBlock(x, y, z + 1, quartz);
        world.setBlock(x, y, z - 1, quartz);

        return true;
    }
}
