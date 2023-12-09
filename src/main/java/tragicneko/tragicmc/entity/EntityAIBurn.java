package tragicneko.tragicmc.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIBurn extends EntityAIBase {

    private final boolean brightLightBurn;
    private EntityLiving theEntity;

    public EntityAIBurn(EntityLiving entity, boolean brightLightInclusive) {
        this.brightLightBurn = brightLightInclusive;
        this.theEntity = entity;
    }

    @Override
    public boolean shouldExecute() {
        return this.theEntity.worldObj
            .getBlockLightValue((int) this.theEntity.posX, (int) this.theEntity.posY, (int) this.theEntity.posZ) >= 8
            && this.brightLightBurn
            || this.theEntity.worldObj
                .canBlockSeeTheSky((int) this.theEntity.posX, (int) this.theEntity.posY, (int) this.theEntity.posZ)
                && this.theEntity.worldObj.isDaytime();
    }

    @Override
    public boolean continueExecuting() {
        return this.theEntity.isEntityAlive() && !this.theEntity.isBurning() && super.continueExecuting();
    }

    @Override
    public void startExecuting() {
        this.theEntity.setFire(4);
    }

    @Override
    public boolean isInterruptible() {
        return false;
    }
}
