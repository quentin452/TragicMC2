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
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;

public class BlockTragicOres extends Block {

    private String[] oreNames = new String[] { "Mercury", "Tungsten", "Ruby", "Sapphire", "Lapis", "Diamond", "Emerald",
        "Gold", "Iron", "Coal", "XP" };
    private int[] digLevels = new int[] { 1, 2, 3, 3, 1, 2, 2, 2, 1, 0, 3 };
    private IIcon[] iconArray = new IIcon[oreNames.length];

    public BlockTragicOres() {
        super(Material.rock);
        this.setCreativeTab(TragicMC.Survival);
        this.setResistance(10.0F);
        this.setHardness(6.0F);
        this.setStepSound(soundTypeStone);
        this.setBlockName("tragicmc.tragicOres");
        this.setHarvestLevels();
    }

    private void setHarvestLevels() {
        for (int i = 0; i < this.oreNames.length; i++) {
            this.setHarvestLevel("pickaxe", digLevels[i], i);
        }
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
    public Item getItemDropped(int meta, Random rand, int fortuneLevel) {
        switch (meta) {
            case 2:
                return TragicItems.Ruby;
            case 3:
                return TragicItems.Sapphire;
            case 5:
                return Items.diamond;
            case 6:
                return Items.emerald;
            case 9:
                return Items.coal;
            case 4:
                return Items.dye;
            case 10:
                return null;
            default:
                return super.getItemDropped(meta, rand, fortuneLevel);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        for (int i = 0; i < oreNames.length; i++) {
            this.iconArray[i] = par1IconRegister.registerIcon("tragicmc:Ore" + oreNames[i]);
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

    @Override
    public int getExpDrop(IBlockAccess p_149690_1_, int p_149690_5_, int p_149690_7_) {
        Random rand = TragicMC.rand;
        if (this.getItemDropped(p_149690_5_, rand, p_149690_7_) == null) {
            return 48 + rand.nextInt(48);
        } else if (this.getItemDropped(p_149690_5_, rand, p_149690_7_) == Item.getItemFromBlock(this)) {
            return 0;
        } else if (this.getItemDropped(p_149690_5_, rand, p_149690_7_) == Items.diamond
            || this.getItemDropped(p_149690_5_, rand, p_149690_7_) == Items.emerald
            || this.getItemDropped(p_149690_5_, rand, p_149690_7_) == TragicItems.Ruby
            || this.getItemDropped(p_149690_5_, rand, p_149690_7_) == TragicItems.Sapphire) {
                return MathHelper.getRandomIntegerInRange(rand, 3, 7);
            } else if (this.getItemDropped(p_149690_5_, rand, p_149690_7_) == Items.dye) {
                return MathHelper.getRandomIntegerInRange(rand, 4, 8);
            } else if (this.getItemDropped(p_149690_5_, rand, p_149690_7_) == Items.coal) {
                return MathHelper.getRandomIntegerInRange(rand, 0, 3);
            }

        return MathHelper.getRandomIntegerInRange(rand, 0, 3);
    }

}
