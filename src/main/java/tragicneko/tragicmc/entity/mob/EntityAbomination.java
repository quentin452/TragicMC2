package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.abominationStats;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;

public class EntityAbomination extends TragicMob {

    public EntityAbomination(World par1World) {
        super(par1World);
        this.setSize(0.775F, 2.05F);
        this.stepHeight = 1.0F;
        this.experienceValue = 5;
        this.getNavigator()
            .setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.tasks.addTask(5, new EntityAIWander(this, 0.55D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
        this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
    }

    @Override
    public boolean canCorrupt() {
        return false;
    }

    @Override
    public void onKillEntity(EntityLivingBase entity) {
        super.onKillEntity(entity);

        int ticks = (int) entity.getMaxHealth();
        this.setCelebrationTicks(ticks + rand.nextInt(10));

        List<Entity> list = this.worldObj
            .getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(12.0, 12.0, 12.0));
        EntityAbomination mob;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof EntityAbomination) {
                mob = (EntityAbomination) list.get(i);
                if (mob.getAttackTarget() == entity)
                    mob.setCelebrationTicks(ticks + rand.nextInt(10) - rand.nextInt(10));

            }
        }
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, Integer.valueOf(0));
        this.dataWatcher.addObject(17, Integer.valueOf(0));
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return TragicEntities.Beast;
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(abominationStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(abominationStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(abominationStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(abominationStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(abominationStats[4]);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.worldObj.isRemote) return;

        if (this.getAttackTime() > 0) this.decrementAttackTime();
        if (this.isCelebrating()) {
            this.decrementCelebrationTicks();
            if (this.getAttackTime() == 0 && this.getCelebrationTicks() >= 10) this.setAttackTime(10);
            if (this.getCelebrationTicks() % 20 == 0) this.jump();
        }
    }

    @Override
    public void moveEntity(double d0, double d1, double d2) {
        if (this.isCelebrating()) {
            super.moveEntity(0, d1, 0);
        } else {
            super.moveEntity(d0, d1, d2);
        }
    }

    public boolean isCelebrating() {
        return this.dataWatcher.getWatchableObjectInt(17) > 0;
    }

    public int getCelebrationTicks() {
        return this.dataWatcher.getWatchableObjectInt(17);
    }

    private void setCelebrationTicks(int i) {
        this.dataWatcher.updateObject(17, i);
    }

    private void decrementCelebrationTicks() {
        int pow = this.dataWatcher.getWatchableObjectInt(17);
        this.dataWatcher.updateObject(17, --pow);
    }

    public int getAttackTime() {
        return this.dataWatcher.getWatchableObjectInt(16);
    }

    private void decrementAttackTime() {
        int pow = this.dataWatcher.getWatchableObjectInt(16);
        this.dataWatcher.updateObject(16, --pow);
    }

    private void setAttackTime(int i) {
        this.dataWatcher.updateObject(16, i);
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (this.worldObj.isRemote) return false;

        if (this.isCelebrating()) this.setCelebrationTicks(0);

        if (par1DamageSource.isFireDamage()) {
            par2 = (par2 * 2.275F) - 1.0F;
        }

        if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

            if (player.getCurrentEquippedItem() == null
                || player.getCurrentEquippedItem() != null && !(player.getCurrentEquippedItem()
                    .getItem() instanceof ItemSword)) {
                par2 *= 0.725F;
            }
        }

        if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityLivingBase
            && rand.nextBoolean()
            && TragicConfig.abominationHelpCall) {
            List<Entity> list = this.worldObj
                .getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(12.0, 12.0, 12.0));
            EntityAbomination mob;

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof EntityAbomination) {
                    mob = (EntityAbomination) list.get(i);
                    if (mob.getAttackTarget() == null) mob.setTarget(this.getAttackTarget());
                }
            }
        }

        return super.attackEntityFrom(par1DamageSource, par2);
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        if (this.worldObj.isRemote || this.isCelebrating()) return false;

        boolean result = super.attackEntityAsMob(par1Entity);

        if (result) {
            par1Entity.motionY += 0.34D;
            par1Entity.motionX *= 1.11D;
            par1Entity.motionZ *= 1.11D;
            this.setAttackTime(10);
        }

        return result;
    }

    @Override
    public int getTotalArmorValue() {
        return (int) abominationStats[5];
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("attackTime")) this.setAttackTime(tag.getInteger("attackTime"));
        if (tag.hasKey("celebrationTime")) this.setCelebrationTicks(tag.getInteger("celebrationTime"));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setInteger("attackTime", this.getAttackTime());
        tag.setInteger("celebrationTime", this.getCelebrationTicks());
    }

    @Override
    protected boolean isChangeAllowed() {
        return false;
    }

    @Override
    public String getLivingSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.abomination.living" : null;
    }

    @Override
    public String getHurtSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.abomination.hurt" : super.getHurtSound();
    }

    @Override
    public String getDeathSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.abomination.death" : null;
    }

    @Override
    public float getSoundPitch() {
        return 1.0F;
    }

    @Override
    public float getSoundVolume() {
        return 0.8F + rand.nextFloat() * 0.2F;
    }

    @Override
    public int getTalkInterval() {
        return super.getTalkInterval();
    }
}
