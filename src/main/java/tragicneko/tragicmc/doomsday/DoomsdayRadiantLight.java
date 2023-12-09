package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayRadiantLight extends Doomsday {

    public DoomsdayRadiantLight(int id) {
        super(id, EnumDoomType.COMBINATION);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        double d0 = crucMoment ? 32.0 : 16.0;
        List<EntityCreature> list = player.worldObj
            .getEntitiesWithinAABB(EntityCreature.class, player.boundingBox.expand(d0, d0, d0));
        PotionEffect eff = new PotionEffect(Potion.weakness.id, 600, 10);

        for (EntityCreature ent : list) {
            ent.addPotionEffect(eff);
        }

        List<EntityPlayer> list2 = player.worldObj
            .getEntitiesWithinAABB(EntityPlayer.class, player.boundingBox.expand(d0, d0, d0));
        eff = new PotionEffect(Potion.damageBoost.id, 600, 10);

        for (EntityPlayer ply : list2) {
            ply.addPotionEffect(eff);
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

    }

}
