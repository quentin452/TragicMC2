package tragicneko.tragicmc.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityPitchBlack extends EntityThrowable {

    private ItemStack stack;

    public EntityPitchBlack(World world) {
        super(world);
    }

    public EntityPitchBlack(World par1World, EntityLivingBase par2EntityLivingBase) {
        super(par1World, par2EntityLivingBase);
    }

    public EntityPitchBlack(World world, double par2, double par4, double par6, boolean flag) {
        super(world, par2, par4, par6);
    }

    @Override
    protected float getGravityVelocity() {
        return inGround ? 0.0F : 0.05F;
    }

    public void setStack(ItemStack stack) {
        this.stack = stack.copy();
    }

    @Override
    protected void onImpact(MovingObjectPosition mop) {
        if (this.worldObj.isRemote) {
            for (int l = 0; l < 4; ++l) {
                worldObj.spawnParticle(
                    "magicCrit",
                    posX + (rand.nextDouble() - rand.nextDouble() * 0.25D),
                    posY + (rand.nextDouble() - rand.nextDouble() * 0.25D),
                    posZ + (rand.nextDouble() - rand.nextDouble() * 0.25D),
                    0.0D,
                    0.0D,
                    0.0D);
            }
        } else {
            if (mop.entityHit != null && !mop.entityHit.equals(this.getThrower())) {
                mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 10.0F);

                if (mop.entityHit instanceof EntityLivingBase) {
                    ((EntityLivingBase) mop.entityHit).addPotionEffect(new PotionEffect(Potion.blindness.id, 600, 1));
                }
            }

            if (this.getThrower() != null && this.stack != null) {
                if (mop.entityHit != null) this.setPosition(
                    this.getThrower().posX + rand.nextDouble() - rand.nextDouble(),
                    this.getThrower().posY + 0.4D,
                    this.getThrower().posZ + rand.nextDouble() - rand.nextDouble());
                this.entityDropItem(stack, 0.4F);
            }

            this.setDead();
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }

}
