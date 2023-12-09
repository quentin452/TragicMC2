package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.EntityTimeDisruption;

public class ModelGuardianShield extends ModelBase {

    private ModelRenderer outerShell;
    private ModelRenderer midShell;
    private ModelRenderer innerShell;

    private ModelRenderer outerShell2;
    private ModelRenderer midShell2;
    private ModelRenderer innerShell2;

    public ModelGuardianShield() {
        textureWidth = 64;
        textureHeight = 64;

        outerShell = new ModelRenderer(this, 0, 0);
        outerShell.addBox(-8F, -8F, -8F, 16, 16, 16);
        outerShell.setRotationPoint(0F, 16F, 0F);

        midShell = new ModelRenderer(this, 0, 0);
        midShell.addBox(-5F, -5F, -5F, 10, 10, 10);
        midShell.setRotationPoint(0F, 16F, 0F);

        innerShell = new ModelRenderer(this, 0, 0);
        innerShell.addBox(-2F, -2F, -2F, 4, 4, 4);
        innerShell.setRotationPoint(0F, 16F, 0F);

        outerShell2 = new ModelRenderer(this, 0, 0);
        outerShell2.addBox(-8F, -8F, -8F, 16, 16, 16);
        outerShell2.setRotationPoint(0F, 16F, 0F);

        midShell2 = new ModelRenderer(this, 0, 0);
        midShell2.addBox(-5F, -5F, -5F, 10, 10, 10);
        midShell.setRotationPoint(0F, 16F, 0F);

        innerShell2 = new ModelRenderer(this, 0, 0);
        innerShell2.addBox(-2F, -2F, -2F, 4, 4, 4);
        innerShell2.setRotationPoint(0F, 16F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        outerShell.render(f5);
        midShell.render(f5);
        innerShell.render(f5);

        if (entity instanceof EntityTimeDisruption) {
            outerShell2.render(f5);
            midShell2.render(f5);
            innerShell2.render(f5);
        }
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        if (entity instanceof EntityTimeDisruption) {
            EntityTimeDisruption dsr = (EntityTimeDisruption) entity;
            int time = dsr.ticksExisted + dsr.timeToLive;

            innerShell.rotateAngleY = -0.45F + 0.35F * this.simplifyAngle(time + 12, 40.0F);
            innerShell.offsetZ = (float) (Math.sin(Math.PI * this.simplifyAngle(time + 17, 90.0F))) * 0.45F;
            innerShell.offsetX = (float) (Math.sin(Math.PI * this.simplifyAngle(time + 78, 45.0F))) * 0.45F;
            innerShell.offsetY = (float) (Math.sin(Math.PI * this.simplifyAngle(time, 120.0F))) * 0.25F + 0.25F;

            midShell.rotateAngleY = -0.45F + 0.35F * this.simplifyAngle(time, 40.0F);
            midShell.offsetZ = (float) (Math.sin(Math.PI * this.simplifyAngle(time + 46, 120.0F))) * 0.85F;
            midShell.offsetX = (float) (Math.sin(Math.PI * this.simplifyAngle(time + 18, 60.0F))) * 0.85F;
            midShell.offsetY = (float) (Math.sin(Math.PI * this.simplifyAngle(time, 120.0F))) * 0.35F + 0.35F;

            outerShell.rotateAngleY = -0.45F + 0.35F * this.simplifyAngle(time + 25, 40.0F);
            outerShell.offsetZ = (float) (Math.sin(Math.PI * this.simplifyAngle(time + 67, 90.0F))) * 0.45F;
            outerShell.offsetX = (float) (Math.sin(Math.PI * this.simplifyAngle(time + 28, 45.0F))) * 0.45F;
            outerShell.offsetY = (float) (Math.sin(Math.PI * this.simplifyAngle(time, 120.0F))) * 0.25F + 0.25F;

            innerShell2.rotateAngleY = -0.45F + 0.35F * this.simplifyAngle(time + 42, 40.0F);
            innerShell2.offsetZ = (float) (Math.sin(Math.PI * this.simplifyAngle(time - 57, 90.0F))) * 0.45F;
            innerShell2.offsetX = (float) (Math.sin(Math.PI * this.simplifyAngle(time + 18, 45.0F))) * 0.45F;
            innerShell2.offsetY = (float) (Math.sin(Math.PI * this.simplifyAngle(time - 82, 120.0F))) * 0.25F + 0.25F;

            midShell2.rotateAngleY = -0.45F + 0.35F * this.simplifyAngle(time - 7, 40.0F);
            midShell2.offsetZ = (float) (Math.sin(Math.PI * this.simplifyAngle(time - 46, 120.0F))) * 0.85F;
            midShell2.offsetX = (float) (Math.sin(Math.PI * this.simplifyAngle(time - 18, 60.0F))) * 0.85F;
            midShell2.offsetY = (float) (Math.sin(Math.PI * this.simplifyAngle(time + 12, 120.0F))) * 0.35F + 0.35F;

            outerShell2.rotateAngleY = -0.45F + 0.35F * this.simplifyAngle(time - 15, 40.0F);
            outerShell2.offsetZ = (float) (Math.sin(Math.PI * this.simplifyAngle(time - 47, 90.0F))) * 0.45F;
            outerShell2.offsetX = (float) (Math.sin(Math.PI * this.simplifyAngle(time - 18, 45.0F))) * 0.45F;
            outerShell2.offsetY = (float) (Math.sin(Math.PI * this.simplifyAngle(time + 12, 120.0F))) * 0.25F + 0.25F;
        } else {
            midShell.rotateAngleX = (float) (Math.sin(Math.PI * this.simplifyAngle(entity.ticksExisted + 17, 20.0F)));
            midShell.rotateAngleZ = (float) (Math.sin(Math.PI * this.simplifyAngle(entity.ticksExisted + 3, 20.0F)));
            outerShell.rotateAngleX = (float) (Math.sin(Math.PI * this.simplifyAngle(entity.ticksExisted + 16, 20.0F)));
            outerShell.rotateAngleZ = (float) (Math.sin(Math.PI * this.simplifyAngle(entity.ticksExisted + 4, 20.0F)));
        }

    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }

}
