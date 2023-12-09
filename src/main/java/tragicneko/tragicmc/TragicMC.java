/*
 * TragicMC 2 - Minecraft Mod that uses Minecraft Forge API
 * Copyright (C) 2014 TragicNeko
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package tragicneko.tragicmc;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent.MissingMapping;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.Type;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import tragicneko.tragicmc.client.CommonProxy;
import tragicneko.tragicmc.doomsday.DoomsdayManager;
import tragicneko.tragicmc.events.AchievementEvents;
import tragicneko.tragicmc.events.AmuletEvents;
import tragicneko.tragicmc.events.ChallengeItemEvents;
import tragicneko.tragicmc.events.DoomEvents;
import tragicneko.tragicmc.events.DropEvents;
import tragicneko.tragicmc.events.DynamicHealthScaling;
import tragicneko.tragicmc.events.EnchantmentEvents;
import tragicneko.tragicmc.events.MiscEvents;
import tragicneko.tragicmc.events.PotionEvents;
import tragicneko.tragicmc.events.RespawnDoomEvents;
import tragicneko.tragicmc.events.ServerTickEvents;
import tragicneko.tragicmc.events.VanillaChangingEvents;
import tragicneko.tragicmc.network.MessageAmulet;
import tragicneko.tragicmc.network.MessageAttack;
import tragicneko.tragicmc.network.MessageDoom;
import tragicneko.tragicmc.network.MessageFlight;
import tragicneko.tragicmc.network.MessageFrozenInput;
import tragicneko.tragicmc.network.MessageGui;
import tragicneko.tragicmc.network.MessageHandlerAmulet;
import tragicneko.tragicmc.network.MessageHandlerAttack;
import tragicneko.tragicmc.network.MessageHandlerDoom;
import tragicneko.tragicmc.network.MessageHandlerFlight;
import tragicneko.tragicmc.network.MessageHandlerFrozenInput;
import tragicneko.tragicmc.network.MessageHandlerGui;
import tragicneko.tragicmc.network.MessageHandlerPlaySound;
import tragicneko.tragicmc.network.MessageHandlerSpawnParticle;
import tragicneko.tragicmc.network.MessageHandlerUseDoomsday;
import tragicneko.tragicmc.network.MessageParticle;
import tragicneko.tragicmc.network.MessageSound;
import tragicneko.tragicmc.network.MessageUseDoomsday;
import tragicneko.tragicmc.worldgen.FlowerWorldGen;

@Mod(
    modid = TragicMC.MODID,
    name = TragicMC.MODNAME,
    version = TragicMC.VERSION,
    acceptedMinecraftVersions = TragicMC.ACCEPTED_VERSION,
    dependencies = "required-after:Forge")
public class TragicMC {

    public static final String MODNAME = "TragicMC 2";
    public static final String MODID = "TragicMC";
    public static final String VERSION = "2.46.2879";
    public static final String ACCEPTED_VERSION = "[1.7.10]";

    @Instance(TragicMC.MODID)
    private static TragicMC instance;

    @SidedProxy(
        clientSide = "tragicneko.tragicmc.client.ClientProxy",
        serverSide = "tragicneko.tragicmc.client.CommonProxy")
    public static CommonProxy proxy;

    public static SimpleNetworkWrapper net;
    private static final Logger logger = LogManager.getLogger(TragicMC.MODID);

    public static final Random rand = new Random();
    private static Configuration config;

    public static CreativeTabs Survival;
    public static CreativeTabs Creative;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        doPotionReflection();

        config = null;
        config = new Configuration(event.getSuggestedConfigurationFile(), TragicMC.VERSION, true);
        TragicConfig.load();

        if (TragicConfig.allowIre) registerFMLEvent(new ServerTickEvents());

        if (TragicConfig.allowPotions) {
            if (Potion.potionTypes.length >= 128) {
                TragicPotion.load();
                registerEvent(new PotionEvents());
            } else {
                logError(
                    "The potionType array was not set to an adequate amount, as a result potion effects are disabled now to prevent any crashes.");
                TragicConfig.disablePotions();
            }
        }

        if (TragicConfig.allowEnchantments) TragicEnchantments.load();
        if (TragicConfig.allowEnchantments) registerEvent(new EnchantmentEvents());

        if (TragicConfig.allowSurvivalTab) {
            Survival = (new CreativeTabs("tragicMCSurvival") {

                @Override
                public Item getTabIconItem() {
                    return TragicItems.AwakeningStone;
                }
            });
        }

        Creative = (new CreativeTabs("tragicMCCreative") {

            @Override
            public Item getTabIconItem() {
                return TragicItems.NekoNekoWand;
            }
        });

        TragicBlocks.load();
        TragicItems.load();
        if (TragicConfig.allowRandomWeaponLore && TragicConfig.allowNonMobItems)
            tragicneko.tragicmc.util.LoreHelper.registerLoreJson(event.getModConfigurationDirectory());
        if (TragicConfig.allowPotions) TragicPotion.setPotionIcons();
        if (TragicConfig.allowRecipes) TragicRecipes.load();
        if (TragicConfig.allowAmulets) registerEvent(new AmuletEvents());

        registerEvent(new MiscEvents());
        if (TragicConfig.allowChallengeScrolls && TragicConfig.allowNonMobItems)
            registerEvent(new ChallengeItemEvents());

        if (TragicConfig.allowDoom) {
            registerEvent(new DoomEvents());
            registerFMLEvent(new RespawnDoomEvents());
        }

        if (TragicConfig.allowDimension) {
            if (TragicConfig.allowCollision) {
                if (DimensionManager.isDimensionRegistered(TragicConfig.collisionID)) {
                    int id = DimensionManager.getNextFreeDimId();
                    TragicConfig.collisionID = id;
                    TragicConfig.collisionProviderID = id;
                }

                DimensionManager.registerProviderType(
                    TragicConfig.collisionProviderID,
                    tragicneko.tragicmc.dimension.TragicWorldProvider.class,
                    TragicConfig.keepCollisionLoaded);
                DimensionManager.registerDimension(TragicConfig.collisionID, TragicConfig.collisionProviderID);
                logInfo("Dimension (The Collision) was registered with an ID of " + TragicConfig.collisionID);
            }

            if (TragicConfig.allowSynapse) {
                if (DimensionManager.isDimensionRegistered(TragicConfig.synapseID)) {
                    int id = DimensionManager.getNextFreeDimId();
                    TragicConfig.synapseID = id;
                    TragicConfig.synapseProviderID = id;
                }

                DimensionManager.registerProviderType(
                    TragicConfig.synapseProviderID,
                    tragicneko.tragicmc.dimension.SynapseWorldProvider.class,
                    TragicConfig.keepSynapseLoaded);
                DimensionManager.registerDimension(TragicConfig.synapseID, TragicConfig.synapseProviderID);
                logInfo("Dimension (Synapse) was registered with an ID of " + TragicConfig.synapseID);
            }

            TragicBiome.load();
            MinecraftForge.ORE_GEN_BUS.register(new tragicneko.tragicmc.events.MiscEvents());
        }

        TragicEntities.load();

        if (TragicConfig.allowMobs) {
            tragicneko.tragicmc.util.EntityDropHelper.fill();
            registerEvent(new DynamicHealthScaling());
        }

        if (TragicConfig.allowChallengeScrolls && TragicConfig.allowNonMobItems) TragicItems.initializeChallengeItem();
        if (TragicConfig.allowNonMobItems && TragicConfig.allowNonMobBlocks) registerEvent(new DropEvents());

        NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
        if (TragicConfig.allowDoomsdays) registerFMLEvent(new DoomsdayManager());
        DoomsdayManager.clearRegistry();

        if (TragicConfig.allowVanillaChanges) registerEvent(new VanillaChangingEvents());
        if (TragicConfig.allowOverworldOreGen)
            GameRegistry.registerWorldGenerator(new tragicneko.tragicmc.worldgen.OverworldOreWorldGen(), 1);
        if (TragicConfig.allowNetherOreGen)
            GameRegistry.registerWorldGenerator(new tragicneko.tragicmc.worldgen.NetherOreWorldGen(), 2);
        if (TragicConfig.allowFlowerGen) GameRegistry.registerWorldGenerator(new FlowerWorldGen(), 3);

        if (TragicConfig.allowDimension && TragicConfig.allowFlowerGen) {
            FlowerWorldGen.allowedBiomes.add(TragicBiome.PaintedClearing);
            FlowerWorldGen.allowedBiomes.add(TragicBiome.PaintedForest);
            FlowerWorldGen.allowedBiomes.add(TragicBiome.PaintedHills);
            FlowerWorldGen.allowedBiomes.add(TragicBiome.PaintedPlains);
            FlowerWorldGen.allowedBiomes.add(TragicBiome.AshenBadlands);
            FlowerWorldGen.allowedBiomes.add(TragicBiome.AshenHills);
            FlowerWorldGen.allowedBiomes.add(TragicBiome.AshenMountains);
            FlowerWorldGen.allowedBiomes.add(TragicBiome.DarkForest);
            FlowerWorldGen.allowedBiomes.add(TragicBiome.DarkForestHills);
            FlowerWorldGen.allowedBiomes.add(TragicBiome.DarkMarsh);
            FlowerWorldGen.allowedBiomes.add(TragicBiome.HallowedHills);
            FlowerWorldGen.allowedBiomes.add(TragicBiome.HallowedForest);
            FlowerWorldGen.allowedBiomes.add(TragicBiome.HallowedCliffs);
            FlowerWorldGen.allowedBiomes.add(TragicBiome.HallowedPrarie);
        }

        if (TragicConfig.allowStructureGen)
            GameRegistry.registerWorldGenerator(new tragicneko.tragicmc.worldgen.StructureWorldGen(), 10);

        if (TragicConfig.allowNetwork) {
            net = new SimpleNetworkWrapper(TragicMC.MODID);
            net.registerMessage(MessageHandlerDoom.class, MessageDoom.class, 0, Side.CLIENT);
            net.registerMessage(MessageHandlerAmulet.class, MessageAmulet.class, 1, Side.CLIENT);
            net.registerMessage(MessageHandlerGui.class, MessageGui.class, 2, Side.SERVER);
            net.registerMessage(MessageHandlerUseDoomsday.class, MessageUseDoomsday.class, 3, Side.SERVER);
            net.registerMessage(MessageHandlerFlight.class, MessageFlight.class, 4, Side.CLIENT);
            net.registerMessage(MessageHandlerAttack.class, MessageAttack.class, 5, Side.SERVER);
            net.registerMessage(MessageHandlerSpawnParticle.class, MessageParticle.class, 6, Side.CLIENT);
            net.registerMessage(MessageHandlerPlaySound.class, MessageSound.class, 7, Side.CLIENT);
            net.registerMessage(MessageHandlerFrozenInput.class, MessageFrozenInput.class, 8, Side.SERVER);
        }

        if (TragicConfig.allowAchievements && TragicConfig.allowNonMobItems
            && TragicConfig.allowNonMobBlocks
            && TragicConfig.allowChallengeScrolls
            && TragicConfig.allowAmulets
            && TragicConfig.allowDoom
            && TragicConfig.allowDoomsdays
            && TragicConfig.allowDimension) // register achievements after everything else is processed
        {
            TragicAchievements.load();
            registerEvent(new AchievementEvents());
        } else if (TragicConfig.allowAchievements) {
            logWarning(
                "Achievements are enabled in config but are disabled due to certain blocks and items being disabled. This is to prevent game crashes from ocurring.");
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.registerRenders();
    }

    @EventHandler
    public void onServerLoad(FMLServerStartingEvent event) {
        if (TragicConfig.allowDoom) event.registerServerCommand(new tragicneko.tragicmc.commands.DoomCommand());
        if (TragicConfig.allowDoomsdays) event.registerServerCommand(new tragicneko.tragicmc.commands.DoomsdayComand());

        if (!event.getServer()
            .isFlightAllowed()) {
            TragicConfig.allowFlight = false;
            logWarning(
                "Flight potion effect is disabled due to the server not allowing it. Change the option in your server.properties file if you want it enabled.");
        }
    }

    @EventHandler
    public void onMapping(FMLMissingMappingsEvent event) {
        if (!TragicConfig.allowNonMobBlocks || !TragicConfig.allowNonMobItems) return;

        List<MissingMapping> list = event.get();

        for (MissingMapping mm : list) {
            if (mm.name.equals("TragicMC:darkStoneBlocks")) {
                if (mm.type == Type.BLOCK) {
                    mm.remap(TragicBlocks.DarkStone);
                } else {
                    mm.remap(Item.getItemFromBlock(TragicBlocks.DarkStone));
                }
            } else if (mm.name.equals("TragicMC:obsidianVariants")) {
                if (mm.type == Type.BLOCK) {
                    mm.remap(TragicBlocks.TragicObsidian);
                } else {
                    mm.remap(Item.getItemFromBlock(TragicBlocks.TragicObsidian));
                }
            } else if (mm.name.equals("TragicMC:darkCobbleBlocks")) {
                if (mm.type == Type.BLOCK) {
                    mm.remap(TragicBlocks.DarkCobblestone);
                } else {
                    mm.remap(Item.getItemFromBlock(TragicBlocks.DarkCobblestone));
                }
            } else if (mm.name.equals("TragicMC:rubyOreBlock")) {
                if (mm.type == Type.BLOCK) {
                    mm.remap(TragicBlocks.RubyOre);
                } else {
                    mm.remap(Item.getItemFromBlock(TragicBlocks.RubyOre));
                }
            } else if (mm.name.equals("TragicMC:sapphireOreBlock")) {
                if (mm.type == Type.BLOCK) {
                    mm.remap(TragicBlocks.SapphireOre);
                } else {
                    mm.remap(Item.getItemFromBlock(TragicBlocks.SapphireOre));
                }
            } else if (mm.name.equals("TragicMC:mercuryOreBlock")) {
                if (mm.type == Type.BLOCK) {
                    mm.remap(TragicBlocks.MercuryOre);
                } else {
                    mm.remap(Item.getItemFromBlock(TragicBlocks.MercuryOre));
                }
            } else if (mm.name.equals("TragicMC:tungstenOreBlock")) {
                if (mm.type == Type.BLOCK) {
                    mm.remap(TragicBlocks.TungstenOre);
                } else {
                    mm.remap(Item.getItemFromBlock(TragicBlocks.TungstenOre));
                }
            } else if (mm.name.equals("TragicMC:summonBlocks")) {
                if (mm.type == Type.BLOCK) {
                    mm.remap(TragicBlocks.SummonBlock);
                } else {
                    mm.remap(Item.getItemFromBlock(TragicBlocks.SummonBlock));
                }
            } else if (mm.name.equals("TragicMC:quicksandBlocks")) {
                if (mm.type == Type.BLOCK) {
                    mm.remap(TragicBlocks.Quicksand);
                } else {
                    mm.remap(Item.getItemFromBlock(TragicBlocks.Quicksand));
                }
            } else if (mm.name.equals("TragicMC:foxBlock")) {
                if (mm.type == Type.BLOCK) {
                    mm.remap(TragicBlocks.SmoothNetherrack);
                } else {
                    mm.remap(Item.getItemFromBlock(TragicBlocks.SmoothNetherrack));
                }
            } else if (mm.name.equals("TragicMC:storageBlocks")) {
                if (mm.type == Type.BLOCK) {
                    mm.remap(TragicBlocks.CompactOre);
                } else {
                    mm.remap(Item.getItemFromBlock(TragicBlocks.CompactOre));
                }
            } else if (mm.name.equals("TragicMC:structureSeeds")) {
                if (mm.type == Type.BLOCK) {
                    mm.remap(TragicBlocks.StructureSeed);
                } else {
                    mm.remap(Item.getItemFromBlock(TragicBlocks.StructureSeed));
                }
            } else if (mm.name.equals("TragicMC:lightCobble")) {
                if (mm.type == Type.BLOCK) {
                    mm.remap(TragicBlocks.LightCobblestone);
                } else {
                    mm.remap(Item.getItemFromBlock(TragicBlocks.LightCobblestone));
                }
            } else if (mm.name.contains("TragicMC:")) {
                mm.ignore();
            }
        }
    }

    public static void doPotionReflection() {
        try {
            Potion[] potionTypes;
            Field f = ReflectionHelper.findField(Potion.class, "potionTypes", "field_76425_a");

            Field modfield = Field.class.getDeclaredField("modifiers");
            modfield.setAccessible(true);
            modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);
            potionTypes = (Potion[]) f.get(null);

            if (potionTypes.length <= 128) {
                final Potion[] newPotionTypes = new Potion[256];
                System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
                f.set(null, newPotionTypes);
            } else {
                logWarning(
                    "potionTypes[]'s array length was " + Potion.potionTypes.length
                        + ", so it is assumed that it was previously reflected to an adequate amount.");
            }
        } catch (Exception e) {
            logError(
                "There was an error during Potion array reflection, this may be due to obfuscation and could have unintended side effects.",
                e);
        }
    }

    public static void logError(String s) {
        logger.error(s);
    }

    public static void logError(String s, Exception e) {
        logger.error(s, e);
    }

    public static void logError(String s, Throwable t) {
        logger.error(s, t);
    }

    public static void logInfo(String s) {
        logger.info(s);
    }

    public static void logWarning(String s) {
        logger.warn(s);
    }

    public static Configuration getConfig() {
        return config;
    }

    public static TragicMC getInstance() {
        return instance;
    }

    private static void registerEvent(Object o) {
        MinecraftForge.EVENT_BUS.register(o);
    }

    private static void registerFMLEvent(Object o) {
        FMLCommonHandler.instance()
            .bus()
            .register(o);
    }
}
