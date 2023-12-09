package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.mob.EntityStin;

public class ModelStinBaby extends ModelBase {

    private ModelRenderer body;
    private ModelRenderer legFR;
    private ModelRenderer legFL;
    private ModelRenderer legBL;
    private ModelRenderer legBR;

    public ModelStinBaby() {
        textureWidth = 128;
        textureHeight = 128;

        // Body
        body = new ModelRenderer(this, 22, 0);
        body.addBox(-2F, 1F, -2F, 4, 8, 13);
        body.setRotationPoint(0F, 14F, -2F);
        ModelRenderer bodyThickness = new ModelRenderer(this, 0, 30);
        bodyThickness.addBox(-4F, 2F, 0F, 8, 6, 13);
        body.addChild(bodyThickness);

        // Front Right Leg
        legFR = new ModelRenderer(this, 64, 0);
        legFR.addBox(-5F, 2F, 2F, 2, 7, 3);
        legFR.rotateAngleX = -0.2230717F;
        legFR.rotateAngleZ = 0.0371786F;
        body.addChild(legFR);

        // Front Left Leg
        legFL = new ModelRenderer(this, 64, 20);
        legFL.addBox(3.5F, 2F, 2F, 2, 7, 3);
        legFL.rotateAngleX = -0.2230717F;
        legFL.rotateAngleZ = -0.0371786F;
        body.addChild(legFL);

        // Back Left Leg
        legBL = new ModelRenderer(this, 64, 20);
        legBL.addBox(3.5F, 4F, 5F, 2, 7, 3);
        legBL.rotateAngleX = 0.1487144F;
        legBL.rotateAngleZ = -0.1487144F;
        body.addChild(legBL);

        // Back Right Leg
        legBR = new ModelRenderer(this, 64, 0);
        legBR.addBox(-5F, 4F, 5F, 2, 7, 3);
        legBR.rotateAngleX = 0.1487144F;
        legBR.rotateAngleZ = 0.1487144F;
        body.addChild(legBR);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        body.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        EntityStin stin = (EntityStin) entity;

        legFR.rotateAngleX = -0.2230717F + this.simplifyAngle(f, 3.0F) * 0.093F * f1;
        legFL.rotateAngleX = -0.2230717F + this.simplifyAngle(f + 1.5F, 3.0F) * 0.093F * f1;
        legBL.rotateAngleX = 0.1487144F + this.simplifyAngle(f + 3.0F, 3.0F) * 0.093F * f1;
        legBR.rotateAngleX = 0.1487144F + this.simplifyAngle(f + 4.5F, 3.0F) * 0.093F * f1;

        if (stin.getClimbDirection() == 2) {
            body.rotateAngleZ = 1.0F;
            body.rotateAngleX = 0.0F;
        } else if (stin.getClimbDirection() == 3) {
            body.rotateAngleZ = -1.0F;
            body.rotateAngleX = 0.0F;
        } else if (stin.getClimbDirection() == 4) {
            body.rotateAngleX = 1.0F;
            body.rotateAngleZ = 0.0F;
        } else if (stin.getClimbDirection() == 5) {
            body.rotateAngleX = -1.0F;
            body.rotateAngleZ = 0.0F;
        } else {
            body.rotateAngleZ = 0.0F;
            body.rotateAngleX = 0.0F;
        }
    }

    protected float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }

}
