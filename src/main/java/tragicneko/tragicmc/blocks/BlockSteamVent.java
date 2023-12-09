package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;

public class BlockSteamVent extends Block {

    private IIcon topIcon;
    private IIcon sideIcon;
    private IIcon bottomIcon;

    public BlockSteamVent() {
        super(Material.rock);
        this.setCreativeTab(TragicMC.Survival);
        this.setBlockName("tragicmc.steamVent");
        this.setBlockTextureName("tragicmc:Geyser");
        this.setHarvestLevel("pickaxe", 1);
        this.setTickRandomly(true);
        this.setResistance(10.0F);
        this.setHardness(1.0F);
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
        this.topIcon = par1IconRegister.registerIcon("tragicmc:SteamVentTop");
        this.sideIcon = par1IconRegister.registerIcon("tragicmc:MoltenRockSide");
        this.bottomIcon = par1IconRegister.registerIcon("tragicmc:MoltenRockBottom");
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
    }

    @Override
    public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
        entity.motionY += 0.8 * world.rand.nextDouble();
        entity.velocityChanged = true;
        if (!entity.isImmuneToFire() && world.rand.nextInt(256) == 0) entity.setFire(8 + world.rand.nextInt(4));
    }

    @Override
    public int tickRate(World world) {
        return 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        if (!world.getBlock(x, y + 1, z)
            .isOpaqueCube()
            && !world.getBlock(x, y + 2, z)
                .isOpaqueCube()) {
            for (int i = 0; i < 16; i++) {
                world.spawnParticle(
                    "cloud",
                    x + ((rand.nextDouble() - rand.nextDouble()) * 0.5) + 0.5,
                    y + 0.25,
                    z + ((rand.nextDouble() - rand.nextDouble()) * 0.5) + 0.5,
                    (rand.nextFloat() - rand.nextFloat()) * 0.1F,
                    rand.nextFloat() * 0.4F,
                    (rand.nextFloat() - rand.nextFloat()) * 0.1F);
            }
        }
    }
}
