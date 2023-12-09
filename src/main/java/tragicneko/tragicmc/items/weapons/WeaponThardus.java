package tragicneko.tragicmc.items.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.entity.projectile.EntityIcicle;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class WeaponThardus extends EpicWeapon {

    public WeaponThardus(Doomsday dday) {
        super(dday);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (player.worldObj.isRemote || itemRand.nextInt(4) != 0) return super.onLeftClickEntity(stack, player, entity);

        PropertyDoom doom = PropertyDoom.get(player);

        if (!super.onLeftClickEntity(stack, player, entity) && entity instanceof EntityLivingBase
            && canUseAbility(doom, TragicConfig.doomAbilityCost[29])
            && getStackCooldown(stack) == 0
            && TragicConfig.doomAbility[29]) {
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 1200, 6));
            if (!player.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.doomAbilityCost[29]);
            setStackCooldown(stack, 5);
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

        if (doom == null || !TragicConfig.allowNonDoomsdayAbilities || par2World.isRemote) return par1ItemStack;

        Vec3 vec = WorldHelper.getVecFromEntity(par3EntityPlayer);
        if (vec == null) return par1ItemStack;

        if (canUseAbility(doom, TragicConfig.doomAbilityCost[30]) && getStackCooldown(par1ItemStack) == 0
            && TragicConfig.doomAbility[30]) {
            double d4 = vec.xCoord - par3EntityPlayer.posX;
            double d5 = vec.yCoord - (par3EntityPlayer.posY + par3EntityPlayer.height / 2.0F);
            double d6 = vec.zCoord - par3EntityPlayer.posZ;

            for (int i = 0; i < 7; i++) {
                EntityIcicle rocket = new EntityIcicle(
                    par3EntityPlayer.worldObj,
                    par3EntityPlayer,
                    d4 + itemRand.nextDouble() - itemRand.nextDouble(),
                    d5,
                    d6 + itemRand.nextDouble() - itemRand.nextDouble());
                rocket.posX += d4 * 0.115D;
                rocket.posY = par3EntityPlayer.posY + 0.6D;
                rocket.posZ += d6 * 0.115D;
                par3EntityPlayer.worldObj.spawnEntityInWorld(rocket);
            }

            if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.doomAbilityCost[30]);
            setStackCooldown(par1ItemStack, 5);
        }

        return par1ItemStack;
    }

}
