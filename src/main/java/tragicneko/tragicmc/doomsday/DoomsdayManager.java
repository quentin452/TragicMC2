package tragicneko.tragicmc.doomsday;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerDisconnectionFromClientEvent;
import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday.EnumDoomType;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;

public class DoomsdayManager {

    public static final Logger logger = LogManager.getLogger(TragicMC.MODID + "/Doomsday Manager");
    private static Map<UUID, ArrayList<DoomsdayEffect>> playerMap = new HashMap();

    public synchronized static void registerDoomsdayEffect(UUID playerID, DoomsdayEffect effect) {
        if (playerMap.containsKey(playerID)) {
            try {
                ArrayList<DoomsdayEffect> list = playerMap.get(playerID);

                for (int i = 0; i < list.size(); i++) {
                    DoomsdayEffect effect2 = list.get(i);
                    if (effect2.dday == effect.dday) {
                        logger.warn(
                            "" + getUsernameFromUUID(playerID)
                                + " attempted to register a new Doomsday effect for a Doomsday they already have active, ignoring registration.");
                        return;
                    }
                }
                list.add(effect);
            } catch (ConcurrentModificationException e) {
                logger.error("Error caught while attempting to register a new Doomsday effect.", e);
                return;
            }
        } else {
            try {
                ArrayList<DoomsdayEffect> list = new ArrayList();
                list.add(effect);
                playerMap.put(playerID, list);
            } catch (ConcurrentModificationException e) {
                logger.error("Error caught while attempting to register a new Doomsday effect.", e);
                return;
            }
        }
    }

    public synchronized static void clearRegistry() {
        try {
            playerMap.clear();
            logger.info("Doomsday map was cleared.");
        } catch (Exception e) {
            logger.error("Error caught while clearing the playerMap", e);
        }
    }

    public synchronized static void clearPlayerFromRegistry(UUID playerID, String reason) {
        try {
            if (playerMap.containsKey(playerID)) {
                playerMap.remove(playerID);
                if (reason != null) {
                    logger.info(
                        "Registry removed registration for " + getUsernameFromUUID(playerID) + ", reason: " + reason);
                }
            } else {
                logger.error(
                    "Attempted to remove a player that was not registered in the map, there is a problem somewhere.");
            }
        } catch (Exception e) {
            logger.error("Error caught while attempting to remove a player from the playerMap", e);
        }
    }

    public static String getUsernameFromUUID(UUID uuid) {
        try {
            for (int i = 0; i < MinecraftServer.getServer()
                .getConfigurationManager().playerEntityList.size(); i++) {
                EntityPlayer player = (EntityPlayer) MinecraftServer.getServer()
                    .getConfigurationManager().playerEntityList.get(i);
                if (player.getUniqueID() == uuid) return player.getCommandSenderName();
            }
        } catch (Exception e) {
            logger.error("There was an error while retrieving a username from a UUID", e);
        }
        return uuid.toString();
    }

    @SubscribeEvent
    public void onTick(ServerTickEvent event) {
        if (playerMap.isEmpty()) return;

        if (event.phase == Phase.END) {
            Set set = playerMap.keySet();
            Iterator<UUID> ite = set.iterator();
            String reason = null;

            while (ite.hasNext()) {
                ArrayList<DoomsdayEffect> list = playerMap.get(ite.next());
                DoomsdayEffect effect = null;
                DoomsdayEffect temp = null;
                reason = null;

                boolean flag = false;

                if (list.size() == 0) reason = "No Doomsday effects registered.";

                for (int i = 0; i < list.size(); i++) {
                    effect = list.get(i);

                    if (effect == null) {
                        logger.error(
                            "Player had a null registration somehow! To prevent any problems, the player's registrations were cleared!");
                        reason = "Null Doomsday registration.";
                        list.clear();
                        break;
                    }

                    if (TragicConfig.allowCombinationDoomsdays) {
                        if (flag && temp != null && temp.dday.getCombination() == effect.dday) {
                            if (temp.isActive && !temp.isInstant) {
                                if (!effect.player.capabilities.isCreativeMode && !effect.isCommandActivated)
                                    temp.dday.applyDoomCost(temp.doom);
                            }

                            list.clear();

                            try {
                                list.add(
                                    new DoomsdayEffect(
                                        effect.dday.getCombination().doomID,
                                        effect.doom,
                                        effect.isCommandActivated).inheritCooldown(temp, effect));
                            } catch (NullPointerException e) {
                                e.printStackTrace();
                                logger.error("Combination doomsdays had a null Combination, report this!");
                                break;
                            }

                            if (TragicConfig.allowAchievements)
                                effect.player.triggerAchievement(TragicAchievements.doomsdayCombo);
                            break;
                        }

                        if (effect.dday instanceof IExtendedDoomsday && effect.dday.getCombination() != null) {
                            flag = true;
                            temp = effect;
                        }

                        if (!(effect.dday instanceof IExtendedDoomsday) && TragicConfig.allowPartnerDoomsdays) // TODO
                                                                                                               // test
                                                                                                               // partner
                                                                                                               // doomsdays
                        {
                            Iterator<UUID> it = playerMap.keySet()
                                .iterator();

                            while (it.hasNext()) {
                                UUID uuid = it.next();
                                ArrayList<DoomsdayEffect> effs = playerMap.get(uuid);
                                DoomsdayEffect eff;

                                for (int j = 0; j < effs.size(); j++) {
                                    eff = effs.get(j);
                                    if (eff.dday instanceof IExtendedDoomsday
                                        && eff.dday.getCombination() == effect.dday
                                        && effect.doom.getPlayer()
                                            .getDistanceToEntity(eff.doom.getPlayer())
                                            <= TragicConfig.partnerDoomsdayDistance) {
                                        list.clear();
                                        reason = "Partner activated a Combination.";
                                        clearPlayerFromRegistry(uuid, "Partner Combination activated.");
                                        registerDoomsdayEffect(
                                            uuid,
                                            new DoomsdayEffect(
                                                effect.dday.getCombination().doomID,
                                                effect.doom,
                                                effect.isCommandActivated).inheritCooldown(eff, effect));
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    effect.onDoomsdayUpdate();

                    if (!effect.isActive && effect.isInstant || effect.dday instanceof IExtendedDoomsday
                        && effect.timeBetweenUpdates == ((IExtendedDoomsday) effect.dday).getWaitTime()) {
                        if (!effect.player.capabilities.isCreativeMode && !effect.isCommandActivated
                            && effect.sneakTicks == 0) effect.dday.applyDoomCost(effect.doom);
                    }

                    if (!effect.isActive) {
                        if (!effect.player.capabilities.isCreativeMode && !effect.isCommandActivated
                            && !effect.player.isPotionActive(TragicPotion.Convergence)) {
                            if (effect.dday.doomsdayType == EnumDoomType.COMBINATION) {
                                effect.dday.applyCooldown(effect.doom, effect.iterations, effect.inheritedCooldown);
                            } else {
                                effect.dday.applyCooldown(effect.doom, effect.iterations);
                            }
                        }
                        list.remove(effect);
                    }
                }

                if (effect.player.getHealth() <= 0F) reason = "Player is dead.";
                if (list.isEmpty() || effect.player.getHealth() <= 0F)
                    clearPlayerFromRegistry(effect.player.getUniqueID(), reason);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerDisconnect(ServerDisconnectionFromClientEvent event) {
        if (event.handler instanceof NetHandlerPlayServer && !playerMap.isEmpty()) {
            NetHandlerPlayServer net = (NetHandlerPlayServer) event.handler;
            if (net.playerEntity == null) return;

            if (playerMap.containsKey(net.playerEntity.getUniqueID())) {
                clearPlayerFromRegistry(net.playerEntity.getUniqueID(), "Disconnected from server.");
            }
        }
    }

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
        if (event.entityLiving instanceof EntityPlayer && playerMap.containsKey(event.entityLiving.getUniqueID())) {
            clearPlayerFromRegistry(event.entityLiving.getUniqueID(), "Player died during use.");
        }
    }
}
