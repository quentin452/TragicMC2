package tragicneko.tragicmc.blocks.itemblocks;

import net.minecraft.block.Block;

public class ItemBlockSummonBlocks extends TragicItemBlock {

    private static final String[] subNames = new String[] { "wither", "enderDragon", "apis", "deathReaper", "kitsune",
        "polaris", "yeti", "timeController", "enyvil", "claymation", "aegar" };

    public ItemBlockSummonBlocks(Block p_i45326_1_) {
        super(p_i45326_1_, subNames);
        this.setUnlocalizedName("tragicmc.summonBlock");
    }
}
