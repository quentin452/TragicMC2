package tragicneko.tragicmc.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import tragicneko.tragicmc.TragicBlocks;

public class WorldGenPaintedTree extends WorldGenAbstractTree {

    private boolean isLargeTree;

    public WorldGenPaintedTree(boolean p_i45449_1_, boolean p_i45449_2_) {
        super(p_i45449_1_);
        this.isLargeTree = p_i45449_2_;
    }

    @Override
    public boolean generate(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_) {
        int l = p_76484_2_.nextInt(3) + 5;

        if (this.isLargeTree) {
            l += p_76484_2_.nextInt(7);
        }

        boolean flag = true;

        if (p_76484_4_ >= 1 && p_76484_4_ + l + 1 <= 256) {
            int j1;
            int k1;

            for (int i1 = p_76484_4_; i1 <= p_76484_4_ + 1 + l; ++i1) {
                byte b0 = 1;

                if (i1 == p_76484_4_) {
                    b0 = 0;
                }

                if (i1 >= p_76484_4_ + 1 + l - 2) {
                    b0 = 2;
                }

                for (j1 = p_76484_3_ - b0; j1 <= p_76484_3_ + b0 && flag; ++j1) {
                    for (k1 = p_76484_5_ - b0; k1 <= p_76484_5_ + b0 && flag; ++k1) {
                        if (i1 >= 0 && i1 < 256) {
                            p_76484_1_.getBlock(j1, i1, k1);

                            if (!this.isReplaceable(p_76484_1_, j1, i1, k1)) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else {
                Block block2 = p_76484_1_.getBlock(p_76484_3_, p_76484_4_ - 1, p_76484_5_);

                boolean isSoil = block2.canSustainPlant(
                    p_76484_1_,
                    p_76484_3_,
                    p_76484_4_ - 1,
                    p_76484_5_,
                    ForgeDirection.UP,
                    (IPlantable) TragicBlocks.TragicSapling);
                if (isSoil && p_76484_4_ < 256 - l - 1) {
                    block2.onPlantGrow(
                        p_76484_1_,
                        p_76484_3_,
                        p_76484_4_ - 1,
                        p_76484_5_,
                        p_76484_3_,
                        p_76484_4_,
                        p_76484_5_);
                    int k2;

                    for (k2 = p_76484_4_ - 3 + l; k2 <= p_76484_4_ + l; ++k2) {
                        j1 = k2 - (p_76484_4_ + l);
                        k1 = 1 - j1 / 2;

                        for (int l2 = p_76484_3_ - k1; l2 <= p_76484_3_ + k1; ++l2) {
                            int l1 = l2 - p_76484_3_;

                            for (int i2 = p_76484_5_ - k1; i2 <= p_76484_5_ + k1; ++i2) {
                                int j2 = i2 - p_76484_5_;

                                if (Math.abs(l1) != k1 || Math.abs(j2) != k1 || p_76484_2_.nextInt(2) != 0 && j1 != 0) {
                                    Block block1 = p_76484_1_.getBlock(l2, k2, i2);

                                    if (block1.isAir(p_76484_1_, l2, k2, i2)
                                        || block1.isLeaves(p_76484_1_, l2, k2, i2)) {
                                        this.setBlockAndNotifyAdequately(
                                            p_76484_1_,
                                            l2,
                                            k2,
                                            i2,
                                            TragicBlocks.PaintedLeaves,
                                            2);
                                    }
                                }
                            }
                        }
                    }

                    for (k2 = 0; k2 < l; ++k2) {
                        Block block3 = p_76484_1_.getBlock(p_76484_3_, p_76484_4_ + k2, p_76484_5_);

                        if (block3.isAir(p_76484_1_, p_76484_3_, p_76484_4_ + k2, p_76484_5_)
                            || block3.isLeaves(p_76484_1_, p_76484_3_, p_76484_4_ + k2, p_76484_5_)) {
                            if (k2 > 1) {
                                byte xr = (byte) (p_76484_2_.nextInt(3) - 1);
                                byte zr = (byte) (p_76484_2_.nextInt(3) - 1);
                                byte dir = (byte) (xr == 1 && zr == 0 ? 5
                                    : (xr == -1 && zr == 0 ? 4 : (zr == -1 ? 2 : 3)));
                                Block block4 = p_76484_1_.getBlock(p_76484_3_ + xr, p_76484_4_ + k2, p_76484_5_ + zr);
                                byte m = (byte) Direction.rotateOpposite[Direction.facingToDirection[dir]];
                                boolean flag2 = Math.abs(xr) == 1 && Math.abs(zr) == 1 || xr == 0 && zr == 0;
                                if (!flag2 && block4.isAir(p_76484_1_, p_76484_3_ + xr, p_76484_4_, p_76484_5_ + zr)) {
                                    this.setBlockAndNotifyAdequately(
                                        p_76484_1_,
                                        p_76484_3_ + xr,
                                        p_76484_4_ + k2,
                                        p_76484_5_ + zr,
                                        TragicBlocks.SkyFruit,
                                        m);
                                }
                            }
                            this.setBlockAndNotifyAdequately(
                                p_76484_1_,
                                p_76484_3_,
                                p_76484_4_ + k2,
                                p_76484_5_,
                                TragicBlocks.PaintedWood,
                                2);
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}
