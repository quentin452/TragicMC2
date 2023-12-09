package tragicneko.tragicmc.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;

public class BlockTragicFlower extends BlockBush implements IGrowable {

    private static final String[][] plantNames = new String[][] {
        { "BlueSpiranthes", "PinkSpiranthes", "RedSpiranthes", "WhiteSpiranthes", "BlueCoral", "RedCoral", "PinkGinger",
            "RedGinger", "Bluebonnet", "VioletSage", "PinkSage", "WhiteSage", "BirdOfParadise", "JuniperBush",
            "Stapelia", "Thistle" },
        { "Bramble", "Tangleweed", "DeathClaw", "Fusche", "Osiris", "Thusk", "Podtail", "FanBrush", "Torchweed",
            "Halon", "Rizaphora", "BlackSpot", "Nannon", "BarbedWire", "Kern", "Flahgrass" } };

    private IIcon[] iconArray = new IIcon[16];
    private final int flowerSet;

    public BlockTragicFlower(int i) {
        super();
        this.flowerSet = i;
        this.setCreativeTab(TragicMC.Survival);
        this.setBlockName("tragicmc.flower");
        this.setStepSound(soundTypeGrass);
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        return this.canPlaceBlockOn(world.getBlock(x, y - 1, z));
    }

    @Override
    protected boolean canPlaceBlockOn(Block block) {
        return block instanceof BlockGrass || block == Blocks.dirt
            || block.getMaterial() == Material.ground
            || block.getMaterial() == Material.grass
            || block == TragicBlocks.MoltenRock
            || block == TragicBlocks.ScorchedRock;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (meta >= this.iconArray.length || this.iconArray[meta] == null) {
            return this.iconArray[0];
        }
        return this.iconArray[meta];
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        for (int i = 0; i < this.plantNames[this.flowerSet].length; i++) {
            this.iconArray[i] = par1IconRegister.registerIcon("tragicmc:" + this.plantNames[this.flowerSet][i]);
        }
    }

    @Override
    public int damageDropped(int par1) {
        return par1;
    }

    @Override
    public void getSubBlocks(Item par1, CreativeTabs par2, List par3) {
        for (int i = 0; i < this.plantNames[this.flowerSet].length; i++) {
            par3.add(new ItemStack(par1, 1, i));
        }
    }

    @Override
    public boolean func_149851_a(World world, int x, int y, int z, boolean bool) {
        return true;
    }

    @Override
    public boolean func_149852_a(World world, Random rand, int x, int y, int z) {
        return true;
    }

    @Override
    public void func_149853_b(World world, Random rand, int x, int y, int z) {
        if (rand.nextInt(4) != 0) return;
        int meta = world.getBlockMetadata(x, y, z);

        if (this.flowerSet == 0 && meta == 14 && rand.nextInt(4) != 0) return;
        this.dropBlockAsItem(world, x, y, z, new ItemStack(this, 1, meta));
    }

}
