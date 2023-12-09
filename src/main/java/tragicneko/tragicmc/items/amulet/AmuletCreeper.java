package tragicneko.tragicmc.items.amulet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.properties.PropertyAmulets;

import static tragicneko.tragicmc.TragicMC.rand;

public class AmuletCreeper extends ItemAmulet {

    public AmuletCreeper() {
        super("Creeper", EnumAmuletType.NORMAL, 0x27C123, 0x43E140);
    }

    @Override
    public void onAmuletUpdate(final PropertyAmulets amu, final EntityPlayer player, final World world, final byte slot,
        final byte level) {
        if (TragicConfig.amuCreeper && player.ticksExisted % 60 == 0) {
            player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 600, level));
            if (player.isPotionActive(Potion.digSpeed) && rand.nextBoolean() && !world.isRemote)
                amu.damageStackInSlot(slot, 4 - level);
        }
    }
}
