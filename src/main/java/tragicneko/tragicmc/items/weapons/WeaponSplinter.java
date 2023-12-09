package tragicneko.tragicmc.items.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

import java.util.List;

public class WeaponSplinter extends EpicWeapon {

    public WeaponSplinter(Doomsday dday) {
        super(dday);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

        if (doom == null || !TragicConfig.allowNonDoomsdayAbilities || par2World.isRemote) return par1ItemStack;

        Vec3 vec = WorldHelper.getVecFromEntity(par3EntityPlayer);
        if (vec == null) return par1ItemStack;

        if (canUseAbility(doom, TragicConfig.doomAbilityCost[28]) && getStackCooldown(par1ItemStack) == 0
            && TragicConfig.doomAbility[28]) {
            List<Entity> list = par2World.getEntitiesWithinAABBExcludingEntity(
                par3EntityPlayer,
                par3EntityPlayer.boundingBox.expand(12.0D, 12.0D, 12.0D)
                    .offset(vec.xCoord, vec.yCoord, vec.zCoord));
            EntityLivingBase entity;

            if (list.isEmpty()) return par1ItemStack;

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof EntityLivingBase) {
                    entity = (EntityLivingBase) list.get(i);

                    entity.motionX = (itemRand.nextDouble() - itemRand.nextDouble()) * 2.455;
                    entity.motionY = 0.45D;
                    entity.motionZ = (itemRand.nextDouble() - itemRand.nextDouble()) * 2.455;
                }
            }

            if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.doomAbilityCost[28]);
            setStackCooldown(par1ItemStack, 5);
        }

        return par1ItemStack;
    }

}
