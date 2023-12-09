package tragicneko.tragicmc.items.amulet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.properties.PropertyAmulets;

import static tragicneko.tragicmc.TragicMC.rand;

public class AmuletSunken extends ItemAmulet {

    public AmuletSunken() {
        super("Sunken", EnumAmuletType.CURSED, 0x0000FF, 0x466DB3);
    }

    @Override
    public void onAmuletUpdate(final PropertyAmulets amu, final EntityPlayer player, final World world, final byte slot,
        final byte level) {
        if (TragicConfig.amuSunken && player.ticksExisted % 60 == 0
            && TragicConfig.allowAquaSuperiority
            && player.isInWater()) {
            player.addPotionEffect(new PotionEffect(TragicPotion.AquaSuperiority.id, 200, level));
            if (player.isPotionActive(TragicPotion.AquaSuperiority) && rand.nextBoolean() && !world.isRemote)
                this.damageAmulet(amu, slot, level);
        }
    }
}
