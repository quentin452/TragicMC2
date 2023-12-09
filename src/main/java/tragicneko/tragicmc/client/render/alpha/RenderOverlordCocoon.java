package tragicneko.tragicmc.client.render.alpha;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelOverlordCocoon;
import tragicneko.tragicmc.client.render.boss.RenderBoss;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCocoon;

public class RenderOverlordCocoon extends RenderBoss {

    private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/OverlordCocoon.png");

    public RenderOverlordCocoon() {
        super(new ModelOverlordCocoon(), 0.756F, 3.25F);
    }

    @Override
    protected void rotateCorpse(EntityLivingBase entity, float par1, float par2, float par3) {
        GL11.glRotatef(180.0F - par2, 0.0F, 1.0F, 0.0F);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
        GL11.glScalef(this.scale, this.scale, this.scale);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return getEntityTexture((EntityOverlordCocoon) entity);
    }

    public ResourceLocation getEntityTexture(EntityOverlordCocoon core) {
        return texture;
    }
}
