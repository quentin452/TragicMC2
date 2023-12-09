package tragicneko.tragicmc.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import tragicneko.tragicmc.entity.miniboss.EntityMegaCryse;
import tragicneko.tragicmc.entity.miniboss.EntityVoxStellarum;
import tragicneko.tragicmc.entity.mob.EntityCryse;
import tragicneko.tragicmc.entity.mob.EntityNorVox;

public class EntityStarShard extends EntityProjectile {

    public EntityStarShard(World par1World) {
        super(par1World);
        this.setSize(0.325F, 0.325F);
    }

    public EntityStarShard(World par1World, EntityLivingBase entity, double par2, double par4, double par6) {
        super(par1World, entity, par2, par4, par6);
    }

    @Override
    public float getMotionFactor() {
        return 0.925F;
    }

    @Override
    protected void onImpact(MovingObjectPosition mop) {
        if (mop.entityHit != null && !this.worldObj.isRemote) {
            if (mop.entityHit instanceof EntityVoxStellarum || mop.entityHit instanceof EntityNorVox
                || mop.entityHit instanceof EntityCryse
                || mop.entityHit instanceof EntityMegaCryse) return;
            mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), 2.0F);
        }

        if (mop != null && !this.worldObj.isRemote) this.setDead();
    }

    @Override
    protected String getParticleString() {
        return "crit";
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.ticksExisted >= 120) {
            this.setDead();
        }
    }

}
