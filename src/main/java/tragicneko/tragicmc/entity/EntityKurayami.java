package tragicneko.tragicmc.entity;

import static tragicneko.tragicmc.TragicConfig.kurayamiStats;

import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicPotion;

public class EntityKurayami extends EntityGolem {

    public EntityPlayer owner;
    public int armorValue;
    private AttributeModifier mod = new AttributeModifier(
        UUID.fromString("c6334c3a-6cf4-4755-8fe5-d1b713c1f375"),
        "kitsuneSpeedDebuff",
        TragicConfig.modifier[1],
        0);
    private int timeToLive;

    public EntityKurayami(World world) {
        super(world);
        this.setSize(0.745F * 0.825F, 1.745F * 0.825F);
        this.experienceValue = 0;
        this.getNavigator()
            .setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
        this.tasks.addTask(8, new EntityAIWatchTarget(this, 32.0F));
        this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0D, 64.0F));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, IMob.class, 0, true));
        this.isImmuneToFire = true;
        this.timeToLive = 1800;
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    public void setOwner(EntityPlayer player) {
        this.owner = player;
    }

    public void setKurayamiLevel(double d0) {
        this.armorValue = MathHelper.ceiling_double_int(d0 * kurayamiStats[5]);
        this.timeToLive = MathHelper.ceiling_double_int(1800 * d0);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(kurayamiStats[0] * d0);
        this.setHealth(this.getMaxHealth());
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(kurayamiStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(kurayamiStats[2] * d0);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(kurayamiStats[3] * d0);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(kurayamiStats[4] * d0);
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
            .setBaseValue(kurayamiStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(kurayamiStats[1]);
        this.getAttributeMap()
            .registerAttribute(SharedMonsterAttributes.attackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(kurayamiStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(kurayamiStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(kurayamiStats[4]);
    }

    @Override
    public int getTotalArmorValue() {
        return this.armorValue;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(15, Integer.valueOf(0));
        this.dataWatcher.addObject(16, Integer.valueOf(0));
        this.dataWatcher.addObject(17, Integer.valueOf(0));
    }

    public int getHurtTime() {
        return this.dataWatcher.getWatchableObjectInt(15);
    }

    private void setHurtTime(int i) {
        this.dataWatcher.updateObject(15, i);
    }

    public int getAttackTime() {
        return this.dataWatcher.getWatchableObjectInt(16);
    }

    private void setAttackTime(int i) {
        this.dataWatcher.updateObject(16, i);
    }

    public int getFiringTicks() {
        return this.dataWatcher.getWatchableObjectInt(17);
    }

    private void setFiringTicks(int i) {
        this.dataWatcher.updateObject(17, i);
    }

    @Override
    public void onLivingUpdate() {
        if (TragicConfig.allowCorruption && this.isPotionActive(TragicPotion.Corruption))
            this.removePotionEffect(TragicPotion.Corruption.id);

        super.onLivingUpdate();

        if (!this.worldObj.isRemote) {
            if (this.getHurtTime() > 0) this.setHurtTime(this.getHurtTime() - 1);
            if (this.getAttackTime() > 0) this.setAttackTime(this.getAttackTime() - 1);
            if (this.getFiringTicks() > 0) this.setFiringTicks(this.getFiringTicks() - 1);

            if (this.getAttackTarget() == this.owner || this.getAttackTarget() != null && this.getAttackTarget().isDead
                || this.getAttackTarget() instanceof EntityKurayami) this.setAttackTarget(null);
            if (this.isInWater()) this.teleportRandomly();

            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
                .removeModifier(mod);
            if (this.getFiringTicks() > 0) this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
                .applyModifier(mod);

            if (this.getAttackTarget() != null) {
                if (!this.canEntityBeSeen(this.getAttackTarget())) {
                    if (this.rand.nextInt(56) == 0
                        || this.getHurtTime() > 0 && this.getHurtTime() % 20 == 0
                            && this.getDistanceToEntity(this.getAttackTarget()) > 4.0F
                        || this.getDistanceToEntity(this.getAttackTarget()) >= 14.0F && rand.nextInt(4) == 0) {
                        this.teleportToEntity(this.getAttackTarget());
                    }
                }

                if (this.getFiringTicks() > 0 && (this.getDistanceToEntity(this.getAttackTarget()) < 4.0F
                    || this.getDistanceToEntity(this.getAttackTarget()) >= 14.0F)) {
                    this.setFiringTicks(0);
                } else if (this.onGround && this.getDistanceToEntity(this.getAttackTarget()) < 4.0F
                    && rand.nextInt(32) == 0) {
                        double d0 = this.getAttackTarget().posX - this.posX;
                        double d1 = this.getAttackTarget().posZ - this.posZ;
                        double d2 = this.getAttackTarget().posY - this.posY;
                        float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
                        this.motionX = d0 / f2 * 1.05D * 0.500000011920929D + this.motionX * 0.40000000298023224D;
                        this.motionZ = d1 / f2 * 1.05D * 0.500000011920929D + this.motionZ * 0.40000000298023224D;
                        this.motionY = d1 / f2 * 1.1D * 0.200000011920929D + this.motionY * 0.20000000298023224D;
                    }

                if (this.rand.nextInt(56) == 0
                    || this.getHurtTime() > 0 && this.getHurtTime() % 20 == 0
                        && this.getDistanceToEntity(this.getAttackTarget()) > 4.0F
                    || this.getDistanceToEntity(this.getAttackTarget()) >= 14.0F && rand.nextInt(4) == 0) {
                    this.teleportToEntity(this.getAttackTarget());
                }

                if (this.isEntityInRange(this.getAttackTarget(), 6.0F, 16.0F) && rand.nextInt(4) == 0
                    && this.getFiringTicks() == 0
                    && this.canEntityBeSeen(this.getAttackTarget())
                    && this.ticksExisted % 5 == 0) {
                    this.setFiringTicks(40);
                }

                if (this.isEntityInRange(this.getAttackTarget(), 4.0F, 16.0F)
                    && this.canEntityBeSeen(this.getAttackTarget())
                    && this.getFiringTicks() > 0
                    && this.getFiringTicks() % 25 == 0) {
                    double d0 = this.getAttackTarget().posX - this.posX;
                    double d1 = this.getAttackTarget().boundingBox.minY + this.getAttackTarget().height / 2.0F
                        - (this.posY + this.height / 2.0F);
                    double d2 = this.getAttackTarget().posZ - this.posZ;

                    EntityLargeFireball fireball = new EntityLargeFireball(this.worldObj, this, d0, d1, d2);
                    fireball.posY = this.posY + (this.height * 2 / 3);
                    this.worldObj.spawnEntityInWorld(fireball);
                }

                if (this.getDistanceToEntity(this.getAttackTarget()) >= 12.0F && rand.nextInt(36) == 0
                    && this.getFiringTicks() == 0) {
                    boolean flag = this.teleportToEntity(this.getAttackTarget());
                    if (!flag) this.teleportRandomly();
                }
            }

            if (this.owner == null || this.ticksExisted > this.timeToLive) this.setDead();
        }
    }

    public boolean isEntityInRange(Entity entity, float min, float max) {
        float f = this.getDistanceToEntity(entity);
        return f >= min && f <= max;
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        if (this.worldObj.isRemote || TragicConfig.allowStun && this.isPotionActive(TragicPotion.Stun)
            || this.getAttackTime() > 0) return false;

        boolean flag = entity != this.owner && entity.attackEntityFrom(
            DamageSource.causeMobDamage(this),
            (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
                .getAttributeValue());

        if (flag) {
            if (this.getFiringTicks() > 0) this.setFiringTicks(0);
            if (this.getAttackTime() == 0) this.setAttackTime(20);

            if (entity instanceof EntityLivingBase && rand.nextBoolean()) {
                switch (rand.nextInt(8)) {
                    default:
                        ((EntityLivingBase) entity)
                            .addPotionEffect(new PotionEffect(Potion.blindness.id, rand.nextInt(200) + 320));
                        break;
                    case 1:
                        ((EntityLivingBase) entity)
                            .addPotionEffect(new PotionEffect(Potion.digSlowdown.id, rand.nextInt(200) + 320));
                        break;
                    case 2:
                        if (TragicConfig.allowDisorientation) {
                            ((EntityLivingBase) entity).addPotionEffect(
                                new PotionEffect(TragicPotion.Disorientation.id, rand.nextInt(200) + 320));
                        }
                        break;
                }
            }

            if (this.rand.nextInt(4) == 0) entity.setFire(4 + rand.nextInt(8));
        }

        return flag;
    }

    @Override
    public boolean attackEntityFrom(DamageSource src, float dmg) {
        if (this.getHurtTime() > 0) return false;

        if (src.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) src.getEntity();
            boolean flag = player.getCurrentEquippedItem() == null ? false
                : (player.getCurrentEquippedItem()
                    .getItem() == TragicItems.BowOfJustice
                    || player.getCurrentEquippedItem()
                        .getItem() == TragicItems.SwordOfJustice);

            if (src.getEntity() == this.owner || flag && player.capabilities.isCreativeMode)
                return super.attackEntityFrom(src, dmg);
        }

        return super.attackEntityFrom(src, dmg);
    }

    protected boolean teleportRandomly() {
        double d0 = this.posX + (this.rand.nextDouble() - 0.5D) * 24.0D;
        double d1 = this.posY + (this.rand.nextInt(48) - 24);
        double d2 = this.posZ + (this.rand.nextDouble() - 0.5D) * 24.0D;
        return this.teleportTo(d0, d1, d2);
    }

    protected boolean teleportToEntity(Entity par1Entity) {
        Vec3 vec3 = Vec3.createVectorHelper(
            this.posX - par1Entity.posX,
            this.boundingBox.minY + this.height / 2.0F - par1Entity.posY + par1Entity.getEyeHeight(),
            this.posZ - par1Entity.posZ);
        vec3 = vec3.normalize();
        double d0 = 16.0D;
        double d1 = this.posX + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.xCoord * d0;
        double d2 = this.posY + (this.rand.nextInt(16) - 8) - vec3.yCoord * d0;
        double d3 = this.posZ + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.zCoord * d0;
        return this.teleportTo(d1, d2, d3);
    }

    protected boolean teleportTo(double par1, double par3, double par5) {
        double d3 = this.posX;
        double d4 = this.posY;
        double d5 = this.posZ;
        this.posX = par1;
        this.posY = par3;
        this.posZ = par5;
        boolean flag = false;
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.posY);
        int k = MathHelper.floor_double(this.posZ);

        if (this.worldObj.blockExists(i, j, k)) {
            boolean flag1 = false;

            while (!flag1 && j > 0) {
                Block block = this.worldObj.getBlock(i, j - 1, k);

                if (block.getMaterial()
                    .blocksMovement()) {
                    flag1 = true;
                } else {
                    --this.posY;
                    --j;
                }
            }

            if (flag1) {
                this.setPosition(this.posX, this.posY, this.posZ);

                if (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox)
                    .isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox)) {
                    flag = true;
                }
            }
        }

        if (!flag) {
            this.setPosition(d3, d4, d5);
            return false;
        } else {
            short short1 = 128;

            for (int l = 0; l < short1; ++l) {
                double d6 = l / (short1 - 1.0D);
                float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
                float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
                float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
                double d7 = d3 + (this.posX - d3) * d6 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
                double d8 = d4 + (this.posY - d4) * d6 + this.rand.nextDouble() * this.height;
                double d9 = d5 + (this.posZ - d5) * d6 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
                this.worldObj.spawnParticle("flame", d7, d8, d9, f, f1, f2);
            }
            this.worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
            this.playSound(this.getLivingSound() == null ? "mob.endermen.portal" : this.getLivingSound(), 1.0F, 1.0F);
            return true;
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("firingTicks")) this.setFiringTicks(tag.getInteger("firingTicks"));
        if (tag.hasKey("hurtTime")) this.setHurtTime(tag.getInteger("hurtTime"));
        if (tag.hasKey("attackTime")) this.setAttackTime(tag.getInteger("attackTime"));
        if (tag.hasKey("armorValue")) this.armorValue = tag.getInteger("armorValue");
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setInteger("firingTicks", this.getFiringTicks());
        tag.setInteger("hurtTime", this.getHurtTime());
        tag.setInteger("attackTime", this.getAttackTime());
        tag.setInteger("armorValue", this.armorValue);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        if (this.owner == null) this.setOwner(this.worldObj.getClosestPlayer(this.posX, this.posY, this.posZ, 16.0));
        this.setKurayamiLevel(1.0);
        return super.onSpawnWithEgg(data);
    }

    @Override
    public String getLivingSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:boss.kitsune.living" : null;
    }

    @Override
    public String getHurtSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:boss.kitsune.hurt" : super.getHurtSound();
    }

    @Override
    public String getDeathSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:boss.kitsune.hurt" : null;
    }

    @Override
    public float getSoundPitch() {
        return 1.4F + rand.nextFloat() * 0.2F;
    }

    @Override
    public float getSoundVolume() {
        return 0.6F;
    }

    @Override
    public int getTalkInterval() {
        return super.getTalkInterval() / 2;
    }
}
