package tragicneko.tragicmc.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import tragicneko.tragicmc.TragicMC;

import java.util.List;
import java.util.Random;

public class BlockFox extends Block {

    private String[] subNames = new String[] { "SmoothNetherrack", "ChiseledNetherrack", "BeveledNetherrack",
        "SculptedNetherrack", "FoxtailRock", "MoltenNetherrack" };

    private IIcon[] iconArray = new IIcon[subNames.length];

    public BlockFox() {
        super(Material.rock);
        this.setCreativeTab(TragicMC.Survival);
        this.setResistance(12.0F);
        this.setHardness(6.0F);
        this.setStepSound(soundTypeStone);
        this.setBlockName("tragicmc.fox");
        this.setHarvestLevel("pickaxe", 0);
    }

    @Override
    public int quantityDroppedWithBonus(int p_149679_1_, Random p_149679_2_) {
        if (p_149679_1_ > 0 && Item.getItemFromBlock(this) != this.getItemDropped(0, p_149679_2_, p_149679_1_)) {
            int j = p_149679_2_.nextInt(p_149679_1_ + 2) - 1;

            if (j < 0) {
                j = 0;
            }

            return this.quantityDropped(p_149679_2_) * (j + 1);
        } else {
            return this.quantityDropped(p_149679_2_);
        }
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int level) {
        return super.getItemDropped(meta, rand, level);
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        if (world.getBlockMetadata(x, y, z) == 4 && world.getBlock(x, y, z) == this) {
            return 8;
        }

        if (world.getBlockMetadata(x, y, z) == 5 && world.getBlock(x, y, z) == this) {
            return 12;
        }

        return super.getLightValue(world, x, y, z);
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
        for (int i = 0; i < subNames.length; i++) {
            this.iconArray[i] = par1IconRegister.registerIcon("tragicmc:" + subNames[i]);
        }
    }

    @Override
    public int damageDropped(int par1) {
        return par1;
    }

    @Override
    public void getSubBlocks(Item par1, CreativeTabs par2, List par3) {
        for (int i = 0; i < this.subNames.length; i++) {
            par3.add(new ItemStack(par1, 1, i));
        }
    }

    @Override
    public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side) {
        return side == ForgeDirection.UP;
    }
}
