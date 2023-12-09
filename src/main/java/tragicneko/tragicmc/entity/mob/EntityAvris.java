package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.avrisStats;
import static tragicneko.tragicmc.TragicMC.rand;

import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCore;
import tragicneko.tragicmc.items.ItemChallenge;
import tragicneko.tragicmc.util.EntityDropHelper.EntityDrop;

public class EntityAvris extends TragicMob {

    public int rarity = 1;
    private int timeAlive = 0;

    public EntityAvris(World par1World) {
        super(par1World);
        this.setSize(1.26F, 2.76F);
        this.experienceValue = 3;
        this.getNavigator()
            .setAvoidsWater(true);
        this.getNavigator()
            .setCanSwim(true);
        this.tasks.addTask(0, new EntityAIPanic(this, 1.2D));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityPlayer.class, 12.0F, 0.6D, 1.2D));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityGolem.class, 12.0F, 0.8D, 1.4D));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.tasks.addTask(5, new EntityAIWander(this, 0.85D));
        this.stepHeight = 1.0F;
        this.isImmuneToFire = true;
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    protected boolean isChangeAllowed() {
        return false;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (this.getHealth() > 0F) this.timeAlive++;

        if (this.worldObj.isRemote) {
            for (int i = 0; i < 3; i++) {
                this.worldObj.spawnParticle(
                    "flame",
                    this.posX + ((rand.nextDouble() - rand.nextDouble()) * this.width),
                    this.posY + rand.nextDouble() * this.height,
                    this.posZ + ((rand.nextDouble() - rand.nextDouble()) * this.width),
                    0.0F,
                    0.155F * this.rand.nextFloat(),
                    0.0F);
            }

            return;
        }

        if (this.ticksExisted % 4 == 0 && this.timeAlive >= 2400 - this.rarity * 400 && this.getHealth() > 0F) {
            List<Entity> entities = this.worldObj
                .getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(64.0, 64.0, 64.0));
            boolean flag = true;
            for (Entity e : entities) {
                if (e instanceof EntityLivingBase && this.canEntityBeSeen(e)) flag = false;
            }

            if (this.timeAlive >= 3600 && TragicConfig.avrisDespawnTime) flag = true;

            if (flag && TragicConfig.avrisDespawnTime) {
                this.setDead();

                if (TragicConfig.avrisAnnouncements) {
                    List<EntityPlayerMP> list = this.worldObj
                        .getEntitiesWithinAABB(EntityPlayerMP.class, boundingBox.expand(48.0, 48.0, 48.0));

                    for (EntityPlayerMP mp : list)
                        mp.addChatMessage(new ChatComponentText("The Avris has eluded pursuers!"));
                }
            }
        }

        if (TragicConfig.allowMobIllumination) {
            int x = (int) (this.posX + rand.nextInt(2) - rand.nextInt(2));
            int y = (int) (this.posY + rand.nextInt(2) - rand.nextInt(2)) + ((int) this.height * 2 / 3);
            int z = (int) (this.posZ + rand.nextInt(2) - rand.nextInt(2));
            if (EntityOverlordCore.replaceableBlocks.contains(worldObj.getBlock(x, y, z)))
                this.worldObj.setBlock(x, y, z, TragicBlocks.Luminescence);
        }
    }

    @Override
    public void onDeath(DamageSource src) {
        super.onDeath(src);
        if (!this.worldObj.isRemote) {
            if (TragicConfig.avrisAnnouncements) {
                List<EntityPlayerMP> list = this.worldObj
                    .getEntitiesWithinAABB(EntityPlayerMP.class, boundingBox.expand(48.0, 48.0, 48.0));

                for (EntityPlayerMP mp : list) mp.addChatMessage(new ChatComponentText("The Avris has been slain!"));
            }

            int x = 7;

            if (src.getEntity() != null && src.getEntity() instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) src.getEntity();

                if (player.getCurrentEquippedItem() != null) {
                    ItemStack weapon = player.inventory.getCurrentItem();
                    x += EnchantmentHelper.getEnchantmentLevel(Enchantment.looting.effectId, weapon);
                }

                if (player instanceof EntityPlayerMP && TragicConfig.allowAchievements)
                    ((EntityPlayerMP) player).triggerAchievement(TragicAchievements.avris);
            }

            int drops = 0;

            for (int i = 0; i < x; i++) {
                if (rand.nextInt(100) <= TragicConfig.commonDropRate + (x * 4)) {
                    ItemStack drop = ((EntityDrop) WeightedRandom.getRandomItem(rand, this.getDropsFromRarity()))
                        .getStack();
                    if (drop != null) this.entityDropItem(drop.copy(), 0.4F);
                    drops++;
                }

                if (this.recentlyHit > 0 && rand.nextInt(100) <= TragicConfig.rareDropRate + x) {
                    ItemStack drop = ((EntityDrop) WeightedRandom.getRandomItem(rand, this.getDropsFromRarity()))
                        .getStack();
                    if (drop != null) this.entityDropItem(drop.copy(), 0.4F);
                    drops++;
                }

                if (drops > x * 2.5) break;
            }
        }
    }

    private EntityDrop[] getDropsFromRarity() {
        return this.rarity >= 3 ? ItemChallenge.rewards
            : (this.rarity == 2 ? ItemChallenge.cheapRewards : ItemChallenge.badRewards);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(avrisStats[0]);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(avrisStats[1]);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(avrisStats[2]);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(avrisStats[3]);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(avrisStats[4]);
    }

    @Override
    public int getTotalArmorValue() {
        return (int) avrisStats[5];
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        if (!this.worldObj.isRemote) {
            rarity = rand.nextInt(3) + 1;

            if (TragicConfig.avrisAnnouncements) {
                List<EntityPlayerMP> list = this.worldObj
                    .getEntitiesWithinAABB(EntityPlayerMP.class, boundingBox.expand(48.0, 48.0, 48.0));

                for (EntityPlayerMP mp : list)
                    mp.addChatMessage(new ChatComponentText("An Avris has appeared nearby!"));
            }
        }
        return super.onSpawnWithEgg(data);
    }

    @Override
    public boolean getCanSpawnHere() {
        return super.getCanSpawnHere() && rand.nextInt(32) == 0;
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (this.worldObj.isRemote) return false;

        boolean flag = super.attackEntityFrom(par1DamageSource, par2);
        if (flag && par1DamageSource.getEntity() != null) par1DamageSource.getEntity()
            .setFire(4 + rand.nextInt(4));

        return flag;
    }

    @Override
    public String getLivingSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.avris.laugh" : null;
    }

    @Override
    public String getHurtSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.avris.hurt" : super.getHurtSound();
    }

    @Override
    public String getDeathSound() {
        return TragicConfig.allowMobSounds ? "tragicmc:mob.avris.death" : null;
    }

    @Override
    public float getSoundPitch() {
        return 1.0F;
    }

    @Override
    public float getSoundVolume() {
        return 1.0F;
    }
}
