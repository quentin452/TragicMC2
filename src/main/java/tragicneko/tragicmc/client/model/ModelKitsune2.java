package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.boss.EntityClaymation;
import tragicneko.tragicmc.entity.boss.EntityKitsune;

public class ModelKitsune2 extends ModelBase {

    protected ModelRenderer head;
    protected ModelRenderer leftEar;
    protected ModelRenderer rightEar;
    protected ModelRenderer lowerMouth;
    protected ModelRenderer tongue;
    protected ModelRenderer body;
    protected ModelRenderer rightArm;
    protected ModelRenderer leftArm;

    protected ModelRenderer rightLeg;
    protected ModelRenderer leftLeg;

    protected ModelRenderer tailFR;
    protected ModelRenderer tailTipFR;
    protected ModelRenderer tailMR;
    protected ModelRenderer tailTipMR;
    protected ModelRenderer tailMid;
    protected ModelRenderer tailTipMid;
    protected ModelRenderer tailML;
    protected ModelRenderer tailTipML;
    protected ModelRenderer tailFL;
    protected ModelRenderer tailTipFL;

    public ModelKitsune2() {
        textureWidth = 64;
        textureHeight = 64;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-2.5F, -6F, -4F, 5, 5, 5);
        head.setRotationPoint(0F, 4F, -2F);

        leftEar = new ModelRenderer(this, 44, 0);
        leftEar.addBox(2F, -10F, -2F, 1, 5, 2);
        leftEar.rotateAngleX = -0.4089647F;
        leftEar.rotateAngleY = 0.5205006F;
        head.addChild(leftEar);

        rightEar = new ModelRenderer(this, 36, 0);
        rightEar.addBox(-3F, -10F, -2F, 1, 5, 2);
        rightEar.rotateAngleX = -0.4089647F;
        rightEar.rotateAngleY = -0.5205006F;
        head.addChild(rightEar);

        ModelRenderer snout = new ModelRenderer(this, 22, 0);
        snout.addBox(-1.5F, -4F, -7F, 3, 1, 3);
        head.addChild(snout);

        ModelRenderer nose = new ModelRenderer(this, 22, 10);
        nose.addBox(-1F, -5F, -8F, 2, 1, 1);
        head.addChild(nose);

        lowerMouth = new ModelRenderer(this, 22, 6);
        lowerMouth.addBox(-1F, -3F, -6F, 2, 1, 2);
        head.addChild(lowerMouth);

        ModelRenderer tongue = new ModelRenderer(this, 30, 9);
        tongue.addBox(-0.5F, -3.5F, -5.5F, 1, 1, 2);
        tongue.rotateAngleX = 0.075F;
        head.addChild(tongue);

        body = new ModelRenderer(this, 26, 16);
        body.addBox(-4.5F, 0F, -2F, 9, 5, 5);
        body.setRotationPoint(0F, 3F, -1F);
        body.rotateAngleX = 0.1487144F;

        ModelRenderer mane = new ModelRenderer(this, 16, 48);
        mane.addBox(-3.5F, -2F, -2F, 7, 5, 3);
        mane.rotateAngleX = -0.1487144F - 0.8922867F;
        body.addChild(mane);

        ModelRenderer abdomen = new ModelRenderer(this, 20, 32);
        abdomen.addBox(-2.5F, 5F, -1F, 5, 5, 2);
        abdomen.rotateAngleX = -0.1487144F;
        body.addChild(abdomen);

        rightArm = new ModelRenderer(this, 38, 30);
        rightArm.addBox(-3F, 0F, -1F, 2, 6, 2);
        rightArm.setRotationPoint(-3F, 4F, -1F);
        rightArm.rotateAngleX = -0.1858931F;
        rightArm.rotateAngleZ = 0.3717861F;

        ModelRenderer rightForearm = new ModelRenderer(this, 48, 30);
        rightForearm.addBox(4F, 0F, 1F, 2, 6, 2);
        rightForearm.setRotationPoint(-7F, 8F, -2F);
        rightForearm.rotateAngleX = 0.1858931F - 0.4833219F;
        rightForearm.rotateAngleZ = -0.3717861F - 0.1487144F;
        rightArm.addChild(rightForearm);

        leftArm = new ModelRenderer(this, 38, 30);
        leftArm.mirror = true;
        leftArm.addBox(1F, 0F, -1F, 2, 6, 2);
        leftArm.setRotationPoint(3F, 4F, -1F);
        leftArm.rotateAngleX = -0.1858931F;
        leftArm.rotateAngleZ = -0.3717861F;

        ModelRenderer leftForearm = new ModelRenderer(this, 48, 30);
        leftForearm.mirror = true;
        leftForearm.addBox(-6F, 0F, 1F, 2, 6, 2);
        leftForearm.setRotationPoint(7F, 8F, -2F);
        leftForearm.rotateAngleX = 0.1858931F - 0.4833219F;
        leftForearm.rotateAngleZ = 0.3717861F + 0.1487144F;
        leftArm.addChild(leftForearm);

        rightLeg = new ModelRenderer(this, 0, 16);
        rightLeg.addBox(-1F, 0F, -1F, 2, 6, 2);
        rightLeg.setRotationPoint(-2F, 13F, -1F);
        rightLeg.rotateAngleX = -0.1858931F;
        rightLeg.rotateAngleZ = 0.1487144F;

        ModelRenderer lowerRightLeg = new ModelRenderer(this, 10, 16);
        lowerRightLeg.addBox(3.5F, -11F, 2.5F, 3, 6, 3);
        lowerRightLeg.setRotationPoint(-3F, 18F, -2F);
        lowerRightLeg.rotateAngleX = 0.1858931F;
        lowerRightLeg.rotateAngleZ = -0.1487144F;
        rightLeg.addChild(lowerRightLeg);

        leftLeg = new ModelRenderer(this, 0, 16);
        leftLeg.mirror = true;
        leftLeg.addBox(-1F, 0F, -1F, 2, 6, 2);
        leftLeg.setRotationPoint(2F, 13F, -1F);
        leftLeg.rotateAngleX = -0.1858931F;
        leftLeg.rotateAngleZ = -0.1487144F;

        ModelRenderer lowerLeftLeg = new ModelRenderer(this, 10, 16);
        lowerLeftLeg.mirror = true;
        lowerLeftLeg.addBox(-5.5F, -11F, 2.5F, 3, 6, 3);
        lowerLeftLeg.setRotationPoint(3F, 18F, -2F);
        lowerLeftLeg.rotateAngleX = 0.1858931F;
        lowerLeftLeg.rotateAngleZ = 0.1487144F;
        leftLeg.addChild(lowerLeftLeg);

        tailFR = new ModelRenderer(this, 0, 30);
        tailFR.addBox(-7F, -9F, 0F, 4, 8, 4);
        tailFR.setRotationPoint(0F, 10F, 0F);
        tailFR.rotateAngleX = -0.1115358F;
        tailFR.rotateAngleZ = -0.8922867F;

        tailTipFR = new ModelRenderer(this, 0, 48);
        tailTipFR.addBox(-6.5F, -16F, 0.5F, 3, 8, 3);
        tailFR.addChild(tailTipFR);

        tailMR = new ModelRenderer(this, 0, 30);
        tailMR.addBox(-5F, -8F, 0F, 4, 8, 4);
        tailMR.setRotationPoint(0F, 10F, 0F);
        tailMR.rotateAngleX = -0.7435722F;
        tailMR.rotateAngleZ = -0.4833219F;

        tailTipMR = new ModelRenderer(this, 0, 48);
        tailTipMR.addBox(-4.5F, -16F, 0.5F, 3, 8, 3);
        tailMR.addChild(tailTipMR);

        tailMid = new ModelRenderer(this, 0, 30);
        tailMid.addBox(-2F, -8F, 1F, 4, 8, 4);
        tailMid.setRotationPoint(0F, 10F, 0F);
        tailMid.rotateAngleX = -0.5576792F;

        tailTipMid = new ModelRenderer(this, 0, 48);
        tailTipMid.addBox(-1.5F, -14.5F, 1.5F, 3, 8, 3);
        tailMid.addChild(tailTipMid);

        tailML = new ModelRenderer(this, 0, 30);
        tailML.addBox(1F, -8F, 0F, 4, 8, 4);
        tailML.setRotationPoint(0F, 10F, 0F);
        tailML.rotateAngleX = -0.7435722F;
        tailML.rotateAngleZ = 0.4833219F;

        tailTipML = new ModelRenderer(this, 0, 48);
        tailTipML.addBox(1.5F, -16F, 0.5F, 3, 8, 3);
        tailML.addChild(tailTipML);

        tailFL = new ModelRenderer(this, 0, 30);
        tailFL.addBox(2F, -9F, 0F, 4, 8, 4);
        tailFL.setRotationPoint(0F, 10F, 0F);
        tailFL.rotateAngleX = -0.1115358F;
        tailFL.rotateAngleZ = 0.8922867F;

        tailTipFL = new ModelRenderer(this, 0, 48);
        tailTipFL.addBox(3.5F, -16F, 1.5F, 3, 8, 3);
        tailFL.addChild(tailTipFL);
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
        tailFR.render(f5);
        tailMR.render(f5);
        tailMid.render(f5);
        tailML.render(f5);
        tailFL.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        if (!(entity instanceof EntityKitsune) && !(entity instanceof EntityClaymation)) return;

        leftLeg.offsetY = 0.0F;
        rightLeg.offsetY = 0.0F;

        int taunt = 0;
        int firing = 0;
        int attack = 0;
        int hurt = 0;

        if (entity instanceof EntityKitsune) {
            EntityKitsune kitsu = (EntityKitsune) entity;
            taunt = kitsu.getTauntTicks();
            firing = kitsu.getFiringTicks();
            attack = kitsu.getAttackTime();
            hurt = kitsu.getHurtTime();
        } else {
            EntityClaymation clay = (EntityClaymation) entity;
            firing = clay.getUtilityInt();
            attack = clay.getUtilityInt2();
            hurt = clay.getUtilityInt3();
        }

        head.rotateAngleY = f3 / (180F / (float) Math.PI);
        head.rotateAngleX = f4 / (180F / (float) Math.PI);

        leftLeg.rotateAngleX = -1.5F * this.simplifyAngle(f, 20.0F) * f1;
        rightLeg.rotateAngleX = 1.5F * this.simplifyAngle(f, 20.0F) * f1;

        leftArm.rotateAngleX = 0.55F * this.simplifyAngle(f, 20.0F) * f1;
        rightArm.rotateAngleX = -0.55F * this.simplifyAngle(f, 20.0F) * f1;

        tailFR.rotateAngleZ = -0.8922867F + this.simplifyAngle(entity.ticksExisted - 45.0F, 70.0F) * 0.14F;
        tailMR.rotateAngleZ = -0.4833219F + this.simplifyAngle(entity.ticksExisted - 15.0F, 70.0F) * 0.14F;
        tailMid.rotateAngleZ = this.simplifyAngle(entity.ticksExisted, 60.0F) * 0.14F;
        tailML.rotateAngleZ = 0.4833219F + this.simplifyAngle(entity.ticksExisted + 50.0F, 70.0F) * 0.14F;
        tailFL.rotateAngleZ = 0.8922867F + this.simplifyAngle(entity.ticksExisted + 30.0F, 70.0F) * 0.14F;

        tailTipFR.rotateAngleZ = this.simplifyAngle(entity.ticksExisted - 45.0F, 70.0F) * 0.04F;
        tailTipMR.rotateAngleZ = this.simplifyAngle(entity.ticksExisted - 15.0F, 70.0F) * 0.04F;
        tailTipMid.rotateAngleZ = this.simplifyAngle(entity.ticksExisted, 70.0F) * 0.04F;
        tailTipML.rotateAngleZ = this.simplifyAngle(entity.ticksExisted + 50.0F, 70.0F) * 0.04F;
        tailTipFL.rotateAngleZ = this.simplifyAngle(entity.ticksExisted + 30.0F, 70.0F) * 0.04F;

        lowerMouth.rotateAngleX = 0.0F;

        if (taunt > 0) {
            leftArm.rotateAngleX = -1.86F + this.simplifyAngle(taunt, 10.0F) * -0.165F;
            rightArm.rotateAngleX = -1.86F + this.simplifyAngle(taunt, 10.0F) * -0.165F;

            leftArm.rotateAngleZ = 0.48F + this.simplifyAngle(taunt, 10.0F) * -0.065F;
            rightArm.rotateAngleZ = -0.48F + this.simplifyAngle(taunt, 10.0F) * 0.065F;

            lowerMouth.rotateAngleX = 0.15F;

            rightEar.rotateAngleX = -0.4089647F + this.simplifyAngle(taunt, 8.0F) * 0.125F;
            leftEar.rotateAngleX = -0.4089647F + this.simplifyAngle(taunt, 8.0F) * 0.125F;
        } else {
            if (attack > 0) {
                if (attack >= 5) {
                    leftArm.rotateAngleX = -0.186F + this.simplifyAngle(attack, 5.0F) * -0.865F;
                    leftArm.rotateAngleZ = -0.48F + this.simplifyAngle(attack, 5.0F) * -0.125F;
                } else {
                    rightArm.rotateAngleX = -0.186F + this.simplifyAngle(attack, 5.0F) * -0.865F;
                    rightArm.rotateAngleZ = 0.48F + this.simplifyAngle(attack, 5.0F) * 0.125F;
                }
            } else {
                if (firing > 0) {
                    leftArm.rotateAngleX = -1.25F + this.simplifyAngle(firing, 40.0F) * 0.265F;
                    rightArm.rotateAngleX = -1.25F + this.simplifyAngle(firing, 40.0F) * 0.265F;

                    leftArm.rotateAngleZ = -0.48F + this.simplifyAngle(firing, 40.0F) * -0.225F;
                    rightArm.rotateAngleZ = 0.48F + this.simplifyAngle(firing, 40.0F) * 0.225F;

                    lowerMouth.rotateAngleX = this.simplifyAngle(firing, 40.0F) * 0.215F;
                } else {

                    if (hurt > 90 && hurt <= 100) {
                        leftArm.rotateAngleZ = -0.4858931F + this.simplifyAngle(hurt, 10.0F) * 0.215F;
                        rightArm.rotateAngleZ = 0.4858931F + this.simplifyAngle(hurt, 10.0F) * -0.215F;

                        lowerMouth.rotateAngleX = this.simplifyAngle(hurt, 10.0F) * 0.115F;

                        rightEar.rotateAngleX = -0.4089647F + this.simplifyAngle(hurt, 8.0F) * 0.125F;
                        leftEar.rotateAngleX = -0.4089647F + this.simplifyAngle(hurt, 8.0F) * 0.125F;
                    } else {
                        leftArm.rotateAngleZ = -0.1858931F;
                        rightArm.rotateAngleZ = 0.1858931F;
                    }
                }
            }
        }
    }

    protected float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }
}
