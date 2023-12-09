package tragicneko.tragicmc.client.render.boss;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelTimeController;
import tragicneko.tragicmc.entity.boss.EntityTimeController;
import tragicneko.tragicmc.entity.boss.TragicBoss;

public class RenderTimeController extends RenderBoss {

    private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/TimeController.png");

    public RenderTimeController() {
        super(new ModelTimeController(), 0.415F);
    }

    @Override
    protected void renderModel(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5,
        float par6, float par7) {
        this.bindEntityTexture(par1EntityLivingBase);

        if (!par1EntityLivingBase.isInvisible()
            && !par1EntityLivingBase.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer)) {
            GL11.glPushMatrix();
            float f2 = 1.8F;
            EntityTimeController ctrl = (EntityTimeController) par1EntityLivingBase;
            if (ctrl.getLeapTicks() > 0) {
                f2 = 1.0F + MathHelper.cos(ctrl.ticksExisted * 0.4F) * 0.876F;
            }

            GL11.glColor4f(f2 - 0.4F, f2, f2 - 0.4F, 1.0F);
            GL11.glDepthMask(false);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
            this.mainModel.render(par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            GL11.glDepthMask(true);
            GL11.glPopMatrix();
        } else {
            this.mainModel.setRotationAngles(par2, par3, par4, par5, par6, par7, par1EntityLivingBase);
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

    protected int shouldRenderPass(TragicBoss boss, int par2, float par3) {
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

        EntityTimeController ctrl = (EntityTimeController) boss;

        if (ctrl.getPurgeTicks() > 0) {
            if (par2 == 1) {
                float f1 = boss.ticksExisted + par3;
                this.bindTexture(texture);
                GL11.glMatrixMode(GL11.GL_TEXTURE);
                GL11.glLoadIdentity();
                float f2 = MathHelper.cos(f1 * 0.15F) * 0.56F;
                float f3 = f1 * 0.02F;
                GL11.glTranslatef(f2, f3, 0.0F);
                this.setRenderPassModel(this.mainModel);
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glColor4f(0.1F, 0.65F, 0.1F, 0.35F);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
                GL11.glTranslatef(0.0F, 0.0F, 0.0F);
                GL11.glScalef(1.0F, 1.0F, 1.0F);
                return 2;
            }

            if (par2 == 2) {
                float f1 = (float) boss.ticksExisted + 28 + par3;
                this.bindTexture(texture);
                GL11.glMatrixMode(GL11.GL_TEXTURE);
                GL11.glLoadIdentity();
                float f2 = MathHelper.sin(f1 * 0.15F) * 0.56F;
                float f3 = f1 * 0.02F;
                GL11.glTranslatef(f3, f2, 0.0F);
                this.setRenderPassModel(this.mainModel);
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glColor4f(0.55F, 0.1F, 0.55F, 0.35F);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
                GL11.glTranslatef(0.0F, 0.0F, 0.0F);
                GL11.glScalef(1.0F, 1.0F, 1.0F);
                return 3;
            }
        }

        GL11.glMatrixMode(GL11.GL_TEXTURE);
        GL11.glLoadIdentity();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);

        return -1;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity var1) {
        return texture;
    }

}
