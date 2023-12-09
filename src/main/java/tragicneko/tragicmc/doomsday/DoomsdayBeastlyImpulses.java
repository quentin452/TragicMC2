package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayBeastlyImpulses extends Doomsday {

    public DoomsdayBeastlyImpulses(int id) {
        super(id, EnumDoomType.CRISIS);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        int d0 = 12;
        d0 /= this.getCrisis(player);
        if (crucMoment) d0 *= 2;

        player.addPotionEffect(new PotionEffect(Potion.resistance.id, d0, 1));
        player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, d0, 2));
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
        player.addPotionEffect(new PotionEffect(Potion.hunger.id, 120, 1));
    }
}
