package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.world.World;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.schematic.SchematicCubeMaze;

public class StructureCubeMaze extends Structure {

    public StructureCubeMaze(int id, String s) {
        super(new SchematicCubeMaze(), id, s);
    }

    @Override
    public boolean isValidDimension(int dim) {
        return dim == TragicConfig.collisionID;
    }

    @Override
    public boolean areCoordsValidForGeneration(World world, int x, int y, int z, Random rand) {
        if (y >= 48 || world.getTopSolidOrLiquidBlock(x, z) - 22 < y) return false;
        return super.areCoordsValidForGeneration(world, x, y, z, rand) && this.getRarity(200);
    }

    @Override
    public boolean generateStructureWithVariant(int variant, World world, Random rand, int x, int y, int z) {
        if (!super.generateStructureWithVariant(variant, world, rand, x, y, z)) return false;
        return this.schematic.generateStructure(world, rand, x, y, z);
    }

    @Override
    public int getStructureColor() {
        return 0x000000;
    }
}
