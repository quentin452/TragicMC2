package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.biome.BiomeGenDarkForest;
import tragicneko.tragicmc.worldgen.schematic.SchematicSpiderNest;

public class StructureSpiderNest extends Structure {

    public StructureSpiderNest(int id, String s) {
        super(new SchematicSpiderNest(), id, s);
    }

    @Override
    public boolean isValidDimension(int dim) {
        return dim == TragicConfig.collisionID;
    }

    @Override
    public boolean areCoordsValidForGeneration(World world, int x, int y, int z, Random rand) {
        if (y > 62 || y > world.getTopSolidOrLiquidBlock(x, z)) return false;
        BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
        if (biome instanceof BiomeGenDarkForest) {
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
        return 0x56327B;
    }
}
