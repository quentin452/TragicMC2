package tragicneko.tragicmc.client.render.boss;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelVoxStellarum;
import tragicneko.tragicmc.entity.miniboss.EntityVoxStellarum;

public class RenderVoxStellarum extends RenderLiving {

    private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/VoxStellarum.png");
    private static final float scale = 2.25F;

    public RenderVoxStellarum() {
        super(new ModelVoxStellarum(), 0.75F * scale);
    }

    @Override
    protected void renderModel(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5,
        float par6, float par7) {
        this.renderModel((EntityVoxStellarum) par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
    }

    private void renderModel(EntityVoxStellarum par1EntityLivingBase, float par2, float par3, float par4, float par5,
        float par6, float par7) {
        this.bindEntityTexture(par1EntityLivingBase);

        if (!par1EntityLivingBase.isInvisible()
            && !par1EntityLivingBase.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer)) {
            GL11.glPushMatrix();

            float[] rgb = this.getRGBThroughTextureID(par1EntityLivingBase.getTextureID());

            GL11.glColor4f(rgb[0] * 2.55F, rgb[1] * 2.55F, rgb[2] * 2.55F, 0.85F);
            GL11.glDepthMask(false);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
            this.mainModel.render(par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.2F);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glDepthMask(true);
            GL11.glPopMatrix();

        } else {
            this.mainModel.setRotationAngles(par2, par3, par4, par5, par6, par7, par1EntityLivingBase);
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
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
        GL11.glScalef(RenderVoxStellarum.scale, RenderVoxStellarum.scale, RenderVoxStellarum.scale);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return texture;
    }

    protected int shouldRenderPass(EntityVoxStellarum boss, int par2, float par3) {
        if (boss.isInvisible()) {
            GL11.glDepthMask(false);
            return 0;
        } else {
            GL11.glDepthMask(true);
        }

        if (par2 == 0) {
            this.setRenderPassModel(this.mainModel);
            GL11.glEnable(GL11.GL_NORMALIZE);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            return 1;
        }

        if (boss.isHealing() && boss.getHealTicks() >= 100) {
            if (par2 == 1) {
                float f1 = boss.ticksExisted + par3;
                this.bindTexture(texture);
                GL11.glMatrixMode(GL11.GL_TEXTURE);
                GL11.glLoadIdentity();
                float f2 = MathHelper.cos(f1 * 0.02F) * 3.0F;
                float f3 = f1 * 0.01F;
                GL11.glTranslatef(f2, f3, 0.0F);
                this.setRenderPassModel(this.mainModel);
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glEnable(GL11.GL_BLEND);
                float f4 = 0.275F;
                GL11.glColor4f(f4, f4, f4, 1.0F);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
                GL11.glTranslatef(0.0F, -0.125F, 0.0F);
                GL11.glScalef(1.1F, 1.1F, 1.1F);
                return 2;
            } else if (par2 == 2) {
                GL11.glMatrixMode(GL11.GL_TEXTURE);
                GL11.glLoadIdentity();
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_BLEND);
                return -1;
            }
        }

        return -1;
    }

    @Override
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
        return this.shouldRenderPass((EntityVoxStellarum) par1EntityLivingBase, par2, par3);
    }

    @Override
    protected int inheritRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
        return -1;
    }

}
