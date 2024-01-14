package tragicneko.tragicmc.events;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.Clone;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.network.MessageDoom;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomEvents {

    @SubscribeEvent
    public void onEntityConstructing(EntityConstructing event) {
        if (event.entity instanceof EntityPlayer) {
            PropertyDoom doom = PropertyDoom.get((EntityPlayer) event.entity);

            if (doom == null) {
                PropertyDoom.register((EntityPlayer) event.entity);
            } else {
                doom.loadNBTData(new NBTTagCompound());
            }

            if (event.entity instanceof EntityPlayerMP && doom != null) {
                TragicMC.net.sendTo(new MessageDoom((EntityPlayer) event.entity), (EntityPlayerMP) event.entity);
            }
        }
    }

    @SubscribeEvent
    public void onEntityUpdate(LivingUpdateEvent event) {
        if (event.entityLiving instanceof EntityPlayer && !event.entity.worldObj.isRemote) {
            PropertyDoom doom = PropertyDoom.get((EntityPlayer) event.entityLiving);

            if (doom != null) {
                doom.onUpdate();
                sendDoomUpdateMessage((EntityPlayer) event.entityLiving);
            }
        }
    }

    private void sendDoomUpdateMessage(EntityPlayer player) {
        if (player instanceof EntityPlayerMP) {
            TragicMC.net.sendTo(new MessageDoom(player), (EntityPlayerMP) player);
        }
    }

    @SubscribeEvent
    public void onLivingDeathEvent(PlayerEvent.Clone event) {
        if (!event.entity.worldObj.isRemote && TragicConfig.allowDoom && (PropertyDoom.get(event.original) != null)) {
                NBTTagCompound tag = new NBTTagCompound();
                PropertyDoom.get(event.original)
                    .saveNBTData(tag);
                PropertyDoom.get(event.entityPlayer)
                    .loadNBTData(tag);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onAttack(LivingHurtEvent event) {
        if (event.entityLiving instanceof EntityPlayer && TragicConfig.allowDoomPainRecharge && (!event.entityLiving.worldObj.isRemote)) {
                PropertyDoom properties = PropertyDoom.get((EntityPlayer) event.entityLiving);
                properties.applyDoomPainRecharge(event.ammount);
        }

        if (event.entityLiving instanceof EntityMob && TragicConfig.allowDoomPainRecharge && (event.source.getEntity() instanceof EntityLivingBase
                && event.source.getEntity() instanceof EntityPlayer)) {
                EntityPlayer player = (EntityPlayer) event.source.getEntity();

                PropertyDoom properties = PropertyDoom.get(player);
                properties.applyDoomPainRecharge(event.ammount);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onDeath(Clone event) {
        if (event.entityLiving instanceof EntityPlayerMP && !event.wasDeath) {
            EntityPlayerMP player = (EntityPlayerMP) event.entityLiving;
            TragicMC.net.sendTo(new MessageDoom(player), player);
        }
    }

    @SubscribeEvent
    public void onDeath(LivingDeathEvent event) {
        if (event.entityLiving instanceof EntityMob && TragicConfig.allowDoomKillRecharge && (event.source.getEntity() instanceof EntityLivingBase
                && event.source.getEntity() instanceof EntityPlayer)) {
                EntityPlayer player = (EntityPlayer) event.source.getEntity();

                PropertyDoom properties = PropertyDoom.get(player);
                properties.increaseDoom(TragicConfig.doomRechargeAmount);
        }
    }
}
