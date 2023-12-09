package tragicneko.tragicmc.client.model.weapon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSilentHellraiser extends ModelBase {

    private ModelRenderer hellraiser;

    public ModelSilentHellraiser() {
        textureWidth = 96;
        textureHeight = 32;

        hellraiser = new ModelRenderer(this, 0, 0);
        hellraiser.addBox(-1.5F, -8F, -1.5F, 3, 4, 3);
        hellraiser.setRotationPoint(16F, 8F, -3F);

        ModelRenderer hellraiserHandle = new ModelRenderer(this, 0, 0);
        hellraiserHandle.addBox(-1F, -4F, -1F, 2, 4, 2);
        hellraiser.addChild(hellraiserHandle);

        ModelRenderer hellraiserHandle2 = new ModelRenderer(this, 0, 0);
        hellraiserHandle2.addBox(-1.5F, 0F, -1.5F, 3, 2, 3);
        hellraiser.addChild(hellraiserHandle2);

        ModelRenderer hellraiserHandle3 = new ModelRenderer(this, 0, 0);
        hellraiserHandle3.addBox(-1F, 1F, -4.5F, 2, 2, 3);
        hellraiser.addChild(hellraiserHandle3);

        ModelRenderer hellraiserHandle4 = new ModelRenderer(this, 0, 0);
        hellraiserHandle4.addBox(-1.5F, 1F, 1.5F, 3, 2, 3);
        hellraiser.addChild(hellraiserHandle4);

        ModelRenderer hellraiserHandle5 = new ModelRenderer(this, 0, 0);
        hellraiserHandle5.addBox(-1F, 3F, 3.5F, 2, 2, 3);
        hellraiser.addChild(hellraiserHandle5);

        ModelRenderer hellraiserHandle6 = new ModelRenderer(this, 0, 0);
        hellraiserHandle6.addBox(-1F, 5F, 2.5F, 2, 2, 2);
        hellraiser.addChild(hellraiserHandle6);

        ModelRenderer hellraiserBlade = new ModelRenderer(this, 32, 0);
        hellraiserBlade.addBox(-1F, 2F, -0.5F, 2, 4, 2);
        hellraiser.addChild(hellraiserBlade);

        ModelRenderer hellraiserBlade2 = new ModelRenderer(this, 32, 0);
        hellraiserBlade2.addBox(-1.5F, 6F, -1.5F, 3, 5, 3);
        hellraiser.addChild(hellraiserBlade2);

        ModelRenderer hellraiserBlade3 = new ModelRenderer(this, 32, 0);
        hellraiserBlade3.addBox(-1.5F, 11F, -4.5F, 3, 3, 8);
        hellraiser.addChild(hellraiserBlade3);

        ModelRenderer hellraiserBlade4 = new ModelRenderer(this, 32, 0);
        hellraiserBlade4.addBox(-1.5F, 14F, -6.5F, 3, 3, 6);
        hellraiser.addChild(hellraiserBlade4);

        ModelRenderer hellraiserBlade5 = new ModelRenderer(this, 32, 0);
        hellraiserBlade5.addBox(-1.5F, 14F, 1F, 3, 3, 2);
        hellraiser.addChild(hellraiserBlade5);

        ModelRenderer hellraiserBlade5b = new ModelRenderer(this, 32, 0);
        hellraiserBlade5b.addBox(-1F, 17F, 1.5F, 2, 4, 2);
        hellraiser.addChild(hellraiserBlade5b);

        ModelRenderer hellraiserBlade5c = new ModelRenderer(this, 32, 0);
        hellraiserBlade5c.addBox(-1F, 21F, 1F, 2, 2, 1);
        hellraiser.addChild(hellraiserBlade5c);

        ModelRenderer hellraiserBlade6 = new ModelRenderer(this, 32, 0);
        hellraiserBlade6.addBox(-1.5F, 17F, -6F, 3, 3, 5);
        hellraiser.addChild(hellraiserBlade6);

        ModelRenderer hellraiserBlade7 = new ModelRenderer(this, 32, 0);
        hellraiserBlade7.addBox(-1F, 20F, -5F, 2, 4, 4);
        hellraiser.addChild(hellraiserBlade7);

        ModelRenderer hellraiserBlade8 = new ModelRenderer(this, 32, 0);
        hellraiserBlade8.addBox(-1F, 24F, -4F, 2, 4, 3);
        hellraiser.addChild(hellraiserBlade8);

        ModelRenderer hellraiserBlade9 = new ModelRenderer(this, 32, 0);
        hellraiserBlade9.addBox(-0.5F, 28F, -3F, 1, 4, 3);
        hellraiser.addChild(hellraiserBlade9);

        ModelRenderer hellraiserBlade10 = new ModelRenderer(this, 32, 0);
        hellraiserBlade10.addBox(-0.5F, 32F, -1.5F, 1, 4, 2);
        hellraiser.addChild(hellraiserBlade10);

        ModelRenderer hellraiserBlade11 = new ModelRenderer(this, 32, 0);
        hellraiserBlade11.addBox(-0.5F, 36F, -1F, 1, 2, 1);
        hellraiser.addChild(hellraiserBlade11);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        hellraiser.render(f5);
    }
}
