package tragicneko.tragicmc.client.render.mob;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import tragicneko.tragicmc.client.model.ModelStin;
import tragicneko.tragicmc.client.model.ModelStinBaby;
import tragicneko.tragicmc.entity.mob.EntityStin;

public class RenderStin extends RenderLiving {

    private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Stin.png");

    public RenderStin() {
        super(new ModelStin(), 0.755F);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float par2) {
        EntityStin stin = (EntityStin) entity;

        if (!stin.isAdult() && !(this.mainModel instanceof ModelStinBaby)) {
            this.mainModel = new ModelStinBaby();
        } else if (stin.isAdult() && this.mainModel instanceof ModelStinBaby) {
            this.mainModel = new ModelStin();
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return texture;
    }

}
