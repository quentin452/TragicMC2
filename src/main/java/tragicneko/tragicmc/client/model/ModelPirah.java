package tragicneko.tragicmc.client.model;

import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.mob.EntityPirah;

public class ModelPirah extends ModelBase {

    private ModelRenderer body;
    private ModelRenderer fin;

    public ModelPirah() {
        textureWidth = 64;
        textureHeight = 32;

        body = new ModelRenderer(this, 0, 0);
        body.addBox(-1F, -1F, -2F, 2, 4, 4);
        body.setRotationPoint(0F, 18F, 0F);
        ModelRenderer snout = new ModelRenderer(this, 0, 8);
        snout.addBox(-0.5F, -1F, -6F, 1, 1, 4);
        body.addChild(snout);
        ModelRenderer topFin = new ModelRenderer(this, 14, 0);
        topFin.addBox(-0.5F, -2F, 0F, 1, 1, 5);
        body.addChild(topFin);
        ModelRenderer snoutTip = new ModelRenderer(this, 14, 0);
        snoutTip.addBox(-0.5F, 0F, -5F, 1, 1, 1);
        body.addChild(snoutTip);
        fin = new ModelRenderer(this, 14, 8);
        fin.addBox(-1F, 0F, 2F, 2, 2, 2);
        body.addChild(fin);
        ModelRenderer finTip = new ModelRenderer(this, 24, 0);
        finTip.addBox(-1F, 0F, 4F, 2, 1, 3);
        fin.addChild(finTip);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        body.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        EntityPirah pirah = (EntityPirah) entity;
        Material material = pirah.getPirahType() == 0 ? Material.water : Material.lava;

        if (pirah.isInsideOfMaterial(material)) {
            fin.rotateAngleY = this.simplifyAngle(pirah.ticksExisted, 10.0F) * 0.35F;
            fin.rotateAngleX = 0.0F;
            body.rotateAngleX = this.simplifyAngle(pirah.ticksExisted, 10.0F) * 0.235F + f4 / (180F / (float) Math.PI);
            body.rotateAngleY = f3 / (180F / (float) Math.PI);
            body.rotateAngleZ = 0.0F;
        } else {
            fin.rotateAngleY = 0.0F;
            body.rotateAngleX = this.simplifyAngle(pirah.ticksExisted, 10.0F) + 2.5F;
            fin.rotateAngleX = this.simplifyAngle(pirah.ticksExisted, 8.0F) * 0.425F;
            body.rotateAngleY = 0.0F;
            body.rotateAngleZ = this.simplifyAngle(pirah.ticksExisted, 30.0F) * 0.825F;
        }
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }
}
