package tragicneko.tragicmc.client.render.mob;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelTox;
import tragicneko.tragicmc.entity.mob.EntityTox;

public class RenderTox extends RenderLiving {

    private ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Tox.png");
    private ResourceLocation texture2 = new ResourceLocation("tragicmc:textures/mobs/Pox.png");

    public RenderTox() {
        super(new ModelTox(), 0.855F);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float par2) {
        EntityTox tox = (EntityTox) entity;
        float scale = tox.getToxType() == 0 ? 1.0F : 0.635F;
        GL11.glScalef(scale, scale, scale);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return getEntityTexture((EntityTox) entity);
    }

    protected ResourceLocation getEntityTexture(EntityTox entity) {
        return entity.getToxType() == 0 ? texture : texture2;
    }
}
