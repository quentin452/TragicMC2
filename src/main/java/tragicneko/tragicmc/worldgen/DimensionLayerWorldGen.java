package tragicneko.tragicmc.worldgen;

import static tragicneko.tragicmc.TragicBlocks.DarkStone;

import java.util.Random;

import net.minecraft.world.World;

import tragicneko.tragicmc.dimension.TragicChunkProvider;

public class DimensionLayerWorldGen implements IWorldGen {

    private static final byte[] modifiers = new byte[] { -88, -61, -47, -30, -20, 0, 20, 37, 75, 95 };

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world) {

        if (!(world.getChunkProvider() instanceof TragicChunkProvider)) return;

        int paloma = ((TragicChunkProvider) world.getChunkProvider()).getWorldDependency();
        int x = chunkX * 16;
        int z = chunkZ * 16;

        byte j;
        byte k;
        byte meta;
        int y;
        byte p;

        label1: for (byte m = 0; m < modifiers.length; m++) {
            meta = (byte) ((m % 5) + 1);
            p = modifiers[m];

            for (byte r = -1; r < 2; r++) {
                y = paloma + p + r;
                if (y < 10) break;
                if (y > 200) break label1;

                for (byte pew = 0; pew < 48; pew++) {
                    j = (byte) random.nextInt(16);
                    k = (byte) random.nextInt(16);
                    if (world.getBlock(x + j, y, z + k) == DarkStone)
                        world.setBlockMetadataWithNotify(x + j, y, z + k, meta, 2);
                }
            }
        }
    }
}
