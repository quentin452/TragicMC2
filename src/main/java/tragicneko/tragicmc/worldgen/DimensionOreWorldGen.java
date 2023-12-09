package tragicneko.tragicmc.worldgen;

import static tragicneko.tragicmc.TragicBlocks.DarkStone;
import static tragicneko.tragicmc.TragicBlocks.TragicOres;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class DimensionOreWorldGen implements IWorldGen {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world) {

        byte i;
        int Xcoord, Ycoord, Zcoord;

        for (i = 0; i < 8; i++) // Mercury
        {
            Xcoord = (chunkX * 16) + random.nextInt(16);
            Ycoord = random.nextInt(32) + 16;
            Zcoord = (chunkZ * 16) + random.nextInt(16);
            new WorldGenMinable(TragicOres, 0, 3, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
        }

        for (i = 0; i < 8; i++) // Mercury, upper gen
        {
            Xcoord = (chunkX * 16) + random.nextInt(16);
            Ycoord = random.nextInt(154) + 102;
            Zcoord = (chunkZ * 16) + random.nextInt(16);
            new WorldGenMinable(TragicOres, 0, 3, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
        }

        for (i = 0; i < 4; i++) // Ruby
        {
            Xcoord = (chunkX * 16) + random.nextInt(16);
            Ycoord = random.nextInt(32) + 5;
            Zcoord = (chunkZ * 16) + random.nextInt(16);
            new WorldGenMinable(TragicOres, 2, 3, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
        }

        for (i = 0; i < 4; i++) // Sapphire
        {
            Xcoord = (chunkX * 16) + random.nextInt(16);
            Ycoord = random.nextInt(140) + 116;
            Zcoord = (chunkZ * 16) + random.nextInt(16);
            new WorldGenMinable(TragicOres, 3, 3, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
        }

        for (i = 0; i < 8; i++) // Tungsten
        {
            Xcoord = (chunkX * 16) + random.nextInt(16);
            Ycoord = random.nextInt(32) + 16;
            Zcoord = (chunkZ * 16) + random.nextInt(16);
            new WorldGenMinable(TragicOres, 1, 3, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
        }

        for (i = 0; i < 8; i++) // Tungsten, upper gen
        {
            Xcoord = (chunkX * 16) + random.nextInt(16);
            Ycoord = random.nextInt(154) + 102;
            Zcoord = (chunkZ * 16) + random.nextInt(16);
            new WorldGenMinable(TragicOres, 1, 3, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
        }

        for (i = 0; i < 6; i++) // Diamond
        {
            Xcoord = (chunkX * 16) + random.nextInt(16);
            Ycoord = random.nextInt(24) + 2;
            Zcoord = (chunkZ * 16) + random.nextInt(16);
            new WorldGenMinable(TragicOres, 5, 3, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
        }

        for (i = 0; i < 6; i++) // Emerald
        {
            Xcoord = (chunkX * 16) + random.nextInt(16);
            Ycoord = random.nextInt(124) + 132;
            Zcoord = (chunkZ * 16) + random.nextInt(16);
            new WorldGenMinable(TragicOres, 6, 3, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
        }

        for (i = 0; i < 6; i++) // Gold
        {
            Xcoord = (chunkX * 16) + random.nextInt(16);
            Ycoord = random.nextInt(48) + 16;
            Zcoord = (chunkZ * 16) + random.nextInt(16);
            new WorldGenMinable(TragicOres, 7, 5, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
        }

        for (i = 0; i < 6; i++) // Gold, upper gen
        {
            Xcoord = (chunkX * 16) + random.nextInt(16);
            Ycoord = random.nextInt(156) + 100;
            Zcoord = (chunkZ * 16) + random.nextInt(16);
            new WorldGenMinable(TragicOres, 7, 5, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
        }

        for (i = 0; i < 10; i++) // Iron
        {
            Xcoord = (chunkX * 16) + random.nextInt(16);
            Ycoord = random.nextInt(96) + 4;
            Zcoord = (chunkZ * 16) + random.nextInt(16);
            new WorldGenMinable(TragicOres, 8, 5, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
        }

        for (i = 0; i < 10; i++) // Iron, upper gen
        {
            Xcoord = (chunkX * 16) + random.nextInt(16);
            Ycoord = random.nextInt(184) + 72;
            Zcoord = (chunkZ * 16) + random.nextInt(16);
            new WorldGenMinable(TragicOres, 8, 5, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
        }

        for (i = 0; i < 6; i++) // Lapis
        {
            Xcoord = (chunkX * 16) + random.nextInt(16);
            Ycoord = random.nextInt(48) + 32;
            Zcoord = (chunkZ * 16) + random.nextInt(16);
            new WorldGenMinable(TragicOres, 4, 5, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
        }

        for (i = 0; i < 6; i++) // Lapis, upper gen
        {
            Xcoord = (chunkX * 16) + random.nextInt(16);
            Ycoord = random.nextInt(156) + 100;
            Zcoord = (chunkZ * 16) + random.nextInt(16);
            new WorldGenMinable(TragicOres, 4, 5, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
        }

        for (i = 0; i < 18; i++) // Coal
        {
            Xcoord = (chunkX * 16) + random.nextInt(16);
            Ycoord = random.nextInt(236) + 10;
            Zcoord = (chunkZ * 16) + random.nextInt(16);
            new WorldGenMinable(TragicOres, 9, 5, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
        }

        for (i = 0; i < 16; i++) // XP
        {
            Xcoord = (chunkX * 16) + random.nextInt(16);
            Ycoord = random.nextInt(236) + 10;
            Zcoord = (chunkZ * 16) + random.nextInt(16);
            new WorldGenMinable(TragicOres, 10, 3, DarkStone).generate(world, random, Xcoord, Ycoord, Zcoord);
        }
    }

}
