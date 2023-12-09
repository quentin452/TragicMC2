package tragicneko.tragicmc.items.weapons;

import com.google.common.collect.Sets;
import cpw.mods.fml.common.eventhandler.Event.Result;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.doomsday.Doomsday;

import java.util.Set;

public class ItemScythe extends TragicTool {

    private static final Set blocksEffectiveAgainst = Sets.newHashSet(
        new Block[] { Blocks.tallgrass, Blocks.brown_mushroom_block, Blocks.cactus, Blocks.red_mushroom_block,
            Blocks.carrots, Blocks.cocoa, Blocks.deadbush, Blocks.double_plant, Blocks.hay_block, Blocks.leaves,
            Blocks.leaves2, Blocks.melon_block, Blocks.melon_stem, Blocks.nether_wart, Blocks.potatoes,
            Blocks.pumpkin_stem, Blocks.red_flower, Blocks.reeds, Blocks.sapling, Blocks.sponge, Blocks.tripwire,
            Blocks.vine, Blocks.waterlily, Blocks.web, Blocks.wheat, Blocks.yellow_flower, TragicBlocks.CarrotBlock,
            TragicBlocks.PotatoBlock, TragicBlocks.AshenTallGrass, TragicBlocks.DriedGrass,
            TragicBlocks.StarlitTallGrass, TragicBlocks.PaintedTallGrass, TragicBlocks.Glowvine, TragicBlocks.DeadBush,
            TragicBlocks.AshenBush, TragicBlocks.TragicFlower, TragicBlocks.TragicSapling,
            TragicBlocks.HallowedLeafTrim, TragicBlocks.HallowedLeaves, TragicBlocks.DarkLeaves,
            TragicBlocks.WickedVine, TragicBlocks.DarkVine });

    public ItemScythe(ToolMaterial par2Material, Doomsday dday) {
        super(3.0F, par2Material, blocksEffectiveAgainst, dday);
        this.setHarvestLevel("scythe", 3);
        this.setCreativeTab(TragicMC.Survival);
    }

    @Override
    public float func_150893_a(ItemStack stack, Block block) {
        Material material = block.getMaterial();
        return material == Material.plants || material == Material.vine
            || material == Material.coral
            || material == Material.leaves
            || material == Material.gourd ? this.toolMaterial.getEfficiencyOnProperMaterial()
                : super.func_150893_a(stack, block);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float f,
        float f1, float f2) {
        if (!player.canPlayerEdit(x, y, z, meta, stack)) {
            return false;
        } else {
            UseHoeEvent event = new UseHoeEvent(player, stack, world, x, y, z);
            if (MinecraftForge.EVENT_BUS.post(event)) {
                return false;
            }

            if (event.getResult() == Result.ALLOW) {
                stack.damageItem(1, player);
                return true;
            }

            Block block = world.getBlock(x, y, z);

            if (meta != 0 && world.getBlock(x, y + 1, z)
                .isAir(world, x, y + 1, z) && (block == Blocks.grass || block == Blocks.dirt)) {
                Block block1 = Blocks.farmland;
                world.playSoundEffect(
                    x + 0.5F,
                    y + 0.5F,
                    z + 0.5F,
                    block1.stepSound.getStepResourcePath(),
                    (block1.stepSound.getVolume() + 1.0F) / 2.0F,
                    block1.stepSound.getPitch() * 0.8F);

                if (world.isRemote) {
                    return true;
                } else {
                    world.setBlock(x, y, z, block1);
                    stack.damageItem(1, player);
                    return true;
                }
            } else {
                return false;
            }
        }
    }
}
