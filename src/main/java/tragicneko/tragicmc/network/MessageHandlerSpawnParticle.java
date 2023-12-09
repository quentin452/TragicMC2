package tragicneko.tragicmc.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import tragicneko.tragicmc.TragicMC;

public class MessageHandlerSpawnParticle implements IMessageHandler<MessageParticle, IMessage> {

    @Override
    public IMessage onMessage(MessageParticle message, MessageContext ctx) {
        EntityPlayer player = TragicMC.proxy.getPlayerFromMessageCtx(ctx);
        if (player == null || player.worldObj == null || message.nameTag == null) return null;
        NBTTagCompound tag = message.nameTag;
        player.worldObj.spawnParticle(
            tag.hasKey("particleName") ? tag.getString("particleName") : "null",
            tag.hasKey("xPos") ? tag.getDouble("xPos") : 0.0,
            tag.hasKey("yPos") ? tag.getDouble("yPos") : 0.0,
            tag.hasKey("zPos") ? tag.getDouble("zPos") : 0.0,
            tag.hasKey("xAccel") ? tag.getDouble("xAccel") : 0.0,
            tag.hasKey("yAccel") ? tag.getDouble("yAccel") : 0.0,
            tag.hasKey("zAccel") ? tag.getDouble("zAccel") : 0.0);
        return null;
    }
}
