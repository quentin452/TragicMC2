package tragicneko.tragicmc.client.render.mob;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelJabba;
import tragicneko.tragicmc.entity.mob.EntityJabba;

public class RenderJabba extends RenderLiving {

    private ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Jabba.png");
    private ResourceLocation texture2 = new ResourceLocation("tragicmc:textures/mobs/Janna.png");

    public RenderJabba() {
        super(new ModelJabba(), 0.655F);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float par2) {
        EntityJabba jab = (EntityJabba) entity;
        float scale = jab.getJabbaType() == 0 ? 1.0F : 0.825F;
        GL11.glScalef(scale, scale, scale);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return getEntityTexture((EntityJabba) entity);
    }

    protected ResourceLocation getEntityTexture(EntityJabba entity) {
        return entity.getJabbaType() == 0 ? texture : texture2;
    }
}
