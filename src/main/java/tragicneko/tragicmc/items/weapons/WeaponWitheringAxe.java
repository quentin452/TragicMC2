package tragicneko.tragicmc.items.weapons;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class WeaponWitheringAxe extends TragicWeapon {

    public WeaponWitheringAxe(ToolMaterial material, Doomsday dday) {
        super(material, dday);
        this.setHarvestLevel("axe", 3);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        PropertyDoom doom = PropertyDoom.get(player);

        if (!super.onLeftClickEntity(stack, player, entity) && entity instanceof EntityLivingBase
            && itemRand.nextInt(4) == 0
            && canUseAbility(doom, TragicConfig.doomAbilityCost[34])
            && getStackCooldown(stack) == 0
            && TragicConfig.doomAbility[34]) {
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.wither.id, 60, itemRand.nextInt(4)));
            if (!player.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.doomAbilityCost[34]);
            setStackCooldown(stack, 5);
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

        Vec3 vec = WorldHelper.getVecFromEntity(par3EntityPlayer);
        if (vec == null) return par1ItemStack;

        if (doom != null && !par2World.isRemote && getStackCooldown(par1ItemStack) == 0) {
            double d4 = vec.xCoord - par3EntityPlayer.posX;
            double d5 = vec.yCoord - (par3EntityPlayer.posY + par3EntityPlayer.getEyeHeight());
            double d6 = vec.zCoord - par3EntityPlayer.posZ;

            if (!par3EntityPlayer.isSneaking()) {
                if (canUseAbility(doom, TragicConfig.doomAbilityCost[35]) && TragicConfig.doomAbility[35]) {
                    if (!par3EntityPlayer.capabilities.isCreativeMode)
                        doom.increaseDoom(-TragicConfig.doomAbilityCost[35]);
                    setStackCooldown(par1ItemStack, 5);

                    EntityWitherSkull skull = new EntityWitherSkull(par2World, par3EntityPlayer, d4, d5, d6);
                    skull.posY += par3EntityPlayer.getEyeHeight();
                    par2World.spawnEntityInWorld(skull);
                    return par1ItemStack;
                }
            } else {
                if (canUseAbility(doom, TragicConfig.doomAbilityCost[36]) && TragicConfig.doomAbility[36]) {
                    if (!par3EntityPlayer.capabilities.isCreativeMode)
                        doom.increaseDoom(-TragicConfig.doomAbilityCost[36]);
                    setStackCooldown(par1ItemStack, 5);

                    EntityWitherSkull skull = new EntityWitherSkull(par2World, par3EntityPlayer, d4, d5, d6);
                    skull.posY += par3EntityPlayer.getEyeHeight();
                    skull.setInvulnerable(true);
                    par2World.spawnEntityInWorld(skull);
                    return par1ItemStack;
                }
            }
        }

        return par1ItemStack;
    }

    @Override
    public float func_150893_a(ItemStack stack, Block block) {
        Material material = block.getMaterial();
        return material == Material.wood || material == Material.gourd ? this.material.getEfficiencyOnProperMaterial()
            : super.func_150893_a(stack, block);
    }
}
