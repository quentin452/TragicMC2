package tragicneko.tragicmc.worldgen;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SurfaceWorldGen2 implements IWorldGen {

    public final byte iterations;
    public final Block block;
    public final byte meta;
    public final byte width;
    public final byte height;

    public SurfaceWorldGen2(byte iterations, Block block, byte meta, byte width, byte height) {
        this.iterations = iterations;
        this.block = block;
        this.meta = meta;
        this.width = width;
        this.height = height;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world) {
        if (!TragicConfig.allowScatteredSurfaceGen) return;
        if (!world.getChunkProvider().chunkExists(chunkX, chunkZ)) {
            return;
        }

        int baseX = chunkX * 16;
        int baseZ = chunkZ * 16;

        Set<Long> modifiedPositions = new HashSet<>();

        for (byte i = 0; i < iterations; i++) {
            int Xcoord = baseX + random.nextInt(16);
            int Zcoord = baseZ + random.nextInt(16);
            int Ycoord = world.getHeightValue(Xcoord, Zcoord);

            long positionHash = ((long)Xcoord & 0xFFFFFFFFL) | (((long)Ycoord & 0xFFFFFL) << 32) | (((long)Zcoord & 0xFFFFFFFFL) << 20);

            if (modifiedPositions.contains(positionHash)) {
                continue;
            }

            if (Ycoord < 0 || Ycoord > 256) break;

            if (world.isAirBlock(Xcoord, Ycoord, Zcoord)) {
                Block existingBlock = world.getBlock(Xcoord, Ycoord - 1, Zcoord);

                if (Structure.validBlocks.contains(existingBlock) || existingBlock.isLeaves(world, Xcoord, Ycoord - 1, Zcoord)) {
                    world.setBlock(Xcoord, Ycoord, Zcoord, this.block, meta, 2);
                    modifiedPositions.add(positionHash);
                }
            }
        }
    }
}
