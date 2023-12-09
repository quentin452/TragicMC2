package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class DoomsdayFireRain extends Doomsday implements IExtendedDoomsday {

    public DoomsdayFireRain(int id) {
        super(id, EnumDoomType.WORLDSHAPER);
    }

    @Override
    public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        super.doInitialEffects(effect, doom, player, crucMoment);
        player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 500));
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        double d1 = (rand.nextDouble() - rand.nextDouble()) * 4.0D;
        double d2 = (rand.nextDouble() - rand.nextDouble()) * 4.0D + 4.0D;
        double d3 = (rand.nextDouble() - rand.nextDouble()) * 4.0D;

        int i = crucMoment ? 16 : 8;
        if (crucMoment) addCrucialMessage(player);

        double y = player.posY - WorldHelper.getDistanceToGround(player);

        for (int l = 0; l < i; l++) {
            EntitySmallFireball fireball = new EntitySmallFireball(player.worldObj, player, -d1, -d2, -d3);
            fireball.setPosition(
                player.posX + rand.nextInt(12) - rand.nextInt(12),
                y + 18 + rand.nextInt(12),
                player.posZ + rand.nextInt(12) - rand.nextInt(12));
            player.worldObj.spawnEntityInWorld(fireball);
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {}

    @Override
    public Doomsday getCombination() {
        return Doomsday.DragonsRoar;
    }

    @Override
    public int getWaitTime() {
        return 10;
    }

    @Override
    public int getMaxIterations() {
        return 120;
    }
}
