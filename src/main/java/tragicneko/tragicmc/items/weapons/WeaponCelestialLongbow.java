package tragicneko.tragicmc.items.weapons;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class WeaponCelestialLongbow extends TragicBow {

    private static final String[] bowPullIconName = new String[] { "pulling", "pulling1", "pulling2" };

    public WeaponCelestialLongbow() {
        super(1348, Doomsday.Snipe);
        this.setCreativeTab(TragicMC.Survival);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        if (usingItem == null) return itemIcon;

        int ticksInUse = stack.getMaxItemUseDuration() - useRemaining;

        if (ticksInUse > 48) {
            return iconArray[2];
        } else if (ticksInUse > 24) {
            return iconArray[1];
        } else if (ticksInUse > 6) {
            return iconArray[0];
        } else {
            return itemIcon;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon(this.getIconString() + "_standby");
        this.iconArray = new IIcon[bowPullIconName.length];

        for (int i = 0; i < this.iconArray.length; ++i) {
            this.iconArray[i] = par1IconRegister.registerIcon(this.getIconString() + "_" + bowPullIconName[i]);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getItemIconForUseDuration(int par1) {
        return this.iconArray[par1];
    }

    @Override
    public int getItemEnchantability() {
        return 20;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

        if (!par3EntityPlayer.isSneaking() || !TragicWeapon.canUseAbility(doom, TragicConfig.doomAbilityCost[5])) {
            ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
            MinecraftForge.EVENT_BUS.post(event);
            if (event.isCanceled()) return event.result;

            if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(Items.arrow)) {
                par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
            }
        } else {
            if (TragicWeapon.getStackCooldown(par1ItemStack) == 0 && TragicConfig.doomAbility[8]) {
                float f = 1.0F;
                float f1 = par3EntityPlayer.prevRotationPitch
                    + (par3EntityPlayer.rotationPitch - par3EntityPlayer.prevRotationPitch) * f;
                float f2 = par3EntityPlayer.prevRotationYaw
                    + (par3EntityPlayer.rotationYaw - par3EntityPlayer.prevRotationYaw) * f;
                double d0 = par3EntityPlayer.prevPosX + (par3EntityPlayer.posX - par3EntityPlayer.prevPosX) * f;
                double d1 = par3EntityPlayer.prevPosY + (par3EntityPlayer.posY - par3EntityPlayer.prevPosY) * f
                    + (par3EntityPlayer.worldObj.isRemote
                        ? par3EntityPlayer.getEyeHeight() - par3EntityPlayer.getDefaultEyeHeight()
                        : par3EntityPlayer.getEyeHeight()); // isRemote check to revert changes to ray trace position
                                                            // due to adding the eye height clientside and player
                                                            // yOffset differences
                double d2 = par3EntityPlayer.prevPosZ + (par3EntityPlayer.posZ - par3EntityPlayer.prevPosZ) * f;
                Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
                float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
                float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
                float f5 = -MathHelper.cos(-f1 * 0.017453292F);
                float f6 = MathHelper.sin(-f1 * 0.017453292F);
                float f7 = f4 * f5;
                float f8 = f3 * f5;
                double d3 = 50.0D;

                if (par3EntityPlayer instanceof EntityPlayerMP) {
                    d3 = ((EntityPlayerMP) par3EntityPlayer).theItemInWorldManager.getBlockReachDistance() + 46.0;
                }

                Vec3 vec31 = vec3.addVector(f7 * d3, f6 * d3, f8 * d3);
                MovingObjectPosition mop = par3EntityPlayer.worldObj.func_147447_a(vec3, vec31, true, false, true);

                if (mop == null) {
                    if (!par2World.isRemote)
                        par3EntityPlayer.addChatMessage(new ChatComponentText("Out of range to teleport to!"));
                    return par1ItemStack;
                }

                if (mop.typeOfHit == MovingObjectType.BLOCK && par3EntityPlayer instanceof EntityPlayerMP
                    && ((EntityPlayerMP) par3EntityPlayer).playerNetServerHandler.func_147362_b()
                        .isChannelOpen()) {
                    if (par3EntityPlayer.isRiding()) par3EntityPlayer.mountEntity((Entity) null);

                    double d4 = WorldHelper.getXPositionFromSide(mop.sideHit, mop.hitVec.xCoord);
                    double d5 = WorldHelper.getYPositionFromSide(mop.sideHit, mop.hitVec.yCoord);
                    double d6 = WorldHelper.getZPositionFromSide(mop.sideHit, mop.hitVec.zCoord);

                    par3EntityPlayer.setPositionAndUpdate(d4, d5, d6);
                    par3EntityPlayer.fallDistance = 0.0F;
                    if (!par3EntityPlayer.capabilities.isCreativeMode)
                        doom.increaseDoom(-TragicConfig.doomAbilityCost[8]);
                    TragicWeapon.setStackCooldown(par1ItemStack, 5);
                }
            }

        }
        return par1ItemStack;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer,
        int par4) {
        int j = this.getMaxItemUseDuration(par1ItemStack) - par4;

        ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, j);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled()) return;
        j = event.charge;

        boolean flag = par3EntityPlayer.capabilities.isCreativeMode
            || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

        if (flag || par3EntityPlayer.inventory.hasItem(Items.arrow)) {
            float f = j / 40.0F;
            f = (f * f + f * 2.0F) / 3.0F;
            f *= 0.75F;

            if (f < 0.2D) return;
            if (f > 1.0F) f = 1.0F;

            EntityArrow arrow = new EntityArrow(par2World, par3EntityPlayer, f);
            arrow.setDamage(arrow.getDamage() + 2.0);
            arrow.motionX *= 1.6;
            arrow.motionZ *= 1.6;
            arrow.motionY *= 1.15;
            if (f >= 1.0F) arrow.setIsCritical(true);

            int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);
            if (k > 0) arrow.setDamage(arrow.getDamage() + k * 0.5D + 0.5D);

            k = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);
            if (k > 0) arrow.setKnockbackStrength(k);

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0)
                arrow.setFire(100);

            par1ItemStack.damageItem(1, par3EntityPlayer);
            par2World.playSoundAtEntity(
                par3EntityPlayer,
                "random.bow",
                1.0F,
                1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            if (flag) arrow.canBePickedUp = 2;
            else par3EntityPlayer.inventory.consumeInventoryItem(Items.arrow);

            if (!par2World.isRemote) par2World.spawnEntityInWorld(arrow);
        }
    }
}
