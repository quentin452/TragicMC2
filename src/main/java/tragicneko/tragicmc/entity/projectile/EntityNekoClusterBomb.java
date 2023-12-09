package tragicneko.tragicmc.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityNekoClusterBomb extends EntityThrowable {

    public EntityNekoClusterBomb(World world) {
        super(world);
    }

    public EntityNekoClusterBomb(World world, EntityLivingBase entity) {
        super(world, entity);
    }

    public EntityNekoClusterBomb(World world, double par2, double par4, double par6) {
        super(world, par2, par4, par6);
    }

    @Override
    protected float getGravityVelocity() {
        return inGround ? 0.0F : 0.03F;
    }

    @Override
    protected void onImpact(MovingObjectPosition mop) {
        if (mop == null || this.worldObj.isRemote) return;

        if (mop.entityHit != null) {
            mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), 1.0F);
        } else {
            for (byte l = 0; l < 8; l++) {
                if (this.getThrower() != null && !this.worldObj.isRemote) {
                    EntityNekoMiniBomb bomb = new EntityNekoMiniBomb(this.worldObj, this.getThrower());

                    bomb.setPosition(this.posX, this.posY, this.posZ);
                    bomb.motionX = (rand.nextDouble() - rand.nextDouble()) * 0.25;
                    bomb.motionY = -0.325;
                    bomb.motionZ = (rand.nextDouble() - rand.nextDouble()) * 0.25;
                    this.worldObj.spawnEntityInWorld(bomb);
                }
            }

            boolean flag = this.worldObj.getGameRules()
                .getGameRuleBooleanValue("mobGriefing");
            this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() * 0.5F + 2.5F, flag);

            this.setDead();
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

        if (this.ticksExisted >= 40) {
            if (this.worldObj.isRemote) {
                this.worldObj.spawnParticle("hugeexplosion", this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0);
            } else {
                for (byte l = 0; l < 8; l++) {
                    if (this.getThrower() != null && !this.worldObj.isRemote) {
                        EntityNekoMiniBomb bomb = new EntityNekoMiniBomb(this.worldObj, this.getThrower());

                        bomb.setPosition(this.posX, this.posY, this.posZ);
                        bomb.motionX = (rand.nextDouble() - rand.nextDouble()) * 0.25;
                        bomb.motionY = -0.325;
                        bomb.motionZ = (rand.nextDouble() - rand.nextDouble()) * 0.25;
                        this.worldObj.spawnEntityInWorld(bomb);
                    }
                }

                boolean flag = this.worldObj.getGameRules()
                    .getGameRuleBooleanValue("mobGriefing");
                this.worldObj
                    .createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() * 0.5F + 2.5F, flag);

                this.setDead();
            }
        }
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer player) {}
}
