package tragicneko.tragicmc.items.amulet;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.properties.PropertyAmulets;

import java.util.List;

public class AmuletEnyvil extends ItemAmulet {

    public AmuletEnyvil() {
        super("Enyvil", EnumAmuletType.EPIC, 0xFF6FFF, 0x5D1543);
    }

    @Override
    public void onAmuletUpdate(final PropertyAmulets amu, final EntityPlayer player, final World world, final byte slot,
        final byte level) {
        if (TragicConfig.amuEnyvil) {
            List<Entity> list = world
                .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(16.0, 16.0, 16.0));

            for (Entity e : list) {
                if (e instanceof EntityLivingBase) {
                    ((EntityLivingBase) e).addPotionEffect(
                        new PotionEffect(TragicConfig.allowFear ? TragicPotion.Fear.id : Potion.blindness.id, 120, 0));
                }
            }
        }
    }
}
