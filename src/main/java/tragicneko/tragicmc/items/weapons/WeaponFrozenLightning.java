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
import tragicneko.tragicmc.entity.EntityDirectedLightning;
import tragicneko.tragicmc.entity.projectile.EntityIcicle;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class WeaponFrozenLightning extends TragicWeapon {

    public WeaponFrozenLightning(ToolMaterial material, Doomsday dday) {
        super(material, dday);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        PropertyDoom doom = PropertyDoom.get(player);

        if (!super.onLeftClickEntity(stack, player, entity) && entity instanceof EntityLivingBase
            && itemRand.nextInt(4) == 0
            && canUseAbility(doom, TragicConfig.doomAbilityCost[12])
            && getStackCooldown(stack) == 0
            && TragicConfig.doomAbility[12]) {
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 1));
            if (!player.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.doomAbilityCost[12]);
            setStackCooldown(stack, 5);
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

        if (doom != null && !par2World.isRemote) {
            Vec3 vec = WorldHelper.getVecFromEntity(par3EntityPlayer, 30.0);
            if (vec == null) return par1ItemStack;

            double d4 = vec.xCoord;
            double d5 = vec.yCoord;
            double d6 = vec.zCoord;

            if (par3EntityPlayer.isSneaking()) {
                if (canUseAbility(doom, TragicConfig.doomAbilityCost[13]) && getStackCooldown(par1ItemStack) == 0
                    && TragicConfig.doomAbility[13]) {
                    for (int i = 0; i < 3; i++) {
                        par3EntityPlayer.worldObj.spawnEntityInWorld(
                            new EntityDirectedLightning(
                                par3EntityPlayer.worldObj,
                                d4 + itemRand.nextDouble() - itemRand.nextDouble(),
                                d5,
                                d6 + itemRand.nextDouble() - itemRand.nextDouble(),
                                par3EntityPlayer));
                    }

                    par3EntityPlayer.worldObj.createExplosion(
                        par3EntityPlayer,
                        d4,
                        d5,
                        d6,
                        itemRand.nextFloat() * 2.0F,
                        TragicConfig.griefConfig[2]);
                    if (!par3EntityPlayer.capabilities.isCreativeMode)
                        doom.increaseDoom(-TragicConfig.doomAbilityCost[13]);
                    setStackCooldown(par1ItemStack, 5);
                }
            } else {
                if (canUseAbility(doom, TragicConfig.doomAbilityCost[14]) && getStackCooldown(par1ItemStack) == 0
                    && TragicConfig.doomAbility[14]) {
                    for (int i = 0; i < 5; i++) {
                        d4 = vec.xCoord - par3EntityPlayer.posX;
                        d5 = vec.yCoord - (par3EntityPlayer.posY + par3EntityPlayer.getEyeHeight());
                        d6 = vec.zCoord - par3EntityPlayer.posZ;

                        EntityIcicle rocket = new EntityIcicle(
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
                        doom.increaseDoom(-TragicConfig.doomAbilityCost[14]);
                    setStackCooldown(par1ItemStack, 5);
                    return par1ItemStack;
                }
            }
        }
        return par1ItemStack;
    }

}
