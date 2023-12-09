package tragicneko.tragicmc.worldgen.schematic;

import static tragicneko.tragicmc.TragicBlocks.Candle;
import static tragicneko.tragicmc.TragicBlocks.DarkSand;
import static tragicneko.tragicmc.TragicBlocks.DarkSandstone;
import static tragicneko.tragicmc.TragicBlocks.SummonBlock;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicItems;

public class SchematicClaymationRuin extends Schematic {

    private static final byte[] metas = new byte[] { 0, 1, 3, 2, 5, 4 };
    private static final byte ROOM_PADDING = 13;
    private static final byte ROOM_HEIGHT = 8;

    public SchematicClaymationRuin() {
        super(5, 5, 5);
    }

    @Override
    public boolean generateStructure(int variant, World world, Random rand, int x, int y, int z) {

        this.generateEntrance(world, rand, x, y, z);
        return true;
    }

    public byte[][] createMap(Random rand) {
        byte[][] map = new byte[5][5];
        boolean flag = false; // if the starting room has been created yet in the map
        boolean flag2 = false; // if the boss room has been created yet in the map
        byte noroom = 0; // have a max of 5 no rooms per map
        byte trap = 0; // have a max of 3 trap rooms per map
        byte strt = 0;

        for (byte i = 0; i < map.length; i++) {
            for (byte j = 0; j < map[i].length; j++) {
                byte b0 = !flag ? (byte) rand.nextInt(metas.length)
                    : (!flag2 ? (byte) rand.nextInt(metas.length - 1) : (byte) (rand.nextInt(metas.length - 3) + 1));

                if (i == 0) {
                    if (!flag && j == map[i].length - 1) b0 = 5; // ensures that a room in the first byte array is the
                                                                 // starting room
                    if (b0 == 4) b0 = (byte) (rand.nextInt(3) + 1); // ensure that a boss room is not in the first array
                    if (b0 == 5) {
                        flag = true; // if a starting room is chosen, no more shall be chosen
                        strt = (byte) j; // saves which byte is the starting room to make sure there is a connection to
                                         // it, this will be changed per following array so that it is more likely to
                                         // have a connected path
                    }
                } else {
                    if (j == strt && b0 == 0) b0 = (byte) (rand.nextInt(metas.length - 2) + 1); // ensures that the room
                                                                                                // connected to the
                                                                                                // start is either a
                                                                                                // trap or a column
                    if ((i == 0 || flag2) && b0 == 4) b0 = (byte) (rand.nextInt(metas.length - 3) + 1); // if a boss
                                                                                                        // room is
                                                                                                        // chosen before
                                                                                                        // the 4th row
                                                                                                        // it is
                                                                                                        // rerolled as
                                                                                                        // an empty room
                                                                                                        // or column
                    if (b0 > 0 && rand.nextBoolean()) strt = (byte) j; // saves the byte of a non-empty room
                    if (i == map.length - 1 && !flag2 && j == map[i].length - 1) b0 = 4; // ensures that the last room
                                                                                         // is a boss room if none were
                                                                                         // chosen before in this array
                    if (i > 0 && !flag2 && b0 == 4) flag2 = true; // if a boss room is chosen no more shall be chosen
                }
                if (b0 == 0) noroom++;
                if (noroom > 7 && b0 == 0) b0 = (byte) (rand.nextInt(metas.length - 3) + 1); // set meta to something
                                                                                             // that isn't an empty room
                if (b0 == 3) trap++;
                if (trap > 3 && b0 == 3) b0 = (byte) (rand.nextBoolean() ? 1 : 2); // set meta to a empty or column room
                map[i][j] = b0;
            }
        }

        return map;
    }

    public ArrayList<byte[][]> generateTablet(World world, Random rand, final int x, final int y, final int z) {
        byte[][] map = this.createMap(rand);
        byte[][] map2 = this.createMap(rand);

        for (byte i = 0; i < map.length; i++) {
            for (byte j = 0; j < map[i].length; j++) {
                if (map2[i][j] == 5) map2[i][j] = (byte) (rand.nextInt(metas.length - 2) + 1); // if the 2nd map has a
                                                                                               // starting room, replace
                                                                                               // with a different one
                if (map[i][j] == 4 && map2[i][j] == 4) // ensures that the drop down is not the boss room, if it is then
                                                       // replace with a normal one and place the boss room elsewhere
                {
                    map2[i][j] = 1;
                    byte val = (byte) rand.nextInt(map2.length);
                    byte val2 = (byte) rand.nextInt(map2[val].length);
                    if (val != i && val2 != j) map2[val][val2] = 4; // attempt to set a new boss dropdown room to avoid
                                                                    // there being a double dropdown (effectively making
                                                                    // the whole second floor useless)
                    else // if the randomly generated one matches the previous one, then pick a random room out of the
                         // first array (since the first dropdown will never be in the first array)
                    {
                        map2[0][rand.nextInt(map2[0].length)] = 4;
                    }
                }
                if (map[i][j] == 4 && map2[i][j] != 1) map2[i][j] = 1; // ensure that the 2nd map has an empty room at
                                                                       // the dropdown of the first
                world.setBlock(x + j - 2, y + i, z, DarkSandstone, metas[map[i][j]], 2); // hide the tablet until after
                                                                                         // I get the generation for
                                                                                         // each room out of the way
            }
        }

        for (byte y1 = -2; y1 < 0; y1++) {
            for (byte x1 = -2; x1 < 3; x1++) {
                if (world.getBlock(x + x1, y + y1, z)
                    .isAir(world, x + x1, y + y1, z)) world.setBlock(x + x1, y + y1, z, DarkSandstone, 0, 2);
            }
        }

        ArrayList<byte[][]> list = new ArrayList<byte[][]>();
        list.add(map);
        list.add(map2);

        return list;
    }

    public void generateEntrance(World world, Random rand, final int x, final int y, final int z) {
        for (byte y1 = -2; y1 < 3; y1++) {
            for (byte x1 = -2; x1 < 3; x1++) {
                for (byte z1 = -2; z1 < 3; z1++) {
                    byte m = 1;
                    if (z1 == -2 || z1 == 2 || x1 == -2 || x1 == 2) m = 0;
                    if (y1 < 0) {
                        world.setBlock(x + x1, y + y1, z + z1, DarkSandstone, m, 2);
                    } else {
                        world.setBlockToAir(x + x1, y + y1, z + z1);
                    }
                }
            }
        }

        world.setBlock(x + 2, y, z + 2, DarkSandstone, 0, 2);
        world.setBlock(x - 2, y, z + 2, DarkSandstone, 0, 2);
        world.setBlock(x + 2, y, z - 2, DarkSandstone, 0, 2);
        world.setBlock(x - 2, y, z - 2, DarkSandstone, 0, 2);

        world.setBlock(x + 2, y + 1, z - 2, Candle, 8, 2);
        world.setBlock(x - 2, y + 1, z - 2, Candle, 8, 2);

        byte y1, z1;

        for (y1 = -1, z1 = 2; y1 > -60 && y1 + y > 32; y1--, z1++) {
            for (byte i = 0; i < 5; i++) {
                world.setBlockToAir(x - 2, y + y1 + i, z + z1);
                world.setBlockToAir(x - 1, y + y1 + i, z + z1);
                world.setBlockToAir(x, y + y1 + i, z + z1);
                world.setBlockToAir(x + 1, y + y1 + i, z + z1);
                world.setBlockToAir(x + 2, y + y1 + i, z + z1);
            }

            world.setBlock(x - 2, y + y1 + 1, z + z1, DarkSandstone, 0, 2);
            world.setBlock(x - 1, y + y1, z + z1, DarkSandstone, 1, 2);
            world.setBlock(x, y + y1, z + z1, DarkSandstone, 1, 2);
            world.setBlock(x + 1, y + y1, z + z1, DarkSandstone, 1, 2);
            world.setBlock(x + 2, y + y1 + 1, z + z1, DarkSandstone, 0, 2);

            world.setBlock(x - 2, y + y1, z + z1, DarkSandstone, 0, 2);
            world.setBlock(x + 2, y + y1, z + z1, DarkSandstone, 0, 2);

            world.setBlock(x - 1, y + y1, z + z1 - 1, DarkSandstone, 1, 2);
            world.setBlock(x, y + y1, z + z1 - 1, DarkSandstone, 1, 2);
            world.setBlock(x + 1, y + y1, z + z1 - 1, DarkSandstone, 1, 2);

            if ((y1 - 2) % 3 == 0) {
                world.setBlock(x + 2, y + y1 + 2, z + z1, Candle, 8, 2);
                world.setBlock(x - 2, y + y1 + 2, z + z1, Candle, 8, 2);
            } else if ((y1 - 1) % 3 == 0) {
                for (byte i = 1; i < 5; i++) {
                    world.setBlock(x + 2, y + y1 + i, z + z1, DarkSandstone, 2, 2);
                    world.setBlock(x - 2, y + y1 + i, z + z1, DarkSandstone, 2, 2);
                }
            }
        }

        for (byte j = 0; j < 5; j++) {
            for (byte x1 = -2; x1 < 3; x1++) {
                for (byte k = 0; k < 4; k++) world.setBlockToAir(x + x1, y + y1 + j, z + z1 + k);
            }
        }

        for (byte j = 0; j < 2; j++) {
            world.setBlock(x + 2, y + y1, z + z1 + j, DarkSandstone, 0, 2);
            world.setBlock(x + 1, y + y1, z + z1 + j, DarkSandstone, 1, 2);
            world.setBlock(x, y + y1, z + z1 + j, DarkSandstone, 1, 2);
            world.setBlock(x - 1, y + y1, z + z1 + j, DarkSandstone, 1, 2);
            world.setBlock(x - 2, y + y1, z + z1 + j, DarkSandstone, 0, 2);
        }

        world.setBlock(x + 2, y + y1 + 1, z + z1 + 1, Candle, 8, 2);
        world.setBlock(x - 2, y + y1 + 1, z + z1 + 1, Candle, 8, 2);

        ArrayList<byte[][]> list = this.generateTablet(world, rand, x, y + 1, z - 3);
        this.generateFirstTunnel(world, rand, x, y + y1, z + z1 + 2);
        this.generateRoomsBasedOnMap(world, rand, x, y + y1, z + z1 + 15, list);
    }

    public void generateRoomsBasedOnMap(World world, Random rand, final int x, final int y, final int z,
        final ArrayList<byte[][]> list) // Uses the first room's coords to determine how the grid of rooms will be
                                        // generated, then generates them
    {
        byte[] start = this.findRoomInMap(list.get(0), 5);
        byte[] dropdown = this.findRoomInMap(list.get(0), 4);

        byte w = start[1];
        int offsetX, offsetZ, offsetY;
        byte b0;

        for (byte d = 0; d < list.size(); d++) {
            byte[][] map = list.get(d);

            for (byte i = 0; i < map.length; i++) {
                for (byte j = 0; j < map[i].length; j++) {
                    b0 = map[i][j];
                    offsetX = (j - w) * ROOM_PADDING;
                    offsetZ = i * ROOM_PADDING;
                    offsetY = d * ROOM_HEIGHT;

                    switch (b0) {
                        case 0:
                            continue;
                        case 1:
                            this.generateEmptyRoom(world, rand, x + offsetX, y - offsetY, z + offsetZ);
                            break;
                        case 2:
                            this.generateColumnRoom(world, rand, x + offsetX, y - offsetY, z + offsetZ);
                            break;
                        case 3:
                            this.generateTrapRoom(world, rand, x + offsetX, y - offsetY, z + offsetZ);
                            break;
                        case 4:
                            if (d != 0) this.generateBossRoom(world, rand, x + offsetX, y - offsetY, z + offsetZ);
                            break;
                        case 5:
                            this.generateColumnRoom(world, rand, x + offsetX, y - offsetY, z + offsetZ);
                            break;
                    }

                    if (i == dropdown[0] && j == dropdown[1] && d == 1) // generate it on the generation of the 2nd
                                                                        // floor to allow the hole to generate down to
                                                                        // the proper height
                    {
                        this.generateDropdown(world, rand, x + offsetX, y, z + offsetZ);
                    }
                }
            }
        }
    }

    public byte[] findRoomInMap(final byte[][] map, final int room) // returns first room in the input map that matches
                                                                    // the room number input, useful for finding
                                                                    // entrance and boss rooms
    {
        for (byte i = 0; i < map.length; i++) {
            for (byte j = 0; j < map[i].length; j++) {
                if (map[i][j] == room) return new byte[] { i, j };
            }
        }

        return new byte[] { 0, 0 };
    }

    public void generateFirstTunnel(World world, Random rand, final int x, final int y, final int z) // generates the
                                                                                                     // first tunnel
                                                                                                     // which leads into
                                                                                                     // the first room
    {
        for (byte z1 = 0; z1 < 5; z1++) {
            for (byte x1 = -3; x1 < 4; x1++) {
                for (byte y1 = 0; y1 < 6; y1++) {
                    world.setBlockToAir(x + x1, y + y1, z + z1);
                }
            }

            for (byte x1 = -2; x1 < 3; x1++) {
                byte m = 1;
                if (x1 == -2 || x1 == 2) m = 0;
                world.setBlock(x + x1, y, z + z1, DarkSandstone, m, 2);
            }

            if (z1 >= 2 && z1 <= 3 || z1 >= 6 && z1 <= 7) {
                world.setBlock(x + 3, y, z + z1, DarkSandstone, 0, 2);
                world.setBlock(x - 3, y, z + z1, DarkSandstone, 0, 2);

                for (byte y1 = 1; y1 < 6; y1++) {
                    world.setBlock(x + 4, y + y1, z + z1, DarkSandstone, 4, 2);
                    world.setBlock(x - 4, y + y1, z + z1, DarkSandstone, 4, 2);
                }
            } else {
                for (byte y1 = 1; y1 < 6; y1++) {
                    world.setBlock(x + 3, y + y1, z + z1, DarkSandstone, 2, 2);
                    world.setBlock(x - 3, y + y1, z + z1, DarkSandstone, 2, 2);
                }
            }

            if (z1 == 0) {
                for (byte y1 = 0; y1 < 5; y1++) {
                    world.setBlock(x + 2, y + y1, z + z1, DarkSandstone, 4, 2);
                    world.setBlock(x - 2, y + y1, z + z1, DarkSandstone, 4, 2);
                }

                for (byte x1 = -2; x1 < 3; x1++) {
                    int m = (x1 == -2 || x1 == 2) ? 3 : 4;
                    world.setBlock(x + x1, y + 5, z + z1, DarkSandstone, m, 2);
                }
            } else if (z1 == 1) {
                world.setBlock(x + 2, y + 1, z + z1, Candle, 8, 2);
                world.setBlock(x - 2, y + 1, z + z1, Candle, 8, 2);

                for (byte x1 = -2; x1 < 3; x1++) {
                    world.setBlock(x + x1, y + 6, z + z1, DarkSandstone, 4, 2);
                }
            } else {
                byte m = (byte) (z1 == 4 ? 3 : 2);
                world.setBlock(x + 3, y + 5, z + z1, DarkSandstone, m, 2);
                world.setBlock(x - 3, y + 5, z + z1, DarkSandstone, m, 2);

                for (byte x1 = -2; x1 < 3; x1++) {
                    world.setBlockToAir(x + x1, y + 6, z + z1);
                }

                world.setBlock(x + 2, y + 6, z + z1, DarkSandstone, 4, 2);
                world.setBlock(x - 2, y + 6, z + z1, DarkSandstone, 4, 2);

                for (byte x1 = -1; x1 < 2; x1++) {
                    world.setBlock(x + x1, y + 7, z + z1, DarkSandstone, 3, 2);
                }
            }
        }
    }

    public void generateColumnRoom(World world, Random rand, final int x, final int y, final int z) // normal room with
                                                                                                    // column in the
                                                                                                    // middle, chance to
                                                                                                    // generate with
                                                                                                    // hidden loot
    {
        for (byte y1 = 0; y1 < 8; y1++) {
            for (byte x1 = -4; x1 < 5; x1++) {
                for (byte z1 = -4; z1 < 5; z1++) {
                    if (y1 == 0 || y1 >= 6) {
                        world.setBlock(x + x1, y + y1, z + z1, DarkSandstone, 0, 2);
                    } else {
                        world.setBlockToAir(x + x1, y + y1, z + z1);
                    }
                }
            }
        }

        for (byte x1 = -3; x1 < 4; x1++) {
            for (byte z1 = -3; z1 < 4; z1++) {
                world.setBlock(x + x1, y, z + z1, DarkSandstone, 1, 2);
            }
        }

        for (byte x1 = -2; x1 < 3; x1++) {
            world.setBlock(x + x1, y, z - 4, DarkSandstone, 1, 2);
            world.setBlock(x + x1, y, z + 4, DarkSandstone, 1, 2);
        }

        for (byte z1 = -2; z1 < 3; z1++) {
            world.setBlock(x + 4, y, z + z1, DarkSandstone, 1, 2);
            world.setBlock(x - 4, y, z + z1, DarkSandstone, 1, 2);
        }

        for (byte y1 = 0; y1 < 8; y1++) {
            for (byte x1 = -1; x1 < 2; x1++) {
                for (byte z1 = -1; z1 < 2; z1++) {
                    world.setBlock(x + x1, y + y1, z + z1, DarkSandstone, 0, 2);

                    if (y1 == 0) {
                        world.setBlock(x + 1, y + y1, z + 2, DarkSandstone, 0, 2);
                        world.setBlock(x - 1, y + y1, z + 2, DarkSandstone, 0, 2);
                        world.setBlock(x, y + y1, z + 2, DarkSandstone, 0, 2);

                        world.setBlock(x + 1, y + y1, z - 2, DarkSandstone, 0, 2);
                        world.setBlock(x - 1, y + y1, z - 2, DarkSandstone, 0, 2);
                        world.setBlock(x, y + y1, z - 2, DarkSandstone, 0, 2);

                        world.setBlock(x + 2, y + y1, z - 1, DarkSandstone, 0, 2);
                        world.setBlock(x + 2, y + y1, z, DarkSandstone, 0, 2);
                        world.setBlock(x + 2, y + y1, z + 1, DarkSandstone, 0, 2);

                        world.setBlock(x - 2, y + y1, z - 1, DarkSandstone, 0, 2);
                        world.setBlock(x - 2, y + y1, z, DarkSandstone, 0, 2);
                        world.setBlock(x - 2, y + y1, z + 1, DarkSandstone, 0, 2);
                    }
                }
            }

            if (y1 > 0 && y1 < 6) {
                if (y1 == 1 || y1 == 5) {
                    world.setBlock(x + 4, y + y1, z + 4, DarkSandstone, 4, 2);
                    world.setBlock(x - 4, y + y1, z + 4, DarkSandstone, 4, 2);
                    world.setBlock(x + 4, y + y1, z - 4, DarkSandstone, 4, 2);
                    world.setBlock(x - 4, y + y1, z - 4, DarkSandstone, 4, 2);
                } else {
                    world.setBlock(x + 5, y + y1, z + 4, DarkSandstone, 4, 2);
                    world.setBlock(x - 5, y + y1, z + 4, DarkSandstone, 4, 2);
                    world.setBlock(x + 5, y + y1, z - 4, DarkSandstone, 4, 2);
                    world.setBlock(x - 5, y + y1, z - 4, DarkSandstone, 4, 2);

                    world.setBlock(x + 4, y + y1, z + 5, DarkSandstone, 4, 2);
                    world.setBlock(x - 4, y + y1, z + 5, DarkSandstone, 4, 2);
                    world.setBlock(x + 4, y + y1, z - 5, DarkSandstone, 4, 2);
                    world.setBlock(x - 4, y + y1, z - 5, DarkSandstone, 4, 2);
                }
            }
        }

        if (rand.nextInt(5) == 0) {
            world.setBlock(x, y + 1, z, Blocks.chest, 0, 2);
            world.setBlockToAir(x, y + 2, z);
            world.setBlockToAir(x, y + 3, z);
            this.applyChestContents(world, rand, x, y + 1, z, TragicItems.NetherStructureHook);
        }

        this.generateTunnels(world, rand, x, y, z);
    }

    public void generateEmptyRoom(World world, Random rand, final int x, final int y, final int z) // normal room, small
                                                                                                   // chance to generate
                                                                                                   // with loot
    {
        for (byte y1 = 0; y1 < 8; y1++) {
            for (byte x1 = -4; x1 < 5; x1++) {
                for (byte z1 = -4; z1 < 5; z1++) {
                    if (y1 == 0 || y1 >= 6) {
                        world.setBlock(x + x1, y + y1, z + z1, DarkSandstone, 0, 2);
                    } else {
                        world.setBlockToAir(x + x1, y + y1, z + z1);
                    }
                }
            }
        }

        for (byte x1 = -3; x1 < 4; x1++) {
            for (byte z1 = -3; z1 < 4; z1++) {
                world.setBlock(x + x1, y, z + z1, DarkSandstone, 1, 2);
            }
        }

        for (byte x1 = -2; x1 < 3; x1++) {
            world.setBlock(x + x1, y, z - 4, DarkSandstone, 1, 2);
            world.setBlock(x + x1, y, z + 4, DarkSandstone, 1, 2);
        }

        for (byte z1 = -2; z1 < 3; z1++) {
            world.setBlock(x + 4, y, z + z1, DarkSandstone, 1, 2);
            world.setBlock(x - 4, y, z + z1, DarkSandstone, 1, 2);
        }

        for (byte x1 = -1; x1 < 2; x1++) {
            for (byte z1 = -1; z1 < 2; z1++) {
                world.setBlock(x + x1, y, z + z1, DarkSandstone, 0, 2);
            }
        }

        for (byte y1 = 0; y1 < 8; y1++) {
            if (y1 == 0) {
                for (byte x1 = -1; x1 < 2; x1++) {
                    for (byte z1 = -1; z1 < 2; z1++) {
                        world.setBlock(x + 1, y, z + 2, DarkSandstone, 0, 2);
                        world.setBlock(x - 1, y, z + 2, DarkSandstone, 0, 2);
                        world.setBlock(x, y, z + 2, DarkSandstone, 0, 2);

                        world.setBlock(x + 1, y, z - 2, DarkSandstone, 0, 2);
                        world.setBlock(x - 1, y, z - 2, DarkSandstone, 0, 2);
                        world.setBlock(x, y, z - 2, DarkSandstone, 0, 2);

                        world.setBlock(x + 2, y, z - 1, DarkSandstone, 0, 2);
                        world.setBlock(x + 2, y, z, DarkSandstone, 0, 2);
                        world.setBlock(x + 2, y, z + 1, DarkSandstone, 0, 2);

                        world.setBlock(x - 2, y, z - 1, DarkSandstone, 0, 2);
                        world.setBlock(x - 2, y, z, DarkSandstone, 0, 2);
                        world.setBlock(x - 2, y, z + 1, DarkSandstone, 0, 2);
                    }
                }
            }

            if (y1 > 0 && y1 < 6) {
                if (y1 == 1 || y1 == 5) {
                    world.setBlock(x + 4, y + y1, z + 4, DarkSandstone, 4, 2);
                    world.setBlock(x - 4, y + y1, z + 4, DarkSandstone, 4, 2);
                    world.setBlock(x + 4, y + y1, z - 4, DarkSandstone, 4, 2);
                    world.setBlock(x - 4, y + y1, z - 4, DarkSandstone, 4, 2);
                } else {
                    world.setBlock(x + 5, y + y1, z + 4, DarkSandstone, 4, 2);
                    world.setBlock(x - 5, y + y1, z + 4, DarkSandstone, 4, 2);
                    world.setBlock(x + 5, y + y1, z - 4, DarkSandstone, 4, 2);
                    world.setBlock(x - 5, y + y1, z - 4, DarkSandstone, 4, 2);

                    world.setBlock(x + 4, y + y1, z + 5, DarkSandstone, 4, 2);
                    world.setBlock(x - 4, y + y1, z + 5, DarkSandstone, 4, 2);
                    world.setBlock(x + 4, y + y1, z - 5, DarkSandstone, 4, 2);
                    world.setBlock(x - 4, y + y1, z - 5, DarkSandstone, 4, 2);
                }
            }
        }

        if (rand.nextInt(20) == 0) {
            world.setBlock(x, y + 1, z, Blocks.trapped_chest, 0, 2);
            this.applyChestContents(world, rand, x, y + 1, z, TragicItems.LameChestHook);
        }

        this.generateTunnels(world, rand, x, y, z);
    }

    public void generateDropdown(World world, Random rand, final int x, final int y, final int z) // first floor
                                                                                                  // dropdown
    {
        for (byte y1 = 0; y1 < 8; y1++) {
            for (byte x1 = -4; x1 < 5; x1++) {
                for (byte z1 = -4; z1 < 5; z1++) {
                    if (y1 == 0 || y1 >= 6) {
                        world.setBlock(x + x1, y + y1, z + z1, DarkSandstone, 0, 2);
                    } else {
                        world.setBlockToAir(x + x1, y + y1, z + z1);
                    }
                }
            }
        }

        for (byte x1 = -3; x1 < 4; x1++) {
            for (byte z1 = -3; z1 < 4; z1++) {
                world.setBlock(x + x1, y, z + z1, DarkSandstone, 1, 2);
            }
        }

        for (byte x1 = -2; x1 < 3; x1++) {
            world.setBlock(x + x1, y, z - 4, DarkSandstone, 1, 2);
            world.setBlock(x + x1, y, z + 4, DarkSandstone, 1, 2);
        }

        for (byte z1 = -2; z1 < 3; z1++) {
            world.setBlock(x + 4, y, z + z1, DarkSandstone, 1, 2);
            world.setBlock(x - 4, y, z + z1, DarkSandstone, 1, 2);
        }

        for (byte y1 = -2; y1 < 0; y1++) {
            for (byte x1 = -2; x1 < 3; x1++) {
                for (byte z1 = -2; z1 < 3; z1++) {
                    world.setBlock(x + x1, y + y1, z + z1, DarkSandstone, 0, 2);
                }
            }
        }

        for (byte y1 = -6; y1 < 2; y1++) {
            for (byte x1 = -1; x1 < 2; x1++) {
                for (byte z1 = -1; z1 < 2; z1++) {
                    world.setBlockToAir(x + x1, y + y1, z + z1);
                }
            }
        }

        for (byte y1 = 0; y1 < 8; y1++) {
            if (y1 == 0) {
                for (byte x1 = -1; x1 < 2; x1++) {
                    for (byte z1 = -1; z1 < 2; z1++) {
                        world.setBlock(x + 1, y, z + 2, DarkSandstone, 0, 2);
                        world.setBlock(x - 1, y, z + 2, DarkSandstone, 0, 2);
                        world.setBlock(x, y, z + 2, DarkSandstone, 0, 2);

                        world.setBlock(x + 1, y, z - 2, DarkSandstone, 0, 2);
                        world.setBlock(x - 1, y, z - 2, DarkSandstone, 0, 2);
                        world.setBlock(x, y, z - 2, DarkSandstone, 0, 2);

                        world.setBlock(x + 2, y, z - 1, DarkSandstone, 0, 2);
                        world.setBlock(x + 2, y, z, DarkSandstone, 0, 2);
                        world.setBlock(x + 2, y, z + 1, DarkSandstone, 0, 2);

                        world.setBlock(x - 2, y, z - 1, DarkSandstone, 0, 2);
                        world.setBlock(x - 2, y, z, DarkSandstone, 0, 2);
                        world.setBlock(x - 2, y, z + 1, DarkSandstone, 0, 2);
                    }
                }
            }

            if (y1 > 0 && y1 < 6) {
                if (y1 == 1 || y1 == 5) {
                    world.setBlock(x + 4, y + y1, z + 4, DarkSandstone, 4, 2);
                    world.setBlock(x - 4, y + y1, z + 4, DarkSandstone, 4, 2);
                    world.setBlock(x + 4, y + y1, z - 4, DarkSandstone, 4, 2);
                    world.setBlock(x - 4, y + y1, z - 4, DarkSandstone, 4, 2);
                } else {
                    world.setBlock(x + 5, y + y1, z + 4, DarkSandstone, 4, 2);
                    world.setBlock(x - 5, y + y1, z + 4, DarkSandstone, 4, 2);
                    world.setBlock(x + 5, y + y1, z - 4, DarkSandstone, 4, 2);
                    world.setBlock(x - 5, y + y1, z - 4, DarkSandstone, 4, 2);

                    world.setBlock(x + 4, y + y1, z + 5, DarkSandstone, 4, 2);
                    world.setBlock(x - 4, y + y1, z + 5, DarkSandstone, 4, 2);
                    world.setBlock(x + 4, y + y1, z - 5, DarkSandstone, 4, 2);
                    world.setBlock(x - 4, y + y1, z - 5, DarkSandstone, 4, 2);
                }
            }
        }

        this.generateTunnels(world, rand, x, y, z);
    }

    public void generateBossRoom(World world, Random rand, final int x, final int y, final int z) // boss pit dropdown
                                                                                                  // into large cavern
    {
        this.generateDropdown(world, rand, x, y, z);

        for (byte y1 = 0; y1 < 8; y1++) {
            for (byte x1 = -4; x1 < 5; x1++) {
                for (byte z1 = -4; z1 < 5; z1++) {
                    if (y1 == 0 || y1 >= 6) {
                        world.setBlock(x + x1, y + y1 - 16, z + z1, DarkSandstone, 0, 2);
                    } else {
                        world.setBlockToAir(x + x1, y + y1 - 16, z + z1);
                    }
                }
            }
        }

        for (byte x1 = -3; x1 < 4; x1++) {
            for (byte z1 = -3; z1 < 4; z1++) {
                world.setBlock(x + x1, y - 16, z + z1, DarkSandstone, 1, 2);
            }
        }

        for (byte x1 = -2; x1 < 3; x1++) {
            world.setBlock(x + x1, y - 16, z - 4, DarkSandstone, 1, 2);
            world.setBlock(x + x1, y - 16, z + 4, DarkSandstone, 1, 2);
        }

        for (byte z1 = -2; z1 < 3; z1++) {
            world.setBlock(x + 4, y - 16, z + z1, DarkSandstone, 1, 2);
            world.setBlock(x - 4, y - 16, z + z1, DarkSandstone, 1, 2);
        }

        for (byte x1 = -1; x1 < 2; x1++) {
            for (byte z1 = -1; z1 < 2; z1++) {
                world.setBlock(x + x1, y - 16, z + z1, DarkSandstone, 0, 2);
            }
        }

        for (byte y1 = 0; y1 < 8; y1++) {
            if (y1 == 0) {
                for (byte x1 = -1; x1 < 2; x1++) {
                    world.setBlock(x + x1, y - 16, z - 2, DarkSandstone, 0, 2);
                    world.setBlock(x + 2, y - 16, z + x1, DarkSandstone, 0, 2);
                    world.setBlock(x - 2, y - 16, z + x1, DarkSandstone, 0, 2);
                }
            } else {
                for (byte x1 = -3; x1 < 4; x1++) {
                    world.setBlock(x + x1, y + y1 - 16, z + 5, DarkSandstone, 3, 2);
                    world.setBlock(x + x1, y + y1 - 16, z - 5, DarkSandstone, 3, 2);

                    world.setBlock(x + 5, y + y1 - 16, z + x1, DarkSandstone, 3, 2);
                    world.setBlock(x - 5, y + y1 - 16, z + x1, DarkSandstone, 3, 2);
                }
            }

            if (y1 > 0 && y1 < 6) {
                if (y1 == 1 || y1 == 5) {
                    world.setBlock(x + 4, y + y1 - 16, z + 4, DarkSandstone, 4, 2);
                    world.setBlock(x - 4, y + y1 - 16, z + 4, DarkSandstone, 4, 2);
                    world.setBlock(x + 4, y + y1 - 16, z - 4, DarkSandstone, 4, 2);
                    world.setBlock(x - 4, y + y1 - 16, z - 4, DarkSandstone, 4, 2);
                } else {
                    world.setBlock(x + 5, y + y1 - 16, z + 4, DarkSandstone, 4, 2);
                    world.setBlock(x - 5, y + y1 - 16, z + 4, DarkSandstone, 4, 2);
                    world.setBlock(x + 5, y + y1 - 16, z - 4, DarkSandstone, 4, 2);
                    world.setBlock(x - 5, y + y1 - 16, z - 4, DarkSandstone, 4, 2);

                    world.setBlock(x + 4, y + y1 - 16, z + 5, DarkSandstone, 4, 2);
                    world.setBlock(x - 4, y + y1 - 16, z + 5, DarkSandstone, 4, 2);
                    world.setBlock(x + 4, y + y1 - 16, z - 5, DarkSandstone, 4, 2);
                    world.setBlock(x - 4, y + y1 - 16, z - 5, DarkSandstone, 4, 2);
                }
            }
        }

        for (byte y1 = -3; y1 > -11; y1--) {
            for (byte x1 = -2; x1 < 3; x1++) {
                for (byte z1 = -2; z1 < 3; z1++) {
                    world.setBlock(x + x1, y + y1, z + z1, DarkSandstone, 0, 2);
                }
            }

            for (byte x1 = -1; x1 < 2; x1++) {
                for (byte z1 = -1; z1 < 2; z1++) {
                    world.setBlockToAir(x + x1, y + y1, z + z1);
                }
            }
        }

        for (byte y1 = 0; y1 < 7; y1++) {
            for (byte z1 = 5; z1 < 13; z1++) {
                for (byte x1 = -3; x1 < 4; x1++) world.setBlockToAir(x + x1, y + y1 - 16, z + z1);

                if (y1 == 0) {
                    for (byte x1 = -1; x1 < 2; x1++) world.setBlock(x + x1, y - 16, z + z1, DarkSandstone, 1, 2);

                    world.setBlock(x - 2, y - 16, z + z1, DarkSandstone, 0, 2);
                    world.setBlock(x + 2, y - 16, z + z1, DarkSandstone, 0, 2);

                    if (z1 == 6 || z1 == 7 || z1 == 10 || z1 == 11) {
                        world.setBlock(x - 3, y - 16, z + z1, DarkSandstone, 0, 2);
                        world.setBlock(x + 3, y - 16, z + z1, DarkSandstone, 0, 2);
                    }
                } else if (y1 < 6) {
                    if (z1 == 6 || z1 == 7 || z1 == 10 || z1 == 11) {
                        world.setBlock(x + 4, y + y1 - 16, z + z1, DarkSandstone, 4, 2);
                        world.setBlock(x - 4, y + y1 - 16, z + z1, DarkSandstone, 4, 2);

                        if (y1 == 5) {
                            world.setBlock(x + 3, y + y1 - 16, z + z1, DarkSandstone, 2, 2);
                            world.setBlock(x - 3, y + y1 - 16, z + z1, DarkSandstone, 2, 2);
                        }
                    }

                    if (z1 == 5 || z1 == 8 || z1 == 9 || z1 == 12) {
                        byte m = (byte) (y1 == 5 ? 3 : 2);
                        world.setBlock(x + 3, y + y1 - 16, z + z1, DarkSandstone, m, 2);
                        world.setBlock(x - 3, y + y1 - 16, z + z1, DarkSandstone, m, 2);
                    }

                    if ((z1 == 5 || z1 == 11) && y1 == 1) {
                        world.setBlock(x + 2, y + y1 - 16, z + z1, Candle, 8, 2);
                        world.setBlock(x - 2, y + y1 - 16, z + z1, Candle, 8, 2);
                    }
                } else {
                    world.setBlock(x + 2, y + y1 - 16, z + z1, DarkSandstone, 4, 2);
                    world.setBlock(x - 2, y + y1 - 16, z + z1, DarkSandstone, 4, 2);

                    if (z1 == 5 || z1 == 12) {
                        for (byte x1 = -1; x1 < 2; x1++)
                            world.setBlock(x + x1, y + y1 - 16, z + z1, DarkSandstone, 4, 2);
                    } else {
                        for (byte x1 = -1; x1 < 2; x1++)
                            world.setBlock(x + x1, y + y1 - 15, z + z1, DarkSandstone, 3, 2);
                    }
                }
            }
        }

        this.generateAltarRoom(world, rand, x, y - 16, z + 24);
    }

    public void generateAltarRoom(World world, Random rand, final int x, final int y, final int z) // generates the
                                                                                                   // actual final boss
                                                                                                   // room
    {
        for (byte y1 = 0; y1 < 11; y1++) {
            for (byte x1 = -10; x1 < 11 && y1 > 0; x1++) {
                for (byte z1 = -11; z1 < 12; z1++) {
                    world.setBlockToAir(x + x1, y + y1, z + z1);
                }
            }

            for (byte x1 = -11; x1 < 12; x1++) {
                world.setBlock(x + x1, y + y1, z - 12, DarkSandstone, 4, 2);
                world.setBlock(x + x1, y + y1, z + 12, DarkSandstone, 4, 2);

                world.setBlock(x + 11, y + y1, z + x1, DarkSandstone, 4, 2);
                world.setBlock(x - 11, y + y1, z + x1, DarkSandstone, 4, 2);
            }

            for (byte x1 = -2; x1 < 3 && y1 > 0 && y1 < 7; x1++) {
                for (byte z1 = -12; z1 < -8; z1++) {
                    world.setBlockToAir(x + x1, y + y1, z + z1);
                }
            }

            if (y1 == 0 || y1 == 10) {
                for (byte x1 = -10; x1 < 11; x1++) {
                    for (byte z1 = -11; z1 < 12; z1++) {
                        world.setBlock(x + x1, y + y1, z + z1, DarkSandstone, 1, 2);
                    }
                }

                if (y1 == 0) {
                    for (byte z1 = -11; z1 < 12; z1++) {
                        for (byte x1 = -1; x1 < 2; x1++) {
                            world.setBlock(x + x1, y + y1, z + z1, DarkSandstone, 4, 2);
                        }

                        world.setBlock(x + 2, y + y1, z + z1, DarkSandstone, 0, 2);
                        world.setBlock(x - 2, y + y1, z + z1, DarkSandstone, 0, 2);
                    }
                }
            }

            for (byte z1 = -9; z1 < 6 && y1 > 0; z1 += 3) {
                world.setBlock(x + 2, y + y1, z + z1, DarkSandstone, 3, 2);
                world.setBlock(x - 2, y + y1, z + z1, DarkSandstone, 3, 2);
            }

            for (byte z1 = -11; z1 < 12 && y1 > 0; z1 += 4) {
                world.setBlock(x + 10, y + y1, z + z1, DarkSandstone, 2, 2);
                world.setBlock(x - 10, y + y1, z + z1, DarkSandstone, 2, 2);
            }

            if (y1 > 0) {
                world.setBlock(x + 6, y + y1, z - 11, DarkSandstone, 2, 2);
                world.setBlock(x + 3, y + y1, z - 11, DarkSandstone, 2, 2);
                world.setBlock(x - 3, y + y1, z - 11, DarkSandstone, 2, 2);
                world.setBlock(x - 6, y + y1, z - 11, DarkSandstone, 2, 2);

                if (y1 > 1) {
                    world.setBlock(x + 6, y + y1, z + 11, DarkSandstone, 2, 2);
                    world.setBlock(x + 3, y + y1, z + 11, DarkSandstone, 2, 2);
                    world.setBlock(x - 3, y + y1, z + 11, DarkSandstone, 2, 2);
                    world.setBlock(x - 6, y + y1, z + 11, DarkSandstone, 2, 2);
                }
            }

            if (y1 == 1) {
                for (byte i = 0; i < 42; i++) {
                    int xr = (x + ((rand.nextInt(9) + 3) * (rand.nextBoolean() ? -1 : 1)));
                    int zr = (z + rand.nextInt(24) - 12);
                    if (world.getBlock(xr, y + y1, zr)
                        .isAir(world, xr, y + y1, zr)) world.setBlock(xr, y + y1, zr, Candle, 8, 2);
                }
            } else if (y1 == 7) {
                world.setBlock(x + 2, y + y1, z - 11, DarkSandstone, 2, 2);
                world.setBlock(x - 2, y + y1, z - 11, DarkSandstone, 2, 2);
                world.setBlock(x + 1, y + y1, z - 11, DarkSandstone, 2, 2);
                world.setBlock(x - 1, y + y1, z - 11, DarkSandstone, 2, 2);
                world.setBlock(x, y + y1, z - 11, DarkSandstone, 5, 2);
            } else if (y1 == 8) {
                world.setBlock(x + 2, y + y1, z - 11, Candle, 8, 2);
                world.setBlock(x - 2, y + y1, z - 11, Candle, 8, 2);
                world.setBlock(x + 1, y + y1, z - 11, DarkSandstone, 2, 2);
                world.setBlock(x - 1, y + y1, z - 11, DarkSandstone, 2, 2);
                world.setBlock(x, y + y1, z - 11, DarkSandstone, 2, 2);
            }
        }

        for (byte z1 = 5; z1 < 12; z1++) {
            world.setBlock(x + 2, y + 1, z + z1, DarkSandstone, z1 > 5 ? 1 : 0, 2);
            world.setBlock(x + 1, y + 1, z + z1, DarkSandstone, z1 > 5 ? 1 : 0, 2);
            world.setBlock(x, y + 1, z + z1, DarkSandstone, z1 > 5 ? 1 : 0, 2);
            world.setBlock(x - 1, y + 1, z + z1, DarkSandstone, z1 > 5 ? 1 : 0, 2);
            world.setBlock(x - 2, y + 1, z + z1, DarkSandstone, z1 > 5 ? 1 : 0, 2);

            if (z1 > 5) {
                world.setBlock(x + 3, y + 1, z + z1, DarkSandstone, z1 > 6 ? 1 : 0, 2);
                world.setBlock(x - 3, y + 1, z + z1, DarkSandstone, z1 > 6 ? 1 : 0, 2);

                if (z1 > 6) {
                    world.setBlock(x + 4, y + 1, z + z1, DarkSandstone, z1 > 7 ? 1 : 0, 2);
                    world.setBlock(x - 4, y + 1, z + z1, DarkSandstone, z1 > 7 ? 1 : 0, 2);
                    world.setBlock(x + 5, y + 1, z + z1, DarkSandstone, z1 > 7 ? 1 : 0, 2);
                    world.setBlock(x - 5, y + 1, z + z1, DarkSandstone, z1 > 7 ? 1 : 0, 2);

                    if (z1 > 7) {
                        world.setBlock(x + 6, y + 1, z + z1, DarkSandstone, z1 > 8 ? 1 : 0, 2);
                        world.setBlock(x - 6, y + 1, z + z1, DarkSandstone, z1 > 8 ? 1 : 0, 2);

                        if (z1 > 8) {
                            world.setBlock(x + 7, y + 1, z + z1, DarkSandstone, 0, 2);
                            world.setBlock(x - 7, y + 1, z + z1, DarkSandstone, 0, 2);
                        }
                    }
                }
            }

            if (z1 >= 10) {
                world.setBlock(x + 1, y + 2, z + z1, DarkSandstone, 3, 2);
                world.setBlock(x, y + 2, z + z1, DarkSandstone, 3, 2);
                world.setBlock(x - 1, y + 2, z + z1, DarkSandstone, 3, 2);

                if (z1 == 11) {
                    for (byte y1 = 3; y1 < 7; y1++) {
                        world.setBlock(x + 1, y + y1, z + z1, DarkSandstone, 3, 2);
                        if (y1 < 6) world.setBlock(x, y + y1, z + z1, DarkSandstone, 3, 2);
                        world.setBlock(x - 1, y + y1, z + z1, DarkSandstone, 3, 2);
                    }
                } else {
                    world.setBlock(x + 1, y + 3, z + z1, Candle, 8, 2);
                    world.setBlock(x, y + 3, z + z1, SummonBlock, 9, 2);
                    world.setBlock(x - 1, y + 3, z + z1, Candle, 8, 2);
                }
            }
        }

        world.setBlock(x + 2, y + 2, z + 6, Candle, 8, 2);
        world.setBlock(x - 2, y + 2, z + 6, Candle, 8, 2);
        world.setBlock(x + 5, y + 2, z + 10, Candle, 8, 2);
        world.setBlock(x - 5, y + 2, z + 10, Candle, 8, 2);
    }

    public void generateTrapRoom(World world, Random rand, final int x, final int y, final int z) // generates a trap
                                                                                                  // room, should be a
                                                                                                  // few different
                                                                                                  // varieties of this
    {
        for (byte y1 = 0; y1 < 8; y1++) {
            for (byte x1 = -4; x1 < 5; x1++) {
                for (byte z1 = -4; z1 < 5; z1++) {
                    if (y1 == 0 || y1 >= 6) {
                        world.setBlock(x + x1, y + y1, z + z1, DarkSandstone, 0, 2);
                    } else {
                        world.setBlockToAir(x + x1, y + y1, z + z1);
                    }
                }
            }
        }

        for (byte x1 = -3; x1 < 4; x1++) {
            for (byte z1 = -3; z1 < 4; z1++) {
                world.setBlock(x + x1, y, z + z1, DarkSandstone, 1, 2);
            }
        }

        for (byte x1 = -2; x1 < 3; x1++) {
            world.setBlock(x + x1, y, z - 4, DarkSandstone, 1, 2);
            world.setBlock(x + x1, y, z + 4, DarkSandstone, 1, 2);
        }

        for (byte z1 = -2; z1 < 3; z1++) {
            world.setBlock(x + 4, y, z + z1, DarkSandstone, 1, 2);
            world.setBlock(x - 4, y, z + z1, DarkSandstone, 1, 2);
        }

        for (byte x1 = -1; x1 < 2; x1++) {
            for (byte z1 = -1; z1 < 2; z1++) {
                world.setBlock(x + x1, y, z + z1, DarkSandstone, 0, 2);
            }
        }

        for (byte y1 = 0; y1 < 8; y1++) {
            if (y1 == 0) {
                for (byte x1 = -1; x1 < 2; x1++) {
                    for (byte z1 = -1; z1 < 2; z1++) {
                        world.setBlock(x + 1, y, z + 2, DarkSandstone, 0, 2);
                        world.setBlock(x - 1, y, z + 2, DarkSandstone, 0, 2);
                        world.setBlock(x, y, z + 2, DarkSandstone, 0, 2);

                        world.setBlock(x + 1, y, z - 2, DarkSandstone, 0, 2);
                        world.setBlock(x - 1, y, z - 2, DarkSandstone, 0, 2);
                        world.setBlock(x, y, z - 2, DarkSandstone, 0, 2);

                        world.setBlock(x + 2, y, z - 1, DarkSandstone, 0, 2);
                        world.setBlock(x + 2, y, z, DarkSandstone, 0, 2);
                        world.setBlock(x + 2, y, z + 1, DarkSandstone, 0, 2);

                        world.setBlock(x - 2, y, z - 1, DarkSandstone, 0, 2);
                        world.setBlock(x - 2, y, z, DarkSandstone, 0, 2);
                        world.setBlock(x - 2, y, z + 1, DarkSandstone, 0, 2);
                    }
                }
            }

            if (y1 > 0 && y1 < 6) {
                if (y1 == 1 || y1 == 5) {
                    world.setBlock(x + 4, y + y1, z + 4, DarkSandstone, 4, 2);
                    world.setBlock(x - 4, y + y1, z + 4, DarkSandstone, 4, 2);
                    world.setBlock(x + 4, y + y1, z - 4, DarkSandstone, 4, 2);
                    world.setBlock(x - 4, y + y1, z - 4, DarkSandstone, 4, 2);
                } else {
                    world.setBlock(x + 5, y + y1, z + 4, DarkSandstone, 4, 2);
                    world.setBlock(x - 5, y + y1, z + 4, DarkSandstone, 4, 2);
                    world.setBlock(x + 5, y + y1, z - 4, DarkSandstone, 4, 2);
                    world.setBlock(x - 5, y + y1, z - 4, DarkSandstone, 4, 2);

                    world.setBlock(x + 4, y + y1, z + 5, DarkSandstone, 4, 2);
                    world.setBlock(x - 4, y + y1, z + 5, DarkSandstone, 4, 2);
                    world.setBlock(x + 4, y + y1, z - 5, DarkSandstone, 4, 2);
                    world.setBlock(x - 4, y + y1, z - 5, DarkSandstone, 4, 2);
                }
            }
        }

        byte r = (byte) rand.nextInt(6);

        switch (r) {
            default:
            case 0:
                world.setBlock(x, y + 1, z, Blocks.trapped_chest, 0, 2);
                this.applyChestContents(world, rand, x, y + 1, z, TragicItems.NetherStructureHook);

                world.setBlock(x, y + 2, z, Blocks.mob_spawner, 0, 2);
                this.setSpawnerMob(world, x, y + 2, z, "Blaze");
                break;
            case 1:
                world.setBlock(x, y + 1, z, Blocks.trapped_chest, 0, 2);
                this.applyChestContents(world, rand, x, y + 1, z, TragicItems.NetherStructureHook);

                for (byte x1 = -3; x1 < 4; x1++) {
                    for (byte z1 = -3; z1 < 4; z1++) {
                        world.setBlock(x + x1, y + 6, z + z1, DarkSand, 0, 2);
                        world.setBlock(x + x1, y + 5, z + z1, DarkSand, 0, 2);
                        world.setBlock(x + x1, y + 4, z + z1, Blocks.red_flower, 0, 2);
                        world.scheduleBlockUpdate(x + x1, y + 4, z + z1, Blocks.red_flower, 2);
                    }
                }

                for (byte x1 = -2; x1 < 3; x1++) {
                    for (byte z1 = -2; z1 < 3; z1++) {
                        world.setBlock(x + x1, y + 4, z + z1, DarkSand, 0, 2);
                        world.setBlock(x + x1, y + 3, z + z1, DarkSand, 0, 2);
                        world.setBlock(x + x1, y + 2, z + z1, Blocks.red_flower, 0, 2);
                        world.scheduleBlockUpdate(x + x1, y + 2, z + z1, Blocks.red_flower, 2);
                    }
                }

                world.setBlock(x, y + 2, z, DarkSand, 0, 2);
                break;
            case 2:
                world.setBlock(x, y + 1, z, Blocks.mob_spawner, 0, 2);
                this.setSpawnerMob(world, x, y + 1, z, "Blaze");

                for (byte x1 = -3; x1 < 4; x1++) {
                    for (byte z1 = -3; z1 < 4; z1++) {
                        world.setBlock(x + x1, y + 6, z + z1, DarkSand, 0, 2);
                        world.setBlock(x + x1, y + 5, z + z1, DarkSand, 0, 2);
                        world.setBlock(x + x1, y + 4, z + z1, Blocks.red_flower, 0, 2);
                        world.scheduleBlockUpdate(x + x1, y + 4, z + z1, Blocks.red_flower, 2);
                    }
                }

                for (byte x1 = -2; x1 < 3; x1++) {
                    for (byte z1 = -2; z1 < 3; z1++) {
                        world.setBlock(x + x1, y + 4, z + z1, DarkSand, 0, 2);
                        world.setBlock(x + x1, y + 3, z + z1, DarkSand, 0, 2);
                        world.setBlock(x + x1, y + 2, z + z1, Blocks.red_flower, 0, 2);
                        world.scheduleBlockUpdate(x + x1, y + 2, z + z1, Blocks.red_flower, 2);
                    }
                }

                world.setBlock(x, y + 2, z, DarkSand, 0, 2);
                break;
            case 3:
                world.setBlock(x, y + 1, z, Blocks.mob_spawner, 0, 2);
                this.setSpawnerMob(world, x, y + 1, z, "Blaze");

                world.setBlock(x, y + 2, z, Blocks.trapped_chest, 0, 2);
                this.applyChestContents(world, rand, x, y + 2, z, TragicItems.NetherStructureHook);

                for (byte x1 = -3; x1 < 4; x1++) {
                    for (byte z1 = -3; z1 < 4; z1++) {
                        world.setBlock(x + x1, y - 1, z + z1, DarkSandstone, 0, 2);
                        world.setBlock(x + x1, y, z + z1, Blocks.flowing_lava, 0, 2);
                    }
                }

                for (byte x1 = -4; x1 < 5; x1++) {
                    for (byte z1 = -2; z1 < 3; z1++) {
                        world.setBlock(x + x1, y - 1, z + z1, DarkSandstone, 0, 2);
                        world.setBlock(x + x1, y, z + z1, Blocks.flowing_lava, 0, 2);

                        world.setBlock(x + z1, y - 1, z + x1, DarkSandstone, 0, 2);
                        world.setBlock(x + z1, y, z + x1, Blocks.flowing_lava, 0, 2);
                    }
                }
                break;
            case 4:
                for (byte x1 = -1; x1 < 2; x1++) {
                    for (byte z1 = -1; z1 < 2; z1++) {
                        world.setBlock(x + x1, y + 1, z + z1, Blocks.mob_spawner, 0, 2);
                        this.setSpawnerMob(world, x + x1, y + 1, z + z1, "Blaze");
                    }
                }

                world.setBlock(x, y + 1, z, Blocks.trapped_chest, 0, 2);
                this.applyChestContents(world, rand, x, y + 1, z, TragicItems.NetherStructureHook);
                break;
            case 5:
                for (byte x1 = -1; x1 < 2; x1++) {
                    for (byte z1 = -1; z1 < 2; z1++) {
                        world.setBlock(x + x1, y + 1, z + z1, Blocks.mob_spawner, 0, 2);
                        this.setSpawnerMob(world, x + x1, y + 1, z + z1, "Blaze");
                    }
                }

                world.setBlock(x, y + 1, z, Blocks.trapped_chest, 0, 2);
                this.applyChestContents(world, rand, x, y + 1, z, TragicItems.NetherStructureHook);

                world.setBlock(x, y, z, Blocks.tnt, 0, 2);
                break;
        }

        this.generateTunnels(world, rand, x, y, z);
    }

    public void generateTunnels(World world, Random rand, final int x, final int y, final int z) // generates tunnels
                                                                                                 // around a room, these
                                                                                                 // are half tunnels
                                                                                                 // that will connect if
                                                                                                 // there is an adjacent
                                                                                                 // room with tunnels
    {
        for (byte y1 = 0; y1 < 7; y1++) {
            for (byte z1 = -5; z1 > -9; z1--) {
                for (byte x1 = -3; x1 < 4; x1++) world.setBlockToAir(x + x1, y + y1, z + z1);

                if (y1 == 0) {
                    for (byte x1 = -1; x1 < 2; x1++) world.setBlock(x + x1, y, z + z1, DarkSandstone, 1, 2);

                    world.setBlock(x - 2, y, z + z1, DarkSandstone, 0, 2);
                    world.setBlock(x + 2, y, z + z1, DarkSandstone, 0, 2);

                    if (z1 == -6 || z1 == -7) {
                        world.setBlock(x - 3, y, z + z1, DarkSandstone, 0, 2);
                        world.setBlock(x + 3, y, z + z1, DarkSandstone, 0, 2);
                    }
                } else if (y1 < 6) {
                    if (z1 == -6 || z1 == -7) {
                        world.setBlock(x + 4, y + y1, z + z1, DarkSandstone, 4, 2);
                        world.setBlock(x - 4, y + y1, z + z1, DarkSandstone, 4, 2);

                        if (y1 == 5) {
                            world.setBlock(x + 3, y + y1, z + z1, DarkSandstone, 2, 2);
                            world.setBlock(x - 3, y + y1, z + z1, DarkSandstone, 2, 2);
                        }
                    }

                    if (z1 == -5 || z1 == -8) {
                        byte m = (byte) (y1 == 5 ? 3 : 2);
                        world.setBlock(x + 3, y + y1, z + z1, DarkSandstone, m, 2);
                        world.setBlock(x - 3, y + y1, z + z1, DarkSandstone, m, 2);
                    }

                    if (z1 == -5 && y1 == 1) {
                        world.setBlock(x + 2, y + y1, z + z1, Candle, 8, 2);
                        world.setBlock(x - 2, y + y1, z + z1, Candle, 8, 2);
                    }
                } else {
                    world.setBlock(x + 2, y + y1, z + z1, DarkSandstone, 4, 2);
                    world.setBlock(x - 2, y + y1, z + z1, DarkSandstone, 4, 2);

                    if (z1 == -5) {
                        for (byte x1 = -1; x1 < 2; x1++) world.setBlock(x + x1, y + y1, z + z1, DarkSandstone, 4, 2);
                    } else {
                        for (byte x1 = -1; x1 < 2; x1++)
                            world.setBlock(x + x1, y + y1 + 1, z + z1, DarkSandstone, 3, 2);
                    }
                }
            }

            for (byte z1 = 5; z1 < 9; z1++) {
                for (byte x1 = -3; x1 < 4; x1++) world.setBlockToAir(x + x1, y + y1, z + z1);

                if (y1 == 0) {
                    for (byte x1 = -1; x1 < 2; x1++) world.setBlock(x + x1, y, z + z1, DarkSandstone, 1, 2);

                    world.setBlock(x - 2, y, z + z1, DarkSandstone, 0, 2);
                    world.setBlock(x + 2, y, z + z1, DarkSandstone, 0, 2);

                    if (z1 == 6 || z1 == 7) {
                        world.setBlock(x - 3, y, z + z1, DarkSandstone, 0, 2);
                        world.setBlock(x + 3, y, z + z1, DarkSandstone, 0, 2);
                    }
                } else if (y1 < 6) {
                    if (z1 == 6 || z1 == 7) {
                        world.setBlock(x + 4, y + y1, z + z1, DarkSandstone, 4, 2);
                        world.setBlock(x - 4, y + y1, z + z1, DarkSandstone, 4, 2);

                        if (y1 == 5) {
                            world.setBlock(x + 3, y + y1, z + z1, DarkSandstone, 2, 2);
                            world.setBlock(x - 3, y + y1, z + z1, DarkSandstone, 2, 2);
                        }
                    }

                    if (z1 == 5 || z1 == 8) {
                        int m = y1 == 5 ? 3 : 2;
                        world.setBlock(x + 3, y + y1, z + z1, DarkSandstone, m, 2);
                        world.setBlock(x - 3, y + y1, z + z1, DarkSandstone, m, 2);
                    }

                    if (z1 == 5 && y1 == 1) {
                        world.setBlock(x + 2, y + y1, z + z1, Candle, 8, 2);
                        world.setBlock(x - 2, y + y1, z + z1, Candle, 8, 2);
                    }
                } else {
                    world.setBlock(x + 2, y + y1, z + z1, DarkSandstone, 4, 2);
                    world.setBlock(x - 2, y + y1, z + z1, DarkSandstone, 4, 2);

                    if (z1 == 5) {
                        for (byte x1 = -1; x1 < 2; x1++) world.setBlock(x + x1, y + y1, z + z1, DarkSandstone, 4, 2);
                    } else {
                        for (byte x1 = -1; x1 < 2; x1++)
                            world.setBlock(x + x1, y + y1 + 1, z + z1, DarkSandstone, 3, 2);
                    }
                }
            }

            for (byte x1 = -5; x1 > -9; x1--) {
                for (byte z1 = -3; z1 < 4; z1++) world.setBlockToAir(x + x1, y + y1, z + z1);

                if (y1 == 0) {
                    for (byte z1 = -1; z1 < 2; z1++) world.setBlock(x + x1, y, z + z1, DarkSandstone, 1, 2);

                    world.setBlock(x + x1, y, z + 2, DarkSandstone, 0, 2);
                    world.setBlock(x + x1, y, z - 2, DarkSandstone, 0, 2);

                    if (x1 == -6 || x1 == -7) {
                        world.setBlock(x + x1, y, z - 3, DarkSandstone, 0, 2);
                        world.setBlock(x + x1, y, z + 3, DarkSandstone, 0, 2);
                    }
                } else if (y1 < 6) {
                    if (x1 == -6 || x1 == -7) {
                        world.setBlock(x + x1, y + y1, z + 4, DarkSandstone, 4, 2);
                        world.setBlock(x + x1, y + y1, z - 4, DarkSandstone, 4, 2);

                        if (y1 == 5) {
                            world.setBlock(x + x1, y + y1, z + 3, DarkSandstone, 2, 2);
                            world.setBlock(x + x1, y + y1, z - 3, DarkSandstone, 2, 2);
                        }
                    }

                    if (x1 == -5 || x1 == -8) {
                        int m = y1 == 5 ? 3 : 2;
                        world.setBlock(x + x1, y + y1, z + 3, DarkSandstone, m, 2);
                        world.setBlock(x + x1, y + y1, z - 3, DarkSandstone, m, 2);
                    }

                    if (x1 == -5 && y1 == 1) {
                        world.setBlock(x + x1, y + y1, z + 2, Candle, 8, 2);
                        world.setBlock(x + x1, y + y1, z - 2, Candle, 8, 2);
                    }
                } else {
                    world.setBlock(x + x1, y + y1, z + 2, DarkSandstone, 4, 2);
                    world.setBlock(x + x1, y + y1, z - 2, DarkSandstone, 4, 2);

                    if (x1 == -5) {
                        for (byte z1 = -1; z1 < 2; z1++) world.setBlock(x + x1, y + y1, z + z1, DarkSandstone, 4, 2);
                    } else {
                        for (byte z1 = -1; z1 < 2; z1++)
                            world.setBlock(x + x1, y + y1 + 1, z + z1, DarkSandstone, 3, 2);
                    }
                }
            }

            for (byte x1 = 5; x1 < 9; x1++) {
                for (byte z1 = -3; z1 < 4; z1++) world.setBlockToAir(x + x1, y + y1, z + z1);

                if (y1 == 0) {
                    for (byte z1 = -1; z1 < 2; z1++) world.setBlock(x + x1, y, z + z1, DarkSandstone, 1, 2);

                    world.setBlock(x + x1, y, z + 2, DarkSandstone, 0, 2);
                    world.setBlock(x + x1, y, z - 2, DarkSandstone, 0, 2);

                    if (x1 == 6 || x1 == 7) {
                        world.setBlock(x + x1, y, z - 3, DarkSandstone, 0, 2);
                        world.setBlock(x + x1, y, z + 3, DarkSandstone, 0, 2);
                    }
                } else if (y1 < 6) {
                    if (x1 == 6 || x1 == 7) {
                        world.setBlock(x + x1, y + y1, z + 4, DarkSandstone, 4, 2);
                        world.setBlock(x + x1, y + y1, z - 4, DarkSandstone, 4, 2);

                        if (y1 == 5) {
                            world.setBlock(x + x1, y + y1, z + 3, DarkSandstone, 2, 2);
                            world.setBlock(x + x1, y + y1, z - 3, DarkSandstone, 2, 2);
                        }
                    }

                    if (x1 == 5 || x1 == 8) {
                        byte m = (byte) (y1 == 5 ? 3 : 2);
                        world.setBlock(x + x1, y + y1, z + 3, DarkSandstone, m, 2);
                        world.setBlock(x + x1, y + y1, z - 3, DarkSandstone, m, 2);
                    }

                    if (x1 == 5 && y1 == 1) {
                        world.setBlock(x + x1, y + y1, z + 2, Candle, 8, 2);
                        world.setBlock(x + x1, y + y1, z - 2, Candle, 8, 2);
                    }
                } else {
                    world.setBlock(x + x1, y + y1, z + 2, DarkSandstone, 4, 2);
                    world.setBlock(x + x1, y + y1, z - 2, DarkSandstone, 4, 2);

                    if (x1 == 5) {
                        for (byte z1 = -1; z1 < 2; z1++) world.setBlock(x + x1, y + y1, z + z1, DarkSandstone, 4, 2);
                    } else {
                        for (byte z1 = -1; z1 < 2; z1++)
                            world.setBlock(x + x1, y + y1 + 1, z + z1, DarkSandstone, 3, 2);
                    }
                }
            }
        }
    }
}
