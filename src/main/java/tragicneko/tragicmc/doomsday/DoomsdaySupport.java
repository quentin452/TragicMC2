package tragicneko.tragicmc.doomsday;

import java.util.Collections;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdaySupport extends Doomsday {

    public DoomsdaySupport(int id) {
        super(id, EnumDoomType.OVERFLOW);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

        double d0 = crucMoment ? 32.0 : 16.0;
        int amt = this.getScaledDoomRequirement(doom);
        if (crucMoment) amt *= 1.5;

        List<EntityPlayer> list = player.worldObj
            .getEntitiesWithinAABB(EntityPlayer.class, player.boundingBox.expand(d0, d0, d0));

        if (list.size() > 1) {
            while (amt > 0) {
                Collections.shuffle(list);
                EntityPlayer play = list.get(rand.nextInt(list.size()));
                if (play != player && play instanceof EntityPlayerMP) {
                    int j = rand.nextInt(amt);
                    PropertyDoom doom2 = PropertyDoom.get(play);
                    if (doom2 != null) {
                        doom2.increaseDoom(j);
                        amt -= j;
                    }
                }
            }
        } else {
            addNoEntityMessage(player);
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

    }

    @Override
    public Doomsday getCombination() {
        return Doomsday.Resurge;
    }
}
