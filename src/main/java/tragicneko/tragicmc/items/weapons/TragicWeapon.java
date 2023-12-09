package tragicneko.tragicmc.items.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.doomsday.Doomsday.EnumDoomType;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.LoreHelper;
import tragicneko.tragicmc.util.LoreHelper.EnchantEntry;
import tragicneko.tragicmc.util.LoreHelper.Lore;
import tragicneko.tragicmc.util.LoreHelper.LoreEntry;

import java.util.List;

public class TragicWeapon extends ItemSword {

    protected final Doomsday doomsday;
    protected Doomsday doomsday2;
    public Item.ToolMaterial material;
    public float ascensionLevel;

    public TragicWeapon(ToolMaterial material, Doomsday dday) {
        super(material);
        this.doomsday = dday;
        this.setCreativeTab(TragicMC.Survival);
        this.material = material;
    }

    public TragicWeapon(ToolMaterial material, Doomsday dday, Doomsday dday2) {
        this(material, dday);
        this.doomsday2 = dday2;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        int rarity = stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLoreRarity")
            ? stack.stackTagCompound.getInteger("tragicLoreRarity")
            : 0;
        return EnumRarity.values()[rarity];
    }

    public Doomsday getDoomsday() {
        return this.doomsday;
    }

    public EnumDoomType doomsdayType() {
        return this.doomsday.doomsdayType;
    }

    public TragicWeapon setAscensionLevel(float f) {
        this.ascensionLevel = f;
        return this;
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
            EnumChatFormatting format;

            if (this.doomsday2 != null) {
                format = doomsday2.getDoomsdayType()
                    .getFormat();
                par2List.add(format + doomsday2.getLocalizedType() + ": " + doomsday2.getLocalizedName());
                par2List.add(
                    EnumChatFormatting.GOLD + "Doom Cost: "
                        + doomsday2.getScaledDoomRequirement(par2EntityPlayer.worldObj));
                par2List.add(
                    EnumChatFormatting.DARK_AQUA + "Cooldown: "
                        + doomsday2.getScaledCooldown(par2EntityPlayer.worldObj.difficultySetting));
                par2List.add(""); // extra space in between
            }

            format = doomsday.getDoomsdayType()
                .getFormat();
            par2List.add(format + doomsday.getLocalizedType() + ": " + doomsday.getLocalizedName());
            par2List.add(
                EnumChatFormatting.GOLD + "Doom Cost: " + doomsday.getScaledDoomRequirement(par2EntityPlayer.worldObj));
            par2List.add(
                EnumChatFormatting.DARK_AQUA + "Cooldown: "
                    + doomsday.getScaledCooldown(par2EntityPlayer.worldObj.difficultySetting));
            par2List.add(""); // extra space
        }

        if (this.ascensionLevel > 0F) {
            par2List.add(EnumChatFormatting.LIGHT_PURPLE + "Ascension Level: " + this.ascensionLevel);
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag) {
        if (world.isRemote || !(entity instanceof EntityPlayer)) return;
        updateAsWeapon(stack, world, entity, numb, flag);
    }

    /**
     * Allow any item class to update as a weapon and not require extending the main TragicWeapon class, this applies
     * and decrements cooldown and sets up a weapon's lore
     *
     * @param stack
     * @param world
     * @param entity
     * @param numb
     * @param flag
     */
    public static void updateAsWeapon(ItemStack stack, World world, Entity entity, int numb, boolean flag) {
        if (world.isRemote) return;
        if (!stack.hasTagCompound()) stack.stackTagCompound = new NBTTagCompound();

        if (!stack.stackTagCompound.hasKey("cooldown")) stack.stackTagCompound.setInteger("cooldown", 0);
        if (getStackCooldown(stack) > 0) setStackCooldown(stack, getStackCooldown(stack) - 1);

        if (!TragicConfig.allowRandomWeaponLore || stack.getItem() == null) return;

        LoreEntry entry = LoreHelper.getLoreEntry(
            stack.getItem()
                .getClass());
        if (entry == null) return;
        Lore lore = entry.getRandomLore();
        if (lore == null) return;

        if (!stack.stackTagCompound.hasKey("tragicLoreRarity"))
            stack.stackTagCompound.setByte("tragicLoreRarity", Byte.valueOf((byte) lore.getRarity()));
        if (!stack.stackTagCompound.hasKey("tragicLoreDesc"))
            stack.stackTagCompound.setString("tragicLoreDesc", lore.getDesc());

        int rarity = stack.stackTagCompound.getByte("tragicLoreRarity");
        lore = entry.getLoreOfRarity(rarity);

        if (!stack.isItemEnchanted() && lore != null) {
            EnchantEntry[] enchants = entry.getEnchantmentsForRarity(rarity);
            if (enchants == null) return;

            for (EnchantEntry e : enchants) {
                if (e != null && e.getEnchantment() != null)
                    stack.addEnchantment(e.getEnchantment(), e.getEnchantLevel());
            }
        }
    }

    public static boolean canUseAbility(PropertyDoom doom, int rq) {
        return doom != null && TragicConfig.allowNonDoomsdayAbilities
            && doom.getCurrentCooldown() == 0
            && doom.getCurrentDoom() >= rq;
    }

    public static void setStackCooldown(ItemStack stack, int i) {
        if (!stack.hasTagCompound()) return;
        stack.stackTagCompound.setInteger("cooldown", i);
    }

    public static int getStackCooldown(ItemStack stack) {
        return stack.hasTagCompound() && stack.stackTagCompound.hasKey("cooldown")
            ? stack.stackTagCompound.getInteger("cooldown")
            : 0;
    }

    public Doomsday getSecondaryDoomsday() {
        return this.doomsday2;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase entity2) {
        if (entity instanceof EntityPlayer && !TragicConfig.allowPvP) return false;
        return super.hitEntity(stack, entity, entity2);
    }
}
