package tragicneko.tragicmc.client.model.weapon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelReaperScythe extends ModelBase {

    private ModelRenderer scythe;

    public ModelReaperScythe() {
        textureWidth = 96;
        textureHeight = 32;

        scythe = new ModelRenderer(this, 0, 0);
        scythe.addBox(0F, -8F, 0F, 1, 4, 1);
        scythe.setRotationPoint(16F, 4F, -3F);

        ModelRenderer scythe2 = new ModelRenderer(this, 0, 0);
        scythe2.addBox(-1F, -4F, 0F, 1, 4, 1);
        scythe.addChild(scythe2);

        ModelRenderer scythe3 = new ModelRenderer(this, 0, 0);
        scythe3.addBox(0F, 0F, 0F, 1, 8, 1);
        scythe.addChild(scythe3);

        ModelRenderer scythe4 = new ModelRenderer(this, 0, 0);
        scythe4.addBox(1F, 8F, 0F, 1, 4, 1);
        scythe.addChild(scythe4);

        ModelRenderer scythe5 = new ModelRenderer(this, 0, 0);
        scythe5.addBox(0F, 12F, 0F, 1, 4, 1);
        scythe.addChild(scythe5);

        ModelRenderer scythe6 = new ModelRenderer(this, 0, 0);
        scythe6.addBox(-1F, 16F, 0F, 1, 4, 1);
        scythe.addChild(scythe6);

        ModelRenderer scythe7 = new ModelRenderer(this, 0, 0);
        scythe7.addBox(0F, 20F, 0F, 1, 8, 1);
        scythe.addChild(scythe7);

        ModelRenderer scytheBlade = new ModelRenderer(this, 46, 0);
        scytheBlade.addBox(1F, 13F, 0F, 2, 13, 1);
        scythe.addChild(scytheBlade);

        ModelRenderer scytheBlade1b = new ModelRenderer(this, 38, 0);
        scytheBlade1b.addBox(0F, 16F, 0F, 1, 4, 1);
        scythe.addChild(scytheBlade1b);

        ModelRenderer scytheBlade2 = new ModelRenderer(this, 38, 0);
        scytheBlade2.addBox(3F, 14F, 0F, 4, 11, 1);
        scythe.addChild(scytheBlade2);

        ModelRenderer scytheBlade3 = new ModelRenderer(this, 30, 0);
        scytheBlade3.addBox(7F, 13F, 0F, 5, 10, 1);
        scythe.addChild(scytheBlade3);

        ModelRenderer scytheBlade4 = new ModelRenderer(this, 30, 0);
        scytheBlade4.addBox(12F, 12F, 0F, 4, 10, 1);
        scythe.addChild(scytheBlade4);

        ModelRenderer scytheBlade5 = new ModelRenderer(this, 30, 0);
        scytheBlade5.addBox(16F, 12F, 0F, 4, 9, 1);
        scythe.addChild(scytheBlade5);

        ModelRenderer scytheBlade6 = new ModelRenderer(this, 30, 0);
        scytheBlade6.addBox(20F, 11F, 0F, 2, 9, 1);
        scythe.addChild(scytheBlade6);

        ModelRenderer scytheBlade7 = new ModelRenderer(this, 30, 0);
        scytheBlade7.addBox(22F, 10F, 0F, 2, 7, 1);
        scythe.addChild(scytheBlade7);

        ModelRenderer scytheBlade8 = new ModelRenderer(this, 30, 0);
        scytheBlade8.addBox(24F, 9F, 0F, 2, 7, 1);
        scythe.addChild(scytheBlade8);

        ModelRenderer scytheBlade9 = new ModelRenderer(this, 30, 0);
        scytheBlade9.addBox(22F, 6F, 0F, 2, 4, 1);
        scythe.addChild(scytheBlade9);

        ModelRenderer scytheBlade10 = new ModelRenderer(this, 30, 0);
        scytheBlade10.addBox(20F, 6F, 0F, 2, 2, 1);
        scythe.addChild(scytheBlade10);

        ModelRenderer scytheBlade11 = new ModelRenderer(this, 30, 0);
        scytheBlade11.addBox(18F, 5F, 0F, 2, 1, 1);
        scythe.addChild(scytheBlade11);

        ModelRenderer scytheBlade12 = new ModelRenderer(this, 46, 0);
        scytheBlade12.addBox(-2F, 12F, 0F, 2, 4, 1);
        scythe.addChild(scytheBlade12);

        ModelRenderer scytheBlade12b = new ModelRenderer(this, 46, 0);
        scytheBlade12b.addBox(-2F, 20F, 0F, 2, 4, 1);
        scythe.addChild(scytheBlade12b);

        ModelRenderer scytheBlade12c = new ModelRenderer(this, 46, 0);
        scytheBlade12c.addBox(-2F, 16F, 0F, 1, 4, 1);
        scythe.addChild(scytheBlade12c);

        ModelRenderer scytheBlade13 = new ModelRenderer(this, 38, 0);
        scytheBlade13.addBox(-4F, 13F, 0F, 2, 10, 1);
        scythe.addChild(scytheBlade13);

        ModelRenderer scytheBlade14 = new ModelRenderer(this, 30, 0);
        scytheBlade14.addBox(-6F, 15F, 0F, 2, 6, 1);
        scythe.addChild(scytheBlade14);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        scythe.render(f5);
    }
}
