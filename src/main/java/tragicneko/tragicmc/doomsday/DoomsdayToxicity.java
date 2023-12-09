package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.projectile.EntityPoisonBarb;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayToxicity extends Doomsday {

    public DoomsdayToxicity(int id) {
        super(id);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        double d = crucMoment ? 16.0 : 12.0;
        List<Entity> list = player.worldObj
            .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d, d, d));

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof EntityLivingBase) {
                    EntityLivingBase entity = (EntityLivingBase) list.get(i);
                    if (entity instanceof EntityPlayer && !TragicConfig.allowPvP) continue;

                    for (int j = 0; j < 4; j++) {
                        double d0 = entity.posX - player.posX;
                        double d1 = entity.posY - player.posY + 0.65;
                        double d2 = entity.posZ - player.posZ;

                        EntityPoisonBarb fireball = new EntityPoisonBarb(player.worldObj, player, d0, d1, d2);
                        fireball
                            .setPosition(player.posX + (d0 * 0.115), player.posY + 0.6D, player.posZ + (d2 * 0.115));
                        player.worldObj.spawnEntityInWorld(fireball);
                    }
                }
            }
        } else {
            addNoEntityMessage(player);
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
        player.addPotionEffect(new PotionEffect(Potion.poison.id, 120, 0));
    }

}
