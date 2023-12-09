package tragicneko.tragicmc.client.render.boss;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import tragicneko.tragicmc.client.model.ModelAegar;
import tragicneko.tragicmc.entity.boss.EntityAegar;

public class RenderAegar extends RenderBoss {

    private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Aegar.png");
    private static final ResourceLocation texture2 = new ResourceLocation("tragicmc:textures/mobs/AegarDamaged.png");

    public RenderAegar() {
        super(new ModelAegar(), 0.845F, 1.745F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.getEntityTexture((EntityAegar) entity);
    }

    private ResourceLocation getEntityTexture(EntityAegar entity) {
        return entity.getHypermode() ? texture2 : texture;
    }

}
