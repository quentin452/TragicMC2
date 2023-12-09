package tragicneko.tragicmc.client.render.mob;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import tragicneko.tragicmc.entity.mob.TragicMob;

public class RenderArbiter extends RenderLiving {

    private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Arbiter.png");

    private static final ResourceLocation textureMad = new ResourceLocation("tragicmc:textures/mobs/ArbiterAngry.png");

    private static final ResourceLocation textureHappy = new ResourceLocation(
        "tragicmc:textures/mobs/ArbiterHappy.png");

    public RenderArbiter(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity var1) {
        if (var1 instanceof TragicMob) // change this to EntityArbiter
        {
            return texture; // change this to invoke a different method, snythetic bridge method
        }
        return texture;
    }

}
