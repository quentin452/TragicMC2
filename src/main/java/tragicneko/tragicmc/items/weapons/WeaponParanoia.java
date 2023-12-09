package tragicneko.tragicmc.items.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.entity.projectile.EntityDarkEnergy;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class WeaponParanoia extends EpicWeapon {

    public WeaponParanoia(Doomsday dday) {
        super(dday);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        PropertyDoom doom = PropertyDoom.get(player);

        if (!super.onLeftClickEntity(stack, player, entity) && entity instanceof EntityLivingBase
            && itemRand.nextInt(4) == 0
            && canUseAbility(doom, TragicConfig.doomAbilityCost[21])
            && getStackCooldown(stack) == 0
            && TragicConfig.doomAbility[21]) {
            if (TragicConfig.allowFear) ((EntityLivingBase) entity)
                .addPotionEffect(new PotionEffect(TragicPotion.Fear.id, 240, itemRand.nextInt(2)));
            if (TragicConfig.allowSubmission && itemRand.nextInt(16) == 0) ((EntityLivingBase) entity)
                .addPotionEffect(new PotionEffect(TragicPotion.Submission.id, 320, itemRand.nextInt(4)));

            if (!player.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.doomAbilityCost[21]);
            setStackCooldown(stack, 5);
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

        if (par2World.isRemote || doom == null) return par1ItemStack;

        if (par3EntityPlayer.isSneaking()) {
            if (canUseAbility(doom, TragicConfig.doomAbilityCost[22]) && getStackCooldown(par1ItemStack) == 0
                && TragicConfig.doomAbility[22]) {
                for (int l = 0; l < 5; l++) {
                    double d0 = (MathHelper.getRandomIntegerInRange(itemRand, -4, 4) + par3EntityPlayer.posX)
                        - par3EntityPlayer.posX;
                    double d1 = (MathHelper.getRandomIntegerInRange(itemRand, -2, 2) + par3EntityPlayer.posY)
                        - par3EntityPlayer.posY;
                    double d2 = (MathHelper.getRandomIntegerInRange(itemRand, -4, 4) + par3EntityPlayer.posZ)
                        - par3EntityPlayer.posZ;

                    EntityDarkEnergy fireball = new EntityDarkEnergy(
                        par3EntityPlayer.worldObj,
                        par3EntityPlayer,
                        d0,
                        d1,
                        d2);
                    fireball.setPosition(
                        par3EntityPlayer.posX + (d0 * 0.115),
                        par3EntityPlayer.posY + 1.2,
                        par3EntityPlayer.posZ + (d2 * 0.115));
                    par3EntityPlayer.worldObj.spawnEntityInWorld(fireball);
                }

                if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.doomAbilityCost[22]);
                setStackCooldown(par1ItemStack, 5);

                return par1ItemStack;
            }
        } else {
            if (canUseAbility(doom, TragicConfig.doomAbilityCost[23]) && getStackCooldown(par1ItemStack) == 0
                && TragicConfig.doomAbility[23]) {
                Vec3 vec = WorldHelper.getVecFromEntity(par3EntityPlayer);
                if (vec == null) return par1ItemStack;

                double d4 = vec.xCoord - par3EntityPlayer.posX;
                double d5 = vec.yCoord - (par3EntityPlayer.posY + par3EntityPlayer.getEyeHeight());
                double d6 = vec.zCoord - par3EntityPlayer.posZ;

                EntityDarkEnergy rocket = new EntityDarkEnergy(par3EntityPlayer.worldObj, par3EntityPlayer, d4, d5, d6);
                rocket.posX = par3EntityPlayer.posX + d4 * 0.15D;
                rocket.posY = par3EntityPlayer.posY + par3EntityPlayer.getEyeHeight();
                rocket.posZ = par3EntityPlayer.posZ + d6 * 0.15D;
                par3EntityPlayer.worldObj.spawnEntityInWorld(rocket);

                if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.doomAbilityCost[23]);
                setStackCooldown(par1ItemStack, 5);

                return par1ItemStack;
            }
        }

        return par1ItemStack;
    }

}
