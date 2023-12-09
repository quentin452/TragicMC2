package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.miniboss.EntityVoxStellarum;

public class ModelVoxStellarum extends ModelNorVox {

    private ModelRenderer jaw2;
    private ModelRenderer jaw3;
    private ModelRenderer jaw4;

    public ModelVoxStellarum() {
        super();
        textureWidth = 128;
        textureHeight = 64;

        // Head 1 pieces
        head.childModels.clear();
        head.cubeList.clear();
        head = new ModelRenderer(this, 56, 0);
        head.addBox(-9F, -4F, -6F, 18, 6, 16);
        head.setRotationPoint(0F, 12F, -2F);
        ModelRenderer headJaw = new ModelRenderer(this, 64, 0);
        headJaw.addBox(-4F, 2F, -6F, 8, 3, 14);
        head.addChild(headJaw);
        ModelRenderer noseBridge = new ModelRenderer(this, 0, 0);
        noseBridge.addBox(-1F, -3F, -7F, 2, 6, 1);
        head.addChild(noseBridge);
        ModelRenderer leftEyeInner = new ModelRenderer(this, 0, 0);
        leftEyeInner.addBox(1F, -3F, -8F, 1, 4, 2);
        head.addChild(leftEyeInner);
        ModelRenderer rightEyeInner = new ModelRenderer(this, 0, 0);
        rightEyeInner.addBox(-2F, -3F, -8F, 1, 4, 2);
        head.addChild(rightEyeInner);
        ModelRenderer headTop = new ModelRenderer(this, 64, 0);
        headTop.addBox(-4F, -6F, -6F, 8, 2, 6);
        head.addChild(headTop);
        ModelRenderer leftEyeLower = new ModelRenderer(this, 0, 0);
        leftEyeLower.addBox(2F, 0F, -8F, 4, 1, 2);
        head.addChild(leftEyeLower);
        ModelRenderer leftEyeUpper = new ModelRenderer(this, 0, 0);
        leftEyeUpper.addBox(2F, -3F, -8F, 4, 1, 2);
        head.addChild(leftEyeUpper);
        ModelRenderer leftEyeOuter = new ModelRenderer(this, 0, 0);
        leftEyeOuter.addBox(5F, -2F, -8F, 1, 2, 2);
        head.addChild(leftEyeOuter);
        ModelRenderer rightEyeLower = new ModelRenderer(this, 0, 0);
        rightEyeLower.addBox(-6F, 0F, -8F, 4, 1, 2);
        head.addChild(rightEyeLower);
        ModelRenderer rightEyeUpper = new ModelRenderer(this, 0, 0);
        rightEyeUpper.addBox(-6F, -3F, -8F, 4, 1, 2);
        head.addChild(rightEyeUpper);
        ModelRenderer rightEyeOuter = new ModelRenderer(this, 0, 0);
        rightEyeOuter.addBox(-6F, -2F, -8F, 1, 2, 2);
        head.addChild(rightEyeOuter);
        ModelRenderer noseForehead = new ModelRenderer(this, 0, 0);
        noseForehead.addBox(-3F, -5F, -7F, 6, 2, 1);
        head.addChild(noseForehead);
        ModelRenderer noseBottom = new ModelRenderer(this, 0, 0);
        noseBottom.addBox(-3F, 3F, -7F, 6, 1, 1);
        head.addChild(noseBottom);
        ModelRenderer leftNostrilUpper = new ModelRenderer(this, 0, 0);
        leftNostrilUpper.addBox(1F, 1F, -7F, 2, 1, 1);
        head.addChild(leftNostrilUpper);
        ModelRenderer rightNostrilUpper = new ModelRenderer(this, 0, 0);
        rightNostrilUpper.addBox(-3F, 1F, -7F, 2, 1, 1);
        head.addChild(rightNostrilUpper);
        ModelRenderer leftNostrilOuter = new ModelRenderer(this, 0, 0);
        leftNostrilOuter.addBox(2F, 2F, -7F, 1, 1, 1);
        head.addChild(leftNostrilOuter);
        ModelRenderer rightNostrilOuter = new ModelRenderer(this, 0, 0);
        rightNostrilOuter.addBox(-3F, 2F, -7F, 1, 1, 1);
        head.addChild(rightNostrilOuter);

        // Jaw 1 pieces
        this.jaw.childModels.clear();
        this.jaw.cubeList.clear();
        this.jaw = new ModelRenderer(this, 64, 0);
        jaw.addBox(-3F, 6F, -5F, 6, 2, 4);
        head.addChild(jaw);
        ModelRenderer backJaw = new ModelRenderer(this, 64, 0);
        backJaw.addBox(-4F, 5F, -3F, 8, 2, 3);
        jaw.addChild(backJaw);

        // Head 3 pieces
        ModelRenderer headTop3 = new ModelRenderer(this, 64, 0);
        headTop3.addBox(3F, -6F, -2F, 6, 2, 8);
        head.addChild(headTop3);
        ModelRenderer noseForehead3 = new ModelRenderer(this, 0, 0);
        noseForehead3.addBox(9F, -5F, -1F, 1, 2, 6);
        head.addChild(noseForehead3);
        ModelRenderer noseBridge3 = new ModelRenderer(this, 0, 0);
        noseBridge3.addBox(9F, -3F, 1F, 1, 6, 2);
        head.addChild(noseBridge3);
        ModelRenderer leftEyeInner3 = new ModelRenderer(this, 0, 0);
        leftEyeInner3.addBox(9F, -2F, 3F, 2, 2, 1);
        head.addChild(leftEyeInner3);
        ModelRenderer rightEyeInner3 = new ModelRenderer(this, 0, 0);
        rightEyeInner3.addBox(9F, -2F, 0F, 2, 2, 1);
        head.addChild(rightEyeInner3);
        ModelRenderer leftEyeOuter3 = new ModelRenderer(this, 0, 0);
        leftEyeOuter3.addBox(9F, -2F, -4F, 2, 2, 1);
        head.addChild(leftEyeOuter3);
        ModelRenderer rightEyeOuter3 = new ModelRenderer(this, 0, 0);
        rightEyeOuter3.addBox(9F, -2F, 7F, 2, 2, 1);
        head.addChild(rightEyeOuter3);
        ModelRenderer leftEyeUpper3 = new ModelRenderer(this, 0, 0);
        leftEyeUpper3.addBox(9F, -3F, -3F, 2, 1, 3);
        head.addChild(leftEyeUpper3);
        ModelRenderer rightEyeUpper3 = new ModelRenderer(this, 0, 0);
        rightEyeUpper3.addBox(9F, -3F, 4F, 2, 1, 3);
        head.addChild(rightEyeUpper3);
        ModelRenderer rightNostrilUpper3 = new ModelRenderer(this, 0, 0);
        rightNostrilUpper3.addBox(9F, 1F, 3F, 1, 1, 2);
        head.addChild(rightNostrilUpper3);
        ModelRenderer leftNostrilUpper3 = new ModelRenderer(this, 0, 0);
        leftNostrilUpper3.addBox(9F, 1F, -1F, 1, 1, 2);
        head.addChild(leftNostrilUpper3);
        ModelRenderer rightNostrilOuter3 = new ModelRenderer(this, 0, 0);
        rightNostrilOuter3.addBox(9F, 2F, 4F, 1, 1, 1);
        head.addChild(rightNostrilOuter3);
        ModelRenderer leftNostrilOuter3 = new ModelRenderer(this, 0, 0);
        leftNostrilOuter3.addBox(9F, 2F, -1F, 1, 1, 1);
        head.addChild(leftNostrilOuter3);
        ModelRenderer noseBottom3 = new ModelRenderer(this, 0, 0);
        noseBottom3.addBox(9F, 3F, -1F, 1, 1, 6);
        head.addChild(noseBottom3);
        ModelRenderer backJaw3 = new ModelRenderer(this, 64, 0);
        backJaw3.addBox(3F, 5F, -2F, 3, 2, 10);
        head.addChild(backJaw3);

        // Jaw 3 pieces
        jaw3 = new ModelRenderer(this, 64, 0);
        jaw3.addBox(4F, 7F, -1F, 4, 2, 6);
        head.addChild(jaw3);
        ModelRenderer teeth3 = new ModelRenderer(this, 0, 0);
        teeth3.addBox(6F, 5F, -1F, 1, 2, 6);
        jaw3.addChild(teeth3);
        ModelRenderer expression3 = new ModelRenderer(this, 0, 0);
        expression3.addBox(7F, 5F, 0F, 1, 1, 4);
        jaw3.addChild(expression3);

        // Head 2 pieces
        ModelRenderer headTop2 = new ModelRenderer(this, 64, 0);
        headTop2.addBox(-9F, -6F, -2F, 6, 2, 8);
        head.addChild(headTop2);
        ModelRenderer noseBridge2 = new ModelRenderer(this, 0, 0);
        noseBridge2.addBox(-10F, -3F, 1F, 1, 6, 2);
        head.addChild(noseBridge2);
        ModelRenderer noseForehead2 = new ModelRenderer(this, 0, 0);
        noseForehead2.addBox(-10F, -5F, -1F, 1, 2, 6);
        head.addChild(noseForehead2);
        ModelRenderer rightEyeInner2 = new ModelRenderer(this, 0, 0);
        rightEyeInner2.addBox(-11F, -1F, 3F, 2, 2, 1);
        head.addChild(rightEyeInner2);
        ModelRenderer leftEyeInner2 = new ModelRenderer(this, 0, 0);
        leftEyeInner2.addBox(-11F, -1F, 0F, 2, 2, 1);
        head.addChild(leftEyeInner2);
        ModelRenderer rightEyeUpper2 = new ModelRenderer(this, 0, 0);
        rightEyeUpper2.addBox(-11F, -2F, 4F, 2, 1, 4);
        head.addChild(rightEyeUpper2);
        ModelRenderer rightEyeLower2 = new ModelRenderer(this, 0, 0);
        rightEyeLower2.addBox(-11F, 0F, 4F, 2, 1, 3);
        head.addChild(rightEyeLower2);
        ModelRenderer rightEyeOuter2 = new ModelRenderer(this, 0, 0);
        rightEyeOuter2.addBox(-11F, -1F, 6F, 2, 1, 1);
        head.addChild(rightEyeOuter2);
        ModelRenderer leftEyeUpper2 = new ModelRenderer(this, 0, 0);
        leftEyeUpper2.addBox(-11F, -2F, -4F, 2, 1, 4);
        head.addChild(leftEyeUpper2);
        ModelRenderer leftEyeLower2 = new ModelRenderer(this, 0, 0);
        leftEyeLower2.addBox(-11F, 0F, -3F, 2, 1, 3);
        head.addChild(leftEyeLower2);
        ModelRenderer leftEyeOuter2 = new ModelRenderer(this, 0, 0);
        leftEyeOuter2.addBox(-11F, -1F, -3F, 2, 1, 1);
        head.addChild(leftEyeOuter2);
        ModelRenderer rightNostril2 = new ModelRenderer(this, 0, 0);
        rightNostril2.addBox(-10F, 1F, 3F, 1, 1, 2);
        head.addChild(rightNostril2);
        ModelRenderer leftNostril2 = new ModelRenderer(this, 0, 0);
        leftNostril2.addBox(-10F, 1F, -1F, 1, 1, 2);
        head.addChild(leftNostril2);
        ModelRenderer noseBottom2 = new ModelRenderer(this, 0, 0);
        noseBottom2.addBox(-10F, 3F, -1F, 1, 1, 6);
        head.addChild(noseBottom2);
        ModelRenderer leftNostrilOuter2 = new ModelRenderer(this, 0, 0);
        leftNostrilOuter2.addBox(-10F, 2F, -1F, 1, 1, 1);
        head.addChild(leftNostrilOuter2);
        ModelRenderer rightNostrilOuter2 = new ModelRenderer(this, 0, 0);
        rightNostrilOuter2.addBox(-10F, 2F, 4F, 1, 1, 1);
        head.addChild(rightNostrilOuter2);
        ModelRenderer backJaw2 = new ModelRenderer(this, 64, 0);
        backJaw2.addBox(-6F, 5F, -2F, 3, 2, 8);
        head.addChild(backJaw2);
        ModelRenderer headJaw2 = new ModelRenderer(this, 64, 0);
        headJaw2.addBox(-9F, 2F, -2F, 18, 3, 8);
        head.addChild(headJaw2);

        // Jaw 2 pieces
        jaw2 = new ModelRenderer(this, 64, 0);
        jaw2.addBox(-8F, 7F, -1F, 4, 2, 6);
        head.addChild(jaw2);
        ModelRenderer teeth2 = new ModelRenderer(this, 0, 0);
        teeth2.addBox(-7F, 5F, -1F, 1, 2, 6);
        jaw2.addChild(teeth2);

        // Head 4 pieces
        ModelRenderer noseForehead4 = new ModelRenderer(this, 0, 0);
        noseForehead4.addBox(-3F, -5F, 10F, 6, 2, 1);
        head.addChild(noseForehead4);
        ModelRenderer noseBridge4 = new ModelRenderer(this, 0, 0);
        noseBridge4.addBox(-1F, -3F, 10F, 2, 6, 1);
        head.addChild(noseBridge4);
        ModelRenderer headTop4 = new ModelRenderer(this, 64, 0);
        headTop4.addBox(-4F, -6F, 4F, 8, 2, 6);
        head.addChild(headTop4);
        ModelRenderer leftEyeInner4 = new ModelRenderer(this, 0, 0);
        leftEyeInner4.addBox(-2F, -2F, 10F, 1, 2, 2);
        head.addChild(leftEyeInner4);
        ModelRenderer rightEyeInner4 = new ModelRenderer(this, 0, 0);
        rightEyeInner4.addBox(1F, -2F, 10F, 1, 2, 2);
        head.addChild(rightEyeInner4);
        ModelRenderer leftEyeOuter4 = new ModelRenderer(this, 0, 0);
        leftEyeOuter4.addBox(-5F, -2F, 10F, 1, 2, 2);
        head.addChild(leftEyeOuter4);
        ModelRenderer rightEyeOuter4 = new ModelRenderer(this, 0, 0);
        rightEyeOuter4.addBox(4F, -2F, 10F, 1, 2, 2);
        head.addChild(rightEyeOuter4);
        ModelRenderer leftEyeLower4 = new ModelRenderer(this, 0, 0);
        leftEyeLower4.addBox(-4F, 0F, 10F, 2, 1, 2);
        head.addChild(leftEyeLower4);
        ModelRenderer rightEyeLower4 = new ModelRenderer(this, 0, 0);
        rightEyeLower4.addBox(2F, 0F, 10F, 2, 1, 2);
        head.addChild(rightEyeLower4);
        ModelRenderer leftNostrilUpper4 = new ModelRenderer(this, 0, 0);
        leftNostrilUpper4.addBox(-3F, 1F, 10F, 2, 1, 1);
        head.addChild(leftNostrilUpper4);
        ModelRenderer rightNostrilUpper4 = new ModelRenderer(this, 0, 0);
        rightNostrilUpper4.addBox(1F, 1F, 10F, 2, 1, 1);
        head.addChild(rightNostrilUpper4);
        ModelRenderer rightNostrilOuter4 = new ModelRenderer(this, 0, 0);
        rightNostrilOuter4.addBox(2F, 2F, 10F, 1, 1, 1);
        head.addChild(rightNostrilOuter4);
        ModelRenderer leftNostrilOuter4 = new ModelRenderer(this, 0, 0);
        leftNostrilOuter4.addBox(-2F, 2F, 10F, 1, 1, 1);
        head.addChild(leftNostrilOuter4);
        ModelRenderer noseBottom4 = new ModelRenderer(this, 0, 0);
        noseBottom4.addBox(-3F, 3F, 10F, 6, 1, 1);
        head.addChild(noseBottom4);
        ModelRenderer backJaw4 = new ModelRenderer(this, 64, 0);
        backJaw4.addBox(-4F, 5F, 3F, 8, 2, 3);
        head.addChild(backJaw4);

        // Jaw 4 pieces
        jaw4 = new ModelRenderer(this, 64, 0);
        jaw4.addBox(-3F, 7F, 4F, 6, 2, 4);
        head.addChild(jaw4);
        ModelRenderer teeth4 = new ModelRenderer(this, 0, 0);
        teeth4.addBox(-3F, 5F, 6F, 6, 2, 1);
        jaw4.addChild(teeth4);
        ModelRenderer expression4 = new ModelRenderer(this, 0, 0);
        expression4.addBox(-2F, 6F, 7F, 4, 1, 1);
        jaw4.addChild(expression4);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        if (!(entity instanceof EntityVoxStellarum)) return;
        EntityVoxStellarum vox = (EntityVoxStellarum) entity;

        if (vox.isSpinning()) {
            head.rotateAngleY = this.simplifyAngle(vox.ticksExisted, vox.getRotationSpeed());

            this.jaw.offsetY = this.simplifyAngle(vox.ticksExisted, 0.35F) * 0.1F;
            this.jaw2.offsetY = this.simplifyAngle(vox.ticksExisted, 0.35F) * 0.1F;
            this.jaw3.offsetY = this.simplifyAngle(vox.ticksExisted, 0.35F) * 0.1F;
            this.jaw4.offsetY = this.simplifyAngle(vox.ticksExisted, 0.35F) * 0.1F;
        } else {
            head.rotateAngleY = 0.0F;

            super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

            if (vox.getFiringTicks() >= 40 || vox.getNodTicks() > 0 || vox.getAttackTime() > 0) {
                this.jaw2.offsetY = this.simplifyAngle(vox.ticksExisted, 0.35F) * 0.1F;
                this.jaw3.offsetY = this.simplifyAngle(vox.ticksExisted, 0.35F) * 0.1F;
                this.jaw4.offsetY = this.simplifyAngle(vox.ticksExisted, 0.35F) * 0.1F;
            } else {
                this.jaw2.offsetY = 0.0F;
                this.jaw3.offsetY = 0.0F;
                this.jaw4.offsetY = 0.0F;
            }
        }

        if (vox.isHealing()) {
            this.head.rotateAngleY += 1.5F;
        } else if (vox.isFiring()) {
            this.head.rotateAngleY += 1.5F;
        } else if (vox.getAttackTime() > 0) {
            this.head.rotateAngleY -= 1.5F;
        }
    }

}
