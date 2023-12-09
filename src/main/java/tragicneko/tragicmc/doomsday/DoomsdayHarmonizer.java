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

public class DoomsdayHarmonizer extends Doomsday {

    public DoomsdayHarmonizer(int id) {
        super(id, EnumDoomType.CRISIS);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

        double d0 = 12.0;
        List<Entity> list = player.worldObj
            .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

        float crisis = this.getCrisis(player);
        crisis /= 1.0F / 20.0F;

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof EntityLivingBase) {
                    EntityLivingBase entity = (EntityLivingBase) list.get(i);
                    if (entity instanceof EntityPlayer && !TragicConfig.allowPvP) continue;

                    int dur = crucMoment ? 600 : 300;

                    if (TragicConfig.allowHarmony) {
                        entity.addPotionEffect(new PotionEffect(TragicPotion.Harmony.id, dur));
                    } else {
                        entity.addPotionEffect(new PotionEffect(Potion.blindness.id, dur));
                    }

                    if (player.getHealth() < player.getMaxHealth()) {
                        float potato = crucMoment ? crisis * 2 : crisis;
                        player.heal(potato);
                    }
                }
            }
        } else {
            addNoEntityMessage(player);
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
        if (TragicConfig.allowHarmony) {
            player.addPotionEffect(new PotionEffect(TragicPotion.Harmony.id, 240));
        } else {
            player.addPotionEffect(new PotionEffect(Potion.blindness.id, 240));
        }
    }

    @Override
    public Doomsday getCombination() {
        return Doomsday.Dimentia;
    }
}
