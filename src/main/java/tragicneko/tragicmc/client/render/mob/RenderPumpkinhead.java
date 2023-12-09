package tragicneko.tragicmc.client.render.mob;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import tragicneko.tragicmc.client.model.ModelPumpkinhead;
import tragicneko.tragicmc.entity.mob.EntityPumpkinhead;

public class RenderPumpkinhead extends RenderLiving {

    private ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/PumpkinheadNormal.png");
    private ResourceLocation texture2 = new ResourceLocation("tragicmc:textures/mobs/Pumpkinhead.png");

    public RenderPumpkinhead() {
        super(new ModelPumpkinhead(), 0.655F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return getEntityTexture((EntityPumpkinhead) entity);
    }

    protected ResourceLocation getEntityTexture(EntityPumpkinhead entity) {
        return entity.isAngry() ? texture2 : texture;
    }
}
