package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.boss.EntityClaymation;
import tragicneko.tragicmc.entity.mob.EntityMinotaur;

public class ModelMinotaur extends ModelBase {

    private ModelRenderer head;
    private ModelRenderer body;
    private ModelRenderer rightArm;
    private ModelRenderer leftArm;
    private ModelRenderer rightLeg;
    private ModelRenderer leftLeg;

    public ModelMinotaur() {
        textureWidth = 64;
        textureHeight = 64;

        // Head, Horns and Nose
        head = new ModelRenderer(this, 0, 0);
        head.addBox(-3.5F, -8F, -6F, 7, 6, 8);
        head.setRotationPoint(0F, -5F, 0F);
        ModelRenderer leftHorn = new ModelRenderer(this, 30, 0);
        leftHorn.addBox(3.5F, -8F, 0F, 2, 2, 9);
        leftHorn.rotateAngleX = 0.2974289F;
        leftHorn.rotateAngleY = 0.3866576F;
        head.addChild(leftHorn);
        ModelRenderer rightHorn = new ModelRenderer(this, 30, 0);
        rightHorn.addBox(-5.5F, -8F, 0F, 2, 2, 9);
        rightHorn.rotateAngleX = 0.2974289F;
        rightHorn.rotateAngleY = -0.3866576F;
        head.addChild(rightHorn);
        ModelRenderer nose = new ModelRenderer(this, 0, 25);
        nose.addBox(-2F, -5F, -9F, 4, 3, 3);
        head.addChild(nose);

        // Body and Abdomen
        body = new ModelRenderer(this, 16, 16);
        body.addBox(-6F, 0F, -3F, 12, 9, 10);
        body.setRotationPoint(0F, -7F, -1F);
        ModelRenderer abdomen = new ModelRenderer(this, 26, 36);
        abdomen.addBox(-4F, 8F, 0F, 8, 6, 4);
        body.addChild(abdomen);

        // Right Arm and Forearm
        rightArm = new ModelRenderer(this, 0, 35);
        rightArm.addBox(-3F, -2F, -2F, 4, 12, 4);
        rightArm.setRotationPoint(-7F, -5F, 0F);
        rightArm.rotateAngleX = -0.1189716F;
        rightArm.rotateAngleZ = 0.2082002F;
        ModelRenderer rightArm2 = new ModelRenderer(this, 0, 52);
        rightArm2.addBox(-4F, 9F, -1F, 3, 7, 3);
        rightArm2.rotateAngleX = -0.1189716F;
        rightArm2.rotateAngleZ = -0.2082002F;
        rightArm.addChild(rightArm2);

        // Left Arm and Forearm
        leftArm = new ModelRenderer(this, 0, 35);
        leftArm.addBox(-1F, -2F, -2F, 4, 12, 4);
        leftArm.setRotationPoint(7F, -5F, 0F);
        leftArm.rotateAngleX = -0.1189716F;
        leftArm.rotateAngleZ = -0.2082002F;
        ModelRenderer leftArm2 = new ModelRenderer(this, 0, 52);
        leftArm2.addBox(1F, 9F, -1F, 3, 7, 3);
        leftArm2.rotateAngleX = -0.1189716F;
        leftArm2.rotateAngleZ = 0.2082002F;
        leftArm.addChild(leftArm2);

        // Right Leg, Thigh and Hoof
        rightLeg = new ModelRenderer(this, 0, 16);
        rightLeg.addBox(-1F, 8F, -2F, 3, 5, 3);
        rightLeg.setRotationPoint(-3F, 6F, 1F);
        ModelRenderer rightThigh = new ModelRenderer(this, 16, 48);
        rightThigh.addBox(-4F, 0F, -3F, 6, 8, 7);
        rightLeg.addChild(rightThigh);
        ModelRenderer rightHoof = new ModelRenderer(this, 42, 47);
        rightHoof.addBox(-2.5F, 13F, -2.5F, 5, 5, 5);
        rightLeg.addChild(rightHoof);

        // Left Leg, Thigh and Hoof
        leftLeg = new ModelRenderer(this, 0, 16);
        leftLeg.addBox(-2F, 8F, -2F, 3, 5, 3);
        leftLeg.setRotationPoint(3F, 6F, 1F);
        ModelRenderer leftThigh = new ModelRenderer(this, 16, 48);
        leftThigh.addBox(-3F, 0F, -3F, 6, 8, 7);
        leftLeg.addChild(leftThigh);
        ModelRenderer leftHoof = new ModelRenderer(this, 42, 47);
        leftHoof.addBox(-2.5F, 13F, -2.5F, 5, 5, 5);
        leftLeg.addChild(leftHoof);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        head.render(f5);
        body.render(f5);
        rightArm.render(f5);
        leftArm.render(f5);
        rightLeg.render(f5);
        leftLeg.render(f5);
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6,
        Entity entity) {
        if (!(entity instanceof EntityMinotaur) && !(entity instanceof EntityClaymation)) return;

        this.head.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.head.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.leftLeg.rotateAngleX = -1.5F * this.simplifyAngle(par1, 13.0F) * par2;
        this.rightLeg.rotateAngleX = 1.5F * this.simplifyAngle(par1, 13.0F) * par2;
        this.leftArm.rotateAngleX = 0.55F * this.simplifyAngle(par1, 13.0F) * par2;
        this.rightArm.rotateAngleX = -0.55F * this.simplifyAngle(par1, 13.0F) * par2;

        int charge = 0;
        if (entity instanceof EntityMinotaur) {
            EntityMinotaur mino = (EntityMinotaur) entity;
            charge = mino.getChargeTicks();
        } else {
            EntityClaymation clay = (EntityClaymation) entity;
            charge = clay.getUtilityInt();
        }

        if (charge > 10) {
            body.rotateAngleX = 0.4082002F;
            body.offsetY = 0.235F;
            head.offsetY = 0.265F;
            head.offsetZ = -0.095F;
            head.rotateAngleX = 0.4082002F;
            head.rotateAngleZ = 0.0F;
            leftArm.rotateAngleX = -0.8189716F;
            leftArm.rotateAngleZ = 0.1082002F;
            rightArm.rotateAngleX = -0.8189716F;
            rightArm.rotateAngleZ = -0.1082002F;
            leftArm.offsetY = 0.245F;
            rightArm.offsetY = 0.245F;
            leftLeg.rotateAngleX = -0.8189716F;
            rightLeg.rotateAngleX = 0.8189716F;
            rightLeg.offsetY = 0.145F;
            rightLeg.offsetZ = 0.025F;
            leftLeg.offsetY = 0.145F;
            leftLeg.offsetZ = -0.025F;
        } else {
            head.offsetY = 0.0F;
            head.offsetZ = 0.0F;
            body.rotateAngleX = 0.0F;
            body.offsetY = 0.0F;
            leftArm.offsetY = 0.0F;
            rightArm.offsetY = 0.0F;
            rightLeg.offsetY = 0.0F;
            leftLeg.offsetY = 0.0F;
            rightLeg.offsetZ = 0.0F;
            leftLeg.offsetZ = 0.0F;
            leftArm.rotateAngleX = -0.1189716F;
            leftArm.rotateAngleZ = -0.2082002F;
            rightArm.rotateAngleX = -0.1189716F;
            rightArm.rotateAngleZ = 0.2082002F;
        }
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }

}
