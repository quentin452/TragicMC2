package tragicneko.tragicmc.client.render.boss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelApis;
import tragicneko.tragicmc.entity.boss.EntityApis;
import tragicneko.tragicmc.entity.boss.TragicBoss;

public class RenderApis extends RenderBoss {

    private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/ApisCombat2.png");
    private static final ResourceLocation combatTexture = new ResourceLocation(
        "tragicmc:textures/mobs/ApisCombat3.png");

    public RenderApis() {
        super(new ModelApis(), 0.556F);
    }

    protected int shouldRenderPass(TragicBoss boss, int par2, float par3) {
        EntityApis apis = (EntityApis) boss;

        if (boss.isInvisible()) {
            GL11.glDepthMask(false);
            return 0;
        } else {
            GL11.glDepthMask(true);
        }

        if (par2 == 0) {
            this.setRenderPassModel(this.mainModel);
            GL11.glEnable(GL11.GL_NORMALIZE);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            return 1;
        }

        if (apis.isReflecting()) {
            if (par2 == 1) {
                float f1 = boss.ticksExisted + par3;
                this.bindTexture(combatTexture);
                GL11.glMatrixMode(GL11.GL_TEXTURE);
                GL11.glLoadIdentity();
                float f2 = MathHelper.cos(f1 * 0.02F) * 3.0F;
                float f3 = f1 * 0.01F;
                GL11.glTranslatef(f2, f3, 0.0F);
                this.setRenderPassModel(this.mainModel);
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glEnable(GL11.GL_BLEND);
                float f4 = 0.5F;
                GL11.glColor4f(f4, f4, f4, 1.0F);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
                GL11.glTranslatef(0.0F, 0.1F, 0.0F);
                GL11.glScalef(1.1F, 1.1F, 1.1F);
                return 1;
            }

            if (par2 == 2) {
                GL11.glMatrixMode(GL11.GL_TEXTURE);
                GL11.glLoadIdentity();
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_BLEND);
                return -1;
            }
        }

        return -1;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
        GL11.glScalef(1.0F, 1.0F, 1.0F);

        if (par1EntityLivingBase.getHealth() <= par1EntityLivingBase.getMaxHealth() / 2) {
            GL11.glTranslatef(0.0F, -0.001F, 0.0F);
        }
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
