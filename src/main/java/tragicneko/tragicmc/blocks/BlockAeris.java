package tragicneko.tragicmc.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.blocks.tileentity.TileEntityAeris;

import java.util.List;
import java.util.Random;

public class BlockAeris extends BlockBush implements ITileEntityProvider {

    @SideOnly(Side.CLIENT)
    private IIcon corruptingTexture, corruptedTexture;

    public BlockAeris() {
        super();
        this.setBlockName("tragicmc.aeris");
        this.setCreativeTab(TragicMC.Survival);
        this.setTickRandomly(true);
        this.setStepSound(soundTypeGrass);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (meta == 1) return this.corruptingTexture;
        if (meta == 2) return this.corruptedTexture;
        return this.blockIcon;
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon("tragicmc:PureAeris");
        this.corruptedTexture = par1IconRegister.registerIcon("tragicmc:FullyCorruptedAeris");
        this.corruptingTexture = par1IconRegister.registerIcon("tragicmc:PartiallyCorruptedAeris");
    }

    @Override
    public void getSubBlocks(Item par1, CreativeTabs par2, List par3) {
        for (int i = 0; i < 3; i++) par3.add(new ItemStack(par1, 1, i));
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityAeris();
    }

    @Override
    public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_,
        int p_149749_6_) {
        super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
        p_149749_1_.removeTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);
    }

    @Override
    public boolean onBlockEventReceived(World p_149696_1_, int p_149696_2_, int p_149696_3_, int p_149696_4_,
        int p_149696_5_, int p_149696_6_) {
        super.onBlockEventReceived(p_149696_1_, p_149696_2_, p_149696_3_, p_149696_4_, p_149696_5_, p_149696_6_);
        TileEntity tileentity = p_149696_1_.getTileEntity(p_149696_2_, p_149696_3_, p_149696_4_);
        return tileentity != null ? tileentity.receiveClientEvent(p_149696_5_, p_149696_6_) : false;
    }

    @Override
    public int damageDropped(int par1) {
        return par1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        if (y <= 50 && world.provider.dimensionId == TragicConfig.collisionID && !world.canBlockSeeTheSky(x, y, z)
            || world.getBlockMetadata(x, y, z) >= 2) {
            int pow = world.getBlockMetadata(x, y, z) >= 2 ? 8 : 3;

            for (int l = 0; l < pow; ++l) {
                double d1 = y + rand.nextFloat();
                double d3 = 0.0D;
                double d4 = 0.0D;
                double d5 = 0.0D;
                int i1 = rand.nextInt(2) * 2 - 1;
                int j1 = rand.nextInt(2) * 2 - 1;
                d3 = (rand.nextFloat() - 0.5D) * 0.125D;
                d4 = (rand.nextFloat() - 0.5D) * 0.125D;
                d5 = (rand.nextFloat() - 0.5D) * 0.125D;
                double d2 = z + 0.5D + 0.25D * j1;
                d5 = rand.nextFloat() * 1.0F * j1;
                double d0 = x + 0.5D + 0.25D * i1;
                d3 = rand.nextFloat() * 1.0F * i1;
                world.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
            }

            if (rand.nextInt(16) == 0 || world.getBlockMetadata(x, y, z) >= 2) {
                for (int i = 0; i < 3; i++) {
                    world.spawnParticle(
                        "witchMagic",
                        x + (rand.nextDouble() - rand.nextDouble()) * 0.25 + 0.5,
                        y + rand.nextDouble() * 0.35 + 0.25,
                        z + (rand.nextDouble() - rand.nextDouble()) * 0.25 + 0.5,
                        0,
                        0,
                        0);
                }
            }
        }
    }
}
