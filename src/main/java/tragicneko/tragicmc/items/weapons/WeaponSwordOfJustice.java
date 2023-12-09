package tragicneko.tragicmc.items.weapons;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicMC;

import java.util.List;

public class WeaponSwordOfJustice extends ItemSword {

    public WeaponSwordOfJustice(ToolMaterial p_i45356_1_) {
        super(p_i45356_1_);
        this.setMaxDamage(1);
        this.setCreativeTab(TragicMC.Creative);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase entity2) {
        if (entity instanceof EntityPlayer && !TragicConfig.allowPvP) return false;
        if (entity2 instanceof EntityPlayer && !entity.worldObj.isRemote)
            entity.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) entity2), Float.MAX_VALUE);
        if (entity2 instanceof EntityPlayer && !entity.worldObj.isRemote
            && !((EntityPlayer) entity2).capabilities.isCreativeMode) stack.stackSize--;
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.epic;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack, int passes) {
        return true;
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag) {
        if (world.isRemote) return;

        if (!stack.isItemEnchanted()) {
            if (TragicConfig.allowReach) stack.addEnchantment(TragicEnchantments.Reach, 10);
            stack.addEnchantment(Enchantment.looting, 10);
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par2World.isRemote) return par1ItemStack;

        if (par3EntityPlayer.capabilities.isCreativeMode && par3EntityPlayer.canCommandSenderUseCommand(2, "")) {
            List<Entity> list = par2World.getEntitiesWithinAABBExcludingEntity(
                par3EntityPlayer,
                par3EntityPlayer.boundingBox.expand(128.0, 128.0, 128.0));
            int count = 0;
            for (Entity e : list) {
                if (e instanceof EntityLivingBase && !e.isDead && ((EntityLivingBase) e).getHealth() > 0F) {
                    if (e instanceof EntityPlayer && !TragicConfig.allowPvP) continue;
                    if (!e.attackEntityFrom(DamageSource.outOfWorld, Float.MAX_VALUE)) e.setDead();
                    count++;
                }
            }
            if (count > 0) par3EntityPlayer.addChatMessage(
                new ChatComponentText(
                    par3EntityPlayer.getCommandSenderName() + " killed all nearby entities (" + count + " entities)!"));
        }

        return par1ItemStack;
    }
}
