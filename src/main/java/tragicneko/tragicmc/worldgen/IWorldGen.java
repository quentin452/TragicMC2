package tragicneko.tragicmc.worldgen;

import java.util.Random;

import net.minecraft.world.World;

public interface IWorldGen {

    public abstract void generate(Random random, int chunkX, int chunkZ, World world);
}
