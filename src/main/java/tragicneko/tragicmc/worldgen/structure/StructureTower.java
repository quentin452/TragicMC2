package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.biome.BiomeGenHell;
import net.minecraft.world.biome.BiomeGenMesa;
import net.minecraft.world.biome.BiomeGenSnow;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.biome.BiomeGenCorrodedSteppe;
import tragicneko.tragicmc.worldgen.biome.BiomeGenFrozenTundra;
import tragicneko.tragicmc.worldgen.biome.BiomeGenScorchedWasteland;
import tragicneko.tragicmc.worldgen.schematic.SchematicDesertTower;

public class StructureTower extends Structure {

    public StructureTower(int id, String name) {
        super(new SchematicDesertTower(), id, name);
    }

    @Override
    public int getVariantSize() {
        return 5;
    }

    @Override
    public boolean isSurfaceStructure() {
        return true;
    }

    @Override
    public boolean isValidDimension(int dim) {
        return dim != -1 && dim != TragicConfig.synapseID;
    }

    @Override
    public boolean areCoordsValidForGeneration(World world, int x, int y, int z, Random rand) {
        return super.areCoordsValidForGeneration(world, x, y, z, rand) && this.getRarity(200) && rand.nextInt(4) == 0;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        return generateStructureWithVariant(
            this.getVariantFromBiome(world.getBiomeGenForCoords(x, z)),
            world,
            rand,
            x,
            y,
            z);
    }

    @Override
    public boolean generateStructureWithVariant(int variant, World world, Random rand, int x, int y, int z) {
        if (!super.generateStructureWithVariant(variant, world, rand, x, y, z)) return false;
        return this.schematic.generateStructure(variant, world, rand, x, y, z);
    }

    public int getVariantFromBiome(BiomeGenBase biome) {
        if (biome instanceof BiomeGenMesa) return 1; // clay tower
        else if (biome instanceof BiomeGenDesert) return 0; // sandstone tower
        else if (biome instanceof BiomeGenHell || biome instanceof BiomeGenScorchedWasteland
            || biome instanceof BiomeGenCorrodedSteppe) return 3; // netherbrick tower
        else if (biome == BiomeGenBase.coldTaiga || biome == BiomeGenBase.coldTaigaHills
            || biome == BiomeGenBase.coldBeach
            || biome instanceof BiomeGenSnow
            || biome instanceof BiomeGenFrozenTundra) return 4; // ice tower
        return 2; // stone tower
    }

    @Override
    public int getStructureColor() {
        return 0xC3E799;
    }
}
