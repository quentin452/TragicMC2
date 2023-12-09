package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenHell;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.biome.BiomeGenScorchedWasteland;
import tragicneko.tragicmc.worldgen.schematic.SchematicKitsuneDen;

public class StructureKitsuneDen extends StructureBoss {

    public StructureKitsuneDen(int id, String name) {
        super(new SchematicKitsuneDen(), id, name);
    }

    @Override
    public boolean isValidDimension(int dim) {
        return dim == -1 || dim == TragicConfig.collisionID;
    }

    @Override
    public int getVariantSize() {
        return 4;
    }

    @Override
    public boolean areCoordsValidForGeneration(World world, int x, int y, int z, Random rand) {
        if (world.provider.dimensionId != -1 && y >= 62 || world.getTopSolidOrLiquidBlock(x, z) < y) return false;
        BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
        if (biome instanceof BiomeGenHell || biome instanceof BiomeGenScorchedWasteland) {
            return super.areCoordsValidForGeneration(world, x, y, z, rand) && this.getRarity(200);
        }
        return false;
    }

    @Override
    public boolean generateStructureWithVariant(int variant, World world, Random rand, int x, int y, int z) {
        if (!super.generateStructureWithVariant(variant, world, rand, x, y, z)) return false;
        return this.schematic.generateStructure(variant, world, rand, x, y, z);
    }

    @Override
    public int getStructureColor() {
        return 0xAF0000;
    }
}
