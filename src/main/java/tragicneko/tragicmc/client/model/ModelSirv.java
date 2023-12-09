package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.mob.EntitySirv;

public class ModelSirv extends ModelBiped {

    public ModelSirv() {
        textureWidth = 64;
        textureHeight = 32;

        bipedHead = new ModelRenderer(this, 22, 0);
        bipedHead.addBox(-1.5F, -3F, -1.5F, 3, 4, 3);
        bipedHead.setRotationPoint(0F, -3F, 0F);

        bipedBody = new ModelRenderer(this, 8, 0);
        bipedBody.addBox(-2F, 0F, -1F, 4, 3, 2);
        bipedBody.setRotationPoint(0F, -1F, 0F);
        ModelRenderer stomach = new ModelRenderer(this, 10, 6);
        stomach.addBox(-1F, 3F, 0F, 2, 5, 1);
        bipedBody.addChild(stomach);
        ModelRenderer neck = new ModelRenderer(this, 18, 8);
        neck.addBox(-0.5F, -1F, -0.5F, 1, 1, 1);
        bipedBody.addChild(neck);

        bipedRightArm = new ModelRenderer(this, 0, 12);
        bipedRightArm.addBox(-1F, 0F, -0.5F, 1, 9, 1);
        bipedRightArm.setRotationPoint(-2F, -1F, 0F);
        ModelRenderer armRightFist = new ModelRenderer(this, 6, 14);
        armRightFist.addBox(-1F, 9F, -1F, 1, 8, 2);
        bipedRightArm.addChild(armRightFist);

        bipedLeftArm = new ModelRenderer(this, 0, 12);
        bipedLeftArm.addBox(0F, 0F, -0.5F, 1, 9, 1);
        bipedLeftArm.setRotationPoint(2F, -1F, 0F);
        ModelRenderer armLeftFist = new ModelRenderer(this, 6, 14);
        armLeftFist.addBox(0F, 9F, -1F, 1, 8, 2);
        bipedLeftArm.addChild(armLeftFist);

        bipedRightLeg = new ModelRenderer(this, 0, 0);
        bipedRightLeg.addBox(-0.5F, 0F, -0.5F, 1, 8, 1);
        bipedRightLeg.setRotationPoint(-1F, 7F, 0F);
        ModelRenderer legRightFoot = new ModelRenderer(this, 0, 0);
        legRightFoot.addBox(-0.5F, 8F, -0.5F, 1, 9, 2);
        bipedRightLeg.addChild(legRightFoot);

        bipedLeftLeg = new ModelRenderer(this, 0, 0);
        bipedLeftLeg.addBox(-0.5F, 0F, -0.5F, 1, 8, 1);
        bipedLeftLeg.setRotationPoint(1F, 7F, 0F);
        ModelRenderer legLeftFoot = new ModelRenderer(this, 0, 0);
        legLeftFoot.addBox(-0.5F, 8F, -0.5F, 1, 9, 2);
        bipedLeftLeg.addChild(legLeftFoot);

        bipedHeadwear = new ModelRenderer(this, 0, 0);
        bipedHeadwear.addBox(0F, 0F, 0F, 0, 0, 0);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.isRiding = entity.isRiding();
        super.render(entity, f, f1, f2, f3, f4, f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        bipedHead.rotateAngleY = f3 / (180F / (float) Math.PI);
        bipedHead.rotateAngleX = f4 / (180F / (float) Math.PI);
        bipedLeftLeg.rotateAngleX = -0.85F * this.simplifyAngle(f, 10.0F) * f1;
        bipedRightLeg.rotateAngleX = 0.85F * this.simplifyAngle(f, 10.0F) * f1;

        EntitySirv sirv = (EntitySirv) entity;

        if (sirv.getAttackTime() > 0) {
            this.bipedRightArm.rotateAngleX = -0.85F + 0.85F * this.simplifyAngle(sirv.getAttackTime(), 10.0F);
            this.bipedRightArm.rotateAngleZ = 0.45F + -0.55F * this.simplifyAngle(sirv.getAttackTime(), 10.0F);

            this.bipedLeftArm.rotateAngleX = -0.85F + 0.85F * this.simplifyAngle(sirv.getAttackTime(), 10.0F);
            this.bipedLeftArm.rotateAngleZ = -0.45F + 0.55F * this.simplifyAngle(sirv.getAttackTime(), 10.0F);
        } else {
            this.bipedRightArm.rotateAngleX = 0F;
            this.bipedRightArm.rotateAngleZ = 0F;
            this.bipedLeftArm.rotateAngleX = 0F;
            this.bipedLeftArm.rotateAngleZ = 0F;
        }

        if (this.isRiding) {
            this.bipedRightArm.rotateAngleX = -1.11F;
            this.bipedLeftArm.rotateAngleX = -1.11F;
            this.bipedRightLeg.rotateAngleX = -((float) Math.PI * 2F / 5F);
            this.bipedLeftLeg.rotateAngleX = -((float) Math.PI * 2F / 5F);
            this.bipedRightLeg.rotateAngleY = ((float) Math.PI / 10F);
            this.bipedLeftLeg.rotateAngleY = -((float) Math.PI / 10F);
        }
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }
}
