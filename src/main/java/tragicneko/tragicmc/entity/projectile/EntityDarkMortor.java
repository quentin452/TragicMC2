package tragicneko.tragicmc.entity.projectile;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;

public class EntityDarkMortor extends EntityProjectile {

    public EntityDarkMortor(World par1World) {
        super(par1World);
        this.setSize(0.325F, 0.325F);
    }

    public EntityDarkMortor(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5,
        double par7) {
        super(par1World, par2EntityLivingBase, par3, par5, par7);
    }

    @Override
    public float getMotionFactor() {
        return 0.869F;
    }

    @Override
    protected void onImpact(MovingObjectPosition var1) {
        if (var1 != null) {
            if (var1.entityHit != null && var1.entityHit instanceof EntityLivingBase && !this.worldObj.isRemote) {
                ((EntityLivingBase) var1.entityHit)
                    .addPotionEffect(new PotionEffect(Potion.blindness.id, 80 + rand.nextInt(60)));
                if (TragicConfig.allowSubmission) ((EntityLivingBase) var1.entityHit).addPotionEffect(
                    new PotionEffect(TragicPotion.Submission.id, 120 + rand.nextInt(80), rand.nextInt(4)));
            } else if (var1.entityHit != null && !this.worldObj.isRemote) {
                var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), 3.5F);
            }

            if (this.worldObj.isRemote) {
                for (int i = 0; i < 24; i++) {
                    this.worldObj.spawnParticle(
                        "smoke",
                        this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.755D),
                        this.posY + 0.115D + rand.nextDouble(),
                        this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.755D),
                        0.0F,
                        0.155F * this.rand.nextFloat(),
                        0.0F);
                }
            } else {
                List<EntityLivingBase> list = this.worldObj
                    .getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(2.0D, 2.0D, 2.0D));
                EntityLivingBase target;
                for (int i = 0; i < list.size(); i++) {
                    target = list.get(i);
                    if (target != this.shootingEntity) {
                        target.attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), 1.5F);
                        target.addPotionEffect(new PotionEffect(Potion.blindness.id, 80 + rand.nextInt(60)));
                        if (TragicConfig.allowSubmission) target.addPotionEffect(
                            new PotionEffect(TragicPotion.Submission.id, 120 + rand.nextInt(80), rand.nextInt(4)));
                    }
                }
            }

            if (!this.worldObj.isRemote) this.setDead();
            if (!this.worldObj.isRemote)
                this.worldObj.playSoundAtEntity(this, "tragicmc:random.rocketflying", 1.0F, 0.2F);
        }
    }

    @Override
    protected String getParticleString() {
        return "smoke";
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        this.motionY -= 0.0705D;
    }

}
