package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.harvesterStats;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;

public class EntityHarvester extends TragicMob {

    private int directionTicks;

    public EntityHarvester(World par1World) {
        super(par1World);
        this.setSize(0.925F, 1.525F);
        this.stepHeight = 1.0F;
        this.experienceValue = 5;
        this.getNavigator()
            .setAvoidsWater(true);
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(harvesterStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(harvesterStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(harvesterStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(harvesterStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(harvesterStats[4]);
    }

    @Override
    public int getTotalArmorValue() {
        return (int) harvesterStats[5];
    }

    @Override
    protected boolean isChangeAllowed() {
        return false;
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return TragicEntities.Synapse;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, Integer.valueOf(0)); // should release
        this.dataWatcher.addObject(17, Integer.valueOf(0)); // direction
    }

    private void setReleaseTicks(int i) {
        this.dataWatcher.updateObject(16, i);
    }

    public int getReleaseTicks() {
        return this.dataWatcher.getWatchableObjectInt(16);
    }

    private void setDirection(int i) {
        this.dataWatcher.updateObject(17, i);
    }

    public int getDirection() {
        return this.dataWatcher.getWatchableObjectInt(17);
    }

    @Override
    public void onLivingUpdate() {
        this.motionY = 0D;
        this.rotationPitch = this.rotationYaw = 0;
        super.onLivingUpdate();

        if (this.worldObj.isRemote) {
            for (int i = 0; i < 6; i++) {
                this.worldObj.spawnParticle(
                    "mobSpellAmbient",
                    this.posX + (this.rand.nextDouble() - 0.5D) * this.width * 1.3D,
                    this.posY + (rand.nextDouble() * 0.115D) + 0.545D,
                    this.posZ + (this.rand.nextDouble() - 0.5D) * this.width * 1.3D,
                    0.0,
                    rand.nextDouble() * 0.5556,
                    0.0);
            }

            if (this.getReleaseTicks() == 10) {
                for (int i = 0; i < 24; i++) {
                    this.worldObj.spawnParticle(
                        "smoke",
                        this.posX + (this.rand.nextDouble() - 0.5D) * this.width * 1.3D,
                        this.posY + (rand.nextDouble() * 0.115D) + 0.545D,
                        this.posZ + (this.rand.nextDouble() - 0.5D) * this.width * 1.3D,
                        (rand.nextDouble() - rand.nextDouble()) * 0.125,
                        rand.nextDouble() * 0.2556,
                        (rand.nextDouble() - rand.nextDouble()) * 0.125);
                }
            }
            return;
        }

        if (this.isCollidedHorizontally || this.motionX == 0D && this.motionZ == 0D || this.directionTicks == 0) {
            this.directionTicks = 200 + rand.nextInt(80);
            this.setDirection(rand.nextInt(4));
        }

        if (this.directionTicks > 0) {
            this.directionTicks--;
            double d0 = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
                .getAttributeValue();

            switch (this.getDirection()) {
                case 0:
                    this.motionX = -d0;
                    this.motionZ = 0;
                    break;
                case 1:
                    this.motionX = d0;
                    this.motionZ = 0;
                    break;
                case 2:
                    this.motionX = 0;
                    this.motionZ = d0;
                    break;
                case 3:
                    this.motionX = 0;
                    this.motionZ = -d0;
                    break;
            }
        }

        if (this.ticksExisted % 20 == 0 && TragicConfig.harvesterBuffDebuffEntities) {
            double d0 = this.getEntityAttribute(SharedMonsterAttributes.followRange)
                .getAttributeValue();
            List<Entity> list = this.worldObj
                .getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(d0, d0, d0));
            boolean flag = false;

            for (Entity e : list) {
                if (this.canEntityBeSeen(e) && e instanceof EntityLivingBase) {
                    if (((EntityLivingBase) e).getCreatureAttribute() == TragicEntities.Synapse) {
                        ((EntityLivingBase) e).addPotionEffect(new PotionEffect(Potion.damageBoost.id, 120, 0));
                    } else if (!(e instanceof EntityPlayer)) {
                        ((EntityLivingBase) e).addPotionEffect(new PotionEffect(Potion.weakness.id, 120, 0));
                        flag = true;
                    } else {
                        EntityPlayer player = (EntityPlayer) e;
                        if (!player.capabilities.isCreativeMode) {
                            ((EntityLivingBase) e).addPotionEffect(new PotionEffect(Potion.weakness.id, 120, 0));
                            flag = true;
                        }
                    }
                }
            }

            if (flag && rand.nextInt(32) == 0 && this.getReleaseTicks() == 0) this.setReleaseTicks(20);
            if (TragicConfig.allowMobSounds)
                this.worldObj.playSoundAtEntity(this, "tragicmc:mob.harvester.hover", 0.6F, 1.0F);
        }

        if (this.getReleaseTicks() > 0) this.setReleaseTicks(this.getReleaseTicks() - 1);
        if (this.getReleaseTicks() == 10 && TragicConfig.harvesterNanoSwarms) {
            EntityNanoSwarm swarm = new EntityNanoSwarm(this.worldObj);
            swarm.setPosition(this.posX, this.posY, this.posZ);
            this.worldObj.spawnEntityInWorld(swarm);
            this.worldObj.playSoundAtEntity(this, "random.chestopen", 1.0F, 0.4F);
        }
    }

    @Override
    public void fall(float par1) {}

    @Override
    public void updateFallState(double par1, boolean par2) {}

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("directionTicks")) this.directionTicks = 0;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setInteger("directionTicks", this.directionTicks);
    }

    @Override
    public String getLivingSound() {
        return null;
    }

    @Override
    public String getHurtSound() {
        return super.getHurtSound();
    }

    @Override
    public String getDeathSound() {
        return null; // "tragicmc:mob.nanoswarm.glitch";
    }

    @Override
    public float getSoundPitch() {
        return 1.0F;
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

    @Override
    public void onDeath(DamageSource src) {
        super.onDeath(src);

        if (src.getEntity() instanceof EntityPlayerMP && TragicConfig.allowAchievements) {
            if (!((EntityLivingBase) src.getEntity()).isPotionActive(Potion.weakness)) {
                ((EntityPlayerMP) src.getEntity()).triggerAchievement(TragicAchievements.harvester);
            }
        }
    }
}
