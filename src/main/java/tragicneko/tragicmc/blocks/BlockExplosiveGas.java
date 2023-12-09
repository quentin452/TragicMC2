package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.util.WorldHelper;

public class BlockExplosiveGas extends BlockGas {

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        for (int i = 0; i < 10; i++) {
            world.spawnParticle(
                "reddust",
                x + rand.nextDouble() - rand.nextDouble(),
                y + (rand.nextDouble() * 0.725),
                z + rand.nextDouble() - rand.nextDouble(),
                0.6F,
                0.6F,
                0.6F);
            world.spawnParticle(
                "reddust",
                x + rand.nextDouble() - rand.nextDouble(),
                y + (rand.nextDouble() * 0.725),
                z + rand.nextDouble() - rand.nextDouble(),
                0.8F,
                0.8F,
                0.8F);
        }
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        Block[] block = new Block[] { world.getBlock(x, y, z), world.getBlock(x + 1, y, z), world.getBlock(x - 1, y, z),
            world.getBlock(x, y, z + 1), world.getBlock(x, y, z - 1), world.getBlock(x, y + 1, z),
            world.getBlock(x, y - 1, z) };
        for (Block b : block) {
            if (b instanceof BlockFire || b.getMaterial() == Material.lava)
                world.createExplosion(null, x, y, z, 1.5F + rand.nextFloat(), WorldHelper.getMobGriefing(world));
        }
        world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
    }

    @Override
    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion) {
        if (!world.isRemote)
            world.createExplosion(null, x, y, z, 1.5F + world.rand.nextFloat(), WorldHelper.getMobGriefing(world));
    }
}
