package tragicneko.tragicmc.client.model;

import java.util.Random;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

import tragicneko.tragicmc.entity.miniboss.EntityJarra;

public class ModelJarra extends ModelJabba {

    private ModelRenderer body2Spike, body2Spike2, body2Spike3, body2Spike4;
    private ModelRenderer bodySpike, bodySpike2, bodySpike3;
    private ModelRenderer body3Spike, body3Spike2, body3Spike3;
    private ModelRenderer[] spikeArray = new ModelRenderer[10];

    public ModelJarra() {
        super();

        // Mid body spikes
        body2Spike = new ModelRenderer(this, 8, 48);
        body2Spike.addBox(-2F, -9F, 0F, 1, 5, 1);
        body2.addChild(body2Spike);
        body2Spike2 = new ModelRenderer(this, 2, 49);
        body2Spike2.addBox(2F, -7F, -3F, 1, 3, 1);
        body2.addChild(body2Spike2);
        body2Spike3 = new ModelRenderer(this, 8, 48);
        body2Spike3.addBox(-7F, -1F, -5F, 3, 1, 1);
        body2.addChild(body2Spike3);
        body2Spike4 = new ModelRenderer(this, 0, 48);
        body2Spike4.addBox(4F, 0F, 0F, 2, 1, 1);
        body2.addChild(body2Spike4);

        // Head body spikes
        bodySpike = new ModelRenderer(this, 11, 50);
        bodySpike.addBox(-4F, 0F, 0F, 1, 1, 1);
        body.addChild(bodySpike);
        bodySpike2 = new ModelRenderer(this, 8, 52);
        bodySpike2.addBox(3F, -2F, 0F, 2, 1, 1);
        body.addChild(bodySpike2);
        bodySpike3 = new ModelRenderer(this, 4, 50);
        bodySpike3.addBox(-1F, -5F, 0F, 1, 2, 1);
        body.addChild(bodySpike3);

        // Tail body spikes
        body3Spike = new ModelRenderer(this, 0, 48);
        body3Spike.addBox(-5F, 0F, 0F, 2, 1, 1);
        body3.addChild(body3Spike);
        body3Spike2 = new ModelRenderer(this, 10, 48);
        body3Spike2.addBox(3F, -1F, -1F, 1, 1, 1);
        body3.addChild(body3Spike2);
        body3Spike3 = new ModelRenderer(this, 0, 48);
        body3Spike3.addBox(0F, -5F, 0F, 1, 2, 1);
        body3.addChild(body3Spike3);

        this.spikeArray = new ModelRenderer[] { body2Spike, body2Spike2, body2Spike3, body2Spike4, bodySpike,
            bodySpike2, bodySpike3, body3Spike, body3Spike2, body3Spike3 };
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);

    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6,
        Entity entity) {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
    }

    @Override
    public void setLivingAnimations(EntityLivingBase entity, float par1, float par2, float par3) {
        super.setLivingAnimations(entity, par1, par2, par3);

        if (!(entity instanceof EntityJarra)) return; // For the mob statues

        EntityJarra jar = (EntityJarra) entity;
        int i = jar.getAttackTicks();
        Random rand = jar.worldObj.rand;

        if (i > 0) {
            for (int j = 0; j < this.spikeArray.length; j++) {
                spikeArray[j].rotateAngleX = ((rand.nextFloat() - rand.nextFloat()) * 0.15F)
                    * this.simplifyAngle(i - par3, 15.0F);
                spikeArray[j].rotateAngleZ = ((rand.nextFloat() - rand.nextFloat()) * 0.15F)
                    * this.simplifyAngle(i - par3, 15.0F);
            }
        } else {
            for (int j = 0; j < this.spikeArray.length; j++) {
                spikeArray[j].rotateAngleX = 0.0F;
                spikeArray[j].rotateAngleZ = 0.0F;
            }
        }
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }

}
