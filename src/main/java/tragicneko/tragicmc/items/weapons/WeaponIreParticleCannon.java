package tragicneko.tragicmc.items.weapons;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.entity.projectile.EntityIreEnergy;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class WeaponIreParticleCannon extends TragicWeapon {

    public WeaponIreParticleCannon(ToolMaterial material, Doomsday dday) {
        super(material, dday);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);
        if (doom == null || doom.getCurrentDoom() < 5
            || doom.getCurrentCooldown() > 0
            || getStackCooldown(par1ItemStack) > 0) return par1ItemStack;

        Vec3 vec = WorldHelper.getVecFromEntity(par3EntityPlayer);
        if (vec == null) return par1ItemStack;

        double d4 = vec.xCoord - par3EntityPlayer.posX;
        double d5 = vec.yCoord - (par3EntityPlayer.posY + par3EntityPlayer.height / 2.0F);
        double d6 = vec.zCoord - par3EntityPlayer.posZ;

        for (int i = 0; i < 5; i++) {
            EntityIreEnergy rocket = new EntityIreEnergy(par3EntityPlayer.worldObj, par3EntityPlayer, d4, d5, d6);
            rocket.posY = par3EntityPlayer.posY + par3EntityPlayer.getEyeHeight() - 0.45;
            rocket.posX += d4 * 0.215;
            rocket.posZ += d6 * 0.215;
            if (!par2World.isRemote) par3EntityPlayer.worldObj.spawnEntityInWorld(rocket);
        }

        par1ItemStack.damageItem(1, par3EntityPlayer);
        if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-5);
        setStackCooldown(par1ItemStack, 3);

        return par1ItemStack;
    }
}
