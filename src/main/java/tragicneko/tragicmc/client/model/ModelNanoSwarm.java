package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelNanoSwarm extends ModelBase {

    private ModelRenderer shape;
    private ModelRenderer shape2;
    private ModelRenderer shape3;
    private ModelRenderer shape4;
    private ModelRenderer shape5;
    private ModelRenderer shape6;
    private ModelRenderer shape7;
    private ModelRenderer shape8;
    private ModelRenderer shape9;
    private ModelRenderer shape10;

    private ModelRenderer[] shapeArray = new ModelRenderer[10];

    public ModelNanoSwarm() {
        textureWidth = 64;
        textureHeight = 32;

        shape = new ModelRenderer(this, 0, 0);
        shape.addBox(0F, 0F, 0F, 1, 1, 1);
        shape.setRotationPoint(0F, 10F, 0F);

        shape2 = new ModelRenderer(this, 0, 0);
        shape2.addBox(0F, 5F, 5F, 1, 1, 1);
        shape2.setRotationPoint(0F, 10F, 0F);

        shape3 = new ModelRenderer(this, 0, 0);
        shape3.addBox(-1F, 0F, -4F, 1, 1, 1);
        shape3.setRotationPoint(0F, 10F, 0F);

        shape4 = new ModelRenderer(this, 0, 0);
        shape4.addBox(3F, -2F, 1F, 1, 1, 1);
        shape4.setRotationPoint(0F, 10F, 0F);

        shape5 = new ModelRenderer(this, 0, 0);
        shape5.addBox(-5F, 5F, -6F, 1, 1, 1);
        shape5.setRotationPoint(0F, 10F, 0F);

        shape6 = new ModelRenderer(this, 0, 0);
        shape6.addBox(2F, 7F, 0F, 1, 1, 1);
        shape6.setRotationPoint(0F, 10F, 0F);

        shape7 = new ModelRenderer(this, 0, 0);
        shape7.addBox(-3F, 0F, 3F, 1, 1, 1);
        shape7.setRotationPoint(0F, 10F, 0F);

        shape8 = new ModelRenderer(this, 0, 0);
        shape8.addBox(0F, -4F, 6F, 1, 1, 1);
        shape8.setRotationPoint(0F, 10F, 0F);

        shape9 = new ModelRenderer(this, 0, 0);
        shape9.addBox(3F, 2F, -7F, 1, 1, 1);
        shape9.setRotationPoint(0F, 10F, 0F);

        shape10 = new ModelRenderer(this, 0, 0);
        shape10.addBox(-6F, -2F, 0F, 1, 1, 1);
        shape10.setRotationPoint(0F, 10F, 0F);

        this.shapeArray = new ModelRenderer[] { shape, shape2, shape3, shape4, shape5, shape6, shape7, shape8, shape9,
            shape10 };
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        shape.render(f5);
        shape2.render(f5);
        shape3.render(f5);
        shape4.render(f5);
        shape5.render(f5);
        shape6.render(f5);
        shape7.render(f5);
        shape8.render(f5);
        shape9.render(f5);
        shape10.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        int j = entity.ticksExisted;
        float meow;

        for (int i = 0; i < this.shapeArray.length; i++) {
            meow = (0.65F + (i * 0.25F)) * (float) Math.PI;
            shapeArray[i].rotateAngleX = meow * this.simplifyAngle(j, 180.0F);
            shapeArray[i].rotateAngleY = meow * this.simplifyAngle(j, 180.0F);
            shapeArray[i].rotateAngleZ = meow * this.simplifyAngle(j, 180.0F);
        }
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }
}
