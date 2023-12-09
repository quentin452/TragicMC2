package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.miniboss.EntityStinQueen;

public class ModelStinQueen extends ModelBase {

    private ModelRenderer body;
    private ModelRenderer head;

    private ModelRenderer legFR;
    private ModelRenderer legFL;
    private ModelRenderer legML;
    private ModelRenderer legMR;
    private ModelRenderer legBR;
    private ModelRenderer legBL;

    private ModelRenderer tail;

    private float[] legValues = new float[] { -0.2230717F, -0.2230717F, -0.0371786F, -0.0371786F, 0.0743572F,
        0.0743572F };

    public ModelStinQueen() {
        textureWidth = 128;
        textureHeight = 128;

        body = new ModelRenderer(this, 22, 0);
        body.addBox(-2F, 0F, -2F, 4, 9, 13);
        body.setRotationPoint(0F, -9F, -2F);
        ModelRenderer bodyThickness = new ModelRenderer(this, 0, 30);
        bodyThickness.addBox(-4F, 2F, -3F, 8, 6, 13);
        body.addChild(bodyThickness);

        head = new ModelRenderer(this, 0, 54);
        head.addBox(-2F, -6F, -7F, 4, 3, 6);
        body.addChild(head);
        ModelRenderer neckThickness = new ModelRenderer(this, 0, 84);
        neckThickness.addBox(-3F, -4F, -8F, 6, 12, 8);
        head.addChild(neckThickness);

        legFR = new ModelRenderer(this, 64, 0);
        legFR.addBox(-5F, -4F, 0F, 2, 22, 3);
        legFR.rotateAngleX = -0.2230717F;
        legFR.rotateAngleZ = 0.0371786F;
        body.addChild(legFR);
        ModelRenderer legTipFR = new ModelRenderer(this, 0, 0);
        legTipFR.addBox(-4.05F, 17F, 1F, 1, 15, 2);
        legFR.addChild(legTipFR);

        legFL = new ModelRenderer(this, 64, 18);
        legFL.addBox(4F, -4F, -1F, 2, 22, 3);
        legFL.rotateAngleX = -0.2230717F;
        legFL.rotateAngleZ = -0.0371786F;
        body.addChild(legFL);
        ModelRenderer legTipFL = new ModelRenderer(this, 10, 0);
        legTipFL.addBox(4.05F, 18F, 0F, 1, 15, 2);
        legFL.addChild(legTipFL);

        legML = new ModelRenderer(this, 64, 20);
        legML.addBox(3F, 0F, 4F, 2, 20, 3);
        legML.rotateAngleX = -0.0371786F;
        legML.rotateAngleZ = -0.1387144F;
        body.addChild(legML);
        ModelRenderer legTipML = new ModelRenderer(this, 10, 0);
        legTipML.addBox(3.95F, 19F, 4.5F, 1, 15, 2);
        legML.addChild(legTipML);

        legMR = new ModelRenderer(this, 64, 0);
        legMR.addBox(-5F, 0F, 4F, 2, 20, 3);
        legMR.rotateAngleX = -0.0371786F;
        legMR.rotateAngleZ = 0.1487144F;
        body.addChild(legMR);
        ModelRenderer legTipMR = new ModelRenderer(this, 0, 0);
        legTipMR.addBox(-3.95F, 19F, 4.5F, 1, 15, 2);
        legMR.addChild(legTipMR);

        legBR = new ModelRenderer(this, 64, 0);
        legBR.addBox(-5F, 0F, 8F, 2, 20, 3);
        legBR.rotateAngleX = 0.0743572F;
        legBR.rotateAngleZ = 0.1115358F;
        body.addChild(legBR);
        ModelRenderer legTipBR = new ModelRenderer(this, 0, 0);
        legTipBR.addBox(-4.95F, 19F, 7.95F, 1, 15, 2);
        legBR.addChild(legTipBR);

        legBL = new ModelRenderer(this, 64, 20);
        legBL.addBox(3F, 0F, 8F, 2, 20, 3);
        legBL.rotateAngleX = 0.0743572F;
        legBL.rotateAngleZ = -0.1115358F;
        body.addChild(legBL);
        ModelRenderer legTipBL = new ModelRenderer(this, 10, 0);
        legTipBL.addBox(3.95F, 19F, 7.95F, 1, 15, 2);
        legBL.addChild(legTipBL);

        tail = new ModelRenderer(this, 60, 52);
        tail.addBox(-3F, 6F, 8F, 6, 4, 5);
        tail.rotateAngleX = 0.0743572F;
        body.addChild(tail);
        ModelRenderer tailThickness = new ModelRenderer(this, 60, 70);
        tailThickness.addBox(-8F, 10F, 6.5F, 16, 16, 16);
        tail.addChild(tailThickness);
        ModelRenderer tailTip = new ModelRenderer(this, 22, 62);
        tailTip.addBox(-5F, 23F, 8F, 10, 6, 11);
        tail.addChild(tailTip);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        body.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        if (!(entity instanceof EntityStinQueen)) return;

        EntityStinQueen stin = (EntityStinQueen) entity;

        if (stin.isGalloping()) {
            float rate = 15.0F;
            float wow = stin.getGallopTicks() % rate;

            body.rotateAngleX = -0.215F + this.simplifyAngle(wow, rate) * 0.235F;
            body.offsetY = this.simplifyAngle(wow, rate) * 0.115F - 0.1F;
            head.rotateAngleX = 0.215F + this.simplifyAngle(wow, rate) * -0.235F;
            head.offsetY = this.simplifyAngle(wow, rate) * 0.0145F;
            head.offsetZ = this.simplifyAngle(wow, rate) * 0.075F - 0.05F;

            tail.rotateAngleX = -0.2230717F + this.simplifyAngle(wow, rate) * -0.145F + 0.715F;
            tail.offsetZ = this.simplifyAngle(wow, rate) * 0.0475F - 0.045F;
            tail.offsetY = this.simplifyAngle(wow, rate) * -0.085F + 0.2F;
            tail.offsetX = 0.0F;

            if (wow >= 5.0F && wow <= 10.0F) {
                legFR.rotateAngleX = legValues[0] + this.simplifyAngle(wow, 3.0F) * 0.1145F;
                legFL.rotateAngleX = legValues[1] + this.simplifyAngle(wow + 1.5F, 3.0F) * 0.1145F;
            } else {
                legFR.rotateAngleX = legValues[0];
                legFL.rotateAngleX = legValues[1];
            }

            legBR.rotateAngleX = -legValues[4] + 0.215F + this.simplifyAngle(wow, rate) * -0.235F;
            legBR.offsetY = this.simplifyAngle(wow, rate) * -0.115F + 0.1F;

            legMR.rotateAngleX = -legValues[3] + 0.215F + this.simplifyAngle(wow, rate) * -0.265F;
            legMR.offsetY = this.simplifyAngle(wow, rate) * -0.0675F + 0.05F;

            legBL.rotateAngleX = legValues[5] + 0.215F + this.simplifyAngle(wow, rate) * -0.235F;
            legBL.offsetY = this.simplifyAngle(wow, rate) * -0.115F + 0.1F;

            legML.rotateAngleX = legValues[2] + 0.215F + this.simplifyAngle(wow, rate) * -0.265F;
            legML.offsetY = this.simplifyAngle(wow, rate) * -0.0675F + 0.05F;
        } else {
            body.offsetY = head.offsetY = head.offsetZ = tail.offsetZ = tail.offsetY = tail.offsetX = 0.0F;
            body.rotateAngleX = head.rotateAngleX = tail.rotateAngleX = 0.0F;
            legFR.offsetY = legFL.offsetY = legBR.offsetY = legBL.offsetY = legMR.offsetY = legML.offsetY = 0.0F;

            head.rotateAngleY = f3 / (180F / (float) Math.PI);
            head.rotateAngleX = f4 / (180F / (float) Math.PI);

            tail.rotateAngleX = this.simplifyAngle(stin.ticksExisted, 40.0F) * 0.0245F + 0.2230717F;

            legFR.rotateAngleX = legValues[0] + this.simplifyAngle(f, 3.0F) * 0.093F * f1;
            legMR.rotateAngleX = legValues[3] + this.simplifyAngle(f + 0.5F, 3.0F) * 0.093F * f1;
            legBR.rotateAngleX = legValues[4] + this.simplifyAngle(f + 1.0F, 3.0F) * 0.093F * f1;

            legFL.rotateAngleX = legValues[1] + this.simplifyAngle(f + 2.0F, 3.0F) * 0.093F * f1;
            legML.rotateAngleX = legValues[2] + this.simplifyAngle(f + 2.0F, 3.0F) * 0.093F * f1;
            legBL.rotateAngleX = legValues[5] + this.simplifyAngle(f + 3.0F, 3.0F) * 0.093F * f1;
        }
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }
}
