package tragicneko.tragicmc.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.properties.PropertyDoom;

import java.util.List;

public class ItemDoomUpgrade extends Item {

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.epic;
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {
        par2List.add("Increases your Max Doom limit");
        par2List.add("If at max limit, then refills Doom partially");
        par2List.add("Does not affect cooldown");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!par2World.isRemote && !TragicConfig.allowDoom) return par1ItemStack;

        PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);
        if (doom == null) return par1ItemStack;

        if (TragicConfig.shouldDoomLimitIncrease
            && doom.getMaxDoom() + TragicConfig.doomConsumeAmount <= TragicConfig.maxDoomAmount) {
            if (!par2World.isRemote) {
                doom.increaseConsumptionLevel();

                if (TragicConfig.allowConsumeRefill) {
                    if (TragicConfig.consumeRefillAmount >= 100) {
                        doom.fillDoom();
                    } else {
                        double refill = doom.getMaxDoom() * TragicConfig.consumeRefillAmount / 100;

                        if (doom.getCurrentDoom() + refill < doom.getMaxDoom()) {
                            doom.increaseDoom((int) refill);
                        } else {
                            doom.fillDoom();
                        }
                    }
                }

                if (TragicConfig.allowAchievements && par3EntityPlayer instanceof EntityPlayerMP)
                    par3EntityPlayer.triggerAchievement(TragicAchievements.doomConsume);
                if (!par3EntityPlayer.capabilities.isCreativeMode) par1ItemStack.stackSize--;
                par3EntityPlayer.addChatMessage(new ChatComponentText("Doom max limit increased!"));
            } else {
                par3EntityPlayer.playSound("tragicmc:random.doomconsume", 1.0F, 1.0F);
            }

        } else if (doom.getCurrentDoom() < doom.getMaxDoom()) {
            if (!par2World.isRemote) {
                if (TragicConfig.allowConsumeRefill) {
                    if (TragicConfig.consumeRefillAmount >= 100) {
                        doom.fillDoom();
                    } else {
                        double refill = doom.getMaxDoom() * TragicConfig.consumeRefillAmount / 100;

                        if (doom.getCurrentDoom() + refill < doom.getMaxDoom()) {
                            doom.increaseDoom((int) refill);
                        } else {
                            doom.fillDoom();
                        }
                    }
                }

                if (!par3EntityPlayer.capabilities.isCreativeMode) par1ItemStack.stackSize--;
                par3EntityPlayer.addChatMessage(new ChatComponentText("Doom was refilled!"));
            } else {
                par3EntityPlayer.playSound("tragicmc:random.doomconsume", 1.0F, 1.0F);
            }
        }

        return par1ItemStack;
    }
}
