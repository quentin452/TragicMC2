package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenHell;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.biome.BiomeGenDecayingWasteland;
import tragicneko.tragicmc.worldgen.biome.BiomeGenScorchedWasteland;
import tragicneko.tragicmc.worldgen.schematic.SchematicDeathCircle;

public class StructureDeathCircle extends StructureBoss {

    public StructureDeathCircle(int id, String name) {
        super(new SchematicDeathCircle(), id, name);
    }

    @Override
    public boolean isSurfaceStructure() {
        return true;
    }

    @Override
    public boolean isValidDimension(int dim) {
        return dim == -1 || dim == TragicConfig.collisionID;
    }

    @Override
    public boolean areCoordsValidForGeneration(World world, int x, int y, int z, Random rand) {
        BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
        if (biome instanceof BiomeGenHell || biome instanceof BiomeGenDecayingWasteland
            || biome instanceof BiomeGenScorchedWasteland) {
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
        return 0x770300;
    }
}
