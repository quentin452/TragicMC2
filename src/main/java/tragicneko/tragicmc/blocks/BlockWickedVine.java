package tragicneko.tragicmc.blocks;

import net.minecraft.block.BlockVine;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;

public class BlockWickedVine extends BlockVine {

    public BlockWickedVine() {
        super();
        this.setCreativeTab(TragicMC.Survival);
        this.setLightLevel(0.4F);
        this.setLightOpacity(0);
        this.setTickRandomly(true);
        this.setBlockTextureName("tragicmc:WickedVine");
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

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (!world.isRemote && entity instanceof EntityLivingBase) entity.attackEntityFrom(DamageSource.cactus, 1.0F);
    }
}
