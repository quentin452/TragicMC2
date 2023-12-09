package tragicneko.tragicmc.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentCombustion extends Enchantment {

    public EnchantmentCombustion(int par1, int par2, EnumEnchantmentType par3EnumEnchantmentType) {
        super(par1, par2, par3EnumEnchantmentType);
        this.setName("combustion");
    }

    @Override
    public int getMinEnchantability(int par1) {
        return 20;
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
        return super.canApplyTogether(par1Enchantment) && par1Enchantment.effectId != Enchantment.silkTouch.effectId;
    }

}
