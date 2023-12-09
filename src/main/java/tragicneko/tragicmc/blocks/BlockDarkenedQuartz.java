package tragicneko.tragicmc.blocks;

import java.util.List;

import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;

public class BlockDarkenedQuartz extends BlockRotatedPillar {

    private String[] variantNames = new String[] { "Smooth", "Chiseled", "Pillared" };

    private IIcon[] sideIconArray = new IIcon[variantNames.length];
    private IIcon[] topIconArray = new IIcon[variantNames.length];

    public BlockDarkenedQuartz() {
        super(Material.rock);
        this.setCreativeTab(TragicMC.Survival);
        this.setHardness(0.8F);
        this.setBlockName("tragicmc.darkenedQuartz");
        this.setHarvestLevel("pickaxe", 0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        for (int i = 0; i < this.variantNames.length; i++) {
            this.topIconArray[i] = par1IconRegister.registerIcon("tragicmc:" + this.variantNames[i] + "DarkenedQuartz"); // top
            this.sideIconArray[i] = par1IconRegister
                .registerIcon("tragicmc:" + this.variantNames[i] + "DarkenedQuartzSide"); // side
        }
    }

    @Override
    public int damageDropped(int par1) {
        return par1 >= 2 ? 2 : par1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item par1, CreativeTabs par2, List par3) {
        for (int i = 0; i < this.variantNames.length; i++) {
            par3.add(new ItemStack(par1, 1, i));
        }
    }

    @Override
    protected IIcon getTopIcon(int i) {
        return this.topIconArray[i >= 2 ? 2 : i];
    }

    @Override
    protected IIcon getSideIcon(int i) {
        return this.sideIconArray[i >= 2 ? 2 : i];
    }
}
