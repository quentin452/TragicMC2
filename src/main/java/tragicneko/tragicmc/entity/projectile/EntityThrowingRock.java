package tragicneko.tragicmc.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityThrowingRock extends EntityThrowable {

    public EntityThrowingRock(World world) {
        super(world);
    }

    public EntityThrowingRock(World world, EntityLivingBase entity, boolean flag) {
        super(world, entity);
        if (flag) this.setLavaRock();
    }

    public EntityThrowingRock(World world, double par2, double par4, double par6, boolean flag) {
        super(world, par2, par4, par6);
        if (flag) this.setLavaRock();
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, Integer.valueOf(0));
    }

    public void setLavaRock() {
        this.dataWatcher.updateObject(16, 1);
    }

    @Override
    protected float getGravityVelocity() {
        return 0.0375F;
    }

    @Override
    protected void onImpact(MovingObjectPosition mop) {
        if (this.worldObj.isRemote) {
            for (int l = 0; l < 4; ++l) {
                worldObj.spawnParticle("crit", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
            }
        } else {
            if (mop.entityHit != null) {
                float f = 1.0F;

                if (this.isLavaRock()) {
                    f = 3.0F;
                    mop.entityHit.setFire(rand.nextInt(6) + 2);
                }
                mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), f);
            }

            if (mop != null) this.setDead();
        }
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer player) {

    }

    public boolean isLavaRock() {
        return this.dataWatcher.getWatchableObjectInt(16) == 1;
    }
}
