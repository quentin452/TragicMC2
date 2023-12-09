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

public class DoomsdayDecay extends Doomsday {

    public DoomsdayDecay(int id) {
        super(id);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        List<Entity> list = player.worldObj
            .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(8.0, 8.0, 8.0));

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof EntityLivingBase && rand.nextInt(4) == 0) {
                    EntityLivingBase entity = (EntityLivingBase) list.get(i);
                    if (entity instanceof EntityPlayer && !TragicConfig.allowPvP) continue;

                    entity.addPotionEffect(new PotionEffect(Potion.wither.id, rand.nextInt(120) + 120));
                    entity
                        .addPotionEffect(new PotionEffect(Potion.hunger.id, rand.nextInt(240) + 160, rand.nextInt(2)));
                    if (crucMoment && TragicConfig.allowCripple) entity.addPotionEffect(
                        new PotionEffect(TragicPotion.Cripple.id, rand.nextInt(240) + 120, rand.nextInt(6)));
                }
            }
        } else {
            addNoEntityMessage(player);
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
        player.addPotionEffect(new PotionEffect(Potion.hunger.id, rand.nextInt(240) + 160, rand.nextInt(2) + 1));
    }

    @Override
    public Doomsday getCombination() {
        return Doomsday.RadiantLight;
    }
}
