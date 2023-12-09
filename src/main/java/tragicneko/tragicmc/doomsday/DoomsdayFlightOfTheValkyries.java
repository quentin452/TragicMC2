package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.entity.EntityDirectedLightning;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayFlightOfTheValkyries extends Doomsday implements IExtendedDoomsday {

    public DoomsdayFlightOfTheValkyries(int id) {
        super(id, EnumDoomType.COMBINATION);
    }

    @Override
    public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        super.doInitialEffects(effect, doom, player, crucMoment);

        if (TragicConfig.allowInvulnerability)
            player.addPotionEffect(new PotionEffect(TragicPotion.Invulnerability.id, 200, 0));
        doom.fillDoom();
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        EntityLivingBase entity;
        float damage = crucMoment ? 8.0F : 4.0F;
        float amt = crucMoment ? 1.5F : 0.5F;

        List<Entity> list = player.worldObj
            .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(32.0D, 32.0D, 32.0D));

        if (list.size() > 0) {
            if (crucMoment) addCrucialMessage(player);

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof EntityLivingBase) {
                    entity = (EntityLivingBase) list.get(i);
                    if (entity instanceof EntityPlayer && !TragicConfig.allowPvP) continue;

                    entity.motionX = rand.nextDouble() - rand.nextDouble();
                    entity.motionY = 0.75 - rand.nextDouble();
                    entity.motionZ = rand.nextDouble() - rand.nextDouble();

                    if (rand.nextBoolean()) {
                        entity.attackEntityFrom(
                            DamageSource.causePlayerDamage(player),
                            1.0F + (rand.nextFloat() * damage));

                        if (rand.nextInt(16) == 0) {
                            player.worldObj.spawnEntityInWorld(
                                new EntityDirectedLightning(
                                    player.worldObj,
                                    entity.posX,
                                    entity.posY,
                                    entity.posZ,
                                    player));
                            player.heal(amt);
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

    }

    @Override
    public int getWaitTime() {
        return 5;
    }

    @Override
    public int getMaxIterations() {
        return 200;
    }
}
