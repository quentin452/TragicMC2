package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.miniboss.EntityMegaCryse;

public class ModelMegaCryse extends ModelCryse {

    private ModelRenderer body2;
    private ModelRenderer bottom2;
    private ModelRenderer top2;
    private ModelRenderer left2;
    private ModelRenderer right2;
    private ModelRenderer topSemi2;
    private ModelRenderer bottomSemi2;
    private ModelRenderer leftSemi2;
    private ModelRenderer rightSemi2;
    private ModelRenderer tlBar2;
    private ModelRenderer trBar2;
    private ModelRenderer blBar2;
    private ModelRenderer brBar2;
    private ModelRenderer face2;
    private ModelRenderer eyeRight2;
    private ModelRenderer eyeLeft2;

    private ModelRenderer body3;
    private ModelRenderer bottom3;
    private ModelRenderer top3;
    private ModelRenderer left3;
    private ModelRenderer right3;
    private ModelRenderer topSemi3;
    private ModelRenderer bottomSemi3;
    private ModelRenderer leftSemi3;
    private ModelRenderer rightSemi3;
    private ModelRenderer tlBar3;
    private ModelRenderer trBar3;
    private ModelRenderer blBar3;
    private ModelRenderer brBar3;
    private ModelRenderer face3;
    private ModelRenderer eyeRight3;
    private ModelRenderer eyeLeft3;

    private ModelRenderer[] bodyParts2 = new ModelRenderer[13];
    private ModelRenderer[] bodyParts3 = new ModelRenderer[13];

    private ModelRenderer shield;
    private ModelRenderer shield2;
    private ModelRenderer shield3;
    private ModelRenderer shield4;

    public ModelMegaCryse() {
        super();

        body2 = new ModelRenderer(this, 0, 0);
        body2.addBox(-1F, -1F, -1.5F, 2, 2, 3);
        body2.setRotationPoint(0F, 9F, 0F);
        top2 = new ModelRenderer(this, 0, 0);
        top2.addBox(-1F, -7F, -1F, 2, 2, 2);
        body2.addChild(top2);
        bottom2 = new ModelRenderer(this, 0, 0);
        bottom2.addBox(-1F, 5F, -1F, 2, 2, 2);
        body2.addChild(bottom2);
        left2 = new ModelRenderer(this, 0, 0);
        left2.addBox(-6F, -1F, -1F, 2, 2, 2);
        body2.addChild(left2);
        right2 = new ModelRenderer(this, 0, 0);
        right2.addBox(4F, -1F, -1F, 2, 2, 2);
        body2.addChild(right2);
        topSemi2 = new ModelRenderer(this, 0, 0);
        topSemi2.addBox(-1F, -5F, -1F, 1, 4, 1);
        body2.addChild(topSemi2);
        bottomSemi2 = new ModelRenderer(this, 0, 0);
        bottomSemi2.addBox(0F, 1F, 0F, 1, 4, 1);
        body2.addChild(bottomSemi2);
        leftSemi2 = new ModelRenderer(this, 0, 0);
        leftSemi2.addBox(1F, -1F, 0F, 3, 1, 1);
        body2.addChild(leftSemi2);
        rightSemi2 = new ModelRenderer(this, 0, 0);
        rightSemi2.addBox(-4F, 0F, -1F, 3, 1, 1);
        body2.addChild(rightSemi2);
        tlBar2 = new ModelRenderer(this, 0, 0);
        tlBar2.addBox(-3F, -5F, -1F, 6, 1, 1);
        tlBar2.rotateAngleZ = 0.7435722F;
        body2.addChild(tlBar2);
        trBar2 = new ModelRenderer(this, 0, 0);
        trBar2.addBox(-3F, 4F, 0F, 6, 1, 1);
        trBar2.rotateAngleZ = 2.379431F;
        body2.addChild(trBar2);
        brBar2 = new ModelRenderer(this, 0, 0);
        brBar2.addBox(-3F, 4F, -1F, 6, 1, 1);
        brBar2.rotateAngleZ = 0.7435722F;
        body2.addChild(brBar2);
        blBar2 = new ModelRenderer(this, 0, 0);
        blBar2.addBox(-3F, -5F, 0F, 6, 1, 1);
        blBar2.rotateAngleZ = 2.379431F;
        body2.addChild(blBar2);

        bodyParts2 = new ModelRenderer[] { body2, bottom2, left2, right2, top2, topSemi2, bottomSemi2, leftSemi2,
            rightSemi2, tlBar2, trBar2, brBar2, blBar2 };

        face2 = new ModelRenderer(this, 0, 10);
        face2.addBox(-2F, 2F, -2F, 2, 2, 1);
        face2.setRotationPoint(0F, 9F, 0F);
        eyeRight2 = new ModelRenderer(this, 0, 10);
        eyeRight2.addBox(-3F, -2F, -2F, 1, 1, 1);
        face2.addChild(eyeRight2);
        eyeLeft2 = new ModelRenderer(this, 0, 10);
        eyeLeft2.addBox(2F, -3F, -2F, 1, 1, 1);
        face2.addChild(eyeLeft2);

        body3 = new ModelRenderer(this, 0, 0);
        body3.addBox(-1F, -1F, -1.5F, 2, 2, 3);
        body3.setRotationPoint(0F, 9F, 0F);
        top3 = new ModelRenderer(this, 0, 0);
        top3.addBox(-1F, -7F, -1F, 2, 2, 2);
        body3.addChild(top3);
        bottom3 = new ModelRenderer(this, 0, 0);
        bottom3.addBox(-1F, 5F, -1F, 2, 2, 2);
        body3.addChild(bottom3);
        left3 = new ModelRenderer(this, 0, 0);
        left3.addBox(-6F, -1F, -1F, 2, 2, 2);
        body3.addChild(left3);
        right3 = new ModelRenderer(this, 0, 0);
        right3.addBox(4F, -1F, -1F, 2, 2, 2);
        body3.addChild(right3);
        topSemi3 = new ModelRenderer(this, 0, 0);
        topSemi3.addBox(-1F, -5F, -1F, 1, 4, 1);
        body3.addChild(topSemi3);
        bottomSemi3 = new ModelRenderer(this, 0, 0);
        bottomSemi3.addBox(0F, 1F, 0F, 1, 4, 1);
        body3.addChild(bottomSemi3);
        leftSemi3 = new ModelRenderer(this, 0, 0);
        leftSemi3.addBox(1F, -1F, 0F, 3, 1, 1);
        body3.addChild(leftSemi3);
        rightSemi3 = new ModelRenderer(this, 0, 0);
        rightSemi3.addBox(-4F, 0F, -1F, 3, 1, 1);
        body3.addChild(rightSemi3);
        tlBar3 = new ModelRenderer(this, 0, 0);
        tlBar3.addBox(-3F, -5F, -1F, 6, 1, 1);
        tlBar3.rotateAngleZ = 0.7435722F;
        body3.addChild(tlBar3);
        trBar3 = new ModelRenderer(this, 0, 0);
        trBar3.addBox(-3F, 4F, 0F, 6, 1, 1);
        trBar3.rotateAngleZ = 2.379431F;
        body3.addChild(trBar3);
        brBar3 = new ModelRenderer(this, 0, 0);
        brBar3.addBox(-3F, 4F, -1F, 6, 1, 1);
        brBar3.rotateAngleZ = 0.7435722F;
        body3.addChild(brBar3);
        blBar3 = new ModelRenderer(this, 0, 0);
        blBar3.addBox(-3F, -5F, 0F, 6, 1, 1);
        blBar3.rotateAngleZ = 2.379431F;
        body3.addChild(blBar3);

        bodyParts3 = new ModelRenderer[] { body3, bottom3, left3, right3, top3, topSemi3, bottomSemi3, leftSemi3,
            rightSemi3, tlBar3, trBar3, brBar3, blBar3 };

        face3 = new ModelRenderer(this, 0, 10);
        face3.addBox(-2F, 2F, -2F, 2, 2, 1);
        face3.setRotationPoint(0F, 9F, 0F);
        eyeRight3 = new ModelRenderer(this, 0, 10);
        eyeRight3.addBox(-3F, -2F, -2F, 1, 1, 1);
        face3.addChild(eyeRight3);
        eyeLeft3 = new ModelRenderer(this, 0, 10);
        eyeLeft3.addBox(2F, -3F, -2F, 1, 1, 1);
        face3.addChild(eyeLeft3);

        shield = new ModelRenderer(this, 0, 0);
        shield.addBox(-1.5F, -3F, -11.5F, 3, 6, 1);
        shield.setRotationPoint(0F, 9F, 0F);
        shield2 = new ModelRenderer(this, 0, 0);
        shield2.addBox(-1.5F, -3F, -11.5F, 3, 6, 1);
        shield2.setRotationPoint(0F, 9F, 0F);
        shield3 = new ModelRenderer(this, 0, 0);
        shield3.addBox(-1.5F, -3F, -11.5F, 3, 6, 1);
        shield3.setRotationPoint(0F, 9F, 0F);
        shield4 = new ModelRenderer(this, 0, 0);
        shield4.addBox(-1.5F, -3F, -11.5F, 3, 6, 1);
        shield4.setRotationPoint(0F, 9F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        body2.render(f5);
        face2.render(f5);
        body3.render(f5);
        face3.render(f5);

        if (!(entity instanceof EntityMegaCryse)) return; // for the mob statues

        EntityMegaCryse cryse = (EntityMegaCryse) entity;

        if (cryse.getShields() > 0) {
            shield.render(f5);
            if (cryse.getShields() > 1) {
                shield2.render(f5);

                if (cryse.getShields() > 2) {
                    shield3.render(f5);

                    if (cryse.getShields() > 3) shield4.render(f5);
                }
            }
        }
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        float defX = 0.45F;
        float defZ = 0.15F;
        float defY = -0.65F;

        float def2X = -0.35F;
        float def2Z = -0.15F;
        float def2Y = -0.75F;

        if (!(entity instanceof EntityMegaCryse)) {
            defX = 0.225F;
            defZ = 0.075F;
            defY = -0.325F;

            def2X = -0.175F;
            def2Z = -0.075F;
            def2Y = -0.375F;

            eyeRight2.offsetZ = eyeRight2.offsetY = eyeLeft2.offsetZ = eyeLeft2.offsetY = 0.0F;
            eyeRight3.offsetZ = eyeRight3.offsetY = eyeLeft3.offsetZ = eyeLeft3.offsetY = 0.0F;

            for (int i = 0; i < bodyParts2.length; i++) {
                bodyParts2[i].offsetX = 0.0F;
                bodyParts2[i].offsetY = 0.0F;
                bodyParts2[i].offsetX = 0.0F;
            }

            for (int i = 0; i < bodyParts3.length; i++) {
                bodyParts3[i].offsetX = 0.0F;
                bodyParts3[i].offsetY = 0.0F;
                bodyParts3[i].offsetX = 0.0F;
            }

            body2.offsetY = defY;
            body2.offsetZ = defZ;
            body2.offsetX = defX;
            face2.offsetY = defY;
            face2.offsetX = defX;
            face2.offsetZ = defZ;

            body3.offsetY = def2Y;
            body3.offsetZ = def2Z;
            body3.offsetX = def2X;
            face3.offsetY = def2Y;
            face3.offsetX = def2X;
            face3.offsetZ = def2Z;

            return; // for the mob statues
        }

        EntityMegaCryse cryse = (EntityMegaCryse) entity;

        shield.offsetY = -0.45F + 0.35F * this.simplifyAngle(cryse.ticksExisted + 13, 120.0F);
        shield2.offsetY = -0.45F + 0.35F * this.simplifyAngle(cryse.ticksExisted + 97, 120.0F);
        shield3.offsetY = -0.45F + 0.35F * this.simplifyAngle(cryse.ticksExisted - 7, 120.0F);
        shield4.offsetY = -0.45F + 0.35F * this.simplifyAngle(cryse.ticksExisted - 79, 120.0F);

        shield.rotateAngleY = 0.15F + 3.0F * this.simplifyAngle(cryse.ticksExisted + 33, 60.0F);
        shield2.rotateAngleY = -0.35F + 3.0F * this.simplifyAngle(cryse.ticksExisted - 27, 60.0F);
        shield3.rotateAngleY = 0.65F + 3.0F * this.simplifyAngle(cryse.ticksExisted + 57, 60.0F);
        shield4.rotateAngleY = -0.85F + 3.0F * this.simplifyAngle(cryse.ticksExisted - 3, 60.0F);

        if (cryse.getAttackTime() > 0) {
            for (int i = 0; i < bodyParts2.length; i++) {
                bodyParts2[i].rotateAngleZ = barDefaults[i] + 0.065F * this.simplifyAngle(cryse.getAttackTime(), 7.5F);
            }

            for (int i = 0; i < bodyParts3.length; i++) {
                bodyParts3[i].rotateAngleZ = barDefaults[i] + 0.065F * this.simplifyAngle(cryse.getAttackTime(), 7.5F);
            }

            face2.offsetZ = defZ + 0.075F * this.simplifyAngle(cryse.getAttackTime(), 15.0F);
            face2.offsetY = defY + 0.075F * this.simplifyAngle(cryse.getAttackTime(), 15.0F);
            face3.offsetZ = def2Z + 0.075F * this.simplifyAngle(cryse.getAttackTime(), 15.0F);
            face3.offsetY = def2Y + 0.075F * this.simplifyAngle(cryse.getAttackTime(), 15.0F);

            eyeRight2.offsetZ = 0.375F * this.simplifyAngle(cryse.getAttackTime(), 15.0F);
            eyeRight2.offsetY = -0.375F * this.simplifyAngle(cryse.getAttackTime(), 15.0F);
            eyeRight3.offsetZ = 0.375F * this.simplifyAngle(cryse.getAttackTime(), 15.0F);
            eyeRight3.offsetY = -0.375F * this.simplifyAngle(cryse.getAttackTime(), 15.0F);

            eyeLeft2.offsetZ = -0.375F * this.simplifyAngle(cryse.getAttackTime(), 15.0F);
            eyeLeft2.offsetY = -0.375F * this.simplifyAngle(cryse.getAttackTime(), 15.0F);
            eyeLeft3.offsetZ = -0.375F * this.simplifyAngle(cryse.getAttackTime(), 15.0F);
            eyeLeft3.offsetY = -0.375F * this.simplifyAngle(cryse.getAttackTime(), 15.0F);
        } else {
            eyeRight2.offsetZ = eyeRight2.offsetY = eyeLeft2.offsetZ = eyeLeft2.offsetY = 0.0F;
            eyeRight3.offsetZ = eyeRight3.offsetY = eyeLeft3.offsetZ = eyeLeft3.offsetY = 0.0F;

            for (int i = 0; i < bodyParts2.length; i++) {
                bodyParts2[i].offsetX = 0.0F;
                bodyParts2[i].offsetY = 0.0F;
                bodyParts2[i].offsetX = 0.0F;
            }

            for (int i = 0; i < bodyParts3.length; i++) {
                bodyParts3[i].offsetX = 0.0F;
                bodyParts3[i].offsetY = 0.0F;
                bodyParts3[i].offsetX = 0.0F;
            }

            body2.offsetY = defY;
            body2.offsetZ = defZ;
            body2.offsetX = defX;
            face2.offsetY = defY;
            face2.offsetX = defX;
            face2.offsetZ = defZ;

            body3.offsetY = def2Y;
            body3.offsetZ = def2Z;
            body3.offsetX = def2X;
            face3.offsetY = def2Y;
            face3.offsetX = def2X;
            face3.offsetZ = def2Z;

            if (cryse.isSpinning()) {
                if (cryse.getSpinTicks2() > 0) {
                    for (int i = 0; i < bodyParts2.length; i++) {
                        bodyParts2[i].rotateAngleZ = barDefaults[i] + this.simplifyAngle(cryse.getSpinTicks2(), 20.0F);
                    }

                    face2.offsetY = defY + 0.075F * this.simplifyAngle(cryse.getSpinTicks2(), 60.0F);
                    eyeRight2.offsetZ = 0.275F * this.simplifyAngle(cryse.getSpinTicks2(), 60.0F);
                    eyeLeft2.offsetZ = -0.275F * this.simplifyAngle(cryse.getSpinTicks2(), 60.0F);
                }

                if (cryse.getSpinTicks3() > 0) {
                    for (int i = 0; i < bodyParts3.length; i++) {
                        bodyParts3[i].rotateAngleZ = barDefaults[i] + this.simplifyAngle(cryse.getSpinTicks3(), 20.0F);
                    }

                    face3.offsetY = def2Y + 0.075F * this.simplifyAngle(cryse.getSpinTicks3(), 60.0F);
                    eyeRight3.offsetZ = 0.275F * this.simplifyAngle(cryse.getSpinTicks3(), 60.0F);
                    eyeLeft3.offsetZ = -0.275F * this.simplifyAngle(cryse.getSpinTicks3(), 60.0F);
                }
            } else {
                for (int i = 0; i < bodyParts2.length; i++) {
                    bodyParts2[i].rotateAngleZ = barDefaults[i];
                }

                for (int i = 0; i < bodyParts3.length; i++) {
                    bodyParts3[i].rotateAngleZ = barDefaults[i];
                }

                if (cryse.isFluttering()) {
                    int k = cryse.getFlutterTicks() % 16;
                    k /= 2;
                    float f0 = 0.115F;
                    tlBar2.offsetY = flutterArray[0][k] * f0;
                    top2.offsetY = flutterArray[0][k] * f0;
                    left2.offsetY = flutterArray[0][k] * f0;
                    topSemi2.offsetY = flutterArray[1][k] * f0;
                    leftSemi2.offsetY = flutterArray[1][k] * f0;
                    trBar2.offsetY = flutterArray[2][k] * f0;
                    blBar2.offsetY = flutterArray[3][k] * f0;
                    bottomSemi2.offsetY = flutterArray[3][k] * f0;
                    rightSemi2.offsetY = flutterArray[3][k] * f0;
                    bottom2.offsetY = flutterArray[4][k] * f0;
                    right2.offsetY = flutterArray[4][k] * f0;
                    brBar2.offsetY = flutterArray[4][k] * f0;

                    tlBar2.offsetZ = flutterArray[0][k] * f0;
                    top2.offsetZ = flutterArray[0][k] * f0;
                    left2.offsetZ = flutterArray[0][k] * f0;
                    topSemi2.offsetZ = flutterArray[1][k] * f0;
                    leftSemi2.offsetZ = flutterArray[1][k] * f0;
                    trBar2.offsetZ = flutterArray[2][k] * f0;
                    blBar2.offsetZ = flutterArray[3][k] * f0;
                    bottomSemi2.offsetZ = flutterArray[3][k] * f0;
                    rightSemi2.offsetZ = flutterArray[3][k] * f0;
                    bottom2.offsetZ = flutterArray[4][k] * f0;
                    right2.offsetZ = flutterArray[4][k] * f0;
                    brBar2.offsetZ = flutterArray[4][k] * f0;

                    tlBar3.offsetY = flutterArray[0][k] * f0;
                    top3.offsetY = flutterArray[0][k] * f0;
                    left3.offsetY = flutterArray[0][k] * f0;
                    topSemi3.offsetY = flutterArray[1][k] * f0;
                    leftSemi3.offsetY = flutterArray[1][k] * f0;
                    trBar3.offsetY = flutterArray[2][k] * f0;
                    blBar3.offsetY = flutterArray[3][k] * f0;
                    bottomSemi3.offsetY = flutterArray[3][k] * f0;
                    rightSemi3.offsetY = flutterArray[3][k] * f0;
                    bottom3.offsetY = flutterArray[4][k] * f0;
                    right3.offsetY = flutterArray[4][k] * f0;
                    brBar3.offsetY = flutterArray[4][k] * f0;

                    tlBar3.offsetZ = flutterArray[0][k] * f0;
                    top3.offsetZ = flutterArray[0][k] * f0;
                    left3.offsetZ = flutterArray[0][k] * f0;
                    topSemi3.offsetZ = flutterArray[1][k] * f0;
                    leftSemi3.offsetZ = flutterArray[1][k] * f0;
                    trBar3.offsetZ = flutterArray[2][k] * f0;
                    blBar3.offsetZ = flutterArray[3][k] * f0;
                    bottomSemi3.offsetZ = flutterArray[3][k] * f0;
                    rightSemi3.offsetZ = flutterArray[3][k] * f0;
                    bottom3.offsetZ = flutterArray[4][k] * f0;
                    right3.offsetZ = flutterArray[4][k] * f0;
                    brBar3.offsetZ = flutterArray[4][k] * f0;

                    face2.offsetY = defY + 0.075F * this.simplifyAngle(cryse.getSpinTicks2(), 10.0F);
                    eyeRight2.offsetX = 0.375F * this.simplifyAngle(cryse.getSpinTicks2(), 10.0F);
                    eyeLeft2.offsetX = -0.375F * this.simplifyAngle(cryse.getSpinTicks2(), 10.0F);

                    face3.offsetY = def2Y + 0.075F * this.simplifyAngle(cryse.getSpinTicks3(), 10.0F);
                    eyeRight3.offsetX = 0.375F * this.simplifyAngle(cryse.getSpinTicks3(), 10.0F);
                    eyeLeft3.offsetX = -0.375F * this.simplifyAngle(cryse.getSpinTicks3(), 10.0F);
                } else {
                    eyeRight2.offsetZ = eyeRight2.offsetY = eyeLeft2.offsetZ = eyeLeft2.offsetY = 0.0F;
                    eyeRight3.offsetZ = eyeRight3.offsetY = eyeLeft3.offsetZ = eyeLeft3.offsetY = 0.0F;

                    for (int i = 0; i < bodyParts2.length; i++) {
                        bodyParts2[i].offsetX = 0.0F;
                        bodyParts2[i].offsetY = 0.0F;
                        bodyParts2[i].offsetX = 0.0F;
                    }

                    for (int i = 0; i < bodyParts3.length; i++) {
                        bodyParts3[i].offsetX = 0.0F;
                        bodyParts3[i].offsetY = 0.0F;
                        bodyParts3[i].offsetX = 0.0F;
                    }

                    body2.offsetY = defY;
                    body2.offsetZ = defZ;
                    body2.offsetX = defX;
                    face2.offsetY = defY;
                    face2.offsetX = defX;
                    face2.offsetZ = defZ;

                    body3.offsetY = def2Y;
                    body3.offsetZ = def2Z;
                    body3.offsetX = def2X;
                    face3.offsetY = def2Y;
                    face3.offsetX = def2X;
                    face3.offsetZ = def2Z;
                }

            }
        }
    }

}
