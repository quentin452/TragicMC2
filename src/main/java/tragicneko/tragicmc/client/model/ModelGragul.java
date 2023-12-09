package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.mob.EntityGragul;

public class ModelGragul extends ModelBase {

    protected ModelRenderer body;
    private ModelRenderer top;
    private ModelRenderer rightNub;
    private ModelRenderer leftNub;
    private ModelRenderer leftLeg;
    private ModelRenderer rightLeg;
    private ModelRenderer eye;
    private ModelRenderer jaw;

    public ModelGragul() {
        textureWidth = 64;
        textureHeight = 32;

        body = new ModelRenderer(this, 0, 0);
        body.addBox(-1.5F, 4.0F, -1.5F, 3, 3, 3);
        body.setRotationPoint(0F, 13F, 0F);
        top = new ModelRenderer(this, 16, 0);
        top.addBox(-1F, 3.0F, -0.5F, 2, 1, 1);
        body.addChild(top);
        rightNub = new ModelRenderer(this, 0, 8);
        rightNub.addBox(-2F, 5.5F, -1F, 1, 1, 1);
        body.addChild(rightNub);
        leftNub = new ModelRenderer(this, 0, 8);
        leftNub.addBox(1F, 5.5F, -1F, 1, 1, 1);
        body.addChild(leftNub);
        leftLeg = new ModelRenderer(this, 8, 8);
        leftLeg.addBox(0.5F, 7.0F, -1F, 1, 1, 1);
        body.addChild(leftLeg);
        rightLeg = new ModelRenderer(this, 8, 8);
        rightLeg.addBox(-1.5F, 7.0F, -1F, 1, 1, 1);
        body.addChild(rightLeg);
        eye = new ModelRenderer(this, 0, 16);
        eye.addBox(-1F, 4.5F, -2F, 2, 2, 1);
        body.addChild(eye);
        jaw = new ModelRenderer(this, 0, 12);
        jaw.addBox(-1.5F, 6.5F, -2.5F, 3, 1, 1);
        body.addChild(jaw);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        body.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        if (!(entity instanceof EntityGragul)) return;
        int i = entity.ticksExisted;

        this.leftLeg.rotateAngleZ = -0.075F * this.simplifyAngle(i, 15.0F) * f1;
        this.rightLeg.rotateAngleZ = 0.075F * this.simplifyAngle(i, 15.0F) * f1;
        this.leftNub.rotateAngleX = -0.075F * this.simplifyAngle(i, 15.0F) * f1;
        this.rightNub.rotateAngleX = 0.075F * this.simplifyAngle(i, 15.0F) * f1;
        this.body.rotateAngleZ = 0.165F * this.simplifyAngle(i, 30.0F) * f1;
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }
}
