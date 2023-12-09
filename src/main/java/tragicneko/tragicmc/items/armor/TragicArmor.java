package tragicneko.tragicmc.items.armor;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.util.LoreHelper;
import tragicneko.tragicmc.util.LoreHelper.EnchantEntry;
import tragicneko.tragicmc.util.LoreHelper.Lore;
import tragicneko.tragicmc.util.LoreHelper.LoreEntry;

import java.util.List;

public class TragicArmor extends ItemArmor {

    public final Doomsday doomsday;
    private IIcon damagedIcon;

    public TragicArmor(ArmorMaterial material, int armorType, Doomsday dday) {
        super(material, 0, armorType);
        this.doomsday = dday;
        this.setCreativeTab(TragicMC.Survival);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        int rarity = stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLoreRarity")
            ? stack.stackTagCompound.getInteger("tragicLoreRarity")
            : 0;
        return EnumRarity.values()[rarity];
    }

    @Override
    public void registerIcons(IIconRegister register) {
        super.registerIcons(register);
        this.damagedIcon = register.registerIcon(this.iconString + "2");
    }

    @Override
    public IIcon getIconFromDamage(int damage) {
        if (damage >= this.getMaxDamage() * 2 / 3) return this.damagedIcon;
        return this.itemIcon;
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
        if (!stack.hasTagCompound()) stack.stackTagCompound = new NBTTagCompound();
        if (!TragicConfig.allowRandomWeaponLore) return;

        LoreEntry entry = LoreHelper.getLoreEntry(
            stack.getItem()
                .getClass());
        if (entry == null) return;
        Lore lore = entry.getRandomLore();
        if (lore == null) return;

        if (!stack.stackTagCompound.hasKey("tragicLoreRarity"))
            stack.stackTagCompound.setByte("tragicLoreRarity", Byte.valueOf((byte) lore.getRarity()));
        if (!stack.stackTagCompound.hasKey("tragicLoreDesc"))
            stack.stackTagCompound.setString("tragicLoreDesc", lore.getDesc() == null ? "" : lore.getDesc());

        int rarity = stack.stackTagCompound.getByte("tragicLoreRarity");
        lore = entry.getLoreOfRarity(rarity);

        if (!stack.isItemEnchanted() && lore != null) {
            EnchantEntry[] enchants = entry.getEnchantmentsForArmor(rarity, this.armorType);
            if (enchants == null) return;

            for (EnchantEntry e : enchants) {
                if (e != null && e.getEnchantment() != null)
                    stack.addEnchantment(e.getEnchantment(), e.getEnchantLevel());
            }
        }
    }
}
