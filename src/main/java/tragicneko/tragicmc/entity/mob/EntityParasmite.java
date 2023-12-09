package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.parasmiteStats;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityParasmite extends TragicMob {

    public byte power = 0;

    public EntityParasmite(World par1World) {
        super(par1World);
        this.setSize(0.625F, 0.625F);
        this.experienceValue = 5;
        this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, true, false));
    }

    @Override
    protected boolean isChangeAllowed() {
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
            .setBaseValue(parasmiteStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(parasmiteStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(parasmiteStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(parasmiteStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(parasmiteStats[4]);
    }

    @Override
    public int getTotalArmorValue() {
        return (int) parasmiteStats[5];
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return TragicEntities.Beast;
    }

    @Override
    protected boolean canCorrupt() {
        return true;
    }

    @Override
    public boolean canRenderOnFire() {
        return false;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (this.ridingEntity != null) {
            this.yOffset = -0.2F;
        } else {
            this.yOffset = 0F;
        }

        if (this.worldObj.isRemote) return;

        if (this.ridingEntity != null && TragicConfig.parasmiteLeech) {
            this.power = 1;
            if (this.ridingEntity instanceof EntityParasmite) {
                this.power = 2;
                if (this.ridingEntity.ridingEntity != null && this.ridingEntity.ridingEntity instanceof EntityParasmite)
                    this.power = 3;
            } else if (this.ridingEntity instanceof EntityLivingBase) {
                ((EntityLivingBase) this.ridingEntity)
                    .addPotionEffect(new PotionEffect(Potion.damageBoost.id, 10, this.power));
                if (this.ticksExisted % 60 == 0) this.ridingEntity.attackEntityFrom(
                    DamageSource.causeMobDamage(this),
                    (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
                        .getAttributeValue() * this.power);
            }
            this.setAttackTarget(null);
            this.setInvisible(this.ridingEntity.isInvisible());
        } else {
            this.power = 0;
        }

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
        } else {
            this.motionY += (rand.nextDouble() - rand.nextDouble()) * 0.2 - 0.1;
            if (rand.nextInt(6) == 0 && WorldHelper.getDistanceToGround(this) < 10)
                this.motionY += rand.nextDouble() + 0.8;

            this.motionX += (rand.nextDouble() - rand.nextDouble()) * 0.2;
            this.motionZ += (rand.nextDouble() - rand.nextDouble()) * 0.2;
            this.motionX *= 0.542D;
            this.motionZ *= 0.542D;
            this.motionY *= 0.256D;
            this.moveFlying((float) this.motionX, (float) this.motionY, (float) this.motionZ);

            if (TragicConfig.parasmiteLeech) {
                EntityParasmite smite = (EntityParasmite) this.worldObj
                    .findNearestEntityWithinAABB(EntityParasmite.class, this.boundingBox.expand(2.0, 2.0, 2.0), this);
                if (smite != null && smite.riddenByEntity == null && this.ridingEntity == null) this.mountEntity(smite);
            }
        }

    }

    @Override
    public void fall(float par1) {}

    @Override
    public void updateFallState(double par1, boolean par2) {}

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        if (!this.worldObj.isRemote && par1Entity.riddenByEntity == null && this.ridingEntity == null) {
            if (!TragicConfig.parasmiteLeech) return super.attackEntityAsMob(par1Entity);

            this.mountEntity(par1Entity);
            return true;
        }
        return !this.worldObj.isRemote;
    }

    @Override
    public boolean canAttackClass(Class oclass) {
        return oclass != this.getClass();
    }

    @Override
    public String getLivingSound() {
        return TragicConfig.allowMobSounds
            ? (this.ridingEntity == null ? "tragicmc:mob.parasmite.squeek" : "tragicmc:mob.parasmite.shriek")
            : null;
    }

    @Override
    public String getHurtSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.parasmite.hurt" : super.getHurtSound();
    }

    @Override
    public String getDeathSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.parasmite.shriek" : null;
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
        // this.playSound("tragicmc:mob.sirv.snap", 0.45F, 1.0F);
    }

    @Override
    public int getTalkInterval() {
        return super.getTalkInterval();
    }
}
