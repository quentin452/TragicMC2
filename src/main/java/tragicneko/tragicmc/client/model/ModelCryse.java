package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.mob.EntityCryse;

public class ModelCryse extends ModelBase {

    private ModelRenderer body;
    private ModelRenderer bottom;
    private ModelRenderer top;
    private ModelRenderer left;
    private ModelRenderer right;
    private ModelRenderer topSemi;
    private ModelRenderer bottomSemi;
    private ModelRenderer leftSemi;
    private ModelRenderer rightSemi;
    private ModelRenderer tlBar;
    private ModelRenderer trBar;
    private ModelRenderer blBar;
    private ModelRenderer brBar;
    private ModelRenderer face;
    private ModelRenderer eyeRight;
    private ModelRenderer eyeLeft;

    private ModelRenderer[] bodyParts = new ModelRenderer[13];
    protected float[] barDefaults = new float[13];
    protected float[][] flutterArray = new float[][] {
        { 0.055F, 0.135F, 0.055F, 0.0F, -0.055F, -0.135F, -0.055F, 0.0F },
        { -0.055F, 0.0F, 0.055F, 0.135F, 0.055F, 0.0F, -0.055F, -0.135F },
        { -0.055F, -0.135F, -0.055F, 0.0F, 0.055F, 0.135F, 0.055F, 0.0F },
        { 0.055F, 0.0F, -0.055F, -0.135F, -0.055F, 0.0F, 0.055F, 0.135F },
        { 0.055F, 0.135F, 0.055F, 0.0F, -0.055F, -0.135F, -0.055F, 0.0F },
        { -0.055F, 0.0F, 0.055F, 0.135F, 0.055F, 0.0F, -0.055F, -0.135F } };

    public ModelCryse() {
        textureWidth = 64;
        textureHeight = 32;

        body = new ModelRenderer(this, 0, 0);
        body.addBox(-1F, -1F, -1.5F, 2, 2, 3);
        body.setRotationPoint(0F, 9F, 0F);
        top = new ModelRenderer(this, 0, 0);
        top.addBox(-1F, -7F, -1F, 2, 2, 2);
        body.addChild(top);
        bottom = new ModelRenderer(this, 0, 0);
        bottom.addBox(-1F, 5F, -1F, 2, 2, 2);
        body.addChild(bottom);
        left = new ModelRenderer(this, 0, 0);
        left.addBox(-6F, -1F, -1F, 2, 2, 2);
        body.addChild(left);
        right = new ModelRenderer(this, 0, 0);
        right.addBox(4F, -1F, -1F, 2, 2, 2);
        body.addChild(right);
        topSemi = new ModelRenderer(this, 0, 0);
        topSemi.addBox(-1F, -5F, -1F, 1, 4, 1);
        body.addChild(topSemi);
        bottomSemi = new ModelRenderer(this, 0, 0);
        bottomSemi.addBox(0F, 1F, 0F, 1, 4, 1);
        body.addChild(bottomSemi);
        leftSemi = new ModelRenderer(this, 0, 0);
        leftSemi.addBox(1F, -1F, 0F, 3, 1, 1);
        body.addChild(leftSemi);
        rightSemi = new ModelRenderer(this, 0, 0);
        rightSemi.addBox(-4F, 0F, -1F, 3, 1, 1);
        body.addChild(rightSemi);
        tlBar = new ModelRenderer(this, 0, 0);
        tlBar.addBox(-3F, -5F, -1F, 6, 1, 1);
        tlBar.rotateAngleZ = 0.7435722F;
        body.addChild(tlBar);
        trBar = new ModelRenderer(this, 0, 0);
        trBar.addBox(-3F, 4F, 0F, 6, 1, 1);
        trBar.rotateAngleZ = 2.379431F;
        body.addChild(trBar);
        brBar = new ModelRenderer(this, 0, 0);
        brBar.addBox(-3F, 4F, -1F, 6, 1, 1);
        brBar.rotateAngleZ = 0.7435722F;
        body.addChild(brBar);
        blBar = new ModelRenderer(this, 0, 0);
        blBar.addBox(-3F, -5F, 0F, 6, 1, 1);
        blBar.rotateAngleZ = 2.379431F;
        body.addChild(blBar);

        bodyParts = new ModelRenderer[] { body, bottom, left, right, top, topSemi, bottomSemi, leftSemi, rightSemi,
            tlBar, trBar, brBar, blBar };
        barDefaults = new float[] { 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7435722F, 2.379431F,
            0.7435722F, 2.379431F };

        face = new ModelRenderer(this, 0, 10);
        face.addBox(-2F, 2F, -2F, 2, 2, 1);
        face.setRotationPoint(0F, 9F, 0F);
        eyeRight = new ModelRenderer(this, 0, 10);
        eyeRight.addBox(-3F, -2F, -2F, 1, 1, 1);
        face.addChild(eyeRight);
        eyeLeft = new ModelRenderer(this, 0, 10);
        eyeLeft.addBox(2F, -3F, -2F, 1, 1, 1);
        face.addChild(eyeLeft);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        body.render(f5);
        face.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        if (!(entity instanceof EntityCryse)) return;

        EntityCryse cryse = (EntityCryse) entity;

        if (cryse.getAttackTime() > 0) {
            for (int i = 0; i < bodyParts.length; i++) {
                bodyParts[i].rotateAngleZ = barDefaults[i] + 0.065F * this.simplifyAngle(cryse.getAttackTime(), 7.5F);
            }

            face.offsetZ = 0.075F * this.simplifyAngle(cryse.getAttackTime(), 15.0F);
            face.offsetY = 0.075F * this.simplifyAngle(cryse.getAttackTime(), 15.0F);

            eyeRight.offsetZ = 0.375F * this.simplifyAngle(cryse.getAttackTime(), 15.0F);
            eyeRight.offsetY = -0.375F * this.simplifyAngle(cryse.getAttackTime(), 15.0F);

            eyeLeft.offsetZ = -0.375F * this.simplifyAngle(cryse.getAttackTime(), 15.0F);
            eyeLeft.offsetY = -0.375F * this.simplifyAngle(cryse.getAttackTime(), 15.0F);
        } else {
            eyeRight.offsetZ = eyeRight.offsetY = eyeLeft.offsetZ = eyeLeft.offsetY = 0.0F;
            eyeRight.offsetZ = eyeRight.offsetY = eyeLeft.offsetZ = eyeLeft.offsetY = 0.0F;

            for (int i = 0; i < bodyParts.length; i++) {
                bodyParts[i].offsetX = 0.0F;
                bodyParts[i].offsetY = 0.0F;
                bodyParts[i].offsetX = 0.0F;
            }

            body.offsetY = 0.0F;
            body.offsetZ = 0.0F;
            body.offsetX = 0.0F;
            face.offsetY = 0.0F;
            face.offsetX = 0.0F;
            face.offsetZ = 0.0F;

            if (cryse.isSpinning()) {
                if (cryse.getSpinTicks() > 0) // for the Mega Cryse
                {
                    for (int i = 0; i < bodyParts.length; i++) {
                        bodyParts[i].rotateAngleZ = barDefaults[i] + this.simplifyAngle(cryse.getSpinTicks(), 20.0F);
                    }

                    face.offsetY = 0.075F * this.simplifyAngle(cryse.getSpinTicks(), 60.0F);
                    eyeRight.offsetZ = 0.275F * this.simplifyAngle(cryse.getSpinTicks(), 60.0F);
                    eyeLeft.offsetZ = -0.275F * this.simplifyAngle(cryse.getSpinTicks(), 60.0F);
                }
            } else {
                for (int i = 0; i < bodyParts.length; i++) {
                    bodyParts[i].rotateAngleZ = barDefaults[i];
                }

                if (cryse.isFluttering()) {
                    int k = cryse.getFlutterTicks() % 16;
                    k /= 2;
                    float f0 = 0.115F;
                    tlBar.offsetY = flutterArray[0][k] * f0;
                    top.offsetY = flutterArray[0][k] * f0;
                    left.offsetY = flutterArray[0][k] * f0;
                    topSemi.offsetY = flutterArray[1][k] * f0;
                    leftSemi.offsetY = flutterArray[1][k] * f0;
                    trBar.offsetY = flutterArray[2][k] * f0;
                    blBar.offsetY = flutterArray[3][k] * f0;
                    bottomSemi.offsetY = flutterArray[3][k] * f0;
                    rightSemi.offsetY = flutterArray[3][k] * f0;
                    bottom.offsetY = flutterArray[4][k] * f0;
                    right.offsetY = flutterArray[4][k] * f0;
                    brBar.offsetY = flutterArray[4][k] * f0;

                    tlBar.offsetZ = flutterArray[0][k] * f0;
                    top.offsetZ = flutterArray[0][k] * f0;
                    left.offsetZ = flutterArray[0][k] * f0;
                    topSemi.offsetZ = flutterArray[1][k] * f0;
                    leftSemi.offsetZ = flutterArray[1][k] * f0;
                    trBar.offsetZ = flutterArray[2][k] * f0;
                    blBar.offsetZ = flutterArray[3][k] * f0;
                    bottomSemi.offsetZ = flutterArray[3][k] * f0;
                    rightSemi.offsetZ = flutterArray[3][k] * f0;
                    bottom.offsetZ = flutterArray[4][k] * f0;
                    right.offsetZ = flutterArray[4][k] * f0;
                    brBar.offsetZ = flutterArray[4][k] * f0;

                    face.offsetY = 0.075F * this.simplifyAngle(cryse.getSpinTicks(), 10.0F);
                    eyeRight.offsetX = 0.375F * this.simplifyAngle(cryse.getSpinTicks(), 10.0F);
                    eyeLeft.offsetX = -0.375F * this.simplifyAngle(cryse.getSpinTicks(), 10.0F);
                } else {
                    eyeRight.offsetZ = eyeRight.offsetY = eyeLeft.offsetZ = eyeLeft.offsetY = 0.0F;
                    eyeRight.offsetZ = eyeRight.offsetY = eyeLeft.offsetZ = eyeLeft.offsetY = 0.0F;

                    for (int i = 0; i < bodyParts.length; i++) {
                        bodyParts[i].offsetX = 0.0F;
                        bodyParts[i].offsetY = 0.0F;
                        bodyParts[i].offsetX = 0.0F;
                    }

                    body.offsetY = 0.0F;
                    body.offsetZ = 0.0F;
                    body.offsetX = 0.0F;
                    face.offsetY = 0.0F;
                    face.offsetX = 0.0F;
                    face.offsetZ = 0.0F;
                }

            }
        }
    }

    protected float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }
}
