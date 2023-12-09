package tragicneko.tragicmc.worldgen.schematic;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;

public class SchematicHackerNet extends Schematic {

    public SchematicHackerNet() {
        super(8, 14, 14);
    }

    @Override
    public boolean generateStructure(int variant, World world, Random rand, int x, int y, int z) {

        final int iterations = 400 + rand.nextInt(200);
        world.setBlockToAir(x, y, z);

        for (int i = -14; i < 15; i++) {
            for (int j = -14; j < 15; j++) {
                for (int k = -14; k < 15; k++) {
                    world.setBlockToAir(x + i, y + j, z + k);
                }
            }
        }

        for (int i = 0; i < iterations; i++) {
            world.setBlock(
                x + rand.nextInt(14) - rand.nextInt(14),
                y + rand.nextInt(14) - rand.nextInt(14),
                z + rand.nextInt(14) - rand.nextInt(14),
                TragicBlocks.DigitalSea,
                0,
                2);
        }

        for (int i = 0; i < 20; i++) {
            if (rand.nextBoolean()) {
                int xr = x + rand.nextInt(14) - rand.nextInt(14);
                int yr = y + rand.nextInt(14) - rand.nextInt(14);
                int zr = z + rand.nextInt(14) - rand.nextInt(14);
                world.setBlock(xr, yr, zr, Blocks.mob_spawner, 0, 2);
                this.setSpawnerMob(world, xr, yr, zr, TragicConfig.allowHunter ? "TragicMC.Hunter" : "Ghast");
            }
        }

        for (int i = 0; i < 10; i++) {
            if (rand.nextInt(4) == 0) {
                int xr = x + rand.nextInt(14) - rand.nextInt(14);
                int yr = y + rand.nextInt(14) - rand.nextInt(14);
                int zr = z + rand.nextInt(14) - rand.nextInt(14);
                world.setBlock(xr, yr, zr, Blocks.chest, 0, 2);
                this.applyChestContents(world, rand, xr, yr, zr, TragicItems.NetherStructureHook);
            }
        }
        return true;
    }

}
