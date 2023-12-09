package tragicneko.tragicmc.enchantment;

import java.util.Random;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicPotion;

public class EnchantmentArmorExtra extends Enchantment {

    private String[] enchantNames = { "ignition", "paralysis", "toxicity", "elasticity", "deathTouch" };
    private int damageType;
    private final Random rand = TragicMC.rand;

    public EnchantmentArmorExtra(int par1, int par2, int par3) {
        super(par1, par2, EnumEnchantmentType.armor);
        this.setName("armorExtra." + enchantNames[par3]);
        this.damageType = par3;
    }

    @Override
    public int getMinEnchantability(int par1) {
        return 24 + (par1 * 2);
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
        Boolean flag = true;

        if (par1Enchantment instanceof EnchantmentArmorExtra || par1Enchantment instanceof EnchantmentThorns) {
            flag = false;
        }

        return flag;
    }

    @Override
    public int calcModifierDamage(int par1, DamageSource par2DamageSource) {
        if (!par2DamageSource.canHarmInCreative()) {
            if (par2DamageSource.getEntity() != null && par2DamageSource.getEntity() instanceof EntityLivingBase) {
                EntityLivingBase entity = (EntityLivingBase) par2DamageSource.getEntity();
                boolean flag = false;
                if (par1 > 5) par1 = 5;
                if (rand.nextInt(12 - (par1 * 2)) == 0 && !par2DamageSource.isProjectile()
                    && !par2DamageSource.isMagicDamage()
                    && !par2DamageSource.isExplosion()) // 12.5% chance with level 1, 18% chance with level 2, 25%
                                                        // chance with level 3
                {
                    switch (this.damageType) {
                        case 0:
                            if (rand.nextInt(2) == 0) {
                                entity.setFire(8 * par1);
                                flag = true;
                            }
                            break;
                        case 1:
                            if (rand.nextInt(4) == 0 && TragicConfig.allowStun) {
                                entity.addPotionEffect(new PotionEffect(TragicPotion.Stun.id, 60 * par1));
                                flag = true;
                            }
                            break;
                        case 2:
                            if (rand.nextInt(3) == 0) {
                                entity.addPotionEffect(new PotionEffect(Potion.poison.id, 120 * par1, par1));
                                flag = true;
                            }
                            break;
                        case 3:
                            if (rand.nextInt(2) == 0) // 12.5% chance with level 3, 6.25% with level 1
                            {
                                EntityPlayer player = entity.worldObj.getClosestPlayerToEntity(entity, 5.0);

                                if (player != null) {
                                    entity.applyEntityCollision(player);

                                    switch (par1) {
                                        case 1:
                                            entity.motionX *= 1.5;
                                            entity.motionY *= 1.5;
                                            entity.motionZ *= 1.5;
                                            break;
                                        case 2:
                                            entity.motionX *= 2;
                                            entity.motionY *= 2;
                                            entity.motionZ *= 2;
                                            break;
                                        case 3:
                                            entity.motionX *= 2.5;
                                            entity.motionY *= 2.5;
                                            entity.motionZ *= 2.5;
                                            break;
                                        default:
                                            break;
                                    }
                                    entity.velocityChanged = true;
                                }

                                flag = true;
                            }
                            break;
                        case 4:
                            if (rand.nextInt(3) == 0) {
                                entity.addPotionEffect(new PotionEffect(Potion.wither.id, 120 * par1, par1));
                                flag = true;
                            }
                            break;
                    }

                    if (flag && entity instanceof EntityPlayerMP && TragicConfig.allowAchievements)
                        ((EntityPlayerMP) entity).triggerAchievement(TragicAchievements.enchantArmor);
                }
            }
        }
        return 0;
    }

}
