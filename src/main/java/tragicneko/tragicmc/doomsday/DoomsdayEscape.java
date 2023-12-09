package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayEscape extends Doomsday {

    public DoomsdayEscape(int id) {
        super(id);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        player.worldObj.createExplosion(player, player.posX, player.posY, player.posZ, crucMoment ? 5.0F : 3.0F, false);

        List<Entity> list = player.worldObj
            .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(8.0D, 8.0D, 8.0D));
        EntityLivingBase entity;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof EntityLivingBase) {
                entity = (EntityLivingBase) list.get(i);
                if (entity instanceof EntityPlayer && !TragicConfig.allowPvP) continue;
                entity.addPotionEffect(new PotionEffect(Potion.blindness.id, 120, 10));
            }
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

    }

}
