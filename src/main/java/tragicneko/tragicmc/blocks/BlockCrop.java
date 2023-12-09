package tragicneko.tragicmc.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockReed;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;

import java.util.Random;

public class BlockCrop extends BlockReed implements IGrowable {

    public IIcon icon;

    public BlockCrop() {
        super();
        this.setCreativeTab(TragicMC.Creative);
        this.setStepSound(soundTypeGrass);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return meta < 8 ? this.icon : this.blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        super.registerBlockIcons(par1IconRegister);
        this.icon = par1IconRegister.registerIcon(this.textureName + "Unripe");
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (world.getBlock(x, y - 1, z) == this || this.func_150170_e(world, x, y, z)) {
            if (world.isAirBlock(x, y + 1, z)) {
                int l;

                for (l = 1; world.getBlock(x, y - l, z) == this; ++l) {
                    ;
                }

                if (l < 5) // grow up to 5 high naturally
                {
                    int i1 = world.getBlockMetadata(x, y, z);

                    if (i1 == 15) {
                        world.setBlock(x, y + 1, z, this);
                        world.setBlockMetadataWithNotify(x, y, z, 15, 4);
                    } else {
                        world.setBlockMetadataWithNotify(x, y, z, i1 + 1, 4);
                    }
                }
            }
        }
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return super.canPlaceBlockAt(world, x, y, z)
            || this == TragicBlocks.Deathglow && world.getBlock(x, y - 1, z) == TragicBlocks.DarkCobblestone
            || world.getBlock(x, y - 1, z) instanceof BlockGenericGrass;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
        return this == TragicBlocks.Deathglow ? TragicItems.Deathglow : TragicItems.Honeydrop;
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_) {
        return -1;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Plains;
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune) {
        boolean flag = this == TragicBlocks.Deathglow;
        return meta < 8 ? (flag ? TragicItems.DeathglowSeeds : TragicItems.HoneydropSeeds)
            : (flag ? TragicItems.Deathglow : TragicItems.Honeydrop);
    }

    @Override
    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction,
        IPlantable plantable) {
        Block plant = plantable.getPlant(world, x, y + 1, z);
        return plant instanceof BlockCrop && plant == this
            || super.canSustainPlant(world, x, y, z, direction, plantable);
    }

    @Override
    public boolean func_149851_a(World world, int x, int y, int z, boolean rand) {
        return world.getBlockMetadata(x, y, z) <= 15 && world.getBlock(x, y + 1, z)
            .isAir(world, x, y + 1, z);
    }

    @Override
    public boolean func_149852_a(World world, Random rand, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z) <= 15 && world.getBlock(x, y + 1, z)
            .isAir(world, x, y + 1, z);
    }

    @Override
    public void func_149853_b(World world, Random rand, int x, int y, int z) {
        int m = world.getBlockMetadata(x, y, z);
        int r = rand.nextInt(4) + 1;
        if (m < 15 && m + r <= 15) {
            world.setBlockMetadataWithNotify(x, y, z, m + r, 4);
        } else if (m == 15) {
            this.updateTick(world, x, y, z, rand);
        }
    }
}
