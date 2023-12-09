package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;

public class BlockIceSpike extends Block {

    private final boolean onGround;

    public BlockIceSpike(boolean flag) {
        super(Material.coral);
        this.onGround = flag;
        this.setCreativeTab(TragicMC.Survival);
        this.setBlockName("tragicmc.iceSpike");
        this.setHardness(25.0F);
        this.setResistance(10.0F);
        this.setHarvestLevel("pickaxe", 1);
        this.setStepSound(soundTypeStone);
        this.setLightOpacity(0);
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        if (!this.onGround && world.getBlock(x, y + 1, z)
            .isSideSolid(world, x, y + 1, z, ForgeDirection.SOUTH)) return true;
        return this.onGround && World.doesBlockHaveSolidTopSurface(world, x, y - 1, z);
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        if (!this.canBlockStay(world, x, y, z) && onGround
            && TragicBlocks.IceSpikeHanging.canBlockStay(world, x, y, z)) {
            world.setBlock(x, y, z, TragicBlocks.IceSpikeHanging, 0, 4);
        }
    }

    @Override
    public void onFallenUpon(World world, int x, int y, int z, Entity entity, float distance) {
        if (this.onGround) entity.attackEntityFrom(DamageSource.cactus, distance * 1.5F);
    }

    @Override
    public int getRenderType() {
        return 1;
    }

    @Override
    public boolean canHarvestBlock(EntityPlayer player, int meta) {
        return super.canHarvestBlock(player, meta);
    }

    @Override
    protected boolean canSilkHarvest() {
        return true;
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int level) {
        return null;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        Block block = world.getBlock(x, y, z);
        return block == this ? false : super.shouldSideBeRendered(world, x, y, z, side);
    }

    @Override
    public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
        super.onEntityWalking(world, x, y, z, entity);
        if (world.rand.nextInt(4) == 0 && entity instanceof EntityLivingBase)
            entity.attackEntityFrom(new DamageSource("iceSpike").setDamageBypassesArmor(), 1F);
    }
}
