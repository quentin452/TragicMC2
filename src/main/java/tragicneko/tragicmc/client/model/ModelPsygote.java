package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.mob.EntityPsygote;

public class ModelPsygote extends ModelBase {

    private ModelRenderer center;
    private ModelRenderer head;
    private ModelRenderer neck;
    private ModelRenderer body;
    private ModelRenderer legRight;
    private ModelRenderer legLeft;
    private ModelRenderer armRight;
    private ModelRenderer armLeft;

    public ModelPsygote() {
        textureWidth = 64;
        textureHeight = 32;

        center = new ModelRenderer(this, 0, 0);
        center.addBox(0F, 0F, 0F, 0, 0, 0);
        center.setRotationPoint(0F, 2F, 0F);

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -6F, 2F, 8, 8, 8);
        head.rotateAngleX = 0.2230717F;
        center.addChild(head);

        neck = new ModelRenderer(this, 20, 20);
        neck.addBox(-0.5F, 5F, 0F, 1, 1, 3);
        neck.rotateAngleX = 0.9294653F;
        center.addChild(neck);

        body = new ModelRenderer(this, 0, 20);
        body.addBox(-2F, 4F, -1.5F, 4, 2, 4);
        body.rotateAngleX = 0.4204006F;
        center.addChild(body);

        legRight = new ModelRenderer(this, 36, 16);
        legRight.addBox(-2.5F, 4F, -2F, 2, 2, 5);
        legRight.rotateAngleX = -0.3346075F;
        center.addChild(legRight);

        legLeft = new ModelRenderer(this, 36, 16);
        legLeft.addBox(0.5F, 4F, -2F, 2, 2, 5);
        legLeft.rotateAngleX = -0.3346075F;
        center.addChild(legLeft);

        armRight = new ModelRenderer(this, 36, 0);
        armRight.addBox(-4F, -5F, 2F, 2, 4, 2);
        armRight.rotateAngleX = -1.673038F;
        center.addChild(armRight);

        armLeft = new ModelRenderer(this, 36, 0);
        armLeft.addBox(2F, -5F, 2F, 2, 4, 2);
        armLeft.rotateAngleX = -1.673038F;
        center.addChild(armLeft);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        center.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        EntityPsygote psy = (EntityPsygote) entity;

        center.offsetY = (float) (Math.sin(Math.PI + this.simplifyAngle(psy.ticksExisted, 60.0F)) * 0.12F + 0.25F);

        if (psy.getFiringTicks() >= 40 || psy.getSwitchTicks() > 0) {
            center.rotateAngleX = this.simplifyAngle(psy.ticksExisted, 5.0F) * 0.16F;
            center.rotateAngleY = ((psy.ticksExisted * 5.25F) % 180.0F) - 90F;
        }

        if (psy.getHurtTime() > 0) {
            center.rotateAngleX = 0.2230717F + this.simplifyAngle(psy.getHurtTime(), 10.0F) * -0.112F;
        }
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }
}
