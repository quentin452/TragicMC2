package tragicneko.tragicmc.blocks.itemblocks;

import net.minecraft.block.Block;

public class ItemBlockStarCrystal extends TragicItemBlock {

    private static String[] subNames = new String[] { "black", "red", "green", "brown", "blue", "purple", "cyan",
        "lightGray", "darkGray", "pink", "limeGreen", "yellow", "lightBlue", "magenta", "orange", "white" };

    public ItemBlockStarCrystal(Block p_i45328_1_) {
        super(p_i45328_1_, subNames);
        this.setUnlocalizedName("tragicmc.starCrystal");
    }
}
