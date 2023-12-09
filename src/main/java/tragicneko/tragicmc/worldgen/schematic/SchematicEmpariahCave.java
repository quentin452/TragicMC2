package tragicneko.tragicmc.worldgen.schematic;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.util.WorldHelper;

public class SchematicEmpariahCave extends Schematic {

    public SchematicEmpariahCave() {
        super(8, 12, 12);
    }

    @Override
    public boolean generateStructure(int variant, World world, Random rand, int x, int y, int z) {

        ArrayList<int[]> list = new ArrayList<int[]>();

        for (int i = 0; i < 16 && y - i > 0; i++) {
            list = WorldHelper.getBlocksInCircularRange(
                world,
                i > 12 ? 5.95 + rand.nextDouble()
                    : (i > 7 ? 3.55 + rand.nextDouble() * 1.25 : 3.25 + rand.nextDouble()),
                x + rand.nextInt(2) - rand.nextInt(2),
                y - i,
                z + rand.nextInt(2) - rand.nextInt(2));

            for (int[] coords : list) {
                world.setBlock(coords[0], coords[1], coords[2], Blocks.packed_ice);
            }

            list = WorldHelper.getBlocksInCircularRange(
                world,
                i > 12 ? (1.25 + rand.nextDouble() * 3.75)
                    : (i > 7 ? 1.55 + rand.nextDouble() * 1.25 : 1.25 + rand.nextDouble() * 2),
                x + rand.nextInt(2) - rand.nextInt(2),
                y - i,
                z + rand.nextInt(2) - rand.nextInt(2));

            for (int[] coords : list) {
                world.setBlockToAir(coords[0], coords[1], coords[2]);
            }
        }

        for (int i = 16; i < 32 && y - i > 0; i++) {
            list = WorldHelper.getBlocksInCircularRange(
                world,
                i > 24 ? 11.95 + rand.nextDouble() : 8.55 + rand.nextDouble() * 2,
                x + rand.nextInt(3) - rand.nextInt(3),
                y - i,
                z + rand.nextInt(3) - rand.nextInt(3));

            for (int[] coords : list) {
                world.setBlock(coords[0], coords[1], coords[2], Blocks.packed_ice);
            }

            list = WorldHelper.getBlocksInCircularRange(
                world,
                i > 24 ? 7.95 + rand.nextDouble() * 2 : 3.45 + rand.nextDouble() * 3.5,
                x + rand.nextInt(3) - rand.nextInt(3),
                y - i,
                z + rand.nextInt(3) - rand.nextInt(3));

            for (int[] coords : list) {
                world.setBlockToAir(coords[0], coords[1], coords[2]);
            }
        }

        for (int i = 0; i < 2; i++) {
            list = WorldHelper.getBlocksInCircularRange(world, i == 0 ? 10.25 : 4.55, x, y - 32 + i, z);
            for (int[] coords : list) {
                world.setBlock(coords[0], coords[1], coords[2], rand.nextInt(8) != 0 ? Blocks.packed_ice : Blocks.snow);
            }
        }

        world.setBlock(x, y - 31, z, Blocks.chest, 0, 2);
        this.applyChestContents(world, rand, x, y - 31, z, TragicItems.BossStructureHook);
        world.setBlock(x, y - 30, z, TragicBlocks.SummonBlock, 6, 2);

        if (TragicConfig.allowAbomination) {
            world.setBlock(x + 5, y - 31, z, Blocks.mob_spawner, 0, 2);
            this.setSpawnerMob(world, x + 5, y - 31, z, "TragicMC.Abomination");

            world.setBlock(x - 5, y - 31, z, Blocks.mob_spawner, 0, 2);
            this.setSpawnerMob(world, x - 5, y - 31, z, "TragicMC.Abomination");

            world.setBlock(x, y - 31, z + 5, Blocks.mob_spawner, 0, 2);
            this.setSpawnerMob(world, x, y - 31, z + 5, "TragicMC.Abomination");

            world.setBlock(x, y - 31, z - 5, Blocks.mob_spawner, 0, 2);
            this.setSpawnerMob(world, x, y - 31, z - 5, "TragicMC.Abomination");
        }

        return true;
    }

}
