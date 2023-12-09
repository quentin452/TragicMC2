package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.biome.BiomeGenHallowedHills;
import tragicneko.tragicmc.worldgen.schematic.SchematicLightSpire;

public class StructureLightSpire extends Structure {

    public StructureLightSpire(int id, String s) {
        super(new SchematicLightSpire(), id, s);
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
        BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
        if (biome instanceof BiomeGenHallowedHills) {
            return super.areCoordsValidForGeneration(world, x, y, z, rand) && this.getRarity(200);
        }

        return false;
    }

    @Override
    public boolean generateStructureWithVariant(int variant, World world, Random rand, int x, int y, int z) {
        if (!super.generateStructureWithVariant(variant, world, rand, x, y, z)) return false;
        return this.schematic.generateStructure(world, rand, x, y, z);
    }

    @Override
    public int getStructureColor() {
        return 0xFFFCAF;
    }
}
