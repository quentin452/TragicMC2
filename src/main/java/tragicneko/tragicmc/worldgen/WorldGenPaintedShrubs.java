package tragicneko.tragicmc.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import tragicneko.tragicmc.TragicBlocks;

public class WorldGenPaintedShrubs extends WorldGenTrees {

    public WorldGenPaintedShrubs() {
        super(false);
    }

    @Override
    public boolean generate(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_) {
        Block block;

        do {
            block = p_76484_1_.getBlock(p_76484_3_, p_76484_4_, p_76484_5_);
            if (!(block.isLeaves(p_76484_1_, p_76484_3_, p_76484_4_, p_76484_5_)
                || block.isAir(p_76484_1_, p_76484_3_, p_76484_4_, p_76484_5_))) {
                break;
            }
            --p_76484_4_;
        } while (p_76484_4_ > 0);

        Block block1 = p_76484_1_.getBlock(p_76484_3_, p_76484_4_, p_76484_5_);

        if (block1.canSustainPlant(
            p_76484_1_,
            p_76484_3_,
            p_76484_4_,
            p_76484_5_,
            ForgeDirection.UP,
            (IPlantable) TragicBlocks.TragicSapling)) {
            ++p_76484_4_;
            this.setBlockAndNotifyAdequately(
                p_76484_1_,
                p_76484_3_,
                p_76484_4_,
                p_76484_5_,
                TragicBlocks.PaintedWood,
                0);

            for (int l = p_76484_4_; l <= p_76484_4_ + 2; ++l) {
                int i1 = l - p_76484_4_;
                int j1 = 2 - i1;

                for (int k1 = p_76484_3_ - j1; k1 <= p_76484_3_ + j1; ++k1) {
                    int l1 = k1 - p_76484_3_;

                    for (int i2 = p_76484_5_ - j1; i2 <= p_76484_5_ + j1; ++i2) {
                        int j2 = i2 - p_76484_5_;

                        if ((Math.abs(l1) != j1 || Math.abs(j2) != j1 || p_76484_2_.nextInt(2) != 0)
                            && p_76484_1_.getBlock(k1, l, i2)
                                .canBeReplacedByLeaves(p_76484_1_, k1, l, i2)) {
                            this.setBlockAndNotifyAdequately(p_76484_1_, k1, l, i2, TragicBlocks.PaintedLeaves, 0);
                        }
                    }
                }
            }
        }

        return true;
    }
}
