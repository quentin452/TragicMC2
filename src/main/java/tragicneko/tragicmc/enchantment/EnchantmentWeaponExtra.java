package tragicneko.tragicmc.enchantment;

import java.util.Random;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.items.weapons.ItemScythe;

public class EnchantmentWeaponExtra extends Enchantment {

    private String[] enchantNames = { "vampirism", "leech", "consume", "distract", "rust" };
    private int damageType;

    private final Random rand = TragicMC.rand;

    public EnchantmentWeaponExtra(int par1, int par2, int par3) {
        super(par1, par2, EnumEnchantmentType.weapon);
        this.setName("weaponExtra." + enchantNames[par3]);
        this.damageType = par3;
    }

    @Override
    public int getMinEnchantability(int par1) {
        return 15 + (par1 * 5);
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
        return !(par1Enchantment instanceof EnchantmentWeaponExtra) && super.canApplyTogether(par1Enchantment);
    }

    @Override
    public boolean canApply(ItemStack par1ItemStack) {
        return par1ItemStack.getItem() instanceof ItemAxe || par1ItemStack.getItem() instanceof ItemScythe ? true
            : super.canApply(par1ItemStack);
    }

    public float calcModifierLiving(int par1, EntityLivingBase par2EntityLivingBase) {
        par1 = MathHelper.clamp_int(par1, 1, 5);

        if (rand.nextInt(12 - (par1 * 2)) == 0 && this.damageType == 3) {
            if (rand.nextInt(3) == 0) {
                switch (rand.nextInt(10)) {
                    case 0:
                        par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.blindness.id, 120 * par1, par1));
                    case 1:
                        par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.confusion.id, 120 * par1, par1));
                        break;
                    case 2:
                        par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.blindness.id, 120 * par1, par1));
                        break;
                    case 3:
                        if (TragicConfig.allowDisorientation) par2EntityLivingBase
                            .addPotionEffect(new PotionEffect(TragicPotion.Disorientation.id, 120 * par1, par1));
                        break;
                    case 4:
                        par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.confusion.id, 120 * par1, par1));
                    case 5:
                        if (TragicConfig.allowDisorientation) par2EntityLivingBase
                            .addPotionEffect(new PotionEffect(TragicPotion.Disorientation.id, 120 * par1, par1));
                        break;
                    case 6:
                        par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.blindness.id, 120 * par1, par1));
                    case 7:
                        if (TragicConfig.allowDisorientation) par2EntityLivingBase
                            .addPotionEffect(new PotionEffect(TragicPotion.Disorientation.id, 120 * par1, par1));
                    case 8:
                        par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.confusion.id, 120 * par1, par1));
                        break;
                    case 9:
                        if (TragicConfig.allowStun) par2EntityLivingBase
                            .addPotionEffect(new PotionEffect(TragicPotion.Stun.id, 120 * par1, par1));
                        break;
                }
            }
        }

        if (rand.nextInt(12 - (par1 * 2)) == 0 && this.damageType == 4) {
            for (int i = 1; i < 5; i++) {
                if (par2EntityLivingBase.getEquipmentInSlot(i) != null) par2EntityLivingBase.getEquipmentInSlot(i)
                    .attemptDamageItem(rand.nextInt(par1) + 1, rand);
            }
        }
        return 0F;
    }

}
