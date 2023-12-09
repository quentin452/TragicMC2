package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.tragicNekoStats;

import java.util.Calendar;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.projectile.EntityNekoClusterBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoMiniBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoRocket;
import tragicneko.tragicmc.entity.projectile.EntityNekoStickyBomb;

public class EntityTragicNeko extends TragicMob {

    private AttributeModifier mod = new AttributeModifier(
        UUID.fromString("ef7bc471-3df8-4d0d-8aa6-8f52ae0a6045"),
        "tragicNekoSpeedDebuff",
        TragicConfig.modifier[9],
        0);

    public EntityTragicNeko(World par1World) {
        super(par1World);
        this.setSize(0.475F, 1.955F);
        this.experienceValue = 5;
        this.tasks.addTask(0, new EntityAISwimming(this));
        if (!this.isProperDate())
            this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.tasks.addTask(6, new EntityAIWander(this, 0.65D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
        if (!this.isProperDate()) this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 0.85D, 32.0F));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
        if (!this.isProperDate())
            this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    @Override
    public boolean canCorrupt() {
        return false;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, Integer.valueOf(0));
        this.dataWatcher.addObject(17, Integer.valueOf(0));
        this.dataWatcher.addObject(18, Integer.valueOf(0));
        this.dataWatcher.addObject(19, Integer.valueOf(0));
    }

    public int getFiringTicks() {
        return this.dataWatcher.getWatchableObjectInt(16);
    }

    private void setFiringTicks(int i) {
        this.dataWatcher.updateObject(16, i);
    }

    private void incrementFiringTicks() {
        int pow = this.getFiringTicks();
        this.setFiringTicks(++pow);
    }

    public boolean isAboutToFire() {
        return this.getFiringTicks() > 0 && this.getFiringTicks() <= 40;
    }

    public boolean hasFired() {
        return this.getFiringTicks() > 40;
    }

    public boolean canFire() {
        return this.getFiringTicks() >= 80;
    }

    public int getThrowingTicks() {
        return this.dataWatcher.getWatchableObjectInt(17);
    }

    private void setThrowingTicks(int i) {
        this.dataWatcher.updateObject(17, i);
    }

    private void decrementThrowingTicks() {
        int pow = this.getThrowingTicks();
        this.setThrowingTicks(--pow);
    }

    public int getAttackTime() {
        return this.dataWatcher.getWatchableObjectInt(18);
    }

    private void setAttackTime(int i) {
        this.dataWatcher.updateObject(18, i);
    }

    private void decrementAttackTime() {
        int pow = this.getAttackTime();
        this.setAttackTime(--pow);
    }

    public int getFlickTime() {
        return this.dataWatcher.getWatchableObjectInt(19);
    }

    private void setFlickTime(int i) {
        this.dataWatcher.updateObject(19, i);
    }

    private void decrementFlickTime() {
        int pow = this.getFlickTime();
        this.setFlickTime(--pow);
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(tragicNekoStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(tragicNekoStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(tragicNekoStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(tragicNekoStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(tragicNekoStats[4]);
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }

    @Override
    public void setInWeb() {}

    @Override
    public int getTotalArmorValue() {
        return (int) tragicNekoStats[5];
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (this.worldObj.isRemote) return;

        if (this.getAttackTarget() != null) this.incrementFiringTicks();
        if (this.getThrowingTicks() > 0) this.decrementThrowingTicks();
        if (this.getAttackTime() > 0) this.decrementAttackTime();
        if (this.getFlickTime() > 0) this.decrementFlickTime();

        if (this.getAttackTarget() != null && !this.isProperDate()) {
            if (this.getFlickTime() > 0) this.setFlickTime(0);

            if (this.getFiringTicks() == 40 && this.canEntityBeSeen(this.getAttackTarget())) {
                if (TragicConfig.tragicNekoRockets) this.doMissleAttack();
            } else if (this.hasFired() && rand.nextInt(8) == 0
                && this.getFiringTicks() % 20 == 0
                && this.getAttackTime() == 0) {
                    this.throwRandomProjectile();
                    this.setThrowingTicks(20);
                }

            int i = this.getHealth() <= this.getMaxHealth() / 2 ? 4 : 16;

            if (rand.nextInt(i) == 0 && this.canFire()
                && this.ticksExisted % 10 == 0
                && this.getThrowingTicks() == 0
                && this.getAttackTime() == 0
                && this.getDistanceToEntity(this.getAttackTarget()) >= 2.0F) {
                this.setFiringTicks(0);
            }
        } else {
            if (this.ticksExisted % 20 == 0 && rand.nextInt(4) == 0) this.setFlickTime(10);
            this.setFiringTicks(35);
        }

        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .removeModifier(mod);
        if (this.isAboutToFire() || this.getThrowingTicks() > 0)
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
                .applyModifier(mod);
    }

    @Override
    public void onDeath(DamageSource par1DamageSource) {
        super.onDeath(par1DamageSource);
        if (!this.worldObj.isRemote) this.setThrowingTicks(20);
    }

    @Override
    public void onDeathUpdate() {
        super.onDeathUpdate();

        if (this.worldObj.isRemote) return;

        if (this.deathTime == 20 && rand.nextInt(4) == 0 && !this.isProperDate() && TragicConfig.tragicNekoDeathBomb) {
            this.worldObj.playSoundAtEntity(this, "creeper.primed", 1.0F, 1.0F);
            int x = rand.nextInt(10) == 0 ? 1 : rand.nextInt(3) + 2;

            for (int i = 0; i < x; i++) {
                EntityNekoClusterBomb bomb = new EntityNekoClusterBomb(this.worldObj);

                bomb.posY = this.posY + 0.15;
                bomb.posX = this.posX + rand.nextDouble() - rand.nextDouble();
                bomb.posZ = this.posZ + rand.nextDouble() - rand.nextDouble();
                bomb.motionY = rand.nextDouble() + 0.25;
                bomb.motionZ = (rand.nextDouble() - rand.nextDouble()) * 0.125;
                bomb.motionX = (rand.nextDouble() - rand.nextDouble()) * 0.125;

                this.worldObj.spawnEntityInWorld(bomb);
            }
        }
    }

    private void doMissleAttack() {
        EntityLivingBase entity = this.getAttackTarget();
        double d0 = entity.posX - this.posX;
        double d1 = entity.boundingBox.minY + entity.height / 2.0 - this.posY - 0.65;
        double d2 = entity.posZ - this.posZ;
        EntityNekoRocket rocket = new EntityNekoRocket(this.worldObj, this, d0, d1, d2);
        rocket.shootingEntity = this;
        rocket.target = entity;
        rocket.posY = this.posY + (0.65D);
        rocket.posX = this.posX - (Math.sin(this.rotationYaw * (float) Math.PI / 180.0F) * 0.025D);
        rocket.posZ = this.posZ - (Math.cos(this.rotationYaw * (float) Math.PI / 180.0F) * 0.025D);
        this.worldObj.spawnEntityInWorld(rocket);
        this.worldObj.playSoundAtEntity(this, "tragicmc:random.rocketlaunch", 1.0F, 1.0F);
        this.worldObj.playSoundAtEntity(this, "tragicmc:random.rocketflying", 1.0F, 1.0F);
    }

    private void throwRandomProjectile() {
        EntityThrowable theProjectile = null;

        switch (rand.nextInt(5)) {
            case 1:
            case 4:
                if (TragicConfig.tragicNekoStickyBombs) theProjectile = new EntityNekoStickyBomb(this.worldObj, this);
                break;
            case 2:
                if (TragicConfig.tragicNekoClusterBombs) theProjectile = new EntityNekoClusterBomb(this.worldObj, this);
                break;
            default:
                if (TragicConfig.tragicNekoClusterBombs) theProjectile = new EntityNekoMiniBomb(this.worldObj, this);
                break;
        }

        if (theProjectile == null) return;

        theProjectile.motionX = (this.getAttackTarget().posX - this.posX) * 0.335D;
        theProjectile.motionZ = (this.getAttackTarget().posZ - this.posZ) * 0.335D;
        theProjectile.motionY = (this.getAttackTarget().posY - this.posY) * 0.335D;
        this.worldObj.spawnEntityInWorld(theProjectile);
    }

    @Override
    public boolean getCanSpawnHere() {
        if (MathHelper.floor_double(this.boundingBox.minY) <= 63) {
            return false;
        } else {
            return super.getCanSpawnHere() || this.isProperDate();
        }
    }

    public boolean isProperDate() {
        if (!TragicConfig.tragicNekoCelebration) return false;
        Calendar calendar = this.worldObj.getCurrentDate();

        if ((calendar.get(2) + 1 == 8 && calendar.get(5) > 29) || (calendar.get(2) + 1 == 9 || calendar.get(5) < 3)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (par1DamageSource.isExplosion() || this.worldObj.isRemote) return false;

        if (this.getFiringTicks() < 60) this.setFiringTicks(61);
        boolean result = super.attackEntityFrom(par1DamageSource, par2);
        if (result) this.setAttackTime(10);

        return result;
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        if (this.getThrowingTicks() > 0) return false;
        if (this.getFiringTicks() < 60) this.setFiringTicks(61);
        boolean result = super.attackEntityAsMob(par1Entity);
        if (result) this.setAttackTime(10);
        return result;
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("attackTime")) this.setAttackTime(tag.getInteger("attackTime"));
        if (tag.hasKey("firingTicks")) this.setFiringTicks(tag.getInteger("firingTicks"));
        if (tag.hasKey("throwingTicks")) this.setThrowingTicks(tag.getInteger("throwingTicks"));
        if (tag.hasKey("flickTime")) this.setFlickTime(tag.getInteger("flickTime"));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setInteger("attackTime", this.getAttackTime());
        tag.setInteger("firingTicks", this.getFiringTicks());
        tag.setInteger("throwingTicks", this.getThrowingTicks());
        tag.setInteger("flickTime", this.getFlickTime());
    }

    @Override
    protected boolean isChangeAllowed() {
        return false;
    }

    @Override
    public String getLivingSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.tragicneko.living" : null;
    }

    @Override
    public String getHurtSound() {
        return TragicConfig.allowMobSounds && rand.nextInt(6) == 0 ? "tragicmc:mob.tragicneko.hurt"
            : super.getHurtSound();
    }

    @Override
    public String getDeathSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.tragicneko.death" : null;
    }

    @Override
    public float getSoundPitch() {
        return 1.0F;
    }

    @Override
    public float getSoundVolume() {
        return 0.8F;
    }

    @Override
    public int getTalkInterval() {
        return 320 + rand.nextInt(120);
    }

    @Override
    public int getDropAmount() {
        return 3;
    }

    @Override
    public boolean interact(EntityPlayer player) {
        if (player.getCurrentEquippedItem() != null && this.isProperDate()) {
            ItemStack item = player.getCurrentEquippedItem();
            if (item.getItem() == Items.cake || item.getItem() == Item.getItemFromBlock(Blocks.tnt)) {
                if (this.worldObj.isRemote) {
                    for (byte i = 0; i < 16; i++) this.worldObj.spawnParticle(
                        "heart",
                        this.posX + rand.nextDouble() - rand.nextDouble(),
                        this.posY + rand.nextDouble() * this.height,
                        this.posZ + rand.nextDouble() - rand.nextDouble(),
                        1.0,
                        1.0,
                        1.0);
                } else {
                    ItemStack stack = new ItemStack(Items.fireworks, 1, 0);
                    NBTTagCompound tag = new NBTTagCompound();
                    NBTTagCompound newTag = new NBTTagCompound();
                    newTag.setByte("Flight", (byte) 0);
                    NBTTagList tagList = new NBTTagList();

                    NBTTagCompound explosion = new NBTTagCompound();
                    explosion.setByte("Type", (byte) 2);
                    explosion.setByte("Fade", (byte) 1);
                    explosion.setByte("Flicker", (byte) 1);
                    explosion.setIntArray("Colors", new int[] { 0xFF0000, 0x00FF00, 0x2378FF });
                    explosion.setIntArray("FadeColors", new int[] { 0xFFFFFF, 0xFFFFFF, 0xFFFFFF });
                    tagList.appendTag(explosion);

                    NBTTagCompound explosion2 = new NBTTagCompound();
                    explosion2.setByte("Type", (byte) 4);
                    explosion2.setByte("Trail", (byte) 1);
                    explosion2.setIntArray("Colors", new int[] { 0x00FF00, 0x4488FF, 0x45FF54 });
                    explosion2.setIntArray("FadeColors", new int[] { 0x00FF00, 0x4488FF, 0x45FF54 });
                    tagList.appendTag(explosion2);

                    NBTTagCompound explosion3 = new NBTTagCompound();
                    explosion3.setByte("Type", (byte) 3);
                    explosion3.setIntArray("Colors", new int[] { 0xFF0023, 0x004400, 0x2378FF });
                    explosion3.setIntArray("FadeColors", new int[] { 0x00FF00, 0x00FF00, 0x00FF00 });
                    tagList.appendTag(explosion3);

                    NBTTagCompound explosion4 = new NBTTagCompound();
                    explosion4.setByte("Type", (byte) 0);
                    explosion4.setByte("Trail", (byte) 1);
                    explosion4.setIntArray("Colors", new int[] { 0x000000, 0x000000, 0x000000 });
                    explosion4.setIntArray("FadeColors", new int[] { 0xFF0000, 0xFF0000, 0xFF0000 });
                    tagList.appendTag(explosion4);

                    NBTTagCompound explosion5 = new NBTTagCompound();
                    explosion5.setByte("Type", (byte) 1);
                    explosion5.setIntArray("Colors", new int[] { 0xFF0023, 0xFF4400, 0xFF7844 });
                    explosion5.setIntArray("FadeColors", new int[] { 0x46A0F3, 0xAF12EE, 0x4675FF });
                    tagList.appendTag(explosion5);

                    NBTTagCompound explosion6 = new NBTTagCompound();
                    explosion6.setByte("Type", (byte) 0);
                    explosion6.setIntArray("Colors", new int[] { 0x4436FF, 0x2246FF, 0x3311FF });
                    explosion6.setIntArray("FadeColors", new int[] { 0x00FF00, 0x0036FF, 0x0000FF });
                    tagList.appendTag(explosion6);

                    newTag.setTag("Explosions", tagList);
                    tag.setTag("Fireworks", newTag);
                    stack.setTagCompound(tag);

                    for (byte i = 0; i < 3; i++) this.worldObj.spawnEntityInWorld(
                        new EntityFireworkRocket(
                            this.worldObj,
                            this.posX + rand.nextDouble() - rand.nextDouble(),
                            this.posY + rand.nextDouble() * this.height,
                            this.posZ + rand.nextDouble() - rand.nextDouble(),
                            stack));
                }

                if (TragicConfig.allowAchievements && player instanceof EntityPlayerMP)
                    player.triggerAchievement(TragicAchievements.tragicNeko);
                if (!player.capabilities.isCreativeMode) player.getCurrentEquippedItem().stackSize--;
                return true;
            }
        }
        return false;
    }
}
