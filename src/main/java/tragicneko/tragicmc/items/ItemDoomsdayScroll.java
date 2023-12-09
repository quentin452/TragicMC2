package tragicneko.tragicmc.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.properties.PropertyDoom;

import java.awt.*;
import java.util.List;

public class ItemDoomsdayScroll extends Item {

    public ItemDoomsdayScroll() {
        this.setCreativeTab(TragicMC.Survival);
        this.setMaxDamage(0);
        this.maxStackSize = 1;
        this.setHasSubtypes(true);
        this.setUnlocalizedName("tragicmc.doomsdayScroll");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.epic;
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {
        Doomsday dday = Doomsday.getDoomsdayFromId(par1ItemStack.getItemDamage() + 1);

        par2List.add(EnumChatFormatting.WHITE + "This is a one-time use, regardless of if the");
        par2List.add(EnumChatFormatting.WHITE + "effect is successful or not.");

        if (TragicConfig.allowDoomsdays && dday != null) {
            par2List.add(""); // extra space in between
            EnumChatFormatting format = dday.getDoomsdayType()
                .getFormat();
            par2List.add(format + dday.getLocalizedType() + ": " + dday.getLocalizedName());
            par2List.add(
                EnumChatFormatting.GOLD + "Doom Cost: " + dday.getScaledDoomRequirement(par2EntityPlayer.worldObj));
            par2List.add(
                EnumChatFormatting.DARK_AQUA + "Cooldown: "
                    + dday.getScaledCooldown(par2EntityPlayer.worldObj.difficultySetting));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
        if (par1ItemStack.getItemDamage() < Doomsday.doomsdayNames.length) {
            Doomsday dday = Doomsday.getDoomsdayFromId(par1ItemStack.getItemDamage() + 1);
            Color color = new Color(0x00, 0x00, 0x00);
            if (dday != null) {
                switch (dday.getDoomsdayType()) {
                    case COMBINATION:
                        color = new Color(0xFF, 0xDB, 0x76);
                        // color = light purple
                        break;
                    case CRISIS:
                        color = new Color(0xB7, 0x00, 0x00);
                        // color = red
                        break;
                    case INFLUENCE:
                        // color = aqua or light blue
                        color = new Color(0x43, 0xBD, 0xBD);
                        break;
                    case OVERFLOW:
                        // color = green
                        color = new Color(0x00, 0xFF, 0x00);
                        break;
                    case WORLDSHAPER:
                        color = new Color(0x9C, 0x23, 0xAC);
                        // color = dark purple
                        break;
                    default:
                        break;
                }

                return color.getRGB();
            }
        }
        return -1;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        String s = ("" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
        if (stack.getItemDamage() >= Doomsday.doomsdayNames.length - 1
            || Doomsday.getDoomsdayFromId(stack.getItemDamage() + 1) == null) return s;
        return Doomsday.getDoomsdayFromId(stack.getItemDamage() + 1)
            .getLocalizedName() + " "
            + s;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!TragicConfig.allowDoomsdays) return stack;

        if (world.isRemote) {
            player.playSound("tragicmc:random.doomsuccess", 1.0F, 1.0F);
        } else {
            PropertyDoom doom = PropertyDoom.get(player);
            if (doom != null) {
                Doomsday doomsday = Doomsday.getDoomsdayFromId(stack.getItemDamage() + 1);
                if (doomsday != null) doomsday.activateDoomsday(doom);
                stack.stackSize--;
                if (TragicConfig.allowAchievements && player instanceof EntityPlayerMP)
                    player.triggerAchievement(TragicAchievements.doomsdayScroll);
            }
        }
        return stack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 1; i < Doomsday.doomsdayList.length; i++) {
            if (Doomsday.doomsdayList[i] != null) list.add(new ItemStack(item, 1, i - 1));
            else break;
        }
    }
}
