package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayEvacuation extends Doomsday {

    public DoomsdayEvacuation(int id) {
        super(id, EnumDoomType.COMBINATION);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        double d = crucMoment ? 32.0 : 16.0;
        List<Entity> list = player.worldObj
            .getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d, d, d));

        for (Entity entity : list) {
            double x = entity.posX;
            double y = entity.posY;
            double z = entity.posZ;

            if (!(entity instanceof EntityLivingBase)) continue;

            label0: for (int y1 = 0; y1 < 24; y1++) {
                for (int z1 = -8; z1 < 9; z1++) {
                    for (int x1 = -8; x1 < 9; x1++) {
                        if (World.doesBlockHaveSolidTopSurface(
                            player.worldObj,
                            (int) player.posX + x1,
                            (int) player.posY + y1 - 1,
                            (int) player.posZ + z1) && rand.nextBoolean()) {
                            if (entity instanceof EntityPlayerMP) {
                                EntityPlayerMP mp = (EntityPlayerMP) entity;

                                if (mp.capabilities.isCreativeMode || !TragicConfig.allowPvP) break label0;

                                if (mp.playerNetServerHandler.func_147362_b()
                                    .isChannelOpen() && player.worldObj == mp.worldObj) {
                                    if (mp.isRiding()) mp.mountEntity(null);
                                    AxisAlignedBB bb = mp.boundingBox.copy();
                                    bb.offset(x + x1, y + y1, z + z1);

                                    if (player.worldObj.checkNoEntityCollision(bb)
                                        && player.worldObj.getCollidingBoundingBoxes(mp, bb)
                                            .isEmpty()
                                        && !player.worldObj.isAnyLiquid(bb)) {
                                        mp.playerNetServerHandler.setPlayerLocation(
                                            x + x1,
                                            y + y1,
                                            z + z1,
                                            mp.rotationYaw,
                                            mp.rotationPitch);

                                        mp.addPotionEffect(new PotionEffect(Potion.blindness.id, 200, 0));
                                        mp.fallDistance = 0.0F;
                                        player.worldObj.playSoundAtEntity(mp, "tragicmc:mob.stin.teleport", 0.4F, 0.4F);
                                        break label0;
                                    }
                                }
                            } else {
                                entity.setPosition(x + x1, y + y1, z + z1);

                                if (player.worldObj.checkNoEntityCollision(entity.boundingBox)
                                    && player.worldObj.getCollidingBoundingBoxes(entity, entity.boundingBox)
                                        .isEmpty()
                                    && !player.worldObj.isAnyLiquid(entity.boundingBox)) {

                                    player.worldObj.playSoundAtEntity(entity, "tragicmc:mob.stin.teleport", 0.4F, 0.4F);
                                    ((EntityLivingBase) entity)
                                        .addPotionEffect(new PotionEffect(Potion.blindness.id, 100, 0));
                                    break label0;
                                } else {
                                    entity.setPosition(x, y, z);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

    }

}
