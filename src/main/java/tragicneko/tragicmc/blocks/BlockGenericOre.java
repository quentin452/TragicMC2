package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;

public class BlockGenericOre extends Block {

    private boolean dropsSelf;

    public BlockGenericOre(int level, boolean dropsSelf) {
        super(Material.rock);
        this.dropsSelf = dropsSelf;
        this.setHarvestLevel("pickaxe", level);
        this.setStepSound(soundTypeStone);
        this.setCreativeTab(TragicMC.Survival);
    }

    @Override
    public Item getItemDropped(int par1, Random rand, int par3) {
        if (this.dropsSelf) return Item.getItemFromBlock(this);
        return this == TragicBlocks.RubyOre ? TragicItems.Ruby : TragicItems.Sapphire;
    }

    @Override
    public int quantityDropped(Random rand) {
        return 1;
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
    public int getExpDrop(IBlockAccess p_149690_1_, int p_149690_5_, int p_149690_7_) {
        if (this.getItemDropped(p_149690_5_, TragicMC.rand, p_149690_7_) != Item.getItemFromBlock(this)) {
            int j1 = MathHelper.getRandomIntegerInRange(TragicMC.rand, 2, 5);
            return j1;
        }
        return 0;
    }

}
