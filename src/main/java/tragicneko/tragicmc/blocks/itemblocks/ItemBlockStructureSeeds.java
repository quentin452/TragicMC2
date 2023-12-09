package tragicneko.tragicmc.blocks.itemblocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class ItemBlockStructureSeeds extends TragicItemBlock {

    public ItemBlockStructureSeeds(Block p_i45326_1_) {
        super(p_i45326_1_, null);
        this.setUnlocalizedName("tragicmc.structureSeed");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
        int damage = par1ItemStack.getItemDamage();
        return Structure.structureList[damage] != null ? Structure.structureList[damage].getStructureColor() : 0;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        int damage = itemstack.getItemDamage();

        if (damage >= Structure.structureList.length) {
            damage = Structure.structureList.length - 1;
        }

        if (Structure.structureList[damage] == null) {
            damage = 0;
        }
        return getUnlocalizedName() + "." + Structure.structureList[damage].getUnlocalizedName();
    }
}
