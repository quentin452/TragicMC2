package tragicneko.tragicmc.client.render.alpha;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelOverlordCombat;
import tragicneko.tragicmc.client.render.boss.RenderBoss;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCombat;

public class RenderOverlordCombat extends RenderBoss {

    private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/OverlordCombat.png");
    private static final ResourceLocation noiseTexture = new ResourceLocation(
        "tragicmc:textures/mobs/OverlordCombatNoise.png");

    public RenderOverlordCombat() {
        super(new ModelOverlordCombat(), 0.756F, 2.25F);
    }

    @Override
    protected void rotateCorpse(EntityLivingBase entity, float par1, float par2, float par3) {
        if (entity.deathTime > 0) return;
        super.rotateCorpse(entity, par1, par2, par3);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
        GL11.glScalef(this.scale, this.scale, this.scale);
    }

    @Override
    protected void renderModel(EntityLivingBase entity, float par2, float par3, float par4, float par5, float par6,
        float par7) {
        super.renderModel(entity, par2, par3, par4, par5, par6, par7);
    }

    @Override
    protected int shouldRenderPass(EntityLivingBase boss, int par2, float par3) {
        EntityOverlordCombat combat = (EntityOverlordCombat) boss;

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

        if (combat.getReflectionTicks() > 0) {
            if (par2 == 1) {
                float f1 = boss.ticksExisted + par3;
                this.bindTexture(noiseTexture);
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
    protected ResourceLocation getEntityTexture(Entity entity) {
        return getEntityTexture((EntityOverlordCombat) entity);
    }

    public ResourceLocation getEntityTexture(EntityOverlordCombat core) {
        return texture;
    }

}
