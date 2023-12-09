package tragicneko.tragicmc.client.render.mob;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import tragicneko.tragicmc.client.model.ModelErkel;
import tragicneko.tragicmc.entity.mob.EntityErkel;

public class RenderErkel extends RenderLiving {

    private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Erkel.png");
    private static final ResourceLocation ashenTexture = new ResourceLocation("tragicmc:textures/mobs/ErkelAshen.png");
    private static final ResourceLocation paintedTexture = new ResourceLocation(
        "tragicmc:textures/mobs/ErkelPainted.png");
    private static final ResourceLocation starlitTexture = new ResourceLocation(
        "tragicmc:textures/mobs/ErkelStarlit.png");
    private static final ResourceLocation decayingTexture = new ResourceLocation(
        "tragicmc:textures/mobs/ErkelDecaying.png");
    private static final ResourceLocation hallowedTexture = new ResourceLocation(
        "tragicmc:textures/mobs/ErkelHallowed.png");
    private static final ResourceLocation frozenTexture = new ResourceLocation(
        "tragicmc:textures/mobs/ErkelFrozen.png");

    public RenderErkel() {
        super(new ModelErkel(), 0.625F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return getEntityTexture((EntityErkel) entity);
    }

    private ResourceLocation getEntityTexture(EntityErkel entity) {
        switch (entity.getTextureId()) {
            default:
            case 0:
                return texture;
            case 1:
                return ashenTexture;
            case 2:
                return paintedTexture;
            case 3:
                return starlitTexture;
            case 4:
                return decayingTexture;
            case 5:
                return hallowedTexture;
            case 6:
                return frozenTexture;
        }
    }

}
