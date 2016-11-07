package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.World;

public class S43PacketCamera implements Packet
{
    public int entityId;
    private static final String __OBFID = "CL_00002289";

    public S43PacketCamera() {}

    public S43PacketCamera(Entity entityIn)
    {
        this.entityId = entityIn.getEntityId();
    }

    /**
     * Reads the raw packet data from the data stream.
     */
    public void readPacketData(PacketBuffer buf) throws IOException
    {
        this.entityId = buf.readVarIntFromBuffer();
    }

    /**
     * Writes the raw packet data to the data stream.
     */
    public void writePacketData(PacketBuffer buf) throws IOException
    {
        buf.writeVarIntToBuffer(this.entityId);
    }

    public void func_179779_a(INetHandlerPlayClient handler)
    {
        handler.handleCamera(this);
    }

    public Entity getEntity(World worldIn)
    {
        return worldIn.getEntityByID(this.entityId);
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket(INetHandler handler)
    {
        this.func_179779_a((INetHandlerPlayClient)handler);
    }
}