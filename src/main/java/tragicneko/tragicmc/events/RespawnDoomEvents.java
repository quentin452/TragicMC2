package tragicneko.tragicmc.events;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.network.MessageDoom;
import tragicneko.tragicmc.properties.PropertyDoom;

public class RespawnDoomEvents {

    @SubscribeEvent
    public void onRespawn(PlayerRespawnEvent event) {
        if (!(event.player instanceof EntityPlayerMP)) return;

        PropertyDoom property = PropertyDoom.get(event.player);
        if (property == null) return;

        if (TragicConfig.allowRespawnPunishment && !event.player.capabilities.isCreativeMode) {
            if (event.player.worldObj.difficultySetting == EnumDifficulty.HARD) {
                property.emptyDoom();
                event.player.addPotionEffect(new PotionEffect(Potion.weakness.id, 1800, 1));

                if (TragicConfig.allowCripple) {
                    event.player.addPotionEffect(new PotionEffect(TragicPotion.Cripple.id, 1800, 5));
                    event.player.setHealth(event.player.getMaxHealth());
                }
            }

            if (event.player.worldObj.difficultySetting == EnumDifficulty.NORMAL) {
                property.increaseDoom(-(property.getCurrentDoom() / 2));
                event.player.addPotionEffect(new PotionEffect(Potion.weakness.id, 1200));

                if (TragicConfig.allowCripple) {
                    event.player.addPotionEffect(new PotionEffect(TragicPotion.Cripple.id, 1200, 3));
                    event.player.setHealth(event.player.getMaxHealth());
                }
            }

            if (event.player.worldObj.difficultySetting == EnumDifficulty.EASY) {
                property.increaseDoom(-(property.getCurrentDoom() / 4));

                if (TragicConfig.allowCripple) {
                    event.player.addPotionEffect(new PotionEffect(TragicPotion.Cripple.id, 600, 1));
                    event.player.setHealth(event.player.getMaxHealth());
                }
            }
        }

        property.setCooldown(0);
        TragicMC.net.sendTo(new MessageDoom(event.player), (EntityPlayerMP) event.player);
    }
}
