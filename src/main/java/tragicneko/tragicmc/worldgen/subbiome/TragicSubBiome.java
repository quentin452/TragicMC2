package tragicneko.tragicmc.worldgen.subbiome;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import tragicneko.tragicmc.TragicBlocks;

public class TragicSubBiome {

    public final int subID;
    public final String subName;

    public Block subBlock;

    public static TragicSubBiome[] subBiomes = new TragicSubBiome[16];

    public static TragicSubBiome ferris = new TragicSubBiome(0, "ferris");
    public static TragicSubBiome corsite = new TragicSubBiome(1, "corsite").setSubBlock(TragicBlocks.Corsin);

    public TragicSubBiome(int id, String name) {
        this.subID = id;
        this.subName = name;
        this.subBlock = Blocks.stone;

        if (subBiomes[id] != null) throw new IllegalArgumentException("There is already a sub-biome with that ID!");
        subBiomes[id] = this;
    }

    public TragicSubBiome setSubBlock(Block block) {
        this.subBlock = block;
        return this;
    }

}
