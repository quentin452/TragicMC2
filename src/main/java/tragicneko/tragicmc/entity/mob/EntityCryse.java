package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.cryseStats;
import static tragicneko.tragicmc.TragicConfig.starCryseStats;

import net.minecraft.block.Block;
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
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.entity.miniboss.EntityVoxStellarum;
import tragicneko.tragicmc.util.DamageHelper;
import tragicneko.tragicmc.worldgen.biome.BiomeGenStarlitPrarie;

public class EntityCryse extends TragicMob {

    public EntityCryse(World par1World) {
        super(par1World);
        this.stepHeight = 1.0F;
        this.experienceValue = 5;
        this.getNavigator()
            .setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 48.0F));
        this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0D, 48.0F));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    @Override
    public boolean isMobVariant() {
        return this.getCryseType() == 1;
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
    public boolean isAIEnabled() {
        return true;
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

    public int getSpinTicks() {
        return this.dataWatcher.getWatchableObjectInt(16);
    }

    protected void decrementSpinTicks() {
        int pow = this.getSpinTicks();
        this.setSpinTicks(--pow);
    }

    protected void setSpinTicks(int i) {
        this.dataWatcher.updateObject(16, i);
    }

    public boolean isSpinning() {
        return this.getSpinTicks() > 0;
    }

    public byte getCryseType() {
        return this.dataWatcher.getWatchableObjectByte(17);
    }

    protected void setCryseType(byte b) {
        this.dataWatcher.updateObject(17, b);

        if (b == 0) {
            this.setSize(0.425F, 1.475F);
        } else {
            this.experienceValue = 6;
            this.setSize(0.525F * 0.625F, 1.475F * 0.625F);
        }
    }

    public byte getTextureID() {
        return this.dataWatcher.getWatchableObjectByte(18);
    }

    protected void setTextureID(byte b) {
        this.dataWatcher.updateObject(18, b);
    }

    public int getAttackTime() {
        return this.dataWatcher.getWatchableObjectInt(19);
    }

    protected void setAttackTime(int i) {
        this.dataWatcher.updateObject(19, i);
    }

    private void decrementAttackTime() {
        int pow = this.getAttackTime();
        this.setAttackTime(--pow);
    }

    public int getFlutterTicks() {
        return this.dataWatcher.getWatchableObjectInt(20);
    }

    protected void setFlutterTicks(int i) {
        this.dataWatcher.updateObject(20, i);
    }

    private void decrementFlutterTicks() {
        int pow = this.getFlutterTicks();
        this.setFlutterTicks(--pow);
    }

    public boolean isFluttering() {
        return this.getFlutterTicks() > 0;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        boolean flag = this.getCryseType() == 0;
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(flag ? cryseStats[0] : starCryseStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(flag ? cryseStats[1] : starCryseStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(flag ? cryseStats[2] : starCryseStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(flag ? cryseStats[3] : starCryseStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(flag ? cryseStats[4] : starCryseStats[4]);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (this.worldObj.isRemote) {
            if (this.getCryseType() == 0) {
                this.setSize(0.425F, 1.475F);
            } else {
                this.setSize(0.525F * 0.625F, 1.475F * 0.625F);
            }
        } else {
            if (this.isSpinning()) this.decrementSpinTicks();
            if (this.getAttackTime() > 0) this.decrementAttackTime();
            if (this.isFluttering()) this.decrementFlutterTicks();
            if (this.superiorForm == null && this.getCryseType() == 1)
                this.superiorForm = new EntityVoxStellarum(this.worldObj);

            if (this.ticksExisted % 120 == 0 && this.getAttackTarget() == null
                && rand.nextBoolean()
                && !this.isSpinning()
                && !this.isFluttering()) {
                if (rand.nextBoolean()) {
                    this.setSpinTicks(120);
                } else {
                    this.setFlutterTicks(60);
                }
            }
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (this.worldObj.isRemote) return false;

        if (this.isSpinning()) this.setSpinTicks(0);
        if (this.isFluttering()) this.setFlutterTicks(0);

        if (par1DamageSource.isFireDamage()) {
            par2 = (par2 * 4.175F) - 1.0F;
        }

        if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

            if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem()
                .getItem() instanceof ItemPickaxe) {
                par2 *= 1.275;
            }
        }

        boolean result = super.attackEntityFrom(par1DamageSource, par2);

        if (result) this.setAttackTime(10);

        if (result && par1DamageSource.getEntity() != null
            && this.rand.nextInt(4) == 0
            && TragicConfig.cryseReflection) {
            if (TragicConfig.allowMobSounds)
                this.worldObj.playSoundAtEntity(this, "tragicmc:mob.cryse.deflect", 1.0F, 1.9F);
            par1DamageSource.getEntity()
                .attackEntityFrom(DamageHelper.causeModMagicDamageToEntity(this), par2 / 4);
        }

        return result;
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        if (this.worldObj.isRemote) return false;

        boolean result = super.attackEntityAsMob(par1Entity);

        EnumDifficulty dif = this.worldObj.difficultySetting;
        int x = 2;

        if (dif == EnumDifficulty.NORMAL) {
            x = 3;
        } else if (dif == EnumDifficulty.HARD) {
            x = 4;
        }

        if (result) {
            if (this.isSpinning()) this.dataWatcher.updateObject(16, 0);
            if (this.getFlutterTicks() > 0) this.setFlutterTicks(0);

            if (par1Entity instanceof EntityLivingBase) {
                if (rand.nextInt(16 / x) == 0) ((EntityLivingBase) par1Entity).addPotionEffect(
                    new PotionEffect(Potion.moveSlowdown.id, 100 + rand.nextInt(140), 1 + rand.nextInt(2)));
                if (rand.nextInt(32 / x) == 0) ((EntityLivingBase) par1Entity).addPotionEffect(
                    new PotionEffect(Potion.digSlowdown.id, 100 + rand.nextInt(140), 1 + rand.nextInt(2)));
            }
        }

        return result;
    }

    @Override
    public int getTotalArmorValue() {
        return (int) (this.getCryseType() == 0 ? cryseStats[5] : starCryseStats[5]);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("texture")) this.setTextureID(tag.getByte("texture"));
        if (tag.hasKey("cryseType")) this.setCryseType(tag.getByte("cryseType"));
        if (tag.hasKey("spinTicks")) this.setSpinTicks(tag.getInteger("spinTicks"));
        if (tag.hasKey("attackTime")) this.setAttackTime(tag.getInteger("attackTime"));
        if (tag.hasKey("flutterTicks")) this.setFlutterTicks(tag.getInteger("flutterTicks"));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setByte("texture", this.getTextureID());
        tag.setByte("cryseType", this.getCryseType());
        tag.setInteger("spinTicks", this.getSpinTicks());
        tag.setInteger("attackTime", this.getAttackTime());
        tag.setInteger("flutterTicks", this.getFlutterTicks());
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        if (!this.worldObj.isRemote) {
            BiomeGenBase biome = this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ);
            this.setCryseType(biome instanceof BiomeGenStarlitPrarie ? (byte) 1 : 0);
            this.setTextureID((byte) rand.nextInt(8));
        }
        return super.onSpawnWithEgg(data);
    }

    @Override
    protected boolean isChangeAllowed() {
        return TragicConfig.allowMegaCryse;
    }

    @Override
    public String getLivingSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.cryse.glass" : null;
    }

    @Override
    public String getHurtSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.cryse.hit" : super.getHurtSound();
    }

    @Override
    public String getDeathSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.cryse.break" : null;
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

    }

    @Override
    public String getVariantName() {
        return "TragicMC.StarCryse";
    }
}
