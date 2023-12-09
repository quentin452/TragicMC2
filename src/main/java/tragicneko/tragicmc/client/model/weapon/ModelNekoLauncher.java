package tragicneko.tragicmc.client.model.weapon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelNekoLauncher extends ModelBase {

    private ModelRenderer launcher;

    public ModelNekoLauncher() {
        textureWidth = 96;
        textureHeight = 32;

        launcher = new ModelRenderer(this, 0, 0);
        launcher.addBox(-4F, -8F, -4F, 8, 4, 8);
        launcher.setRotationPoint(16F, 8F, 8F);

        ModelRenderer launcher2 = new ModelRenderer(this, 0, 0);
        launcher2.addBox(-5F, -4F, -5F, 10, 12, 10);
        launcher.addChild(launcher2);

        ModelRenderer launcher2b = new ModelRenderer(this, 48, 0);
        launcher2b.addBox(-3F, -3F, -6F, 6, 6, 1);
        launcher.addChild(launcher2b);

        ModelRenderer launcher2c = new ModelRenderer(this, 48, 0);
        launcher2c.addBox(-3F, -3F, 5F, 6, 6, 1);
        launcher.addChild(launcher2c);

        ModelRenderer launcherHandle = new ModelRenderer(this, 0, 0);
        launcherHandle.addBox(0F, -1F, -11F, 2, 2, 5);
        launcher.addChild(launcherHandle);

        ModelRenderer launcherHandle2 = new ModelRenderer(this, 0, 0);
        launcherHandle2.addBox(-7F, 3F, 3F, 2, 2, 8);
        launcher.addChild(launcherHandle2);

        ModelRenderer launcher3 = new ModelRenderer(this, 0, 0);
        launcher3.addBox(-4F, 8F, -4F, 8, 16, 8);
        launcher.addChild(launcher3);

        ModelRenderer launcher3b = new ModelRenderer(this, 48, 0);
        launcher3b.addBox(4F, 8F, -1F, 1, 10, 2);
        launcher.addChild(launcher3b);

        ModelRenderer launcher3c = new ModelRenderer(this, 48, 0);
        launcher3c.addBox(-5F, 8F, -1F, 1, 10, 2);
        launcher.addChild(launcher3c);

        ModelRenderer launcher3d = new ModelRenderer(this, 48, 0);
        launcher3d.addBox(4F, 18F, -2F, 1, 6, 4);
        launcher.addChild(launcher3d);

        ModelRenderer launcher3e = new ModelRenderer(this, 48, 0);
        launcher3e.addBox(-5F, 18F, -2F, 1, 6, 4);
        launcher.addChild(launcher3e);

        ModelRenderer launcher3f = new ModelRenderer(this, 48, 0);
        launcher3f.addBox(-3F, 10F, 4F, 6, 2, 2);
        launcher.addChild(launcher3f);

        ModelRenderer launcher3g = new ModelRenderer(this, 48, 0);
        launcher3g.addBox(-3F, 14F, 4F, 6, 2, 2);
        launcher.addChild(launcher3g);

        ModelRenderer launcher3h = new ModelRenderer(this, 48, 0);
        launcher3h.addBox(-3F, 18F, 4F, 6, 2, 2);
        launcher.addChild(launcher3h);

        ModelRenderer launcher3i = new ModelRenderer(this, 48, 0);
        launcher3i.addBox(-3F, 10F, -6F, 6, 2, 2);
        launcher.addChild(launcher3i);

        ModelRenderer launcher3j = new ModelRenderer(this, 48, 0);
        launcher3j.addBox(-3F, 14F, -6F, 6, 2, 2);
        launcher.addChild(launcher3j);

        ModelRenderer launcher3k = new ModelRenderer(this, 48, 0);
        launcher3k.addBox(-3F, 18F, -6F, 6, 2, 2);
        launcher.addChild(launcher3k);

        ModelRenderer launcher4 = new ModelRenderer(this, 48, 0);
        launcher4.addBox(-3F, 24F, -3F, 2, 8, 6);
        launcher.addChild(launcher4);

        ModelRenderer launcher4a = new ModelRenderer(this, 48, 0);
        launcher4a.addBox(1F, 24F, -3F, 2, 8, 6);
        launcher.addChild(launcher4a);

        ModelRenderer launcher4b = new ModelRenderer(this, 48, 0);
        launcher4b.addBox(-1F, 24F, -3F, 2, 8, 2);
        launcher.addChild(launcher4b);

        ModelRenderer launcher4c = new ModelRenderer(this, 48, 0);
        launcher4c.addBox(-1F, 24F, 1F, 2, 8, 2);
        launcher.addChild(launcher4c);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        launcher.render(f5);
    }
}
