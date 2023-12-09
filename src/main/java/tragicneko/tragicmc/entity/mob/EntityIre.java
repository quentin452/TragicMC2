package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.ireStats;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.entity.EntityAIWatchTarget;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCore;
import tragicneko.tragicmc.entity.boss.EntityApis;
import tragicneko.tragicmc.entity.projectile.EntityIreEnergy;

public class EntityIre extends TragicMob {

    public static byte ireTick; // ticks per world tick, each Ire will either heal or attack at this time and take that
                                // opportunity to update their target
    public static short ireNetSize; // updated by each Ire currently being updated in the world, whatever Ire has the
                                    // most Ire near them will be the value set per Ire tick

    public static final IEntitySelector selec = new IEntitySelector() {

        @Override
        public boolean isEntityApplicable(Entity entity) {
            return !(entity instanceof EntityIre) && !(entity instanceof EntityArchangel)
                && !(entity instanceof EntityApis);
        }
    };

    public EntityIre(World par1World) {
        super(par1World);
        this.setSize(1.355F, 1.825F);
        this.isImmuneToFire = true;
        this.experienceValue = 5;
        this.tasks.addTask(4, new EntityAIWatchTarget(this, 64.0F));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
        this.targetTasks
            .addTask(3, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, true, false, selec));
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(ireStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(ireStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(ireStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(ireStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(ireStats[4]);
    }

    @Override
    public int getTotalArmorValue() {
        return (int) ireStats[5];
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, Integer.valueOf(0)); // kill ticks
        this.dataWatcher.addObject(17, Integer.valueOf(0)); // target id
    }

    private void setTargetId(int i) {
        this.dataWatcher.updateObject(17, i);
    }

    public int getTargetId() {
        return this.dataWatcher.getWatchableObjectInt(17);
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
        return false;
    }

    @Override
    protected boolean isChangeAllowed() {
        return false;
    }

    @Override
    public void onLivingUpdate() {
        this.motionX = this.motionY = this.motionZ = 0;

        super.onLivingUpdate();

        if (this.worldObj.isRemote) {
            Entity entity = this.worldObj.getEntityByID(this.getTargetId());
            if (entity != null) {
                double d0 = entity.posX - this.posX;
                double d1 = entity.posY - this.posY;
                double d2 = entity.posZ - this.posZ;

                for (int l = 0; l < 4; l++) {
                    double d3 = 0.23D * l + (rand.nextDouble() * 0.25D);
                    this.worldObj.spawnParticle(
                        "crit",
                        this.posX + d0 * d3,
                        this.posY + d1 * d3 + 0.75D,
                        this.posZ + d2 * d3,
                        0.0,
                        0.0,
                        0.0);
                }
            }

            List<EntityIre> list = this.worldObj
                .getEntitiesWithinAABB(EntityIre.class, this.boundingBox.expand(16.0, 16.0, 16.0));
            for (EntityIre ire : list) {
                if (ire != this && ire.canEntityBeSeen(this)) {
                    double d0 = ire.posX - this.posX;
                    double d1 = ire.posY - this.posY;
                    double d2 = ire.posZ - this.posZ;

                    for (int l = 0; l < 4; l++) {
                        double d3 = 0.23D * l + (rand.nextDouble() * 0.25D);
                        this.worldObj.spawnParticle(
                            "crit",
                            this.posX + d0 * d3,
                            this.posY + d1 * d3 + 0.75D,
                            this.posZ + d2 * d3,
                            0.0,
                            0.0,
                            0.0);
                    }
                }
            }
            return;
        }

        if (this.getAttackTarget() != null) {
            this.setTargetId(
                this.getAttackTarget()
                    .getEntityId());

            if (ireTick == 20) {
                if (this.canEntityBeSeen(this.getAttackTarget())) {
                    if (TragicConfig.ireEnergyBurst) {
                        double d0 = this.getAttackTarget().posX - this.posX;
                        double d1 = this.getAttackTarget().boundingBox.minY + this.getAttackTarget().height / 3.0F
                            - (this.posY + this.height / 2.0F);
                        double d2 = this.getAttackTarget().posZ - this.posZ;

                        EntityIreEnergy energy = new EntityIreEnergy(this.worldObj, this, d0, d1, d2);
                        energy.posX = this.posX + d0 * 0.115D;
                        energy.posY = this.posY + (this.height * 2 / 3);
                        energy.posZ = this.posZ + d2 * 0.115D;
                        this.worldObj.spawnEntityInWorld(energy);
                    }
                } else {
                    if (this.getHealth() < this.getMaxHealth()) this.heal(ireNetSize);
                }

                if (TragicConfig.allowMobSounds)
                    this.worldObj.playSoundAtEntity(this, "tragicmc:mob.ire.active", 0.8F, 0.5F + rand.nextFloat());
            }

            if (this.getDistanceToEntity(this.getAttackTarget()) >= 16.0D || this.getAttackTarget().isDead
                || this.getAttackTarget()
                    .getHealth() <= 0F
                || this.worldObj.getEntityByID(this.getTargetId()) == null) {
                this.setAttackTarget(null);
            }
        } else {
            this.setTargetId(0);
        }

        List<EntityIre> list = this.worldObj
            .getEntitiesWithinAABB(EntityIre.class, this.boundingBox.expand(16.0, 16.0, 16.0));
        short count = 0;
        for (EntityIre ire : list) {
            if (ire != this && ire.canEntityBeSeen(this) && count < Short.MAX_VALUE) count++;
        }

        if (count > ireNetSize) ireNetSize = count;

        if (TragicConfig.allowMobIllumination) {
            int x = (int) (this.posX + rand.nextInt(2) - rand.nextInt(2));
            int y = (int) (this.posY + rand.nextInt(2) - rand.nextInt(2)) + ((int) this.height * 2 / 3);
            int z = (int) (this.posZ + rand.nextInt(2) - rand.nextInt(2));
            if (EntityOverlordCore.replaceableBlocks.contains(worldObj.getBlock(x, y, z)))
                this.worldObj.setBlock(x, y, z, TragicBlocks.Luminescence);
        }
    }

    @Override
    public void fall(float par1) {}

    @Override
    public void updateFallState(double par1, boolean par2) {}

    @Override
    public boolean canAttackClass(Class oclass) {
        return super.canAttackClass(oclass) && oclass != this.getClass()
            && oclass != EntityArchangel.class
            && oclass != EntityApis.class;
    }

    @Override
    public String getLivingSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.ire.tone" : null;
    }

    @Override
    public String getHurtSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.ire.hit" : super.getHurtSound();
    }

    @Override
    public String getDeathSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.ire.death" : null;
    }

    @Override
    public float getSoundPitch() {
        return rand.nextFloat() + 0.5F;
    }

    @Override
    public float getSoundVolume() {
        return 0.4F;
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
            EntityPlayerMP mp = (EntityPlayerMP) src.getEntity();
            if (src.getSourceOfDamage() instanceof EntityIreEnergy && mp.getCurrentEquippedItem() != null) {
                if (mp.getCurrentEquippedItem()
                    .getItem() == TragicItems.IreNetParticleCannon) {
                    mp.triggerAchievement(TragicAchievements.ire);
                }
            }
        }
    }
}
