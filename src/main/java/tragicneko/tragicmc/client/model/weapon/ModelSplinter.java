package tragicneko.tragicmc.client.model.weapon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSplinter extends ModelBase {

    public ModelRenderer splinter;

    public ModelSplinter() {
        textureWidth = 96;
        textureHeight = 32;

        splinter = new ModelRenderer(this, 16, 0);
        splinter.addBox(0F, -5F, 0F, 2, 8, 1);
        splinter.setRotationPoint(16F, 4F, -3F);

        ModelRenderer splinterBlade7 = new ModelRenderer(this, 16, 0);
        splinterBlade7.addBox(-2F, 3F, 0F, 2, 6, 2);
        splinter.addChild(splinterBlade7);

        ModelRenderer splinterBlade6 = new ModelRenderer(this, 16, 0);
        splinterBlade6.addBox(-3F, 9F, 0F, 6, 8, 2);
        splinter.addChild(splinterBlade6);

        ModelRenderer splinterBlade = new ModelRenderer(this, 16, 0);
        splinterBlade.addBox(-5F, 17F, 0F, 7, 12, 2);
        splinter.addChild(splinterBlade);

        ModelRenderer splinterBlade2 = new ModelRenderer(this, 24, 0);
        splinterBlade2.addBox(-3F, 29F, 0F, 5, 13, 2);
        splinter.addChild(splinterBlade2);

        ModelRenderer splinterBlade3 = new ModelRenderer(this, 24, 0);
        splinterBlade3.addBox(-2F, 42F, 0F, 2, 13, 2);
        splinter.addChild(splinterBlade3);

        ModelRenderer splinterBlade4 = new ModelRenderer(this, 32, 0);
        splinterBlade4.addBox(-3F, 55F, 0F, 2, 4, 2);
        splinter.addChild(splinterBlade4);

        ModelRenderer splinterBlade5 = new ModelRenderer(this, 48, 0);
        splinterBlade5.addBox(-2F, 59F, 0F, 1, 8, 2);
        splinter.addChild(splinterBlade5);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        splinter.render(f5);
    }
}
