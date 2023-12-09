package tragicneko.tragicmc.items.amulet;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.properties.PropertyAmulets;

import java.util.List;

import static tragicneko.tragicmc.TragicMC.rand;

public class AmuletPeace extends ItemAmulet {

    public AmuletPeace() {
        super("Peace", EnumAmuletType.NORMAL, 0x9F5B86, 0xFF9ACA);
    }

    @Override
    public void onAmuletUpdate(final PropertyAmulets amu, final EntityPlayer player, final World world, final byte slot,
        final byte level) {
        if (TragicConfig.amuPeace && player.ticksExisted % 60 == 0) {
            double d0 = 8.0 + (level * 4.0);
            int chance;

            if (level == 2) {
                chance = 25;
            } else if (level == 3) {
                chance = 40;
            } else {
                chance = 10;
            }

            List<Entity> list = world
                .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d0, d0, d0));

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof EntityMob && rand.nextInt(100) <= chance) {
                    if (!((EntityLivingBase) list.get(i)).isPotionActive(TragicPotion.Harmony.id)) {
                        ((EntityMob) list.get(i))
                            .addPotionEffect(new PotionEffect(TragicPotion.Harmony.id, 120 + rand.nextInt(320)));
                        if (!world.isRemote) this.damageAmulet(amu, slot, level);
                    }
                }
            }
        }
    }

}
