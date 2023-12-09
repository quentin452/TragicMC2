package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.mob.EntityRanmas;

/**
 * ModelRanmas - TragicNeko
 * Created using Tabula 4.1.1
 */
public class ModelRanmas extends ModelBase {

    public ModelRenderer head;
    public ModelRenderer poleFR;
    public ModelRenderer poleFL;
    public ModelRenderer poleBL;
    public ModelRenderer poleBR;
    public ModelRenderer top;
    public ModelRenderer bottom;
    public ModelRenderer top2;
    public ModelRenderer shape9;

    public ModelRanmas() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.poleBL = new ModelRenderer(this, 32, 32);
        this.poleBL.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.poleBL.addBox(4.0F, -10.0F, 4.0F, 2, 16, 2, 0.0F);
        this.head = new ModelRenderer(this, 0, 32);
        this.head.setRotationPoint(0.0F, 9.0F, 0.0F);
        this.head.addBox(-3.0F, -5.0F, -3.0F, 6, 8, 6, 0.0F);
        this.bottom = new ModelRenderer(this, 0, 0);
        this.bottom.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bottom.addBox(-8.0F, 6.0F, -8.0F, 16, 5, 16, 0.0F);
        this.poleFL = new ModelRenderer(this, 32, 32);
        this.poleFL.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.poleFL.addBox(4.0F, -10.0F, -6.0F, 2, 16, 2, 0.0F);
        this.top2 = new ModelRenderer(this, 0, 0);
        this.top2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.top2.addBox(-3.0F, -16.0F, -3.0F, 6, 4, 6, 0.0F);
        this.poleFR = new ModelRenderer(this, 32, 32);
        this.poleFR.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.poleFR.addBox(-6.0F, -10.0F, -6.0F, 2, 16, 2, 0.0F);
        this.poleBR = new ModelRenderer(this, 32, 32);
        this.poleBR.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.poleBR.addBox(-6.0F, -10.0F, 4.0F, 2, 16, 2, 0.0F);
        this.top = new ModelRenderer(this, 0, 0);
        this.top.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.top.addBox(-7.0F, -12.0F, -7.0F, 14, 4, 14, 0.0F);
        this.shape9 = new ModelRenderer(this, 0, 0);
        this.shape9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape9.addBox(-3.0F, 11.0F, -3.0F, 6, 3, 6, 0.0F);
        this.head.addChild(this.poleBL);
        this.head.addChild(this.bottom);
        this.head.addChild(this.poleFL);
        this.head.addChild(this.top2);
        this.head.addChild(this.poleFR);
        this.head.addChild(this.poleBR);
        this.head.addChild(this.top);
        this.head.addChild(this.shape9);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        EntityRanmas ran = (EntityRanmas) entity;

        head.offsetY = (float) (Math.sin(Math.PI + this.simplifyAngle(ran.ticksExisted, 60.0F)) * 0.12F + 0.25F);
        if (ran.getChargeTicks() == 0) head.rotateAngleY = ((ran.ticksExisted * 5.25F) % 180.0F) - 90F;
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
