package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.pyragrStats;
import static tragicneko.tragicmc.TragicConfig.ragrStats;

import java.util.ArrayList;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBow;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import com.google.common.collect.Sets;

import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.blocks.BlockDarkCobble;
import tragicneko.tragicmc.blocks.BlockGenericGrass;
import tragicneko.tragicmc.blocks.BlockPermafrost;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.entity.miniboss.EntityJarra;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityRagr extends TragicMob {

    public static Set crushableBlocks = Sets.newHashSet(
        new Block[] { Blocks.yellow_flower, Blocks.red_flower, Blocks.red_mushroom, Blocks.brown_mushroom,
            Blocks.tallgrass, Blocks.leaves, Blocks.leaves2, TragicBlocks.HallowedLeaves, TragicBlocks.AshenLeaves,
            TragicBlocks.DarkLeaves, TragicBlocks.PaintedLeaves, TragicBlocks.BleachedLeaves,
            TragicBlocks.AshenTallGrass, TragicBlocks.AshenBush, Blocks.deadbush, TragicBlocks.TragicFlower,
            TragicBlocks.StarlitTallGrass, TragicBlocks.DarkTallGrass, Blocks.snow_layer });

    public EntityRagr(World par1World) {
        super(par1World);
        this.setSize(1.335F, 2.675F);
        this.stepHeight = 1.5F;
        this.experienceValue = 10;
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityTameable.class, 0, true));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true));
    }

    @Override
    public boolean isMobVariant() {
        return this.getRagrType() == 1;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, Integer.valueOf(0));
        this.dataWatcher.addObject(17, (byte) 0);
    }

    public int getAngerTicks() {
        return this.dataWatcher.getWatchableObjectInt(16);
    }

    private void setAngerTicks(int i) {
        this.dataWatcher.updateObject(16, i);
    }

    private void incrementAngerTicks() {
        int pow = this.getAngerTicks();
        this.setAngerTicks(++pow);
    }

    private void decrementAngerTicks() {
        int pow = this.getAngerTicks();
        this.setAngerTicks(--pow);
    }

    public byte getRagrType() {
        return this.dataWatcher.getWatchableObjectByte(17);
    }

    protected void setRagrType(byte b) {
        this.dataWatcher.updateObject(17, b);

        if (b != 0) {
            this.experienceValue = 16;
            this.isImmuneToFire = true;
        }
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return TragicEntities.Beast;
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        boolean flag = this.getRagrType() == 0;
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(flag ? ragrStats[0] : pyragrStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(flag ? ragrStats[1] : pyragrStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(flag ? ragrStats[2] : pyragrStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(flag ? ragrStats[3] : pyragrStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(flag ? ragrStats[4] : pyragrStats[4]);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.worldObj.isRemote) return;

        if (this.superiorForm == null && this.getRagrType() == 1) this.superiorForm = new EntityJarra(this.worldObj);
        if (this.getRagrType() == 1 && this.ticksExisted % 5 == 0
            && this.worldObj.isRaining()
            && this.worldObj.canBlockSeeTheSky((int) this.posX, (int) this.posY, (int) this.posZ))
            this.attackEntityFrom(DamageSource.drown, 1.0F);
        if (this.getRagrType() == 1 && this.isInsideOfMaterial(Material.water))
            this.attackEntityFrom(DamageSource.drown, 1.0F);

        if (this.getAttackTarget() != null) {
            this.incrementAngerTicks();
            if (this.isCorrupted()) this.incrementAngerTicks();

            if (this.onGround && this.rand.nextInt(8) == 0) {
                double d0 = this.getAttackTarget().posX - this.posX;
                double d1 = this.getAttackTarget().posZ - this.posZ;
                float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
                this.motionX = d0 / f2 * 1.5D * 0.800000011920929D + this.motionX * 0.60000000298023224D;
                this.motionZ = d1 / f2 * 1.5D * 0.800000011920929D + this.motionZ * 0.60000000298023224D;
                this.jump();
            } else if (this.onGround && rand.nextBoolean()) {
                double d0 = rand.nextDouble() * 1.45D - rand.nextDouble() * 1.45D;
                double d1 = rand.nextDouble() * 1.45D - rand.nextDouble() * 1.45D;
                float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
                this.motionX = d0 / f2 * 1.5D * 0.800000011920929D + this.motionX * 0.60000000298023224D;
                this.motionZ = d1 / f2 * 1.5D * 0.800000011920929D + this.motionZ * 0.60000000298023224D;
                this.jump();
            }
        } else {
            if (this.getAngerTicks() > 0) this.decrementAngerTicks();

            if (this.onGround && rand.nextBoolean()) {
                double d0 = rand.nextDouble() * 1.45D - rand.nextDouble() * 1.45D;
                double d1 = rand.nextDouble() * 1.45D - rand.nextDouble() * 1.45D;
                float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
                this.motionX = d0 / f2 * 1.5D * 0.800000011920929D + this.motionX * 0.60000000298023224D;
                this.motionZ = d1 / f2 * 1.5D * 0.800000011920929D + this.motionZ * 0.60000000298023224D;
                this.jump();
            }
        }
    }

    @Override
    protected void jump() {
        if (TragicConfig.allowMobSounds) this.playSound("tragicmc:mob.ragr.jump", this.getSoundVolume(), 0.8F);
        this.motionY = this.getAngerTicks() > 600 ? rand.nextDouble() * 1.055 + 0.455 : 0.545;
        if (this.isPotionActive(Potion.jump)) this.motionY += (this.getActivePotionEffect(Potion.jump)
            .getAmplifier() + 1) * 0.1F;

        if (this.isSprinting()) {
            float f = this.rotationYaw * 0.017453292F;
            this.motionX -= MathHelper.sin(f) * 0.2F;
            this.motionZ += MathHelper.cos(f) * 0.2F;
        }

        this.isAirBorne = true;
    }

    @Override
    protected void fall(float par1) {
        if (this.worldObj.isRemote) return;

        boolean flag = this.getMobGriefing() && TragicConfig.ragrExplosions;

        if (par1 >= 8.0F) {
            this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() * 3.0F + 2.0F, flag);
        } else if (par1 >= 4.0F) {
            this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() * 2.0F + 1.0F, flag);
        } else if (par1 >= 2.0F) {
            this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() * 2.0F, flag);
        }

        if (!flag || !TragicConfig.ragrBlockCrushing) return;

        int x = (int) this.posX;
        int y = (int) this.posY;
        int z = (int) this.posZ;
        par1 = MathHelper.clamp_float(par1 / 2.0F, 1.0F, 4.0F);
        ArrayList<int[]> list = WorldHelper.getBlocksInSphericalRange(worldObj, par1, x, y, z);
        int[] coords;
        Block block;

        for (int i = 0; i < list.size(); i++) {
            coords = list.get(i);
            x = coords[0];
            y = coords[1];
            z = coords[2];
            block = this.worldObj.getBlock(x, y, z);

            if (EntityRagr.crushableBlocks.contains(block)) {
                this.worldObj.setBlockToAir(x, y, z);
            } else if (block == Blocks.grass) {
                this.worldObj.setBlock(x, y, z, Blocks.dirt);
            } else if (block == Blocks.stone) {
                this.worldObj.setBlock(x, y, z, Blocks.cobblestone);
            } else if (block == Blocks.stonebrick) {
                this.worldObj.setBlock(x, y, z, Blocks.stonebrick, 2, 2);
            } else if (block == Blocks.cobblestone) {
                this.worldObj.setBlock(x, y, z, Blocks.gravel);
            } else if (TragicConfig.allowNonMobBlocks) // if mobsOnly mode is enabled all of these blocks will be null
            {
                if (block instanceof BlockGenericGrass) {
                    this.worldObj.setBlock(x, y, z, TragicBlocks.DeadDirt);
                } else if (block instanceof BlockPermafrost) {
                    this.worldObj.setBlock(x, y, z, TragicBlocks.Permafrost, 1, 2);
                } else if (block == TragicBlocks.DarkStone) {
                    this.worldObj.setBlock(x, y, z, TragicBlocks.DarkCobblestone);
                } else if (block instanceof BlockDarkCobble) {
                    this.worldObj.setBlock(x, y, z, TragicBlocks.DeadDirt);
                }
            }
        }
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }

    @Override
    public void setInWeb() {}

    @Override
    public int getTotalArmorValue() {
        return this.isBurning() ? 0 : (int) ragrStats[5];
        // return this.isBurning() && this.getRagrType() == 0 ? 0 : (this.getRagrType() == 0 ? (int) ragrStats[5] :
        // (int) pyragrStats[5]);
    }

    @Override
    public boolean getCanSpawnHere() {
        if (MathHelper.floor_double(this.boundingBox.minY) <= this.worldObj.provider.getAverageGroundLevel()
            && !(this.worldObj.provider instanceof TragicWorldProvider)) return false;
        return super.getCanSpawnHere();
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

            if (player.getCurrentEquippedItem() != null) {
                if (!(player.getCurrentEquippedItem()
                    .getItem() instanceof ItemBow)) {
                    player.getCurrentEquippedItem()
                        .damageItem(rand.nextInt(4) + 1, player);
                }
            } else {
                player.attackEntityFrom(DamageSource.causeMobDamage(this), 1.0F);
            }
        }

        if (par1DamageSource.isProjectile()) par2 /= 2;

        return super.attackEntityFrom(par1DamageSource, par2);
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        if (this.worldObj.isRemote) return false;

        boolean result = super.attackEntityAsMob(par1Entity);
        if (result) {
            if (par1Entity instanceof EntityLivingBase && rand.nextInt(4) == 0) {
                switch (rand.nextInt(3)) {
                    case 0:
                        ((EntityLivingBase) par1Entity)
                            .addPotionEffect(new PotionEffect(Potion.confusion.id, rand.nextInt(200)));
                        break;
                    case 1:
                        ((EntityLivingBase) par1Entity)
                            .addPotionEffect(new PotionEffect(Potion.weakness.id, rand.nextInt(200)));
                        break;
                    case 2:
                        if (TragicConfig.allowSubmission) {
                            ((EntityLivingBase) par1Entity)
                                .addPotionEffect(new PotionEffect(TragicPotion.Submission.id, rand.nextInt(200)));
                        }
                        break;
                }
            }

            if (!this.onGround) {
                par1Entity.motionX += this.motionX;
                par1Entity.motionZ += this.motionZ;
                par1Entity.motionY += this.motionY;
            }

            if (this.getRagrType() != 0) {
                par1Entity.setFire(4 + rand.nextInt(4));
            }

        }
        return result;
    }

    @Override
    protected boolean isChangeAllowed() {
        return this.getRagrType() == 1 && TragicConfig.allowAggro;
    }

    @Override
    public String getLivingSound() {
        return null;
    }

    @Override
    public String getHurtSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.ragr.hurt" : super.getHurtSound();
    }

    @Override
    public String getDeathSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.ragr.death" : null;
    }

    @Override
    public float getSoundPitch() {
        return 0.8F;
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
    public int getDropAmount() {
        return this.getRagrType() == 0 ? 3 : 5;
    }

    @Override
    public void onDeath(DamageSource src) {
        super.onDeath(src);

        if (src.getEntity() instanceof EntityPlayerMP && TragicConfig.allowAchievements
            && this.isBurning()
            && this.getRagrType() == 0) {
            ((EntityPlayerMP) src.getEntity()).triggerAchievement(TragicAchievements.ragr);
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("ragrType")) this.setRagrType(tag.getByte("ragrType"));
        if (tag.hasKey("angerTicks")) this.setAngerTicks(tag.getInteger("angerTicks"));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setByte("ragrType", this.getRagrType());
        tag.setInteger("angerTicks", this.getAngerTicks());
    }

    @Override
    public String getVariantName() {
        return "TragicMC.Pyragr";
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ);
        // this.setRagrType(biome.temperature > 0.5F ? (byte) 1 : 0);
        return super.onSpawnWithEgg(data);
    }
}
