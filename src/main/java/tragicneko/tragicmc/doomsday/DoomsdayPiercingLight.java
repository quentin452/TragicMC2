package tragicneko.tragicmc.doomsday;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayPiercingLight extends Doomsday {

    public DoomsdayPiercingLight(int id) {
        super(id, EnumDoomType.CRISIS);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        int posX = (int) player.posX;
        int posY = (int) player.posY;
        int posZ = (int) player.posZ;

        float crisis = this.getCrisis(player);
        List<Entity> list = new ArrayList();

        if (player.worldObj.getLightBrightness(posX, posY, posZ) <= 0.75F) {
            double d0 = 16.0;

            if (crisis >= 0.25F) {
                d0 = 12.0;
            }

            if (crisis >= 0.5F) {
                d0 = 8.0;
            }

            if (crisis >= 0.75F) {
                d0 = 4.0;
            }

            list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

            if (effect.utilityList.size() == 0) addNoEntityMessage(player);
        } else {
            player.addChatMessage(
                new ChatComponentText(EnumChatFormatting.ITALIC + "It's not dark enough here to use that!"));
            if (!list.isEmpty()) list.clear();
        }

        for (int x = 0; x < list.size(); x++) {
            if (list.get(x) instanceof EntityLivingBase) {
                EntityLivingBase entity = (EntityLivingBase) list.get(x);
                if (entity instanceof EntityPlayer && !TragicConfig.allowPvP) continue;

                float f = entity.worldObj.getLightBrightness((int) entity.posX, (int) entity.posY, (int) entity.posZ);

                if (f <= 0.75F) {
                    if (f < 0.1F) f = 0.1F;
                    entity.setFire(10 + rand.nextInt(16));
                    int j = crucMoment ? 40 : 20;
                    entity.attackEntityFrom(DamageSource.magic, (j / MathHelper.ceiling_float_int((f * 10)) + crisis));
                }
            }
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
        player.setFire(8 + rand.nextInt(16));
    }

}
