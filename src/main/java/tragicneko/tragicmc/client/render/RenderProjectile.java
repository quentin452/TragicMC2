package tragicneko.tragicmc.client.render;

import static tragicneko.tragicmc.TragicItems.Projectile;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.entity.projectile.EntityThrowingRock;

@SideOnly(Side.CLIENT)
public class RenderProjectile extends Render {

    private int damage = 0;

    public RenderProjectile() {}

    public RenderProjectile(int damage) {
        this.damage = damage;
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        float scale = 0.5F;
        if (par1Entity instanceof EntityThrowingRock && ((EntityThrowingRock) par1Entity).isLavaRock()) this.damage = 1;
        IIcon icon = Projectile.getIconFromDamage(this.damage);

        if (icon != null) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float) par2, (float) par4, (float) par6);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glScalef(scale, scale, scale);
            this.bindEntityTexture(par1Entity);
            Tessellator tessellator = Tessellator.instance;
            this.renderIcon(tessellator, icon);
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return TextureMap.locationItemsTexture;
    }

    private void renderIcon(Tessellator par1Tessellator, IIcon par2Icon) {
        float f = par2Icon.getMinU();
        float f1 = par2Icon.getMaxU();
        float f2 = par2Icon.getMinV();
        float f3 = par2Icon.getMaxV();
        float f4 = 1.0F;
        float f5 = 0.5F;
        float f6 = 0.25F;
        GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        par1Tessellator.startDrawingQuads();
        par1Tessellator.setNormal(0.0F, 1.0F, 0.0F);
        par1Tessellator.addVertexWithUV(0.0F - f5, 0.0F - f6, 0.0D, f, f3);
        par1Tessellator.addVertexWithUV(f4 - f5, 0.0F - f6, 0.0D, f1, f3);
        par1Tessellator.addVertexWithUV(f4 - f5, f4 - f6, 0.0D, f1, f2);
        par1Tessellator.addVertexWithUV(0.0F - f5, f4 - f6, 0.0D, f, f2);
        par1Tessellator.draw();
    }
}
