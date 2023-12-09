package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.entity.EntityDirectedLightning;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayLightningRush extends Doomsday implements IExtendedDoomsday {

    public DoomsdayLightningRush(int id) {
        super(id, EnumDoomType.COMBINATION);
    }

    @Override
    public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        super.doInitialEffects(effect, doom, player, crucMoment);
        if (TragicConfig.allowInvulnerability)
            player.addPotionEffect(new PotionEffect(TragicPotion.Invulnerability.id, 600, 0));
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

        List<Entity> list = player.worldObj
            .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(2.0D, 2.0D, 2.0D));
        EntityLivingBase entity;

        if (list.size() > 0) {
            if (crucMoment) player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof EntityLivingBase) {
                    entity = (EntityLivingBase) list.get(i);
                    if (entity instanceof EntityPlayer && !TragicConfig.allowPvP) continue;
                    entity.applyEntityCollision(player);

                    entity.motionX *= 1.8;
                    entity.motionZ *= 1.8;
                    entity.motionY *= 1.8;

                    float f = crucMoment ? 3.0F : 1.0F;
                    entity.attackEntityFrom(DamageSource.causePlayerDamage(player), f);
                    player.worldObj
                        .createExplosion(player, entity.posX, entity.posY, entity.posZ, f * rand.nextFloat(), false);
                    player.worldObj.spawnEntityInWorld(
                        new EntityDirectedLightning(player.worldObj, entity.posX, entity.posY, entity.posZ, player));

                    if (TragicConfig.allowStun) entity.addPotionEffect(new PotionEffect(TragicPotion.Stun.id, 60, 1));
                }
            }
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

    }

    @Override
    public int getWaitTime() {
        return 3;
    }

    @Override
    public int getMaxIterations() {
        return 200;
    }

}
