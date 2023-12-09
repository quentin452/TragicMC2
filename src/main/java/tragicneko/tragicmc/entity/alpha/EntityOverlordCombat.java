package tragicneko.tragicmc.entity.alpha;

import static tragicneko.tragicmc.TragicConfig.overlordCombatStats;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.EntityAIWatchTarget;
import tragicneko.tragicmc.entity.EntityDimensionalAnomaly;
import tragicneko.tragicmc.entity.boss.TragicBoss;
import tragicneko.tragicmc.entity.mob.EntityHunter;
import tragicneko.tragicmc.entity.mob.EntityNanoSwarm;
import tragicneko.tragicmc.util.DamageHelper;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityOverlordCombat extends TragicBoss {

    private boolean hasLeaped;
    private int aggregate = 1;
    private int unstableBuffer;
    private int reflectionBuffer;

    public static final IEntitySelector selec = new IEntitySelector() {

        @Override
        public boolean isEntityApplicable(Entity entity) {
            return entity instanceof EntityLivingBase
                && ((EntityLivingBase) entity).getCreatureAttribute() != TragicEntities.Synapse;
        }
    };

    public EntityOverlordCombat(World par1World) {
        super(par1World);
        this.setSize(4.385F, 5.325F);
        this.stepHeight = 2.0F;
        this.experienceValue = 75;
        this.getNavigator()
            .setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
        this.tasks.addTask(8, new EntityAIWatchTarget(this, 64.0F));
        this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 1.0D, 48.0F));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
        this.targetTasks
            .addTask(3, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, true, false, selec));
        this.isImmuneToFire = true;
    }

    @Override
    public void setAir(int i) {}

    @Override
    protected void despawnEntity() {}

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    public void fall(float f) {
        if (!this.worldObj.isRemote && this.hasLeaped) {
            this.hasLeaped = false;
            List<Entity> list = this.worldObj
                .getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(8.0, 8.0, 8.0));
            for (Entity e : list) {
                if (e instanceof EntityLivingBase) {
                    double d1 = e.posX - this.posX;
                    double d2 = e.posZ - this.posZ;
                    double d3 = e.posY - this.posY;
                    float f2 = MathHelper.sqrt_double(d1 * d1 + d2 * d2 + d3 * d3);
                    double d4 = 0.25D;

                    e.motionX = d1 / f2 * d4 * 26.800000011920929D + e.motionX * 20.70000000298023224D;
                    e.motionZ = d2 / f2 * d4 * 26.800000011920929D + e.motionZ * 20.70000000298023224D;
                    e.motionY = rand.nextDouble() + rand.nextDouble() * 2.700000011920929D
                        + e.motionZ * 1.70000000298023224D;
                    e.velocityChanged = true;
                    if (TragicConfig.allowHacked
                        && ((EntityLivingBase) e).getCreatureAttribute() != TragicEntities.Synapse)
                        ((EntityLivingBase) e)
                            .addPotionEffect(new PotionEffect(TragicPotion.Hacked.id, 40 + rand.nextInt(20), 0));
                }
            }

            if (TragicConfig.allowMobSounds)
                this.worldObj.playSoundAtEntity(this, "tragicmc:boss.overlordcombat.wam", 1.8F, 1.0F);
        }

        if (this.worldObj.isRemote && f > 16.0F) {
            double dr, dr2, dr3, x, y, z;
            float f0 = 0, f1 = 0, f2 = 0;

            for (int i = 0; i < 22; i++) {
                dr = rand.nextDouble() - rand.nextDouble();
                dr2 = rand.nextDouble() - rand.nextDouble();
                dr3 = rand.nextDouble() - rand.nextDouble();
                x = dr * 3.25 + this.posX;
                y = dr2 * 1.25 + this.posY + this.height / 2.0D;
                z = dr3 * 3.25 + this.posZ;
                this.worldObj.spawnParticle("cloud", x, y, z, f0, f1, f2);
            }
        }
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return TragicEntities.Synapse;
    }

    @Override
    public void onDeath(DamageSource par1DamageSource) {
        super.onDeath(par1DamageSource);
        if (!this.worldObj.isRemote) {
            EntityOverlordCore core = new EntityOverlordCore(this.worldObj);
            core.setPosition(this.posX, this.posY, this.posZ);
            core.setStartTransform();
            this.worldObj.spawnEntityInWorld(core);

            List<Entity> list = this.worldObj
                .getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(16.0, 16.0, 16.0));
            for (Entity e : list) {
                if (e instanceof EntityLivingBase
                    && ((EntityLivingBase) e).getCreatureAttribute() == TragicEntities.Synapse
                    && e != core) {
                    e.setDead();
                }
            }

            if (par1DamageSource.getEntity() instanceof EntityPlayerMP)
                ((EntityPlayerMP) par1DamageSource.getEntity()).triggerAchievement(TragicAchievements.overlord3);
            List<EntityPlayerMP> list2 = this.worldObj
                .getEntitiesWithinAABB(EntityPlayerMP.class, this.boundingBox.expand(24.0, 24.0, 24.0));
            if (!list2.isEmpty()) {
                for (EntityPlayerMP mp : list2) {
                    mp.triggerAchievement(TragicAchievements.overlord3);
                }
            }
        }
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(overlordCombatStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(overlordCombatStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(overlordCombatStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(overlordCombatStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(overlordCombatStats[4]);
    }

    @Override
    public int getTotalArmorValue() {
        return (int) overlordCombatStats[5];
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, Integer.valueOf(0)); // unstable ticks
        this.dataWatcher.addObject(17, Integer.valueOf(0)); // hurt time
        this.dataWatcher.addObject(18, Integer.valueOf(0)); // attack time
        this.dataWatcher.addObject(19, Integer.valueOf(0)); // leap ticks
        this.dataWatcher.addObject(20, Integer.valueOf(0)); // charge ticks
        this.dataWatcher.addObject(21, Integer.valueOf(0)); // grapple ticks
        this.dataWatcher.addObject(22, Integer.valueOf(0)); // reflection ticks
        this.dataWatcher.addObject(23, Integer.valueOf(0)); // transformation ticks
    }

    private void setUnstableTicks(int i) {
        this.dataWatcher.updateObject(16, i);
    }

    public int getUnstableTicks() {
        return this.dataWatcher.getWatchableObjectInt(16);
    }

    private void setHurtTime(int i) {
        this.dataWatcher.updateObject(17, i);
    }

    public int getHurtTime() {
        return this.dataWatcher.getWatchableObjectInt(17);
    }

    private void setAttackTime(int i) {
        this.dataWatcher.updateObject(18, i);
    }

    public int getAttackTime() {
        return this.dataWatcher.getWatchableObjectInt(18);
    }

    private void setLeapTicks(int i) {
        this.dataWatcher.updateObject(19, i);
    }

    public int getLeapTicks() {
        return this.dataWatcher.getWatchableObjectInt(19);
    }

    private void setChargeTicks(int i) {
        this.dataWatcher.updateObject(20, i);
    }

    public int getChargeTicks() {
        return this.dataWatcher.getWatchableObjectInt(20);
    }

    private void setGrappleTicks(int i) {
        this.dataWatcher.updateObject(21, i);
    }

    public int getGrappleTicks() {
        return this.dataWatcher.getWatchableObjectInt(21);
    }

    private void setReflectionTicks(int i) {
        this.dataWatcher.updateObject(22, i);
    }

    public int getReflectionTicks() {
        return this.dataWatcher.getWatchableObjectInt(22);
    }

    private void setTransformationTicks(int i) {
        this.dataWatcher.updateObject(23, i);
    }

    public int getTransformationTicks() {
        return this.dataWatcher.getWatchableObjectInt(23);
    }

    public EntityOverlordCombat setTransforming() {
        this.setTransformationTicks(120);
        return this;
    }

    private boolean canUseAbility() {
        return this.getUnstableTicks() == 0 && !this.hasLeaped
            && this.getLeapTicks() == 0
            && this.getChargeTicks() == 0
            && this.getGrappleTicks() == 0
            && this.getReflectionTicks() == 0
            && this.getTransformationTicks() == 0
            && this.unstableBuffer == 0
            && this.reflectionBuffer == 0;
    }

    @Override
    public void onLivingUpdate() {
        if (this.getGrappleTicks() > 0 || this.getLeapTicks() > 20) {
            this.motionX = this.motionZ = 0D;
            this.motionY = -1D;
        }

        if (this.getReflectionTicks() > 0 || this.getTransformationTicks() > 0) {
            this.motionX = this.motionZ = this.motionY = 0D;
        }

        if (this.getUnstableTicks() > 0) {
            this.motionY = -1D;
            this.motionX *= 0.25D;
            this.motionZ *= 0.25D;
        }

        super.onLivingUpdate();

        if (this.worldObj.isRemote) {
            double x;
            double y;
            double z;
            double dr;
            double dr2;
            double dr3;
            float f = 0F;
            float f1 = 0F;
            float f2 = 0F;

            if (this.getTransformationTicks() > 0) {
                return;
            }

            if (this.getGrappleTicks() > 0) {
                for (int i = 0; i < 22; i++) {
                    dr = rand.nextDouble() - rand.nextDouble();
                    dr2 = rand.nextDouble() - rand.nextDouble();
                    dr3 = rand.nextDouble() - rand.nextDouble();
                    x = dr * 2.25 + this.posX;
                    y = dr2 * 2.25 + this.posY + this.height / 2.0D;
                    z = dr3 * 2.25 + this.posZ;
                    this.worldObj.spawnParticle("reddust", x, y, z, f, f1, f2);
                }
            }

            if (this.getLeapTicks() >= 10 && !this.onGround) {
                for (int i = 0; i < 22; i++) {
                    dr = rand.nextDouble() - rand.nextDouble();
                    dr2 = rand.nextDouble() - rand.nextDouble();
                    dr3 = rand.nextDouble() - rand.nextDouble();
                    x = dr * 2.25 + this.posX;
                    y = dr2 * 2.25 + this.posY + this.height / 2.0D;
                    z = dr3 * 2.25 + this.posZ;
                    this.worldObj.spawnParticle("cloud", x, y, z, f, f1, f2);
                }
            }

            if (this.getChargeTicks() > 0) {
                for (int i = 0; i < 32; i++) {
                    dr = rand.nextDouble() - rand.nextDouble();
                    dr2 = rand.nextDouble() - rand.nextDouble();
                    dr3 = rand.nextDouble() - rand.nextDouble();
                    x = dr * 3.25 + this.posX;
                    y = dr2 * 1.25 + this.posY + this.height / 2.0D;
                    z = dr3 * 3.25 + this.posZ;
                    this.worldObj.spawnParticle("crit", x, y, z, f, f1, f2);
                }
            }

            if (this.getUnstableTicks() > 0) {
                for (int i = 0; i < 44; i++) {
                    dr = rand.nextDouble() - rand.nextDouble();
                    dr2 = rand.nextDouble() - rand.nextDouble();
                    dr3 = rand.nextDouble() - rand.nextDouble();
                    x = dr * 3.25 + this.posX;
                    y = dr2 * 3.25 + this.posY + this.height / 2.0D;
                    z = dr3 * 3.25 + this.posZ;
                    this.worldObj.spawnParticle("reddust", x, y, z, f, f1, f2);
                }
            }

            if (this.getReflectionTicks() > 0) {
                for (int i = 0; i < 22; i++) {
                    dr = rand.nextDouble() - rand.nextDouble();
                    dr2 = rand.nextDouble() - rand.nextDouble();
                    dr3 = rand.nextDouble() - rand.nextDouble();
                    x = dr * 3.25 + this.posX;
                    y = dr2 * 3.25 + this.posY + this.height / 2.0D;
                    z = dr3 * 3.25 + this.posZ;
                    this.worldObj.spawnParticle("enchantmenttable", x, y, z, f, f1, f2);
                }

                f = 0.33F;
                f1 = 0.88F;
                f2 = 0.94F;

                for (int i = 0; i < 22; i++) {
                    dr = rand.nextDouble() - rand.nextDouble();
                    dr2 = rand.nextDouble() - rand.nextDouble();
                    dr3 = rand.nextDouble() - rand.nextDouble();
                    x = dr * 3.25 + this.posX;
                    y = dr2 * 3.25 + this.posY + this.height / 2.0D;
                    z = dr3 * 3.25 + this.posZ;
                    this.worldObj.spawnParticle("reddust", x, y, z, f, f1, f2);
                }
            }

            return;
        }

        if (this.getTransformationTicks() > 0) {
            this.setTransformationTicks(this.getTransformationTicks() - 1);

            this.reflectionBuffer = 0;
            this.unstableBuffer = 0;
            this.setHurtTime(0);
            this.setLeapTicks(0);
            this.setReflectionTicks(0);
            this.setUnstableTicks(0);
            this.setAttackTime(0);
            this.setGrappleTicks(0);
            this.hasLeaped = false;
            return;
        }

        if (this.unstableBuffer > 0) this.unstableBuffer--;
        if (this.reflectionBuffer > 0) this.reflectionBuffer--;
        if (this.getHurtTime() > 0) this.setHurtTime(this.getHurtTime() - 1);
        if (this.getLeapTicks() > 0) this.setLeapTicks(this.getLeapTicks() - 1);
        if (this.hasLeaped && !this.onGround) this.motionY = -1D;
        if (this.getAttackTime() > 0) this.setAttackTime(this.getAttackTime() - 1);

        if (this.getLeapTicks() > 0 && this.getLeapTicks() <= 20) {
            if (this.getLeapTicks() == 1) this.hasLeaped = true;
            this.motionY = 1.4D;
        }

        if (this.getUnstableTicks() > 0) {
            this.setUnstableTicks(this.getUnstableTicks() - 1);
            if (this.getUnstableTicks() % 20 == 0 && rand.nextBoolean()) {
                EntityDimensionalAnomaly ana = new EntityDimensionalAnomaly(this.worldObj);
                ana.setPosition(
                    this.posX + rand.nextInt(4) - rand.nextInt(4),
                    this.posY + rand.nextInt(6),
                    this.posZ + rand.nextInt(4) - rand.nextInt(4));
                this.worldObj.spawnEntityInWorld(ana);
            }

            if (this.getHealth() <= this.getMaxHealth() / 2 && this.ticksExisted % 10 == 0 && rand.nextBoolean()) {
                EntityNanoSwarm swarm = new EntityNanoSwarm(this.worldObj);
                swarm.copyLocationAndAnglesFrom(this);
                this.worldObj.spawnEntityInWorld(swarm);
            }

            if (this.getHealth() <= this.getMaxHealth() / 4 && this.ticksExisted % 10 == 0 && rand.nextBoolean()) {
                EntityHunter hunter = new EntityHunter(this.worldObj);
                hunter.setPosition(
                    this.posX + rand.nextDouble() - rand.nextDouble(),
                    this.posY + rand.nextDouble(),
                    this.posZ + rand.nextDouble() - rand.nextDouble());
                this.worldObj.spawnEntityInWorld(hunter);
            }

            if (rand.nextBoolean() && this.ticksExisted % 10 == 0) {
                List<Entity> list = this.worldObj
                    .getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(8.0, 8.0, 8.0));
                for (Entity e : list) {
                    if (e instanceof EntityLivingBase
                        && ((EntityLivingBase) e).getCreatureAttribute() != TragicEntities.Synapse) {
                        if (rand.nextBoolean())
                            e.attackEntityFrom(DamageHelper.causeArmorPiercingDamageToEntity(this), 1.0F);
                        if (rand.nextBoolean()) {
                            e.motionX += (rand.nextDouble() - rand.nextDouble()) * 2.45D;
                            e.motionY += (rand.nextDouble() - rand.nextDouble()) * 2.45D;
                            e.motionZ += (rand.nextDouble() - rand.nextDouble()) * 1.45D;
                        }
                    }
                }
            }

            if (this.getUnstableTicks() % 10 == 0)
                this.worldObj.playSoundAtEntity(this, "tragicmc:boss.overlordcocoon.wah", 1.4F, 0.5F);

            this.unstableBuffer = 300;
            this.aggregate = 1;
        }

        if (this.canUseAbility() && this.ticksExisted % 20 == 0 && this.onGround && rand.nextInt(64) == 0)
            this.setLeapTicks(40);

        if (this.canUseAbility() && this.unstableBuffer == 0
            && this.ticksExisted % (1000 / aggregate) == 0
            && rand.nextInt(32) == 0) {
            this.setUnstableTicks(200 + rand.nextInt(100));
            this.aggregate = 1;

            List<Entity> list = this.worldObj
                .getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(8.0, 8.0, 8.0));
            for (Entity e : list) {
                if (e instanceof EntityLivingBase && TragicConfig.allowHacked
                    && ((EntityLivingBase) e).getCreatureAttribute() != TragicEntities.Synapse)
                    ((EntityLivingBase) e)
                        .addPotionEffect(new PotionEffect(TragicPotion.Hacked.id, 40 + rand.nextInt(20), 0));
            }
        }

        if (this.getAttackTarget() != null && this.canUseAbility()
            && this.ticksExisted % 20 == 0
            && rand.nextInt(32) == 0
            && this.onGround
            && !this.isInWater()) this.setChargeTicks(200);

        this.setSprinting(false);
        if (this.getChargeTicks() > 0) {
            this.setChargeTicks(this.getChargeTicks() - 1);
            this.setSprinting(true);
            this.motionY = -0.2D;

            if (this.getAttackTarget() != null) {
                if (this.getChargeTicks() <= 170) {
                    float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ) * 30.0F;

                    if (this.isCollidedHorizontally && f >= 2.0F) {
                        if (f > 10.0F) f = 10.0F;
                        this.worldObj.createExplosion(
                            this,
                            this.posX,
                            this.posY,
                            this.posZ,
                            (f / 2) * rand.nextFloat() + (f / 2),
                            this.getMobGriefing());
                        this.setChargeTicks(0);
                    } else {
                        double d0 = this.getAttackTarget().posX - this.posX;
                        double d1 = this.getAttackTarget().posZ - this.posZ;
                        float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
                        double d2 = d0 / f2 * 0.23D * 0.310000011929209D + this.motionX * 1.44D;
                        this.motionX = Math.min(Math.abs(d2), 1.6D) == Math.abs(d2) ? d2 : 1.6D * (d2 < 0 ? -1 : 1);
                        double d3 = d1 / f2 * 0.23D * 0.310000011929209D + this.motionZ * 1.44D;
                        this.motionZ = Math.min(Math.abs(d3), 1.6D) == Math.abs(d3) ? d3 : 1.6D * (d3 < 0 ? -1 : 1);
                    }
                }

                if (this.getChargeTicks() % 10 == 0 && TragicConfig.allowMobSounds)
                    this.worldObj.playSoundAtEntity(this, "tragicmc:boss.overlordcombat.march", 1.8F, 1.0F);
            } else {
                this.setChargeTicks(0);
            }
        }

        if (this.canUseAbility() && this.getAttackTarget() != null
            && this.ticksExisted % 10 == 0
            && this.getDistanceToEntity(this.getAttackTarget()) >= 8.0F
            && rand.nextInt(16) == 0
            && this.onGround) this.setGrappleTicks(80 + rand.nextInt(40));
        if (this.getGrappleTicks() > 0) {
            this.setGrappleTicks(this.getGrappleTicks() - 1);
            this.motionX *= 0.2D;
            this.motionZ *= 0.2D;
            this.motionY = -0.4D;

            if (this.getAttackTarget() != null) {
                EntityLivingBase e = this.getAttackTarget();
                e.attackEntityFrom(DamageHelper.causeArmorPiercingDamageToEntity(this), 1.0F);

                double d1 = e.posX - this.posX;
                double d2 = e.posZ - this.posZ;
                double d3 = e.posY - this.posY;
                float f2 = MathHelper.sqrt_double(d1 * d1 + d2 * d2 + d3 * d3);
                double d4 = 0.5D;

                e.motionX = -d1 / f2 * d4 * 0.600000011920929D + e.motionX * 0.20000000298023224D;
                e.motionZ = -d2 / f2 * d4 * 0.600000011920929D + e.motionZ * 0.20000000298023224D;
                e.motionY = -d3 / f2 * d4 * 0.300000011920929D + e.motionZ * 0.10000000298023224D;

                if (this.getGrappleTicks() % 20 == 0 && TragicConfig.allowMobSounds)
                    this.worldObj.playSoundAtEntity(this, "tragicmc:boss.overlordcombat.phaser", 1.8F, 1.0F);
            } else {
                this.setGrappleTicks(0);
            }
        }

        if (this.canUseAbility() && rand.nextInt(16) == 0
            && this.ticksExisted % 5 == 0
            && !this.onGround
            && this.motionY <= 0
            && this.reflectionBuffer == 0
            && !this.isInWater()) this.setReflectionTicks(200 + rand.nextInt(100));
        if (this.getReflectionTicks() > 0) {
            this.setReflectionTicks(this.getReflectionTicks() - 1);
            this.reflectionBuffer = 200;
            if (this.motionY < 0 && !this.onGround) {
                ArrayList<int[]> list = WorldHelper
                    .getBlocksInCircularRange(this.worldObj, 2.5, this.posX, this.posY - 1, this.posZ);
                for (int[] coords : list) {
                    if (EntityOverlordCore.replaceableBlocks
                        .contains(this.worldObj.getBlock(coords[0], coords[1], coords[2]))) {
                        this.worldObj.setBlock(coords[0], coords[1], coords[2], TragicBlocks.OverlordBarrier, 0, 2);
                    }
                }
            }
            this.motionY = 0;

            if (this.ticksExisted % 10 == 0) {
                EntityHunter hunter = new EntityHunter(this.worldObj);
                hunter.setPosition(
                    this.posX + rand.nextDouble() - rand.nextDouble(),
                    this.posY + rand.nextDouble(),
                    this.posZ + rand.nextDouble() - rand.nextDouble());
                this.worldObj.spawnEntityInWorld(hunter);
            }

            if (this.getReflectionTicks() % 20 == 0 && TragicConfig.allowMobSounds)
                this.worldObj.playSoundAtEntity(this, "tragicmc:boss.overlordcombat.wow", 1.8F, 1.0F);
        }
        if (this.onGround && this.hasLeaped) this.hasLeaped = false;

        if (this.posY <= 1 && this.motionY < 0 && !this.onGround && this.getReflectionTicks() == 0) {
            ArrayList<int[]> list = WorldHelper
                .getBlocksInCircularRange(this.worldObj, 2.5, this.posX, this.posY - 1, this.posZ);
            for (int[] coords : list) {
                if (EntityOverlordCore.replaceableBlocks
                    .contains(this.worldObj.getBlock(coords[0], coords[1], coords[2]))) {
                    this.worldObj.setBlock(coords[0], coords[1], coords[2], TragicBlocks.OverlordBarrier, 0, 2);
                }
            }
        }

        if (TragicConfig.allowMobIllumination) {
            int x = (int) (this.posX + rand.nextInt(2) - rand.nextInt(2));
            int y = (int) (this.posY + rand.nextInt(2) - rand.nextInt(2)) + ((int) this.height * 2 / 3);
            int z = (int) (this.posZ + rand.nextInt(2) - rand.nextInt(2));
            if (EntityOverlordCore.replaceableBlocks.contains(worldObj.getBlock(x, y, z)))
                this.worldObj.setBlock(x, y, z, TragicBlocks.Luminescence);
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource src, float dmg) {
        if (this.getTransformationTicks() > 0) return false;

        if (src.getEntity() instanceof EntityLivingBase && !this.worldObj.isRemote) {
            EntityLivingBase entity = (EntityLivingBase) src.getEntity();
            boolean flag = TragicConfig.allowDivinity && entity.isPotionActive(TragicPotion.Divinity);

            if (this.getReflectionTicks() > 0 && entity.getCreatureAttribute() != TragicEntities.Synapse
                && dmg > 1.0F) {
                entity.attackEntityFrom(DamageHelper.causeArmorPiercingDamageToEntity(this), dmg * 0.825F);
                return false;
            }

            if (flag || !TragicConfig.allowDivinity && entity.getCreatureAttribute() != TragicEntities.Synapse
                || entity.getHeldItem() != null && entity.getHeldItem()
                    .getItem() == TragicItems.SwordOfJustice
                || src.canHarmInCreative())// || this.getVulnerableTicks() > 0 && entity.getCreatureAttribute() !=
                                           // TragicEntities.Synapse)
            {
                if (rand.nextBoolean() && this.worldObj
                    .getEntitiesWithinAABB(EntityNanoSwarm.class, this.boundingBox.expand(64.0, 64.0, 64.0D))
                    .size() < 16) {
                    EntityNanoSwarm swarm = new EntityNanoSwarm(this.worldObj);
                    swarm.setPosition(this.posX, this.posY, this.posZ);
                    this.worldObj.spawnEntityInWorld(swarm);
                }

                if (this.getHurtTime() == 0 && this.getChargeTicks() == 0) this.setHurtTime(40);
                if (this.getChargeTicks() > 0) dmg *= 0.825F;

                return super.attackEntityFrom(src, dmg);
            }

            if (rand.nextInt(4) == 0 && this.getAttackTarget() != entity
                && entity.getCreatureAttribute() != TragicEntities.Synapse) this.setAttackTarget(entity);
            if (aggregate < 100) aggregate++;
        }
        if (!this.worldObj.isRemote) this.worldObj.playSoundAtEntity(this, super.getHurtSound(), 1.0F, 1.0F);
        return true;
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        if (this.worldObj.isRemote || this.getTransformationTicks() > 0) return false;
        if (this.getChargeTicks() == 0 && this.getAttackTime() == 0) this.setAttackTime(20);
        if (this.getGrappleTicks() > 0) this.setGrappleTicks(0);

        if (this.getChargeTicks() > 0 || this.canUseAbility()) {
            boolean flag = super.attackEntityAsMob(entity);
            if (flag && !this.worldObj.isRemote) {
                entity.motionX *= 2.24D;
                entity.motionZ *= 2.24D;
                entity.motionY += 0.4D;

                List<Entity> list = this.worldObj
                    .getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(4.0, 4.0, 4.0));
                for (Entity e : list) {
                    if (e instanceof EntityLivingBase && e != entity) e.attackEntityFrom(
                        DamageSource.causeMobDamage(this),
                        (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
                            .getAttributeValue());
                }
                if (this.getChargeTicks() == 0 && this.getAttackTime() == 20 && TragicConfig.allowMobSounds)
                    this.worldObj.playSoundAtEntity(this, "tragicmc:boss.overlordcombat.shink", 1.9F, 1.0F);
            }
            return flag;
        } else {
            return false;
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("unstableTicks")) this.setUnstableTicks(tag.getInteger("unstableTicks"));
        if (tag.hasKey("unstableBuffer")) this.unstableBuffer = tag.getInteger("unstableBuffer");
        if (tag.hasKey("aggregate")) this.aggregate = tag.getInteger("aggregate");
        if (tag.hasKey("hurtTicks")) this.setHurtTime(tag.getInteger("hurtTicks"));
        if (tag.hasKey("leapTicks")) this.setLeapTicks(tag.getInteger("leapTicks"));
        if (tag.hasKey("hasLeaped")) this.hasLeaped = tag.getBoolean("hasLeaped");
        if (tag.hasKey("chargeTicks")) this.setChargeTicks(tag.getInteger("chargeTicks"));
        if (tag.hasKey("grappleTicks")) this.setGrappleTicks(tag.getInteger("grappleTicks"));
        if (tag.hasKey("reflectionTicks")) this.setReflectionTicks(tag.getInteger("reflectionTicks"));
        if (tag.hasKey("reflectionBuffer")) this.reflectionBuffer = tag.getInteger("reflectionBuffer");
        if (tag.hasKey("transformationTicks")) this.setTransformationTicks(tag.getInteger("transformationTicks"));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setInteger("unstableTicks", this.getUnstableTicks());
        tag.setInteger("unstableBuffer", this.unstableBuffer);
        tag.setInteger("aggregate", this.aggregate);
        tag.setInteger("hurtTicks", this.getHurtTime());
        tag.setInteger("leapTicks", this.getLeapTicks());
        tag.setBoolean("hasLeaped", this.hasLeaped);
        tag.setInteger("chargeTicks", this.getChargeTicks());
        tag.setInteger("grappleTicks", this.getGrappleTicks());
        tag.setInteger("reflectionTicks", this.getReflectionTicks());
        tag.setInteger("reflectionBuffer", this.reflectionBuffer);
        tag.setInteger("transformationTicks", this.getTransformationTicks());
    }

    @Override
    public void addPotionEffect(PotionEffect pe) {}

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        this.setTransforming();
        this.playLivingSound();
        return super.onSpawnWithEgg(data);
    }

    @Override
    public String getLivingSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:boss.overlordcombat.living" : null;
    }

    @Override
    public String getHurtSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:boss.overlordcombat.hurt" : super.getHurtSound();
    }

    @Override
    public String getDeathSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:boss.overlordcombat.death" : null;
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
    protected void func_145780_a(int x, int y, int z, Block block) {
        this.playSound("mob.irongolem.walk", 1.2F, 1.8F);
    }

    @Override
    public int getTalkInterval() {
        return super.getTalkInterval();
    }
}
