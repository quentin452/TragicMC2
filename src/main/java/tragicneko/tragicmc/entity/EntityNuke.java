package tragicneko.tragicmc.entity;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import tragicneko.tragicmc.entity.alpha.EntityOverlordCore;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityNuke extends Entity {

    public int nukeStage = 0;
    public int stageTime = 0;

    public EntityNuke(World world) {
        super(world);
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
    public boolean attackEntityFrom(DamageSource source, float par2) {
        if (this.worldObj.isRemote || this.isEntityInvulnerable() || source.isExplosion() || this.nukeStage > 0)
            return false;

        this.setDead();
        this.setBeenAttacked();

        if (!this.worldObj.getGameRules()
            .getGameRuleBooleanValue("doMobLoot")) return true;

        // this.entityDropItem(new ItemStack(TragicItems.Nuke), 0.4F);
        return true;
    }

    @Override
    protected void entityInit() {}

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag) {
        if (tag.hasKey("nukeStage")) this.nukeStage = tag.getInteger("nukeStage");
        if (tag.hasKey("stageTime")) this.stageTime = tag.getInteger("stageTime");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag) {
        tag.setInteger("nukeStage", this.nukeStage);
        tag.setInteger("stageTime", this.stageTime);
    }

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();

        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.nukeStage == 1 && this.stageTime % 20 == 0 && this.stageTime < 199)
            this.worldObj.playSoundAtEntity(this, "tragicmc:random.truncatedbeep", 1.9F, stageTime > 130 ? 1.9F : 1.0F);

        if (this.nukeStage > 0) this.stageTime++;
        if (this.stageTime > 200) {
            this.nukeStage++;
            this.stageTime = 0;
        }

        if (this.nukeStage > 0) {
            this.worldObj.spawnParticle(
                "cloud",
                this.posX + rand.nextDouble() - rand.nextDouble(),
                this.posY,
                this.posZ + rand.nextDouble() - rand.nextDouble(),
                0,
                0,
                0);

            if (this.nukeStage > 1) {
                this.setInvisible(true);
                for (int i = 0; i < 12; i++) this.worldObj.spawnParticle(
                    "hugeexplosion",
                    this.posX + rand.nextInt(18) - rand.nextInt(18),
                    this.posY + (this.stageTime % 50),
                    this.posZ + rand.nextInt(18) - rand.nextInt(18),
                    0,
                    rand.nextDouble() * 0.5D,
                    0);
            }
        }

        if (this.nukeStage == 2 && !this.worldObj.isRemote) {
            this.worldObj.createExplosion(
                this,
                this.posX + rand.nextInt(16) - rand.nextInt(16),
                this.posY + (this.stageTime % 50),
                this.posZ + rand.nextInt(12) - rand.nextInt(12),
                4.0F,
                true);

            if (this.stageTime % 10 == 0) {
                this.worldObj.createExplosion(
                    this,
                    this.posX + rand.nextDouble() - rand.nextDouble(),
                    this.posY + (this.stageTime % 50) - 5,
                    this.posZ + rand.nextDouble() - rand.nextDouble(),
                    6.0F + 2.5F * rand.nextFloat(),
                    true);
            }

            this.worldObj.createExplosion(
                this,
                this.posX + rand.nextInt(32) - rand.nextInt(32),
                this.posY + rand.nextInt(4),
                this.posZ + rand.nextInt(24) - rand.nextInt(24),
                2.5F,
                true);
        }

        if (this.nukeStage == 3) {
            if (this.worldObj.isRemote) {
                for (int i = 0; i < 8; i++) this.worldObj.spawnParticle(
                    "cloud",
                    this.posX + rand.nextInt(12) - rand.nextInt(12),
                    this.posY + rand.nextInt(4),
                    this.posZ + rand.nextInt(12) - rand.nextInt(12),
                    0,
                    0,
                    0);
            } else {
                this.worldObj.createExplosion(
                    this,
                    this.posX + rand.nextInt(32) - rand.nextInt(32),
                    this.posY + (this.stageTime % 25),
                    this.posZ + rand.nextInt(32) - rand.nextInt(32),
                    6.0F,
                    true);

                if (this.stageTime % 5 == 0) {
                    this.worldObj.createExplosion(
                        this,
                        this.posX + rand.nextInt(12) - rand.nextInt(12),
                        this.posY + (this.stageTime % 50) - 10,
                        this.posZ + rand.nextInt(6) - rand.nextInt(6),
                        12.0F + 7.5F * rand.nextFloat(),
                        true);
                }

                for (int i = 0; i < 4; i++) this.worldObj.createExplosion(
                    this,
                    this.posX + rand.nextInt(64) - rand.nextInt(64),
                    this.posY + rand.nextInt(12) - 5,
                    this.posZ + rand.nextInt(64) - rand.nextInt(64),
                    4.5F,
                    true);
            }
        }

        if (this.nukeStage > 3) {
            this.setDead();

            for (int i = 0; i < 14; i++) {
                ArrayList<int[]> list = WorldHelper.getBlocksInSphericalRange(
                    this.worldObj,
                    6.0F,
                    this.posX + rand.nextInt(32) - rand.nextInt(32),
                    this.posY + rand.nextInt(8) - rand.nextInt(16),
                    this.posZ + rand.nextInt(32) - rand.nextInt(32));

                for (int[] coords : list) if (rand.nextInt(8) == 0
                    && EntityOverlordCore.replaceableBlocks
                        .contains(this.worldObj.getBlock(coords[0], coords[1], coords[2]))
                    && World.doesBlockHaveSolidTopSurface(worldObj, coords[0], coords[1] - 1, coords[2]))
                    this.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.fire);
                else if (this.worldObj.getBlock(coords[0], coords[1], coords[2])
                    .getMaterial() == Material.water) this.worldObj.setBlockToAir(coords[0], coords[1], coords[2]);
            }
        }

        if (!this.worldObj.isRemote && rand.nextInt(8) == 0 && this.nukeStage >= 2) {
            ArrayList<int[]> list = WorldHelper.getBlocksInSphericalRange(
                this.worldObj,
                6.0F,
                this.posX + rand.nextInt(32) - rand.nextInt(32),
                this.posY + rand.nextInt(8),
                this.posZ + rand.nextInt(32) - rand.nextInt(32));

            for (int[] coords : list) if (rand.nextInt(8) == 0
                && EntityOverlordCore.replaceableBlocks
                    .contains(this.worldObj.getBlock(coords[0], coords[1], coords[2]))
                && World.doesBlockHaveSolidTopSurface(worldObj, coords[0], coords[1] - 1, coords[2]))
                this.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.fire);
            else if (this.worldObj.getBlock(coords[0], coords[1], coords[2])
                .getMaterial() == Material.water) this.worldObj.setBlockToAir(coords[0], coords[1], coords[2]);
        }
    }

    @Override
    public boolean interactFirst(EntityPlayer player) {
        if (player.getCurrentEquippedItem() != null) {
            ItemStack item = player.getCurrentEquippedItem();
            if (item.getItem() == Items.shears && this.nukeStage == 1) {
                this.nukeStage = 0;
                this.attackEntityFrom(DamageSource.causePlayerDamage(player), 1.0F);
                this.worldObj.playSoundAtEntity(this, "random.fizz", 1.0F, 1.0F);
            } else if (item.getItem() == Items.flint_and_steel && this.nukeStage == 0) {
                this.nukeStage = 1;
            }
        }
        return false;
    }

}
