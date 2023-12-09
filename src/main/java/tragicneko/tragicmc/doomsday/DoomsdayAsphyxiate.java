package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.entity.projectile.EntityDarkEnergy;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayAsphyxiate extends Doomsday implements IExtendedDoomsday {

    public DoomsdayAsphyxiate(int id) {
        super(id, EnumDoomType.CRISIS);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        double d0 = 12.0;
        List list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

        float crisis = 1 / this.getCrisis(player);

        if (list.size() > 0) {
            if (crucMoment) addCrucialMessage(player);

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof EntityLivingBase) {
                    EntityLivingBase entity = (EntityLivingBase) list.get(i);
                    entity.addPotionEffect(new PotionEffect(Potion.blindness.id, 2400, 10));
                    if (TragicConfig.allowSubmission)
                        entity.addPotionEffect(new PotionEffect(TragicPotion.Submission.id, 2400, (int) crisis));
                    entity.motionX = entity.motionY = entity.motionZ = 0.0;
                    entity.velocityChanged = false;

                    if (rand.nextInt(8) == 0) {
                        for (int l = 0; l < 3; l++) {
                            double d1 = rand.nextDouble() - rand.nextDouble();
                            double d2 = (rand.nextDouble() * 6.0D + rand.nextDouble() * 6.0D) + 2.0D;
                            double d3 = rand.nextDouble() - rand.nextDouble();

                            EntityDarkEnergy fireball = new EntityDarkEnergy(player.worldObj, player, -d1, -d2, -d3);
                            fireball.setPosition(
                                entity.posX + (-d1 * 0.115),
                                entity.posY + 6.0 + (-d2 * 0.115),
                                entity.posZ + (-d3 * 0.115));
                            player.worldObj.spawnEntityInWorld(fireball);
                        }
                    }
                }
            }
        } else {
            addNoEntityMessage(player);
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
        for (int l = 0; l < 3; l++) {
            double d1 = rand.nextDouble() - rand.nextDouble();
            double d2 = (rand.nextDouble() * 6.0D + rand.nextDouble() * 6.0D) + 2.0D;
            double d3 = rand.nextDouble() - rand.nextDouble();

            EntityDarkEnergy fireball = new EntityDarkEnergy(player.worldObj, player, -d1, -d2, -d3);
            fireball.setPosition(player.posX + (-d1 * 0.115), player.posY + (-d2 * 0.115), player.posZ + (-d3 * 0.115));
            player.worldObj.spawnEntityInWorld(fireball);
        }
    }

    @Override
    public Doomsday getCombination() {
        return Doomsday.Harmonizer;
    }

    @Override
    public int getWaitTime() {
        return 3;
    }

    @Override
    public int getMaxIterations() {
        return 50;
    }
}
