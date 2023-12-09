package tragicneko.tragicmc.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;

public class BlockDarkSandstone extends Block {

    private String[] stoneNames = new String[] { "Rough", "Smooth", "Bricked", "Chiseled", "Gridded", "Carved" };

    private IIcon[] iconArray = new IIcon[stoneNames.length];

    public BlockDarkSandstone() {
        super(Material.rock);
        this.setHarvestLevel("pickaxe", 0);
        this.setCreativeTab(TragicMC.Survival);
        this.setResistance(3.0F);
        this.setHardness(1.5F);
        this.setBlockName("tragicmc.darkSandstone");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (meta >= this.iconArray.length) meta = this.iconArray.length - 1;
        return this.iconArray[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        for (int i = 0; i < this.stoneNames.length; i++) {
            this.iconArray[i] = par1IconRegister.registerIcon("tragicmc:" + this.stoneNames[i] + "DarkSandstone");
        }
    }

    @Override
    public int damageDropped(int par1) {
        return par1;
    }

    @Override
    public void getSubBlocks(Item par1, CreativeTabs par2, List par3) {
        for (int i = 0; i < this.stoneNames.length; i++) {
            par3.add(new ItemStack(par1, 1, i));
        }
    }

}
