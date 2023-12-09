package tragicneko.tragicmc.client.render.mob;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderMob extends RenderLiving {

    private final ResourceLocation texture;
    private final float scale;
    private String filePath = "tragicmc:textures/mobs/";
    private String extension = ".png";

    public RenderMob(ModelBase model, float shadowSize, String path, float scale) {
        super(model, scale * shadowSize);
        this.texture = new ResourceLocation(this.filePath + path + this.extension);
        this.scale = scale;
    }

    public RenderMob(ModelBase model, float shadowSize, String path) {
        this(model, shadowSize, path, 1.0F);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float par2) {
        GL11.glScalef(scale, scale, scale);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return texture;
    }

}
