package tragicneko.tragicmc.blocks.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.storage.WorldInfo;
import tragicneko.tragicmc.TragicConfig;

import java.util.Random;

public class TileEntityTimeDisruptor extends TileEntity {

    @Override
    public void updateEntity() {
        if (this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord)
            && TragicConfig.allowItemTimeAltering) {
            WorldInfo info = this.worldObj.getWorldInfo();
            Random rand = this.worldObj.rand;
            info.setWorldTime(rand.nextInt(6000) + rand.nextInt(6000) + rand.nextInt(6000) + rand.nextInt(6000));
        }
    }
}
