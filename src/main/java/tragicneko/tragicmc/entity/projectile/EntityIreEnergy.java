package tragicneko.tragicmc.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import tragicneko.tragicmc.entity.mob.EntityIre;
import tragicneko.tragicmc.util.DamageHelper;

public class EntityIreEnergy extends EntityProjectile {

    public EntityIreEnergy(World world) {
        super(world);
        this.setSize(0.325F, 0.325F);
    }

    public EntityIreEnergy(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5,
        double par7) {
        super(par1World, par2EntityLivingBase, par3, par5, par7);
    }

    @Override
    public float getMotionFactor() {
        return 0.985F;
    }

    @Override
    protected void onImpact(MovingObjectPosition mop) {
        if (this.worldObj.isRemote) return;

        if (mop.entityHit != null) {
            if (mop.entityHit instanceof EntityIreEnergy || mop.entityHit instanceof EntityIre) return;
            if (mop.entityHit instanceof EntityLivingBase) {
                mop.entityHit.attackEntityFrom(
                    DamageHelper.causeModMagicDamageToEntity(this.shootingEntity),
                    MathHelper.clamp_float(EntityIre.ireNetSize, 1.0F, 100.0F));
                if (rand.nextBoolean()) mop.entityHit.setFire(4 + rand.nextInt(8));
            }
        }

        if (mop != null) this.setDead();
    }

    @Override
    protected String getParticleString() {
        return "crit";
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.ticksExisted >= 60) this.setDead();

        if (!this.worldObj.isRemote && this.ticksExisted % 10 == 1) {
            this.worldObj.playSoundAtEntity(this, "tragicmc:random.energyambient", 0.05F, 1.0F);
        }
    }
}
