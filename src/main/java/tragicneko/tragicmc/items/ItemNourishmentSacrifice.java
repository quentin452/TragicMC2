package tragicneko.tragicmc.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.properties.PropertyDoom;

import java.util.List;

public class ItemNourishmentSacrifice extends Item {

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {
        par2List.add("Sacrifice some Hunger for Doom");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.uncommon;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!par2World.isRemote && TragicConfig.allowDoom) {
            PropertyDoom property = PropertyDoom.get(par3EntityPlayer);
            float amount = par3EntityPlayer.getFoodStats()
                .getFoodLevel() - 1;
            amount = (amount / 20.0F) * property.getMaxDoom();

            if (property.getCurrentDoom() < property.getMaxDoom()) {
                if (amount + property.getCurrentDoom() >= property.getMaxDoom()) {
                    property.fillDoom();
                } else {
                    property.increaseDoom((int) amount);
                }

                par3EntityPlayer.getFoodStats()
                    .addStats(
                        -par3EntityPlayer.getFoodStats()
                            .getFoodLevel() + 1,
                        0.0F);
                if (TragicConfig.allowMalnourish)
                    par3EntityPlayer.addPotionEffect(new PotionEffect(TragicPotion.Malnourish.id, 600, 0));
                if (TragicConfig.allowConvergence)
                    par3EntityPlayer.addPotionEffect(new PotionEffect(TragicPotion.Convergence.id, 300));

                if (!par3EntityPlayer.capabilities.isCreativeMode) par1ItemStack.stackSize--;

                par3EntityPlayer.addChatMessage(new ChatComponentText("Hunger sacrificed!"));
            }
        }

        return par1ItemStack;
    }
}
