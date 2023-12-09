package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayRavage extends Doomsday {

    public DoomsdayRavage(int id) {
        super(id, EnumDoomType.WORLDSHAPER);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        double d0 = crucMoment ? 24.0D : 12.0D;
        List<Entity> list = player.worldObj
            .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));
        float f = crucMoment ? 5.0F : 2.5F;
        int meow = crucMoment ? 4 : 0;

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

        for (int x = 0; x < rand.nextInt(8) + 4 + meow; x++) {
            player.worldObj.createExplosion(
                player,
                player.posX + (rand.nextInt(9) - 4),
                player.posY + (rand.nextInt(4) - 1),
                player.posZ + (rand.nextInt(9) - 4),
                rand.nextFloat() * f,
                TragicConfig.griefConfig[1]);
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
        player.worldObj.createExplosion(
            null,
            player.posX,
            player.posY,
            player.posZ,
            rand.nextFloat() * 1.5F,
            TragicConfig.griefConfig[1]);
    }

    @Override
    public Doomsday getCombination() {
        return Doomsday.DangerZone;
    }
}
