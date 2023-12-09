package tragicneko.tragicmc.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
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

        int Xcoord = (chunkX * 16) + random.nextInt(16);
        int Zcoord = (chunkZ * 16) + random.nextInt(16);
        int Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);

        Block block;
        for (byte i = 0; i < iterations; i++) {
            Xcoord += random.nextInt(width) - random.nextInt(width);
            Zcoord += random.nextInt(width) - random.nextInt(width);
            Ycoord += random.nextInt(height) - random.nextInt(height);

            if (Ycoord < 0 || Ycoord > 256) break;
            block = world.getBlock(Xcoord, Ycoord, Zcoord);

            if (Structure.validBlocks.contains(block) || block.canBeReplacedByLeaves(world, Xcoord, Ycoord, Zcoord)
                || block.isAir(world, Xcoord, Ycoord, Zcoord)) {
                if (World.doesBlockHaveSolidTopSurface(world, Xcoord, Ycoord - 1, Zcoord) && !block.getMaterial()
                    .isLiquid()) world.setBlock(Xcoord, Ycoord, Zcoord, this.block, meta, 2);
            }
        }
    }

}
