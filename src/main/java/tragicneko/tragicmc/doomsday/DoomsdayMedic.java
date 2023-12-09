package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayMedic extends Doomsday implements IExtendedDoomsday {

    public DoomsdayMedic(int id) {
        super(id);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        List<Entity> list = player.worldObj
            .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(32.0, 32.0, 32.0));

        for (Entity e : list) {
            if (e instanceof EntityPlayer && rand.nextBoolean()) {
                ((EntityPlayer) e).heal(1.0F);

                if (rand.nextInt(4) == 0) {
                    PropertyDoom doom2 = PropertyDoom.get((EntityPlayer) e);
                    if (doom2 != null) {
                        doom2.increaseDoom(1 + rand.nextInt(2));
                    }
                }
            }
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

    }

    @Override
    public Doomsday getCombination() {
        return Doomsday.Support;
    }

    @Override
    public int getWaitTime() {
        return 10;
    }

    @Override
    public int getMaxIterations() {
        return 60;
    }
}
