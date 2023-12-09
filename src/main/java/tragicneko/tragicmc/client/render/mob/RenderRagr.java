package tragicneko.tragicmc.client.render.mob;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import tragicneko.tragicmc.client.model.ModelRagr;
import tragicneko.tragicmc.entity.mob.EntityRagr;

public class RenderRagr extends RenderLiving {

    private ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Ragr.png");
    private ResourceLocation texture2 = new ResourceLocation("tragicmc:textures/mobs/Ragr.png"); // TODO rename to
                                                                                                 // Pyragr

    public RenderRagr() {
        super(new ModelRagr(), 0.435F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return getEntityTexture((EntityRagr) entity);
    }

    protected ResourceLocation getEntityTexture(EntityRagr entity) {
        return entity.getRagrType() == 0 ? texture : texture2;
    }
}
