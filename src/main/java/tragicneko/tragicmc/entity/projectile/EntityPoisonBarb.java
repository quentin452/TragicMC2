package tragicneko.tragicmc.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.mob.EntityThorg;
import tragicneko.tragicmc.entity.mob.EntityTox;

public class EntityPoisonBarb extends EntityProjectile {

    public EntityPoisonBarb(World world) {
        super(world);
        this.setSize(0.125F, 0.125F);
    }

    public EntityPoisonBarb(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5,
        double par7) {
        super(par1World, par2EntityLivingBase, par3, par5, par7);
    }

    @Override
    protected void onImpact(MovingObjectPosition mop) {
        if (this.worldObj.isRemote) {
            for (int l = 0; l < 4; ++l) {
                worldObj.spawnParticle(
                    "witchMagic",
                    posX + (rand.nextDouble() - rand.nextDouble() * 0.25D),
                    posY + (rand.nextDouble() - rand.nextDouble() * 0.25D),
                    posZ + (rand.nextDouble() - rand.nextDouble() * 0.25D),
                    0.0D,
                    0.0D,
                    0.0D);
            }
        } else {
            if (mop.entityHit != null && !(mop.entityHit instanceof EntityTox)
                && !(mop.entityHit instanceof EntityThorg)) {
                if (mop.entityHit instanceof EntityLivingBase) {
                    mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), 1.0F);
                    ((EntityLivingBase) mop.entityHit)
                        .addPotionEffect(new PotionEffect(Potion.poison.id, 100 + rand.nextInt(100), 0));

                    if (rand.nextInt(16) == 0 && TragicConfig.allowStun) {
                        ((EntityLivingBase) mop.entityHit).addPotionEffect(
                            new PotionEffect(TragicPotion.Stun.id, 20 + rand.nextInt(40), rand.nextInt(2)));
                    }
                }
            }

            if (mop != null) this.setDead();
        }
    }
}
