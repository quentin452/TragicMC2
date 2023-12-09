package tragicneko.tragicmc.entity.alpha;

import static tragicneko.tragicmc.TragicConfig.overlordCoreStats;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.google.common.collect.Sets;

import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.boss.TragicBoss;
import tragicneko.tragicmc.entity.mob.EntityNanoSwarm;
import tragicneko.tragicmc.entity.projectile.EntityOverlordMortor;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityOverlordCore extends TragicBoss {

    public double targetX;
    public double targetY;
    public double targetZ;

    public boolean forceNewTarget;
    public boolean slowed;
    private Entity target;

    private int hoverBuffer;
    private int aggregate;
    private int courseChangeCooldown;

    public static final Set ignoredBlocks = Sets.newHashSet(
        new Block[] { TragicBlocks.OverlordBarrier, Blocks.air, TragicBlocks.Luminescence, TragicBlocks.DigitalSea,
            TragicBlocks.Conduit, TragicBlocks.WitheringGas, TragicBlocks.CorruptedGas });
    public static final Set replaceableBlocks = Sets.newHashSet(new Block[] { Blocks.air, TragicBlocks.Luminescence });

    public EntityOverlordCore(World par1World) {
        super(par1World);
        this.setSize(4.2F, 3.8F);
        this.targetY = 50.0;
        this.noClip = true;
        this.ignoreFrustumCheck = true;
        this.experienceValue = 100;
    }

    @Override
    public boolean canRenderOnFire() {
        return false;
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return TragicEntities.Synapse;
    }

    @Override
    public boolean handleWaterMovement() {
        return false;
    }

    @Override
    public boolean isInvisible() {
        return this.getTransformationTicks() > 60 ? true : super.isInvisible();
    }

    @Override
    public void setAir(int i) {}

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(overlordCoreStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(overlordCoreStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(overlordCoreStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(overlordCoreStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(overlordCoreStats[4]);
    }

    @Override
    public int getTotalArmorValue() {
        return (int) overlordCoreStats[5];
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
    }

    public int getHoverTicks() {
        return this.dataWatcher.getWatchableObjectInt(16);
    }

    private void setHoverTicks(int i) {
        this.dataWatcher.updateObject(16, i);
    }

    private void decrementHoverTicks() {
        this.setHoverTicks(this.getHoverTicks() - 1);
    }

    public int getVulnerableTicks() {
        return this.dataWatcher.getWatchableObjectInt(17);
    }

    private void setVulnerableTicks(int i) {
        this.dataWatcher.updateObject(17, i);
    }

    private void decrementVulnerableTicks() {
        this.setVulnerableTicks(this.getVulnerableTicks() - 1);
    }

    public int getHurtTicks() {
        return this.dataWatcher.getWatchableObjectInt(18);
    }

    private void setHurtTicks(int i) {
        this.dataWatcher.updateObject(18, i);
    }

    private void decrementHurtTicks() {
        this.setHurtTicks(this.getHurtTicks() - 1);
    }

    public boolean isNearTarget() {
        return this.dataWatcher.getWatchableObjectInt(19) == 0;
    }

    private void setNearTarget(boolean flag) {
        this.dataWatcher.updateObject(19, flag ? 0 : 1);
    }

    public int getDropTicks() {
        return this.dataWatcher.getWatchableObjectInt(20);
    }

    private void setDropTicks(int i) {
        this.dataWatcher.updateObject(20, i);
    }

    private void decrementDropTicks() {
        this.setDropTicks(this.getDropTicks() - 1);
    }

    private void setTransformationTicks(int i) {
        this.dataWatcher.updateObject(21, i);
    }

    public int getTransformationTicks() {
        return this.dataWatcher.getWatchableObjectInt(21);
    }

    private void decrementTransformationTicks() {
        this.setTransformationTicks(this.getTransformationTicks() - 1);
    }

    @Override
    public void onLivingUpdate() {
        this.getLookHelper()
            .onUpdateLook();
        if (this.getTransformationTicks() > 0) this.motionX = this.motionY = this.motionZ = 0;

        double d0;
        double d1;
        double d2;
        double d10;
        if (this.worldObj.isRemote) {
            if (this.newPosRotationIncrements > 0) {
                d10 = this.posX + (this.newPosX - this.posX) / this.newPosRotationIncrements;
                d0 = this.posY + (this.newPosY - this.posY) / this.newPosRotationIncrements;
                d1 = this.posZ + (this.newPosZ - this.posZ) / this.newPosRotationIncrements;
                d2 = MathHelper.wrapAngleTo180_double(this.newRotationYaw - this.rotationYaw);
                this.rotationYaw = (float) (this.rotationYaw + d2 / this.newPosRotationIncrements);
                this.rotationPitch = (float) (this.rotationPitch
                    + (this.newRotationPitch - this.rotationPitch) / this.newPosRotationIncrements);
                --this.newPosRotationIncrements;
                this.setPosition(d10, d0, d1);
                this.setRotation(this.rotationYaw, this.rotationPitch);
            }

            double dr;
            double dr2;
            double dr3;
            double x;
            double y;
            double z;

            if (this.deathTime > 0) {
                for (int i = 0; i < 16; i++) {
                    dr = rand.nextDouble() - rand.nextDouble();
                    dr2 = rand.nextDouble() - rand.nextDouble();
                    dr3 = rand.nextDouble() - rand.nextDouble();
                    x = dr * 12.25 + this.posX;
                    y = dr2 * 12.25 + this.posY + this.height / 2.0D;
                    z = dr3 * 12.25 + this.posZ;
                    this.worldObj.spawnParticle("reddust", x, y, z, 0, 0, 0);
                }
                return;
            }

            int t = this.getTransformationTicks();

            if (t > 0) {
                float f = 0.33F;
                float f1 = 0.88F;
                float f2 = 0.94F;

                if (t <= 60) {
                    for (int i = 0; i < 12; i++) {
                        dr = rand.nextDouble() - rand.nextDouble();
                        dr2 = rand.nextDouble() - rand.nextDouble();
                        dr3 = rand.nextDouble() - rand.nextDouble();
                        x = dr * 2.25 + this.posX;
                        y = dr2 * 2.25 + this.posY + this.height / 2.0D;
                        z = dr3 * 2.25 + this.posZ;
                        this.worldObj.spawnParticle("reddust", x, y, z, f, f1, f2);
                    }

                    if (t >= 56) {
                        for (int i = 0; i < 24; i++) {
                            dr = rand.nextDouble() - rand.nextDouble();
                            dr2 = rand.nextDouble() - rand.nextDouble();
                            dr3 = rand.nextDouble() - rand.nextDouble();
                            x = dr * 6.25 + this.posX;
                            y = dr2 * 2.25 + this.posY + this.height / 2.0D;
                            z = dr3 * 6.25 + this.posZ;
                            this.worldObj.spawnParticle("reddust", x, y, z, f, f1, f2);
                        }
                    }

                    if (t <= 10) {
                        for (int i = 0; i < 24; i++) {
                            dr = rand.nextDouble() - rand.nextDouble();
                            dr2 = rand.nextDouble() - rand.nextDouble();
                            dr3 = rand.nextDouble() - rand.nextDouble();
                            x = dr * 6.25 + this.posX;
                            y = dr2 * 3.25 + this.posY + this.height / 2.0D;
                            z = dr3 * 6.25 + this.posZ;
                            this.worldObj.spawnParticle(
                                "reddust",
                                x,
                                y,
                                z,
                                rand.nextFloat(),
                                rand.nextFloat(),
                                rand.nextFloat());
                        }
                    }

                    if (t > 55) {
                        for (int i = 0; i < 4; i++) {
                            dr = rand.nextDouble() - rand.nextDouble();
                            dr2 = rand.nextDouble() - rand.nextDouble();
                            dr3 = rand.nextDouble() - rand.nextDouble();
                            x = dr * 4.25 + this.posX;
                            y = dr2 * 2.25 + this.posY + this.height / 2.0D;
                            z = dr3 * 4.25 + this.posZ;
                            this.worldObj.spawnParticle(
                                "hugeexplosion",
                                x,
                                y,
                                z,
                                rand.nextFloat(),
                                rand.nextFloat(),
                                rand.nextFloat());
                        }
                    }
                } else {
                    float r = (t - 60F) / 60F;
                    float r2 = 0.33F - (r * 0.22F);
                    float r3 = 0.88F - (r * 0.77F);
                    float r4 = 0.94F - (r * 0.83F);

                    for (int i = 0; i < 32; i++) {
                        dr = rand.nextDouble() - rand.nextDouble();
                        dr2 = rand.nextDouble() - rand.nextDouble();
                        dr3 = rand.nextDouble() - rand.nextDouble();
                        x = dr * 2.25 + this.posX;
                        y = dr2 * 2.25 + this.posY + rand.nextInt(6);
                        z = dr3 * 2.25 + this.posZ;
                        this.worldObj.spawnParticle("reddust", x, y, z, r2, r3, r4);
                    }

                    if (t >= 80) {
                        for (int i = 0; i < 32; i++) {
                            double d7 = (rand.nextDouble() * 10.0D - rand.nextDouble() * 10.0D);
                            double d8 = (rand.nextDouble() * 10.0D - rand.nextDouble() * 10.0D);
                            double d9 = (rand.nextDouble() * 10.0D - rand.nextDouble() * 10.0D);

                            d0 = d7 + this.posX;
                            d1 = d8 + this.posY + rand.nextInt(4);
                            d2 = d9 + this.posZ;

                            f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
                            double d3 = 0.5D;

                            double d4 = d0 / f2 * d3 * 0.100000011920929D + d7 * 0.10000000298023224D;
                            double d5 = d1 / f2 * d3 * 0.100000011920929D + d8 * 0.10000000298023224D;
                            double d6 = d2 / f2 * d3 * 0.100000011920929D + d9 * 0.10000000298023224D;

                            this.worldObj.spawnParticle("portal", d0, d1, d2, d4 * 15.5, d5 * 15.5, d6 * 15.5);
                        }
                    }
                }
                boolean flag2 = t <= 60 && t >= 20;
                for (int i = 0; i < 16; i++) {
                    dr = rand.nextDouble() - rand.nextDouble();
                    dr2 = rand.nextDouble() - rand.nextDouble();
                    dr3 = rand.nextDouble() - rand.nextDouble();
                    x = dr * 3.25 + this.posX;
                    y = dr2 * 3.25 + this.posY + rand.nextInt(6);
                    z = dr3 * 3.25 + this.posZ;
                    this.worldObj
                        .spawnParticle("reddust", x, y, z, flag2 ? 0F : 0.99F, flag2 ? 0F : 0.99F, flag2 ? 0F : 0.99F);
                }
                return;
            }
            int j = this.getHoverTicks() > 0 ? 24 : 12;
            boolean flag = this.getVulnerableTicks() > 0;

            float f = flag ? 0.0F : 0.33F;
            float f1 = flag ? 0.0F : 0.88F;
            float f2 = flag ? 0.0F : 0.94F;

            for (int i = 0; i < j; i++) {
                dr = rand.nextDouble() - rand.nextDouble();
                dr2 = rand.nextDouble() - rand.nextDouble();
                dr3 = rand.nextDouble() - rand.nextDouble();
                x = dr * 2.25 + this.posX;
                y = dr2 * 2.25 + this.posY + this.height / 2.0D;
                z = dr3 * 2.25 + this.posZ;
                this.worldObj.spawnParticle("reddust", x, y, z, f, f1, f2);
            }

            for (int i = 0; i < 6 && this.getHoverTicks() == 0; i++) {
                dr = rand.nextDouble() - rand.nextDouble();
                dr2 = rand.nextDouble() - rand.nextDouble();
                dr3 = rand.nextDouble() - rand.nextDouble();
                x = dr * 2.25 + this.posX;
                y = dr2 * 2.25 + this.posY + this.height / 2.0D;
                z = dr3 * 2.25 + this.posZ;
                this.worldObj.spawnParticle("reddust", x + dr3 * 2.25, y + dr2 * 2.25, z + dr * 2.25, 0.1F, 0.1F, 0.1F);
            }

            return;
        }

        if (this.worldObj.difficultySetting.getDifficultyId() == 0) this.setDead();
        if (this.deathTime > 0) return;
        if (this.getTransformationTicks() > 0) {
            this.decrementTransformationTicks();
            this.setHoverTicks(0);
            this.setVulnerableTicks(0);
            this.setHurtTicks(0);
            this.setNearTarget(false);
            this.setDropTicks(0);

            if (this.getTransformationTicks() == 199 && TragicConfig.allowMobSounds)
                this.playSound("tragicmc:boss.overlordcore.appearance", 1.0F, 1.0F);

            List<Entity> list = this.worldObj
                .getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(16.0, 12.0, 16.0));
            for (Entity e : list) this.applyEntityCollision(e);
            return;
        }
        if (this.getVulnerableTicks() > 0 && this.target != null) this.forceNewTarget = true;
        if (this.getVulnerableTicks() % 20 == 0 && this.getVulnerableTicks() > 0 && TragicConfig.allowMobSounds)
            this.worldObj.playSoundAtEntity(this, "tragicmc:boss.overlordcocoon.wah", 1.4F, 1.8F);

        if (this.target != null) {
            this.targetX = this.target.posX;
            this.targetZ = this.target.posZ;
            double d3 = this.targetX - this.posX;
            double d5 = this.targetZ - this.posZ;
            double d7 = Math.sqrt(d3 * d3 + d5 * d5);
            double d8 = 0.4000000059604645D + d7 / 80.0D - 1.0D;

            if (d8 > 10.0D) d8 = 10.0D;

            this.targetY = this.target.boundingBox.minY + d8;
        } else {
            this.targetX += this.rand.nextGaussian() * 2.0D;
            this.targetZ += this.rand.nextGaussian() * 2.0D;
        }

        if (this.isNearTarget()) this.setNearTarget(false);
        if (this.target != null && this.getDistanceToEntity(this.target) <= 5.0) {
            this.setNearTarget(true);
            if (rand.nextInt(16) == 0 && this.getHoverTicks() == 0
                && this.hoverBuffer == 0
                && this.getVulnerableTicks() == 0
                && this.getDistanceToEntity(this.target) <= 10.0
                && this.getDropTicks() == 0) {
                this.setDropTicks(120 + rand.nextInt(60));
                this.mountEntity(this.target);
            }
        }

        d0 = this.targetX - this.posX;
        d1 = this.targetY - this.posY;
        d2 = this.targetZ - this.posZ;
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;
        double d4 = this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .getAttributeValue();

        if (this.forceNewTarget || d3 < 100.0D
            || d3 > 22500.0D
            || this.isCollidedHorizontally
            || this.isCollidedVertically) this.setNewTarget();

        if (this.target != null) {
            float f = 8.0F;
            if (this.canEntityBeSeen(this.target) && this.getDistanceToEntity(this.target) > d4) f = 24.0F;

            this.targetX = this.target.posX + (this.rand.nextFloat() * 2.0F - 1.0F) * f;
            this.targetY = this.target.posY + (this.rand.nextFloat() * 2.0F - 1.0F) * f;
            this.targetZ = this.target.posZ + (this.rand.nextFloat() * 2.0F - 1.0F) * f;

            if (this.getDropTicks() == 0 && this.getVulnerableTicks() == 0) {
                if (this.rand.nextInt(512) == 0 && this.hoverBuffer == 0
                    || this.aggregate >= 10 && this.hoverBuffer == 0
                        && rand.nextInt(10) == 0
                        && this.getDistanceToEntity(this.target) <= d4
                        && this.canEntityBeSeen(this.target))
                    this.setHoverTicks(300 + rand.nextInt(120));
            }
        } else {
            this.targetX = this.posX + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
            this.targetY = (this.worldObj.getActualHeight() / 4)
                + (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.targetZ = this.posZ + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
        }

        if (this.courseChangeCooldown-- <= 0) {
            this.courseChangeCooldown += this.rand.nextInt(5) + 3;
            d3 = MathHelper.sqrt_double(d3);
            double d5 = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
                .getAttributeValue();

            this.motionX += d0 / d3 * d5 * 0.5;
            this.motionY += d1 / d3 * d5 * 0.5;
            this.motionZ += d2 / d3 * d5 * 0.5;
        }

        if (this.target != null) {
            double d5 = this.target.posX - this.posX;
            double d7 = this.target.posZ - this.posZ;
            this.renderYawOffset = this.rotationYaw = -((float) Math.atan2(d5, d7)) * 180.0F / (float) Math.PI;
        } else {
            this.renderYawOffset = this.rotationYaw = -((float) Math.atan2(this.motionX, this.motionZ)) * 180.0F
                / (float) Math.PI;
        }

        double m = this.motionX;
        double m2 = this.motionY;
        double m3 = this.motionZ;

        if (this.slowed) {
            m *= 0.900000011920929D;
            m2 *= 0.900000011920929D;
            m3 *= 0.900000011920929D;
        }

        if (this.getVulnerableTicks() > 0 && !this.slowed) {
            m *= 0.455D;
            m2 *= 0.455D;
            m3 *= 0.455D;
        }

        this.moveEntity(m, m2, m3);

        if (this.hoverBuffer > 0) --this.hoverBuffer;

        if (this.getHoverTicks() > 0) {
            this.decrementHoverTicks();
            this.motionX = this.motionZ = this.motionY = 0.0F;
            if (this.target == null || this.getDistanceToEntity(this.target) > d4
                || !this.canEntityBeSeen(this.target)) {
                this.setHoverTicks(0);
                this.hoverBuffer = 100;
            }
            if (this.target != null && this.getHoverTicks() > 60 && this.getHoverTicks() % 10 == 0)
                this.createMortors();
            if (this.ticksExisted % 10 == 0 && this.getHealth() < this.getMaxHealth())
                this.healByFactorRanged(5.0F, 5.0F, 30.0F);

            if (this.getHoverTicks() == 0) this.hoverBuffer = 200;
            this.aggregate = 0;
            if (this.ticksExisted % 20 == 0 && TragicConfig.allowMobSounds)
                this.worldObj.playSoundAtEntity(this, "tragicmc:boss.overlordcore.vulnerable", 1.8F, 1.0F);
        }

        if (this.getDropTicks() > 0) {
            this.decrementDropTicks();
            this.motionX = this.motionZ = 0.0F;
            this.motionY = 0.1F;

            if (this.ridingEntity != null && this.getDropTicks() > 10) {
                if (this.getDropTicks() % 10 == 0) {
                    this.ridingEntity
                        .attackEntityFrom(DamageSource.causeMobDamage(this), (float) overlordCoreStats[2] / 2);
                    this.heal(1.0F);
                }

                this.ridingEntity.motionY = 0.2D;
                this.ridingEntity.velocityChanged = true;
                this.ridingEntity.fallDistance = 0.0F;
            }

            if (this.getDropTicks() <= 10) this.mountEntity(null);
            if (this.getDropTicks() > 80 && this.ridingEntity == null) {
                this.mountEntity(null);
                this.setDropTicks(0);
                this.aggregate++;
            }

            if (this.getDropTicks() == 0) this.hoverBuffer = 200;
        } else if (this.ridingEntity != null) {
            this.mountEntity(null);
        }

        if (this.getVulnerableTicks() > 0) {
            this.decrementVulnerableTicks();
            if (this.getVulnerableTicks() % 10 == 0 && TragicConfig.allowMobSounds)
                this.worldObj.playSoundAtEntity(this, "tragicmc:boss.overlordcocoon.wah", 1.4F, 1.5F);
        }

        if (this.getHurtTicks() > 0) this.decrementHurtTicks();

        if (this.getHurtTicks() == 0 && this.getHoverTicks() == 0
            && this.getDropTicks() == 0
            && this.getTransformationTicks() == 0
            && this.hoverBuffer >= 100)
            this.attackEntitiesInList(
                this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(1.0D, 1.0D, 1.0D)));

        this.slowed = this.destroyBlocksInAABB(this.boundingBox);

        if (this.getHealth() <= this.getMaxHealth() / 4 && this.ticksExisted % 20 == 0
            && rand.nextBoolean()
            && this.worldObj.getEntitiesWithinAABB(EntityNanoSwarm.class, this.boundingBox.expand(64.0, 64.0, 64.0D))
                .size() < 16) {
            EntityNanoSwarm swarm = new EntityNanoSwarm(this.worldObj);
            swarm.setPosition(this.posX, this.posY, this.posZ);
            this.worldObj.spawnEntityInWorld(swarm);
        }

        if (this.getHealth() <= this.getMaxHealth() / 2 && this.ticksExisted % 10 == 0
            && rand.nextInt(16) == 0
            && this.getVulnerableTicks() > 0) {
            this.createMortors();
        }

        int x = (int) (this.posX + rand.nextInt(2) - rand.nextInt(2));
        int y = (int) (this.posY + rand.nextInt(2) - rand.nextInt(2));
        int z = (int) (this.posZ + rand.nextInt(2) - rand.nextInt(2));
        if (replaceableBlocks.contains(worldObj.getBlock(x, y, z)))
            this.worldObj.setBlock(x, y, z, TragicBlocks.Luminescence);

        this.motionX *= 0.98D;
        this.motionY *= 0.98D;
        this.motionZ *= 0.98D;

        if (TragicConfig.allowMobSounds && this.ticksExisted % 20 == 0)
            this.playSound("tragicmc:boss.overlordcore.heartbeat", 0.3F, 1.0F);
    }

    private boolean destroyBlocksInAABB(AxisAlignedBB bb) {
        int i = MathHelper.floor_double(bb.minX);
        int j = MathHelper.floor_double(bb.minY);
        int k = MathHelper.floor_double(bb.minZ);
        int l = MathHelper.floor_double(bb.maxX);
        int i1 = MathHelper.floor_double(bb.maxY);
        int j1 = MathHelper.floor_double(bb.maxZ);
        boolean flag = false;
        boolean flag1 = false;

        for (int k1 = i; k1 <= l; ++k1) {
            for (int l1 = j; l1 <= i1; ++l1) {
                for (int i2 = k; i2 <= j1; ++i2) {
                    Block block = this.worldObj.getBlock(k1, l1, i2);
                    float f = block.getBlockHardness(this.worldObj, k1, l1, i2);

                    if (block.getMaterial()
                        .blocksMovement()) {
                        if (this.worldObj.getGameRules()
                            .getGameRuleBooleanValue("mobGriefing") && !ignoredBlocks.contains(block)
                            && f > 0F
                            && f < 16F) {
                            flag1 = this.worldObj.setBlockToAir(k1, l1, i2) || flag1;
                        } else {
                            flag = true;
                        }
                    }
                }
            }
        }

        return flag;
    }

    private void attackEntitiesInList(List list) {
        for (int i = 0; i < list.size(); ++i) {
            Entity entity = (Entity) list.get(i);
            float f = (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
                .getAttributeValue();
            if (entity instanceof EntityLivingBase
                && ((EntityLivingBase) entity).getCreatureAttribute() != TragicEntities.Synapse
                && !entity.equals(this.ridingEntity))
                entity.attackEntityFrom(
                    DamageSource.causeMobDamage(this),
                    this.getVulnerableTicks() > 0 ? MathHelper.ceiling_float_int(f / 2) : f);
        }
    }

    private void setNewTarget() {
        this.forceNewTarget = false;
        boolean retry = !this.worldObj.playerEntities.isEmpty() && this.getVulnerableTicks() == 0;

        if (retry) {
            Entity ent = (Entity) this.worldObj.playerEntities
                .get(this.rand.nextInt(this.worldObj.playerEntities.size()));

            if (ent instanceof EntityPlayer && !((EntityPlayer) ent).capabilities.isCreativeMode) {
                this.target = ent;
                retry = false;
            }

            if (retry) {
                double d0 = this.getEntityAttribute(SharedMonsterAttributes.followRange)
                    .getAttributeValue();
                List<Entity> list = this.worldObj
                    .getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(d0, d0, d0));

                for (Entity e : list) {
                    if (e instanceof EntityLivingBase
                        && ((EntityLivingBase) e).getCreatureAttribute() != TragicEntities.Synapse) {
                        if (e instanceof EntityPlayer && ((EntityPlayer) e).capabilities.isCreativeMode) continue;
                        this.target = e;
                        retry = false;
                        break;
                    }
                }
            }
        }

        if (retry) {
            boolean flag = false;

            do {
                this.targetX = 0.0D;
                this.targetY = 50.0F + this.rand.nextFloat() * 50.0F;
                this.targetZ = 0.0D;
                this.targetX += this.rand.nextFloat() * 120.0F - 60.0F;
                this.targetZ += this.rand.nextFloat() * 120.0F - 60.0F;
                double d0 = this.posX - this.targetX;
                double d1 = this.posY - this.targetY;
                double d2 = this.posZ - this.targetZ;
                flag = d0 * d0 + d1 * d1 + d2 * d2 > 100.0D;
            } while (!flag);

            this.target = null;
        }
    }

    private void createMortors() {
        if (this.target == null || this.getHealth() <= 0) return;

        double d0 = this.target.posX - this.posX;
        double d1 = rand.nextInt(4);
        double d2 = this.target.posZ - this.posZ;
        float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.target)) * 0.975F;

        EntityOverlordMortor mortor = new EntityOverlordMortor(
            this.worldObj,
            this,
            d0 + this.rand.nextGaussian() * f1,
            d1,
            d2 + this.rand.nextGaussian() * f1);
        mortor.posY += (rand.nextDouble() * this.height) + d1 * 0.04335D;
        mortor.posX += d0 * 0.04335D;
        mortor.posZ += d2 * 0.04335D;
        mortor.motionY += rand.nextFloat() - rand.nextFloat();
        mortor.motionX += rand.nextFloat() - rand.nextFloat();
        mortor.motionZ += rand.nextFloat() - rand.nextFloat();
        this.worldObj.spawnEntityInWorld(mortor);
    }

    @Override
    protected void despawnEntity() {}

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    public void fall(float f) {}

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("targetX")) this.targetX = tag.getDouble("targetX");
        if (tag.hasKey("targetY")) this.targetY = tag.getDouble("targetY");
        if (tag.hasKey("targetZ")) this.targetZ = tag.getDouble("targetZ");
        if (tag.hasKey("hoverTicks")) this.setHoverTicks(tag.getInteger("hoverTicks"));
        if (tag.hasKey("hoverBuffer")) this.hoverBuffer = tag.getInteger("hoverBuffer");
        if (tag.hasKey("aggregate")) this.aggregate = tag.getInteger("aggregate");
        if (tag.hasKey("hurtTicks")) this.setHurtTicks(tag.getInteger("hurtTicks"));
        if (tag.hasKey("vulnerableTicks")) this.setVulnerableTicks(tag.getInteger("vulnerableTicks"));
        if (tag.hasKey("dropTicks")) this.setDropTicks(tag.getInteger("dropTicks"));
        if (tag.hasKey("transformationTicks")) this.setTransformationTicks(tag.getInteger("transformationTicks"));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setDouble("targetX", this.targetX);
        tag.setDouble("targetY", this.targetY);
        tag.setDouble("targetZ", this.targetZ);
        tag.setInteger("hoverTicks", this.getHoverTicks());
        tag.setInteger("hoverBuffer", this.hoverBuffer);
        tag.setInteger("aggregate", this.aggregate);
        tag.setInteger("hurtTicks", this.getHurtTicks());
        tag.setInteger("vulnerableTicks", this.getVulnerableTicks());
        tag.setInteger("dropTicks", this.getDropTicks());
        tag.setInteger("transformationTicks", this.getTransformationTicks());
    }

    @Override
    public boolean attackEntityFrom(DamageSource src, float dmg) {
        if (this.getTransformationTicks() > 0) return false;

        if (src.getEntity() instanceof EntityLivingBase && !this.worldObj.isRemote) {
            EntityLivingBase entity = (EntityLivingBase) src.getEntity();
            boolean flag = TragicConfig.allowDivinity && entity.isPotionActive(TragicPotion.Divinity);

            if (flag || !TragicConfig.allowDivinity && entity.getCreatureAttribute() != TragicEntities.Synapse
                || this.getVulnerableTicks() > 0 && entity.getCreatureAttribute() != TragicEntities.Synapse
                || entity.getHeldItem() != null && entity.getHeldItem()
                    .getItem() == TragicItems.SwordOfJustice
                || src.canHarmInCreative()) {
                if (rand.nextBoolean() && this.worldObj
                    .getEntitiesWithinAABB(EntityNanoSwarm.class, this.boundingBox.expand(64.0, 64.0, 64.0D))
                    .size() < 16) {
                    EntityNanoSwarm swarm = new EntityNanoSwarm(this.worldObj);
                    swarm.setPosition(this.posX, this.posY, this.posZ);
                    this.worldObj.spawnEntityInWorld(swarm);
                }

                if (this.getHoverTicks() > 0) {
                    this.setHoverTicks(0);
                    this.forceNewTarget = true;
                    this.hoverBuffer = 100;
                }

                if (this.getDropTicks() > 0) {
                    this.setDropTicks(0);
                    this.forceNewTarget = true;
                    this.hoverBuffer = 100;
                }

                if (flag && this.getVulnerableTicks() == 0) {
                    this.setVulnerableTicks(120 + rand.nextInt(40));
                    if (TragicConfig.allowMobSounds) this.playSound("tragicmc:boss.overlordcore.expose", 1.0F, 1.0F);
                }
                if (this.getHurtTicks() == 0) this.setHurtTicks(40);
                if (this.ridingEntity != null) this.mountEntity(null);

                return super.attackEntityFrom(src, dmg);
            } else if (TragicConfig.allowMobSounds) {
                this.playSound("tragicmc:boss.overlordcore.negate", 1.0F, 1.0F);
            }

            ++aggregate;
            if (aggregate >= 10) {
                this.forceNewTarget = true;
                if (this.getDropTicks() > 0) this.setDropTicks(0);
            }

            if (rand.nextInt(4) == 0 && this.target != entity
                && entity.getCreatureAttribute() != TragicEntities.Synapse) this.target = entity;
        }

        return true;
    }

    @Override
    public void onDeath(DamageSource par1DamageSource) {
        if (!this.worldObj.isRemote) {
            ArrayList<int[]> list = WorldHelper.getBlocksInCircularRange(
                this.worldObj,
                2.5,
                ((int) this.posX) + 0.5,
                ((int) this.posY) - 0.5,
                ((int) this.posZ) + 0.5);
            for (int[] coords : list) {
                if (replaceableBlocks.contains(this.worldObj.getBlock(coords[0], coords[1], coords[2]))) {
                    this.worldObj.setBlock(
                        coords[0],
                        coords[1],
                        coords[2],
                        !TragicConfig.allowNonMobBlocks ? Blocks.obsidian : TragicBlocks.CelledBlock,
                        0,
                        2);
                }
            }
        }

        super.onDeath(par1DamageSource);

        if (par1DamageSource.getEntity() instanceof EntityPlayerMP)
            ((EntityPlayerMP) par1DamageSource.getEntity()).triggerAchievement(TragicAchievements.overlord4);
        List<EntityPlayerMP> list = this.worldObj
            .getEntitiesWithinAABB(EntityPlayerMP.class, this.boundingBox.expand(24.0, 24.0, 24.0));
        if (!list.isEmpty()) {
            for (EntityPlayerMP mp : list) {
                mp.triggerAchievement(TragicAchievements.overlord4);
            }
        }
    }

    @Override
    protected void dropFewItems(boolean flag, int l) {
        super.dropFewItems(flag, l);

        if (!this.worldObj.isRemote && TragicConfig.allowMobStatueDrops
            && rand.nextInt(100) <= TragicConfig.mobStatueDropChance)
            this.capturedDrops.add(
                new EntityItem(
                    this.worldObj,
                    this.posX,
                    this.posY,
                    this.posZ,
                    new ItemStack(TragicItems.MobStatue, 1, 17)));
        if (!this.worldObj.isRemote && this.getAllowLoot()) this.capturedDrops
            .add(new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(TragicItems.Sentinel)));
    }

    public void setStartTransform() {
        this.setTransformationTicks(200);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        this.setStartTransform();
        return super.onSpawnWithEgg(data);
    }

    @Override
    public void addPotionEffect(PotionEffect pe) {}

    @Override
    protected void onDeathUpdate() {
        ++this.deathTime;

        List<Entity> list = this.worldObj
            .getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(16.0, 16.0, 16.0));
        for (Entity e : list) {
            if (e instanceof EntityLivingBase
                && ((EntityLivingBase) e).getCreatureAttribute() == TragicEntities.Synapse) {
                e.setDead();
            }
        }

        if (this.deathTime >= 300) {
            int i;

            if (!this.worldObj.isRemote && (this.recentlyHit > 0 || this.isPlayer())
                && this.func_146066_aG()
                && this.worldObj.getGameRules()
                    .getGameRuleBooleanValue("doMobLoot")) {
                i = this.getExperiencePoints(this.attackingPlayer);

                while (i > 0) {
                    int j = EntityXPOrb.getXPSplit(i);
                    i -= j;
                    this.worldObj
                        .spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
                }
            }

            this.setDead();

            for (int ji = 0; ji < 20; ++ji) {
                this.worldObj.spawnParticle(
                    "hugeexplosion",
                    this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width,
                    this.posY + this.rand.nextFloat() * this.height * 2.0F,
                    this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width,
                    0.1,
                    0.1,
                    0.1);
            }

            for (int j = 0; j < 120; ++j) {
                this.worldObj.spawnParticle(
                    "reddust",
                    this.posX + this.rand.nextFloat() * this.width * 3.0F - this.width,
                    this.posY + this.rand.nextFloat() * this.height * 2.0F,
                    this.posZ + this.rand.nextFloat() * this.width * 3.0F - this.width,
                    0,
                    0,
                    0);
            }
        }

        for (int j = 0; j < 40; ++j) {
            this.worldObj.spawnParticle(
                "reddust",
                this.posX + this.rand.nextFloat() * this.width * 5.0F - this.width,
                this.posY + this.rand.nextFloat() * this.height * 2.0F,
                this.posZ + this.rand.nextFloat() * this.width * 5.0F - this.width,
                0,
                0,
                0);
        }

        for (int ji = 0; ji < 40; ++ji) {
            this.worldObj.spawnParticle(
                "reddust",
                this.posX + this.rand.nextFloat() * this.width * 5.0F - this.width,
                this.posY + this.rand.nextFloat() * this.height * 2.0F,
                this.posZ + this.rand.nextFloat() * this.width * 5.0F - this.width,
                0.1,
                0.1,
                0.1);
        }

        if (this.deathTime % 5 == 0) {
            for (int ji = 0; ji < 20; ++ji) {
                this.worldObj.spawnParticle(
                    "largeexplode",
                    this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width,
                    this.posY + this.rand.nextFloat() * this.height * 2.0F,
                    this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width,
                    0.1,
                    0.1,
                    0.1);
            }
        }
    }

    public int getDistanceToGround() {
        int x = MathHelper.floor_double(this.posX);
        int y = MathHelper.floor_double(this.boundingBox.minY);
        int z = MathHelper.floor_double(this.posZ);

        for (int i = 0; y - i > 0; ++i) {
            if (this.worldObj.getBlock(x, y - i, z)
                .getMaterial()
                .blocksMovement()) return i;
        }

        return y;
    }

    @Override
    public String getLivingSound() {
        return this.getTransformationTicks() > 0 ? null
            : (this.getVulnerableTicks() > 0 ? null : "tragicmc:boss.overlordcore.living");
    }

    @Override
    public String getHurtSound() {
        return this.getTransformationTicks() > 0 || this.getVulnerableTicks() == 0 ? null
            : "tragicmc:boss.overlordcore.hit";
    }

    @Override
    public String getDeathSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:boss.overlordcore.death" : null;
    }

    @Override
    public float getSoundPitch() {
        return 1.0F;
    }

    @Override
    public float getSoundVolume() {
        return 1.8F;
    }

    @Override
    protected void func_145780_a(int x, int y, int z, Block block) {}

    @Override
    public int getTalkInterval() {
        return super.getTalkInterval();
    }
}
