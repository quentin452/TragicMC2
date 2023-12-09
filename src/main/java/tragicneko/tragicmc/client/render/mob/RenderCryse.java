package tragicneko.tragicmc.client.render.mob;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelCryse;
import tragicneko.tragicmc.entity.mob.EntityCryse;

public class RenderCryse extends RenderLiving {

    private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Cryse.png");
    private static final ResourceLocation texture2 = new ResourceLocation("tragicmc:textures/mobs/StarCryse.png");

    public RenderCryse() {
        super(new ModelCryse(), 0.335F);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float par2) {
        EntityCryse cryse = (EntityCryse) entity;
        float scale = cryse.getCryseType() == 0 ? 1.0F : 0.625F;
        GL11.glScalef(scale, scale, scale);
    }

    @Override
    protected void renderModel(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5,
        float par6, float par7) {
        this.bindEntityTexture(par1EntityLivingBase);

        if (!par1EntityLivingBase.isInvisible()
            && !par1EntityLivingBase.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer)) {
            EntityCryse cryse = (EntityCryse) par1EntityLivingBase;
            float[] rgb = cryse.getCryseType() == 0 ? new float[] { 1.0F, 1.0F, 1.0F }
                : getRGBThroughTextureID(cryse.getTextureID());
            float trans = cryse.getCryseType() == 0 ? 0.35F : 0.75F;

            GL11.glPushMatrix();
            GL11.glColor4f(rgb[0] * 2.55F, rgb[1] * 2.55F, rgb[2] * 2.55F, trans);
            GL11.glDepthMask(false);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
            this.mainModel.render(par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            GL11.glDepthMask(true);
            GL11.glPopMatrix();
        } else {
            this.mainModel.setRotationAngles(par2, par3, par4, par5, par6, par7, par1EntityLivingBase);
        }
    }

    @Override
    protected int shouldRenderPass(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_) {
        if (p_77032_1_.isInvisible()) {
            return 0;
        } else if (p_77032_2_ == 0) {
            this.setRenderPassModel(this.mainModel);
            GL11.glEnable(GL11.GL_NORMALIZE);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            return 1;
        } else {
            if (p_77032_2_ == 1) {
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            }

            return -1;
        }
    }

    private float[] getRGBThroughTextureID(int textureID) {
        switch (textureID) {
            default:
                return new float[] { 0.75F, 0.75F, 0.75F };
            case 1:
                return new float[] { 0.75F, 0.25F, 0.25F };
            case 2:
                return new float[] { 0.25F, 0.75F, 0.25F };
            case 3:
                return new float[] { 0.25F, 0.25F, 0.75F };
            case 4:
                return new float[] { 0.25F, 0.75F, 0.75F };
            case 5:
                return new float[] { 0.75F, 0.75F, 0.25F };
            case 6:
                return new float[] { 0.75F, 0.25F, 0.75F };
            case 7:
                return new float[] { 0.25F, 0.25F, 0.25F };
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity var1) {
        return getEntityTexture((EntityCryse) var1);
    }

    private ResourceLocation getEntityTexture(EntityCryse cryse) {
        return cryse.getCryseType() == 0 ? texture : texture2;
    }
}
