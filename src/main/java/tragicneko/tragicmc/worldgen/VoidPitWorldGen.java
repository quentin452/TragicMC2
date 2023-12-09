package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.world.World;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.util.WorldHelper;

public class VoidPitWorldGen implements IWorldGen {

    public final double radius;
    public final double variation;

    public VoidPitWorldGen(double radius, double var) {
        this.radius = radius;
        this.variation = var;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world) {

        if (!TragicConfig.allowVoidPitGen) return;

        int Xcoord = (chunkX * 16) + random.nextInt(16);
        int Ycoord = random.nextInt(35) + 60;
        int Zcoord = (chunkZ * 16) + random.nextInt(16);

        double size;
        ArrayList<int[]> list;
        ArrayList<int[]> cands = new ArrayList<int[]>();

        size = this.variation * random.nextDouble() + this.radius;

        for (int pow = 0; pow + Ycoord >= 0 && pow + Ycoord <= 256; --pow) {
            if (size >= 5.5D) {
                list = WorldHelper.getBlocksInCircularRange(world, size * 0.31773D, Xcoord, Ycoord + pow, Zcoord); // makes
                                                                                                                   // sure
                                                                                                                   // the
                                                                                                                   // middle
                                                                                                                   // of
                                                                                                                   // the
                                                                                                                   // pit
                                                                                                                   // is
                                                                                                                   // clear

                for (int[] coords : list) {
                    if (random.nextBoolean() && !cands.contains(coords)) cands.add(coords);
                }
            }

            list = WorldHelper.getBlocksInCircularRange(world, size * 0.64773D, Xcoord, Ycoord + pow, Zcoord); // gives
                                                                                                               // the
                                                                                                               // pit
                                                                                                               // more
                                                                                                               // of a
                                                                                                               // gradual
                                                                                                               // feel

            for (int[] coords : list) {
                if (random.nextBoolean() && !cands.contains(coords)) cands.add(coords);
            }

            list = WorldHelper.getBlocksInCircularRange(world, size, Xcoord, Ycoord + pow, Zcoord); // outer part that
                                                                                                    // has the most
                                                                                                    // scattered blocks

            for (int[] coords : list) {
                if (random.nextBoolean() && !cands.contains(coords)) cands.add(coords);
            }

            if (size >= 3.0D && random.nextInt(4) == 0) size *= 0.977425D; // reduces size of the void pit randomly,
                                                                           // similarly to spikes, but this is to reduce
                                                                           // lag
        }

        for (int[] coords2 : cands) {
            world.setBlockToAir(coords2[0], coords2[1], coords2[2]);
        }
    }

}
