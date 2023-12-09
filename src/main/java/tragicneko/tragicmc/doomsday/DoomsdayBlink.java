package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;

import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class DoomsdayBlink extends Doomsday {

    public DoomsdayBlink(int id) {
        super(id);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

        MovingObjectPosition mop = WorldHelper.getMOPFromEntity(player, crucMoment ? 50.0 : 35.0);

        if (mop.typeOfHit == MovingObjectType.BLOCK && player instanceof EntityPlayerMP
            && ((EntityPlayerMP) player).playerNetServerHandler.func_147362_b()
                .isChannelOpen()) {
            if (player.isRiding()) player.mountEntity((Entity) null);

            double d4 = WorldHelper.getXPositionFromSide(mop.sideHit, mop.hitVec.xCoord);
            double d5 = WorldHelper.getYPositionFromSide(mop.sideHit, mop.hitVec.yCoord);
            double d6 = WorldHelper.getZPositionFromSide(mop.sideHit, mop.hitVec.zCoord);

            player.fallDistance = 0.0F;
            player.setPositionAndUpdate(d4, d5, d6);
            player.worldObj.playSoundAtEntity(player, "mob.endermen.portal", 1.0F, 1.0F);
        } else {
            player.addChatMessage(
                new ChatComponentText(
                    EnumChatFormatting.ITALIC + "Out of teleport range, aim at a spot that's closer!"));
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

    }

    @Override
    public Doomsday getCombination() {
        return Doomsday.Evacuation;
    }
}
