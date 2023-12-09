package tragicneko.tragicmc.blocks.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class TileEntityStructureSeed extends TileEntity {

    @Override
    public void updateEntity() {
        if (!this.worldObj.isRemote && this.worldObj.getTotalWorldTime() % 20L == 0L) this.growStructure();
    }

    public void growStructure() {
        int meta = this.getBlockMetadata();

        Structure structure = Structure.structureList[meta];
        if (structure == null) {
            EntityPlayer player = this.worldObj.getClosestPlayer(this.xCoord, this.yCoord, this.zCoord, 16.0);
            if (player != null) player.addChatMessage(
                new ChatComponentText(
                    "The structure you are attempting to generate is null for some reason. Try a different seed."));
            return;
        }

        if (structure.getHeight() + this.yCoord > 256) {
            EntityPlayer player = this.worldObj.getClosestPlayer(this.xCoord, this.yCoord, this.zCoord, 16.0);
            if (player != null) player.addChatMessage(
                new ChatComponentText(
                    structure.getLocalizedName() + " wasn't able to generate due to not enough height!"));
            return;
        }

        if (structure.generateStructureWithVariant(
            this.worldObj.rand.nextInt(structure.getVariantSize()),
            this.worldObj,
            this.worldObj.rand,
            this.xCoord,
            this.yCoord,
            this.zCoord)) {
            EntityPlayer player = this.worldObj.getClosestPlayer(this.xCoord, this.yCoord, this.zCoord, 16.0);
            if (player != null) player
                .addChatMessage(new ChatComponentText(structure.getLocalizedName() + " was generated successfully!"));
        } else {
            TragicMC.logError(
                "Something went wrong while generating a " + structure.getLocalizedName() + " with a structure seed");
        }
    }

}
