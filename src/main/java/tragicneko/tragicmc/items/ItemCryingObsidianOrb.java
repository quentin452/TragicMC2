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

public class ItemCryingObsidianOrb extends Item {

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {
        par2List.add("When Right-Clicked, will set your");
        par2List.add("spawn point, only affects the dimension");
        par2List.add("you're currently in");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!par2World.isRemote) {
            par2World.getSpawnPoint();
            int x = (int) par3EntityPlayer.posX;
            int z = (int) par3EntityPlayer.posZ;
            int y = (int) par3EntityPlayer.posY;

            ChunkCoordinates newCC = new ChunkCoordinates(x, y, z);

            int dim = par2World.provider.dimensionId;

            par3EntityPlayer.setSpawnChunk(newCC, true, dim);
            par3EntityPlayer.addChatMessage(
                new ChatComponentText(
                    "Spawn set to " + x
                        + ", "
                        + y
                        + ", "
                        + z
                        + " for dimension with id of "
                        + par2World.provider.dimensionId));

            if (par3EntityPlayer instanceof EntityPlayerMP && TragicConfig.allowAchievements)
                ((EntityPlayerMP) par3EntityPlayer).triggerAchievement(TragicAchievements.useOrb);
            par1ItemStack.stackSize--;
        }
        return par1ItemStack;
    }
}
