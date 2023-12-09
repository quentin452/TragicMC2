package tragicneko.tragicmc.entity.boss;

import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public interface IMultiPart {

    World getWorld();

    boolean attackEntityFromPart(EntityPart entity, DamageSource source, float damage);

    EntityPart getDefaultPart();
}
