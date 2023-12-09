package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.util.WorldHelper;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class CustomSpikesWorldGen implements IWorldGen {

    public final byte relays; // how many spikes should be attempted per chunk
    public final Block block;
    public final byte meta;
    public final double regression; // how quickly the spike reduces in size
    public final double cutoff; // how long the spike generates until it gets too thin to continue
    public final boolean usesSpikeTypes; // if the spike can be one of the different spike types
    public final boolean isStarCrystal; // if the spike should use a randomized meta value per spike
    public final double size; // starting spike size, basically the minimum
    public final double sizeVariation; // the degree that the size can be up to, basically this plus the minimum is the
                                       // maximum, this will be multipled by a random from worldgen

    public CustomSpikesWorldGen(byte relay, Block spikeBlock, byte metaValue, double regress, double spikeCutoff,
        double baseSize, double sizeVariation, boolean flag, boolean flag2) {
        this.relays = relay;
        this.block = spikeBlock;
        this.meta = metaValue;
        this.regression = regress;
        this.cutoff = spikeCutoff;
        this.size = baseSize;
        this.sizeVariation = sizeVariation;
        this.usesSpikeTypes = flag;
        this.isStarCrystal = flag2;
    }

    public CustomSpikesWorldGen(byte relay, Block spikeBlock, byte metaValue, double regress, double spikeCutoff,
        double baseSize, double sizeVariation) {
        this(relay, spikeBlock, metaValue, regress, spikeCutoff, baseSize, sizeVariation, true, false);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world) {
        if (!TragicConfig.allowSpikeGen || random.nextInt(4) == 0) return;

        int Xcoord = (chunkX * 16) + random.nextInt(16);
        int Zcoord = (chunkZ * 16) + random.nextInt(16);
        int Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);

        ArrayList<int[]> list;
        double spikeSize;
        byte spikeType;
        ArrayList<int[]> cands = new ArrayList<int[]>();
        double regress = regression;
        byte m = meta;

        for (byte buzza = 0; buzza < relays; buzza++) {
            spikeSize = (random.nextDouble() * sizeVariation) + size;
            Xcoord += random.nextInt(8) - random.nextInt(8);
            Zcoord += random.nextInt(8) - random.nextInt(8);
            Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);

            if (Structure.validBlocks.contains(world.getBlock(Xcoord, Ycoord - 1, Zcoord))) {
                spikeType = (byte) (this.usesSpikeTypes ? random.nextInt(6) : random.nextInt(2));
                if (this.isStarCrystal) m = (byte) random.nextInt(16);

                boolean flag3 = false;
                boolean flag2 = false;

                for (byte y1 = 0; y1 < Byte.MAX_VALUE; y1++) {
                    if (spikeSize < cutoff || Ycoord + y1 > world.getActualHeight()) break;

                    if (random.nextBoolean()) {
                        spikeSize *= regress; // reduce the radius of the spike randomly

                        if (random.nextInt(3) == 0 && spikeSize >= 0.4888233D) // randomly apply offset to the spike,
                                                                               // this sometimes gives it a cool spiral
                                                                               // effect
                        {
                            Xcoord += random.nextInt(2) - random.nextInt(2);
                            Zcoord += random.nextInt(2) - random.nextInt(2);
                        }

                        if (spikeType == 1 && !flag3
                            && y1 >= 35
                            && y1 <= 70
                            && random.nextBoolean()
                            && size <= 0.774446314D) // Type 1 has chance to thicken at a random spot once
                        {
                            spikeSize *= 2.86333567D;
                            flag3 = true;
                        }
                    }

                    spikeSize *= regress;

                    if (this.usesSpikeTypes && random.nextBoolean()) {
                        if (spikeType == 2 && size >= 0.5625292D) // Type 2 has greater chance of offset, making it look
                                                                  // more coral-like
                        {
                            Xcoord += random.nextInt(2) - random.nextInt(2);
                            Zcoord += random.nextInt(2) - random.nextInt(2);
                        } else if (spikeType == 3 && !flag2
                            && random.nextInt(4) == 0
                            && y1 >= 35
                            && spikeSize <= 1.41115648D
                            && spikeSize >= 0.76663601D) // Type 3 has a chance to create a smaller spike near the top
                        {
                            generateChildSpike(
                                world,
                                random,
                                spikeSize * 1.32977745D,
                                Xcoord + random.nextInt(5) - random.nextInt(5),
                                Ycoord + y1,
                                Zcoord + random.nextInt(5) - random.nextInt(5),
                                block,
                                m);
                            flag2 = true;
                        } else if (spikeType == 4 && random.nextInt(4) == 0 && y1 >= 35 && spikeSize >= 0.76663601D) // Type
                                                                                                                     // 4
                                                                                                                     // creates
                                                                                                                     // a
                                                                                                                     // lot
                                                                                                                     // of
                                                                                                                     // smaller
                                                                                                                     // spikes
                                                                                                                     // going
                                                                                                                     // up
                                                                                                                     // the
                                                                                                                     // spike
                        {
                            generateChildSpike(
                                world,
                                random,
                                spikeSize * 1.12977745D,
                                Xcoord + random.nextInt(5) - random.nextInt(5),
                                Ycoord + y1,
                                Zcoord + random.nextInt(5) - random.nextInt(5),
                                block,
                                m);
                        } else if (spikeType == 5 && random.nextInt(4) == 0) // Type 5 creates huge spikes at the base,
                                                                             // and smaller ones near the top, making
                                                                             // for a huge web of spikes (usually)
                        {
                            if (y1 <= 24) {
                                generateChildSpike(
                                    world,
                                    random,
                                    spikeSize * 1.12977745D,
                                    Xcoord + random.nextInt(6) - random.nextInt(6),
                                    Ycoord + y1,
                                    Zcoord + random.nextInt(6) - random.nextInt(6),
                                    block,
                                    m);
                            } else if (spikeSize >= 0.76663601D) {
                                generateChildSpike(
                                    world,
                                    random,
                                    spikeSize * 1.13977745D,
                                    Xcoord + random.nextInt(5) - random.nextInt(5),
                                    Ycoord + y1,
                                    Zcoord + random.nextInt(5) - random.nextInt(5),
                                    block,
                                    m);
                            }
                        }
                    }

                    list = WorldHelper.getBlocksInSphericalRange(world, spikeSize, Xcoord, Ycoord + y1, Zcoord);

                    for (int[] coords : list) {
                        Block ablock = world.getBlock(coords[0], coords[1], coords[2]);
                        if (Structure.validBlocks.contains(ablock) && ablock != TragicBlocks.DarkStone
                            && !cands.contains(coords)) cands.add(coords);
                    }
                }

                for (int[] coords : cands) {
                    world.setBlock(coords[0], coords[1], coords[2], block, m, 2);
                }
            }
        }

    }

    public void generateChildSpike(World world, Random rand, double size, double Xcoord, double Ycoord, double Zcoord,
        Block spike, int meta) {
        ArrayList<int[]> list;
        ArrayList<int[]> cands = new ArrayList<int[]>();
        Block block;

        for (byte y1 = 0; y1 < Byte.MAX_VALUE; y1++) {
            if (size < 0.36443755D || Ycoord + y1 > 256) break;

            if (rand.nextBoolean()) {
                size *= 0.95977745D;

                if (size >= 0.54288233D) {
                    Xcoord += rand.nextInt(2) - rand.nextInt(2);
                    Zcoord += rand.nextInt(2) - rand.nextInt(2);
                }
            }

            list = WorldHelper.getBlocksInSphericalRange(world, size, Xcoord, Ycoord + y1, Zcoord);

            for (int[] coords : list) {
                block = world.getBlock(coords[0], coords[1], coords[2]);
                if (Structure.validBlocks.contains(block) && !cands.contains(coords)) cands.add(coords);
            }
        }

        for (int[] coords : cands) {
            world.setBlock(coords[0], coords[1], coords[2], spike, meta, 2);
        }
    }
}
