package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.ranmasStats;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.entity.EntityAIWatchTarget;

public class EntityRanmas extends TragicMob {

    private double[] motions = new double[] { 0, 0, 0 };
    private int chargeBuffer = 120;

    public EntityRanmas(World par1World) {
        super(par1World);
        this.setSize(1.355F, 2.425F);
        this.isImmuneToFire = true;
        this.experienceValue = 10;
        this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
        this.tasks.addTask(4, new EntityAIWatchTarget(this, 64.0F));
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
            .setBaseValue(ranmasStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(ranmasStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(ranmasStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(ranmasStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(ranmasStats[4]);
    }

    @Override
    public int getTotalArmorValue() {
        return (int) ranmasStats[5];
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return TragicEntities.Natural;
    }

    @Override
    protected boolean canCorrupt() {
        return false;
    }

    @Override
    public boolean canRenderOnFire() {
        return false;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, Integer.valueOf(0)); // charge ticks
    }

    private void setChargeTicks(int i) {
        this.dataWatcher.updateObject(16, i);
    }

    public int getChargeTicks() {
        return this.dataWatcher.getWatchableObjectInt(16);
    }

    @Override
    public void onLivingUpdate() {
        if (this.worldObj.isRemote || this.getChargeTicks() == 0) this.motionX = this.motionY = this.motionZ = 0;
        super.onLivingUpdate();

        if (this.worldObj.isRemote) return;

        if (this.chargeBuffer > 0) --this.chargeBuffer;
        if (this.getChargeTicks() > 0 && TragicConfig.ranmasCharge) {
            this.setChargeTicks(this.getChargeTicks() - 1);
            this.chargeBuffer = 40 + (int) (60 * (this.getHealth() / this.getMaxHealth()));

            if (this.isCollidedHorizontally) {
                this.setChargeTicks(0);

                double x = this.motions[0];
                double y = this.motions[1];
                double z = this.motions[2];
                this.motions = new double[] { 0, 0, 0 };

                float f = MathHelper.sqrt_double(x * x + z * z + y * y) * 5.0F;
                if (f >= 2.0F && TragicConfig.ranmasImpactExplosions) {
                    if (f > 5.0F) f = 5.0F;
                    this.worldObj.createExplosion(
                        this,
                        this.posX,
                        this.posY,
                        this.posZ,
                        (f / 2) * rand.nextFloat() + (f / 2),
                        this.getMobGriefing());
                    this.attackEntityFrom(DamageSource.fall, f);
                }

                this.worldObj.playSoundAtEntity(this, "random.anvil_land", 0.4F, 1.0F);
            }
            this.motionX = this.motions[0];
            this.motionY = this.motions[1];
            this.motionZ = this.motions[2];
        } else {
            if (this.getAttackTarget() != null && this.canEntityBeSeen(this.getAttackTarget())
                && this.ticksExisted % 5 == 0
                && rand.nextInt(8) == 0
                && this.chargeBuffer == 0) {
                double d0 = MathHelper.clamp_double(this.getAttackTarget().posX - this.posX, -2.4D, 3.2D);
                double d1 = MathHelper.clamp_double(
                    this.getAttackTarget().posY + this.getAttackTarget().height / 2.0
                        - (this.posY + this.height / 2.0F),
                    -3.2D,
                    3.2D);
                double d2 = MathHelper.clamp_double(this.getAttackTarget().posZ - this.posZ, -3.2D, 3.2D);

                this.motions = new double[] { d0, d1, d2 };
                this.setChargeTicks(15);
            } else if (this.ticksExisted % 10 == 0 && TragicConfig.allowMobSounds)
                this.worldObj.playSoundAtEntity(this, "tragicmc:mob.harvester.hover", 0.2F, 0.2F);
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource src, float dmg) {
        boolean flag = false;
        if (this.worldObj.isRemote) return flag;

        if (src.getEntity() != null && src.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) src.getEntity();
            flag = player.getCurrentEquippedItem() != null && (player.getCurrentEquippedItem()
                .getItem() == TragicItems.SwordOfJustice
                || player.getCurrentEquippedItem()
                    .getItem() == TragicItems.BowOfJustice);
        }

        if (flag) return super.attackEntityFrom(src, dmg);

        return super.attackEntityFrom(src.setDamageBypassesArmor(), Math.min(1.0F, dmg));
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        if (this.worldObj.isRemote) return false;

        double x = this.motions[0];
        double y = this.motions[1];
        double z = this.motions[2];

        float f = MathHelper.sqrt_double(x * x + z * z + y * y) * 5.0F;
        if (f < 1.0F) f = 1.0F;
        if (f > 20.0F) f = 20.0F;
        boolean flag = par1Entity.attackEntityFrom(
            DamageSource.causeMobDamage(this),
            f * (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
                .getAttributeValue());

        if (flag && this.getChargeTicks() > 0) {
            par1Entity.motionX += this.motionX;
            par1Entity.motionZ += this.motionZ;
        }
        return flag;
    }

    @Override
    public void fall(float par1) {}

    @Override
    public void updateFallState(double par1, boolean par2) {}

    @Override
    public boolean handleWaterMovement() {
        return false;
    }

    @Override
    public boolean handleLavaMovement() {
        return false;
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("chargeTicks")) this.setChargeTicks(tag.getInteger("chageTicks"));
        if (tag.hasKey("chargeBuffer")) this.chargeBuffer = tag.getInteger("chargeBuffer");
        if (tag.hasKey("chargeX") && tag.hasKey("chargeY") && tag.hasKey("chargeZ")) this.motions = new double[] {
            tag.getDouble("chargeX"), tag.getDouble("chargeY"), tag.getDouble("chargeZ") };
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setInteger("chargeTicks", this.getChargeTicks());
        tag.setInteger("chargeBuffer", this.chargeBuffer);
        tag.setDouble("chargeX", this.motions[0]);
        tag.setDouble("chargeY", this.motions[1]);
        tag.setDouble("chargeZ", this.motions[2]);
    }

    @Override
    public String getLivingSound() {
        return null;
    }

    @Override
    public String getHurtSound() {
        return "random.anvil_land";
    }

    @Override
    public String getDeathSound() {
        return getHurtSound();
    }

    @Override
    public float getSoundPitch() {
        return 0.4F;
    }

    @Override
    public float getSoundVolume() {
        return 0.6F + rand.nextFloat() * 0.2F;
    }

    @Override
    protected void func_145780_a(int x, int y, int z, Block block) {

    }

    @Override
    public int getTalkInterval() {
        return super.getTalkInterval();
    }
}
