package tragicneko.tragicmc.dimension;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.WeightedRandom;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager.BiomeEntry;

import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;

public class TragicBiomeGenLayer extends GenLayer {

    private ArrayList[] biomeList;

    public TragicBiomeGenLayer(long seed, GenLayer genlayer) {
        this(seed);
        this.parent = genlayer;
    }

    public TragicBiomeGenLayer(long seed) {
        super(seed);
        biomeList = new ArrayList[10];

        ArrayList<BiomeEntry> decayingBiomes = new ArrayList<BiomeEntry>();
        decayingBiomes.add(new BiomeEntry(TragicBiome.DecayingWasteland, TragicConfig.decayingWastelandW));
        decayingBiomes.add(new BiomeEntry(TragicBiome.DecayingHills, TragicConfig.decayingHillsW));
        decayingBiomes.add(new BiomeEntry(TragicBiome.DecayingMountains, TragicConfig.decayingMountainsW));
        decayingBiomes.add(new BiomeEntry(TragicBiome.DecayingValley, TragicConfig.decayingValleyW));

        ArrayList<BiomeEntry> paintedBiomes = new ArrayList<BiomeEntry>();
        paintedBiomes.add(new BiomeEntry(TragicBiome.PaintedForest, TragicConfig.paintedForestW));
        paintedBiomes.add(new BiomeEntry(TragicBiome.PaintedHills, TragicConfig.paintedHillsW));
        paintedBiomes.add(new BiomeEntry(TragicBiome.PaintedPlains, TragicConfig.paintedPlainsW));
        paintedBiomes.add(new BiomeEntry(TragicBiome.PaintedClearing, TragicConfig.paintedClearingW));

        ArrayList<BiomeEntry> ashenBiomes = new ArrayList<BiomeEntry>();
        ashenBiomes.add(new BiomeEntry(TragicBiome.AshenBadlands, TragicConfig.ashenBadlandsW));
        ashenBiomes.add(new BiomeEntry(TragicBiome.AshenHills, TragicConfig.ashenHillsW));
        ashenBiomes.add(new BiomeEntry(TragicBiome.AshenMountains, TragicConfig.ashenMountainsW));

        ArrayList<BiomeEntry> starlitBiomes = new ArrayList<BiomeEntry>();
        starlitBiomes.add(new BiomeEntry(TragicBiome.StarlitPrarie, TragicConfig.starlitPrarieW));
        starlitBiomes.add(new BiomeEntry(TragicBiome.StarlitPlateaus, TragicConfig.starlitPlateausW));
        starlitBiomes.add(new BiomeEntry(TragicBiome.StarlitLowlands, TragicConfig.starlitLowlandsW));
        starlitBiomes.add(new BiomeEntry(TragicBiome.StarlitCliffs, TragicConfig.starlitCliffsW));

        ArrayList<BiomeEntry> taintedBiomes = new ArrayList<BiomeEntry>();
        taintedBiomes.add(new BiomeEntry(TragicBiome.TaintedLowlands, TragicConfig.taintedLowlandsW));
        taintedBiomes.add(new BiomeEntry(TragicBiome.TaintedSpikes, TragicConfig.taintedSpikesW));
        taintedBiomes.add(new BiomeEntry(TragicBiome.TaintedIsles, TragicConfig.taintedIslesW));
        taintedBiomes.add(new BiomeEntry(TragicBiome.TaintedRises, TragicConfig.taintedRisesW));
        taintedBiomes.add(new BiomeEntry(TragicBiome.TaintedScarlands, TragicConfig.taintedScarlandsW));

        ArrayList<BiomeEntry> hallowedBiomes = new ArrayList<BiomeEntry>();
        hallowedBiomes.add(new BiomeEntry(TragicBiome.HallowedHills, TragicConfig.hallowedHillsW));
        hallowedBiomes.add(new BiomeEntry(TragicBiome.HallowedForest, TragicConfig.hallowedForestW));
        hallowedBiomes.add(new BiomeEntry(TragicBiome.HallowedPrarie, TragicConfig.hallowedPrarieW));
        hallowedBiomes.add(new BiomeEntry(TragicBiome.HallowedCliffs, TragicConfig.hallowedCliffsW));

        ArrayList<BiomeEntry> scorchedBiomes = new ArrayList<BiomeEntry>();
        scorchedBiomes.add(new BiomeEntry(TragicBiome.ScorchedWastelands, TragicConfig.scorchedWastelandsW));
        scorchedBiomes.add(new BiomeEntry(TragicBiome.ScorchedValley, TragicConfig.scorchedValleyW));
        scorchedBiomes.add(new BiomeEntry(TragicBiome.ScorchedScarlands, TragicConfig.scorchedScarlandsW));

        ArrayList<BiomeEntry> corrodedBiomes = new ArrayList<BiomeEntry>();
        corrodedBiomes.add(new BiomeEntry(TragicBiome.CorrodedSteppe, TragicConfig.corrodedSteppeW));
        corrodedBiomes.add(new BiomeEntry(TragicBiome.CorrodedHeights, TragicConfig.corrodedHeightsW));
        corrodedBiomes.add(new BiomeEntry(TragicBiome.CorrodedVeld, TragicConfig.corrodedVeldW));
        corrodedBiomes.add(new BiomeEntry(TragicBiome.CorrodedRunoff, TragicConfig.corrodedRunoffW));
        corrodedBiomes.add(new BiomeEntry(TragicBiome.CorrodedFallout, TragicConfig.corrodedFalloutW));

        ArrayList<BiomeEntry> frozenBiomes = new ArrayList<BiomeEntry>();
        frozenBiomes.add(new BiomeEntry(TragicBiome.FrozenTundra, TragicConfig.frozenTundraW));
        frozenBiomes.add(new BiomeEntry(TragicBiome.FrozenHills, TragicConfig.frozenHillsW));
        frozenBiomes.add(new BiomeEntry(TragicBiome.FrozenDepths, TragicConfig.frozenDepthsW));

        ArrayList<BiomeEntry> darkForestBiomes = new ArrayList<BiomeEntry>();
        darkForestBiomes.add(new BiomeEntry(TragicBiome.DarkForest, TragicConfig.darkForestW));
        darkForestBiomes.add(new BiomeEntry(TragicBiome.DarkForestHills, TragicConfig.darkForestHillsW));
        darkForestBiomes.add(new BiomeEntry(TragicBiome.DarkMarsh, TragicConfig.darkMarshW));

        biomeList[0] = decayingBiomes;
        biomeList[1] = paintedBiomes;
        biomeList[2] = ashenBiomes;
        biomeList[3] = starlitBiomes;
        biomeList[4] = taintedBiomes;
        biomeList[5] = hallowedBiomes;
        biomeList[6] = scorchedBiomes;
        biomeList[7] = corrodedBiomes;
        biomeList[8] = frozenBiomes;
        biomeList[9] = darkForestBiomes;
    }

    @Override
    public int[] getInts(int x, int z, int width, int depth) {
        int[] dest = IntCache.getIntCache(width * depth);

        for (int dz = 0; dz < depth; dz++) {
            for (int dx = 0; dx < width; dx++) {
                this.initChunkSeed(dx + x, dz + z);
                try {
                    List<BiomeEntry> biomes = biomeList[this.nextInt(biomeList.length)];
                    dest[dx + dz * width] = this.nextInt(1000) <= TragicConfig.crystalW ? TragicBiome.Crystal.biomeID
                        : ((BiomeEntry) WeightedRandom
                            .getItem(biomes, this.nextInt(WeightedRandom.getTotalWeight(biomes)))).biome.biomeID;
                } catch (Exception e) {
                    TragicMC.logError(
                        "Error adding a biome entry to the gen layer, defaulting biome to Decaying Wasteland",
                        e);
                    dest[dx + dz * width] = TragicBiome.DecayingWasteland.biomeID;
                    continue;
                }
            }
        }

        return dest;
    }
}
