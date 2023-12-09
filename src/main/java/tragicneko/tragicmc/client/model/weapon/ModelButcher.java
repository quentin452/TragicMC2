package tragicneko.tragicmc.client.model.weapon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelButcher extends ModelBase {

    private ModelRenderer butcher;

    public ModelButcher() {
        textureWidth = 96;
        textureHeight = 32;

        butcher = new ModelRenderer(this, 0, 0);
        butcher.addBox(0F, -8F, 0F, 2, 36, 2);
        butcher.setRotationPoint(16F, -4F, -3F);

        ModelRenderer butcherBlade = new ModelRenderer(this, 16, 0);
        butcherBlade.addBox(1F, 13F, -0.5F, 5, 26, 3);
        butcher.addChild(butcherBlade);

        ModelRenderer butcherBlade2 = new ModelRenderer(this, 24, 0);
        butcherBlade2.addBox(-1F, 39F, -0.5F, 7, 26, 3);
        butcher.addChild(butcherBlade2);

        ModelRenderer butcherBlade3 = new ModelRenderer(this, 32, 0);
        butcherBlade3.addBox(-3F, 65F, -0.5F, 9, 3, 3);
        butcher.addChild(butcherBlade3);

        ModelRenderer butcherBlade4 = new ModelRenderer(this, 48, 0);
        butcherBlade4.addBox(-6, 68F, -0.5F, 12, 3, 3);
        butcher.addChild(butcherBlade4);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        butcher.render(f5);
    }
}
