package tragicneko.tragicmc.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockVine;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;

import java.util.Random;

public class BlockMoss extends BlockVine {

    public BlockMoss() {
        super();
        this.setCreativeTab(TragicMC.Survival);
        this.setLightOpacity(0);
        this.setTickRandomly(true);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        super.updateTick(world, x, y, z, rand);
        if (world.getBlock(x, y - 1, z) == TragicBlocks.Permafrost && world.getBlockMetadata(x, y - 1, z) != 2)
            world.setBlockMetadataWithNotify(x, y - 1, z, 2, 4);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor() {
        return -1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int p_149741_1_) {
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_) {
        return -1;
    }

    @Override
    public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity) {
        return false;
    }
}
