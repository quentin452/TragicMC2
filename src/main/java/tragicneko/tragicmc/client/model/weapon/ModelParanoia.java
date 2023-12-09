package tragicneko.tragicmc.client.model.weapon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelParanoia extends ModelBase {

    private ModelRenderer paranoia;

    public ModelParanoia() {
        textureWidth = 96;
        textureHeight = 32;

        paranoia = new ModelRenderer(this, 0, 0);
        paranoia.addBox(0F, -20F, 0F, 1, 24, 1);
        paranoia.setRotationPoint(16F, 4F, -3F);

        ModelRenderer paraPart = new ModelRenderer(this, 0, 0);
        paraPart.addBox(-1F, 4F, -0.5F, 2, 16, 2);
        paranoia.addChild(paraPart);

        ModelRenderer paraPart2 = new ModelRenderer(this, 0, 0);
        paraPart2.addBox(0F, 20F, -0.5F, 2, 8, 2);
        paranoia.addChild(paraPart2);

        ModelRenderer paraPart3 = new ModelRenderer(this, 0, 0);
        paraPart3.addBox(-1F, 28F, -1F, 3, 12, 3);
        paranoia.addChild(paraPart3);

        ModelRenderer paraPart4 = new ModelRenderer(this, 0, 0);
        paraPart4.addBox(-8F, 40F, -6F, 14, 8, 12);
        paranoia.addChild(paraPart4);

        ModelRenderer paraPart5 = new ModelRenderer(this, 0, 0);
        paraPart5.addBox(-14F, 42F, -4F, 6, 4, 8);
        paranoia.addChild(paraPart5);

        ModelRenderer paraPart6 = new ModelRenderer(this, 0, 0);
        paraPart6.addBox(6F, 41F, -4F, 6, 3, 7);
        paranoia.addChild(paraPart6);

        ModelRenderer paraEye = new ModelRenderer(this, 64, 0);
        paraEye.addBox(-4F, 43F, -7F, 4, 4, 1);
        paranoia.addChild(paraEye);

        ModelRenderer paraEye2 = new ModelRenderer(this, 64, 0);
        paraEye.addBox(-4F, 43F, 6F, 4, 4, 1);
        paranoia.addChild(paraEye2);

        ModelRenderer paraSpike = new ModelRenderer(this, 32, 0);
        paraSpike.addBox(-4F, 14F, -1F, 3, 1, 1);
        paranoia.addChild(paraSpike);

        ModelRenderer paraSpike2 = new ModelRenderer(this, 32, 0);
        paraSpike2.addBox(-10F, 21F, 1F, 10, 1, 1);
        paranoia.addChild(paraSpike2);

        ModelRenderer paraSpike3 = new ModelRenderer(this, 32, 0);
        paraSpike3.addBox(-14F, 34F, -0.5F, 13, 2, 2);
        paranoia.addChild(paraSpike3);

        ModelRenderer paraSpike4 = new ModelRenderer(this, 32, 0);
        paraSpike4.addBox(-16F, 40F, -1F, 8, 2, 2);
        paranoia.addChild(paraSpike4);

        ModelRenderer paraSpike5 = new ModelRenderer(this, 32, 0);
        paraSpike5.addBox(-26F, 43F, -1F, 12, 2, 2);
        paranoia.addChild(paraSpike5);

        ModelRenderer paraSpike6 = new ModelRenderer(this, 0, 0);
        paraSpike6.addBox(-18F, 48F, -1F, 16, 2, 2);
        paranoia.addChild(paraSpike6);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        paranoia.render(f5);
    }
}
