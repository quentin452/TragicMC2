package tragicneko.tragicmc.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;

import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.doomsday.Doomsday.EnumDoomType;
import tragicneko.tragicmc.items.armor.TragicArmor;
import tragicneko.tragicmc.items.weapons.TragicBow;
import tragicneko.tragicmc.items.weapons.TragicTool;
import tragicneko.tragicmc.items.weapons.TragicWeapon;
import tragicneko.tragicmc.properties.PropertyDoom;

public class MessageHandlerUseDoomsday implements IMessageHandler<MessageUseDoomsday, MessageSound> {

    @Override
    public MessageSound onMessage(MessageUseDoomsday message, MessageContext ctx) {
        EntityPlayer player = MinecraftServer.getServer()
            .isDedicatedServer() ? TragicMC.proxy.getPlayerFromMessageCtx(ctx) : ctx.getServerHandler().playerEntity;

        if (player == null) return null;
        PropertyDoom doom = PropertyDoom.get(player);

        if (doom == null || !TragicConfig.allowDoom || !TragicConfig.allowDoomsdays) return null;

        ItemStack stack = message.stack;
        Doomsday doomsday = null;
        if (stack != null) {
            if (stack.getItem() instanceof TragicBow) {
                doomsday = ((TragicBow) stack.getItem()).doomsday;
            } else if (stack.getItem() instanceof TragicWeapon) {
                TragicWeapon weapon = (TragicWeapon) stack.getItem();
                doomsday = !player.isSneaking() && weapon.getSecondaryDoomsday() != null ? weapon.getSecondaryDoomsday()
                    : weapon.getDoomsday();
            } else if (stack.getItem() instanceof TragicTool) {
                doomsday = ((TragicTool) stack.getItem()).getDoomsday();
            }
        } else {
            boolean flag2 = false;
            Doomsday[] doomsdays = new Doomsday[4];

            for (int i = 0; i < 4; i++) {
                stack = player.getCurrentArmor(i);

                if (stack != null && stack.getItem() instanceof TragicArmor) {
                    if (i == 0) {
                        doomsday = ((TragicArmor) stack.getItem()).doomsday;
                    }

                    doomsdays[i] = ((TragicArmor) stack.getItem()).doomsday;
                }
            }

            for (int i = 0; i < 4; i++) {
                if (doomsdays[i] == null) {
                    flag2 = false;
                } else if (doomsdays[i] == doomsday) {
                    flag2 = true;
                } else {
                    flag2 = false;
                }
            }

            if (!flag2) return null;
        }

        if (doomsday == null) return null;

        if (doomsday.getDoomsdayType() == EnumDoomType.INFLUENCE && !TragicConfig.allowInfluenceDoomsdays
            || doomsday.getDoomsdayType() == EnumDoomType.CRISIS && !TragicConfig.allowCrisisDoomsdays
            || doomsday.getDoomsdayType() == EnumDoomType.OVERFLOW && !TragicConfig.allowOverflowDoomsdays
            || doomsday.getDoomsdayType() == EnumDoomType.WORLDSHAPER && !TragicConfig.allowWorldShaperDoomsdays
            || doomsday.getDoomsdayType() == EnumDoomType.COMBINATION && !TragicConfig.allowCombinationDoomsdays) {
            player.addChatComponentMessage(
                new ChatComponentText("The Doomsday of that type is disabled, enable in config."));
            return null;
        }

        if (!TragicConfig.doomsdayAllow[doomsday.doomID]) {
            player.addChatComponentMessage(
                new ChatComponentText("That particular Doomsday is disabled, enable in config."));
            return null;
        }

        String s = doomsday.activateDoomsday(doom) ? "tragicmc:random.doomsuccess" : "tragicmc:random.doomfailure";
        return new MessageSound(s, 0.6F, 1.0F);
    }

}
