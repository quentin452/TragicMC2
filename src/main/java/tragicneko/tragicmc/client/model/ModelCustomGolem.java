package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.entity.EntityLivingBase;

import tragicneko.tragicmc.entity.boss.EntityClaymation;

public class ModelCustomGolem extends ModelIronGolem {

    @Override
    public void setLivingAnimations(EntityLivingBase entity, float p_78086_2_, float p_78086_3_, float p_78086_4_) {
        EntityClaymation clay = (EntityClaymation) entity;
        int i = clay.getUtilityInt();

        if (i > 0) {
            this.ironGolemRightArm.rotateAngleX = -2.0F + 1.5F * this.func_78172_a(i - p_78086_4_, 10.0F);
            this.ironGolemLeftArm.rotateAngleX = -2.0F + 1.5F * this.func_78172_a(i - p_78086_4_, 10.0F);
        }
    }

    protected float func_78172_a(float p_78172_1_, float p_78172_2_) {
        return (Math.abs(p_78172_1_ % p_78172_2_ - p_78172_2_ * 0.5F) - p_78172_2_ * 0.25F) / (p_78172_2_ * 0.25F);
    }
}
