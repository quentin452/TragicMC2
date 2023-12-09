package tragicneko.tragicmc.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import tragicneko.tragicmc.properties.PropertyDoom;

public class MessageDoom implements IMessage {

    public NBTTagCompound tag;

    public MessageDoom() {}

    public MessageDoom(EntityPlayer player) {
        this.tag = new NBTTagCompound();
        PropertyDoom.get(player)
            .saveNBTData(tag);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        tag = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, tag);
    }
}
