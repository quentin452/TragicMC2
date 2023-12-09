package tragicneko.tragicmc.client.model.weapon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSentinel extends ModelBase {

    private ModelRenderer sentinel;

    public ModelSentinel() {
        textureWidth = 128;
        textureHeight = 32;

        sentinel = new ModelRenderer(this, 32, 0);
        sentinel.addBox(-1F, -8F, -1F, 2, 4, 2);
        sentinel.setRotationPoint(16F, 4F, 0F);

        ModelRenderer sentinelHandle = new ModelRenderer(this, 64, 0);
        sentinelHandle.addBox(-0.5F, -4F, -0.5F, 1, 4, 1);
        sentinel.addChild(sentinelHandle);

        ModelRenderer sentinelGuard = new ModelRenderer(this, 96, 0);
        sentinelGuard.addBox(-2F, 0F, -2F, 4, 4, 4);
        sentinel.addChild(sentinelGuard);

        ModelRenderer sentinelGuardb = new ModelRenderer(this, 64, 0);
        sentinelGuardb.addBox(-6F, -3F, -1F, 2, 2, 2);
        sentinel.addChild(sentinelGuardb);

        ModelRenderer sentinelGuardb2 = new ModelRenderer(this, 32, 0);
        sentinelGuardb2.addBox(-8F, -5F, -0.5F, 1, 1, 1);
        sentinel.addChild(sentinelGuardb2);

        ModelRenderer sentinelGuardc = new ModelRenderer(this, 64, 0);
        sentinelGuardc.addBox(4F, -3F, -1F, 2, 2, 2);
        sentinel.addChild(sentinelGuardc);

        ModelRenderer sentinelGuardc2 = new ModelRenderer(this, 32, 0);
        sentinelGuardc2.addBox(7F, -5F, -0.5F, 1, 1, 1);
        sentinel.addChild(sentinelGuardc2);

        ModelRenderer sentinelGuardd = new ModelRenderer(this, 64, 0);
        sentinelGuardd.addBox(-6F, 5F, -1F, 2, 2, 2);
        sentinel.addChild(sentinelGuardd);

        ModelRenderer sentinelGuardd2 = new ModelRenderer(this, 32, 0);
        sentinelGuardd2.addBox(-8F, 8F, -0.5F, 1, 1, 1);
        sentinel.addChild(sentinelGuardd2);

        ModelRenderer sentinelGuarde = new ModelRenderer(this, 64, 0);
        sentinelGuarde.addBox(4F, 5F, -1F, 2, 2, 2);
        sentinel.addChild(sentinelGuarde);

        ModelRenderer sentinelGuarde2 = new ModelRenderer(this, 32, 0);
        sentinelGuarde2.addBox(7F, 8F, -0.5F, 1, 1, 1);
        sentinel.addChild(sentinelGuarde2);

        ModelRenderer sentinelHandle2 = new ModelRenderer(this, 64, 0);
        sentinelHandle2.addBox(2F, 1F, -1F, 6, 2, 2);
        sentinel.addChild(sentinelHandle2);

        ModelRenderer sentinelHandle3 = new ModelRenderer(this, 64, 0);
        sentinelHandle3.addBox(-8F, 1F, -1F, 6, 2, 2);
        sentinel.addChild(sentinelHandle3);

        ModelRenderer sentinelBlade = new ModelRenderer(this, 96, 0);
        sentinelBlade.addBox(-2F, 4F, -0.5F, 4, 2, 1);
        sentinel.addChild(sentinelBlade);

        ModelRenderer sentinelBlade2 = new ModelRenderer(this, 64, 0);
        sentinelBlade2.addBox(-3F, 6F, -1F, 6, 2, 2);
        sentinel.addChild(sentinelBlade2);

        ModelRenderer sentinelBlade3 = new ModelRenderer(this, 32, 0);
        sentinelBlade3.addBox(-4F, 8F, -1F, 8, 2, 2);
        sentinel.addChild(sentinelBlade3);

        ModelRenderer sentinelBlade3b = new ModelRenderer(this, 0, 0);
        sentinelBlade3b.addBox(-6F, 10F, -1F, 4, 1, 2);
        sentinel.addChild(sentinelBlade3b);

        ModelRenderer sentinelBlade3c = new ModelRenderer(this, 0, 0);
        sentinelBlade3c.addBox(2F, 10F, -1F, 4, 1, 2);
        sentinel.addChild(sentinelBlade3c);

        ModelRenderer sentinelBlade3d = new ModelRenderer(this, 0, 0);
        sentinelBlade3d.addBox(4F, 11F, -1F, 4, 1, 2);
        sentinel.addChild(sentinelBlade3d);

        ModelRenderer sentinelBlade3e = new ModelRenderer(this, 0, 0);
        sentinelBlade3e.addBox(-8F, 11F, -1F, 4, 1, 2);
        sentinel.addChild(sentinelBlade3e);

        ModelRenderer sentinelBlade4 = new ModelRenderer(this, 96, 0);
        sentinelBlade4.addBox(-1F, 10F, -0.5F, 2, 4, 1);
        sentinel.addChild(sentinelBlade4);

        ModelRenderer sentinelBlade5 = new ModelRenderer(this, 64, 0);
        sentinelBlade5.addBox(-3F, 14F, -1F, 6, 2, 2);
        sentinel.addChild(sentinelBlade5);

        ModelRenderer sentinelBlade6 = new ModelRenderer(this, 32, 0);
        sentinelBlade6.addBox(-4F, 16F, -1F, 8, 2, 2);
        sentinel.addChild(sentinelBlade6);

        ModelRenderer sentinelBlade6b = new ModelRenderer(this, 0, 0);
        sentinelBlade6b.addBox(-6F, 18F, -1F, 4, 1, 2);
        sentinel.addChild(sentinelBlade6b);

        ModelRenderer sentinelBlade6c = new ModelRenderer(this, 0, 0);
        sentinelBlade6c.addBox(2F, 18F, -1F, 4, 1, 2);
        sentinel.addChild(sentinelBlade6c);

        ModelRenderer sentinelBlade7 = new ModelRenderer(this, 96, 0);
        sentinelBlade7.addBox(-1F, 18F, -0.5F, 2, 3, 1);
        sentinel.addChild(sentinelBlade7);

        ModelRenderer sentinelBlade8 = new ModelRenderer(this, 64, 0);
        sentinelBlade8.addBox(-2F, 21F, -1F, 4, 2, 2);
        sentinel.addChild(sentinelBlade8);

        ModelRenderer sentinelBlade9 = new ModelRenderer(this, 32, 0);
        sentinelBlade9.addBox(-3F, 23F, -1F, 6, 1, 2);
        sentinel.addChild(sentinelBlade9);

        ModelRenderer sentinelBlade9b = new ModelRenderer(this, 0, 0);
        sentinelBlade9b.addBox(-5F, 24F, -1F, 3, 1, 2);
        sentinel.addChild(sentinelBlade9b);

        ModelRenderer sentinelBlade9c = new ModelRenderer(this, 0, 0);
        sentinelBlade9c.addBox(2F, 24F, -1F, 3, 1, 2);
        sentinel.addChild(sentinelBlade9c);

        ModelRenderer sentinelBlade10 = new ModelRenderer(this, 96, 0);
        sentinelBlade10.addBox(-1F, 24F, -0.5F, 2, 2, 1);
        sentinel.addChild(sentinelBlade10);

        ModelRenderer sentinelBlade11 = new ModelRenderer(this, 64, 0);
        sentinelBlade11.addBox(-2F, 26F, -1F, 4, 1, 2);
        sentinel.addChild(sentinelBlade11);

        ModelRenderer sentinelBlade12a = new ModelRenderer(this, 32, 0);
        sentinelBlade12a.addBox(-4F, 27F, -1F, 2, 1, 2);
        sentinel.addChild(sentinelBlade12a);

        ModelRenderer sentinelBlade12b = new ModelRenderer(this, 32, 0);
        sentinelBlade12b.addBox(2F, 27F, -1F, 2, 1, 2);
        sentinel.addChild(sentinelBlade12b);

        ModelRenderer sentinelBlade13 = new ModelRenderer(this, 96, 0);
        sentinelBlade13.addBox(-1F, 27F, -0.5F, 2, 2, 1);
        sentinel.addChild(sentinelBlade13);

        ModelRenderer sentinelBlade14 = new ModelRenderer(this, 64, 0);
        sentinelBlade14.addBox(-2F, 29F, -1F, 4, 1, 2);
        sentinel.addChild(sentinelBlade14);

        ModelRenderer sentinelBlade15a = new ModelRenderer(this, 32, 0);
        sentinelBlade15a.addBox(-4F, 30F, -1F, 3, 1, 2);
        sentinel.addChild(sentinelBlade15a);

        ModelRenderer sentinelBlade15b = new ModelRenderer(this, 32, 0);
        sentinelBlade15b.addBox(1F, 30F, -1F, 3, 1, 2);
        sentinel.addChild(sentinelBlade15b);

        ModelRenderer sentinelBlade16 = new ModelRenderer(this, 96, 0);
        sentinelBlade16.addBox(-1F, 30F, -0.5F, 2, 3, 1);
        sentinel.addChild(sentinelBlade16);

        ModelRenderer sentinelBlade17 = new ModelRenderer(this, 64, 0);
        sentinelBlade17.addBox(-2F, 33F, -0.5F, 4, 1, 1);
        sentinel.addChild(sentinelBlade17);

        ModelRenderer sentinelBlade18a = new ModelRenderer(this, 32, 0);
        sentinelBlade18a.addBox(-2F, 34F, -0.5F, 1, 2, 1);
        sentinel.addChild(sentinelBlade18a);

        ModelRenderer sentinelBlade18b = new ModelRenderer(this, 32, 0);
        sentinelBlade18b.addBox(1F, 34F, -0.5F, 1, 2, 1);
        sentinel.addChild(sentinelBlade18b);

        ModelRenderer sentinelBlade19 = new ModelRenderer(this, 64, 0);
        sentinelBlade19.addBox(-2F, 36F, -0.5F, 4, 1, 1);
        sentinel.addChild(sentinelBlade19);

        ModelRenderer sentinelBladeInset = new ModelRenderer(this, 0, 0);
        sentinelBladeInset.addBox(-1F, 34F, -1F, 2, 2, 2);
        sentinel.addChild(sentinelBladeInset);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        sentinel.render(f5);
    }
}
