package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.sirvStats;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;

public class EntitySirv extends TragicMob {

    public EntitySirv(World par1World) {
        super(par1World);
        this.setSize(0.45F, 1.85F);
        this.stepHeight = 1.0F;
        this.experienceValue = 5;
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
        this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 64.0F));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, Integer.valueOf(0));
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

    @Override
    public boolean canCorrupt() {
        return false;
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(sirvStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(sirvStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(sirvStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(sirvStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(sirvStats[4]);
    }

    @Override
    public int getTotalArmorValue() {
        return (int) sirvStats[5];
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.worldObj.isRemote && this.getAttackTime() > 0) this.decrementAttackTime();
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        boolean result = super.attackEntityFrom(par1DamageSource, par2);

        if (result && par1DamageSource.getEntity() != null
            && par1DamageSource.getEntity() instanceof EntityLivingBase) {
            if (TragicConfig.sirvHelpCall) {
                List<Entity> list = this.worldObj
                    .getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(32.0D, 32.0D, 32.0D));
                EntitySirv sirv;

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) instanceof EntitySirv) {
                        sirv = (EntitySirv) list.get(i);
                        if (sirv.getAttackTarget() == null)
                            sirv.setAttackTarget((EntityLivingBase) par1DamageSource.getEntity());
                    }
                }
            }

            if (par2 >= 30F && par1DamageSource.getEntity() instanceof EntityPlayerMP
                && TragicConfig.allowAchievements) {
                ((EntityPlayerMP) par1DamageSource.getEntity()).triggerAchievement(TragicAchievements.sirv);
            }
        }

        return result;
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        boolean result = super.attackEntityAsMob(par1Entity);

        if (result) {
            if (par1Entity instanceof EntityLivingBase && rand.nextInt(4) == 0) {
                switch (rand.nextInt(3)) {
                    default:
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

            this.setAttackTime(10);
        }
        return result;
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("attackTime")) this.setAttackTime(tag.getInteger("attackTime"));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setInteger("attackTime", this.getAttackTime());
    }

    @Override
    protected boolean isChangeAllowed() {
        return false;
    }

    @Override
    public String getLivingSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.sirv.crunch" : null;
    }

    @Override
    public String getHurtSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.sirv.snap" : super.getHurtSound();
    }

    @Override
    public String getDeathSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.sirv.crunch" : null;
    }

    @Override
    public float getSoundPitch() {
        return 1.0F;
    }

    @Override
    public float getSoundVolume() {
        return 0.4F + rand.nextFloat() * 0.2F;
    }

    @Override
    protected void func_145780_a(int x, int y, int z, Block block) {
        if (TragicConfig.allowMobSounds) this.playSound("tragicmc:mob.sirv.snap", 0.45F, 1.0F);
    }

    @Override
    public int getTalkInterval() {
        return super.getTalkInterval();
    }
}
