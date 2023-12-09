package tragicneko.tragicmc.worldgen.schematic;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;

public class SchematicKitsuneDen extends Schematic {

    private static Block fox = TragicBlocks.SmoothNetherrack; // 1 is chiseled, 2 is beveled, 3 is sculpted, 5 is molten
    private static Block chest = Blocks.chest;
    private static Block spawner = Blocks.mob_spawner;

    public SchematicKitsuneDen() {
        super(12, 9, 9);
    }

    @Override
    public boolean generateStructure(int variant, World world, Random rand, int x, int y, int z) {
        int dens = MathHelper.clamp_int(variant, 0, 4);

        for (int y1 = 0; y1 < 8; y1++) {
            for (int x1 = -5; x1 < 6; x1++) {
                for (int z1 = -5; z1 < 6; z1++) {
                    if (y1 == 0) {
                        world.setBlock(x + x1, y + y1, z + z1, fox, 1, 2);
                    } else {
                        world.setBlockToAir(x + x1, y + y1, z + z1);
                    }
                }
            }
        }

        for (int y1 = -1; y1 < 2; y1++) // sets the middle inset layer of sculpted blocks for the spawner or summon
                                        // block
        {
            for (int x1 = -2; x1 < 3; x1++) {
                for (int z1 = -2; z1 < 3; z1++) {
                    if (y1 == -1) {
                        world.setBlock(x + x1, y + y1, z + z1, fox, 3, 2);
                    } else {
                        world.setBlockToAir(x + x1, y + y1, z + z1);
                    }
                }
            }
        }

        world.setBlock(x + 2, y, z + 2, fox, 3, 2); // sets the blocks on the lower layer to round it out
        world.setBlock(x - 2, y, z + 2, fox, 3, 2);
        world.setBlock(x + 2, y, z - 2, fox, 3, 2);
        world.setBlock(x - 2, y, z - 2, fox, 3, 2);

        world.setBlock(x, y, z, chest, 0, 2);
        this.applyChestContents(world, rand, x, y, z, TragicItems.NetherStructureHook);

        world.setBlock(x, y + 1, z, spawner, 0, 2);
        this.setSpawnerMob(world, x, y + 1, z, TragicConfig.allowKitsunakuma ? "TragicMC.Kitsune" : "Blaze");

        for (int z1 = -1; z1 < 2; z1++) // sets the extra sculpted blocks on the 0 layer
        {
            world.setBlock(x + 3, y, z + z1, fox, 3, 2);
            world.setBlock(x - 3, y, z + z1, fox, 3, 2);
            world.setBlock(x + z1, y, z + 3, fox, 3, 2);
            world.setBlock(x + z1, y, z - 3, fox, 3, 2);
        }

        for (int z1 = -5; z1 < 6; z1++) // Sets the outer blocks as beveled
        {
            world.setBlock(x + 5, y, z + z1, fox, 2, 2);
            world.setBlock(x - 5, y, z + z1, fox, 2, 2);
            world.setBlock(x + z1, y, z + 5, fox, 2, 2);
            world.setBlock(x + z1, y, z - 5, fox, 2, 2);
        }

        world.setBlock(x + 4, y, z + 3, fox, 2, 2); // finishes setting the outer beveled netherrack
        world.setBlock(x + 4, y, z - 3, fox, 2, 2);
        world.setBlock(x - 4, y, z + 3, fox, 2, 2);
        world.setBlock(x - 4, y, z - 3, fox, 2, 2);
        world.setBlock(x + 3, y, z + 4, fox, 2, 2);
        world.setBlock(x + 3, y, z - 4, fox, 2, 2);
        world.setBlock(x - 3, y, z + 4, fox, 2, 2);
        world.setBlock(x - 3, y, z - 4, fox, 2, 2);

        for (int z1 = -5; z1 < -2; z1++) // Removes the corners
        {
            world.setBlockToAir(x + 5, y, z + z1);
            world.setBlockToAir(x - 5, y, z + z1);
            world.setBlockToAir(x + z1, y, z + 5);
            world.setBlockToAir(x + z1, y, z - 5);
        }

        for (int z1 = 3; z1 < 6; z1++) // Removes the opposite corners
        {
            world.setBlockToAir(x + 5, y, z + z1);
            world.setBlockToAir(x - 5, y, z + z1);
            world.setBlockToAir(x + z1, y, z + 5);
            world.setBlockToAir(x + z1, y, z - 5);
        }

        world.setBlockToAir(x + 4, y, z + 4); // finishes rounding out the corners
        world.setBlockToAir(x - 4, y, z + 4);
        world.setBlockToAir(x + 4, y, z - 4);
        world.setBlockToAir(x - 4, y, z - 4);

        for (int y1 = 1; y1 < 5; y1++) {
            world.setBlock(x + 5, y + y1, z + 3, fox, 2, 2); // sets the outer beveled edges
            world.setBlock(x - 5, y + y1, z + 3, fox, 2, 2);
            world.setBlock(x + 5, y + y1, z - 3, fox, 2, 2);
            world.setBlock(x - 5, y + y1, z - 3, fox, 2, 2);
            world.setBlock(x + 3, y + y1, z + 5, fox, 2, 2);
            world.setBlock(x - 3, y + y1, z + 5, fox, 2, 2);
            world.setBlock(x + 3, y + y1, z - 5, fox, 2, 2);
            world.setBlock(x - 3, y + y1, z - 5, fox, 2, 2);

            for (int z1 = -2; z1 < 3; z1++) // sets the large outer smooth netherrack
            {
                world.setBlock(x + 6, y + y1, z + z1, fox);
                world.setBlock(x - 6, y + y1, z + z1, fox);
                world.setBlock(x + z1, y + y1, z + 6, fox);
                world.setBlock(x + z1, y + y1, z - 6, fox);
            }

            world.setBlock(x + 4, y + y1, z + 4, fox); // these are for the spaces in between the columns
            world.setBlock(x + 4, y + y1, z - 4, fox);
            world.setBlock(x - 4, y + y1, z + 4, fox);
            world.setBlock(x - 4, y + y1, z - 4, fox);
        }

        for (int z1 = -2; z1 < 3; z1++) // sets the upper layer of beveled netherrack
        {
            world.setBlock(x + 5, y + 5, z + z1, fox, 2, 2);
            world.setBlock(x - 5, y + 5, z + z1, fox, 2, 2);
            world.setBlock(x + z1, y + 5, z + 5, fox, 2, 2);
            world.setBlock(x + z1, y + 5, z - 5, fox, 2, 2);
        }

        world.setBlock(x + 4, y + 5, z + 3, fox, 2, 2); // finishes setting the upper outer beveled netherrack
        world.setBlock(x + 4, y + 5, z - 3, fox, 2, 2);
        world.setBlock(x - 4, y + 5, z + 3, fox, 2, 2);
        world.setBlock(x - 4, y + 5, z - 3, fox, 2, 2);
        world.setBlock(x + 3, y + 5, z + 4, fox, 2, 2);
        world.setBlock(x + 3, y + 5, z - 4, fox, 2, 2);
        world.setBlock(x - 3, y + 5, z + 4, fox, 2, 2);
        world.setBlock(x - 3, y + 5, z - 4, fox, 2, 2);

        for (int x1 = -2; x1 < 3; x1++) // Sets all of the blocks for the top layer, to be replaced
        {
            for (int z1 = -4; z1 < 5; z1++) {
                world.setBlock(x + x1, y + 5, z + z1, fox, 1, 2);
            }
        }

        for (int x1 = -4; x1 < 5; x1++) {
            for (int z1 = -2; z1 < 3; z1++) {
                world.setBlock(x + x1, y + 5, z + z1, fox, 1, 2);
            }
        }

        world.setBlock(x + 3, y + 5, z + 3, fox, 1, 2); // Fills in the last four blocks to finish the top
        world.setBlock(x - 3, y + 5, z + 3, fox, 1, 2);
        world.setBlock(x + 3, y + 5, z - 3, fox, 1, 2);
        world.setBlock(x - 3, y + 5, z - 3, fox, 1, 2);

        for (int y1 = 5; y1 < 7; y1++) {
            for (int x1 = -2; x1 < 3; x1++) {
                for (int z1 = -2; z1 < 3; z1++) {
                    if (y1 == 5) {
                        world.setBlockToAir(x + x1, y + y1, z + z1);
                    } else {
                        world.setBlock(x + x1, y + y1, z + z1, fox, 3, 2);
                    }
                }
            }
        }

        for (int z1 = -1; z1 < 2; z1++) // sets the extra sculpted blocks on the upper layer
        {
            world.setBlock(x + 3, y + 5, z + z1, fox, 3, 2);
            world.setBlock(x - 3, y + 5, z + z1, fox, 3, 2);
            world.setBlock(x + z1, y + 5, z + 3, fox, 3, 2);
            world.setBlock(x + z1, y + 5, z - 3, fox, 3, 2);
        }

        world.setBlock(x + 2, y + 5, z + 2, fox, 3, 2); // sets the blocks on the upper layer to round it out
        world.setBlock(x - 2, y + 5, z + 2, fox, 3, 2);
        world.setBlock(x + 2, y + 5, z - 2, fox, 3, 2);
        world.setBlock(x - 2, y + 5, z - 2, fox, 3, 2);

        world.setBlock(x, y + 6, z, fox, 5, 2); // sets the one light block in the middle on top, lights it up just
                                                // enough to see but not enough to prevent spawning

        if (dens > 0) {
            this.generateDen(world, rand, x + 11, y, z);

            for (int y1 = 1; y1 < 3; y1++) // generates the doorway to the extra den
            {
                for (int z1 = -1; z1 < 2; z1++) {
                    world.setBlockToAir(x + 6, y + y1, z + z1);
                    world.setBlock(x + 6, y, z + z1, fox, 2, 2);
                }
            }
            world.setBlockToAir(x + 6, y + 3, z);

            if (dens > 1) {
                this.generateDen(world, rand, x - 11, y, z);

                for (int y1 = 1; y1 < 3; y1++) // generates the doorway to the extra den
                {
                    for (int z1 = -1; z1 < 2; z1++) {
                        world.setBlockToAir(x - 6, y + y1, z + z1);
                        world.setBlock(x - 6, y, z + z1, fox, 2, 2);
                    }
                }
                world.setBlockToAir(x - 6, y + 3, z);

                if (dens > 2) {
                    this.generateDen(world, rand, x, y, z + 11);

                    for (int y1 = 1; y1 < 3; y1++) // generates the doorway to the extra den
                    {
                        for (int z1 = -1; z1 < 2; z1++) {
                            world.setBlockToAir(x + z1, y + y1, z + 6);
                            world.setBlock(x + z1, y, z + 6, fox, 2, 2);
                        }
                    }
                    world.setBlockToAir(x, y + 3, z + 6);

                    if (dens > 3) {
                        this.generateDen(world, rand, x, y, z - 11);

                        for (int y1 = 1; y1 < 3; y1++) // generates the doorway to the extra den
                        {
                            for (int z1 = -1; z1 < 2; z1++) {
                                world.setBlockToAir(x + z1, y + y1, z - 6);
                                world.setBlock(x + z1, y, z - 6, fox, 2, 2);
                            }
                        }
                        world.setBlockToAir(x, y + 3, z - 6);
                    }
                }
            }
        }
        return true;
    }

    public void generateDen(World world, Random rand, int x, int y, int z) {

        for (int y1 = 0; y1 < 6; y1++) {
            for (int x1 = -4; x1 < 5; x1++) {
                for (int z1 = -4; z1 < 5; z1++) {
                    if (y1 == 0) {
                        world.setBlock(x + x1, y + y1, z + z1, fox, 1, 2);
                    } else {
                        world.setBlockToAir(x + x1, y + y1, z + z1);
                    }
                }
            }
        }

        for (int y1 = -1; y1 < 2; y1++) // sets the middle inset layer of sculpted blocks for the spawner or summon
                                        // block
        {
            for (int x1 = -1; x1 < 2; x1++) {
                for (int z1 = -1; z1 < 2; z1++) {
                    if (y1 == -1) {
                        world.setBlock(x + x1, y + y1, z + z1, fox, 3, 2);
                    } else {
                        world.setBlockToAir(x + x1, y + y1, z + z1);
                    }
                }
            }
        }

        world.setBlock(x + 1, y, z + 1, fox, 3, 2); // sets the blocks on the lower layer to round it out
        world.setBlock(x - 1, y, z + 1, fox, 3, 2);
        world.setBlock(x + 1, y, z - 1, fox, 3, 2);
        world.setBlock(x - 1, y, z - 1, fox, 3, 2);

        world.setBlock(x, y, z, chest, 0, 2);
        this.applyChestContents(world, rand, x, y, z, TragicItems.NetherStructureHook);

        world.setBlock(x, y + 1, z, spawner, 0, 2);
        this.setSpawnerMob(world, x, y + 1, z, TragicConfig.allowJabba ? "TragicMC.Jabba" : "Blaze");

        world.setBlock(x + 1, y, z, fox, 3, 2);
        world.setBlock(x - 1, y, z, fox, 3, 2);
        world.setBlock(x, y, z + 1, fox, 3, 2);
        world.setBlock(x, y, z - 1, fox, 3, 2);

        world.setBlock(x + 1, y, z + 1, fox, 3, 2);
        world.setBlock(x - 1, y, z + 1, fox, 3, 2);
        world.setBlock(x + 1, y, z + 1, fox, 3, 2);
        world.setBlock(x + 1, y, z - 1, fox, 3, 2);

        for (int z1 = -1; z1 < 2; z1++) // Sets the outer blocks as beveled
        {
            world.setBlock(x + 4, y, z + z1, fox, 2, 2);
            world.setBlock(x - 4, y, z + z1, fox, 2, 2);
            world.setBlock(x + z1, y, z + 4, fox, 2, 2);
            world.setBlock(x + z1, y, z - 4, fox, 2, 2);
        }

        world.setBlock(x + 3, y, z + 2, fox, 2, 2); // finishes setting the outer beveled netherrack
        world.setBlock(x + 3, y, z - 2, fox, 2, 2);
        world.setBlock(x - 3, y, z + 2, fox, 2, 2);
        world.setBlock(x - 3, y, z - 2, fox, 2, 2);
        world.setBlock(x + 2, y, z + 3, fox, 2, 2);
        world.setBlock(x + 2, y, z - 3, fox, 2, 2);
        world.setBlock(x - 2, y, z + 3, fox, 2, 2);
        world.setBlock(x - 2, y, z - 3, fox, 2, 2);

        for (int y1 = 1; y1 < 5; y1++) // sets up the walls
        {
            world.setBlock(x + 4, y + y1, z + 2, fox, 2, 2); // sets the outer beveled edges
            world.setBlock(x - 4, y + y1, z + 2, fox, 2, 2);
            world.setBlock(x + 4, y + y1, z - 2, fox, 2, 2);
            world.setBlock(x - 4, y + y1, z - 2, fox, 2, 2);
            world.setBlock(x + 2, y + y1, z + 4, fox, 2, 2);
            world.setBlock(x - 2, y + y1, z + 4, fox, 2, 2);
            world.setBlock(x + 2, y + y1, z - 4, fox, 2, 2);
            world.setBlock(x - 2, y + y1, z - 4, fox, 2, 2);

            for (int z1 = -1; z1 < 2; z1++) // sets the large outer smooth netherrack
            {
                world.setBlock(x + 5, y + y1, z + z1, fox);
                world.setBlock(x - 5, y + y1, z + z1, fox);
                world.setBlock(x + z1, y + y1, z + 5, fox);
                world.setBlock(x + z1, y + y1, z - 5, fox);
            }

            world.setBlock(x + 3, y + y1, z + 3, fox); // these are for the spaces in between the columns
            world.setBlock(x + 3, y + y1, z - 3, fox);
            world.setBlock(x - 3, y + y1, z + 3, fox);
            world.setBlock(x - 3, y + y1, z - 3, fox);
        }

        for (int z1 = -1; z1 < 2; z1++) // sets the upper layer of beveled netherrack
        {
            world.setBlock(x + 4, y + 5, z + z1, fox, 2, 2);
            world.setBlock(x - 4, y + 5, z + z1, fox, 2, 2);
            world.setBlock(x + z1, y + 5, z + 4, fox, 2, 2);
            world.setBlock(x + z1, y + 5, z - 4, fox, 2, 2);
        }

        world.setBlock(x + 3, y + 5, z + 2, fox, 2, 2); // finishes setting the upper outer beveled netherrack
        world.setBlock(x + 3, y + 5, z - 2, fox, 2, 2);
        world.setBlock(x - 3, y + 5, z + 2, fox, 2, 2);
        world.setBlock(x - 3, y + 5, z - 2, fox, 2, 2);
        world.setBlock(x + 2, y + 5, z + 3, fox, 2, 2);
        world.setBlock(x + 2, y + 5, z - 3, fox, 2, 2);
        world.setBlock(x - 2, y + 5, z + 3, fox, 2, 2);
        world.setBlock(x - 2, y + 5, z - 3, fox, 2, 2);

        for (int x1 = -1; x1 < 2; x1++) // Sets all of the blocks for the top layer, to be replaced
        {
            for (int z1 = -3; z1 < 4; z1++) {
                world.setBlock(x + x1, y + 5, z + z1, fox, 1, 2);
            }
        }

        for (int x1 = -3; x1 < 4; x1++) {
            for (int z1 = -1; z1 < 2; z1++) {
                world.setBlock(x + x1, y + 5, z + z1, fox, 1, 2);
            }
        }

        world.setBlock(x + 2, y + 5, z + 2, fox, 1, 2); // Fills in the last four blocks to finish the top
        world.setBlock(x - 2, y + 5, z + 2, fox, 1, 2);
        world.setBlock(x + 2, y + 5, z - 2, fox, 1, 2);
        world.setBlock(x - 2, y + 5, z - 2, fox, 1, 2);

        for (int y1 = 5; y1 < 7; y1++) {
            for (int x1 = -1; x1 < 2; x1++) {
                for (int z1 = -1; z1 < 2; z1++) {
                    if (y1 == 5) {
                        world.setBlockToAir(x + x1, y + y1, z + z1);
                    } else {
                        world.setBlock(x + x1, y + y1, z + z1, fox, 3, 2);
                    }
                }
            }
        }

        world.setBlock(x + 2, y + 5, z, fox, 3, 2);
        world.setBlock(x - 2, y + 5, z, fox, 3, 2);
        world.setBlock(x, y + 5, z + 2, fox, 3, 2);
        world.setBlock(x, y + 5, z - 2, fox, 3, 2);

        world.setBlock(x + 2, y + 5, z + 1, fox, 3, 2);
        world.setBlock(x - 2, y + 5, z + 1, fox, 3, 2);
        world.setBlock(x + 1, y + 5, z + 2, fox, 3, 2);
        world.setBlock(x + 1, y + 5, z - 2, fox, 3, 2);

        world.setBlock(x + 1, y + 5, z + 1, fox, 3, 2); // sets the blocks on the upper layer to round it out
        world.setBlock(x - 1, y + 5, z + 1, fox, 3, 2);
        world.setBlock(x + 1, y + 5, z - 1, fox, 3, 2);
        world.setBlock(x - 1, y + 5, z - 1, fox, 3, 2);

        world.setBlock(x, y + 6, z, fox, 5, 2); // sets the one light block in the middle on top, lights it up just
                                                // enough to see but not enough to prevent spawning
    }
}
