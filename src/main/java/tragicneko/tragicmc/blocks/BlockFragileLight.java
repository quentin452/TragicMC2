package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicBlocks;

public class BlockFragileLight extends Block {

    private final boolean isVisible;

    public BlockFragileLight(boolean flag) {
        super(Material.glass);
        this.setHarvestLevel("pickaxe", 0);
        this.setBlockTextureName("tragicmc:FragileLight");
        this.setBlockName("tragicmc.fragileLight");
        this.setHardness(0.5F);
        this.setResistance(0.5F);
        this.setStepSound(Block.soundTypeGlass);
        this.setLightOpacity(0);
        this.setLightLevel(!flag ? 0.2F : 0.6F);
        this.isVisible = flag;
        this.setTickRandomly(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        super.registerBlockIcons(par1IconRegister);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2) {
        return super.getIcon(par1, par2);
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.getItemFromBlock(TragicBlocks.FragileLight);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_,
        int p_149668_4_) {
        return null;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (!isVisible) world.setBlock(x, y, z, isVisible ? TragicBlocks.FragileLightInvis : TragicBlocks.FragileLight);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (!world.isRemote && entity instanceof EntityLivingBase && isVisible) {
            world.setBlock(x, y, z, TragicBlocks.FragileLightInvis);
            world.scheduleBlockUpdate(x, y, z, TragicBlocks.FragileLightInvis, 10);
        }
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        Block block = world.getBlock(x, y, z);
        return block == this ? false : super.shouldSideBeRendered(world, x, y, z, side);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderType() {
        return 1;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() {
        return isVisible ? 0 : 1;
    }
}
