package tragicneko.tragicmc.items.food;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFoodSeed extends Item {

    public final Block block;

    public ItemFoodSeed(Block block) {
        super();
        this.block = block;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
        float hitX, float hitY, float hitZ) {
        if (world.isRemote) return true;

        Block block = world.getBlock(x, y, z);

        if (block == Blocks.snow_layer && (world.getBlockMetadata(x, y, z) & 7) < 1) {
            side = 1;
        } else if (block != Blocks.vine && block != Blocks.tallgrass && block != Blocks.deadbush) {
            if (side == 0) {
                --y;
            }

            if (side == 1) {
                ++y;
            }

            if (side == 2) {
                --z;
            }

            if (side == 3) {
                ++z;
            }

            if (side == 4) {
                --x;
            }

            if (side == 5) {
                ++x;
            }
        }

        if (!player.canPlayerEdit(x, y, z, side, stack)) {
            return false;
        } else if (stack.stackSize == 0) {
            return false;
        } else if (!(this.block.canPlaceBlockAt(world, x, y, z))) {
            return false;
        } else {
            if (world.canPlaceEntityOnSide(this.block, x, y, z, false, side, (Entity) null, stack)) {
                int i1 = this.block.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, 0);

                if (world.setBlock(x, y, z, this.block, i1, 3)) {
                    if (world.getBlock(x, y, z) == this.block) {
                        this.block.onBlockPlacedBy(world, x, y, z, player, stack);
                        this.block.onPostBlockPlaced(world, x, y, z, i1);
                    }

                    world.playSoundEffect(
                        (double) ((float) x + 0.5F),
                        (double) ((float) y + 0.5F),
                        (double) ((float) z + 0.5F),
                        this.block.stepSound.func_150496_b(),
                        (this.block.stepSound.getVolume() + 1.0F) / 2.0F,
                        this.block.stepSound.getPitch() * 0.8F);
                    --stack.stackSize;

                    if (!this.block.canBlockStay(world, x, y, z)) this.block.updateTick(world, x, y, z, world.rand);
                }
            }
        }

        return true;
    }
}
