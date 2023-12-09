package tragicneko.tragicmc.items.amulet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.events.AmuletEvents;
import tragicneko.tragicmc.properties.PropertyAmulets;
import tragicneko.tragicmc.properties.PropertyDoom;

public class AmuletChicken extends ItemAmulet {

    public AmuletChicken() {
        super("Chicken", EnumAmuletType.NORMAL, 0xDEDEDE, 0xFFEAA1);
    }

    @Override
    public void onAmuletUpdate(final PropertyAmulets amu, final EntityPlayer player, final World world, final byte slot,
        final byte level) {
        if (TragicConfig.amuChicken && player.isPlayerFullyAsleep()) {
            PropertyDoom doom = PropertyDoom.get(player);

            if (level == 1) {
                player.heal(player.getMaxHealth() / 4);
                if (doom != null) doom.increaseDoom(doom.getMaxDoom() / 4);
            } else if (level == 2) {
                player.heal(player.getMaxHealth() / 2);
                if (doom != null) doom.increaseDoom(doom.getMaxDoom() / 2);
            } else {
                player.heal(player.getMaxHealth() * 3 / 4);
                if (doom != null) doom.increaseDoom(doom.getMaxDoom() * 3 / 4);

                for (int i = 0; i < Potion.potionTypes.length; i++) {
                    if (Potion.potionTypes[i] != null) {
                        Potion potion = Potion.potionTypes[i];

                        if (player.isPotionActive(potion) && AmuletEvents.badPotions.contains(potion)) {
                            player.removePotionEffect(i);
                        }
                    }
                }
            }

            if (!world.isRemote) this.damageAmulet(amu, slot, level);
        }
    }
}
