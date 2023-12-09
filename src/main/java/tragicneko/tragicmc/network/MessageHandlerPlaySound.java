package tragicneko.tragicmc.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import tragicneko.tragicmc.TragicMC;

public class MessageHandlerPlaySound implements IMessageHandler<MessageSound, IMessage> {

    @Override
    public IMessage onMessage(MessageSound message, MessageContext ctx) {
        EntityPlayer player = TragicMC.proxy.getPlayerFromMessageCtx(ctx);

        if (player == null || player.worldObj == null || message.nameTag == null) return null;
        NBTTagCompound tag = message.nameTag;
        player.playSound(
            tag.hasKey("name") ? tag.getString("name") : "null",
            tag.hasKey("volume") ? tag.getFloat("volume") : 1F,
            tag.hasKey("pitch") ? tag.getFloat("pitch") : 1F);
        return null;
    }
}
