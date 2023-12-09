package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class DoomsdayMarionette extends Doomsday implements IExtendedDoomsday {

    public DoomsdayMarionette(int id) {
        super(id, EnumDoomType.OVERFLOW);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        if (effect.utilityEntity != null && !effect.utilityEntity.isDead
            && effect.utilityEntity instanceof EntityLivingBase) {
            Vec3 vec = WorldHelper.getVecFromEntity(player, 5.5D);

            if (vec != null) {
                ((EntityLivingBase) effect.utilityEntity).setPositionAndUpdate(vec.xCoord, vec.yCoord, vec.zCoord);
                if (rand.nextInt(8) == 0 || crucMoment) effect.utilityEntity
                    .attackEntityFrom(DamageSource.causePlayerDamage(player), crucMoment ? 5.0F : 3.0F);
            }

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
    public Doomsday getCombination() {
        return Doomsday.Torment;
    }

    @Override
    public int getWaitTime() {
        return 1;
    }

    @Override
    public int getMaxIterations() {
        return 100;
    }
}
