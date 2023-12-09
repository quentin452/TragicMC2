package tragicneko.tragicmc.enchantment;

import java.util.Random;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDurability;
import net.minecraft.enchantment.EnchantmentUntouching;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

public class EnchantmentUnbreakable extends Enchantment {

    public EnchantmentUnbreakable(int par1, int par2, EnumEnchantmentType par3EnumEnchantmentType) {
        super(par1, par2, par3EnumEnchantmentType);
        this.setName("unbreakable");
    }

    @Override
    public int getMinEnchantability(int par1) {
        return 30;
    }

    @Override
    public int getMaxEnchantability(int par1) {
        return super.getMinEnchantability(par1) + 50;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean canApplyTogether(Enchantment par1Enchantment) {
        return super.canApplyTogether(par1Enchantment) && !(par1Enchantment instanceof EnchantmentUntouching)
            && !(par1Enchantment instanceof EnchantmentDurability);
    }

    @Override
    public boolean canApply(ItemStack p_92089_1_) {
        return p_92089_1_.isItemStackDamageable() ? true : super.canApply(p_92089_1_);
    }

    public static boolean negateDamage(ItemStack stack, int p_92097_1_, Random p_92097_2_) {
        return stack.getItemDamage() >= stack.getMaxDamage() - 1;
    }
}
