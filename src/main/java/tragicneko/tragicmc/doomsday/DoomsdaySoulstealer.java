package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdaySoulstealer extends Doomsday implements IExtendedDoomsday {

    public DoomsdaySoulstealer(int id) {
        super(id);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        if (effect.utilityEntity != null && !effect.utilityEntity.isDead
            && effect.utilityEntity instanceof EntityLivingBase) {
            effect.utilityEntity.attackEntityFrom(DamageSource.magic, crucMoment ? 5.0F : 1.0F);
            player.heal(crucMoment ? 5.0F : 1.0F);
            if (crucMoment) addCrucialMessage(player);
        } else {
            List<Entity> list = player.worldObj
                .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(6.0D, 6.0D, 6.0D));
            EntityLivingBase entity = null;

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof EntityLivingBase) {
                    if (list.get(i) instanceof EntityPlayer && !TragicConfig.allowPvP) continue;
                    if (entity != null) {
                        if (player.getDistanceToEntity(entity) > player.getDistanceToEntity(list.get(i)))
                            entity = (EntityLivingBase) list.get(i);
                    } else entity = (EntityLivingBase) list.get(i);
                }
            }
            effect.utilityEntity = entity;
            if (entity == null) addNoEntityMessage(player);
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

    }

    @Override
    public int getWaitTime() {
        return 10;
    }

    @Override
    public int getMaxIterations() {
        return 40;
    }

}
