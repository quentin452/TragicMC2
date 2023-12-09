package tragicneko.tragicmc.entity.projectile;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.entity.EntityDimensionalAnomaly;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityOverlordMortor extends EntityProjectile {

    protected EntityLivingBase target = null;
    protected int ticksWithTarget;
    public boolean spawnsAnomalies = true;

    public EntityOverlordMortor(World par1World) {
        super(par1World);
        this.setSize(1.0F, 1.0F);
    }

    @Override
    public float getMotionFactor() {
        return 0.75F;
    }

    public EntityOverlordMortor(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5,
        double par7) {
        super(par1World, par2EntityLivingBase, par3, par5, par7);
    }

    @Override
    protected void onImpact(MovingObjectPosition mop) {
        if (mop == null || this.worldObj.isRemote) return;

        if (mop.entityHit != null) {
            if (mop.entityHit == this.shootingEntity && this.shootingEntity != null) return;

            if (mop.entityHit instanceof EntityLivingBase
                && ((EntityLivingBase) mop.entityHit).getCreatureAttribute() != TragicEntities.Synapse) {
                mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), 10.0F);

                boolean flag = this.worldObj.getGameRules()
                    .getGameRuleBooleanValue("mobGriefing");
                this.worldObj.createExplosion(
                    this.shootingEntity != null ? this.shootingEntity : this,
                    this.posX,
                    this.posY,
                    this.posZ,
                    rand.nextFloat() + 4.0F,
                    flag);
            }
        } else {
            if (this.worldObj
                .getEntitiesWithinAABB(EntityDimensionalAnomaly.class, this.boundingBox.expand(64.0, 64.0, 64.0D))
                .size() < 8 && rand.nextBoolean() && this.spawnsAnomalies) {
                EntityDimensionalAnomaly anomoly = new EntityDimensionalAnomaly(this.worldObj);
                anomoly.setPosition(mop.hitVec.xCoord, mop.hitVec.yCoord, mop.hitVec.zCoord);
                this.worldObj.spawnEntityInWorld(anomoly);
            } else {
                ArrayList<int[]> list = WorldHelper
                    .getBlocksInSphericalRange(this.worldObj, 2.5, this.posX, this.posY, this.posZ);
                for (int[] coords : list) {
                    if (World.doesBlockHaveSolidTopSurface(this.worldObj, coords[0], coords[1] - 1, coords[2])
                        && this.worldObj.getBlock(coords[0], coords[1], coords[2])
                            .isAir(this.worldObj, coords[0], coords[1], coords[2])) {
                        this.worldObj.setBlock(coords[0], coords[1], coords[2], TragicBlocks.CorruptedGas);
                    }
                }
            }
        }

        this.setDead();
        if (!this.worldObj.isRemote) this.worldObj.playSoundAtEntity(this, "tragicmc:random.rocketflying", 1.0F, 0.2F);
    }

    @Override
    public void onUpdate() {
        if (this.target != null && (this.target.getHealth() == 0 || this.target.isDead)) {
            this.target = null;
        }

        if (this.target != null && this.ticksInAir > 5) {
            this.ticksWithTarget++;
        }

        if ((this.ticksWithTarget > 60 || this.ticksInAir > 120) && !this.worldObj.isRemote) {
            if (rand.nextInt(4) == 0 && this.worldObj
                .getEntitiesWithinAABB(EntityDimensionalAnomaly.class, this.boundingBox.expand(64.0, 64.0, 64.0D))
                .size() < 8) {
                EntityDimensionalAnomaly anomoly = new EntityDimensionalAnomaly(this.worldObj);
                anomoly.setPosition(this.posX, this.posY, this.posZ);
                this.worldObj.spawnEntityInWorld(anomoly);
            }

            this.setDead();
        }

        super.onUpdate();

        if (this.target == null && this.ticksInAir > 5) {
            List<EntityLivingBase> list = this.worldObj
                .getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(16.0, 16.0, 16.0));

            for (EntityLivingBase e : list) {
                if (e != this.shootingEntity && this.shootingEntity != null
                    && this.shootingEntity.canEntityBeSeen(e)
                    && e.getCreatureAttribute() != TragicEntities.Synapse) {
                    this.target = e;
                    break;
                }
            }
        }

        if (this.target != null && this.ticksInAir > 6 && this.ticksExisted % 5 == 0) {
            boolean flag = true;
            float f = this.getDistanceToEntity(this.target);
            if ((this.ticksWithTarget > 30 || this.ticksWithTarget > f * 10) && f > 3.0) flag = false;

            if (flag) {
                double limit = 0.23;
                double mx = (target.posX - this.posX) * 0.068;
                boolean mxf = Math.abs(mx) > 0 && Math.abs(mx) > limit;
                this.motionX += mxf ? (mx > 0 ? limit : -limit) : mx;

                double my = (target.posY - this.posY + (target.height * 2 / 3)) * 0.068;
                boolean myf = Math.abs(my) > 0 && Math.abs(my) > limit;
                this.motionY += myf ? (my > 0 ? limit : -limit) : my;

                double mz = (target.posZ - this.posZ) * 0.068;
                boolean mzf = Math.abs(mz) > 0 && Math.abs(mz) > limit;
                this.motionZ += mzf ? (mz > 0 ? limit : -limit) : mz;
            }
        }

        if (this.ticksExisted % 10 == 1 && TragicConfig.allowMobSounds && !this.worldObj.isRemote)
            this.worldObj.playSoundAtEntity(this, "tragicmc:boss.overlordcore.vulnerable", 1.8F, 1.0F);
    }

    @Override
    protected String getParticleString() {
        return "crit";
    }
}
