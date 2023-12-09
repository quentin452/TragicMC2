package tragicneko.tragicmc.client.render.mob;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;

import org.lwjgl.opengl.GL11;

public class RenderMobTransparent extends RenderMob {

    private final float maxTransparency;

    public RenderMobTransparent(ModelBase model, float shadowSize, String path, float scale, float trans) {
        super(model, shadowSize, path, scale);
        this.maxTransparency = trans;
    }

    @Override
    protected void renderModel(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5,
        float par6, float par7) {
        this.bindEntityTexture(par1EntityLivingBase);

        if (!par1EntityLivingBase.isInvisible()
            && !par1EntityLivingBase.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer)) {
            float[] rgb = new float[] { 1.0F, 1.0F, 1.0F };

            GL11.glPushMatrix();
            GL11.glColor4f(rgb[0] * 2.55F, rgb[1] * 2.55F, rgb[2] * 2.55F, this.maxTransparency);
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
}
