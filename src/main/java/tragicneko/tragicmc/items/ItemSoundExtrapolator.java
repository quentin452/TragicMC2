package tragicneko.tragicmc.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.boss.EntityPart;

import java.lang.reflect.Method;
import java.util.List;

public class ItemSoundExtrapolator extends Item {

    public ItemSoundExtrapolator() {
        this.setMaxDamage(256);
        this.setMaxStackSize(1);
        this.setCreativeTab(TragicMC.Creative);
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {
        par2List.add(EnumChatFormatting.DARK_RED + "Extrapolate sounds from living creatures!");
        par2List.add("Hit a mob to extrapolate the sound from it.");
        par2List.add("R-Click to play a sound from it!");
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (!stack.hasTagCompound()) stack.stackTagCompound = new NBTTagCompound();
        if (entity != null && entity instanceof EntityPart) {
            entity = (Entity) ((EntityPart) entity).main;
        }

        if (entity != null && entity instanceof EntityLiving && !entity.worldObj.isRemote) {
            stack.stackTagCompound.setString(
                "extrapolatedEntity",
                entity.getClass()
                    .getCanonicalName());
            player.addChatMessage(new ChatComponentText("Sound extrapolated from " + entity.getCommandSenderName()));
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par1ItemStack.hasTagCompound() && par1ItemStack.stackTagCompound.hasKey("extrapolatedEntity")) {
            Class oclass;
            String sound = null;
            float pitch = 1F;
            float volume = 1F;
            try {
                oclass = Class.forName(par1ItemStack.stackTagCompound.getString("extrapolatedEntity"));
                EntityLiving entity = (EntityLiving) oclass.getConstructor(new Class[] { World.class })
                    .newInstance(new Object[] { par2World });

                Method m = EntityLiving.class.getDeclaredMethod("getLivingSound", (Class<?>[]) null);
                m.setAccessible(true);
                sound = (String) m.invoke(entity, (Object[]) null);
                if (sound == null || sound.isEmpty()) return par1ItemStack;
                m = EntityLivingBase.class.getDeclaredMethod("getSoundPitch", (Class<?>[]) null);
                m.setAccessible(true);
                pitch = (Float) m.invoke(entity, (Object[]) null);
                m = EntityLivingBase.class.getDeclaredMethod("getSoundVolume", (Class<?>[]) null);
                m.setAccessible(true);
                volume = (Float) m.invoke(entity, (Object[]) null);
                if (par2World.isRemote) par3EntityPlayer.playSound(sound, volume, pitch);

                entity = null;

                if (!par3EntityPlayer.capabilities.isCreativeMode) par1ItemStack.damageItem(1, par3EntityPlayer);
            } catch (Exception e) {
                TragicMC.logError("Error caught from Sound Extrapolator", e);
                return par1ItemStack;
            }

        }
        return par1ItemStack;
    }
}
