package tragicneko.tragicmc.doomsday;

import java.util.Collections;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayDeathMark extends Doomsday implements IExtendedDoomsday {

    public DoomsdayDeathMark(int id) {
        super(id, EnumDoomType.OVERFLOW);
    }

    @Override
    public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        super.doInitialEffects(effect, doom, player, crucMoment);

        double radius = crucMoment ? 12.0D : 6.0D;
        List list = player.worldObj
            .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(radius, radius, radius));

        Collections.shuffle(list);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof EntityMob) {
                EntityMob e = (EntityMob) list.get(i);
                e.addPotionEffect(
                    new PotionEffect(
                        TragicConfig.allowSubmission ? TragicPotion.Submission.id : Potion.weakness.id,
                        600,
                        10));
                effect.utilityEntity = e;
                break;
            }
        }
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

        if (effect.utilityEntity == null || effect.utilityEntity.isDead
            || ((EntityLivingBase) effect.utilityEntity).getHealth() == 0F) {
            effect.utilityEntity = null;
            double radius = crucMoment ? 12.0D : 6.0D;
            List list = player.worldObj
                .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(radius, radius, radius));

            Collections.shuffle(list);

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof EntityMob) {
                    EntityMob e = (EntityMob) list.get(i);
                    e.addPotionEffect(
                        new PotionEffect(
                            TragicConfig.allowSubmission ? TragicPotion.Submission.id : Potion.weakness.id,
                            600,
                            10));
                    effect.utilityEntity = e;
                    break;
                }
            }

            if (effect.utilityEntity == null) addNoEntityMessage(player);
        }

        if (effect.utilityEntity != null) {
            if (rand.nextBoolean()) effect.utilityEntity.attackEntityFrom(DamageSource.outOfWorld, 1.0F);
            player.addChatMessage(
                new ChatComponentText(
                    "Target is at " + ((int) effect.utilityEntity.posX)
                        + ", "
                        + ((int) effect.utilityEntity.posY)
                        + ", "
                        + ((int) effect.utilityEntity.posZ)));
        }

    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
        player.addPotionEffect(
            new PotionEffect(TragicConfig.allowSubmission ? TragicPotion.Submission.id : Potion.weakness.id, 200, 10));
    }

    @Override
    public int getWaitTime() {
        return 20;
    }

    @Override
    public int getMaxIterations() {
        return 20;
    }

}
