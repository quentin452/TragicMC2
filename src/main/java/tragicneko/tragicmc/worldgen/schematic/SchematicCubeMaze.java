package tragicneko.tragicmc.worldgen.schematic;

import java.util.Arrays;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;

public class SchematicCubeMaze extends Schematic {

    private static final byte[][] values = new byte[][] { { -10, -10 }, { -10, -4 }, { -10, 3 }, { -10, 9 },
        { -4, -10 }, { -4, -4 }, { -4, 3 }, { -4, 9 }, { 3, -10 }, { 3, -4 }, { 3, 3 }, { 3, 9 }, { 9, -10 }, { 9, -4 },
        { 9, 3 }, { 9, 9 } };

    public SchematicCubeMaze() {
        super(32, 24, 24);
    }

    @Override
    public boolean generateStructure(int variant, World world, Random rand, int x, int y, int z) {

        for (byte y1 = 0; y1 < 37; y1++) {
            for (byte x1 = -13; x1 < 13; x1++) {
                for (byte z1 = -13; z1 < 13; z1++) {
                    if (y1 % 6 == 0) {
                        world.setBlock(x + x1, y + y1, z + z1, Blocks.obsidian);
                    } else {
                        world.setBlockToAir(x + x1, y + y1, z + z1);
                    }
                }
            }

            for (byte x1 = -13; x1 < 13; x1++) {
                world.setBlock(x + x1, y + y1, z + 12, Blocks.obsidian);
                world.setBlock(x + x1, y + y1, z - 13, Blocks.obsidian);
                world.setBlock(x - 13, y + y1, z + x1, Blocks.obsidian);
                world.setBlock(x + 12, y + y1, z + x1, Blocks.obsidian);

                world.setBlock(x + x1, y + y1, z + 6, Blocks.obsidian);
                world.setBlock(x + x1, y + y1, z - 7, Blocks.obsidian);
                world.setBlock(x - 7, y + y1, z + x1, Blocks.obsidian);
                world.setBlock(x + 6, y + y1, z + x1, Blocks.obsidian);

                world.setBlock(x + x1, y + y1, z, Blocks.obsidian);
                world.setBlock(x + x1, y + y1, z - 1, Blocks.obsidian);
                world.setBlock(x - 1, y + y1, z + x1, Blocks.obsidian);
                world.setBlock(x, y + y1, z + x1, Blocks.obsidian);
            }
        }

        byte exits = 0;
        boolean invFlag = false;
        byte roomType = 0;
        byte t = 0;
        byte trap = 0;

        for (byte y1 = 0; y1 < 33; y1 += 6) {
            for (byte i = 0; i < values.length; i++) // generate glowstone and the possible openings for each room
            {
                byte x0 = values[i][0];
                byte z0 = values[i][1];

                if ((Math.abs(x0) > 6 || Math.abs(z0) > 6) && exits < 5 && rand.nextInt(16) == 0) {
                    exits++;
                    invFlag = true;
                } else {
                    invFlag = false;
                }

                if (Math.abs(x0) > 6 || Math.abs(z0) > 6) // if on the edge
                {
                    if (Math.abs(x0) > 6) // if on the edge of the x axis
                    {
                        if (Math.abs(z0) > 6) // if on the corner
                        {
                            boolean flag = x0 > 0;
                            boolean flag2 = z0 > 0; // determine which corner it's on to eliminate exits

                            if (Math.abs(x0) > 6) // generate exit based on x value
                            {
                                if (flag) {
                                    roomType = (byte) (flag2 ? 5 : 6);
                                } else {
                                    roomType = (byte) (flag2 ? 8 : 7);
                                }
                            } else if (Math.abs(z0) > 6)// generate based on z value
                            {
                                if (flag2) {
                                    roomType = (byte) (flag ? 5 : 8);
                                } else {
                                    roomType = (byte) (flag ? 6 : 7);
                                }
                            } else {
                                return false;
                            }
                        } else {
                            boolean flag = x0 > 0; // eliminate exits based on which edge it's on
                            roomType = (byte) (flag ? 1 : 2);
                        }
                    } else if (Math.abs(z0) > 6) // if on the edge of the z axis
                    {
                        boolean flag = z0 > 0; // eliminate exits based on which edge it's on
                        roomType = (byte) (flag ? 3 : 4);
                    } else // this would be an error of some sort since the previous check should remove all middle
                           // rooms
                    {
                        return false;
                    }
                } else // middle room
                {
                    roomType = 0;
                }

                this.generateOpenings(world, rand, x + x0, y + y1, z + z0, roomType, y1, invFlag);
                boolean treasure = roomType == 0 && rand.nextInt(8) == 0 && t == 0 && y1 > 5 && y1 < 28;
                world.setBlock(
                    x + x0,
                    y + y1 + 3,
                    z + z0,
                    treasure ? Blocks.chest : (rand.nextInt(16) == 0 ? Blocks.air : Blocks.glowstone),
                    0,
                    2);
                if (treasure) {
                    this.applyChestContents(world, rand, x + x0, y + y1 + 3, z + z0, TragicItems.NetherStructureHook);
                    t++;
                } else if (trap < 3 && rand.nextInt(16) == 0) {
                    if (rand.nextBoolean()) {
                        world.setBlock(x + x0, y + y1 + 4, z + z0, Blocks.tnt, 1, 2);
                    } else {
                        world.setBlock(x + x0, y + y1 + 4, z + z0, Blocks.mob_spawner, 0, 2);
                        this.setSpawnerMob(
                            world,
                            x + x0,
                            y + y1 + 4,
                            z + z0,
                            TragicConfig.allowInkling ? "TragicMC.Inkling" : "Enderman");
                    }
                    trap++;
                }
            }
        }
        return true;
    }

    /**
     * Generate openings based on room location in grid, for roomtypes: 0 = normal, 1 = +x, 2 = -x, 3 = +z, 4 = -z, 5 =
     * +x+z, 6 = +x-z, 7 =-x-z, 8 = -x+z,
     * inverse is used for exits to generate an opening to outside of the cube
     * 
     * @param world
     * @param rand
     * @param x
     * @param y
     * @param z
     * @param roomType
     */
    public void generateOpenings(World world, Random rand, final int x, final int y, final int z, int roomType,
        final byte y0, final boolean inverse) {
        boolean[] discrim = new boolean[6];
        Arrays.fill(discrim, true);

        if (roomType == 3 || roomType == 5 || roomType == 8) // these are where we will set our discriminators
        {
            discrim[0] = false; // positive z exclude
        }

        if (roomType == 4 || roomType == 6 || roomType == 7) {
            discrim[1] = false; // negative z exclude
        }

        if (roomType == 1 || roomType == 5 || roomType == 6) {
            discrim[2] = false; // positive x exclude
        }

        if (roomType == 2 || roomType == 7 || roomType == 8) {
            discrim[3] = false; // negative x exclude
        }

        if (y0 < 10) discrim[4] = false;
        if (y0 > 26) discrim[5] = false;

        if (inverse) {
            for (int i = 0; i < discrim.length; i++) {
                if (!discrim[i]) discrim[i] = true;
            }
        }

        byte attempts = (byte) (inverse ? 3 : 2);

        for (byte i = 0; i < attempts; i++) {
            byte j = (byte) rand.nextInt(discrim.length);
            boolean flag = false;

            while (!discrim[j]) j = (byte) rand.nextInt(discrim.length);

            if (discrim[j]) {
                if (j == 0) {
                    for (int x1 = 2; x1 < 5; x1++) // positive z exclude
                    {
                        world.setBlockToAir(x, y + 1, z + x1);
                        world.setBlockToAir(x, y + 2, z + x1);
                    }
                } else if (j == 1) {
                    for (int x1 = -2; x1 > -5; x1--) // negative z exclude
                    {
                        world.setBlockToAir(x, y + 1, z + x1);
                        world.setBlockToAir(x, y + 2, z + x1);
                    }
                } else if (j == 2) {
                    for (int x1 = 2; x1 < 5; x1++) // positive x exclude
                    {
                        world.setBlockToAir(x + x1, y + 1, z);
                        world.setBlockToAir(x + x1, y + 2, z);
                    }
                } else if (j == 3) {
                    for (int x1 = -2; x1 > -5; x1--) // negative x exclude
                    {
                        world.setBlockToAir(x + x1, y + 1, z);
                        world.setBlockToAir(x + x1, y + 2, z);
                    }
                } else if (j == 4) {
                    for (int x1 = 1; x1 > -1; x1--) // exclude bottom row
                    {
                        world.setBlockToAir(x, y + x1, z);
                        world.setBlockToAir(x, y + x1, z);
                        world.setBlockToAir(x + 1, y + x1, z);
                        world.setBlockToAir(x - 1, y + x1, z);
                        world.setBlockToAir(x, y + x1, z + 1);
                        world.setBlockToAir(x, y + x1, z - 1);
                    }
                } else if (j == 5) // exclude top row
                {
                    for (int x1 = 5; x1 < 7; x1++) {
                        world.setBlockToAir(x, y + x1, z);
                        world.setBlockToAir(x, y + x1, z);
                        world.setBlockToAir(x + 1, y + x1, z);
                        world.setBlockToAir(x - 1, y + x1, z);
                        world.setBlockToAir(x, y + x1, z + 1);
                        world.setBlockToAir(x, y + x1, z - 1);
                    }
                }
            }
        }
    }
}
