package tragicneko.tragicmc.blocks;

import static net.minecraftforge.common.util.ForgeDirection.DOWN;

import net.minecraft.block.BlockChest;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.blocks.tileentity.TileEntitySoulChest;

public class BlockSoulChest extends BlockChest {

    public BlockSoulChest(int open) {
        super(open);
        this.setCreativeTab(TragicMC.Creative);
    }

    @Override
    public int getRenderType() {
        return 42;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.blockIcon = reg.registerIcon("tragicmc:SoulChest");
    }

    @Override
    public IInventory func_149951_m(World world, int x, int y, int z) {
        Object object = world.getTileEntity(x, y, z);

        if (object == null) {
            return null;
        } else if (world.isSideSolid(x, y + 1, z, DOWN)) {
            return null;
        } else if (world.getBlock(x - 1, y, z) == this && (world.isSideSolid(x - 1, y + 1, z, DOWN))) {
            return null;
        } else if (world.getBlock(x + 1, y, z) == this && (world.isSideSolid(x + 1, y + 1, z, DOWN))) {
            return null;
        } else if (world.getBlock(x, y, z - 1) == this && (world.isSideSolid(x, y + 1, z - 1, DOWN))) {
            return null;
        } else if (world.getBlock(x, y, z + 1) == this && (world.isSideSolid(x, y + 1, z + 1, DOWN))) {
            return null;
        } else {
            if (world.getBlock(x - 1, y, z) == this) {
                object = new InventoryLargeChest(
                    "container.chestDouble",
                    (TileEntityChest) world.getTileEntity(x - 1, y, z),
                    (IInventory) object);
            }

            if (world.getBlock(x + 1, y, z) == this) {
                object = new InventoryLargeChest(
                    "container.chestDouble",
                    (IInventory) object,
                    (TileEntityChest) world.getTileEntity(x + 1, y, z));
            }

            if (world.getBlock(x, y, z - 1) == this) {
                object = new InventoryLargeChest(
                    "container.chestDouble",
                    (TileEntityChest) world.getTileEntity(x, y, z - 1),
                    (IInventory) object);
            }

            if (world.getBlock(x, y, z + 1) == this) {
                object = new InventoryLargeChest(
                    "container.chestDouble",
                    (IInventory) object,
                    (TileEntityChest) world.getTileEntity(x, y, z + 1));
            }

            return (IInventory) object;
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        TileEntitySoulChest tileentitychest = new TileEntitySoulChest(30);
        return tileentitychest;
    }

}
