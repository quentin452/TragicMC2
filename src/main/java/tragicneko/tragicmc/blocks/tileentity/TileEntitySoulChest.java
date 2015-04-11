package tragicneko.tragicmc.blocks.tileentity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChatComponentText;
import tragicneko.tragicmc.TragicMC;

public class TileEntitySoulChest extends TileEntityChest {

	private int souls = 0;
	public int requiredSouls = 30;

	private Set<UUID> deathCounter = new HashSet();

	public TileEntitySoulChest() {}

	public TileEntitySoulChest(int requirement)
	{
		this.requiredSouls = requirement;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		if (this.souls < this.requiredSouls)
		{
			if (!this.worldObj.isRemote) player.addChatMessage(new ChatComponentText("Mob kills are required to open this chest! Souls required: " + (this.requiredSouls - this.souls)));
			return false;
		}
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();

		if (this.souls >= this.requiredSouls) return; 

		List<EntityMob> list = this.worldObj.getEntitiesWithinAABB(EntityMob.class, this.getRenderBoundingBox().expand(6.0, 6.0, 6.0));

		for (EntityMob mob : list)
		{
			if (mob.deathTime > 0 || mob.getHealth() <= 0F || mob.isDead)
			{
				if (mob != null)
				{
					double d0 = mob.posX - this.xCoord;
					double d1 = mob.posY - this.yCoord;
					double d2 = mob.posZ - this.zCoord;
					
					for (int l = 0; l < 4; l++)
					{
						double d3 = 0.23D * l + (this.worldObj.rand.nextDouble() * 0.25D);
						this.worldObj.spawnParticle("flame", this.xCoord + 0.5 + d0 * d3, this.yCoord + 0.5 + d1 * d3 + 0.75D, this.zCoord + 0.5 + d2 * d3, 0, 0, 0);
						this.worldObj.spawnParticle("flame", this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.5, (this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble()) * 0.125, this.worldObj.rand.nextDouble() * 0.15, (this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble()) * 0.125);
					}
				}
				if (this.addMobToDeathCounter(mob)) this.souls++;
			}
		}
		
		if (this.worldObj.rand.nextInt(32) == 0)
		{
			for (int l = 0; l < 3; l++)
			{
				this.worldObj.spawnParticle("flame", this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.5, (this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble()) * 0.125, this.worldObj.rand.nextDouble() * 0.15, (this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble()) * 0.125);
			}
		}
	}

	public boolean addMobToDeathCounter(EntityMob mob)
	{
		if (this.worldObj.isRemote || this.deathCounter.contains(mob.getUniqueID())) return false;

		this.deathCounter.add(mob.getUniqueID());
		TragicMC.logInfo("Mob death counted. Soul total is " + (this.souls + 1));
		return true;
	}

	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		if (tag.hasKey("soulRequirement")) this.requiredSouls = tag.getInteger("soulRequirement");
		if (tag.hasKey("souls")) this.souls = tag.getInteger("souls");
	}

	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setInteger("soulRequirement", this.requiredSouls);
		tag.setInteger("souls", this.souls);
	}
}
