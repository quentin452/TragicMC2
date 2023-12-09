package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;

public class BlockOverlordBarrier extends Block {

    public BlockOverlordBarrier() {
        super(Material.iron);
        this.setBlockName("tragicmc.overlordBarrier");
        this.setBlockUnbreakable();
        this.setResistance(6000000.0F);
        this.setCreativeTab(TragicMC.Creative);
        this.setStepSound(soundTypeStone);
        this.setBlockTextureName("tragicmc:OverlordBarrier");
        this.setLightLevel(10.0F);
        this.setLightOpacity(0);
    }

    @Override
    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
        return false;
    }

    @Override
    public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
        return false;
    }

    @Override
    public boolean canReplace(World world, int x, int y, int z, int meta, ItemStack stack) {
        return true;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        world.setBlockToAir(x, y, z);
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        if (!world.isRemote) world.scheduleBlockUpdate(x, y, z, this, 45 + world.rand.nextInt(16));
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return null;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderBlockPass() {
        return 1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public void dropBlockAsItemWithChance(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_,
        int p_149690_5_, float p_149690_6_, int p_149690_7_) {}
}
