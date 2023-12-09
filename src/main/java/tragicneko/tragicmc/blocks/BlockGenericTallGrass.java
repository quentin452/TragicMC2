package tragicneko.tragicmc.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;

public class BlockGenericTallGrass extends BlockTallGrass {

    private String texturePrefix;

    public BlockGenericTallGrass(String s) {
        super();
        this.texturePrefix = s;
        this.setCreativeTab(TragicMC.Survival);
        this.setLightLevel(0.5F);
        this.setStepSound(soundTypeGrass);
    }

    @Override
    protected boolean canPlaceBlockOn(Block p_149854_1_) {
        return p_149854_1_ == Blocks.grass || p_149854_1_ == Blocks.dirt
            || p_149854_1_ == Blocks.farmland
            || p_149854_1_ == TragicBlocks.BrushedGrass
            || p_149854_1_ == TragicBlocks.DeadDirt
            || p_149854_1_ == TragicBlocks.AshenGrass
            || p_149854_1_ == TragicBlocks.StarlitGrass;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_) {
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 1));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon("tragicmc:" + this.texturePrefix + "TallGrass");
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
    public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
        return true;
    }
}
