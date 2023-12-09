package tragicneko.tragicmc.events;

import static tragicneko.tragicmc.TragicConfig.modifier;

import java.util.UUID;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingPackSizeEvent;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.boss.TragicBoss;
import tragicneko.tragicmc.entity.miniboss.TragicMiniBoss;
import tragicneko.tragicmc.entity.mob.TragicMob;
import tragicneko.tragicmc.properties.PropertyMisc;

public class DynamicHealthScaling {

    private static AttributeModifier normalHealthDebuff = new AttributeModifier(
        UUID.fromString("967abeb0-255d-4ac9-bfa7-9636b25b8ca0"),
        "dynamicMobHealthDebuff",
        modifier[10],
        0);
    private static AttributeModifier normalHealthBuff = new AttributeModifier(
        UUID.fromString("3abc10da-144e-4f04-a5dd-f9d7e5feeafe"),
        "dynamicMobHealthBuff",
        modifier[11],
        0);
    private static AttributeModifier bossHealthBuff = new AttributeModifier(
        UUID.fromString("909ba6e3-8763-4720-8fb4-c36db00da69b"),
        "dynamicBossHealthBuff",
        modifier[13],
        0);

    @SubscribeEvent
    public void onJoinWorld(EntityJoinWorldEvent event) {
        if (!TragicConfig.allowDynamicHealthScaling || !(event.entity instanceof EntityLivingBase)) return;

        PropertyMisc misc = PropertyMisc.get((EntityLivingBase) event.entity);
        if (misc == null || misc.hasBeenBuffed()) return;

        misc.setBuffed(); // to prevent them from being rebuffed and regenerating health

        if (event.entity.worldObj.difficultySetting == EnumDifficulty.EASY) {
            if (event.entity instanceof TragicMob) {
                IAttributeInstance att = ((EntityLivingBase) event.entity)
                    .getEntityAttribute(SharedMonsterAttributes.maxHealth);

                if (att.getBaseValue() > Math.abs(normalHealthDebuff.getAmount()) * 2) {
                    att.removeModifier(normalHealthDebuff);
                    att.applyModifier(normalHealthDebuff);
                }
            } else if (event.entity instanceof TragicBoss) {
                event.entity.setDead();
            }
        }

        if (event.entity.worldObj.difficultySetting == EnumDifficulty.HARD) {
            if (event.entity instanceof TragicMob) {
                IAttributeInstance att = ((EntityLivingBase) event.entity)
                    .getEntityAttribute(SharedMonsterAttributes.maxHealth);

                if (att.getBaseValue() > Math.abs(normalHealthBuff.getAmount()) * 2) {
                    att.removeModifier(normalHealthBuff);
                    att.applyModifier(normalHealthBuff);
                    ((EntityLivingBase) event.entity).heal(((EntityLivingBase) event.entity).getMaxHealth());
                }
            } else if (event.entity instanceof TragicBoss) {
                IAttributeInstance att = ((EntityLivingBase) event.entity)
                    .getEntityAttribute(SharedMonsterAttributes.maxHealth);

                if (att.getBaseValue() > Math.abs(bossHealthBuff.getAmount()) * 1.25) {
                    att.removeModifier(bossHealthBuff);
                    att.applyModifier(bossHealthBuff);
                    ((EntityLivingBase) event.entity).heal(((EntityLivingBase) event.entity).getMaxHealth());
                }
            }
        }
    }

    @SubscribeEvent
    public void onEntitySpawn(LivingPackSizeEvent event) {
        if (event.entityLiving instanceof TragicBoss || event.entityLiving instanceof TragicMiniBoss) {
            if (event.entityLiving instanceof TragicBoss && ((TragicBoss) event.entityLiving).isHalloween()) return;
            if (event.entityLiving instanceof TragicMob && ((TragicMob) event.entityLiving).isHalloween()) return;
            event.maxPackSize = 1;
            event.setResult(Result.ALLOW);
        }
    }
}
