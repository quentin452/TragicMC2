package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.boss.EntityApis;
import tragicneko.tragicmc.entity.boss.EntityClaymation;

public class ModelApis extends ModelBase {

    private ModelRenderer head;
    private ModelRenderer body;
    private ModelRenderer rightArm;
    private ModelRenderer leftArm;
    private ModelRenderer rightForearm;
    private ModelRenderer leftForearm;
    private ModelRenderer rightLeg;
    private ModelRenderer leftLeg;

    public ModelApis() {
        textureWidth = 128;
        textureHeight = 128;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-5F, -9F, -6F, 10, 9, 10);
        head.setRotationPoint(0F, -18F, 0F);

        ModelRenderer snout = new ModelRenderer(this, 0, 22);
        snout.addBox(-2F, -4F, -11F, 4, 4, 5);
        head.addChild(snout);

        ModelRenderer leftHorn = new ModelRenderer(this, 0, 100);
        leftHorn.addBox(3F, -2F, 6F, 3, 3, 12);
        leftHorn.rotateAngleX = 1.264073F;
        leftHorn.rotateAngleZ = 0.2974289F;
        head.addChild(leftHorn);
        ModelRenderer leftHornTip = new ModelRenderer(this, 32, 100);
        leftHornTip.addBox(12F, -22F, 0F, 2, 9, 2);
        leftHornTip.rotateAngleX = -0.2602503F;
        leftHornTip.rotateAngleZ = -0.2602503F;
        head.addChild(leftHornTip);

        ModelRenderer rightHorn = new ModelRenderer(this, 0, 100);
        rightHorn.addBox(-6F, -2F, 6F, 3, 3, 12);
        rightHorn.rotateAngleX = 1.264073F;
        rightHorn.rotateAngleZ = -0.2974289F;
        head.addChild(rightHorn);
        ModelRenderer rightHornTip = new ModelRenderer(this, 32, 100);
        rightHornTip.addBox(-14F, -22F, 0F, 2, 9, 2);
        rightHornTip.rotateAngleX = -0.2602503F;
        rightHornTip.rotateAngleZ = 0.2602503F;
        head.addChild(rightHornTip);

        body = new ModelRenderer(this, 48, 48);
        body.addBox(-8F, 0F, -2F, 16, 12, 10);
        body.setRotationPoint(0F, -18F, 0F);
        body.rotateAngleX = 0.3346075F;
        ModelRenderer abdomen = new ModelRenderer(this, 48, 72);
        abdomen.addBox(-5F, 9F, 2F, 10, 10, 6);
        abdomen.rotateAngleX = -0.3346075F;
        body.addChild(abdomen);

        rightArm = new ModelRenderer(this, 40, 16);
        rightArm.addBox(-6F, -2F, -2F, 6, 18, 8);
        rightArm.setRotationPoint(-8F, -16F, 0F);
        rightArm.rotateAngleX = 0.2115358F;
        rightArm.rotateAngleZ = 0.21115358F;
        ModelRenderer rightShoulder = new ModelRenderer(this, 72, 0);
        rightShoulder.addBox(-10F, -5F, -4F, 10, 10, 14);
        rightArm.addChild(rightShoulder);
        rightForearm = new ModelRenderer(this, 72, 28);
        rightForearm.addBox(-5F, 9F, 10F, 5, 12, 4);
        rightForearm.rotateAngleX = -0.7435722F;
        rightArm.addChild(rightForearm);

        leftArm = new ModelRenderer(this, 40, 16);
        leftArm.addBox(0F, -2F, -2F, 6, 18, 8);
        leftArm.setRotationPoint(8F, -16F, 0F);
        leftArm.rotateAngleX = 0.2115358F;
        leftArm.rotateAngleZ = -0.21115358F;
        ModelRenderer leftShoulder = new ModelRenderer(this, 72, 0);
        leftShoulder.addBox(0F, -5F, -4F, 10, 10, 14);
        leftArm.addChild(leftShoulder);
        leftForearm = new ModelRenderer(this, 72, 28);
        leftForearm.addBox(0F, 9F, 10F, 5, 12, 4);
        leftForearm.rotateAngleX = -0.7435722F;
        leftArm.addChild(leftForearm);

        rightLeg = new ModelRenderer(this, 0, 32);
        rightLeg.addBox(-3F, 0F, -2F, 6, 12, 6);
        rightLeg.setRotationPoint(-3F, 2F, 5F);
        rightLeg.rotateAngleZ = 0.1487144F;
        ModelRenderer rightShin = new ModelRenderer(this, 0, 56);
        rightShin.addBox(-3F, 12F, 0F, 4, 9, 4);
        rightShin.rotateAngleZ = -0.0743572F;
        rightLeg.addChild(rightShin);
        ModelRenderer rightFoot = new ModelRenderer(this, 0, 72);
        rightFoot.addBox(-5F, 20F, -5F, 5, 2, 8);
        rightFoot.rotateAngleZ = -0.07487144F;
        rightShin.addChild(rightFoot);

        leftLeg = new ModelRenderer(this, 0, 32);
        leftLeg.addBox(-2F, 0F, -2F, 6, 12, 6);
        leftLeg.setRotationPoint(3F, 2F, 5F);
        leftLeg.rotateAngleZ = -0.1487144F;
        ModelRenderer leftShin = new ModelRenderer(this, 0, 56);
        leftShin.addBox(0F, 12F, 0F, 4, 9, 4);
        leftShin.rotateAngleZ = 0.0743572F;
        leftLeg.addChild(leftShin);
        ModelRenderer leftFoot = new ModelRenderer(this, 0, 72);
        leftFoot.addBox(1F, 20F, -5F, 5, 2, 8);
        leftFoot.rotateAngleZ = 0.07487144F;
        leftShin.addChild(leftFoot);
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
        if (!(entity instanceof EntityApis) && !(entity instanceof EntityClaymation)) return;

        head.rotateAngleY = f3 / (180F / (float) Math.PI);
        head.rotateAngleX = f4 / (180F / (float) Math.PI);

        leftLeg.rotateAngleX = -1.5F * this.simplifyAngle(f, 13.0F) * f1;
        rightLeg.rotateAngleX = 1.5F * this.simplifyAngle(f, 13.0F) * f1;
        leftArm.rotateAngleX = 0.55F * this.simplifyAngle(f, 13.0F) * f1;
        rightArm.rotateAngleX = -0.55F * this.simplifyAngle(f, 13.0F) * f1;

        rightLeg.offsetY = -0.1F;
        leftLeg.offsetY = -0.1F;

        int stomp = 0;
        int charge = 0;
        int attack = 0;
        int hurt = 0;

        if (entity instanceof EntityApis) {
            EntityApis apis = (EntityApis) entity;
            stomp = apis.getStompTicks();
            charge = apis.getChargeTicks();
            attack = apis.getAttackTime();
            hurt = apis.getHurtTime();
        } else {
            EntityClaymation clay = (EntityClaymation) entity;
            stomp = clay.getUtilityInt();
            charge = clay.getUtilityInt2();
            attack = clay.getUtilityInt3();
            hurt = clay.getUtilityInt3();
        }

        if (stomp > 0) {
            head.rotateAngleX = rightArm.rotateAngleX = leftArm.rotateAngleX = rightArm.rotateAngleZ = leftArm.rotateAngleZ = leftLeg.rotateAngleX = rightLeg.rotateAngleX = 0.0F;

            head.rotateAngleX = 0.435F * simplifyAngle(stomp, 40.0F);

            rightArm.rotateAngleX = 0.645F * simplifyAngle(stomp, 40.0F) + 0.2115358F;
            leftArm.rotateAngleX = 0.645F * simplifyAngle(stomp, 40.0F) + 0.2115358F;

            rightArm.rotateAngleZ = -0.325F * simplifyAngle(stomp, 40.0F) + 0.21115358F;
            leftArm.rotateAngleZ = 0.325F * simplifyAngle(stomp, 40.0F) + -0.21115358F;

            if (stomp <= 22 && stomp >= 2) {
                leftLeg.offsetY = 0.125F * simplifyAngle(stomp, 40.0F) - 0.2F;
            } else {
                leftLeg.offsetY = -0.1F;
            }
        } else {
            head.rotateAngleX = rightArm.rotateAngleX = leftArm.rotateAngleX = rightArm.rotateAngleZ = leftArm.rotateAngleZ = 0.0F;

            if (attack > 0) {
                rightArm.rotateAngleX = 0.745F * simplifyAngle(attack, 10.0F) - 0.745F;
                rightArm.rotateAngleZ = -0.425F * simplifyAngle(attack, 10.0F) + 0.425F;
            } else {
                if (charge > 0) {
                    rightArm.rotateAngleX = -0.556F;
                    leftArm.rotateAngleX = -0.446F;
                    head.rotateAngleX = 0.336F;
                    rightArm.rotateAngleZ = -0.246F;
                    leftArm.rotateAngleZ = 0.246F;

                    leftLeg.rotateAngleX = 0.656F;
                    rightLeg.rotateAngleX = -0.656F;
                } else {
                    head.rotateAngleX = rightArm.rotateAngleX = leftArm.rotateAngleX = rightArm.rotateAngleZ = leftArm.rotateAngleZ = leftLeg.rotateAngleX = rightLeg.rotateAngleX = 0.0F;
                    rightArm.rotateAngleX = leftArm.rotateAngleX = 0.21115358F;
                    rightArm.rotateAngleZ = 0.21115358F;
                    leftArm.rotateAngleZ = -0.21115358F;

                    head.rotateAngleY = f3 / (180F / (float) Math.PI);
                    head.rotateAngleX = f4 / (180F / (float) Math.PI);

                    leftLeg.rotateAngleX = -1.5F * this.simplifyAngle(f, 13.0F) * f1;
                    rightLeg.rotateAngleX = 1.5F * this.simplifyAngle(f, 13.0F) * f1;
                    leftArm.rotateAngleX = 0.55F * this.simplifyAngle(f, 13.0F) * f1;
                    rightArm.rotateAngleX = -0.55F * this.simplifyAngle(f, 13.0F) * f1;

                    if (hurt > 0) {
                        head.rotateAngleX = 0.135F * simplifyAngle(hurt, 10.0F) - 0.135F;

                        rightArm.rotateAngleX = 0.145F * simplifyAngle(hurt, 10.0F) - 0.145F;
                        leftArm.rotateAngleX = 0.145F * simplifyAngle(hurt, 10.0F) - 0.145F;

                        rightArm.rotateAngleZ = -0.125F * simplifyAngle(hurt, 10.0F) + 0.125F;
                        leftArm.rotateAngleZ = 0.125F * simplifyAngle(hurt, 10.0F) + -0.125F;
                    }
                }

            }
        }
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }
}
