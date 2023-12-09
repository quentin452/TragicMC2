package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.hunterStats;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCombat;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityHunter extends TragicMob {

    public EntityHunter(World par1World) {
        super(par1World);
        this.setSize(0.625F, 0.725F);
        this.experienceValue = 5;
        this.getNavigator()
            .setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(
            3,
            new EntityAINearestAttackableTarget(
                this,
                EntityLivingBase.class,
                0,
                true,
                false,
                EntityOverlordCombat.selec));
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(hunterStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(hunterStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(hunterStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(hunterStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(hunterStats[4]);
    }

    @Override
    public int getTotalArmorValue() {
        return (int) hunterStats[5];
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
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (this.worldObj.isRemote) {
            for (int l = 0; l < 2; ++l) {
                this.worldObj.spawnParticle(
                    "enchantmenttable",
                    this.posX + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.5D,
                    this.posY + this.rand.nextDouble() * this.height + 0.15D,
                    this.posZ + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.5D,
                    (this.rand.nextDouble() - 0.6D) * 0.1D,
                    this.rand.nextDouble() * 0.1D,
                    (this.rand.nextDouble() - 0.6D) * 0.1D);
            }
            return;
        }

        if (this.getAttackTarget() != null) {
            double d0 = this.getAttackTarget().posX - this.posX;
            double d1 = this.getAttackTarget().posZ - this.posZ;
            double d4 = this.getAttackTarget().posY - this.posY
                + (this.getAttackTarget().height * 0.5 + (rand.nextDouble() * 0.25));

            float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d4 * d4);
            double d2 = d0 / f2 * 1.33D * 0.11D + this.motionX * 0.84D;
            this.motionX = Math.min(Math.abs(d2), 0.76D) == Math.abs(d2) ? d2 : 0.76D * (d2 < 0 ? -1 : 1);
            double d3 = d1 / f2 * 1.33D * 0.11D + this.motionZ * 0.84D;
            this.motionZ = Math.min(Math.abs(d3), 0.76D) == Math.abs(d3) ? d3 : 0.76D * (d3 < 0 ? -1 : 1);
            this.motionY = Math.min(Math.abs(d4), 1.16D) == Math.abs(d4) ? d4 : 1.16D * (d4 < 0 ? -1 : 1);
            if (this.isCollided) this.motionY += rand.nextDouble() - rand.nextDouble();
            this.moveFlying((float) this.motionX, (float) this.motionY, (float) this.motionZ);
        } else {
            this.motionY += (rand.nextDouble() - rand.nextDouble()) * 0.2 - 0.1;
            if (rand.nextInt(6) == 0 && WorldHelper.getDistanceToGround(this) < 10)
                this.motionY += rand.nextDouble() + 0.8;

            this.motionX += (rand.nextDouble() - rand.nextDouble()) * 0.2;
            this.motionZ += (rand.nextDouble() - rand.nextDouble()) * 0.2;
            this.motionX *= 0.542D;
            this.motionZ *= 0.542D;
            this.motionY *= 0.256D;
            this.moveFlying((float) this.motionX, (float) this.motionY, (float) this.motionZ);
        }
    }

    @Override
    public void fall(float par1) {}

    @Override
    public void updateFallState(double par1, boolean par2) {}

    @Override
    public String getLivingSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.hunter.noise" : null;
    }

    @Override
    public String getHurtSound() {
        return super.getHurtSound();
    }

    @Override
    public String getDeathSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.hunter.noise" : null;
    }

    @Override
    public float getSoundPitch() {
        return this.getAttackTarget() != null ? 1.8F : 1.0F;
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
        return 2;
    }
}
