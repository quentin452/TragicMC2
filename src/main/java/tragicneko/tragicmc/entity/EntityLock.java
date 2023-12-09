package tragicneko.tragicmc.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.mob.EntityLockbot;

public class EntityLock extends Entity {

    private EntityLockbot lockOwner;

    public EntityLock(World world) {
        super(world);
        this.setSize(0.15F, 0.15F);
    }

    public EntityLock(World world, EntityLockbot owner, EntityLivingBase prisoner) {
        this(world);
        this.lockOwner = owner;
        this.mountEntity(prisoner);
    }

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();

        if (!this.worldObj.isRemote) {
            if (this.ridingEntity instanceof EntityLivingBase && this.lockOwner != null
                && this.getDistanceToEntity(this.lockOwner) >= this.lockOwner.getLockdownDistance()) {
                this.ridingEntity.motionX = this.ridingEntity.motionZ = this.ridingEntity.motionY = 0D;
                if (TragicConfig.allowLeadFoot) ((EntityLivingBase) this.ridingEntity)
                    .addPotionEffect(new PotionEffect(TragicPotion.LeadFoot.id, 10));
                ((EntityLivingBase) this.ridingEntity)
                    .addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 10, 10));
            }
            if (this.lockOwner == null || this.lockOwner.isDead || this.ridingEntity == null) this.setDead();
        }
    }

    @Override
    protected void entityInit() {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {

    }

}
