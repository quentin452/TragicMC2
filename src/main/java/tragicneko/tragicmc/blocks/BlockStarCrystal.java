package tragicneko.tragicmc.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;

public class BlockStarCrystal extends Block {

    private String[] subNames = new String[] { "Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "LightGray",
        "DarkGray", "Pink", "LimeGreen", "Yellow", "LightBlue", "Magenta", "Orange", "White" };
    private IIcon[] iconArray = new IIcon[subNames.length];

    public BlockStarCrystal() {
        super(Material.glass);
        this.setCreativeTab(TragicMC.Survival);
        this.setResistance(10.0F);
        this.setHardness(2.0F);
        this.setStepSound(soundTypeGlass);
        this.setBlockName("tragicmc.starCrystal");
        this.lightValue = 15;
        this.lightOpacity = 2;
        this.setHarvestLevel("pickaxe", 0);
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random rand) {
        if (fortune > 0 && this.getItemDropped(0, rand, fortune) == Items.dye) {
            int j = rand.nextInt(fortune + 2) - 1;
            if (j < 0) j = 0;

            return this.quantityDropped(rand) * (j + 1);
        } else {
            return this.quantityDropped(rand);
        }
    }

    @Override
    public int quantityDropped(Random rand) {
        return 1 + rand.nextInt(4);
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int level) {
        return Items.dye;
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
            this.iconArray[i] = par1IconRegister.registerIcon("tragicmc:" + subNames[i] + "StarCrystal");
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
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() {
        return 1;
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

}
