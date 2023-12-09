package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayBloodlust extends Doomsday {

    public DoomsdayBloodlust(int id) {
        super(id);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

        int a = 2;

        if (crucMoment) {
            a *= 2;
        }

        player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 240 * a, a * 2));
        player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 240 * a, a * 2));
        player.addPotionEffect(new PotionEffect(Potion.jump.id, 240 * a, a * 2));
        player.addPotionEffect(new PotionEffect(Potion.resistance.id, 240 * a, a * 2));
        player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 240 * a, a * 2));

        if (!crucMoment) {
            a = 1;
            player.addPotionEffect(new PotionEffect(Potion.confusion.id, 160 * a, 0));
            if (TragicConfig.allowDisorientation)
                player.addPotionEffect(new PotionEffect(TragicPotion.Disorientation.id, 160 * a, a));
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

        player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 60, 0));
        player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 60, 0));
        player.addPotionEffect(new PotionEffect(Potion.jump.id, 60, 0));
        player.addPotionEffect(new PotionEffect(Potion.resistance.id, 60, 0));
        player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 60, 0));

        player.addPotionEffect(new PotionEffect(Potion.confusion.id, 640, 0));
        if (TragicConfig.allowDisorientation)
            player.addPotionEffect(new PotionEffect(TragicPotion.Disorientation.id, 640, 1));
    }

}
