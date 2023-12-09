package tragicneko.tragicmc.blocks;

import net.minecraft.block.BlockVine;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;

public class BlockGlowvine extends BlockVine {

    public BlockGlowvine() {
        super();
        this.setCreativeTab(TragicMC.Survival);
        this.setLightLevel(1.0F);
        this.setLightOpacity(0);
        this.setTickRandomly(true);
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
