package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.boss.EntityYeti;

public class ModelYeti2 extends ModelBase {

    private ModelRenderer head;
    private ModelRenderer lowerJaw;
    private ModelRenderer leftBrow;
    private ModelRenderer rightBrow;

    private ModelRenderer body;
    private ModelRenderer rightArm;
    private ModelRenderer leftArm;
    private ModelRenderer rightLeg;
    private ModelRenderer leftLeg;

    public ModelYeti2() {
        textureWidth = 128;
        textureHeight = 64;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-5F, -8F, -4F, 10, 8, 6);
        head.setRotationPoint(0F, -7F, 0F);

        lowerJaw = new ModelRenderer(this, 0, 0);
        lowerJaw.addBox(-2F, 0F, -4F, 4, 2, 2);
        head.addChild(lowerJaw);

        ModelRenderer nose = new ModelRenderer(this, 32, 0);
        nose.addBox(-2F, -6F, -5F, 4, 2, 1);
        head.addChild(nose);

        ModelRenderer rightEar = new ModelRenderer(this, 48, 0);
        rightEar.addBox(-5F, -6F, -2F, 1, 3, 3);
        rightEar.rotateAngleZ = -0.214257F;
        head.addChild(rightEar);

        ModelRenderer rightEarUpper = new ModelRenderer(this, 32, 3);
        rightEarUpper.addBox(-5F, -7F, 0F, 1, 1, 2);
        rightEar.addChild(rightEarUpper);

        ModelRenderer rightEarTip = new ModelRenderer(this, 0, 0);
        rightEarTip.addBox(-5F, -8F, 1F, 1, 1, 1);
        rightEar.addChild(rightEarTip);

        ModelRenderer leftEar = new ModelRenderer(this, 48, 0);
        leftEar.addBox(4F, -6F, -2F, 1, 3, 3);
        leftEar.rotateAngleZ = 0.214257F;
        head.addChild(leftEar);

        ModelRenderer leftEarUpper = new ModelRenderer(this, 32, 3);
        leftEarUpper.addBox(4F, -7F, 0F, 1, 1, 2);
        leftEar.addChild(leftEarUpper);

        ModelRenderer leftEarTip = new ModelRenderer(this, 0, 0);
        leftEarTip.addBox(4F, -8F, 1F, 1, 1, 1);
        leftEar.addChild(leftEarTip);

        leftBrow = new ModelRenderer(this, 32, 12);
        leftBrow.addBox(2F, -7F, -5F, 4, 1, 1);
        leftBrow.rotateAngleZ = -0.214257F;
        head.addChild(leftBrow);

        rightBrow = new ModelRenderer(this, 32, 12);
        rightBrow.addBox(-6F, -7F, -5F, 4, 1, 1);
        rightBrow.rotateAngleZ = 0.214257F;
        head.addChild(rightBrow);

        ModelRenderer rightWhisker = new ModelRenderer(this, 32, 7);
        rightWhisker.addBox(-4F, 0F, -4F, 1, 4, 1);
        head.addChild(rightWhisker);

        ModelRenderer leftWhisker = new ModelRenderer(this, 32, 7);
        leftWhisker.addBox(3F, 0F, -4F, 1, 4, 1);
        head.addChild(leftWhisker);

        ModelRenderer crownbit = new ModelRenderer(this, 0, 0);
        crownbit.addBox(-5F, -11F, -2F, 1, 3, 1);
        head.addChild(crownbit);

        ModelRenderer crownbit2 = new ModelRenderer(this, 0, 0);
        crownbit2.addBox(4F, -11F, -2F, 1, 3, 1);
        head.addChild(crownbit2);

        ModelRenderer crownbit3 = new ModelRenderer(this, 0, 0);
        crownbit3.addBox(-3F, -12F, -3F, 1, 4, 1);
        head.addChild(crownbit3);

        ModelRenderer crownbit4 = new ModelRenderer(this, 0, 0);
        crownbit4.addBox(2F, -12F, -3F, 1, 4, 1);
        head.addChild(crownbit4);

        ModelRenderer crownbit5 = new ModelRenderer(this, 0, 0);
        crownbit5.addBox(-1F, -11F, -4F, 2, 3, 1);
        head.addChild(crownbit5);

        ModelRenderer crownbit6 = new ModelRenderer(this, 0, 0);
        crownbit6.addBox(-2F, -10F, -3F, 4, 2, 1);
        head.addChild(crownbit6);

        ModelRenderer crownbit7 = new ModelRenderer(this, 0, 0);
        crownbit7.addBox(-4F, -9F, -4F, 8, 1, 1);
        head.addChild(crownbit7);

        ModelRenderer crownbit8 = new ModelRenderer(this, 0, 0);
        crownbit8.addBox(-4F, -9F, -2F, 8, 1, 1);
        head.addChild(crownbit8);

        body = new ModelRenderer(this, 70, 0);
        body.addBox(-8F, 0F, -2F, 16, 12, 6);
        body.setRotationPoint(0F, -7F, 1F);
        body.rotateAngleX = 0.2082002F;

        ModelRenderer chestUpper = new ModelRenderer(this, 76, 30);
        chestUpper.addBox(-7F, 3F, -3F, 14, 6, 1);
        body.addChild(chestUpper);

        ModelRenderer chestLower = new ModelRenderer(this, 76, 38);
        chestLower.addBox(-5F, 9F, -2.8F, 10, 6, 1);
        body.addChild(chestLower);

        ModelRenderer abdomen = new ModelRenderer(this, 73, 18);
        abdomen.addBox(-6F, 12F, -2F, 12, 6, 5);
        body.addChild(abdomen);

        rightArm = new ModelRenderer(this, 48, 10);
        rightArm.addBox(-3F, 0F, -2F, 4, 12, 5);
        rightArm.setRotationPoint(-9F, -7F, 1F);
        rightArm.rotateAngleX = -0.1142557F;

        ModelRenderer rightShoulder = new ModelRenderer(this, 66, 46);
        rightShoulder.addBox(-5F, -1F, -3F, 7, 7, 7);
        rightArm.addChild(rightShoulder);

        ModelRenderer rightFist = new ModelRenderer(this, 0, 36);
        rightFist.addBox(-4F, 11F, -2F, 6, 10, 8);
        rightFist.rotateAngleX = -0.2142572F;
        rightArm.addChild(rightFist);

        ModelRenderer thumbClaw = new ModelRenderer(this, 0, 0);
        thumbClaw.addBox(0F, 21F, 0F, 1, 2, 1);
        rightFist.addChild(thumbClaw);

        ModelRenderer middleClaw = new ModelRenderer(this, 0, 0);
        middleClaw.addBox(-3F, 21F, 1F, 1, 3, 1);
        rightFist.addChild(middleClaw);

        ModelRenderer indexClaw = new ModelRenderer(this, 0, 0);
        indexClaw.addBox(-2F, 21F, -1F, 1, 3, 1);
        rightFist.addChild(indexClaw);

        ModelRenderer ringClaw = new ModelRenderer(this, 0, 0);
        ringClaw.addBox(-3F, 21F, 3F, 1, 3, 1);
        rightFist.addChild(ringClaw);

        ModelRenderer pinkyClaw = new ModelRenderer(this, 0, 0);
        pinkyClaw.addBox(-2F, 21F, 5F, 1, 2, 1);
        rightFist.addChild(pinkyClaw);

        leftArm = new ModelRenderer(this, 48, 10);
        leftArm.addBox(0F, 0F, -2F, 4, 12, 5);
        leftArm.setRotationPoint(8F, -7F, 1F);
        leftArm.rotateAngleX = -0.1142557F;

        ModelRenderer leftShoulder = new ModelRenderer(this, 66, 46);
        leftShoulder.addBox(-1F, -1F, -3F, 7, 7, 7);
        leftArm.addChild(leftShoulder);

        ModelRenderer leftFist = new ModelRenderer(this, 32, 36);
        leftFist.addBox(-1F, 11F, -2F, 6, 10, 8);
        leftFist.rotateAngleX = -0.2142572F;
        leftArm.addChild(leftFist);

        ModelRenderer thumbClaw2 = new ModelRenderer(this, 0, 0);
        thumbClaw2.addBox(-1F, 21F, 0F, 1, 2, 1);
        leftFist.addChild(thumbClaw2);

        ModelRenderer indexClaw2 = new ModelRenderer(this, 0, 0);
        indexClaw2.addBox(1F, 21F, -1F, 1, 3, 1);
        leftFist.addChild(indexClaw2);

        ModelRenderer middleClaw2 = new ModelRenderer(this, 0, 0);
        middleClaw2.addBox(2F, 21F, 1F, 1, 3, 1);
        leftFist.addChild(middleClaw2);

        ModelRenderer ringClaw2 = new ModelRenderer(this, 0, 0);
        ringClaw2.addBox(2F, 21F, 3F, 1, 3, 1);
        leftFist.addChild(ringClaw2);

        ModelRenderer pinkyClaw2 = new ModelRenderer(this, 0, 0);
        pinkyClaw2.addBox(1F, 21F, 5F, 1, 2, 1);
        leftFist.addChild(pinkyClaw2);

        rightLeg = new ModelRenderer(this, 0, 16);
        rightLeg.addBox(-2F, 0F, -2F, 4, 10, 4);
        rightLeg.setRotationPoint(-4F, 10F, 5F);

        ModelRenderer rightFoot = new ModelRenderer(this, 17, 21);
        rightFoot.addBox(-3F, 10F, -6F, 5, 4, 8);
        rightLeg.addChild(rightFoot);

        ModelRenderer rightClaw = new ModelRenderer(this, 0, 0);
        rightClaw.addBox(1F, 11F, -8F, 1, 3, 2);
        rightFoot.addChild(rightClaw);

        ModelRenderer rightClaw2 = new ModelRenderer(this, 0, 0);
        rightClaw2.addBox(-3F, 11F, -8F, 1, 3, 2);
        rightFoot.addChild(rightClaw2);

        ModelRenderer rightClaw3 = new ModelRenderer(this, 0, 0);
        rightClaw3.addBox(-1F, 11F, -8F, 1, 3, 2);
        rightFoot.addChild(rightClaw3);

        leftLeg = new ModelRenderer(this, 0, 16);
        leftLeg.addBox(-2F, 0F, -2F, 4, 10, 4);
        leftLeg.setRotationPoint(4F, 10F, 5F);

        ModelRenderer leftFoot = new ModelRenderer(this, 17, 21);
        leftFoot.addBox(-2F, 10F, -6F, 5, 4, 8);
        leftLeg.addChild(leftFoot);

        ModelRenderer leftClaw = new ModelRenderer(this, 0, 0);
        leftClaw.addBox(2F, 11F, -8F, 1, 3, 2);
        leftFoot.addChild(leftClaw);

        ModelRenderer leftClaw2 = new ModelRenderer(this, 0, 0);
        leftClaw2.addBox(-2F, 11F, -8F, 1, 3, 2);
        leftFoot.addChild(leftClaw2);

        ModelRenderer leftClaw3 = new ModelRenderer(this, 0, 0);
        leftClaw3.addBox(0F, 11F, -8F, 1, 3, 2);
        leftFoot.addChild(leftClaw3);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        head.render(f5);
        body.render(f5);
        rightArm.render(f5);
        leftArm.render(f5);
        rightLeg.render(f5);
        leftLeg.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        if (!(entity instanceof EntityYeti)) return;
        EntityYeti yeti = (EntityYeti) entity;

        leftBrow.rotateAngleZ = -0.214257F;
        rightBrow.rotateAngleZ = 0.214257F;

        rightArm.rotateAngleX = -0.2142557F;
        leftArm.rotateAngleX = -0.2142557F;

        rightArm.rotateAngleZ = 0.0F;
        leftArm.rotateAngleZ = 0.0F;

        head.rotateAngleY = f3 / (180F / (float) Math.PI);
        head.rotateAngleX = f4 / (180F / (float) Math.PI);
        head.rotateAngleZ = 0.0F;

        leftLeg.rotateAngleX = -1.5F * this.simplifyAngle(f, 13.0F) * f1;
        rightLeg.rotateAngleX = 1.5F * this.simplifyAngle(f, 13.0F) * f1;
        leftArm.rotateAngleX = 0.55F * this.simplifyAngle(f, 13.0F) * f1;
        rightArm.rotateAngleX = -0.55F * this.simplifyAngle(f, 13.0F) * f1;

        leftArm.rotateAngleZ = 0.0F;
        rightArm.rotateAngleZ = 0.0F;

        if (yeti.isCharging()) {
            leftArm.rotateAngleX = -0.35F;
            rightArm.rotateAngleX = -0.45F;
            leftLeg.rotateAngleX = -1.25F;
            rightLeg.rotateAngleX = 1.25F;
        } else {
            if (yeti.getHurtTime() > 0) {
                leftBrow.rotateAngleZ = -0.214257F + this.simplifyAngle(yeti.getHurtTime(), 10.0F) * -0.145F;
                rightBrow.rotateAngleZ = 0.214257F + this.simplifyAngle(yeti.getHurtTime(), 10.0F) * 0.145F;
                head.rotateAngleX = this.simplifyAngle(yeti.getHurtTime(), 10.0F) * 0.245F;

                rightArm.rotateAngleX = -0.2142557F + this.simplifyAngle(yeti.getHurtTime(), 10.0F) * 0.114F;
                leftArm.rotateAngleX = -0.2142557F + this.simplifyAngle(yeti.getHurtTime(), 10.0F) * 0.114F;
                rightArm.rotateAngleZ = this.simplifyAngle(yeti.getHurtTime(), 10.0F) * -0.224F;
                leftArm.rotateAngleZ = this.simplifyAngle(yeti.getHurtTime(), 10.0F) * 0.224F;
            } else {
                if (yeti.isRoaring()) {
                    rightArm.rotateAngleX = -2.2F + this.simplifyAngle(yeti.getRoarTicks(), 20.0F) * 0.125F;
                    leftArm.rotateAngleX = -2.2F + this.simplifyAngle(yeti.getRoarTicks(), 20.0F) * 0.125F;

                    rightArm.rotateAngleZ = this.simplifyAngle(yeti.getRoarTicks(), 20.0F) * -0.124F;
                    leftArm.rotateAngleZ = this.simplifyAngle(yeti.getRoarTicks(), 20.0F) * 0.124F;
                } else {
                    if (yeti.getFrostTicks() > 0) {
                        rightArm.rotateAngleZ = this.simplifyAngle(yeti.getFrostTicks(), 5.0F) * -0.124F + 0.224F;
                        leftArm.rotateAngleZ = this.simplifyAngle(yeti.getFrostTicks(), 5.0F) * 0.124F - 0.224F;
                        head.rotateAngleZ = this.simplifyAngle(yeti.getFrostTicks() + 5, 5.0F) * 0.124F;
                    } else {
                        if (yeti.getAttackTime() > 0) {
                            rightArm.rotateAngleX = -0.4142557F
                                + this.simplifyAngle(yeti.getAttackTime(), 10.0F) * 1.514F;
                            leftArm.rotateAngleX = -0.4142557F
                                + this.simplifyAngle(yeti.getAttackTime(), 10.0F) * 1.514F;
                            rightArm.rotateAngleZ = 0.46F + this.simplifyAngle(yeti.getAttackTime(), 10.0F) * -0.664F;
                            leftArm.rotateAngleZ = -0.46F + this.simplifyAngle(yeti.getAttackTime(), 10.0F) * 0.664F;
                        } else {
                            int meow = (yeti.ticksExisted - 10) % 60;
                            if (yeti.getThrowing() && meow > 50 && meow <= 60) {
                                int mer = Math.max(0, meow);
                                rightArm.rotateAngleX = -1.2142557F + this.simplifyAngle(mer, 10.0F) * 1.214F;
                                leftArm.rotateAngleX = -1.2142557F + this.simplifyAngle(mer, 10.0F) * 1.214F;
                                rightArm.rotateAngleZ = 0.26F + this.simplifyAngle(mer, 10.0F) * -0.164F;
                                leftArm.rotateAngleZ = -0.26F + this.simplifyAngle(mer, 10.0F) * 0.164F;
                            }
                        }
                    }
                }
            }
        }
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }
}
