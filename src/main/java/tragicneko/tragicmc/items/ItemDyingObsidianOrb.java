package tragicneko.tragicmc.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;

import java.util.List;

public class ItemDyingObsidianOrb extends Item {

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {
        par2List.add("When Right-Clicked, will teleport you");
        par2List.add("to your last spawn point in the overworld");
        par2List.add("no matter what dimension you're in");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!par2World.isRemote) {
            int dim = par2World.provider.dimensionId;

            if (dim != 0) {
                par3EntityPlayer.travelToDimension(0);
            }

            ChunkCoordinates cc = par3EntityPlayer.getBedLocation(0);

            if (cc != null) {
                par3EntityPlayer.setPositionAndUpdate(cc.posX, cc.posY, cc.posZ);
                par3EntityPlayer.addChatMessage(
                    new ChatComponentText("Teleported to " + cc.posX + ", " + cc.posY + ", " + cc.posZ));
            } else {
                ChunkCoordinates cc2 = par2World.getSpawnPoint();
                par3EntityPlayer
                    .setPositionAndUpdate(cc2.posX, par2World.getTopSolidOrLiquidBlock(cc2.posX, cc2.posZ), cc2.posZ);
                par3EntityPlayer.addChatMessage(
                    new ChatComponentText("Teleported to " + cc2.posX + ", " + cc2.posY + ", " + cc2.posZ));
            }

            if (par3EntityPlayer instanceof EntityPlayerMP && TragicConfig.allowAchievements)
                ((EntityPlayerMP) par3EntityPlayer).triggerAchievement(TragicAchievements.useOrb);
            par1ItemStack.stackSize--;
        }
        return par1ItemStack;
    }
}
