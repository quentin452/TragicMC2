package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;

import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayRecall extends Doomsday {

    public DoomsdayRecall(int id) {
        super(id);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        int dim = player.worldObj.provider.dimensionId;
        ChunkCoordinates cc = player.getBedLocation(dim);

        if (cc != null) {
            player.setPositionAndUpdate(cc.posX, cc.posY, cc.posZ);
        } else {
            cc = player.worldObj.getSpawnPoint();
            player.setPositionAndUpdate(cc.posX, cc.posY, cc.posZ);
        }

        player.addChatMessage(
            new ChatComponentText(
                EnumChatFormatting.ITALIC + player
                    .getCommandSenderName() + " was teleported to " + cc.posX + ", " + cc.posY + ", " + cc.posZ));
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

    }

}
