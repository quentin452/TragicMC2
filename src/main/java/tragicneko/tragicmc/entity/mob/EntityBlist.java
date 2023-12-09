package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.blistStats;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.entity.EntityAIWatchTarget;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityBlist extends TragicMob {

    private double[] motions = new double[] { 0, 0, 0 };
    private int chargeBuffer = 80;
    private int[] attachedBlock;
    private int[] gridCoords;
    private boolean isAttached = false;

    private static final IEntitySelector selec = new IEntitySelector() {

        @Override
        public boolean isEntityApplicable(Entity entity) {
            return !(entity instanceof EntityBlist);
        }
    };

    public EntityBlist(World par1World) {
        super(par1World);
        this.setSize(0.45F, 0.55F);
        this.experienceValue = 10;
        this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
        this.tasks.addTask(4, new EntityAIWatchTarget(this, 64.0F));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityAnimal.class, 0, true, false));
        this.targetTasks
            .addTask(4, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, true, false, selec)); // TODO
                                                                                                                   // change
                                                                                                                   // to
                                                                                                                   // not
                                                                                                                   // target
                                                                                                                   // other
                                                                                                                   // Blist
    }

    @Override
    protected boolean isChangeAllowed() {
        return false;
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(blistStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(blistStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(blistStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(blistStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(blistStats[4]);
    }

    @Override
    public int getTotalArmorValue() {
        return (int) blistStats[5];
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
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, Integer.valueOf(0)); // charge ticks
    }

    private void setChargeTicks(int i) {
        this.dataWatcher.updateObject(16, i);
    }

    public int getChargeTicks() {
        return this.dataWatcher.getWatchableObjectInt(16);
    }

    @Override
    public void onLivingUpdate() {
        if (this.worldObj.isRemote || this.getChargeTicks() <= 0) this.motionX = this.motionY = this.motionZ = 0;
        super.onLivingUpdate();

        if (this.worldObj.isRemote) return;

        if (this.chargeBuffer > 0) --this.chargeBuffer;
        if (this.getChargeTicks() > 0) {
            this.isAttached = false;
            this.setChargeTicks(this.getChargeTicks() - 1);
            this.chargeBuffer = 60;

            if (this.isCollidedHorizontally) {
                this.setChargeTicks(0);

                double x = this.motions[0];
                double y = this.motions[1];
                double z = this.motions[2];
                this.motions = new double[] { 0, 0, 0 };

                float f = MathHelper.sqrt_double(x * x + z * z + y * y) * 5.0F;
                if (f >= 1.0F) {
                    if (f > 5.0F) f = 5.0F;
                    this.worldObj.createExplosion(
                        this,
                        this.posX,
                        this.posY,
                        this.posZ,
                        1.5F + rand.nextFloat(),
                        this.getMobGriefing());
                    this.setDead();
                    // TODO play death sound here
                }
            }
            this.motionX = this.motions[0] * 0.98;
            this.motionY = this.motions[1] * 0.98;
            this.motionZ = this.motions[2] * 0.98;
        } else {
            this.gridCoords = new int[] { (int) this.posX, (int) this.posY, (int) this.posZ };
            if (this.getAttackTarget() != null && this.canEntityBeSeen(this.getAttackTarget())
                && this.ticksExisted % 5 == 0
                && this.chargeBuffer == 0) {
                double d0 = MathHelper.clamp_double(this.getAttackTarget().posX - this.posX, -3.4D, 3.4D);
                double d1 = MathHelper.clamp_double(
                    this.getAttackTarget().posY + this.getAttackTarget().height / 2.0
                        - (this.posY + this.height / 2.0F),
                    -3.4D,
                    3.4D);
                double d2 = MathHelper.clamp_double(this.getAttackTarget().posZ - this.posZ, -3.4D, 3.4D);

                double d = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
                    .getAttributeValue();

                this.motions = new double[] { d0 * d, d1 * d, d2 * d };
                this.setChargeTicks(15);
            }

            this.attachedBlock = null;
            ArrayList<int[]> adj = WorldHelper.getBlocksAdjacent(this.gridCoords);

            for (int[] coord : adj) {
                Block block = this.worldObj.getBlock(coord[0], coord[1], coord[2]);
                if (block.getMaterial()
                    .blocksMovement() && block.isOpaqueCube()) {
                    this.attachedBlock = coord;
                    break;
                }
            }

            if (this.attachedBlock != null) {
                this.isAttached = true;
            } else {
                this.isAttached = false;
                this.moveEntity(0.0, -0.1, 0.0);
            }
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        if (this.worldObj.isRemote || this.getChargeTicks() <= 0 || this.isAttached) return false;

        double x = this.motions[0];
        double y = this.motions[1];
        double z = this.motions[2];

        float f = MathHelper.sqrt_double(x * x + z * z + y * y) * 5.0F;
        if (f > 5.0F) f = 5.0F;
        boolean flag = par1Entity.attackEntityFrom(
            DamageSource.causeMobDamage(this),
            (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
                .getAttributeValue());

        if (flag && this.getChargeTicks() > 0 && f >= 1.0F) {
            this.worldObj
                .createExplosion(this, this.posX, this.posY, this.posZ, 1.5F + rand.nextFloat(), this.getMobGriefing());
            this.setDead();
            // TODO play death sound here
        }
        return flag;
    }

    @Override
    public void fall(float par1) {
        if (this.getChargeTicks() <= 0) super.fall(par1);
    }

    @Override
    public void updateFallState(double par1, boolean par2) {
        super.updateFallState(par1, par2);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("chargeTicks")) this.setChargeTicks(tag.getInteger("chageTicks"));
        if (tag.hasKey("chargeBuffer")) this.chargeBuffer = tag.getInteger("chargeBuffer");
        if (tag.hasKey("chargeX") && tag.hasKey("chargeY") && tag.hasKey("chargeZ")) this.motions = new double[] {
            tag.getDouble("chargeX"), tag.getDouble("chargeY"), tag.getDouble("chargeZ") };
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setInteger("chargeTicks", this.getChargeTicks());
        tag.setInteger("chargeBuffer", this.chargeBuffer);
        tag.setDouble("chargeX", this.motions[0]);
        tag.setDouble("chargeY", this.motions[1]);
        tag.setDouble("chargeZ", this.motions[2]);
    }

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
