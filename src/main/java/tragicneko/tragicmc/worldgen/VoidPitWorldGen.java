package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import net.minecraft.world.chunk.Chunk;
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
        if (!world.getChunkProvider().chunkExists(chunkX, chunkZ)){
            return;
        }

        int Xcoord = (chunkX * 16) + random.nextInt(16);
        int Ycoord = random.nextInt(35) + 60;
        int Zcoord = (chunkZ * 16) + random.nextInt(16);

        double size;
        ArrayList<int[]> list;
        ArrayList<int[]> cands = new ArrayList<>();

        size = this.variation * random.nextDouble() + this.radius;

        // TODO: Consider breaking this loop into separate methods for better readability and maintainability

        for (int pow = 0; pow + Ycoord >= 0 && pow + Ycoord <= 256; --pow) {
            if (size >= 5.5D) {
                list = WorldHelper.getBlocksInCircularRange(world, size * 0.31773D, Xcoord, Ycoord + pow, Zcoord);
                // TODO: Ensure the middle of the pit is clear

                for (int[] coords : list) {
                    if (random.nextBoolean() && !cands.contains(coords)) cands.add(coords);
                }
            }

            list = WorldHelper.getBlocksInCircularRange(world, size * 0.64773D, Xcoord, Ycoord + pow, Zcoord);
            // TODO: Give the pit a more gradual feel

            for (int[] coords : list) {
                if (random.nextBoolean() && !cands.contains(coords)) cands.add(coords);
            }

            list = WorldHelper.getBlocksInCircularRange(world, size, Xcoord, Ycoord + pow, Zcoord);
            // TODO: Handle the outer part that has the most scattered blocks

            for (int[] coords : list) {
                if (random.nextBoolean() && !cands.contains(coords)) cands.add(coords);
            }

            if (size >= 3.0D && random.nextInt(4) == 0) size *= 0.977425D;
            // TODO: Reduce size of the void pit randomly to minimize lag
        }

        for (int[] coords2 : cands) {
            if (world.blockExists(coords2[0], coords2[1], coords2[2])) {
                world.setBlock(coords2[0], coords2[1], coords2[2], Blocks.air, 0, 2);
            }
        }
    }

}
