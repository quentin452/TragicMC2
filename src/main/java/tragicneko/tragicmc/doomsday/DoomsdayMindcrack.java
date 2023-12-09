package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayMindcrack extends Doomsday implements IExtendedDoomsday {

    public DoomsdayMindcrack(int id) {
        super(id, EnumDoomType.COMBINATION);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        double d0 = 12.0;
        List<Entity> list = player.worldObj
            .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof EntityCreature) {
                    EntityCreature entity = (EntityCreature) list.get(i);
                    if (entity.getMaxHealth() >= 50.0F) continue;
                    entity.addPotionEffect(new PotionEffect(Potion.wither.id, 2400, 4));
                    entity.addPotionEffect(new PotionEffect(Potion.blindness.id, 2400, 10));
                    if (TragicConfig.allowStun) entity.addPotionEffect(new PotionEffect(TragicPotion.Stun.id, 240));
                    entity.tasks.taskEntries.clear();
                    entity.targetTasks.taskEntries.clear();
                    entity.motionX = 0.0;
                    entity.motionY = 0.0;
                    entity.motionZ = 0.0;
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
    public int getWaitTime() {
        return 3;
    }

    @Override
    public int getMaxIterations() {
        return 300;
    }

}
