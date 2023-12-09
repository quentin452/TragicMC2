package tragicneko.tragicmc.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.worldgen.WorldGenAshenTree;
import tragicneko.tragicmc.worldgen.WorldGenBleachedOakTree;
import tragicneko.tragicmc.worldgen.WorldGenDarkForestTree;
import tragicneko.tragicmc.worldgen.WorldGenHallowedTree;
import tragicneko.tragicmc.worldgen.WorldGenLargePaintedTree;
import tragicneko.tragicmc.worldgen.WorldGenPaintedTree;

public class BlockTragicSapling extends Block implements IGrowable, IPlantable {

    private String[] treeNames = new String[] { "Painted", "Bleached", "Ashen", "Hallowed", "Darkwood" };
    private IIcon[] iconArray = new IIcon[treeNames.length];

    public BlockTragicSapling() {
        super(Material.plants);
        this.setBlockName("tragicmc.sapling");
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        this.setCreativeTab(TragicMC.Survival);
        this.setStepSound(soundTypeGrass);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_,
        int p_149668_4_) {
        return null;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return 1;
    }

    @Override
    public int damageDropped(int p_149692_1_) {
        return p_149692_1_;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item par1, CreativeTabs par2, List par3) {
        for (int i = 0; i < this.treeNames.length; i++) {
            par3.add(new ItemStack(par1, 1, i));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        for (int i = 0; i < treeNames.length; i++) {
            iconArray[i] = p_149651_1_.registerIcon("tragicmc:" + treeNames[i] + "Sapling");
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (meta >= this.iconArray.length) {
            meta = this.iconArray.length - 1;
        }
        return this.iconArray[meta];
    }

    @Override
    public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_,
        boolean p_149851_5_) {
        return true;
    }

    @Override
    public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_,
        int p_149852_5_) {
        return true;
    }

    @Override
    public void func_149853_b(World world, Random rand, int x, int y, int z) {
        if (rand.nextBoolean()) return;
        int meta = world.getBlockMetadata(x, y, z);
        Object object = null;

        switch (meta) {
            case 0:
                if (rand.nextBoolean()) {
                    object = new WorldGenPaintedTree(true, rand.nextBoolean());
                } else {
                    object = new WorldGenLargePaintedTree(true, rand.nextInt(3) + 4, 10);
                }
                break;
            case 1:
                object = new WorldGenBleachedOakTree(true, true);
                break;
            case 2:
                object = new WorldGenAshenTree(true);
                break;
            case 3:
                object = new WorldGenHallowedTree(true);
                break;
            case 4:
                object = new WorldGenDarkForestTree();
                break;
            default:
                return;
        }

        world.setBlockToAir(x, y, z);

        if (object instanceof WorldGenerator) {
            WorldGenerator worldGen = (WorldGenerator) object;
            if (!worldGen.generate(world, rand, x, y - 1, z)) {
                world.setBlock(x, y, z, this, meta, 2);
            }
        }
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Plains;
    }

    @Override
    public Block getPlant(IBlockAccess world, int x, int y, int z) {
        return this;
    }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z);
    }
}
