package tragicneko.tragicmc.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiPetStatus extends GuiContainer {

    private static final ResourceLocation location = new ResourceLocation(
        "tragicmc:textures/gui/amulet_tragicneko.png");

    public GuiPetStatus(Container container) {
        super(container);
    }

    @Override
    protected void keyTyped(char c, int keyCode) {
        super.keyTyped(c, keyCode);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {
        mc.getTextureManager()
            .bindTexture(location);
        drawTexturedModalRect(guiLeft + 16, guiTop - 2, 0, 0, xSize, ySize + 24);

    }

}
