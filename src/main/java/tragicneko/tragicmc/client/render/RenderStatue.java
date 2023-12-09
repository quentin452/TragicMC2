package tragicneko.tragicmc.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import tragicneko.tragicmc.client.model.ModelAegar;
import tragicneko.tragicmc.client.model.ModelApis;
import tragicneko.tragicmc.client.model.ModelBlock;
import tragicneko.tragicmc.client.model.ModelClaymation;
import tragicneko.tragicmc.client.model.ModelDeathReaper;
import tragicneko.tragicmc.client.model.ModelEnyvil;
import tragicneko.tragicmc.client.model.ModelGreaterStin;
import tragicneko.tragicmc.client.model.ModelJarra;
import tragicneko.tragicmc.client.model.ModelKitsune2;
import tragicneko.tragicmc.client.model.ModelKragul;
import tragicneko.tragicmc.client.model.ModelMegaCryse;
import tragicneko.tragicmc.client.model.ModelOverlordCocoon;
import tragicneko.tragicmc.client.model.ModelOverlordCombat;
import tragicneko.tragicmc.client.model.ModelOverlordCore;
import tragicneko.tragicmc.client.model.ModelPolaris;
import tragicneko.tragicmc.client.model.ModelStinKing;
import tragicneko.tragicmc.client.model.ModelStinQueen;
import tragicneko.tragicmc.client.model.ModelTimeController;
import tragicneko.tragicmc.client.model.ModelTox;
import tragicneko.tragicmc.client.model.ModelVoxStellarum;
import tragicneko.tragicmc.client.model.ModelYeti;
import tragicneko.tragicmc.entity.EntityStatue;

public class RenderStatue extends Render {

    private ModelBase model = new ModelBlock();

    private static final String mobPath = "tragicmc:textures/mobs/";
    private static final String textPath = "tragicmc:textures/statue/";

    private static final ResourceLocation[][] textures = new ResourceLocation[][] {
        { new ResourceLocation(mobPath + "ApisCombat2.png"), new ResourceLocation(mobPath + "Kitsune2.png"),
            new ResourceLocation(mobPath + "DeathReaper.png"), new ResourceLocation(mobPath + "TimeController.png"),
            new ResourceLocation(mobPath + "Yeti2.png"), new ResourceLocation(mobPath + "Polaris2.png"),
            new ResourceLocation(mobPath + "Jarra.png"), new ResourceLocation(mobPath + "Kragul.png"),
            new ResourceLocation(mobPath + "Magmox2.png"), new ResourceLocation(mobPath + "Cryse.png"),
            new ResourceLocation(mobPath + "StinKing.png"), new ResourceLocation(mobPath + "StinQueen.png"),
            new ResourceLocation(mobPath + "GreaterStin.png"), new ResourceLocation(mobPath + "VoxStellarum.png"),
            new ResourceLocation(mobPath + "Enyvil.png"), new ResourceLocation(mobPath + "Claymation.png"),
            new ResourceLocation(mobPath + "Aegar.png"), new ResourceLocation(mobPath + "OverlordCore.png"),
            new ResourceLocation(mobPath + "OverlordCombat.png"),
            new ResourceLocation(mobPath + "OverlordCocoon.png") },
        { new ResourceLocation(textPath + "IronStatue.png"), new ResourceLocation(textPath + "GoldStatue.png"),
            new ResourceLocation(textPath + "DiamondStatue.png"), new ResourceLocation(textPath + "StoneStatue.png"),
            new ResourceLocation(textPath + "WoodStatue.png"), new ResourceLocation(textPath + "EmeraldStatue.png"),
            new ResourceLocation(textPath + "LeafStatue.png"), new ResourceLocation(textPath + "TungstenStatue.png"),
            new ResourceLocation(textPath + "RubyStatue.png"), new ResourceLocation(textPath + "SapphireStatue.png"),
            new ResourceLocation(textPath + "RedstoneStatue.png"), new ResourceLocation(textPath + "CoalStatue.png"),
            new ResourceLocation(textPath + "LapisStatue.png"), new ResourceLocation(textPath + "NetherrackStatue.png"),
            new ResourceLocation(textPath + "EnderStatue.png") } };

    private static final ModelBase[] models = new ModelBase[] { new ModelApis(), new ModelKitsune2(),
        new ModelDeathReaper(), new ModelTimeController(), new ModelYeti(), new ModelPolaris(), new ModelJarra(),
        new ModelKragul(), new ModelTox(), new ModelMegaCryse(), new ModelStinKing(), new ModelStinQueen(),
        new ModelGreaterStin(), new ModelVoxStellarum(), new ModelEnyvil(), new ModelClaymation(), new ModelAegar(),
        new ModelOverlordCore(), new ModelOverlordCombat(), new ModelOverlordCocoon() };

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRender((EntityStatue) par1Entity, par2, par4, par6, par8, par9);
    }

    public void doRender(EntityStatue statue, double par2, double par3, double par4, float par5, float par6) {
        if (model != models[statue.getMobID()]) model = models[statue.getMobID()];

        boolean flag = model instanceof ModelApis || model instanceof ModelTimeController; // much smaller
        boolean flag2 = model instanceof ModelKragul; // much larger
        boolean flag3 = model instanceof ModelJarra || model instanceof ModelMegaCryse
            || model instanceof ModelClaymation
            || model instanceof ModelVoxStellarum; // slightly larger
        boolean flag4 = model instanceof ModelGreaterStin || model instanceof ModelStinKing
            || model instanceof ModelStinQueen
            || model instanceof ModelOverlordCombat; // slightly smaller

        float f = flag ? 0.505F : (flag2 ? 1.825F : (flag3 ? 0.925F : (flag4 ? 0.625F : 0.725F)));
        float f1 = flag ? 0.01725F : (flag2 ? 0.0825F : (flag3 ? 0.04F : (flag4 ? 0.02F : 0.03F)));
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glTranslatef((float) par2, (float) par3 + f, (float) par4);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        float f0 = statue.getAnimated() ? ((statue.ticksExisted * 2) % 360.0F) + statue.getEntityId() % 100
            : statue.getRotation();
        GL11.glRotatef(f0, 0.0F, 1.0F, 0.0F);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        this.bindTexture(this.getEntityTexture(statue));
        model.render(statue, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, f1);
        GL11.glDepthMask(true);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDepthFunc(GL11.GL_EQUAL);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return getEntityTexture((EntityStatue) entity);
    }

    private ResourceLocation getEntityTexture(EntityStatue statue) {
        if (statue.getTextureID() == 0) return textures[0][statue.getMobID()];
        return textures[1][statue.getTextureID() - 1];
    }

}
