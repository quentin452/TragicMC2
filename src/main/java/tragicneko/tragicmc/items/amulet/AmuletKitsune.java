package tragicneko.tragicmc.items.amulet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.properties.PropertyAmulets;

import static tragicneko.tragicmc.TragicMC.rand;

public class AmuletKitsune extends ItemAmulet {

    public AmuletKitsune() {
        super("Kitsune", EnumAmuletType.CURSED, 0xFFD087, 0xFF0000);
    }

    @Override
    public void onAmuletUpdate(final PropertyAmulets amu, final EntityPlayer player, final World world, final byte slot,
        final byte level) {
        if (TragicConfig.amuKitsune && player.ticksExisted % 60 == 0) {
            if (player.isBurning()) player.extinguish();
            player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 600, 0));
            if (player.isPotionActive(Potion.fireResistance) && rand.nextBoolean() && !world.isRemote)
                this.damageAmulet(amu, slot, level);
        }
    }

}
