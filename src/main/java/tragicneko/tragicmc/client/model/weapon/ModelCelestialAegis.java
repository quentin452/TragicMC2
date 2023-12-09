package tragicneko.tragicmc.client.model.weapon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCelestialAegis extends ModelBase {

    public ModelRenderer aegis;

    public ModelCelestialAegis() {
        textureWidth = 96;
        textureHeight = 32;

        aegis = new ModelRenderer(this, 32, 0);
        aegis.addBox(-2F, -5F, -0.5F, 5, 4, 2);
        aegis.setRotationPoint(16F, 4F, -3F);

        ModelRenderer aegis2 = new ModelRenderer(this, 32, 0);
        aegis2.addBox(-1F, -1F, 0F, 2, 6, 1);
        aegis.addChild(aegis2);

        ModelRenderer aegis3 = new ModelRenderer(this, 0, 0);
        aegis3.addBox(-3F, 5F, -1F, 7, 4, 4);
        aegis.addChild(aegis3);

        ModelRenderer aegis4 = new ModelRenderer(this, 64, 0);
        aegis4.addBox(-8F, 4F, -1F, 4, 2, 4);
        aegis.addChild(aegis4);

        ModelRenderer aegis5 = new ModelRenderer(this, 64, 0);
        aegis5.addBox(4F, 4F, -1F, 4, 2, 4);
        aegis.addChild(aegis5);

        ModelRenderer aegis6 = new ModelRenderer(this, 64, 0);
        aegis6.addBox(-12F, 2F, -1F, 6, 2, 4);
        aegis.addChild(aegis6);

        ModelRenderer aegis7 = new ModelRenderer(this, 64, 0);
        aegis7.addBox(7F, 2F, -1F, 6, 2, 4);
        aegis.addChild(aegis7);

        ModelRenderer aegis8 = new ModelRenderer(this, 64, 0);
        aegis8.addBox(-18F, 8F, -1F, 10, 2, 4);
        aegis.addChild(aegis8);

        ModelRenderer aegis9 = new ModelRenderer(this, 64, 0);
        aegis9.addBox(10F, 8F, -1F, 10, 2, 4);
        aegis.addChild(aegis9);

        ModelRenderer aegisBlade = new ModelRenderer(this, 42, 0);
        aegisBlade.addBox(-9F, 9F, -0.5F, 2, 48, 3);
        aegis.addChild(aegisBlade);

        ModelRenderer aegisBladeb = new ModelRenderer(this, 36, 0);
        aegisBladeb.addBox(-7F, 7F, -0.5F, 4, 2, 3);
        aegis.addChild(aegisBladeb);

        ModelRenderer aegisBladec = new ModelRenderer(this, 84, 0);
        aegisBladec.addBox(-7F, 29F, 0F, 2, 36, 1);
        aegis.addChild(aegisBladec);

        ModelRenderer aegisBladed = new ModelRenderer(this, 74, 0);
        aegisBladed.addBox(-5F, 9F, 0F, 2, 54, 1);
        aegis.addChild(aegisBladed);

        ModelRenderer aegisBladee = new ModelRenderer(this, 72, 0);
        aegisBladee.addBox(-3F, 9F, 0F, 1, 36, 1);
        aegis.addChild(aegisBladee);

        ModelRenderer aegisBladef = new ModelRenderer(this, 67, 0);
        aegisBladef.addBox(-2F, 9F, 0F, 1, 24, 1);
        aegis.addChild(aegisBladef);

        ModelRenderer aegisBladeg = new ModelRenderer(this, 37, 0);
        aegisBladeg.addBox(-11F, 11F, -0.5F, 2, 36, 3);
        aegis.addChild(aegisBladeg);

        ModelRenderer aegisBladeh = new ModelRenderer(this, 33, 0);
        aegisBladeh.addBox(-7F, 9F, -0.5F, 2, 20, 3);
        aegis.addChild(aegisBladeh);

        ModelRenderer aegisBlade2 = new ModelRenderer(this, 52, 0);
        aegisBlade2.addBox(7F, 9F, -0.5F, 2, 48, 3);
        aegis.addChild(aegisBlade2);

        ModelRenderer aegisBlade2b = new ModelRenderer(this, 42, 0);
        aegisBlade2b.addBox(3F, 7F, -0.5F, 4, 2, 3);
        aegis.addChild(aegisBlade2b);

        ModelRenderer aegisBlade2c = new ModelRenderer(this, 66, 0);
        aegisBlade2c.addBox(5F, 29F, 0F, 2, 36, 1);
        aegis.addChild(aegisBlade2c);

        ModelRenderer aegisBlade2d = new ModelRenderer(this, 74, 0);
        aegisBlade2d.addBox(3F, 9F, 0F, 2, 54, 1);
        aegis.addChild(aegisBlade2d);

        ModelRenderer aegisBlade2e = new ModelRenderer(this, 64, 0);
        aegisBlade2e.addBox(2F, 9F, 0F, 1, 36, 1);
        aegis.addChild(aegisBlade2e);

        ModelRenderer aegisBlade2f = new ModelRenderer(this, 68, 0);
        aegisBlade2f.addBox(1F, 9F, 0F, 1, 24, 1);
        aegis.addChild(aegisBlade2f);

        ModelRenderer aegisBlade2g = new ModelRenderer(this, 37, 0);
        aegisBlade2g.addBox(9F, 11F, -0.5F, 2, 36, 3);
        aegis.addChild(aegisBlade2g);

        ModelRenderer aegisBlade2h = new ModelRenderer(this, 33, 0);
        aegisBlade2h.addBox(5F, 9F, -0.5F, 2, 20, 3);
        aegis.addChild(aegisBlade2h);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        aegis.render(f5);
    }
}
