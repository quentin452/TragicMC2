package tragicneko.tragicmc.events;

import java.lang.reflect.Field;
import java.util.UUID;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.blocks.BlockQuicksand;
import tragicneko.tragicmc.dimension.SynapseWorldProvider;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.items.weapons.TragicWeapon;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.properties.PropertyMisc;

public class MiscEvents {

    public static AttributeModifier synthMod = new AttributeModifier(
        UUID.fromString("c77b57e3-fbb3-4f31-a26e-3e614c57d7ef"),
        "synthesisModifier",
        TragicConfig.modifier[22],
        0);
    public static AttributeModifier hydraMod = new AttributeModifier(
        UUID.fromString("a0de9d5c-2fa2-4042-8261-f68bec735e56"),
        "hydrationKnockbackResistanceBuff",
        TragicConfig.modifier[19],
        0);
    public static AttributeModifier moonMod = new AttributeModifier(
        UUID.fromString("7913bbbe-8b78-4e5f-8a7e-1d429e0ef1b6"),
        "moonlightModifier",
        TragicConfig.modifier[21],
        0);
    public static AttributeModifier lightMod = new AttributeModifier(
        UUID.fromString("7611c3b7-5bb8-4597-849b-c75788f8cc9b"),
        "lightningRodAttackBuff",
        TragicConfig.modifier[20],
        0);

    public static boolean DO_FIRE_REFLECT = true;

    @SubscribeEvent
    public void quicksandJumping(LivingJumpEvent event) {
        if (!TragicConfig.allowNonMobBlocks) return;
        if (event.entityLiving instanceof EntityPlayer && event.entityLiving.worldObj.getBlock(
            (int) event.entityLiving.posX,
            (int) event.entityLiving.posY,
            (int) event.entityLiving.posZ) instanceof BlockQuicksand) event.entityLiving.motionY *= 0.625D;
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void whileHoldingSpecialWeapon(LivingHurtEvent event) {
        if (!TragicConfig.allowNonDoomsdayAbilities) return;

        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entityLiving;
            PropertyDoom doom = PropertyDoom.get(player);

            if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem()
                .getItem() instanceof TragicWeapon && doom != null) {
                Item weapon = player.getCurrentEquippedItem()
                    .getItem();

                if (weapon == TragicItems.BlindingLight && event.source.isProjectile() && TragicConfig.doomAbility[3]) {
                    if (event.isCancelable() && TragicWeapon.canUseAbility(doom, TragicConfig.doomAbilityCost[3])) {
                        if (!player.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.doomAbilityCost[3]);
                        event.setCanceled(true);
                    }
                }

                if (weapon == TragicItems.CelestialAegis
                    && TragicWeapon.canUseAbility(doom, TragicConfig.doomAbilityCost[7])
                    && TragicConfig.doomAbility[7]) {
                    event.ammount *= 0.825F;
                    if (!player.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.doomAbilityCost[7]);
                }
            }
        } else if (event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.source.getEntity();
            PropertyDoom doom = PropertyDoom.get(player);
            ItemStack stack = player.getCurrentEquippedItem();

            if (stack != null && stack.getItem() instanceof TragicWeapon && doom != null) {
                Item weapon = stack.getItem();

                if (weapon == TragicItems.Butcher && TragicWeapon.canUseAbility(doom, TragicConfig.doomAbilityCost[4])
                    && TragicConfig.doomAbility[4]) {
                    event.entity.motionX = event.entity.posX - player.posX;
                    event.entity.motionY = event.entity.posY - player.posY;
                    event.entity.motionZ = event.entity.posZ - player.posZ;

                    if (player.isSprinting() && player.motionY < 0.0 && player.fallDistance > 0) {
                        event.entity.motionX *= 1.2D;
                        event.entity.motionY *= 1.15D;
                        event.entity.motionZ *= 1.2D;
                    }

                    if (!player.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.doomAbilityCost[4]);
                    TragicWeapon.setStackCooldown(stack, 5);
                } else if (weapon == TragicItems.Splinter
                    && TragicWeapon.canUseAbility(doom, TragicConfig.doomAbilityCost[27])
                    && player.worldObj.rand.nextInt(4) == 0
                    && TragicConfig.doomAbility[27]) {
                        event.entity.motionX = (player.worldObj.rand.nextDouble() - player.worldObj.rand.nextDouble())
                            * 2.75D;
                        event.entity.motionY = (player.worldObj.rand.nextDouble() - player.worldObj.rand.nextDouble())
                            * 2.75D;
                        event.entity.motionZ = (player.worldObj.rand.nextDouble() - player.worldObj.rand.nextDouble())
                            * 2.75D;

                        if (!player.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.doomAbilityCost[27]);
                        TragicWeapon.setStackCooldown(stack, 5);
                    } else if (weapon == TragicItems.GravitySpike
                        && TragicWeapon.canUseAbility(doom, TragicConfig.doomAbilityCost[15])
                        && TragicConfig.doomAbility[15]) {
                            double d0 = 16.0D;
                            double d1 = event.entity.posX - player.posX;
                            double d2 = event.entity.posZ - player.posZ;
                            float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
                            double d3 = 5.0D;

                            event.entity.motionX = d1 / f2 * d3 * 0.800000011920929D
                                + event.entity.motionX * 0.60000000298023224D;
                            event.entity.motionZ = d2 / f2 * d3 * 0.800000011920929D
                                + event.entity.motionZ * 0.60000000298023224D;
                            event.entity.motionY += 1.45;

                            if (!player.capabilities.isCreativeMode)
                                doom.increaseDoom(-TragicConfig.doomAbilityCost[15]);
                            TragicWeapon.setStackCooldown(stack, 5);
                        }
            }

        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onLightningHurt(EntityStruckByLightningEvent event) {
        if (event.entity instanceof EntityPlayerMP && TragicConfig.allowNonDoomsdayAbilities) {
            EntityPlayerMP mp = (EntityPlayerMP) event.entity;
            PropertyDoom doom = PropertyDoom.get(mp);

            if (mp.getCurrentEquippedItem() != null
                && TragicWeapon.canUseAbility(doom, TragicConfig.doomAbilityCost[33])
                && TragicConfig.doomAbility[33]) {
                if (mp.getCurrentEquippedItem()
                    .getItem() == TragicItems.Titan) {
                    if (event.isCancelable()) event.setCanceled(true);
                    if (mp.getHealth() <= mp.getMaxHealth()) mp.heal(mp.getMaxHealth() * 0.25F);
                    if (!mp.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.doomAbilityCost[33]);
                }
            }
        }
    }

    @SubscribeEvent
    public void onMagicAttack(LivingAttackEvent event) {
        if (event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayerMP
            && TragicConfig.allowNonMobItems) {
            EntityPlayerMP player = (EntityPlayerMP) event.source.getEntity();
            ItemStack stack = player.getEquipmentInSlot(0);

            if (stack != null
                && (stack.getItem() == TragicItems.Thardus || stack.getItem() == TragicItems.FrozenLightning)) {
                event.source.setMagicDamage(); // instead of canceling and subbing in magic damage, we can just change
                                               // the source to act as magic damage
            }
        }
    }

    @SubscribeEvent
    public void carrotAndPotatoBlockCreation(BonemealEvent event) {
        if (!TragicConfig.allowNonMobBlocks) return;
        if (event.block == Blocks.carrots) {
            int meta = event.world.getBlockMetadata(event.x, event.y, event.z);

            if (meta >= 7) {
                if (event.world.rand.nextInt(4) == 0) {
                    event.world.setBlock(event.x, event.y, event.z, TragicBlocks.CarrotBlock);
                    event.world.playSoundAtEntity(event.entityPlayer, "random.pop", 0.8F, 1.0F);
                }
                event.setResult(Result.ALLOW);
            }
        }

        if (event.block == Blocks.potatoes) {
            int meta = event.world.getBlockMetadata(event.x, event.y, event.z);

            if (meta >= 7) {
                if (event.world.rand.nextInt(4) == 0) {
                    event.world.setBlock(event.x, event.y, event.z, TragicBlocks.PotatoBlock);
                    event.world.playSoundAtEntity(event.entityPlayer, "random.pop", 0.8F, 1.0F);
                }
                event.setResult(Result.ALLOW);
            }
        }
    }

    @SubscribeEvent
    public void denyDimensionVanillaGen(OreGenEvent.GenerateMinable event) {
        if (event.world.provider instanceof TragicWorldProvider
            || event.world.provider instanceof SynapseWorldProvider) {
            if (event.hasResult()) event.setResult(Result.DENY);
        }
    }

    @SubscribeEvent
    public void onUpdate(LivingUpdateEvent event) {
        if (event.entityLiving.worldObj.isRemote) return;

        if (event.entityLiving instanceof EntityPlayer) {
            if (TragicConfig.allowBurned) {
                if (event.entityLiving.isBurning()) {
                    int burn = 5;

                    if (DO_FIRE_REFLECT) {
                        try {
                            Field f = ReflectionHelper.findField(Entity.class, "fire");
                            burn = f.getInt(event.entityLiving);
                        } catch (Exception e) {
                            TragicMC.logError("Error caused while reflecting for burn potion effect", e);
                            event.entityLiving.addPotionEffect(new PotionEffect(TragicPotion.Burned.id, burn, 0));
                            DO_FIRE_REFLECT = false;
                        }
                    }

                    event.entityLiving.addPotionEffect(new PotionEffect(TragicPotion.Burned.id, burn, 0));
                } else if (event.entityLiving.isPotionActive(TragicPotion.Burned.id)) {
                    event.entityLiving.removePotionEffect(TragicPotion.Burned.id);
                }
            }

            EntityPlayer player = (EntityPlayer) event.entityLiving;
            int i = 0;
            if (!TragicConfig.allowNonMobItems) return;

            for (byte a = 1; a < 5; a++) {
                if (player.getEquipmentInSlot(a) != null) {
                    Item armor = player.getEquipmentInSlot(a)
                        .getItem();

                    if (armor == TragicItems.OverlordHelm || armor == TragicItems.OverlordPlate
                        || armor == TragicItems.OverlordLegs
                        || armor == TragicItems.OverlordBoots) i++;
                }
            }

            AttributeModifier mod = new AttributeModifier(
                UUID.fromString("1fc1fb49-44ae-4cc2-a6d2-c3109188c9d2"),
                "overlordArmorHealthMod",
                TragicConfig.modifier[24] * i,
                0);
            IAttributeInstance ins = player.getEntityAttribute(SharedMonsterAttributes.maxHealth);

            if (ins != null) {
                ins.removeModifier(mod);
                if (i > 0) ins.applyModifier(mod);

                ins.removeModifier(synthMod);
                if (player.inventory.hasItem(TragicItems.SynthesisTalisman) && !player.worldObj.isRaining()
                    && !player.worldObj.isThundering()) {
                    ins.applyModifier(synthMod);
                }

                ins.removeModifier(moonMod);
                if (player.inventory.hasItem(TragicItems.MoonlightTalisman) && !player.worldObj.isRaining()
                    && !player.worldObj.isThundering()
                    && !player.worldObj.isDaytime()) {
                    ins.applyModifier(moonMod);
                }
            }

            ins = player.getEntityAttribute(SharedMonsterAttributes.knockbackResistance);

            if (ins != null) {
                ins.removeModifier(hydraMod);

                if (player.inventory.hasItem(TragicItems.HydrationTalisman)
                    && (player.worldObj.isRaining() || player.isInsideOfMaterial(Material.water))) {
                    ins.applyModifier(hydraMod);
                }
            }

            ins = player.getEntityAttribute(SharedMonsterAttributes.attackDamage);

            if (ins != null) {
                ins.removeModifier(lightMod);

                if (player.inventory.hasItem(TragicItems.LightningRodTalisman) && player.worldObj.isThundering()) {
                    ins.applyModifier(lightMod);
                }
            }
        }
    }

    @SubscribeEvent
    public void onEntityConstructing(EntityConstructing event) {
        if (event.entity instanceof EntityLivingBase) {
            PropertyMisc misc = PropertyMisc.get((EntityLivingBase) event.entity);

            if (misc == null) {
                PropertyMisc.register((EntityLivingBase) event.entity);
            } else {
                misc.loadNBTData(new NBTTagCompound());
            }
        }
    }

    @SubscribeEvent
    public void onEntityUpdate(LivingUpdateEvent event) {
        if (event.entity instanceof EntityLivingBase) {
            PropertyMisc misc = PropertyMisc.get((EntityLivingBase) event.entity);
            if (misc != null) misc.onUpdate();
        }
    }
    /*
     * @SubscribeEvent
     * public void onPlayerKill(LivingDeathEvent event)
     * {
     * if (event.source.getEntity() instanceof EntityPlayer) // && TragicConfig.allowPets
     * {
     * PropertyMisc misc = PropertyMisc.get((EntityLivingBase) event.source.getEntity());
     * if (misc != null)
     * {
     * if (misc.getCurrentPet() != null)
     * {
     * misc.getCurrentPet().addExperience(event.entityLiving);
     * }
     * }
     * }
     * }
     */
}
