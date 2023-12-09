package tragicneko.tragicmc.blocks;

import java.util.List;

import net.minecraft.block.BlockObsidian;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;

public class BlockObsidianVariant extends BlockObsidian {

    private String[] variantNames = new String[] { "Crying", "Bleeding", "Dying" };

    private IIcon[] iconArray = new IIcon[variantNames.length];

    public BlockObsidianVariant() {
        super();
        this.setCreativeTab(TragicMC.Survival);
        this.setResistance(2500.0F);
        this.setHardness(50.0F);
        this.setBlockName("tragicmc.tragicObsidian");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (meta >= this.iconArray.length) {
            meta = this.iconArray.length - 1;
        }
        return this.iconArray[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        for (int i = 0; i < this.variantNames.length; i++) {
            this.iconArray[i] = par1IconRegister.registerIcon("tragicmc:" + this.variantNames[i] + "Obsidian");
        }
    }

    @Override
    public int damageDropped(int par1) {
        return par1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item par1, CreativeTabs par2, List par3) {
        for (int i = 0; i < this.variantNames.length; i++) {
            par3.add(new ItemStack(par1, 1, i));
        }
    }

    @Override
    public int getMobilityFlag() {
        return 2;
    }

}
