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

public class DoomsdayTorment extends Doomsday {

    public DoomsdayTorment(int id) {
        super(id);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        double d0 = crucMoment ? 12.0D : 6.0D;
        List<Entity> list = player.worldObj
            .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof EntityLivingBase) {
                EntityLivingBase entity = (EntityLivingBase) list.get(i);
                if (entity instanceof EntityPlayer && !TragicConfig.allowPvP) continue;
                int f = crucMoment ? 2000 : 200;

                entity.addPotionEffect(new PotionEffect(Potion.wither.id, f, 2 + rand.nextInt(2)));
                if (TragicConfig.allowStun)
                    entity.addPotionEffect(new PotionEffect(TragicPotion.Stun.id, f, 2 + rand.nextInt(2)));
                if (TragicConfig.allowSubmission)
                    entity.addPotionEffect(new PotionEffect(TragicPotion.Submission.id, f, 2 + rand.nextInt(2)));
            }
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
        player.addPotionEffect(new PotionEffect(Potion.wither.id, 200, 2 + rand.nextInt(2)));
        if (TragicConfig.allowStun)
            player.addPotionEffect(new PotionEffect(TragicPotion.Stun.id, 200, 2 + rand.nextInt(2)));
        if (TragicConfig.allowSubmission)
            player.addPotionEffect(new PotionEffect(TragicPotion.Submission.id, 200, 2 + rand.nextInt(2)));
    }

    @Override
    public Doomsday getCombination() {
        return Doomsday.Mindcrack;
    }
}
