package tragicneko.tragicmc.items.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.properties.PropertyDoom;

public class WeaponHarmonyBell extends TragicWeapon {

    public WeaponHarmonyBell(ToolMaterial material, Doomsday dday) {
        super(material, dday);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        PropertyDoom doom = PropertyDoom.get(player);

        if (!super.onLeftClickEntity(stack, player, entity) && entity instanceof EntityLivingBase
            && TragicConfig.allowHarmony
            && canUseAbility(doom, TragicConfig.doomAbilityCost[17])
            && getStackCooldown(stack) == 0
            && TragicConfig.doomAbility[17]) {
            if (doom != null && doom.getCurrentDoom() >= 3) {
                ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(TragicPotion.Harmony.id, 60, 0));
                if (!player.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.doomAbilityCost[17]);
                setStackCooldown(stack, 5);
            }
        }

        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
        super.onUpdate(stack, world, entity, par4, par5);

        if (entity instanceof EntityLivingBase) {
            if (par5 && entity.ticksExisted % 120 == 0
                && ((EntityLivingBase) entity).getHealth() < ((EntityLivingBase) entity).getMaxHealth()
                && TragicConfig.doomAbility[18]) {
                if (entity instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) entity;
                    PropertyDoom doom = PropertyDoom.get(player);

                    if (doom != null && canUseAbility(doom, TragicConfig.doomAbilityCost[18])) {
                        if (!player.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.doomAbilityCost[18]);
                        player.heal(1.0F);
                    }
                }
            }
        }
    }
}
