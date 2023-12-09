package tragicneko.tragicmc.entity;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.properties.PropertyMisc;

public class EntityPet extends EntityCreature {

    private float level = 0F; // level of the pet
    private AbilityBase[] abilities = new AbilityBase[8];

    private boolean unlockAvailable = false;
    public EntityPlayer owner = null;
    private UUID ownerID = null;

    private boolean standby = false;
    private boolean follow = false;

    private Set<ItemFood> foods = new HashSet<ItemFood>();

    public EntityPet(World world) {
        super(world);
        // for nbt:
        // save level
        // save ownerID
        // save each ability's pertinent information, save as an nbttaglist, each abilty will save it's own information
    }

    public void setOwner(EntityPlayer player) {
        this.owner = player;
        this.ownerID = player.getPersistentID();
    }

    public boolean isOwner(EntityPlayer player) {
        return this.hasOwner() && this.ownerID == player.getPersistentID();
    }

    public boolean hasOwner() {
        return this.ownerID != null;
    }

    public AbilityBase[] getAbilities() {
        return this.abilities;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (!this.worldObj.isRemote && !this.standby) {
            for (AbilityBase ability : this.getAbilities()) {
                if (ability != null && ability.isUnlocked() && ability.isSelected()) ability.onUpdate();
            }
        }

        if (this.owner != null) {
            PropertyMisc misc = PropertyMisc.get(this.owner);
            if (misc != null && misc.getCurrentPet() != this) this.owner = null;
        } else {
            if (this.standby = false) {
                this.standby = true;
                this.onStandby();
            }
        }
    }

    public void onStandby() {

    }

    @Override
    public void onKillEntity(EntityLivingBase entity) {
        super.onKillEntity(entity);

        if (entity instanceof EntityMob && this.getActualLevel() < this.getMaxLevel()) {
            this.addExperience(entity);
        }
    }

    public void addExperience(EntityLivingBase entity) {
        if (this.getActualLevel() >= this.getMaxLevel()) return;

        try {
            Field f = EntityLiving.class.getField("experienceValue");
            f.setAccessible(true);
            float j = f.getInt(entity);

            j *= this.getExperienceRate();
            int i = this.getActualLevel();
            this.level += j;
            if (this.getActualLevel() > i) TragicMC.logInfo("Pet has leveled up!");
        } catch (Exception e) {
            TragicMC.logError("There was an error getting the experience value from a mob for leveling up a pet.", e);
        }
    }

    public float getExperienceRate() {
        return 1.0F - (this.getActualLevel() * this.getExperienceDeprecation());
    }

    public float getExperienceDeprecation() {
        return 0.1F;
    }

    public int getActualLevel() {
        return MathHelper.floor_double(this.level / this.getExperienceScale());
    }

    public int getExperienceScale() {
        return 100; // xp required to level up, this is for flat levelling rather than a scalar amount
    }

    public int getMaxLevel() {
        return this.abilities.length * 5;
    }

    public int getUnlockedTier() {
        return this.getActualLevel() > 15 ? 2 : (this.getActualLevel() > 10 ? 1 : 0);
    }

    @Override
    protected boolean interact(EntityPlayer player) {
        if (this.hasOwner() && this.isOwner(player)) {
            ItemStack stack = player.getCurrentEquippedItem();
            if (stack == null) {
                if (player.isSneaking()) {
                    this.standby = !this.standby;
                } else {
                    // open gui
                }
            } else if (this.foods.contains(stack.getItem())) {
                this.heal(((ItemFood) stack.getItem()).func_150905_g(stack));
            }
        } else if (this.hasOwner() && !this.isOwner(player)) {
            return false;
        }
        return super.interact(player);
    }

    public abstract static class AbilityBase {

        public final EntityPet pet;
        private boolean unlocked = false;
        private boolean selected = false;
        private final byte tier;

        public AbilityBase(EntityPet pet, byte tier) {
            this.pet = pet;
            this.tier = tier;
        }

        public abstract void onUpdate();

        public boolean isUnlocked() {
            return this.unlocked;
        }

        public void setUnlocked() {
            this.unlocked = true;
        }

        public byte getTier() {
            return this.tier;
        }

        public boolean isSelected() {
            return this.selected;
        }

        public void setSelected(boolean flag) {
            this.selected = flag;
        }

        public void saveToNBT(NBTTagCompound tag) {
            tag.setBoolean("unlocked", this.unlocked);
            tag.setBoolean("selected", this.selected);
        }

        public void readFromNBT(NBTTagCompound tag) {
            if (tag.hasKey("unlocked")) this.unlocked = tag.getBoolean("unlocked");
            if (tag.hasKey("selected")) this.selected = tag.getBoolean("selected");
        }
    }

    public static class SampleAbility extends AbilityBase {

        public SampleAbility(EntityPet pet) {
            super(pet, (byte) 2);
        }

        @Override
        public void onUpdate() {
            if (this.pet.owner != null && this.pet.owner.getHealth() < this.pet.owner.getMaxHealth()
                && this.pet.ticksExisted % 5 == 0) {
                this.pet.owner.heal(1.0F);
            }
        }
    }
}
