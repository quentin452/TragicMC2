package tragicneko.tragicmc.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import tragicneko.tragicmc.entity.mob.EntityGragul;
import tragicneko.tragicmc.util.DamageHelper;

public class EntitySpiritCast extends EntityProjectile {

    public EntitySpiritCast(World par1World) {
        super(par1World);
        this.setSize(0.465F, 0.465F);
    }

    public EntitySpiritCast(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5,
        double par7) {
        super(par1World, par2EntityLivingBase, par3, par5, par7);
    }

    @Override
    public float getMotionFactor() {
        return 0.812F;
    }

    @Override
    protected void onImpact(MovingObjectPosition mop) {
        if (mop.entityHit != null && this.shootingEntity != null) {
            if (mop.entityHit instanceof EntityLivingBase && !(mop.entityHit instanceof EntityGragul)) {
                mop.entityHit.attackEntityFrom(DamageHelper.causeSuffocationDamageFromMob(this.shootingEntity), 2.5F);
            }
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.ticksExisted >= 60) this.setDead();

        if (!this.worldObj.isRemote && this.ticksExisted % 5 == 1) {
            this.worldObj.playSoundAtEntity(this, "tragicmc:random.rocketflying", 1.0F, 1.7F);
        }
    }

    @Override
    protected String getParticleString() {
        return "crit";
    }

}
