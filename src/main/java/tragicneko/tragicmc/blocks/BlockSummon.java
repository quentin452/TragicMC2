package tragicneko.tragicmc.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.blocks.tileentity.TileEntitySummonBlock;

public class BlockSummon extends BlockContainer {

    private String[] bossNames = new String[] { "Wither", "EnderDragon", "Apis", "DeathReaper", "Kitsune", "Polaris",
        "Yeti", "TimeController", "Enyvil", "Claymation", "Aegar" };

    private IIcon[] iconArray = new IIcon[bossNames.length];

    public BlockSummon() {
        super(Material.iron);
        this.setCreativeTab(TragicMC.Creative);
        this.setResistance(100.0F);
        this.setHardness(150.0F);
        this.setStepSound(soundTypeStone);
        this.setBlockName("tragicmc.summonBlock");
    }

    @Override
    protected boolean canSilkHarvest() {
        return false;
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
        for (int i = 0; i < bossNames.length; i++) {
            this.iconArray[i] = par1IconRegister.registerIcon("tragicmc:SummonBlock" + bossNames[i]);
        }
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntitySummonBlock();
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return null;
    }

    @Override
    public int damageDropped(int par1) {
        return par1;
    }

    @Override
    public void getSubBlocks(Item par1, CreativeTabs par2, List par3) {
        for (int i = 0; i < this.bossNames.length; i++) {
            par3.add(new ItemStack(par1, 1, i));
        }
    }

    @Override
    public int getExpDrop(IBlockAccess p_149690_1_, int p_149690_5_, int p_149690_7_) {
        return 48 + TragicMC.rand.nextInt(48);
    }
}
