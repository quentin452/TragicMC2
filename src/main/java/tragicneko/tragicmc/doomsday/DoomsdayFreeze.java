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

public class DoomsdayFreeze extends Doomsday {

    public DoomsdayFreeze(int id) {
        super(id);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

        double d0 = crucMoment ? 8.0 : 12.0D;
        List<Entity> list = player.worldObj
            .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof EntityLivingBase) {
                    EntityLivingBase entity = (EntityLivingBase) list.get(i);
                    if (entity instanceof EntityPlayer && !TragicConfig.allowPvP) continue;
                    entity.motionX = 0;
                    entity.motionY = 0;
                    entity.motionZ = 0;
                    entity.velocityChanged = false;
                    entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, rand.nextInt(120) + 120));
                    if (crucMoment && TragicConfig.allowStun) entity.addPotionEffect(
                        new PotionEffect(TragicPotion.Stun.id, rand.nextInt(240) + 120, rand.nextInt(6)));
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
        return Doomsday.Blizzard;
    }
}
