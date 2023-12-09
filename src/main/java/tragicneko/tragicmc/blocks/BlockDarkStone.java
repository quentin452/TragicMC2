package tragicneko.tragicmc.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;

public class BlockDarkStone extends Block {

    private String[] oreNames = new String[] { "Smooth", "Red", "Green", "Teal", "Brown", "Violet", "Navy", "Beveled",
        "BeveledRed", "BeveledGreen", "BeveledTeal", "BeveledYellow", "BeveledPurple", "BeveledNavy", "Spike" };

    private IIcon[] iconArray = new IIcon[oreNames.length];
    private IIcon[] spikeTexture = new IIcon[2];

    public BlockDarkStone() {
        super(Material.rock);
        this.setHarvestLevel("pickaxe", 0);
        this.setCreativeTab(TragicMC.Survival);
        this.setResistance(3.0F);
        this.setHardness(1.5F);
        this.setBlockName("tragicmc.darkStone");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (meta == 14) {
            if (side == 0 || side == 1) return spikeTexture[0];
            return this.spikeTexture[1];
        }

        if (meta >= this.iconArray.length) {
            meta = this.iconArray.length - 1;
        }

        return this.iconArray[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        for (int i = 0; i < this.oreNames.length - 1; i++) {
            this.iconArray[i] = par1IconRegister.registerIcon("tragicmc:" + this.oreNames[i] + "DarkStone");
        }
        this.spikeTexture[0] = par1IconRegister.registerIcon("tragicmc:SpikeTopDarkStone");
        this.spikeTexture[1] = par1IconRegister.registerIcon("tragicmc:SpikeSideDarkStone");
    }

    @Override
    public int damageDropped(int par1) {
        return par1;
    }

    @Override
    public void getSubBlocks(Item par1, CreativeTabs par2, List par3) {
        for (int i = 0; i < this.oreNames.length; i++) {
            par3.add(new ItemStack(par1, 1, i));
        }
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int looting) {
        return meta == 0 ? TragicBlocks.DarkCobblestone.getItemDropped(0, rand, looting)
            : super.getItemDropped(meta, rand, looting);
    }
}
