package tragicneko.tragicmc.items.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class WeaponDragonFang extends EpicWeapon {

    public WeaponDragonFang(Doomsday dday) {
        super(dday);
        this.doomsday2 = Doomsday.FireRain;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (player.worldObj.isRemote || itemRand.nextInt(4) != 0) return super.onLeftClickEntity(stack, player, entity);

        PropertyDoom doom = PropertyDoom.get(player);

        if (doom != null && canUseAbility(doom, TragicConfig.doomAbilityCost[9])
            && entity instanceof EntityLivingBase
            && getStackCooldown(stack) == 0
            && TragicConfig.doomAbility[9]) {
            entity.setFire(8 + itemRand.nextInt(5));
            if (!player.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.doomAbilityCost[9]);
            setStackCooldown(stack, 5);
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par2World.isRemote) return par1ItemStack;

        PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);
        if (doom == null) return par1ItemStack;

        Vec3 vec = WorldHelper.getVecFromEntity(par3EntityPlayer);
        if (vec == null) return par1ItemStack;

        double d4 = vec.xCoord - par3EntityPlayer.posX;
        double d5 = vec.yCoord - (par3EntityPlayer.posY + par3EntityPlayer.getEyeHeight());
        double d6 = vec.zCoord - par3EntityPlayer.posZ;

        if (getStackCooldown(par1ItemStack) == 0) {
            if (par3EntityPlayer.isSneaking()) {
                if (canUseAbility(doom, TragicConfig.doomAbilityCost[10]) && TragicConfig.doomAbility[10]) {
                    EntityLargeFireball rocket = new EntityLargeFireball(
                        par3EntityPlayer.worldObj,
                        par3EntityPlayer,
                        d4 + itemRand.nextDouble() - itemRand.nextDouble(),
                        d5,
                        d6 + itemRand.nextDouble() - itemRand.nextDouble());
                    rocket.posX += d4 * 0.115D;
                    rocket.posY = par3EntityPlayer.posY + par3EntityPlayer.getEyeHeight();
                    rocket.posZ += d6 * 0.115D;
                    par3EntityPlayer.worldObj.spawnEntityInWorld(rocket);

                    if (!par3EntityPlayer.capabilities.isCreativeMode)
                        doom.increaseDoom(-TragicConfig.doomAbilityCost[10]);
                    setStackCooldown(par1ItemStack, 5);
                }
            } else {
                if (canUseAbility(doom, TragicConfig.doomAbilityCost[37]) && TragicConfig.doomAbility[37]) {
                    for (int i = 0; i < 7; i++) {
                        EntitySmallFireball rocket = new EntitySmallFireball(
                            par3EntityPlayer.worldObj,
                            par3EntityPlayer,
                            d4 + itemRand.nextDouble() - itemRand.nextDouble(),
                            d5,
                            d6 + itemRand.nextDouble() - itemRand.nextDouble());
                        rocket.posX += d4 * 0.115D;
                        rocket.posY = par3EntityPlayer.posY + par3EntityPlayer.getEyeHeight();
                        rocket.posZ += d6 * 0.115D;
                        par3EntityPlayer.worldObj.spawnEntityInWorld(rocket);
                    }

                    if (!par3EntityPlayer.capabilities.isCreativeMode)
                        doom.increaseDoom(-TragicConfig.doomAbilityCost[37]);
                    setStackCooldown(par1ItemStack, 5);
                }
            }
        }

        return par1ItemStack;
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
        super.onUpdate(stack, world, entity, par4, par5);
        if (entity.isBurning() && TragicConfig.doomAbility[11]) {
            entity.extinguish();
            if (entity instanceof EntityPlayerMP) {
                EntityPlayerMP mp = (EntityPlayerMP) entity;
                PropertyDoom doom = PropertyDoom.get(mp);

                if (doom != null && !mp.capabilities.isCreativeMode)
                    doom.increaseDoom(TragicConfig.doomAbilityCost[11]);
            }
        }
    }

}
