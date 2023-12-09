package tragicneko.tragicmc.blocks.tileentity;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class TileEntitySoulChest extends TileEntityChest {

    private int souls = 0;
    public int requiredSouls = 30;

    private Set<UUID> deathCounter = new HashSet();

    public TileEntitySoulChest() {}

    public TileEntitySoulChest(int requirement) {
        this.requiredSouls = requirement;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        if (this.souls < this.requiredSouls && !player.capabilities.isCreativeMode) {
            if (!this.worldObj.isRemote) player.addChatMessage(
                new ChatComponentText(
                    "Mob kills are required to open this chest! Souls required: " + (this.requiredSouls - this.souls)));
            return false;
        }
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false
            : player.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (this.souls >= this.requiredSouls) return;

        List<EntityMob> list = this.worldObj.getEntitiesWithinAABB(
            EntityMob.class,
            AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1)
                .offset(this.xCoord, this.yCoord, this.zCoord)
                .expand(6.0, 6.0, 6.0));

        for (EntityMob mob : list) {
            if (mob.deathTime > 0 || mob.getHealth() <= 0F || mob.isDead) {
                double d0 = mob.posX - this.xCoord;
                double d1 = mob.posY - this.yCoord;
                double d2 = mob.posZ - this.zCoord;

                for (int l = 0; l < 4; l++) {
                    double d3 = 0.23D * l + (this.worldObj.rand.nextDouble() * 0.25D);
                    this.worldObj.spawnParticle(
                        "flame",
                        this.xCoord + 0.5 + d0 * d3,
                        this.yCoord + 0.5 + d1 * d3,
                        this.zCoord + 0.5 + d2 * d3,
                        0,
                        0,
                        0);
                    this.worldObj.spawnParticle(
                        "flame",
                        this.xCoord + 0.5,
                        this.yCoord + 0.5,
                        this.zCoord + 0.5,
                        (this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble()) * 0.125,
                        this.worldObj.rand.nextDouble() * 0.15,
                        (this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble()) * 0.125);
                }

                if (this.addMobToDeathCounter(mob)) {
                    this.worldObj
                        .playSoundEffect(mob.posX, mob.posY, mob.posZ, "tragicmc:random.soulbreath", 1.0F, 1.0F);
                    this.souls++;
                }
            }
        }

        if (this.worldObj.rand.nextInt(32) == 0) {
            for (int l = 0; l < 3; l++) {
                this.worldObj.spawnParticle(
                    "flame",
                    this.xCoord + 0.5,
                    this.yCoord + 0.5,
                    this.zCoord + 0.5,
                    (this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble()) * 0.125,
                    this.worldObj.rand.nextDouble() * 0.15,
                    (this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble()) * 0.125);
            }
        }
    }

    public boolean addMobToDeathCounter(EntityMob mob) {
        if (this.deathCounter.contains(mob.getUniqueID())) return false;

        this.deathCounter.add(mob.getUniqueID());
        // this.worldObj.playSoundAtEntity(mob, "tragicmc:random.soulbreath", 0.6F, this.worldObj.rand.nextFloat() *
        // 0.5F + 0.5F);

        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        if (tag.hasKey("soulRequirement")) this.requiredSouls = tag.getInteger("soulRequirement");
        if (tag.hasKey("souls")) this.souls = tag.getInteger("souls");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("soulRequirement", this.requiredSouls);
        tag.setInteger("souls", this.souls);
    }
}
