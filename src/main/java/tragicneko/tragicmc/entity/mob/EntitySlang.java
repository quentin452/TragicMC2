package tragicneko.tragicmc.entity.mob;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.entity.miniboss.EntitySlangLeader;

public class EntitySlang extends TragicMob {

    protected final EntityAIBase runawayAI = new EntityAIAvoidEntity(this, EntityPlayer.class, 10.0F, 0.8D, 1.1D);
    protected final EntityAIBase targetAI = new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true);
    protected final EntityAIBase moveAI = new EntityAIMoveTowardsTarget(this, 1.0D, 16.0F);
    protected ItemStack stolenItem = null;

    public boolean hasStolenItem = false; // for an achievement

    public EntitySlang(World par1World) {
        super(par1World);
        this.setSize(0.775F, 2.05F);
        this.stepHeight = 1.0F;
        this.experienceValue = 3;
        this.getNavigator()
            .setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.tasks.addTask(5, new EntityAIWander(this, 0.55D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 16.0F));
    }

    @Override
    protected boolean isChangeAllowed() {
        return true;
    }

    @Override
    protected boolean isAIEnabled() {
        return true;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(24.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(0.328);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(4.0);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(16.0);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(0.5);
    }

    @Override
    public int getTotalArmorValue() {
        return 6;
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return TragicEntities.Natural;
    }

    @Override
    protected boolean canCorrupt() {
        return true;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.stolenItem != null && this.getAttackTarget() != null) this.setAttackTarget(null);
        if (this.superiorForm == null) this.superiorForm = new EntitySlangLeader(this.worldObj);
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        boolean flag = super.attackEntityAsMob(par1Entity);

        if (flag && rand.nextInt(3) == 0 && this.stolenItem == null) {
            if (par1Entity instanceof EntityLivingBase) {
                EntityLivingBase el = (EntityLivingBase) par1Entity;
                this.stealItem(el);
            }
        }

        return flag;
    }

    public void stealItem(EntityLivingBase entity) {
        if (entity.getEquipmentInSlot(0) != null) {
            ItemStack stack = entity.getEquipmentInSlot(0)
                .copy();
            entity.setCurrentItemOrArmor(0, null);

            this.stolenItem = stack;
            this.hasStolenItem = true;

            this.targetTasks.removeTask(targetAI);
            this.tasks.removeTask(moveAI);
            this.tasks.addTask(1, runawayAI);
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource src, float dmg) {
        if (src.isExplosion()) return false;
        return super.attackEntityFrom(src, dmg);
    }

    @Override
    public void onDeath(DamageSource src) {
        super.onDeath(src);
        if (!this.worldObj.isRemote && this.stolenItem != null) this.entityDropItem(this.stolenItem, 0.4F);

        if (!this.worldObj.isRemote && this.stolenItem == null && rand.nextBoolean()) {
            EntityTNTPrimed tnt = new EntityTNTPrimed(this.worldObj, this.posX, this.posY, this.posZ, this);
            this.worldObj.spawnEntityInWorld(tnt);
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("stolenItem")) {
            this.stolenItem = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("stolenItem"));

            this.targetTasks.removeTask(targetAI);
            this.tasks.removeTask(moveAI);
            this.tasks.addTask(1, runawayAI);
        } else {
            this.targetTasks.addTask(0, targetAI);
            this.tasks.addTask(1, moveAI);
            this.tasks.removeTask(runawayAI);
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        if (this.stolenItem != null) {
            NBTTagCompound tag2 = new NBTTagCompound();
            this.stolenItem.writeToNBT(tag2);
            tag.setTag("stolenItem", tag2);
        }
    }

    @Override
    public boolean getCanSpawnHere() {
        if (MathHelper.floor_double(this.boundingBox.minY) >= this.worldObj.provider.getAverageGroundLevel())
            return false;
        return super.getCanSpawnHere();
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        if (!this.worldObj.isRemote) {
            if (this.stolenItem != null) {
                this.targetTasks.removeTask(targetAI);
                this.tasks.removeTask(moveAI);
                this.tasks.addTask(1, runawayAI);
            } else {
                this.targetTasks.addTask(0, targetAI);
                this.tasks.addTask(1, moveAI);
                this.tasks.removeTask(runawayAI);
            }
        }
        return super.onSpawnWithEgg(data);
    }

    @Override
    public String getLivingSound() {
        return null;
    }

    @Override
    public String getHurtSound() {
        return super.getHurtSound();
    }

    @Override
    public String getDeathSound() {
        return super.getHurtSound();
    }

    @Override
    public float getSoundPitch() {
        return super.getSoundPitch();
    }

    @Override
    public float getSoundVolume() {
        return super.getSoundVolume();
    }

    @Override
    protected void func_145780_a(int x, int y, int z, Block block) {

    }

    @Override
    public int getTalkInterval() {
        return super.getTalkInterval();
    }
}
