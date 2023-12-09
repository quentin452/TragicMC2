package tragicneko.tragicmc.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityNekoMiniBomb extends EntityThrowable {

    public EntityNekoMiniBomb(World world) {
        super(world);
    }

    public EntityNekoMiniBomb(World world, EntityLivingBase entity) {
        super(world, entity);
    }

    public EntityNekoMiniBomb(World world, double par2, double par4, double par6) {
        super(world, par2, par4, par6);
    }

    @Override
    protected float getGravityVelocity() {
        return inGround ? 0.0F : 0.03F;
    }

    @Override
    protected void onImpact(MovingObjectPosition mop) {
        if (mop.entityHit != null) {
            mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), 1.0F);
        } else {
            this.motionX = this.motionZ = this.motionY = 0D;
            this.onGround = true;
            this.inGround = true;
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.isInWater()) {
            if (this.worldObj.isRemote) {
                for (int l = 0; l < 5; ++l) {
                    worldObj.spawnParticle("smoke", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
                }
                this.worldObj.playSoundAtEntity(this, "random.fizz", 0.4F, 0.4F);
            } else {
                this.setDead();
            }
        }

        if (this.ticksExisted >= 40 && rand.nextInt(4) == 0) {
            if (this.worldObj.isRemote) {
                this.worldObj.spawnParticle("hugeexplosion", this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0);
            } else {
                boolean flag = this.worldObj.getGameRules()
                    .getGameRuleBooleanValue("mobGriefing");
                this.worldObj
                    .createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() * 0.5F + 1.0F, flag);

                this.setDead();
            }
        }
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer player) {}
}
