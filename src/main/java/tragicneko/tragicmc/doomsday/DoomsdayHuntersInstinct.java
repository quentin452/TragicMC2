package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayHuntersInstinct extends Doomsday {

    public DoomsdayHuntersInstinct(int id) {
        super(id);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        int i = crucMoment ? 800 : 400;
        player.addPotionEffect(new PotionEffect(Potion.jump.id, i, 1));
        player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, i, 2));
        player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, i, 0));
        if (crucMoment) player.addPotionEffect(new PotionEffect(Potion.regeneration.id, i, 1));
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {}
}
