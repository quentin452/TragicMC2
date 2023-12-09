
package tragicneko.tragicmc.entity.miniboss;

import static tragicneko.tragicmc.TragicConfig.stinQueenStats;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.mob.EntityStin;
import tragicneko.tragicmc.entity.projectile.EntityWebBomb;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityStinQueen extends EntityGreaterStin {

    public EntityStinQueen(World par1World) {
        super(par1World);
        this.setSize(1.755F, 3.15F);
        this.stepHeight = 1.5F;
        this.superiorForm = null;
        this.experienceValue = 27;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(stinQueenStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(stinQueenStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(stinQueenStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(stinQueenStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(stinQueenStats[4]);
    }

    @Override
    public int getTotalArmorValue() {
        return (int) stinQueenStats[5];
    }

    @Override
    public int getChargeTicks() {
        return 0;
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

        if (this.worldObj.isRemote) this.setSize(1.755F, 3.15F);

        if (this.worldObj.isRemote) return;

        if (TragicConfig.allowStinBaby && TragicConfig.stinQueenBabies) {
            int la = this.getHealth() <= this.getMaxHealth() / 2 ? 4 : 8;

            if (this.ticksExisted % 30 == 0 && rand.nextInt(la) == 0
                && this.getAttackTarget() != null
                && this.getDistanceToEntity(this.getAttackTarget()) >= 4.0F) {
                for (int i = 0; i < 4; i++) {
                    this.spawnBabies();
                }

                ArrayList<int[]> list = WorldHelper
                    .getBlocksInSphericalRange(this.worldObj, 0.75D, this.posX, this.posY, this.posZ);
                int[] coords;

                for (int i = 0; i < list.size(); i++) {
                    coords = list.get(i);
                    if (this.worldObj.getBlock(coords[0], coords[1], coords[2]) == Blocks.air)
                        this.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.web);
                }
            }
        }

        if (this.isFiring()) {
            this.decrementFiringTicks();
            if (this.isCharging()) this.setChargeTicks(0);
            if (this.isGalloping()) this.setGallopTicks(0);
        }

        if (this.getAttackTarget() != null && !this.isCharging()
            && !this.isFiring()
            && this.getDistanceToEntity(this.getAttackTarget()) >= 8.0F
            && rand.nextInt(12) == 0
            && this.ticksExisted % 10 == 0
            && TragicConfig.stinQueenWebBombs) {
            this.setFiringTicks(50);
        }

        if (this.isFiring() && this.getFiringTicks() % 25 == 0 && this.getAttackTarget() != null) {
            this.doMortorFire();
        }
    }

    private void doMortorFire() {
        if (!TragicConfig.stinQueenWebBombs) return;
        double d0 = this.getAttackTarget().posX - this.posX + rand.nextInt(5) - rand.nextInt(5);
        double d1 = this.getAttackTarget().boundingBox.minY + this.getAttackTarget().height / 3.0F
            - (this.posY + this.height / 2.0F);
        double d2 = this.getAttackTarget().posZ - this.posZ + rand.nextInt(5) - rand.nextInt(5);
        float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.975F;

        EntityWebBomb mortor = new EntityWebBomb(
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

    public void spawnBabies() {
        if (!TragicConfig.allowStinBaby || !TragicConfig.stinQueenBabies) return;
        EntityStin baby = new EntityStin(this.worldObj);
        baby.setChild();
        baby.copyLocationAndAnglesFrom(this);

        double x = this.posX;
        double y = this.posY;
        double z = this.posZ;

        for (int y1 = -2; y1 < 4; y1++) {
            for (int z1 = -2; z1 < 3; z1++) {
                for (int x1 = -2; x1 < 3; x1++) {
                    if (World.doesBlockHaveSolidTopSurface(
                        this.worldObj,
                        (int) this.posX + x1,
                        (int) this.posY + y1 - 1,
                        (int) this.posZ + z1) && rand.nextBoolean()) {
                        baby.setPosition(x + x1, y + y1, z + z1);

                        if (this.worldObj.checkNoEntityCollision(baby.boundingBox)
                            && this.worldObj.getCollidingBoundingBoxes(baby, baby.boundingBox)
                                .isEmpty()
                            && !this.worldObj.isAnyLiquid(baby.boundingBox)) {
                            this.worldObj.spawnEntityInWorld(baby);
                            baby.onSpawnWithEgg(null);
                            if (this.getAttackTarget() != null) baby.setAttackTarget(this.getAttackTarget());
                            return;
                        }

                    }
                }
            }
        }
    }

    @Override
    protected boolean teleportEnemyAway(EntityLivingBase entity, boolean flag) {
        super.teleportEnemyAway(entity, flag);

        if (!TragicConfig.stinQueenWebs) return flag;
        ArrayList<int[]> list = WorldHelper
            .getBlocksInSphericalRange(this.worldObj, 0.5D, entity.posX, entity.posY, entity.posZ);
        int[] coords;

        for (int i = 0; i < list.size(); i++) {
            coords = list.get(i);
            if (this.worldObj.getBlock(coords[0], coords[1], coords[2]) == Blocks.air)
                this.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.web);
        }

        return flag;
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        if (this.worldObj.isRemote) return false;

        boolean flag = super.attackEntityAsMob(par1Entity);

        if (flag && rand.nextInt(8) == 0 && par1Entity instanceof EntityLivingBase) {
            if (TragicConfig.allowStun)
                ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotion.Stun.id, 60, 0));
        }

        return flag;
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
    public float getSoundPitch() {
        return 1.9F;
    }
}
