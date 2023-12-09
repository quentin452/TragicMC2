package tragicneko.tragicmc.client.model.weapon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelThardus extends ModelBase {

    public ModelRenderer thardus;

    public ModelThardus() {
        textureWidth = 96;
        textureHeight = 32;

        thardus = new ModelRenderer(this, 0, 0);
        thardus.addBox(-2F, -5F, -2F, 5, 4, 5);
        thardus.setRotationPoint(16F, 4F, -3F);

        ModelRenderer thardusPart = new ModelRenderer(this, 16, 0);
        thardusPart.addBox(0, -1, 0F, 1, 12, 1);
        thardus.addChild(thardusPart);

        ModelRenderer thardusPart2 = new ModelRenderer(this, 64, 0);
        thardusPart2.addBox(-2F, 11F, -2F, 5, 5, 5);
        thardus.addChild(thardusPart2);

        ModelRenderer thardusPart3 = new ModelRenderer(this, 16, 0);
        thardusPart3.addBox(-8F, 13F, 0F, 6, 1, 1);
        thardus.addChild(thardusPart3);

        ModelRenderer thardusPart3b = new ModelRenderer(this, 0, 0);
        thardusPart3b.addBox(-12F, 7F, -2.5F, 6, 6, 6);
        thardus.addChild(thardusPart3b);

        ModelRenderer thardusPart4 = new ModelRenderer(this, 16, 0);
        thardusPart4.addBox(3F, 13F, 0F, 6, 1, 1);
        thardus.addChild(thardusPart4);

        ModelRenderer thardusPart4b = new ModelRenderer(this, 0, 0);
        thardusPart4b.addBox(7F, 7F, -2.5F, 6, 6, 6);
        thardus.addChild(thardusPart4b);

        ModelRenderer thardusPart5 = new ModelRenderer(this, 16, 0);
        thardusPart5.addBox(0F, 13F, -8F, 1, 1, 6);
        thardus.addChild(thardusPart5);

        ModelRenderer thardusPart5b = new ModelRenderer(this, 0, 0);
        thardusPart5b.addBox(-3F, 7F, -12F, 6, 6, 6);
        thardus.addChild(thardusPart5b);

        ModelRenderer thardusPart6 = new ModelRenderer(this, 16, 0);
        thardusPart6.addBox(0F, 13F, 3F, 1, 1, 6);
        thardus.addChild(thardusPart6);

        ModelRenderer thardusPart6b = new ModelRenderer(this, 0, 0);
        thardusPart6b.addBox(-3F, 7F, 7F, 6, 6, 6);
        thardus.addChild(thardusPart6b);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        thardus.render(f5);
    }
}
