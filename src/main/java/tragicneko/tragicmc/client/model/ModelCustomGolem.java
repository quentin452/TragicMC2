package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.entity.EntityLivingBase;
import tragicneko.tragicmc.entity.boss.EntityClaymation;

public class ModelCustomGolem extends ModelIronGolem {

    @Override
    public void setLivingAnimations(EntityLivingBase entity, float p_78086_2_, float p_78086_3_, float p_78086_4_)
    {
        EntityClaymation clay = (EntityClaymation) entity;

        this.ironGolemRightArm.rotateAngleX = -2.0F + 1.5F * this.func_78172_a(clay.getUtilityInt(), 10.0F);
        this.ironGolemLeftArm.rotateAngleX = -2.0F + 1.5F * this.func_78172_a(clay.getUtilityInt(), 10.0F);
    }

    protected float func_78172_a(float par1, float par2)
    {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }
}
