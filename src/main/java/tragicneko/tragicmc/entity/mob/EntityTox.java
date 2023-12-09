package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.poxStats;
import static tragicneko.tragicmc.TragicConfig.toxStats;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.entity.miniboss.EntityMagmox;
import tragicneko.tragicmc.entity.projectile.EntitySpore;
import tragicneko.tragicmc.items.weapons.ItemScythe;
import tragicneko.tragicmc.worldgen.biome.BiomeGenPaintedForest;

public class EntityTox extends TragicMob {

    public EntityTox(World par1World) {
        super(par1World);
        this.experienceValue = 5;
        this.getNavigator()
            .setAvoidsWater(true);
        this.getNavigator()
            .setCanSwim(false);
        this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 0.05D, true));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0D, 64.0F));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 32.0F));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    @Override
    public boolean isMobVariant() {
        return this.getToxType() == 1;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (byte) 0);
        this.dataWatcher.addObject(17, Integer.valueOf(0));
        this.dataWatcher.addObject(18, Integer.valueOf(0));
        this.dataWatcher.addObject(19, Integer.valueOf(0));
    }

    public byte getToxType() {
        return this.dataWatcher.getWatchableObjectByte(16);
    }

    protected void setToxType(byte b) {
        this.dataWatcher.updateObject(16, b);

        if (b == 0) {
            this.setSize(0.625F, 1.965F);
        } else {
            this.experienceValue = 6;
            this.setSize(0.625F * 0.635F, 1.965F * 0.635F);
        }
    }

    public int getFiringTicks() {
        return this.dataWatcher.getWatchableObjectInt(17);
    }

    protected void setFiringTicks(int i) {
        this.dataWatcher.updateObject(17, i);
    }

    protected void decrementFiringTicks() {
        int pow = this.getFiringTicks();
        this.setFiringTicks(--pow);
    }

    public boolean isFiring() {
        return this.getFiringTicks() > 0;
    }

    public int getAttackTime() {
        return this.dataWatcher.getWatchableObjectInt(18);
    }

    protected void setAttackTime(int i) {
        this.dataWatcher.updateObject(18, i);
    }

    protected void decrementAttackTime() {
        int pow = this.getAttackTime();
        this.setAttackTime(--pow);
    }

    public int getWiggleTime() {
        return this.dataWatcher.getWatchableObjectInt(19);
    }

    protected void setWiggleTime(int i) {
        this.dataWatcher.updateObject(19, i);
    }

    protected void decrementWiggleTime() {
        int pow = this.getWiggleTime();
        this.setWiggleTime(--pow);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float par1) {
        return this.getToxType() == 1 ? 15728880 : super.getBrightnessForRender(par1);
    }

    @Override
    public float getBrightness(float par1) {
        return this.getToxType() == 1 ? 1.0F : super.getBrightness(par1);
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return TragicEntities.Natural;
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        boolean flag = this.getToxType() == 0;
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(flag ? toxStats[0] : poxStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(flag ? toxStats[1] : poxStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(flag ? toxStats[2] : poxStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(flag ? toxStats[3] : poxStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(flag ? toxStats[4] : poxStats[4]);
    }

    @Override
    public void onLivingUpdate() {
        if (!this.worldObj.isRemote) {
            if (this.motionY > 0.0D) this.motionY = -0.1D;
            if (Math.abs(this.motionX) > 0.25D) this.motionX *= 0.225D;
            if (Math.abs(this.motionZ) > 0.25D) this.motionZ *= 0.225D;
            if (this.isPotionActive(Potion.poison)) this.removePotionEffect(Potion.poison.id);
        }

        super.onLivingUpdate();

        if (this.worldObj.isRemote) {
            if (this.getToxType() == 0) {
                this.setSize(0.625F, 1.965F);

                if ((this.getWiggleTime() > 0 || this.isFiring() || this.getAttackTime() > 0)
                    && !this.isImmuneToFire()) {
                    this.worldObj.spawnParticle(
                        "mobSpellAmbient",
                        this.posX + (this.rand.nextDouble() - 0.5D) * this.width * 2.5D,
                        this.posY + this.rand.nextDouble() * this.height,
                        this.posZ + (this.rand.nextDouble() - 0.5D) * this.width * 2.5D,
                        (this.rand.nextDouble() - 0.6D) * 0.1D,
                        this.rand.nextDouble() * 0.1D,
                        (this.rand.nextDouble() - 0.6D) * 0.1D);
                }
            } else {
                this.setSize(0.625F * 0.635F, 1.965F * 0.635F);

                if (this.getWiggleTime() > 0 || this.isFiring() || this.getAttackTime() > 0) {
                    this.worldObj.spawnParticle(
                        "slime",
                        this.posX + (this.rand.nextDouble() - 0.5D) * this.width * 2.5D,
                        this.posY + this.rand.nextDouble() * this.height,
                        this.posZ + (this.rand.nextDouble() - 0.5D) * this.width * 2.5D,
                        (this.rand.nextDouble() - 0.6D) * 0.1D,
                        this.rand.nextDouble() * 0.1D,
                        (this.rand.nextDouble() - 0.6D) * 0.1D);
                }
            }
        } else {
            if (this.getWiggleTime() > 0) {
                this.decrementWiggleTime();
                if (this.getAttackTarget() != null) this.setWiggleTime(0);
            }

            if (this.superiorForm == null && this.getToxType() == 0)
                this.superiorForm = new EntityMagmox(this.worldObj);
            if (this.isFiring()) this.decrementFiringTicks();
            if (this.getAttackTime() > 0) this.decrementAttackTime();

            if (this.ticksExisted % 60 == 0 && rand.nextInt(4) == 0 && this.getAttackTarget() == null)
                this.setWiggleTime(20);

            if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) >= 2.0F
                && this.ticksExisted % 20 == 0
                && rand.nextInt(4) == 0
                && !this.isFiring()) {
                this.setFiringTicks(120);
            }

            int rate = this.getToxType() == 0 ? 10 : 5;
            if (this.getFiringTicks() >= 40 && this.ticksExisted % rate == 0
                && this.getAttackTarget() != null
                && this.canEntityBeSeen(this.getAttackTarget())
                && this.getDistanceToEntity(this.getAttackTarget()) >= 2.0F) {
                this.shootProjectiles();
            }

            if (this.ticksExisted % 60 == 0 && this.getHealth() < this.getMaxHealth()) this.heal(3.0F);
        }
    }

    protected void shootProjectiles() {
        this.fireProjectileAtEntity(new EntitySpore(this.worldObj), this.getAttackTarget(), 0F);
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (this.worldObj.isRemote) return false;

        if (this.isFiring()) this.setFiringTicks(19);
        if (this.getWiggleTime() > 0) this.setWiggleTime(0);

        if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

            if (player.getCurrentEquippedItem() != null && (player.getCurrentEquippedItem()
                .getItem() instanceof ItemScythe
                || player.getCurrentEquippedItem()
                    .getItem() instanceof ItemAxe)) {
                par2 *= 1.425;
            }
        }

        if (par1DamageSource.isFireDamage()) par2 *= 1.625;

        boolean result = super.attackEntityFrom(par1DamageSource, par2);
        if (result) this.setAttackTime(5);

        return result;
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        if (this.worldObj.isRemote) return false;

        boolean result = super.attackEntityAsMob(par1Entity);

        if (result && par1Entity instanceof EntityLivingBase && rand.nextInt(16) == 0 && !this.isImmuneToFire()) {
            ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 600, 1));
        }

        return result;
    }

    @Override
    public int getTotalArmorValue() {
        return this.isFiring() ? 0 : (this.getToxType() == 0 ? (int) toxStats[5] : (int) poxStats[5]);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("toxType")) this.setToxType(tag.getByte("toxType"));
        if (tag.hasKey("firingTicks")) this.setFiringTicks(tag.getInteger("firingTicks"));
        if (tag.hasKey("attackTime")) this.setAttackTime(tag.getInteger("attackTime"));
        if (tag.hasKey("wiggleTime")) this.setWiggleTime(tag.getInteger("wiggleTime"));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setByte("toxType", this.getToxType());
        tag.setInteger("firingTicks", this.getFiringTicks());
        tag.setInteger("attackTime", this.getAttackTime());
        tag.setInteger("wiggleTime", this.getWiggleTime());
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ);
        this.setToxType(biome instanceof BiomeGenPaintedForest ? (byte) 1 : (byte) 0);
        return super.onSpawnWithEgg(data);
    }

    @Override
    protected boolean isChangeAllowed() {
        return TragicConfig.allowMagmox;
    }

    @Override
    public String getLivingSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.tox.living" : null;
    }

    @Override
    public String getHurtSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.tox.hurt" : super.getHurtSound();
    }

    @Override
    public String getDeathSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.tox.hurt" : null;
    }

    @Override
    public float getSoundPitch() {
        return this.getToxType() == 1 ? 1.4F : 1.0F;
    }

    @Override
    public float getSoundVolume() {
        return 0.9F + rand.nextFloat();
    }

    @Override
    public int getTalkInterval() {
        return super.getTalkInterval();
    }

    @Override
    public String getVariantName() {
        return "TragicMC.Pox";
    }
}
