package tragicneko.tragicmc.entity.boss;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;

public class EntityAegarCrystal extends EntityPart {

    private float health;

    public EntityAegarCrystal(IMultiPart main) {
        super(main, "crystal", 2.0F, 2.0F);
        this.health = 100.0F;
    }

    public float getHealth() {
        return this.health;
    }

    public void setHealth(float f) {
        this.health = f;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);

    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        if (!this.worldObj.isRemote && this.health > 0) this.health -= damage / 2.0F;
        return super.attackEntityFrom(source, damage);
    }
}
