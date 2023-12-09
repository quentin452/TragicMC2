package tragicneko.tragicmc.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicPotion;

public class EnchantmentDamageBoost extends Enchantment {

    private String[] enchantNames = { "decay", "absolve", "slay", "runeBreak" };
    private int damageType;

    public EnchantmentDamageBoost(int par1, int par2, int par3) {
        super(par1, par2, EnumEnchantmentType.weapon);
        this.setName("damageBoost." + enchantNames[par3]);
        this.damageType = par3;
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
    public boolean canApplyTogether(Enchantment par1Enchantment) {
        Boolean flag = true;

        if (par1Enchantment instanceof EnchantmentDamage || par1Enchantment instanceof EnchantmentDamageBoost) {
            flag = false;
        }

        return flag;
    }

    @Override
    public boolean canApply(ItemStack par1ItemStack) {
        return par1ItemStack.getItem() instanceof ItemAxe ? true : super.canApply(par1ItemStack);
    }

    public float calcModifierLiving(int par1, EntityLivingBase par2EntityLivingBase) {
        switch (this.damageType) {
            case 0:
                if (par2EntityLivingBase.getCreatureAttribute() == TragicEntities.Natural) {
                    return (float) (par1 * 1.25);
                }
                break;
            case 1:
                if (TragicConfig.allowCorruption && par2EntityLivingBase.isPotionActive(TragicPotion.Corruption.id)) {
                    if (par2EntityLivingBase.worldObj.rand.nextInt((16 / par1) + 1) == 0) {
                        par2EntityLivingBase.removePotionEffect(TragicPotion.Corruption.id);
                    }

                    return (float) (par1 * 1.25);
                }
                break;
            case 2:
                if (par2EntityLivingBase.getCreatureAttribute() == TragicEntities.Beast) {
                    return (float) (par1 * 1.25);
                }
                break;
            case 3:
            default:
                return 0F;
        }
        return 0F;
    }

}
