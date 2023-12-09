package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;

public class BlockGiantCrop extends Block {

    @SideOnly(Side.CLIENT)
    private IIcon topIcon, bottomIcon;

    public BlockGiantCrop() {
        super(Material.plants);
        this.setCreativeTab(TragicMC.Survival);
        this.setHardness(0.5F);
        this.setResistance(1.0F);
        this.setStepSound(soundTypeGrass);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2) {
        if (this == TragicBlocks.PotatoBlock) return this.blockIcon;

        if (par1 == 0) {
            return this.bottomIcon;
        }

        if (par1 == 1) {
            return this.topIcon;
        }

        return this.blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon("tragicmc:" + this.textureName);

        if (this == TragicBlocks.CarrotBlock) {
            this.topIcon = par1IconRegister.registerIcon("tragicmc:" + this.textureName + "_Top");
            this.bottomIcon = par1IconRegister.registerIcon("tragicmc:" + this.textureName + "_Bottom");
        }
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return this == TragicBlocks.CarrotBlock ? Items.carrot : Items.potato;
    }

    @Override
    public int quantityDropped(Random rand) {
        return 2 + rand.nextInt(3);
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

}
