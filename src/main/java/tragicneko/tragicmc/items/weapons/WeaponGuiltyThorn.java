package tragicneko.tragicmc.items.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.properties.PropertyDoom;

public class WeaponGuiltyThorn extends TragicWeapon {

    public WeaponGuiltyThorn(ToolMaterial p_i45356_1_, Doomsday dday) {
        super(p_i45356_1_, dday);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        PropertyDoom doom = PropertyDoom.get(player);

        if (!super.onLeftClickEntity(stack, player, entity) && entity instanceof EntityLivingBase
            && itemRand.nextInt(8) == 0
            && canUseAbility(doom, TragicConfig.doomAbilityCost[16])
            && getStackCooldown(stack) == 0
            && TragicConfig.doomAbility[16]) {
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.poison.id, 60, itemRand.nextInt(4)));
            if (TragicConfig.allowStun && itemRand.nextInt(6) == 0)
                ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(TragicPotion.Stun.id, 40, 0));
            if (!player.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.doomAbilityCost[16]);
            setStackCooldown(stack, 5);
        }
        return super.onLeftClickEntity(stack, player, entity);
    }
}
