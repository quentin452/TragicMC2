package tragicneko.tragicmc.entity.miniboss;

import static tragicneko.tragicmc.TragicConfig.greaterStinStats;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.mob.EntityStin;

public class EntityGreaterStin extends EntityStin implements TragicMiniBoss {

    public EntityGreaterStin(World par1World) {
        super(par1World);
        this.setSize(1.15F, 3.45F);
        this.experienceValue = 15;
        this.stepHeight = 1.5F;
    }

    @Override
    public boolean isMobVariant() {
        return false;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(greaterStinStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(greaterStinStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(greaterStinStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(greaterStinStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(greaterStinStats[4]);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(20, Integer.valueOf(0));
    }

    public int getChargeTicks() {
        return this.dataWatcher.getWatchableObjectInt(20);
    }

    protected void setChargeTicks(int i) {
        this.dataWatcher.updateObject(20, i);
    }

    protected void decrementChargeTicks() {
        int pow = this.getChargeTicks();
        this.setChargeTicks(--pow);
    }

    public boolean isCharging() {
        return this.getChargeTicks() > 0;
    }

    @Override
    public int getTotalArmorValue() {
        return (int) greaterStinStats[5];
    }

    @Override
    public boolean isAdult() {
        return true;
    }

    @Override
    protected void growUp() {}

    @Override
    public void setChild() {}

    @Override
    public void onLivingUpdate() {
        if (this.isCharging() && this.getChargeTicks() > 170) this.motionX = this.motionZ = 0.0D;

        super.onLivingUpdate();

        if (!this.worldObj.isRemote) this.setSize(1.15F, 3.45F);

        if (this.worldObj.isRemote && this.getChargeTicks() <= 170 && this.isCharging()) {
            for (int i = 0; i < 2; i++) {
                this.worldObj.spawnParticle(
                    "crit",
                    this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.355D),
                    this.posY + 0.115D + rand.nextDouble(),
                    this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.355D),
                    0.0F,
                    0.155F * this.rand.nextFloat(),
                    0.0F);
            }
        }

        if (this.worldObj.isRemote) return;

        if (this.superiorForm == null && this.isChangeAllowed())
            this.superiorForm = this.rand.nextBoolean() && TragicConfig.allowStinKing
                ? new EntityStinKing(this.worldObj)
                : (TragicConfig.allowStinQueen ? new EntityStinQueen(this.worldObj) : null);

        if (this.isCharging()) {
            this.setSprinting(true);
            if (this.isGalloping()) this.setGallopTicks(0);
            this.decrementChargeTicks();
            if (this.getAttackTarget() == null || this.getAttackTarget().posY >= this.posY + 4.5D)
                this.setChargeTicks(0);
        } else {
            this.setSprinting(false);
        }

        if (this.getAttackTarget() != null && !this.isCharging()
            && !this.isGalloping()
            && this.getDistanceToEntity(this.getAttackTarget()) <= 12.0F
            && this.ticksExisted % 10 == 0
            && rand.nextInt(12) == 0
            && this.getAttackTarget().onGround
            && this.onGround
            && this.getDistanceToEntity(this.getAttackTarget()) >= 3.0F
            && TragicConfig.greaterStinCharge) {
            this.setChargeTicks(200);
        }

        if (this.getAttackTarget() != null && this.isCharging()) {
            if (this.getChargeTicks() <= 170) {
                float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ) * 30.0F;

                if (this.isCollidedHorizontally && f >= 2.0F) {
                    this.worldObj.createExplosion(
                        this,
                        this.posX,
                        this.posY,
                        this.posZ,
                        (f / 2) * rand.nextFloat() + (f / 2),
                        this.getMobGriefing());
                    this.setChargeTicks(0);
                } else {
                    double d0 = this.getAttackTarget().posX - this.posX;
                    double d1 = this.getAttackTarget().posZ - this.posZ;
                    float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
                    this.motionX += d0 / f2 * 0.13D * 0.1100000011920929D + this.motionX * 0.1000000298023224D;
                    this.motionZ += d1 / f2 * 0.13D * 0.1100000011920929D + this.motionZ * 0.1000000298023224D;
                    this.motionY = -0.1D;
                }
            } else {
                this.motionY = -0.1D;
            }
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (this.worldObj.isRemote) return false;
        if (this.isCharging() && this.getChargeTicks() > 170) this.setChargeTicks(0);
        if (this.isCharging() && this.getChargeTicks() <= 170) par2 /= 2;
        return super.attackEntityFrom(par1DamageSource, par2);
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        if (this.isCharging() && this.getChargeTicks() > 170) return false;
        boolean flag = super.attackEntityAsMob(par1Entity);

        if (flag && this.isCharging() && this.getChargeTicks() <= 170) {
            if (rand.nextBoolean() && par1Entity instanceof EntityLivingBase) {
                ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.confusion.id, 300, 0));
                if (TragicConfig.allowSubmission) ((EntityLivingBase) par1Entity)
                    .addPotionEffect(new PotionEffect(TragicPotion.Submission.id, 300, 1 + rand.nextInt(3)));
            }

            par1Entity.motionY += 1.222543D;
            par1Entity.motionX *= 1.65D;
            par1Entity.motionZ *= 1.65D;
        }

        return flag;
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
        return this.superiorForm instanceof EntityStinKing ? TragicConfig.allowStinKing : TragicConfig.allowStinQueen;
    }

    @Override
    public Class getLesserForm() {
        return EntityStin.class;
    }

    @Override
    public float getSoundPitch() {
        return 0.4F;
    }

    @Override
    public int getDropAmount() {
        return 5;
    }
}
