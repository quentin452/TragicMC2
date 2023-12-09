package tragicneko.tragicmc.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;

import java.util.ArrayList;
import java.util.Random;

public class BlockFruit extends BlockCocoa {

    public IIcon[] stageIcons;

    public BlockFruit() {
        super();
        this.setCreativeTab(TragicMC.Creative);
        this.setStepSound(soundTypeGrass);
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        int l = getDirection(world.getBlockMetadata(x, y, z));
        x += Direction.offsetX[l];
        z += Direction.offsetZ[l];
        Block block = world.getBlock(x, y, z);
        return block.getMaterial() == Material.wood && block.getMaterial()
            .blocksMovement() && block.renderAsNormalBlock();
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return this.blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getCocoaIcon(int meta) {
        return this.stageIcons.length > meta ? this.stageIcons[meta] : this.stageIcons[0];
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon("tragicmc:SkyFruit");
        this.stageIcons = new IIcon[] { par1IconRegister.registerIcon("tragicmc:SkyFruitPod"),
            par1IconRegister.registerIcon("tragicmc:SkyFruitPod2"),
            par1IconRegister.registerIcon("tragicmc:SkyFruitPod3") };
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int level) {
        return meta > 3 ? TragicItems.SkyFruit : TragicItems.SkyFruitSeeds;
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

        int count = func_149987_c(metadata);

        if (count < 1) count = 1;
        Item item = getItemDropped(metadata, world.rand, fortune);

        for (int i = 0; i < count + fortune; i++) {
            if (item != null) {
                ret.add(new ItemStack(item, 1, 0));
            }
        }
        return ret;
    }
}
