package tragicneko.tragicmc.client.render.alpha;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelOverlordCore;
import tragicneko.tragicmc.client.render.boss.RenderBoss;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCore;

public class RenderOverlordCore extends RenderBoss {

    private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/OverlordCore.png");
    private static final ResourceLocation damagedTexture = new ResourceLocation(
        "tragicmc:textures/mobs/OverlordCoreDamaged.png");
    private static final ResourceLocation enderDragonExplodingTextures = new ResourceLocation(
        "textures/entity/enderdragon/dragon_exploding.png");

    public RenderOverlordCore() {
        super(new ModelOverlordCore(), 0.756F, 2.25F);
    }

    @Override
    protected void rotateCorpse(EntityLivingBase entity, float par1, float par2, float par3) {
        if (entity.deathTime > 0) return;
        super.rotateCorpse(entity, par1, par2, par3);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
        GL11.glScalef(this.scale, this.scale, this.scale);
        GL11.glTranslatef(0F, 0.5F, 0F);
    }

    @Override
    protected void renderModel(EntityLivingBase entity, float par2, float par3, float par4, float par5, float par6,
        float par7) {
        EntityOverlordCore core = (EntityOverlordCore) entity;

        if (core.deathTime > 0) {
            float f6 = core.deathTime / 300.0F;
            GL11.glDepthFunc(GL11.GL_LEQUAL);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glAlphaFunc(GL11.GL_GREATER, f6);
            this.bindTexture(enderDragonExplodingTextures);
            GL11.glTranslated(
                entity.getRNG()
                    .nextGaussian() * 0.3,
                entity.getRNG()
                    .nextGaussian() * 0.3,
                entity.getRNG()
                    .nextGaussian() * 0.3);
            this.mainModel.render(core, par2, par3, par4, par5, par6, par7);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            GL11.glDepthFunc(GL11.GL_EQUAL);
        }

        this.bindEntityTexture(core);

        if (!core.isInvisible() && core.getVulnerableTicks() == 0
            && core.getTransformationTicks() <= 60
            && core.getTransformationTicks() >= 20) {
            this.mainModel.render(core, par2, par3, par4, par5, par6, par7);
        } else if (!core.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer)) {
            float trans = 1.0F;

            GL11.glPushMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, trans);
            GL11.glDepthMask(false);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.03921569F);
            if (core.deathTime > 0) GL11.glTranslated(
                entity.getRNG()
                    .nextGaussian() * 0.2,
                entity.getRNG()
                    .nextGaussian() * 0.2,
                entity.getRNG()
                    .nextGaussian() * 0.2);
            this.mainModel.render(core, par2, par3, par4, par5, par6, par7);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            GL11.glDepthMask(true);
            GL11.glPopMatrix();
        } else {
            this.mainModel.setRotationAngles(par2, par3, par4, par5, par6, par7, core);
        }
    }

    @Override
    protected int shouldRenderPass(EntityLivingBase entity, int pass, float partialTick) {
        EntityOverlordCore core = (EntityOverlordCore) entity;

        if (core.isInvisible()) {
            GL11.glDepthMask(false);
            return 0;
        } else {
            GL11.glDepthMask(true);
        }

        if (pass == 0) {
            this.setRenderPassModel(this.mainModel);
            GL11.glEnable(GL11.GL_NORMALIZE);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            return 1;
        }
        /*
         * if (core.deathTime > 0)
         * {
         * Random rand = core.getRNG();
         * if (pass == 1)
         * {
         * float f1 = core.deathTime + partialTick;
         * this.bindTexture(texture);
         * GL11.glMatrixMode(GL11.GL_TEXTURE);
         * GL11.glLoadIdentity();
         * float f2 = MathHelper.cos(f1 * 0.02F) * 3.0F;
         * float f3 = f1 * 0.01F;
         * GL11.glTranslatef(f2, f3, 0.0F);
         * this.setRenderPassModel(this.mainModel);
         * GL11.glMatrixMode(GL11.GL_MODELVIEW);
         * GL11.glEnable(GL11.GL_BLEND);
         * float f4 = 1.0F;
         * GL11.glColor4f(f4, f4, f4, 1.0F);
         * GL11.glDisable(GL11.GL_LIGHTING);
         * GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
         * GL11.glTranslatef(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
         * GL11.glScalef(1.1F + rand.nextFloat(), 1.1F + rand.nextFloat(), 1.1F + rand.nextFloat());
         * return 1;
         * }
         * if (pass == 2)
         * {
         * GL11.glMatrixMode(GL11.GL_TEXTURE);
         * GL11.glLoadIdentity();
         * GL11.glMatrixMode(GL11.GL_MODELVIEW);
         * GL11.glEnable(GL11.GL_LIGHTING);
         * GL11.glDisable(GL11.GL_BLEND);
         * return -1;
         * }
         * }
         */

        if (pass == 1) {
            if (core.getVulnerableTicks() == 0
                || core.getTransformationTicks() >= 20 && core.getTransformationTicks() <= 60) return -1;
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }

        return -1;

    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return getEntityTexture((EntityOverlordCore) entity);
    }

    public ResourceLocation getEntityTexture(EntityOverlordCore core) {
        return core.getVulnerableTicks() > 0
            || core.getTransformationTicks() <= 60 && core.getTransformationTicks() >= 20 ? damagedTexture : texture;
    }

}
