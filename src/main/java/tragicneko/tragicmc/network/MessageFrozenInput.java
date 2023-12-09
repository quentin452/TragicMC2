package tragicneko.tragicmc.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class MessageFrozenInput implements IMessage {

    public byte unfreeze;

    public MessageFrozenInput() {}

    public MessageFrozenInput(boolean unfreeze) {
        this.unfreeze = (byte) (unfreeze ? 1 : 0);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.unfreeze = buf.readByte();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeByte(this.unfreeze);
    }
}
