package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.world.World;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.biome.BiomeGenSynapse;
import tragicneko.tragicmc.worldgen.schematic.SchematicMemoryCache;

public class StructureMemoryCache extends Structure {

    public StructureMemoryCache(int id, String s) {
        super(new SchematicMemoryCache(), id, s);
    }

    @Override
    public boolean isValidDimension(int dim) {
        return dim == TragicConfig.synapseID;
    }

    @Override
    public boolean areCoordsValidForGeneration(World world, int x, int y, int z, Random rand) {
        return world.getBiomeGenForCoords(x, z) instanceof BiomeGenSynapse && y > 24 && y < 112 && this.getRarity(200);
    }

    @Override
    public boolean generateStructureWithVariant(int variant, World world, Random rand, int x, int y, int z) {
        if (!super.generateStructureWithVariant(variant, world, rand, x, y, z)) return false;
        return this.schematic.generateStructure(variant, world, rand, x, y, z);
    }

    @Override
    public int getStructureColor() {
        return 0x484848;
    }
}
