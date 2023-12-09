package tragicneko.tragicmc.events;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.items.Challenge;
import tragicneko.tragicmc.items.ItemChallenge;
import tragicneko.tragicmc.util.WorldHelper;

public class ChallengeItemEvents {

    @SubscribeEvent
    public void onEntityDeath(LivingDeathEvent event) {
        if (event.entityLiving.worldObj.isRemote) return;

        EntityPlayer player;
        if (event.entityLiving instanceof EntityPlayer) {
            player = (EntityPlayer) event.entityLiving;
            ItemStack[] inv = player.inventory.mainInventory;
            ItemStack stack;
            Challenge challenge = null;

            for (ItemStack element : inv) {
                if (element != null && element.hasTagCompound()
                    && element.getItem() instanceof ItemChallenge
                    && element.getItemDamage() != 0
                    && element.getItemDamage() != 250) {
                    stack = element;
                    if (!stack.hasTagCompound() || !stack.getTagCompound()
                        .hasKey("challengeID")) continue;
                    challenge = Challenge.getChallengeFromID(stack.stackTagCompound.getInteger("challengeID"));
                    if (stack.stackTagCompound.hasKey("challengeProgress") && challenge != null
                        && !challenge.savesProgress) stack.stackTagCompound.setInteger("challengeProgress", 0);
                }
            }
        } else if (event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer) {
            player = (EntityPlayer) event.source.getEntity();
            ItemStack[] inv = player.inventory.mainInventory;
            ItemStack stack;
            Challenge challenge = null;

            for (ItemStack element : inv) {
                if (element != null && element.hasTagCompound()
                    && element.getItem() instanceof ItemChallenge
                    && element.getItemDamage() != 0
                    && element.getItemDamage() != 250) {
                    stack = element;
                    if (!stack.hasTagCompound() || !stack.getTagCompound()
                        .hasKey("challengeID")) continue;
                    challenge = Challenge.getChallengeFromID(stack.stackTagCompound.getInteger("challengeID"));
                    if (stack.stackTagCompound.hasKey("challengeProgress") && challenge != null
                        && !challenge.isItemChallenge
                        && challenge.challengeClass != null) {
                        Class cls = challenge.challengeClass;
                        Class cls2 = event.entityLiving.getClass();
                        boolean flag = cls == cls2;

                        for (Class cl : cls2.getInterfaces()) {
                            if (flag || cl == cls) {
                                flag = true;
                                break;
                            }
                        }

                        while (!flag) {
                            if (cls == cls2) {
                                flag = true;
                                break;
                            }

                            if (cls2.getSuperclass() == null) break;
                            cls2 = cls2.getSuperclass();
                        }

                        if (flag) {
                            int pow = stack.stackTagCompound.getInteger("challengeProgress");
                            stack.stackTagCompound.setInteger("challengeProgress", ++pow);
                        }
                    }
                }
            }

            if (TragicMC.rand.nextInt(1000) <= TragicConfig.challengeScrollDropChance
                && event.entityLiving instanceof EntityMob) {
                event.entityLiving.entityDropItem(new ItemStack(TragicItems.ChallengeScroll, 1, 0), 0.4F);
            }
        }
    }

    @SubscribeEvent
    public void onEntityUpdate(LivingUpdateEvent event) {
        if (event.entityLiving.worldObj.isRemote || !(event.entityLiving instanceof EntityPlayer)
            || event.entityLiving.ticksExisted % 10 != 0) return;
        EntityPlayer player = (EntityPlayer) event.entityLiving;
        ItemStack[] inv = player.inventory.mainInventory;
        ItemStack stack;
        Challenge challenge = null;

        for (ItemStack element : inv) {
            if (element != null && element.hasTagCompound()
                && element.getItem() instanceof ItemChallenge
                && element.getItemDamage() != 0
                && element.getItemDamage() != 250) {
                stack = element;
                if (!stack.hasTagCompound() || !stack.getTagCompound()
                    .hasKey("challengeID")) continue;
                challenge = Challenge.getChallengeFromID(stack.stackTagCompound.getInteger("challengeID"));

                if (stack.stackTagCompound.hasKey("challengeProgress") && challenge != null) {
                    if (challenge.isMobRush) {
                        List<EntityMob> list = player.worldObj
                            .getEntitiesWithinAABB(EntityMob.class, player.boundingBox.expand(100.0, 100.0, 100.0));

                        for (int j = 0; j < list.size(); j++) {
                            list.get(j)
                                .setAttackTarget(player);
                        }

                        player.worldObj
                            .getChunkFromBlockCoords((int) player.posX, (int) player.posZ).inhabitedTime += 10000L;
                    } else if (challenge.isTargetChallenge && challenge.challengeClass != null) {
                        List<Entity> list = player.worldObj
                            .getEntitiesWithinAABB(challenge.challengeClass, player.boundingBox.expand(6.0, 6.0, 6.0));

                        for (int j = 0; j < list.size(); j++) {
                            Class cls = challenge.challengeClass;
                            Class cls2 = list.get(j)
                                .getClass();
                            boolean flag = cls == cls2;

                            for (Class cl : cls2.getInterfaces()) {
                                if (cl == cls) {
                                    flag = true;
                                    break;
                                }
                            }

                            while (!flag) {
                                if (cls == cls2) {
                                    flag = true;
                                    break;
                                }

                                if (cls2.getSuperclass() == null) break;
                                cls2 = cls2.getSuperclass();
                            }

                            if (flag) {
                                stack.stackTagCompound.setInteger("challengeProgress", 1);
                                break;
                            }
                        }
                    } else if (challenge.isBlockChallenge) {
                        ArrayList<int[]> list = WorldHelper.getBlocksInCircularRange(
                            player.worldObj,
                            1.5,
                            player.posX,
                            player.boundingBox.minY - 1.5,
                            player.posZ);
                        Block block;
                        for (int[] coords : list) {
                            block = player.worldObj.getBlock(coords[0], coords[1], coords[2]);
                            if (block == challenge.challengeBlock) {
                                stack.stackTagCompound.setInteger("challengeProgress", 1);
                                break;
                            }
                        }
                    } else if (challenge.isLoreChallenge) {
                        ItemStack loreStack;
                        int amt = 0;

                        for (ItemStack element2 : inv) {
                            if (element2 != null && element2.hasTagCompound()
                                && element2.stackTagCompound.hasKey("tragicLoreRarity")) {
                                loreStack = element2;
                                if (loreStack.stackTagCompound.getInteger("tragicLoreRarity") <= challenge.loreRarity)
                                    amt++;
                            }
                        }
                        stack.stackTagCompound.setInteger("challengeProgress", amt);
                    } else if (challenge.isArmorChallenge) {
                        int amt = 0;
                        ItemStack[] armorInv = player.inventory.armorInventory;
                        ItemStack[] challengeArmor = challenge.challengeArmor;

                        for (ItemStack element2 : armorInv) {
                            if (element2 != null) {
                                for (ItemStack element3 : challengeArmor) {
                                    if (element3 != null && element2.getItem() == element3.getItem()) amt++;
                                }
                            }
                        }
                        stack.stackTagCompound.setInteger("challengeProgress", amt);
                    }

                    if (challenge.isLocationBased) {
                        boolean flag = false;
                        if (challenge.challengeBiome != null) {
                            flag = player.worldObj.getBiomeGenForCoords((int) player.posX, (int) player.posZ)
                                == challenge.challengeBiome;
                        } else {
                            double x = Math.abs(player.posX);
                            double z = Math.abs(player.posZ);
                            flag = MathHelper.sqrt_double(x * x + z * z) >= challenge.challengeRange;
                        }
                        stack.stackTagCompound.setBoolean("challengeLocation", flag);
                    }
                }
            }
        }
    }
}
