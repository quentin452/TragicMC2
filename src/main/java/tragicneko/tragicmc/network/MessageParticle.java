package tragicneko.tragicmc.network;

import net.minecraft.nbt.NBTTagCompound;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class MessageParticle implements IMessage {

    public NBTTagCompound nameTag;

    public MessageParticle() {}

    public MessageParticle(String s, double x, double y, double z, double x2, double y2, double z2) {
        this.nameTag = new NBTTagCompound();
        this.nameTag.setString("particleName", s);
        this.nameTag.setDouble("xPos", x);
        this.nameTag.setDouble("yPos", y);
        this.nameTag.setDouble("zPos", z);
        this.nameTag.setDouble("xAccel", x2);
        this.nameTag.setDouble("yAccel", y2);
        this.nameTag.setDouble("zAccel", z2);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.nameTag = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, this.nameTag);
    }

}
