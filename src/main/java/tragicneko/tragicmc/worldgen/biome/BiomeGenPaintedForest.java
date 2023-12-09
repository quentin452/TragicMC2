package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.mob.EntityJabba;
import tragicneko.tragicmc.entity.mob.EntityTox;
import tragicneko.tragicmc.worldgen.WorldGenLargePaintedTree;
import tragicneko.tragicmc.worldgen.WorldGenPaintedShrubs;
import tragicneko.tragicmc.worldgen.WorldGenPaintedTree;

public class BiomeGenPaintedForest extends TragicBiome {

    public static final float[][] heights = new float[][] { { 0.15F, 0.15F }, { 0.05F, 0.01F }, { 0.35F, 0.18F },
        { 0.01F, -0.12F } };

    public BiomeGenPaintedForest(int par1, byte par2) {
        super(par1, par2);
        if (TragicConfig.allowPox) this.spawnableMonsterList.add(
            new BiomeGenBase.SpawnListEntry(
                EntityTox.class,
                TragicConfig.poxSC,
                TragicConfig.poxGS[0],
                TragicConfig.poxGS[1]));
        if (TragicConfig.allowJanna) this.spawnableMonsterList.add(
            new BiomeGenBase.SpawnListEntry(
                EntityJabba.class,
                TragicConfig.jannaSC,
                TragicConfig.jannaGS[0],
                TragicConfig.jannaGS[1]));
        this.fillerBlock = TragicBlocks.DeadDirt;
        this.topBlock = TragicBlocks.BrushedGrass;
        this.temperature = 1.2F;
        this.rainfall = 1.5F;
        this.heightVariation = heights[variant][0];
        this.rootHeight = heights[variant][1];
        this.theBiomeDecorator.treesPerChunk = variant == 1 ? 2 : (variant == 3 ? -999 : 12);
        this.theBiomeDecorator.mushroomsPerChunk = 4;
        this.theBiomeDecorator.grassPerChunk = variant == 1 || variant == 3 ? 4 : 2;
    }

    @Override
    public int getFlowersFromBiomeType() {
        return variant == 3 ? 24 : 6;
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random p_76730_1_) {
        return new WorldGenTallGrass(TragicBlocks.PaintedTallGrass, 0);
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random rand) {
        if (variant == 1 || variant == 3) {
            if (rand.nextInt(4) != 0) {
                return new WorldGenPaintedShrubs();
            } else {
                return new WorldGenPaintedTree(false, rand.nextBoolean());
            }
        } else {
            if (rand.nextInt(8) != 0) {
                return new WorldGenLargePaintedTree(false, rand.nextInt(3) + 4, 10);
            } else if (rand.nextInt(8) == 0) {
                return new WorldGenPaintedShrubs();
            }
        }

        return new WorldGenPaintedTree(false, rand.nextBoolean());
    }
}
