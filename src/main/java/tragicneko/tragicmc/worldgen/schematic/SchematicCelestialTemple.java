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

public class SchematicCelestialTemple extends Schematic {

    private static Block quartz = TragicBlocks.DarkenedQuartz;
    private static Block obs = TragicBlocks.DarkStone;
    private static Block glow = TragicBlocks.StarCrystal;
    private static Block chest = Blocks.chest;
    private static Block stone = TragicBlocks.DarkStone;
    private static Block summon = TragicBlocks.SummonBlock;

    public SchematicCelestialTemple() {
        super(12, 25, 25);
    }

    @Override
    public boolean generateStructure(int variant, World world, Random rand, int x, int y, int z) {
        ArrayList<int[]> list;
        byte relays = (byte) (6 + (rand.nextInt(variant + 1) + 1) * (rand.nextInt(variant + 1) + 1));
        Block block;
        double regression = 0.88977745D;
        double cutoff = 0.48943755D;
        double size;
        ArrayList<int[]> cands = new ArrayList<int[]>();
        int yMax;
        int Xcoord;
        int Zcoord;
        int Ycoord;

        for (byte buzza = 0; buzza < relays; buzza++) {
            if (buzza == 0) {
                size = 15.0D;
                Xcoord = x + 4;
                Zcoord = z + 4;
                Ycoord = y;
                cutoff = 0.6278D;
                regression = 0.8876D;
            } else {
                size = rand.nextDouble() * 5.5D + 4.5D;
                Xcoord = x + 4 + rand.nextInt(10) - rand.nextInt(10);
                Zcoord = z + 4 + rand.nextInt(10) - rand.nextInt(10);
                Ycoord = y - rand.nextInt(2);
                regression = 0.88977745D;
                cutoff = 0.68943755D;
            }
            yMax = Ycoord;

            for (byte y1 = 0; y1 > -32; y1--) {
                if (size < cutoff) break;
                size *= regression;

                if (rand.nextInt(12) == 0) {
                    Xcoord += rand.nextInt(2) - rand.nextInt(2);
                    Zcoord += rand.nextInt(2) - rand.nextInt(2);
                }

                list = WorldHelper.getBlocksInCircularRange(world, size, Xcoord, Ycoord + y1, Zcoord);

                for (int[] coords2 : list) {
                    block = world.getBlock(coords2[0], coords2[1], coords2[2]);
                    if (Structure.validBlocks.contains(block) && !cands.contains(coords2)) {
                        if (yMax < coords2[1]) yMax = coords2[1];
                        cands.add(coords2);
                    }
                }
            }

            byte rnd = (byte) (rand.nextInt(3) + 1);

            // Prevent cascading updates by only modifying blocks if necessary
            for (int[] coords2 : cands) {
                Block currentBlock = world.getBlock(coords2[0], coords2[1], coords2[2]);
                if (coords2[1] >= yMax) {
                    if (currentBlock != TragicBlocks.AshenGrass) {
                        world.setBlock(coords2[0], coords2[1], coords2[2], TragicBlocks.AshenGrass, 0, 2);
                    }
                } else if (coords2[1] >= yMax - rnd - rand.nextInt(2)) {
                    if (currentBlock != TragicBlocks.DeadDirt) {
                        world.setBlock(coords2[0], coords2[1], coords2[2], TragicBlocks.DeadDirt, 0, 2);
                    }
                } else {
                    if (currentBlock != TragicBlocks.DarkStone) {
                        world.setBlock(coords2[0], coords2[1], coords2[2], TragicBlocks.DarkStone, 0, 2);
                    }
                }
            }
            cands.clear(); // Clear the list for the next iteration to prevent duplicates
        }

        for (byte y1 = 0; y1 < 10; y1++) {
            for (byte x1 = -8; x1 < 17; x1++) {
                for (byte z1 = -6; z1 < 15; z1++) {
                    world.setBlockToAir(x + x1, y + y1 + 1, z + z1);
                }
            }
        }
        // First layer

        // Top row

        for (int i = 2; i < 8; i++) {
            world.setBlock(x - 7, y, z + i, quartz, 0, 2);
        }

        // Second row
        for (int i = 1; i < 9; i++) {
            world.setBlock(x - 6, y, z + i, quartz, 0, 2);
        }

        // Third row
        for (int i = -1; i < 11; i++) {
            world.setBlock(x - 5, y, z + i, quartz, 0, 2);
        }

        // Fourth row
        for (int i = -2; i < 12; i++) {
            world.setBlock(x - 4, y, z + i, quartz, 0, 2);
        }

        // Fifth row
        for (int i = -2; i < 12; i++) {
            world.setBlock(x - 3, y, z + i, quartz, 0, 2);
        }

        // Sixth row
        for (int i = -3; i < 13; i++) {
            world.setBlock(x - 2, y, z + i, quartz, 0, 2);
        }

        // Seventh row
        for (int i = -4; i < 14; i++) {
            world.setBlock(x - 1, y, z + i, quartz, 0, 2);
        }

        world.setBlock(x - 1, y, z, quartz, 1, 2);
        world.setBlock(x - 1, y, z + 9, quartz, 1, 2);

        // Eighth row
        for (int i = -5; i < 15; i++) {
            world.setBlock(x, y, z + i, quartz, 0, 2);
        }

        world.setBlock(x, y, z + 4, obs, 0, 2);
        world.setBlock(x, y, z + 5, obs, 0, 2);

        // Ninth row
        for (int i = -5; i < 15; i++) {
            world.setBlock(x + 1, y, z + i, quartz, 0, 2);
        }

        world.setBlock(x + 1, y, z, quartz, 1, 2);
        world.setBlock(x + 1, y, z + 3, obs, 0, 2);
        world.setBlock(x + 1, y, z + 4, quartz, 1, 2);
        world.setBlock(x + 1, y, z + 5, quartz, 1, 2);
        world.setBlock(x + 1, y, z + 6, obs, 0, 2);
        world.setBlock(x + 1, y, z + 9, quartz, 1, 2);

        // Tenth row
        for (int i = -5; i < 15; i++) {
            world.setBlock(x + 2, y, z + i, quartz, 0, 2);
        }

        world.setBlock(x + 2, y, z, quartz, 1, 2);
        world.setBlock(x + 2, y, z + 2, obs, 0, 2);
        world.setBlock(x + 2, y, z + 3, quartz, 1, 2);
        world.setBlock(x + 2, y, z + 4, quartz, 1, 2);
        world.setBlock(x + 2, y, z + 5, quartz, 1, 2);
        world.setBlock(x + 2, y, z + 6, quartz, 1, 2);
        world.setBlock(x + 2, y, z + 7, obs, 0, 2);
        world.setBlock(x + 2, y, z + 9, quartz, 1, 2);

        // Eleventh row
        for (int i = -5; i < 15; i++) {
            world.setBlock(x + 3, y, z + i, quartz, 0, 2);
        }

        world.setBlock(x + 3, y, z, quartz, 1, 2);
        world.setBlock(x + 3, y, z + 2, obs, 0, 2);
        world.setBlock(x + 3, y, z + 3, quartz, 1, 2);
        world.setBlock(x + 3, y, z + 4, obs, 0, 2);
        world.setBlock(x + 3, y, z + 5, obs, 0, 2);
        world.setBlock(x + 3, y, z + 6, quartz, 1, 2);
        world.setBlock(x + 3, y, z + 7, obs, 0, 2);
        world.setBlock(x + 3, y, z + 9, quartz, 1, 2);

        // Twelfth row - This is the middle row
        for (int i = -5; i < 15; i++) {
            world.setBlock(x + 4, y, z + i, quartz, 0, 2);
        }

        world.setBlock(x + 4, y, z + 1, obs, 0, 2);
        world.setBlock(x + 4, y, z + 2, quartz, 1, 2);
        world.setBlock(x + 4, y, z + 3, glow, 0, 2);
        world.setBlock(x + 4, y, z + 4, chest, 0, 2);
        world.setBlock(x + 4, y, z + 5, chest, 0, 2);
        world.setBlock(x + 4, y, z + 6, glow, 0, 2);
        world.setBlock(x + 4, y, z + 7, quartz, 1, 2);
        world.setBlock(x + 4, y, z + 8, obs, 0, 2);

        // Thirteenth row
        for (int i = -5; i < 15; i++) {
            world.setBlock(x + 5, y, z + i, quartz, 0, 2);
        }

        world.setBlock(x + 5, y, z, quartz, 1, 2);
        world.setBlock(x + 5, y, z + 2, obs, 0, 2);
        world.setBlock(x + 5, y, z + 3, quartz, 1, 2);
        world.setBlock(x + 5, y, z + 4, obs, 0, 2);
        world.setBlock(x + 5, y, z + 5, obs, 0, 2);
        world.setBlock(x + 5, y, z + 6, quartz, 1, 2);
        world.setBlock(x + 5, y, z + 7, obs, 0, 2);
        world.setBlock(x + 5, y, z + 9, quartz, 1, 2);

        // Fourteenth row
        for (int i = -5; i < 15; i++) {
            world.setBlock(x + 6, y, z + i, quartz, 0, 2);
        }

        world.setBlock(x + 6, y, z, quartz, 1, 2);
        world.setBlock(x + 6, y, z + 2, obs, 0, 2);
        world.setBlock(x + 6, y, z + 3, quartz, 1, 2);
        world.setBlock(x + 6, y, z + 4, quartz, 1, 2);
        world.setBlock(x + 6, y, z + 5, quartz, 1, 2);
        world.setBlock(x + 6, y, z + 6, quartz, 1, 2);
        world.setBlock(x + 6, y, z + 7, obs, 0, 2);
        world.setBlock(x + 6, y, z + 9, quartz, 1, 2);

        // Fifteenth row
        for (int i = -5; i < 15; i++) {
            world.setBlock(x + 7, y, z + i, quartz, 0, 2);
        }

        world.setBlock(x + 7, y, z, quartz, 1, 2);
        world.setBlock(x + 7, y, z + 3, obs, 0, 2);
        world.setBlock(x + 7, y, z + 4, quartz, 1, 2);
        world.setBlock(x + 7, y, z + 5, quartz, 1, 2);
        world.setBlock(x + 7, y, z + 6, obs, 0, 2);
        world.setBlock(x + 7, y, z + 9, quartz, 1, 2);

        // Sixteenth row
        for (int i = -5; i < 15; i++) {
            world.setBlock(x + 8, y, z + i, quartz, 0, 2);
        }

        world.setBlock(x + 8, y, z + 4, obs, 0, 2);
        world.setBlock(x + 8, y, z + 5, obs, 0, 2);

        // Seventeenth row
        for (int i = -4; i < 14; i++) {
            world.setBlock(x + 9, y, z + i, quartz, 0, 2);
        }

        world.setBlock(x + 9, y, z, quartz, 1, 2);
        world.setBlock(x + 9, y, z + 9, quartz, 1, 2);

        // Eighteenth row
        for (int i = -3; i < 13; i++) {
            world.setBlock(x + 10, y, z + i, quartz, 0, 2);
        }

        // Nineteenth row
        for (int i = -2; i < 12; i++) {
            world.setBlock(x + 11, y, z + i, quartz, 0, 2);
        }

        // Twentieth row
        for (int i = -2; i < 12; i++) {
            world.setBlock(x + 12, y, z + i, quartz, 0, 2);
        }

        // Twenty-first row
        for (int i = -1; i < 11; i++) {
            world.setBlock(x + 13, y, z + i, quartz, 0, 2);
        }

        // Twenty-second row
        for (int i = 1; i < 9; i++) {
            world.setBlock(x + 14, y, z + i, quartz, 0, 2);
        }

        // Twenty-third row
        for (int i = 2; i < 8; i++) {
            world.setBlock(x + 15, y, z + i, quartz, 0, 2);
        }

        if (!world.isRemote) {
            // First layer chest gen
            this.applyChestContents(world, rand, x + 4, y, z + 4, TragicItems.BossStructureHook);
            this.applyChestContents(world, rand, x + 4, y, z + 5, TragicItems.BossStructureHook);
        }
        y++;
        // Second layer
        x -= 1;

        // First row
        world.setBlock(x - 6, y, z + 2, quartz, 1, 2);
        world.setBlock(x - 6, y, z + 7, quartz, 1, 2);

        // Second row
        world.setBlock(x - 5, y, z + 1, quartz, 1, 2);
        world.setBlock(x - 5, y, z + 2, quartz, 1, 2);
        world.setBlock(x - 5, y, z + 3, quartz, 1, 2);
        world.setBlock(x - 5, y, z + 6, quartz, 1, 2);
        world.setBlock(x - 5, y, z + 7, quartz, 1, 2);
        world.setBlock(x - 5, y, z + 8, quartz, 1, 2);

        // Third row
        world.setBlock(x - 4, y, z - 1, quartz, 1, 2);
        world.setBlock(x - 4, y, z, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 1, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 2, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 3, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 6, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 7, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 8, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 9, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 10, quartz, 1, 2);

        // Fourth row
        world.setBlock(x - 3, y, z - 2, quartz, 1, 2);
        world.setBlock(x - 3, y, z - 1, quartz, 1, 2);
        world.setBlock(x - 3, y, z + 10, quartz, 1, 2);
        world.setBlock(x - 3, y, z + 11, quartz, 1, 2);

        // Fifth row
        world.setBlock(x - 2, y, z - 3, quartz, 1, 2);
        world.setBlock(x - 2, y, z - 2, quartz, 1, 2);
        world.setBlock(x - 2, y, z - 1, quartz, 1, 2);
        world.setBlock(x - 2, y, z + 10, quartz, 1, 2);
        world.setBlock(x - 2, y, z + 11, quartz, 1, 2);
        world.setBlock(x - 2, y, z + 12, quartz, 1, 2);

        // Sixth row
        world.setBlock(x - 1, y, z - 3, quartz, 1, 2);
        world.setBlock(x - 1, y, z - 2, quartz, 1, 2);
        world.setBlock(x - 1, y, z - 1, quartz, 1, 2);
        world.setBlock(x - 1, y, z + 10, quartz, 1, 2);
        world.setBlock(x - 1, y, z + 11, quartz, 1, 2);
        world.setBlock(x - 1, y, z + 12, quartz, 1, 2);

        // Seventh row
        world.setBlock(x, y, z - 3, quartz, 1, 2);
        world.setBlock(x, y, z - 2, quartz, 1, 2);
        world.setBlock(x, y, z, quartz, 1, 2);
        world.setBlock(x, y, z + 9, quartz, 1, 2);
        world.setBlock(x, y, z + 11, quartz, 1, 2);
        world.setBlock(x, y, z + 12, quartz, 1, 2);

        // Eighth row
        world.setBlock(x + 1, y, z - 4, quartz, 1, 2);
        world.setBlock(x + 1, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 1, y, z + 12, quartz, 1, 2);
        world.setBlock(x + 1, y, z + 13, quartz, 1, 2);

        // Ninth row
        world.setBlock(x + 2, y, z - 5, quartz, 1, 2);
        world.setBlock(x + 2, y, z - 4, quartz, 1, 2);
        world.setBlock(x + 2, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 2, y, z + 12, quartz, 1, 2);
        world.setBlock(x + 2, y, z + 13, quartz, 1, 2);
        world.setBlock(x + 2, y, z + 14, quartz, 1, 2);

        // Tenth row
        world.setBlock(x + 3, y, z - 4, quartz, 1, 2);
        world.setBlock(x + 3, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 3, y, z + 12, quartz, 1, 2);
        world.setBlock(x + 3, y, z + 13, quartz, 1, 2);

        // Eleventh row

        // Twelfth row - middle row
        world.setBlock(x + 5, y, z + 4, obs, 0, 2);
        world.setBlock(x + 5, y, z + 5, obs, 0, 2);

        // Thirteenth row

        // Fourteenth row
        world.setBlock(x + 7, y, z - 4, quartz, 1, 2);
        world.setBlock(x + 7, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 7, y, z + 12, quartz, 1, 2);
        world.setBlock(x + 7, y, z + 13, quartz, 1, 2);

        // Fifteenth row - 9
        world.setBlock(x + 8, y, z - 5, quartz, 1, 2);
        world.setBlock(x + 8, y, z - 4, quartz, 1, 2);
        world.setBlock(x + 8, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 8, y, z + 12, quartz, 1, 2);
        world.setBlock(x + 8, y, z + 13, quartz, 1, 2);
        world.setBlock(x + 8, y, z + 14, quartz, 1, 2);

        // Sixteenth row
        world.setBlock(x + 9, y, z - 4, quartz, 1, 2);
        world.setBlock(x + 9, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 9, y, z + 12, quartz, 1, 2);
        world.setBlock(x + 9, y, z + 13, quartz, 1, 2);

        // Seventeenth row
        world.setBlock(x + 10, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 10, y, z - 2, quartz, 1, 2);
        world.setBlock(x + 10, y, z, quartz, 1, 2);
        world.setBlock(x + 10, y, z + 9, quartz, 1, 2);
        world.setBlock(x + 10, y, z + 11, quartz, 1, 2);
        world.setBlock(x + 10, y, z + 12, quartz, 1, 2);

        // Eighteenth row - 6
        world.setBlock(x + 11, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 11, y, z - 2, quartz, 1, 2);
        world.setBlock(x + 11, y, z - 1, quartz, 1, 2);
        world.setBlock(x + 11, y, z + 10, quartz, 1, 2);
        world.setBlock(x + 11, y, z + 11, quartz, 1, 2);
        world.setBlock(x + 11, y, z + 12, quartz, 1, 2);

        // Nineteenth row
        world.setBlock(x + 12, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 12, y, z - 2, quartz, 1, 2);
        world.setBlock(x + 12, y, z - 1, quartz, 1, 2);
        world.setBlock(x + 12, y, z + 10, quartz, 1, 2);
        world.setBlock(x + 12, y, z + 11, quartz, 1, 2);
        world.setBlock(x + 12, y, z + 12, quartz, 1, 2);

        // Twentieth row - 4
        world.setBlock(x + 13, y, z - 2, quartz, 1, 2);
        world.setBlock(x + 13, y, z - 1, quartz, 1, 2);
        world.setBlock(x + 13, y, z + 10, quartz, 1, 2);
        world.setBlock(x + 13, y, z + 11, quartz, 1, 2);

        // Twenty-first row
        world.setBlock(x + 14, y, z - 1, quartz, 1, 2);
        world.setBlock(x + 14, y, z, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 1, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 2, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 3, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 6, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 7, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 8, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 9, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 10, quartz, 1, 2);

        // Twenty-second row
        world.setBlock(x + 15, y, z + 1, quartz, 1, 2);
        world.setBlock(x + 15, y, z + 2, quartz, 1, 2);
        world.setBlock(x + 15, y, z + 3, quartz, 1, 2);
        world.setBlock(x + 15, y, z + 6, quartz, 1, 2);
        world.setBlock(x + 15, y, z + 7, quartz, 1, 2);
        world.setBlock(x + 15, y, z + 8, quartz, 1, 2);

        // Twenty-third row
        world.setBlock(x + 16, y, z + 2, quartz, 1, 2);
        world.setBlock(x + 16, y, z + 7, quartz, 1, 2);

        y++;

        // Third layer

        // First row

        // Second row
        world.setBlock(x - 5, y, z + 2, quartz, 1, 2);
        world.setBlock(x - 5, y, z + 3, stone, 0, 2);
        world.setBlock(x - 5, y, z + 6, stone, 0, 2);
        world.setBlock(x - 5, y, z + 7, quartz, 1, 2);

        // Third row
        world.setBlock(x - 4, y, z - 1, quartz, 1, 2);
        world.setBlock(x - 4, y, z, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 1, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 2, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 3, stone, 0, 2);
        world.setBlock(x - 4, y, z + 6, stone, 0, 2);
        world.setBlock(x - 4, y, z + 7, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 8, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 9, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 10, quartz, 1, 2);

        // Fourth row
        world.setBlock(x - 3, y, z - 2, quartz, 1, 2);
        world.setBlock(x - 3, y, z - 1, quartz, 1, 2);
        world.setBlock(x - 3, y, z + 10, quartz, 1, 2);
        world.setBlock(x - 3, y, z + 11, quartz, 1, 2);

        // Fifth row
        world.setBlock(x - 2, y, z - 2, quartz, 1, 2);
        world.setBlock(x - 2, y, z - 1, quartz, 1, 2);
        world.setBlock(x - 2, y, z + 10, quartz, 1, 2);
        world.setBlock(x - 2, y, z + 11, quartz, 1, 2);

        // Sixth row
        world.setBlock(x - 1, y, z - 3, quartz, 1, 2);
        world.setBlock(x - 1, y, z - 2, quartz, 1, 2);
        world.setBlock(x - 1, y, z - 1, quartz, 1, 2);
        world.setBlock(x - 1, y, z + 10, quartz, 1, 2);
        world.setBlock(x - 1, y, z + 11, quartz, 1, 2);
        world.setBlock(x - 1, y, z + 12, quartz, 1, 2);

        // Seventh row
        world.setBlock(x, y, z - 3, quartz, 1, 2);
        world.setBlock(x, y, z - 2, quartz, 1, 2);
        world.setBlock(x, y, z, quartz, 1, 2);
        world.setBlock(x, y, z + 9, quartz, 1, 2);
        world.setBlock(x, y, z + 11, quartz, 1, 2);
        world.setBlock(x, y, z + 12, quartz, 1, 2);

        // Eighth row
        world.setBlock(x + 1, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 1, y, z + 12, quartz, 1, 2);

        // Ninth row
        world.setBlock(x + 2, y, z - 4, quartz, 1, 2);
        world.setBlock(x + 2, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 2, y, z + 12, quartz, 1, 2);
        world.setBlock(x + 2, y, z + 13, quartz, 1, 2);

        // Tenth row
        world.setBlock(x + 3, y, z - 4, stone, 0, 2);
        world.setBlock(x + 3, y, z - 3, stone, 0, 2);
        world.setBlock(x + 3, y, z + 12, stone, 0, 2);
        world.setBlock(x + 3, y, z + 13, stone, 0, 2);

        // Eleventh row

        // Twelfth row - middle row

        if (variant == 1) {
            world.setBlock(x + 5, y, z + 4, summon, 5, 2);
            world.setBlockToAir(x + 5, y, z + 5);
        } else if (variant == 2) {
            world.setBlock(x + 5, y, z + 4, summon, 5, 2);
            world.setBlock(x + 5, y, z + 5, summon, 5, 2);
        } else {
            world.setBlockToAir(x + 5, y, z + 4);
            world.setBlock(x + 5, y, z + 5, summon, 5, 2);
        }

        // Thirteenth row

        // Fourteenth row
        world.setBlock(x + 7, y, z - 4, stone, 0, 2);
        world.setBlock(x + 7, y, z - 3, stone, 0, 2);
        world.setBlock(x + 7, y, z + 12, stone, 0, 2);
        world.setBlock(x + 7, y, z + 13, stone, 0, 2);

        // Fifteenth row - 9
        world.setBlock(x + 8, y, z - 4, quartz, 1, 2);
        world.setBlock(x + 8, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 8, y, z + 12, quartz, 1, 2);
        world.setBlock(x + 8, y, z + 13, quartz, 1, 2);

        // Sixteenth row
        world.setBlock(x + 9, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 9, y, z + 12, quartz, 1, 2);

        // Seventeenth row
        world.setBlock(x + 10, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 10, y, z - 2, quartz, 1, 2);
        world.setBlock(x + 10, y, z, quartz, 1, 2);
        world.setBlock(x + 10, y, z + 9, quartz, 1, 2);
        world.setBlock(x + 10, y, z + 11, quartz, 1, 2);
        world.setBlock(x + 10, y, z + 12, quartz, 1, 2);

        // Eighteenth row - 6
        world.setBlock(x + 11, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 11, y, z - 2, quartz, 1, 2);
        world.setBlock(x + 11, y, z - 1, quartz, 1, 2);
        world.setBlock(x + 11, y, z + 10, quartz, 1, 2);
        world.setBlock(x + 11, y, z + 11, quartz, 1, 2);
        world.setBlock(x + 11, y, z + 12, quartz, 1, 2);

        // Nineteenth row
        world.setBlock(x + 12, y, z - 2, quartz, 1, 2);
        world.setBlock(x + 12, y, z - 1, quartz, 1, 2);
        world.setBlock(x + 12, y, z + 10, quartz, 1, 2);
        world.setBlock(x + 12, y, z + 11, quartz, 1, 2);

        // Twentieth row - 4
        world.setBlock(x + 13, y, z - 2, quartz, 1, 2);
        world.setBlock(x + 13, y, z - 1, quartz, 1, 2);
        world.setBlock(x + 13, y, z + 10, quartz, 1, 2);
        world.setBlock(x + 13, y, z + 11, quartz, 1, 2);

        // Twenty-first row
        world.setBlock(x + 14, y, z - 1, quartz, 1, 2);
        world.setBlock(x + 14, y, z, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 1, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 2, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 3, stone, 0, 2);
        world.setBlock(x + 14, y, z + 6, stone, 0, 2);
        world.setBlock(x + 14, y, z + 7, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 8, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 9, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 10, quartz, 1, 2);

        // Twenty-second row
        world.setBlock(x + 15, y, z + 2, quartz, 1, 2);
        world.setBlock(x + 15, y, z + 3, stone, 0, 2);
        world.setBlock(x + 15, y, z + 6, stone, 0, 2);
        world.setBlock(x + 15, y, z + 7, quartz, 1, 2);

        // Twenty-third row

        y++;

        // Fourth layer

        // First row

        // Second row
        world.setBlock(x - 5, y, z + 3, stone, 0, 2);
        world.setBlock(x - 5, y, z + 6, stone, 0, 2);

        // Third row
        world.setBlock(x - 4, y, z - 1, quartz, 1, 2);
        world.setBlock(x - 4, y, z, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 1, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 2, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 3, stone, 0, 2);
        world.setBlock(x - 4, y, z + 6, stone, 0, 2);
        world.setBlock(x - 4, y, z + 7, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 8, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 9, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 10, quartz, 1, 2);

        // Fourth row
        world.setBlock(x - 3, y, z - 1, quartz, 1, 2);
        world.setBlock(x - 3, y, z + 10, quartz, 1, 2);

        // Fifth row
        world.setBlock(x - 2, y, z - 1, quartz, 1, 2);
        world.setBlock(x - 2, y, z + 10, quartz, 1, 2);

        // Sixth row
        world.setBlock(x - 1, y, z - 3, quartz, 1, 2);
        world.setBlock(x - 1, y, z - 2, quartz, 1, 2);
        world.setBlock(x - 1, y, z - 1, quartz, 1, 2);
        world.setBlock(x - 1, y, z + 10, quartz, 1, 2);
        world.setBlock(x - 1, y, z + 11, quartz, 1, 2);
        world.setBlock(x - 1, y, z + 12, quartz, 1, 2);

        // Seventh row
        world.setBlock(x, y, z - 3, quartz, 1, 2);
        world.setBlock(x, y, z - 2, quartz, 1, 2);
        world.setBlock(x, y, z, quartz, 1, 2);
        world.setBlock(x, y, z + 9, quartz, 1, 2);
        world.setBlock(x, y, z + 11, quartz, 1, 2);
        world.setBlock(x, y, z + 12, quartz, 1, 2);

        // Eighth row
        world.setBlock(x + 1, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 1, y, z + 12, quartz, 1, 2);

        // Ninth row
        world.setBlock(x + 2, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 2, y, z + 12, quartz, 1, 2);

        // Tenth row
        world.setBlock(x + 3, y, z - 4, stone, 0, 2);
        world.setBlock(x + 3, y, z - 3, stone, 0, 2);
        world.setBlock(x + 3, y, z + 12, stone, 0, 2);
        world.setBlock(x + 3, y, z + 13, stone, 0, 2);

        // Eleventh row

        // Twelfth row - middle row

        // Thirteenth row

        // Fourteenth row
        world.setBlock(x + 7, y, z - 4, stone, 0, 2);
        world.setBlock(x + 7, y, z - 3, stone, 0, 2);
        world.setBlock(x + 7, y, z + 12, stone, 0, 2);
        world.setBlock(x + 7, y, z + 13, stone, 0, 2);

        // Fifteenth row - 9
        world.setBlock(x + 8, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 8, y, z + 12, quartz, 1, 2);

        // Sixteenth row
        world.setBlock(x + 9, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 9, y, z + 12, quartz, 1, 2);

        // Seventeenth row
        world.setBlock(x + 10, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 10, y, z - 2, quartz, 1, 2);
        world.setBlock(x + 10, y, z, quartz, 1, 2);
        world.setBlock(x + 10, y, z + 9, quartz, 1, 2);
        world.setBlock(x + 10, y, z + 11, quartz, 1, 2);
        world.setBlock(x + 10, y, z + 12, quartz, 1, 2);

        // Eighteenth row - 6
        world.setBlock(x + 11, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 11, y, z - 2, quartz, 1, 2);
        world.setBlock(x + 11, y, z - 1, quartz, 1, 2);
        world.setBlock(x + 11, y, z + 10, quartz, 1, 2);
        world.setBlock(x + 11, y, z + 11, quartz, 1, 2);
        world.setBlock(x + 11, y, z + 12, quartz, 1, 2);

        // Nineteenth row
        world.setBlock(x + 12, y, z - 1, quartz, 1, 2);
        world.setBlock(x + 12, y, z + 10, quartz, 1, 2);

        // Twentieth row - 4
        world.setBlock(x + 13, y, z - 1, quartz, 1, 2);
        world.setBlock(x + 13, y, z + 10, quartz, 1, 2);

        // Twenty-first row
        world.setBlock(x + 14, y, z - 1, quartz, 1, 2);
        world.setBlock(x + 14, y, z, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 1, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 2, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 3, stone, 0, 2);
        world.setBlock(x + 14, y, z + 6, stone, 0, 2);
        world.setBlock(x + 14, y, z + 7, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 8, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 9, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 10, quartz, 1, 2);

        // Twenty-second row
        world.setBlock(x + 15, y, z + 3, stone, 0, 2);
        world.setBlock(x + 15, y, z + 6, stone, 0, 2);

        // Twenty-third row

        y++;

        // Fifth layer

        // First row

        // Second row
        world.setBlock(x - 5, y, z + 3, quartz, 1, 2);
        world.setBlock(x - 5, y, z + 4, stone, 0, 2);
        world.setBlock(x - 5, y, z + 5, stone, 0, 2);
        world.setBlock(x - 5, y, z + 6, quartz, 1, 2);

        // Third row
        world.setBlock(x - 4, y, z, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 1, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 2, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 3, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 4, stone, 0, 2);
        world.setBlock(x - 4, y, z + 5, stone, 0, 2);
        world.setBlock(x - 4, y, z + 6, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 7, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 8, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 9, quartz, 1, 2);

        // Fourth row
        world.setBlock(x - 3, y, z - 1, quartz, 1, 2);
        world.setBlock(x - 3, y, z, quartz, 1, 2);
        world.setBlock(x - 3, y, z + 9, quartz, 1, 2);
        world.setBlock(x - 3, y, z + 10, quartz, 1, 2);

        // Fifth row
        world.setBlock(x - 2, y, z - 1, quartz, 1, 2);
        world.setBlock(x - 2, y, z + 10, quartz, 1, 2);

        // Sixth row
        world.setBlock(x - 1, y, z - 2, quartz, 1, 2);
        world.setBlock(x - 1, y, z - 1, quartz, 1, 2);
        world.setBlock(x - 1, y, z + 10, quartz, 1, 2);
        world.setBlock(x - 1, y, z + 11, quartz, 1, 2);

        // Seventh row
        world.setBlock(x, y, z - 3, quartz, 1, 2);
        world.setBlock(x, y, z - 2, quartz, 1, 2);
        world.setBlock(x, y, z, quartz, 1, 2);
        world.setBlock(x, y, z + 9, quartz, 1, 2);
        world.setBlock(x, y, z + 11, quartz, 1, 2);
        world.setBlock(x, y, z + 12, quartz, 1, 2);

        // Eighth row
        world.setBlock(x + 1, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 1, y, z + 12, quartz, 1, 2);

        // Ninth row
        world.setBlock(x + 2, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 2, y, z + 12, quartz, 1, 2);

        // Tenth row
        world.setBlock(x + 3, y, z - 4, quartz, 1, 2);
        world.setBlock(x + 3, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 3, y, z + 12, quartz, 1, 2);
        world.setBlock(x + 3, y, z + 13, quartz, 1, 2);

        // Eleventh row
        world.setBlock(x + 4, y, z - 4, stone, 0, 2);
        world.setBlock(x + 4, y, z - 3, stone, 0, 2);
        world.setBlock(x + 4, y, z + 12, stone, 0, 2);
        world.setBlock(x + 4, y, z + 13, stone, 0, 2);

        // Twelfth row - middle row
        world.setBlock(x + 5, y, z - 4, stone, 0, 2);
        world.setBlock(x + 5, y, z - 3, stone, 0, 2);
        world.setBlock(x + 5, y, z + 12, stone, 0, 2);
        world.setBlock(x + 5, y, z + 13, stone, 0, 2);

        // Thirteenth row
        world.setBlock(x + 6, y, z - 4, stone, 0, 2);
        world.setBlock(x + 6, y, z - 3, stone, 0, 2);
        world.setBlock(x + 6, y, z + 12, stone, 0, 2);
        world.setBlock(x + 6, y, z + 13, stone, 0, 2);

        // Fourteenth row
        world.setBlock(x + 7, y, z - 4, quartz, 1, 2);
        world.setBlock(x + 7, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 7, y, z + 12, quartz, 1, 2);
        world.setBlock(x + 7, y, z + 13, quartz, 1, 2);

        // Fifteenth row - 9
        world.setBlock(x + 8, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 8, y, z + 12, quartz, 1, 2);

        // Sixteenth row
        world.setBlock(x + 9, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 9, y, z + 12, quartz, 1, 2);

        // Seventeenth row
        world.setBlock(x + 10, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 10, y, z - 2, quartz, 1, 2);
        world.setBlock(x + 10, y, z, quartz, 1, 2);
        world.setBlock(x + 10, y, z + 9, quartz, 1, 2);
        world.setBlock(x + 10, y, z + 11, quartz, 1, 2);
        world.setBlock(x + 10, y, z + 12, quartz, 1, 2);

        // Eighteenth row - 6
        world.setBlock(x + 11, y, z - 2, quartz, 1, 2);
        world.setBlock(x + 11, y, z - 1, quartz, 1, 2);
        world.setBlock(x + 11, y, z + 10, quartz, 1, 2);
        world.setBlock(x + 11, y, z + 11, quartz, 1, 2);

        // Nineteenth row
        world.setBlock(x + 12, y, z - 1, quartz, 1, 2);
        world.setBlock(x + 12, y, z + 10, quartz, 1, 2);

        // Twentieth row - 4
        world.setBlock(x + 13, y, z - 1, quartz, 1, 2);
        world.setBlock(x + 13, y, z, quartz, 1, 2);
        world.setBlock(x + 13, y, z + 9, quartz, 1, 2);
        world.setBlock(x + 13, y, z + 10, quartz, 1, 2);

        // Twenty-first row
        world.setBlock(x + 14, y, z, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 1, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 2, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 3, stone, 0, 2);
        world.setBlock(x + 14, y, z + 6, stone, 0, 2);
        world.setBlock(x + 14, y, z + 7, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 8, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 9, quartz, 1, 2);

        // Twenty-second row
        world.setBlock(x + 15, y, z + 3, quartz, 1, 2);
        world.setBlock(x + 15, y, z + 4, stone, 0, 2);
        world.setBlock(x + 15, y, z + 5, stone, 0, 2);
        world.setBlock(x + 15, y, z + 6, quartz, 1, 2);

        // Twenty-third row

        y++;

        // Sixth layer

        // Third row
        world.setBlock(x - 4, y, z + 1, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 2, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 3, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 4, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 5, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 6, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 7, quartz, 1, 2);
        world.setBlock(x - 4, y, z + 8, quartz, 1, 2);

        // Fourth row
        world.setBlock(x - 3, y, z, quartz, 1, 2);
        world.setBlock(x - 3, y, z + 9, quartz, 1, 2);

        // Fifth row
        world.setBlock(x - 2, y, z - 1, quartz, 1, 2);
        world.setBlock(x - 2, y, z + 10, quartz, 1, 2);

        // Sixth row
        world.setBlock(x - 1, y, z - 1, quartz, 1, 2);
        world.setBlock(x - 1, y, z + 10, quartz, 1, 2);

        // Seventh row
        world.setBlock(x, y, z - 2, quartz, 1, 2);
        world.setBlock(x, y, z, quartz, 1, 2);
        world.setBlock(x, y, z + 9, quartz, 1, 2);
        world.setBlock(x, y, z + 11, quartz, 1, 2);

        // Eighth row
        world.setBlock(x + 1, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 1, y, z + 12, quartz, 1, 2);

        // Ninth row
        world.setBlock(x + 2, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 2, y, z + 12, quartz, 1, 2);

        // Tenth row
        world.setBlock(x + 3, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 3, y, z + 12, quartz, 1, 2);

        // Eleventh row
        world.setBlock(x + 4, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 4, y, z + 12, quartz, 1, 2);

        // Twelfth row - middle row
        world.setBlock(x + 5, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 5, y, z + 12, quartz, 1, 2);

        // Thirteenth row
        world.setBlock(x + 6, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 6, y, z + 12, quartz, 1, 2);

        // Fourteenth row
        world.setBlock(x + 7, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 7, y, z + 12, quartz, 1, 2);

        // Fifteenth row - 9
        world.setBlock(x + 8, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 8, y, z + 12, quartz, 1, 2);

        // Sixteenth row
        world.setBlock(x + 9, y, z - 3, quartz, 1, 2);
        world.setBlock(x + 9, y, z + 12, quartz, 1, 2);

        // Seventeenth row
        world.setBlock(x + 10, y, z - 2, quartz, 1, 2);
        world.setBlock(x + 10, y, z, quartz, 1, 2);
        world.setBlock(x + 10, y, z + 9, quartz, 1, 2);
        world.setBlock(x + 10, y, z + 11, quartz, 1, 2);

        // Eighteenth row - 6
        world.setBlock(x + 11, y, z - 1, quartz, 1, 2);
        world.setBlock(x + 11, y, z + 10, quartz, 1, 2);

        // Nineteenth row
        world.setBlock(x + 12, y, z - 1, quartz, 1, 2);
        world.setBlock(x + 12, y, z + 10, quartz, 1, 2);

        // Twentieth row - 4
        world.setBlock(x + 13, y, z, quartz, 1, 2);
        world.setBlock(x + 13, y, z + 9, quartz, 1, 2);

        // Twenty-first row
        world.setBlock(x + 14, y, z, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 1, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 2, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 3, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 4, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 5, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 6, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 7, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 8, quartz, 1, 2);
        world.setBlock(x + 14, y, z + 9, quartz, 1, 2);

        y++;
        // Seventh layer

        // Seventh row
        world.setBlock(x, y, z, quartz, 1, 2);
        world.setBlock(x, y, z + 1, quartz, 1, 2);
        world.setBlock(x, y, z + 2, quartz, 1, 2);
        world.setBlock(x, y, z + 3, quartz, 1, 2);
        world.setBlock(x, y, z + 4, quartz, 1, 2);
        world.setBlock(x, y, z + 5, quartz, 1, 2);
        world.setBlock(x, y, z + 6, quartz, 1, 2);
        world.setBlock(x, y, z + 7, quartz, 1, 2);
        world.setBlock(x, y, z + 8, quartz, 1, 2);
        world.setBlock(x, y, z + 9, quartz, 1, 2);

        // Eighth row
        world.setBlock(x + 1, y, z, quartz, 1, 2);
        world.setBlock(x + 1, y, z + 9, quartz, 1, 2);

        // Ninth row
        world.setBlock(x + 2, y, z, quartz, 1, 2);
        world.setBlock(x + 2, y, z + 9, quartz, 1, 2);

        // Tenth row
        world.setBlock(x + 3, y, z, quartz, 1, 2);
        world.setBlock(x + 3, y, z + 9, quartz, 1, 2);

        // Eleventh row
        world.setBlock(x + 4, y, z, quartz, 1, 2);
        world.setBlock(x + 4, y, z + 9, quartz, 1, 2);

        // Twelfth row - middle row
        world.setBlock(x + 5, y, z, quartz, 1, 2);
        world.setBlock(x + 5, y, z + 9, quartz, 1, 2);

        // Thirteenth row
        world.setBlock(x + 6, y, z, quartz, 1, 2);
        world.setBlock(x + 6, y, z + 9, quartz, 1, 2);

        // Fourteenth row
        world.setBlock(x + 7, y, z, quartz, 1, 2);
        world.setBlock(x + 7, y, z + 9, quartz, 1, 2);

        // Fifteenth row - 9
        world.setBlock(x + 8, y, z, quartz, 1, 2);
        world.setBlock(x + 8, y, z + 9, quartz, 1, 2);

        // Sixteenth row
        world.setBlock(x + 9, y, z, quartz, 1, 2);
        world.setBlock(x + 9, y, z + 9, quartz, 1, 2);

        // Seventeenth row
        world.setBlock(x + 10, y, z, quartz, 1, 2);
        world.setBlock(x + 10, y, z + 1, quartz, 1, 2);
        world.setBlock(x + 10, y, z + 2, quartz, 1, 2);
        world.setBlock(x + 10, y, z + 3, quartz, 1, 2);
        world.setBlock(x + 10, y, z + 4, quartz, 1, 2);
        world.setBlock(x + 10, y, z + 5, quartz, 1, 2);
        world.setBlock(x + 10, y, z + 6, quartz, 1, 2);
        world.setBlock(x + 10, y, z + 7, quartz, 1, 2);
        world.setBlock(x + 10, y, z + 8, quartz, 1, 2);
        world.setBlock(x + 10, y, z + 9, quartz, 1, 2);

        return true;
    }
}
