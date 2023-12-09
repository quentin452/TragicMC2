package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.boss.EntityClaymation;
import tragicneko.tragicmc.entity.mob.EntityNorVox;

public class ModelNorVox extends ModelBase {

    protected ModelRenderer head;
    protected ModelRenderer jaw;

    public ModelNorVox() {
        textureWidth = 64;
        textureHeight = 32;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-7F, -5F, -5F, 14, 6, 7);
        head.setRotationPoint(0F, 14F, 2F);
        ModelRenderer noseBridge = new ModelRenderer(this, 50, 0);
        noseBridge.addBox(-1F, -4F, -6F, 2, 6, 1);
        head.addChild(noseBridge);
        ModelRenderer leftEyeInner = new ModelRenderer(this, 0, 20);
        leftEyeInner.addBox(1F, -4F, -7F, 1, 4, 2);
        head.addChild(leftEyeInner);
        ModelRenderer rightEyeInner = new ModelRenderer(this, 0, 19);
        rightEyeInner.addBox(-2F, -4F, -7F, 1, 4, 2);
        head.addChild(rightEyeInner);
        ModelRenderer headTop = new ModelRenderer(this, 37, 8);
        headTop.addBox(-4F, -7F, -5F, 8, 2, 6);
        head.addChild(headTop);
        ModelRenderer leftEyeLower = new ModelRenderer(this, 0, 22);
        leftEyeLower.addBox(2F, -1F, -7F, 4, 1, 2);
        head.addChild(leftEyeLower);
        ModelRenderer leftEyeUpper = new ModelRenderer(this, 0, 22);
        leftEyeUpper.addBox(2F, -4F, -7F, 4, 1, 2);
        head.addChild(leftEyeUpper);
        ModelRenderer leftEyeOuter = new ModelRenderer(this, 0, 21);
        leftEyeOuter.addBox(5F, -3F, -7F, 1, 2, 2);
        head.addChild(leftEyeOuter);
        ModelRenderer rightEyeLower = new ModelRenderer(this, 0, 22);
        rightEyeLower.addBox(-6F, -1F, -7F, 4, 1, 2);
        head.addChild(rightEyeLower);
        ModelRenderer rightEyeUpper = new ModelRenderer(this, 0, 22);
        rightEyeUpper.addBox(-6F, -4F, -7F, 4, 1, 2);
        head.addChild(rightEyeUpper);
        ModelRenderer rightEyeOuter = new ModelRenderer(this, 0, 20);
        rightEyeOuter.addBox(-6F, -3F, -7F, 1, 2, 2);
        head.addChild(rightEyeOuter);
        ModelRenderer noseForehead = new ModelRenderer(this, 45, 0);
        noseForehead.addBox(-3F, -6F, -6F, 6, 2, 1);
        head.addChild(noseForehead);
        ModelRenderer noseBottom = new ModelRenderer(this, 45, 0);
        noseBottom.addBox(-3F, 2F, -6F, 6, 1, 1);
        head.addChild(noseBottom);
        ModelRenderer leftNostrilUpper = new ModelRenderer(this, 48, 0);
        leftNostrilUpper.addBox(1F, 0F, -6F, 2, 1, 1);
        head.addChild(leftNostrilUpper);
        ModelRenderer rightNostrilUpper = new ModelRenderer(this, 50, 0);
        rightNostrilUpper.addBox(-3F, 0F, -6F, 2, 1, 1);
        head.addChild(rightNostrilUpper);
        ModelRenderer leftNostrilOuter = new ModelRenderer(this, 51, 0);
        leftNostrilOuter.addBox(2F, 1F, -6F, 1, 1, 1);
        head.addChild(leftNostrilOuter);
        ModelRenderer rightNostrilOuter = new ModelRenderer(this, 50, 0);
        rightNostrilOuter.addBox(-3F, 1F, -6F, 1, 1, 1);
        head.addChild(rightNostrilOuter);
        ModelRenderer upperJaw = new ModelRenderer(this, 13, 16);
        upperJaw.addBox(-4F, 1F, -5F, 8, 3, 6);
        head.addChild(upperJaw);
        ModelRenderer backJaw = new ModelRenderer(this, 42, 18);
        backJaw.addBox(-4F, 4F, -3F, 8, 2, 3);
        head.addChild(backJaw);

        jaw = new ModelRenderer(this, 23, 28);
        jaw.addBox(-3F, 4F, -4F, 6, 2, 1);
        head.addChild(jaw);
        ModelRenderer lowerJaw = new ModelRenderer(this, 42, 24);
        lowerJaw.addBox(-3F, 6F, -5F, 6, 2, 4);
        jaw.addChild(lowerJaw);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        head.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        if (!(entity instanceof EntityNorVox) && !(entity instanceof EntityClaymation)) return;

        int firing = 0;
        int nod = 0;
        int attack = 0;

        if (entity instanceof EntityNorVox) {
            EntityNorVox vox = (EntityNorVox) entity;
            firing = vox.getFiringTicks();
            nod = vox.getNodTicks();
            attack = vox.getAttackTime();
        } else {
            EntityClaymation clay = (EntityClaymation) entity;
            firing = clay.getUtilityInt();
            attack = clay.getUtilityInt2();
        }

        if (firing >= 40 || nod > 0 || attack > 0) {
            this.jaw.offsetY = this.simplifyAngle(entity.ticksExisted, 0.35F) * 0.1F;
        } else {
            this.jaw.offsetY = 0.0F;
        }

        this.head.rotateAngleY = f3 / (180F / (float) Math.PI);

        if (nod > 0) {
            this.head.rotateAngleX = this.simplifyAngle(entity.ticksExisted, 10.0F) * 0.25F
                + f4 / (180F / (float) Math.PI);
        } else {
            if (attack > 0) {
                this.head.rotateAngleX = this.simplifyAngle(entity.ticksExisted, 5.0F) * 0.25F
                    + f4 / (180F / (float) Math.PI);
            } else {
                this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
            }
        }
    }

    protected float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }
}
