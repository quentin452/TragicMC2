package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayParasite extends Doomsday implements IExtendedDoomsday {

    public DoomsdayParasite(int id) {
        super(id);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

        double d0 = 12.0;
        List list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

        if (list.size() > 0) {
            if (crucMoment) addCrucialMessage(player);

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof EntityLivingBase) {
                    EntityLivingBase entity = (EntityLivingBase) list.get(i);
                    if (entity instanceof EntityPlayer && !TragicConfig.allowPvP) continue;
                    entity.attackEntityFrom(DamageSource.magic, crucMoment ? 5.0F : 1.0F);
                    player.heal(crucMoment ? 5.0F : 1.0F);
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
        return Doomsday.Fear;
    }

    @Override
    public int getWaitTime() {
        return 20;
    }

    @Override
    public int getMaxIterations() {
        return 30;
    }
}
