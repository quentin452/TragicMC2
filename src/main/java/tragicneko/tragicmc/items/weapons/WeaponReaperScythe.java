package tragicneko.tragicmc.items.weapons;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.entity.projectile.EntityLargePumpkinbomb;
import tragicneko.tragicmc.entity.projectile.EntityPumpkinbomb;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class WeaponReaperScythe extends ItemScythe {

    public WeaponReaperScythe(ToolMaterial par2Material, Doomsday dday) {
        super(par2Material, dday);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

        if (!TragicWeapon.canUseAbility(doom, 5) || TragicWeapon.getStackCooldown(par1ItemStack) > 0
            || par2World.isRemote) return par1ItemStack;

        Vec3 vec = WorldHelper.getVecFromEntity(par3EntityPlayer, 30.0);
        if (vec == null) return par1ItemStack;

        if (TragicWeapon.getStackCooldown(par1ItemStack) == 0) {
            if (!par3EntityPlayer.isSneaking()) {
                if (TragicWeapon.canUseAbility(doom, TragicConfig.doomAbilityCost[25])
                    && TragicConfig.doomAbility[25]) {
                    EntityPumpkinbomb bomb = new EntityPumpkinbomb(par2World, par3EntityPlayer);
                    bomb.posY = par3EntityPlayer.posY + par3EntityPlayer.getEyeHeight();
                    par3EntityPlayer.worldObj.spawnEntityInWorld(bomb);

                    if (!par3EntityPlayer.capabilities.isCreativeMode)
                        doom.increaseDoom(-TragicConfig.doomAbilityCost[25]);
                    TragicWeapon.setStackCooldown(par1ItemStack, 5);

                    return par1ItemStack;
                }
            } else {
                if (TragicWeapon.canUseAbility(doom, TragicConfig.doomAbilityCost[26])
                    && TragicConfig.doomAbility[26]) {
                    EntityLargePumpkinbomb bomb = new EntityLargePumpkinbomb(par2World, par3EntityPlayer);
                    bomb.posY = par3EntityPlayer.posY + par3EntityPlayer.getEyeHeight();
                    par3EntityPlayer.worldObj.spawnEntityInWorld(bomb);

                    if (!par3EntityPlayer.capabilities.isCreativeMode)
                        doom.increaseDoom(-TragicConfig.doomAbilityCost[26]);
                    TragicWeapon.setStackCooldown(par1ItemStack, 5);

                    return par1ItemStack;
                }
            }
        }

        return par1ItemStack;
    }
}
