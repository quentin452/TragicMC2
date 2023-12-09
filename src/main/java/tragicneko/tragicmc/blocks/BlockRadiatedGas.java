package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockRadiatedGas extends BlockGas {

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (!world.isRemote && entity instanceof EntityLivingBase) {
            entity.attackEntityFrom(DamageSource.cactus, 1.0F);
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.poison.id, 200, 0));
        }
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
                0.4F,
                1.0F,
                0.4F);
            world.spawnParticle(
                "reddust",
                x + rand.nextDouble() - rand.nextDouble(),
                y + (rand.nextDouble() * 0.725),
                z + rand.nextDouble() - rand.nextDouble(),
                0.1F,
                1.0F,
                0.1F);
        }
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {

    }
}
