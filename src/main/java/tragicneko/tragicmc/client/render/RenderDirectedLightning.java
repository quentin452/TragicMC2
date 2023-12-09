package tragicneko.tragicmc.client.render;

import java.util.Random;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.entity.EntityDirectedLightning;

public class RenderDirectedLightning extends Render {

    public void doRender(EntityDirectedLightning bolt, double par1, double par2, double par3, float par4, float par5) {
        Tessellator tessellator = Tessellator.instance;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);

        Entity user = bolt.worldObj.getEntityByID(bolt.getUserID());
        Random rand = new Random(bolt.boltVertex);
        double bx = (bolt.posX - bolt.prevPosX);
        double by = (bolt.posY - bolt.prevPosY);
        double bz = (bolt.posZ - bolt.prevPosZ);
        double x = user != null ? (user.posX - user.prevPosX) : bx;
        double y = user != null ? (user.posY - user.prevPosY) : by;
        double z = user != null ? (user.posZ - user.prevPosZ) : bz;
        GL11.glPushMatrix();
        /*
         * for (int i = 0; i < 10; i++) //TODO improve Directed Lightning render
         * {
         * tessellator.startDrawing(3);
         * tessellator.setColorRGBA_I(0xFFFFFF, 0x767676);
         * tessellator.addVertex(x + rand.nextDouble() * 0.0125 - rand.nextDouble() * 0.0125, y + rand.nextDouble() *
         * 0.0125 - rand.nextDouble() * 0.0125, z + rand.nextDouble() * 0.0125 - rand.nextDouble() * 0.0125);
         * tessellator.addVertex(bx + rand.nextDouble() * 0.0125 - rand.nextDouble() * 0.0125, by + rand.nextDouble() *
         * 0.0125 - rand.nextDouble() * 0.0125, bz + rand.nextDouble() * 0.0125 - rand.nextDouble() * 0.0125);
         * tessellator.draw();
         * }
         */

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return null;
    }

    @Override
    public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_,
        float p_76986_9_) {
        this.doRender((EntityDirectedLightning) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}
