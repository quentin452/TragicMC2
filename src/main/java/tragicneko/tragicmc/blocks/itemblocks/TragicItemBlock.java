package tragicneko.tragicmc.blocks.itemblocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class TragicItemBlock extends ItemBlock {

    protected final String[] subNames;

    public TragicItemBlock(Block p_i45326_1_, String[] subNames, String unlocalizedName) {
        super(p_i45326_1_);
        this.setUnlocalizedName("tragicmc." + unlocalizedName);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.subNames = subNames;
    }

    public TragicItemBlock(Block block, String[] subNames) {
        this(block, subNames, "null");
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        int damage = itemstack.getItemDamage();

        if (damage >= subNames.length) {
            damage = subNames.length - 1;
        }
        return getUnlocalizedName() + "." + subNames[damage];
    }

    @Override
    public int getMetadata(int damageValue) {
        return damageValue;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        return this.field_150939_a.getIcon(0, meta);
    }
}
