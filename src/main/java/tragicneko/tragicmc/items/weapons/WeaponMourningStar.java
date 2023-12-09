package tragicneko.tragicmc.items.weapons;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class WeaponMourningStar extends TragicWeapon {

    public WeaponMourningStar(ToolMaterial material, Doomsday dday) {
        super(material, dday);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par2World.isRemote) return par1ItemStack;

        PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

        if (doom != null) {
            if (!par3EntityPlayer.isSneaking()) {
                if (canUseAbility(doom, TragicConfig.doomAbilityCost[19]) && getStackCooldown(par1ItemStack) == 0
                    && TragicConfig.doomAbility[19]) {
                    if (!par3EntityPlayer.capabilities.isCreativeMode)
                        doom.increaseDoom(-TragicConfig.doomAbilityCost[19]);
                    setStackCooldown(par1ItemStack, 5);

                    par2World.createExplosion(
                        par3EntityPlayer,
                        par3EntityPlayer.posX,
                        par3EntityPlayer.posY,
                        par3EntityPlayer.posZ,
                        3.0F * itemRand.nextFloat() + 2.0F,
                        TragicConfig.griefConfig[3]);
                    return par1ItemStack;
                }
            } else {
                if (canUseAbility(doom, TragicConfig.doomAbilityCost[20]) && getStackCooldown(par1ItemStack) == 0
                    && TragicConfig.doomAbility[20]) {
                    Vec3 vec = WorldHelper.getVecFromEntity(par3EntityPlayer, 30.0);
                    if (vec == null) return par1ItemStack;

                    double d4 = vec.xCoord;
                    double d5 = vec.yCoord;
                    double d6 = vec.zCoord;

                    par3EntityPlayer.worldObj.createExplosion(
                        par3EntityPlayer,
                        d4,
                        d5,
                        d6,
                        3.0F * itemRand.nextFloat() + 2.0F,
                        TragicConfig.griefConfig[3]);
                    if (!par3EntityPlayer.capabilities.isCreativeMode)
                        doom.increaseDoom(-TragicConfig.doomAbilityCost[20]);
                    setStackCooldown(par1ItemStack, 5);

                    return par1ItemStack;
                }
            }
        }
        return par1ItemStack;
    }

}
