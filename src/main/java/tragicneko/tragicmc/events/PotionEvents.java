package tragicneko.tragicmc.events;

import static tragicneko.tragicmc.TragicMC.rand;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent.Start;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent.Tick;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.boss.TragicBoss;
import tragicneko.tragicmc.items.amulet.ItemAmulet.AmuletModifier;
import tragicneko.tragicmc.network.MessageFlight;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.properties.PropertyMisc;

public class PotionEvents {

    @SubscribeEvent
    public void flightSync(EntityJoinWorldEvent event) {
        if (event.entity instanceof EntityPlayerMP) {
            TragicMC.net.sendTo(new MessageFlight(TragicConfig.allowFlight), (EntityPlayerMP) event.entity);
        }
    }

    @SubscribeEvent
    public void tragicPotionEffect(LivingUpdateEvent event) {
        EntityLivingBase entity = event.entityLiving;
        World world = event.entityLiving.worldObj;

        if (entity.getActivePotionEffects()
            .isEmpty()) return;

        if (TragicConfig.allowClarity && entity.isPotionActive(TragicPotion.Clarity.id)) {
            if (TragicConfig.allowDisorientation && event.entityLiving.isPotionActive(TragicPotion.Disorientation)) {
                event.entityLiving.removePotionEffect(TragicPotion.Disorientation.id);
            }

            if (event.entityLiving.isPotionActive(Potion.confusion)) {
                event.entityLiving.removePotionEffect(Potion.confusion.id);
            }

            if (event.entityLiving.isPotionActive(Potion.blindness)) {
                event.entityLiving.removePotionEffect(Potion.blindness.id);
            }

            if (TragicConfig.allowFear && event.entityLiving.isPotionActive(TragicPotion.Fear)) {
                event.entityLiving.removePotionEffect(TragicPotion.Fear.id);
            }
        }

        if (TragicConfig.allowImmunity && event.entityLiving.isPotionActive(TragicPotion.Immunity)) {
            if (TragicConfig.allowInhibit && event.entityLiving.isPotionActive(TragicPotion.Inhibit)) {
                event.entityLiving.removePotionEffect(TragicPotion.Immunity.id);
            }

            if (TragicConfig.allowStun && event.entityLiving.isPotionActive(TragicPotion.Stun)) {
                event.entityLiving.removePotionEffect(TragicPotion.Stun.id);
            }

            if (event.entityLiving.isPotionActive(Potion.poison)) {
                event.entityLiving.removePotionEffect(Potion.poison.id);
            }

            if (event.entityLiving.isPotionActive(Potion.wither)) {
                event.entityLiving.removePotionEffect(Potion.wither.id);
            }

            if (TragicConfig.allowCorruption && event.entityLiving.isPotionActive(TragicPotion.Corruption)) {
                event.entityLiving.removePotionEffect(TragicPotion.Corruption.id);
            }
        }

        if (TragicConfig.allowInhibit && event.entityLiving.isPotionActive(TragicPotion.Inhibit)) {
            if (event.entityLiving.isPotionActive(Potion.regeneration)) {
                event.entityLiving.removePotionEffect(Potion.regeneration.id);
            }

            if (event.entityLiving.isPotionActive(Potion.damageBoost)) {
                event.entityLiving.removePotionEffect(Potion.damageBoost.id);
            }

            if (event.entityLiving.isPotionActive(Potion.digSpeed)) {
                event.entityLiving.removePotionEffect(Potion.digSpeed.id);
            }

            if (event.entityLiving.isPotionActive(Potion.moveSpeed)) {
                event.entityLiving.removePotionEffect(Potion.moveSpeed.id);
            }
        }

        if (TragicConfig.allowMalnourish && event.entityLiving.isPotionActive(TragicPotion.Malnourish)) {
            if (event.entityLiving.isPotionActive(Potion.field_76443_y))
                event.entityLiving.removePotionEffect(TragicPotion.Malnourish.id);
            if (event.entityLiving.isPotionActive(Potion.hunger.id))
                event.entityLiving.removePotionEffect(Potion.hunger.id);
        }

        if (TragicConfig.allowCripple && event.entityLiving.isPotionActive(TragicPotion.Cripple)) {
            if (event.entityLiving.isPotionActive(Potion.field_76434_w))
                event.entityLiving.removePotionEffect(TragicPotion.Cripple.id);
        }

        if (TragicConfig.allowSubmission && event.entityLiving.isPotionActive(TragicPotion.Submission)) {
            if (event.entityLiving.isPotionActive(Potion.field_76434_w))
                event.entityLiving.removePotionEffect(TragicPotion.Submission.id);
        }

        if (TragicConfig.allowDivinity && entity.isPotionActive(TragicPotion.Divinity)) {
            if (entity.getHealth() < entity.getMaxHealth() && rand.nextBoolean() && entity.ticksExisted % 10 == 0)
                entity.heal(1.0F);
            if (TragicConfig.allowHacked && entity.isPotionActive(TragicPotion.Hacked))
                entity.removePotionEffect(TragicPotion.Hacked.id);
        }

        if (TragicConfig.allowAquaSuperiority && entity.isPotionActive(TragicPotion.AquaSuperiority.id)) {
            if (entity instanceof EntityPlayer && entity.isInWater()) {
                if (Math.abs(entity.motionX) <= 0.8115D) entity.motionX *= 1.2115;
                if (Math.abs(entity.motionZ) <= 0.815D) entity.motionZ *= 1.2115;
                ((EntityPlayer) entity).setAir(300);

                if (entity.motionY > 0.0) {
                    if (Math.abs(entity.motionY) <= 0.8115D) entity.motionY *= 1.2115;
                } else {
                    if (entity.isSneaking()) {
                        entity.motionY -= 0.1;
                    } else {
                        entity.motionY *= 0.8;
                    }
                }
            }
        }

        if (TragicConfig.allowFlight && entity.isPotionActive(TragicPotion.Flight.id)) {
            if (entity instanceof EntityPlayer && !entity.isInWater() && !entity.onGround) {
                if (rand.nextInt(128) == 0 && entity.motionY > 0.0) {
                    ((EntityPlayer) entity).addExhaustion(0.5F);
                }

                if (entity.motionY <= 0.0) {
                    if (entity.isSneaking()) {
                        entity.motionY = -0.0212;
                    } else {
                        entity.motionY -= 0.0215D;
                    }
                } else if (entity.motionY <= 0.4115) {
                    entity.motionY *= 1.298647D;
                }

                if (!entity.isSneaking()) {
                    if (Math.abs(entity.motionX) <= 0.4115) entity.motionX *= 1.075D;
                    if (Math.abs(entity.motionZ) <= 0.4115) entity.motionZ *= 1.075D;
                }

                entity.fallDistance = 0.0F;

            }
        }

        if (world.isRemote) return;

        if (TragicConfig.allowCorruption && entity.isPotionActive(TragicPotion.Corruption.id)) {
            int a = MathHelper.ceiling_double_int(
                80 / (entity.getActivePotionEffect(TragicPotion.Corruption)
                    .getAmplifier() + 1))
                + 1;
            if (entity instanceof EntityPlayer && entity.ticksExisted % a == 0) {
                if (!((EntityPlayer) entity).capabilities.isCreativeMode) {
                    entity.attackEntityFrom(DamageSource.magic, 0.5F);
                }
            } else if (entity instanceof EntityAnimal && entity.ticksExisted % a == 0) {
                entity.attackEntityFrom(DamageSource.magic, 0.5F);
            }

            if (entity.ticksExisted % 5 == 0) {
                List<Entity> list = world
                    .getEntitiesWithinAABBExcludingEntity(entity, entity.boundingBox.expand(4.0D, 4.0D, 4.0D));
                boolean flag = !list.isEmpty();
                PotionEffect temp = entity.getActivePotionEffect(TragicPotion.Corruption);

                for (Entity target : list) {
                    if (target instanceof EntityLivingBase) {
                        if (entity.getDistanceToEntity(target) <= 4.0D && entity.canEntityBeSeen(target)) {
                            if (!flag) flag = true;
                            if (rand.nextBoolean()
                                && !((EntityLivingBase) target).isPotionActive(TragicPotion.Corruption.id))
                                ((EntityLivingBase) target).addPotionEffect(
                                    new PotionEffect(
                                        TragicPotion.Corruption.id,
                                        temp.getDuration() / 2,
                                        temp.getAmplifier()));
                        }
                    }
                }

                PropertyMisc misc = PropertyMisc.get(entity);
                if (misc == null) return;
                boolean flag2 = false;

                if (!flag) {
                    list = world
                        .getEntitiesWithinAABBExcludingEntity(entity, entity.boundingBox.expand(12.0D, 12.0D, 12.0D));

                    for (Entity e : list) {
                        if (entity.canEntityBeSeen(e) && entity.getDistanceToEntity(e) <= 12.0F) flag2 = true;
                    }

                    if (!flag2) {
                        if (misc.recoveryTime++ >= 60) {
                            entity.removePotionEffect(TragicPotion.Corruption.id);
                            misc.recoveryTime = 0;
                        }
                    } else {
                        if (misc.recoveryTime > 0) misc.recoveryTime--;
                    }
                } else {
                    misc.recoveryTime = 0;
                }
            }
        } else if (TragicConfig.allowCorruption) {
            PropertyMisc misc = PropertyMisc.get(entity);
            if (misc != null && misc.recoveryTime > 0) misc.recoveryTime = 0;
        }

        if (TragicConfig.allowFear && entity.isPotionActive(TragicPotion.Fear.id)) {
            if (entity instanceof EntityCreature) {
                ((EntityCreature) entity).setTarget(null);
                ((EntityCreature) entity).setAttackTarget(null);

                if (!((EntityCreature) entity).getNavigator()
                    .noPath()) {
                    PathEntity path = ((EntityCreature) entity).getNavigator()
                        .getPath();
                    int x = path.getFinalPathPoint().xCoord;
                    int y = path.getFinalPathPoint().yCoord;
                    int z = path.getFinalPathPoint().zCoord;

                    if (!entity.worldObj.getEntitiesWithinAABB(
                        EntityLivingBase.class,
                        entity.boundingBox.copy()
                            .offset(x, y, z))
                        .isEmpty()) {
                        ((EntityCreature) entity).getNavigator()
                            .clearPathEntity();
                    }
                }

                for (int i = 0; i < 10; i++) {
                    if (((EntityCreature) entity).getNavigator()
                        .noPath()) {
                        double x = entity.posX + (rand.nextDouble() * 5) - (rand.nextDouble() * 5);
                        double y = entity.posY + (rand.nextDouble() * 5) - (rand.nextDouble() * 5);
                        double z = entity.posZ + (rand.nextDouble() * 5) - (rand.nextDouble() * 5);
                        if (((EntityCreature) entity).getNavigator()
                            .tryMoveToXYZ(x, y, z, 1.0)) break;
                    }
                }
            }
        }

        if (TragicConfig.allowHarmony && event.entityLiving.isPotionActive(TragicPotion.Harmony)) {
            if (event.entityLiving instanceof EntityEnderman || event.entityLiving instanceof EntityDragon
                || event.entityLiving instanceof EntityWither
                || event.entityLiving instanceof TragicBoss) {
                event.entityLiving.removePotionEffect(TragicPotion.Harmony.id);
            }
        }

        if (TragicConfig.allowHarmony && entity.isPotionActive(TragicPotion.Harmony.id)) {
            if (entity instanceof EntityCreature) {
                ((EntityCreature) entity).setPathToEntity(null);
                ((EntityCreature) entity).setAttackTarget(null);
            }

            if (entity.getActivePotionEffect(TragicPotion.Harmony)
                .getDuration() == 0) {
                entity.removePotionEffect(TragicPotion.Harmony.id);
            }
        }

        if (TragicConfig.allowMalnourish && entity.isPotionActive(TragicPotion.Malnourish.id)) {
            if (entity instanceof EntityPlayerMP) {
                int amp = entity.getActivePotionEffect(TragicPotion.Malnourish)
                    .getAmplifier() * 2;
                FoodStats food = ((EntityPlayerMP) entity).getFoodStats();
                entity.worldObj.getGameRules()
                    .getGameRuleBooleanValue("naturalRegeneration");

                if (amp > 10) {
                    amp = 10;
                }

                if (amp <= 0) {
                    amp = 1;
                }

                int f = food.getFoodLevel();
                int limit = amp >= 10 ? 9 : (amp > 6 ? 13 : (amp > 3 ? 15 : 19));

                if (entity.getHealth() >= entity.getMaxHealth() - amp) {
                    if (f >= limit) {
                        if (f - amp < 0) f = amp;
                        food.addStats(-amp, -amp);
                    }

                    int chance = Math.abs(MathHelper.ceiling_double_int(16 / amp) * 20);
                    if (chance < 8) chance = 8;
                    if (Math.abs(amp) >= 2 && rand.nextInt(chance) == 0) food.addExhaustion(0.5F);
                }
            }
        }

        if (TragicConfig.allowCripple && event.entityLiving.isPotionActive(TragicPotion.Cripple)) {
            if (event.entityLiving.getHealth() > event.entityLiving.getMaxHealth()) {
                event.entityLiving.setHealth(event.entityLiving.getMaxHealth());
            }
        }

        if (TragicConfig.allowLeadFoot && event.entityLiving.isPotionActive(TragicPotion.LeadFoot)) {
            event.entityLiving.motionY = -0.1D;
            if (TragicConfig.allowFlight && event.entityLiving.isPotionActive(TragicPotion.Flight))
                event.entityLiving.removePotionEffect(TragicPotion.Flight.id);
        }

        if (TragicConfig.allowConvergence && event.entityLiving.isPotionActive(TragicPotion.Convergence)
            && event.entityLiving instanceof EntityPlayerMP
            && TragicConfig.allowDoom) {
            PropertyDoom doom = PropertyDoom.get((EntityPlayer) event.entityLiving);
            if (doom != null && doom.getCurrentCooldown() > 0) doom.setCooldown(0);
            if (doom != null && event.entityLiving.ticksExisted % 5 == 0 && rand.nextBoolean()) {
                doom.increaseDoom(rand.nextInt(4));
            }
        }

        if (!TragicConfig.allowAnimalGolemCorruption && TragicConfig.allowCorruption
            && event.entityLiving.isPotionActive(TragicPotion.Corruption.id)
            && (event.entityLiving instanceof EntityGolem || event.entityLiving instanceof EntityAnimal)) {
            event.entityLiving.removePotionEffect(TragicPotion.Corruption.id);
        }
    }

    @SubscribeEvent
    public void denyJump(LivingJumpEvent event) {
        if (TragicConfig.allowLeadFoot && event.entityLiving.isPotionActive(TragicPotion.LeadFoot))
            event.entityLiving.motionY = -0.1D;
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void clarifyInvisibleEnemies(LivingUpdateEvent event) {
        if (event.entityLiving.worldObj.isRemote) {
            EntityLivingBase entity = event.entityLiving;
            World world = entity.worldObj;

            if (TragicConfig.allowClarity && entity.isPotionActive(TragicPotion.Clarity)) {
                double d0 = 16.0D + (8.0D * entity.getActivePotionEffect(TragicPotion.Clarity).getAmplifier());

                List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(entity, entity.boundingBox.expand(d0, d0, d0));
                EntityLivingBase target;

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) instanceof EntityLivingBase) {
                        target = (EntityLivingBase) list.get(i);
                        if (target.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer)) {
                            for (int j = 0; j < 4; j++) {
                                double d1 = target.width * (rand.nextDouble() - rand.nextDouble());
                                double d2 = target.height * rand.nextDouble();
                                double d3 = target.width * (rand.nextDouble() - rand.nextDouble());
                                EntityPortalFX fx = new EntityPortalFX(
                                    target.worldObj,
                                    target.posX + d1,
                                    target.posY + d2,
                                    target.posZ + d3,
                                    0.0,
                                    0.0,
                                    0.0);
                                fx.setRBGColorF(1.0F, 1.0F, 1.0F);
                                Minecraft.getMinecraft().effectRenderer.addEffect(fx);
                            }
                        }
                    }
                }
            }

            if (TragicConfig.allowDisorientation && entity.isPotionActive(TragicPotion.Disorientation)) {
                if (entity.ticksExisted % 60 == 0) {
                    float strafe = rand.nextFloat() * MathHelper.getRandomIntegerInRange(rand, -2, 2);
                    float forward = rand.nextFloat() * MathHelper.getRandomIntegerInRange(rand, -2, 2);
                    entity.moveEntityWithHeading(strafe, forward);
                }

                if (entity instanceof EntityPlayer) {
                    ((EntityPlayer) entity).cameraYaw = (rand.nextFloat() - rand.nextFloat()) * 2.25F;
                    ((EntityPlayer) entity).cameraPitch = (rand.nextFloat() - rand.nextFloat()) * 2.25F;
                }
            }
        }
    }

    @SubscribeEvent
    public void livingJump(LivingJumpEvent event) {
        Boolean flag1 = false;
        Boolean flag2 = false;

        if (TragicConfig.allowStun) {
            flag1 = event.entityLiving.isPotionActive(TragicPotion.Stun.id);
        }

        if (TragicConfig.allowCripple) {
            flag2 = event.entityLiving.isPotionActive(TragicPotion.Cripple.id);
        }

        if (flag1) // || event.entityLiving.isPotionActive(TragicPotion.Frozen.id))
        {
            event.entityLiving.motionY = 0.0;
            event.entityLiving.motionZ = 0.0;
            event.entityLiving.motionX = 0.0;
            event.entityLiving.onGround = true;

            if (event.entityLiving instanceof EntityPlayer)
                ((EntityPlayer) event.entityLiving).capabilities.isFlying = false;
        }

        if (flag2 && !flag1) {
            event.entityLiving.motionY *= 0.9;
        }
    }

    @SubscribeEvent
    public void potionFallEvent(LivingFallEvent event) {
        if (TragicConfig.allowFlight && event.entityLiving.isPotionActive(TragicPotion.Flight.id)) {
            if (event.isCancelable()) event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void potionDeathEvent(LivingDeathEvent event) {
        if (event.entity.worldObj.isRemote) return;

        if (event.entityLiving instanceof EntityPlayer && TragicConfig.allowResurrection) {
            EntityPlayer player = (EntityPlayer) event.entityLiving;
            if (player.isPotionActive(TragicPotion.Resurrection.id)) {
                if (event.isCancelable()) {
                    event.setCanceled(true);
                }

                float amp = player.getActivePotionEffect(TragicPotion.Resurrection)
                    .getAmplifier();

                if (amp > 3.0F) {
                    amp = 3.0F;
                }

                float percent = (amp + 1) / 4;
                player.setHealth((player.getMaxHealth() * percent));

                for (int i = 0; i < Potion.potionTypes.length; i++) {
                    if (Potion.potionTypes[i] != null && player.isPotionActive(i)) {
                        player.removePotionEffect(i);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onItemUsed(Start event) {
        if (TragicConfig.allowMalnourish && event.entityPlayer.isPotionActive(TragicPotion.Malnourish)
            && event.item.getItem() instanceof ItemFood) {
            int amp = event.entityPlayer.getActivePotionEffect(TragicPotion.Malnourish)
                .getAmplifier() * 2;

            if (amp > event.entityPlayer.getMaxHealth()) {
                amp = (int) event.entityPlayer.getMaxHealth();
            }

            if (amp <= 0) {
                amp = 1;
            }

            if (event.entityPlayer.getHealth() >= event.entityPlayer.getMaxHealth() - amp) {
                if (event.isCancelable()) event.setCanceled(true);
            }
        }

        if (TragicConfig.allowStun && event.entityPlayer.isPotionActive(TragicPotion.Stun)
            || TragicConfig.allowFear && event.entityPlayer.isPotionActive(TragicPotion.Fear)
            || TragicConfig.allowHacked && event.entityPlayer.isPotionActive(TragicPotion.Hacked)) // ||
                                                                                                   // event.entityPlayer.isPotionActive(TragicPotion.Frozen))
        {
            if (event.isCancelable()) event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void whileUsingItem(Tick event) {
        if (TragicConfig.allowStun && event.entityPlayer.isPotionActive(TragicPotion.Stun)
            || TragicConfig.allowFear && event.entityPlayer.isPotionActive(TragicPotion.Fear)
            || TragicConfig.allowHacked && event.entityPlayer.isPotionActive(TragicPotion.Hacked)) // ||
                                                                                                   // event.entityPlayer.isPotionActive(TragicPotion.Frozen))
        {
            if (event.isCancelable()) event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onDig(BreakEvent event) {
        if (event.getPlayer() != null && TragicConfig.allowStun
            && event.getPlayer()
                .isPotionActive(TragicPotion.Stun)
            || TragicConfig.allowFear && event.getPlayer() != null
                && event.getPlayer()
                    .isPotionActive(TragicPotion.Fear)
            || TragicConfig.allowHacked && event.getPlayer() != null
                && event.getPlayer()
                    .isPotionActive(TragicPotion.Hacked)) // || event.getPlayer().isPotionActive(TragicPotion.Frozen))
        {
            if (event.isCancelable()) event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onBreaking(BreakSpeed event) {
        if (TragicConfig.allowStun && event.entityPlayer.isPotionActive(TragicPotion.Stun)
            || TragicConfig.allowFear && event.entityPlayer.isPotionActive(TragicPotion.Fear)
            || TragicConfig.allowHacked && event.entityPlayer.isPotionActive(TragicPotion.Hacked)) // ||
                                                                                                   // event.entityPlayer.isPotionActive(TragicPotion.Frozen))
        {
            if (event.isCancelable()) event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onAttack(LivingAttackEvent event) {
        if (event.source.getEntity() != null && event.source.getEntity() instanceof EntityLivingBase) {
            if (TragicConfig.allowHarmony
                && ((EntityLivingBase) event.source.getEntity()).isPotionActive(TragicPotion.Harmony)) {
                if (event.isCancelable()) event.setCanceled(true);
            }

            if (TragicConfig.allowStun
                && ((EntityLivingBase) event.source.getEntity()).isPotionActive(TragicPotion.Stun)) {
                if (event.isCancelable()) event.setCanceled(true);
            }
            /*
             * if (((EntityLivingBase) event.source.getEntity()).isPotionActive(TragicPotion.Frozen))
             * {
             * if (event.isCancelable()) event.setCanceled(true);
             * }
             */

            if (((EntityLivingBase) event.source.getEntity()).isPotionActive(Potion.confusion)
                && TragicConfig.allowNauseaRandomMiss
                && rand.nextInt(100) <= TragicConfig.nauseaMissChance
                && !event.source.isProjectile()
                && !event.source.isMagicDamage()
                && !event.source.isUnblockable()
                && !event.source.isExplosion()) {
                if (event.isCancelable()) event.setCanceled(true);
            }

            if (((EntityLivingBase) event.source.getEntity()).isPotionActive(Potion.blindness)
                && TragicConfig.allowBlindnessReachDebuff
                && !event.source.isProjectile()
                && !event.source.isExplosion()
                && !event.source.isMagicDamage()) {
                int a = ((EntityLivingBase) event.source.getEntity()).getActivePotionEffect(Potion.blindness)
                    .getAmplifier();
                double r = -TragicConfig.blindnessReachDebuffAmount * (double) a;

                IAttributeInstance ins = ((EntityLivingBase) event.source.getEntity())
                    .getEntityAttribute(AmuletModifier.reach);
                double d = ins != null ? ins.getAttributeValue() : 0.0;
                double d2 = TragicConfig.allowReach
                    && ((EntityLivingBase) event.source.getEntity()).getHeldItem() != null
                        ? EnchantmentHelper.getEnchantmentLevel(
                            TragicEnchantments.Reach.effectId,
                            ((EntityLivingBase) event.source.getEntity()).getHeldItem())
                        : 0;
                double c = event.source.getEntity() instanceof EntityPlayer
                    && ((EntityPlayer) event.source.getEntity()).capabilities.isCreativeMode ? 2.5 : 1.5;
                if (event.entityLiving.getDistanceToEntity(event.source.getEntity()) - event.entityLiving.width
                    > c + r + d + d2 && event.isCancelable()) event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void submissiveHurt(LivingHurtEvent event) {
        if (TragicConfig.allowSubmission && event.entityLiving.isPotionActive(TragicPotion.Submission)) {
            int x = event.entityLiving.getActivePotionEffect(TragicPotion.Submission)
                .getAmplifier() + 1; // +1 because base amplifier is 0
            // for (int m = 0; m < x; m++) event.ammount *= 1.5F; //exponential
            event.ammount += event.ammount * 0.5 * x; // linear

            event.entityLiving.motionX *= 1 + 0.15 * x;
            event.entityLiving.motionZ *= 1 + 0.15 * x;
            event.entityLiving.motionY *= 1 + 0.15 * x;
        }

        if (event.entityLiving.isPotionActive(Potion.confusion) || event.entityLiving.isPotionActive(Potion.blindness)
            || TragicConfig.allowDisorientation && event.entityLiving.isPotionActive(TragicPotion.Disorientation)
            || TragicConfig.allowFear && event.entityLiving.isPotionActive(TragicPotion.Fear)) {
            event.ammount *= 1.15;
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void invulnerableHurt(LivingHurtEvent event) {
        if (TragicConfig.allowInvulnerability && event.entityLiving.isPotionActive(TragicPotion.Invulnerability)
            && !event.source.canHarmInCreative()) {
            if (event.isCancelable()) event.setCanceled(true);
            if (event.source.getSourceOfDamage() != null)
                event.entityLiving.applyEntityCollision(event.source.getSourceOfDamage());
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void renderHandWhileStunned(RenderHandEvent event) {
        if (TragicConfig.allowStun && Minecraft.getMinecraft().thePlayer.isPotionActive(TragicPotion.Stun)) // ||
                                                                                                            // Minecraft.getMinecraft().thePlayer.isPotionActive(TragicPotion.Frozen))
        {
            if (event.isCancelable()) event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void ignoreTargetsWithHarmony(LivingSetAttackTargetEvent event) {
        if (!event.entityLiving.worldObj.isRemote && event.target != null
            && (TragicConfig.allowHarmony && event.entityLiving.isPotionActive(TragicPotion.Harmony)
                || TragicConfig.allowFear && event.entityLiving.isPotionActive(TragicPotion.Fear))) {
            if (event.entityLiving instanceof EntityCreature && !(event.entityLiving instanceof EntityWither)
                && !(event.entityLiving instanceof EntityDragon)
                && !(event.entityLiving instanceof TragicBoss)) {
                ((EntityCreature) event.entityLiving).setTarget(null);
                ((EntityLiving) event.entityLiving).setAttackTarget(null);
            }
        }
    }

    @SubscribeEvent
    public void onCorruptedAttack(LivingHurtEvent event) {
        if (event.entityLiving.worldObj.isRemote || !TragicConfig.allowCorruption) return;

        if (!event.entityLiving.isPotionActive(TragicPotion.Corruption.id)) {
            if (event.source.getEntity() instanceof EntityLivingBase) {
                EntityLivingBase source = (EntityLivingBase) event.source.getEntity();

                if (source.isPotionActive(TragicPotion.Corruption.id)) {
                    PotionEffect effect = source.getActivePotionEffect(TragicPotion.Corruption);
                    event.entityLiving.addPotionEffect(new PotionEffect(effect.getPotionID(), effect.getDuration()));
                }
            }
        }
    }

    @SubscribeEvent
    public void onCorruptedInteract(EntityInteractEvent event) {
        if (event.entityPlayer.worldObj.isRemote || !(event.target instanceof EntityLivingBase)
            || !TragicConfig.allowCorruption) return;

        EntityLivingBase target = (EntityLivingBase) event.target;
        ItemStack stack = event.entityPlayer.getCurrentEquippedItem();
        if (target.isPotionActive(TragicPotion.Corruption) && stack != null) {
            if (stack.getItem() == TragicItems.Deathglow) {
                target.removePotionEffect(TragicPotion.Corruption.id);
                if (TragicConfig.allowImmunity)
                    target.addPotionEffect(new PotionEffect(TragicPotion.Immunity.id, 6000));
                --stack.stackSize;
            } else if (stack.getItem() == TragicItems.GoldenSushi) {
                target.removePotionEffect(TragicPotion.Corruption.id);
                if (TragicConfig.allowImmunity)
                    target.addPotionEffect(new PotionEffect(TragicPotion.Immunity.id, 6000));
                target.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 2400, 4));
                target.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 1200, 1));
                target.addPotionEffect(new PotionEffect(Potion.field_76443_y.id, 2400, 1));
                target.addPotionEffect(new PotionEffect(Potion.regeneration.id, 2400, 1));
                if (TragicConfig.allowClarity)
                    target.addPotionEffect(new PotionEffect(TragicPotion.Clarity.id, 2400, 1));
                --stack.stackSize;
            }
        }
    }
    /*
     * @SubscribeEvent
     * public void onHeal(LivingHealEvent event)
     * {
     * if (event.isCancelable() && event.entityLiving.isPotionActive(TragicPotion.EvilPresence))
     * event.setCanceled(true);
     * }
     */
}
