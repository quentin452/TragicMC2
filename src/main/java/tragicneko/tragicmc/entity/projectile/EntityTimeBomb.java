package tragicneko.tragicmc.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

import tragicneko.tragicmc.entity.EntityTimeDisruption;
import tragicneko.tragicmc.entity.boss.EntityTimeController;
import tragicneko.tragicmc.util.DamageHelper;

public class EntityTimeBomb extends EntityProjectile {

    public EntityTimeBomb(World par1World) {
        super(par1World);
    }

    public EntityTimeBomb(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5,
        double par7) {
        super(par1World, par2EntityLivingBase, par3, par5, par7);
        this.setSize(0.335F, 0.335F);
    }

    @Override
    public float getMotionFactor() {
        return 0.766736F;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.ticksExisted >= 240) {
            this.setDead();
        }
    }

    @Override
    protected String getParticleString() {
        return "crit";
    }

    @Override
    protected void onImpact(MovingObjectPosition var1) {
        if (var1 != null && !this.worldObj.isRemote) {
            if (var1.typeOfHit == MovingObjectType.BLOCK) {
                this.setDead();

                if (rand.nextBoolean()) {
                    EntityTimeDisruption ent = new EntityTimeDisruption(this.worldObj);
                    ent.copyLocationAndAnglesFrom(this);
                    this.worldObj.spawnEntityInWorld(ent);
                }
            } else if (var1.entityHit != null
                && !(var1.entityHit instanceof EntityTimeController || !(var1.entityHit instanceof EntityLivingBase))) {
                    var1.entityHit
                        .attackEntityFrom(DamageHelper.causeModMagicDamageToEntity(this.shootingEntity), 1.0F);
                }
        }
    }

}
