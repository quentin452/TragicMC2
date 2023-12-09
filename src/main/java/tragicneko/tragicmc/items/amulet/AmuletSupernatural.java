package tragicneko.tragicmc.items.amulet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.events.AmuletEvents;
import tragicneko.tragicmc.properties.PropertyAmulets;

import static tragicneko.tragicmc.TragicMC.rand;

public class AmuletSupernatural extends ItemAmulet {

    public AmuletSupernatural() {
        super("Supernatural", EnumAmuletType.NORMAL, 0x99DD99, 0x87CE87);
    }

    @Override
    public void onAmuletUpdate(final PropertyAmulets amu, final EntityPlayer player, final World world, final byte slot,
        final byte level) {
        if (player.ticksExisted % 400 == 0 && TragicConfig.amuSupernatural && !world.isRemote) {
            int i = level * 25;
            int j = 100 - i;

            if (rand.nextInt(level + 1) != 0) {
                if (rand.nextInt(j) > rand.nextInt(i)) {
                    Potion pot = Potion.potionTypes[0];
                    while (pot == null || !AmuletEvents.badPotions.contains(pot)) {
                        pot = Potion.potionTypes[rand.nextInt(Potion.potionTypes.length)];
                    }
                    player.addPotionEffect(new PotionEffect(pot.id, 200 + rand.nextInt(100), rand.nextInt(5 - level)));
                } else {
                    Potion pot = Potion.potionTypes[0];
                    while (pot == null || AmuletEvents.badPotions.contains(pot)) {
                        pot = Potion.potionTypes[rand.nextInt(Potion.potionTypes.length)];
                    }
                    player.addPotionEffect(new PotionEffect(pot.id, 200 + rand.nextInt(200), rand.nextInt(level)));
                }
                this.damageAmulet(amu, slot, level);
            }
        }
    }
}
