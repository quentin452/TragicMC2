package tragicneko.tragicmc.worldgen.schematic;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicItems;

public class SchematicDarkHut extends Schematic {

    public SchematicDarkHut() {
        super(13, 5, 7);
    }

    @Override
    public boolean generateStructure(int variant, World world, Random rand, int x, int y, int z) {

        for (byte y1 = 0; y1 < 13; y1++) {
            for (byte x1 = -4; x1 < 5 && y1 > 2; x1++) {
                for (byte z1 = -4; z1 < 5; z1++) {
                    world.setBlockToAir(x + x1, y + y1, z + z1);
                }
            }

            if (y1 == 0 && world.getBlock(x, y, z) == TragicBlocks.StructureSeed) world.setBlockToAir(x, y, z);

            if (y1 > 3 || world.getBlock(x + 2, y + y1, z - 3)
                .isAir(world, x + 2, y + y1, z - 3)) world.setBlock(x + 2, y + y1, z - 3, TragicBlocks.Darkwood, 0, 2);
            if (y1 > 3 || world.getBlock(x - 3, y + y1, z - 3)
                .isAir(world, x + 2, y + y1, z - 3)) world.setBlock(x - 3, y + y1, z - 3, TragicBlocks.Darkwood, 0, 2);
            if (y1 > 3 || world.getBlock(x + 2, y + y1, z + 3)
                .isAir(world, x + 2, y + y1, z - 3)) world.setBlock(x + 2, y + y1, z + 3, TragicBlocks.Darkwood, 0, 2);
            if (y1 > 3 || world.getBlock(x - 3, y + y1, z + 3)
                .isAir(world, x + 2, y + y1, z - 3)) world.setBlock(x - 3, y + y1, z + 3, TragicBlocks.Darkwood, 0, 2);

            if (y1 == 4) {
                for (byte x1 = -2; x1 < 3; x1++) {
                    world.setBlock(x + x1, y + y1, z + 3, TragicBlocks.Darkwood, 4, 2);
                    world.setBlock(x + x1, y + y1, z - 3, TragicBlocks.Darkwood, 4, 2);
                }

                for (byte x1 = -2; x1 < 3; x1++) {
                    world.setBlock(x + 2, y + y1, z + x1, TragicBlocks.Darkwood, 0, 2);
                    world.setBlock(x - 3, y + y1, z + x1, TragicBlocks.Darkwood, 0, 2);
                }

                world.setBlock(x + 2, y + y1, z + 4, TragicBlocks.Darkwood, 8, 2);
                world.setBlock(x - 3, y + y1, z + 4, TragicBlocks.Darkwood, 8, 2);
                world.setBlock(x + 2, y + y1, z - 4, TragicBlocks.Darkwood, 8, 2);
                world.setBlock(x - 3, y + y1, z - 4, TragicBlocks.Darkwood, 8, 2);

                world.setBlock(x + 3, y + y1, z + 3, TragicBlocks.Darkwood, 4, 2);
                world.setBlock(x - 4, y + y1, z + 3, TragicBlocks.Darkwood, 4, 2);
                world.setBlock(x + 3, y + y1, z - 3, TragicBlocks.Darkwood, 4, 2);
                world.setBlock(x - 4, y + y1, z - 3, TragicBlocks.Darkwood, 4, 2);

                world.setBlock(x + 3, y + y1, z + 1, TragicBlocks.Darkwood, 4, 2);
                world.setBlock(x - 4, y + y1, z + 1, TragicBlocks.Darkwood, 4, 2);
                world.setBlock(x + 3, y + y1, z - 1, TragicBlocks.Darkwood, 4, 2);
                world.setBlock(x - 4, y + y1, z - 1, TragicBlocks.Darkwood, 4, 2);

                world.setBlock(x, y + y1, z - 4, TragicBlocks.Darkwood, 8, 2);
                world.setBlock(x - 1, y + y1, z - 4, TragicBlocks.Darkwood, 8, 2);
                world.setBlock(x, y + y1, z - 5, TragicBlocks.Darkwood, 8, 2);
                world.setBlock(x - 1, y + y1, z - 5, TragicBlocks.Darkwood, 8, 2);

                world.setBlock(x, y + y1, z + 4, TragicBlocks.Darkwood, 8, 2);
                world.setBlock(x - 1, y + y1, z + 4, TragicBlocks.Darkwood, 8, 2);
            } else if (y1 == 5) {
                for (byte x1 = -2; x1 < 3; x1++) {
                    world.setBlock(x + x1, y + y1, z + 3, TragicBlocks.Darkwood, 0, 2);
                    world.setBlock(x + x1, y + y1, z - 3, TragicBlocks.Darkwood, 0, 2);
                    world.setBlock(x + 2, y + y1, z + x1, TragicBlocks.Darkwood, 0, 2);
                    world.setBlock(x - 3, y + y1, z + x1, TragicBlocks.Darkwood, 0, 2);
                }

                world.setBlock(x + 3, y + y1, z + 1, TragicBlocks.Darkwood, 0, 2);
                world.setBlock(x - 4, y + y1, z + 1, TragicBlocks.Darkwood, 0, 2);
                world.setBlock(x + 3, y + y1, z - 1, TragicBlocks.Darkwood, 0, 2);
                world.setBlock(x - 4, y + y1, z - 1, TragicBlocks.Darkwood, 0, 2);

                world.setBlock(x, y + y1, z - 4, TragicBlocks.Darkwood, 0, 2);
                world.setBlock(x - 1, y + y1, z - 4, TragicBlocks.Darkwood, 0, 2);

                world.setBlock(x + 1, y + y1, z - 4, TragicBlocks.Darkwood, 0, 2);
                world.setBlock(x - 2, y + y1, z - 4, TragicBlocks.Darkwood, 0, 2);

                for (byte x1 = -2; x1 < 2; x1++) {
                    for (byte z1 = -2; z1 < 3; z1++) {
                        world.setBlock(x + x1, y + y1, z + z1, TragicBlocks.DarkwoodPlanks, 0, 2);
                    }
                }
            } else if (y1 == 6 || y1 == 8) {
                for (byte x1 = -2; x1 < 3; x1++) {
                    world.setBlock(x + x1, y + y1, z + 3, TragicBlocks.DarkwoodPlanks, 0, 2);
                    world.setBlock(x + 2, y + y1, z + x1, TragicBlocks.DarkwoodPlanks, 0, 2);
                    world.setBlock(x - 3, y + y1, z + x1, TragicBlocks.DarkwoodPlanks, 0, 2);
                }

                world.setBlock(x - 2, y + y1, z - 3, TragicBlocks.DarkwoodPlanks, 0, 2);
                world.setBlock(x + 1, y + y1, z - 3, TragicBlocks.DarkwoodPlanks, 0, 2);

                if (y1 == 6) {
                    world.setBlock(x - 2, y + y1, z - 4, TragicBlocks.Darkwood, 0, 2);
                    world.setBlock(x + 1, y + y1, z - 4, TragicBlocks.Darkwood, 0, 2);

                    world.setBlock(x + 1, y + y1, z - 2, Blocks.anvil, 2, 2);
                    world.setBlock(x - 2, y + y1, z - 2, Blocks.trapped_chest, 0, 2);
                    world.setBlock(x - 2, y + y1, z - 1, Blocks.trapped_chest, 0, 2);
                    this.applyChestContents(world, rand, x - 2, y + y1, z - 2, TragicItems.LameChestHook);
                    this.applyChestContents(world, rand, x - 2, y + y1, z - 1, TragicItems.LameChestHook);

                    world.setBlock(x + 1, y + y1, z + 2, Blocks.crafting_table, 0, 2);

                    world.setBlock(x, y + y1, z + 2, Blocks.bed, 8, 2);
                    world.setBlock(x, y + y1, z + 1, Blocks.bed, 0, 2);
                    world.setBlock(x - 1, y + y1, z + 2, Blocks.bed, 8, 2);
                    world.setBlock(x - 1, y + y1, z + 1, Blocks.bed, 0, 2);

                    world.setBlock(x - 2, y + y1, z + 2, Blocks.ender_chest, 3, 2);
                } else {
                    for (byte x1 = -2; x1 < 2; x1++)
                        world.setBlock(x + x1, y + y1, z - 3, TragicBlocks.DarkwoodPlanks, 0, 2);
                }
            } else if (y1 == 7) {
                world.setBlock(x - 2, y + y1, z + 3, TragicBlocks.DarkwoodPlanks, 0, 2);
                world.setBlock(x + 1, y + y1, z + 3, TragicBlocks.DarkwoodPlanks, 0, 2);

                world.setBlock(x - 2, y + y1, z - 3, TragicBlocks.DarkwoodPlanks, 0, 2);
                world.setBlock(x + 1, y + y1, z - 3, TragicBlocks.DarkwoodPlanks, 0, 2);

                world.setBlock(x - 3, y + y1, z + 2, TragicBlocks.DarkwoodPlanks, 0, 2);
                world.setBlock(x + 2, y + y1, z - 2, TragicBlocks.DarkwoodPlanks, 0, 2);

                world.setBlock(x - 3, y + y1, z - 2, TragicBlocks.DarkwoodPlanks, 0, 2);
                world.setBlock(x + 2, y + y1, z + 2, TragicBlocks.DarkwoodPlanks, 0, 2);
            } else if (y1 == 9) {
                for (byte x1 = -2; x1 < 3; x1++) {
                    world.setBlock(x + x1, y + y1, z + 3, TragicBlocks.Darkwood, 0, 2);
                    world.setBlock(x + x1, y + y1, z - 3, TragicBlocks.Darkwood, 0, 2);
                }

                for (byte x1 = -4; x1 < 4; x1++) {
                    world.setBlock(x + x1, y + y1, z + 4, TragicBlocks.Darkwood, 4, 2);
                    world.setBlock(x + x1, y + y1, z - 4, TragicBlocks.Darkwood, 4, 2);
                }

                for (byte x1 = -2; x1 < 3; x1++) {
                    world.setBlock(x + 2, y + y1, z + x1, TragicBlocks.Darkwood, 8, 2);
                    world.setBlock(x - 3, y + y1, z + x1, TragicBlocks.Darkwood, 8, 2);
                }

                world.setBlock(x + 2, y + y1, z + 5, TragicBlocks.Darkwood, 8, 2);
                world.setBlock(x - 3, y + y1, z + 5, TragicBlocks.Darkwood, 8, 2);
                world.setBlock(x + 2, y + y1, z - 5, TragicBlocks.Darkwood, 8, 2);
                world.setBlock(x - 3, y + y1, z - 5, TragicBlocks.Darkwood, 8, 2);

                world.setBlock(x + 3, y + y1, z + 1, TragicBlocks.Darkwood, 4, 2);
                world.setBlock(x - 4, y + y1, z + 1, TragicBlocks.Darkwood, 4, 2);
                world.setBlock(x + 3, y + y1, z - 1, TragicBlocks.Darkwood, 4, 2);
                world.setBlock(x - 4, y + y1, z - 1, TragicBlocks.Darkwood, 4, 2);

                for (byte x1 = -2; x1 < 2; x1++) {
                    world.setBlock(x + x1, y + y1, z - 1, TragicBlocks.Darkwood, 4, 2);
                    world.setBlock(x + x1, y + y1, z + 1, TragicBlocks.Darkwood, 4, 2);
                }
            } else if (y1 == 10) {
                for (byte x1 = -2; x1 < 3; x1++) {
                    world.setBlock(x + 1, y + y1, z + x1, TragicBlocks.DarkwoodPlanks, 0, 2);
                    world.setBlock(x - 2, y + y1, z + x1, TragicBlocks.DarkwoodPlanks, 0, 2);
                }

                for (byte x1 = -2; x1 < 2; x1++) {
                    world.setBlock(x + x1, y + y1, z - 3, TragicBlocks.Darkwood, 0, 2);
                    world.setBlock(x + x1, y + y1, z + 3, TragicBlocks.Darkwood, 0, 2);
                }

                world.setBlock(x + 3, y + y1, z + 1, TragicBlocks.Darkwood, 0, 2);
                world.setBlock(x - 4, y + y1, z + 1, TragicBlocks.Darkwood, 0, 2);
                world.setBlock(x + 3, y + y1, z - 1, TragicBlocks.Darkwood, 0, 2);
                world.setBlock(x - 4, y + y1, z - 1, TragicBlocks.Darkwood, 0, 2);

                world.setBlock(x - 1, y + y1, z - 4, TragicBlocks.Darkwood, 0, 2);
                world.setBlock(x, y + y1, z - 4, TragicBlocks.Darkwood, 0, 2);

                world.setBlock(x - 1, y + y1, z + 4, TragicBlocks.Darkwood, 0, 2);
                world.setBlock(x, y + y1, z + 4, TragicBlocks.Darkwood, 0, 2);

                world.setBlock(x - 1, y + y1, z + 1, Blocks.mob_spawner, 0, 2);
                this.setSpawnerMob(world, x - 1, y + y1, z + 1, "Witch");

                world.setBlock(x, y + y1, z + 1, Blocks.mob_spawner, 0, 2);
                this.setSpawnerMob(world, x, y + y1, z + 1, "Witch");

                world.setBlock(x - 1, y + y1, z - 1, Blocks.mob_spawner, 0, 2);
                this.setSpawnerMob(world, x - 1, y + y1, z - 1, "Witch");

                world.setBlock(x, y + y1, z - 1, Blocks.mob_spawner, 0, 2);
                this.setSpawnerMob(world, x, y + y1, z - 1, "Witch");
            } else if (y1 == 11) {
                for (byte x1 = -2; x1 < 3; x1++) {
                    world.setBlock(x, y + y1, z + x1, TragicBlocks.DarkwoodPlanks, 0, 2);
                    world.setBlock(x - 1, y + y1, z + x1, TragicBlocks.DarkwoodPlanks, 0, 2);
                }

                for (byte x1 = -2; x1 < 2; x1++) {
                    world.setBlock(x + x1, y + y1, z - 3, TragicBlocks.Darkwood, 0, 2);
                    world.setBlock(x + x1, y + y1, z + 3, TragicBlocks.Darkwood, 0, 2);
                }
            } else if (y1 == 12) {
                world.setBlock(x - 1, y + y1, z - 3, TragicBlocks.Darkwood, 0, 2);
                world.setBlock(x, y + y1, z - 3, TragicBlocks.Darkwood, 0, 2);

                world.setBlock(x - 1, y + y1, z + 3, TragicBlocks.Darkwood, 0, 2);
                world.setBlock(x, y + y1, z + 3, TragicBlocks.Darkwood, 0, 2);
            }
        }

        return true;
    }

}
