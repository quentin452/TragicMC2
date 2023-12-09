package tragicneko.tragicmc.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import tragicneko.tragicmc.TragicMC;

import java.util.List;
import java.util.Random;

public class BlockBone extends Block {

    private String[] oreNames = new String[] { "Normal", "Rotten" };
    private IIcon[] iconArray = new IIcon[oreNames.length];

    public BlockBone() {
        super(Material.gourd);
        this.setCreativeTab(TragicMC.Survival);
        this.setHardness(0.5F);
        this.setResistance(1.0F);
        this.setStepSound(soundTypeStone);
        this.setBlockName("tragicmc.boneBlock");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item par1, CreativeTabs par2, List par3) {
        for (int i = 0; i < this.oreNames.length; i++) {
            par3.add(new ItemStack(par1, 1, i));
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
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        for (int i = 0; i < 2; i++) {
            this.iconArray[i] = par1IconRegister.registerIcon("tragicmc:" + oreNames[i] + "BoneBlock");
        }
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Items.dye;
    }

    @Override
    public int damageDropped(int par1) {
        return 15;
    }

    @Override
    public int quantityDropped(Random rand) {
        return 2 + rand.nextInt(3);
    }

    @Override
    public int quantityDroppedWithBonus(int p_149679_1_, Random p_149679_2_) {
        if (p_149679_1_ > 0 && Item.getItemFromBlock(this) != this.getItemDropped(0, p_149679_2_, p_149679_1_)) {
            int j = p_149679_2_.nextInt(p_149679_1_ + 2) - 1;

            if (j < 1) {
                j = 1;
            }

            return this.quantityDropped(p_149679_2_) * (j);
        } else {
            return this.quantityDropped(p_149679_2_);
        }
    }

}
