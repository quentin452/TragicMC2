package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.mob.EntityHarvester;

public class ModelHarvester extends ModelBase {

    private ModelRenderer body;
    private ModelRenderer rotator;
    private ModelRenderer panel;

    private static final float[] directionArray = new float[] { 1.57F, -1.57F, 0.0F, 3.14F };

    public ModelHarvester() {
        textureWidth = 64;
        textureHeight = 32;

        body = new ModelRenderer(this, 0, 0);
        body.addBox(-3F, -3F, -6F, 6, 6, 10);
        body.setRotationPoint(0F, 13F, 0F);

        ModelRenderer mouthpiece = new ModelRenderer(this, 12, 18);
        mouthpiece.addBox(-4F, 3F, -8F, 8, 3, 6);
        body.addChild(mouthpiece);

        rotator = new ModelRenderer(this, 24, 0);
        rotator.addBox(0F, 6F, -5F, 3, 1, 3);
        body.addChild(rotator);

        ModelRenderer underbit = new ModelRenderer(this, 24, 5);
        underbit.addBox(-1F, 3F, 0F, 2, 1, 3);
        body.addChild(underbit);

        panel = new ModelRenderer(this, 38, 0);
        panel.addBox(-2F, -2F, 4F, 4, 5, 1);
        body.addChild(panel);

        ModelRenderer eyeplate = new ModelRenderer(this, 0, 18);
        eyeplate.addBox(-2F, -1F, -7F, 4, 4, 1);
        body.addChild(eyeplate);
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
        EntityHarvester harvester = (EntityHarvester) entity;

        this.body.rotateAngleY = directionArray[harvester.getDirection()];
        this.panel.rotateAngleX = 0F;

        if (harvester.getReleaseTicks() > 0) {
            this.panel.rotateAngleX = this.simplifyAngle(harvester.getReleaseTicks(), 20F) * 0.425F;
        }

        this.rotator.rotateAngleY = this.simplifyAngle(harvester.ticksExisted, 180F) * 10.5F;
        this.rotator.offsetY = this.simplifyAngle(harvester.ticksExisted, 120F) * 0.046F + 0.05F;
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }

}
