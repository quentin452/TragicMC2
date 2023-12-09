package tragicneko.tragicmc.worldgen.schematic;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicItems;

public class SchematicSoulTomb extends Schematic {

    public static Block[] blocks = new Block[] { TragicBlocks.DarkCobblestone, TragicBlocks.DarkenedQuartz,
        TragicBlocks.SmoothNetherrack, Blocks.stonebrick, TragicBlocks.ScorchedRock, TragicBlocks.ErodedStone };

    public SchematicSoulTomb() {
        super(10, 6, 6);
    }

    @Override
    public boolean generateStructure(int variant, World world, Random rand, int x, int y, int z) {

        for (int y1 = 0; y1 < 10; y1++) {
            for (int x1 = -3; x1 < 4; x1++) {
                for (int z1 = -3; z1 < 4; z1++) {
                    world.setBlockToAir(x + x1, y + y1, z + z1);
                }
            }
        }

        Block block = blocks[variant];

        for (int x1 = -3; x1 < 4; x1++) {
            for (int z1 = -3; z1 < 4; z1++) {
                if (Math.abs(x1) != 3 && Math.abs(z1) != 3 && Math.abs(x1) != Math.abs(z1))
                    world.setBlock(x + x1, y, z + z1, block, 1, 2);
            }
        }

        world.setBlock(x, y, z, block, 1, 2);
        world.setBlock(x + 1, y, z + 1, Blocks.lava);
        world.setBlock(x + 1, y, z - 1, Blocks.lava);
        world.setBlock(x - 1, y, z + 1, Blocks.lava);
        world.setBlock(x - 1, y, z - 1, Blocks.lava);

        world.setBlock(x + 3, y + 1, z, block, 1, 2);
        world.setBlock(x - 3, y + 1, z, block, 1, 2);
        world.setBlock(x, y + 1, z + 3, block, 1, 2);
        world.setBlock(x, y + 1, z - 3, block, 1, 2);

        world.setBlock(x + 3, y + 1, z + 1, block, 1, 2);
        world.setBlock(x - 3, y + 1, z + 1, block, 1, 2);
        world.setBlock(x + 1, y + 1, z + 3, block, 1, 2);
        world.setBlock(x + 1, y + 1, z - 3, block, 1, 2);

        world.setBlock(x + 3, y + 1, z - 1, block, 1, 2);
        world.setBlock(x - 3, y + 1, z - 1, block, 1, 2);
        world.setBlock(x - 1, y + 1, z + 3, block, 1, 2);
        world.setBlock(x - 1, y + 1, z - 3, block, 1, 2);

        for (int y1 = 1; y1 < 8; y1++) {
            world.setBlock(x + 2, y + y1, z + 2, block, 1, 2);
            world.setBlock(x + 2, y + y1, z - 2, block, 1, 2);
            world.setBlock(x - 2, y + y1, z + 2, block, 1, 2);
            world.setBlock(x - 2, y + y1, z - 2, block, 1, 2);
        }

        for (int y1 = 1; y1 < 8; y1++) {
            world.setBlock(x + 4, y + y1, z, block, 1, 2);
            world.setBlock(x - 4, y + y1, z, block, 1, 2);
            world.setBlock(x, y + y1, z + 4, block, 1, 2);
            world.setBlock(x, y + y1, z - 4, block, 1, 2);
        }

        for (int y1 = 6; y1 < 8; y1++) {
            world.setBlock(x + 3, y + y1, z, block, 1, 2);
            world.setBlock(x - 3, y + y1, z, block, 1, 2);
            world.setBlock(x, y + y1, z + 3, block, 1, 2);
            world.setBlock(x, y + y1, z - 3, block, 1, 2);
        }

        for (int x1 = -3; x1 < 4; x1++) {
            world.setBlock(x + x1, y + 6, z + 3, block, 1, 2);
            world.setBlock(x + x1, y + 6, z - 3, block, 1, 2);
            world.setBlock(x + 3, y + 6, z + x1, block, 1, 2);
            world.setBlock(x - 3, y + 6, z + x1, block, 1, 2);
        }

        for (int x1 = -2; x1 < 3; x1++) {
            for (int z1 = -2; z1 < 3; z1++) {
                if (Math.abs(x1) != 3 && Math.abs(z1) != 3 && Math.abs(x1) != Math.abs(z1))
                    world.setBlock(x + x1, y + 7, z + z1, block, 1, 2);
            }
        }

        world.setBlock(x, y + 7, z, block, 1, 2);

        world.setBlock(x + 2, y + 8, z, block, 1, 2);
        world.setBlock(x - 2, y + 8, z, block, 1, 2);
        world.setBlock(x, y + 8, z + 2, block, 1, 2);
        world.setBlock(x, y + 8, z - 2, block, 1, 2);

        world.setBlock(x + 1, y + 9, z, block, 1, 2);
        world.setBlock(x - 1, y + 9, z, block, 1, 2);
        world.setBlock(x, y + 9, z + 1, block, 1, 2);
        world.setBlock(x, y + 9, z - 1, block, 1, 2);

        world.setBlock(x, y + 9, z, block, 1, 2);
        world.setBlock(x, y + 10, z, block, 1, 2);

        world.setBlock(x, y + 1, z, TragicBlocks.SoulChest, 0, 2);
        this.applyChestContents(world, rand, x, y + 1, z, TragicItems.NetherStructureHook);

        world.setBlock(x, y + 8, z, block, 1, 2);
        world.setBlock(x, y + 8, z + 1, Blocks.flowing_lava, 0, 2);
        world.setBlock(x, y + 8, z - 1, Blocks.flowing_lava, 0, 2);
        world.setBlock(x + 1, y + 8, z, Blocks.flowing_lava, 0, 2);
        world.setBlock(x - 1, y + 8, z, Blocks.flowing_lava, 0, 2);

        return true;
    }

}
