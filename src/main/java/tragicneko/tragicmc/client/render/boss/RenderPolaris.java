package tragicneko.tragicmc.client.render.boss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelPolaris;
import tragicneko.tragicmc.entity.boss.EntityPolaris;
import tragicneko.tragicmc.entity.boss.TragicBoss;

public class RenderPolaris extends RenderBoss {

    private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Polaris2.png");

    public RenderPolaris() {
        super(new ModelPolaris(), 0.335F);
    }

    protected int shouldRenderPass(TragicBoss boss, int par2, float par3) {
        if (boss.isInvisible()) {
            GL11.glDepthMask(false);
            return 0;
        } else {
            GL11.glDepthMask(true);
        }

        EntityPolaris polar = (EntityPolaris) boss;

        if (par2 == 0 && !polar.getDaytime()) {
            float f1 = boss.ticksExisted;
            this.bindTexture(texture);
            GL11.glMatrixMode(GL11.GL_TEXTURE);
            GL11.glLoadIdentity();
            float f2 = MathHelper.cos(f1 * 0.02F) * 0.5F;
            float f3 = f1 * 0.01F;
            GL11.glTranslatef(f2, f3, 0.0F);
            this.setRenderPassModel(this.mainModel);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glEnable(GL11.GL_NORMALIZE);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            return 1;
        }

        GL11.glMatrixMode(GL11.GL_TEXTURE);
        GL11.glLoadIdentity();
        GL11.glMatrixMode(GL11.GL_TEXTURE);
        GL11.glLoadIdentity();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);

        return -1;
    }

    @Override
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
        return this.shouldRenderPass((TragicBoss) par1EntityLivingBase, par2, par3);
    }

    @Override
    protected int inheritRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
        return -1;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return texture;
    }
}
