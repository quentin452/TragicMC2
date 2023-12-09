package tragicneko.tragicmc.entity.boss;

import static tragicneko.tragicmc.TragicConfig.kitsunakumaStats;

import java.util.UUID;

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
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.EntityAIWatchTarget;
import tragicneko.tragicmc.entity.EntityKurayami;

public class EntityKitsune extends TragicBoss {

    private AttributeModifier mod = new AttributeModifier(
        UUID.fromString("c6334c3a-6cf4-4755-8fe5-d1b713c1f375"),
        "kitsuneSpeedDebuff",
        TragicConfig.modifier[1],
        0);

    public EntityKitsune(World par1World) {
        super(par1World);
        this.setSize(0.745F, 1.745F);
        this.experienceValue = 60;
        this.getNavigator()
            .setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
        this.tasks.addTask(8, new EntityAIWatchTarget(this, 32.0F));
        this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0D, 64.0F));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.isImmuneToFire = true;
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return TragicEntities.Beast;
    }

    @Override
    public boolean canRenderOnFire() {
        return false;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(kitsunakumaStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(kitsunakumaStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(kitsunakumaStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(kitsunakumaStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(kitsunakumaStats[4]);
    }

    @Override
    public int getTotalArmorValue() {
        return (int) kitsunakumaStats[5];
    }

    @Override
    public void onDeath(DamageSource src) {
        super.onDeath(src);
        if (src.getEntity() instanceof EntityPlayerMP && TragicConfig.allowAchievements)
            ((EntityPlayerMP) src.getEntity()).triggerAchievement(TragicAchievements.kitsunakuma);
    }

    @Override
    protected void dropFewItems(boolean flag, int l) {
        super.dropFewItems(flag, l);
        if (!this.worldObj.isRemote && TragicConfig.allowMobStatueDrops
            && rand.nextInt(100) <= TragicConfig.mobStatueDropChance
            && this.getAllowLoot())
            this.capturedDrops.add(
                new EntityItem(
                    this.worldObj,
                    this.posX,
                    this.posY,
                    this.posZ,
                    new ItemStack(TragicItems.MobStatue, 1, 1)));
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

    private void decrementFiringTicks() {
        int pow = this.getFiringTicks();
        this.setFiringTicks(--pow);
    }

    public boolean isFiring() {
        return this.getFiringTicks() > 0;
    }

    public int getTauntTicks() {
        return this.dataWatcher.getWatchableObjectInt(17);
    }

    private void setTauntTicks(int i) {
        this.dataWatcher.updateObject(17, i);
    }

    private void decrementTauntTicks() {
        int pow = this.getTauntTicks();
        this.setTauntTicks(--pow);
    }

    public int getHurtTime() {
        return this.dataWatcher.getWatchableObjectInt(18);
    }

    private void setHurtTime(int i) {
        this.dataWatcher.updateObject(18, i);
    }

    private void decrementHurtTime() {
        int pow = this.getHurtTime();
        this.setHurtTime(--pow);
    }

    public int getAttackTime() {
        return this.dataWatcher.getWatchableObjectInt(19);
    }

    private void setAttackTime(int i) {
        this.dataWatcher.updateObject(19, i);
    }

    private void decrementAttackTime() {
        int pow = this.getAttackTime();
        this.setAttackTime(--pow);
    }

    @Override
    public void onLivingUpdate() {
        if (this.getTauntTicks() > 0 || this.isFiring()) this.motionX = this.motionZ = 0.0D;
        if (this.getTauntTicks() > 0 || this.isFiring()) this.motionY = -0.1D;

        super.onLivingUpdate();

        if (this.worldObj.isRemote) {
            if (rand.nextBoolean() || this.isFiring() || this.getHurtTime() > 0) {
                int wow = this.isFiring() ? 4 : (this.getHurtTime() > 0 ? 4 : 1);
                String s = this.getHurtTime() > 0 ? "smoke" : "flame";

                for (int i = 0; i < wow; i++) {
                    this.worldObj.spawnParticle(
                        s,
                        this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.355D),
                        this.posY + 0.115D + rand.nextDouble(),
                        this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.355D),
                        0.0F,
                        0.155F * this.rand.nextFloat(),
                        0.0F);
                }
            }

            if (this.getHurtTime() == 100) {
                for (int i = 0; i < 36; i++) {
                    this.worldObj.spawnParticle(
                        "flame",
                        this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.355D),
                        this.posY + 0.115D + rand.nextDouble(),
                        this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.355D),
                        rand.nextFloat() - rand.nextFloat(),
                        0.155F * this.rand.nextFloat(),
                        rand.nextFloat());
                }
            }
            return;
        }
        if (this.getAttackTime() > 0) this.decrementAttackTime();
        if (this.isFiring()) this.decrementFiringTicks();
        if (this.getHurtTime() > 0) this.decrementHurtTime();
        if (this.getTauntTicks() > 0 && this.getHurtTime() > 0) this.setTauntTicks(0);
        if (this.getTauntTicks() > 0) this.decrementTauntTicks();

        if ((this.getTauntTicks() == 1 && TragicConfig.kitsunakumaTaunt || this.getAttackTime() == 1)
            && TragicConfig.kitsunakumaTeleport) this.teleportRandomly();

        if (this.getAttackTime() == 5 && this.getAttackTarget() != null
            && this.getDistanceToEntity(this.getAttackTarget()) <= 5.0F)
            this.getAttackTarget()
                .attackEntityFrom(
                    DamageSource.causeMobDamage(this),
                    (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
                        .getAttributeValue()); // double swipe

        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .removeModifier(mod);
        if (this.isFiring() || this.getTauntTicks() > 0) this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .applyModifier(mod);

        if (this.isInWater() && TragicConfig.kitsunakumaTeleport) this.teleportRandomly();

        if (this.getAttackTarget() != null) {
            if (this.isFiring() && (this.getDistanceToEntity(this.getAttackTarget()) < 4.0F
                || this.getDistanceToEntity(this.getAttackTarget()) >= 14.0F)) {
                this.setFiringTicks(0);
            } else
                if (this.onGround && this.getDistanceToEntity(this.getAttackTarget()) < 4.0F && rand.nextInt(32) == 0) {
                    double d0 = this.getAttackTarget().posX - this.posX;
                    double d1 = this.getAttackTarget().posZ - this.posZ;
                    double d2 = this.getAttackTarget().posY - this.posY;
                    float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
                    this.motionX = d0 / f2 * 1.05D * 0.500000011920929D + this.motionX * 0.40000000298023224D;
                    this.motionZ = d1 / f2 * 1.05D * 0.500000011920929D + this.motionZ * 0.40000000298023224D;
                    this.motionY = d1 / f2 * 1.1D * 0.200000011920929D + this.motionY * 0.20000000298023224D;
                }

            if (this.canEntityBeSeen(this.getAttackTarget())) {
                if (this.rand.nextInt(48) == 0) {
                    EntityLivingBase entity = this.getAttackTarget();

                    if (rand.nextInt(72) == 0) {
                        entity.addPotionEffect(new PotionEffect(Potion.blindness.id, 300 + rand.nextInt(320), 0));
                    }

                    if (rand.nextInt(72) == 0 && TragicConfig.allowDisorientation) {
                        entity.addPotionEffect(
                            new PotionEffect(TragicPotion.Disorientation.id, 300 + rand.nextInt(320), rand.nextInt(3)));
                    }

                    if (rand.nextInt(72) == 0) {
                        entity.addPotionEffect(new PotionEffect(Potion.confusion.id, 300 + rand.nextInt(320), 0));
                    }

                    if (this.ticksExisted % 120 == 0 && this.rand.nextInt(16) == 0
                        && TragicConfig.kitsunakumaTeleport) {
                        this.teleportRandomly();
                    }
                }

                if (this.getHurtTime() % 20 == 0 && this.getHurtTime() > 0
                    && this.getDistanceToEntity(this.getAttackTarget()) > 4.0F
                    && TragicConfig.kitsunakumaFireballs) {
                    double d0 = this.getAttackTarget().posX - this.posX;
                    double d1 = this.getAttackTarget().boundingBox.minY + this.getAttackTarget().height / 3.0F
                        - (this.posY + this.height / 2.0F);
                    double d2 = this.getAttackTarget().posZ - this.posZ;

                    float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.175F;

                    for (int i = 0; i < 3; i++) {
                        EntitySmallFireball fireball = new EntitySmallFireball(
                            this.worldObj,
                            this,
                            d0 + this.rand.nextGaussian() * f1,
                            d1,
                            d2 + this.rand.nextGaussian() * f1);
                        fireball.posY = this.posY + (this.height * 2 / 3);
                        this.worldObj.spawnEntityInWorld(fireball);
                    }
                }
            } else {
                if (this.rand.nextInt(56) == 0
                    || this.getHurtTime() > 0 && this.getHurtTime() % 20 == 0
                        && this.getDistanceToEntity(this.getAttackTarget()) > 4.0F
                    || this.getDistanceToEntity(this.getAttackTarget()) >= 14.0F && rand.nextInt(4) == 0) {
                    if (TragicConfig.kitsunakumaTeleport) this.teleportToEntity(this.getAttackTarget());
                }
            }

            if (this.isEntityInRange(this.getAttackTarget(), 6.0F, 16.0F) && rand.nextInt(4) == 0
                && !this.isFiring()
                && this.canEntityBeSeen(this.getAttackTarget())
                && this.getTauntTicks() == 0
                && this.ticksExisted % 5 == 0
                && TragicConfig.kitsunakumaFireballs) {
                this.setFiringTicks(40);
            }

            if (this.isEntityInRange(this.getAttackTarget(), 4.0F, 16.0F)
                && this.canEntityBeSeen(this.getAttackTarget())
                && this.getTauntTicks() == 0
                && this.isFiring()
                && this.getFiringTicks() % 25 == 0
                && TragicConfig.kitsunakumaFireballs) {
                double d0 = this.getAttackTarget().posX - this.posX;
                double d1 = this.getAttackTarget().boundingBox.minY + this.getAttackTarget().height / 2.0F
                    - (this.posY + this.height / 2.0F);
                double d2 = this.getAttackTarget().posZ - this.posZ;

                EntityLargeFireball fireball = new EntityLargeFireball(this.worldObj, this, d0, d1, d2);
                fireball.posY = this.posY + (this.height * 2 / 3);
                this.worldObj.spawnEntityInWorld(fireball);
            }

            if (this.getDistanceToEntity(this.getAttackTarget()) >= 12.0F && rand.nextInt(36) == 0
                && !this.isFiring()
                && this.getTauntTicks() == 0
                && TragicConfig.kitsunakumaTeleport) {
                boolean flag = this.teleportToEntity(this.getAttackTarget());
                if (!flag) this.teleportRandomly();
            }

            if (!this.isFiring() && this.getDistanceToEntity(this.getAttackTarget()) > 8.0F
                && this.getDistanceToEntity(this.getAttackTarget()) < 16.0F
                && rand.nextInt(56) == 0
                && this.getTauntTicks() == 0
                && TragicConfig.kitsunakumaTaunt) this.setTauntTicks(40);
            if (this.getTauntTicks() == 40 && TragicConfig.allowMobSounds)
                this.worldObj.playSoundAtEntity(this, "tragicmc:boss.kitsune.taunt", 1.0F, 1.0F);
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (this.worldObj.isRemote || this.getHurtTime() > 0) return false;

        boolean flag = false;

        if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();
            flag = player.getCurrentEquippedItem() != null && (player.getCurrentEquippedItem()
                .getItem() == TragicItems.SwordOfJustice
                || player.getCurrentEquippedItem()
                    .getItem() == TragicItems.BowOfJustice);
        }

        if (par1DamageSource.getEntity() instanceof EntityKurayami) {
            return super.attackEntityFrom(par1DamageSource, par2 * 0.145F);
        }

        if (!TragicConfig.kitsunakumaFireballExempt) return super.attackEntityFrom(par1DamageSource, par2);

        if (!par1DamageSource.getDamageType()
            .equals("fireball") && !flag) {
            return super.attackEntityFrom(par1DamageSource, 0.0F);
        } else {
            if (this.getHurtTime() == 0 && !flag) this.setHurtTime(100);
            par2 = flag ? Float.MAX_VALUE : (this.isFiring() && this.getFiringTicks() % 20 >= 15 ? 20 : 10);
            if (!flag && par1DamageSource.getEntity() != null && TragicConfig.kitsunakumaTeleport)
                this.teleportToEntity(par1DamageSource.getEntity());
        }

        return super.attackEntityFrom(par1DamageSource, par2);
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        if (this.worldObj.isRemote) return false;

        boolean flag = super.attackEntityAsMob(par1Entity);

        if (flag) {
            if (this.getTauntTicks() > 0) this.setTauntTicks(0);
            if (this.getFiringTicks() > 0) this.setFiringTicks(0);
            if (this.getAttackTime() == 0) this.setAttackTime(10);

            if (par1Entity instanceof EntityLivingBase && rand.nextBoolean()) {
                switch (rand.nextInt(8)) {
                    default:
                        ((EntityLivingBase) par1Entity)
                            .addPotionEffect(new PotionEffect(Potion.blindness.id, rand.nextInt(200) + 320));
                        break;
                    case 1:
                        ((EntityLivingBase) par1Entity)
                            .addPotionEffect(new PotionEffect(Potion.digSlowdown.id, rand.nextInt(200) + 320));
                        break;
                    case 2:
                        if (TragicConfig.allowDisorientation) {
                            ((EntityLivingBase) par1Entity).addPotionEffect(
                                new PotionEffect(TragicPotion.Disorientation.id, rand.nextInt(200) + 320));
                        }
                        break;
                }
            }

            if (this.rand.nextInt(4) == 0) par1Entity.setFire(4 + rand.nextInt(8));
        }

        return flag;
    }

    protected boolean teleportRandomly() {
        double d0 = this.posX + (this.rand.nextDouble() - 0.5D) * 24.0D;
        double d1 = this.posY + (this.rand.nextInt(48) - 24);
        double d2 = this.posZ + (this.rand.nextDouble() - 0.5D) * 24.0D;
        return this.teleportTo(d0, d1, d2);
    }

    protected boolean teleportToEntity(Entity par1Entity) {
        Vec3 vec3 = Vec3.createVectorHelper(
            this.posX - par1Entity.posX,
            this.boundingBox.minY + this.height / 2.0F - par1Entity.posY + par1Entity.getEyeHeight(),
            this.posZ - par1Entity.posZ);
        vec3 = vec3.normalize();
        double d0 = 16.0D;
        double d1 = this.posX + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.xCoord * d0;
        double d2 = this.posY + (this.rand.nextInt(16) - 8) - vec3.yCoord * d0;
        double d3 = this.posZ + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.zCoord * d0;
        return this.teleportTo(d1, d2, d3);
    }

    protected boolean teleportTo(double par1, double par3, double par5) {
        double d3 = this.posX;
        double d4 = this.posY;
        double d5 = this.posZ;
        this.posX = par1;
        this.posY = par3;
        this.posZ = par5;
        boolean flag = false;
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.posY);
        int k = MathHelper.floor_double(this.posZ);

        if (this.worldObj.blockExists(i, j, k)) {
            boolean flag1 = false;

            while (!flag1 && j > 0) {
                Block block = this.worldObj.getBlock(i, j - 1, k);

                if (block.getMaterial()
                    .blocksMovement()) {
                    flag1 = true;
                } else {
                    --this.posY;
                    --j;
                }
            }

            if (flag1) {
                this.setPosition(this.posX, this.posY, this.posZ);

                if (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox)
                    .isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox)) {
                    flag = true;
                }
            }
        }

        if (!flag) {
            this.setPosition(d3, d4, d5);
            return false;
        } else {
            short short1 = 128;

            for (int l = 0; l < short1; ++l) {
                double d6 = l / (short1 - 1.0D);
                float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
                float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
                float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
                double d7 = d3 + (this.posX - d3) * d6 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
                double d8 = d4 + (this.posY - d4) * d6 + this.rand.nextDouble() * this.height;
                double d9 = d5 + (this.posZ - d5) * d6 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
                this.worldObj.spawnParticle("flame", d7, d8, d9, f, f1, f2);
            }
            this.worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
            this.playSound(this.getLivingSound() == null ? "mob.endermen.portal" : this.getLivingSound(), 1.0F, 1.0F);
            return true;
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("firingTicks")) this.setFiringTicks(tag.getInteger("firingTicks"));
        if (tag.hasKey("tauntTicks")) this.setTauntTicks(tag.getInteger("tauntTicks"));
        if (tag.hasKey("hurtTime")) this.setHurtTime(tag.getInteger("hurtTime"));
        if (tag.hasKey("attackTime")) this.setAttackTime(tag.getInteger("attackTime"));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setInteger("firingTicks", this.getFiringTicks());
        tag.setInteger("tauntTicks", this.getTauntTicks());
        tag.setInteger("hurtTime", this.getHurtTime());
        tag.setInteger("attackTime", this.getAttackTime());
    }

    @Override
    public String getLivingSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:boss.kitsune.living" : null;
    }

    @Override
    public String getHurtSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:boss.kitsune.hurt" : super.getHurtSound();
    }

    @Override
    public String getDeathSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:boss.kitsune.hurt" : null;
    }

    @Override
    public float getSoundPitch() {
        return 0.8F + rand.nextFloat() * 0.2F;
    }

    @Override
    public float getSoundVolume() {
        return 0.6F;
    }

    @Override
    public int getTalkInterval() {
        return super.getTalkInterval() / 2;
    }
}
