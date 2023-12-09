package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.entity.passive.EntityBat;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.miniboss.EntityKragul;
import tragicneko.tragicmc.entity.mob.EntityGragul;
import tragicneko.tragicmc.entity.mob.EntityInkling;
import tragicneko.tragicmc.entity.mob.EntityNorVox;
import tragicneko.tragicmc.worldgen.RuggedTerrainWorldGen;
import tragicneko.tragicmc.worldgen.SurfaceWorldGen;
import tragicneko.tragicmc.worldgen.WorldGenAshenShrubs;
import tragicneko.tragicmc.worldgen.WorldGenAshenTree;

public class BiomeGenAshenHills extends TragicBiome {

    public final SurfaceWorldGen shieldGen;
    public final RuggedTerrainWorldGen ruggedGen;

    public static final float[][] heights = new float[][] { { 0.85F, 0.45F }, { 0.45F, 0.25F }, { 0.05F, 0.05F } };

    public BiomeGenAshenHills(int par1, byte par2) {
        super(par1, par2);
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityBat.class, 50, 3, 5));
        if (TragicConfig.allowInkling) this.spawnableMonsterList.add(
            new BiomeGenBase.SpawnListEntry(
                EntityInkling.class,
                TragicConfig.inklingSC,
                TragicConfig.inklingGS[0],
                TragicConfig.inklingGS[1]));
        if (TragicConfig.allowGragul) this.spawnableMonsterList.add(
            new BiomeGenBase.SpawnListEntry(
                EntityGragul.class,
                TragicConfig.gragulSC,
                TragicConfig.gragulGS[0],
                TragicConfig.gragulGS[1]));
        if (TragicConfig.allowKragul) this.spawnableMonsterList.add(
            new BiomeGenBase.SpawnListEntry(
                EntityKragul.class,
                TragicConfig.kragulSC,
                TragicConfig.kragulGS[0],
                TragicConfig.kragulGS[1]));
        if (TragicConfig.allowNorVox) this.spawnableMonsterList.add(
            new BiomeGenBase.SpawnListEntry(
                EntityNorVox.class,
                TragicConfig.norVoxSC,
                TragicConfig.norVoxGS[0],
                TragicConfig.norVoxGS[1]));
        this.enableRain = false;
        this.enableSnow = false;
        this.temperature = 1.6F;
        this.rainfall = 0F;
        this.heightVariation = heights[variant][0];
        this.rootHeight = heights[variant][1];
        this.fillerBlock = TragicBlocks.DarkStone;
        this.topBlock = TragicBlocks.AshenGrass;
        this.theBiomeDecorator.treesPerChunk = variant == 2 ? 1 : (variant == 1 ? 2 : 4);
        this.theBiomeDecorator.grassPerChunk = variant == 2 ? 3 : 1;
        this.shieldGen = new SurfaceWorldGen(
            3.0D,
            4.0D,
            false,
            (byte) 0,
            TragicBlocks.DeadDirt,
            (byte) 1,
            TragicBlocks.AshenGrass,
            true,
            false);
        this.ruggedGen = new RuggedTerrainWorldGen(
            TragicBlocks.DeadDirt,
            (byte) 1,
            TragicBlocks.AshenGrass,
            (byte) 3,
            3.0D,
            2.0D,
            false,
            (byte) 8);
    }

    @Override
    public int getBushesFromBiomeType() {
        return 12;
    }

    @Override
    public int getFlowersFromBiomeType() {
        return variant == 2 ? 8 : 2;
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random rand) {
        if (rand.nextInt(16) == 0) {
            return new WorldGenAshenShrubs();
        }
        return new WorldGenAshenTree(false);
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {
        return new WorldGenTallGrass(rand.nextInt(16) == 0 ? TragicBlocks.DriedGrass : TragicBlocks.AshenTallGrass, 0);
    }

    @Override
    public void decorate(World world, Random rand, int x, int z) {
        super.decorate(world, rand, x, z);
        if (variant == 2) {
            this.shieldGen.generate(rand, x / 16, z / 16, world);
            this.ruggedGen.generate(rand, x / 16, z / 16, world);
        }
    }
}
