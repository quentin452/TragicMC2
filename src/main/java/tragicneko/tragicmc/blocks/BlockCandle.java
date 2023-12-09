package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.BlockTorch;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;

public class BlockCandle extends BlockTorch {

    public BlockCandle() {
        super();
        this.setLightLevel(0.49F);
        this.setLightOpacity(0);
        this.setCreativeTab(TragicMC.Survival);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon("tragicmc:Candle");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_,
        Random p_149734_5_) {
        int l = p_149734_1_.getBlockMetadata(p_149734_2_, p_149734_3_, p_149734_4_);
        double d0 = p_149734_2_ + 0.5F;
        double d1 = p_149734_3_ + 0.75F;
        double d2 = p_149734_4_ + 0.5F;
        double d3 = 0.2399999988079071D;
        double d4 = 0.27000001072883606D;

        if (l == 1) {
            p_149734_1_.spawnParticle("smoke", d0 - d4 + 0.05, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
            p_149734_1_.spawnParticle("flame", d0 - d4 + 0.05, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
        } else if (l == 2) {
            p_149734_1_.spawnParticle("smoke", d0 + d4 - 0.05, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
            p_149734_1_.spawnParticle("flame", d0 + d4 - 0.05, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
        } else if (l == 3) {
            p_149734_1_.spawnParticle("smoke", d0, d1 + d3, d2 - d4 + 0.05, 0.0D, 0.0D, 0.0D);
            p_149734_1_.spawnParticle("flame", d0, d1 + d3, d2 - d4 + 0.05, 0.0D, 0.0D, 0.0D);
        } else if (l == 4) {
            p_149734_1_.spawnParticle("smoke", d0, d1 + d3, d2 + d4 - 0.05, 0.0D, 0.0D, 0.0D);
            p_149734_1_.spawnParticle("flame", d0, d1 + d3, d2 + d4 - 0.05, 0.0D, 0.0D, 0.0D);
        } else {
            p_149734_1_.spawnParticle("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
            p_149734_1_.spawnParticle("flame", d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }
}
