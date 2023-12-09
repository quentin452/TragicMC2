package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class DoomsdayDangerZone extends Doomsday implements IExtendedDoomsday {

    public DoomsdayDangerZone(int id) {
        super(id, EnumDoomType.COMBINATION);
    }

    @Override
    public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        super.doInitialEffects(effect, doom, player, crucMoment);
        if (TragicConfig.allowInvulnerability)
            player.addPotionEffect(new PotionEffect(TragicPotion.Invulnerability.id, 300));
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        double d0 = 12.0D;
        List<Entity> list = player.worldObj
            .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));
        float f = crucMoment ? 2.5F : 1.5F;
        int meow = crucMoment ? 12 : 4;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof EntityLivingBase) {
                EntityLivingBase entity = (EntityLivingBase) list.get(i);
                if (entity instanceof EntityPlayer && !TragicConfig.allowPvP) continue;
                player.worldObj.createExplosion(
                    player,
                    entity.posX,
                    entity.posY,
                    entity.posZ,
                    rand.nextFloat() * f,
                    TragicConfig.griefConfig[1]);
            }
        }

        double y = player.posY - WorldHelper.getDistanceToGround(player);

        for (int l = 0; l < meow; l++) {
            EntityTNTPrimed tnt = new EntityTNTPrimed(
                player.worldObj,
                player.posX + rand.nextInt(16) - rand.nextInt(16),
                y + 24 + rand.nextInt(16),
                player.posZ + rand.nextInt(16) - rand.nextInt(16),
                player);
            player.worldObj.spawnEntityInWorld(tnt);
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

    }

    @Override
    public int getWaitTime() {
        return 10;
    }

    @Override
    public int getMaxIterations() {
        return 120;
    }

}
