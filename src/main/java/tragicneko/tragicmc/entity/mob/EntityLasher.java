package tragicneko.tragicmc.entity.mob;

import net.minecraft.world.World;

public class EntityLasher extends TragicMob {

    public EntityLasher(World par1World) {
        super(par1World);
    }

    @Override
    protected boolean isChangeAllowed() {
        return false;
    }

}
