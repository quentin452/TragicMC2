package tragicneko.tragicmc.events;

import java.util.List;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.EntityLock;
import tragicneko.tragicmc.entity.mob.EntityLockbot;
import tragicneko.tragicmc.entity.mob.EntityPumpkinhead;
import tragicneko.tragicmc.entity.mob.TragicMob;
import tragicneko.tragicmc.items.ItemTalisman;
import tragicneko.tragicmc.items.amulet.ItemAmulet;
import tragicneko.tragicmc.items.armor.ArmorOverlord;
import tragicneko.tragicmc.items.armor.TragicArmor;
import tragicneko.tragicmc.items.weapons.ItemJack;
import tragicneko.tragicmc.items.weapons.TragicBow;
import tragicneko.tragicmc.items.weapons.TragicTool;
import tragicneko.tragicmc.items.weapons.TragicWeapon;
import tragicneko.tragicmc.properties.PropertyAmulets;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.LoreHelper;

public class AchievementEvents {

    @SubscribeEvent
    public void onUpdate(LivingUpdateEvent event) {
        if (event.entityLiving instanceof EntityPlayerMP) {
            EntityPlayerMP mp = (EntityPlayerMP) event.entityLiving;
            mp.triggerAchievement(TragicAchievements.thanks);

            InventoryPlayer inv = mp.inventory;

            if (inv.hasItem(TragicItems.CryingObsidianOrb) || inv.hasItem(TragicItems.DyingObsidianOrb)
                || inv.hasItem(TragicItems.BleedingObsidianOrb)) {
                mp.triggerAchievement(TragicAchievements.obsidianOrb);
            }

            if (inv.hasItem(TragicItems.DimensionalKey)) {
                mp.triggerAchievement(TragicAchievements.dimensionalKey);
            }

            if (inv.hasItem(TragicItems.SynapseLink)) {
                mp.triggerAchievement(TragicAchievements.synapseLink);
            }

            if (inv.hasItem(TragicItems.Talisman)) {
                mp.triggerAchievement(TragicAchievements.talisman);
            }

            if (inv.hasItem(TragicItems.MobStatue)) {
                mp.triggerAchievement(TragicAchievements.mobStatue);
            }

            if (inv.hasItem(TragicItems.RainDanceTalisman) && inv.hasItem(TragicItems.SynthesisTalisman)
                && inv.hasItem(TragicItems.MoonlightTalisman)
                && inv.hasItem(TragicItems.ThunderstormTalisman)
                && inv.hasItem(TragicItems.LightningRodTalisman)
                && inv.hasItem(TragicItems.HydrationTalisman)
                && inv.hasItem(TragicItems.TimeManipulatorTalisman)
                && inv.hasItem(TragicItems.SunnyDayTalisman)) {
                mp.triggerAchievement(TragicAchievements.talismanFull);
            }

            for (ItemStack stack : inv.mainInventory) {
                if (stack != null && stack.getItem() instanceof TragicWeapon) {
                    mp.triggerAchievement(TragicAchievements.weapon);

                    if (TragicConfig.allowRandomWeaponLore && LoreHelper.getRarityFromStack(stack) > 0) {
                        mp.triggerAchievement(TragicAchievements.loot);
                        if (LoreHelper.getRarityFromStack(stack) >= 3) mp.triggerAchievement(TragicAchievements.loot2);
                    }
                } else if (stack != null && stack.getItem() instanceof ItemAmulet) {
                    mp.triggerAchievement(TragicAchievements.amulet);
                } else if (stack != null && stack.getItem() instanceof ItemJack) {
                    mp.triggerAchievement(TragicAchievements.jack);
                } else if (stack != null && stack.getItem() instanceof ItemTalisman) {
                    mp.triggerAchievement(TragicAchievements.talisman);
                } else if (stack != null
                    && (stack.getItem() instanceof TragicTool || stack.getItem() instanceof TragicBow)) {
                        if (TragicConfig.allowRandomWeaponLore && LoreHelper.getRarityFromStack(stack) > 0) {
                            mp.triggerAchievement(TragicAchievements.loot);
                            if (LoreHelper.getRarityFromStack(stack) >= 3)
                                mp.triggerAchievement(TragicAchievements.loot2);
                        }
                    }
            }

            boolean flag = true;
            boolean ovFlag = true;

            for (ItemStack stack : inv.armorInventory) {
                if (stack == null || !(stack.getItem() instanceof TragicArmor)) flag = false;
                if (stack == null || !(stack.getItem() instanceof ArmorOverlord)) ovFlag = false;

                if (stack != null && stack.getItem() instanceof TragicArmor) {
                    if (TragicConfig.allowRandomWeaponLore && LoreHelper.getRarityFromStack(stack) > 0) {
                        mp.triggerAchievement(TragicAchievements.loot);
                        if (LoreHelper.getRarityFromStack(stack) >= 3) mp.triggerAchievement(TragicAchievements.loot2);
                    }
                }
            }

            if (flag) mp.triggerAchievement(TragicAchievements.fullSuit);
            if (ovFlag) mp.triggerAchievement(TragicAchievements.haxEngage);

            if (TragicConfig.allowAmulets) {
                PropertyAmulets amu = PropertyAmulets.get(mp);
                if (amu != null) {
                    boolean flag2 = true;

                    for (ItemStack stack : amu.inventory.inventory) {
                        if (stack != null && stack.getItem() instanceof ItemAmulet) {
                            mp.triggerAchievement(TragicAchievements.amulet);
                        }

                        if (stack == null) flag2 = false;
                    }

                    if (flag2) mp.triggerAchievement(TragicAchievements.amuletMax);

                    for (byte b = 0; b < 3; b++) {
                        if (amu.getActiveAmulet(b) != null) {
                            ItemAmulet item = amu.getActiveAmulet(b);
                            mp.triggerAchievement(TragicAchievements.amuletEquip);

                            if (ItemAmulet.cursedAmulets.contains(item) || ItemAmulet.epicAmulets.contains(item)) {
                                mp.triggerAchievement(TragicAchievements.amuletSpecial);
                            }
                        }
                    }
                }
            }

            if (TragicConfig.allowDoom) {
                PropertyDoom doom = PropertyDoom.get(mp);
                if (doom != null) {
                    if (doom.getCurrentDoom() == doom.getMaxDoom()) mp.triggerAchievement(TragicAchievements.doom);
                }
            }

            if (mp.worldObj != null) {
                if (mp.worldObj.provider.dimensionId == TragicConfig.collisionID)
                    mp.triggerAchievement(TragicAchievements.collision);
                if (mp.worldObj.provider.dimensionId == TragicConfig.synapseID)
                    mp.triggerAchievement(TragicAchievements.synapse);
            }

            if (TragicConfig.allowFlight && mp.isPotionActive(TragicPotion.Flight))
                mp.triggerAchievement(TragicAchievements.flight);
            if (TragicConfig.allowDisorientation && mp.isPotionActive(TragicPotion.Disorientation))
                mp.triggerAchievement(TragicAchievements.disorientation);
        }
    }

    @SubscribeEvent
    public void onBlockBreak(BreakEvent event) {
        if (event.getPlayer() instanceof EntityPlayerMP) {
            EntityPlayerMP mp = (EntityPlayerMP) event.getPlayer();

            if (event.block == TragicBlocks.MercuryOre) {
                mp.triggerAchievement(TragicAchievements.mercury);
            }

            if (event.block == TragicBlocks.Aeris && event.blockMetadata >= 2) {
                mp.triggerAchievement(TragicAchievements.aeris);
            }

            if (event.block == TragicBlocks.TungstenOre) {
                mp.triggerAchievement(TragicAchievements.tungsten);
            }

            if (event.block == TragicBlocks.TragicOres && event.blockMetadata == 10) {
                mp.triggerAchievement(TragicAchievements.mineXP);
            }

            if (mp.getCurrentEquippedItem() != null) {
                ItemStack stack = mp.getCurrentEquippedItem();
                if (TragicConfig.allowLuminescence
                    && EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Luminescence.effectId, stack) > 0
                    && !mp.worldObj.canBlockSeeTheSky((int) mp.posX, (int) mp.posY, (int) mp.posZ)) {
                    mp.triggerAchievement(TragicAchievements.luminescence);
                }

                if (TragicConfig.allowVeteran
                    && EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Veteran.effectId, stack) > 0) {
                    mp.triggerAchievement(TragicAchievements.veteran);
                }

                if (TragicConfig.allowCombustion
                    && EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Combustion.effectId, stack) > 0) {
                    mp.triggerAchievement(TragicAchievements.combustion);
                }
            }
        }
    }

    @SubscribeEvent
    public void onAttack(LivingHurtEvent event) {
        if (event.source.getEntity() instanceof EntityPlayerMP) {
            EntityPlayerMP mp = (EntityPlayerMP) event.source.getEntity();
            if (TragicConfig.allowReach
                && EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Reach.effectId, mp.getCurrentEquippedItem())
                    > 0
                && mp.getDistanceToEntity(event.entityLiving) > 4.0F) {
                mp.triggerAchievement(TragicAchievements.reach);
            }

            if (TragicConfig.allowAbsolve
                && EnchantmentHelper
                    .getEnchantmentLevel(TragicEnchantments.Absolve.effectId, mp.getCurrentEquippedItem()) > 0
                && TragicConfig.allowCorruption
                && event.entityLiving.isPotionActive(TragicPotion.Corruption)) {
                mp.triggerAchievement(TragicAchievements.enchant);
            }

            if (TragicConfig.allowSlay
                && EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Slay.effectId, mp.getCurrentEquippedItem())
                    > 0
                && event.entityLiving.getCreatureAttribute() == TragicEntities.Beast) {
                mp.triggerAchievement(TragicAchievements.enchant);
            }

            if (TragicConfig.allowDecay
                && EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Decay.effectId, mp.getCurrentEquippedItem())
                    > 0
                && event.entityLiving.getCreatureAttribute() == TragicEntities.Natural) {
                mp.triggerAchievement(TragicAchievements.enchant);
            }
        }
    }

    @SubscribeEvent
    public void onKill(LivingDeathEvent event) {
        if (event.source.getEntity() instanceof EntityPlayerMP) {
            EntityPlayerMP mp = (EntityPlayerMP) event.source.getEntity();

            if (TragicConfig.allowHacked && mp.isPotionActive(TragicPotion.Hacked))
                mp.triggerAchievement(TragicAchievements.systemCrash);
            if (TragicConfig.allowDivinity && mp.isPotionActive(TragicPotion.Divinity)
                && event.entityLiving instanceof TragicMob) mp.triggerAchievement(TragicAchievements.divinity);

            if (TragicConfig.allowAchievements && mp.riddenByEntity instanceof EntityLock
                && !(event.entityLiving instanceof EntityLockbot)) mp.triggerAchievement(TragicAchievements.lockdown);
        }
    }

    @SubscribeEvent
    public void onPlace(BlockEvent.PlaceEvent event) {
        if (event.player instanceof EntityPlayerMP) {
            EntityPlayerMP mp = (EntityPlayerMP) event.player;

            if (TragicConfig.allowPumpkinhead && (event.block == Blocks.pumpkin || event.block == Blocks.lit_pumpkin)) {
                List<EntityPumpkinhead> list = mp.worldObj
                    .getEntitiesWithinAABB(EntityPumpkinhead.class, mp.boundingBox.expand(16.0, 16.0, 16.0));

                if (!list.isEmpty()) {
                    for (EntityPumpkinhead ph : list) {
                        if (!ph.hasHomePumpkin()) mp.triggerAchievement(TragicAchievements.pumpkinhead);
                    }
                }
            }
        }
    }
}
