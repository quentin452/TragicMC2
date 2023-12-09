package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelInkling extends ModelBase {

    private ModelRenderer body;
    private ModelRenderer rightArm;
    private ModelRenderer leftArm;
    private ModelRenderer rightLeg;
    private ModelRenderer leftLeg;

    public ModelInkling() {
        textureWidth = 64;
        textureHeight = 32;

        // Body
        body = new ModelRenderer(this, 0, 0);
        body.addBox(-3F, 0F, -1F, 6, 10, 2);
        body.setRotationPoint(0F, 2F, 0F);

        // Right Arm
        rightArm = new ModelRenderer(this, 16, 16);
        rightArm.addBox(-2F, 0F, -1F, 2, 10, 2);
        rightArm.setRotationPoint(-3F, 2F, 0F);
        rightArm.rotateAngleZ = 0.3346075F;

        // Left Arm
        leftArm = new ModelRenderer(this, 16, 16);
        leftArm.addBox(0F, 0F, -1F, 2, 10, 2);
        leftArm.setRotationPoint(3F, 2F, 0F);
        leftArm.rotateAngleZ = -0.3346075F;

        // Right Leg
        rightLeg = new ModelRenderer(this, 0, 16);
        rightLeg.addBox(-1F, 0F, -1F, 2, 12, 2);
        rightLeg.setRotationPoint(-2F, 12F, 0F);

        // Left Leg
        leftLeg = new ModelRenderer(this, 0, 16);
        leftLeg.addBox(-1F, 0F, -1F, 2, 12, 2);
        leftLeg.setRotationPoint(2F, 12F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        body.render(f5);
        if (entity.getEntityId() % 3 != 0) rightArm.render(f5);
        if (entity.getEntityId() % 7 != 0) leftArm.render(f5);
        rightLeg.render(f5);
        leftLeg.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        this.leftLeg.rotateAngleX = -1.115F * this.simplifyAngle(f, 8.0F) * f1;
        this.rightLeg.rotateAngleX = 1.115F * this.simplifyAngle(f, 8.0F) * f1;
        this.leftArm.rotateAngleX = 0.55F * this.simplifyAngle(f, 8.0F) * f1;
        this.rightArm.rotateAngleX = -0.55F * this.simplifyAngle(f, 8.0F) * f1;
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }
}
