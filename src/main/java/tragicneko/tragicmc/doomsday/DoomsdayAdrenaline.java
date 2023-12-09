package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayAdrenaline extends Doomsday {

    public DoomsdayAdrenaline(int id) {
        super(id, EnumDoomType.CRISIS);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        float f = (1 / this.getCrisis(player));
        int i = (int) (f * 20);
        if (crucMoment) i *= 2;
        player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 200 + i, 4));
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

    }
}
