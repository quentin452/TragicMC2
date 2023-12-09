package tragicneko.tragicmc.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;

public class BlockLeafTrim extends BlockTallGrass {

    private String texturePrefix;

    public BlockLeafTrim(String s) {
        super();
        this.texturePrefix = s;
        this.setCreativeTab(TragicMC.Survival);
        this.setLightLevel(0.5F);
        this.setStepSound(soundTypeGrass);
    }

    @Override
    public boolean isLeaves(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    @Override
    protected boolean canPlaceBlockOn(Block block) {
        return block instanceof BlockLeaves || block instanceof BlockLeavesBase || block instanceof BlockLeafTrim;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_) {
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 1));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon("tragicmc:" + this.texturePrefix + "LeafTrim");
    }

    @Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return this.blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor() {
        return -1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int p_149741_1_) {
        return -1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_) {
        return -1;
    }

    @Override
    public void func_149853_b(World world, Random rand, int x, int y, int z) {

    }

    @Override
    public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_,
        boolean p_149851_5_) {
        return false;
    }

    @Override
    public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_,
        int p_149852_5_) {
        return false;
    }
}
