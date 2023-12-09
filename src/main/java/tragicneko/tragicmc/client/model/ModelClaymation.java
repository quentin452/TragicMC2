package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.boss.EntityClaymation;

public class ModelClaymation extends ModelBase {

    private ModelRenderer head; // head
    private ModelRenderer upperBody; // upper body
    private ModelRenderer legRight; // right leg
    private ModelRenderer legLeft; // left leg
    private ModelRenderer armLeft; // left arm
    private ModelRenderer armRight; // right arm

    public ModelClaymation() {
        textureWidth = 64;
        textureHeight = 64;

        head = new ModelRenderer(this, 0, 15);
        head.addBox(-3F, -4F, -3F, 6, 4, 4);
        head.setRotationPoint(0F, 4F, 0F);

        ModelRenderer headpiece = new ModelRenderer(this, 0, 0);
        headpiece.addBox(-1F, -3F, -4F, 2, 3, 1);
        head.addChild(headpiece);

        ModelRenderer headpiece2 = new ModelRenderer(this, 0, 0);
        headpiece2.addBox(-5F, -6F, -3F, 10, 2, 2);
        head.addChild(headpiece2);

        ModelRenderer headpiece3 = new ModelRenderer(this, 0, 0);
        headpiece3.addBox(-5F, -6F, -4F, 10, 1, 1);
        head.addChild(headpiece3);

        ModelRenderer headpiece4 = new ModelRenderer(this, 0, 0);
        headpiece4.addBox(-1F, -2F, -5F, 2, 2, 1);
        head.addChild(headpiece4);

        ModelRenderer headpiece5 = new ModelRenderer(this, 0, 0);
        headpiece5.addBox(-4F, -2F, -4F, 8, 1, 1);
        head.addChild(headpiece5);

        ModelRenderer headpiece6 = new ModelRenderer(this, 0, 0);
        headpiece6.addBox(-2F, -1F, -4F, 4, 2, 1);
        head.addChild(headpiece6);

        ModelRenderer headpiece7 = new ModelRenderer(this, 0, 0);
        headpiece7.addBox(-4F, -5F, -1F, 8, 1, 1);
        head.addChild(headpiece7);

        ModelRenderer headpiece8 = new ModelRenderer(this, 0, 0);
        headpiece8.addBox(-1F, -5F, 0F, 2, 1, 1);
        head.addChild(headpiece8);

        ModelRenderer headpiece9 = new ModelRenderer(this, 0, 0);
        headpiece9.addBox(-1F, -4F, 1F, 2, 4, 1);
        head.addChild(headpiece9);

        ModelRenderer headpiece10 = new ModelRenderer(this, 0, 0);
        headpiece10.addBox(-1F, -8F, -1F, 2, 3, 1);
        head.addChild(headpiece10);

        ModelRenderer headpiece11 = new ModelRenderer(this, 0, 0);
        headpiece11.addBox(-2F, -7F, -2F, 4, 1, 1);
        head.addChild(headpiece11);

        ModelRenderer headpiece12 = new ModelRenderer(this, 0, 0);
        headpiece12.addBox(3F, -2F, -3F, 1, 1, 4);
        head.addChild(headpiece12);

        ModelRenderer headpiece13 = new ModelRenderer(this, 0, 0);
        headpiece13.addBox(-4F, -2F, -3F, 1, 1, 4);
        head.addChild(headpiece13);

        ModelRenderer headpiece14 = new ModelRenderer(this, 0, 0);
        headpiece14.addBox(3F, -4F, 0F, 1, 2, 1);
        head.addChild(headpiece14);

        ModelRenderer headpiece15 = new ModelRenderer(this, 0, 0);
        headpiece15.addBox(-4F, -4F, 0F, 1, 2, 1);
        head.addChild(headpiece15);

        ModelRenderer headpiece16 = new ModelRenderer(this, 0, 0);
        headpiece16.addBox(-5F, -7F, -4F, 2, 1, 1);
        head.addChild(headpiece16);

        ModelRenderer headpiece17 = new ModelRenderer(this, 0, 0);
        headpiece17.addBox(3F, -7F, -4F, 2, 1, 1);
        head.addChild(headpiece17);

        upperBody = new ModelRenderer(this, 10, 33);
        upperBody.addBox(-7F, 0F, -1F, 14, 4, 4);
        upperBody.setRotationPoint(0F, 4F, 0F);

        ModelRenderer abdomen = new ModelRenderer(this, 23, 11);
        abdomen.addBox(-3F, 4F, 0F, 6, 5, 2);
        upperBody.addChild(abdomen);

        ModelRenderer pelvis = new ModelRenderer(this, 34, 0);
        pelvis.addBox(-4F, 9F, -1F, 8, 2, 4);
        upperBody.addChild(pelvis);

        ModelRenderer bodyPiece = new ModelRenderer(this, 0, 0);
        bodyPiece.addBox(-7F, 1F, -2F, 14, 1, 1);
        upperBody.addChild(bodyPiece);

        ModelRenderer bodyPiece2 = new ModelRenderer(this, 0, 0);
        bodyPiece2.addBox(-5F, 2F, -2F, 4, 1, 1);
        upperBody.addChild(bodyPiece2);

        ModelRenderer bodyPiece3 = new ModelRenderer(this, 0, 0);
        bodyPiece3.addBox(1F, 2F, -2F, 4, 1, 1);
        upperBody.addChild(bodyPiece3);

        ModelRenderer bodyPiece4 = new ModelRenderer(this, 0, 0);
        bodyPiece4.addBox(-1F, 4F, -1F, 2, 5, 1);
        upperBody.addChild(bodyPiece4);

        ModelRenderer bodyPiece5 = new ModelRenderer(this, 0, 0);
        bodyPiece5.addBox(1F, 5F, -1F, 2, 1, 1);
        upperBody.addChild(bodyPiece5);

        ModelRenderer bodyPiece6 = new ModelRenderer(this, 0, 0);
        bodyPiece6.addBox(-3F, 5F, -1F, 2, 1, 1);
        upperBody.addChild(bodyPiece6);

        ModelRenderer bodyPiece7 = new ModelRenderer(this, 0, 0);
        bodyPiece7.addBox(-3F, 6F, -1F, 1, 3, 1);
        upperBody.addChild(bodyPiece7);

        ModelRenderer bodyPiece8 = new ModelRenderer(this, 0, 0);
        bodyPiece8.addBox(2F, 6F, -1F, 1, 3, 1);
        upperBody.addChild(bodyPiece8);

        ModelRenderer bodyPiece9 = new ModelRenderer(this, 0, 0);
        bodyPiece9.addBox(-5F, 1F, 3F, 4, 1, 1);
        upperBody.addChild(bodyPiece9);

        ModelRenderer bodyPiece10 = new ModelRenderer(this, 0, 0);
        bodyPiece10.addBox(1F, 1F, 3F, 4, 1, 1);
        upperBody.addChild(bodyPiece10);

        ModelRenderer bodyPiece11 = new ModelRenderer(this, 0, 0);
        bodyPiece11.addBox(1F, 2F, 3F, 1, 2, 1);
        upperBody.addChild(bodyPiece11);

        ModelRenderer bodyPiece12 = new ModelRenderer(this, 0, 0);
        bodyPiece12.addBox(-2F, 2F, 3F, 1, 2, 1);
        upperBody.addChild(bodyPiece12);

        ModelRenderer bodyPiece13 = new ModelRenderer(this, 0, 0);
        bodyPiece13.addBox(1F, 4F, 2F, 1, 2, 1);
        upperBody.addChild(bodyPiece13);

        ModelRenderer bodyPiece14 = new ModelRenderer(this, 0, 0);
        bodyPiece14.addBox(-2F, 4F, 2F, 1, 2, 1);
        upperBody.addChild(bodyPiece14);

        ModelRenderer bodyPiece15 = new ModelRenderer(this, 0, 0);
        bodyPiece15.addBox(-2F, 6F, 2F, 4, 1, 1);
        upperBody.addChild(bodyPiece15);

        ModelRenderer bodyPiece16 = new ModelRenderer(this, 0, 0);
        bodyPiece16.addBox(-5F, 0F, 3F, 1, 1, 1);
        upperBody.addChild(bodyPiece16);

        ModelRenderer bodyPiece17 = new ModelRenderer(this, 0, 0);
        bodyPiece17.addBox(4F, 0F, 3F, 1, 1, 1);
        upperBody.addChild(bodyPiece17);

        ModelRenderer bodyPiece18 = new ModelRenderer(this, 0, 0);
        bodyPiece18.addBox(-6F, -1F, 1F, 2, 1, 2);
        upperBody.addChild(bodyPiece18);

        ModelRenderer bodyPiece19 = new ModelRenderer(this, 0, 0);
        bodyPiece19.addBox(4F, -1F, 1F, 2, 1, 2);
        upperBody.addChild(bodyPiece19);

        ModelRenderer bodyPiece20 = new ModelRenderer(this, 0, 0);
        bodyPiece20.addBox(-8F, 1F, -1F, 1, 1, 3);
        upperBody.addChild(bodyPiece20);

        ModelRenderer bodyPiece21 = new ModelRenderer(this, 0, 0);
        bodyPiece21.addBox(7F, 1F, -1F, 1, 1, 3);
        upperBody.addChild(bodyPiece21);

        ModelRenderer bodyPiece22 = new ModelRenderer(this, 0, 0);
        bodyPiece22.addBox(-1F, 3F, 3F, 2, 1, 1);
        upperBody.addChild(bodyPiece22);

        legRight = new ModelRenderer(this, 0, 30);
        legRight.addBox(-1F, 0F, -1F, 2, 9, 2);
        legRight.setRotationPoint(-3F, 15F, 1F);

        ModelRenderer legRightPiece = new ModelRenderer(this, 0, 0);
        legRightPiece.addBox(-2F, 2F, -2F, 4, 1, 4);
        legRight.addChild(legRightPiece);

        ModelRenderer legRightPiece2 = new ModelRenderer(this, 0, 0);
        legRightPiece2.addBox(-2F, 6F, -2F, 4, 1, 4);
        legRight.addChild(legRightPiece2);

        legLeft = new ModelRenderer(this, 0, 43);
        legLeft.addBox(-1F, 0F, -1F, 2, 9, 2);
        legLeft.setRotationPoint(3F, 15F, 1F);

        ModelRenderer legLeftPiece = new ModelRenderer(this, 0, 0);
        legLeftPiece.addBox(-2F, 2F, -2F, 4, 1, 4);
        legLeft.addChild(legLeftPiece);

        ModelRenderer legLeftPiece2 = new ModelRenderer(this, 0, 0);
        legLeftPiece2.addBox(-2F, 6F, -2F, 4, 1, 4);
        legLeft.addChild(legLeftPiece2);

        armLeft = new ModelRenderer(this, 52, 10);
        armLeft.addBox(0F, 0F, 0F, 1, 8, 1);
        armLeft.setRotationPoint(5F, 8F, 0F);

        armRight = new ModelRenderer(this, 46, 10);
        armRight.addBox(0F, 0F, 0F, 1, 8, 1);
        armRight.setRotationPoint(-6F, 8F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        head.render(f5);
        upperBody.render(f5);
        legRight.render(f5);
        legLeft.render(f5);
        armLeft.render(f5);
        armRight.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        if (!(entity instanceof EntityClaymation)) return;

        EntityClaymation clay = (EntityClaymation) entity;

        head.rotateAngleY = f3 / (180F / (float) Math.PI);
        head.rotateAngleX = f4 / (180F / (float) Math.PI);

        legLeft.rotateAngleX = -1.5F * this.simplifyAngle(f, 13.0F) * f1;
        legRight.rotateAngleX = 1.5F * this.simplifyAngle(f, 13.0F) * f1;
        armLeft.rotateAngleX = 0.55F * this.simplifyAngle(f, 13.0F) * f1;
        armRight.rotateAngleX = -0.55F * this.simplifyAngle(f, 13.0F) * f1;

        if (clay.getUtilityInt() > 0) {
            armRight.rotateAngleX = -0.556F;
            armLeft.rotateAngleX = -0.446F;
            head.rotateAngleX = 0.336F;
            armRight.rotateAngleZ = -0.246F;
            armLeft.rotateAngleZ = 0.246F;

            legLeft.rotateAngleX = 0.656F;
            legRight.rotateAngleX = -0.656F;
        } else {
            if (clay.getUtilityInt2() > 0) {
                armRight.rotateAngleZ = -0.246F + this.simplifyAngle(clay.getUtilityInt2(), 10.0F) * 0.246F;
                armLeft.rotateAngleZ = 0.246F + this.simplifyAngle(clay.getUtilityInt2(), 10.0F) * -0.246F;
            }
        }
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }
}
