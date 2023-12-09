package tragicneko.tragicmc.entity.projectile;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityEnergyCharge extends EntityProjectile {

    public boolean isHoming = false;
    private EntityLivingBase target = null;

    public EntityEnergyCharge(World par1World) {
        super(par1World);
        this.setSize(0.325F, 0.325F);
    }

    public EntityEnergyCharge(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5,
        double par7) {
        super(par1World, par2EntityLivingBase, par3, par5, par7);
    }

    @Override
    public float getMotionFactor() {
        return this.isHoming ? 0.793F : 0.629F;
    }

    @Override
    protected void onImpact(MovingObjectPosition var1) {
        if (var1 != null) {
            if (var1.entityHit != null && !this.worldObj.isRemote && var1.entityHit != this.shootingEntity) {
                var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), 3.5F);
            }
        }
    }

    @Override
    protected String getParticleString() {
        return "crit";
    }

    @Override
    public void onUpdate() {
        if (this.target != null && (this.target.getHealth() == 0 || this.target.isDead)) this.target = null;

        super.onUpdate();

        if (this.target == null && this.ticksInAir > 2 && this.isHoming) {
            List<Entity> list = this.worldObj
                .getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(8.0, 8.0, 8.0));

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != this.shootingEntity && list.get(i) instanceof EntityLivingBase
                    && this.shootingEntity != null
                    && this.shootingEntity.canEntityBeSeen(list.get(i))) {
                    if (this.target == null
                        || this.getDistanceToEntity(list.get(i)) < this.getDistanceToEntity(this.target))
                        this.target = (EntityLivingBase) list.get(i);
                }
            }
        }

        if (this.target != null && this.ticksInAir % 5 == 0 && this.ticksInAir > 5) {
            this.motionX = (target.posX - this.posX) * 0.08;
            this.motionY = (target.posY - this.posY) * 0.08;
            this.motionZ = (target.posZ - this.posZ) * 0.08;

            float f2 = MathHelper
                .sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
            this.posX -= this.motionX / f2 * 0.0000000074505806D;
            this.posY -= this.motionY / f2 * 0.00000000074505806D;
            this.posZ -= this.motionZ / f2 * 0.0000000074505806D;
        }
    }

    @Override
    public int getLifespan() {
        return this.isHoming ? 400 : 40;
    }

    public EntityEnergyCharge setHoming() {
        this.isHoming = true;
        return this;
    }

    public void setTarget(EntityLivingBase entity) {
        this.target = entity;
    }
}
