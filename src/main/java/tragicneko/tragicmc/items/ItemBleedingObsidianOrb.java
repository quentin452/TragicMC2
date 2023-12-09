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

public class ItemBleedingObsidianOrb extends Item {

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {
        par2List.add("When Right-Clicked, will teleport you");
        par2List.add("to your last spawn point in your current");
        par2List.add("dimension or to the overworld if no spawn point");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!par2World.isRemote) {
            int dim = par2World.provider.dimensionId;
            ChunkCoordinates cc = par3EntityPlayer.getBedLocation(dim);

            if (cc != null) {
                par3EntityPlayer.setPositionAndUpdate(cc.posX, cc.posY, cc.posZ);
                par3EntityPlayer.addChatMessage(
                    new ChatComponentText(
                        "Teleported to " + cc.posX
                            + ", "
                            + cc.posY
                            + ", "
                            + cc.posZ
                            + " for dimension with id of "
                            + dim));
            } else {
                if (dim != 0) {
                    par3EntityPlayer.travelToDimension(0);
                }

                ChunkCoordinates cc2 = par3EntityPlayer.getBedLocation(0);

                if (cc2 != null) {
                    par3EntityPlayer.setPositionAndUpdate(cc2.posX, cc2.posY, cc2.posZ);
                    par3EntityPlayer.addChatMessage(
                        new ChatComponentText(
                            "Teleported to " + cc2.posX
                                + ", "
                                + cc2.posY
                                + ", "
                                + cc2.posZ
                                + " for dimension with id of 0"));
                } else {
                    ChunkCoordinates cc3 = par2World.getSpawnPoint();
                    par3EntityPlayer.setPositionAndUpdate(
                        cc3.posX,
                        par2World.getTopSolidOrLiquidBlock(cc3.posX, cc3.posZ),
                        cc3.posZ);
                    par3EntityPlayer.addChatMessage(
                        new ChatComponentText(
                            "Teleported to " + cc3.posX
                                + ", "
                                + cc3.posY
                                + ", "
                                + cc3.posZ
                                + " for dimension with id of 0"));
                }
            }

            if (par3EntityPlayer instanceof EntityPlayerMP && TragicConfig.allowAchievements)
                ((EntityPlayerMP) par3EntityPlayer).triggerAchievement(TragicAchievements.useOrb);
            par1ItemStack.stackSize--;
        }
        return par1ItemStack;
    }
}
