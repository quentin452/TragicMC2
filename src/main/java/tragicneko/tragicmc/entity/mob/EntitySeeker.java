package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.seekerStats;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.entity.EntityAIWatchTarget;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCocoon;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCombat;
import tragicneko.tragicmc.util.DamageHelper;

public class EntitySeeker extends TragicMob {

    private int timeSinceTarget;
    private boolean shouldRelocate;
    private EntityOverlordCocoon owner = null;
    private byte relocations;
    private boolean hasDamagedEntity = false;

    public EntitySeeker(World par1World) {
        super(par1World);
        this.setSize(0.625F, 1.225F);
        this.stepHeight = 1.0F;
        this.experienceValue = 10;
        this.tasks.addTask(4, new EntityAIWatchTarget(this, 64.0F));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(
            3,
            new EntityAINearestAttackableTarget(
                this,
                EntityLivingBase.class,
                0,
                true,
                false,
                EntityOverlordCombat.selec));
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(seekerStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(seekerStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(seekerStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(seekerStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(seekerStats[4]);
    }

    @Override
    public int getTotalArmorValue() {
        return (int) seekerStats[5];
    }

    @Override
    protected boolean isChangeAllowed() {
        return false;
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return TragicEntities.Synapse;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, Integer.valueOf(0)); // kill ticks
        this.dataWatcher.addObject(17, Integer.valueOf(0)); // target id
    }

    private void setKillTicks(int i) {
        this.dataWatcher.updateObject(16, i);
    }

    public int getKillTicks() {
        return this.dataWatcher.getWatchableObjectInt(16);
    }

    private void setTargetId(int i) {
        this.dataWatcher.updateObject(17, i);
    }

    public int getTargetId() {
        return this.dataWatcher.getWatchableObjectInt(17);
    }

    @Override
    public void onLivingUpdate() {
        this.motionX = this.motionY = this.motionZ = 0D;
        super.onLivingUpdate();

        if (this.worldObj.isRemote) {
            Entity entity = this.worldObj.getEntityByID(this.getTargetId());
            if (entity != null && this.getKillTicks() > 0) {
                double d0 = entity.posX - this.posX;
                double d1 = entity.posY - this.posY;
                double d2 = entity.posZ - this.posZ;

                for (int l = 0; l < 4; l++) {
                    double d3 = 0.23D * l + (rand.nextDouble() * 0.25D);
                    this.worldObj.spawnParticle(
                        "reddust",
                        this.posX + d0 * d3,
                        this.posY + d1 * d3 + 0.75D,
                        this.posZ + d2 * d3,
                        0.0,
                        0.0,
                        0.0);
                    if (this.getKillTicks() >= 300) this.worldObj.spawnParticle(
                        "flame",
                        this.posX + d0 * d3,
                        this.posY + d1 * d3 + 0.75D,
                        this.posZ + d2 * d3,
                        0.0,
                        0.0,
                        0.0);
                }

                if (this.getKillTicks() >= 300) {
                    for (int i = 0; i < 4; i++) {
                        this.worldObj.spawnParticle(
                            "flame",
                            this.posX + rand.nextDouble() - rand.nextDouble(),
                            this.posY + 0.25 + rand.nextDouble() * 0.5,
                            this.posZ + rand.nextDouble() - rand.nextDouble(),
                            0.0,
                            0.0,
                            0.0);
                    }
                }
            }
            return;
        }

        if (this.getAttackTarget() != null) {
            this.timeSinceTarget = 0;
            this.setTargetId(
                this.getAttackTarget()
                    .getEntityId());
            if (this.canEntityBeSeen(this.getAttackTarget())) {
                if (this.getKillTicks() < 400) this.setKillTicks(this.getKillTicks() + 1);

                if (this.getKillTicks() >= 300) {
                    if (this.getAttackTarget()
                        .attackEntityFrom(
                            DamageHelper.causeModMagicDamageToEntity(this),
                            Math.max(
                                this.getAttackTarget()
                                    .getMaxHealth() / 20F,
                                2.0F))) {
                        this.hasDamagedEntity = true;
                    }

                }

                List<Entity> list = this.worldObj
                    .getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(64.0, 64.0, 64.0));
                for (Entity e : list) {
                    if (this.getAttackTarget() == null) break;
                    if (e instanceof EntityLivingBase
                        && ((EntityLivingBase) e).getCreatureAttribute() == TragicEntities.Synapse
                        && ((EntityLiving) e).getAttackTarget() == null
                        && ((EntityLivingBase) e).getCreatureAttribute() != TragicEntities.Synapse) {
                        ((EntityLiving) e).setAttackTarget(this.getAttackTarget());
                    }
                }
            } else {
                if (this.ticksExisted % 2 == 0 && this.getKillTicks() > 0) this.setKillTicks(this.getKillTicks() - 1);
                if (this.getKillTicks() == 0) this.setAttackTarget(null);
            }

            if (this.getAttackTarget() == null || this.getDistanceToEntity(this.getAttackTarget()) >= 64.0D
                || this.getAttackTarget().isDead
                || this.getAttackTarget()
                    .getHealth() <= 0F
                || this.worldObj.getEntityByID(this.getTargetId()) == null) {
                this.setKillTicks(0);
                this.setAttackTarget(null);
            }
        } else {
            if (this.getKillTicks() > 0) this.setKillTicks(this.getKillTicks() - 1);
            this.timeSinceTarget++;
            if (this.timeSinceTarget >= 200) this.setKillTicks(0);
            this.setTargetId(0);
        }

        if (this.getOwner() != null) {
            if (this.getDistanceToEntity(this.getOwner()) >= 32.0 || this.getDistanceToEntity(this.getOwner()) <= 4.0
                || !this.canEntityBeSeen(this.getOwner())) this.shouldRelocate = true;
            if (this.getOwner().isDead) this.setOwner(null);
        }

        if (this.shouldRelocate) {
            this.shouldRelocate = false;
            this.teleportRandomly();
            this.relocations++;
        }

        if (this.relocations >= 10) {
            if (this.getOwner() == null) {
                this.setDead();
            } else {
                this.setPosition(this.getOwner().posX, this.getOwner().posY, this.getOwner().posZ);
            }
            this.relocations = 0;
        }

        if (this.ticksExisted % 20 == 0 && this.getAttackTarget() != null && this.getKillTicks() > 0) {
            float f = this.getKillTicks() / 300.0F;
            if (TragicConfig.allowMobSounds) {
                this.playSound("tragicmc:mob.seeker.tone", f, f * 1.9F);
                this.worldObj.playSoundAtEntity(this.getAttackTarget(), "tragicmc:mob.seeker.tone", f, f * 1.9F);
            }
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource src, float dmg) {
        if (!this.worldObj.isRemote && src == DamageSource.inWall) this.shouldRelocate = true;
        return super.attackEntityFrom(src, dmg);
    }

    @Override
    public void fall(float par1) {}

    @Override
    public void updateFallState(double par1, boolean par2) {}

    protected boolean teleportRandomly() {
        double d0 = this.posX + (this.rand.nextDouble() * 8.0D);
        double d1 = this.posY + (this.rand.nextInt(8) - 8) + 4;
        double d2 = this.posZ + (this.rand.nextDouble() * 8.0D);
        return this.teleportTo(d0, d1, d2);
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
            return true;
        }
    }

    public void setOwner(EntityOverlordCocoon cc) {
        this.owner = cc;
    }

    public EntityOverlordCocoon getOwner() {
        return this.owner;
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("owner") && this.worldObj.getEntityByID(tag.getInteger("owner")) instanceof EntityOverlordCocoon)
            this.setOwner((EntityOverlordCocoon) this.worldObj.getEntityByID(tag.getInteger("owner")));
        if (tag.hasKey("killTicks")) this.setKillTicks(tag.getInteger("killTicks"));
        if (tag.hasKey("relocations")) this.relocations = tag.getByte("relocations");
        if (tag.hasKey("targetlessTime")) this.timeSinceTarget = tag.getInteger("targetlessTime");
        if (tag.hasKey("shouldRelocate")) this.shouldRelocate = tag.getBoolean("shouldRelocate");
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        if (this.getOwner() != null) tag.setInteger(
            "owner",
            this.getOwner()
                .getEntityId());
        tag.setInteger("killTicks", this.getKillTicks());
        tag.setInteger("targetID", this.getTargetId());
        tag.setByte("relocations", this.relocations);
        tag.setInteger("targetlessTime", this.timeSinceTarget);
        tag.setBoolean("shouldRelocate", this.shouldRelocate);
    }

    @Override
    protected void func_145780_a(int x, int y, int z, Block block) {

    }

    @Override
    public void onDeath(DamageSource src) {
        super.onDeath(src);

        if (!this.hasDamagedEntity && src.getEntity() instanceof EntityPlayerMP && TragicConfig.allowAchievements) {
            ((EntityPlayerMP) src.getEntity()).triggerAchievement(TragicAchievements.seeker);
        }
    }
}
