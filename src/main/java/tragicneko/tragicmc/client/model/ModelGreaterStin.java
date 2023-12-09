package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.miniboss.EntityGreaterStin;

public class ModelGreaterStin extends ModelBase {

    private ModelRenderer body;
    private ModelRenderer head;

    private ModelRenderer legFR;
    private ModelRenderer legFL;
    private ModelRenderer legML;
    private ModelRenderer legMR;
    private ModelRenderer legBL;
    private ModelRenderer legBR;

    private ModelRenderer tail;
    private ModelRenderer tailThickness;

    private float[] legValues = new float[] { -0.2230717F, -0.2230717F, -0.0371786F, -0.0371786F, 0.0743572F,
        0.0743572F };

    public ModelGreaterStin() {
        textureWidth = 128;
        textureHeight = 128;

        // Body, Body thickness and Body spikes
        body = new ModelRenderer(this, 22, 0);
        body.addBox(-2F, 0F, -2F, 4, 10, 13);
        body.setRotationPoint(0F, -7F, -2F);
        ModelRenderer bodyThickness = new ModelRenderer(this, 0, 30);
        bodyThickness.addBox(-4F, 2F, 0F, 8, 8, 13);
        body.addChild(bodyThickness);

        ModelRenderer backSpike = new ModelRenderer(this, 0, 18);
        backSpike.addBox(0F, -6F, 5F, 2, 8, 2);
        backSpike.rotateAngleX = 0.2230717F;
        backSpike.rotateAngleZ = 0.2230717F;
        body.addChild(backSpike);
        ModelRenderer backSpike2 = new ModelRenderer(this, 0, 18);
        backSpike2.addBox(-4F, -8F, 2F, 2, 10, 2);
        backSpike2.rotateAngleZ = -0.2230717F;
        body.addChild(backSpike2);
        ModelRenderer backSpike3 = new ModelRenderer(this, 0, 18);
        backSpike3.addBox(2F, -4F, 10F, 2, 6, 2);
        backSpike3.rotateAngleX = -0.1115358F;
        backSpike3.rotateAngleZ = -0.3346075F;
        body.addChild(backSpike3);

        // Head, Neck thicknesses and spikes
        head = new ModelRenderer(this, 0, 54);
        head.addBox(-2F, -14F, -4F, 4, 20, 6);
        body.addChild(head);
        ModelRenderer neckThickness = new ModelRenderer(this, 0, 84);
        neckThickness.addBox(-3F, -4F, -3F, 6, 14, 6);
        head.addChild(neckThickness);
        ModelRenderer neckThickness2 = new ModelRenderer(this, 32, 80);
        neckThickness2.addBox(-1F, -18F, -5F, 2, 17, 6);
        head.addChild(neckThickness2);

        ModelRenderer neckSpike = new ModelRenderer(this, 0, 18);
        neckSpike.addBox(-1F, -11F, -5F, 2, 12, 2);
        neckSpike.rotateAngleX = 0.5205006F;
        head.addChild(neckSpike);
        ModelRenderer neckSpike2 = new ModelRenderer(this, 0, 18);
        neckSpike2.addBox(-1F, -15F, -1F, 2, 7, 2);
        neckSpike2.rotateAngleX = 0.5205006F;
        head.addChild(neckSpike2);

        // Front Right leg
        legFR = new ModelRenderer(this, 64, 0);
        legFR.addBox(-5F, 4F, 0F, 2, 15, 3);
        legFR.rotateAngleX = -0.2230717F;
        legFR.rotateAngleZ = 0.0371786F;
        body.addChild(legFR);
        ModelRenderer legTipFR = new ModelRenderer(this, 0, 0);
        legTipFR.addBox(-3F, 19F, -1F, 1, 15, 2);
        legTipFR.rotateAngleX = 0.1115358F;
        legTipFR.rotateAngleZ = 0.0743572F;
        legFR.addChild(legTipFR);

        // Front Left leg
        legFL = new ModelRenderer(this, 64, 20);
        legFL.addBox(2F, 4F, -1F, 2, 15, 3);
        legFL.rotateAngleX = -0.2230717F;
        legFL.rotateAngleZ = -0.0371786F;
        body.addChild(legFL);
        ModelRenderer legTipFL = new ModelRenderer(this, 10, 0);
        legTipFL.addBox(1.5F, 19F, -2.5F, 1, 15, 2);
        legTipFL.rotateAngleX = 0.1115358F;
        legTipFL.rotateAngleZ = -0.0743572F;
        legFL.addChild(legTipFL);

        // Mid Left leg
        legML = new ModelRenderer(this, 64, 20);
        legML.addBox(4F, 4F, 4F, 2, 15, 3);
        legML.rotateAngleX = -0.0371786F;
        legML.rotateAngleZ = -0.1487144F;
        body.addChild(legML);
        ModelRenderer legTipML = new ModelRenderer(this, 10, 0);
        legTipML.addBox(3.5F, 19F, 3.5F, 1, 15, 2);
        legTipML.rotateAngleX = 0.0743572F;
        legTipML.rotateAngleZ = -0.0371786F;
        legML.addChild(legTipML);

        // Mid Right leg
        legMR = new ModelRenderer(this, 64, 0);
        legMR.addBox(-5F, 4F, 4F, 2, 15, 3);
        legMR.rotateAngleX = -0.0371786F;
        legMR.rotateAngleZ = 0.1487144F;
        body.addChild(legMR);
        ModelRenderer legTipMR = new ModelRenderer(this, 0, 0);
        legTipMR.addBox(-4F, 19F, 3.5F, 1, 15, 2);
        legTipMR.rotateAngleX = 0.0743572F;
        legTipMR.rotateAngleZ = 0.0371786F;
        legMR.addChild(legTipMR);

        // Back Right leg
        legBR = new ModelRenderer(this, 64, 0);
        legBR.addBox(-5F, 4F, 8F, 2, 15, 3);
        legBR.rotateAngleX = 0.0743572F;
        legBR.rotateAngleZ = 0.1115358F;
        body.addChild(legBR);
        ModelRenderer legTipBR = new ModelRenderer(this, 0, 0);
        legTipBR.addBox(-4.5F, 19F, 6F, 1, 15, 2);
        legTipBR.rotateAngleX = 0.1487144F;
        legBR.addChild(legTipBR);

        // Back Left leg
        legBL = new ModelRenderer(this, 64, 20);
        legBL.addBox(4F, 4F, 8F, 2, 15, 3);
        legBL.rotateAngleX = 0.0743572F;
        legBL.rotateAngleZ = -0.1115358F;
        body.addChild(legBL);
        ModelRenderer legTipBL = new ModelRenderer(this, 10, 0);
        legTipBL.addBox(4F, 19F, 6F, 1, 15, 2);
        legTipBL.rotateAngleX = 0.1487144F;
        legBL.addChild(legTipBL);

        // Tail, thickness and tip
        tail = new ModelRenderer(this, 32, 56);
        tail.addBox(-3F, 5F, 12F, 6, 4, 4);
        tail.rotateAngleX = 0.2230717F;
        body.addChild(tail);
        tailThickness = new ModelRenderer(this, 60, 56);
        tailThickness.addBox(-6F, 11F, 11.5F, 12, 15, 6);
        tailThickness.rotateAngleX = 0.2230717F;
        tail.addChild(tailThickness);
        ModelRenderer tailTip = new ModelRenderer(this, 32, 66);
        tailTip.addBox(-4F, 26F, 13F, 8, 7, 4);
        tailThickness.addChild(tailTip);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        body.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        if (!(entity instanceof EntityGreaterStin)) return;

        EntityGreaterStin stin = (EntityGreaterStin) entity;

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

            if (stin.isCharging() && stin.getChargeTicks() >= 170) {
                body.rotateAngleX = 0.2F;
                legFL.rotateAngleX = legValues[1] - 0.2F;
                legFR.rotateAngleX = legValues[0] - 0.2F;
                legML.rotateAngleX = legValues[2] - 0.2F;
                legMR.rotateAngleX = legValues[3] - 0.2F;
                legBR.rotateAngleX = legValues[4] - 0.2F;
                legBL.rotateAngleX = legValues[5] - 0.2F;
            } else {
                head.rotateAngleY = f3 / (180F / (float) Math.PI);
                head.rotateAngleX = f4 / (180F / (float) Math.PI);

                tail.rotateAngleX = this.simplifyAngle(stin.ticksExisted, 40.0F) * 0.0245F + 0.2230717F;
                tailThickness.rotateAngleX = this.simplifyAngle(stin.ticksExisted, 80.0F) * 0.0445F + 0.2230717F;

                legFR.rotateAngleX = legValues[0] + this.simplifyAngle(f, 3.0F) * 0.093F * f1;
                legMR.rotateAngleX = legValues[3] + this.simplifyAngle(f + 0.5F, 3.0F) * 0.093F * f1;
                legBR.rotateAngleX = legValues[4] + this.simplifyAngle(f + 1.0F, 3.0F) * 0.093F * f1;

                legFL.rotateAngleX = legValues[1] + this.simplifyAngle(f + 2.0F, 3.0F) * 0.093F * f1;
                legML.rotateAngleX = legValues[2] + this.simplifyAngle(f + 2.0F, 3.0F) * 0.093F * f1;
                legBL.rotateAngleX = legValues[5] + this.simplifyAngle(f + 3.0F, 3.0F) * 0.093F * f1;
            }
        }
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }
}
