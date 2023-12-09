package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;

import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayDimentia extends Doomsday {

    public DoomsdayDimentia(int id) {
        super(id, EnumDoomType.COMBINATION);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        double d0 = crucMoment ? 48.0 : 24.0;
        List<Entity> list = player.worldObj
            .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));
        Entity prevEnt = null;

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof EntityCreature) {
                    EntityCreature ent = (EntityCreature) list.get(i);
                    ent.getNavigator()
                        .clearPathEntity();
                    ent.setAttackTarget(null);

                    if (rand.nextBoolean() && prevEnt instanceof EntityLivingBase) {
                        ent.targetTasks
                            .addTask(3, new EntityAINearestAttackableTarget(ent, prevEnt.getClass(), 0, true));
                        ent.setAttackTarget((EntityLivingBase) prevEnt);
                    } else {
                        ent.targetTasks
                            .addTask(3, new EntityAINearestAttackableTarget(ent, EntityCreature.class, 0, true));
                    }
                }

                if (rand.nextBoolean()) prevEnt = list.get(i);
            }
        } else {
            addNoEntityMessage(player);
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

    }

}
