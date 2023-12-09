package tragicneko.tragicmc.dimension;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IRenderHandler;

import org.lwjgl.opengl.GL11;

public class TragicSkyRenderer extends IRenderHandler {

    private static final ResourceLocation skyTexture = new ResourceLocation(
        "tragicmc:textures/environment/collisionSky2.png");

    @Override
    public void render(float partialTicks, WorldClient world, Minecraft mc) {

        GL11.glDisable(GL11.GL_FOG);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        RenderHelper.disableStandardItemLighting();
        GL11.glDepthMask(false);
        mc.renderEngine.bindTexture(skyTexture);
        Tessellator tessellator = Tessellator.instance;

        for (int i = 0; i < 6; ++i) {
            GL11.glPushMatrix();

            if (i == 1) {
                GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            }

            if (i == 2) {
                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            }

            if (i == 3) {
                GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
            }

            if (i == 4) {
                GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            }

            if (i == 5) {
                GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
            }

            tessellator.startDrawingQuads();
            tessellator.setColorRGBA_F(0.4F, 0.4F, 0.4F, 0.72F);
            tessellator.addVertexWithUV(-64.0D, -64.0D, -64.0D, 0.0D, 0.0D);
            tessellator.addVertexWithUV(-64.0D, -64.0D, 64.0D, 0.0D, 64.0D);
            tessellator.addVertexWithUV(64.0D, -64.0D, 64.0D, 64.0D, 64.0D);
            tessellator.addVertexWithUV(64.0D, -64.0D, -64.0D, 64.0D, 0.0D);
            tessellator.draw();
            GL11.glPopMatrix();
        }

        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
    }

}
