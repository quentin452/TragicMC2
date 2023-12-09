package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLeaves;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class DoomsdayPermafrost extends Doomsday implements IExtendedDoomsday {

    public DoomsdayPermafrost(int id) {
        super(id, EnumDoomType.WORLDSHAPER);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

        double radius = crucMoment ? 8.0D : 4.0D;
        List list = WorldHelper
            .getBlocksInSphericalRange(player.worldObj, radius, player.posX, player.posY, player.posZ);
        List list2 = player.worldObj
            .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(radius, radius, radius));

        Block block;
        int[] coords;

        for (int i = 0; i < list2.size(); i++) {
            if (list2.get(i) instanceof EntityMob)
                ((EntityLivingBase) list2.get(i)).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 120, 1));
        }

        for (int i = 0; i < list.size(); i++) {
            coords = (int[]) list.get(i);
            block = player.worldObj.getBlock(coords[0], coords[1], coords[2]);

            if (block == Blocks.lava) {
                player.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.obsidian);
            } else if (block == Blocks.water) {
                player.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.ice);
            } else if (block == Blocks.ice) {
                player.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.packed_ice);
            } else if (block instanceof BlockBush || block instanceof BlockLeaves) {
                player.worldObj.setBlockToAir(coords[0], coords[1], coords[2]);
            } else if (block == Blocks.air
                && World.doesBlockHaveSolidTopSurface(player.worldObj, coords[0], coords[1] - 1, coords[2])
                && rand.nextBoolean()) {
                    player.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.snow_layer, rand.nextInt(8), 2);
                }
        }

        if (crucMoment) addCrucialMessage(player);
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
        player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 120, 1));
    }

    @Override
    public Doomsday getCombination() {
        return Doomsday.Freeze;
    }

    @Override
    public int getWaitTime() {
        return 5;
    }

    @Override
    public int getMaxIterations() {
        return 60;
    }
}
