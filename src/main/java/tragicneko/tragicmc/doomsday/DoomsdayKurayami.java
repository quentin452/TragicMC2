package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MathHelper;

import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.EntityKurayami;
import tragicneko.tragicmc.entity.boss.EntityKitsune;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayKurayami extends Doomsday {

    public DoomsdayKurayami(int id) {
        super(id, EnumDoomType.OVERFLOW);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        double d = (double) this.getScaledDoomRequirement(doom);
        double d0 = MathHelper
            .clamp_double(((double) doom.getCurrentDoom() - d) / ((double) doom.getMaxDoom() - d), 0.1, 1.0);
        EntityKurayami kurayami = new EntityKurayami(player.worldObj);
        kurayami.setPosition(player.posX, player.posY, player.posZ);
        kurayami.setOwner(player);
        kurayami.setKurayamiLevel(d0);
        player.worldObj.spawnEntityInWorld(kurayami);

        if (TragicConfig.allowAchievements && TragicConfig.allowKitsunakuma) {
            List<EntityKitsune> list = player.worldObj
                .getEntitiesWithinAABB(EntityKitsune.class, player.boundingBox.expand(32.0, 32.0, 32.0));
            if (!list.isEmpty()) {
                if (player instanceof EntityPlayerMP) player.triggerAchievement(TragicAchievements.kurayami);
            }
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

    }

}
