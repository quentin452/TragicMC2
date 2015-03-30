package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.erkelStats;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.entity.boss.TragicBoss;
import tragicneko.tragicmc.worldgen.biome.BiomeGenAshenHills;
import tragicneko.tragicmc.worldgen.biome.BiomeGenDecayingWasteland;
import tragicneko.tragicmc.worldgen.biome.BiomeGenHallowedHills;
import tragicneko.tragicmc.worldgen.biome.BiomeGenPaintedForest;
import tragicneko.tragicmc.worldgen.biome.BiomeGenStarlitPrarie;
import tragicneko.tragicmc.worldgen.biome.BiomeGenTaintedSpikes;

public class EntityErkel extends TragicMob {

	public EntityErkel(World par1World) {
		super(par1World);
		this.setSize(0.56F, 1.06F);
		this.experienceValue = 5;
		this.getNavigator().setAvoidsWater(true);
		this.getNavigator().setCanSwim(false);
		this.tasks.addTask(0, new EntityAIPanic(this, 0.65D));
		this.tasks.addTask(1, new EntityAIAvoidEntity(this, TragicBoss.class, 12.0F, 0.4D, 0.6D));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.tasks.addTask(5, new EntityAIWander(this, 0.45D));
	}
	
	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.getDataWatcher().addObject(16, Integer.valueOf(0));
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(erkelStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(erkelStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(erkelStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(erkelStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(erkelStats[4]);
	}
	
	@Override
	public int getTotalArmorValue()
	{
		return (int) erkelStats[5];
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float damage)
	{
		if (source.getEntity() != null && source.getEntity() instanceof EntityLivingBase && !source.isProjectile() && rand.nextBoolean() && !this.worldObj.isRemote)
		{
			((EntityLivingBase) source.getEntity()).addPotionEffect(new PotionEffect(Potion.poison.id, 120, 0));
		}
		
		if (this.worldObj.isRemote)
		{
			for (int i = 0; i < 2; i++)
			{
				this.worldObj.spawnParticle("witchMagic", this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), this.posY + 0.115D + rand.nextDouble(),
						this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), 0.0F, 0.155F * this.rand.nextFloat(), 0.0F);
			}
		}
		return super.attackEntityFrom(source, damage);
	}
	
	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Natural;
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.worldObj.isRemote)
		{
			String s = "mobSpellAmbient";
			
			for (int i = 0; i < 2; i++)
			{
				this.worldObj.spawnParticle(s, this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), this.posY + 0.115D + rand.nextDouble(),
						this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), 0.0F, 0.155F * this.rand.nextFloat(), 0.0F);
			}
		}
		else
		{
			if (this.rand.nextInt(32) == 0 && this.onGround && this.getMobGriefing() && this.ticksExisted % 20 == 0)
			{
				int x = (int) (this.posX + rand.nextInt(2) - rand.nextInt(2));
				int y = (int) (this.posY + rand.nextInt(2) - rand.nextInt(2));
				int z = (int) (this.posZ + rand.nextInt(2) - rand.nextInt(2));
				
				if (this.worldObj.isAirBlock(x, y, z) || this.worldObj.getBlock(x, y, z) instanceof BlockTallGrass)
				{
					if (Blocks.brown_mushroom.canBlockStay(this.worldObj, x, y, z)) this.worldObj.setBlock(x, y, z, rand.nextBoolean() ? Blocks.brown_mushroom : Blocks.red_mushroom);
				}
			}
		}
	}
	
	@Override
	protected boolean isValidLightLevel()
    {
        return true;
    }
	
	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data)
	{
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ);
		int i = 0;
		if (biome instanceof BiomeGenAshenHills || biome instanceof BiomeGenTaintedSpikes)
		{
			i = 1;
		}
		else if (biome instanceof BiomeGenPaintedForest)
		{
			i = 2;
		}
		else if (biome instanceof BiomeGenStarlitPrarie)
		{
			i = 3;
		}
		else if (biome instanceof BiomeGenDecayingWasteland)
		{
			i = 4;
		}
		else if (biome instanceof BiomeGenHallowedHills)
		{
			i = 5;
		}
		
		this.setTextureId(i);
		return super.onSpawnWithEgg(data);
	}
	
	private void setTextureId(int i)
	{
		this.getDataWatcher().updateObject(16, i);
	}
	
	public int getTextureId()
	{
		return this.getDataWatcher().getWatchableObjectInt(16);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("texture")) this.setTextureId(tag.getInteger("texture"));
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("texture", this.dataWatcher.getWatchableObjectInt(16));
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}
}
