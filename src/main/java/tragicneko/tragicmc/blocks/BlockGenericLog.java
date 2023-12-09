package tragicneko.tragicmc.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;

public class BlockGenericLog extends BlockRotatedPillar {

    @SideOnly(Side.CLIENT)
    private IIcon sideIcon;

    private String texturePrefix;

    public BlockGenericLog(String s) {
        super(Material.wood);
        this.texturePrefix = s;
        this.setCreativeTab(TragicMC.Survival);
        this.setResistance(5.0F);
        this.setHardness(1.0F);
        this.setStepSound(soundTypeWood);
        this.setHarvestLevel("axe", 1);
        this.setLightLevel(0.5F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.field_150164_N = par1IconRegister.registerIcon("tragicmc:" + this.texturePrefix + "LogTop");
        this.sideIcon = par1IconRegister.registerIcon("tragicmc:" + this.texturePrefix + "LogSide");
    }

    @Override
    protected IIcon getSideIcon(int p_150163_1_) {
        return this.sideIcon;
    }

    @Override
    public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_,
        int p_149749_6_) {
        byte b0 = 4;
        int i1 = b0 + 1;

        if (p_149749_1_.checkChunksExist(
            p_149749_2_ - i1,
            p_149749_3_ - i1,
            p_149749_4_ - i1,
            p_149749_2_ + i1,
            p_149749_3_ + i1,
            p_149749_4_ + i1)) {
            for (int j1 = -b0; j1 <= b0; ++j1) {
                for (int k1 = -b0; k1 <= b0; ++k1) {
                    for (int l1 = -b0; l1 <= b0; ++l1) {
                        Block block = p_149749_1_.getBlock(p_149749_2_ + j1, p_149749_3_ + k1, p_149749_4_ + l1);
                        if (block.isLeaves(p_149749_1_, p_149749_2_ + j1, p_149749_3_ + k1, p_149749_4_ + l1)) {
                            block.beginLeavesDecay(p_149749_1_, p_149749_2_ + j1, p_149749_3_ + k1, p_149749_4_ + l1);
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    @Override
    public boolean isWood(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    @Override
    public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
        return false;
    }
}
