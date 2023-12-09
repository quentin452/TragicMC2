package tragicneko.tragicmc.events;

import static tragicneko.tragicmc.TragicMC.rand;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;

import com.google.common.collect.Sets;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCore;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class EnchantmentEvents {

    private Set replaceableBlocks = Sets.newHashSet(new Block[] { Blocks.air, TragicBlocks.Luminescence });

    @SubscribeEvent
    public void onLuminescence(LivingUpdateEvent event) {
        if (!event.entityLiving.worldObj.isRemote && TragicConfig.allowLuminescence) {
            boolean flag = false;

            for (int i = 0; i < 5 && !flag; i++) {
                ItemStack stack = event.entityLiving.getEquipmentInSlot(i);
                if (stack != null && stack.getItem() != Items.enchanted_book
                    && EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Luminescence.effectId, stack) > 0)
                    flag = true;
            }
            if (!flag) return;

            int x = (int) (event.entityLiving.posX + rand.nextInt(2) - rand.nextInt(2));
            int y = (int) (event.entityLiving.posY + rand.nextInt(2) - rand.nextInt(2));
            int z = (int) (event.entityLiving.posZ + rand.nextInt(2) - rand.nextInt(2));
            if (EntityOverlordCore.replaceableBlocks.contains(event.entityLiving.worldObj.getBlock(x, y, z))) {
                event.entityLiving.worldObj.setBlock(x, y, z, TragicBlocks.Luminescence);
            }
        }
    }

    @SubscribeEvent
    public void onCombustion(HarvestDropsEvent event) {
        if (event.harvester != null && !event.isSilkTouching) {
            if (event.harvester.getEquipmentInSlot(0) != null) {
                ItemStack tool = event.harvester.getEquipmentInSlot(0);

                if (tool.getItem() instanceof ItemTool && TragicConfig.allowCombustion
                    && EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Combustion.effectId, tool) > 0) {
                    if (tool.getItem()
                        .func_150893_a(tool, event.block) > 1.0F) {
                        int z = EnchantmentHelper.getFortuneModifier(event.harvester);
                        ItemStack result = FurnaceRecipes.smelting()
                            .getSmeltingResult(new ItemStack(event.block, 1, event.blockMetadata));

                        if (result != null) {
                            result.stackSize = z > 0 ? rand.nextInt(z) + 1 : 1;
                            tool.attemptDamageItem(1, rand);
                            event.drops.clear();
                            event.drops.add(result.copy());
                            event.world.playSoundAtEntity(event.harvester, "random.fire.hiss", 0.4F, 1.0F);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onBreak(BreakEvent event) {
        if (event.getPlayer() != null && !event.isCanceled()) {
            if (event.getPlayer()
                .getEquipmentInSlot(0) != null) {
                ItemStack tool = event.getPlayer()
                    .getEquipmentInSlot(0);
                int i = TragicConfig.allowVeteran
                    ? EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Veteran.effectId, tool) * 2 + 1
                    : 1;

                if (tool.getItemDamage() >= tool.getMaxDamage() - i && TragicConfig.allowUnbreakable
                    && EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Unbreakable.effectId, tool) > 0) {
                    if (event.isCancelable()) event.setCanceled(true);
                    return;
                }

                if (tool.getItem() instanceof ItemTool && canMineWithTool(event.world, tool, event.x, event.y, event.z)
                    && TragicConfig.allowVeteran
                    && !event.world.isRemote) {
                    int e = EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Veteran.effectId, tool);
                    if (e < 1) return;
                    MovingObjectPosition mop = WorldHelper
                        .getMOPFromEntity(event.getPlayer(), event.getPlayer().capabilities.isCreativeMode ? 4.5 : 3.5);

                    if (mop != null) {
                        if (mop.sideHit == 0 || mop.sideHit == 1) {
                            if (canMineWithTool(event.world, tool, event.x + 1, event.y, event.z))
                                event.world.func_147480_a(event.x + 1, event.y, event.z, true);
                            if (canMineWithTool(event.world, tool, event.x - 1, event.y, event.z))
                                event.world.func_147480_a(event.x - 1, event.y, event.z, true);
                            if (canMineWithTool(event.world, tool, event.x, event.y, event.z - 1))
                                event.world.func_147480_a(event.x, event.y, event.z - 1, true);
                            if (canMineWithTool(event.world, tool, event.x, event.y, event.z + 1))
                                event.world.func_147480_a(event.x, event.y, event.z + 1, true);

                            if (e >= 2) {
                                if (canMineWithTool(event.world, tool, event.x - 1, event.y, event.z - 1))
                                    event.world.func_147480_a(event.x - 1, event.y, event.z - 1, true);
                                if (canMineWithTool(event.world, tool, event.x + 1, event.y, event.z + 1))
                                    event.world.func_147480_a(event.x + 1, event.y, event.z + 1, true);
                                if (canMineWithTool(event.world, tool, event.x + 1, event.y, event.z - 1))
                                    event.world.func_147480_a(event.x + 1, event.y, event.z - 1, true);
                                if (canMineWithTool(event.world, tool, event.x - 1, event.y, event.z + 1))
                                    event.world.func_147480_a(event.x - 1, event.y, event.z + 1, true);

                                if (e >= 3) {
                                    if (canMineWithTool(event.world, tool, event.x + 2, event.y, event.z))
                                        event.world.func_147480_a(event.x + 2, event.y, event.z, true);
                                    if (canMineWithTool(event.world, tool, event.x - 2, event.y, event.z))
                                        event.world.func_147480_a(event.x - 2, event.y, event.z, true);
                                    if (canMineWithTool(event.world, tool, event.x, event.y, event.z - 2))
                                        event.world.func_147480_a(event.x, event.y, event.z - 2, true);
                                    if (canMineWithTool(event.world, tool, event.x, event.y, event.z + 2))
                                        event.world.func_147480_a(event.x, event.y, event.z + 2, true);
                                }
                            }
                        } else {
                            if (canMineWithTool(event.world, tool, event.x, event.y + 1, event.z))
                                event.world.func_147480_a(event.x, event.y + 1, event.z, true);
                            if (canMineWithTool(event.world, tool, event.x, event.y - 1, event.z))
                                event.world.func_147480_a(event.x, event.y - 1, event.z, true);

                            if (e >= 3) {
                                if (canMineWithTool(event.world, tool, event.x, event.y + 2, event.z))
                                    event.world.func_147480_a(event.x, event.y + 2, event.z, true);
                                if (canMineWithTool(event.world, tool, event.x, event.y - 2, event.z))
                                    event.world.func_147480_a(event.x, event.y - 2, event.z, true);
                            }

                            if (mop.sideHit == 2 || mop.sideHit == 3) {
                                if (canMineWithTool(event.world, tool, event.x + 1, event.y, event.z))
                                    event.world.func_147480_a(event.x + 1, event.y, event.z, true);
                                if (canMineWithTool(event.world, tool, event.x - 1, event.y, event.z))
                                    event.world.func_147480_a(event.x - 1, event.y, event.z, true);

                                if (e >= 2) {
                                    if (canMineWithTool(event.world, tool, event.x + 1, event.y + 1, event.z))
                                        event.world.func_147480_a(event.x + 1, event.y + 1, event.z, true);
                                    if (canMineWithTool(event.world, tool, event.x - 1, event.y + 1, event.z))
                                        event.world.func_147480_a(event.x - 1, event.y + 1, event.z, true);
                                    if (canMineWithTool(event.world, tool, event.x + 1, event.y - 1, event.z))
                                        event.world.func_147480_a(event.x + 1, event.y - 1, event.z, true);
                                    if (canMineWithTool(event.world, tool, event.x - 1, event.y - 1, event.z))
                                        event.world.func_147480_a(event.x - 1, event.y - 1, event.z, true);

                                    if (e >= 3) {
                                        if (canMineWithTool(event.world, tool, event.x + 2, event.y, event.z))
                                            event.world.func_147480_a(event.x + 2, event.y, event.z, true);
                                        if (canMineWithTool(event.world, tool, event.x - 2, event.y, event.z))
                                            event.world.func_147480_a(event.x - 2, event.y, event.z, true);
                                    }
                                }
                            } else {
                                if (canMineWithTool(event.world, tool, event.x, event.y, event.z - 1))
                                    event.world.func_147480_a(event.x, event.y, event.z - 1, true);
                                if (canMineWithTool(event.world, tool, event.x, event.y, event.z + 1))
                                    event.world.func_147480_a(event.x, event.y, event.z + 1, true);

                                if (e >= 2) {
                                    if (canMineWithTool(event.world, tool, event.x, event.y + 1, event.z - 1))
                                        event.world.func_147480_a(event.x, event.y + 1, event.z - 1, true);
                                    if (canMineWithTool(event.world, tool, event.x, event.y + 1, event.z + 1))
                                        event.world.func_147480_a(event.x, event.y + 1, event.z + 1, true);
                                    if (canMineWithTool(event.world, tool, event.x, event.y - 1, event.z - 1))
                                        event.world.func_147480_a(event.x, event.y - 1, event.z - 1, true);
                                    if (canMineWithTool(event.world, tool, event.x, event.y - 1, event.z + 1))
                                        event.world.func_147480_a(event.x, event.y - 1, event.z + 1, true);

                                    if (e >= 3) {
                                        if (canMineWithTool(event.world, tool, event.x, event.y, event.z - 2))
                                            event.world.func_147480_a(event.x, event.y, event.z - 2, true);
                                        if (canMineWithTool(event.world, tool, event.x, event.y, event.z + 2))
                                            event.world.func_147480_a(event.x, event.y, event.z + 2, true);
                                    }
                                }
                            }
                        }

                        tool.attemptDamageItem(e + rand.nextInt(e), rand);
                    }
                }
            }
        }
    }

    public static boolean canMineWithTool(World world, ItemStack stack, int x, int y, int z) {
        ItemTool tool = (ItemTool) stack.getItem();
        Block block = world.getBlock(x, y, z);
        if (tool.func_150893_a(stack, block) > 1.0F && block.getBlockHardness(world, x, y, z) > 0) return true;
        return false;
    }

    @SubscribeEvent
    public void multiplyArrow(ArrowLooseEvent event) {
        if (TragicConfig.allowMultiply) {
            EntityPlayer player = event.entityPlayer;
            World world = player.worldObj;

            float f = event.charge / 20.0F;
            f = (f * f + f * 2.0F) / 3.0F;

            if (f < 0.5D) {
                return;
            }

            if (f > 1.0F) {
                f = 1.0F;
            }

            if (EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Multiply.effectId, event.bow) > 0) {
                EntityArrow entityarrow = new EntityArrow(world, player, f * 1.5F);
                entityarrow.motionX *= 0.95;
                entityarrow.motionZ *= 0.95;
                EntityArrow entityarrow2 = new EntityArrow(world, player, f * 1.5F);
                entityarrow2.motionX *= 1.3;
                entityarrow2.motionZ *= 1.3;

                if (f == 1.0F) {
                    entityarrow.setIsCritical(true);
                    entityarrow2.setIsCritical(true);
                }

                int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, event.bow);

                if (k > 0) {
                    entityarrow.setDamage(entityarrow.getDamage() + k * 0.5D);
                    entityarrow2.setDamage(entityarrow.getDamage() + k * 0.5D);
                }

                int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, event.bow);

                if (l > 0) {
                    entityarrow.setKnockbackStrength(0);
                    entityarrow2.setKnockbackStrength(0);
                }

                if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, event.bow) > 0) {
                    entityarrow.setFire(100);
                    entityarrow2.setFire(100);
                }

                entityarrow.canBePickedUp = 2;
                entityarrow2.canBePickedUp = 2;

                if (!world.isRemote) {
                    world.spawnEntityInWorld(entityarrow);
                    world.spawnEntityInWorld(entityarrow2);
                }
            }
        }
    }

    @SubscribeEvent
    public void withAgility(LivingHurtEvent event) {
        if (event.entityLiving instanceof EntityPlayer && TragicConfig.allowAgility && !event.source.isMagicDamage()) {
            EntityPlayer player = (EntityPlayer) event.entityLiving;
            if (!event.source.canHarmInCreative()) {
                for (int x = 1; x < 5; x++) {
                    if (player.getEquipmentInSlot(x) != null) {
                        ItemStack armor = player.getEquipmentInSlot(x);

                        if (EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Agility.effectId, armor) > 0) {
                            int l = EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Agility.effectId, armor);

                            if (rand.nextInt(400) <= 10 * l) {
                                if (event.isCancelable()) {
                                    event.setCanceled(true);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onExtraWeaponEnchantUse(LivingHurtEvent event) {
        if (event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer
            && TragicConfig.allowEnchantments) {
            EntityPlayer player = (EntityPlayer) event.source.getEntity();

            if (player.getCurrentEquippedItem() != null) {
                float f = event.ammount / 10;

                if (TragicConfig.allowVampirism) {
                    int vamp = EnchantmentHelper
                        .getEnchantmentLevel(TragicEnchantments.Vampirism.effectId, player.getCurrentEquippedItem());

                    if (vamp > 0 && rand.nextInt(10 - (vamp * 2)) == 0 && rand.nextInt(4) == 0) {
                        player.heal(f * vamp);

                        if (TragicConfig.allowCripple) {
                            event.entityLiving
                                .addPotionEffect(new PotionEffect(TragicPotion.Cripple.id, 60 * vamp, vamp * 2));
                        }

                        if (rand.nextInt(3) == 0) {
                            player.addExhaustion(f);
                        }
                    }
                }

                if (TragicConfig.allowLeech) {
                    int leech = EnchantmentHelper
                        .getEnchantmentLevel(TragicEnchantments.Leech.effectId, player.getCurrentEquippedItem());

                    if (leech > 0 && rand.nextInt(10 - (leech * 2)) == 0 && rand.nextInt(2) == 0) {
                        int food = player.getFoodStats()
                            .getFoodLevel();

                        if (food + leech <= 20) {
                            player.getFoodStats()
                                .addStats(leech, 1.0F);
                        }

                        if (event.entityLiving instanceof EntityPlayer) {
                            ((EntityPlayer) event.entityLiving).addExhaustion(f);
                        }

                        if (rand.nextInt(3) == 0) {
                            PropertyDoom property = PropertyDoom.get(player);
                            property.increaseDoom(-leech);
                        }
                    }
                }

                if (TragicConfig.allowConsume) {
                    int consume = EnchantmentHelper
                        .getEnchantmentLevel(TragicEnchantments.Consume.effectId, player.getCurrentEquippedItem());

                    if (consume > 0 && rand.nextInt(10 - (consume * 2)) == 0 && rand.nextInt(3) == 0) {
                        PropertyDoom property1 = PropertyDoom.get(player);

                        if (property1 != null && TragicConfig.allowDoom) {
                            property1.increaseDoom(consume);
                            if (event.entityLiving instanceof EntityPlayer) {
                                PropertyDoom property2 = PropertyDoom.get((EntityPlayer) event.entityLiving);

                                property2.increaseDoom(-consume);
                            }

                            if (rand.nextInt(3) == 0) {
                                player.attackEntityFrom(DamageSource.generic, consume);
                            }
                        }
                    }
                }

                if (TragicConfig.allowRuneBreak) {
                    int runeBreak = EnchantmentHelper
                        .getEnchantmentLevel(TragicEnchantments.RuneBreak.effectId, player.getCurrentEquippedItem());

                    if (runeBreak > 0 && event.source.isMagicDamage()) {
                        for (int i = 0; i < runeBreak; i++) {
                            event.ammount *= 1.125F;
                        }
                        if (player instanceof EntityPlayerMP)
                            ((EntityPlayerMP) player).triggerAchievement(TragicAchievements.enchant);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onUnbreakableUse(LivingAttackEvent event) {
        if (event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.source.getEntity();
            if (player.getCurrentEquippedItem() != null) {
                ItemStack stack = player.getCurrentEquippedItem();

                if (stack.getItemDamage() >= stack.getMaxDamage() - 1 && TragicConfig.allowUnbreakable
                    && EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Unbreakable.effectId, stack) > 0) {
                    if (event.isCancelable()) event.setCanceled(true);
                }
            }
        }
    }
}
