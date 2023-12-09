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

public class BlockCorsin extends Block {

    private String[] oreNames = new String[] { "", "Faded", "Brick", "FadedBrick", "Circle", "Celled", "Scarred",
        "Crystal", "CrystalWrap" };
    private IIcon[] iconArray = new IIcon[oreNames.length];

    public BlockCorsin() {
        super(Material.rock);
        this.setCreativeTab(TragicMC.Survival);
        this.setResistance(15.0F);
        this.setHardness(3.5F);
        this.setStepSound(soundTypeStone);
        this.setHarvestLevel("pickaxe", 0);
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
    public int getLightValue(IBlockAccess access, int x, int y, int z) {
        int meta = access.getBlockMetadata(x, y, z);
        switch (meta) {
            default:
                return 0;
            case 7:
                return 15;
            case 8:
                return 8;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        for (int i = 0; i < this.oreNames.length; i++) {
            this.iconArray[i] = par1IconRegister.registerIcon("tragicmc:Corsin" + this.oreNames[i]);
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
