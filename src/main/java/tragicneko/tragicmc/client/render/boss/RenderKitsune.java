package tragicneko.tragicmc.client.render.boss;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import tragicneko.tragicmc.client.model.ModelKitsune2;

public class RenderKitsune extends RenderBoss {

    private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Kitsune2.png");

    public RenderKitsune() {
        super(new ModelKitsune2(), 0.375F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity var1) {
        return texture;
    }

}
