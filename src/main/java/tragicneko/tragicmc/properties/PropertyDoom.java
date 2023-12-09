package tragicneko.tragicmc.properties;

import static tragicneko.tragicmc.TragicMC.rand;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.network.MessageDoom;

public class PropertyDoom implements IExtendedEntityProperties {

    public static final String propertyName = "TragicMC.Doom";
    private final EntityPlayer thePlayer;

    private int doomCooldown;

    private int tick;
    private int cooldownTick;

    private int maxDoom;
    private int currentDoom;

    private boolean hasUpgraded;

    public PropertyDoom(EntityPlayer player) {
        this.thePlayer = player;
        this.doomCooldown = 0;
        this.maxDoom = TragicConfig.maxDoomStart;
        this.currentDoom = 0;
        this.hasUpgraded = false;
    }

    public static final void register(EntityPlayer player) {
        player.registerExtendedProperties(PropertyDoom.propertyName, new PropertyDoom(player));
    }

    public static final PropertyDoom get(EntityPlayer player) {
        return (PropertyDoom) player.getExtendedProperties(PropertyDoom.propertyName);
    }

    @Override
    public void saveNBTData(NBTTagCompound compound) {

        NBTTagCompound properties = new NBTTagCompound();

        properties.setInteger("doomCooldown", this.doomCooldown);
        properties.setInteger("doomAmount", this.currentDoom);
        properties.setInteger("maxDoom", this.maxDoom);
        properties.setBoolean("hasUpgraded", this.hasUpgraded);

        compound.setTag(PropertyDoom.propertyName, properties);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        NBTTagCompound properties = (NBTTagCompound) compound.getTag(PropertyDoom.propertyName);

        if (properties != null) {
            this.doomCooldown = properties.getInteger("doomCooldown");
            this.maxDoom = properties.getInteger("maxDoom");
            this.currentDoom = properties.getInteger("doomAmount");
            this.hasUpgraded = properties.getBoolean("hasUpgraded");
        }

        if (this.maxDoom < TragicConfig.maxDoomStart || !this.hasUpgraded && this.maxDoom > TragicConfig.maxDoomStart)
            this.maxDoom = TragicConfig.maxDoomStart;
    }

    @Override
    public void init(Entity entity, World world) {
        if (entity instanceof EntityPlayer) {
            PropertyDoom doom = PropertyDoom.get((EntityPlayer) entity);
            if (doom != null) doom.loadNBTData(new NBTTagCompound());
        }
    }

    /**
     * This increments the Doom and cooldown amounts, does checks to see if they should be done automatically, also does
     * any fixes to doom and cooldown amounts
     * so that they aren't numbers that they aren't supposed to be
     */
    public void onUpdate() {
        if (this.getCurrentDoom() < 0) this.setCurrentDoom(0);
        if (this.getCurrentDoom() > this.getMaxDoom()) this.setCurrentDoom(this.getMaxDoom());
        if (this.getCurrentCooldown() < 0) this.setCooldown(0);

        if (this.shouldDecrementCooldown()) {
            doomCooldown--;
        } else {
            if (this.shouldRecoverNaturally()) {
                int doom = this.getCurrentDoom();
                int increment = TragicConfig.doomRechargeAmount;

                if (doom >= this.getMaxDoom()) return;

                if (increment + doom >= this.getMaxDoom()) {
                    this.fillDoom();
                } else {
                    this.currentDoom += increment;
                }
            }
        }
    }

    public boolean shouldDecrementCooldown() {
        if (this.getCurrentCooldown() < 0) {
            this.setCooldown(0);
        }

        if (this.getCurrentCooldown() > 0) {
            cooldownTick++;

            if (this.cooldownTick >= 8) {
                cooldownTick = 0;
                return true;
            }
        }
        return false;
    }

    public int getCurrentCooldown() {
        return this.doomCooldown;
    }

    public void setCooldown(int cooldown) {
        if (TragicConfig.allowCooldown) {
            this.doomCooldown = cooldown;
        } else {
            this.doomCooldown = 0;
        }
    }

    public void increaseCooldown(int cooldown) {
        if (TragicConfig.allowCooldown) {
            this.doomCooldown += cooldown;
        } else {
            this.doomCooldown = 0;
        }
    }

    public int getCurrentDoom() {
        return this.currentDoom;
    }

    public int getMaxDoom() {
        return this.maxDoom;
    }

    /**
     * Does a check to see if the amount passed in is higher than the current max amount or lower than 0, then passes
     * off to setCurrentDoom(), use a negative
     * amount to decrease
     * 
     * @param amount
     */
    public void increaseDoom(int amount) {
        if (amount > 0 && this.getCurrentCooldown() > 0) return; // If the player still has cooldown, don't let their
                                                                 // doom regenerate while this is happening

        if (this.getCurrentDoom() + amount >= this.getMaxDoom()) {
            this.fillDoom();
        } else if (this.getCurrentDoom() + amount <= 0) {
            this.emptyDoom();
        } else {
            this.setCurrentDoom(this.getCurrentDoom() + amount);
        }
    }

    /**
     * Increases max doom amount, performs check if possible to increase without going over max consumption level, then
     * passes off to setMaxDoom(), use a negative
     * amount to decrease
     * 
     * @param amount
     */
    public void increaseConsumptionLevel() {
        this.hasUpgraded = true;
        if (this.getMaxDoom() + TragicConfig.doomConsumeAmount <= TragicConfig.maxDoomAmount) {
            this.setMaxDoom(this.getMaxDoom() + TragicConfig.doomConsumeAmount);
        } else {
            this.setMaxDoom(TragicConfig.maxDoomAmount);
        }

        if (this.thePlayer instanceof EntityPlayerMP)
            TragicMC.net.sendTo(new MessageDoom(this.thePlayer), (EntityPlayerMP) this.thePlayer);
    }

    /**
     * Updates current doom amount, should not be called by anything except the increaseDoom() method
     * 
     * @param amount
     */
    protected void setCurrentDoom(int amount) {
        if (amount < 0) amount = 0;
        if (amount > this.getMaxDoom()) amount = this.getMaxDoom();

        this.currentDoom = amount;
        if (this.thePlayer instanceof EntityPlayerMP)
            TragicMC.net.sendTo(new MessageDoom(this.thePlayer), (EntityPlayerMP) this.thePlayer);
    }

    /**
     * Updates current max doom (data watcher), should not be called by anything except the increaseConsumptionLevel()
     * method
     * 
     * @param amount
     */
    protected void setMaxDoom(int amount) {
        if (amount < 0) amount = 0;
        this.maxDoom = amount;
        if (this.thePlayer instanceof EntityPlayerMP)
            TragicMC.net.sendTo(new MessageDoom(this.thePlayer), (EntityPlayerMP) this.thePlayer);
    }

    /**
     * Subtracts the given amount from the current doom if possible, applies cooldown as well, checks for having enough
     * doom should be done beforehand
     * 
     * @param amount
     * @param cooldown
     */
    public void decreaseDoomAmountAndApplyCooldown(int amount, int cooldown) {
        this.increaseCooldown(cooldown);
        this.increaseDoom(-amount);
    }

    public void emptyDoom() {
        this.setCurrentDoom(0);
    }

    public void fillDoom() {
        this.setCurrentDoom(this.getMaxDoom());
    }

    /**
     * Partially fills doom based on float percentage passed in, should only be used by Doom Consumes when the user has
     * it configured to be partial recovery
     * 
     * @param partial
     */
    public void partiallyFillDoom(float partial) {
        int partialAmount = (int) (partial * this.getMaxDoom());
        if (this.getCurrentDoom() + partialAmount > this.getMaxDoom()) {
            this.fillDoom();
        } else {
            this.increaseDoom(partialAmount);
        }
    }

    protected boolean shouldRecoverWithDamage() {
        if (TragicConfig.allowDoomPainRecharge && this.getCurrentDoom() < this.getMaxDoom()) {
            return true;
        }
        return false;
    }

    protected boolean shouldRecoverNaturally() {
        if (this.getCurrentCooldown() != 0) {
            return false;
        }

        tick++;

        if (TragicConfig.allowNaturalRecharge && this.getCurrentDoom() < this.getMaxDoom()
            && this.tick >= 100 / TragicConfig.doomRechargeRate) {
            tick = 0;
            return true;
        }

        return false;
    }

    /**
     * Calculates doom recover amount based on float passed in, also does check if it should recover
     * 
     * @param source
     */
    public void applyDoomPainRecharge(float source) {
        if (this.shouldRecoverWithDamage()) {
            source = MathHelper.clamp_float(source / 10.0F, 0.0F, 6.0F);

            if (source < 1.0F) {
                if (rand.nextFloat() < source) {
                    this.increaseDoom(1);
                }
            } else if (source < 2.0F) {
                if (rand.nextFloat() * 2.0F < source) {
                    this.increaseDoom(1);
                }
            } else if (source < 3.0F) {
                this.increaseDoom(2);
            } else if (source < 4.0F) {
                this.increaseDoom(3);
            } else if (source < 5.0F) {
                this.increaseDoom(5);
            } else {
                this.increaseDoom(6);
            }

            if (this.thePlayer instanceof EntityPlayerMP)
                TragicMC.net.sendTo(new MessageDoom(this.thePlayer), (EntityPlayerMP) this.thePlayer);
        }
    }

    /**
     * Returns the player that the doom properties are for
     * 
     * @return
     */
    public EntityPlayer getPlayer() {
        return this.thePlayer;
    }
}
