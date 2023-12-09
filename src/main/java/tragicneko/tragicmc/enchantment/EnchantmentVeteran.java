package tragicneko.tragicmc.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

import tragicneko.tragicmc.TragicEnchantments;

public class EnchantmentVeteran extends Enchantment {

    public EnchantmentVeteran(int par1, int par2, EnumEnchantmentType par3EnumEnchantmentType) {
        super(par1, par2, par3EnumEnchantmentType);
        this.setName("veteran");
    }

    @Override
    public int getMinEnchantability(int par1) {
        return 12 + (par1 * 4);
    }

    @Override
    public int getMaxEnchantability(int par1) {
        return super.getMinEnchantability(par1) + 50;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean canApplyTogether(Enchantment par1Enchantment) {
        return super.canApplyTogether(par1Enchantment) && par1Enchantment.effectId != Enchantment.silkTouch.effectId
            && par1Enchantment.effectId != TragicEnchantments.Combustion.effectId;
    }

}
