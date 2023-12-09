package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.boss.EntityClaymation;
import tragicneko.tragicmc.entity.miniboss.EntityStinKing;

public class ModelStinKing extends ModelBase {

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

    public ModelStinKing() {
        textureWidth = 128;
        textureHeight = 128;

        // Body
        body = new ModelRenderer(this, 22, 0);
        body.addBox(-2F, 0F, -2F, 4, 12, 13);
        body.setRotationPoint(0F, -9F, -2F);
        ModelRenderer bodyThickness = new ModelRenderer(this, 0, 30);
        bodyThickness.addBox(-4F, 2F, 0F, 8, 8, 13);
        body.addChild(bodyThickness);

        // Back spikes
        ModelRenderer backSpike = new ModelRenderer(this, 0, 18);
        backSpike.addBox(0F, -3F, 6F, 2, 11, 2);
        backSpike.rotateAngleX = 0.2230717F;
        backSpike.rotateAngleZ = 0.2230717F;
        body.addChild(backSpike);
        ModelRenderer backSpike2 = new ModelRenderer(this, 0, 18);
        backSpike2.addBox(-4F, -8F, 5F, 2, 10, 2);
        backSpike2.rotateAngleZ = -0.2230717F;
        body.addChild(backSpike2);
        ModelRenderer backSpike3 = new ModelRenderer(this, 0, 18);
        backSpike3.addBox(1F, -6F, 10F, 2, 11, 2);
        backSpike3.rotateAngleX = -0.1115358F;
        backSpike3.rotateAngleZ = -0.1115358F;
        body.addChild(backSpike3);

        // Head and neck spikes
        head = new ModelRenderer(this, 0, 54);
        head.addBox(-2F, -14F, -5F, 4, 9, 6);
        body.addChild(head);
        ModelRenderer neckThickness = new ModelRenderer(this, 0, 84);
        neckThickness.addBox(-3F, -6F, -6F, 6, 14, 8);
        head.addChild(neckThickness);
        ModelRenderer neckThickness2 = new ModelRenderer(this, 32, 80);
        neckThickness2.addBox(-1F, -23F, -4F, 2, 17, 6);
        head.addChild(neckThickness2);

        // Tusks, Horns and Horn tips
        ModelRenderer tuskRight = new ModelRenderer(this, 0, 18);
        tuskRight.addBox(-3F, -12F, -5F, 2, 15, 3);
        tuskRight.rotateAngleX = -0.2974289F;
        tuskRight.rotateAngleZ = -0.1858931F;
        head.addChild(tuskRight);
        ModelRenderer tuskLeft = new ModelRenderer(this, 0, 18);
        tuskLeft.addBox(1F, -12F, -5F, 2, 15, 3);
        tuskLeft.rotateAngleX = -0.2974289F;
        tuskLeft.rotateAngleZ = 0.185931F;
        head.addChild(tuskLeft);
        ModelRenderer tuskLeftTip = new ModelRenderer(this, 0, 18);
        tuskLeftTip.addBox(1F, -27F, -5F, 1, 15, 2);
        tuskLeft.addChild(tuskLeftTip);
        ModelRenderer tuskRightTip = new ModelRenderer(this, 0, 18);
        tuskRightTip.addBox(-2F, -27F, -5F, 1, 15, 2);
        tuskRight.addChild(tuskRightTip);

        ModelRenderer horn = new ModelRenderer(this, 0, 18);
        horn.addBox(-1F, -14F, -7F, 2, 14, 3);
        horn.rotateAngleX = 0.5205006F;
        head.addChild(horn);
        ModelRenderer hornTip = new ModelRenderer(this, 0, 18);
        hornTip.addBox(-1F, -26F, -7F, 2, 12, 2);
        horn.addChild(hornTip);
        ModelRenderer neckSpike = new ModelRenderer(this, 0, 18);
        neckSpike.addBox(-1.05F, -16F, -2F, 2, 7, 2);
        neckSpike.rotateAngleX = 0.3717861F;
        head.addChild(neckSpike);
        ModelRenderer neckSpikeTip = new ModelRenderer(this, 0, 18);
        neckSpikeTip.addBox(-1.05F, -21F, -2F, 2, 5, 1);
        neckSpike.addChild(neckSpikeTip);

        // Legs
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
        legML.rotateAngleZ = -0.1487144F;
        body.addChild(legML);
        ModelRenderer legTipML = new ModelRenderer(this, 10, 0);
        legTipML.addBox(2.95F, 19F, 4.5F, 1, 15, 2);
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
        legBL.rotateAngleX = 0.1115358F;
        legBL.rotateAngleZ = -0.1115358F;
        body.addChild(legBL);
        ModelRenderer legTipBL = new ModelRenderer(this, 10, 0);
        legTipBL.addBox(3.95F, 19F, 7.95F, 1, 15, 2);
        legBL.addChild(legTipBL);

        // Tail
        tail = new ModelRenderer(this, 32, 56);
        tail.addBox(-3F, 6F, 12F, 6, 4, 5);
        tail.rotateAngleX = 0.2230717F;
        body.addChild(tail);
        ModelRenderer tailThickness = new ModelRenderer(this, 60, 56);
        tailThickness.addBox(-6F, 10F, 11.5F, 12, 14, 8);
        tail.addChild(tailThickness);
        ModelRenderer tailTip = new ModelRenderer(this, 32, 66);
        tailTip.addBox(-4F, 24F, 13F, 8, 4, 4);
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
        if (!(entity instanceof EntityStinKing) && !(entity instanceof EntityClaymation)) return;

        int gallop = 0;
        int charge = 0;

        if (entity instanceof EntityStinKing) {
            EntityStinKing stin = (EntityStinKing) entity;
            gallop = stin.getGallopTicks();
            charge = stin.getChargeTicks();
        } else {
            EntityClaymation clay = (EntityClaymation) entity;
            gallop = clay.getUtilityInt();
            charge = clay.getUtilityInt2();
        }

        if (gallop > 0) {
            float rate = 15.0F;
            float wow = gallop % rate;

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

            if (charge >= 170) {
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

                tail.rotateAngleX = this.simplifyAngle(entity.ticksExisted, 40.0F) * 0.0245F + 0.2230717F;

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
