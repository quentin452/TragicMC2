package tragicneko.tragicmc.network;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;

public class MessageHandlerAttack implements IMessageHandler<MessageAttack, IMessage> {

    @Override
    public IMessage onMessage(MessageAttack message, MessageContext ctx) {
        EntityPlayer player = MinecraftServer.getServer()
            .isDedicatedServer() ? TragicMC.proxy.getPlayerFromMessageCtx(ctx) : ctx.getServerHandler().playerEntity;
        if (player != null) {
            Entity entity = player.worldObj.getEntityByID(message.entityID);
            if (entity != null) {
                if (entity instanceof EntityPlayer && !TragicConfig.allowPvP) return null;
                player.attackTargetEntityWithCurrentItem(entity);
            }
        }
        return null;
    }

}
