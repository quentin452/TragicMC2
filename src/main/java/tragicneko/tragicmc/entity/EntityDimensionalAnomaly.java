package tragicneko.tragicmc.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicPotion;

public class EntityDimensionalAnomaly extends Entity {

    public EntityDimensionalAnomaly(World par1World) {
        super(par1World);
        this.setSize(0.675F, 1.225F);
        if (!par1World.isRemote) this.setTimeToLive(210 + rand.nextInt(100));
        this.preventEntitySpawning = true;
        this.isImmuneToFire = true;
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public boolean canBePushed() {
        return true;
    }

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();

        if (this.worldObj.isRemote) {
            for (int i = 0; i < 8; i++) {
                this.worldObj.spawnParticle(
                    "reddust",
                    this.posX + rand.nextDouble() - rand.nextDouble(),
                    this.posY + rand.nextDouble() * this.height,
                    this.posZ + rand.nextDouble() - rand.nextDouble(),
                    rand.nextFloat() * 2.25F,
                    rand.nextFloat() * 2.25F,
                    rand.nextFloat() * 2.25F);
            }

            if (this.ticksExisted >= this.getTimeToLive()) for (byte b = 0; b < 32; b++) this.worldObj.spawnParticle(
                "smoke",
                this.posX + rand.nextDouble() - rand.nextDouble(),
                this.posY + rand.nextDouble() * this.height,
                this.posZ + rand.nextDouble() - rand.nextDouble(),
                rand.nextDouble() - rand.nextDouble(),
                rand.nextDouble() - rand.nextDouble(),
                rand.nextDouble() - rand.nextDouble());
            return;
        }

        if (this.ticksExisted >= this.getTimeToLive()) {
            this.setDead();
            this.playSound("tragicmc:random.truncatedbeep", 0.2F, 0.8F);
        }

        if (!this.onGround) {
            this.motionY = -0.035D;
            this.moveEntity(0.0, -0.035, 0.0);
        }

        if (this.ticksExisted % 10 == 0 && this.ticksExisted + 10 < this.getTimeToLive())
            this.worldObj.playSoundAtEntity(this, "tragicmc:random.truncatedbeep", 0.2F, 1.8F);

        if (TragicConfig.allowDivinity && this.ticksExisted % 5 == 0) {
            List<EntityLivingBase> list = this.worldObj
                .getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(1.0, 1.0, 1.0));
            for (EntityLivingBase e : list) {
                if (e.getCreatureAttribute() != TragicEntities.Synapse || e instanceof EntityPlayer) {
                    e.addPotionEffect(new PotionEffect(TragicPotion.Divinity.id, 200 + rand.nextInt(200)));
                    this.setDead();
                }
            }
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float par2) {
        return false;
    }

    @Override
    protected void entityInit() {
        this.dataWatcher.addObject(2, Integer.valueOf(0));
    }

    public int getTimeToLive() {
        return this.dataWatcher.getWatchableObjectInt(2);
    }

    public void setTimeToLive(int i) {
        this.dataWatcher.updateObject(2, i);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound var1) {
        if (var1.hasKey("timeToLive")) this.setTimeToLive(var1.getInteger("timeToLive"));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound var1) {
        var1.setInteger("timeToLive", this.getTimeToLive());
    }
}
