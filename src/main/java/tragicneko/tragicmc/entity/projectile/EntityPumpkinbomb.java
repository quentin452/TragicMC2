package tragicneko.tragicmc.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityPumpkinbomb extends EntityThrowable {

    private int bounces;

    public EntityPumpkinbomb(World world) {
        super(world);
    }

    public EntityPumpkinbomb(World world, EntityLivingBase entity) {
        super(world, entity);
    }

    public EntityPumpkinbomb(World world, double par2, double par4, double par6) {
        super(world, par2, par4, par6);
    }

    @Override
    protected float getGravityVelocity() {
        return inGround ? 0.0F : 0.03F;
    }

    @Override
    protected void onImpact(MovingObjectPosition mop) {
        if (this.worldObj.isRemote) {
            for (int l = 0; l < 6; ++l) {
                worldObj.spawnParticle(
                    "flame",
                    posX,
                    posY,
                    posZ,
                    (rand.nextGaussian() - 0.4D) * 0.05D,
                    (rand.nextGaussian() - 0.4D) * 0.05D,
                    (rand.nextGaussian() - 0.4D) * 0.05D);
            }

            if (this.inGround) {
                this.worldObj.spawnParticle("hugeexplosion", this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0);
            }
        } else {
            if (!inGround) {
                if (mop.entityHit != null) {
                    mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), 1.0F);
                    mop.entityHit.setFire(rand.nextInt(6) + 2);
                }

                this.motionX = (rand.nextDouble() - rand.nextDouble()) * 1.05D;
                this.motionY = rand.nextDouble() * 1.05D;
                this.motionZ = (rand.nextDouble() - rand.nextDouble()) * 1.05D;

                if (Math.abs(motionX) <= 0.15D) {
                    motionX = 0.0;
                }

                if (Math.abs(motionY) <= 0.15D) {
                    motionY = 0.0;
                }

                if (Math.abs(motionZ) <= 0.15D) {
                    motionZ = 0.0;
                }

                if (motionX == 0.0 && motionY == 0.0 && motionZ == 0.0 || this.bounces > 2) {
                    int random = rand.nextInt(8) + 6;

                    for (int l = 0; l < random; l++) {
                        double d0 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + this.posX) - this.posX;
                        double d1 = (MathHelper.getRandomIntegerInRange(rand, 0, 6) + this.posY) - this.posY;
                        double d2 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + this.posZ) - this.posZ;

                        if (this.getThrower() != null && !this.worldObj.isRemote) {
                            EntitySmallFireball fireball = new EntitySmallFireball(
                                this.worldObj,
                                this.getThrower(),
                                d0,
                                d1,
                                d2);

                            fireball.setPosition(this.posX, this.posY, this.posZ);
                            this.worldObj.spawnEntityInWorld(fireball);
                        }
                    }

                    this.setDead();
                } else {
                    this.bounces++;
                    if (!this.worldObj.isRemote)
                        this.worldObj.playSoundAtEntity(this, "tragicmc:random.pumpkinsnicker", 0.6F, 1.0F);
                }
            }

        }

    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.worldObj.isRemote && !this.isInWater()) {
            worldObj.spawnParticle(
                "flame",
                posX,
                posY,
                posZ,
                (rand.nextGaussian() - 0.4D) * 0.05D,
                (rand.nextGaussian() - 0.4D) * 0.05D,
                (rand.nextGaussian() - 0.4D) * 0.05D);
        }

        this.motionY *= 0.98D;
    }
}
