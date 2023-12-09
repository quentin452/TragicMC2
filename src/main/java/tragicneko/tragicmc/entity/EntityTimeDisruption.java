package tragicneko.tragicmc.entity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.entity.boss.EntityTimeController;

public class EntityTimeDisruption extends Entity {

    public int timeToLive;

    public EntityTimeDisruption(World par1World) {
        super(par1World);
        this.setSize(0.475F, 0.825F);
        this.timeToLive = rand.nextInt(800);
        this.preventEntitySpawning = true;
        this.isImmuneToFire = true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float par1) {
        return 15728880;
    }

    @Override
    public float getBrightness(float par1) {
        return 1.0F;
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
    public void onUpdate() {
        this.motionX = this.motionY = this.motionZ = 0.0D;

        super.onUpdate();

        if (this.worldObj.isRemote) {
            for (int i = 0; i < 3; i++) {
                double d7 = (rand.nextDouble() * 3.0D - rand.nextDouble() * 3.0D);
                double d8 = (rand.nextDouble() * 3.0D - rand.nextDouble() * 3.0D);
                double d9 = (rand.nextDouble() * 3.0D - rand.nextDouble() * 3.0D);

                double d0 = d7 + this.posX;
                double d1 = d8 + this.posY + this.height / 2.0D;
                double d2 = d9 + this.posZ;

                float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
                double d3 = 0.5D;

                double d4 = d0 / f2 * d3 * 0.200000011920929D + d7 * 0.20000000298023224D;
                double d5 = d1 / f2 * d3 * 0.200000011920929D + d8 * 0.20000000298023224D;
                double d6 = d2 / f2 * d3 * 0.200000011920929D + d9 * 0.20000000298023224D;

                this.worldObj.spawnParticle("portal", d0, d1, d2, d4 * 15.5, d5 * 15.5, d6 * 15.5);
            }

            if (rand.nextBoolean()) {
                this.worldObj.spawnParticle(
                    "witchMagic",
                    this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.255D),
                    this.posY + 0.0625D + rand.nextDouble() * 0.275D,
                    this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.255D),
                    rand.nextFloat() - rand.nextFloat(),
                    0.0,
                    rand.nextFloat() - rand.nextFloat());
            }

            if (rand.nextInt(64) == 0) {
                for (int l = 0; l < 6; ++l) {
                    this.worldObj.spawnParticle(
                        "instantSpell",
                        this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.155D),
                        this.posY + rand.nextDouble(),
                        this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.155D),
                        0.155F * this.rand.nextFloat(),
                        0.155F * this.rand.nextFloat(),
                        0.155F * this.rand.nextFloat());
                }
            }
            return;
        }

        if (this.ticksExisted % 10 == 0) {
            double d0 = 3.0D;
            List<EntityLivingBase> list = this.worldObj
                .getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(d0, d0, d0));
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                EntityLivingBase entity = (EntityLivingBase) iterator.next();

                if (entity.canEntityBeSeen(this) && !(entity instanceof EntityTimeController)) {
                    for (int i = 0; i < 5; i++) {
                        entity.attackEntityFrom(DamageSource.magic, 1.0F);
                        entity.motionX = (rand.nextDouble() - rand.nextDouble()) * 0.45D + entity.motionX;
                        entity.motionZ = (rand.nextDouble() - rand.nextDouble()) * 0.45D + entity.motionZ;
                        entity.motionY = (rand.nextDouble() - rand.nextDouble()) * 0.25D + entity.motionY;
                    }
                }
            }
        }

        if (this.ticksExisted >= 400 + timeToLive) this.setDead();
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float par2) {
        if (this.worldObj.isRemote || this.isEntityInvulnerable()) return false;

        if (source.getEntity() != null) {
            this.setDead();
            this.setBeenAttacked();

            if (!this.worldObj.isRemote) {
                boolean flag = this.worldObj.getGameRules()
                    .getGameRuleBooleanValue("mobGriefing");
                this.worldObj.createExplosion((Entity) null, this.posX, this.posY, this.posZ, 1.0F, flag);
            }
            return true;
        }
        return false;
    }

    @Override
    protected void entityInit() {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound var1) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound var1) {

    }

}
