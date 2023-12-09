package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBangatang extends ModelBase {

    // fields
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer leftarm;
    ModelRenderer rightarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer nostrills;
    ModelRenderer leftear;
    ModelRenderer rightear;
    ModelRenderer tail;
    ModelRenderer tail2;
    ModelRenderer rightpalm;
    ModelRenderer leftpalm;
    ModelRenderer rightthumb;
    ModelRenderer rightring;
    ModelRenderer rightpinky;
    ModelRenderer leftpoint;
    ModelRenderer leftthumb;
    ModelRenderer leftpinky;
    ModelRenderer rightfoot;
    ModelRenderer leftfoot;

    public ModelBangatang() {
        textureWidth = 64;
        textureHeight = 64;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-2F, -4F, -2F, 4, 4, 4);
        head.setRotationPoint(0F, 14F, 0F);
        head.setTextureSize(64, 64);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        body = new ModelRenderer(this, 6, 13);
        body.addBox(-2F, 0F, -1F, 4, 7, 2);
        body.setRotationPoint(0F, 14F, 0F);
        body.setTextureSize(64, 64);
        body.mirror = true;
        setRotation(body, 0.4461433F, 0F, 0F);
        leftarm = new ModelRenderer(this, 22, 0);
        leftarm.addBox(0F, 0F, 0F, 1, 6, 1);
        leftarm.setRotationPoint(2F, 14F, 0F);
        leftarm.setTextureSize(64, 64);
        leftarm.mirror = true;
        setRotation(leftarm, -0.1784573F, 0F, 0F);
        rightarm = new ModelRenderer(this, 22, 0);
        rightarm.addBox(-1F, 0F, 0F, 1, 6, 1);
        rightarm.setRotationPoint(-2F, 14F, 0F);
        rightarm.setTextureSize(64, 64);
        rightarm.mirror = true;
        setRotation(rightarm, -0.1784573F, 0F, 0F);
        rightleg = new ModelRenderer(this, 0, 18);
        rightleg.addBox(-1F, 0F, 0F, 1, 3, 1);
        rightleg.setRotationPoint(-0.5F, 20F, 2F);
        rightleg.setTextureSize(64, 64);
        rightleg.mirror = true;
        setRotation(rightleg, 0F, 0F, 0F);
        leftleg = new ModelRenderer(this, 0, 18);
        leftleg.addBox(0F, 0F, 0F, 1, 3, 1);
        leftleg.setRotationPoint(0.5F, 20F, 2F);
        leftleg.setTextureSize(64, 64);
        leftleg.mirror = true;
        setRotation(leftleg, 0F, 0F, 0F);
        nostrills = new ModelRenderer(this, 12, 8);
        nostrills.addBox(-1.5F, 0F, 0F, 3, 2, 1);
        nostrills.setRotationPoint(0F, 12F, -3F);
        nostrills.setTextureSize(64, 64);
        nostrills.mirror = true;
        setRotation(nostrills, 0F, 0F, 0F);
        leftear = new ModelRenderer(this, 6, 8);
        leftear.addBox(0F, 0F, 0F, 1, 2, 2);
        leftear.setRotationPoint(-3F, 11F, -1F);
        leftear.setTextureSize(64, 64);
        leftear.mirror = true;
        setRotation(leftear, 0F, 0F, 0F);
        rightear = new ModelRenderer(this, 6, 8);
        rightear.addBox(0F, 0F, 0F, 1, 2, 2);
        rightear.setRotationPoint(2F, 11F, -1F);
        rightear.setTextureSize(64, 64);
        rightear.mirror = true;
        setRotation(rightear, 0F, 0F, 0F);
        tail = new ModelRenderer(this, 28, 0);
        tail.addBox(-0.5F, -0.5F, 0F, 1, 1, 5);
        tail.setRotationPoint(0F, 19F, 3F);
        tail.setTextureSize(64, 64);
        tail.mirror = true;
        setRotation(tail, 0.535372F, 0F, 0F);
        tail2 = new ModelRenderer(this, 40, 0);
        tail2.addBox(-0.5F, 3.5F, 2F, 1, 1, 6);
        tail2.setRotationPoint(0F, 19F, 3F);
        tail2.setTextureSize(64, 64);
        tail2.mirror = true;
        setRotation(tail2, 1.606116F, 0F, 0F);
        rightpalm = new ModelRenderer(this, 20, 12);
        rightpalm.addBox(-2F, -3F, 4F, 2, 5, 2);
        rightpalm.setRotationPoint(-2F, 14F, 0F);
        rightpalm.setTextureSize(64, 64);
        rightpalm.mirror = true;
        setRotation(rightpalm, -2.319945F, 0F, 0F);
        leftpalm = new ModelRenderer(this, 20, 12);
        leftpalm.addBox(0F, -3F, 4F, 2, 5, 2);
        leftpalm.setRotationPoint(2F, 14F, 0F);
        leftpalm.setTextureSize(64, 64);
        leftpalm.mirror = true;
        setRotation(leftpalm, -2.319945F, 0F, 0F);
        rightthumb = new ModelRenderer(this, 22, 8);
        rightthumb.addBox(0F, 1F, 4F, 1, 2, 1);
        rightthumb.setRotationPoint(-2F, 14F, 0F);
        rightthumb.setTextureSize(64, 64);
        rightthumb.mirror = true;
        setRotation(rightthumb, -2.319945F, 0F, 0F);
        rightring = new ModelRenderer(this, 22, 8);
        rightring.addBox(-2.5F, 2F, 3F, 3, 2, 1);
        rightring.setRotationPoint(-2F, 14F, 0F);
        rightring.setTextureSize(64, 64);
        rightring.mirror = true;
        setRotation(rightring, -2.319892F, 0F, 0F);
        rightpinky = new ModelRenderer(this, 22, 8);
        rightpinky.addBox(-3F, 2F, 4F, 1, 1, 1);
        rightpinky.setRotationPoint(-2F, 14F, 0F);
        rightpinky.setTextureSize(64, 64);
        rightpinky.mirror = true;
        setRotation(rightpinky, -2.319892F, 0F, 0F);
        leftpoint = new ModelRenderer(this, 22, 8);
        leftpoint.addBox(-0.5F, 2F, 3F, 3, 2, 1);
        leftpoint.setRotationPoint(2F, 14F, 0F);
        leftpoint.setTextureSize(64, 64);
        leftpoint.mirror = true;
        setRotation(leftpoint, -2.319892F, 0F, 0F);
        leftthumb = new ModelRenderer(this, 22, 8);
        leftthumb.addBox(-1F, 1F, 4F, 1, 2, 1);
        leftthumb.setRotationPoint(2F, 14F, 0F);
        leftthumb.setTextureSize(64, 64);
        leftthumb.mirror = true;
        setRotation(leftthumb, -2.319892F, 0F, 0F);
        leftpinky = new ModelRenderer(this, 22, 8);
        leftpinky.addBox(2F, 2F, 4F, 1, 1, 1);
        leftpinky.setRotationPoint(2F, 14F, 0F);
        leftpinky.setTextureSize(64, 64);
        leftpinky.mirror = true;
        setRotation(leftpinky, -2.319892F, 0F, 0F);
        rightfoot = new ModelRenderer(this, 0, 22);
        rightfoot.addBox(-2F, 3F, -2F, 2, 1, 4);
        rightfoot.setRotationPoint(-0.5F, 20F, 2F);
        rightfoot.setTextureSize(64, 64);
        rightfoot.mirror = true;
        setRotation(rightfoot, 0F, 0F, 0F);
        leftfoot = new ModelRenderer(this, 0, 22);
        leftfoot.addBox(0F, 3F, -2F, 2, 1, 4);
        leftfoot.setRotationPoint(0.5F, 20F, 2F);
        leftfoot.setTextureSize(64, 64);
        leftfoot.mirror = true;
        setRotation(leftfoot, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        head.render(f5);
        body.render(f5);
        leftarm.render(f5);
        rightarm.render(f5);
        rightleg.render(f5);
        leftleg.render(f5);
        nostrills.render(f5);
        leftear.render(f5);
        rightear.render(f5);
        tail.render(f5);
        tail2.render(f5);
        rightpalm.render(f5);
        leftpalm.render(f5);
        rightthumb.render(f5);
        rightring.render(f5);
        rightpinky.render(f5);
        leftpoint.render(f5);
        leftthumb.render(f5);
        leftpinky.render(f5);
        rightfoot.render(f5);
        leftfoot.render(f5);
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
