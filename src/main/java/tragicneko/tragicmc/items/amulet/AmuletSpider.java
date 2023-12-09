package tragicneko.tragicmc.items.amulet;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.properties.PropertyAmulets;
import tragicneko.tragicmc.util.WorldHelper;

import java.util.List;

public class AmuletSpider extends ItemAmulet {

    public AmuletSpider() {
        super("Spider", EnumAmuletType.NORMAL, 0x555555, 0xCF5555);
    }

    @Override
    public void onAmuletUpdate(final PropertyAmulets amu, final EntityPlayer player, final World world, final byte slot,
        final byte level) {
        if (TragicConfig.amuSpider) {
            double d0 = level == 1 ? 0.03 : (level == 2 ? 0.5 : 0.11);
            if (player.isCollidedHorizontally) {
                if (player.isSneaking()) player.motionY = 0;
                else player.motionY = d0;
                player.motionX = MathHelper.clamp_double(player.motionX, -d0, d0);
                player.motionZ = MathHelper.clamp_double(player.motionZ, -d0, d0);
                if (player.motionY < -0.15) player.motionY = -0.15;
            }

            if (!world.isRemote) {
                List<int[]> list = WorldHelper
                    .getBlocksInSphericalRange(world, 1.0, (int) player.posX, (int) player.posY, (int) player.posZ);
                boolean flag = false;
                for (int i = 0; i < list.size() && !flag; i++) {
                    int[] coords = list.get(i);
                    Block block = world.getBlock(coords[0], coords[1], coords[2]);
                    if (block.isOpaqueCube() && coords[1] >= player.posY) flag = true;
                }

                if (player.motionY > 0
                    && (player.motionX <= d0 && player.motionX >= -d0 || player.motionZ <= d0 && player.motionZ >= -d0)
                    && flag) {
                    player.fallDistance = 0;
                    if (player.ticksExisted % 8 == 0) this.damageAmulet(amu, slot, level);
                }
            }

            if (level >= 3 && player.ticksExisted % 20 == 0 && TragicConfig.allowImmunity)
                player.addPotionEffect(new PotionEffect(TragicPotion.Immunity.id, 100, 0));
        }
    }
}
