package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayLifeShare extends Doomsday {

    public DoomsdayLifeShare(int id) {
        super(id);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

        List<EntityLivingBase> list = player.worldObj
            .getEntitiesWithinAABB(EntityLivingBase.class, player.boundingBox.expand(16.0, 16.0, 16.0));
        EntityLivingBase e;
        float total = 0F;

        for (int i = 0; i < list.size(); i++) {
            e = list.get(i);
            total += e.getHealth() / e.getMaxHealth();
        }

        total /= list.size();
        if (total < 0.1F) total = 0.1F;

        for (int i = 0; i < list.size(); i++) {
            e = list.get(i);

            if (e == player && crucMoment)
                player.setHealth(player.getMaxHealth() * total + (player.getMaxHealth() / 2));
            else e.setHealth(total * e.getMaxHealth());
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

    }

}
