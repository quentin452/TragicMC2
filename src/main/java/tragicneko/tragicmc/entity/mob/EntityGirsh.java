package tragicneko.tragicmc.entity.mob;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCore;
import tragicneko.tragicmc.entity.projectile.EntityEnergyCharge;

public class EntityGirsh extends TragicMob {

    public EntityGirsh(World par1World) {
        super(par1World);
        this.setSize(1.85F, 1.85F);
        this.experienceValue = 5;
    }

    @Override
    protected boolean isChangeAllowed() {
        return false;
    }

    @Override
    protected boolean isAIEnabled() {
        return true;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(42.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(0.0);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(0.0);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(32.0);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(1.0);
    }

    @Override
    public int getTotalArmorValue() {
        return 12;
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return TragicEntities.Natural;
    }

    @Override
    protected boolean canCorrupt() {
        return false;
    }

    @Override
    public void onLivingUpdate() {
        this.motionX = this.motionY = this.motionZ = 0;
        super.onLivingUpdate();

        if (this.ticksExisted % 25 == 0) this.pullEntities();
        if (this.worldObj.isRemote) return;

        if (this.ticksExisted % 15 == 0 && rand.nextInt(4) == 0) this.fireEnergyCharges();

        int x = (int) (this.posX + rand.nextInt(2) - rand.nextInt(2));
        int y = (int) (this.posY + rand.nextInt(2) - rand.nextInt(2)) + ((int) this.height * 2 / 3);
        int z = (int) (this.posZ + rand.nextInt(2) - rand.nextInt(2));
        if (EntityOverlordCore.replaceableBlocks.contains(worldObj.getBlock(x, y, z)))
            this.worldObj.setBlock(x, y, z, TragicBlocks.Luminescence);
    }

    @Override
    public void onDeath(DamageSource src) {
        super.onDeath(src);

        if (!this.worldObj.isRemote && src.getEntity() instanceof EntityLivingBase) {
            this.spawnHomingCharges((EntityLivingBase) src.getEntity());
        }
    }

    public void pullEntities() {
        double d0 = 16.0D;
        List<EntityLivingBase> list = this.worldObj
            .getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(d0, d0, d0));
        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            EntityLivingBase entity = (EntityLivingBase) iterator.next();

            if (entity.canEntityBeSeen(this) && !(entity instanceof EntityGirsh)) {
                if (entity instanceof EntityPlayer) {
                    if (!((EntityPlayer) entity).capabilities.isCreativeMode) {
                        double d1 = entity.posX - this.posX;
                        double d2 = entity.posZ - this.posZ;
                        double d3 = entity.posY - this.posY;
                        float f2 = MathHelper.sqrt_double(d1 * d1 + d2 * d2 + d3 * d3);
                        double d4 = 0.5D;

                        entity.motionX = -d1 / f2 * d4 * 0.200000011920929D + entity.motionX * 0.30000000298023224D;
                        entity.motionZ = -d2 / f2 * d4 * 0.200000011920929D + entity.motionZ * 0.30000000298023224D;
                        entity.motionY = -d3 / f2 * d4 * 0.100000011920929D + entity.motionY * 0.30000000298023224D;
                        entity.moveEntity(entity.motionX, entity.motionY, entity.motionZ);
                    }
                } else {
                    double d1 = entity.posX - this.posX;
                    double d2 = entity.posZ - this.posZ;
                    double d3 = entity.posY - this.posY;
                    float f2 = MathHelper.sqrt_double(d1 * d1 + d2 * d2 + d3 * d3);
                    double d4 = 0.5D;

                    entity.motionX = -d1 / f2 * d4 * 0.200000011920929D + entity.motionX * 0.30000000298023224D;
                    entity.motionZ = -d2 / f2 * d4 * 0.200000011920929D + entity.motionZ * 0.30000000298023224D;
                    entity.motionY = -d3 / f2 * d4 * 0.100000011920929D + entity.motionY * 0.30000000298023224D;
                }
            }
        }
    }

    public void fireEnergyCharges() {
        int i = rand.nextInt(6) + 4;
        for (byte b = 0; b < i; b++) {
            double d0 = (rand.nextDouble() - rand.nextDouble()) * 0.25;
            double d1 = (rand.nextDouble() - rand.nextDouble()) * 0.25;
            double d2 = (rand.nextDouble() - rand.nextDouble()) * 0.25;
            EntityEnergyCharge charge = new EntityEnergyCharge(this.worldObj, this, d0, d1, d2);
            charge.setPosition(this.posX + d0 * 0.115, this.posY + d1 * 0.115, this.posZ + d2 * 0.115);
            this.worldObj.spawnEntityInWorld(charge);
        }
    }

    public void spawnHomingCharges(EntityLivingBase entity) {
        int i = rand.nextInt(6) + 4;
        for (byte b = 0; b < i; b++) {
            double d0 = (rand.nextDouble() - rand.nextDouble()) * 0.25;
            double d1 = (rand.nextDouble() - rand.nextDouble()) * 0.25;
            double d2 = (rand.nextDouble() - rand.nextDouble()) * 0.25;
            EntityEnergyCharge charge = new EntityEnergyCharge(this.worldObj, this, d0, d1, d2);
            charge.setPosition(this.posX + d0 * 0.115, this.posY + d1 * 0.115, this.posZ + d2 * 0.115);
            charge.setHoming();
            charge.setTarget(entity);
            this.worldObj.spawnEntityInWorld(charge);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        return false;
    }

    @Override
    public void fall(float par1) {}

    @Override
    public void updateFallState(double par1, boolean par2) {}

    @Override
    public String getLivingSound() {
        return null;
    }

    @Override
    public String getHurtSound() {
        return super.getHurtSound();
    }

    @Override
    public String getDeathSound() {
        return super.getHurtSound();
    }

    @Override
    public float getSoundPitch() {
        return super.getSoundPitch();
    }

    @Override
    public float getSoundVolume() {
        return super.getSoundVolume();
    }

    @Override
    protected void func_145780_a(int x, int y, int z, Block block) {

    }

    @Override
    public int getTalkInterval() {
        return super.getTalkInterval();
    }
}
