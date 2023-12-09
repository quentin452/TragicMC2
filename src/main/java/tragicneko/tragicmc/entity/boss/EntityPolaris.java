package tragicneko.tragicmc.entity.boss;

import static tragicneko.tragicmc.TragicConfig.polarisStats;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
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
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.entity.EntityAIWatchTarget;

public class EntityPolaris extends TragicBoss {

    private EntityAIBase fearGolems = new EntityAIAvoidEntity(this, EntityGolem.class, 6.0F, 1.0D, 1.2D);

    public EntityPolaris(World par1World) {
        super(par1World);
        this.setSize(0.5F, 2.075F);
        this.stepHeight = 2.0F;
        this.experienceValue = 50;
        this.getNavigator()
            .setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchTarget(this, 48.0F));
        this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 0.75D, 32.0F));
        if (TragicConfig.polarisFearGolems) this.tasks.addTask(1, fearGolems);
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return TragicEntities.Natural;
    }

    @Override
    public boolean canRenderOnFire() {
        return super.canRenderOnFire() && !this.isInvisible();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float par1) {
        return !getDaytime() ? 15728880 : super.getBrightnessForRender(par1);
    }

    @Override
    public float getBrightness(float par1) {
        return !getDaytime() ? 1.0F : super.getBrightness(par1);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(polarisStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(polarisStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(polarisStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(polarisStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(polarisStats[4]);
    }

    @Override
    public void onDeath(DamageSource src) {
        super.onDeath(src);
        if (src.getEntity() instanceof EntityPlayerMP && TragicConfig.allowAchievements)
            ((EntityPlayerMP) src.getEntity()).triggerAchievement(TragicAchievements.polaris);
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
                    new ItemStack(TragicItems.MobStatue, 1, 5)));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, Integer.valueOf(0));
        this.dataWatcher.addObject(17, Integer.valueOf(0));
        this.dataWatcher.addObject(18, Integer.valueOf(0));
    }

    public int getAttackTime() {
        return this.dataWatcher.getWatchableObjectInt(16);
    }

    private void setAttackTime(int i) {
        this.dataWatcher.updateObject(16, i);
    }

    private void decrementAttackTime() {
        int pow = this.getAttackTime();
        this.setAttackTime(--pow);
    }

    /**
     * Returns true if it is currently day time
     * 
     * @return
     */
    public boolean getDaytime() {
        return this.dataWatcher.getWatchableObjectInt(17) == 0;
    }

    private void setDaytime(boolean flag) {
        this.dataWatcher.updateObject(17, flag ? 0 : 1);
    }

    public boolean isClone() {
        return this.dataWatcher.getWatchableObjectInt(18) == 1;
    }

    public void setClone() {
        this.dataWatcher.updateObject(18, 1);
    }

    @Override
    public void onLivingUpdate() {
        if (!this.worldObj.isRemote) {
            if (this.worldObj.isDaytime() && !this.getDaytime()) {
                this.setDaytime(true);
            } else if (!this.worldObj.isDaytime() && this.getDaytime()) {
                this.setDaytime(false);
            }
        }

        super.onLivingUpdate();

        if (this.worldObj.isRemote) return;
        if (this.getAttackTime() > 0) this.decrementAttackTime();
        if (this.isClone() && this.tasks.taskEntries.contains(fearGolems)) this.tasks.taskEntries.remove(fearGolems);
        if (this.isInWater()) this.teleportRandomly();

        if (this.ticksExisted % 240 == 0 && TragicConfig.polarisRegeneration) this.heal(3.0F);

        if (this.getAttackTarget() != null && !this.isClone()) {
            if (TragicConfig.polarisNighttimeSet) this.worldObj.getWorldInfo()
                .setWorldTime(18000);

            if (this.isEntityInRange(this.getAttackTarget(), 3.0F, 16.0F) && TragicConfig.polarisInvisibility) {
                this.setInvisible(true);
            } else {
                this.setInvisible(false);
            }

            if (this.getDistanceToEntity(this.getAttackTarget()) > 16.0F && rand.nextInt(48) == 0
                && TragicConfig.polarisTeleport) this.teleportToEntity(this.getAttackTarget());
            if (this.ticksExisted % 10 == 0 && rand.nextInt(4) == 0
                && this.getDistanceToEntity(this.getAttackTarget()) <= 12.0F
                && TragicConfig.polarisTeleport) this.teleportRandomly();
        } else {
            this.setInvisible(false);
        }

        if (this.isClone() && this.ticksExisted >= 100) this.setDead();
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (this.worldObj.isRemote) return false;

        if (this.isClone()) {
            this.setBeenAttacked();
            this.setDead();
            return true;
        }

        if (par1DamageSource.getDamageType()
            .equals("arrow") || this.isInvisible()) par2 *= 1.15;

        if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityLivingBase) {
            if (this.getHealth() - par2 > 0.0F && TragicConfig.polarisTeleport) this.teleportRandomly();

            if (par1DamageSource.getEntity() instanceof EntityPlayer && !par1DamageSource.isProjectile()) {
                EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

                if (player.experienceTotal > 20) {
                    player.experienceTotal -= 20;
                } else {
                    player.experienceTotal = 0;
                }
            }
        }

        if (this.getAttackTime() == 0) this.setAttackTime(5);

        return super.attackEntityFrom(par1DamageSource, par2);
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        if (this.worldObj.isRemote) return false;

        boolean flag = super.attackEntityAsMob(par1Entity);

        if (flag) {
            if (rand.nextBoolean() && par1Entity instanceof EntityLivingBase)
                ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.blindness.id, 30));
            if (TragicConfig.polarisTeleport) this.teleportRandomly();

            ArrayList<EntityPolaris> list = (ArrayList<EntityPolaris>) this.worldObj
                .getEntitiesWithinAABB(EntityPolaris.class, this.boundingBox.expand(32.0D, 32.0D, 32.0D));
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != this && !this.isClone()
                    && list.get(i)
                        .isClone()) {
                    list.get(i)
                        .setDead();
                }
            }

            if (this.isClone()) this.setDead();
        }

        return flag;
    }

    @Override
    public int getTotalArmorValue() {
        return this.worldObj.isDaytime() ? 0 : (int) polarisStats[5];
    }

    @Override
    public void fall(float par1) {}

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
        if (this.isClone()) return false;

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
                this.worldObj.spawnParticle("portal", d7, d8, d9, f, f1, f2);
            }
            this.worldObj.playSoundEffect(
                d3,
                d4,
                d5,
                TragicConfig.allowMobSounds ? "tragicmc:boss.polaris.clone" : "mob.endermen.portal",
                0.4F,
                1.0F);
            this.playSound(
                TragicConfig.allowMobSounds ? "tragicmc:boss.polaris.clone" : "mob.endermen.portal",
                0.4F,
                1.0F);

            if (rand.nextBoolean() && this.getHealth() <= this.getMaxHealth() / 2 && TragicConfig.polarisAfterImage) {
                ArrayList<Entity> list = (ArrayList<Entity>) this.worldObj
                    .getEntitiesWithinAABB(EntityPolaris.class, this.boundingBox.expand(32.0D, 32.0D, 32.0D));
                for (int mow = 0; mow < list.size(); mow++) {
                    if (list.get(mow) == this) list.remove(mow);
                }

                if (list.size() <= 3) {
                    EntityPolaris polar = new EntityPolaris(this.worldObj);
                    polar.copyLocationAndAnglesFrom(this);
                    polar.setPosition(d3, d4, d5);
                    polar.setClone();
                    this.worldObj.spawnEntityInWorld(polar);
                    if (this.getAttackTarget() != null) polar.setAttackTarget(this.getAttackTarget());
                    polar.onSpawnWithEgg(null);
                }
            }
            return true;
        }
    }

    @Override
    public void setInWeb() {}

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        if (!this.worldObj.isRemote && !this.worldObj.isDaytime() && this.getDaytime()) this.setDaytime(false);
        return super.onSpawnWithEgg(data);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("attackTime")) this.setAttackTime(tag.getInteger("attackTime"));
        if (tag.hasKey("isClone") && tag.getBoolean("isClone")) this.setClone();
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setInteger("attackTime", this.getAttackTime());
        tag.setBoolean("isClone", this.isClone());
    }

    @Override
    public String getLivingSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:boss.polaris.sonar" : null;
    }

    @Override
    public String getHurtSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:boss.polaris.ding" : super.getHurtSound();
    }

    @Override
    public String getDeathSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:boss.polaris.death" : null;
    }

    @Override
    public float getSoundPitch() {
        return 0.8F + rand.nextFloat() * 0.2F;
    }

    @Override
    public float getSoundVolume() {
        return 1.0F;
    }

    @Override
    public int getTalkInterval() {
        return super.getTalkInterval();
    }
}
