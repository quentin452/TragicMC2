package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdaySkullCrusher extends Doomsday {

    public DoomsdaySkullCrusher(int id) {
        super(id, EnumDoomType.CRISIS);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        double d0 = 2.0;
        float crisis = this.getCrisis(player);
        if (crisis <= 0.5F) d0 = 4.0;
        if (crisis <= 0.25F) d0 = 6.0;
        if (crucMoment) d0 *= 2;

        List<Entity> list = player.worldObj
            .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

        for (int x = 0; x < list.size(); x++) {
            if (list.get(x) instanceof EntityLivingBase) {
                EntityLivingBase entity = (EntityLivingBase) list.get(x);
                if (entity instanceof EntityPlayer && !TragicConfig.allowPvP) continue;

                entity.applyEntityCollision(player);
                entity.motionX *= 1.25D * d0;
                entity.motionZ *= 1.25D * d0;
                entity.motionY += 0.4D + (d0 * 0.25D);

                entity.addPotionEffect(new PotionEffect(Potion.wither.id, 600, 4));
                if (TragicConfig.allowStun) entity.addPotionEffect(new PotionEffect(TragicPotion.Stun.id, 120));
            }
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
        player.addPotionEffect(new PotionEffect(Potion.wither.id, 300, 1));
    }

}
