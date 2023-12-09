package tragicneko.tragicmc.items.amulet;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.properties.PropertyAmulets;

import java.util.Iterator;
import java.util.List;

public class AmuletSnowGolem extends ItemAmulet {

    public AmuletSnowGolem() {
        super("SnowGolem", EnumAmuletType.NORMAL, 0xFFFDF1, 0xABA290);
    }

    @Override
    public void onAmuletUpdate(final PropertyAmulets amu, final EntityPlayer player, final World world, final byte slot,
        final byte level) {
        if (TragicConfig.amuSnowGolem) {
            double d = level * 16.0D + 16.0D;
            List<EntityItem> list = world.getEntitiesWithinAABB(EntityMob.class, player.boundingBox.expand(d, d, d));
            if (list.size() > 0 && player.ticksExisted % 10 == 0 && !world.isRemote)
                this.damageAmulet(amu, slot, level);
            Iterator ite = list.iterator();
            EntityMob mob;

            while (ite.hasNext()) {
                mob = (EntityMob) ite.next();
                mob.targetTasks.addTask(3, new EntityAINearestAttackableTarget(mob, EntityPlayer.class, 0, true));
                mob.setAttackTarget(player);
                mob.getEntityAttribute(SharedMonsterAttributes.followRange)
                    .setBaseValue(d + 32.0D);
            }
        }
    }
}
