package tragicneko.tragicmc.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityBanana extends EntityThrowable {

    public EntityBanana(World world) {
        super(world);
    }

    public EntityBanana(World par1World, EntityLivingBase par2EntityLivingBase) {
        super(par1World, par2EntityLivingBase);
    }

    public EntityBanana(World world, double par2, double par4, double par6, boolean flag) {
        super(world, par2, par4, par6);
    }

    @Override
    protected float getGravityVelocity() {
        return inGround ? 0.0F : 0.05F;
    }

    @Override
    protected void onImpact(MovingObjectPosition mop) {

        if (this.worldObj.isRemote) {
            for (int l = 0; l < 4; ++l) {
                worldObj.spawnParticle("crit", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
            }
        } else {
            if (mop.entityHit != null) {
                mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), 1.0F);
            } else {
                this.motionX = this.motionZ = this.motionY = 0D;
                this.onGround = true;
                this.inGround = true;
            }

            this.setDead();
        }
    }

}
