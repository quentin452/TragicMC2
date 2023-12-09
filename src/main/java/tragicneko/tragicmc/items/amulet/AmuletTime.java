package tragicneko.tragicmc.items.amulet;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.properties.PropertyAmulets;

import java.util.Iterator;
import java.util.List;

import static tragicneko.tragicmc.TragicMC.rand;

public class AmuletTime extends ItemAmulet {

    public AmuletTime() {
        super("Time", EnumAmuletType.EPIC, 0x94FFA3, 0xEA92E9);
    }

    @Override
    public void onAmuletUpdate(final PropertyAmulets amu, final EntityPlayer player, final World world, final byte slot,
        final byte level) {
        if (TragicConfig.amuTime) {
            List<EntityItem> list = world
                .getEntitiesWithinAABB(EntityItem.class, player.boundingBox.expand(8.0, 8.0, 8.0));
            Iterator ite = list.iterator();
            EntityItem item;

            while (ite.hasNext()) {
                item = (EntityItem) ite.next();

                double d1 = item.posX - player.posX;
                double d2 = item.posZ - player.posZ;
                double d3 = item.posY - player.posY + 0.82;
                float f2 = MathHelper.sqrt_double(d1 * d1 + d2 * d2 + d3 * d3);
                double d4 = 0.25D;

                item.motionX = -d1 / f2 * d4 * 0.300000011920929D;
                item.motionZ = -d2 / f2 * d4 * 0.300000011920929D;
                item.motionY = -d3 / f2 * d4 * 0.300000011920929D;

                if (rand.nextBoolean()) item.motionY += rand.nextDouble() * 0.4D;
            }
        }
    }
}
