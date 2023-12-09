package tragicneko.tragicmc.worldgen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import cpw.mods.fml.common.IWorldGenerator;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;

public class NetherOreWorldGen implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
        IChunkProvider chunkProvider) {
        // TragicMC.logTime();
        if (world.provider.dimensionId == -1) {
            int Xcoord, Ycoord, Zcoord;
            byte i;

            for (i = 0; i < TragicConfig.rubyOreRate; i++) {
                Xcoord = (chunkX * 16) + random.nextInt(16);
                Ycoord = random.nextInt(30) + 15;
                Zcoord = (chunkZ * 16) + random.nextInt(16);
                new WorldGenMinable(TragicBlocks.RubyOre, 0, TragicConfig.rubyOreVeinSize, Blocks.netherrack)
                    .generate(world, random, Xcoord, Ycoord, Zcoord);
            }

            for (i = 0; i < TragicConfig.sapphireOreRate; i++) {
                Xcoord = (chunkX * 16) + random.nextInt(16);
                Ycoord = random.nextInt(20) + 95;
                Zcoord = (chunkZ * 16) + random.nextInt(16);
                new WorldGenMinable(TragicBlocks.SapphireOre, 0, TragicConfig.sapphireOreVeinSize, Blocks.netherrack)
                    .generate(world, random, Xcoord, Ycoord, Zcoord);
            }

            for (i = 0; i < TragicConfig.drudgeRate && TragicConfig.allowDrudgeGen; i++) {
                Xcoord = (chunkX * 16) + random.nextInt(16);
                Ycoord = random.nextInt(60) + 30;
                Zcoord = (chunkZ * 16) + random.nextInt(16);
                new WorldGenMinable(TragicBlocks.Quicksand, 2, TragicConfig.drudgeVeinSize, Blocks.netherrack)
                    .generate(world, random, Xcoord, Ycoord, Zcoord);
            }
        }
        // TragicMC.logDuration("Custom Nether OreGen");
    }

}
