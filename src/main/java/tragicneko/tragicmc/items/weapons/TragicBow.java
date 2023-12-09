package tragicneko.tragicmc.items.weapons;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.util.LoreHelper;

import java.util.List;

public class TragicBow extends ItemBow {

    public final Doomsday doomsday;
    @SideOnly(Side.CLIENT)
    protected IIcon[] iconArray;

    public TragicBow(int dmg, Doomsday dday) {
        this.setFull3D();
        this.setMaxDamage(dmg);
        this.doomsday = dday;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        int rarity = stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLoreRarity")
            ? stack.stackTagCompound.getInteger("tragicLoreRarity")
            : 0;
        return EnumRarity.values()[rarity];
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {
        if (TragicConfig.allowRandomWeaponLore && LoreHelper.getRarityFromStack(stack) >= 0) {
            String lore = LoreHelper.getDescFromStack(stack);

            if (lore != null) {
                LoreHelper
                    .splitDesc(par2List, lore, 32, LoreHelper.getFormatForRarity(LoreHelper.getRarityFromStack(stack)));
                par2List.add(""); // extra space
            }
        }

        if (TragicConfig.allowDoomsdays && this.doomsday != null) {
            EnumChatFormatting format = doomsday.getDoomsdayType()
                .getFormat();
            par2List.add(format + doomsday.getLocalizedType() + ": " + doomsday.getLocalizedName());
            par2List.add(
                EnumChatFormatting.GOLD + "Doom Cost: " + doomsday.getScaledDoomRequirement(par2EntityPlayer.worldObj));
            par2List.add(
                EnumChatFormatting.DARK_AQUA + "Cooldown: "
                    + doomsday.getScaledCooldown(par2EntityPlayer.worldObj.difficultySetting));
            par2List.add(""); // extra space
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag) {
        TragicWeapon.updateAsWeapon(stack, world, entity, numb, flag);
    }
}
