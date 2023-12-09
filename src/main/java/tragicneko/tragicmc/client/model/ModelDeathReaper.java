package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.boss.EntityClaymation;
import tragicneko.tragicmc.entity.boss.EntityDeathReaper;

public class ModelDeathReaper extends ModelBase {

    private ModelRenderer spine;
    private ModelRenderer head;
    private ModelRenderer lowerJaw;

    private ModelRenderer heart;
    private ModelRenderer frontRibTL;
    private ModelRenderer frontRibTR;
    private ModelRenderer frontRibML;
    private ModelRenderer frontRibMR;
    private ModelRenderer frontRibBL;
    private ModelRenderer frontRibBR;

    private ModelRenderer armLeft;
    private ModelRenderer armRight;
    private ModelRenderer forearmRight;
    private ModelRenderer forearmLeft;

    private ModelRenderer scythe;

    public ModelDeathReaper() {
        textureWidth = 128;
        textureHeight = 64;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-2.5F, -5F, -1F, 5, 5, 2);
        head.setRotationPoint(0F, -2F, 2F);
        ModelRenderer hornRight = new ModelRenderer(this, 0, 0);
        hornRight.addBox(-3F, -8F, 0F, 1, 5, 1);
        head.addChild(hornRight);
        ModelRenderer hornLeft = new ModelRenderer(this, 0, 0);
        hornLeft.addBox(2F, -8F, 0F, 1, 5, 1);
        head.addChild(hornLeft);
        ModelRenderer skullFront = new ModelRenderer(this, 0, 14);
        skullFront.addBox(-2F, -5F, -3F, 4, 2, 2);
        head.addChild(skullFront);
        ModelRenderer upperTeeth = new ModelRenderer(this, 0, 0);
        upperTeeth.addBox(-1F, -3F, -3F, 2, 2, 2);
        head.addChild(upperTeeth);
        ModelRenderer leftEye = new ModelRenderer(this, 16, 16);
        leftEye.addBox(-2F, -3F, -2F, 1, 1, 1);
        head.addChild(leftEye);
        ModelRenderer rightEye = new ModelRenderer(this, 16, 16);
        rightEye.addBox(1F, -3F, -2F, 1, 1, 1);
        head.addChild(rightEye);

        lowerJaw = new ModelRenderer(this, 0, 0);
        lowerJaw.addBox(-2.5F, 4F, -6F, 5, 1, 4);
        lowerJaw.setRotationPoint(0F, -4F, 2F);
        head.addChild(lowerJaw);
        ModelRenderer lowerToothRight = new ModelRenderer(this, 0, 0);
        lowerToothRight.addBox(-2F, 3F, -6F, 1, 1, 1);
        lowerJaw.addChild(lowerToothRight);
        ModelRenderer lowerToothLeft = new ModelRenderer(this, 0, 0);
        lowerToothLeft.addBox(1F, 3F, -6F, 1, 1, 1);
        lowerJaw.addChild(lowerToothLeft);

        spine = new ModelRenderer(this, 0, 0);
        spine.addBox(-1F, -3F, -1F, 2, 3, 1);
        spine.setRotationPoint(0F, 1F, 4F);
        spine.rotateAngleX = 0.3346075F;
        ModelRenderer spineMiddle = new ModelRenderer(this, 0, 0);
        spineMiddle.addBox(-1F, 0F, -1F, 2, 6, 1);
        spineMiddle.rotateAngleX = -0.1743572F;
        spine.addChild(spineMiddle);
        ModelRenderer spineBottom = new ModelRenderer(this, 0, 0);
        spineBottom.addBox(-1F, 6F, 1F, 2, 5, 1);
        spineBottom.rotateAngleX = -0.5974289F;
        spine.addChild(spineBottom);

        ModelRenderer ribML = new ModelRenderer(this, 0, 0);
        ribML.addBox(1F, 1.5F, 0F, 4, 1, 1);
        ribML.rotateAngleY = 0.3346075F;
        spine.addChild(ribML);
        ModelRenderer ribTL = new ModelRenderer(this, 0, 0);
        ribTL.addBox(1F, -1F, 0F, 4, 1, 1);
        ribTL.rotateAngleY = 0.3346075F;
        ribTL.rotateAngleX = 0.2112592F;
        spine.addChild(ribTL);
        ModelRenderer ribTR = new ModelRenderer(this, 0, 0);
        ribTR.addBox(-5F, -1F, 0F, 4, 1, 1);
        ribTR.rotateAngleY = -0.3346075F;
        ribTR.rotateAngleX = 0.2112592F;
        spine.addChild(ribTR);
        ModelRenderer ribMR = new ModelRenderer(this, 0, 0);
        ribMR.addBox(-5F, 1.5F, 0F, 4, 1, 1);
        ribMR.rotateAngleY = -0.3717861F;
        spine.addChild(ribMR);
        ModelRenderer ribBL = new ModelRenderer(this, 0, 0);
        ribBL.addBox(1F, 3.5F, 0F, 3, 1, 1);
        ribBL.rotateAngleY = 0.4089647F;
        ribBL.rotateAngleX = -0.32242714F;
        spine.addChild(ribBL);
        ModelRenderer ribBR = new ModelRenderer(this, 0, 0);
        ribBR.addBox(-4F, 3.5F, 0F, 3, 1, 1);
        ribBR.rotateAngleY = -0.5576792F;
        ribBR.rotateAngleX = -0.32242714F;
        spine.addChild(ribBR);
        ModelRenderer ribWBL = new ModelRenderer(this, 0, 0);
        ribWBL.addBox(1F, 5.5F, 0.5F, 3, 1, 1);
        ribWBL.rotateAngleY = 0.4089647F;
        ribWBL.rotateAngleX = -0.32242714F;
        spine.addChild(ribWBL);
        ModelRenderer ribWBR = new ModelRenderer(this, 0, 0);
        ribWBR.addBox(-4F, 5.5F, 0.5F, 3, 1, 1);
        ribWBR.rotateAngleY = -0.5576792F;
        ribWBR.rotateAngleX = -0.32242714F;
        spine.addChild(ribWBR);

        heart = new ModelRenderer(this, 16, 14);
        heart.addBox(-1F, 0F, -3F, 2, 2, 2);
        heart.rotateAngleX = -0.3346075F;
        spine.addChild(heart);
        frontRibTL = new ModelRenderer(this, 0, 0);
        frontRibTL.addBox(-1F, 0F, -4F, 5, 1, 1);
        frontRibTL.rotateAngleY = -0.3386F;
        frontRibTL.rotateAngleZ = -0.1115358F;
        spine.addChild(frontRibTL);
        frontRibTR = new ModelRenderer(this, 0, 0);
        frontRibTR.addBox(-3F, -1F, -4F, 4, 1, 1);
        frontRibTR.rotateAngleY = 0.344878F;
        frontRibTR.rotateAngleZ = -0.1115358F;
        spine.addChild(frontRibTR);
        frontRibML = new ModelRenderer(this, 0, 0);
        frontRibML.addBox(0F, 1.5F, -5F, 4, 1, 1);
        frontRibML.rotateAngleY = -0.3340967F;
        spine.addChild(frontRibML);
        frontRibMR = new ModelRenderer(this, 0, 0);
        frontRibMR.addBox(-3F, 1.5F, -5F, 4, 1, 1);
        frontRibMR.rotateAngleY = 0.3451081F;
        spine.addChild(frontRibMR);
        frontRibBL = new ModelRenderer(this, 0, 0);
        frontRibBL.addBox(0F, 3.5F, -5F, 3, 1, 1);
        frontRibBL.rotateAngleY = -0.402503F;
        frontRibBL.rotateAngleZ = 0.0743572F;
        spine.addChild(frontRibBL);
        frontRibBR = new ModelRenderer(this, 0, 0);
        frontRibBR.addBox(-2F, 3.5F, -5F, 3, 1, 1);
        frontRibBR.rotateAngleY = 0.5461433F;
        frontRibBR.rotateAngleX = 0.1487144F;
        spine.addChild(frontRibBR);

        armRight = new ModelRenderer(this, 0, 0);
        armRight.addBox(-1F, 3F, 0F, 1, 6, 1);
        armRight.setRotationPoint(-5F, -1F, 3F);
        armRight.rotateAngleZ = 0.445289F;
        ModelRenderer shoulderRight = new ModelRenderer(this, 0, 0);
        shoulderRight.addBox(-2F, 0F, -1F, 3, 3, 3);
        armRight.addChild(shoulderRight);

        forearmRight = new ModelRenderer(this, 0, 0);
        forearmRight.addBox(-1F, 0F, -1F, 3, 8, 3);
        forearmRight.setRotationPoint(-1F, 9F, 0F);
        forearmRight.rotateAngleX = -0.5576792F;
        armRight.addChild(forearmRight);

        ModelRenderer rightThumb = new ModelRenderer(this, 0, 0);
        rightThumb.addBox(1F, 8F, 0F, 1, 2, 1);
        forearmRight.addChild(rightThumb);
        ModelRenderer rightFinger = new ModelRenderer(this, 0, 0);
        rightFinger.addBox(-1F, 8F, -1F, 1, 2, 1);
        forearmRight.addChild(rightFinger);
        ModelRenderer rightFinger2 = new ModelRenderer(this, 0, 0);
        rightFinger2.addBox(0F, 8F, 1F, 1, 2, 1);
        forearmRight.addChild(rightFinger2);

        armLeft = new ModelRenderer(this, 0, 0);
        armLeft.addBox(1F, 3F, 0F, 1, 6, 1);
        armLeft.setRotationPoint(4F, -1F, 2F);
        armLeft.rotateAngleZ = -0.442529F;
        ModelRenderer shoulderLeft = new ModelRenderer(this, 0, 0);
        shoulderLeft.addBox(0F, 0F, -1F, 3, 3, 3);
        armLeft.addChild(shoulderLeft);

        forearmLeft = new ModelRenderer(this, 0, 0);
        forearmLeft.addBox(-1F, 0F, -1F, 3, 8, 3);
        forearmLeft.setRotationPoint(1F, 9F, 0F);
        forearmLeft.rotateAngleZ = 0.33104194F;
        forearmLeft.rotateAngleX = -0.950024F;
        armLeft.addChild(forearmLeft);

        ModelRenderer leftFinger = new ModelRenderer(this, 0, 0);
        leftFinger.addBox(0F, 8F, 1F, 1, 2, 1);
        forearmLeft.addChild(leftFinger);
        ModelRenderer leftFinger2 = new ModelRenderer(this, 0, 0);
        leftFinger2.addBox(-1F, 8F, -1F, 1, 2, 1);
        forearmLeft.addChild(leftFinger2);
        ModelRenderer leftThumb = new ModelRenderer(this, 0, 0);
        leftThumb.addBox(1F, 8F, 0F, 1, 2, 1);
        forearmLeft.addChild(leftThumb);

        scythe = new ModelRenderer(this, 0, 26);
        scythe.addBox(-8F, 0F, 0F, 36, 1, 1);
        scythe.setRotationPoint(0F, 18F, -2F);
        scythe.rotateAngleY = 1.25995267F;
        scythe.rotateAngleZ = -2.24554337F;
        scythe.rotateAngleX = -0.723F;
        armRight.addChild(scythe);

        ModelRenderer scytheBlade = new ModelRenderer(this, 0, 46);
        scytheBlade.addBox(13F, 1F, 0F, 13, 2, 1);
        scythe.addChild(scytheBlade);
        ModelRenderer scytheBlade2 = new ModelRenderer(this, 0, 38);
        scytheBlade2.addBox(14F, 3F, 0F, 11, 4, 1);
        scythe.addChild(scytheBlade2);
        ModelRenderer scytheBlade3 = new ModelRenderer(this, 0, 30);
        scytheBlade3.addBox(13F, 7F, 0F, 10, 5, 1);
        scythe.addChild(scytheBlade3);
        ModelRenderer scytheBlade4 = new ModelRenderer(this, 0, 30);
        scytheBlade4.addBox(12F, 12F, 0F, 10, 4, 1);
        scythe.addChild(scytheBlade4);
        ModelRenderer scytheBlade5 = new ModelRenderer(this, 0, 30);
        scytheBlade5.addBox(12F, 16F, 0F, 9, 4, 1);
        scythe.addChild(scytheBlade5);
        ModelRenderer scytheBlade6 = new ModelRenderer(this, 0, 30);
        scytheBlade6.addBox(11F, 20F, 0F, 9, 2, 1);
        scythe.addChild(scytheBlade6);
        ModelRenderer scytheBlade7 = new ModelRenderer(this, 0, 30);
        scytheBlade7.addBox(10F, 22F, 0F, 7, 2, 1);
        scythe.addChild(scytheBlade7);
        ModelRenderer scytheBlade8 = new ModelRenderer(this, 0, 30);
        scytheBlade8.addBox(9F, 24F, 0F, 7, 2, 1);
        scythe.addChild(scytheBlade8);
        ModelRenderer scytheBlade9 = new ModelRenderer(this, 0, 30);
        scytheBlade9.addBox(6F, 22F, 0F, 4, 2, 1);
        scythe.addChild(scytheBlade9);
        ModelRenderer scytheBlade10 = new ModelRenderer(this, 0, 30);
        scytheBlade10.addBox(6F, 20F, 0F, 2, 2, 1);
        scythe.addChild(scytheBlade10);
        ModelRenderer scytheBlade11 = new ModelRenderer(this, 0, 30);
        scytheBlade11.addBox(5F, 18F, 0F, 1, 2, 1);
        scythe.addChild(scytheBlade11);
        ModelRenderer scytheBlade12 = new ModelRenderer(this, 0, 46);
        scytheBlade12.addBox(12F, -2F, 0F, 12, 2, 1);
        scythe.addChild(scytheBlade12);
        ModelRenderer scytheBlade13 = new ModelRenderer(this, 0, 38);
        scytheBlade13.addBox(13F, -4F, 0F, 10, 2, 1);
        scythe.addChild(scytheBlade13);
        ModelRenderer scytheBlade14 = new ModelRenderer(this, 0, 30);
        scytheBlade14.addBox(15F, -6F, 0F, 6, 2, 1);
        scythe.addChild(scytheBlade14);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        spine.render(f5);
        head.render(f5);
        armLeft.render(f5);
        armRight.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        if (!(entity instanceof EntityDeathReaper) && !(entity instanceof EntityClaymation)) return;

        int attack = 0;
        int hit = 0;

        if (entity instanceof EntityDeathReaper) {
            EntityDeathReaper reap = (EntityDeathReaper) entity;
            hit = reap.getHitTime();
            attack = reap.getAttackTime();
        } else {
            EntityClaymation clay = (EntityClaymation) entity;
            hit = clay.getUtilityInt3();
            attack = clay.getUtilityInt2();
        }

        lowerJaw.rotateAngleX = head.rotateAngleX = armRight.rotateAngleX = scythe.offsetX = scythe.offsetZ = 0.0F;
        armRight.rotateAngleZ = 0.445289F;
        scythe.rotateAngleX = -0.723F;
        scythe.rotateAngleY = 1.25995267F;
        scythe.rotateAngleZ = -2.24554337F;

        if (attack > 0) {
            int time = (attack % 20) + 10;

            lowerJaw.rotateAngleX = this.simplifyAngle(time, 8.0F) * 0.223F;
            scythe.rotateAngleZ = 3.25995267F + this.simplifyAngle(time, 20.0F) * -1.667F;

            if (time >= 2 && time <= 7) {
                armRight.rotateAngleX = this.simplifyAngle(time, 20.0F) * 1.246F + 0.326F;
                armRight.rotateAngleZ = 0.845289F + this.simplifyAngle(time, 20.0F) * 0.946F;
            } else {
                armRight.rotateAngleX = this.simplifyAngle(time, 20.0F) * 1.246F + 0.326F;
                armRight.rotateAngleZ = 0.845289F + this.simplifyAngle(time, 20.0F) * 0.946F;
            }
        } else {
            if (hit > 0 && hit <= 11) {
                float hurt = hit - 1.0F;
                float scale = 0.223F;
                float rate = 4.0F;

                frontRibTL.rotateAngleY = -0.3386F + this.simplifyAngle(hurt, rate) * scale;
                frontRibTR.rotateAngleY = 0.344878F + this.simplifyAngle(hurt, rate) * scale;
                frontRibML.rotateAngleY = -0.3340967F + this.simplifyAngle(hurt, rate) * scale;
                frontRibMR.rotateAngleY = 0.3451081F + this.simplifyAngle(hurt, rate) * scale;
                frontRibBL.rotateAngleY = -0.402503F + this.simplifyAngle(hurt, rate) * scale;
                frontRibBR.rotateAngleY = 0.5461433F + this.simplifyAngle(hurt, rate) * scale;

                lowerJaw.rotateAngleX = this.simplifyAngle(hurt + 2.0F, rate) * scale;
                head.rotateAngleX = -0.223F;
                armLeft.rotateAngleX = this.simplifyAngle(hurt + 2.0F, rate) * scale;
                armRight.rotateAngleX = this.simplifyAngle(hurt + 2.0F, rate) * scale;
                scythe.rotateAngleX = this.simplifyAngle(hurt + 2.0F, rate) * scale;
            } else {
                head.rotateAngleY = f3 / (180F / (float) Math.PI);
                head.rotateAngleX = f4 / (180F / (float) Math.PI);

                armLeft.rotateAngleX = 0.55F * this.simplifyAngle(f, 13.0F) * f1;
                armRight.rotateAngleX = -0.55F * this.simplifyAngle(f, 13.0F) * f1;
            }
        }
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }
}
