package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHunter extends ModelBase {

    private ModelRenderer body;
    private ModelRenderer abdomen;
    private ModelRenderer head;
    private ModelRenderer wingUR;
    private ModelRenderer wingUL;
    private ModelRenderer wingLL;
    private ModelRenderer wingLR;

    public ModelHunter() {
        textureWidth = 64;
        textureHeight = 32;

        body = new ModelRenderer(this, 12, 0);
        body.addBox(-1F, -1F, -1F, 2, 3, 3);
        body.setRotationPoint(0F, 12F, 0F);

        head = new ModelRenderer(this, 0, 4);
        head.addBox(-0.5F, 0F, -3F, 1, 1, 2);
        body.addChild(head);

        abdomen = new ModelRenderer(this, 0, 8);
        abdomen.addBox(-1F, -1F, 2F, 2, 2, 3);
        abdomen.rotateAngleX = -0.425F;
        body.addChild(abdomen);

        ModelRenderer stinger = new ModelRenderer(this, 0, 14);
        stinger.addBox(-0.5F, -3F, 4F, 1, 1, 3);
        stinger.rotateAngleX = -0.425F;
        abdomen.addChild(stinger);

        wingUR = new ModelRenderer(this, 0, 0);
        wingUR.addBox(-4F, -1F, 0F, 3, 1, 2);
        body.addChild(wingUR);

        wingUL = new ModelRenderer(this, 0, 0);
        wingUL.addBox(1F, -1F, 0F, 3, 1, 2);
        body.addChild(wingUL);

        wingLL = new ModelRenderer(this, 0, 0);
        wingLL.addBox(1F, 1F, 0F, 3, 1, 2);
        body.addChild(wingLL);

        wingLR = new ModelRenderer(this, 0, 0);
        wingLR.addBox(-4F, 1F, 0F, 3, 1, 2);
        body.addChild(wingLR);
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

        this.body.rotateAngleY = f3 / (180F / (float) Math.PI);
        this.body.rotateAngleX = f4 / (180F / (float) Math.PI);

        this.wingUR.rotateAngleZ = this.simplifyAngle(entity.ticksExisted, 2.0F) * 0.325F;
        this.wingUL.rotateAngleZ = this.simplifyAngle(entity.ticksExisted, 2.0F) * -0.325F;
        this.wingLR.rotateAngleZ = this.simplifyAngle(entity.ticksExisted, 2.0F) * 0.325F;
        this.wingLL.rotateAngleZ = this.simplifyAngle(entity.ticksExisted, 2.0F) * -0.325F;

        this.abdomen.rotateAngleX = this.simplifyAngle(entity.ticksExisted, 16.0F) * 0.325F - 0.325F;
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }

}
