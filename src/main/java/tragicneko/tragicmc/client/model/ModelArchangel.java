package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.mob.EntityArchangel;

/**
 * ModelArchangel - TragicNeko
 * Created using Tabula 4.1.1
 */
public class ModelArchangel extends ModelBase {

    public ModelRenderer center;
    public ModelRenderer shape2;
    public ModelRenderer shape3;
    public ModelRenderer shape4;
    public ModelRenderer shape5;
    public ModelRenderer shape6;
    public ModelRenderer shape7;
    public ModelRenderer shape8;
    public ModelRenderer shape9;
    public ModelRenderer shape10;
    public ModelRenderer shape11;
    public ModelRenderer shape12;
    public ModelRenderer shape13;
    public ModelRenderer shape14;
    public ModelRenderer shape15;
    public ModelRenderer shape16;
    public ModelRenderer shape17;
    public ModelRenderer shape18;
    public ModelRenderer shape19;
    public ModelRenderer wings;
    public ModelRenderer wingLeft;
    public ModelRenderer wingRight;
    public ModelRenderer shape24;
    public ModelRenderer shape26;
    public ModelRenderer shape29;
    public ModelRenderer shape30;
    public ModelRenderer shape32;
    public ModelRenderer shape37;
    public ModelRenderer shape39;
    public ModelRenderer shape40;
    public ModelRenderer shape25;
    public ModelRenderer shape27;
    public ModelRenderer shape28;
    public ModelRenderer shape31;
    public ModelRenderer shape33;
    public ModelRenderer shape38;
    public ModelRenderer shape36;
    public ModelRenderer shape41;

    public ModelArchangel() {

        this.textureWidth = 64;
        this.textureHeight = 16;

        this.shape3 = new ModelRenderer(this, 0, 0);
        this.shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape3.addBox(-4.0F, 3.0F, 0.0F, 8, 1, 1, 0.0F);
        this.center = new ModelRenderer(this, 0, 0);
        this.center.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.center.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.shape18 = new ModelRenderer(this, 0, 0);
        this.shape18.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape18.addBox(4.0F, 8.0F, 0.0F, 1, 1, 1, 0.0F);
        this.shape38 = new ModelRenderer(this, 0, 0);
        this.shape38.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape38.addBox(-15.0F, 2.0F, 0.0F, 1, 2, 1, 0.0F);
        this.shape37 = new ModelRenderer(this, 0, 0);
        this.shape37.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape37.addBox(12.0F, 1.0F, 0.0F, 16, 1, 1, 0.0F);
        this.wingRight = new ModelRenderer(this, 0, 0);
        this.wingRight.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wingRight.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.shape36 = new ModelRenderer(this, 0, 0);
        this.shape36.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape36.addBox(-28.0F, 1.0F, 0.0F, 16, 1, 1, 0.0F);
        this.shape39 = new ModelRenderer(this, 0, 0);
        this.shape39.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape39.addBox(14.0F, 2.0F, 0.0F, 1, 2, 1, 0.0F);
        this.shape15 = new ModelRenderer(this, 0, 0);
        this.shape15.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape15.addBox(-5.0F, 7.0F, 0.0F, 10, 1, 1, 0.0F);
        this.shape2 = new ModelRenderer(this, 0, 0);
        this.shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape2.addBox(-4.0F, -3.0F, 0.0F, 8, 1, 1, 0.0F);
        this.shape16 = new ModelRenderer(this, 0, 0);
        this.shape16.setRotationPoint(3.0F, 9.0F, 0.0F);
        this.shape16.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.shape41 = new ModelRenderer(this, 0, 0);
        this.shape41.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape41.addBox(13.0F, 4.0F, 0.0F, 10, 1, 1, 0.0F);
        this.shape30 = new ModelRenderer(this, 0, 0);
        this.shape30.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape30.addBox(10.0F, -4.0F, 0.0F, 12, 1, 1, 0.0F);
        this.shape9 = new ModelRenderer(this, 0, 0);
        this.shape9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape9.addBox(-2.0F, -4.0F, 0.0F, 4, 1, 1, 0.0F);
        this.shape24 = new ModelRenderer(this, 0, 0);
        this.shape24.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape24.addBox(1.0F, -1.0F, 0.0F, 5, 1, 1, 0.0F);
        this.shape14 = new ModelRenderer(this, 0, 0);
        this.shape14.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape14.addBox(5.0F, -1.5F, 0.0F, 1, 3, 1, 0.0F);
        this.shape19 = new ModelRenderer(this, 0, 0);
        this.shape19.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape19.addBox(-5.0F, 8.0F, 0.0F, 1, 1, 1, 0.0F);
        this.shape27 = new ModelRenderer(this, 0, 0);
        this.shape27.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape27.addBox(-33.0F, -2.0F, 0.0F, 28, 1, 1, 0.0F);
        this.shape33 = new ModelRenderer(this, 0, 0);
        this.shape33.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape33.addBox(-11.0F, -3.0F, 0.0F, 1, 1, 1, 0.0F);
        this.wings = new ModelRenderer(this, 0, 0);
        this.wings.setRotationPoint(0.0F, 0.0F, 2.0F);
        this.wings.addBox(-1.5F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.shape40 = new ModelRenderer(this, 0, 0);
        this.shape40.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape40.addBox(-23.0F, 4.0F, 0.0F, 10, 1, 1, 0.0F);
        this.shape4 = new ModelRenderer(this, 0, 0);
        this.shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape4.addBox(-5.0F, -3.0F, 0.0F, 1, 7, 1, 0.0F);
        this.wingLeft = new ModelRenderer(this, 0, 0);
        this.wingLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wingLeft.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.shape17 = new ModelRenderer(this, 0, 0);
        this.shape17.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape17.addBox(-7.0F, 9.0F, 0.0F, 4, 1, 1, 0.0F);
        this.shape28 = new ModelRenderer(this, 0, 0);
        this.shape28.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape28.addBox(-13.0F, 0.0F, 0.0F, 8, 1, 1, 0.0F);
        this.shape8 = new ModelRenderer(this, 0, 0);
        this.shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape8.addBox(-5.0F, -7.0F, 0.0F, 10, 1, 1, 0.0F);
        this.shape13 = new ModelRenderer(this, 0, 0);
        this.shape13.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape13.addBox(-6.0F, -1.5F, 0.0F, 1, 3, 1, 0.0F);
        this.shape32 = new ModelRenderer(this, 0, 0);
        this.shape32.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape32.addBox(10.0F, -3.0F, 0.0F, 1, 1, 1, 0.0F);
        this.shape12 = new ModelRenderer(this, 0, 0);
        this.shape12.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape12.addBox(2.0F, 4.0F, 0.0F, 1, 3, 1, 0.0F);
        this.shape25 = new ModelRenderer(this, 0, 0);
        this.shape25.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape25.addBox(-6.0F, -1.0F, 0.0F, 5, 1, 1, 0.0F);
        this.shape5 = new ModelRenderer(this, 0, 0);
        this.shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape5.addBox(4.0F, -3.0F, 0.0F, 1, 7, 1, 0.0F);
        this.shape6 = new ModelRenderer(this, 0, 0);
        this.shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape6.addBox(-8.0F, -8.0F, 0.0F, 4, 1, 1, 0.0F);
        this.shape10 = new ModelRenderer(this, 0, 0);
        this.shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape10.addBox(-1.0F, -6.0F, 0.0F, 2, 2, 1, 0.0F);
        this.shape7 = new ModelRenderer(this, 0, 0);
        this.shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape7.addBox(4.0F, -8.0F, 0.0F, 4, 1, 1, 0.0F);
        this.shape26 = new ModelRenderer(this, 0, 0);
        this.shape26.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape26.addBox(5.0F, -2.0F, 0.0F, 28, 1, 1, 0.0F);
        this.shape29 = new ModelRenderer(this, 0, 0);
        this.shape29.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape29.addBox(5.0F, 0.0F, 0.0F, 8, 1, 1, 0.0F);
        this.shape11 = new ModelRenderer(this, 0, 0);
        this.shape11.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape11.addBox(-3.0F, 4.0F, 0.0F, 1, 3, 1, 0.0F);
        this.shape31 = new ModelRenderer(this, 0, 0);
        this.shape31.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape31.addBox(-22.0F, -4.0F, 0.0F, 12, 1, 1, 0.0F);
        this.center.addChild(this.shape3);
        this.center.addChild(this.shape18);
        this.wingRight.addChild(this.shape38);
        this.wingLeft.addChild(this.shape37);
        this.wings.addChild(this.wingRight);
        this.wingRight.addChild(this.shape36);
        this.wingLeft.addChild(this.shape39);
        this.center.addChild(this.shape15);
        this.center.addChild(this.shape2);
        this.center.addChild(this.shape16);
        this.wingLeft.addChild(this.shape41);
        this.wingLeft.addChild(this.shape30);
        this.center.addChild(this.shape9);
        this.wingLeft.addChild(this.shape24);
        this.center.addChild(this.shape14);
        this.center.addChild(this.shape19);
        this.wingRight.addChild(this.shape27);
        this.wingRight.addChild(this.shape33);
        this.center.addChild(this.wings);
        this.wingRight.addChild(this.shape40);
        this.center.addChild(this.shape4);
        this.wings.addChild(this.wingLeft);
        this.center.addChild(this.shape17);
        this.wingRight.addChild(this.shape28);
        this.center.addChild(this.shape8);
        this.center.addChild(this.shape13);
        this.wingLeft.addChild(this.shape32);
        this.center.addChild(this.shape12);
        this.wingRight.addChild(this.shape25);
        this.center.addChild(this.shape5);
        this.center.addChild(this.shape6);
        this.center.addChild(this.shape10);
        this.center.addChild(this.shape7);
        this.wingLeft.addChild(this.shape26);
        this.wingLeft.addChild(this.shape29);
        this.center.addChild(this.shape11);
        this.wingRight.addChild(this.shape31);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.center.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        EntityArchangel arch = (EntityArchangel) entity;

        center.offsetY = (float) (Math.sin(Math.PI + this.simplifyAngle(arch.ticksExisted, 20.0F)) * 0.12F + 0.25F);

        if (arch.getHoverTicks() > 10 && arch.getHoverTicks() <= 120)
            center.rotateAngleX = this.simplifyAngle(arch.ticksExisted, 5.0F) * 0.16F;
        if (arch.hurtTime > 0) center.rotateAngleX = 0.2230717F + this.simplifyAngle(arch.hurtTime, 10.0F) * -0.112F;

        wingRight.rotateAngleY = 0.425F + this.simplifyAngle(arch.ticksExisted, 15.0F) * -0.425F;
        wingLeft.rotateAngleY = -0.425F + this.simplifyAngle(arch.ticksExisted, 15.0F) * 0.425F;
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
