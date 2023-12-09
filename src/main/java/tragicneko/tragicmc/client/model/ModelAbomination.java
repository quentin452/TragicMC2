package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

import tragicneko.tragicmc.entity.mob.EntityAbomination;

public class ModelAbomination extends ModelBase {

    private ModelRenderer head;
    private ModelRenderer body;
    private ModelRenderer rightArm;
    private ModelRenderer leftArm;
    private ModelRenderer rightLeg;
    private ModelRenderer leftLeg;

    public ModelAbomination() {
        this.textureWidth = 64;
        this.textureHeight = 64;

        // Head, Ears and Nose
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-5F, -8F, -4F, 10, 8, 6);
        this.head.setRotationPoint(0F, 0F, 0F);
        ModelRenderer nose = new ModelRenderer(this, 32, 0);
        nose.addBox(-2F, -6F, -5F, 4, 2, 1);
        this.head.addChild(nose);
        ModelRenderer leftEar = new ModelRenderer(this, 48, 0);
        leftEar.addBox(5F, -6F, -2F, 1, 3, 3);
        this.head.addChild(leftEar);
        ModelRenderer rightEar = new ModelRenderer(this, 56, 0);
        rightEar.addBox(-6F, -6F, -2F, 1, 3, 3);
        this.head.addChild(rightEar);
        this.boxList.add(head);

        // Body
        this.body = new ModelRenderer(this, 16, 16);
        this.body.addBox(-6F, 0F, -2F, 12, 16, 4);
        this.body.setRotationPoint(0F, 0F, 1F);
        this.body.rotateAngleX = 0.2082002F;
        this.boxList.add(body);

        // Right Arm and Fist
        this.rightArm = new ModelRenderer(this, 48, 16);
        this.rightArm.addBox(-3F, 0F, -2F, 3, 14, 5);
        this.rightArm.setRotationPoint(-5F, 0F, 0F);
        ModelRenderer rightFist = new ModelRenderer(this, 0, 36);
        rightFist.addBox(-3F, 14F, -3F, 5, 5, 6);
        this.rightArm.addChild(rightFist);
        this.boxList.add(rightArm);

        // Left Arm and Fist
        this.leftArm = new ModelRenderer(this, 48, 16);
        this.leftArm.addBox(-1F, 0F, -2F, 3, 14, 5);
        this.leftArm.setRotationPoint(6F, 0F, 0F);
        ModelRenderer leftFist = new ModelRenderer(this, 0, 36);
        leftFist.addBox(-2F, 14F, -3F, 5, 5, 6);
        this.leftArm.addChild(leftFist);
        this.boxList.add(leftArm);

        // Right Leg
        this.rightLeg = new ModelRenderer(this, 0, 16);
        this.rightLeg.addBox(-2F, 0F, -2F, 4, 8, 4);
        this.rightLeg.setRotationPoint(-3F, 16F, 4F);
        this.boxList.add(rightLeg);

        // Left Leg
        this.leftLeg = new ModelRenderer(this, 0, 16);
        this.leftLeg.addBox(-2F, 0F, -2F, 4, 8, 4);
        this.leftLeg.setRotationPoint(3F, 16F, 4F);
        this.boxList.add(leftLeg);
    }

    @Override
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        super.render(par1Entity, par2, par3, par4, par5, par6, par7);
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
        this.head.render(par7);
        this.body.render(par7);
        this.leftArm.render(par7);
        this.rightArm.render(par7);
        this.leftLeg.render(par7);
        this.rightLeg.render(par7);
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6,
        Entity entity) {
        this.head.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.head.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.leftLeg.rotateAngleX = -1.5F * this.simplifyAngle(par1, 13.0F) * par2;
        this.rightLeg.rotateAngleX = 1.5F * this.simplifyAngle(par1, 13.0F) * par2;
    }

    @Override
    public void setLivingAnimations(EntityLivingBase entity, float par2, float par3, float par4) {
        EntityAbomination abomination = (EntityAbomination) entity;
        int i = abomination.getAttackTime();

        if (i > 0) {
            this.rightArm.rotateAngleX = -2.0F + 1.5F * this.simplifyAngle(i - par4, 10.0F);
            this.leftArm.rotateAngleX = -2.0F + 1.5F * this.simplifyAngle(i - par4, 10.0F);
        } else {
            this.rightArm.rotateAngleX = (-0.2F + 1.5F * this.simplifyAngle(par2, 13.0F)) * par3;
            this.leftArm.rotateAngleX = (-0.2F - 1.5F * this.simplifyAngle(par2, 13.0F)) * par3;
        }
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }

}
