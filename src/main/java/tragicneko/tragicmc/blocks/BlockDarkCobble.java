package tragicneko.tragicmc.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;

public class BlockDarkCobble extends Block {

    private String[] oreNames = new String[] { "Normal", "Hot", "Toxic", "Ashen" };

    private IIcon[] iconArray = new IIcon[oreNames.length];

    public BlockDarkCobble() {
        super(Material.rock);
        this.setHarvestLevel("pickaxe", 0);
        this.setCreativeTab(TragicMC.Survival);
        this.setResistance(1.0F);
        this.setHardness(1.0F);
        this.setBlockName("tragicmc.darkCobblestone");
    }

    @Override
    public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side) {
        if (side == ForgeDirection.UP && world.getBlockMetadata(x, y, z) == 1) {
            return true;
        }
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
        for (int i = 0; i < this.oreNames.length; i++) {
            this.iconArray[i] = par1IconRegister.registerIcon("tragicmc:" + this.oreNames[i] + "DarkCobble");
        }
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
}
