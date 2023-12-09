package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelIre - TragicNeko
 * Created using Tabula 4.1.1
 */
public class ModelIre extends ModelBase {

    public ModelRenderer shape1;
    public ModelRenderer shape2;
    public ModelRenderer shape3;
    public ModelRenderer shape4;
    public ModelRenderer shape5;
    public ModelRenderer shape7;
    public ModelRenderer shape6;

    public ModelIre() {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.rotateAngleY = 12.0F;
        this.shape1.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8);

        this.shape2 = new ModelRenderer(this, 0, 0);
        this.shape2.addBox(-2.0F, -12.0F, -2.0F, 4, 4, 4);
        this.shape1.addChild(this.shape2);

        this.shape3 = new ModelRenderer(this, 0, 0);
        this.shape3.addBox(-2.0F, 8.0F, -2.0F, 4, 4, 4);
        this.shape1.addChild(this.shape3);

        this.shape4 = new ModelRenderer(this, 0, 0);
        this.shape4.addBox(8.0F, -2.0F, -2.0F, 4, 4, 4);
        this.shape1.addChild(this.shape4);

        this.shape5 = new ModelRenderer(this, 0, 0);
        this.shape5.addBox(-2.0F, -2.0F, 8.0F, 4, 4, 4);
        this.shape1.addChild(this.shape5);

        this.shape6 = new ModelRenderer(this, 0, 0);
        this.shape6.addBox(-2.0F, -2.0F, -12.0F, 4, 4, 4);
        this.shape1.addChild(this.shape6);

        this.shape7 = new ModelRenderer(this, 0, 0);
        this.shape7.addBox(-12.0F, -2.0F, -2.0F, 4, 4, 4);
        this.shape1.addChild(this.shape7);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.shape1.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        this.shape1.offsetY = (float) (Math.sin(Math.PI + this.simplifyAngle(entity.ticksExisted, 120.0F)) * -0.15F
            + 0.45F);

        this.shape1.rotateAngleY = f3 / (180F / (float) Math.PI);
        this.shape1.rotateAngleX = f4 / (180F / (float) Math.PI);
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }
}
