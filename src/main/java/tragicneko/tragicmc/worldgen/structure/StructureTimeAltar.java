package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.world.World;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.schematic.SchematicTimeAltar;

public class StructureTimeAltar extends StructureBoss {

    public StructureTimeAltar(int id, String name) {
        super(new SchematicTimeAltar(), id, name);
    }

    @Override
    public int getVariantSize() {
        return 16;
    }

    @Override
    public boolean isSurfaceStructure() {
        return true;
    }

    @Override
    public boolean isValidDimension(int dim) {
        return dim == TragicConfig.collisionID;
    }

    @Override
    public boolean areCoordsValidForGeneration(World world, int x, int y, int z, Random rand) {
        return super.areCoordsValidForGeneration(world, x, y, z, rand) && this.getRarity(200);
    }

    @Override
    public boolean generateStructureWithVariant(int variant, World world, Random rand, int x, int y, int z) {
        if (!super.generateStructureWithVariant(variant, world, rand, x, y, z)) return false;
        return this.schematic.generateStructure(variant, world, rand, x, y, z);
    }

    @Override
    public int getStructureColor() {
        return 0x23FF23;
    }
}
