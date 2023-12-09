package tragicneko.tragicmc.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.entity.boss.EntityAegar;
import tragicneko.tragicmc.entity.boss.EntityApis;
import tragicneko.tragicmc.entity.boss.EntityClaymation;
import tragicneko.tragicmc.entity.boss.EntityDeathReaper;
import tragicneko.tragicmc.entity.boss.EntityEnyvil;
import tragicneko.tragicmc.entity.boss.EntityKitsune;
import tragicneko.tragicmc.entity.boss.EntityPolaris;
import tragicneko.tragicmc.entity.boss.EntityTimeController;
import tragicneko.tragicmc.entity.boss.EntityYeti;
import tragicneko.tragicmc.entity.miniboss.EntityGreaterStin;
import tragicneko.tragicmc.entity.miniboss.EntityJarra;
import tragicneko.tragicmc.entity.miniboss.EntityKragul;
import tragicneko.tragicmc.entity.miniboss.EntityMagmox;
import tragicneko.tragicmc.entity.miniboss.EntityMegaCryse;
import tragicneko.tragicmc.entity.miniboss.EntityStinKing;
import tragicneko.tragicmc.entity.miniboss.EntityStinQueen;
import tragicneko.tragicmc.entity.miniboss.EntityVoxStellarum;

public class EntityStatue extends Entity {

    public EntityStatue(World world) {
        super(world);
        this.preventEntitySpawning = true;
        this.setSize(0.525F, 0.865F);
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
    public boolean attackEntityFrom(DamageSource source, float par2) {
        if (this.worldObj.isRemote || this.isEntityInvulnerable() || source.isExplosion()) return false;

        this.setDead();
        this.setBeenAttacked();

        if (!this.worldObj.getGameRules()
            .getGameRuleBooleanValue("doMobLoot")) return true;

        ItemStack stack = new ItemStack(TragicItems.MobStatue, 1, this.getMobID());
        stack.stackTagCompound = new NBTTagCompound();
        stack.stackTagCompound.setInteger("textureID", this.getTextureID());
        if (this.getAnimated()) stack.stackTagCompound.setInteger("isAnimated", this.getAnimated() ? 1 : 0);

        this.entityDropItem(stack, 0.4F);
        return true;
    }

    public int getMobID() {
        return this.dataWatcher.getWatchableObjectInt(2);
    }

    public void setMobID(int i) {
        this.dataWatcher.updateObject(2, i);
    }

    public int getTextureID() {
        return this.dataWatcher.getWatchableObjectInt(3);
    }

    public void setTextureID(int i) {
        this.dataWatcher.updateObject(3, i);
    }

    public void incrementRotationAngle() {
        float pow = this.dataWatcher.getWatchableObjectFloat(4) + 45.0F;
        this.setRotation(pow);
    }

    public void setRotation(float f) {
        this.dataWatcher.updateObject(4, Math.abs(f) % 360.0F);
    }

    public float getRotation() {
        return this.dataWatcher.getWatchableObjectFloat(4);
    }

    public void setAnimated(boolean flag) {
        this.dataWatcher.updateObject(5, flag ? 1 : 0);
    }

    public boolean getAnimated() {
        return this.dataWatcher.getWatchableObjectInt(5) == 1;
    }

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();

        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (!this.worldObj.isRemote && this.getRotation() > 360.0F) this.setRotation(this.getRotation() - 360.0F);

        List<Entity> list = this.worldObj
            .getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0.0, 0.8, 0.0));
        for (Entity e : list) {
            this.applyEntityCollision(e);
            e.velocityChanged = true;
        }

    }

    @Override
    public AxisAlignedBB getBoundingBox() {
        return this.boundingBox;
    }

    @Override
    protected void entityInit() {
        this.dataWatcher.addObject(2, Integer.valueOf(0));
        this.dataWatcher.addObject(3, Integer.valueOf(0));
        this.dataWatcher.addObject(4, Float.valueOf(0));
        this.dataWatcher.addObject(5, Integer.valueOf(0));
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag) {
        if (tag.hasKey("mobID")) this.setMobID(tag.getInteger("mobID"));
        if (tag.hasKey("rotation")) this.setRotation(tag.getFloat("rotation"));
        if (tag.hasKey("textureID")) this.setTextureID(tag.getInteger("textureID"));
        if (tag.hasKey("animated")) this.setAnimated(tag.getInteger("animated") == 1);
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag) {
        tag.setInteger("mobID", this.getMobID());
        tag.setFloat("rotation", this.getRotation());
        tag.setInteger("textureID", this.getTextureID());
        tag.setBoolean("animated", this.getAnimated());
    }

    @Override
    public boolean interactFirst(EntityPlayer player) {
        if (player.worldObj.isRemote) return false;

        if (player.getCurrentEquippedItem() != null) {
            ItemStack item = player.getCurrentEquippedItem();

            if (item.getItem() == TragicItems.LivingClay) {
                int id = this.getMobID();
                EntityLivingBase entity = null;

                switch (id) {
                    case 0:
                        if (TragicConfig.allowApis) entity = new EntityApis(this.worldObj);
                        break;
                    case 1:
                        if (TragicConfig.allowKitsunakuma) entity = new EntityKitsune(this.worldObj);
                        break;
                    case 2:
                        if (TragicConfig.allowSkultar) entity = new EntityDeathReaper(this.worldObj);
                        break;
                    case 3:
                        if (TragicConfig.allowTimeController) entity = new EntityTimeController(this.worldObj);
                        break;
                    case 4:
                        if (TragicConfig.allowEmpariah) entity = new EntityYeti(this.worldObj);
                        break;
                    case 5:
                        if (TragicConfig.allowPolaris) entity = new EntityPolaris(this.worldObj);
                        break;
                    case 6:
                        if (TragicConfig.allowJarra) entity = new EntityJarra(this.worldObj);
                        break;
                    case 7:
                        if (TragicConfig.allowKragul) entity = new EntityKragul(this.worldObj);
                        break;
                    case 8:
                        if (TragicConfig.allowMagmox) entity = new EntityMagmox(this.worldObj);
                        break;
                    case 9:
                        if (TragicConfig.allowMegaCryse) entity = new EntityMegaCryse(this.worldObj);
                        break;
                    case 10:
                        if (TragicConfig.allowStinKing) entity = new EntityStinKing(this.worldObj);
                        break;
                    case 11:
                        if (TragicConfig.allowStinQueen) entity = new EntityStinQueen(this.worldObj);
                        break;
                    case 12:
                        if (TragicConfig.allowGreaterStin) entity = new EntityGreaterStin(this.worldObj);
                        break;
                    case 13:
                        if (TragicConfig.allowVoxStellarum) entity = new EntityVoxStellarum(this.worldObj);
                        break;
                    case 14:
                        if (TragicConfig.allowEnyvil) entity = new EntityEnyvil(this.worldObj);
                        break;
                    case 15:
                        if (TragicConfig.allowClaymation) entity = new EntityClaymation(this.worldObj);
                        break;
                    case 16:
                        if (TragicConfig.allowAegar) entity = new EntityAegar(this.worldObj);
                        break;
                    case 17:
                    case 18:
                    case 19:
                        return false;
                    default:
                        break;
                }

                if (entity != null) {
                    entity.copyLocationAndAnglesFrom(this);
                    ((EntityLiving) entity).onSpawnWithEgg(null);
                    this.worldObj.removeEntity(this);
                    this.worldObj.spawnEntityInWorld(entity);
                    if (!player.capabilities.isCreativeMode) item.stackSize--;
                }
            } else {
                byte b0 = 0;

                if (item.getItem() == Items.iron_ingot) {
                    b0 = 1;
                } else if (item.getItem() == Items.gold_ingot) {
                    b0 = 2;
                } else if (item.getItem() == Items.diamond) {
                    b0 = 3;
                } else if (item.getItem() == Item.getItemFromBlock(Blocks.stone)) {
                    b0 = 4;
                } else if (item.getItem() == Item.getItemFromBlock(Blocks.log)
                    || item.getItem() == Item.getItemFromBlock(Blocks.log2)) {
                        b0 = 5;
                    } else if (item.getItem() == Items.emerald) {
                        b0 = 6;
                    } else if (item.getItem() == Item.getItemFromBlock(Blocks.leaves)
                        || item.getItem() == Item.getItemFromBlock(Blocks.leaves2)) {
                            b0 = 7;
                        } else if (item.getItem() == TragicItems.Tungsten) {
                            b0 = 8;
                        } else if (item.getItem() == TragicItems.Ruby) {
                            b0 = 9;
                        } else if (item.getItem() == TragicItems.Sapphire) {
                            b0 = 10;
                        } else if (item.getItem() == Items.redstone) {
                            b0 = 11;
                        } else if (item.getItem() == Items.coal) {
                            b0 = 12;
                        } else if (item.getItem() == Items.dye && item.getItemDamage() == 4) {
                            b0 = 13;
                        } else if (item.getItem() == Item.getItemFromBlock(Blocks.netherrack)) {
                            b0 = 14;
                        } else if (item.getItem() == Items.ender_pearl) {
                            b0 = 15;
                        }

                if (item.getItem() == TragicItems.SynapseCrystal) {
                    this.setAnimated(!this.getAnimated());
                    if (!player.capabilities.isCreativeMode) item.stackSize--;
                } else if (b0 == 0 && item.getItem() == Items.blaze_powder && this.getTextureID() != 0
                    || b0 != this.getTextureID() && b0 > 0) {
                        this.setTextureID(b0);
                        if (!player.capabilities.isCreativeMode) item.stackSize--;
                    }
            }
        } else {
            this.incrementRotationAngle();
        }

        return false;
    }

}
