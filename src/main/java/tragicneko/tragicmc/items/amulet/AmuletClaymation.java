package tragicneko.tragicmc.items.amulet;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.events.AmuletEvents;
import tragicneko.tragicmc.properties.PropertyAmulets;

import java.util.List;

import static tragicneko.tragicmc.TragicMC.rand;

public class AmuletClaymation extends ItemAmulet {

    public AmuletClaymation() {
        super("Claymation", EnumAmuletType.NORMAL, 0xFF9500, 0xFFCA02);
    }

    @Override
    public void onAmuletUpdate(final PropertyAmulets amu, final EntityPlayer player, final World world, final byte slot,
        final byte level) {
        if (TragicConfig.amuClaymation && player.ticksExisted % 60 == 0) {
            double d0 = 4.0 + (level * 4.0);
            int chance;
            int goodChance;

            if (level == 2) {
                chance = 40;
                goodChance = 5;
            } else if (level == 3) {
                chance = 60;
                goodChance = 15;
            } else {
                chance = 20;
                goodChance = -1;
            }

            List<Entity> list = world
                .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

            if (rand.nextInt(100) <= chance && list.size() > 0) {
                PotionEffect[] effects = new PotionEffect[16];
                PotionEffect temp;
                int a = 0;

                for (int i = 0; i < Potion.potionTypes.length; i++) {
                    if (list.size() == 0) break;

                    if (Potion.potionTypes[i] != null) {
                        Potion potion = Potion.potionTypes[i];

                        if (player.isPotionActive(potion) && AmuletEvents.badPotions.contains(potion)
                            && a < effects.length) {
                            temp = player.getActivePotionEffect(potion);
                            effects[a++] = new PotionEffect(
                                potion.id,
                                temp.getDuration() * level,
                                temp.getAmplifier() * level);
                            player.removePotionEffect(i);
                            if (!world.isRemote) this.damageAmulet(amu, slot, level);
                        }
                    }
                }

                for (int x = 0; x < list.size(); x++) {
                    if (list.get(x) instanceof EntityMob) {
                        EntityMob entity = (EntityMob) list.get(x);

                        for (PotionEffect effect : effects) {
                            if (effect != null) {
                                entity.addPotionEffect(effect);
                            }
                        }
                    }
                }

                if (rand.nextInt(100) <= goodChance) {
                    switch (rand.nextInt(5)) {
                        case 0:
                            player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 200, 0 + rand.nextInt(3)));
                            break;
                        case 1:
                            player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 200, 0 + rand.nextInt(3)));
                            break;
                        case 2:
                            player.addPotionEffect(new PotionEffect(Potion.resistance.id, 200, 0 + rand.nextInt(3)));
                            break;
                        case 3:
                            player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 200, 0 + rand.nextInt(3)));
                            break;
                        case 4:
                            player.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 200, 0 + rand.nextInt(3)));
                            break;
                    }
                }
            }
        }
    }

}
