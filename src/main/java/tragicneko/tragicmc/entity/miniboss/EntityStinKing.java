
package tragicneko.tragicmc.entity.miniboss;

import static tragicneko.tragicmc.TragicConfig.stinKingStats;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.projectile.EntityDarkMortor;

public class EntityStinKing extends EntityGreaterStin {

    public EntityStinKing(World par1World) {
        super(par1World);
        this.setSize(1.7835F, 5.15F);
        this.stepHeight = 1.5F;
        this.experienceValue = 27;
        this.superiorForm = null;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(stinKingStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(stinKingStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(stinKingStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(stinKingStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(stinKingStats[4]);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(21, Integer.valueOf(0));
    }

    public int getFiringTicks() {
        return this.dataWatcher.getWatchableObjectInt(21);
    }

    protected void setFiringTicks(int i) {
        this.dataWatcher.updateObject(21, i);
    }

    protected void decrementFiringTicks() {
        int pow = this.getFiringTicks();
        this.setFiringTicks(--pow);
    }

    public boolean isFiring() {
        return this.getFiringTicks() > 0;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (this.worldObj.isRemote) {
            this.setSize(1.7835F, 5.15F);
        } else {
            if (this.isFiring()) {
                this.decrementFiringTicks();
                if (this.isCharging()) this.setChargeTicks(0);
                if (this.isGalloping()) this.setGallopTicks(0);
                if (this.getAttackTarget() == null) this.setFiringTicks(0);
            }

            if (this.getAttackTarget() != null && this.ticksExisted % 10 == 0
                && rand.nextInt(256) == 0
                && TragicConfig.allowFear) {
                this.getAttackTarget()
                    .addPotionEffect(new PotionEffect(TragicPotion.Fear.id, 60 + rand.nextInt(160), rand.nextInt(4)));
            }

            if (this.getAttackTarget() != null && !this.isCharging()
                && !this.isFiring()
                && this.getDistanceToEntity(this.getAttackTarget()) >= 6.0F
                && rand.nextInt(12) == 0
                && this.ticksExisted % 10 == 0
                && TragicConfig.stinKingMortors) {
                this.setFiringTicks(80);
            }

            if (this.isFiring() && this.getFiringTicks() % 10 == 0 && this.getAttackTarget() != null) {
                this.doMortorFire();
            }
        }
    }

    private void doMortorFire() {
        if (!TragicConfig.stinKingMortors) return;
        double d0 = this.getAttackTarget().posX - this.posX + rand.nextInt(5) - rand.nextInt(5);
        double d1 = this.getAttackTarget().boundingBox.minY + this.getAttackTarget().height / 3.0F
            - (this.posY + this.height / 2.0F);
        double d2 = this.getAttackTarget().posZ - this.posZ + rand.nextInt(5) - rand.nextInt(5);
        float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.975F;

        EntityDarkMortor mortor = new EntityDarkMortor(
            this.worldObj,
            this,
            d0 + this.rand.nextGaussian() * f1,
            d1,
            d2 + this.rand.nextGaussian() * f1);
        mortor.posY = this.posY + this.height + 0.5D;
        mortor.posX += d0 * 0.04335D;
        mortor.posZ += d2 * 0.04335D;
        mortor.motionY += 0.66D * f1;
        this.worldObj.spawnEntityInWorld(mortor);
    }

    @Override
    public int getTotalArmorValue() {
        return (int) stinKingStats[5];
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (this.worldObj.isRemote || rand.nextInt(32) == 0) return false;
        return super.attackEntityFrom(par1DamageSource, par2);
    }

    @Override
    protected boolean isChangeAllowed() {
        return false;
    }

    @Override
    public Class getLesserForm() {
        return EntityGreaterStin.class;
    }

    @Override
    public String getHurtSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.stin.king" : super.getHurtSound();
    }

    @Override
    public String getDeathSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.stin.king" : null;
    }
}
