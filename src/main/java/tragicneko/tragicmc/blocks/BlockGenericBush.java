package tragicneko.tragicmc.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.material.Material;

import tragicneko.tragicmc.TragicMC;

public class BlockGenericBush extends BlockBush {

    public BlockGenericBush() {
        super();
        this.setStepSound(soundTypeGrass);
        this.setCreativeTab(TragicMC.Survival);
    }

    @Override
    protected boolean canPlaceBlockOn(Block block) {
        return block instanceof BlockDirt || block instanceof BlockGrass
            || block.getMaterial() == Material.grass
            || block.getMaterial() == Material.ground;
    }
}
