package tragicneko.tragicmc.items.amulet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.properties.PropertyAmulets;

import static tragicneko.tragicmc.TragicMC.rand;

public class AmuletLuck extends ItemAmulet {

    public AmuletLuck() {
        super("Luck", EnumAmuletType.NORMAL, 0xBBBA56, 0xFFFA56);
    }

    @Override
    public void onAmuletUpdate(final PropertyAmulets amu, final EntityPlayer player, final World world, final byte slot,
        final byte level) {
        if (TragicConfig.amuLuck && player.ticksExisted % 300 == 0 && rand.nextInt(level * 2) != 0) {
            if (!world.isRemote) {
                player.addExperience(rand.nextInt(level * 10));
                this.damageAmulet(amu, slot, level);
            }
        }
    }
}
