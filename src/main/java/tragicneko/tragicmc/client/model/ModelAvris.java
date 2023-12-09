package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.mob.EntityAvris;

/**
 * ModelAvris - TragicNeko
 * Created using Tabula 4.1.1
 */
public class ModelAvris extends ModelBiped {

    public ModelRenderer lowerJaw;

    public ModelAvris() {
        this.textureWidth = 96;
        this.textureHeight = 64;

        // Head
        this.bipedHead = new ModelRenderer(this, 0, 4);
        this.bipedHead.setRotationPoint(0.0F, -12.0F, -2.0F);
        this.bipedHead.addBox(-4.0F, -6.0F, -4.0F, 8, 4, 7, 0.0F);
        this.lowerJaw = new ModelRenderer(this, 0, 16);
        this.lowerJaw.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lowerJaw.addBox(-2.0F, -2.0F, -5.0F, 4, 2, 5, 0.0F);
        this.bipedHead.addChild(this.lowerJaw);
        ModelRenderer rightHorn = new ModelRenderer(this, 0, 0);
        rightHorn.setRotationPoint(0.0F, 0.0F, 0.0F);
        rightHorn.addBox(-5.0F, -7.0F, 0.0F, 1, 4, 1, 0.0F);
        this.bipedHead.addChild(rightHorn);
        ModelRenderer leftHorn = new ModelRenderer(this, 0, 0);
        leftHorn.setRotationPoint(0.0F, 0.0F, 0.0F);
        leftHorn.addBox(4.0F, -7.0F, 0.0F, 1, 4, 1, 0.0F);
        this.bipedHead.addChild(leftHorn);

        // Body and Bag
        this.bipedBody = new ModelRenderer(this, 36, 0);
        this.bipedBody.setRotationPoint(0.0F, -14.0F, 0.0F);
        this.bipedBody.addBox(-8.0F, 0.0F, -2.0F, 16, 10, 6, 0.0F);
        ModelRenderer hips = new ModelRenderer(this, 54, 20);
        hips.setRotationPoint(0.0F, 0.0F, 0.0F);
        hips.addBox(-4.0F, 13.0F, -2.0F, 8, 3, 5, 0.0F);
        this.bipedBody.addChild(hips);
        ModelRenderer abdomen = new ModelRenderer(this, 36, 18);
        abdomen.setRotationPoint(0.0F, 0.0F, 0.0F);
        abdomen.addBox(-3.0F, 10.0F, -1.0F, 6, 3, 3, 0.0F);
        this.bipedBody.addChild(abdomen);
        ModelRenderer bagTop = new ModelRenderer(this, 0, 26);
        bagTop.setRotationPoint(0.0F, 0.0F, 0.0F);
        bagTop.addBox(-4.0F, -3.0F, 2.0F, 8, 3, 6, 0.0F);
        this.bipedBody.addChild(bagTop);
        ModelRenderer bag = new ModelRenderer(this, 0, 36);
        bag.setRotationPoint(0.0F, 0.0F, 0.0F);
        bag.addBox(-11.0F, -2.0F, 4.0F, 22, 16, 12, 0.0F);
        this.bipedBody.addChild(bag);

        // Right Arm
        this.bipedRightArm = new ModelRenderer(this, 60, 32);
        this.bipedRightArm.setRotationPoint(-9.0F, -11.0F, 0.0F);
        this.bipedRightArm.addBox(-2.0F, -2.0F, -1.5F, 3, 4, 3, 0.0F);
        ModelRenderer rightArm = new ModelRenderer(this, 76, 32);
        rightArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        rightArm.addBox(-1.0F, 2.0F, -0.5F, 1, 9, 1, 0.0F);
        this.bipedRightArm.addChild(rightArm);
        ModelRenderer rightForearm = new ModelRenderer(this, 82, 32);
        rightForearm.setRotationPoint(0.0F, 0.0F, 0.0F);
        rightForearm.addBox(-2.0F, 11.0F, -1.5F, 3, 11, 3, 0.0F);
        this.bipedRightArm.addChild(rightForearm);
        ModelRenderer rightClaw = new ModelRenderer(this, 0, 0);
        rightClaw.setRotationPoint(0.0F, 0.0F, 0.0F);
        rightClaw.addBox(-0.5F, 22.0F, 0.5F, 1, 2, 1, 0.0F);
        rightForearm.addChild(rightClaw);
        ModelRenderer rightClaw2 = new ModelRenderer(this, 0, 0);
        rightClaw2.setRotationPoint(0.0F, 0.0F, 0.0F);
        rightClaw2.addBox(-2.0F, 22.0F, -1.0F, 1, 3, 1, 0.0F);
        rightForearm.addChild(rightClaw2);
        ModelRenderer rightClaw3 = new ModelRenderer(this, 0, 0);
        rightClaw3.setRotationPoint(0.0F, 0.0F, 0.0F);
        rightClaw3.addBox(0.0F, 22.0F, -1.5F, 1, 4, 1, 0.0F);
        rightForearm.addChild(rightClaw3);

        // Left Arm
        this.bipedLeftArm = new ModelRenderer(this, 60, 32);
        this.bipedLeftArm.setRotationPoint(9.0F, -11.0F, 0.0F);
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -1.5F, 3, 4, 3, 0.0F);
        ModelRenderer leftArm = new ModelRenderer(this, 76, 32);
        leftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        leftArm.addBox(0.0F, 2.0F, -0.5F, 1, 9, 1, 0.0F);
        this.bipedLeftArm.addChild(leftArm);
        ModelRenderer leftForearm = new ModelRenderer(this, 82, 32);
        leftForearm.setRotationPoint(0.0F, 0.0F, 0.0F);
        leftForearm.addBox(-1.0F, 11.0F, -1.5F, 3, 11, 3, 0.0F);
        this.bipedLeftArm.addChild(leftForearm);
        ModelRenderer leftClaw = new ModelRenderer(this, 0, 0);
        leftClaw.setRotationPoint(0.0F, 0.0F, 0.0F);
        leftClaw.addBox(-0.5F, 22.0F, 0.5F, 1, 2, 1, 0.0F);
        leftForearm.addChild(leftClaw);
        ModelRenderer leftClaw2 = new ModelRenderer(this, 0, 0);
        leftClaw2.setRotationPoint(0.0F, 0.0F, 0.0F);
        leftClaw2.addBox(1.0F, 22.0F, -1.0F, 1, 3, 1, 0.0F);
        leftForearm.addChild(leftClaw2);
        ModelRenderer leftClaw3 = new ModelRenderer(this, 0, 0);
        leftClaw3.setRotationPoint(0.0F, 0.0F, 0.0F);
        leftClaw3.addBox(-1.0F, 22.0F, -1.5F, 1, 4, 1, 0.0F);
        leftForearm.addChild(leftClaw3);

        // Right Leg
        this.bipedRightLeg = new ModelRenderer(this, 76, 48);
        this.bipedRightLeg.setRotationPoint(-3.5F, 2.0F, 0.0F);
        this.bipedRightLeg.addBox(0.0F, 0.0F, 0.0F, 1, 9, 1, 0.0F);
        ModelRenderer rightShin = new ModelRenderer(this, 84, 48);
        rightShin.setRotationPoint(0.0F, 0.0F, 0.0F);
        rightShin.addBox(-1.0F, 9.0F, -0.5F, 3, 11, 3, 0.0F);
        this.bipedRightLeg.addChild(rightShin);
        ModelRenderer rightTalon = new ModelRenderer(this, 0, 0);
        rightTalon.setRotationPoint(0.0F, 0.0F, 0.0F);
        rightTalon.addBox(-1.5F, 19.0F, -1.5F, 3, 3, 1, 0.0F);
        rightShin.addChild(rightTalon);
        ModelRenderer rightTalon2 = new ModelRenderer(this, 0, 0);
        rightTalon2.setRotationPoint(0.0F, 0.0F, 0.0F);
        rightTalon2.addBox(0.0F, 19.0F, 2.5F, 1, 3, 1, 0.0F);
        rightShin.addChild(rightTalon2);

        // Left Leg
        this.bipedLeftLeg = new ModelRenderer(this, 76, 48);
        this.bipedLeftLeg.setRotationPoint(2.5F, 2.0F, 0.0F);
        this.bipedLeftLeg.addBox(0.0F, 0.0F, 0.0F, 1, 9, 1, 0.0F);
        ModelRenderer leftShin = new ModelRenderer(this, 84, 48);
        leftShin.setRotationPoint(0.0F, 0.0F, 0.0F);
        leftShin.addBox(-1.0F, 9.0F, -0.5F, 3, 11, 3, 0.0F);
        this.bipedLeftLeg.addChild(leftShin);
        ModelRenderer leftTalon = new ModelRenderer(this, 0, 0);
        leftTalon.setRotationPoint(0.0F, 0.0F, 0.0F);
        leftTalon.addBox(-0.5F, 19.0F, -1.5F, 3, 3, 1, 0.0F);
        leftShin.addChild(leftTalon);
        ModelRenderer leftTalon2 = new ModelRenderer(this, 0, 0);
        leftTalon2.setRotationPoint(0.0F, 0.0F, 0.0F);
        leftTalon2.addBox(0.0F, 19.0F, 2.5F, 1, 3, 1, 0.0F);
        leftShin.addChild(leftTalon2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.isRiding = entity.isRiding();
        super.render(entity, f, f1, f2, f3, f4, f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        EntityAvris avris = (EntityAvris) entity;

        bipedHead.rotateAngleY = f3 / (180F / (float) Math.PI);
        bipedHead.rotateAngleX = f4 / (180F / (float) Math.PI);
        if (avris.hurtTime > 0) {
            lowerJaw.rotateAngleX = -0.45F * this.simplifyAngle(avris.hurtTime, 5.0F) * f1;
        } else {
            lowerJaw.rotateAngleX = 0F;
        }

        bipedLeftLeg.rotateAngleX = -0.85F * this.simplifyAngle(f, 30.0F) * f1;
        bipedRightLeg.rotateAngleX = 0.85F * this.simplifyAngle(f, 30.0F) * f1;

        bipedRightArm.rotateAngleX = (-0.5F + 0.5F * this.simplifyAngle(f, 10.0F)) * f1;
        bipedLeftArm.rotateAngleX = (0.5F - 0.5F * this.simplifyAngle(f, 10.0F)) * f1;

        if (this.isRiding) {
            this.bipedRightArm.rotateAngleX = -1.11F;
            this.bipedLeftArm.rotateAngleX = -1.11F;
            this.bipedRightLeg.rotateAngleX = -((float) Math.PI * 2F / 5F);
            this.bipedLeftLeg.rotateAngleX = -((float) Math.PI * 2F / 5F);
            this.bipedRightLeg.rotateAngleY = ((float) Math.PI / 10F);
            this.bipedLeftLeg.rotateAngleY = -((float) Math.PI / 10F);
        }
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }
}
