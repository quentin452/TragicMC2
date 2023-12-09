package tragicneko.tragicmc.network;

import net.minecraft.nbt.NBTTagCompound;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class MessageSound implements IMessage {

    public NBTTagCompound nameTag;

    public MessageSound() {}

    public MessageSound(String s, float f, float f2) {
        this.nameTag = new NBTTagCompound();
        this.nameTag.setString("name", s);
        this.nameTag.setFloat("volume", f);
        this.nameTag.setFloat("pitch", f2);
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
