package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.fuseaStats;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.entity.miniboss.EntityVolatileFusea;
import tragicneko.tragicmc.entity.miniboss.TragicMiniBoss;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityFusea extends TragicMob {

    private int explosionBuffer;
    private boolean hasDamagedEntity = false;

    public EntityFusea(World par1World) {
        super(par1World);
        this.setSize(1.5F, 1.5F);
        this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, true, false));
        this.explosionBuffer = 20;
        this.experienceValue = 5;
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return TragicEntities.Natural;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(fuseaStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(fuseaStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(fuseaStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(fuseaStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(fuseaStats[4]);
    }

    @Override
    public int getTotalArmorValue() {
        return (int) fuseaStats[5];
    }

    @Override
    protected boolean isChangeAllowed() {
        return TragicConfig.allowVolatileFusea;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (this.worldObj.isRemote) {
            for (int l = 0; l < 2; ++l) {
                this.worldObj.spawnParticle(
                    "townaura",
                    this.posX + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.5D,
                    this.posY + this.rand.nextDouble() * this.height + 0.15D,
                    this.posZ + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.5D,
                    (this.rand.nextDouble() - 0.6D) * 0.1D,
                    this.rand.nextDouble() * 0.1D,
                    (this.rand.nextDouble() - 0.6D) * 0.1D);
            }
            return;
        }

        if (this.superiorForm == null && !(this instanceof TragicMiniBoss))
            this.superiorForm = new EntityVolatileFusea(this.worldObj);
        if (this.explosionBuffer > 0) this.explosionBuffer--;
        if (this.isBurning() && this.explosionBuffer > 0) this.explosionBuffer = 0;

        if (this.getAttackTarget() != null) {
            double d0 = this.getAttackTarget().posX - this.posX;
            double d1 = this.getAttackTarget().posZ - this.posZ;
            double d4 = this.getAttackTarget().posY - this.posY
                + (this.getAttackTarget().height * 0.5 + (rand.nextDouble() * 0.25));

            float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d4 * d4);
            double d2 = d0 / f2 * 0.33D * 0.11D + this.motionX * 0.84D;
            this.motionX = Math.min(Math.abs(d2), 0.46D) == Math.abs(d2) ? d2 : 0.46D * (d2 < 0 ? -1 : 1);
            double d3 = d1 / f2 * 0.33D * 0.11D + this.motionZ * 0.84D;
            this.motionZ = Math.min(Math.abs(d3), 0.46D) == Math.abs(d3) ? d3 : 0.46D * (d3 < 0 ? -1 : 1);
            this.motionY = Math.min(Math.abs(d4), 0.66D) == Math.abs(d4) ? d4 : 0.66D * (d4 < 0 ? -1 : 1);
            if (this.isCollided) this.motionY += rand.nextDouble() - rand.nextDouble();
            this.moveFlying((float) this.motionX, (float) this.motionY, (float) this.motionZ);

            if (this.superiorForm != null && this.getAttackTarget()
                .getClass() == this.superiorForm.getClass()) this.setAttackTarget(null);
        } else {
            this.motionY += (rand.nextDouble() - rand.nextDouble()) * 0.2 - 0.1;
            if (rand.nextInt(6) == 0 && WorldHelper.getDistanceToGround(this) < 10)
                this.motionY += rand.nextDouble() + 0.8;

            this.motionX += (rand.nextDouble() - rand.nextDouble()) * 0.2;
            this.motionZ += (rand.nextDouble() - rand.nextDouble()) * 0.2;
            this.motionX *= 0.542D;
            this.motionY *= 0.256D;
            this.motionZ *= 0.542D;
            this.moveFlying((float) this.motionX, (float) this.motionY, (float) this.motionZ);
        }

        int i = this.getAttackTarget() != null ? 15 : 25;
        if (this.ticksExisted % i == 0 && TragicConfig.allowMobSounds) this.worldObj.playSoundAtEntity(
            this,
            "tragicmc:mob.fusea.hum",
            this.getAttackTarget() != null ? 1.4F : 0.8F,
            this.getAttackTarget() != null ? 0.2F : 1.0F);
    }

    @Override
    public void fall(float par1) {}

    @Override
    public void updateFallState(double par1, boolean par2) {}

    @Override
    public boolean attackEntityFrom(DamageSource src, float dmg) {
        boolean flag = false;
        this.hurtTime = 0;
        this.hurtResistantTime = 0;

        if (!this.worldObj.isRemote && src.getEntity() != null && src.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) src.getEntity();
            flag = player.getCurrentEquippedItem() != null && (player.getCurrentEquippedItem()
                .getItem() == TragicItems.SwordOfJustice
                || player.getCurrentEquippedItem()
                    .getItem() == TragicItems.BowOfJustice);
        }

        if (flag) return super.attackEntityFrom(src, 1000.0F);

        if ((src.getEntity() != null && !src.isExplosion() || src == DamageSource.onFire || src == DamageSource.inFire)
            && !this.worldObj.isRemote
            && this.explosionBuffer == 0
            && !flag) {
            if (!TragicConfig.fuseaExplosiveLayers) return super.attackEntityFrom(src, dmg);
            this.explosionBuffer = (int) (60 * (this.getHealth() / this.getMaxHealth()));
            this.setHealth(this.getHealth() - 1F);
            if (TragicConfig.fuseaExplosiveDamage) this.worldObj.createExplosion(
                this,
                this.posX,
                this.posY,
                this.posZ,
                rand.nextFloat() * 2.0F + 1.5F,
                this.getMobGriefing());
            this.recentlyHit = 60;
            if (this.getHealth() == 0F) this.onDeath(src);
        }

        return !this.worldObj.isRemote;
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        if (!this.worldObj.isRemote && this.explosionBuffer == 0
            && this.superiorForm != null
            && par1Entity.getClass() != this.superiorForm.getClass()) {
            if (!TragicConfig.fuseaExplosiveLayers) return super.attackEntityAsMob(par1Entity);
            this.explosionBuffer = (int) (60 * (this.getHealth() / this.getMaxHealth()));
            this.setHealth(this.getHealth() - 1F);
            if (TragicConfig.fuseaExplosiveAttack) this.worldObj.createExplosion(
                this,
                this.posX,
                this.posY,
                this.posZ,
                rand.nextFloat() * 2.0F + 1.5F,
                this.getMobGriefing());
            this.hasDamagedEntity = true;
        }
        return !this.worldObj.isRemote;
    }

    @Override
    public boolean canAttackClass(Class oclass) {
        return super.canAttackClass(oclass) && oclass != this.getClass();
    }

    @Override
    public void onStruckByLightning(EntityLightningBolt bolt) {
        if (this.getHealth() < this.getMaxHealth()) this.setHealth(this.getHealth() + 1F);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("explosionBuffer")) this.explosionBuffer = tag.getInteger("explosionBuffer");
        if (tag.hasKey("hasDamagedEntity")) this.hasDamagedEntity = tag.getBoolean("hasDamagedEntity");
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setInteger("explosionBuffer", this.explosionBuffer);
        tag.setBoolean("hasDamagedEntity", this.hasDamagedEntity);
    }

    @Override
    public String getLivingSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.fusea.living" : null;
    }

    @Override
    public String getHurtSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.fusea.hurt" : super.getHurtSound();
    }

    @Override
    public String getDeathSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.fusea.death" : null;
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
    public void onDeath(DamageSource src) {
        super.onDeath(src);
        if (!this.hasDamagedEntity && TragicConfig.allowAchievements
            && src.isFireDamage()
            && this.getAttackTarget() instanceof EntityPlayerMP)
            ((EntityPlayerMP) this.getAttackTarget()).triggerAchievement(TragicAchievements.fusea);
    }
}
