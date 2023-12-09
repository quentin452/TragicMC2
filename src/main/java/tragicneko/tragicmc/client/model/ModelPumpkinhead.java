package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.mob.EntityPumpkinhead;

public class ModelPumpkinhead extends ModelBiped {

    public ModelPumpkinhead() {
        textureWidth = 64;
        textureHeight = 64;

        bipedHead = new ModelRenderer(this, 0, 0);
        bipedHead.addBox(-5F, -10F, -5F, 10, 10, 10);
        bipedHead.setRotationPoint(0F, 0F, 0F);
        bipedHeadwear = new ModelRenderer(this, 0, 0);
        bipedHeadwear.addBox(0F, 0F, 0F, 0, 0, 0);

        bipedBody = new ModelRenderer(this, 18, 22);
        bipedBody.addBox(-3F, 0F, -2F, 6, 12, 4);
        bipedBody.setRotationPoint(0F, 0F, 0F);
        bipedCloak = new ModelRenderer(this, 44, 0);
        bipedCloak.addBox(-4F, 0F, 3F, 8, 14, 0);
        bipedCloak.rotateAngleX = 0.185931F;
        bipedBody.addChild(bipedCloak);

        bipedRightArm = new ModelRenderer(this, 40, 16);
        bipedRightArm.addBox(-3F, -2F, -1F, 2, 8, 2);
        bipedRightArm.setRotationPoint(-2F, 2F, 0F);
        bipedRightArm.rotateAngleX = -0.2230717F;

        bipedLeftArm = new ModelRenderer(this, 40, 28);
        bipedLeftArm.addBox(0F, -1F, -2F, 4, 12, 4);
        bipedLeftArm.setRotationPoint(3F, 2F, 0F);
        bipedLeftArm.rotateAngleX = -1.301251F;
        bipedLeftArm.rotateAngleZ = -0.5576792F;

        bipedRightLeg = new ModelRenderer(this, 0, 22);
        bipedRightLeg.addBox(-1F, 0F, -1F, 2, 12, 2);
        bipedRightLeg.setRotationPoint(-2F, 12F, 0F);

        bipedLeftLeg = new ModelRenderer(this, 0, 40);
        bipedLeftLeg.addBox(-2F, 0F, -2F, 4, 12, 4);
        bipedLeftLeg.setRotationPoint(2F, 12F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.isRiding = entity.isRiding();
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.renderCloak(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        EntityPumpkinhead pump = (EntityPumpkinhead) entity;
        if (pump.getAngerTicks() > 0) {
            this.bipedHead.rotateAngleY = 0.385F * this.simplifyAngle(f, 2.0F) - 0.385F;
        } else {
            this.bipedHead.rotateAngleY = f4 / (180F / (float) Math.PI);
        }

        this.bipedHead.rotateAngleX = f5 / (180F / (float) Math.PI);

        this.bipedRightLeg.rotateAngleX = 1.115F * this.simplifyAngle(f, 15.0F) * f1;
        this.bipedRightLeg.rotateAngleY = 0F;
        this.bipedLeftLeg.rotateAngleX = 0F;
        this.bipedRightLeg.rotateAngleY = 0F;
        this.bipedRightArm.rotateAngleX = -1.55F * this.simplifyAngle(f, 15.0F) * f1;

        if (this.isRiding) {
            this.bipedRightArm.rotateAngleX = -1.11F;
            this.bipedLeftArm.rotateAngleX = -1.11F;
            this.bipedRightLeg.rotateAngleX = -((float) Math.PI * 2F / 5F);
            this.bipedLeftLeg.rotateAngleX = -((float) Math.PI * 2F / 5F);
            this.bipedRightLeg.rotateAngleY = ((float) Math.PI / 10F);
            this.bipedLeftLeg.rotateAngleY = -((float) Math.PI / 10F);
        }

        if (Math.abs(entity.motionX) > 0.05 || Math.abs(entity.motionZ) > 0.05 || Math.abs(entity.motionY) > 0.01) {
            this.bipedCloak.rotateAngleX = 0.185931F + 0.8F * this.simplifyAngle(entity.ticksExisted, 10.0F) * f1;
        } else {
            this.bipedCloak.rotateAngleX = 0.185931F;
        }
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }

}
