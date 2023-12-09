package tragicneko.tragicmc.entity.boss;

import static tragicneko.tragicmc.TragicConfig.apisStats;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.entity.EntityAIWatchTarget;
import tragicneko.tragicmc.entity.projectile.EntitySolarBomb;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityApis extends TragicBoss {

    public int reflectionBuffer;
    private AttributeModifier mod = new AttributeModifier(
        UUID.fromString("08bd1ef5-8f24-4ee4-9ffa-10cdef76b7ae"),
        "apisStompDebuff",
        TragicConfig.modifier[13],
        0);

    public EntityApis(World par1World) {
        super(par1World);
        this.setSize(1.385F, 3.325F);
        this.stepHeight = 2.0F;
        this.experienceValue = 50;
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
        this.tasks.addTask(8, new EntityAIWatchTarget(this, 48.0F));
        this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 1.0D, 48.0F));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.isImmuneToFire = true;
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return TragicEntities.Beast;
    }

    @Override
    public boolean canRenderOnFire() {
        return false;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(apisStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(apisStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(apisStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(apisStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(apisStats[4]);
    }

    @Override
    public void onDeath(DamageSource src) {
        super.onDeath(src);
        if (src.getEntity() instanceof EntityPlayerMP && TragicConfig.allowAchievements)
            ((EntityPlayerMP) src.getEntity()).triggerAchievement(TragicAchievements.apis);
    }

    @Override
    protected void dropFewItems(boolean flag, int l) {
        super.dropFewItems(flag, l);
        if (!this.worldObj.isRemote && TragicConfig.allowMobStatueDrops
            && rand.nextInt(100) <= TragicConfig.mobStatueDropChance
            && this.getAllowLoot())
            this.capturedDrops.add(
                new EntityItem(
                    this.worldObj,
                    this.posX,
                    this.posY,
                    this.posZ,
                    new ItemStack(TragicItems.MobStatue, 1, 0)));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, Integer.valueOf(0));
        this.dataWatcher.addObject(17, Integer.valueOf(0));
        this.dataWatcher.addObject(18, Integer.valueOf(0));
        this.dataWatcher.addObject(19, Integer.valueOf(0));
        this.dataWatcher.addObject(20, Integer.valueOf(0));
    }

    public int getChargeTicks() {
        return this.dataWatcher.getWatchableObjectInt(16);
    }

    private void setChargeTicks(int i) {
        this.dataWatcher.updateObject(16, i);
    }

    private void decrementChargeTicks() {
        int pow = this.getChargeTicks();
        this.setChargeTicks(--pow);
    }

    public boolean isCharging() {
        return this.getChargeTicks() > 0;
    }

    public int getReflectionTicks() {
        return this.dataWatcher.getWatchableObjectInt(17);
    }

    private void setReflectionTicks(int i) {
        this.dataWatcher.updateObject(17, i);
    }

    private void decrementReflectionTicks() {
        int pow = this.getReflectionTicks();
        this.setReflectionTicks(--pow);
    }

    public boolean isReflecting() {
        return this.getReflectionTicks() > 0;
    }

    public int getStompTicks() {
        return this.dataWatcher.getWatchableObjectInt(18);
    }

    private void setStompTicks(int i) {
        this.dataWatcher.updateObject(18, i);
    }

    private void decrementStompTicks() {
        int pow = this.getStompTicks();
        this.setStompTicks(--pow);
    }

    public boolean isStomping() {
        return this.getStompTicks() > 0;
    }

    public int getAttackTime() {
        return this.dataWatcher.getWatchableObjectInt(19);
    }

    private void setAttackTime(int i) {
        this.dataWatcher.updateObject(19, i);
    }

    private void decrementAttackTime() {
        int pow = this.getAttackTime();
        this.setAttackTime(--pow);
    }

    private void setHurtTime(int i) {
        this.dataWatcher.updateObject(20, i);
    }

    public int getHurtTime() {
        return this.dataWatcher.getWatchableObjectInt(20);
    }

    @Override
    public void onLivingUpdate() {
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .removeModifier(mod);
        if (this.isStomping()) {
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
                .applyModifier(mod);
            this.motionX = this.motionZ = 0.0D;
            this.motionY = -0.2D;
            this.fallDistance = 0.0F;
            this.velocityChanged = false;
        }

        super.onLivingUpdate();

        if (this.worldObj.isRemote) {
            if (this.isStomping()) {
                for (int i = 0; i < 2; i++) {
                    this.worldObj.spawnParticle(
                        "flame",
                        this.posX + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D,
                        this.posY + (rand.nextDouble() * 0.15D),
                        this.posZ + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D,
                        0.0,
                        rand.nextDouble() * 1.2556,
                        0.0);
                }

                if (this.getStompTicks() <= 2) {
                    for (int i = 0; i < 36; i++) {
                        this.worldObj.spawnParticle(
                            "flame",
                            this.posX + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D,
                            this.posY + (rand.nextDouble() * 0.15D),
                            this.posZ + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D,
                            rand.nextDouble() - rand.nextDouble(),
                            rand.nextDouble() * 0.2556,
                            rand.nextDouble() - rand.nextDouble());
                    }
                }
            } else if (this.isCharging()) {
                this.worldObj.spawnParticle(
                    "crit",
                    this.posX + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D,
                    this.posY + (rand.nextDouble() * 0.75D) + 0.45D,
                    this.posZ + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D,
                    0.0,
                    0.0,
                    0.0);
            }
        } else {
            if (this.isStomping()) {
                this.decrementStompTicks();
                if (this.isCharging()) this.setChargeTicks(0);
                if (this.getAttackTime() > 0) this.setAttackTime(0);
                this.setSprinting(false);
            }

            if (this.isCharging()) this.decrementChargeTicks();
            if (this.isReflecting()) {
                this.reflectionBuffer = 160;
                this.decrementReflectionTicks();

                if (this.rand.nextInt(4) == 0 && this.onGround
                    && this.ticksExisted % 2 == 0
                    && this.worldObj.isDaytime()
                    && this.getHealth() <= this.getMaxHealth() / 4) {
                    ArrayList<int[]> list = WorldHelper
                        .getBlocksInCircularRange(worldObj, 0.75, this.posX, this.posY, this.posZ);
                    Block block;
                    int[] coords;

                    for (int i = 0; i < list.size(); i++) {
                        coords = list.get(i);
                        block = this.worldObj.getBlock(coords[0], coords[1], coords[2]);

                        if (block == Blocks.air
                            && World.doesBlockHaveSolidTopSurface(this.worldObj, coords[0], coords[1] - 1, coords[2]))
                            this.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.fire);
                    }
                }
            } else {
                if (this.reflectionBuffer > 0) this.reflectionBuffer--;
            }

            if (this.getAttackTime() > 0) this.decrementAttackTime();
            if (this.getHurtTime() > 0) this.setHurtTime(this.getHurtTime() - 1);

            if (this.getAttackTarget() != null && this.isEntityInRange(this.getAttackTarget(), 2.0F, 8.0F)
                && this.onGround
                && rand.nextInt(32) == 0
                && this.onGround
                && !this.isCharging()
                && !this.isStomping()
                && this.canEntityBeSeen(this.getAttackTarget())
                && TragicConfig.apisChargeAttack) {
                if (rand.nextInt(3) == 0 && TragicConfig.apisExplosiveCharge) {
                    this.worldObj
                        .createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() * 0.5F, false);
                }

                double d0 = this.getAttackTarget().posX - this.posX;
                double d1 = this.getAttackTarget().posZ - this.posZ;
                double d2 = this.getAttackTarget().posY - this.posY;
                float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
                this.motionX = d0 / f2 * 2.5D * 0.600000011920929D + this.motionX * 0.40000000298023224D;
                this.motionZ = d1 / f2 * 2.5D * 0.600000011920929D + this.motionZ * 0.40000000298023224D;
                this.motionY = d1 / f2 * 1.1D * 0.200000011920929D + this.motionY * 0.20000000298023224D;
                this.setChargeTicks(10);
                this.rotationYaw = -((float) Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float) Math.PI;
            }

            if (this.getAttackTarget() != null && this.isEntityInRange(this.getAttackTarget(), 6.0F, 12.0F)
                && this.onGround
                && rand.nextInt(48) == 0
                && !this.isCharging()
                && !this.isStomping()
                && TragicConfig.apisChargeAttack) {
                if (rand.nextInt(3) == 0 && TragicConfig.apisExplosiveCharge) {
                    this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat(), false);
                }

                double d0 = this.getAttackTarget().posX - this.posX;
                double d1 = this.getAttackTarget().posZ - this.posZ;
                double d2 = this.getAttackTarget().posY - this.posY;
                float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
                this.motionX = d0 / f2 * 2.5D * 0.600000011920929D + this.motionX * 0.40000000298023224D;
                this.motionZ = d1 / f2 * 2.5D * 0.600000011920929D + this.motionZ * 0.40000000298023224D;
                this.motionY = d1 / f2 * 1.1D * 0.200000011920929D + this.motionY * 0.20000000298023224D;
                this.setChargeTicks(10);
                this.rotationYaw = -((float) Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float) Math.PI;
            }

            if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) >= 12.0F
                && this.onGround
                && rand.nextInt(48) == 0
                && !this.isCharging()
                && !this.isStomping()
                && TragicConfig.apisChargeAttack) {
                if (rand.nextInt(3) == 0 && TragicConfig.apisExplosiveCharge) {
                    this.worldObj
                        .createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() + 0.5F, false);
                }

                double d0 = this.getAttackTarget().posX - this.posX;
                double d1 = this.getAttackTarget().posZ - this.posZ;
                float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
                this.motionX = d0 / f2 * 3.5D * 0.800000011920929D + this.motionX * 0.60000000298023224D;
                this.motionZ = d1 / f2 * 3.5D * 0.800000011920929D + this.motionZ * 0.60000000298023224D;
                this.motionY = 0.45;
                this.setChargeTicks(10);
                this.rotationYaw = -((float) Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float) Math.PI;
            }

            if (this.getAttackTarget() != null && this.ticksExisted % 3 == 0
                && this.onGround
                && rand.nextInt(48) == 0
                && !this.isCharging()
                && !this.isStomping()
                && this.getDistanceToEntity(this.getAttackTarget()) <= 6.0F
                && TragicConfig.apisSuperStomp) {
                if (TragicConfig.allowMobSounds) this.playSound("tragicmc:boss.apis.roar", 1.8F, 1.0F);
                this.setStompTicks(40);
            }

            if (this.getStompTicks() == 1) {
                List<Entity> list = this.worldObj
                    .getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(6.0D, 6.0D, 6.0D));
                Entity entity;

                for (int i = 0; i < list.size(); i++) {
                    entity = list.get(i);
                    entity.attackEntityFrom(DamageSource.causeMobDamage(this), 8.0F - this.getDistanceToEntity(entity));
                }

                this.worldObj.createExplosion(
                    this,
                    this.posX,
                    this.posY,
                    this.posZ,
                    rand.nextFloat() * 1.225F + 4.0F,
                    this.getMobGriefing());
            }

            if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) >= 10.0F
                && rand.nextInt(8) == 0
                && !this.isCharging()
                && !this.isStomping()
                && TragicConfig.apisSolarBombs) {
                double d0 = this.getAttackTarget().posX - this.posX;
                double d1 = this.getAttackTarget().boundingBox.minY + this.getAttackTarget().height / 3.0F
                    - (this.posY + this.height / 2.0F);
                double d2 = this.getAttackTarget().posZ - this.posZ;

                float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.95F;

                switch (rand.nextInt(6)) {
                    case 0:
                        EntityLargeFireball fireball = new EntityLargeFireball(
                            this.worldObj,
                            this,
                            d0 + this.rand.nextGaussian() * f1,
                            d1,
                            d2 + this.rand.nextGaussian() * f1);
                        fireball.posY = this.posY + this.height;
                        this.worldObj.spawnEntityInWorld(fireball);
                        break;
                    case 1:
                        EntitySolarBomb solarBomb = new EntitySolarBomb(
                            this.worldObj,
                            this,
                            d0 + this.rand.nextGaussian() * f1,
                            d1,
                            d2 + this.rand.nextGaussian() * f1);
                        solarBomb.posY = this.posY + this.height;
                        this.worldObj.spawnEntityInWorld(solarBomb);
                        break;
                    case 2:
                        EntitySolarBomb solarBomb2 = new EntitySolarBomb(
                            this.worldObj,
                            this,
                            d0 + this.rand.nextGaussian() * f1,
                            d1,
                            d2 + this.rand.nextGaussian() * f1);
                        solarBomb2.posY = this.posY + this.height;
                        this.worldObj.spawnEntityInWorld(solarBomb2);
                        break;
                    default:
                        for (int i = 0; i < 3; ++i) {
                            EntitySmallFireball fireball2 = new EntitySmallFireball(
                                this.worldObj,
                                this,
                                d0 + this.rand.nextGaussian() * f1,
                                d1,
                                d2 + this.rand.nextGaussian() * f1);
                            fireball2.posY = this.posY + this.height;
                            this.worldObj.spawnEntityInWorld(fireball2);
                        }
                        break;
                }
            }

            if (this.getAttackTarget() != null && this.reflectionBuffer == 0
                && this.ticksExisted % 20 == 0
                && !this.isReflecting()
                && TragicConfig.apisReflection) {
                this.setReflectionTicks(160);
            }
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (this.worldObj.isRemote || par1DamageSource.isExplosion()) return false;

        if (this.isReflecting() && par1DamageSource.getEntity() != null
            && !(par1DamageSource.getEntity() instanceof EntityApis)) {
            if (par2 > 3.0F) par1DamageSource.getEntity()
                .attackEntityFrom(par1DamageSource, par2 / 2.0F + 1.0F);
            if (TragicConfig.allowMobSounds) this.playSound("tragicmc:boss.apis.reflect", 1.0F, 1.0F);
            return true;
        }

        if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

            if (player.getCurrentEquippedItem() != null) {
                if (!(player.getCurrentEquippedItem()
                    .getItem() instanceof ItemBow)) {
                    player.getCurrentEquippedItem()
                        .damageItem(rand.nextInt(2) + 1, player);
                }
            } else {
                if (this.getHealth() <= this.getMaxHealth() / 2) {
                    player.attackEntityFrom(DamageSource.causeMobDamage(this), 5.0F);
                    player.setFire(rand.nextInt(8) + 4);
                } else {
                    player.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
                }
            }
        }

        if (!par1DamageSource.isProjectile()) par2 *= 0.425F;

        if (this.getHurtTime() == 0 && !this.isCharging() && !this.isStomping()) this.setHurtTime(10);

        return super.attackEntityFrom(par1DamageSource, par2);
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        if (this.worldObj.isRemote || this.isStomping()) return false;

        boolean flag = super.attackEntityAsMob(par1Entity);

        if (flag) {
            if (this.getHealth() <= this.getMaxHealth() / 2) {
                par1Entity.setFire(8 + rand.nextInt(12));
            }

            if (this.isCharging()) {
                par1Entity.motionX *= 2.2000000059604645D;
                par1Entity.motionZ *= 2.2D;
                par1Entity.motionY += 0.56D;
            } else {
                if (this.getAttackTime() == 0) this.setAttackTime(10);
            }

        }
        return flag;
    }

    @Override
    public void collideWithEntity(Entity entity) {
        super.collideWithEntity(entity);

        if (this.worldObj.isRemote) return;
        if (this.ticksExisted % 10 == 0 && entity instanceof EntityLivingBase && this.isReflecting())
            entity.attackEntityFrom(DamageSource.causeMobDamage(this), 1.0F);
    }

    @Override
    public int getTotalArmorValue() {
        return this.worldObj.isDaytime() ? (int) apisStats[5] : MathHelper.floor_double(apisStats[5] / 2);
    }

    @Override
    protected void fall(float par1) {}

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("chargeTicks")) this.setChargeTicks(tag.getInteger("chargeTicks"));
        if (tag.hasKey("reflectionTicks")) this.setReflectionTicks(tag.getInteger("reflectionTicks"));
        if (tag.hasKey("stompTicks")) this.setStompTicks(tag.getInteger("stompTicks"));
        if (tag.hasKey("attackTime")) this.setAttackTime(tag.getInteger("attackTime"));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setInteger("chargeTicks", this.getChargeTicks());
        tag.setInteger("reflectionTicks", this.getReflectionTicks());
        tag.setInteger("stompTicks", this.getStompTicks());
        tag.setInteger("attackTime", this.getAttackTime());
    }

    @Override
    public String getLivingSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:boss.apis.living" : null;
    }

    @Override
    public String getHurtSound() {
        return !this.isReflecting() ? super.getHurtSound() : null;
    }

    @Override
    public String getDeathSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:boss.apis.death" : null;
    }

    @Override
    public float getSoundPitch() {
        return 1.0F;
    }

    @Override
    public float getSoundVolume() {
        return 1.0F;
    }

    @Override
    public int getTalkInterval() {
        return 120;
    }
}
