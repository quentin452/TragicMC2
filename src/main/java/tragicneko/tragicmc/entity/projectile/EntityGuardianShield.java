package tragicneko.tragicmc.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import tragicneko.tragicmc.util.DamageHelper;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityGuardianShield extends EntityProjectile {

    public boolean motionFlag;

    private double xOffset;
    private double zOffset;

    private float maxHealth = 20.0F;
    private float health = maxHealth;

    public EntityGuardianShield(World world) {
        super(world);
        this.motionFlag = false;
        this.preventEntitySpawning = true;
        this.setSize(2.0F, 2.0F);
    }

    public EntityGuardianShield(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5,
        double par7) {
        super(par1World, par2EntityLivingBase, par3, par5, par7);
        if (par2EntityLivingBase != null) this.setOwnerID(par2EntityLivingBase.getEntityId());
    }

    @Override
    protected void entityInit() {
        this.dataWatcher.addObject(4, Integer.valueOf(0));
    }

    private void setOwnerID(int i) {
        this.dataWatcher.updateObject(4, i);
        this.updateOwner();
    }

    public int getOwnerID() {
        return this.dataWatcher.getWatchableObjectInt(4);
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    public void setCurrentItemOrArmor(int i, ItemStack stack) {

    }

    @Override
    public ItemStack[] getLastActiveItems() {
        return new ItemStack[] {};
    }

    public EntityGuardianShield setShieldMaxHealth(float f) {
        this.maxHealth = f;
        this.setHealth(f);
        return this;
    }

    public void setHealth(float f) {
        this.health = f;
    }

    public void setShieldOffsets(double offx, double offz) {
        this.xOffset = offx;
        this.zOffset = offz;
    }

    @Override
    public boolean handleWaterMovement() {
        return false;
    }

    @Override
    public void setAir(int i) {}

    @Override
    public void fall(float f) {}

    @Override
    public void updateFallState(double d0, boolean flag) {}

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);

        if (tag.hasKey("ownerID")) this.setOwnerID(tag.getInteger("ownerID"));
        if (tag.hasKey("motionFlag")) this.motionFlag = tag.getBoolean("motionFlag");
        if (tag.hasKey("offsetX")) this.xOffset = tag.getDouble("offsetX");
        if (tag.hasKey("offsetZ")) this.zOffset = tag.getDouble("offsetZ");
        if (tag.hasKey("health")) this.health = tag.getFloat("health");
        if (tag.hasKey("healthMax")) this.maxHealth = tag.getFloat("maxHealth");
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        if (this.shootingEntity != null) tag.setInteger("ownerID", this.getOwnerID());
        tag.setBoolean("motionFlag", this.motionFlag);
        tag.setDouble("offsetX", this.xOffset);
        tag.setDouble("offsetZ", this.zOffset);
        tag.setFloat("health", this.health);
        tag.setFloat("healthMax", this.maxHealth);
    }

    @Override
    public boolean attackEntityFrom(DamageSource src, float dmg) {
        if (src == DamageSource.inWall) return false;

        if (src.getEntity() != null && src.getEntity() == this.shootingEntity) {
            this.motionFlag = true;
            Vec3 vec3 = this.shootingEntity.getLookVec();

            if (vec3 != null) {
                this.motionX = vec3.xCoord;
                this.motionY = vec3.yCoord;
                this.motionZ = vec3.zCoord;
                this.accelerationX = this.motionX * 0.1D;
                this.accelerationY = this.motionY * 0.1D;
                this.accelerationZ = this.motionZ * 0.1D;
            }

            return super.attackEntityFrom(src, dmg);
        }

        if (!this.worldObj.isRemote) {
            this.health -= dmg;
            if (this.health <= 0F) this.setDead();
        }

        return super.attackEntityFrom(src, dmg);
    }

    @Override
    public void onUpdate() {
        if (this.worldObj.isRemote) {
            for (int l = 0; l < 6; ++l) {
                this.worldObj.spawnParticle(
                    "crit",
                    this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.855D),
                    this.posY + rand.nextDouble() - rand.nextDouble() + 0.235D,
                    this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.855D),
                    0.155F * (this.rand.nextFloat() - this.rand.nextFloat()),
                    0.155F * (this.rand.nextFloat() - this.rand.nextFloat()),
                    0.155F * (this.rand.nextFloat() - this.rand.nextFloat()));
            }
        } else {
            this.updateOwner();
            if (this.shootingEntity == null) this.setDead();

            if (!this.motionFlag && this.shootingEntity != null) {
                this.motionX = this.shootingEntity.motionX;
                this.motionY = this.shootingEntity.motionY;
                this.motionZ = this.shootingEntity.motionZ;

                int i = (this.ticksExisted + this.getEntityId()) % 100;
                double d0 = (Math.sin(i * 0.1)) + 1.6;
                this.setPosition(
                    this.motionX + this.shootingEntity.posX + this.xOffset,
                    this.shootingEntity.posY + d0,
                    this.motionZ + this.shootingEntity.posZ + this.zOffset);
            } else if (this.motionFlag) {
                super.onUpdate();
            }

            if (this.ticksExisted > 600 && !this.worldObj.isRemote) this.setDead();
        }
    }

    private void updateOwner() {
        Entity entity = this.worldObj.getEntityByID(this.getOwnerID());
        if (entity instanceof EntityLivingBase && ((EntityLivingBase) entity).getHealth() > 0F) {
            this.shootingEntity = (EntityLivingBase) entity;
        } else {
            this.shootingEntity = null;
        }
    }

    @Override
    protected void onImpact(MovingObjectPosition var1) {
        if (this.worldObj.isRemote || var1 == null) return;

        if (this.motionFlag) {
            if (var1.entityHit != null && var1.entityHit != this.shootingEntity) {
                if (var1.entityHit instanceof EntityGuardianShield) return;
                var1.entityHit.attackEntityFrom(
                    this.shootingEntity != null ? DamageHelper.causeModMagicDamageToEntity(this.shootingEntity)
                        : DamageSource.magic,
                    this.maxHealth / 10.0F);
            }

            this.worldObj.createExplosion(
                this.shootingEntity != null ? this.shootingEntity : this,
                this.posX,
                this.posY,
                this.posZ,
                (this.maxHealth / 10.0F) * rand.nextFloat(),
                WorldHelper.getMobGriefing(this.worldObj));
            this.setDead();
        }
    }
}
