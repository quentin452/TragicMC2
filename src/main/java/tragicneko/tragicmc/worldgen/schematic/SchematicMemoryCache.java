package tragicneko.tragicmc.worldgen.schematic;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.util.WorldHelper;

public class SchematicMemoryCache extends Schematic {

    public SchematicMemoryCache() {
        super(12, 12, 12);
    }

    @Override
    public boolean generateStructure(int variant, World world, Random rand, int x, int y, int z) {
        double size = rand.nextDouble() * 4.45D + 5.55D;
        ArrayList<int[]> list = WorldHelper.getBlocksInSphericalRange(world, size, x, y, z);

        for (int[] coords : list) {
            world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.Conduit, 0, 2);
        }

        list = WorldHelper.getBlocksInSphericalRange(world, size - 1.0D, x, y, z);

        for (int[] coords : list) {
            world.setBlockToAir(coords[0], coords[1], coords[2]);
        }

        for (int x1 = -1; x1 < 2; x1++) {
            for (int z1 = -1; z1 < 2; z1++) {
                for (int y1 = -1; y1 < 2; y1++) {
                    world.setBlock(x + x1, y + y1, z + z1, Blocks.mob_spawner, 0, 2);
                    this.setSpawnerMob(world, x + x1, y + y1, z + z1, "TragicMC.NanoSwarm");
                }
            }
        }

        world.setBlock(x, y, z, Blocks.chest, 0, 2);
        this.applyChestContents(world, rand, x, y, z, TragicItems.NetherStructureHook);

        return true;
    }

}
