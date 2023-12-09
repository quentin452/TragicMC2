package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;

public class BlockGenericLeaves extends BlockLeaves {

    private IIcon[] ashenIcons = new IIcon[6];
    private IIcon opaqueIcon;

    public static boolean fancyGraphics;

    public BlockGenericLeaves() {
        super();
        this.field_150121_P = true;
        BlockGenericLeaves.fancyGraphics = false;
        this.setCreativeTab(TragicMC.Survival);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        if (this == TragicBlocks.AshenLeaves) {
            this.ashenIcons[0] = par1IconRegister.registerIcon("tragicmc:AshenLeaves");
            this.ashenIcons[1] = par1IconRegister.registerIcon("tragicmc:AshenLeavesTop");
            this.ashenIcons[2] = par1IconRegister.registerIcon("tragicmc:AshenLeavesSide");

            this.ashenIcons[3] = par1IconRegister.registerIcon("tragicmc:OpaqueAshenLeaves");
            this.ashenIcons[4] = par1IconRegister.registerIcon("tragicmc:OpaqueAshenLeavesTop");
            this.ashenIcons[5] = par1IconRegister.registerIcon("tragicmc:OpaqueAshenLeavesSide");
        } else {
            this.blockIcon = par1IconRegister.registerIcon("tragicmc:" + this.textureName);
            this.opaqueIcon = par1IconRegister.registerIcon("tragicmc:Opaque" + this.textureName);
        }
    }

    @Override
    public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {
        // if (p_149674_1_.isRemote) fancyGraphics = Minecraft.getMinecraft().gameSettings.fancyGraphics;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        // fancyGraphics = Minecraft.getMinecraft().gameSettings.fancyGraphics;

        if (this == TragicBlocks.AshenLeaves) {
            if (fancyGraphics) {
                if (side == 0) return ashenIcons[0];
                if (side == 1) return ashenIcons[1];
                return ashenIcons[2];
            } else {
                if (side == 0) return ashenIcons[3];
                if (side == 1) return ashenIcons[4];
                return ashenIcons[5];
            }
        }
        if (!fancyGraphics) return this.opaqueIcon;
        return this.blockIcon;
    }

    @Override
    public int damageDropped(int par1) {
        return this == TragicBlocks.PaintedLeaves ? 0
            : (this == TragicBlocks.BleachedLeaves ? 1
                : (this == TragicBlocks.AshenLeaves ? 2 : (this == TragicBlocks.HallowedLeaves ? 3 : 4)));
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.getItemFromBlock(TragicBlocks.TragicSapling);
    }

    @Override
    public String[] func_150125_e() {
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor() {
        return -1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int p_149741_1_) {
        return -1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_) {
        return -1;
    }

    @Override
    protected ItemStack createStackedBlock(int p_149644_1_) {
        int j = 0;
        Item item = Item.getItemFromBlock(this);

        if (item != null && item.getHasSubtypes()) {
            j = p_149644_1_;
        }

        return new ItemStack(item, 1, j);
    }

    @Override
    protected void func_150124_c(World p_150124_1_, int p_150124_2_, int p_150124_3_, int p_150124_4_, int p_150124_5_,
        int p_150124_6_) {
        if ((p_150124_5_) == 0 && p_150124_1_.rand.nextInt(p_150124_6_) == 0) {
            this.dropBlockAsItem(p_150124_1_, p_150124_2_, p_150124_3_, p_150124_4_, new ItemStack(Items.apple, 1, 0));
        }
    }

    @Override
    public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
        return true;
    }
}
