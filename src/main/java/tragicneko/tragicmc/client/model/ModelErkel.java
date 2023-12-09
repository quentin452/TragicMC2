package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelErkel extends ModelBase {

    private ModelRenderer body;
    private ModelRenderer footRight;
    private ModelRenderer footLeft;
    private ModelRenderer sideShroom;
    private ModelRenderer topShroom;
    private ModelRenderer topShroom2;

    public ModelErkel() {
        textureWidth = 64;
        textureHeight = 32;

        body = new ModelRenderer(this, 16, 0);
        body.addBox(-1.5F, 0F, -1.5F, 3, 6, 3);
        body.setRotationPoint(0F, 14F, 0F);
        ModelRenderer bodyThickness = new ModelRenderer(this, 30, 0);
        bodyThickness.addBox(-3F, 6F, -3F, 6, 3, 6);
        body.addChild(bodyThickness);

        ModelRenderer shroomPart = new ModelRenderer(this, 0, 24);
        shroomPart.addBox(-2F, -4F, -2F, 4, 2, 4);
        body.addChild(shroomPart);
        ModelRenderer shroomPart2 = new ModelRenderer(this, 0, 14);
        shroomPart2.addBox(-3F, -2F, -3F, 6, 2, 6);
        body.addChild(shroomPart2);
        ModelRenderer shroomPart3 = new ModelRenderer(this, 0, 4);
        shroomPart3.addBox(-4F, 0F, -3F, 1, 2, 6);
        body.addChild(shroomPart3);
        ModelRenderer shroomPart4 = new ModelRenderer(this, 0, 4);
        shroomPart4.addBox(3F, 0F, -3F, 1, 2, 6);
        body.addChild(shroomPart4);
        ModelRenderer shroomPart5 = new ModelRenderer(this, 0, 0);
        shroomPart5.addBox(-3F, 0F, -4F, 6, 2, 1);
        body.addChild(shroomPart5);
        ModelRenderer shroomPart6 = new ModelRenderer(this, 0, 0);
        shroomPart6.addBox(-3F, 0F, 3F, 6, 2, 1);
        body.addChild(shroomPart6);

        footRight = new ModelRenderer(this, 20, 12);
        footRight.addBox(-2F, 9F, 0F, 1, 1, 1);
        body.addChild(footRight);

        footLeft = new ModelRenderer(this, 20, 12);
        footLeft.addBox(1F, 9F, 0F, 1, 1, 1);
        body.addChild(footLeft);

        topShroom = new ModelRenderer(this, 26, 14);
        topShroom.addBox(2F, -4F, -2F, 1, 2, 1);
        topShroom.rotateAngleX = 0.1858931F;
        body.addChild(topShroom);
        ModelRenderer topShroomCap = new ModelRenderer(this, 32, 14);
        topShroomCap.addBox(1F, -5F, -3F, 3, 1, 3);
        topShroom.addChild(topShroomCap);

        topShroom2 = new ModelRenderer(this, 26, 14);
        topShroom2.addBox(-3F, -4F, 2F, 1, 2, 1);
        topShroom2.rotateAngleX = -0.2230717F;
        topShroom2.rotateAngleZ = -0.1487144F;
        body.addChild(topShroom2);
        ModelRenderer topShroom2Cap = new ModelRenderer(this, 32, 14);
        topShroom2Cap.addBox(-4F, -5F, 1F, 3, 1, 3);
        topShroom2.addChild(topShroom2Cap);

        sideShroom = new ModelRenderer(this, 26, 14);
        sideShroom.addBox(4F, 1F, 0F, 1, 2, 1);
        sideShroom.rotateAngleZ = 0.817929F;
        body.addChild(sideShroom);
        ModelRenderer sideShroom2 = new ModelRenderer(this, 32, 14);
        sideShroom2.addBox(3F, 0F, -1F, 3, 1, 3);
        sideShroom.addChild(sideShroom2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        body.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        body.rotateAngleY = f3 / (180F / (float) Math.PI);
        body.rotateAngleX = f4 / (180F / (float) Math.PI);
        footLeft.rotateAngleX = -0.25F * this.simplifyAngle(f, 5.0F) * f1;
        footRight.rotateAngleX = 0.25F * this.simplifyAngle(f, 5.0F) * f1;
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }
}
