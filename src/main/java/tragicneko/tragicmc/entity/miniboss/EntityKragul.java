package tragicneko.tragicmc.entity.miniboss;

import static tragicneko.tragicmc.TragicConfig.kragulStats;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.mob.EntityGragul;
import tragicneko.tragicmc.entity.projectile.EntitySpiritCast;
import tragicneko.tragicmc.util.DamageHelper;

public class EntityKragul extends EntityGragul implements TragicMiniBoss {

    private int timeSinceFiring;

    public EntityKragul(World par1World) {
        super(par1World);
        this.setSize(0.225F * 2.115F, 0.515F * 2.115F);
        this.stepHeight = 1.0F;
        this.experienceValue = 22;
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
            .setBaseValue(kragulStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(kragulStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(kragulStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(kragulStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(kragulStats[4]);
    }

    @Override
    public int getTotalArmorValue() {
        return (int) kragulStats[5];
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (this.worldObj.isRemote) return;

        ++this.timeSinceFiring;

        if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 4.0F
            && this.getDistanceToEntity(this.getAttackTarget()) <= 16.0F
            && rand.nextInt(16) == 0
            && this.canEntityBeSeen(this.getAttackTarget())
            && this.timeSinceFiring >= 60
            && TragicConfig.kragulSpiritCasts) {
            double d0 = this.getAttackTarget().posX - this.posX;
            double d1 = this.getAttackTarget().boundingBox.minY + this.getAttackTarget().height / 3.0F
                - (this.posY + this.height / 2.0F);
            double d2 = this.getAttackTarget().posZ - this.posZ;

            float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.75F;

            for (int i = 0; i < 3; i++) {
                EntitySpiritCast fireball = new EntitySpiritCast(
                    this.worldObj,
                    this,
                    d0 + this.rand.nextGaussian() * f1,
                    d1,
                    d2 + this.rand.nextGaussian() * f1);
                fireball.posX = this.posX + d0 * 0.115D;
                fireball.posY = this.posY + this.height;
                fireball.posZ = this.posZ + d0 * 0.115D;
                this.worldObj.spawnEntityInWorld(fireball);
                this.timeSinceFiring = 0;
            }
        }

        if (this.getAttackTarget() != null && this.ticksExisted % 40 == 0
            && rand.nextInt(16) == 0
            && this.canEntityBeSeen(this.getAttackTarget()))
            this.getAttackTarget()
                .addPotionEffect(new PotionEffect(TragicPotion.LeadFoot.id, 120));
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        if (this.worldObj.isRemote || !(par1Entity instanceof EntityLivingBase)) return false;

        boolean result = par1Entity.attackEntityFrom(
            DamageHelper.causeSuffocationDamageFromMob(this),
            Math.max(((EntityLivingBase) par1Entity).getMaxHealth() / 5F, 1F));

        if (result) {
            if (this.worldObj.difficultySetting == EnumDifficulty.HARD) {
                if (rand.nextInt(4) == 0) {
                    ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.blindness.id, 120));
                } else if (rand.nextInt(8) == 0) {
                    ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.confusion.id, 120));
                } else if (rand.nextInt(16) == 0) {
                    if (TragicConfig.allowStun)
                        ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotion.Stun.id, 120));
                }
            }
        }

        return result;
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("waitTime")) this.timeSinceFiring = tag.getInteger("waitTime");
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setInteger("waitTime", this.timeSinceFiring);
    }

    @Override
    protected boolean isChangeAllowed() {
        return false;
    }

    @Override
    public Class getLesserForm() {
        return EntityGragul.class;
    }

    @Override
    public float getSoundPitch() {
        return 0.2F;
    }

    @Override
    public int getDropAmount() {
        return 5;
    }
}
