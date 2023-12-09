package tragicneko.tragicmc.properties;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import tragicneko.tragicmc.entity.EntityPet;

public class PropertyMisc implements IExtendedEntityProperties {

    public static final String propertyName = "TragicMC.Misc";
    private final EntityLivingBase theEntity;

    /**
     * For the Bleed potion effect
     */
    public int bleedOutTime = 0;

    /**
     * For recovering from being Corrupted
     */
    public int recoveryTime = 0;

    public int golemTimer = 0; // for iron golem attacking

    public int frozenInputs = 0; // for the frozen potion effect, to "break" out of being frozen
    public boolean isFrozen = false; // if the player was frozen before

    public EntityPet currentPet = null;

    private boolean hasBeenGeared = false;
    private boolean hasBeenBuffed = false;

    public PropertyMisc(EntityLivingBase ent) {
        this.theEntity = ent;
    }

    public static final void register(EntityLivingBase ent) {
        ent.registerExtendedProperties(PropertyMisc.propertyName, new PropertyMisc(ent));
    }

    public static final PropertyMisc get(EntityLivingBase ent) {
        return (PropertyMisc) ent.getExtendedProperties(PropertyMisc.propertyName);
    }

    @Override
    public void saveNBTData(NBTTagCompound compound) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("bleedOut", this.bleedOutTime);
        tag.setInteger("recoveryTime", this.recoveryTime);
        tag.setBoolean("hasBeenGeared", this.hasBeenGeared);
        tag.setBoolean("hasBeenBuffed", this.hasBeenBuffed);
        tag.setBoolean("isFrozen", this.isFrozen);
        tag.setInteger("frozenInputs", this.frozenInputs);
        compound.setTag(PropertyMisc.propertyName, tag);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        NBTTagCompound tag = (NBTTagCompound) compound.getTag(PropertyMisc.propertyName);

        if (tag != null) {
            this.bleedOutTime = tag.getInteger("bleedOut");
            this.recoveryTime = tag.getInteger("recoveryTime");
            this.hasBeenGeared = tag.getBoolean("hasBeenGeared");
            this.hasBeenBuffed = tag.getBoolean("hasBeenBuffed");
            this.isFrozen = tag.getBoolean("isFrozen");
            this.frozenInputs = tag.getInteger("frozenInputs");
        }
    }

    @Override
    public void init(Entity entity, World world) {
        if (entity instanceof EntityLivingBase) {
            PropertyMisc misc = PropertyMisc.get((EntityLivingBase) entity);
            if (misc != null) misc.loadNBTData(new NBTTagCompound());
        }
    }

    public void onUpdate() {
        /*
         * if (TragicPotion.Bleed != null && this.theEntity.isPotionActive(TragicPotion.Bleed))
         * {
         * if (this.bleedOutTime < 1200) this.bleedOutTime++;
         * }
         * else this.bleedOutTime = 0;
         */

        if (this.getCurrentPet() != null && this.theEntity instanceof EntityPlayer)
            this.getCurrentPet().owner = (EntityPlayer) this.theEntity;
        if (this.golemTimer > 0) this.golemTimer--;
        /*
         * if (!this.isFrozen && this.theEntity.isPotionActive(TragicPotion.Frozen.id))
         * {
         * this.isFrozen = true;
         * this.frozenInputs = 30 + (20 * this.theEntity.getActivePotionEffect(TragicPotion.Frozen).getAmplifier());
         * }
         * else if (this.isFrozen && !this.theEntity.isPotionActive(TragicPotion.Frozen.id))
         * {
         * this.isFrozen = false;
         * this.frozenInputs = 0;
         * }
         */
    }

    /**
     * Returns the current "active" pet, may be null
     * 
     * @return
     */
    public EntityPet getCurrentPet() {
        return this.currentPet;
    }

    public void setCurrentPet(EntityPet pet) {
        this.currentPet = pet;
    }

    public boolean hasBeenGeared() {
        return this.hasBeenGeared;
    }

    public void setGeared() {
        this.hasBeenGeared = true;
    }

    public boolean hasBeenBuffed() {
        return this.hasBeenBuffed;
    }

    public void setBuffed() {
        this.hasBeenBuffed = true;
    }
}
