package tragicneko.tragicmc.worldgen;

import java.util.Random;

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import tragicneko.tragicmc.TragicBlocks;

public class CircuitWorldGen implements IWorldGen {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world) {
        final Chunk chk = world.getChunkFromChunkCoords(chunkX, chunkZ);
        double m;
        byte meta;

        for (int y1 = 0; y1 < world.provider.getActualHeight(); y1++) {
            m = (2.5D + (Math.cos((y1 + 6) / 20.0D)) * 2.5);
            meta = (byte) MathHelper.clamp_int((int) Math.round(m), 0, 4);

            for (byte x1 = 0; x1 < 16; x1++) {
                for (byte z1 = 0; z1 < 16; z1++) {
                    if (chk.getBlock(x1, y1, z1) == TragicBlocks.CircuitBlock) chk.setBlockMetadata(x1, y1, z1, meta);

                    if (y1 > 5 && y1 < 123) {
                        byte yr = (byte) (random.nextInt(6) - random.nextInt(6));
                        if (chk.getBlock(x1, y1 + yr, z1) == TragicBlocks.CircuitBlock)
                            chk.setBlockMetadata(x1, y1 + yr, z1, meta);
                    }
                }
            }
        }
    }

}
