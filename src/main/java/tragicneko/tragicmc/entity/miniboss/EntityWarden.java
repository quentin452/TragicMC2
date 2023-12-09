package tragicneko.tragicmc.entity.miniboss;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import tragicneko.tragicmc.entity.mob.EntityLockbot;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityWarden extends EntityLockbot implements TragicMiniBoss {

    public EntityWarden(World par1World) {
        super(par1World);
        this.setSize(1.425F, 1.825F);
        this.stepHeight = 2.0F;
        this.experienceValue = 15;
        this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0D, 64.0F));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(77.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(0.388);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(7.0);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(64.0);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(100.0);
    }

    @Override
    public int getTotalArmorValue() {
        return 16;
    }

    @Override
    public float getLockdownDistance() {
        return 12.0F;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (!this.worldObj.isRemote && this.getAttackTarget() != null
            && !this.canEntityBeSeen(this.getAttackTarget())
            && this.ticksExisted % 20 == 0
            && this.getDistanceToEntity(this.getAttackTarget()) <= 32.0) {
            double d0 = this.getAttackTarget().posX - this.posX;
            double d1 = this.getAttackTarget().posY - this.posY;
            double d2 = this.getAttackTarget().posZ - this.posZ;

            Vec3 vec31 = Vec3.createVectorHelper(this.posX, this.posY + this.getEyeHeight(), this.posZ);
            Vec3 vec32 = Vec3.createVectorHelper(
                this.getAttackTarget().posX,
                this.getAttackTarget().posY,
                this.getAttackTarget().posZ);
            MovingObjectPosition mop = this.worldObj.rayTraceBlocks(vec31, vec32);

            if (mop != null && mop.typeOfHit == MovingObjectType.BLOCK) {
                EntityWither wither = new EntityWither(this.worldObj);
                ArrayList<int[]> list = WorldHelper.getBlocksInSphericalRange(
                    this.worldObj,
                    1.0,
                    mop.blockX,
                    mop.blockY + this.getEyeHeight(),
                    mop.blockZ);

                for (int[] coords : list) {
                    Block block = this.worldObj.getBlock(coords[0], coords[1], coords[2]);
                    if (!block.isAir(this.worldObj, coords[0], coords[1], coords[2])
                        && block.canEntityDestroy(this.worldObj, coords[0], coords[1], coords[2], wither)) {
                        this.worldObj.func_147480_a(coords[0], coords[1], coords[2], true);
                    }
                }
            }
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        if (this.worldObj.isRemote) return false;

        boolean result = super.attackEntityAsMob(par1Entity);

        if (result) {
            par1Entity.motionY += 0.34D;
            par1Entity.motionX *= 1.11D;
            par1Entity.motionZ *= 1.11D;
        }

        return result;
    }
}
