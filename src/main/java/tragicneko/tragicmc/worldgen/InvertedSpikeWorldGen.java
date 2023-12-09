package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.util.WorldHelper;

public class InvertedSpikeWorldGen implements IWorldGen {

    public final byte iterations;
    public final double radius;
    public final double variation;
    public final double regression;
    public final double cutoff;

    public InvertedSpikeWorldGen(byte relays, double radius, double var, double regress, double cutoff) {
        this.iterations = relays;
        this.radius = radius;
        this.variation = var;
        this.regression = regress;
        this.cutoff = cutoff;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world) {

        if (!TragicConfig.allowInvertedSpikeGen) return;

        int Xcoord = (chunkX * 16) + random.nextInt(16) - random.nextInt(16);
        int Zcoord = (chunkZ * 16) + random.nextInt(16) - random.nextInt(16);
        int Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);

        ArrayList<int[]> list;
        Material material;
        double size;
        byte spikeType;
        ArrayList<int[]> cands = new ArrayList<int[]>();

        for (byte buzza = 0; buzza < this.iterations; buzza++) {
            size = random.nextDouble() * variation + radius;
            Xcoord += random.nextInt(8) - random.nextInt(8);
            Zcoord += random.nextInt(8) - random.nextInt(8);
            Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord) + 1;

            spikeType = (byte) random.nextInt(6);

            boolean flag = false;
            boolean flag2 = false;

            for (byte y1 = 0; y1 > Byte.MIN_VALUE; y1--) {
                if (Ycoord + y1 < 25 || size < cutoff) break;
                size *= regression; // reduce the radius of the spike randomly

                if (random.nextBoolean()) {
                    if (random.nextInt(3) == 0 && size >= 0.4888233D) // randomly apply offset to the spike, this
                                                                      // sometimes gives it a cool spiral effect
                    {
                        Xcoord += random.nextInt(2) - random.nextInt(2);
                        Zcoord += random.nextInt(2) - random.nextInt(2);
                    }

                    if (spikeType == 1 && !flag && y1 <= 35 && y1 <= 70 && random.nextBoolean() && size <= 0.774446314D) // Type
                                                                                                                         // 1
                                                                                                                         // has
                                                                                                                         // chance
                                                                                                                         // to
                                                                                                                         // thicken
                                                                                                                         // at
                                                                                                                         // a
                                                                                                                         // random
                                                                                                                         // spot
                                                                                                                         // once
                    {
                        size *= 2.86333567D;
                        flag = true;
                    }
                }

                if (random.nextBoolean()) {
                    if (spikeType == 2 && size >= 0.5625292D) // Type 2 has greater chance of offset, making it look
                                                              // more coral-like
                    {
                        Xcoord += random.nextInt(2) - random.nextInt(2);
                        Zcoord += random.nextInt(2) - random.nextInt(2);
                    } else if (spikeType == 3 && !flag2
                        && random.nextBoolean()
                        && y1 <= 35
                        && size <= 1.41115648D
                        && size >= 0.76663601D) // Type 3 has a chance to create a smaller spike near the top
                    {
                        generateChildSpike(
                            world,
                            random,
                            size * 1.32977745D,
                            Xcoord + random.nextInt(5) - random.nextInt(5),
                            Ycoord + y1,
                            Zcoord + random.nextInt(5) - random.nextInt(5));
                        flag2 = true;
                    } else if (spikeType == 4 && random.nextBoolean() && y1 <= 35 && size >= 0.76663601D) // Type 4
                                                                                                          // creates a
                                                                                                          // lot of
                                                                                                          // smaller
                                                                                                          // spikes
                                                                                                          // going up
                                                                                                          // the spike
                    {
                        generateChildSpike(
                            world,
                            random,
                            size * 1.12977745D,
                            Xcoord + random.nextInt(5) - random.nextInt(5),
                            Ycoord + y1,
                            Zcoord + random.nextInt(5) - random.nextInt(5));
                    } else if (spikeType == 5 && random.nextBoolean()) // Type 5 creates huge spikes at the base, and
                                                                       // smaller ones near the top, making for a huge
                                                                       // web of spikes (usually)
                    {
                        if (y1 >= 24) {
                            generateChildSpike(
                                world,
                                random,
                                size * 1.12977745D,
                                Xcoord + random.nextInt(6) - random.nextInt(6),
                                Ycoord + y1,
                                Zcoord + random.nextInt(6) - random.nextInt(6));
                        } else if (size >= 0.76663601D) {
                            generateChildSpike(
                                world,
                                random,
                                size * 1.13977745D,
                                Xcoord + random.nextInt(5) - random.nextInt(5),
                                Ycoord + y1,
                                Zcoord + random.nextInt(5) - random.nextInt(5));
                        }
                    }
                }

                list = WorldHelper.getBlocksInSphericalRange(world, size, Xcoord, Ycoord + y1, Zcoord);

                for (int[] coords : list) {
                    material = world.getBlock(coords[0], coords[1], coords[2])
                        .getMaterial();
                    if (!material.isLiquid() && material != Material.air && !cands.contains(coords)) cands.add(coords);
                }
            }

            for (int[] coords : cands) world.setBlockToAir(coords[0], coords[1], coords[2]);
        }

    }

    public void generateChildSpike(World world, Random rand, double size, double Xcoord, double Ycoord, double Zcoord) {
        ArrayList<int[]> list;
        ArrayList<int[]> cands = new ArrayList<int[]>();
        Material material;

        for (byte y1 = 0; y1 < 64; y1++) {
            if (rand.nextBoolean()) {
                size *= 0.95977745D;

                if (size >= 0.54288233D) {
                    Xcoord += rand.nextInt(2) - rand.nextInt(2);
                    Zcoord += rand.nextInt(2) - rand.nextInt(2);
                }
            }

            if (size < 0.36443755D || Ycoord - y1 < 0) break;

            list = WorldHelper.getBlocksInSphericalRange(world, size, Xcoord, Ycoord + y1, Zcoord);

            for (int[] coords : list) {
                material = world.getBlock(coords[0], coords[1], coords[2])
                    .getMaterial();
                if (!material.isLiquid() && material != Material.air && !cands.contains(coords)) cands.add(coords);
            }
        }

        for (int[] coords : cands) world.setBlockToAir(coords[0], coords[1], coords[2]);
    }
}
