package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRagr extends ModelBase {

    private ModelRenderer head;
    private ModelRenderer nose;
    private ModelRenderer body;
    private ModelRenderer rightLeg;
    private ModelRenderer leftLeg;
    private ModelRenderer rightShoulder;
    private ModelRenderer rightForearm;
    private ModelRenderer leftShoulder;
    private ModelRenderer leftForearm;

    private ModelRenderer[] partArray = new ModelRenderer[8];

    public ModelRagr() {
        textureWidth = 128;
        textureHeight = 64;

        // Head, Nose and Forehead
        head = new ModelRenderer(this, 0, 0);
        head.addBox(-6F, -9F, -4F, 12, 14, 8);
        head.setRotationPoint(0F, -12F, 0F);
        nose = new ModelRenderer(this, 0, 32);
        nose.addBox(-2F, -2F, -7F, 4, 8, 3);
        head.addChild(nose);
        ModelRenderer forehead = new ModelRenderer(this, 0, 24);
        forehead.addBox(-6F, -9F, -6F, 12, 4, 2);
        head.addChild(forehead);

        // Body
        body = new ModelRenderer(this, 60, 0);
        body.addBox(-4F, -7F, -1F, 8, 14, 4);
        body.setRotationPoint(0F, 0F, 0F);
        ModelRenderer chest = new ModelRenderer(this, 86, 0);
        chest.addBox(-6F, -7F, -4F, 12, 5, 5);
        body.addChild(chest);

        // Right Leg
        rightLeg = new ModelRenderer(this, 16, 32);
        rightLeg.addBox(-2F, 0F, -1F, 3, 6, 3);
        rightLeg.setRotationPoint(-3F, 7F, 0F);

        // Left Leg
        leftLeg = new ModelRenderer(this, 16, 32);
        leftLeg.addBox(-1F, 0F, -1F, 3, 6, 3);
        leftLeg.setRotationPoint(3F, 7F, 0F);

        // Right Shoulder, Upper and Fore Arm, and Fist
        rightShoulder = new ModelRenderer(this, 32, 22);
        rightShoulder.addBox(-7F, -3F, -3F, 8, 8, 8);
        rightShoulder.setRotationPoint(-7F, -7F, 0F);
        ModelRenderer rightUpperArm = new ModelRenderer(this, 42, 0);
        rightUpperArm.addBox(-5F, 5F, -1F, 4, 8, 4);
        rightShoulder.addChild(rightUpperArm);
        rightForearm = new ModelRenderer(this, 34, 42);
        rightForearm.addBox(-6F, 13F, -2F, 6, 12, 6);
        rightShoulder.addChild(rightForearm);
        ModelRenderer rightFist = new ModelRenderer(this, 0, 46);
        rightFist.addBox(-7F, 25F, -3F, 8, 6, 8);
        rightForearm.addChild(rightFist);

        // Left Shoulder, Upper and Fore Arm, and Fist
        leftShoulder = new ModelRenderer(this, 32, 22);
        leftShoulder.addBox(-1F, -3F, -3F, 8, 8, 8);
        leftShoulder.setRotationPoint(7F, -7F, 0F);
        ModelRenderer leftUpperArm = new ModelRenderer(this, 42, 0);
        leftUpperArm.addBox(1F, 5F, -1F, 4, 8, 4);
        leftShoulder.addChild(leftUpperArm);
        leftForearm = new ModelRenderer(this, 34, 42);
        leftForearm.addBox(0F, 13F, -2F, 6, 12, 6);
        leftShoulder.addChild(leftForearm);
        ModelRenderer leftFist = new ModelRenderer(this, 0, 46);
        leftFist.addBox(-1F, 25F, -3F, 8, 6, 8);
        leftForearm.addChild(leftFist);

        this.partArray = new ModelRenderer[] { head, body, rightLeg, leftLeg, rightShoulder, leftShoulder };
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        head.render(f5);
        body.render(f5);
        rightLeg.render(f5);
        leftLeg.render(f5);
        rightShoulder.render(f5);
        leftShoulder.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
        this.head.rotateAngleX = f4 / (180F / (float) Math.PI);

        this.nose.offsetX = this.nose.offsetY = this.nose.offsetZ = 0.0F;
        this.nose.rotateAngleX = 0.135F + this.simplifyAngle(entity.ticksExisted, 20.0F) * -0.135F;

        if (entity.onGround) {
            this.leftLeg.rotateAngleX = -0.85F * this.simplifyAngle(f1, 15.0F) * 0.85F;
            this.rightLeg.rotateAngleX = 0.85F * this.simplifyAngle(f1, 15.0F) * 0.85F;
        }

        if (entity.onGround || entity.isRiding() || entity.isSneaking()) {
            this.rightShoulder.rotateAngleX = 0.8000468F;
            this.leftShoulder.rotateAngleX = -0.5000468F;
            this.rightShoulder.rotateAngleZ = 0.4000242F;
            this.leftShoulder.rotateAngleZ = -0.6000242F;

            this.rightForearm.rotateAngleX = -0.4000468F;
            this.leftForearm.rotateAngleX = -0.4000468F;
            this.rightForearm.rotateAngleZ = -0.2000242F;
            this.leftForearm.rotateAngleZ = 0.6000242F;
            this.rightForearm.offsetZ = 0.4000468F;
            this.leftForearm.offsetZ = 0.4000468F;
            this.rightForearm.offsetX = -0.1200468F;
            this.leftForearm.offsetX = 0.5200468F;

            for (int i = 0; i < this.partArray.length; i++) {
                this.partArray[i].offsetY = 0.465F;
            }
        } else {
            this.leftShoulder.rotateAngleX = -0.45F * this.simplifyAngle(entity.ticksExisted, 20.0F) * f1;
            this.rightShoulder.rotateAngleX = 0.45F * this.simplifyAngle(entity.ticksExisted, 20.0F) * f1;
            this.leftShoulder.rotateAngleZ = -0.45F * this.simplifyAngle(entity.ticksExisted, 20.0F) * f1;
            this.rightShoulder.rotateAngleZ = 0.45F * this.simplifyAngle(entity.ticksExisted, 20.0F) * f1;

            this.rightForearm.rotateAngleX = 0.0F;
            this.leftForearm.rotateAngleX = 0.0F;
            this.rightForearm.rotateAngleZ = 0.0F;
            this.leftForearm.rotateAngleZ = 0.0F;
            this.rightForearm.offsetZ = 0.0F;
            this.leftForearm.offsetZ = 0.0F;
            this.rightForearm.offsetX = 0.0F;
            this.leftForearm.offsetX = 0.0F;

            for (int i = 0; i < this.partArray.length; i++) {
                this.partArray[i].offsetY = 0.0F;
            }
        }
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }

}
