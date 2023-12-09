package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenDesert;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.biome.BiomeGenDecayingWasteland;
import tragicneko.tragicmc.worldgen.schematic.SchematicClaymationRuin;

public class StructureClaymationRuin extends StructureBoss {

    public StructureClaymationRuin(int id, String name) {
        super(new SchematicClaymationRuin(), id, name);
    }

    @Override
    public boolean isSurfaceStructure() {
        return true;
    }

    @Override
    public int getVariantSize() {
        return 1;
    }

    @Override
    public boolean isValidDimension(int dim) {
        return dim == 0 || dim == TragicConfig.collisionID;
    }

    @Override
    public boolean areCoordsValidForGeneration(World world, int x, int y, int z, Random rand) {
        if (y > 80) return false;
        BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
        if (biome instanceof BiomeGenDesert || biome instanceof BiomeGenDecayingWasteland) {
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
        return 0xFF8100;
    }
}
