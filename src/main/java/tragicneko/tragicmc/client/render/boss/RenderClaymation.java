package tragicneko.tragicmc.client.render.boss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelApis;
import tragicneko.tragicmc.client.model.ModelClaymation;
import tragicneko.tragicmc.client.model.ModelCustomGolem;
import tragicneko.tragicmc.client.model.ModelDeathReaper;
import tragicneko.tragicmc.client.model.ModelJabba;
import tragicneko.tragicmc.client.model.ModelKitsune2;
import tragicneko.tragicmc.client.model.ModelMinotaur;
import tragicneko.tragicmc.client.model.ModelNorVox;
import tragicneko.tragicmc.client.model.ModelRagr;
import tragicneko.tragicmc.client.model.ModelStinKing;
import tragicneko.tragicmc.entity.boss.EntityClaymation;
import tragicneko.tragicmc.entity.boss.TragicBoss;

public class RenderClaymation extends RenderBoss {

    private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Claymation.png");

    private static final ModelBase[] models = new ModelBase[] { new ModelClaymation(), new ModelMinotaur(),
        new ModelApis(), new ModelStinKing(), new ModelNorVox(), new ModelJabba(), new ModelRagr(),
        new ModelDeathReaper(), new ModelKitsune2(), new ModelCustomGolem() };

    public RenderClaymation() {
        super(models[0], 0.556F);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float par2) {
        if (this.mainModel != this.getModelFromClaymationForm((EntityClaymation) entity))
            this.mainModel = this.getModelFromClaymationForm((EntityClaymation) entity);
        if (this.scale != this.getScaleFromClaymationForm((EntityClaymation) entity))
            this.scale = this.getScaleFromClaymationForm((EntityClaymation) entity);
        GL11.glScalef(scale, scale, scale);
    }

    protected int shouldRenderPass(TragicBoss boss, int par2, float par3) {
        if (boss.isInvisible()) {
            GL11.glDepthMask(false);
            return 0;
        } else {
            GL11.glDepthMask(true);
        }

        if (par2 == 0) {
            float f1 = boss.ticksExisted;
            this.bindTexture(texture);
            GL11.glMatrixMode(GL11.GL_TEXTURE);
            GL11.glLoadIdentity();
            float f2 = MathHelper.cos(f1 * 0.02F) * 0.05F;
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

    private float getScaleFromClaymationForm(EntityClaymation clay) {
        switch (clay.getEntityForm()) {
            case 3:
                return 1.625F;
            case 0:
            case 4:
                return 1.445F;
            default:
                return 1.0F;
        }
    }

    private ModelBase getModelFromClaymationForm(EntityClaymation clay) {
        return models[clay.getEntityForm()];
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return texture;
    }

}
