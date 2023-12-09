package tragicneko.tragicmc.items.weapons;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.entity.projectile.EntityPitchBlack;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class WeaponPitchBlack extends TragicWeapon {

    public WeaponPitchBlack(ToolMaterial material, Doomsday dday) {
        super(material, dday);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

        if (par2World.isRemote || doom == null) return par1ItemStack;

        Vec3 vec = WorldHelper.getVecFromEntity(par3EntityPlayer);
        if (vec == null) return par1ItemStack;

        if (canUseAbility(doom, TragicConfig.doomAbilityCost[24]) && getStackCooldown(par1ItemStack) == 0
            && TragicConfig.doomAbility[24]) {
            setStackCooldown(par1ItemStack, 5);

            EntityPitchBlack rocket = new EntityPitchBlack(par3EntityPlayer.worldObj, par3EntityPlayer);
            rocket.posY = par3EntityPlayer.posY + 1.6D;
            rocket.setStack(par1ItemStack);
            par3EntityPlayer.worldObj.spawnEntityInWorld(rocket);

            if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.doomAbilityCost[24]);
            par1ItemStack.stackSize--;

            return par1ItemStack;
        }

        return par1ItemStack;
    }
}
