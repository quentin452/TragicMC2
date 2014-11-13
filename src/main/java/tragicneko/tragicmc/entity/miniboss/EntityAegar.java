package tragicneko.tragicmc.entity.miniboss;

import java.util.List;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.boss.EntityPart;
import tragicneko.tragicmc.entity.boss.IMultiPart;
import tragicneko.tragicmc.entity.mob.TragicMob;
import tragicneko.tragicmc.entity.projectile.EntityCrystalMortor;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.util.DamageHelper;

public class EntityAegar extends TragicMob implements TragicMiniBoss, IMultiPart {

	public EntityPart[] aegarParts;

	public EntityPart aegarBody;
	public EntityAegarCrystal aegarCrystal;
	public EntityPart aegarClaw;
	public EntityPart aegarCannon;
	public EntityPart aegarHead;

	private AttributeModifier mod = new AttributeModifier(UUID.fromString("8771eb81-724e-4d6b-91a4-d7e2cd17f82c"), "aegarSpeedBuff", 0.156, 0);

	public EntityAegar(World par1World) {
		super(par1World);
		this.aegarParts = new EntityPart[] {aegarBody = new EntityPart(this, "body", 1.0F, 1.0F), aegarCrystal = new EntityAegarCrystal(this),
				aegarClaw = new EntityPart(this, "claw", 1.5F, 1.5F), aegarCannon = new EntityPart(this, "cannon", 1.5F, 1.5F),
				aegarHead = new EntityPart(this, "head", 1.0F, 1.0F)};
		this.setSize(1.385F * 1.545F, 2.325F * 1.545F);
		this.stepHeight = 2.0F;
		this.experienceValue = 100;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityGolem.class, 0, true));
		this.isImmuneToFire = true;
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(150.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.185);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(26.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(2.5);
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Integer.valueOf(0));
		this.dataWatcher.addObject(17, Integer.valueOf(0));
		this.dataWatcher.addObject(18, Integer.valueOf(0)); 
		this.dataWatcher.addObject(19, Integer.valueOf(0));
		this.dataWatcher.addObject(20, Integer.valueOf(0));
		this.dataWatcher.addObject(21, Integer.valueOf(0));
		this.dataWatcher.addObject(22, Integer.valueOf(0));
	}

	public boolean getHypermode()
	{
		return this.dataWatcher.getWatchableObjectInt(16) == 1;
	}

	private void setHypermode(boolean flag)
	{
		this.dataWatcher.updateObject(16, flag ? 1 : 0);
	}

	public int getStunTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(17);
	}

	private void setStunTicks(int i)
	{
		this.dataWatcher.updateObject(17, i);
	}

	private void decrementStunTicks()
	{
		this.setStunTicks(this.getStunTicks() - 1);
	}

	public int getShockwaveTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(18);
	}

	private void setShockwaveTicks(int i)
	{
		this.dataWatcher.updateObject(18, i);
	}

	private void decrementShockwaveTicks()
	{
		this.setShockwaveTicks(this.getShockwaveTicks() - 1);
	}

	public int getAttackTime()
	{
		return this.dataWatcher.getWatchableObjectInt(19);
	}

	private void setAttackTime(int i)
	{
		this.dataWatcher.updateObject(19, i);
	}

	private void decrementAttackTime()
	{
		this.setAttackTime(this.getAttackTime() - 1);
	}

	public int getHurtTime()
	{
		return this.dataWatcher.getWatchableObjectInt(20);
	}

	private void setHurtTime(int i)
	{
		this.dataWatcher.updateObject(20, i);
	}

	private void decrementHurtTime()
	{
		this.setHurtTime(this.getHurtTime() - 1);
	}

	public int getLaserTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(21);
	}

	private void setLaserTicks(int i)
	{
		this.dataWatcher.updateObject(21, i);
	}

	private void decrementLaserTicks()
	{
		this.setLaserTicks(this.getLaserTicks() - 1);
	}

	public int getMortorTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(22);
	}

	private void setMortorTicks(int i)
	{
		this.dataWatcher.updateObject(22, i);
	}

	private void decrementMortorTicks()
	{
		this.setMortorTicks(this.getMortorTicks() - 1);
	}

	private boolean canUseAbility()
	{
		return this.getStunTicks() == 0 && this.getShockwaveTicks() == 0 && this.getLaserTicks() == 0 && this.getMortorTicks() == 0;
	}

	@Override
	public void onLivingUpdate()
	{
		if (this.getStunTicks() > 0)
		{
			this.motionX = this.motionZ = 0.0D;
			this.motionY = -0.1D;
			if (this.getShockwaveTicks() > 0) this.setShockwaveTicks(0);
			if (this.getHurtTime() > 0) this.setHurtTime(0);
			if (this.getAttackTime() > 0) this.setAttackTime(0);
			if (this.getLaserTicks() > 0) this.setLaserTicks(0);
			if (this.getMortorTicks() > 0) this.setMortorTicks(0);
		}

		if (this.getLaserTicks() > 0 || this.getShockwaveTicks() > 0 || this.getMortorTicks() > 20) this.motionX = this.motionZ = this.motionY = 0.0D;
		if (this.getLaserTicks() == 5 && this.getAttackTarget() != null) this.fireLaser();

		super.onLivingUpdate();

		float f1 = this.rotationYaw * (float)Math.PI / 180.0F;
		float f2 = MathHelper.sin(f1);
		float f3 = MathHelper.cos(f1);
		double d = 1.225D;

		this.aegarHead.width = 0.35F;
		this.aegarHead.height = 0.75F;
		this.aegarCrystal.height = 0.65F;
		this.aegarCrystal.width = 0.45F;
		this.aegarBody.height = this.aegarBody.width = 0.45F;
		this.aegarCannon.width = this.aegarClaw.width = 0.55F;
		this.aegarClaw.height = this.aegarCannon.height = 1.25F;

		this.aegarHead.setLocationAndAngles(this.posX + (f2 * 0.025D), this.posY + 3.25D, this.posZ + (f3 * 0.025D), 0.0F, 0.0F);
		this.aegarCrystal.setLocationAndAngles(this.posX + (f2 * 0.025D), this.posY + 2.55D, this.posZ + (f3 * 0.025D), 0.0F, 0.0F);
		this.aegarBody.setLocationAndAngles(this.posX + (f2 * 0.025D), this.posY + 1.25D, this.posZ + (f3 * 0.025D), 0.0F, 0.0F);
		this.aegarCannon.setLocationAndAngles(this.posX + (f3 * 0.875D), this.posY + 1.2D, this.posZ + (f2 * 0.875D), 0.0F, 0.0F);
		this.aegarClaw.setLocationAndAngles(this.posX - (f3 * 0.875D), this.posY + 1.2D, this.posZ - (f2 * 0.875D), 0.0F, 0.0F);

		for (int i = 0; i < this.aegarParts.length; i++)
		{
			if (aegarParts[i].isBurning()) aegarParts[i].extinguish();
			aegarParts[i].onUpdate();
		}

		if (this.worldObj.isRemote)
		{
			String s = this.getHypermode() ? "reddust" : "happyVillager";

			this.aegarCrystal.worldObj.spawnParticle(s, this.aegarCrystal.posX + rand.nextDouble() - rand.nextDouble(), this.aegarCrystal.posY  + rand.nextDouble() * this.aegarCrystal.height,
					this.aegarCrystal.posZ + rand.nextDouble() - rand.nextDouble(),
					0.0, 0.0, 0.0);

			if (rand.nextBoolean())
				this.worldObj.spawnParticle(s, this.posX + (rand.nextDouble() - rand.nextDouble()) * this.width, this.posY + rand.nextDouble() * this.height + 0.15, this.posZ + (rand.nextDouble() - rand.nextDouble()) * this.width,
						0.0, 0.0, 0.0);

			for (int i = 0; i < 4; i++)
			{
				this.worldObj.spawnParticle("mobSpellAmbient", this.posX + (rand.nextDouble() - rand.nextDouble()) * 0.55, this.posY + 0.25 + rand.nextDouble(), this.posZ + (rand.nextDouble() - rand.nextDouble()) * 0.55,
						0.0, 0.0, 0.0);
			}

			if (this.getMortorTicks() > 20)
			{
				for (int i = 0; i < 32; i++)
				{
					double d7 = (rand.nextDouble() * 10.0D - rand.nextDouble() * 10.0D);
					double d8 = (rand.nextDouble() * 10.0D - rand.nextDouble() * 10.0D);
					double d9 = (rand.nextDouble() * 10.0D - rand.nextDouble() * 10.0D);

					double d0 = d7 + this.posX;
					double d1 = d8 + this.posY + this.aegarCannon.height / 2.0D;
					double d2 = d9 + this.posZ;

					float f4 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
					double d3 = 0.75D;

					double d4 = d0 / (double)f4 * d3 * 0.100000011920929D + d7 * 0.10000000298023224D;
					double d5 = d1 / (double)f4 * d3 * 0.100000011920929D + d8 * 0.10000000298023224D;
					double d6 = d2 / (double)f4 * d3 * 0.100000011920929D + d9 * 0.10000000298023224D;

					this.worldObj.spawnParticle("reddust", d0, d1, d2, d4 * 2.5D, d5 * 2.5D, d6 * 2.5D);
				}
			}

			if (this.getLaserTicks() > 20)
			{
				for (int i = 0; i < 3; i++)
				{
					this.worldObj.spawnParticle("flame", this.posX + (rand.nextDouble() - rand.nextDouble()) * 0.55, this.posY + 2.25 + rand.nextDouble(), this.posZ + (rand.nextDouble() - rand.nextDouble()) * 0.55,
							0.0, 0.0, 0.0);
				}
			}

			if (this.getShockwaveTicks() > 7)
			{
				for (int i = 0; i < 12; i++)
				{
					this.worldObj.spawnParticle("smoke", this.posX + (rand.nextDouble() - rand.nextDouble()) * 1.25, this.posY + rand.nextDouble() * this.height, this.posZ + (rand.nextDouble() - rand.nextDouble()) * 1.25,
							0.0, rand.nextDouble() * 2.0 + 1.0, 0.0);
				}
			}

			return;
		}

		if (this.getStunTicks() > 0) this.decrementStunTicks();
		if (this.getShockwaveTicks() > 0) this.decrementShockwaveTicks();
		if (this.getHurtTime() > 0) this.decrementHurtTime();
		if (this.getAttackTime() > 0) this.decrementAttackTime();
		if (this.getLaserTicks() > 0) this.decrementLaserTicks();
		if (this.getMortorTicks() > 0) this.decrementMortorTicks();

		if (this.aegarCrystal.getHealth() <= 0 && !this.getHypermode()) this.onCrystalDestruction();

		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(mod);
		if (this.getHypermode()) this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(mod);

		if (this.getAttackTarget() == null)
		{
			if (this.getShockwaveTicks() > 0) this.setShockwaveTicks(0);
			if (this.getHurtTime() > 0) this.setHurtTime(0);
			if (this.getAttackTime() > 0) this.setAttackTime(0);
			if (this.getLaserTicks() > 0) this.setLaserTicks(0);
			if (this.getMortorTicks() > 0) this.setMortorTicks(0);
		}
		else
		{
			if (this.canUseAbility() && this.getDistanceToEntity(this.getAttackTarget()) >= 8.0F && rand.nextInt(64) == 0) this.setLaserTicks(40);

			if (this.getLaserTicks() > 0 && !this.canEntityBeSeen(this.getAttackTarget())) this.setLaserTicks(0);

			if (this.canUseAbility() && this.getDistanceToEntity(this.getAttackTarget()) <= 6.0F && rand.nextInt(this.getHypermode() ? 48 : 128) == 0 && this.onGround) this.setShockwaveTicks(60);
			if (this.getShockwaveTicks() == 5)
			{
				this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 3.0F + 2.0F * rand.nextFloat(), this.getMobGriefing());
				this.attackEntitiesInList(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(6.0D, 6.0D , 6.0D)));
			}

			if (this.canUseAbility() && this.getDistanceToEntity(this.getAttackTarget()) > 12.0F && this.getHypermode() && rand.nextInt(128) == 0) this.setMortorTicks(100);
			if (this.getMortorTicks() > 20 && this.getMortorTicks() % 20 == 0) this.createCrystalMortors();
		}

		if (this.ticksExisted % 120 == 0) TragicMC.logInfo("Aegar health is at " + this.getHealth());
	}

	private void createCrystalMortors() {
		TragicMC.logInfo("Mortor created.");

		double d0 = this.getAttackTarget().posX - this.posX;
		double d1 = rand.nextInt(4);
		double d2 = this.getAttackTarget().posZ - this.posZ;
		float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.975F;

		EntityCrystalMortor mortor = new EntityCrystalMortor(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
		mortor.posY = this.posY + this.height + 0.5D;
		mortor.posX += d0 * 0.04335D;
		mortor.posZ += d2 * 0.04335D;
		mortor.motionY += 0.36D * f1;
		this.worldObj.spawnEntityInWorld(mortor);
	}

	private void fireLaser() {
		TragicMC.logInfo("Laser fired.");
		boolean flag = this.canEntityBeSeen(this.getAttackTarget());

		if (this.worldObj.isRemote)
		{
			if (flag)
			{
				double d0 = this.getAttackTarget().posX - this.posX + 0.5D;
				double d1 = this.getAttackTarget().posY - this.posY + 2.45D;
				double d2 = this.getAttackTarget().posZ - this.posZ + 0.5D;

				for (int i = 0; i < 8; i++)
				{
					double d3 = 0.12D * i + (rand.nextDouble() * 0.25D);
					this.worldObj.spawnParticle("flame", this.posX + d0 * d3, this.posY + d1 * d3 + 0.45D, this.posZ + d2 * d3, 0.0, 0.0, 0.0);
				}
			}
		}
		else
		{
			if (flag)
			{
				this.getAttackTarget().attackEntityFrom(DamageHelper.causeArmorPiercingDamageToEntity(this), 2.0F + rand.nextFloat());
			}
		}
	}

	private void onCrystalDestruction() {
		this.setHypermode(true);
		super.attackEntityFrom(DamageSource.magic, 75.0F);
		this.setStunTicks(120);

		if (this.getShockwaveTicks() > 0) this.setShockwaveTicks(0);
		if (this.getHurtTime() > 0) this.setHurtTime(0);
		if (this.getAttackTime() > 0) this.setAttackTime(0);
		if (this.getLaserTicks() > 0) this.setLaserTicks(0);
		if (this.getMortorTicks() > 0) this.setMortorTicks(0);
	}

	public int getTotalArmorValue()
	{
		return 24;
	}

	@Override
	protected void fall(float par1) {}

	@Override
	public void addPotionEffect(PotionEffect effect) {}

	@Override
	public void setFire(int i) {}

	@Override
	public void onStruckByLightning(EntityLightningBolt bolt) {}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

	@Override
	public World getWorld() {
		return this.worldObj;
	}

	public boolean canRenderOnFire()
	{
		return false;
	}

	public boolean canBeCollidedWith()
	{
		return false;
	}

	public boolean attackEntityFrom(DamageSource source, float damage)
	{
		return false;
	}

	@Override
	public Entity[] getParts()
	{
		return this.aegarParts;
	}

	@Override
	public boolean attackEntityFromPart(EntityPart entity, DamageSource source, float damage) {
		if (this.worldObj.isRemote || source.isFireDamage() || source == DamageSource.drown) return false;
		TragicMC.logInfo("Aegar part hit was " + entity.partName);
		if (this.getHurtTime() == 0) this.setHurtTime(10);

		if (source.canHarmInCreative())
		{
			super.attackEntityFrom(source, damage);
			return true;
		}

		if (this.aegarCrystal.getHealth() <= 0)
		{
			damage *= 1.15F;
		}
		else
		{
			if (entity == this.aegarCrystal)
			{
				damage *= 1.35F;
			}
			else
			{
				damage *= 0.95F;
			}
		}

		TragicMC.logInfo("Damage done to Aegar was " + damage);
		if (source.getEntity() instanceof EntityLivingBase) super.attackEntityFrom(source, damage);

		return true;
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (this.worldObj.isRemote || this.getAttackTime() != 0 || this.getStunTicks() > 0 || this.getDistanceToEntity(par1Entity) > 3.0F || this.getShockwaveTicks() > 0 || this.getLaserTicks() > 0) return false;
		boolean result = super.attackEntityAsMob(par1Entity);
		if (result)
		{
			this.setAttackTime(10);
			par1Entity.motionX *= 2.35D;
			par1Entity.motionZ *= 2.35D;
			if (par1Entity instanceof EntityPlayer && TragicNewConfig.allowHacked && rand.nextInt(6) == 0) ((EntityPlayer) par1Entity).addPotionEffect(new PotionEffect(TragicPotions.Hacked.id, 120, 0));
		}
		return result;
	}

	private void attackEntitiesInList(List list)
	{
		for (int i = 0; i < list.size(); ++i)
		{
			Entity entity = (Entity)list.get(i);

			if (entity instanceof EntityLivingBase)
			{
				entity.attackEntityFrom(DamageSource.causeMobDamage(this), 16.0F);
				if (entity instanceof EntityPlayer && TragicNewConfig.allowHacked && rand.nextInt(6) == 0) ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(TragicPotions.Hacked.id, 120, 0));

				entity.motionX *= 3.225D;
				entity.motionZ *= 3.225D;
				entity.motionY += 0.225D;
			}
		}
	}

	@Override
	public EntityPart getDefaultPart() {
		return this.aegarBody;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("hypermode")) this.setHypermode(tag.getBoolean("hypermode"));
		if (tag.hasKey("stunTicks")) this.setStunTicks(tag.getInteger("stunTicks"));
		if (tag.hasKey("shockwaveTicks")) this.setShockwaveTicks(tag.getInteger("shockwaveTicks"));
		if (tag.hasKey("attackTime")) this.setAttackTime(tag.getInteger("attackTime"));
		if (tag.hasKey("hurtTime")) this.setHurtTime(tag.getInteger("hurtTime"));
		if (tag.hasKey("laserTicks")) this.setLaserTicks(tag.getInteger("laserTicks"));
		if (tag.hasKey("mortorTicks")) this.setMortorTicks(tag.getInteger("mortorTicks"));
		if (tag.hasKey("crystalHealth")) this.aegarCrystal.setHealth(tag.getFloat("crystalHealth"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setBoolean("hypermode", this.getHypermode());
		tag.setInteger("stunTicks", this.getStunTicks());
		tag.setInteger("shockwaveTicks", this.getShockwaveTicks());
		tag.setInteger("attackTime", this.getAttackTime());
		tag.setInteger("hurtTime", this.getHurtTime());
		tag.setInteger("laserTicks", this.getLaserTicks());
		tag.setInteger("mortorTicks", this.getMortorTicks());
		tag.setFloat("crystalHealth", this.aegarCrystal.getHealth());
	}
}