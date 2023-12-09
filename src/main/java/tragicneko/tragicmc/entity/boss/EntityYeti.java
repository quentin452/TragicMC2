package tragicneko.tragicmc.entity.boss;

import static tragicneko.tragicmc.TragicConfig.empariahStats;

import java.util.List;
import java.util.UUID;

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
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.EntityAIWatchTarget;
import tragicneko.tragicmc.entity.mob.EntityAbomination;
import tragicneko.tragicmc.entity.projectile.EntityIcicle;
import tragicneko.tragicmc.entity.projectile.EntityLargeRock;

public class EntityYeti extends TragicBoss {

    private AttributeModifier mod = new AttributeModifier(
        UUID.fromString("b23cd5f8-df05-4c8d-91f4-b09f33b15049"),
        "yetiSpeedDebuff",
        TragicConfig.modifier[3],
        0);
    private int hitTime = 0;

    public EntityYeti(World par1World) {
        super(par1World);
        this.setSize(1.185F, 2.475F);
        this.stepHeight = 2.0F;
        this.experienceValue = 50;
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(6, new EntityAIWander(this, 0.55D));
        this.tasks.addTask(8, new EntityAIWatchTarget(this, 32.0F));
        this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityGolem.class, 0, true));
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return TragicEntities.Beast;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(empariahStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(empariahStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(empariahStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(empariahStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(empariahStats[4]);
    }

    @Override
    public void onDeath(DamageSource src) {
        super.onDeath(src);
        if (src.getEntity() instanceof EntityPlayerMP && TragicConfig.allowAchievements)
            ((EntityPlayerMP) src.getEntity()).triggerAchievement(TragicAchievements.empariah);
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
                    new ItemStack(TragicItems.MobStatue, 1, 4)));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, Integer.valueOf(0));
        this.dataWatcher.addObject(17, Integer.valueOf(0));
        this.dataWatcher.addObject(18, Integer.valueOf(0));
        this.dataWatcher.addObject(19, Integer.valueOf(0));
        this.dataWatcher.addObject(20, Integer.valueOf(0));
        this.dataWatcher.addObject(21, Integer.valueOf(0));
        this.dataWatcher.addObject(22, Integer.valueOf(0));
    }

    public int getDemeanor() {
        return this.dataWatcher.getWatchableObjectInt(16);
    }

    private void setDemeanor(int i) {
        this.dataWatcher.updateObject(16, i);
    }

    private void incrementDemeanor() {
        int pow = this.getDemeanor();
        if (pow < 20) this.setDemeanor(++pow);
    }

    private void decrementDemeanor() {
        int pow = this.getDemeanor();
        if (pow > -20) this.setDemeanor(--pow);
    }

    public boolean isBeingAggressive() {
        return this.getDemeanor() >= 0;
    }

    public int getFrostTicks() {
        return this.dataWatcher.getWatchableObjectInt(17);
    }

    private void setFrostTicks(int i) {
        this.dataWatcher.updateObject(17, i);
    }

    private void decrementFrostTicks() {
        int pow = this.getFrostTicks();
        this.setFrostTicks(--pow);
    }

    public int getRoarTicks() {
        return this.dataWatcher.getWatchableObjectInt(18);
    }

    private void setRoarTicks(int i) {
        this.dataWatcher.updateObject(18, i);
    }

    private void decrementRoarTicks() {
        int pow = this.getRoarTicks();
        this.setRoarTicks(--pow);
    }

    public boolean isRoaring() {
        return this.getRoarTicks() > 0;
    }

    public int getHurtTime() {
        return this.dataWatcher.getWatchableObjectInt(19);
    }

    private void setHurtTime(int i) {
        this.dataWatcher.updateObject(19, i);
    }

    private void decrementHurtTime() {
        int pow = this.getHurtTime();
        this.setHurtTime(--pow);
    }

    public int getAttackTime() {
        return this.dataWatcher.getWatchableObjectInt(20);
    }

    private void setAttackTime(int i) {
        this.dataWatcher.updateObject(20, i);
    }

    private void decrementAttackTime() {
        int pow = this.getAttackTime();
        this.setAttackTime(--pow);
    }

    public int getChargeTicks() {
        return this.dataWatcher.getWatchableObjectInt(21);
    }

    private void setChargeTicks(int i) {
        this.dataWatcher.updateObject(21, i);
    }

    private void decrementChargeTicks() {
        int pow = this.getChargeTicks();
        this.setChargeTicks(--pow);
    }

    public boolean isCharging() {
        return this.getChargeTicks() > 0;
    }

    public boolean getThrowing() {
        return this.dataWatcher.getWatchableObjectInt(22) == 1;
    }

    private void setThrowing(boolean flag) {
        this.dataWatcher.updateObject(22, flag ? 1 : 0);
    }

    @Override
    public void onLivingUpdate() {
        if (this.getDemeanor() < 0 || this.getFrostTicks() > 0 || this.isRoaring()) {
            this.motionX = this.motionZ = 0.0D;
            this.motionY = -0.1D;
        }

        super.onLivingUpdate();

        if (this.worldObj.isRemote) {
            if (this.isRoaring()) {
                for (int i = 0; i < 2; i++) {
                    this.worldObj.spawnParticle(
                        "snowshovel",
                        this.posX + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D,
                        this.posY + (rand.nextDouble() * 0.15D),
                        this.posZ + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D,
                        0.0,
                        rand.nextDouble() * 1.2556,
                        0.0);
                }

                if (this.getRoarTicks() <= 18) {
                    for (int i = 0; i < 36; i++) {
                        this.worldObj.spawnParticle(
                            "snowshovel",
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
            return;
        }

        if (this.ticksExisted % 60 == 0 && this.getHealth() < this.getMaxHealth() && TragicConfig.empariahRegeneration)
            this.heal(3.0F);

        hitTime++;
        if (this.getFrostTicks() > 0) this.decrementFrostTicks();
        if (this.getRoarTicks() > 0) this.decrementRoarTicks();
        if (this.getHurtTime() > 0) this.decrementHurtTime();
        if (this.getAttackTime() > 0) this.decrementAttackTime();
        if (this.getChargeTicks() > 0) this.decrementChargeTicks();

        if (this.getAttackTarget() == null) {
            this.setDemeanor(0);
        } else {
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
                .removeModifier(mod);

            double d0 = this.getAttackTarget().posX - this.posX;
            double d1 = this.getAttackTarget().posZ - this.posZ;
            float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);

            if (this.isBeingAggressive()) {
                if (this.getThrowing()) this.setThrowing(false);
                if (this.getDistanceToEntity(this.getAttackTarget()) > 6.0F && this.onGround
                    && rand.nextInt(48) == 0
                    && this.getFrostTicks() == 0
                    && this.getRoarTicks() == 0
                    && !this.isCharging()
                    && TragicConfig.empariahCharge) {
                    double d2 = this.getAttackTarget().posY - this.posY;
                    f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);

                    this.motionX = d0 / f2 * 2.15D * 0.800000011920929D + this.motionX * 0.80000000298023224D;
                    this.motionZ = d1 / f2 * 2.15D * 0.800000011920929D + this.motionZ * 0.80000000298023224D;
                    this.motionY = d2 / f2 * 1.95D * 0.400000011920929D + this.motionY * 0.80000000298023224D;
                    if (TragicConfig.empariahDemeanor) this.incrementDemeanor();
                    this.setChargeTicks(5);
                    this.rotationYaw = -((float) Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float) Math.PI;
                }

                if (this.getAttackTarget() != null && this.isEntityInRange(this.getAttackTarget(), 5.0F, 14.0F)
                    && this.onGround
                    && rand.nextInt(128) == 0
                    && this.getFrostTicks() == 0
                    && !this.isRoaring()
                    && !this.isCharging()
                    && this.ticksExisted % 3 == 0
                    && this.canEntityBeSeen(this.getAttackTarget())
                    && TragicConfig.empariahFrostBreath) {
                    this.setFrostTicks(100);
                }

                if (this.getDistanceToEntity(this.getAttackTarget()) > 5.0F && this.onGround
                    && rand.nextInt(256) == 0
                    && this.getFrostTicks() == 0
                    && !this.isRoaring()
                    && this.getHealth() < this.getMaxHealth() / 2
                    && !this.isCharging()
                    && TragicConfig.empariahRoar) {
                    this.setRoarTicks(20);
                    if (TragicConfig.allowMobSounds) this.playSound("tragicmc:boss.empariah.roar", 1.6F, 1.0F);
                }

                if (this.getFrostTicks() > 5 && !this.isCharging()) {
                    d0 = this.getAttackTarget().posX - this.posX;
                    d1 = this.getAttackTarget().boundingBox.minY + this.getAttackTarget().height / 3.0F
                        - (this.posY + this.height / 2.0F);
                    double d2 = this.getAttackTarget().posZ - this.posZ;
                    float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.265F;

                    for (byte i = 0; i < 2; i++) {
                        EntityIcicle fireball = new EntityIcicle(
                            this.worldObj,
                            this,
                            d0 + this.rand.nextGaussian() * f1,
                            d1,
                            d2 + this.rand.nextGaussian() * f1);
                        fireball.posY = this.posY + (this.height * 2 / 3);
                        fireball.posX += d0 * 0.155D;
                        fireball.posZ += d2 * 0.155D;
                        this.worldObj.spawnEntityInWorld(fireball);
                    }

                    if (TragicConfig.allowMobSounds && this.ticksExisted % 5 == 0)
                        this.playSound("tragicmc:boss.empariah.frost", 1.0F, 1.0F);
                    if (TragicConfig.empariahDemeanor) this.incrementDemeanor();
                }

                if (rand.nextBoolean() && this.ticksExisted % 10 == 0
                    && TragicConfig.empariahDemeanor
                    && (this.getDistanceToEntity(this.getAttackTarget()) >= 8.0F
                        || !this.canEntityBeSeen(this.getAttackTarget())))
                    this.decrementDemeanor();

                if (this.isRoaring() || this.getFrostTicks() > 0)
                    this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
                        .applyModifier(mod);
            } else {
                this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
                    .applyModifier(mod);

                if (this.getFrostTicks() > 0) this.setFrostTicks(0);
                if (TragicConfig.empariahDemeanor && (rand.nextInt(528) == 0 || this.getAttackTarget()
                    .getHealth()
                    <= this.getAttackTarget()
                        .getMaxHealth() / 3
                    || this.getDistanceToEntity(this.getAttackTarget()) <= 4.0F)) this.setDemeanor(12);

                if (this.getThrowing() && this.ticksExisted % 60 == 0) {
                    d0 = this.getAttackTarget().posX - this.posX;
                    d1 = this.getAttackTarget().boundingBox.minY + this.getAttackTarget().height / 3.0F
                        - (this.posY + this.height / 2.0F);
                    double d2 = this.getAttackTarget().posZ - this.posZ;
                    float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.625F;

                    EntityLargeRock fireball = new EntityLargeRock(
                        this.worldObj,
                        this,
                        d0 + this.rand.nextGaussian() * f1,
                        d1,
                        d2 + this.rand.nextGaussian() * f1);
                    fireball.posY = this.posY + this.height;
                    fireball.posX += d0 * 0.15D;
                    fireball.posZ += d2 * 0.15D;
                    fireball.motionY += 0.12D * f1;
                    this.worldObj.spawnEntityInWorld(fireball);
                    this.setThrowing(false);
                }

                if (this.ticksExisted % 10 == 0
                    && (this.getDistanceToEntity(this.getAttackTarget()) > 8.0F
                        || !this.canEntityBeSeen(this.getAttackTarget()))
                    && !this.isRoaring()
                    && !this.isCharging()
                    && rand.nextBoolean()) {
                    if (TragicConfig.empariahDemeanor) this.decrementDemeanor();
                    if (!this.getThrowing() && TragicConfig.empariahRockThrowing) this.setThrowing(true);
                }

                if (TragicConfig.empariahRoar && this.getDistanceToEntity(this.getAttackTarget()) > 3.0F
                    && this.onGround
                    && this.getFrostTicks() == 0
                    && !this.isRoaring()
                    && !this.getThrowing()
                    && !this.isCharging()
                    && (this.getHealth() < this.getMaxHealth() / 2 && this.getDemeanor() < 0 && rand.nextInt(128) == 0
                        || this.hitTime >= 400
                        || rand.nextInt(128) == 0 && this.ticksExisted % 45 == 0)) {
                    this.setRoarTicks(20);
                    if (TragicConfig.allowMobSounds) this.playSound("tragicmc:boss.empariah.roar", 1.6F, 1.0F);
                }
            }

            if (this.getRoarTicks() == 18 && TragicConfig.empariahRoar) {
                if (TragicConfig.allowAbomination && TragicConfig.empariahSummonAbomination) this.attemptToSummonHelp();
                List<Entity> list = this.worldObj
                    .getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(12.0D, 12.0D, 12.0D));
                EntityLivingBase entity;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) instanceof EntityLivingBase) {
                        entity = (EntityLivingBase) list.get(i);
                        if (!(entity instanceof EntityAbomination) && !(entity instanceof EntityYeti)) {
                            entity.attackEntityFrom(DamageSource.causeMobDamage(this), 1.0F);
                            if (TragicConfig.allowStun)
                                entity.addPotionEffect(new PotionEffect(TragicPotion.Stun.id, 30));
                        } else {
                            entity.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 100));
                        }
                    }
                }

                if (TragicConfig.empariahDemeanor) {
                    this.decrementDemeanor();
                    if (this.getDemeanor() < 0) {
                        this.setDemeanor(12);
                        this.hitTime = 200;
                    }
                }

                if (TragicConfig.empariahCallHelp && TragicConfig.allowAbomination) {
                    list = this.worldObj
                        .getEntitiesWithinAABB(EntityAbomination.class, this.boundingBox.expand(32.0, 32.0, 32.0));
                    EntityAbomination mob;

                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i) instanceof EntityAbomination) {
                            mob = (EntityAbomination) list.get(i);

                            if (mob instanceof EntityAbomination) {
                                mob.targetTasks.addTask(
                                    3,
                                    new EntityAINearestAttackableTarget(
                                        mob,
                                        this.getAttackTarget()
                                            .getClass(),
                                        0,
                                        true));
                                mob.setTarget(this.getAttackTarget());
                            }
                        }
                    }
                }
            }
        }
    }

    private void attemptToSummonHelp() {
        if (!TragicConfig.empariahSummonAbomination) return;
        List<Entity> list = this.worldObj
            .getEntitiesWithinAABB(EntityAbomination.class, boundingBox.expand(32.0, 32.0, 32.0));

        if (list.size() >= 4 || this.getAttackTarget() == null) {
            if (TragicConfig.empariahDemeanor) this.decrementDemeanor();
            return;
        }

        EntityAbomination clone = new EntityAbomination(this.worldObj);
        clone.copyLocationAndAnglesFrom(this);
        EntityLivingBase entitylivingbase = this.getAttackTarget();

        for (int y1 = -4; y1 < 5; y1++) {
            for (int z1 = -3; z1 < 4; z1++) {
                for (int x1 = -3; x1 < 4; x1++) {
                    if (World.doesBlockHaveSolidTopSurface(
                        this.worldObj,
                        (int) this.posX + x1,
                        (int) this.posY + y1 - 1,
                        (int) this.posZ + z1) && rand.nextBoolean()) {
                        clone.setPosition(this.posX + x1, this.posY + y1, this.posZ + z1);

                        if (this.worldObj.checkNoEntityCollision(clone.boundingBox)
                            && this.worldObj.getCollidingBoundingBoxes(clone, clone.boundingBox)
                                .isEmpty()
                            && !this.worldObj.isAnyLiquid(clone.boundingBox)) {
                            this.worldObj.spawnEntityInWorld(clone);
                            clone.onSpawnWithEgg(null);
                            if (entitylivingbase != null) clone.setTarget(entitylivingbase);
                            return;
                        }
                    }
                }
            }
        }

    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (this.worldObj.isRemote) return false;

        this.trackHitType(par1DamageSource.damageType);

        if (par1DamageSource.isProjectile()) par2 *= 0.725F;

        boolean result = super.attackEntityFrom(par1DamageSource, par2);

        if (result) {
            this.hitTime = 0;
            if (this.getFrostTicks() > 0) this.setFrostTicks(0);
            if (this.getHurtTime() == 0) this.setHurtTime(10);
            if (par2 >= 10.0F && TragicConfig.empariahDemeanor) this.incrementDemeanor();

            if (!this.isBeingAggressive() && rand.nextInt(4) == 0 && TragicConfig.empariahDemeanor)
                this.setDemeanor(12);

            if (this.getAttackTarget() != null && TragicConfig.empariahCallHelp) {
                List<Entity> list = this.worldObj
                    .getEntitiesWithinAABB(EntityAbomination.class, this.boundingBox.expand(32.0, 32.0, 32.0));
                EntityAbomination mob;

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) instanceof EntityAbomination) {
                        mob = (EntityAbomination) list.get(i);

                        if (mob instanceof EntityAbomination) {
                            mob.targetTasks.addTask(
                                3,
                                new EntityAINearestAttackableTarget(
                                    mob,
                                    this.getAttackTarget()
                                        .getClass(),
                                    0,
                                    true));
                            mob.setTarget(this.getAttackTarget());
                        }
                    }
                }
            }
        }

        return result;
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        if (this.worldObj.isRemote) return false;

        boolean flag = super.attackEntityAsMob(par1Entity);

        if (flag) {
            if (this.isCharging() || this.isRoaring()) {
                par1Entity.motionX *= 1.4D;
                par1Entity.motionZ *= 1.4D;
                par1Entity.motionY += 0.65D;
            }

            if (TragicConfig.empariahDemeanor) this.incrementDemeanor();
            if (this.getAttackTime() == 0 && !this.isCharging()) this.setAttackTime(10);
            if (TragicConfig.allowMobSounds)
                this.worldObj.playSoundAtEntity(this, "tragicmc:boss.empariah.scratch", 1.8F, 1.9F);
        }

        return flag;
    }

    @Override
    public int getTotalArmorValue() {
        return this.isBeingAggressive() || this.isCharging() ? (int) empariahStats[5]
            : MathHelper.floor_double(empariahStats[5] / 2);
    }

    @Override
    public void fall(float par1) {}

    private void trackHitType(String damageType) {
        if (!TragicConfig.empariahDemeanor) return;

        String hitType = null;
        if (damageType.equals("arrow")) {
            hitType = "projectile";
        } else if (damageType.equals("fireball")) {
            hitType = "projectile";
        } else if (damageType.equals("indirectMagic")) {
            hitType = "normal";
        } else if (damageType.equals("player")) {
            hitType = "normal";
        } else if (damageType.equals("generic")) {
            hitType = "normal";
        } else if (damageType.equals("mob")) {
            hitType = "normal";
        }

        if (hitType == null) {
            return;
        } else if (hitType == "projectile") {
            this.incrementDemeanor();
        }

        this.incrementDemeanor();
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("demeanor")) this.setDemeanor(tag.getInteger("demeanor"));
        if (tag.hasKey("roarTicks")) this.setRoarTicks(tag.getInteger("roarTicks"));
        if (tag.hasKey("frostTicks")) this.setFrostTicks(tag.getInteger("frostTicks"));
        if (tag.hasKey("hurtTime")) this.setHurtTime(tag.getInteger("hurtTime"));
        if (tag.hasKey("attackTime")) this.setAttackTime(tag.getInteger("attackTime"));
        if (tag.hasKey("chargeTicks")) this.setChargeTicks(tag.getInteger("chargeTicks"));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setInteger("demeanor", this.getDemeanor());
        tag.setInteger("roarTicks", this.getRoarTicks());
        tag.setInteger("frostTicks", this.getFrostTicks());
        tag.setInteger("hurtTime", this.getHurtTime());
        tag.setInteger("attackTime", this.getAttackTime());
        tag.setInteger("chargeTicks", this.getChargeTicks());
    }

    @Override
    public String getLivingSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:boss.empariah.living" : null;
    }

    @Override
    public String getHurtSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:boss.empariah.hurt" : super.getHurtSound();
    }

    @Override
    public String getDeathSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:boss.empariah.roar" : null;
    }

    @Override
    public float getSoundPitch() {
        return 1.0F;
    }

    @Override
    public float getSoundVolume() {
        return 0.6F;
    }

    @Override
    public int getTalkInterval() {
        return 240;
    }
}
