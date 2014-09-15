package tragicneko.tragicmc.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import tragicneko.tragicmc.client.model.ModelBlock;
import tragicneko.tragicmc.entity.EntityDarkCrystal;

public class RenderDarkCrystal extends Render {
	
	public ModelBase model;
	
	public RenderDarkCrystal()
	{
		super();
		this.model = new ModelBlock();
	}

	@Override
	public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
		this.doRender((EntityDarkCrystal) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	}

	public void doRender(EntityDarkCrystal entity, double par2, double par3, double par4, float par5, float par6) {
		float f = 0.625F;		
		float f1 = 0.025F;
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glTranslatef((float)par2, (float)par3 + f, (float)par4);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		float f0 = MathHelper.wrapAngleTo180_float(entity.rotationTicks);
		GL11.glRotatef(f0, 0.0F, 1.0F, 0.0F);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		this.bindTexture(this.getEntityTexture(entity));
		this.model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, f1);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return null;
	}

}
