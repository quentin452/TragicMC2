package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.gragulStats;

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
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.miniboss.EntityKragul;
import tragicneko.tragicmc.entity.miniboss.TragicMiniBoss;
import tragicneko.tragicmc.util.DamageHelper;

public class EntityGragul extends TragicMob {

    public EntityGragul(World par1World) {
        super(par1World);
        this.setSize(0.225F, 0.495F);
        this.stepHeight = 1.0F;
        this.experienceValue = 10;
        this.getNavigator()
            .setAvoidsWater(true);
        this.getNavigator()
            .setCanSwim(false);
        this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
        this.tasks.addTask(5, new EntityAIWander(this, 0.75D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.isImmuneToFire = true;
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
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(gragulStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(gragulStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(gragulStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(gragulStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(gragulStats[4]);
    }

    @Override
    public int getTotalArmorValue() {
        return (int) gragulStats[5];
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (this.worldObj.isRemote) return;
        if (this.superiorForm == null && !(this instanceof TragicMiniBoss))
            this.superiorForm = new EntityKragul(this.worldObj);

        if (this.getAttackTarget() != null && this.ticksExisted % 120 == 0) {
            if (this.getAttackTarget() instanceof EntityPlayer && TragicConfig.allowInhibit
                && this.canEntityBeSeen(this.getAttackTarget())) {
                ((EntityPlayer) this.getAttackTarget()).addPotionEffect(new PotionEffect(TragicPotion.Inhibit.id, 200));
            }
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (this.worldObj.isRemote) return false;

        boolean flag = false;

        if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();
            flag = player.getCurrentEquippedItem() != null && (player.getCurrentEquippedItem()
                .getItem() == TragicItems.SwordOfJustice
                || player.getCurrentEquippedItem()
                    .getItem() == TragicItems.BowOfJustice);
        }

        if (flag) return super.attackEntityFrom(par1DamageSource, par2);

        if (par1DamageSource.isProjectile() || par1DamageSource.isFireDamage()) return false;

        if (TragicConfig.gragulDamageReduction) {
            int dif = this.worldObj.difficultySetting.getDifficultyId();

            if (dif == 3) {
                par2 = MathHelper.clamp_float(par2, 0.0F, 1.0F);
            } else if (dif == 2) {
                par2 = MathHelper.clamp_float(par2, 0.0F, 1.5F);
            } else {
                par2 = MathHelper.clamp_float(par2, 0.0F, 2.5F);
            }
        }

        boolean result = super.attackEntityFrom(par1DamageSource, par2);

        if (result) {
            this.hurtResistantTime = 40;
            if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityLivingBase
                && rand.nextInt(6) == 0) {
                if (par1DamageSource.getEntity() instanceof EntityPlayer
                    && ((EntityPlayer) par1DamageSource.getEntity()).capabilities.isCreativeMode) return result;
            }
        }

        return result;
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        if (this.worldObj.isRemote) return false;

        if (par1Entity instanceof EntityLivingBase || par1Entity instanceof EntityPlayer) {
            float f = (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
                .getAttributeValue();
            boolean result = par1Entity.attackEntityFrom(
                DamageHelper.causeSuffocationDamageFromMob(this),
                TragicConfig.gragulPercentageDamage
                    ? Math.max(((EntityLivingBase) par1Entity).getMaxHealth() / 10F, 0.5F)
                    : f);

            if (result) {
                if (this.worldObj.difficultySetting == EnumDifficulty.HARD) {
                    if (rand.nextInt(4) == 0 && TragicConfig.allowLeadFoot) ((EntityLivingBase) par1Entity)
                        .addPotionEffect(new PotionEffect(TragicPotion.LeadFoot.id, 120));

                }
            }

            return result;
        } else {
            return false;
        }
    }

    @Override
    protected boolean isChangeAllowed() {
        return TragicConfig.allowKragul;
    }

    @Override
    public void heal(float f) {}

    @Override
    public void fall(float f) {}

    @Override
    public String getLivingSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.gragul.living" : null;
    }

    @Override
    public String getHurtSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.gragul.hurt" : super.getHurtSound();
    }

    @Override
    public String getDeathSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.gragul.death" : null;
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

    }
}
