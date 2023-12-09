package tragicneko.tragicmc.client.gui;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.properties.PropertyAmulets;

@SideOnly(Side.CLIENT)
public class GuiAmuletStatus extends Gui {

    private final Minecraft mc;
    private static final RenderItem itemRender = new RenderItem();

    private static final ResourceLocation texturepath = new ResourceLocation(
        "tragicmc:textures/gui/amulet_status_minimal.png");
    private static final ResourceLocation texturepath2 = new ResourceLocation(
        "tragicmc:textures/gui/amulet_status_pink.png");
    private static final ResourceLocation texturepath3 = new ResourceLocation(
        "tragicmc:textures/gui/amulet_status_tentacle.png");
    private static final ResourceLocation texturepath4 = new ResourceLocation(
        "tragicmc:textures/gui/amulet_status_pokemon.png");

    public GuiAmuletStatus(Minecraft mc) {
        super();
        this.mc = mc;
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onRenderOverlay(RenderGameOverlayEvent event) {
        if (event.isCancelable() || event.type != ElementType.EXPERIENCE
            || Minecraft.getMinecraft().gameSettings.showDebugInfo) return;

        PropertyAmulets amu = PropertyAmulets.get(this.mc.thePlayer);
        if (amu == null || amu.getSlotsOpen() <= 0) return;

        int xPos = TragicConfig.guiX + 1;
        int yPos = TragicConfig.guiY + 11;
        this.mc.getTextureManager()
            .bindTexture(getTextureFromConfig());
        int length = 0;
        if (amu.getSlotsOpen() == 1) length = 20;
        if (amu.getSlotsOpen() == 2) length = 40;
        if (amu.getSlotsOpen() == 3) length = 60;

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        float trans = TragicConfig.guiTransparency / 100.0F;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, trans);
        GL11.glDisable(GL11.GL_ALPHA_TEST);

        drawTexturedModalRect(xPos, yPos, 0, 0, length, 20);

        ItemStack stack;

        for (int i = 0; i < 3; i++) {
            if (amu.inventory.getStackInSlot(i) != null) {
                stack = amu.inventory.getStackInSlot(i);

                itemRender.renderItemAndEffectIntoGUI(
                    mc.fontRenderer,
                    mc.getTextureManager(),
                    stack,
                    xPos + 3 + (20 * i),
                    yPos + 2);
                itemRender.renderItemOverlayIntoGUI(
                    mc.fontRenderer,
                    mc.getTextureManager(),
                    stack,
                    xPos + 2 + (20 * i),
                    yPos + 4);
                GL11.glDisable(GL11.GL_LIGHTING);
            } else if (amu.getSlotsOpen() < i + 1 && TragicConfig.amuletMaxSlots >= i + 1) {
                String s = "X";
                Color color = new Color(0x23, 0x23, 0x23);
                this.mc.fontRenderer.drawString(s.trim(), xPos + 8 + (20 * i), yPos + 6, color.getRGB());
            }
        }

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
    }

    public static ResourceLocation getTextureFromConfig() {
        switch (TragicConfig.guiTexture) {
            case 0:
                return texturepath3;
            case 1:
                return texturepath2;
            case 2:
                return texturepath;
            default:
                return texturepath4;
        }
    }
}
