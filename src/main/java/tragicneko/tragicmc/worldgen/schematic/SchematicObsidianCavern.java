package tragicneko.tragicmc.worldgen.schematic;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;

import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.util.WorldHelper;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SchematicObsidianCavern extends Schematic {

    private static Block obs = Blocks.obsidian;
    private static Block bedrock = Blocks.bedrock;
    private static Block ladder = Blocks.ladder;
    private static Block summon = TragicBlocks.SummonBlock;
    private static Block chest = Blocks.chest;
    private static Block glowstone = Blocks.glowstone;
    private static Block spawner = Blocks.mob_spawner;

    public SchematicObsidianCavern() {
        super(8, 12, 12);
    }

    @Override
    public boolean generateStructure(int variant, World world, Random rand, int x, int y, int z) {
        int variant2 = rand.nextInt(10);
        generateTube(world, rand, x, y, z);
        generateCavernOfferings(variant2, world, rand, x, y, z);
        generateCaveOpening(variant, world, rand, x, y, z);
        return true;
    }

    public void generateTube(World world, Random rand, int x, int y, int z) {

        int starty = y;
        y = 0;

        for (int wah = 0; wah < 19; wah++) // To ensure the floor of the cavern is full bedrock
        {
            for (int wuh = 0; wuh < 19; wuh++) {
                world.setBlock(x + wah - 9, y, z + wuh - 9, bedrock);
            }
        }

        y = 1;

        for (int who = 0; who < 7; who++) {
            for (int wah = 0; wah < 19; wah++) {
                for (int wuh = 0; wuh < 19; wuh++) // To make sure the cavern is completely empty before generation
                {
                    world.setBlockToAir(x + wah - 9, y + who, z + wuh - 9);
                }
            }

            for (int x1 = 0; x1 < 19; x1++) // To generate the bedrock walls around the cavern
            {
                world.setBlock(x + x1 - 9, y + who, z - 9, bedrock);
                world.setBlock(x - 9, y + who, z + x1 - 9, bedrock);
                world.setBlock(x + 18 - 9, y + who, z + x1 - 9, bedrock);
                world.setBlock(x + x1 - 9, y + who, z + 18 - 9, bedrock);
            }
        }

        y = 7;

        for (int who = 0; who < 5; who++) {
            for (int wah = 0; wah < 11; wah++) {
                for (int wuh = 0; wuh < 11; wuh++) // To make sure the cavern is completely empty before generation
                {
                    world.setBlockToAir(x + wah - 5, y + who, z + wuh - 5);
                }
            }

            for (int x1 = 0; x1 < 13; x1++) // To generate the bedrock walls around the cavern
            {
                world.setBlock(x + x1 - 6, y + who, z - 6, bedrock);
                world.setBlock(x - 6, y + who, z + x1 - 6, bedrock);
                world.setBlock(x + 12 - 6, y + who, z + x1 - 6, bedrock);
                world.setBlock(x + x1 - 6, y + who, z + 12 - 6, bedrock);
            }

            for (int x1 = 0; x1 < 15; x1++) // To generate the bedrock walls around the cavern, 2nd outer layer
            {
                world.setBlock(x + x1 - 7, y + who, z - 7, bedrock);
                world.setBlock(x - 7, y + who, z + x1 - 7, bedrock);
                world.setBlock(x + 14 - 7, y + who, z + x1 - 7, bedrock);
                world.setBlock(x + x1 - 7, y + who, z + 14 - 7, bedrock);
            }

            for (int x1 = 0; x1 < 17; x1++) // To generate the bedrock walls around the cavern, 3rd outer layer
            {
                world.setBlock(x + x1 - 8, y + who, z - 8, bedrock);
                world.setBlock(x - 8, y + who, z + x1 - 8, bedrock);
                world.setBlock(x + 16 - 8, y + who, z + x1 - 8, bedrock);
                world.setBlock(x + x1 - 8, y + who, z + 16 - 8, bedrock);
            }
        }

        y = 12;

        for (int who = 0; who < 2; who++) {
            for (int wah = 0; wah < 7; wah++) {
                for (int wuh = 0; wuh < 7; wuh++) // To make sure the cavern is completely empty before generation
                {
                    world.setBlockToAir(x + wah - 3, y + who, z + wuh - 3);
                }
            }

            for (int x1 = 0; x1 < 9; x1++) // To generate the bedrock walls around the cavern
            {
                world.setBlock(x + x1 - 4, y + who, z - 4, bedrock);
                world.setBlock(x - 4, y + who, z + x1 - 4, bedrock);
                world.setBlock(x + 8 - 4, y + who, z + x1 - 4, bedrock);
                world.setBlock(x + x1 - 4, y + who, z + 8 - 4, bedrock);
            }

            for (int x1 = 0; x1 < 11; x1++) // To generate the bedrock walls around the cavern, outer layer
            {
                world.setBlock(x + x1 - 5, y + who, z - 5, bedrock);
                world.setBlock(x - 5, y + who, z + x1 - 5, bedrock);
                world.setBlock(x + 10 - 5, y + who, z + x1 - 5, bedrock);
                world.setBlock(x + x1 - 5, y + who, z + 10 - 5, bedrock);
            }
        }

        y = 13;

        for (int wah = 0; wah < 5; wah++) {
            for (int wuh = 0; wuh < 7; wuh++) // To make sure the cavern is completely empty before generation
            {
                world.setBlockToAir(x + wah - 2, y, z + wuh - 2);
            }
        }

        for (int x1 = 0; x1 < 7; x1++) // To generate the bedrock walls around the cavern
        {
            world.setBlock(x + x1 - 3, y, z - 3, bedrock);
            world.setBlock(x - 3, y, z + x1 - 3, bedrock);
            world.setBlock(x + 6 - 3, y, z + x1 - 3, bedrock);
            world.setBlock(x + x1 - 3, y, z + 6 - 3, bedrock);
        }

        y = 14;

        for (int who = 0; who < 2; who++) {
            for (int wah = 0; wah < 3; wah++) {
                for (int wuh = 0; wuh < 3; wuh++) // To make sure the cavern is completely empty before generation
                {
                    world.setBlockToAir(x + wah - 1, y + who, z + wuh - 1);
                }
            }

            for (int x1 = 0; x1 < 5; x1++) // To generate the bedrock walls around the cavern
            {
                world.setBlock(x + x1 - 2, y + who, z - 2, bedrock);
                world.setBlock(x - 2, y + who, z + x1 - 2, bedrock);
                world.setBlock(x + 4 - 2, y + who, z + x1 - 2, bedrock);
                world.setBlock(x + x1 - 2, y + who, z + 4 - 2, bedrock);
            }
        }

        y = 16;

        for (int who = 0; y + who <= starty; who++) {
            for (int wah = 0; wah < 3; wah++) {
                for (int wuh = 0; wuh < 3; wuh++) // To make sure the tube is completely air before generating obsidian
                {
                    world.setBlockToAir(x + wah - 1, y + who, z + wuh - 1);
                }
            }

            for (int x1 = 0; x1 < 3; x1++) // To generate the obsidian tube
            {
                if ((y + who) % 12 == 0 && y + who != starty) {
                    world.setBlock(x + x1 - 1, y + who, z - 1, glowstone);
                    world.setBlock(x - 1, y + who, z + x1 - 1, glowstone);
                    world.setBlock(x + 2 - 1, y + who, z + x1 - 1, glowstone);
                    world.setBlock(x + x1 - 1, y + who, z + 2 - 1, glowstone);
                } else {
                    world.setBlock(x + x1 - 1, y + who, z - 1, obs);
                    world.setBlock(x - 1, y + who, z + x1 - 1, obs);
                    world.setBlock(x + 2 - 1, y + who, z + x1 - 1, obs);
                    world.setBlock(x + x1 - 1, y + who, z + 2 - 1, obs);
                }
            }
        }

        for (int i = 0; y + i <= starty; i++) {
            world.setBlock(x, y + i, z, ladder, 3, 2);
        }

        // Glowstone in the large caverns to allow some visibility if the player doesn't h4ck their g4mma or have night
        // vision on
        world.setBlock(x + 8, 6, z + 8, glowstone);
        world.setBlock(x + 8, 6, z - 8, glowstone);
        world.setBlock(x - 8, 6, z + 8, glowstone);
        world.setBlock(x - 8, 6, z - 8, glowstone);

        world.setBlock(x + 5, 11, z + 5, glowstone);
        world.setBlock(x + 5, 11, z - 5, glowstone);
        world.setBlock(x - 5, 11, z + 5, glowstone);
        world.setBlock(x - 5, 11, z - 5, glowstone);
    }

    public void generateCaveOpening(int variant, World world, Random rand, int x, int y, int z) {

        Set set = Structure.validBlocks;
        Block luxury = SchematicDesertTower.luxuryBlocks[rand.nextInt(SchematicDesertTower.luxuryBlocks.length)];
        int meta = 0;

        if (luxury == TragicBlocks.CompactOre) {
            meta = rand.nextInt(5);
        }

        switch (variant) {
            case 0:
                for (int y1 = 0; y1 < 4; y1++) {
                    for (int x1 = -1; x1 < 2; x1++) {
                        for (int z1 = -1; z1 < 2; z1++) {
                            world.setBlock(x + x1, y + y1, z + z1, obs);
                        }
                    }
                }

                for (int y1 = 1; y1 < 3; y1++) {
                    world.setBlockToAir(x + 1, y + y1, z);
                    world.setBlockToAir(x - 1, y + y1, z);
                    world.setBlockToAir(x, y + y1, z + 1);
                    world.setBlockToAir(x, y + y1, z - 1);
                    world.setBlockToAir(x, y + y1, z);
                }

                for (int x1 = -1; x1 < 2; x1++) {
                    world.setBlock(x + x1, y + 4, z, obs);
                    world.setBlock(x, y + 4, z + x1, obs);

                    world.setBlock(x + 2, y, z + x1, obs);
                    world.setBlock(x - 2, y, z + x1, obs);
                    world.setBlock(x + x1, y, z - 2, obs);
                    world.setBlock(x + x1, y, z + 2, obs);
                }

                world.setBlock(x, y + 4, z, luxury, meta, 2);
                world.setBlock(x, y + 5, z, obs);
                world.setBlock(x, y + 6, z, obs);
                break;
            case 1:
                for (int y1 = 0; y1 < 8; y1++) // Creates first block of obsidian for the middle of the head
                {
                    for (int x1 = -1; x1 < 1; x1++) {
                        for (int z1 = -1; z1 < 2; z1++) {
                            world.setBlock(x + x1, y + y1, z + z1, obs);
                        }
                    }
                }

                world.setBlockToAir(x, y + 4, z);
                world.setBlockToAir(x, y + 5, z + 1);
                world.setBlockToAir(x, y + 5, z - 1);
                world.setBlock(x - 1, y + 5, z + 1, luxury, meta, 2);
                world.setBlock(x - 1, y + 5, z - 1, luxury, meta, 2); // Creates the eye sockets and places the luxury
                                                                      // blocks for the eyes

                for (int x1 = -1; x1 < 2; x1++) // Creates the inner "teeth" next to the tube opening
                {
                    world.setBlock(x + x1, y, z - 2, obs);
                    world.setBlock(x + x1, y, z + 2, obs);

                    if (x1 == 0) {
                        world.setBlock(x + x1, y + 1, z - 2, obs);
                        world.setBlock(x + x1, y + 1, z + 2, obs);
                    }

                    if (x1 == -1) {
                        world.setBlock(x + x1, y + 2, z - 2, obs);
                        world.setBlock(x + x1, y + 2, z + 2, obs);
                        world.setBlock(x + x1, y + 1, z - 2, obs);
                        world.setBlock(x + x1, y + 1, z + 2, obs);
                    }
                }

                for (int y1 = 3; y1 < 7; y1++) {
                    for (int x1 = -1; x1 < 1; x1++) {
                        world.setBlock(x + x1, y + y1, z - 2, obs);
                        world.setBlock(x + x1, y + y1, z + 2, obs);
                    }
                }

                for (int y1 = 0; y1 < 8; y1++) // Creates the back layer of the head
                {
                    world.setBlock(x - 2, y + y1, z + 1, obs);
                    world.setBlock(x - 2, y + y1, z, obs);
                    world.setBlock(x - 2, y + y1, z - 1, obs);
                }

                world.setBlock(x - 2, y + 5, z - 2, obs); // sets extra blocks on the back of the head
                world.setBlock(x - 2, y + 5, z + 2, obs);
                world.setBlockToAir(x - 2, y + 7, z - 1);
                world.setBlockToAir(x - 2, y + 7, z + 1);
                world.setBlock(x - 2, y + 7, z, obs);

                world.setBlockToAir(x, y + 1, z); // Creates the open 2x3x2 space to enter the tube
                world.setBlockToAir(x, y + 1, z - 1);
                world.setBlockToAir(x, y + 1, z + 1);
                world.setBlockToAir(x, y + 2, z);
                world.setBlockToAir(x, y + 2, z - 1);
                world.setBlockToAir(x, y + 2, z + 1);
                world.setBlockToAir(x, y + 3, z + 2);
                world.setBlockToAir(x, y + 3, z - 2);

                for (int y1 = 0; y1 < 3; y1++) // Creates the outer "teeth"
                {
                    for (int z1 = -2; z1 < 3; z1++) {
                        if (y1 != 0) {
                            if (z1 != 0) {
                                world.setBlock(x + 2, y + y1, z + z1, obs);
                            }
                        } else {
                            world.setBlock(x + 2, y + y1, z + z1, obs);
                        }
                    }
                }
                break;
            case 2:
                for (int y1 = 0; y1 < 6; y1++) {
                    for (int x1 = -1; x1 < 1; x1++) {
                        for (int z1 = -1; z1 < 2; z1++) {
                            world.setBlock(x + x1, y + y1, z + z1, obs); // Generates the basic 2 x 3 x 5 slab to carve
                                                                         // out of
                        }
                    }
                }

                world.setBlockToAir(x, y + 1, z);
                world.setBlockToAir(x, y + 2, z); // Clears out the space to enter the tube, sets the eye
                world.setBlock(x, y + 4, z, luxury, meta, 2);

                world.setBlock(x + 1, y + 3, z, obs); // sets the obsidian blocks that are in front of the eye (the
                                                      // "goggles")
                world.setBlock(x + 1, y + 4, z - 1, obs);
                world.setBlock(x + 1, y + 4, z + 1, obs);
                world.setBlock(x + 1, y + 5, z, obs);

                world.setBlock(x, y + 4, z - 2, obs); // sets the obsidian blocks on the side of the head that represent
                                                      // the goggles
                world.setBlock(x, y + 4, z + 2, obs);
                world.setBlock(x - 1, y + 4, z - 2, obs);
                world.setBlock(x - 1, y + 4, z + 2, obs);

                world.setBlock(x, y + 6, z, obs); // Sets the blocks on the top of the head that represent the goggles
                world.setBlock(x - 1, y + 6, z, obs);

                for (int y1 = 2; y1 < 6; y1++) {
                    world.setBlock(x - 2, y + y1, z, obs); // sets the blocks on the back of the head for the goggles
                }
                world.setBlock(x - 2, y + 4, z - 1, obs);
                world.setBlock(x - 2, y + 4, z + 1, obs);

                world.setBlock(x + 1, y + 1, z - 1, obs); // sets the two obsidian blocks near the tube opening (duh)
                world.setBlock(x + 1, y + 1, z + 1, obs);
                break;
            case 3:
                for (int z1 = -1; z1 < 2; z1++) {
                    world.setBlock(x - 1, y, z + z1, obs); // Sets the blocks in front of and behind the starting block,
                                                           // the lower mouth
                    world.setBlock(x + 1, y, z + z1, obs);
                    world.setBlock(x + 2, y, z + z1, obs);

                    for (int x1 = -2; x1 < 5; x1++) {
                        world.setBlock(x + x1, y + 3, z + z1, obs); // sets the main blocks that make up the upper part
                                                                    // of the mouth and middle part of the head
                    }
                }

                for (int y1 = 0; y1 < 5; y1++) {
                    for (int z1 = -1; z1 < 2; z1++) // sets the blocks that make up the center of the head
                    {
                        if (y1 > 0 && z1 != 0) {
                            world.setBlock(x, y + y1, z + z1, obs);
                        }

                        world.setBlock(x - 1, y + y1, z + z1, obs);
                    }
                }

                world.setBlock(x + 2, y + 1, z + 1, obs); // Teeth
                world.setBlock(x + 2, y + 1, z - 1, obs);
                world.setBlock(x + 4, y + 2, z + 1, obs);
                world.setBlock(x + 4, y + 2, z - 1, obs);

                for (int x1 = -3; x1 < 5; x1++) // Upper snout
                {
                    world.setBlock(x + x1, y + 4, z, obs);
                }

                world.setBlock(x - 3, y + 5, z, obs); // Tip of middle spike on back
                world.setBlock(x, y + 6, z, obs); // Tip of top spike on head

                world.setBlock(x + 4, y + 4, z + 1, obs); // tip of the nose
                world.setBlock(x + 4, y + 4, z - 1, obs);

                world.setBlock(x + 1, y + 4, z + 1, luxury, meta, 2); // eyes
                world.setBlock(x + 1, y + 4, z - 1, luxury, meta, 2);

                world.setBlock(x + 1, y + 4, z - 2, obs); // blocks around the eyes
                world.setBlock(x + 1, y + 4, z + 2, obs);
                world.setBlock(x + 1, y + 5, z + 1, obs);
                world.setBlock(x + 1, y + 5, z - 1, obs);

                for (int x1 = -1; x1 < 1; x1++) // Blocks behind the eyes
                {
                    world.setBlock(x + x1, y + 4, z + 1, obs);
                    world.setBlock(x + x1, y + 4, z - 1, obs);
                }

                for (int x1 = -1; x1 < 3; x1++) // Middle spike on the top of the head
                {
                    world.setBlock(x + x1, y + 5, z, obs);

                    if (x1 < 2) {
                        world.setBlock(x + x1, y + 3, z - 2, obs); // The sides of the mouth on the head
                        world.setBlock(x + x1, y + 3, z + 2, obs);
                    }
                }

                for (int x1 = -4; x1 < -1; x1++) // The lower spike on the back
                {
                    world.setBlock(x + x1, y + 1, z, obs);
                    if (x1 > -4) {
                        world.setBlock(x + x1, y + 2, z, obs);
                        world.setBlock(x + x1, y, z, obs);
                    }
                }
                break;
            case 4:
                for (int y1 = 1; y1 < 6; y1++) {
                    for (int z1 = -1; z1 < 2; z1++) {
                        world.setBlock(x - 1, y + y1, z + z1, obs); // central blocks in the head

                        if (z1 != 0) {
                            if (y1 == 1 || y1 == 2) {
                                world.setBlock(x + 1, y + y1, z + z1, obs);
                            }

                            if (y1 == 1) {
                                world.setBlock(x + 2, y + y1, z + z1, obs);
                            }
                        }

                        if (y1 > 3) {
                            world.setBlock(x, y + y1, z + z1, obs);
                        } else if (z1 != 0) {
                            world.setBlock(x, y + y1, z + z1, obs);
                        }

                        if (y1 == 3) {
                            for (int x1 = 0; x1 < 4; x1++) // upper mouth or snout
                            {
                                world.setBlock(x + x1, y + y1, z + z1, obs);
                            }
                        }

                        if (y1 == 4 && z1 == 0) {
                            for (int x1 = 0; x1 < 3; x1++) {
                                world.setBlock(x + x1, y + y1, z, obs); // top snout
                            }
                        }
                    }
                }

                world.setBlock(x, y + 4, z - 1, luxury, meta, 2); // The eyes
                world.setBlock(x, y + 4, z + 1, luxury, meta, 2);

                world.setBlock(x, y + 4, z - 2, obs); // blocks around the eyes and on the side of the head
                world.setBlock(x, y + 4, z + 2, obs);
                world.setBlock(x - 1, y + 4, z - 2, obs);
                world.setBlock(x + 1, y + 4, z - 2, obs);
                world.setBlock(x - 1, y + 4, z + 2, obs);
                world.setBlock(x + 1, y + 4, z + 2, obs);
                world.setBlock(x, y + 5, z - 2, obs);
                world.setBlock(x, y + 5, z + 2, obs);
                world.setBlock(x, y + 3, z - 2, obs);
                world.setBlock(x, y + 3, z + 2, obs);

                for (int z1 = -1; z1 < 2; z1++) // forehead and lower jaw
                {
                    world.setBlock(x + 1, y + 5, z + z1, obs);
                    world.setBlock(x + 2, y, z + z1, obs);
                }

                world.setBlockToAir(x - 1, y + 5, z);

                world.setBlock(x - 1, y + 5, z - 2, obs);
                world.setBlock(x - 1, y + 5, z + 2, obs);
                world.setBlock(x - 1, y + 6, z - 2, obs);
                world.setBlock(x - 1, y + 6, z + 2, obs);

                for (int y1 = 5; y1 < 8; y1++) {
                    world.setBlock(x - 1, y + y1, z - 3, obs);
                    world.setBlock(x - 1, y + y1, z + 3, obs);
                }

                world.setBlock(x - 2, y + 6, z - 3, obs);
                world.setBlock(x - 2, y + 6, z + 3, obs);

                world.setBlock(x - 2, y + 7, z - 3, obs);
                world.setBlock(x - 2, y + 7, z + 3, obs);
                world.setBlock(x - 2, y + 7, z - 4, obs);
                world.setBlock(x - 2, y + 7, z + 4, obs);

                world.setBlock(x - 2, y + 8, z + 4, obs);
                world.setBlock(x - 2, y + 8, z - 4, obs);
                world.setBlock(x - 3, y + 8, z + 4, obs);
                world.setBlock(x - 3, y + 8, z - 4, obs);
                break;
            default:
                for (int y1 = 0; y1 < 3; y1++) {
                    switch (y1) {
                        case 0:
                            for (int x1 = -3; x1 < 4; x1++) {
                                for (int z1 = -3; z1 < 4; z1++) {
                                    if (set.contains(world.getBlock(x + x1, y + y1, z + z1))) {
                                        world.setBlock(x + x1, y + y1, z + z1, obs);
                                    }
                                }
                            }
                            break;
                        case 1:
                            for (int x1 = -2; x1 < 3; x1++) {
                                for (int z1 = -2; z1 < 3; z1++) {
                                    if (set.contains(world.getBlock(x + x1, y + y1, z + z1))) {
                                        world.setBlock(x + x1, y + y1, z + z1, obs);
                                    }
                                }
                            }
                            break;
                        case 2:
                            for (int x1 = -1; x1 < 2; x1++) {
                                for (int z1 = -1; z1 < 2; z1++) {
                                    if (set.contains(world.getBlock(x + x1, y + y1, z + z1)) && (x1 != 0 && z1 != 0)) {
                                        world.setBlock(x + x1, y + y1, z + z1, obs);
                                    }
                                }
                            }
                            break;
                    }
                }

                for (int y1 = 0; y1 < 3; y1++) {
                    world.setBlock(x + 1, y + y1, z, obs);
                    world.setBlock(x - 1, y + y1, z, obs);
                    world.setBlock(x, y + y1, z + 1, obs);
                    world.setBlock(x, y + y1, z - 1, obs);
                    world.setBlock(x, y + y1, z, ladder, 3, 2);
                }
                break;
        }

        if (world.getBlock(x, y, z) != Blocks.ladder && world.getBlock(x, y, z) != Blocks.air)
            world.setBlockToAir(x, y, z);

    }

    public void generateCavernOfferings(int variant, World world, Random rand, int x, int y, int z) {
        switch (variant) {
            case 0:
                ArrayList<int[]> list = WorldHelper.getBlocksInCircularRange(world, 3.0D, x, 0, z);
                int[] coords;
                for (int i = 0; i < list.size(); i++) {
                    coords = list.get(i);
                    world.setBlockToAir(coords[0], coords[1], coords[2]);
                }
                world.setBlock(x, 0, z, bedrock);
                world.setBlock(x, 1, z, chest);
                this.generateChestGoodies(world, rand, x, 1, z, 1);
                break;
            case 1:
                world.setBlock(x, 1, z, bedrock);
                world.setBlock(x + 1, 1, z, chest);
                world.setBlock(x - 1, 1, z, chest);
                world.setBlock(x, 1, z + 1, chest);
                world.setBlock(x, 1, z - 1, chest);
                this.generateChestGoodies(world, rand, x + 1, 1, z, 0);
                this.generateChestGoodies(world, rand, x - 1, 1, z, 0);
                this.generateChestGoodies(world, rand, x, 1, z + 1, 0);
                this.generateChestGoodies(world, rand, x, 1, z - 1, 0);
                break;
            case 2:
                world.setBlock(x, 1, z, bedrock);

                int wubwub = rand.nextInt(4) + 1;
                int xerces = rand.nextInt(10);

                if (xerces == 1) {
                    xerces = 0;
                }

                if (xerces >= 7) {
                    xerces = 6;
                }

                world.setBlock(x + 5, 1, z + 5, summon, xerces, 2);
                world.setBlock(x + 1, 1, z, chest);
                this.generateChestGoodies(world, rand, x + 1, 1, z, 2);

                if (wubwub > 1) {
                    xerces = rand.nextInt(10);

                    if (xerces == 1) {
                        xerces = 0;
                    }

                    if (xerces >= 7) {
                        xerces = 6;
                    }

                    world.setBlock(x - 5, 1, z + 5, summon, xerces, 2);
                    world.setBlock(x - 1, 1, z, chest);
                    this.generateChestGoodies(world, rand, x - 1, 1, z, 2);

                    if (wubwub > 2) {
                        xerces = rand.nextInt(10);

                        if (xerces == 1) {
                            xerces = 0;
                        }

                        if (xerces >= 7) {
                            xerces = 6;
                        }

                        world.setBlock(x - 5, 1, z - 5, summon, xerces, 2);
                        world.setBlock(x, 1, z + 1, chest);
                        this.generateChestGoodies(world, rand, x, 1, z + 1, 2);

                        if (wubwub > 3) {
                            xerces = rand.nextInt(10);

                            if (xerces == 1) {
                                xerces = 0;
                            }

                            if (xerces >= 7) {
                                xerces = 6;
                            }

                            world.setBlock(x + 5, 1, z - 5, summon, xerces, 2);
                            world.setBlock(x, 1, z - 1, chest);
                            this.generateChestGoodies(world, rand, x, 1, z - 1, 2);
                        }
                    }
                }
                break;
            case 3:
                int mow = rand.nextInt(10);

                if (mow == 1) {
                    mow = 0;
                }

                if (mow >= 7) {
                    mow = 6;
                }

                world.setBlock(x, 1, z, summon, mow, 2);
                break;
            case 4:
                world.setBlock(x, 1, z, spawner, 0, 2);
                this.setSpawnerMob(world, x, 1, z, this.getRandomEntityNameForSpawner(rand.nextInt(10)));
                break;
            case 5:
                world.setBlock(x, 1, z, bedrock);

                int zera = rand.nextInt(4) + 1;
                int oppa = rand.nextInt(10);

                world.setBlock(x + 5, 1, z + 5, spawner, 0, 2);
                this.setSpawnerMob(world, x + 5, 1, z + 5, this.getRandomEntityNameForSpawner(oppa));
                world.setBlock(x + 1, 1, z, chest);
                this.generateChestGoodies(world, rand, x + 1, 1, z, 0);
                oppa = rand.nextInt(10);

                if (zera > 1) {

                    world.setBlock(x - 5, 1, z + 5, spawner, 0, 2);
                    this.setSpawnerMob(world, x - 5, 1, z + 5, this.getRandomEntityNameForSpawner(oppa));
                    world.setBlock(x - 1, 1, z, chest);
                    this.generateChestGoodies(world, rand, x - 1, 1, z, 0);
                    oppa = rand.nextInt(10);

                    if (zera > 2) {

                        world.setBlock(x - 5, 1, z - 5, spawner, 0, 2);
                        this.setSpawnerMob(world, x - 5, 1, z - 5, this.getRandomEntityNameForSpawner(oppa));
                        world.setBlock(x, 1, z + 1, chest);
                        this.generateChestGoodies(world, rand, x, 1, z + 1, 0);
                        oppa = rand.nextInt(10);

                        if (zera > 3) {
                            world.setBlock(x + 5, 1, z - 5, spawner, 0, 2);
                            this.setSpawnerMob(world, x + 5, 1, z - 5, this.getRandomEntityNameForSpawner(oppa));
                            world.setBlock(x, 1, z - 1, chest);
                            this.generateChestGoodies(world, rand, x, 1, z - 1, 0);
                        }
                    }
                }
                break;
            case 6:
                world.setBlock(x, 1, z, chest);
                this.generateChestGoodies(world, rand, x, 1, z, 1);
                world.setBlock(x, 2, z, spawner, 0, 2);
                this.setSpawnerMob(world, x, 2, z, this.getRandomEntityNameForSpawner(rand.nextInt(10)));
                break;
            case 7:
                int mrow = rand.nextInt(10);

                if (mrow == 1) {
                    mrow = 0;
                }

                if (mrow >= 7) {
                    mrow = 6;
                }

                world.setBlock(x, 1, z, chest, 0, 2);
                this.generateChestGoodies(world, rand, x, 1, z, 0);
                world.setBlock(x, 2, z, summon, mrow, 2);

                zera = rand.nextInt(4) + 1;
                oppa = rand.nextInt(10);

                world.setBlock(x + 5, 1, z + 5, spawner, 0, 2);
                this.setSpawnerMob(world, x + 5, 1, z + 5, this.getRandomEntityNameForSpawner(oppa));
                oppa = rand.nextInt(10);

                if (zera > 1) {
                    world.setBlock(x - 5, 1, z + 5, spawner, 0, 2);
                    this.setSpawnerMob(world, x - 5, 1, z + 5, this.getRandomEntityNameForSpawner(oppa));
                    oppa = rand.nextInt(10);

                    if (zera > 2) {
                        world.setBlock(x - 5, 1, z - 5, spawner, 0, 2);
                        this.setSpawnerMob(world, x - 5, 1, z - 5, this.getRandomEntityNameForSpawner(oppa));
                        oppa = rand.nextInt(10);

                        if (zera > 3) {
                            world.setBlock(x + 5, 1, z - 5, spawner, 0, 2);
                            this.setSpawnerMob(world, x + 5, 1, z - 5, this.getRandomEntityNameForSpawner(oppa));
                        }
                    }
                }
                break;
            case 8:
                mrow = rand.nextInt(10);

                if (mrow == 1) {
                    mrow = 0;
                }

                if (mrow >= 7) {
                    mrow = 6;
                }

                world.setBlock(x, 1, z, chest, 0, 2);
                this.generateChestGoodies(world, rand, x, 1, z, 2);

                Block luxury = SchematicDesertTower.luxuryBlocks[rand
                    .nextInt(SchematicDesertTower.luxuryBlocks.length)];
                int meta = 0;

                if (luxury == TragicBlocks.CompactOre) {
                    meta = rand.nextInt(5);
                }

                world.setBlock(x, 2, z, luxury, meta, 2);
                world.setBlock(x, 3, z, summon, mrow, 2);

                zera = rand.nextInt(4) + 1;
                oppa = rand.nextInt(10);

                world.setBlock(x + 5, 1, z + 5, spawner, 0, 2);
                this.setSpawnerMob(world, x + 5, 1, z + 5, this.getRandomEntityNameForSpawner(oppa));
                oppa = rand.nextInt(10);

                if (zera > 1) {
                    world.setBlock(x - 5, 1, z + 5, spawner, 0, 2);
                    this.setSpawnerMob(world, x - 5, 1, z + 5, this.getRandomEntityNameForSpawner(oppa));
                    oppa = rand.nextInt(10);

                    if (zera > 2) {

                        world.setBlock(x - 5, 1, z - 5, spawner, 0, 2);
                        this.setSpawnerMob(world, x - 5, 1, z - 5, this.getRandomEntityNameForSpawner(oppa));
                        oppa = rand.nextInt(10);

                        if (zera > 3) {
                            world.setBlock(x + 5, 1, z - 5, spawner, 0, 2);
                            this.setSpawnerMob(world, x + 5, 1, z - 5, this.getRandomEntityNameForSpawner(oppa));
                        }
                    }
                }
                break;
            default:
                world.setBlock(x, 1, z, chest);
                this.generateChestGoodies(world, rand, x, 1, z, 1);
                break;
        }
    }

    /**
     * Generate chest hooks based on variant created, flag here is 0 for normal boss chest, 1 for vanilla mineshaft
     * chest, 2 for the super rare items hook, any other integer for
     * the super lame hook
     * 
     * @param world
     * @param rand
     * @param x
     * @param y
     * @param z
     * @param flag
     */
    public void generateChestGoodies(World world, Random rand, int x, int y, int z, int flag) {
        TileEntityChest chest = (TileEntityChest) world.getTileEntity(x, y, z);
        ChestGenHooks hook = null;

        if (chest == null) {
            TragicMC.logWarning("There was an error setting chest contents");
            return;
        }

        if (flag == 0) {
            hook = TragicItems.BossStructureHook;
        } else if (flag == 1) {
            hook = ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR);
        } else if (flag == 2) {
            hook = TragicItems.AwesomeChestHook;
        } else {
            hook = TragicItems.LameChestHook;
        }

        WeightedRandomChestContent.generateChestContents(rand, hook.getItems(rand), chest, hook.getCount(rand));
    }

    public String getRandomEntityNameForSpawner(int i) {
        String s = TragicConfig.allowPlague ? "TragicMC.Plague" : "Blaze";

        switch (i) {
            case 0:
                s = TragicConfig.allowMinotaur ? "TragicMC.Minotaur" : this.getRandomVanillaEntityNameForSpawner(i);
                break;
            case 1:
                s = TragicConfig.allowInkling ? "TragicMC.Inkling" : this.getRandomVanillaEntityNameForSpawner(i);
                break;
            case 2:
                s = TragicConfig.allowJabba ? "TragicMC.Jabba" : this.getRandomVanillaEntityNameForSpawner(i);
                break;
            case 3:
                s = TragicConfig.allowNorVox ? "TragicMC.NorVox" : this.getRandomVanillaEntityNameForSpawner(i);
                break;
            case 4:
                s = TragicConfig.allowRagr ? "TragicMC.Ragr" : this.getRandomVanillaEntityNameForSpawner(i);
                break;
            case 5:
                s = TragicConfig.allowTox ? "TragicMC.Tox" : this.getRandomVanillaEntityNameForSpawner(i);
                break;
            case 6:
                s = TragicConfig.allowGragul ? "TragicMC.Gragul" : this.getRandomVanillaEntityNameForSpawner(i);
                break;
            case 7:
                s = TragicConfig.allowJarra ? "TragicMC.Jarra" : this.getRandomVanillaEntityNameForSpawner(i);
                break;
            case 8:
                s = TragicConfig.allowKragul ? "TragicMC.Kragul" : this.getRandomVanillaEntityNameForSpawner(i);
                break;
            default:
                break;
        }
        return s;
    }

    public String getRandomVanillaEntityNameForSpawner(int i) {
        String s = "Skeleton";

        switch (i) {
            case 0:
                s = "Zombie";
                break;
            case 1:
                s = "Enderman";
                break;
            case 2:
                s = "Slime";
                break;
            case 3:
                s = "Blaze";
                break;
            case 4:
                s = "Spider";
                break;
            case 5:
                s = "Witch";
                break;
            default:
                break;
        }
        return s;
    }

    public String getRandomBossNameForSpawner(int i) {
        String s = TragicConfig.allowApis ? "TragicMC.Apis" : "Enderman";
        switch (i) {
            case 0:
                s = TragicConfig.allowEmpariah ? "TragicMC.Yeti" : "Enderman";
                break;
            case 1:
                s = TragicConfig.allowKitsunakuma ? "TragicMC.Kitsune" : "Enderman";
                break;
            case 2:
                s = TragicConfig.allowPolaris ? "TragicMC.Polaris" : "Enderman";
                break;
            default:
                break;
        }
        return s;
    }
}
