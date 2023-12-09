package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;

public class BlockGenericGrass extends BlockGrass {

    @SideOnly(Side.CLIENT)
    private IIcon topIcon, sideIcon;

    private String texturePrefix;

    public BlockGenericGrass(String s) {
        super();
        this.texturePrefix = s;
        this.setHardness(0.6F);
        this.setHarvestLevel("shovel", 0);
        this.setCreativeTab(TragicMC.Survival);
        this.setStepSound(soundTypeGrass);
    }

    @Override
    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection dir, IPlantable plant) {
        if ((plant instanceof BlockDeadBush || plant instanceof BlockSapling
            || plant instanceof BlockMushroom
            || plant instanceof BlockLog
            || plant instanceof BlockTallGrass
            || plant instanceof BlockTragicSapling) && dir == ForgeDirection.UP) return true;
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess p_149673_1_, int p_149673_2_, int p_149673_3_, int p_149673_4_, int p_149673_5_) {
        return this.getIcon(p_149673_5_, p_149673_1_.getBlockMetadata(p_149673_2_, p_149673_3_, p_149673_4_));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2) {
        if (par1 == 0) {
            return TragicBlocks.DeadDirt.getIcon(par1, par2);
        }

        if (par1 == 1) {
            return this.topIcon;
        }

        return this.sideIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.topIcon = par1IconRegister.registerIcon("tragicmc:" + this.texturePrefix + "GrassTop");
        this.sideIcon = par1IconRegister.registerIcon("tragicmc:" + this.texturePrefix + "GrassSide");
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.getItemFromBlock(TragicBlocks.DeadDirt);
    }

    @Override
    public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {
        if (!p_149674_1_.isRemote && this != TragicBlocks.AshenGrass) {
            if (p_149674_1_.getBlockLightValue(p_149674_2_, p_149674_3_ + 1, p_149674_4_) < 4
                && p_149674_1_.getBlockLightOpacity(p_149674_2_, p_149674_3_ + 1, p_149674_4_) > 2) {
                p_149674_1_.setBlock(p_149674_2_, p_149674_3_, p_149674_4_, TragicBlocks.DeadDirt);
            } else if (p_149674_1_.getBlockLightValue(p_149674_2_, p_149674_3_ + 1, p_149674_4_) >= 6) {
                for (int l = 0; l < 4; ++l) {
                    int i1 = p_149674_2_ + p_149674_5_.nextInt(3) - 1;
                    int j1 = p_149674_3_ + p_149674_5_.nextInt(5) - 3;
                    int k1 = p_149674_4_ + p_149674_5_.nextInt(3) - 1;
                    p_149674_1_.getBlock(i1, j1 + 1, k1);

                    if (p_149674_1_.getBlock(i1, j1, k1) == TragicBlocks.DeadDirt
                        && p_149674_1_.getBlockLightValue(i1, j1 + 1, k1) >= 4
                        && p_149674_1_.getBlockLightOpacity(i1, j1 + 1, k1) <= 2) {
                        p_149674_1_.setBlock(i1, j1, k1, this);
                    }
                }
            }
        }
    }

    @Override
    public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_,
        int p_149853_5_) {
        int l = 0;

        while (l < 128) {
            int i1 = p_149853_3_;
            int j1 = p_149853_4_ + 1;
            int k1 = p_149853_5_;
            int l1 = 0;

            while (true) {
                if (l1 < l / 16) {
                    i1 += p_149853_2_.nextInt(3) - 1;
                    j1 += (p_149853_2_.nextInt(3) - 1) * p_149853_2_.nextInt(3) / 2;
                    k1 += p_149853_2_.nextInt(3) - 1;

                    if (p_149853_1_.getBlock(i1, j1 - 1, k1) != TragicBlocks.AshenGrass
                        && !p_149853_1_.getBlock(i1, j1, k1)
                            .isNormalCube()) {
                        ++l1;
                        continue;
                    }
                } else if (p_149853_1_.getBlock(i1, j1, k1)
                    .getMaterial() == Material.air) {
                        if (p_149853_2_.nextInt(8) != 0) {
                            if (Blocks.tallgrass.canBlockStay(p_149853_1_, i1, j1, k1)) {
                                p_149853_1_.setBlock(i1, j1, k1, this.getTallGrass(), 0, 3);
                            }
                        } else {
                            p_149853_1_.getBiomeGenForCoords(i1, k1)
                                .plantFlower(p_149853_1_, p_149853_2_, i1, j1, k1);
                        }
                    }

                ++l;
                break;
            }
        }
    }

    private Block getTallGrass() {
        if (this == TragicBlocks.BrushedGrass) return TragicBlocks.PaintedTallGrass;
        if (this == TragicBlocks.StarlitGrass) return TragicBlocks.StarlitTallGrass;
        if (this == TragicBlocks.AshenGrass && TragicMC.rand.nextInt(16) == 0)
            return TragicMC.rand.nextBoolean() ? TragicBlocks.AshenTallGrass : TragicBlocks.DriedGrass;
        return TragicBlocks.DeadBush;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor() {
        return -1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int p_149741_1_) {
        return -1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_) {
        return -1;
    }
}
