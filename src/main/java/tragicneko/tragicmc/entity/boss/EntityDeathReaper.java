package tragicneko.tragicmc.entity.boss;

import static tragicneko.tragicmc.TragicConfig.skultarStats;

import java.util.List;

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
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.EntityAIWatchTarget;
import tragicneko.tragicmc.entity.projectile.EntityLargePumpkinbomb;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityDeathReaper extends TragicBoss {

    private boolean isBomb;

    public EntityDeathReaper(World par1World) {
        super(par1World);
        this.setSize(0.7F, 2.1F);
        this.stepHeight = 2.0F;
        this.experienceValue = 70;
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
        this.tasks.addTask(8, new EntityAIWatchTarget(this, 32.0F));
        this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityGolem.class, 0, true));
        this.isImmuneToFire = true;
        this.isBomb = false;
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

    public int getDemeanor() {
        return this.dataWatcher.getWatchableObjectInt(16);
    }

    private void setDemeanor(int i) {
        this.dataWatcher.updateObject(16, i);
    }

    private void decrementDemeanor() {
        int pow = this.getDemeanor();
        if (pow > -20) this.setDemeanor(--pow);
    }

    private void incrementDemeanor() {
        int pow = this.getDemeanor();
        if (pow < 20) this.setDemeanor(++pow);
    }

    public boolean isBeingAggressive() {
        return this.getDemeanor() > 0;
    }

    public int getAttackTime() {
        return this.dataWatcher.getWatchableObjectInt(17);
    }

    private void setAttackTime(int i) {
        this.dataWatcher.updateObject(17, i);
    }

    private void decrementAttackTime() {
        int pow = this.getAttackTime();
        this.setAttackTime(--pow);
    }

    public int getHitTime() {
        return this.dataWatcher.getWatchableObjectInt(18);
    }

    private void setHitTime(int i) {
        this.dataWatcher.updateObject(18, i);
    }

    private void incrementHitTime() {
        int pow = this.getHitTime();
        this.setHitTime(++pow);
    }

    private int getCloneTime() {
        return this.dataWatcher.getWatchableObjectInt(19);
    }

    private void setCloneTime(int i) {
        this.dataWatcher.updateObject(19, i);
    }

    private void incrementCloneTime() {
        int pow = this.getCloneTime();
        this.setCloneTime(++pow);
    }

    public int getReaperType() {
        return this.dataWatcher.getWatchableObjectInt(20);
    }

    private void setReaperType(int i) {
        this.dataWatcher.updateObject(20, i);
        if (i == 0) this.isBomb = false;
    }

    public void setClone() {
        this.setReaperType(1);
        this.isBomb = rand.nextBoolean();
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    public boolean canRenderOnFire() {
        return false;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        boolean flag = this.getReaperType() == 0;
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(skultarStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(flag ? skultarStats[1] : skultarStats[1] * 0.875);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(flag ? skultarStats[2] : skultarStats[2] / 2);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(skultarStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(flag ? skultarStats[4] : skultarStats[4] * 0.4);
    }

    @Override
    public void onDeath(DamageSource src) {
        if (this.getReaperType() == 1) return;
        super.onDeath(src);

        if (src.getEntity() instanceof EntityPlayerMP && TragicConfig.allowAchievements)
            ((EntityPlayerMP) src.getEntity()).triggerAchievement(TragicAchievements.skultar);

        List<EntityDeathReaper> list = this.worldObj
            .getEntitiesWithinAABB(EntityDeathReaper.class, this.boundingBox.expand(32.0, 32.0, 32.0));
        for (EntityDeathReaper reaper : list) {
            if (reaper.getReaperType() == 1) reaper.setDead();
        }
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
                    new ItemStack(TragicItems.MobStatue, 1, 2)));
    }

    @Override
    public void onLivingUpdate() {
        if (this.isPotionActive(Potion.wither.id)) this.removePotionEffect(Potion.wither.id);
        if (this.isPotionActive(Potion.weakness.id)) this.removePotionEffect(Potion.weakness.id);

        if (this.getAttackTime() > 0) this.motionX = this.motionZ = 0.0D;
        if (this.getAttackTime() > 0) this.motionY = -0.1D;

        super.onLivingUpdate();

        if (this.worldObj.isRemote) {
            String s = this.isBeingAggressive() ? "reddust" : "mobSpellAmbient";
            int pow = this.isBeingAggressive() ? 4 : 8;

            for (int i = 0; i < pow; i++) {
                this.worldObj.spawnParticle(
                    s,
                    this.posX + (this.rand.nextDouble() - 0.5D) * this.width * 1.3D,
                    this.posY + (rand.nextDouble() * 0.115D) + 0.545D,
                    this.posZ + (this.rand.nextDouble() - 0.5D) * this.width * 1.3D,
                    0.0,
                    rand.nextDouble() * 0.5556,
                    0.0);
            }

            if (this.getAttackTime() == 2) {
                for (int i = 0; i < 14; i++) {
                    this.worldObj.spawnParticle(
                        "smoke",
                        this.posX + (this.rand.nextDouble() - 0.5D) * this.width * 1.3D,
                        this.posY + (rand.nextDouble() * 0.115D) + 0.375D,
                        this.posZ + (this.rand.nextDouble() - 0.5D) * this.width * 1.3D,
                        0.0,
                        rand.nextDouble() * 0.5556,
                        0.0);
                }
            }
        } else {
            this.incrementCloneTime();
            this.incrementHitTime();
            if (this.rand.nextInt(524) == 0 && this.getDemeanor() <= 0 && this.getAttackTarget() != null
                || this.getHitTime() >= 100 && this.getAttackTarget() != null) this.setDemeanor(5);
            if (this.getAttackTime() > 0) this.decrementAttackTime();

            if (this.isBomb) {
                this.setCloneTime(0);
                if (this.getReaperType() == 0) this.isBomb = false;
            }

            if (this.ticksExisted % 60 == 0 && this.getHealth() < this.getMaxHealth()
                && this.getReaperType() == 0
                && TragicConfig.skultarRegeneration) this.heal(6.0F);

            if (this.ticksExisted >= 600 && this.getReaperType() == 1) this.setDead();

            if (this.getAttackTarget() == null) {
                this.setDemeanor(0);
                this.setCloneTime(0);
                if (this.getReaperType() == 1) this.setDead();
            } else {
                if (this.getAttackTarget() instanceof EntityPlayerMP && TragicConfig.allowAchievements
                    && TragicConfig.allowImmunity
                    && TragicConfig.allowClarity) {
                    if (this.getAttackTarget()
                        .isPotionActive(TragicPotion.Immunity)
                        && this.getAttackTarget()
                            .isPotionActive(TragicPotion.Clarity)) {
                        ((EntityPlayerMP) this.getAttackTarget()).triggerAchievement(TragicAchievements.skultarImmune);
                    }
                }

                if (this.getAttackTarget()
                    .getHealth()
                    <= this.getAttackTarget()
                        .getMaxHealth() / 4)
                    this.setDemeanor(5);

                int z = this.getHealth() <= this.getMaxHealth() / 2 ? 2 : 1;

                if (this.canEntityBeSeen(this.getAttackTarget()) && this.rand.nextInt(96 / z) == 0
                    && this.getReaperType() == 0) {
                    EntityLivingBase entity = this.getAttackTarget();

                    if (rand.nextInt(72) == 0) {
                        entity.addPotionEffect(new PotionEffect(Potion.blindness.id, 300 + rand.nextInt(320), 0));
                    }

                    if (rand.nextInt(256) == 0) {
                        entity.addPotionEffect(new PotionEffect(Potion.wither.id, 300 + rand.nextInt(320), 0));
                    }

                    if (rand.nextInt(128) == 0 && TragicConfig.allowInhibit) {
                        entity.addPotionEffect(new PotionEffect(TragicPotion.Inhibit.id, 300 + rand.nextInt(320), 0));
                    }

                    if (rand.nextInt(72) == 0 && TragicConfig.allowCripple) {
                        entity.addPotionEffect(
                            new PotionEffect(TragicPotion.Cripple.id, 300 + rand.nextInt(320), rand.nextInt(3)));
                    }

                    if (rand.nextInt(72) == 0 && TragicConfig.allowDisorientation) {
                        entity.addPotionEffect(
                            new PotionEffect(TragicPotion.Disorientation.id, 300 + rand.nextInt(320), rand.nextInt(3)));
                    }

                    if (rand.nextInt(72) == 0 && TragicConfig.allowMalnourish) {
                        entity.addPotionEffect(
                            new PotionEffect(TragicPotion.Malnourish.id, 300 + rand.nextInt(320), rand.nextInt(3)));
                    }
                }

                if (this.isEntityInRange(this.getAttackTarget(), 2.0F, 6.0F) && this.onGround
                    && rand.nextInt(16) == 0
                    && this.getAttackTime() == 0) {
                    double d0 = this.getAttackTarget().posX - this.posX;
                    double d1 = this.getAttackTarget().posZ - this.posZ;
                    double d2 = this.getAttackTarget().posY - this.posY;
                    float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);

                    if (this.isBeingAggressive()) {
                        this.motionX = d0 / f2 * 2.45D * 0.800000011920929D + this.motionX * 0.80000000298023224D;
                        this.motionZ = d1 / f2 * 2.45D * 0.800000011920929D + this.motionZ * 0.80000000298023224D;
                        this.motionY = d2 / f2 * 2.45D * 0.800000011920929D + this.motionY * 0.80000000298023224D;
                    } else {
                        this.motionX = -d0 / f2 * 2.45D * 0.800000011920929D + this.motionX * 0.80000000298023224D;
                        this.motionZ = -d1 / f2 * 2.45D * 0.800000011920929D + this.motionZ * 0.80000000298023224D;
                        this.motionY = d2 / f2 * 2.45D * 0.800000011920929D + this.motionY * 0.80000000298023224D;
                    }
                } else if (this.getAttackTarget() != null && this.isEntityInRange(this.getAttackTarget(), 1.0F, 12.0F)
                    && this.getAttackTime() == 0) {
                        double d0 = this.getAttackTarget().posX - this.posX;
                        double d1 = this.getAttackTarget().posZ - this.posZ;
                        double d2 = this.getAttackTarget().posY - this.posY;
                        float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);

                        if (!this.isBeingAggressive()) {
                            this.motionX = -d0 / f2 * 1.25D * 0.200000011920929D + this.motionX * 0.10000000298023224D;
                            this.motionZ = -d1 / f2 * 1.25D * 0.200000011920929D + this.motionZ * 0.10000000298023224D;
                            this.motionY = d2 / f2 * 1.25D * 0.200000011920929D + this.motionY * 0.10000000298023224D;
                        }
                    }

                int x = this.getHealth() <= this.getMaxHealth() / 2 ? 4 : 2;

                if (this.getDistanceToEntity(this.getAttackTarget()) > 4.0F && rand.nextInt(64 / x) == 0
                    && this.canEntityBeSeen(this.getAttackTarget())
                    && this.getAttackTime() == 0
                    && TragicConfig.skultarProjectiles) {
                    double d0 = this.getAttackTarget().posX - this.posX;
                    double d1 = this.getAttackTarget().boundingBox.minY + this.getAttackTarget().height / 3.0F
                        - (this.posY + this.height / 2.0F);
                    double d2 = this.getAttackTarget().posZ - this.posZ;

                    if (this.isBeingAggressive()) {
                        EntityLargePumpkinbomb bomb = new EntityLargePumpkinbomb(this.worldObj, this);
                        bomb.posY = this.posY + (this.height * 2.0 / 3.0);
                        this.worldObj.spawnEntityInWorld(bomb);
                    } else {
                        EntityWitherSkull skull = new EntityWitherSkull(this.worldObj, this, d0, d1, d2);
                        skull.posY = this.posY + (this.height * 2.0 / 3.0);
                        this.worldObj.spawnEntityInWorld(skull);
                    }

                    if (rand.nextInt(4) == 0 && TragicConfig.skultarDemeanor) this.incrementDemeanor();
                }

                if (this.getDistanceToEntity(this.getAttackTarget()) <= 3.0F
                    && this.getHealth() <= this.getMaxHealth() / 2
                    && this.getAttackTime() == 0
                    && this.isBeingAggressive()
                    && rand.nextInt(32) == 0) this.setAttackTime(20);

                if (this.getHealth() <= this.getMaxHealth() / 2 && this.getAttackTime() == 1
                    && TragicConfig.skultarWitheringGas) {
                    List<int[]> list = WorldHelper
                        .getBlocksInSphericalRange(this.worldObj, 3.5, this.posX, this.posY, this.posZ);
                    for (int[] coords : list) {
                        Block block = this.worldObj.getBlock(coords[0], coords[1], coords[2]);
                        if ((block == Blocks.air || block == TragicBlocks.WitheringGas)
                            && World.doesBlockHaveSolidTopSurface(this.worldObj, coords[0], coords[1] - 1, coords[2]))
                            this.worldObj.setBlock(coords[0], coords[1], coords[2], TragicBlocks.WitheringGas);
                    }

                }
            }

            int i = this.getDemeanor() > 0 ? 8 : 16;
            if (this.ticksExisted % i == 0 && !this.isDead && TragicConfig.allowMobSounds)
                this.worldObj.playSoundAtEntity(this, "tragicmc:boss.skultar.heartbeat", 0.8F, 1.0F);
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (this.worldObj.isRemote || par1DamageSource == DamageSource.wither) return false;

        if (this.isBomb && this.getReaperType() == 1 && !par1DamageSource.isExplosion()) {
            par2 = Float.MAX_VALUE;
            if (this.isBomb && this.getReaperType() == 1)
                this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 2.0F, this.getMobGriefing());
        }

        this.trackHitType(par1DamageSource.getDamageType());

        boolean result = super.attackEntityFrom(par1DamageSource, par2);

        if (result) {
            this.setHitTime(0);

            if (this.getCloneTime() > 100 && this.getHealth() <= this.getMaxHealth() / 2
                && rand.nextInt(4) == 0
                && par1DamageSource.getEntity() != null
                && TragicConfig.skultarClone) {
                int potato = this.getHealth() <= this.getMaxHealth() / 4 ? 2 : 1;

                for (int x = 0; x < potato && this.attemptToSummonClones(); x++);
                this.setCloneTime(0);
            }
        }

        return result;
    }

    private boolean attemptToSummonClones() {
        List<Entity> list = this.worldObj
            .getEntitiesWithinAABB(EntityDeathReaper.class, boundingBox.expand(32.0, 32.0, 32.0));

        if (list.size() >= 4 || this.getAttackTarget() == null || this.getCloneTime() <= 100) return false;

        EntityDeathReaper clone = new EntityDeathReaper(this.worldObj);
        clone.copyLocationAndAnglesFrom(this);
        clone.setClone();
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
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        if (this.worldObj.isRemote || this.getAttackTime() > 0) return false;

        boolean result = super.attackEntityAsMob(par1Entity);

        if (result) {
            if (par1Entity instanceof EntityLivingBase && rand.nextInt(8) == 0) {
                switch (rand.nextInt(8)) {
                    case 0:
                        ((EntityLivingBase) par1Entity)
                            .addPotionEffect(new PotionEffect(Potion.confusion.id, rand.nextInt(200) + 320));
                        break;
                    case 1:
                        ((EntityLivingBase) par1Entity)
                            .addPotionEffect(new PotionEffect(Potion.weakness.id, rand.nextInt(200) + 320));
                        break;
                    case 2:
                        ((EntityLivingBase) par1Entity)
                            .addPotionEffect(new PotionEffect(Potion.blindness.id, rand.nextInt(200) + 320));
                        break;
                    case 3:
                        ((EntityLivingBase) par1Entity)
                            .addPotionEffect(new PotionEffect(Potion.digSlowdown.id, rand.nextInt(200) + 320));
                        break;
                    case 4:
                        if (TragicConfig.allowDisorientation) ((EntityLivingBase) par1Entity)
                            .addPotionEffect(new PotionEffect(TragicPotion.Disorientation.id, rand.nextInt(200) + 320));
                        break;
                    case 5:
                        if (TragicConfig.allowFear) ((EntityLivingBase) par1Entity)
                            .addPotionEffect(new PotionEffect(TragicPotion.Fear.id, rand.nextInt(200) + 320));
                        break;
                    default:
                        if (TragicConfig.allowSubmission) ((EntityLivingBase) par1Entity).addPotionEffect(
                            new PotionEffect(TragicPotion.Submission.id, rand.nextInt(200) + 320, rand.nextInt(2) + 1));
                        break;
                }
            }

            if (this.getHealth() <= this.getMaxHealth() / 2) par1Entity.setFire(4 + rand.nextInt(12));
            par1Entity.motionX *= 1.4D;
            par1Entity.motionZ *= 1.4D;
            par1Entity.motionY += 0.3D;

            if (this.getAttackTime() == 0) this.setAttackTime(10);
            if (TragicConfig.allowMobSounds) this.worldObj
                .playSoundAtEntity(this, "tragicmc:boss.skultar.slice", 0.8F, 0.8F + rand.nextFloat() * 0.4F);
        }

        return result;
    }

    @Override
    public int getTotalArmorValue() {
        return this.getReaperType() == 1 ? MathHelper.floor_double(skultarStats[5] / 4)
            : (this.isBeingAggressive() ? (int) skultarStats[5] : MathHelper.floor_double(skultarStats[5] / 3));
    }

    @Override
    public void fall(float par1) {}

    private void trackHitType(String damageType) {
        String hitType = null;
        boolean flag = false;

        if (damageType.equals("arrow")) {
            hitType = "projectile";
        } else if (damageType.equals("fireball")) {
            hitType = "projectile";
        } else if (damageType.equals("indirectMagic")) {
            hitType = "normal";
        } else if (damageType.equals("player")) {
            hitType = "normal";
            flag = true;
        } else if (damageType.equals("generic")) {
            hitType = "normal";
        } else if (damageType.equals("mob")) {
            hitType = "normal";
        }

        if (hitType == null || !TragicConfig.skultarDemeanor) {
            return;
        } else if (hitType == "projectile") {
            this.incrementDemeanor();
            if (flag) this.incrementDemeanor();
            // if (this.getAttackTarget() != null && this.isBeingAggressive() && rand.nextInt(16) == 0)
            // this.worldObj.playSoundAtEntity(this, "tragicmc:boss.skultar.angry", 1.0F, 1.0F);
        } else {
            this.decrementDemeanor();
            if (flag) this.decrementDemeanor();
            // if (this.getAttackTarget() != null && !this.isBeingAggressive() && rand.nextInt(16) == 0)
            // this.worldObj.playSoundAtEntity(this, "tragicmc:boss.skultar.passive", 1.0F, 1.0F);
        }
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        if (!this.worldObj.isRemote) this.isBomb = rand.nextBoolean();
        return super.onSpawnWithEgg(data);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("demeanor")) this.setDemeanor(tag.getInteger("demeanor"));
        if (tag.hasKey("hitTime")) this.setHitTime(tag.getInteger("hitTime"));
        if (tag.hasKey("cloneTime")) this.setCloneTime(tag.getInteger("cloneTime"));
        if (tag.hasKey("attackTime")) this.setAttackTime(tag.getInteger("attackTime"));
        if (tag.hasKey("reaperType")) this.setReaperType(tag.getInteger("reaperType"));
        if (tag.hasKey("isBomb")) this.isBomb = tag.getBoolean("isBomb");
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setInteger("demeanor", this.getDemeanor());
        tag.setInteger("hitTime", this.getHitTime());
        tag.setInteger("cloneTime", this.getCloneTime());
        tag.setInteger("attackTime", this.getAttackTime());
        tag.setInteger("reaperType", this.getReaperType());
        tag.setBoolean("isBomb", this.isBomb);
    }

    @Override
    public String getLivingSound() {
        return "mob.skeleton.say";
    }

    @Override
    public String getHurtSound() {
        return TragicConfig.allowMobSounds && rand.nextBoolean() ? "tragicmc:boss.skultar.laugh" : super.getHurtSound();
    }

    @Override
    public String getDeathSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:boss.skultar.laugh" : null;
    }

    @Override
    protected void func_145780_a(int x, int y, int z, Block block) {}

    @Override
    public float getSoundPitch() {
        return 1.0F;
    }

    @Override
    public float getSoundVolume() {
        return 0.4F;
    }

    @Override
    public int getTalkInterval() {
        return 170;
    }
}
