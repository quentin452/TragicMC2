package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class DoomsdayGrowthSpurt extends Doomsday {

    public DoomsdayGrowthSpurt(int id) {
        super(id, EnumDoomType.COMBINATION);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

        float plantCount = 0.0F;
        int[] coords;

        double radius = crucMoment ? 12.0D : 7.0D;
        List<int[]> list = WorldHelper
            .getBlocksInSphericalRange(player.worldObj, radius, player.posX, player.posY, player.posZ);

        for (int i = 0; i < list.size(); i++) {
            coords = list.get(i);
            Block block = player.worldObj.getBlock(coords[0], coords[1], coords[2]);

            if (block == Blocks.gravel) {
                player.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.farmland);
                plantCount += 0.5;
            } else if (block == Blocks.dirt) {
                player.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.grass);
                plantCount += 0.5;
            } else if (block == Blocks.cobblestone) {
                player.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.mossy_cobblestone);
                plantCount += 0.5;
            } else if (block == Blocks.sand) {
                player.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.dirt);
                plantCount += 0.5;
            } else if (block == Blocks.air && rand.nextInt(4) == 0) {
                Block block2;
                if (World.doesBlockHaveSolidTopSurface(player.worldObj, coords[0], coords[1] - 1, coords[2])) {
                    block2 = player.worldObj.getBlock(coords[0], coords[1] - 1, coords[2]);
                    if (block2 == Blocks.grass) {
                        if (rand.nextBoolean()) {
                            if (rand.nextBoolean()) {
                                if (rand.nextBoolean()) {
                                    player.worldObj.setBlock(
                                        coords[0],
                                        coords[1],
                                        coords[2],
                                        Blocks.tallgrass,
                                        rand.nextInt(4),
                                        3);
                                } else {
                                    player.worldObj.setBlock(
                                        coords[0],
                                        coords[1],
                                        coords[2],
                                        TragicBlocks.TragicFlower,
                                        rand.nextInt(16),
                                        3);
                                }
                            } else {
                                Block block3 = rand.nextBoolean() ? Blocks.red_flower : Blocks.yellow_flower;
                                player.worldObj.setBlock(
                                    coords[0],
                                    coords[1],
                                    coords[2],
                                    block3,
                                    block3 == Blocks.red_flower ? rand.nextInt(8) : 0,
                                    3);
                            }
                        } else {
                            int meta = rand.nextInt(6);
                            player.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.double_plant, meta, 2);
                            player.worldObj.setBlock(coords[0], coords[1] + 1, coords[2], Blocks.double_plant, 8, 2);
                        }
                    } else if (block2 == Blocks.mycelium) {
                        player.worldObj.setBlock(
                            coords[0],
                            coords[1],
                            coords[2],
                            rand.nextBoolean() ? Blocks.red_mushroom : Blocks.brown_mushroom);
                    } else if (block2 instanceof BlockLog || block2 instanceof BlockLeaves) {
                        player.worldObj.setBlock(coords[0], coords[1], coords[2], Blocks.vine);
                    }
                }
            }
        }

        if (plantCount > 40.0F) plantCount = 40.0F;

        if (plantCount > 0.0F) {
            player.heal(plantCount);
        } else {
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Nowhere to grow plants!"));
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

    }

}
