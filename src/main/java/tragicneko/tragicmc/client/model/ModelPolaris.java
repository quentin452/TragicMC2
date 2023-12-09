package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.boss.EntityPolaris;

public class ModelPolaris extends ModelBase {

    private ModelRenderer head;
    private ModelRenderer body;
    private ModelRenderer rightLeg;
    private ModelRenderer leftLeg;
    private ModelRenderer rightArm;
    private ModelRenderer leftArm;

    public ModelPolaris() {
        textureWidth = 64;
        textureHeight = 64;

        head = new ModelRenderer(this, 16, 40);
        head.addBox(-3F, -6F, -3F, 6, 5, 6);
        head.setRotationPoint(0F, -2F, 0F);

        ModelRenderer headpiece = new ModelRenderer(this, 28, 8);
        headpiece.addBox(-1F, -7F, -4F, 2, 7, 6);
        head.addChild(headpiece);

        ModelRenderer eyepiece = new ModelRenderer(this, 28, 0);
        eyepiece.addBox(-4F, -5F, -4F, 8, 2, 5);
        head.addChild(eyepiece);

        ModelRenderer eye = new ModelRenderer(this, 56, 0);
        eye.addBox(-1F, -5F, -5F, 2, 2, 1);
        head.addChild(eye);

        body = new ModelRenderer(this, 14, 24);
        body.addBox(-4F, 0F, -2F, 8, 6, 5);
        body.setRotationPoint(0F, -3F, 0F);

        ModelRenderer abdomen = new ModelRenderer(this, 0, 10);
        abdomen.addBox(-2F, 6F, -1F, 4, 3, 3);
        body.addChild(abdomen);

        ModelRenderer pelvis = new ModelRenderer(this, 0, 0);
        pelvis.addBox(-3F, 9F, -2F, 6, 3, 5);
        body.addChild(pelvis);

        rightArm = new ModelRenderer(this, 0, 42);
        rightArm.addBox(-1F, 1F, -1F, 2, 8, 2);
        rightArm.setRotationPoint(-5F, 0F, 0F);
        rightArm.rotateAngleX = -1.710216F;

        ModelRenderer rightShoulder = new ModelRenderer(this, 46, 16);
        rightShoulder.addBox(-3F, -2F, -2F, 4, 3, 4);
        rightArm.addChild(rightShoulder);

        leftArm = new ModelRenderer(this, 0, 42);
        leftArm.addBox(-1F, 1F, -1F, 2, 8, 2);
        leftArm.setRotationPoint(5F, 0F, 0F);
        leftArm.rotateAngleX = -1.710216F;

        ModelRenderer leftShoulder = new ModelRenderer(this, 46, 16);
        leftShoulder.addBox(-1F, -2F, -2F, 4, 3, 4);
        leftArm.addChild(leftShoulder);

        rightLeg = new ModelRenderer(this, 0, 18);
        rightLeg.addBox(-2F, 0F, -2F, 3, 14, 3);
        rightLeg.setRotationPoint(-2F, 9F, 1F);

        leftLeg = new ModelRenderer(this, 0, 18);
        leftLeg.addBox(-1F, 0F, -2F, 3, 14, 3);
        leftLeg.setRotationPoint(2F, 9F, 1F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        head.render(f5);
        body.render(f5);
        rightLeg.render(f5);
        leftLeg.render(f5);
        rightArm.render(f5);
        leftArm.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        if (!(entity instanceof EntityPolaris)) return;

        EntityPolaris polar = (EntityPolaris) entity;

        head.rotateAngleY = f3 / (180F / (float) Math.PI);
        head.rotateAngleX = f4 / (180F / (float) Math.PI);

        leftLeg.rotateAngleX = -0.5F * this.simplifyAngle(f, 3.0F) * f1;
        rightLeg.rotateAngleX = 0.5F * this.simplifyAngle(f, 3.0F) * f1;
        leftArm.rotateAngleX = -1.710216F + 0.15F * this.simplifyAngle(f, 3.0F) * f1;
        rightArm.rotateAngleX = -1.710216F + -0.15F * this.simplifyAngle(f, 3.0F) * f1;
        leftArm.rotateAngleY = 0.15F * this.simplifyAngle(f, 3.0F) * f1;
        rightArm.rotateAngleY = -0.15F * this.simplifyAngle(f, 3.0F) * f1;

        if (polar.getAttackTime() > 0) {
            rightArm.rotateAngleX = -1.710216F + this.simplifyAngle(polar.getAttackTime(), 5.0F) * 0.85F;
            leftArm.rotateAngleX = -1.710216F + this.simplifyAngle(polar.getAttackTime(), 5.0F) * 0.85F;
            rightArm.rotateAngleZ = 0.45F * this.simplifyAngle(polar.getAttackTime(), 5.0F);
            leftArm.rotateAngleZ = -0.45F * this.simplifyAngle(polar.getAttackTime(), 5.0F);

            head.rotateAngleX = 0.25F * this.simplifyAngle(polar.getAttackTime(), 5.0F);
        }
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }

}
