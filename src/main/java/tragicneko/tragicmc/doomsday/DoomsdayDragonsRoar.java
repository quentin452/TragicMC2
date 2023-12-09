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

public class DoomsdayDragonsRoar extends Doomsday {

    public DoomsdayDragonsRoar(int id) {
        super(id);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        if (TragicConfig.allowFlight) {
            player.addPotionEffect(new PotionEffect(TragicPotion.Flight.id, 6000, 0));
        } else {
            player.addPotionEffect(new PotionEffect(Potion.jump.id, 600, 3));
        }

        if (crucMoment) {
            List<Entity> list = player.worldObj
                .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(8.0D, 8.0D, 8.0D));
            EntityLivingBase entity;

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof EntityLivingBase) {
                    entity = (EntityLivingBase) list.get(i);
                    if (entity instanceof EntityPlayer && !TragicConfig.allowPvP) continue;
                    if (TragicConfig.allowStun) entity.addPotionEffect(new PotionEffect(TragicPotion.Stun.id, 120, 1));
                    else if (TragicConfig.allowFear)
                        entity.addPotionEffect(new PotionEffect(TragicPotion.Fear.id, 120, 1));
                    else entity.addPotionEffect(new PotionEffect(Potion.weakness.id, 120, 10));
                }
            }
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

    }

    @Override
    public Doomsday getCombination() {
        return Doomsday.Firestorm;
    }
}
