package tragicneko.tragicmc.entity.miniboss;

import static tragicneko.tragicmc.TragicConfig.jarraStats;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.entity.mob.EntityJabba;
import tragicneko.tragicmc.entity.projectile.EntityPoisonBarb;

public class EntityJarra extends EntityJabba implements TragicMiniBoss {

    public EntityJarra(World par1World) {
        super(par1World);
        this.stepHeight = 1.0F;
        this.experienceValue = 15;
        this.isImmuneToFire = false;
    }

    @Override
    public boolean isMobVariant() {
        return false;
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return TragicEntities.Natural;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float par1) {
        return 15728880;
    }

    @Override
    public float getBrightness(float par1) {
        return 1.0F;
    }

    @Override
    public boolean canRenderOnFire() {
        return this.isBurning();
    }

    @Override
    protected void setJabbaType(byte b) {
        this.dataWatcher.updateObject(17, (byte) 1);
        this.setSize(0.725F * 1.585F, 0.825F * 1.585F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(jarraStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(jarraStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(jarraStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(jarraStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(jarraStats[4]);
    }

    @Override
    public int getTotalArmorValue() {
        return (int) jarraStats[5];
    }

    @Override
    public void onLivingUpdate() {
        if (!this.worldObj.isRemote && this.isPotionActive(Potion.poison.id)) {
            this.removePotionEffect(Potion.poison.id);
        }

        super.onLivingUpdate();

        if (this.worldObj.isRemote) {
            this.setSize(0.725F * 1.585F, 0.825F * 1.585F);
        }
    }

    @Override
    protected void doParticleEffects() {
        if (this.getAngerTicks() == 0) return;

        for (int l = 0; l < 3; ++l) {
            this.worldObj.spawnParticle(
                "witchMagic",
                this.posX + (this.rand.nextDouble() - 0.5D) * this.width * 2.5D,
                this.posY + this.rand.nextDouble() * this.height,
                this.posZ + (this.rand.nextDouble() - 0.5D) * this.width * 2.5D,
                (this.rand.nextDouble() - 0.6D) * 0.1D,
                this.rand.nextDouble() * 0.1D,
                (this.rand.nextDouble() - 0.6D) * 0.1D);
        }
    }

    @Override
    protected void spawnProjectiles() {
        if (!TragicConfig.jabbaProjectiles) return;
        EntityLivingBase entity = this.getAttackTarget();
        double d0 = entity.posX - this.posX;
        double d1 = entity.boundingBox.minY + entity.height / 2.0F - (this.posY + this.height / 2.0F);
        double d2 = entity.posZ - this.posZ;

        float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(entity)) * 0.5F;

        for (int i = 0; i < 5; ++i) {
            EntityPoisonBarb poisonbarb = new EntityPoisonBarb(
                this.worldObj,
                this,
                d0 + this.rand.nextGaussian() * f1,
                d1,
                d2 + this.rand.nextGaussian() * f1);
            poisonbarb.posX = this.posX + d0 * 0.115D;
            poisonbarb.posY = this.posY + 0.435D;
            poisonbarb.posZ = this.posZ + d0 * 0.115D;
            this.worldObj.spawnEntityInWorld(poisonbarb);

            this.setAngerTicks(this.getAngerTicks() - 50);
        }
    }

    @Override
    protected boolean isChangeAllowed() {
        return false;
    }

    @Override
    public Class getLesserForm() {
        return EntityJabba.class;
    }

    @Override
    public float getSoundPitch() {
        return 0.4F;
    }

    @Override
    public float getSoundVolume() {
        return 0.2F + rand.nextFloat() * 0.1F;
    }

    @Override
    public String getDeathSound() {
        return "tragicmc:mob.jabba.hurt";
    }

    @Override
    public int getDropAmount() {
        return 5;
    }
}
