package tragicneko.tragicmc.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.properties.PropertyMisc;

public class MessageHandlerFrozenInput implements IMessageHandler<MessageFrozenInput, IMessage> {

    @Override
    public IMessage onMessage(MessageFrozenInput message, MessageContext ctx) {
        boolean unfreeze = message.unfreeze == 1;
        EntityPlayer player = MinecraftServer.getServer()
            .isDedicatedServer() ? TragicMC.proxy.getPlayerFromMessageCtx(ctx) : ctx.getServerHandler().playerEntity;
        if (player == null) return null;

        PropertyMisc misc = PropertyMisc.get(player);
        if (misc == null) return null;

        if (misc.frozenInputs > 0) misc.frozenInputs--;

        if (unfreeze) {
            // if (player.isPotionActive(TragicPotion.Frozen.id)) player.removePotionEffect(TragicPotion.Frozen.id);
            misc.frozenInputs = 0;
            misc.isFrozen = false;
            player.worldObj.playSoundAtEntity(player, "tragicmc:mob.cryse.break", 0.2F, 1.0F);
        }

        return null;
    }
}
