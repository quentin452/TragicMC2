package tragicneko.tragicmc.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.weapon.ModelButcher;
import tragicneko.tragicmc.client.model.weapon.ModelCelestialAegis;
import tragicneko.tragicmc.client.model.weapon.ModelDragonFang;
import tragicneko.tragicmc.client.model.weapon.ModelNekoLauncher;
import tragicneko.tragicmc.client.model.weapon.ModelParanoia;
import tragicneko.tragicmc.client.model.weapon.ModelReaperScythe;
import tragicneko.tragicmc.client.model.weapon.ModelSentinel;
import tragicneko.tragicmc.client.model.weapon.ModelSilentHellraiser;
import tragicneko.tragicmc.client.model.weapon.ModelSplinter;
import tragicneko.tragicmc.client.model.weapon.ModelThardus;
import tragicneko.tragicmc.client.model.weapon.ModelTitan;

public class RenderEpicWeapon implements IItemRenderer {

    private static final String path = "tragicmc:textures/weapons/";
    private final Minecraft mc;
    public final ModelBase model;
    public final ResourceLocation texture;

    public static final ModelBase[] models = new ModelBase[] { new ModelReaperScythe(), new ModelButcher(),
        new ModelDragonFang(), new ModelThardus(), new ModelSplinter(), new ModelParanoia(), new ModelCelestialAegis(),
        new ModelTitan(), new ModelSilentHellraiser(), new ModelSentinel(), new ModelNekoLauncher(),
        new ModelNekoLauncher() };

    public static final ResourceLocation[] textures = new ResourceLocation[] {
        new ResourceLocation(path + "ReaperScythe.png"), new ResourceLocation(path + "Butcher.png"),
        new ResourceLocation(path + "DragonFang.png"), new ResourceLocation(path + "Thardus.png"),
        new ResourceLocation(path + "Splinter.png"), new ResourceLocation(path + "Paranoia.png"),
        new ResourceLocation(path + "CelestialAegis.png"), new ResourceLocation(path + "Titan.png"),
        new ResourceLocation(path + "ReaperScythe.png"), new ResourceLocation(path + "Sentinel.png"),
        new ResourceLocation(path + "NekoLauncher.png"),
        new ResourceLocation("tragicmc:textures/armor/LightArmor.png") };

    private final ResourceLocation itemGlint = new ResourceLocation("textures/misc/enchanted_item_glint.png");

    public RenderEpicWeapon(int i, Minecraft mc) {
        this.model = this.getModelFromItem(i);
        this.texture = this.getTextureFromItem(i);
        this.mc = mc;
    }

    private ResourceLocation getTextureFromItem(int i) {
        return textures[i];
    }

    private ModelBase getModelFromItem(int i) {
        return models[i];
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {

        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushMatrix();
        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON && this.model instanceof ModelNekoLauncher)
            GL11.glRotatef(-22.5F, 0F, 0.0F, 1F);
        float f = 0.065F;
        GL11.glScalef(f, f, f);
        mc.renderEngine.bindTexture(texture);
        model.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.625F);
        GL11.glPopMatrix();
    }

}
