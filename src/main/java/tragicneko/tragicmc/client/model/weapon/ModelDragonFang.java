package tragicneko.tragicmc.client.model.weapon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDragonFang extends ModelBase {

    public ModelRenderer fang;

    public ModelDragonFang() {
        textureWidth = 96;
        textureHeight = 32;

        fang = new ModelRenderer(this, 16, 0);
        fang.addBox(-2.5F, -5F, -1.5F, 5, 4, 3);
        fang.setRotationPoint(16F, 4F, -3F);

        ModelRenderer fangBlade = new ModelRenderer(this, 0, 0);
        fangBlade.addBox(-1.5F, -1F, 0F, 3, 8, 2);
        fang.addChild(fangBlade);

        ModelRenderer fangBlade2 = new ModelRenderer(this, 16, 0);
        fangBlade2.addBox(-8F, 7F, -1F, 6, 2, 3);
        fang.addChild(fangBlade2);

        ModelRenderer fangBlade3 = new ModelRenderer(this, 16, 0);
        fangBlade3.addBox(3F, 7F, -1F, 6, 2, 3);
        fang.addChild(fangBlade3);

        ModelRenderer fangBlade2b = new ModelRenderer(this, 16, 0);
        fangBlade2b.addBox(-10F, 9F, -1F, 6, 2, 3);
        fang.addChild(fangBlade2b);

        ModelRenderer fangBlade3b = new ModelRenderer(this, 16, 0);
        fangBlade3b.addBox(5F, 9F, -1F, 6, 2, 3);
        fang.addChild(fangBlade3b);

        ModelRenderer fangBlade4 = new ModelRenderer(this, 72, 0);
        fangBlade4.addBox(-3, 9F, -1F, 6, 6, 3);
        fang.addChild(fangBlade4);

        ModelRenderer fangBlade4b = new ModelRenderer(this, 0, 0);
        fangBlade4b.addBox(-1.5F, 6.5F, -3F, 3, 3, 7);
        fang.addChild(fangBlade4b);

        ModelRenderer fangBlade5 = new ModelRenderer(this, 16, 0);
        fangBlade5.addBox(-6F, 15F, -1F, 5, 2, 3);
        fang.addChild(fangBlade5);

        ModelRenderer fangBlade6 = new ModelRenderer(this, 16, 0);
        fangBlade6.addBox(1F, 15F, -1F, 5, 2, 3);
        fang.addChild(fangBlade6);

        ModelRenderer fangBlade5b = new ModelRenderer(this, 16, 0);
        fangBlade5b.addBox(-8F, 17F, -1F, 5, 2, 3);
        fang.addChild(fangBlade5b);

        ModelRenderer fangBlade6b = new ModelRenderer(this, 16, 0);
        fangBlade6b.addBox(3F, 17F, -1F, 5, 2, 3);
        fang.addChild(fangBlade6b);

        ModelRenderer fangBlade7 = new ModelRenderer(this, 72, 0);
        fangBlade7.addBox(-2, 18F, -1F, 4, 6, 3);
        fang.addChild(fangBlade7);

        ModelRenderer fangBlade7b = new ModelRenderer(this, 0, 0);
        fangBlade7b.addBox(-0.5F, 17F, -2F, 1, 1, 5);
        fang.addChild(fangBlade7b);

        ModelRenderer fangBlade8 = new ModelRenderer(this, 16, 0);
        fangBlade8.addBox(-6F, 24F, -1F, 5, 2, 3);
        fang.addChild(fangBlade8);

        ModelRenderer fangBlade9 = new ModelRenderer(this, 16, 0);
        fangBlade9.addBox(1F, 24F, -1F, 5, 2, 3);
        fang.addChild(fangBlade9);

        ModelRenderer fangBlade8b = new ModelRenderer(this, 16, 0);
        fangBlade8b.addBox(-8F, 26F, -1F, 5, 2, 3);
        fang.addChild(fangBlade8b);

        ModelRenderer fangBlade9b = new ModelRenderer(this, 16, 0);
        fangBlade9b.addBox(3F, 26F, -1F, 5, 2, 3);
        fang.addChild(fangBlade9b);

        ModelRenderer fangBlade10 = new ModelRenderer(this, 72, 0);
        fangBlade10.addBox(-2, 26F, -1F, 4, 6, 3);
        fang.addChild(fangBlade10);

        ModelRenderer fangBlade10b = new ModelRenderer(this, 0, 0);
        fangBlade10b.addBox(-0.5F, 25F, -2F, 1, 1, 5);
        fang.addChild(fangBlade10b);

        ModelRenderer fangBlade11 = new ModelRenderer(this, 16, 0);
        fangBlade11.addBox(-6F, 32F, -1F, 5, 2, 3);
        fang.addChild(fangBlade11);

        ModelRenderer fangBlade12 = new ModelRenderer(this, 16, 0);
        fangBlade12.addBox(1F, 32F, -1F, 5, 2, 3);
        fang.addChild(fangBlade12);

        ModelRenderer fangBlade11b = new ModelRenderer(this, 16, 0);
        fangBlade11b.addBox(-8F, 34F, -1F, 5, 2, 3);
        fang.addChild(fangBlade11b);

        ModelRenderer fangBlade12b = new ModelRenderer(this, 16, 0);
        fangBlade12b.addBox(3F, 34F, -1F, 5, 2, 3);
        fang.addChild(fangBlade12b);

        ModelRenderer fangBlade13 = new ModelRenderer(this, 72, 0);
        fangBlade13.addBox(-1, 34F, -1F, 2, 6, 3);
        fang.addChild(fangBlade13);

        ModelRenderer fangBlade13b = new ModelRenderer(this, 0, 0);
        fangBlade13b.addBox(-0.5F, 33F, -2F, 1, 1, 5);
        fang.addChild(fangBlade13b);

        ModelRenderer fangBlade14 = new ModelRenderer(this, 16, 0);
        fangBlade14.addBox(-4F, 40F, -1F, 3, 2, 3);
        fang.addChild(fangBlade14);

        ModelRenderer fangBlade15 = new ModelRenderer(this, 16, 0);
        fangBlade15.addBox(1F, 40F, -1F, 3, 2, 3);
        fang.addChild(fangBlade15);

        ModelRenderer fangBlade14b = new ModelRenderer(this, 16, 0);
        fangBlade14b.addBox(-6F, 42F, -1F, 3, 2, 3);
        fang.addChild(fangBlade14b);

        ModelRenderer fangBlade15b = new ModelRenderer(this, 16, 0);
        fangBlade15b.addBox(3F, 42F, -1F, 3, 2, 3);
        fang.addChild(fangBlade15b);

        new ModelRenderer(this, 72, 0);
        fangBlade13.addBox(-1, 42F, -1F, 2, 4, 3);
        fang.addChild(fangBlade13);

        new ModelRenderer(this, 0, 0);
        fangBlade13b.addBox(-0.5F, 41F, -1F, 1, 1, 3);
        fang.addChild(fangBlade13b);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        fang.render(f5);
    }
}
