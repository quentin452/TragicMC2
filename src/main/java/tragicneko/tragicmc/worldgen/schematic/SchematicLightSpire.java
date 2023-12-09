package tragicneko.tragicmc.worldgen.schematic;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.util.WorldHelper;

public class SchematicLightSpire extends Schematic {

    public SchematicLightSpire() {
        super(24, 6, 6);
    }

    @Override
    public boolean generateStructure(int variant, World world, Random rand, int x, int y, int z) {

        ArrayList<int[]> list;
        final int height = rand.nextInt(30) + 36;

        for (int y1 = 0; y1 < height && y + y1 < 256; y1++) {
            list = WorldHelper.getBlocksInCircularRange(
                world,
                4.25,
                x + rand.nextInt(3) - rand.nextInt(3),
                y + y1,
                z + rand.nextInt(3) - rand.nextInt(3));
            for (int[] coords : list) {
                world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.StringLight, 0, 2);
            }

            list = WorldHelper.getBlocksInCircularRange(
                world,
                3.25,
                x + rand.nextInt(3) - rand.nextInt(3),
                y + y1,
                z + rand.nextInt(3) - rand.nextInt(3));
            for (int[] coords : list) {
                world.setBlockToAir(coords[0], coords[1], coords[2]);
            }

            if (rand.nextInt(32) == 0) {
                int xr = x + rand.nextInt(3) - rand.nextInt(3);
                int zr = z + rand.nextInt(3) - rand.nextInt(3);
                world.setBlock(xr, y + y1, zr, Blocks.chest, 0, 2);
                this.applyChestContents(world, rand, xr, y + y1, zr, TragicItems.NetherStructureHook);
            } else if (rand.nextInt(16) == 0) {
                int xr = x + rand.nextInt(3) - rand.nextInt(3);
                int zr = z + rand.nextInt(3) - rand.nextInt(3);
                world.setBlock(xr, y + y1, zr, Blocks.mob_spawner, 0, 2);
                this.setSpawnerMob(world, xr, y + y1, zr, TragicConfig.allowArchangel ? "TragicMC.Archangel" : "Ghast");
            }
        }

        return true;
    }

}
