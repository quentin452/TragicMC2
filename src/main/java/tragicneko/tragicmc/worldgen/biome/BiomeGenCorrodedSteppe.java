package tragicneko.tragicmc.worldgen.biome;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.miniboss.EntityJarra;
import tragicneko.tragicmc.entity.mob.EntityFusea;
import tragicneko.tragicmc.entity.mob.EntityTox;
import tragicneko.tragicmc.util.WorldHelper;
import tragicneko.tragicmc.worldgen.PitWorldGen;
import tragicneko.tragicmc.worldgen.RuggedTerrainWorldGen;
import tragicneko.tragicmc.worldgen.SurfacePlantWorldGen;
import tragicneko.tragicmc.worldgen.SurfaceWorldGen;
import tragicneko.tragicmc.worldgen.SurfaceWorldGen2;
import tragicneko.tragicmc.worldgen.VoidPitWorldGen;
import tragicneko.tragicmc.worldgen.WorldGenCustomVine;

public class BiomeGenCorrodedSteppe extends TragicBiome {

    public final SurfaceWorldGen sludgeGen;
    public final RuggedTerrainWorldGen toxicCobbleGen;
    public final SurfaceWorldGen2 gasGen;
    public final VoidPitWorldGen voidPitGen;
    public final WorldGenCustomVine vineGen;
    public final SurfacePlantWorldGen deathglowGen;

    public static final float[][] heights = new float[][] { { 0.05F, 0.03F }, { 0.35F, 0.83F }, { 0.01F, -0.53F },
        { 0.75F, -0.73F }, { 0.01F, -1.25F } };

    public BiomeGenCorrodedSteppe(int par1, byte par2) {
        super(par1, par2);
        this.enableSnow = false;
        this.enableRain = false;
        this.temperature = 1.0F;
        this.rainfall = 0.0F;
        this.heightVariation = heights[variant][0];
        this.rootHeight = heights[variant][1];
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.mushroomsPerChunk = 16;
        this.fillerBlock = TragicBlocks.DarkCobblestone;
        this.topBlock = TragicBlocks.DarkCobblestone;
        if (TragicConfig.allowJarra) this.spawnableMonsterList.add(
            new BiomeGenBase.SpawnListEntry(
                EntityJarra.class,
                TragicConfig.jarraSC,
                TragicConfig.jarraGS[0],
                TragicConfig.jarraGS[1]));
        if (TragicConfig.allowTox) this.spawnableMonsterList.add(
            new BiomeGenBase.SpawnListEntry(
                EntityTox.class,
                TragicConfig.toxSC,
                TragicConfig.toxGS[0],
                TragicConfig.toxGS[1]));
        if (TragicConfig.allowFusea) this.spawnableMonsterList.add(
            new BiomeGenBase.SpawnListEntry(
                EntityFusea.class,
                TragicConfig.fuseaSC,
                TragicConfig.fuseaGS[0],
                TragicConfig.fuseaGS[1]));
        this.sludgeGen = new SurfaceWorldGen(
            3.0D,
            1.5D,
            true,
            (byte) 10,
            TragicBlocks.Quicksand,
            (byte) 3,
            TragicBlocks.DarkCobblestone,
            true,
            true);
        this.toxicCobbleGen = new RuggedTerrainWorldGen(
            TragicBlocks.DarkCobblestone,
            (byte) 2,
            TragicBlocks.DarkCobblestone,
            (byte) 6,
            4.0D,
            3.0D,
            false,
            (byte) 8);
        this.gasGen = new SurfaceWorldGen2(
            (byte) (variant == 0 || variant == 4 ? 14 : (variant >= 3 ? 7 : 2)),
            TragicBlocks.RadiatedGas,
            (byte) 0,
            (byte) 4,
            (byte) 8);
        this.voidPitGen = new VoidPitWorldGen(2.5D, 2.0D);
        this.vineGen = new WorldGenCustomVine(TragicBlocks.WickedVine, 84);
        this.deathglowGen = new SurfacePlantWorldGen(
            (byte) 2,
            TragicBlocks.Deathglow,
            (byte) 14,
            (byte) 4,
            (byte) 4,
            (byte) 2);
    }

    @Override
    public void decorate(World world, Random rand, int x, int z) {
        super.decorate(world, rand, x, z);

        byte mew = (byte) (variant > 2 ? 4 : (variant == 2 ? 16 : 8));
        int k;
        int l;

        for (byte a = 0; a < mew; ++a) {
            k = x + rand.nextInt(16) - 8;
            l = z + rand.nextInt(16) - 8;
            this.vineGen.generate(world, rand, k, rand.nextInt(64) + 42, l);
        }

        int Xcoord = (x * 16) + rand.nextInt(16);
        int Zcoord = (z * 16) + rand.nextInt(16);
        int Ycoord = rand.nextInt(236) + 10;
        ArrayList<int[]> cands;
        cands = WorldHelper.getBlocksInSphericalRange(world, 3.75, Xcoord, Ycoord, Zcoord);
        Block block;
        boolean flag = true;

        for (int[] coords : cands) {
            block = world.getBlock(coords[0], coords[1], coords[2]);
            if (!block.isOpaqueCube() || block.getMaterial() == Material.air
                || block.getMaterial()
                    .isLiquid()) {
                flag = false;
                break;
            }
        }

        if (flag) {
            cands = WorldHelper.getBlocksInSphericalRange(world, 2.75, Xcoord, Ycoord, Zcoord);
            for (int[] coords : cands) world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.ExplosiveGas, 0, 2);
        }

        this.gasGen.generate(rand, x / 16, z / 16, world);
        if (variant > 2) this.sludgeGen.generate(rand, x / 16, z / 16, world);
        this.toxicCobbleGen.generate(rand, x / 16, z / 16, world);

        if (rand.nextInt(8) == 0) {
            boolean flag2 = rand.nextBoolean();
            new PitWorldGen(
                flag2 ? TragicBlocks.Quicksand : TragicBlocks.RadiatedGas,
                (byte) (flag2 ? 3 : 0),
                (byte) 12,
                (byte) 6,
                4.0D,
                3.0D).generate(rand, x / 16, z / 16, world);
        }
        if (TragicConfig.allowVoidPitGen && variant == 4 && rand.nextInt(200) >= 5)
            this.voidPitGen.generate(rand, x / 16, z / 16, world);
        if (rand.nextInt(8) == 0) this.deathglowGen.generate(rand, x / 16, z / 16, world);
    }

}
