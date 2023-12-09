package tragicneko.tragicmc.items.amulet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.properties.PropertyAmulets;

import static tragicneko.tragicmc.TragicMC.rand;

public class AmuletIronGolem extends ItemAmulet {

    public AmuletIronGolem() {
        super("IronGolem", EnumAmuletType.NORMAL, 0xDBCDC1, 0x8B7260);
    }

    @Override
    public void onAmuletUpdate(final PropertyAmulets amu, final EntityPlayer player, final World world, final byte slot,
        final byte level) {
        if (TragicConfig.amuIronGolem && player.ticksExisted % 20 == 0) {
            player.addPotionEffect(new PotionEffect(Potion.resistance.id, 600, level));
            if (player.isPotionActive(Potion.resistance) && rand.nextBoolean() && !world.isRemote)
                this.damageAmulet(amu, slot, level);
        }
    }
}
