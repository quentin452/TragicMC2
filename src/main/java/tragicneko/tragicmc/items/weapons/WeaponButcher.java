package tragicneko.tragicmc.items.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.properties.PropertyDoom;

import java.util.UUID;

public class WeaponButcher extends EpicWeapon {

    private static AttributeModifier mod = new AttributeModifier(
        UUID.fromString("040d7d22-6b19-498b-8216-4316cf39387e"),
        "butcherKnockbackResistanceBuff",
        TragicConfig.modifier[23],
        0);

    public WeaponButcher(Doomsday dday) {
        super(dday);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        PropertyDoom doom = PropertyDoom.get(player);

        if (!super.onLeftClickEntity(stack, player, entity) && entity instanceof EntityLivingBase
            && canUseAbility(doom, TragicConfig.doomAbilityCost[6])
            && getStackCooldown(stack) == 0
            && TragicConfig.doomAbility[6]) {
            if (itemRand.nextBoolean())
                ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.weakness.id, 200, 1));
            if (!player.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.doomAbilityCost[6]);
            setStackCooldown(stack, 5);
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag) {
        super.onUpdate(stack, world, entity, numb, flag);
        if (world.isRemote || !(entity instanceof EntityPlayer)) return;

        EntityPlayer player = (EntityPlayer) entity;
        PropertyDoom doom = PropertyDoom.get(player);

        player.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .removeModifier(mod);

        if (flag && doom != null
            && canUseAbility(doom, TragicConfig.doomAbilityCost[5])
            && TragicConfig.doomAbility[5]) {
            player.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
                .applyModifier(mod);
            if (!player.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.doomAbilityCost[5]);
        }
    }
}
