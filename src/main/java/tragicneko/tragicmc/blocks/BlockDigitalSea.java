package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;

public class BlockDigitalSea extends Block {

    public BlockDigitalSea() {
        super(Material.circuits);
        this.setBlockName("tragicmc.digitalSea");
        this.setCreativeTab(TragicMC.Creative);
        this.setLightLevel(0.0F);
        this.setLightOpacity(0);
        this.setResistance(0.0F);
        this.setHardness(0.0F);
        this.canBlockGrass = true;
        this.opaque = false;
        this.setTickRandomly(false);
    }

    @Override
    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
        return false;
    }

    @Override
    public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    @Override
    public boolean canReplace(World world, int x, int y, int z, int meta, ItemStack stack) {
        return true;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        // if (this.lit) world.setBlock(x, y, z, TragicBlocks.DigitalSea);
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        // if (!world.isRemote) world.scheduleBlockUpdate(x, y, z, this, 12);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon("tragicmc:DigitalSeaInactive");
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return null;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean canCollideCheck(int p_149678_1_, boolean p_149678_2_) {
        return false;
    }

    @Override
    public void dropBlockAsItemWithChance(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_,
        int p_149690_5_, float p_149690_6_, int p_149690_7_) {}

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        // if (!this.lit && update) world.setBlock(x, y, z, TragicBlocks.DigitalSeaPowered);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        // if (!world.isRemote && update)
        // {
        // if (!this.lit && block == TragicBlocks.DigitalSeaPowered) world.setBlock(x, y, z,
        // TragicBlocks.DigitalSeaPowered);
        // }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_,
        int p_149668_4_) {
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        if (world.getBlock(x, y, z) == this) return false;
        return super.shouldSideBeRendered(world, x, y, z, side);
    }
}
