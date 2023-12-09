package tragicneko.tragicmc.worldgen.schematic;

import java.util.Random;

import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;

import tragicneko.tragicmc.TragicMC;

public abstract class Schematic {

    // public ArrayList<BlockPreset[][]> layers;
    public int width;
    public int height;
    public int structureHeight;

    // public int offsetX;
    // public int offsetY;
    // public int offsetZ;
    // may use these to deal with structures that don't have their origin matching the matrix

    public Schematic(int structureHeight, int w, int h) {
        // layers = new ArrayList<BlockPreset[][]>();
        // for (int i = 0; i < structureHeight; i++) layers.add(new BlockPreset[w][h]);
        this.width = w;
        this.height = h;
        this.structureHeight = structureHeight;
        // this.fillMatrices();
    }

    /*
     * public abstract void fillMatrices();
     * public void invertMatrix(BlockPreset[][] presets)
     * {
     * for (int k = 0; k < this.height; k++)
     * {
     * invertRow(presets, k);
     * }
     * }
     * public void invertRow(BlockPreset[][] presets, int row)
     * {
     * BlockPreset[] temp = presets[row];
     * BlockPreset block;
     * for (int i = 0; i < this.width; i++)
     * {
     * block = temp[i];
     * presets[row][this.width - i] = block;
     * }
     * }
     * public void rotateMatrix(BlockPreset[][] presets, int rotations)
     * {
     * for (int k = 0; k < this.height; k++)
     * {
     * rotateRow(presets, k, rotations);
     * }
     * }
     * public void rotateRow(BlockPreset[][] presets, int row, int rotations)
     * {
     * BlockPreset[] temp = presets[row];
     * BlockPreset block;
     * for (int i = 0; i < this.width; i++)
     * {
     * block = temp[i];
     * presets[row][i + rotations >= this.width ? (i + rotations - this.width) : (i + rotations)] = block;
     * }
     * }
     * public void rotateEntireMatrix()
     * {
     * int h = this.height;
     * int w = this.width;
     * for (int m = 0; m < layers.size(); m++)
     * {
     * BlockPreset[][] matrix = layers.get(m);
     * BlockPreset[][] newMatrix = new BlockPreset[h][w];
     * BlockPreset preset;
     * for (int i = 0; i < h; i++)
     * {
     * for (int k = 0; k < w; k++)
     * {
     * newMatrix[k][i] = matrix[i][k];
     * preset = newMatrix[k][i];
     * if (preset.block instanceof net.minecraft.block.BlockStairs) preset.meta = preset.meta < 4 ? (preset.meta + 1) %
     * 4 : (preset.meta + 1 % 4) + 4;
     * }
     * }
     * this.invertMatrix(newMatrix);
     * layers.set(m, newMatrix);
     * }
     * //invert width and height parameters since these are used to keep track of the matrices assumed width and height
     * this.height = w;
     * this.width = h;
     * }
     */

    /**
     * Main method to generate the particular structure, variants should be decided upon before this method is called,
     * this may split off variants
     * into their own methods at the schematic's discretion
     */
    public abstract boolean generateStructure(int variant, World world, Random rand, int x, int y, int z);

    /**
     * This tells the schematic to generate as the basic version
     */
    public boolean generateStructure(World world, Random rand, int x, int y, int z) {
        return this.generateStructure(0, world, rand, x, y, z);
    }

    /**
     * This tells the schematic to generate as a random variant, use the main generation method if you want to pick a
     * particular variant,
     * variantSize should be greater than 0, otherwise errors will ensue
     */
    public boolean generateWithRandomVariant(int variantSize, World world, Random rand, int x, int y, int z) {
        return this.generateStructure(rand.nextInt(variantSize), world, rand, x, y, z);
    }

    /**
     * Use this to apply chest contents to generated chests
     */
    public boolean applyChestContents(World world, Random rand, int x, int y, int z, ChestGenHooks hook) {
        if (y <= 0 || y >= 256) return false;

        TileEntityChest tileentity = (TileEntityChest) world.getTileEntity(x, y, z);
        if (tileentity != null) {
            WeightedRandomChestContent
                .generateChestContents(rand, hook.getItems(rand), tileentity, hook.getCount(rand));
            return true;
        } else {
            TragicMC.logWarning("Chest generation failed. The tile entity was null.");
            return false;
        }
    }

    public boolean addSignContents(World world, int x, int y, int z, int line, String text) {
        TileEntitySign sign = (TileEntitySign) world.getTileEntity(x, y, z);
        if (sign == null || line > 4) {
            TragicMC
                .logWarning("Sign text setup failed. The tile entity was null or an improper text line was chosen.");
            return false;
        }
        sign.signText[line] = text;
        return true;
    }

    public boolean setSpawnerMob(World world, int x, int y, int z, String mobName) {
        TileEntityMobSpawner spawner = (TileEntityMobSpawner) world.getTileEntity(x, y, z);
        if (spawner == null || mobName == null) {
            TragicMC.logWarning("Spawner setup failed. The tile entity was null or mobName was null.");
            return false;
        }
        spawner.func_145881_a()
            .setEntityName(mobName);
        return true;
    }

    /*
     * public static class BlockPreset {
     * public final Block block;
     * public int meta;
     * public boolean isSpecial; //used to determine if a preset has special randomization applied when generating, like
     * for a chest or luxury block
     * public final boolean forceReplace;
     * public BlockPreset(Block block, int meta, boolean replace)
     * {
     * this.block = block;
     * this.meta = meta;
     * this.forceReplace = replace;
     * }
     * }
     */
}
