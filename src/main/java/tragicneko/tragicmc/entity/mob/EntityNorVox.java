package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.norVoxStats;
import static tragicneko.tragicmc.TragicConfig.starVoxStats;

import java.util.Calendar;
import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.miniboss.EntityVoxStellarum;
import tragicneko.tragicmc.entity.projectile.EntityStarShard;
import tragicneko.tragicmc.worldgen.biome.BiomeGenStarlitPrarie;

public class EntityNorVox extends TragicMob {

    protected AttributeModifier mod = new AttributeModifier(
        UUID.fromString("e20a064f-7022-4c64-9902-181d3ac9eb17"),
        "norVoxSpeedDebuff",
        TragicConfig.modifier[7],
        0);

    public EntityNorVox(World par1World) {
        super(par1World);
        this.stepHeight = 2.0F;
        this.experienceValue = 10;
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
        this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    @Override
    public boolean isMobVariant() {
        return this.getNorVoxType() == 1;
    }

    @Override
    protected boolean canCorrupt() {
        return this.getNorVoxType() == 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float par1) {
        return this.getNorVoxType() == 0 ? super.getBrightnessForRender(par1) : 15728880;
    }

    @Override
    public float getBrightness(float par1) {
        return this.getNorVoxType() == 0 ? super.getBrightness(par1) : 1.0F;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, Integer.valueOf(0));
        this.dataWatcher.addObject(17, (byte) 0);
        this.dataWatcher.addObject(18, (byte) 0);
        this.dataWatcher.addObject(19, Integer.valueOf(0));
        this.dataWatcher.addObject(20, Integer.valueOf(0));
    }

    @Override
    public boolean handleWaterMovement() {
        return false;
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    public int getFiringTicks() {
        return this.dataWatcher.getWatchableObjectInt(16);
    }

    protected void setFiringTicks(int i) {
        this.dataWatcher.updateObject(16, i);
    }

    protected void decrementFiringTicks() {
        int pow = this.getFiringTicks();
        this.setFiringTicks(--pow);
    }

    public byte getTextureID() {
        return this.dataWatcher.getWatchableObjectByte(18);
    }

    protected void setTextureID(byte b) {
        this.dataWatcher.updateObject(18, b);
    }

    public byte getNorVoxType() {
        return this.dataWatcher.getWatchableObjectByte(17);
    }

    protected void setNorVoxType(byte b) {
        this.dataWatcher.updateObject(17, b);

        if (b == 0) {
            this.setSize(1.135F, 1.575F);
        } else {
            this.experienceValue = 12;
            this.setSize(1.135F / 1.455F, 1.575F / 1.455F);
        }
    }

    public boolean isFiring() {
        return this.getFiringTicks() > 0;
    }

    public int getAttackTime() {
        return this.dataWatcher.getWatchableObjectInt(19);
    }

    protected void setAttackTime(int i) {
        this.dataWatcher.updateObject(19, i);
    }

    protected void decrementAttackTime() {
        int pow = this.getAttackTime();
        this.setAttackTime(--pow);
    }

    public int getNodTicks() {
        return this.dataWatcher.getWatchableObjectInt(20);
    }

    protected void setNodTicks(int i) {
        this.dataWatcher.updateObject(20, i);
    }

    protected void decrementNodTicks() {
        int pow = this.getNodTicks();
        this.setNodTicks(--pow);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        boolean flag = this.getNorVoxType() == 0;
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(flag ? norVoxStats[0] : starVoxStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(flag ? norVoxStats[1] : starVoxStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(flag ? norVoxStats[2] : starVoxStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(flag ? norVoxStats[3] : starVoxStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(flag ? norVoxStats[4] : starVoxStats[4]);
    }

    @Override
    public void onLivingUpdate() {
        if (!this.worldObj.isRemote && this.isPotionActive(Potion.wither.id)) this.removePotionEffect(Potion.wither.id);
        super.onLivingUpdate();

        if (this.worldObj.isRemote) {
            if (this.getNorVoxType() == 0) {
                this.setSize(1.135F, 1.575F);
            } else {
                this.setSize(1.135F / 1.455F, 1.575F / 1.455F);
            }
        } else {
            if (this.superiorForm == null && this.getNorVoxType() == 1)
                this.superiorForm = new EntityVoxStellarum(this.worldObj);
            if (this.isFiring()) this.decrementFiringTicks();
            if (this.getAttackTime() > 0) this.decrementAttackTime();
            if (this.getNodTicks() > 0) this.decrementNodTicks();
            if (this.getAttackTarget() == null) this.setFiringTicks(0);
            if (TragicConfig.allowStun && this.isPotionActive(TragicPotion.Stun)) {
                if (this.isFiring()) this.setFiringTicks(0);
                if (this.getAttackTime() == 0) this.setAttackTime(10);
            }

            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
                .removeModifier(mod);
            if (this.getFiringTicks() >= 60) this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
                .applyModifier(mod);

            if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 1.0F
                && rand.nextInt(64) == 0
                && !this.isFiring()) {
                boolean flag = !TragicConfig.allowStun ? true
                    : (this.isPotionActive(TragicPotion.Stun.id) ? false : true);
                if (flag) this.setFiringTicks(120);
            }

            if (this.getFiringTicks() >= 60 && this.ticksExisted % 20 == 0 && this.getAttackTarget() != null)
                this.shootProjectiles();

            if (this.ticksExisted % 120 == 0 && this.getHealth() < this.getMaxHealth()
                && TragicConfig.norVoxRegeneration) this.heal(6.0F);

            if (!this.isFiring() && this.getAttackTarget() == null
                && this.ticksExisted % 60 == 0
                && rand.nextInt(4) == 0) this.setNodTicks(20);
        }

        if (!this.onGround && this.motionY < 0.0D) this.motionY *= 0.68D;
    }

    protected void shootProjectiles() {
        if (!TragicConfig.norVoxProjectiles) return;

        if (this.getNorVoxType() == 0) {
            double d0 = this.getAttackTarget().posX - this.posX;
            double d1 = this.getAttackTarget().posY - this.posY;
            double d2 = this.getAttackTarget().posZ - this.posZ;

            float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.95F;
            for (int i = 0; i < 2 + rand.nextInt(2); i++) {
                EntityWitherSkull skull = new EntityWitherSkull(
                    this.worldObj,
                    this,
                    d0 + this.rand.nextGaussian() * f1,
                    d1,
                    d2 + this.rand.nextGaussian() * f1);
                skull.posX = this.posX + 0.115D * d0;
                skull.posY = this.posY + (this.height * 2 / 3);
                skull.posZ = this.posZ + 0.115D * d2;
                this.worldObj.spawnEntityInWorld(skull);
            }
        } else {
            double d0 = this.getAttackTarget().posX - this.posX;
            double d1 = this.getAttackTarget().posY - this.posY;
            double d2 = this.getAttackTarget().posZ - this.posZ;

            float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.925F;
            for (int i = 0; i < 2; i++) {
                EntityStarShard shard = new EntityStarShard(
                    this.worldObj,
                    this,
                    d0 + this.rand.nextGaussian() * f1,
                    d1,
                    d2 + this.rand.nextGaussian() * f1);
                shard.posX = this.posX + 0.115D * d0;
                shard.posY = this.posY + (this.height * 2 / 3);
                shard.posZ = this.posZ + 0.115D + d2;
                this.worldObj.spawnEntityInWorld(shard);
            }
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (this.worldObj.isRemote || par1DamageSource.isExplosion() || par1DamageSource == DamageSource.wither)
            return false;

        if (par1DamageSource.isProjectile()) par2 /= 4;

        boolean result = super.attackEntityFrom(par1DamageSource, par2);

        if (result) {
            this.setAttackTime(10);
            if (this.getNodTicks() > 0) this.setNodTicks(0);
            if (this.isFiring() && par1DamageSource.getEntity() != null
                && rand.nextInt(4) == 0
                && this.getFiringTicks() >= 40) {
                this.setFiringTicks(0);
                if (TragicConfig.allowStun)
                    this.addPotionEffect(new PotionEffect(TragicPotion.Stun.id, 60 + rand.nextInt(40)));
                if (TragicConfig.allowAchievements && par1DamageSource.getEntity() instanceof EntityPlayerMP)
                    ((EntityPlayerMP) par1DamageSource.getEntity()).triggerAchievement(TragicAchievements.norVox);
            }
        }

        return result;
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        boolean flag = super.attackEntityAsMob(par1Entity);

        if (flag && par1Entity instanceof EntityLivingBase && rand.nextInt(8) == 0) {
            if (TragicConfig.allowSubmission) ((EntityLivingBase) par1Entity)
                .addPotionEffect(new PotionEffect(TragicPotion.Submission.id, rand.nextInt(120) + 60, rand.nextInt(2)));
        }
        return flag;
    }

    @Override
    public int getTotalArmorValue() {
        return (int) (this.getNorVoxType() == 0 ? norVoxStats[5] : starVoxStats[5]);
    }

    @Override
    protected void fall(float par1) {}

    @Override
    public void setInWeb() {}

    @Override
    public boolean getCanSpawnHere() {
        int i = MathHelper.floor_double(this.boundingBox.minY);

        if (i >= 63) {
            int x = MathHelper.floor_double(this.posX);
            int z = MathHelper.floor_double(this.posZ);

            BiomeGenBase spawnBiome = this.worldObj.getBiomeGenForCoords(x, z);

            if (spawnBiome == BiomeGenBase.jungle || spawnBiome == BiomeGenBase.jungleHills) {
                return this.worldObj.getBlockLightValue(x, i, z) > this.rand.nextInt(7) ? false
                    : super.getCanSpawnHere();
            }
            return false;
        } else {
            int j = MathHelper.floor_double(this.posX);
            int k = MathHelper.floor_double(this.posZ);
            int l = this.worldObj.getBlockLightValue(j, i, k);
            byte b0 = 4;
            Calendar calendar = this.worldObj.getCurrentDate();

            if ((calendar.get(2) + 1 != 10 || calendar.get(5) < 20)
                && (calendar.get(2) + 1 != 11 || calendar.get(5) > 3)) {
                if (this.rand.nextBoolean()) {
                    return false;
                }
            } else {
                b0 = 7;
            }

            return l > this.rand.nextInt(b0) ? false : super.getCanSpawnHere();
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("norVoxType")) this.setNorVoxType(tag.getByte("norVoxType"));
        if (tag.hasKey("firingTicks")) this.setFiringTicks(tag.getInteger("firingTicks"));
        if (tag.hasKey("attackTime")) this.setAttackTime(tag.getInteger("attackTime"));
        if (tag.hasKey("textureID")) this.setTextureID(tag.getByte("textureID"));
        if (tag.hasKey("nodTicks")) this.setNodTicks(tag.getInteger("nodTicks"));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setByte("norVoxType", this.getNorVoxType());
        tag.setInteger("firingTicks", this.getFiringTicks());
        tag.setInteger("attackTime", this.getAttackTime());
        tag.setByte("textureID", this.getTextureID());
        tag.setInteger("nodTicks", this.getNodTicks());
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        if (!this.worldObj.isRemote) {
            BiomeGenBase biome = this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ);
            this.setNorVoxType(biome instanceof BiomeGenStarlitPrarie ? (byte) 1 : 0);
            this.setTextureID((byte) rand.nextInt(8));
        }
        return super.onSpawnWithEgg(data);
    }

    @Override
    protected boolean isChangeAllowed() {
        return TragicConfig.allowVoxStellarum;
    }

    @Override
    public String getLivingSound() {
        return TragicConfig.allowMobSounds
            ? (this.getNorVoxType() == 0 ? "tragicmc:mob.norvox.scrape" : "tragicmc:mob.cryse.glass")
            : null;
    }

    @Override
    public String getHurtSound() {
        return TragicConfig.allowMobSounds
            ? (this.getNorVoxType() == 0 ? "tragicmc:mob.norvox.hit" : "tragicmc:mob.cryse.hit")
            : super.getHurtSound();
    }

    @Override
    public String getDeathSound() {
        return TragicConfig.allowMobSounds
            ? (this.getNorVoxType() == 0 ? "tragicmc:mob.norvox.hit" : "tragicmc:mob.cryse.break")
            : null;
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
    public int getTalkInterval() {
        return super.getTalkInterval();
    }

    @Override
    protected void func_145780_a(int x, int y, int z, Block block) {
        if (this.getNorVoxType() == 0 && TragicConfig.allowMobSounds)
            this.playSound("tragicmc:mob.norvox.scrape", 0.45F, 1.0F);
    }

    @Override
    public int getDropAmount() {
        return 3;
    }

    @Override
    public String getVariantName() {
        return "TragicMC.StarVox";
    }
}
