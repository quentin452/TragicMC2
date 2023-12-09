package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.minotaurStats;

import net.minecraft.block.Block;
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
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicPotion;

public class EntityMinotaur extends TragicMob {

    public EntityMinotaur(World par1World) {
        super(par1World);
        this.setSize(0.725F, 2.575F);
        this.stepHeight = 1.0F;
        this.experienceValue = 10;
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
        this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 0.65D, 32.0F));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, Integer.valueOf(0));
    }

    public int getChargeTicks() {
        return this.dataWatcher.getWatchableObjectInt(16);
    }

    private void setChargeTicks(int i) {
        this.dataWatcher.updateObject(16, i);
    }

    private void decrementChargeTicks() {
        int pow = this.getChargeTicks();
        this.setChargeTicks(--pow);
    }

    public boolean isCharging() {
        return this.getChargeTicks() > 0;
    }

    @Override
    public boolean canCorrupt() {
        return false;
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
            .setBaseValue(minotaurStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(minotaurStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(minotaurStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(minotaurStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(minotaurStats[4]);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (this.worldObj.isRemote) return;

        if (this.isCharging()) {
            this.decrementChargeTicks();
            this.setSprinting(true);
        } else {
            this.setSprinting(false);
        }

        if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 1.0F
            && this.getDistanceToEntity(this.getAttackTarget()) <= 8.0F
            && this.onGround
            && rand.nextInt(16) == 0
            && this.onGround
            && !this.isCharging()
            && this.canEntityBeSeen(this.getAttackTarget())
            && TragicConfig.minotaurCharge) {
            double d0 = this.getAttackTarget().posX - this.posX;
            double d1 = this.getAttackTarget().posZ - this.posZ;
            double d2 = this.getAttackTarget().posY - this.posY;
            float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
            this.motionX = d0 / f2 * 2.5D * 0.600000011920929D + this.motionX * 0.40000000298023224D;
            this.motionZ = d1 / f2 * 2.5D * 0.600000011920929D + this.motionZ * 0.40000000298023224D;
            this.motionY = d1 / f2 * 1.1D * 0.200000011920929D + this.motionY * 0.20000000298023224D;
            this.setChargeTicks(20);
            this.rotationYaw = -((float) Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float) Math.PI;
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (this.worldObj.isRemote) return false;

        if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer
            && !par1DamageSource.isProjectile()
            && !par1DamageSource.isMagicDamage()) {
            EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

            if (player.getCurrentEquippedItem() != null) {
                if (!(player.getCurrentEquippedItem()
                    .getItem() instanceof ItemBow)) {
                    player.getCurrentEquippedItem()
                        .damageItem(rand.nextInt(2) + 1, player);
                }
            } else {
                player.attackEntityFrom(DamageSource.causeMobDamage(this), 0.5F);
            }
        }

        if (!par1DamageSource.isProjectile() && !par1DamageSource.isExplosion() && !par1DamageSource.isMagicDamage())
            par2 *= 0.65F;
        if (this.isCharging()) par2 *= 0.45F;

        return super.attackEntityFrom(par1DamageSource, par2);
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        boolean result = super.attackEntityAsMob(par1Entity);

        if (result) {
            if (par1Entity instanceof EntityLivingBase && rand.nextInt(8) == 0) {
                switch (rand.nextInt(3)) {
                    default:
                    case 0:
                        ((EntityLivingBase) par1Entity)
                            .addPotionEffect(new PotionEffect(Potion.confusion.id, rand.nextInt(200)));
                        break;
                    case 1:
                        ((EntityLivingBase) par1Entity)
                            .addPotionEffect(new PotionEffect(Potion.weakness.id, rand.nextInt(200)));
                        break;
                    case 2:
                        if (TragicConfig.allowSubmission) ((EntityLivingBase) par1Entity)
                            .addPotionEffect(new PotionEffect(TragicPotion.Submission.id, rand.nextInt(200)));
                        break;
                }
            }

            if (this.isCharging()) {
                par1Entity.motionX += this.motionX;
                par1Entity.motionZ += this.motionZ;
                par1Entity.motionY += this.motionY;
            }
        }

        return result;
    }

    @Override
    public int getTotalArmorValue() {
        return (int) minotaurStats[5];
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("chargeTicks")) this.setChargeTicks(tag.getInteger("chargeTicks"));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setInteger("chargeTicks", this.getChargeTicks());
    }

    @Override
    protected boolean isChangeAllowed() {
        return false;
    }

    @Override
    public String getLivingSound() {
        return TragicConfig.allowMobSounds
            ? (this.getAttackTarget() == null ? "tragicmc:mob.minotaur.snort" : "tragicmc:mob.minotaur.charge")
            : null;
    }

    @Override
    public String getHurtSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.minotaur.hurt" : super.getHurtSound();
    }

    @Override
    public String getDeathSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.minotaur.charge" : null;
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
    protected void func_145780_a(int x, int y, int z, Block block) {
        if (TragicConfig.allowMobSounds)
            this.playSound("tragicmc:mob.minotaur.hoof", 0.1F + rand.nextFloat() * 0.05F, 0.4F);
    }

    @Override
    public int getTalkInterval() {
        return 320;
    }

    @Override
    public int getDropAmount() {
        return 3;
    }
}
