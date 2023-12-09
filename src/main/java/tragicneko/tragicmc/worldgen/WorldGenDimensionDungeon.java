package tragicneko.tragicmc.worldgen;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.DungeonHooks;

import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicItems;

public class WorldGenDimensionDungeon extends WorldGenerator {

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        byte b0 = 3;
        int l = rand.nextInt(2) + 2;
        int i1 = rand.nextInt(2) + 2;
        int j1 = 0;
        int k1;
        int l1;
        int i2;

        for (k1 = x - l - 1; k1 <= x + l + 1; ++k1) {
            for (l1 = y - 1; l1 <= y + b0 + 1; ++l1) {
                for (i2 = z - i1 - 1; i2 <= z + i1 + 1; ++i2) {
                    Material material = world.getBlock(k1, l1, i2)
                        .getMaterial();
                    if (l1 == y - 1 && !material.isSolid() || l1 == y + b0 + 1 && !material.isSolid()) return false;

                    if ((k1 == x - l - 1 || k1 == x + l + 1 || i2 == z - i1 - 1 || i2 == z + i1 + 1) && l1 == y
                        && world.isAirBlock(k1, l1, i2)
                        && world.isAirBlock(k1, l1 + 1, i2)) {
                        ++j1;
                    }
                }
            }
        }

        if (j1 >= 1 && j1 <= 5) {
            for (k1 = x - l - 1; k1 <= x + l + 1; ++k1) {
                for (l1 = y + b0; l1 >= y - 1; --l1) {
                    for (i2 = z - i1 - 1; i2 <= z + i1 + 1; ++i2) {
                        if (k1 != x - l - 1 && l1 != y - 1
                            && i2 != z - i1 - 1
                            && k1 != x + l + 1
                            && l1 != y + b0 + 1
                            && i2 != z + i1 + 1) {
                            world.setBlockToAir(k1, l1, i2);
                        } else if (l1 >= 0 && !world.getBlock(k1, l1 - 1, i2)
                            .getMaterial()
                            .isSolid()) {
                                world.setBlockToAir(k1, l1, i2);
                            } else if (world.getBlock(k1, l1, i2)
                                .getMaterial()
                                .isSolid()) {
                                    if (l1 == y - 1) {
                                        world.setBlock(k1, l1, i2, TragicBlocks.DarkCobblestone, 3, 2);
                                    } else {
                                        world.setBlock(k1, l1, i2, TragicBlocks.DarkCobblestone, 0, 2);
                                    }
                                }
                    }
                }
            }

            k1 = 0;

            while (k1 < 2) {
                l1 = 0;

                while (true) {
                    if (l1 < 3) {
                        label101: {
                            i2 = x + rand.nextInt(l * 2 + 1) - l;
                            int j2 = z + rand.nextInt(i1 * 2 + 1) - i1;

                            if (world.isAirBlock(i2, y, j2)) {
                                int k2 = 0;

                                if (world.getBlock(i2 - 1, y, j2)
                                    .getMaterial()
                                    .isSolid()) {
                                    ++k2;
                                }

                                if (world.getBlock(i2 + 1, y, j2)
                                    .getMaterial()
                                    .isSolid()) {
                                    ++k2;
                                }

                                if (world.getBlock(i2, y, j2 - 1)
                                    .getMaterial()
                                    .isSolid()) {
                                    ++k2;
                                }

                                if (world.getBlock(i2, y, j2 + 1)
                                    .getMaterial()
                                    .isSolid()) {
                                    ++k2;
                                }

                                if (k2 == 1) {
                                    world.setBlock(i2, y, j2, Blocks.chest, 0, 2);
                                    TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(i2, y, j2);

                                    if (tileentitychest != null) {
                                        WeightedRandomChestContent.generateChestContents(
                                            rand,
                                            TragicItems.NetherStructureHook.getItems(rand),
                                            tileentitychest,
                                            TragicItems.NetherStructureHook.getCount(rand));
                                    }

                                    break label101;
                                }
                            }

                            ++l1;
                            continue;
                        }
                    }

                    ++k1;
                    break;
                }
            }

            world.setBlock(x, y, z, Blocks.mob_spawner, 0, 2);
            TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner) world.getTileEntity(x, y, z);

            if (tileentitymobspawner != null) {
                tileentitymobspawner.func_145881_a()
                    .setEntityName(this.pickMobSpawner(rand));
            } else {
                System.err.println("Failed to fetch mob spawner entity at (" + x + ", " + y + ", " + z + ")");
            }

            return true;
        } else {
            return false;
        }
    }

    /**
     * Randomly decides which spawner to use in a dungeon
     */
    private String pickMobSpawner(Random p_76543_1_) {
        return DungeonHooks.getRandomDungeonMob(p_76543_1_);
    }
}
