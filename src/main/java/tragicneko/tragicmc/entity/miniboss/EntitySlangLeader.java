package tragicneko.tragicmc.entity.miniboss;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.mob.EntitySlang;

public class EntitySlangLeader extends EntitySlang implements TragicMiniBoss {

    public ArrayList<ItemStack> stolenItems = new ArrayList<ItemStack>();

    public EntitySlangLeader(World par1World) {
        super(par1World);
        this.setSize(1.175F, 3.45F);
        this.experienceValue = 10;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(48.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(0.338);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(7.5);
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
    public boolean attackEntityAsMob(Entity par1Entity) {
        boolean flag = super.attackEntityAsMob(par1Entity);

        if (flag && this.stolenItems.size() < 2 && this.stolenItem != null && rand.nextBoolean()) {
            if (par1Entity instanceof EntityLivingBase) {
                EntityLivingBase el = (EntityLivingBase) par1Entity;
                if (el.getEquipmentInSlot(0) != null) {
                    ItemStack stack = el.getEquipmentInSlot(0)
                        .copy();
                    el.setCurrentItemOrArmor(0, null);

                    this.stolenItems.add(stack);
                }
            }
        }

        return flag;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (this.stolenItem != null && rand.nextInt(4) == 0 && this.ticksExisted % 35 == 0) {
            List<EntityPlayer> list = this.worldObj
                .getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand(12.0, 12.0, 12.0));

            if (!list.isEmpty()) {
                byte b = 0;
                for (EntityPlayer player : list) {
                    double d0 = player.posX - this.posX;
                    double d1 = player.posY - this.posY;
                    double d2 = player.posZ - this.posZ;

                    EntityTNTPrimed tnt = new EntityTNTPrimed(
                        this.worldObj,
                        this.posX,
                        this.posY + this.height * 2 / 3,
                        this.posZ,
                        this);
                    tnt.motionX = d0 * 0.062;
                    tnt.motionY = d1 * 0.062;
                    tnt.motionZ = d2 * 0.062;
                    this.worldObj.spawnEntityInWorld(tnt);
                    TragicMC.logInfo("Meow.");
                    if (b++ > 2) break;
                }
            }
        }
    }

    @Override
    public void onDeath(DamageSource src) {
        super.onDeath(src);
        if (!this.worldObj.isRemote && !this.stolenItems.isEmpty()) {
            for (ItemStack is : this.stolenItems) {
                if (is != null) this.entityDropItem(is, 0.4F);
            }
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource src, float dmg) {
        boolean flag = super.attackEntityFrom(src, dmg);

        if (flag && rand.nextInt(5) == 0 && this.stolenItem != null) {
            this.entityDropItem(this.stolenItem, 0.4F);
            this.stolenItem = null;

            if (!this.stolenItems.isEmpty()) {
                for (ItemStack is : this.stolenItems) {
                    if (is != null) this.entityDropItem(is, 0.4F);
                }

                this.stolenItems.clear();
            }

            this.targetTasks.addTask(0, targetAI);
            this.tasks.addTask(1, moveAI);
            this.tasks.removeTask(runawayAI);
        } else if (flag && this.stolenItem != null
            && rand.nextInt(8) == 0
            && src.getEntity() instanceof EntityLivingBase
            && this.stolenItems.size() < 2) {
                this.stealItem((EntityLivingBase) src.getEntity());
            }

        return flag;
    }

    public void stealItem(EntityLivingBase entity) {
        if (this.stolenItem != null) {
            if (entity.getEquipmentInSlot(0) != null) {
                ItemStack stack = entity.getEquipmentInSlot(0)
                    .copy();
                entity.setCurrentItemOrArmor(0, null);

                this.stolenItems.add(stack);

                this.targetTasks.removeTask(targetAI);
                this.tasks.removeTask(moveAI);
                this.tasks.addTask(1, runawayAI);
            }
        } else {
            super.stealItem(entity);
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("stolenItems")) {
            NBTTagList list = tag.getTagList("stolenItems", 0);

            for (int i = 0; i < list.tagCount(); i++) {
                NBTTagCompound tag2 = list.getCompoundTagAt(i);
                this.stolenItems.add(ItemStack.loadItemStackFromNBT(tag2));
            }
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        if (!this.stolenItems.isEmpty()) {
            NBTTagList list = new NBTTagList();

            for (ItemStack is : this.stolenItems) {
                if (is != null) {
                    NBTTagCompound tag2 = new NBTTagCompound();
                    list.appendTag(is.writeToNBT(tag2));
                }
            }

            tag.setTag("stolenItems", list);
        }
    }
}
