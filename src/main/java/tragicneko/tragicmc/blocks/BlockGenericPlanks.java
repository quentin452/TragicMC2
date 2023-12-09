package tragicneko.tragicmc.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import tragicneko.tragicmc.TragicMC;

public class BlockGenericPlanks extends Block {

    public BlockGenericPlanks() {
        super(Material.wood);
        this.setCreativeTab(TragicMC.Survival);
        this.setResistance(7.0F);
        this.setHardness(0.7F);
        this.setStepSound(soundTypeWood);
        this.setHarvestLevel("axe", 0);
    }

}
