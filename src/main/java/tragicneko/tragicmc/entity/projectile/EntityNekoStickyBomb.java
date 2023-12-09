package tragicneko.tragicmc.entity.projectile;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;

public class EntityNekoStickyBomb extends EntityThrowable {

    public EntityNekoStickyBomb(World world) {
        super(world);
    }

    public EntityNekoStickyBomb(World world, EntityLivingBase entity) {
        super(world, entity);
    }

    public EntityNekoStickyBomb(World world, double par2, double par4, double par6) {
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
            if (mop.entityHit instanceof EntityLivingBase && !mop.entityHit.equals(this.getThrower())) {
                mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), 1.0F);

                if (mop.entityHit.riddenByEntity == null) {
                    this.motionX = 0;
                    this.motionY = 0;
                    this.motionZ = 0;
                    this.mountEntity(mop.entityHit);
                }

                if (mop.entityHit instanceof EntityLivingBase && TragicConfig.allowStun) {
                    ((EntityLivingBase) mop.entityHit).addPotionEffect(new PotionEffect(TragicPotion.Stun.id, 10, 0));
                }
            }
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
                for (byte l = 0; l < 5; ++l) {
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
                for (int l = 0; l < 11; l++) {
                    double d0 = MathHelper.getRandomIntegerInRange(rand, -1, 1) + this.posX;
                    double d1 = rand.nextInt(3) + this.posY - 1;
                    double d2 = MathHelper.getRandomIntegerInRange(rand, -1, 1) + this.posZ;

                    if (this.worldObj.isAirBlock((int) d0, (int) d1, (int) d2) && rand.nextInt(3) == 0) {
                        Block block = Blocks.web;
                        this.worldObj.setBlock((int) d0, (int) d1, (int) d2, block);
                    }
                }

                this.setDead();
            }
        }
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer player) {}
}
