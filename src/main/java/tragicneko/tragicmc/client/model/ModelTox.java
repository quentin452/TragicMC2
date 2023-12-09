package tragicneko.tragicmc.client.model;

import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

import tragicneko.tragicmc.entity.mob.EntityTox;

public class ModelTox extends ModelBase {

    private ModelRenderer bulb;
    private ModelRenderer root;
    private ModelRenderer root2;
    private ModelRenderer root3;
    private ModelRenderer root4;
    private ModelRenderer stem;
    private ModelRenderer leavesBase;
    private ModelRenderer mouthTop;
    private ModelRenderer mouthBottom;
    private ModelRenderer innerLeafTL;
    private ModelRenderer innerLeafTR;
    private ModelRenderer innerLeafBL;
    private ModelRenderer innerLeafBR;
    private ModelRenderer midLeafBottom;
    private ModelRenderer midLeafTop;
    private ModelRenderer midLeafLeft;
    private ModelRenderer midLeafRight;
    private ModelRenderer outerLeafTL;
    private ModelRenderer outerLeafBL;
    private ModelRenderer outerLeafBR;
    private ModelRenderer outerLeafTR;

    private ModelRenderer[] rootParts = new ModelRenderer[4];
    private ModelRenderer[] leafParts = new ModelRenderer[13];
    private float[][] leafDefaults = new float[13][3];

    public ModelTox() {
        textureWidth = 128;
        textureHeight = 64;

        // Full Bulb
        bulb = new ModelRenderer(this, 0, 0);
        bulb.addBox(-5F, 0F, -5F, 10, 6, 10);
        bulb.setRotationPoint(0F, 16F, 0F);
        ModelRenderer bulbTop = new ModelRenderer(this, 42, 0);
        bulbTop.addBox(-3F, -2F, -3F, 6, 2, 6);
        bulb.addChild(bulbTop);
        root = new ModelRenderer(this, 0, 50);
        root.addBox(3F, 4F, -7F, 4, 4, 4);
        bulb.addChild(root);
        root2 = new ModelRenderer(this, 0, 50);
        root2.addBox(-7F, 4F, -7F, 4, 4, 4);
        bulb.addChild(root2);
        root3 = new ModelRenderer(this, 0, 50);
        root3.addBox(-7F, 4F, 3F, 4, 4, 4);
        bulb.addChild(root3);
        root4 = new ModelRenderer(this, 0, 50);
        root4.addBox(3F, 4F, 3F, 4, 4, 4);
        bulb.addChild(root4);

        rootParts = new ModelRenderer[] { root, root2, root3, root4 };

        // Full Stem
        stem = new ModelRenderer(this, 0, 18);
        stem.addBox(-1F, -7F, -1F, 2, 6, 2);
        stem.setRotationPoint(0F, 16F, 0F);
        stem.rotateAngleX = -0.4089647F;
        ModelRenderer stemMiddle = new ModelRenderer(this, 0, 18);
        stemMiddle.addBox(-1F, -13F, 1F, 2, 6, 2);
        stemMiddle.rotateAngleX = 0.2974289F;
        stem.addChild(stemMiddle);
        ModelRenderer stemTop = new ModelRenderer(this, 0, 19);
        stemTop.addBox(-1F, -11F, 10F, 2, 5, 2);
        stemTop.rotateAngleX = 0.9294653F;
        stemMiddle.addChild(stemTop);

        // Each Leaf will be one main "part" but animated seperately
        leavesBase = new ModelRenderer(this, 0, 32);
        leavesBase.addBox(-2F, -2F, -1F, 4, 4, 1);
        leavesBase.setRotationPoint(0F, 2F, -1F);

        // Mouth
        mouthTop = new ModelRenderer(this, 70, 0);
        mouthTop.addBox(-1F, -2F, -6F, 2, 2, 5);
        leavesBase.addChild(mouthTop);
        mouthBottom = new ModelRenderer(this, 70, 10);
        mouthBottom.addBox(-1F, -1F, -5F, 2, 1, 4);
        mouthBottom.rotateAngleX = 0.3717861F;
        leavesBase.addChild(mouthBottom);

        // Inner Leaves
        innerLeafTL = new ModelRenderer(this, 86, 32);
        innerLeafTL.addBox(0F, -5F, -2F, 2, 4, 1);
        innerLeafTL.rotateAngleX = 0.4461433F;
        innerLeafTL.rotateAngleZ = 0.2974289F;
        leavesBase.addChild(innerLeafTL);
        innerLeafTR = new ModelRenderer(this, 76, 32);
        innerLeafTR.addBox(-2F, -5F, -2F, 2, 4, 1);
        innerLeafTR.rotateAngleX = 0.4461433F;
        innerLeafTR.rotateAngleZ = -0.2974289F;
        leavesBase.addChild(innerLeafTR);
        innerLeafBL = new ModelRenderer(this, 86, 40);
        innerLeafBL.addBox(0F, 2F, -2F, 2, 4, 1);
        innerLeafBL.rotateAngleX = -0.4461433F;
        innerLeafBL.rotateAngleZ = -0.3717861F;
        leavesBase.addChild(innerLeafBL);
        innerLeafBR = new ModelRenderer(this, 76, 40);
        innerLeafBR.addBox(-2F, 2F, -2F, 2, 4, 1);
        innerLeafBR.rotateAngleX = -0.4461433F;
        innerLeafBR.rotateAngleZ = 0.3717861F;
        leavesBase.addChild(innerLeafBR);

        // Mid Leaves
        midLeafBottom = new ModelRenderer(this, 54, 40);
        midLeafBottom.addBox(-2F, 1F, -1F, 4, 6, 1);
        midLeafBottom.rotateAngleX = -0.5205006F;
        leavesBase.addChild(midLeafBottom);
        midLeafTop = new ModelRenderer(this, 54, 22);
        midLeafTop.addBox(-2F, -7F, -1F, 4, 6, 1);
        midLeafTop.rotateAngleX = 0.5205006F;
        leavesBase.addChild(midLeafTop);
        midLeafLeft = new ModelRenderer(this, 60, 32);
        midLeafLeft.addBox(2F, -2F, 0F, 6, 4, 1);
        midLeafLeft.rotateAngleY = 0.668215F;
        leavesBase.addChild(midLeafLeft);
        midLeafRight = new ModelRenderer(this, 44, 32);
        midLeafRight.addBox(-8F, -2F, 0F, 6, 4, 1);
        midLeafRight.rotateAngleY = -0.668215F;
        leavesBase.addChild(midLeafRight);

        // Outer Leaves
        outerLeafTL = new ModelRenderer(this, 28, 18);
        outerLeafTL.addBox(-3F, -10F, 0F, 6, 10, 1);
        outerLeafTL.rotateAngleX = 0.11153585F;
        outerLeafTL.rotateAngleZ = 0.7807408F;
        leavesBase.addChild(outerLeafTL);
        outerLeafBL = new ModelRenderer(this, 28, 32);
        outerLeafBL.addBox(-3F, 0F, 0F, 6, 10, 1);
        outerLeafBL.rotateAngleX = -0.2230717F;
        outerLeafBL.rotateAngleZ = -0.7807408F;
        leavesBase.addChild(outerLeafBL);
        outerLeafBR = new ModelRenderer(this, 12, 18);
        outerLeafBR.addBox(-3F, 0F, 0F, 6, 10, 1);
        outerLeafBR.rotateAngleX = -0.2230717F;
        outerLeafBR.rotateAngleZ = 0.7807408F;
        leavesBase.addChild(outerLeafBR);
        outerLeafTR = new ModelRenderer(this, 12, 32);
        outerLeafTR.addBox(-3F, -10F, 0F, 6, 10, 1);
        outerLeafTR.rotateAngleX = 0.11153585F;
        outerLeafTR.rotateAngleZ = -0.7807408F;
        leavesBase.addChild(outerLeafTR);

        leafParts = new ModelRenderer[] { leavesBase, innerLeafTL, innerLeafTR, innerLeafBL, innerLeafBR, midLeafBottom,
            midLeafTop, midLeafLeft, midLeafRight, outerLeafTL, outerLeafTR, outerLeafBL, outerLeafBR };
        leafDefaults = new float[][] { { 0.0F, 0.0F, 0.0F }, { 0.44161433F, 0.0F, 0.2974289F },
            { 0.44161433F, 0.0F, -0.2974289F }, { -0.44161433F, 0.0F, -0.3717861F }, { -0.44161433F, 0.0F, 0.3717861F },
            { -0.5205006F, 0.0F, 0.0F }, { 0.5205006F, 0.0F, 0.0F }, { 0.0F, 0.668215F, 0.0F },
            { 0.0F, -0.668215F, 0.0F }, { 0.11153585F, 0.0F, 0.7807408F }, { 0.11153585F, 0.0F, -0.7807408F },
            { -0.2230717F, 0.0F, 0.7807408F }, { -0.2230717F, 0.0F, -0.7807408F } };
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        bulb.render(f5);
        stem.render(f5);
        leavesBase.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        this.leavesBase.rotateAngleY = f3 / (180F / (float) Math.PI);
        this.leavesBase.rotateAngleX = f4 / (180F / (float) Math.PI);
    }

    @Override
    public void setLivingAnimations(EntityLivingBase entity, float par2, float par3, float par4) {
        if (!(entity instanceof EntityTox)) return;

        EntityTox tox = (EntityTox) entity;
        Random rand = tox.worldObj.rand;

        if (tox.getWiggleTime() > 0) {
            for (int i = 0; i < rootParts.length; i++) {
                rootParts[i].rotateAngleX = 0.065F
                    * (this.simplifyAngle(tox.ticksExisted, 7.5F) + rand.nextFloat() - rand.nextFloat());
                rootParts[i].rotateAngleZ = 0.065F
                    * (this.simplifyAngle(tox.ticksExisted, 7.5F) + rand.nextFloat() - rand.nextFloat());
            }

            mouthBottom.rotateAngleX = 0.3717861F + 0.1225F * this.simplifyAngle(tox.ticksExisted, 7.5F);

            for (int i = 0; i < leafParts.length; i++) {
                leafParts[i].rotateAngleZ = leafDefaults[i][2] + 0.065F * this.simplifyAngle(tox.ticksExisted, 12.0F);
            }
        } else {
            if (tox.getAttackTime() > 0) {
                for (int i = 0; i < rootParts.length; i++) {
                    rootParts[i].rotateAngleX = 0.065F
                        * (this.simplifyAngle(tox.getAttackTime(), 2.5F) + rand.nextFloat() - rand.nextFloat());
                    rootParts[i].rotateAngleZ = 0.065F
                        * (this.simplifyAngle(tox.getAttackTime(), 2.5F) + rand.nextFloat() - rand.nextFloat());
                }

                for (int i = 0; i < leafParts.length; i++) {
                    leafParts[i].rotateAngleX = leafDefaults[i][0]
                        + 0.465F * this.simplifyAngle(tox.getAttackTime(), 5.0F);
                    leafParts[i].rotateAngleY = leafDefaults[i][1];
                    leafParts[i].rotateAngleZ = leafDefaults[i][2];
                }

                mouthTop.rotateAngleX = 0.465F * this.simplifyAngle(tox.getAttackTime(), 5.0F);
                mouthBottom.rotateAngleX = 0.3717861F + 0.465F * this.simplifyAngle(tox.getAttackTime(), 5.0F);

            } else {
                for (int i = 0; i < rootParts.length; i++) {
                    rootParts[i].rotateAngleX = 0.0F;
                    rootParts[i].rotateAngleZ = 0.0F;
                }

                if (tox.isFiring()) {
                    mouthBottom.rotateAngleX = 0.6725F;
                    for (int i = 0; i < leafParts.length; i++) {
                        leafParts[i].rotateAngleX = leafDefaults[i][0] + 0.065F
                            * (this.simplifyAngle(tox.getAttackTime(), 15.0F) + rand.nextFloat() - rand.nextFloat());
                        leafParts[i].rotateAngleY = leafDefaults[i][1] + 0.065F
                            * (this.simplifyAngle(tox.getAttackTime(), 15.0F) + rand.nextFloat() - rand.nextFloat());
                        leafParts[i].rotateAngleZ = leafDefaults[i][2] + 0.065F
                            * (this.simplifyAngle(tox.getAttackTime(), 15.0F) + rand.nextFloat() - rand.nextFloat());
                    }
                } else {
                    mouthBottom.rotateAngleX = 0.3717861F + 0.065F * this.simplifyAngle(tox.ticksExisted, 35.0F);

                    for (int i = 0; i < leafParts.length; i++) {
                        leafParts[i].rotateAngleX = (leafDefaults[i][0] * 1.15F)
                            + 0.065F * this.simplifyAngle(tox.ticksExisted, 30.0F);
                        leafParts[i].rotateAngleY = (leafDefaults[i][1] * 1.15F)
                            + 0.065F * this.simplifyAngle(tox.ticksExisted, 30.0F);
                        leafParts[i].rotateAngleZ = leafDefaults[i][2];
                    }
                }
            }
        }
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }
}
