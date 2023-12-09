package tragicneko.tragicmc.worldgen.biome;

import java.util.List;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.mob.EntityHarvester;
import tragicneko.tragicmc.entity.mob.EntityHunter;
import tragicneko.tragicmc.entity.mob.EntityLockbot;
import tragicneko.tragicmc.entity.mob.EntityNanoSwarm;

public class BiomeGenSynapse extends TragicBiome {

    public BiomeGenSynapse(int par1) {
        super(par1, (byte) 0);
        this.enableSnow = false;
        this.enableRain = false;
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        if (TragicConfig.allowNanoSwarm) this.spawnableMonsterList.add(
            new BiomeGenBase.SpawnListEntry(
                EntityNanoSwarm.class,
                TragicConfig.nanoSwarmSC,
                TragicConfig.nanoSwarmGS[0],
                TragicConfig.nanoSwarmGS[1]));
        if (TragicConfig.allowHunter) this.spawnableMonsterList.add(
            new BiomeGenBase.SpawnListEntry(
                EntityHunter.class,
                TragicConfig.hunterSC,
                TragicConfig.hunterGS[0],
                TragicConfig.hunterGS[1]));
        if (TragicConfig.allowLockbot) this.spawnableMonsterList.add(
            new BiomeGenBase.SpawnListEntry(
                EntityLockbot.class,
                TragicConfig.lockbotSC,
                TragicConfig.lockbotGS[0],
                TragicConfig.lockbotGS[1]));
        if (TragicConfig.allowHarvester) this.spawnableMonsterList.add(
            new BiomeGenBase.SpawnListEntry(
                EntityHarvester.class,
                TragicConfig.harvesterSC,
                TragicConfig.harvesterGS[0],
                TragicConfig.harvesterGS[1]));
        this.fillerBlock = TragicBlocks.CircuitBlock;
        this.topBlock = TragicBlocks.CircuitBlock;
    }

    @Override
    public List getSpawnableList(EnumCreatureType p_76747_1_) {
        return super.getSpawnableList(p_76747_1_);
    }
}
