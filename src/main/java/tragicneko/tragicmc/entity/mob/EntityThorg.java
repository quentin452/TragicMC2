package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.thorgStats;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.projectile.EntityPoisonBarb;

public class EntityThorg extends TragicMob {

    private boolean hasJumped = false;

    public EntityThorg(World par1World) {
        super(par1World);
        this.setSize(1.175F, 2.15F);
        this.stepHeight = 1.0F;
        this.experienceValue = 5;
        this.getNavigator()
            .setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.tasks.addTask(5, new EntityAIWander(this, 0.55D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
        this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
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
            .setBaseValue(thorgStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(thorgStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(thorgStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(thorgStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(thorgStats[4]);
    }

    @Override
    public int getTotalArmorValue() {
        return (int) thorgStats[5];
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
        if (this.isPotionActive(Potion.poison)) this.removePotionEffect(Potion.poison.id);
        if (TragicConfig.allowStun && this.isPotionActive(TragicPotion.Stun))
            this.removePotionEffect(TragicPotion.Stun.id);

        super.onLivingUpdate();

        if (this.worldObj.isRemote) return;

        if (this.isInsideOfMaterial(Material.water) && this.ticksExisted % 20 == 0) this.heal(1.0F);
        if (this.worldObj.isRaining()
            && this.worldObj.canBlockSeeTheSky((int) this.posX, (int) this.posY, (int) this.posZ)
            && this.ticksExisted % 20 == 0) this.heal(1.0F);

        if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) >= 6.0
            && this.onGround
            && this.ticksExisted % 5 == 0) {
            this.jump();
            this.hasJumped = true;
        }

        if (this.getAttackTarget() != null && this.hasJumped && this.ticksExisted % 3 == 0) {
            double d0 = this.getAttackTarget().posX - this.posX;
            double d1 = this.getAttackTarget().boundingBox.minY + this.getAttackTarget().height / 2.0F
                - (this.posY + this.height / 2.0F);
            double d2 = this.getAttackTarget().posZ - this.posZ;

            EntityPoisonBarb barb = new EntityPoisonBarb(this.worldObj, this, d0, d1, d2);
            barb.posY = this.posY + (this.height * 2 / 3);
            this.worldObj.spawnEntityInWorld(barb);
        }
    }

    @Override
    public void fall(float par1) {
        super.fall(par1);
        this.hasJumped = false;
    }

    @Override
    public boolean attackEntityFrom(DamageSource src, float dmg) {
        if (this.worldObj.isRemote) return false;

        return super.attackEntityFrom(src, dmg);
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        if (this.worldObj.isRemote) return false;

        return super.attackEntityAsMob(par1Entity);
    }
}
