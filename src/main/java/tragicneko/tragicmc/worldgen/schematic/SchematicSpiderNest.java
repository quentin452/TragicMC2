package tragicneko.tragicmc.worldgen.schematic;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;

public class SchematicSpiderNest extends Schematic {

    public SchematicSpiderNest() {
        super(8, 16, 16);
    }

    @Override
    public boolean generateStructure(int variant, World world, Random rand, int x, int y, int z) {

        final int radius = rand.nextInt(20) + 14; // max of 23, min of 14
        final int top = rand.nextInt(3) + 5; // max of 7, min of 5
        final int bottom = rand.nextInt(3) - 3; // max of -3, min of -5
        final double fulcrum = 0.25466875;

        for (int x1 = -radius; x1 < radius; x1++) {
            double raw = Math.cos(fulcrum * x1 * 0.63) * (2 / fulcrum) + (radius * 3 / 4);
            int width = MathHelper.clamp_int(MathHelper.floor_double(raw), (int) radius / 3, radius);

            for (int z1 = -width; z1 < width; z1++) {
                int range = 4 + rand.nextInt(top * 5 / 8);
                int botR = bottom + rand.nextInt(3);

                for (int y1 = bottom; y1 < top; y1++) {
                    if (y1 <= botR || y1 >= range) {
                        if ((y1 == botR || y1 == range) && rand.nextInt(20) == 0) {
                            if (rand.nextInt(6) == 0) {
                                world.setBlock(x + x1, y + y1, z + z1, Blocks.mob_spawner, 0, 2);
                                this.setSpawnerMob(
                                    world,
                                    x + x1,
                                    y + y1,
                                    z + z1,
                                    TragicConfig.allowStin ? "TragicMC.Stin" : "Spider");
                            } else if (rand.nextInt(16) == 0) {
                                world.setBlock(x + x1, y + y1, z + z1, Blocks.chest, 0, 2);
                                this.applyChestContents(
                                    world,
                                    rand,
                                    x + x1,
                                    y + y1,
                                    z + z1,
                                    TragicItems.NetherStructureHook);
                            } else {
                                world.setBlock(x + x1, y + y1, z + z1, TragicBlocks.Corsin, 8, 2);
                            }
                        } else {
                            world.setBlock(
                                x + x1,
                                y + y1,
                                z + z1,
                                TragicBlocks.Corsin,
                                rand.nextInt(4) == 0 ? (rand.nextInt(10) == 0 ? 4 : 1) : 0,
                                2);
                        }
                    } else {
                        if (rand.nextInt(12) == 0) {
                            world.setBlock(x + x1, y + y1, z + z1, Blocks.web, 0, 2);
                        } else {
                            world.setBlockToAir(x + x1, y + y1, z + z1);
                        }
                    }
                }
            }
        }

        world.setBlockToAir(x, y, z);
        return true;
    }

}
