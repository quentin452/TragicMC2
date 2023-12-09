package tragicneko.tragicmc.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;

public class BlockMoltenRock extends Block {

    private IIcon topIcon;
    private IIcon sideIcon;
    private IIcon bottomIcon;

    public BlockMoltenRock() {
        super(Material.rock);
        this.setCreativeTab(TragicMC.Survival);
        this.setBlockName("tragicmc.moltenRock");
        this.setBlockTextureName("tragicmc:MoltenRock");
        this.setHarvestLevel("pickaxe", 0);
        this.setHardness(0.8F);
        this.setResistance(5.0F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2) {
        if (par1 == 0) return this.bottomIcon;
        if (par1 == 1) return this.topIcon;
        return this.sideIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.topIcon = par1IconRegister.registerIcon("tragicmc:MoltenRockTop");
        this.sideIcon = par1IconRegister.registerIcon("tragicmc:MoltenRockSide");
        this.bottomIcon = par1IconRegister.registerIcon("tragicmc:MoltenRockBottom");
    }

    @Override
    public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side) {
        return side == ForgeDirection.UP;
    }
}
