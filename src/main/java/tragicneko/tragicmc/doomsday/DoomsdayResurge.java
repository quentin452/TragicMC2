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

public class DoomsdayResurge extends Doomsday {

    public DoomsdayResurge(int id) {
        super(id);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        List<Entity> list = player.worldObj
            .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(32.0, 32.0, 32.0));

        for (Entity e : list) {
            if (e instanceof EntityPlayer) {
                ((EntityLivingBase) e)
                    .addPotionEffect(new PotionEffect(Potion.regeneration.id, 600, crucMoment ? 5 : 1));
                if (TragicConfig.allowConvergence && e != player) ((EntityLivingBase) e)
                    .addPotionEffect(new PotionEffect(TragicPotion.Convergence.id, 600, crucMoment ? 5 : 1));
            }
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

    }

}
