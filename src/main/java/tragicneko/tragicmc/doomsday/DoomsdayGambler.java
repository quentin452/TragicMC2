package tragicneko.tragicmc.doomsday;

import static tragicneko.tragicmc.events.AmuletEvents.badPotions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayGambler extends Doomsday {

    public DoomsdayGambler(int id) {
        super(id);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        Potion potion = Potion.potionTypes[rand.nextInt(Potion.potionTypes.length)];

        while (potion == null) {
            potion = Potion.potionTypes[rand.nextInt(Potion.potionTypes.length)];
            if (crucMoment && badPotions.contains(potion)) potion = null;
        }

        int dur = rand.nextInt(1400);
        int amp = rand.nextInt(8);

        player.addPotionEffect(new PotionEffect(potion.id, dur, amp));
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
        Potion potion = Potion.potionTypes[rand.nextInt(Potion.potionTypes.length)];

        while (potion == null) {
            potion = Potion.potionTypes[rand.nextInt(Potion.potionTypes.length)];
            if (!badPotions.contains(potion)) potion = null;
        }

        int dur = rand.nextInt(1400);
        int amp = rand.nextInt(8);

        player.addPotionEffect(new PotionEffect(potion.id, dur, amp));
    }

}
