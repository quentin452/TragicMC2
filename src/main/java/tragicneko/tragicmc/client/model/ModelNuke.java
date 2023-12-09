package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelNuke extends ModelBase {

    public ModelRenderer nukeBody;
    public ModelRenderer nukeScreen;

    public ModelNuke() {
        this.textureHeight = 32;
        this.textureWidth = 64;

        nukeBody = new ModelRenderer(this, 0, 0);
        nukeBody.addBox(-2.5F, -1.5F, -2.5F, 5, 22, 5);

        ModelRenderer nukeTop = new ModelRenderer(this, 0, 0);
        nukeTop.addBox(-1.5F, -7.5F, -1.5F, 3, 6, 3);
        nukeBody.addChild(nukeTop);

        ModelRenderer nukeTail = new ModelRenderer(this, 0, 0);
        nukeTail.addBox(-0.5F, 18F, -4.5F, 1, 8, 3);
        nukeBody.addChild(nukeTail);

        ModelRenderer nukeTail2 = new ModelRenderer(this, 0, 0);
        nukeTail2.addBox(-0.5F, 18F, 1.5F, 1, 8, 3);
        nukeBody.addChild(nukeTail2);

        nukeScreen = new ModelRenderer(this, 20, 0);
        nukeScreen.addBox(-1.5F, 8F, -3.5F, 6, 3, 1);
        nukeBody.addChild(nukeScreen);

        ModelRenderer nukeScreen2 = new ModelRenderer(this, 20, 0);
        nukeScreen2.addBox(-1.5F, 11F, -3.5F, 5, 2, 1);
        nukeScreen.addChild(nukeScreen2);

        ModelRenderer nukeWire = new ModelRenderer(this, 37, 0);
        nukeWire.addBox(3F, 9F, -2.5F, 1, 1, 3);
        nukeScreen.addChild(nukeWire);

        ModelRenderer nukeWire2 = new ModelRenderer(this, 37, 0);
        nukeWire2.addBox(2.5F, 9.5F, -0.5F, 1, 2, 1);
        nukeScreen.addChild(nukeWire2);

        ModelRenderer nukeWire3 = new ModelRenderer(this, 48, 0);
        nukeWire3.addBox(-3F, 10F, -3F, 2, 1, 3);
        nukeScreen.addChild(nukeWire3);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        nukeBody.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}
