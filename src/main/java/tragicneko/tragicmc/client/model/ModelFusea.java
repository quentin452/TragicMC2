package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelFusea - TragicNeko
 * Created using Tabula 4.1.1
 */
public class ModelFusea extends ModelBase {

    public ModelRenderer core;
    public ModelRenderer outershell;
    public ModelRenderer isotope;
    public ModelRenderer isotope_1;
    public ModelRenderer isotope_2;
    public ModelRenderer isotope_3;

    public ModelFusea() {

        this.textureWidth = 64;
        this.textureHeight = 64;

        this.core = new ModelRenderer(this, 32, 0);
        this.core.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.core.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);

        this.outershell = new ModelRenderer(this, 0, 32);
        this.outershell.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
        this.core.addChild(this.outershell);

        this.isotope = new ModelRenderer(this, 0, 0);
        this.isotope.addBox(-12.0F, -6.5F, -4.0F, 6, 6, 6, 0.0F);
        this.core.addChild(this.isotope);

        this.isotope_1 = new ModelRenderer(this, 0, 0);
        this.isotope_1.addBox(4.0F, 3.0F, 5.5F, 6, 6, 6, 0.0F);
        this.core.addChild(this.isotope_1);

        this.isotope_2 = new ModelRenderer(this, 0, 0);
        this.isotope_2.addBox(-2.0F, -4.0F, -12.0F, 6, 6, 6, 0.0F);
        this.core.addChild(this.isotope_2);

        this.isotope_3 = new ModelRenderer(this, 0, 0);
        this.isotope_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.isotope_3.addBox(0.0F, -12.0F, 4.0F, 6, 6, 6, 0.0F);
        this.core.addChild(this.isotope_3);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.core.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        this.core.rotateAngleY = 360.0F
            + this.simplifyAngle(entity.ticksExisted + entity.getEntityId() - 23, 120.0F) * -360.0F;
        this.core.rotateAngleX = 180.0F
            + this.simplifyAngle(entity.ticksExisted + entity.getEntityId() + 23, 120.0F) * -180.0F;

        this.outershell.offsetX = 0.15F + this.simplifyAngle(entity.ticksExisted + entity.getEntityId(), 10.0F) * -0.3F;
        this.outershell.offsetY = 0.15F
            + this.simplifyAngle(entity.ticksExisted + entity.getEntityId() - 3, 10.0F) * -0.3F;
        this.outershell.offsetZ = 0.15F
            + this.simplifyAngle(entity.ticksExisted + entity.getEntityId() + 4, 10.0F) * -0.3F;

        this.isotope.rotateAngleY = 360.0F
            + this.simplifyAngle(entity.ticksExisted + entity.getEntityId(), 120.0F) * -360.0F;
        this.isotope.rotateAngleX = 180.0F
            + this.simplifyAngle(entity.ticksExisted + entity.getEntityId(), 120.0F) * -180.0F;

        this.isotope_1.rotateAngleY = 360.0F
            + this.simplifyAngle(entity.ticksExisted + entity.getEntityId() - 142, 120.0F) * -360.0F;
        this.isotope_1.rotateAngleX = 180.0F
            + this.simplifyAngle(entity.ticksExisted + entity.getEntityId() + 14, 120.0F) * -180.0F;

        this.isotope_2.rotateAngleY = 360.0F
            + this.simplifyAngle(entity.ticksExisted + entity.getEntityId() - 24, 120.0F) * -360.0F;
        this.isotope_2.rotateAngleX = 180.0F
            + this.simplifyAngle(entity.ticksExisted + entity.getEntityId() + 111, 120.0F) * -180.0F;

        this.isotope_3.rotateAngleY = 360.0F
            + this.simplifyAngle(entity.ticksExisted + entity.getEntityId() - 77, 120.0F) * -360.0F;
        this.isotope_3.rotateAngleX = 180.0F
            + this.simplifyAngle(entity.ticksExisted + entity.getEntityId() + 43, 120.0F) * -180.0F;
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }
}
