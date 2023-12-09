package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.world.World;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.schematic.SchematicCorruptedSpire;

public class StructureCorruptedSpire extends Structure {

    public StructureCorruptedSpire(int id, String s) {
        super(new SchematicCorruptedSpire(), id, s);
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
        return false; // should not generate naturally
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        return generateStructureWithVariant(rand.nextInt(this.getVariantSize()), world, rand, x, y, z);
    }

    @Override
    public boolean generateStructureWithVariant(int variant, World world, Random rand, int x, int y, int z) {
        if (!super.generateStructureWithVariant(variant, world, rand, x, y, z)) return false;
        return this.schematic.generateStructure(variant, world, rand, x, y, z);
    }

    @Override
    public int getStructureColor() {
        return 0xDDFFFF;
    }
}
