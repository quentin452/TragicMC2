package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayTimeCollapse extends Doomsday implements IExtendedDoomsday {

    public DoomsdayTimeCollapse(int id) {
        super(id);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        double d0 = crucMoment ? 25.0 : 18.0;
        List<Entity> list = player.worldObj
            .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

        if (list.size() > 0) {
            if (crucMoment) addCrucialMessage(player);

            for (int i = 0; i < list.size(); i++) {
                Entity entity = list.get(i);
                if (entity instanceof EntityPlayer && !TragicConfig.allowPvP) continue;
                entity.motionX = entity.motionY = entity.motionZ = 0;
                entity.velocityChanged = false;
                if (crucMoment && TragicConfig.allowStun && entity instanceof EntityLivingBase)
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(TragicPotion.Stun.id, 120, 0));
            }
        } else {
            addNoEntityMessage(player);
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
        player.addPotionEffect(new PotionEffect(Potion.confusion.id, 300, 0));
    }

    @Override
    public int getWaitTime() {
        return 1;
    }

    @Override
    public int getMaxIterations() {
        return 500;
    }

}
