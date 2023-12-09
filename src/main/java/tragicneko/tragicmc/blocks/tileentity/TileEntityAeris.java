package tragicneko.tragicmc.blocks.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.items.ItemCorruptedEgg;
import tragicneko.tragicmc.util.WorldHelper;

import java.util.ArrayList;

public class TileEntityAeris extends TileEntity {

    private int corruptedTicks;

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!this.worldObj.isRemote && this.yCoord <= 50
            && !this.worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord, this.zCoord)
            && this.blockMetadata < 2) {
            corruptedTicks++;
            this.worldObj.getChunkFromBlockCoords(this.xCoord, this.zCoord).inhabitedTime += 100;
            EntityPlayer player = this.worldObj.getClosestPlayer(this.xCoord, this.yCoord, this.zCoord, 16.0D);

            if (this.corruptedTicks % 300 == 0 && player != null && this.corruptedTicks < 3000) {
                player.addChatMessage(
                    new ChatComponentText("Aeris is corrupting... (" + (this.corruptedTicks / 300) + " /10)"));

                if (this.blockMetadata == 1) {
                    for (int i = 0; i < this.worldObj.rand.nextInt(3) + 1; i++) {
                        ItemCorruptedEgg.spawnCreature(
                            this.worldObj,
                            this.xCoord + (this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble() * 4),
                            this.yCoord,
                            this.zCoord + (this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble() * 4));
                    }
                }
            }

            if (this.corruptedTicks >= 3000) {
                this.corruptedTicks = 0;
                this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, blockMetadata + 1, 2);
                String s = this.blockMetadata == 1 ? "Aeris is starting to show signs of corruption..."
                    : "Aeris has fully corrupted!";
                if (player != null) player.addChatMessage(new ChatComponentText(s));
            }

            if (this.corruptedTicks % 20 == 0) {
                ArrayList<int[]> list = WorldHelper
                    .getBlocksInSphericalRange(this.worldObj, 5.0, this.xCoord, this.yCoord, this.zCoord);
                Block block;
                for (int[] coords : list) {
                    block = this.worldObj.getBlock(coords[0], coords[1], coords[2]);
                    if (block != TragicBlocks.Aeris && coords[1] >= this.yCoord
                        && block.getMaterial() != Material.air) {
                        this.worldObj.func_147480_a(coords[0], coords[1], coords[2], true);
                    }
                }
            }
        }
    }
}
