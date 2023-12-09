package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;

import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayPurify extends Doomsday {

    public DoomsdayPurify(int id) {
        super(id);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        double d0 = crucMoment ? 48.0 : 24.0;
        List<EntityLivingBase> list = player.worldObj
            .getEntitiesWithinAABB(EntityLivingBase.class, player.boundingBox.expand(d0, d0, d0));

        for (EntityLivingBase e : list) {
            for (int i = 0; i < Potion.potionTypes.length; i++) {
                if (e.isPotionActive(i)) e.removePotionEffect(i);
            }
            if (e.isBurning()) e.extinguish();
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

    }

}
