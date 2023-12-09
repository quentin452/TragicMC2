package tragicneko.tragicmc.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;

public class EnchantmentRuneWalker extends Enchantment {

    public EnchantmentRuneWalker(int par1, int par2, EnumEnchantmentType par3EnumEnchantmentType) {
        super(par1, par2, par3EnumEnchantmentType);
        this.setName("runeWalker");
    }

    @Override
    public int getMinEnchantability(int par1) {
        return 15 + (par1 * 3);
    }

    @Override
    public int getMaxEnchantability(int par1) {
        return super.getMinEnchantability(par1) + 50;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int calcModifierDamage(int par1, DamageSource source) {
        return source.isMagicDamage() && !(source.canHarmInCreative()) ? MathHelper.floor_float(par1 * 1.5F) : 0;
    }

    @Override
    public boolean canApplyTogether(Enchantment par1Enchantment) {
        Boolean flag = true;

        if (par1Enchantment instanceof EnchantmentProtection) {
            flag = false;
        }
        return super.canApplyTogether(par1Enchantment) && flag;
    }
}
