package tragicneko.tragicmc.blocks.itemblocks;

import net.minecraft.block.Block;

public class ItemBlockDarkStone extends TragicItemBlock {

    private static String[] subNames = new String[] { "smooth", "red", "green", "blue", "yellow", "purple", "navy",
        "beveled", "beveledRed", "beveledGreen", "beveledTeal", "beveledYellow", "beveledPurple", "beveledNavy",
        "spike" };

    public ItemBlockDarkStone(Block block) {
        super(block, subNames);
        this.setUnlocalizedName("tragicmc.darkStone");
    }
}
