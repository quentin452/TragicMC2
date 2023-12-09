package tragicneko.tragicmc.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import tragicneko.tragicmc.client.model.ModelNuke;
import tragicneko.tragicmc.entity.EntityNuke;

public class RenderNuke extends Render {

    private ResourceLocation texture = new ResourceLocation("tragicmc:textures/entities/Nuke.png");
    public ModelBase model;

    public RenderNuke() {
        super();
        this.model = new ModelNuke();
    }

    @Override
    public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_,
        float p_76986_9_) {
        this.doRender((EntityNuke) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    public void doRender(EntityNuke entity, double par2, double par3, double par4, float par5, float par6) {

        float f = 1.425F;
        float f1 = 0.025F;
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glTranslatef((float) par2, (float) par3 + f, (float) par4);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        float f2 = (float) Math.sin(entity.stageTime) * 5F;
        GL11.glScalef(2.25F, 2.25F, 2.25F);
        if (entity.nukeStage == 1) GL11.glColor3f(f2, f2, f2);
        GL11.glRotatef(180.0F, 1.0F, 0F, 0F);
        float f0 = entity.ticksExisted % 360.0F;
        // GL11.glRotatef(f0, 0.0F, 1.0F, 0.0F);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        this.bindTexture(this.getEntityTexture(entity));
        if (entity.nukeStage < 2) this.model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, f1);
        GL11.glDepthMask(true);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDepthFunc(GL11.GL_EQUAL);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return texture;
    }
}
