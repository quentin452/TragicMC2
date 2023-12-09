package tragicneko.tragicmc.items.amulet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.properties.PropertyAmulets;

import static tragicneko.tragicmc.TragicMC.rand;

public class AmuletUndead extends ItemAmulet {

    public AmuletUndead() {
        super("Undead", EnumAmuletType.CURSED, 0x898989, 0x777777);
    }

    @Override
    public void onAmuletUpdate(final PropertyAmulets amu, final EntityPlayer player, final World world, final byte slot,
        final byte level) {
        if (TragicConfig.amuUndead && !world.isRemote) {
            boolean flag = false;

            if (world.canBlockSeeTheSky((int) player.posX, (int) player.posY, (int) player.posZ) && world.isDaytime()
                && player.ticksExisted % 10 == 0) {
                player.setFire(8 + rand.nextInt(4));
                flag = true;
            }

            if (player.isPotionActive(Potion.weakness)) {
                PotionEffect effect = player.getActivePotionEffect(Potion.weakness);
                player.removePotionEffect(Potion.weakness.id);
                player.addPotionEffect(
                    new PotionEffect(Potion.damageBoost.id, effect.getDuration(), effect.getAmplifier()));
                flag = true;
            }

            if (player.isPotionActive(Potion.poison)) {
                PotionEffect effect = player.getActivePotionEffect(Potion.poison);
                player.removePotionEffect(Potion.poison.id);
                player.addPotionEffect(
                    new PotionEffect(Potion.regeneration.id, effect.getDuration(), effect.getAmplifier()));
                flag = true;
            }

            if (TragicConfig.allowCripple && player.isPotionActive(TragicPotion.Cripple)) {
                PotionEffect effect = player.getActivePotionEffect(TragicPotion.Cripple);
                player.removePotionEffect(TragicPotion.Cripple.id);
                player.addPotionEffect(
                    new PotionEffect(Potion.field_76434_w.id, effect.getDuration(), effect.getAmplifier()));
                flag = true;
            }

            if (flag) this.damageAmulet(amu, slot, level);
        }
    }
}
