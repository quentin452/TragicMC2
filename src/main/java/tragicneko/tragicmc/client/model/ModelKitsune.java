package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelKitsune extends ModelBase {

    // fields
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer tail1;
    ModelRenderer tail2;
    ModelRenderer tail3;
    ModelRenderer tail4;
    ModelRenderer snout;
    ModelRenderer leftear;
    ModelRenderer rightear;
    ModelRenderer nose;

    public ModelKitsune() {
        textureWidth = 64;
        textureHeight = 64;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-3F, -6F, -4F, 6, 6, 5);
        head.setRotationPoint(0F, 4F, 0F);
        head.setTextureSize(64, 32);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        body = new ModelRenderer(this, 22, 16);
        body.addBox(-3F, 0F, -2F, 6, 12, 2);
        body.setRotationPoint(0F, 4F, 0F);
        body.setTextureSize(64, 32);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        rightarm = new ModelRenderer(this, 40, 16);
        rightarm.addBox(-3F, 0F, -2F, 2, 8, 2);
        rightarm.setRotationPoint(-2F, 5F, 0F);
        rightarm.setTextureSize(64, 32);
        rightarm.mirror = true;
        setRotation(rightarm, 0F, 0F, 0.1487144F);
        leftarm = new ModelRenderer(this, 40, 16);
        leftarm.addBox(1F, 0F, -2F, 2, 8, 2);
        leftarm.setRotationPoint(2F, 5F, 0F);
        leftarm.setTextureSize(64, 32);
        leftarm.mirror = true;
        setRotation(leftarm, 0F, 0F, -0.1487144F);
        rightleg = new ModelRenderer(this, 0, 16);
        rightleg.addBox(-2F, 0F, -1F, 2, 8, 2);
        rightleg.setRotationPoint(-1F, 16F, -1F);
        rightleg.setTextureSize(64, 32);
        rightleg.mirror = true;
        setRotation(rightleg, 0F, 0F, 0F);
        leftleg = new ModelRenderer(this, 0, 16);
        leftleg.addBox(-1F, 0F, -1F, 2, 8, 2);
        leftleg.setRotationPoint(2F, 16F, -1F);
        leftleg.setTextureSize(64, 32);
        leftleg.mirror = true;
        setRotation(leftleg, 0F, 0F, 0F);
        tail1 = new ModelRenderer(this, 0, 30);
        tail1.addBox(1F, -15F, 0F, 5, 14, 5);
        tail1.setRotationPoint(0F, 16F, 1F);
        tail1.setTextureSize(64, 32);
        tail1.mirror = true;
        setRotation(tail1, -0.1115358F, 0F, 0.7063936F);
        tail2 = new ModelRenderer(this, 0, 30);
        tail2.addBox(-7F, -15F, 0F, 5, 14, 5);
        tail2.setRotationPoint(0F, 16F, 1F);
        tail2.setTextureSize(64, 32);
        tail2.mirror = true;
        setRotation(tail2, -0.1115358F, 0F, -0.7063936F);
        tail3 = new ModelRenderer(this, 0, 30);
        tail3.addBox(0F, -18F, 0F, 5, 17, 5);
        tail3.setRotationPoint(0F, 16F, 1F);
        tail3.setTextureSize(64, 32);
        tail3.mirror = true;
        setRotation(tail3, -0.5576792F, -0.0743572F, 0.2230717F);
        tail4 = new ModelRenderer(this, 0, 30);
        tail4.addBox(-6F, -18F, 0F, 5, 17, 5);
        tail4.setRotationPoint(0F, 16F, 1F);
        tail4.setTextureSize(64, 32);
        tail4.mirror = true;
        setRotation(tail4, -0.5576792F, 0.0743572F, -0.2230717F);
        snout = new ModelRenderer(this, 22, 0);
        snout.addBox(-1.5F, -0.5F, -5F, 3, 2, 2);
        snout.setRotationPoint(0F, 1F, -1F);
        snout.setTextureSize(64, 32);
        snout.mirror = true;
        setRotation(snout, 0F, 0F, 0F);
        leftear = new ModelRenderer(this, 44, 0);
        leftear.addBox(3F, -6F, -1F, 1, 5, 2);
        leftear.setRotationPoint(0F, 1F, -1F);
        leftear.setTextureSize(64, 32);
        leftear.mirror = true;
        setRotation(leftear, -0.4089647F, 0F, 0F);
        rightear = new ModelRenderer(this, 36, 0);
        rightear.addBox(-4F, -6F, -1F, 1, 5, 2);
        rightear.setRotationPoint(0F, 1F, -1F);
        rightear.setTextureSize(64, 32);
        rightear.mirror = true;
        setRotation(rightear, -0.4089647F, 0F, 0F);
        nose = new ModelRenderer(this, 22, 4);
        nose.addBox(-1F, -1F, -6F, 2, 1, 1);
        nose.setRotationPoint(0F, 1F, -1F);
        nose.setTextureSize(64, 32);
        nose.mirror = true;
        setRotation(nose, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        head.render(f5);
        body.render(f5);
        rightarm.render(f5);
        leftarm.render(f5);
        rightleg.render(f5);
        leftleg.render(f5);
        tail1.render(f5);
        tail2.render(f5);
        tail3.render(f5);
        tail4.render(f5);
        snout.render(f5);
        leftear.render(f5);
        rightear.render(f5);
        nose.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}
