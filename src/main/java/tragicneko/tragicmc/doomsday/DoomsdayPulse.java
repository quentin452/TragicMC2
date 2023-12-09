package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayPulse extends Doomsday implements IExtendedDoomsday {

    public DoomsdayPulse(int id) {
        super(id, EnumDoomType.OVERFLOW);
    }

    @Override
    public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        super.doInitialEffects(effect, doom, player, crucMoment);

        double d0 = crucMoment ? 16.0 : 8.0; // Do effect on initial so that there isn't an awkward pause at the start
                                             // since it has such a long wait time
        List list = player.worldObj
            .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0 / 2, d0));

        for (int j = 0; j < list.size(); j++) {
            if (list.get(j) instanceof EntityLivingBase && player.canEntityBeSeen((Entity) list.get(j))) {
                if (list.get(j) instanceof EntityPlayer && !TragicConfig.allowPvP) continue;
                if (effect.utilityFlag) {
                    ((Entity) list.get(j)).motionY = 1.0;
                    if (TragicConfig.allowSubmission) ((EntityLivingBase) list.get(j))
                        .addPotionEffect(new PotionEffect(TragicPotion.Submission.id, 120, rand.nextInt(2)));
                } else {
                    ((Entity) list.get(j)).motionY = -1.0;
                }
            }
        }

        effect.utilityFlag = false;
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        double d0 = crucMoment ? 16.0 : 8.0;
        List list = player.worldObj
            .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0 / 2, d0));

        for (int j = 0; j < list.size(); j++) {
            if (list.get(j) instanceof EntityLivingBase && player.canEntityBeSeen((Entity) list.get(j))) {
                if (list.get(j) instanceof EntityPlayer && !TragicConfig.allowPvP) continue;
                if (effect.utilityFlag) {
                    ((Entity) list.get(j)).motionY = 1.0;
                    if (TragicConfig.allowSubmission) ((EntityLivingBase) list.get(j))
                        .addPotionEffect(new PotionEffect(TragicPotion.Submission.id, 120, rand.nextInt(2)));
                } else {
                    ((Entity) list.get(j)).motionY = -1.0;
                }
            }
        }

        effect.utilityFlag = !effect.utilityFlag;
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
        player.motionY += 1.4D;
    }

    @Override
    public int getWaitTime() {
        return 20;
    }

    @Override
    public int getMaxIterations() {
        return 40;
    }

}
