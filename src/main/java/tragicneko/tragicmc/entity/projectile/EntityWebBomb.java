package tragicneko.tragicmc.entity.projectile;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import tragicneko.tragicmc.util.WorldHelper;

public class EntityWebBomb extends EntityProjectile {

    public EntityWebBomb(World par1World) {
        super(par1World);
    }

    public EntityWebBomb(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5,
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
            if (var1.entityHit != null && !this.worldObj.isRemote) {
                var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), 3.5F);
            }

            if (this.worldObj.isRemote) {
                for (int i = 0; i < 12; i++) {
                    this.worldObj.spawnParticle(
                        "crit",
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
                    }
                }

                ArrayList<int[]> list2 = WorldHelper
                    .getBlocksInSphericalRange(this.worldObj, 0.75D, this.posX, this.posY, this.posZ);
                int[] coords;

                for (int i = 0; i < list2.size(); i++) {
                    coords = list2.get(i);
                    if (this.worldObj.getBlock(coords[0], coords[1], coords[2]) == Blocks.air)
                        this.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.web);
                }

                this.setDead();
            }
        }
    }

    @Override
    protected String getParticleString() {
        return "crit";
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        this.motionY -= 0.0705D;
    }

}
