package tragicneko.tragicmc.entity.miniboss;

import static tragicneko.tragicmc.TragicConfig.megaCryseStats;

import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.entity.mob.EntityCryse;
import tragicneko.tragicmc.util.DamageHelper;

public class EntityMegaCryse extends EntityCryse implements TragicMiniBoss {

    private AttributeModifier mod = new AttributeModifier(
        UUID.fromString("3466b84d-0df6-4d6c-93cf-0fd4bedc77e9"),
        "megaCryseNoShieldAttackBuff",
        TragicConfig.modifier[5],
        0);
    private int timeSinceFirstBreak;

    public EntityMegaCryse(World par1World) {
        super(par1World);
        this.experienceValue = 15;
    }

    @Override
    public boolean isMobVariant() {
        return false;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(21, Integer.valueOf(0));
        this.dataWatcher.addObject(22, Integer.valueOf(0));
        this.dataWatcher.addObject(23, Integer.valueOf(0));
    }

    @Override
    protected void setCryseType(byte b) {
        this.dataWatcher.updateObject(17, (byte) 1);
        this.setSize(1.435F, 3.075F);
    }

    public int getShields() {
        return this.dataWatcher.getWatchableObjectInt(21);
    }

    public void setShields(int i) {
        this.dataWatcher.updateObject(21, i);
    }

    public int getSpinTicks2() {
        return this.dataWatcher.getWatchableObjectInt(22);
    }

    public void setSpinTicks2(int i) {
        this.dataWatcher.updateObject(22, i);
    }

    public int getSpinTicks3() {
        return this.dataWatcher.getWatchableObjectInt(23);
    }

    public void setSpinTicks3(int i) {
        this.dataWatcher.updateObject(23, i);
    }

    @Override
    protected void decrementSpinTicks() {
        int pow = this.getSpinTicks();
        if (pow > 0) this.dataWatcher.updateObject(16, --pow);
        pow = this.getSpinTicks2();
        if (pow > 0) this.setSpinTicks2(--pow);
        pow = this.getSpinTicks3();
        if (pow > 0) this.setSpinTicks3(--pow);
    }

    @Override
    public boolean isSpinning() {
        return this.getSpinTicks() > 0 || this.getSpinTicks2() > 0 || this.getSpinTicks3() > 0;
    }

    @Override
    protected void setSpinTicks(int i) {
        if (rand.nextBoolean()) {
            this.dataWatcher.updateObject(16, i);
        } else {
            if (rand.nextBoolean()) {
                this.setSpinTicks2(i);
            } else {
                this.setSpinTicks3(i);
            }
        }
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(megaCryseStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(megaCryseStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(megaCryseStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(megaCryseStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(megaCryseStats[4]);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (this.worldObj.isRemote) {
            this.setSize(1.435F, 3.075F);
        } else {
            if (this.timeSinceFirstBreak > 0) --this.timeSinceFirstBreak;
            if (this.getShields() < 4 && this.timeSinceFirstBreak == 0) this.setShields(4);

            this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
                .removeModifier(mod);
            if (this.getShields() == 0) this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
                .applyModifier(mod);
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (this.worldObj.isRemote) return false;

        boolean flag = false;

        if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();
            flag = player.getCurrentEquippedItem() != null && (player.getCurrentEquippedItem()
                .getItem() == TragicItems.SwordOfJustice
                || player.getCurrentEquippedItem()
                    .getItem() == TragicItems.BowOfJustice);
        }

        if (this.getShields() > 0 && par2 >= 1.0F && !flag && TragicConfig.megaCryseShields) {
            if (this.getShields() == 4) {
                this.timeSinceFirstBreak = 80;
            }
            int pow = this.getShields();
            this.setShields(--pow);

            if (!par1DamageSource.isMagicDamage()) par2 = 0;
        }

        boolean result = super.attackEntityFrom(par1DamageSource, par2);

        if (result) {
            this.dataWatcher.updateObject(16, 0);
            this.setSpinTicks2(0);
            this.setSpinTicks3(0);
        }
        return super.attackEntityFrom(par1DamageSource, par2);
    }

    @Override
    public int getTotalArmorValue() {
        return (int) megaCryseStats[5];
    }

    @Override
    public void collideWithEntity(Entity entity) {
        super.collideWithEntity(entity);

        if (this.worldObj.isRemote) return;
        if (this.ticksExisted % 20 == 0 && entity instanceof EntityLivingBase && this.getShields() > 0)
            entity.attackEntityFrom(DamageHelper.causeModMagicDamageToEntity(this), 1.0F);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("spinTicks")) this.dataWatcher.updateObject(16, tag.getInteger("spinTicks"));
        if (tag.hasKey("spinTicks2")) this.setSpinTicks2(tag.getInteger("spinTicks2"));
        if (tag.hasKey("spinTicks3")) this.setSpinTicks3(tag.getInteger("spinTicks3"));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setInteger("spinTicks2", this.getSpinTicks2());
        tag.setInteger("spinTicks3", this.getSpinTicks3());
    }

    @Override
    protected boolean isChangeAllowed() {
        return false;
    }

    @Override
    public Class getLesserForm() {
        return EntityCryse.class;
    }

    @Override
    protected void func_145780_a(int x, int y, int z, Block block) {
        // this.playSound("tragicmc:mob.norvox.scrape", 0.45F, 1.0F);
    }

    @Override
    public int getDropAmount() {
        return 5;
    }
}
