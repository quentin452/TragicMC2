package tragicneko.tragicmc.client.render;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSoulChest extends TileEntitySpecialRenderer {

    private static final ResourceLocation doubleTexture = new ResourceLocation(
        "tragicmc:textures/entities/SoulChestDouble.png");
    private static final ResourceLocation singleTexture = new ResourceLocation(
        "tragicmc:textures/entities/SoulChestSingle.png");
    private ModelChest singleModel = new ModelChest();
    private ModelChest doubleModel = new ModelLargeChest();

    public void renderTileEntityAt(TileEntityChest te, double x, double y, double z, float partialTick) {
        int i;

        if (!te.hasWorldObj()) {
            i = 0;
        } else {
            Block block = te.getBlockType();
            i = te.getBlockMetadata();

            if (block instanceof BlockChest && i == 0) {
                try {
                    ((BlockChest) block).func_149954_e(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord);
                } catch (ClassCastException e) {
                    FMLLog.severe(
                        "Attempted to render a chest at %d,  %d, %d that was not a chest",
                        te.xCoord,
                        te.yCoord,
                        te.zCoord);
                }
                i = te.getBlockMetadata();
            }

            te.checkForAdjacentChests();
        }

        if (te.adjacentChestZNeg == null && te.adjacentChestXNeg == null) {
            ModelChest modelchest;

            if (te.adjacentChestXPos == null && te.adjacentChestZPos == null) {
                modelchest = this.singleModel;
                this.bindTexture(singleTexture);
            } else {
                modelchest = this.doubleModel;
                this.bindTexture(doubleTexture);
            }

            GL11.glPushMatrix();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glTranslatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
            GL11.glScalef(1.0F, -1.0F, -1.0F);
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            short short1 = 0;

            if (i == 2) {
                short1 = 180;
            }

            if (i == 3) {
                short1 = 0;
            }

            if (i == 4) {
                short1 = 90;
            }

            if (i == 5) {
                short1 = -90;
            }

            if (i == 2 && te.adjacentChestXPos != null) {
                GL11.glTranslatef(1.0F, 0.0F, 0.0F);
            }

            if (i == 5 && te.adjacentChestZPos != null) {
                GL11.glTranslatef(0.0F, 0.0F, -1.0F);
            }

            GL11.glRotatef(short1, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            float f1 = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * partialTick;
            float f2;

            if (te.adjacentChestZNeg != null) {
                f2 = te.adjacentChestZNeg.prevLidAngle
                    + (te.adjacentChestZNeg.lidAngle - te.adjacentChestZNeg.prevLidAngle) * partialTick;

                if (f2 > f1) {
                    f1 = f2;
                }
            }

            if (te.adjacentChestXNeg != null) {
                f2 = te.adjacentChestXNeg.prevLidAngle
                    + (te.adjacentChestXNeg.lidAngle - te.adjacentChestXNeg.prevLidAngle) * partialTick;

                if (f2 > f1) {
                    f1 = f2;
                }
            }

            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            modelchest.chestLid.rotateAngleX = -(f1 * (float) Math.PI / 2.0F);
            modelchest.renderAll();
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTick) {
        this.renderTileEntityAt((TileEntityChest) te, x, y, z, partialTick);
    }
}
