package tragicneko.tragicmc.entity.miniboss;

import static tragicneko.tragicmc.TragicConfig.aggroStats;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import tragicneko.tragicmc.entity.mob.EntityRagr;

public class EntityAggro extends EntityRagr implements TragicMiniBoss {

    public EntityAggro(World par1World) {
        super(par1World);
        this.setSize(1.335F * 1.585F, 2.675F * 1.585F);
        this.stepHeight = 1.5F;
        this.experienceValue = 25;
        this.isImmuneToFire = true;
        this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(18, Integer.valueOf(0));
    }

    public int getTantrumTicks() {
        return this.dataWatcher.getWatchableObjectInt(18);
    }

    private void setTantrumTicks(int i) {
        this.dataWatcher.updateObject(18, i);
    }

    @Override
    public boolean canCorrupt() {
        return true;
    }

    @Override
    public boolean isMobVariant() {
        return false;
    }

    @Override
    public boolean canRenderOnFire() {
        return false;
    }

    @Override
    protected void setRagrType(byte b) {
        this.dataWatcher.updateObject(17, (byte) 1);
        this.setSize(1.335F * 1.585F, 2.675F * 1.585F);
    }

    @Override
    public int getDropAmount() {
        return 5;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(aggroStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(aggroStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(aggroStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(aggroStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(aggroStats[4]);
    }

    @Override
    public int getTotalArmorValue() {
        return (int) aggroStats[5];
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (!this.worldObj.isRemote) {
            if (this.getTantrumTicks() > 0) this.setTantrumTicks(this.getTantrumTicks() - 1);

            if (this.getAttackTarget() != null) {
                if (this.getAngerTicks() > 100 && this.getTantrumTicks() <= 0
                    && this.ticksExisted % 69 == 0
                    && rand.nextInt(4) == 0) this.setTantrumTicks(300);
                if (this.getTantrumTicks() > 0 && this.getDistanceToEntity(this.getAttackTarget()) <= 4.0F
                    && this.onGround
                    && rand.nextInt(8) == 0)
                    this.worldObj.createExplosion(
                        this,
                        this.posX,
                        this.posY,
                        this.posZ,
                        3.5F + 2.5F * rand.nextFloat(),
                        this.getMobGriefing());
            } else {
                if (this.getTantrumTicks() > 0) this.setTantrumTicks(this.getTantrumTicks() - 1);
            }
        }
    }

    @Override
    public void jump() {
        if (this.getTantrumTicks() <= 0) {
            super.jump();

            if (this.getAttackTarget() == null) return;

            EntityLivingBase entity = this.getAttackTarget();
            double d0 = entity.posX - this.posX;
            double d1 = entity.boundingBox.minY + entity.height / 2.0F - (this.posY + this.height / 2.0F);
            double d2 = entity.posZ - this.posZ;

            float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(entity)) * 0.5F;

            for (byte i = 0; i < 5; ++i) {
                EntitySmallFireball entitysmallfireball = new EntitySmallFireball(
                    this.worldObj,
                    this,
                    d0 + this.rand.nextGaussian() * f1,
                    d1,
                    d2 + this.rand.nextGaussian() * f1);
                entitysmallfireball.posY = this.posY + 0.5D;
                this.worldObj.spawnEntityInWorld(entitysmallfireball);
            }
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        boolean flag = super.attackEntityAsMob(par1Entity);

        if (flag) {
            par1Entity.setFire(8 + rand.nextInt(4));

            if (this.getTantrumTicks() > 0) {
                par1Entity.motionX += this.motionX * 1.25;
                par1Entity.motionZ += this.motionZ * 1.25;
                par1Entity.motionY += this.motionY * 1.25;
            }
        }

        return flag;
    }
}
