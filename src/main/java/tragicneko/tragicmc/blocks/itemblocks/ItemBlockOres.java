package tragicneko.tragicmc.blocks.itemblocks;

import net.minecraft.block.Block;

public class ItemBlockOres extends TragicItemBlock {

    private static String[] subNames = new String[] { "mercury", "tungsten", "ruby", "sapphire", "lapis", "diamond",
        "emerald", "gold", "iron", "coal", "xp" };

    public ItemBlockOres(Block p_i45328_1_) {
        super(p_i45328_1_, subNames);
        this.setUnlocalizedName("tragicmc.ores");
    }
}
