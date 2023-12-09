package tragicneko.tragicmc.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;

public class BlockCircuit extends Block {

    private String[] variantNames = new String[] { "Live", "Damaged", "VeryDamaged", "Dead", "Aged" };
    private IIcon[] iconArray = new IIcon[variantNames.length];

    public BlockCircuit() {
        super(Material.rock);
        this.setCreativeTab(TragicMC.Survival);
        this.setHarvestLevel("pickaxe", 1);
        this.setResistance(27.0F);
        this.setHardness(3.6F);
    }

    @Override
    public int getLightValue(IBlockAccess access, int x, int y, int z) {
        int meta = access.getBlockMetadata(x, y, z);
        switch (meta) {
            default:
                return 0;
            case 0:
                return 6;
            case 1:
                return 4;
            case 2:
                return 2;
        }
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess access, int x, int y, int z, int direction) {
        int meta = access.getBlockMetadata(x, y, z);
        return meta == 0 ? 12 : (meta == 1 ? 8 : (meta == 2 ? 4 : 0));
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
        for (int i = 0; i < this.variantNames.length; i++) {
            this.iconArray[i] = par1IconRegister.registerIcon("tragicmc:" + this.variantNames[i] + "Circuit");
        }
    }

    @Override
    public int damageDropped(int par1) {
        return par1;
    }

    @Override
    public void getSubBlocks(Item par1, CreativeTabs par2, List par3) {
        for (int i = 0; i < this.variantNames.length; i++) {
            par3.add(new ItemStack(par1, 1, i));
        }
    }
}
