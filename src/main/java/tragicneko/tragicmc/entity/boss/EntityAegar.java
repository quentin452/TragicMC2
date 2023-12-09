package tragicneko.tragicmc.entity.boss;

import static tragicneko.tragicmc.TragicConfig.aegarStats;

import java.util.List;
import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.projectile.EntityCrystalMortor;
import tragicneko.tragicmc.util.DamageHelper;

public class EntityAegar extends TragicBoss implements IMultiPart {

    public EntityPart[] aegarParts;

    public EntityPart aegarBody;
    public EntityAegarCrystal aegarCrystal;
    public EntityPart aegarClaw;
    public EntityPart aegarCannon;
    public EntityPart aegarHead;

    private AttributeModifier mod = new AttributeModifier(
        UUID.fromString("8771eb81-724e-4d6b-91a4-d7e2cd17f82c"),
        "aegarSpeedBuff",
        TragicConfig.modifier[4],
        0);

    public EntityAegar(World par1World) {
        super(par1World);
        this.aegarParts = new EntityPart[] { aegarBody = new EntityPart(this, "body", 1.0F, 1.0F),
            aegarCrystal = new EntityAegarCrystal(this), aegarClaw = new EntityPart(this, "claw", 1.5F, 1.5F),
            aegarCannon = new EntityPart(this, "cannon", 1.5F, 1.5F),
            aegarHead = new EntityPart(this, "head", 1.0F, 1.0F) };
        this.setSize(1.385F * 1.545F, 2.325F * 1.545F);
        this.stepHeight = 2.0F;
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
        this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityGolem.class, 0, true));
        this.isImmuneToFire = true;
        this.experienceValue = 36;
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return TragicEntities.Synapse;
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(aegarStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(aegarStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(aegarStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(aegarStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(aegarStats[4]);
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
        this.dataWatcher.addObject(23, Integer.valueOf(0));
        this.dataWatcher.addObject(24, Integer.valueOf(0));
    }

    public boolean getHypermode() {
        return this.dataWatcher.getWatchableObjectInt(16) == 1;
    }

    private void setHypermode(boolean flag) {
        this.dataWatcher.updateObject(16, flag ? 1 : 0);
    }

    public int getStunTicks() {
        return this.dataWatcher.getWatchableObjectInt(17);
    }

    private void setStunTicks(int i) {
        this.dataWatcher.updateObject(17, i);
    }

    private void decrementStunTicks() {
        this.setStunTicks(this.getStunTicks() - 1);
    }

    public int getShockwaveTicks() {
        return this.dataWatcher.getWatchableObjectInt(18);
    }

    private void setShockwaveTicks(int i) {
        this.dataWatcher.updateObject(18, i);
    }

    private void decrementShockwaveTicks() {
        this.setShockwaveTicks(this.getShockwaveTicks() - 1);
    }

    public int getAttackTime() {
        return this.dataWatcher.getWatchableObjectInt(19);
    }

    private void setAttackTime(int i) {
        this.dataWatcher.updateObject(19, i);
    }

    private void decrementAttackTime() {
        this.setAttackTime(this.getAttackTime() - 1);
    }

    public int getHurtTime() {
        return this.dataWatcher.getWatchableObjectInt(20);
    }

    private void setHurtTime(int i) {
        this.dataWatcher.updateObject(20, i);
    }

    private void decrementHurtTime() {
        this.setHurtTime(this.getHurtTime() - 1);
    }

    public int getLaserTicks() {
        return this.dataWatcher.getWatchableObjectInt(21);
    }

    private void setLaserTicks(int i) {
        this.dataWatcher.updateObject(21, i);
    }

    private void decrementLaserTicks() {
        this.setLaserTicks(this.getLaserTicks() - 1);
    }

    public int getMortorTicks() {
        return this.dataWatcher.getWatchableObjectInt(22);
    }

    private void setMortorTicks(int i) {
        this.dataWatcher.updateObject(22, i);
    }

    private void decrementMortorTicks() {
        this.setMortorTicks(this.getMortorTicks() - 1);
    }

    public int getAutoTicks() {
        return this.dataWatcher.getWatchableObjectInt(23);
    }

    private void setAutoTicks(int i) {
        this.dataWatcher.updateObject(23, i);
    }

    private void decrementAutoTicks() {
        this.setAutoTicks(this.getAutoTicks() - 1);
    }

    public int getTargetID() {
        return this.dataWatcher.getWatchableObjectInt(24);
    }

    private void setTargetID(int i) {
        this.dataWatcher.updateObject(24, i);
    }

    private boolean canUseAbility() {
        return this.getStunTicks() == 0 && this.getShockwaveTicks() == 0
            && this.getLaserTicks() == 0
            && this.getMortorTicks() == 0
            && this.getAutoTicks() == 0;
    }

    @Override
    public void onLivingUpdate() {
        if (this.getStunTicks() > 0) {
            this.motionX = this.motionZ = 0.0D;
            this.motionY = -0.1D;
            if (this.getShockwaveTicks() > 0) this.setShockwaveTicks(0);
            if (this.getHurtTime() > 0) this.setHurtTime(0);
            if (this.getAttackTime() > 0) this.setAttackTime(0);
            if (this.getLaserTicks() > 0) this.setLaserTicks(0);
            if (this.getMortorTicks() > 0) this.setMortorTicks(0);
        }

        if (this.getLaserTicks() > 0 || this.getShockwaveTicks() > 0 || this.getMortorTicks() > 20)
            this.motionX = this.motionZ = this.motionY = 0.0D;

        super.onLivingUpdate();

        float f1 = this.rotationYaw * (float) Math.PI / 180.0F;
        float f2 = MathHelper.sin(f1);
        float f3 = MathHelper.cos(f1);
        this.aegarHead.width = 0.35F;
        this.aegarHead.height = 0.75F;
        this.aegarCrystal.height = 0.65F;
        this.aegarCrystal.width = 0.45F;
        this.aegarBody.height = this.aegarBody.width = 0.45F;
        this.aegarCannon.width = this.aegarClaw.width = 0.55F;
        this.aegarClaw.height = this.aegarCannon.height = 1.25F;

        this.aegarHead
            .setLocationAndAngles(this.posX + (f2 * 0.025D), this.posY + 3.25D, this.posZ + (f3 * 0.025D), 0.0F, 0.0F);
        this.aegarCrystal
            .setLocationAndAngles(this.posX + (f2 * 0.025D), this.posY + 2.55D, this.posZ + (f3 * 0.025D), 0.0F, 0.0F);
        this.aegarBody
            .setLocationAndAngles(this.posX + (f2 * 0.025D), this.posY + 1.25D, this.posZ + (f3 * 0.025D), 0.0F, 0.0F);
        this.aegarCannon
            .setLocationAndAngles(this.posX + (f3 * 0.875D), this.posY + 1.2D, this.posZ + (f2 * 0.875D), 0.0F, 0.0F);
        this.aegarClaw
            .setLocationAndAngles(this.posX - (f3 * 0.875D), this.posY + 1.2D, this.posZ - (f2 * 0.875D), 0.0F, 0.0F);

        for (EntityPart aegarPart : this.aegarParts) {
            if (aegarPart.isBurning()) aegarPart.extinguish();
            aegarPart.onUpdate();
        }

        if (this.worldObj.isRemote) {
            String s = this.getHypermode() ? "reddust" : "happyVillager";

            this.aegarCrystal.worldObj.spawnParticle(
                s,
                this.aegarCrystal.posX + rand.nextDouble() - rand.nextDouble(),
                this.aegarCrystal.posY + rand.nextDouble() * this.aegarCrystal.height,
                this.aegarCrystal.posZ + rand.nextDouble() - rand.nextDouble(),
                0.0,
                0.0,
                0.0);

            if (rand.nextBoolean()) this.worldObj.spawnParticle(
                s,
                this.posX + (rand.nextDouble() - rand.nextDouble()) * this.width,
                this.posY + rand.nextDouble() * this.height + 0.15,
                this.posZ + (rand.nextDouble() - rand.nextDouble()) * this.width,
                0.0,
                0.0,
                0.0);

            for (int i = 0; i < 4; i++) {
                this.worldObj.spawnParticle(
                    "mobSpellAmbient",
                    this.posX + (rand.nextDouble() - rand.nextDouble()) * 0.55,
                    this.posY + 0.25 + rand.nextDouble(),
                    this.posZ + (rand.nextDouble() - rand.nextDouble()) * 0.55,
                    0.0,
                    0.0,
                    0.0);
            }

            if (this.getHurtTime() >= 8) {
                for (int i = 0; i < 12; i++) this.worldObj.spawnParticle(
                    s,
                    this.posX + (rand.nextDouble() - rand.nextDouble()) * this.width,
                    this.posY + rand.nextDouble() * this.height + 0.15,
                    this.posZ + (rand.nextDouble() - rand.nextDouble()) * this.width,
                    0.0,
                    0.0,
                    0.0);
            }

            if (this.getMortorTicks() > 20) {
                for (int i = 0; i < 32; i++) {
                    double d7 = (rand.nextDouble() * 10.0D - rand.nextDouble() * 10.0D);
                    double d8 = (rand.nextDouble() * 10.0D - rand.nextDouble() * 10.0D);
                    double d9 = (rand.nextDouble() * 10.0D - rand.nextDouble() * 10.0D);

                    double d0 = d7 + this.posX;
                    double d1 = d8 + this.posY + this.aegarCannon.height / 2.0D;
                    double d2 = d9 + this.posZ;

                    float f4 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
                    double d3 = 0.75D;

                    double d4 = d0 / f4 * d3 * 0.100000011920929D + d7 * 0.10000000298023224D;
                    double d5 = d1 / f4 * d3 * 0.100000011920929D + d8 * 0.10000000298023224D;
                    double d6 = d2 / f4 * d3 * 0.100000011920929D + d9 * 0.10000000298023224D;

                    this.worldObj.spawnParticle("reddust", d0, d1, d2, d4 * 2.5D, d5 * 2.5D, d6 * 2.5D);
                }
            }

            if (this.getLaserTicks() > 20 || this.getAutoTicks() > 20) {
                for (int i = 0; i < 3; i++) {
                    this.worldObj.spawnParticle(
                        "flame",
                        this.posX + (rand.nextDouble() - rand.nextDouble()) * 0.55,
                        this.posY + 2.25 + rand.nextDouble(),
                        this.posZ + (rand.nextDouble() - rand.nextDouble()) * 0.55,
                        0.0,
                        0.0,
                        0.0);
                }
            }

            if (this.getLaserTicks() == 5 || this.getAutoTicks() > 0 && this.getAutoTicks() % 5 == 0) {
                Entity entity = this.worldObj.getEntityByID(this.getTargetID());
                if (entity != null) {
                    double d0 = entity.posX - this.aegarCannon.posX;
                    double d1 = entity.posY - this.aegarCannon.posY;
                    double d2 = entity.posZ - this.aegarCannon.posZ;

                    for (int l = 0; l < 4; l++) {
                        double d3 = 0.23D * l + (rand.nextDouble() * 0.25D);
                        this.worldObj.spawnParticle(
                            "flame",
                            this.aegarCannon.posX + d0 * d3,
                            this.aegarCannon.posY + d1 * d3 + 1.25D,
                            this.aegarCannon.posZ + d2 * d3,
                            0.0,
                            0.0,
                            0.0);
                    }
                }
            }

            if (this.getShockwaveTicks() > 7) {
                for (int i = 0; i < 12; i++) {
                    this.worldObj.spawnParticle(
                        "smoke",
                        this.posX + (rand.nextDouble() - rand.nextDouble()) * 1.25,
                        this.posY + rand.nextDouble() * this.height,
                        this.posZ + (rand.nextDouble() - rand.nextDouble()) * 1.25,
                        0.0,
                        rand.nextDouble() * 2.0 + 1.0,
                        0.0);
                }
            }

            return;
        }

        if (this.getStunTicks() > 0) this.decrementStunTicks();
        if (this.getShockwaveTicks() > 0) this.decrementShockwaveTicks();
        if (this.getHurtTime() > 0) this.decrementHurtTime();
        if (this.getAttackTime() > 0) this.decrementAttackTime();
        if (this.getLaserTicks() > 0) this.decrementLaserTicks();
        if (this.getMortorTicks() > 0) this.decrementMortorTicks();
        if (this.getAutoTicks() > 0) this.decrementAutoTicks();

        if (this.aegarCrystal.getHealth() <= 0 && !this.getHypermode()) this.onCrystalDestruction();

        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .removeModifier(mod);
        if (this.getHypermode()) this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .applyModifier(mod);

        if (this.getAttackTarget() == null) {
            if (this.getShockwaveTicks() > 0) this.setShockwaveTicks(0);
            if (this.getLaserTicks() > 0) this.setLaserTicks(0);
            if (this.getMortorTicks() > 0) this.setMortorTicks(0);
            if (this.getAutoTicks() > 0) this.setAutoTicks(0);
            this.setTargetID(0);
        } else {
            this.setTargetID(
                this.getAttackTarget()
                    .getEntityId());
            if (this.canUseAbility() && this.getDistanceToEntity(this.getAttackTarget()) >= 8.0F
                && rand.nextInt(64) == 0
                && TragicConfig.aegarLasers) this.setLaserTicks(40);
            if (this.canUseAbility() && this.getDistanceToEntity(this.getAttackTarget()) >= 4.0F
                && rand.nextInt(128) == 0
                && this.getHypermode()
                && TragicConfig.aegarLasers) this.setAutoTicks(100 + rand.nextInt(80));
            if (this.getLaserTicks() == 5 && this.canEntityBeSeen(this.getAttackTarget())
                || this.getAutoTicks() > 20 && this.getAutoTicks() % 5 == 0
                    && this.canEntityBeSeen(this.getAttackTarget()))
                this.fireLaser();
            if (this.getLaserTicks() > 0 && !this.canEntityBeSeen(this.getAttackTarget())) this.setLaserTicks(0);

            if (this.canUseAbility() && this.getDistanceToEntity(this.getAttackTarget()) <= 6.0F
                && rand.nextInt(this.getHypermode() ? 48 : 128) == 0
                && this.onGround
                && TragicConfig.aegarShockwave) {
                if (TragicConfig.allowMobSounds)
                    this.worldObj.playSoundAtEntity(this, "tragicmc:boss.aegar.shockwave", 1.0F, 1.0F);
                this.setShockwaveTicks(60);
            }

            if (this.getShockwaveTicks() == 5) {
                this.worldObj.createExplosion(
                    this,
                    this.posX,
                    this.posY,
                    this.posZ,
                    3.0F + 2.0F * rand.nextFloat(),
                    this.getMobGriefing());
                this.attackEntitiesInList(
                    this.worldObj
                        .getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(6.0D, 6.0D, 6.0D)));
            }

            if (this.canUseAbility() && this.getDistanceToEntity(this.getAttackTarget()) > 12.0F
                && this.getHypermode()
                && rand.nextInt(128) == 0
                && TragicConfig.aegarMortors) this.setMortorTicks(100);
            if (this.getMortorTicks() > 20 && this.getMortorTicks() % 20 == 0) this.createCrystalMortors();
        }
    }

    private void createCrystalMortors() {

        if (!TragicConfig.aegarMortors) return;
        double d0 = this.getAttackTarget().posX - this.posX;
        double d1 = rand.nextInt(4);
        double d2 = this.getAttackTarget().posZ - this.posZ;
        float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.975F;

        EntityCrystalMortor mortor = new EntityCrystalMortor(
            this.worldObj,
            this,
            d0 + this.rand.nextGaussian() * f1,
            d1,
            d2 + this.rand.nextGaussian() * f1);
        mortor.posY = this.posY + this.height + 0.5D;
        mortor.posX += d0 * 0.04335D;
        mortor.posZ += d2 * 0.04335D;
        mortor.motionY += 0.36D * f1;
        this.worldObj.spawnEntityInWorld(mortor);

        if (TragicConfig.allowMobSounds) this.worldObj.playSoundAtEntity(this, "tragicmc:boss.aegar.trill", 0.6F, 1.0F);
    }

    private void fireLaser() {
        if (!TragicConfig.aegarLasers) return;
        if (TragicConfig.allowMobSounds) {
            this.worldObj.playSoundAtEntity(this, "tragicmc:boss.aegar.laser", 1.0F, 1.0F);
            this.worldObj.playSoundAtEntity(this.getAttackTarget(), "tragicmc:boss.aegar.laser", 1.0F, 1.0F);
        }
        this.getAttackTarget()
            .attackEntityFrom(DamageHelper.causeArmorPiercingDamageToEntity(this), 1.0F + rand.nextFloat());
    }

    private void onCrystalDestruction() {
        if (TragicConfig.aegarHypermode) this.setHypermode(true);
        super.attackEntityFrom(DamageSource.magic, 75.0F);
        this.setStunTicks(120);

        if (this.getShockwaveTicks() > 0) this.setShockwaveTicks(0);
        if (this.getHurtTime() > 0) this.setHurtTime(0);
        if (this.getAttackTime() > 0) this.setAttackTime(0);
        if (this.getLaserTicks() > 0) this.setLaserTicks(0);
        if (this.getMortorTicks() > 0) this.setMortorTicks(0);
    }

    @Override
    public int getTotalArmorValue() {
        return (int) aegarStats[5];
    }

    @Override
    protected void fall(float par1) {}

    @Override
    public void addPotionEffect(PotionEffect effect) {}

    @Override
    public void setFire(int i) {}

    @Override
    public void onStruckByLightning(EntityLightningBolt bolt) {}

    @Override
    public World getWorld() {
        return this.worldObj;
    }

    @Override
    public boolean canRenderOnFire() {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        if (source.getEntity() != null && !(source.getEntity() instanceof EntityPlayer)) {
            return this.attackEntityFromPart(this.getDefaultPart(), source, damage);
        }
        return false;
    }

    @Override
    public Entity[] getParts() {
        return this.aegarParts;
    }

    @Override
    public boolean attackEntityFromPart(EntityPart entity, DamageSource source, float damage) {
        if (this.worldObj.isRemote || source.isFireDamage() || source == DamageSource.drown) return false;
        if (this.getHurtTime() == 0) this.setHurtTime(10);

        if (source.canHarmInCreative()) {
            super.attackEntityFrom(source, damage);
            return true;
        }

        if (this.aegarCrystal.getHealth() <= 0) {
            damage *= 1.15F;
        } else {
            if (entity == this.aegarCrystal) {
                if (TragicConfig.allowMobSounds)
                    this.worldObj.playSoundAtEntity(this, "tragicmc:boss.aegar.harshhurt", 0.6F, 1.0F);
                damage *= 1.35F;
            } else {
                damage *= 0.95F;
            }
        }

        if (source.getEntity() instanceof EntityLivingBase
            && ((EntityLivingBase) source.getEntity()).getCreatureAttribute() != TragicEntities.Synapse)
            super.attackEntityFrom(source, damage);

        return true;
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        if (this.worldObj.isRemote || this.getAttackTime() != 0
            || this.getStunTicks() > 0
            || this.getDistanceToEntity(par1Entity) > 3.0F
            || this.getShockwaveTicks() > 0
            || this.getLaserTicks() > 0) return false;
        boolean result = super.attackEntityAsMob(par1Entity);
        if (result) {
            this.setAttackTime(20);
            par1Entity.motionX *= 2.35D;
            par1Entity.motionZ *= 2.35D;
            if (par1Entity instanceof EntityPlayer && TragicConfig.allowHacked && rand.nextInt(6) == 0)
                ((EntityPlayer) par1Entity).addPotionEffect(new PotionEffect(TragicPotion.Hacked.id, 120, 0));
        }
        return result;
    }

    private void attackEntitiesInList(List list) {
        for (int i = 0; i < list.size(); ++i) {
            Entity entity = (Entity) list.get(i);

            if (entity instanceof EntityLivingBase
                && ((EntityLivingBase) entity).getCreatureAttribute() != TragicEntities.Synapse) {
                entity.attackEntityFrom(DamageSource.causeMobDamage(this), 16.0F);
                if (entity instanceof EntityPlayer && TragicConfig.allowHacked && rand.nextInt(6) == 0)
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(TragicPotion.Hacked.id, 120, 0));

                entity.motionX *= 3.225D;
                entity.motionZ *= 3.225D;
                entity.motionY += 0.225D;
            }
        }
    }

    @Override
    protected void despawnEntity() {}

    @Override
    public EntityPart getDefaultPart() {
        return this.aegarBody;
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("hypermode")) this.setHypermode(tag.getBoolean("hypermode"));
        if (tag.hasKey("stunTicks")) this.setStunTicks(tag.getInteger("stunTicks"));
        if (tag.hasKey("shockwaveTicks")) this.setShockwaveTicks(tag.getInteger("shockwaveTicks"));
        if (tag.hasKey("attackTime")) this.setAttackTime(tag.getInteger("attackTime"));
        if (tag.hasKey("hurtTime")) this.setHurtTime(tag.getInteger("hurtTime"));
        if (tag.hasKey("laserTicks")) this.setLaserTicks(tag.getInteger("laserTicks"));
        if (tag.hasKey("mortorTicks")) this.setMortorTicks(tag.getInteger("mortorTicks"));
        if (tag.hasKey("autoTicks")) this.setAutoTicks(tag.getInteger("autoTicks"));
        if (tag.hasKey("crystalHealth")) this.aegarCrystal.setHealth(tag.getFloat("crystalHealth"));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setBoolean("hypermode", this.getHypermode());
        tag.setInteger("stunTicks", this.getStunTicks());
        tag.setInteger("shockwaveTicks", this.getShockwaveTicks());
        tag.setInteger("attackTime", this.getAttackTime());
        tag.setInteger("hurtTime", this.getHurtTime());
        tag.setInteger("laserTicks", this.getLaserTicks());
        tag.setInteger("mortorTicks", this.getMortorTicks());
        tag.setInteger("autoTicks", this.getAutoTicks());
        tag.setFloat("crystalHealth", this.aegarCrystal.getHealth());
    }

    @Override
    public String getLivingSound() {
        return TragicConfig.allowMobSounds
            ? (this.getHypermode() ? "tragicmc:boss.aegar.hyperwah" : "tragicmc:boss.aegar.wah")
            : null;
    }

    @Override
    public String getHurtSound() {
        return TragicConfig.allowMobSounds
            ? (this.getHypermode() ? "tragicmc:boss.aegar.harshhurt" : "tragicmc:boss.aegar.hurt")
            : super.getHurtSound();
    }

    @Override
    public String getDeathSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:boss.aegar.death" : null;
    }

    @Override
    public float getSoundPitch() {
        return 1.0F;
    }

    @Override
    public float getSoundVolume() {
        return 0.6F + rand.nextFloat() * 0.2F;
    }

    @Override
    protected void func_145780_a(int x, int y, int z, Block block) {

    }

    @Override
    public int getTalkInterval() {
        return 160;
    }

    @Override
    public void onDeath(DamageSource par1) {
        super.onDeath(par1);

        if (this.worldObj.isRemote) return;

        if (par1.getEntity() instanceof EntityPlayerMP && TragicConfig.allowAchievements && !this.getHypermode()) {
            ((EntityPlayerMP) par1.getEntity()).triggerAchievement(TragicAchievements.aegar);
        }
    }

    @Override
    public void dropFewItems(boolean flag, int l) {
        super.dropFewItems(flag, l);
        if (flag) this.capturedDrops.add(
            new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(TragicItems.SynapseLink)));
    }
}
