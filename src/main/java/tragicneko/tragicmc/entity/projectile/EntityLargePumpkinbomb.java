package tragicneko.tragicmc.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityLargePumpkinbomb extends EntityThrowable {

    private int airTicks;

    public EntityLargePumpkinbomb(World world) {
        super(world);
    }

    public EntityLargePumpkinbomb(World world, EntityLivingBase entity) {
        super(world, entity);
    }

    public EntityLargePumpkinbomb(World world, double par2, double par4, double par6) {
        super(world, par2, par4, par6);
    }

    @Override
    protected float getGravityVelocity() {
        return inGround ? 0.0F : 0.06F;
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
                    (rand.nextGaussian() - 0.4D) * 0.2D,
                    (rand.nextGaussian() - 0.4D) * 0.2D,
                    (rand.nextGaussian() - 0.4D) * 0.2D);
            }

            this.worldObj.spawnParticle("hugeexplosion", this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0);
        } else {
            if (mop.entityHit != null) {
                mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 5.0F);
                if (mop.entityHit instanceof EntityLivingBase) {
                    ((EntityLivingBase) mop.entityHit)
                        .addPotionEffect(new PotionEffect(Potion.wither.id, 200 + rand.nextInt(160)));
                }
            }

            int random = rand.nextInt(8) + 6;

            if (this.getThrower() != null) {
                for (int l = 0; l < random; l++) {
                    EntityPumpkinbomb bomb = new EntityPumpkinbomb(this.worldObj, this.getThrower());

                    bomb.setPosition(
                        this.posX + MathHelper.getRandomIntegerInRange(rand, -4, 4),
                        this.posY + 1,
                        this.posZ + MathHelper.getRandomIntegerInRange(rand, -4, 4));
                    this.worldObj.spawnEntityInWorld(bomb);
                }

                boolean flag = this.worldObj.getGameRules()
                    .getGameRuleBooleanValue("mobGriefing");
                this.worldObj
                    .createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() * 0.5F + 2.5F, flag);
            }

            this.setDead();
        }
    }

    @Override
    public void onUpdate() {
        if (this.isInWater()) {
            for (int l = 0; l < 5; ++l) {
                worldObj.spawnParticle("smoke", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
            }
            this.worldObj.playSoundAtEntity(this, "random.fizz", 0.4F, 0.4F);
            this.setDead();
        }

        if (!inGround) {
            this.airTicks++;

            for (int l = 0; l < 4; ++l) {
                worldObj.spawnParticle("flame", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
            }

            for (int l = 0; l < 5; ++l) {
                worldObj.spawnParticle("smoke", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
            }
        }

        if (this.airTicks >= 60) {
            int random = rand.nextInt(8) + 4;
            this.worldObj.spawnParticle("hugeexplosion", this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0);
            this.worldObj.playSoundAtEntity(this, "mob.blaze.breathe", 0.4F, 0.4F);

            for (int l = 0; l < random; l++) {
                double d0 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + this.posX) - this.posX;
                double d1 = (MathHelper.getRandomIntegerInRange(rand, 0, 6) + this.posY) - this.posY;
                double d2 = (MathHelper.getRandomIntegerInRange(rand, -4, 4) + this.posZ) - this.posZ;

                if (this.getThrower() != null && !this.worldObj.isRemote) {
                    EntityPumpkinbomb bomb = new EntityPumpkinbomb(this.worldObj, d0, d1, d2);

                    bomb.setPosition(this.posX, this.posY, this.posZ);
                    this.worldObj.spawnEntityInWorld(bomb);
                }
            }

            if (!this.worldObj.isRemote) this.setDead();
        }

        if (this.airTicks > 60 && !this.worldObj.isRemote) {
            this.setDead();
        }

        super.onUpdate();

    }

    @Override
    public void onCollideWithPlayer(EntityPlayer player) {
        player.setFire(rand.nextInt(4) + 2);
    }
}
