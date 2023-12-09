package tragicneko.tragicmc.worldgen.schematic;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicItems;

public class SchematicDesertTower extends Schematic {

    // this is for the base desert tower
    private static Block sand = Blocks.sand;
    private static Block sandstone = Blocks.sandstone;
    private static Block tnt = Blocks.tnt;
    private static Block plate = TragicBlocks.SandstonePressurePlate;
    private static Block ladder = Blocks.ladder;
    private static Block lava = Blocks.lava;
    private static Block sandStair = Blocks.sandstone_stairs;
    private static Block redstone = Blocks.redstone_wire;
    private static Block trapChest = Blocks.trapped_chest;
    private static Block slab = Blocks.stone_slab;

    private static Block hardClay = Blocks.hardened_clay;

    // this is for the plains variant
    private static Block cobble = Blocks.cobblestone;
    private static Block mossCob = Blocks.mossy_cobblestone;
    private static Block brick = Blocks.stonebrick;
    private static Block brickStair = Blocks.stone_brick_stairs;
    private static Block plateStone = Blocks.stone_pressure_plate;

    // this is for the nether variant
    private static Block netherBrick = Blocks.nether_brick;
    private static Block nether = Blocks.netherrack;
    private static Block netherStair = Blocks.nether_brick_stairs;
    private static Block plateNether = TragicBlocks.NetherBrickPressurePlate;
    private static Block netherFence = Blocks.nether_brick_fence;

    // this is for the snow/ice variant
    private static Block snow = Blocks.snow;
    private static Block ice = Blocks.packed_ice;

    // 9 luxury blocks currently
    public static Block[] luxuryBlocks = new Block[] { Blocks.diamond_block, Blocks.gold_block, Blocks.emerald_block,
        TragicBlocks.CompactOre, Blocks.iron_block, Blocks.lapis_block };

    public SchematicDesertTower() {
        super(25, 9, 9);
    }

    @Override
    public boolean generateStructure(int variant, World world, Random rand, int x, int y, int z) {
        switch (variant) {
            case 0: // Desert tower
                generateVariant0(world, rand, x, y, z);
                break;
            case 1: // Mesa tower
                generateVariant1(world, rand, x, y, z);
                break;
            default:
            case 2: // Plains/Forest tower
                generateVariant2(world, rand, x, y, z);
                break;
            case 3: // Nether tower
                generateVariant3(world, rand, x, y, z);
                break;
            case 4: // Ice tower
                generateVariant4(world, rand, x, y, z);
                break;
        }
        return true;
    }

    public void generateVariant0(World world, Random rand, int x, int y, int z) {
        // First layer

        // First row
        world.setBlock(x - 3, y, z - 3, sandstone, 1, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z); // Starting block of the whole schematic
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, sandstone, 1, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, sandstone, 0, 2);
        world.setBlock(x - 2, y, z - 1, sandstone, 0, 2);
        world.setBlockToAir(x - 2, y, z);
        world.setBlock(x - 2, y, z + 1, sandstone, 0, 2);
        world.setBlock(x - 2, y, z + 2, sandstone, 0, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, sandstone, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, sandstone, 0, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlockToAir(x, y, z - 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, sandstone, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlockToAir(x, y, z + 2);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, sandstone, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, sandstone, 0, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, sandstone, 0, 2);
        world.setBlock(x + 2, y, z - 1, sandstone, 0, 2);
        world.setBlockToAir(x + 2, y, z);
        world.setBlock(x + 2, y, z + 1, sandstone, 0, 2);
        world.setBlock(x + 2, y, z + 2, sandstone, 0, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, sandstone, 1, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, sandstone, 1, 2);

        // Second layer
        y++;

        // First row
        world.setBlock(x - 3, y, z - 3, sandstone, 1, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z);
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, sandstone, 1, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, sandstone, 0, 2);
        world.setBlock(x - 2, y, z - 1, sandstone, 0, 2);
        world.setBlockToAir(x - 2, y, z);
        world.setBlock(x - 2, y, z + 1, sandstone, 0, 2);
        world.setBlock(x - 2, y, z + 2, sandstone, 0, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, sandstone, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, sandstone, 0, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlockToAir(x, y, z - 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, sandstone, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlockToAir(x, y, z + 2);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, sandstone, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, sandstone, 0, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, sandstone, 0, 2);
        world.setBlock(x + 2, y, z - 1, sandstone, 0, 2);
        world.setBlockToAir(x + 2, y, z);
        world.setBlock(x + 2, y, z + 1, sandstone, 0, 2);
        world.setBlock(x + 2, y, z + 2, sandstone, 0, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, sandstone, 1, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, sandstone, 1, 2);

        // Third layer
        y++;

        // First row
        world.setBlock(x - 3, y, z - 3, sandstone, 1, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z);
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, sandstone, 1, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, sandstone, 2, 2);
        world.setBlock(x - 2, y, z - 1, sandstone, 2, 2);
        world.setBlock(x - 2, y, z, sandstone, 2, 2);
        world.setBlock(x - 2, y, z + 1, sandstone, 2, 2);
        world.setBlock(x - 2, y, z + 2, sandstone, 2, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, sandstone, 2, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, sandstone, 2, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlock(x, y, z - 2, sandstone, 2, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, sandstone, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, sandstone, 2, 2);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, sandstone, 2, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, sandstone, 2, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, sandstone, 2, 2);
        world.setBlock(x + 2, y, z - 1, sandstone, 2, 2);
        world.setBlock(x + 2, y, z, sandstone, 2, 2);
        world.setBlock(x + 2, y, z + 1, sandstone, 2, 2);
        world.setBlock(x + 2, y, z + 2, sandstone, 2, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, sandstone, 1, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, sandstone, 1, 2);

        // Fourth layer
        y++;

        // First row
        world.setBlock(x - 3, y, z - 3, sandstone, 1, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z);
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, sandstone, 1, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, sandstone, 0, 2);
        world.setBlock(x - 2, y, z - 1, sandstone, 0, 2);
        world.setBlock(x - 2, y, z, sandStair, 0, 2);
        world.setBlock(x - 2, y, z + 1, sandstone, 0, 2);
        world.setBlock(x - 2, y, z + 2, sandstone, 0, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, sandstone, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, sandstone, 0, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlock(x, y, z - 2, sandStair, 2, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, sandstone, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, sandStair, 3, 2);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, sandstone, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, sandstone, 0, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, sandstone, 0, 2);
        world.setBlock(x + 2, y, z - 1, sandstone, 0, 2);
        world.setBlock(x + 2, y, z, sandStair, 1, 2);
        world.setBlock(x + 2, y, z + 1, sandstone, 0, 2);
        world.setBlock(x + 2, y, z + 2, sandstone, 0, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, sandstone, 1, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, sandstone, 1, 2);

        // Fifth layer
        y++;

        // First row
        world.setBlock(x - 3, y, z - 3, sandstone, 1, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z);
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, sandstone, 1, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, sandstone, 0, 2);
        world.setBlock(x - 2, y, z - 1, sandstone, 0, 2);
        world.setBlockToAir(x - 2, y, z);
        world.setBlock(x - 2, y, z + 1, sandstone, 0, 2);
        world.setBlock(x - 2, y, z + 2, sandstone, 0, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, sandstone, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, sandstone, 0, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlockToAir(x, y, z - 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, sandstone, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlockToAir(x, y, z + 2);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, sandstone, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, sandstone, 0, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, sandstone, 0, 2);
        world.setBlock(x + 2, y, z - 1, sandstone, 0, 2);
        world.setBlockToAir(x + 2, y, z);
        world.setBlock(x + 2, y, z + 1, sandstone, 0, 2);
        world.setBlock(x + 2, y, z + 2, sandstone, 0, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, sandstone, 1, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, sandstone, 1, 2);

        // Sixth layer
        y++;

        // First row
        world.setBlock(x - 3, y, z - 3, sandstone, 1, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z);
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, sandstone, 1, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, sandstone, 2, 2);
        world.setBlock(x - 2, y, z - 1, sandstone, 2, 2);
        world.setBlock(x - 2, y, z, sandstone, 2, 2);
        world.setBlock(x - 2, y, z + 1, sandstone, 2, 2);
        world.setBlock(x - 2, y, z + 2, sandstone, 2, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, sandstone, 2, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, sandstone, 2, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlock(x, y, z - 2, sandstone, 2, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, sandstone, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, sandstone, 2, 2);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, sandstone, 2, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, sandstone, 2, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, sandstone, 2, 2);
        world.setBlock(x + 2, y, z - 1, sandstone, 2, 2);
        world.setBlock(x + 2, y, z, sandstone, 2, 2);
        world.setBlock(x + 2, y, z + 1, sandstone, 2, 2);
        world.setBlock(x + 2, y, z + 2, sandstone, 2, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, sandstone, 1, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, sandstone, 1, 2);

        // Seventh layer
        y++;

        // First row
        world.setBlock(x - 3, y, z - 3, sandstone, 1, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z);
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, sandstone, 1, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, sandstone, 1, 2);
        world.setBlock(x - 2, y, z - 1, sandstone, 0, 2);
        world.setBlock(x - 2, y, z, sandStair, 0, 2);
        world.setBlock(x - 2, y, z + 1, sandstone, 0, 2);
        world.setBlock(x - 2, y, z + 2, sandstone, 1, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, sandstone, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, sandstone, 0, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlock(x, y, z - 2, sandStair, 2, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, sandstone, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, sandStair, 3, 2);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, sandstone, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, sandstone, 0, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, sandstone, 1, 2);
        world.setBlock(x + 2, y, z - 1, sandstone, 0, 2);
        world.setBlock(x + 2, y, z, sandStair, 1, 2);
        world.setBlock(x + 2, y, z + 1, sandstone, 0, 2);
        world.setBlock(x + 2, y, z + 2, sandstone, 1, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, sandstone, 1, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, sandstone, 1, 2);

        // Eighth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, sandstone, 1, 2);
        world.setBlock(x - 2, y, z - 1, sandstone, 0, 2);
        world.setBlockToAir(x - 2, y, z);
        world.setBlock(x - 2, y, z + 1, sandstone, 0, 2);
        world.setBlock(x - 2, y, z + 2, sandstone, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, sandstone, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, sandstone, 0, 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, sandstone, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlockToAir(x, y, z + 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, sandstone, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, sandstone, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, sandstone, 1, 2);
        world.setBlock(x + 2, y, z - 1, sandstone, 0, 2);
        world.setBlockToAir(x + 2, y, z);
        world.setBlock(x + 2, y, z + 1, sandstone, 0, 2);
        world.setBlock(x + 2, y, z + 2, sandstone, 1, 2);

        // Ninth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, sandstone, 1, 2);
        world.setBlock(x - 2, y, z - 1, sandstone, 2, 2);
        world.setBlock(x - 2, y, z, sandstone, 2, 2);
        world.setBlock(x - 2, y, z + 1, sandstone, 2, 2);
        world.setBlock(x - 2, y, z + 2, sandstone, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, sandstone, 2, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, sandstone, 2, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, sandstone, 2, 2);
        world.setBlock(x, y, z - 1, sandstone, 2, 2);
        world.setBlock(x, y, z, sandstone, 1, 2);
        world.setBlock(x, y, z + 1, sandstone, 2, 2);
        world.setBlock(x, y, z + 2, sandstone, 2, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, sandstone, 2, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, sandstone, 2, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, sandstone, 1, 2);
        world.setBlock(x + 2, y, z - 1, sandstone, 2, 2);
        world.setBlock(x + 2, y, z, sandstone, 2, 2);
        world.setBlock(x + 2, y, z + 1, sandstone, 2, 2);
        world.setBlock(x + 2, y, z + 2, sandstone, 1, 2);

        // Tenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, sandstone, 1, 2);
        world.setBlock(x - 2, y, z - 1, sandstone, 0, 2);
        world.setBlock(x - 2, y, z, sandStair, 0, 2);
        world.setBlock(x - 2, y, z + 1, sandstone, 0, 2);
        world.setBlock(x - 2, y, z + 2, sandstone, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, sandstone, 0, 2);
        world.setBlockToAir(x - 1, y, z - 1);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, sandstone, 0, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, sandStair, 2, 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, sandstone, 1, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlock(x, y, z + 2, sandStair, 3, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, sandstone, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlockToAir(x + 1, y, z + 1);
        world.setBlock(x + 1, y, z + 2, sandstone, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, sandstone, 1, 2);
        world.setBlock(x + 2, y, z - 1, sandstone, 0, 2);
        world.setBlock(x + 2, y, z, sandStair, 1, 2);
        world.setBlock(x + 2, y, z + 1, sandstone, 0, 2);
        world.setBlock(x + 2, y, z + 2, sandstone, 1, 2);

        // Eleventh layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, sandstone, 1, 2);
        world.setBlock(x - 2, y, z - 1, sandstone, 0, 2);
        world.setBlockToAir(x - 2, y, z);
        world.setBlock(x - 2, y, z + 1, sandstone, 0, 2);
        world.setBlock(x - 2, y, z + 2, sandstone, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, sandstone, 0, 2);
        world.setBlockToAir(x - 1, y, z - 1);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, sandstone, 0, 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, sandstone, 1, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlockToAir(x, y, z + 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, sandstone, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlockToAir(x + 1, y, z + 1);
        world.setBlock(x + 1, y, z + 2, sandstone, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, sandstone, 1, 2);
        world.setBlock(x + 2, y, z - 1, sandstone, 0, 2);
        world.setBlockToAir(x + 2, y, z);
        world.setBlock(x + 2, y, z + 1, sandstone, 0, 2);
        world.setBlock(x + 2, y, z + 2, sandstone, 1, 2);

        // Twelfth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, sandstone, 1, 2);
        world.setBlock(x - 2, y, z - 1, sandstone, 2, 2);
        world.setBlock(x - 2, y, z, sandstone, 2, 2);
        world.setBlock(x - 2, y, z + 1, sandstone, 2, 2);
        world.setBlock(x - 2, y, z + 2, sandstone, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, sandstone, 2, 2);
        world.setBlock(x - 1, y, z - 1, sandstone, 2, 2);
        world.setBlock(x - 1, y, z, sandstone, 2, 2);
        world.setBlock(x - 1, y, z + 1, sandstone, 2, 2);
        world.setBlock(x - 1, y, z + 2, sandstone, 2, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, sandstone, 2, 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, sandstone, 1, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlock(x, y, z + 2, sandstone, 2, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, sandstone, 2, 2);
        world.setBlock(x + 1, y, z - 1, sandstone, 2, 2);
        world.setBlock(x + 1, y, z, sandstone, 2, 2);
        world.setBlock(x + 1, y, z + 1, sandstone, 2, 2);
        world.setBlock(x + 1, y, z + 2, sandstone, 2, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, sandstone, 1, 2);
        world.setBlock(x + 2, y, z - 1, sandstone, 2, 2);
        world.setBlock(x + 2, y, z, sandstone, 2, 2);
        world.setBlock(x + 2, y, z + 1, sandstone, 2, 2);
        world.setBlock(x + 2, y, z + 2, sandstone, 1, 2);

        // Thirteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, sandstone, 1, 2);
        world.setBlock(x - 2, y, z, sandStair, 0, 2);
        world.setBlock(x - 2, y, z + 1, sandstone, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, sandstone, 1, 2);
        world.setBlock(x - 1, y, z - 1, sandstone, 0, 2);
        world.setBlock(x - 1, y, z, tnt, 0, 2);
        world.setBlock(x - 1, y, z + 1, sandstone, 0, 2);
        world.setBlock(x - 1, y, z + 2, sandstone, 1, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, sandStair, 2, 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, sand, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlock(x, y, z + 2, sandStair, 3, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, sandstone, 1, 2);
        world.setBlock(x + 1, y, z - 1, sandstone, 0, 2);
        world.setBlock(x + 1, y, z, tnt, 0, 2);
        world.setBlock(x + 1, y, z + 1, sandstone, 0, 2);
        world.setBlock(x + 1, y, z + 2, sandstone, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, sandstone, 1, 2);
        world.setBlock(x + 2, y, z, sandStair, 1, 2);
        world.setBlock(x + 2, y, z + 1, sandstone, 1, 2);

        // Fourteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, sandstone, 1, 2);
        world.setBlock(x - 2, y, z, sandstone, 0, 2);
        world.setBlock(x - 2, y, z + 1, sandstone, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, sandstone, 1, 2);
        world.setBlock(x - 1, y, z - 1, sandstone, 0, 2);
        world.setBlock(x - 1, y, z, tnt, 0, 2);
        world.setBlock(x - 1, y, z + 1, sandstone, 0, 2);
        world.setBlock(x - 1, y, z + 2, sandstone, 1, 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, sand, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlockToAir(x, y, z + 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, sandstone, 1, 2);
        world.setBlock(x + 1, y, z - 1, sandstone, 0, 2);
        world.setBlock(x + 1, y, z, tnt, 0, 2);
        world.setBlock(x + 1, y, z + 1, sandstone, 0, 2);
        world.setBlock(x + 1, y, z + 2, sandstone, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, sandstone, 1, 2);
        world.setBlock(x + 2, y, z, sandstone, 0, 2);
        world.setBlock(x + 2, y, z + 1, sandstone, 1, 2);

        // Fifteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, sandstone, 1, 2);
        world.setBlock(x - 2, y, z, sandstone, 2, 2);
        world.setBlock(x - 2, y, z + 1, sandstone, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, sandstone, 1, 2);
        world.setBlock(x - 1, y, z - 1, sandstone, 2, 2);
        world.setBlock(x - 1, y, z, tnt, 0, 2);
        world.setBlock(x - 1, y, z + 1, sandstone, 2, 2);
        world.setBlock(x - 1, y, z + 2, sandstone, 1, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, sandstone, 2, 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, sand, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlock(x, y, z + 2, sandstone, 2, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, sandstone, 1, 2);
        world.setBlock(x + 1, y, z - 1, sandstone, 2, 2);
        world.setBlock(x + 1, y, z, tnt, 0, 2);
        world.setBlock(x + 1, y, z + 1, sandstone, 2, 2);
        world.setBlock(x + 1, y, z + 2, sandstone, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, sandstone, 1, 2);
        world.setBlock(x + 2, y, z, sandstone, 2, 2);
        world.setBlock(x + 2, y, z + 1, sandstone, 1, 2);

        // Sixteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, sandstone, 1, 2);
        world.setBlock(x - 2, y, z, sandstone, 0, 2);
        world.setBlock(x - 2, y, z + 1, sandstone, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, sandstone, 1, 2);
        world.setBlock(x - 1, y, z - 1, sandstone, 0, 2);
        world.setBlock(x - 1, y, z, sandstone, 0, 2);
        world.setBlock(x - 1, y, z + 1, sandstone, 0, 2);
        world.setBlock(x - 1, y, z + 2, sandstone, 1, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, sandstone, 0, 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, sand, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlock(x, y, z + 2, sandstone, 0, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, sandstone, 1, 2);
        world.setBlock(x + 1, y, z - 1, sandstone, 0, 2);
        world.setBlock(x + 1, y, z, sandstone, 0, 2);
        world.setBlock(x + 1, y, z + 1, sandstone, 0, 2);
        world.setBlock(x + 1, y, z + 2, sandstone, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, sandstone, 1, 2);
        world.setBlock(x + 2, y, z, sandstone, 0, 2);
        world.setBlock(x + 2, y, z + 1, sandstone, 1, 2);

        // Seventeenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, sandstone, 1, 2);
        world.setBlock(x - 2, y, z, sandstone, 0, 2);
        world.setBlock(x - 2, y, z + 1, sandstone, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, sandstone, 1, 2);
        world.setBlock(x - 1, y, z - 1, sandstone, 0, 2);
        world.setBlock(x - 1, y, z, redstone, 0, 2);
        world.setBlock(x - 1, y, z + 1, sandstone, 0, 2);
        world.setBlock(x - 1, y, z + 2, sandstone, 1, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, sandstone, 0, 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, sand, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlock(x, y, z + 2, sandstone, 0, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, sandstone, 1, 2);
        world.setBlock(x + 1, y, z - 1, sandstone, 0, 2);
        world.setBlock(x + 1, y, z, redstone, 0, 2);
        world.setBlock(x + 1, y, z + 1, sandstone, 0, 2);
        world.setBlock(x + 1, y, z + 2, sandstone, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, sandstone, 1, 2);
        world.setBlock(x + 2, y, z, sandstone, 0, 2);
        world.setBlock(x + 2, y, z + 1, sandstone, 1, 2);

        // Eighteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, sandstone, 1, 2);
        world.setBlock(x - 2, y, z, sandstone, 2, 2);
        world.setBlock(x - 2, y, z + 1, sandstone, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, sandstone, 1, 2);
        world.setBlock(x - 1, y, z - 1, plate, 0, 2);
        world.setBlock(x - 1, y, z, sandstone, 1, 2);
        world.setBlock(x - 1, y, z + 1, plate, 0, 2);
        world.setBlock(x - 1, y, z + 2, sandstone, 1, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, sandstone, 2, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, trapChest, 0, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, sandstone, 2, 2);

        this.applyChestContents(world, rand, x, y, z, TragicItems.NetherStructureHook);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, sandstone, 1, 2);
        world.setBlock(x + 1, y, z - 1, plate, 0, 2);
        world.setBlock(x + 1, y, z, sandstone, 1, 2);
        world.setBlock(x + 1, y, z + 1, plate, 0, 2);
        world.setBlock(x + 1, y, z + 2, sandstone, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, sandstone, 1, 2);
        world.setBlock(x + 2, y, z, sandstone, 2, 2);
        world.setBlock(x + 2, y, z + 1, sandstone, 1, 2);

        // Nineteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, sandstone, 1, 2);
        world.setBlock(x - 2, y, z, sandstone, 0, 2);
        world.setBlock(x - 2, y, z + 1, sandstone, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, sandstone, 1, 2);
        world.setBlockToAir(x - 1, y, z - 1);
        world.setBlock(x - 1, y, z, sandstone, 1, 2);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, sandstone, 1, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, sandStair, 2, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlockToAir(x, y, z);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, sandStair, 3, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, sandstone, 1, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlock(x + 1, y, z, sandstone, 1, 2);
        world.setBlockToAir(x + 1, y, z + 1);
        world.setBlock(x + 1, y, z + 2, sandstone, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, sandstone, 1, 2);
        world.setBlock(x + 2, y, z, sandstone, 0, 2);
        world.setBlock(x + 2, y, z + 1, sandstone, 1, 2);

        // Twentieth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, sandstone, 1, 2);
        world.setBlock(x - 2, y, z, sandstone, 0, 2);
        world.setBlock(x - 2, y, z + 1, sandstone, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, sandstone, 1, 2);
        world.setBlock(x - 1, y, z - 1, sandstone, 1, 2);
        world.setBlock(x - 1, y, z, sandstone, 1, 2);
        world.setBlock(x - 1, y, z + 1, sandstone, 1, 2);
        world.setBlock(x - 1, y, z + 2, sandstone, 1, 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlockToAir(x, y, z);
        world.setBlockToAir(x, y, z + 1);
        world.setBlockToAir(x, y, z + 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, sandstone, 1, 2);
        world.setBlock(x + 1, y, z - 1, sandstone, 1, 2);
        world.setBlock(x + 1, y, z, sandstone, 1, 2);
        world.setBlock(x + 1, y, z + 1, sandstone, 1, 2);
        world.setBlock(x + 1, y, z + 2, sandstone, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, sandstone, 1, 2);
        world.setBlock(x + 2, y, z, sandstone, 0, 2);
        world.setBlock(x + 2, y, z + 1, sandstone, 1, 2);

        // Twenty-first layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, sandStair, 2, 2);
        world.setBlock(x - 2, y, z, sandStair, 0, 2);
        world.setBlock(x - 2, y, z + 1, sandStair, 3, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, sandStair, 0, 2);
        world.setBlock(x - 1, y, z - 1, sandstone, 1, 2);
        world.setBlock(x - 1, y, z, sandstone, 1, 2);
        world.setBlock(x - 1, y, z + 1, sandstone, 1, 2);
        world.setBlock(x - 1, y, z + 2, sandStair, 0, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, sandStair, 2, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlockToAir(x, y, z);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, sandStair, 3, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, sandStair, 1, 2);
        world.setBlock(x + 1, y, z - 1, sandstone, 1, 2);
        world.setBlock(x + 1, y, z, sandstone, 1, 2);
        world.setBlock(x + 1, y, z + 1, sandstone, 1, 2);
        world.setBlock(x + 1, y, z + 2, sandStair, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, sandStair, 2, 2);
        world.setBlock(x + 2, y, z, sandStair, 1, 2);
        world.setBlock(x + 2, y, z + 1, sandStair, 3, 2);

        // Twenty-second layer
        y++;

        // Third row
        world.setBlock(x - 1, y, z - 1, sandStair, 0, 2);
        world.setBlock(x - 1, y, z, sandstone, 1, 2);
        world.setBlock(x - 1, y, z + 1, sandStair, 0, 2);

        // Fourth row
        world.setBlock(x, y, z - 1, sandStair, 2, 2);
        world.setBlockToAir(x, y, z);
        world.setBlock(x, y, z + 1, sandStair, 3, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 1, sandStair, 1, 2);
        world.setBlock(x + 1, y, z, sandstone, 1, 2);
        world.setBlock(x + 1, y, z + 1, sandStair, 1, 2);

        // Twenty-third layer
        y++;

        // Third row
        world.setBlock(x - 1, y, z, sandstone, 1, 2);

        // Fourth row
        world.setBlockToAir(x, y, z);

        // Fifth row
        world.setBlock(x + 1, y, z, sandstone, 1, 2);

        // Twenty-fourth layer
        y++;

        // Third row
        world.setBlock(x - 1, y, z, sandStair, 0, 2);

        // Fourth row
        world.setBlock(x, y, z, luxuryBlocks[rand.nextInt(luxuryBlocks.length)], 0, 2);

        // Fifth row
        world.setBlock(x + 1, y, z, sandStair, 1, 2);

        // Twenty-fifth layer
        y++;

        // Fourth row
        world.setBlock(x, y, z, slab, 1, 2);

    }

    public void generateVariant1(World world, Random rand, int x, int y, int z) {
        // First layer

        // Second row
        world.setBlock(x - 2, y, z - 2, hardClay, 0, 2);
        world.setBlock(x - 2, y, z - 1, hardClay, 0, 2);
        world.setBlockToAir(x - 2, y, z);
        world.setBlock(x - 2, y, z + 1, hardClay, 0, 2);
        world.setBlock(x - 2, y, z + 2, hardClay, 0, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, hardClay, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, hardClay, 0, 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, hardClay, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlockToAir(x, y, z + 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, hardClay, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, hardClay, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, hardClay, 0, 2);
        world.setBlock(x + 2, y, z - 1, hardClay, 0, 2);
        world.setBlockToAir(x + 2, y, z);
        world.setBlock(x + 2, y, z + 1, hardClay, 0, 2);
        world.setBlock(x + 2, y, z + 2, hardClay, 0, 2);

        // Second layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, hardClay, 0, 2);
        world.setBlock(x - 2, y, z - 1, hardClay, 0, 2);
        world.setBlockToAir(x - 2, y, z);
        world.setBlock(x - 2, y, z + 1, hardClay, 0, 2);
        world.setBlock(x - 2, y, z + 2, hardClay, 0, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, hardClay, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, hardClay, 0, 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, hardClay, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlockToAir(x, y, z + 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, hardClay, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, hardClay, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, hardClay, 0, 2);
        world.setBlock(x + 2, y, z - 1, hardClay, 0, 2);
        world.setBlockToAir(x + 2, y, z);
        world.setBlock(x + 2, y, z + 1, hardClay, 0, 2);
        world.setBlock(x + 2, y, z + 2, hardClay, 0, 2);

        // Third layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, hardClay, 2, 2);
        world.setBlock(x - 2, y, z - 1, hardClay, 2, 2);
        world.setBlock(x - 2, y, z, hardClay, 2, 2);
        world.setBlock(x - 2, y, z + 1, hardClay, 2, 2);
        world.setBlock(x - 2, y, z + 2, hardClay, 2, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, hardClay, 2, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, hardClay, 2, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, hardClay, 2, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, hardClay, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, hardClay, 2, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, hardClay, 2, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, hardClay, 2, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, hardClay, 2, 2);
        world.setBlock(x + 2, y, z - 1, hardClay, 2, 2);
        world.setBlock(x + 2, y, z, hardClay, 2, 2);
        world.setBlock(x + 2, y, z + 1, hardClay, 2, 2);
        world.setBlock(x + 2, y, z + 2, hardClay, 2, 2);

        // Fourth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, hardClay, 0, 2);
        world.setBlock(x - 2, y, z - 1, hardClay, 0, 2);
        world.setBlockToAir(x - 2, y, z);
        world.setBlock(x - 2, y, z + 1, hardClay, 0, 2);
        world.setBlock(x - 2, y, z + 2, hardClay, 0, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, hardClay, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, hardClay, 0, 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, hardClay, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlockToAir(x, y, z + 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, hardClay, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, hardClay, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, hardClay, 0, 2);
        world.setBlock(x + 2, y, z - 1, hardClay, 0, 2);
        world.setBlockToAir(x + 2, y, z);
        world.setBlock(x + 2, y, z + 1, hardClay, 0, 2);
        world.setBlock(x + 2, y, z + 2, hardClay, 0, 2);

        // Fifth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, hardClay, 0, 2);
        world.setBlock(x - 2, y, z - 1, hardClay, 0, 2);
        world.setBlockToAir(x - 2, y, z);
        world.setBlock(x - 2, y, z + 1, hardClay, 0, 2);
        world.setBlock(x - 2, y, z + 2, hardClay, 0, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, hardClay, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, hardClay, 0, 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, hardClay, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlockToAir(x, y, z + 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, hardClay, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, hardClay, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, hardClay, 0, 2);
        world.setBlock(x + 2, y, z - 1, hardClay, 0, 2);
        world.setBlockToAir(x + 2, y, z);
        world.setBlock(x + 2, y, z + 1, hardClay, 0, 2);
        world.setBlock(x + 2, y, z + 2, hardClay, 0, 2);

        // Sixth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, hardClay, 2, 2);
        world.setBlock(x - 2, y, z - 1, hardClay, 2, 2);
        world.setBlock(x - 2, y, z, hardClay, 2, 2);
        world.setBlock(x - 2, y, z + 1, hardClay, 2, 2);
        world.setBlock(x - 2, y, z + 2, hardClay, 2, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, hardClay, 2, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, hardClay, 2, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, hardClay, 2, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, hardClay, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, hardClay, 2, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, hardClay, 2, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, hardClay, 2, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, hardClay, 2, 2);
        world.setBlock(x + 2, y, z - 1, hardClay, 2, 2);
        world.setBlock(x + 2, y, z, hardClay, 2, 2);
        world.setBlock(x + 2, y, z + 1, hardClay, 2, 2);
        world.setBlock(x + 2, y, z + 2, hardClay, 2, 2);

        // Seventh layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, hardClay, 1, 2);
        world.setBlock(x - 2, y, z - 1, hardClay, 0, 2);
        world.setBlockToAir(x - 2, y, z);
        world.setBlock(x - 2, y, z + 1, hardClay, 0, 2);
        world.setBlock(x - 2, y, z + 2, hardClay, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, hardClay, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, hardClay, 0, 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, hardClay, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlockToAir(x, y, z + 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, hardClay, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, hardClay, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, hardClay, 1, 2);
        world.setBlock(x + 2, y, z - 1, hardClay, 0, 2);
        world.setBlockToAir(x + 2, y, z);
        world.setBlock(x + 2, y, z + 1, hardClay, 0, 2);
        world.setBlock(x + 2, y, z + 2, hardClay, 1, 2);

        // Eighth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, hardClay, 1, 2);
        world.setBlock(x - 2, y, z - 1, hardClay, 0, 2);
        world.setBlockToAir(x - 2, y, z);
        world.setBlock(x - 2, y, z + 1, hardClay, 0, 2);
        world.setBlock(x - 2, y, z + 2, hardClay, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, hardClay, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, hardClay, 0, 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, hardClay, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlockToAir(x, y, z + 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, hardClay, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, hardClay, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, hardClay, 1, 2);
        world.setBlock(x + 2, y, z - 1, hardClay, 0, 2);
        world.setBlockToAir(x + 2, y, z);
        world.setBlock(x + 2, y, z + 1, hardClay, 0, 2);
        world.setBlock(x + 2, y, z + 2, hardClay, 1, 2);

        // Ninth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, hardClay, 1, 2);
        world.setBlock(x - 2, y, z - 1, hardClay, 2, 2);
        world.setBlock(x - 2, y, z, hardClay, 2, 2);
        world.setBlock(x - 2, y, z + 1, hardClay, 2, 2);
        world.setBlock(x - 2, y, z + 2, hardClay, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, hardClay, 2, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, hardClay, 2, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, hardClay, 2, 2);
        world.setBlock(x, y, z - 1, hardClay, 2, 2);
        world.setBlock(x, y, z, hardClay, 1, 2);
        world.setBlock(x, y, z + 1, hardClay, 2, 2);
        world.setBlock(x, y, z + 2, hardClay, 2, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, hardClay, 2, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, hardClay, 2, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, hardClay, 1, 2);
        world.setBlock(x + 2, y, z - 1, hardClay, 2, 2);
        world.setBlock(x + 2, y, z, hardClay, 2, 2);
        world.setBlock(x + 2, y, z + 1, hardClay, 2, 2);
        world.setBlock(x + 2, y, z + 2, hardClay, 1, 2);

        // Tenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, hardClay, 1, 2);
        world.setBlock(x - 2, y, z - 1, hardClay, 0, 2);
        world.setBlockToAir(x - 2, y, z);
        world.setBlock(x - 2, y, z + 1, hardClay, 0, 2);
        world.setBlock(x - 2, y, z + 2, hardClay, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, hardClay, 0, 2);
        world.setBlockToAir(x - 1, y, z - 1);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, hardClay, 0, 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, hardClay, 1, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlockToAir(x, y, z + 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, hardClay, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlockToAir(x + 1, y, z + 1);
        world.setBlock(x + 1, y, z + 2, hardClay, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, hardClay, 1, 2);
        world.setBlock(x + 2, y, z - 1, hardClay, 0, 2);
        world.setBlockToAir(x + 2, y, z);
        world.setBlock(x + 2, y, z + 1, hardClay, 0, 2);
        world.setBlock(x + 2, y, z + 2, hardClay, 1, 2);

        // Eleventh layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, hardClay, 1, 2);
        world.setBlock(x - 2, y, z - 1, hardClay, 0, 2);
        world.setBlockToAir(x - 2, y, z);
        world.setBlock(x - 2, y, z + 1, hardClay, 0, 2);
        world.setBlock(x - 2, y, z + 2, hardClay, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, hardClay, 0, 2);
        world.setBlockToAir(x - 1, y, z - 1);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, hardClay, 0, 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, hardClay, 1, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlockToAir(x, y, z + 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, hardClay, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlockToAir(x + 1, y, z + 1);
        world.setBlock(x + 1, y, z + 2, hardClay, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, hardClay, 1, 2);
        world.setBlock(x + 2, y, z - 1, hardClay, 0, 2);
        world.setBlockToAir(x + 2, y, z);
        world.setBlock(x + 2, y, z + 1, hardClay, 0, 2);
        world.setBlock(x + 2, y, z + 2, hardClay, 1, 2);

        // Twelfth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, hardClay, 1, 2);
        world.setBlock(x - 2, y, z - 1, hardClay, 2, 2);
        world.setBlock(x - 2, y, z, hardClay, 2, 2);
        world.setBlock(x - 2, y, z + 1, hardClay, 2, 2);
        world.setBlock(x - 2, y, z + 2, hardClay, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, hardClay, 2, 2);
        world.setBlock(x - 1, y, z - 1, hardClay, 2, 2);
        world.setBlock(x - 1, y, z, hardClay, 2, 2);
        world.setBlock(x - 1, y, z + 1, hardClay, 2, 2);
        world.setBlock(x - 1, y, z + 2, hardClay, 2, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, hardClay, 2, 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, hardClay, 1, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlock(x, y, z + 2, hardClay, 2, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, hardClay, 2, 2);
        world.setBlock(x + 1, y, z - 1, hardClay, 2, 2);
        world.setBlock(x + 1, y, z, hardClay, 2, 2);
        world.setBlock(x + 1, y, z + 1, hardClay, 2, 2);
        world.setBlock(x + 1, y, z + 2, hardClay, 2, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, hardClay, 1, 2);
        world.setBlock(x + 2, y, z - 1, hardClay, 2, 2);
        world.setBlock(x + 2, y, z, hardClay, 2, 2);
        world.setBlock(x + 2, y, z + 1, hardClay, 2, 2);
        world.setBlock(x + 2, y, z + 2, hardClay, 1, 2);

        // Thirteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, hardClay, 1, 2);
        world.setBlock(x - 2, y, z, hardClay, 0, 2);
        world.setBlock(x - 2, y, z + 1, hardClay, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, hardClay, 1, 2);
        world.setBlock(x - 1, y, z - 1, hardClay, 0, 2);
        world.setBlock(x - 1, y, z, tnt, 0, 2);
        world.setBlock(x - 1, y, z + 1, hardClay, 0, 2);
        world.setBlock(x - 1, y, z + 2, hardClay, 1, 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, hardClay, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlockToAir(x, y, z + 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, hardClay, 1, 2);
        world.setBlock(x + 1, y, z - 1, hardClay, 0, 2);
        world.setBlock(x + 1, y, z, tnt, 0, 2);
        world.setBlock(x + 1, y, z + 1, hardClay, 0, 2);
        world.setBlock(x + 1, y, z + 2, hardClay, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, hardClay, 1, 2);
        world.setBlock(x + 2, y, z, hardClay, 0, 2);
        world.setBlock(x + 2, y, z + 1, hardClay, 1, 2);

        // Fourteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, hardClay, 1, 2);
        world.setBlock(x - 2, y, z, hardClay, 0, 2);
        world.setBlock(x - 2, y, z + 1, hardClay, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, hardClay, 1, 2);
        world.setBlock(x - 1, y, z - 1, hardClay, 0, 2);
        world.setBlock(x - 1, y, z, tnt, 0, 2);
        world.setBlock(x - 1, y, z + 1, hardClay, 0, 2);
        world.setBlock(x - 1, y, z + 2, hardClay, 1, 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, hardClay, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlockToAir(x, y, z + 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, hardClay, 1, 2);
        world.setBlock(x + 1, y, z - 1, hardClay, 0, 2);
        world.setBlock(x + 1, y, z, tnt, 0, 2);
        world.setBlock(x + 1, y, z + 1, hardClay, 0, 2);
        world.setBlock(x + 1, y, z + 2, hardClay, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, hardClay, 1, 2);
        world.setBlock(x + 2, y, z, hardClay, 0, 2);
        world.setBlock(x + 2, y, z + 1, hardClay, 1, 2);

        // Fifteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, hardClay, 1, 2);
        world.setBlock(x - 2, y, z, hardClay, 2, 2);
        world.setBlock(x - 2, y, z + 1, hardClay, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, hardClay, 1, 2);
        world.setBlock(x - 1, y, z - 1, hardClay, 2, 2);
        world.setBlock(x - 1, y, z, tnt, 0, 2);
        world.setBlock(x - 1, y, z + 1, hardClay, 2, 2);
        world.setBlock(x - 1, y, z + 2, hardClay, 1, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, hardClay, 2, 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, hardClay, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlock(x, y, z + 2, hardClay, 2, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, hardClay, 1, 2);
        world.setBlock(x + 1, y, z - 1, hardClay, 2, 2);
        world.setBlock(x + 1, y, z, tnt, 0, 2);
        world.setBlock(x + 1, y, z + 1, hardClay, 2, 2);
        world.setBlock(x + 1, y, z + 2, hardClay, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, hardClay, 1, 2);
        world.setBlock(x + 2, y, z, hardClay, 2, 2);
        world.setBlock(x + 2, y, z + 1, hardClay, 1, 2);

        // Sixteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, hardClay, 1, 2);
        world.setBlock(x - 2, y, z, hardClay, 0, 2);
        world.setBlock(x - 2, y, z + 1, hardClay, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, hardClay, 1, 2);
        world.setBlock(x - 1, y, z - 1, hardClay, 0, 2);
        world.setBlock(x - 1, y, z, tnt, 0, 2);
        world.setBlock(x - 1, y, z + 1, hardClay, 0, 2);
        world.setBlock(x - 1, y, z + 2, hardClay, 1, 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, hardClay, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlockToAir(x, y, z + 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, hardClay, 1, 2);
        world.setBlock(x + 1, y, z - 1, hardClay, 0, 2);
        world.setBlock(x + 1, y, z, tnt, 0, 2);
        world.setBlock(x + 1, y, z + 1, hardClay, 0, 2);
        world.setBlock(x + 1, y, z + 2, hardClay, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, hardClay, 1, 2);
        world.setBlock(x + 2, y, z, hardClay, 0, 2);
        world.setBlock(x + 2, y, z + 1, hardClay, 1, 2);

        // Seventeenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, hardClay, 1, 2);
        world.setBlock(x - 2, y, z, hardClay, 0, 2);
        world.setBlock(x - 2, y, z + 1, hardClay, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, hardClay, 1, 2);
        world.setBlock(x - 1, y, z - 1, hardClay, 0, 2);
        world.setBlock(x - 1, y, z, tnt, 0, 2);
        world.setBlock(x - 1, y, z + 1, hardClay, 0, 2);
        world.setBlock(x - 1, y, z + 2, hardClay, 1, 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, hardClay, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlockToAir(x, y, z + 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, hardClay, 1, 2);
        world.setBlock(x + 1, y, z - 1, hardClay, 0, 2);
        world.setBlock(x + 1, y, z, tnt, 0, 2);
        world.setBlock(x + 1, y, z + 1, hardClay, 0, 2);
        world.setBlock(x + 1, y, z + 2, hardClay, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, hardClay, 1, 2);
        world.setBlock(x + 2, y, z, hardClay, 0, 2);
        world.setBlock(x + 2, y, z + 1, hardClay, 1, 2);

        // Eighteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, hardClay, 1, 2);
        world.setBlock(x - 2, y, z, hardClay, 2, 2);
        world.setBlock(x - 2, y, z + 1, hardClay, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, hardClay, 1, 2);
        world.setBlock(x - 1, y, z - 1, hardClay, 1, 2);
        world.setBlock(x - 1, y, z, hardClay, 1, 2);
        world.setBlock(x - 1, y, z + 1, hardClay, 1, 2);
        world.setBlock(x - 1, y, z + 2, hardClay, 1, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, hardClay, 2, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, trapChest, 0, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, hardClay, 2, 2);

        this.applyChestContents(world, rand, x, y, z, TragicItems.NetherStructureHook);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, hardClay, 1, 2);
        world.setBlock(x + 1, y, z - 1, hardClay, 0, 2);
        world.setBlock(x + 1, y, z, hardClay, 1, 2);
        world.setBlock(x + 1, y, z + 1, hardClay, 0, 2);
        world.setBlock(x + 1, y, z + 2, hardClay, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, hardClay, 1, 2);
        world.setBlock(x + 2, y, z, hardClay, 2, 2);
        world.setBlock(x + 2, y, z + 1, hardClay, 1, 2);

        // Nineteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, hardClay, 1, 2);
        world.setBlock(x - 2, y, z, hardClay, 0, 2);
        world.setBlock(x - 2, y, z + 1, hardClay, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, hardClay, 1, 2);
        world.setBlock(x - 1, y, z - 1, hardClay, 0, 2);
        world.setBlock(x - 1, y, z, hardClay, 1, 2);
        world.setBlock(x - 1, y, z + 1, hardClay, 0, 2);
        world.setBlock(x - 1, y, z + 2, hardClay, 1, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, hardClay, 0, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlockToAir(x, y, z);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, hardClay, 0, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, hardClay, 1, 2);
        world.setBlock(x + 1, y, z - 1, hardClay, 0, 2);
        world.setBlock(x + 1, y, z, hardClay, 1, 2);
        world.setBlock(x + 1, y, z + 1, hardClay, 0, 2);
        world.setBlock(x + 1, y, z + 2, hardClay, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, hardClay, 1, 2);
        world.setBlock(x + 2, y, z, hardClay, 0, 2);
        world.setBlock(x + 2, y, z + 1, hardClay, 1, 2);

        // Twentieth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, hardClay, 1, 2);
        world.setBlock(x - 2, y, z, hardClay, 0, 2);
        world.setBlock(x - 2, y, z + 1, hardClay, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, hardClay, 1, 2);
        world.setBlock(x - 1, y, z - 1, hardClay, 1, 2);
        world.setBlock(x - 1, y, z, hardClay, 1, 2);
        world.setBlock(x - 1, y, z + 1, hardClay, 1, 2);
        world.setBlock(x - 1, y, z + 2, hardClay, 1, 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlockToAir(x, y, z);
        world.setBlockToAir(x, y, z + 1);
        world.setBlockToAir(x, y, z + 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, hardClay, 1, 2);
        world.setBlock(x + 1, y, z - 1, hardClay, 1, 2);
        world.setBlock(x + 1, y, z, hardClay, 1, 2);
        world.setBlock(x + 1, y, z + 1, hardClay, 1, 2);
        world.setBlock(x + 1, y, z + 2, hardClay, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, hardClay, 1, 2);
        world.setBlock(x + 2, y, z, hardClay, 0, 2);
        world.setBlock(x + 2, y, z + 1, hardClay, 1, 2);

        // Twenty-first layer
        y++;

        // Second row
        world.setBlockToAir(x - 2, y, z - 1);
        world.setBlockToAir(x - 2, y, z);
        world.setBlockToAir(x - 2, y, z + 1);

        // Third row
        world.setBlockToAir(x - 1, y, z - 2);
        world.setBlock(x - 1, y, z - 1, hardClay, 1, 2);
        world.setBlock(x - 1, y, z, hardClay, 1, 2);
        world.setBlock(x - 1, y, z + 1, hardClay, 1, 2);
        world.setBlockToAir(x - 1, y, z + 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlockToAir(x, y, z);
        world.setBlockToAir(x, y, z + 1);
        world.setBlockToAir(x, y, z + 2);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 2);
        world.setBlock(x + 1, y, z - 1, hardClay, 1, 2);
        world.setBlock(x + 1, y, z, hardClay, 1, 2);
        world.setBlock(x + 1, y, z + 1, hardClay, 1, 2);
        world.setBlockToAir(x + 1, y, z + 2);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 1);
        world.setBlockToAir(x + 2, y, z);
        world.setBlockToAir(x + 2, y, z + 1);

        // Twenty-second layer
        y++;

        // Third row
        world.setBlockToAir(x - 1, y, z - 1);
        world.setBlock(x - 1, y, z, hardClay, 1, 2);
        world.setBlockToAir(x - 1, y, z + 1);

        // Fourth row
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, luxuryBlocks[rand.nextInt(luxuryBlocks.length)], 0, 2);
        world.setBlockToAir(x, y, z + 1);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlock(x + 1, y, z, hardClay, 1, 2);
        world.setBlockToAir(x + 1, y, z + 1);

        // Twentiy-third layer
        y++;

        // Fourth row
        world.setBlock(x, y, z, hardClay, 0, 2);
    }

    public void generateVariant2(World world, Random rand, int x, int y, int z) {
        // First layer

        // First row
        world.setBlock(x - 3, y, z - 3, brick, 0, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z); // Starting block of the whole schematic
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, brick, 0, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, cobble, 0, 2);
        world.setBlock(x - 2, y, z - 1, mossCob, 0, 2);
        world.setBlockToAir(x - 2, y, z);
        world.setBlock(x - 2, y, z + 1, mossCob, 0, 2);
        world.setBlock(x - 2, y, z + 2, cobble, 0, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, cobble, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, cobble, 0, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlockToAir(x, y, z - 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, brick, 0, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlockToAir(x, y, z + 2);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, mossCob, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, cobble, 0, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, mossCob, 0, 2);
        world.setBlock(x + 2, y, z - 1, cobble, 0, 2);
        world.setBlockToAir(x + 2, y, z);
        world.setBlock(x + 2, y, z + 1, mossCob, 0, 2);
        world.setBlock(x + 2, y, z + 2, mossCob, 0, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, brick, 0, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, brick, 0, 2);

        // Second layer
        y++;

        // First row
        world.setBlock(x - 3, y, z - 3, brick, 0, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z);
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, brick, 0, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, cobble, 0, 2);
        world.setBlock(x - 2, y, z - 1, cobble, 0, 2);
        world.setBlockToAir(x - 2, y, z);
        world.setBlock(x - 2, y, z + 1, mossCob, 0, 2);
        world.setBlock(x - 2, y, z + 2, cobble, 0, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, mossCob, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, cobble, 0, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlockToAir(x, y, z - 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, brick, 0, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlockToAir(x, y, z + 2);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, cobble, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, cobble, 0, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, mossCob, 0, 2);
        world.setBlock(x + 2, y, z - 1, cobble, 0, 2);
        world.setBlockToAir(x + 2, y, z);
        world.setBlock(x + 2, y, z + 1, cobble, 0, 2);
        world.setBlock(x + 2, y, z + 2, cobble, 0, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, brick, 0, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, brick, 0, 2);

        // Third layer
        y++;

        // First row
        world.setBlock(x - 3, y, z - 3, brick, 0, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z);
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, brick, 0, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, brick, 1, 2);
        world.setBlock(x - 2, y, z - 1, brick, 1, 2);
        world.setBlock(x - 2, y, z, brick, 1, 2);
        world.setBlock(x - 2, y, z + 1, brick, 1, 2);
        world.setBlock(x - 2, y, z + 2, brick, 1, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, brick, 1, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, brick, 1, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlock(x, y, z - 2, brick, 1, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, brick, 0, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, brick, 1, 2);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, brick, 1, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, brick, 1, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, brick, 1, 2);
        world.setBlock(x + 2, y, z - 1, brick, 1, 2);
        world.setBlock(x + 2, y, z, brick, 1, 2);
        world.setBlock(x + 2, y, z + 1, brick, 1, 2);
        world.setBlock(x + 2, y, z + 2, brick, 1, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, brick, 0, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, brick, 0, 2);

        // Fourth layer
        y++;

        // First row
        world.setBlock(x - 3, y, z - 3, brick, 0, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z);
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, brick, 0, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, mossCob, 0, 2);
        world.setBlock(x - 2, y, z - 1, cobble, 0, 2);
        world.setBlock(x - 2, y, z, brickStair, 0, 2);
        world.setBlock(x - 2, y, z + 1, cobble, 0, 2);
        world.setBlock(x - 2, y, z + 2, cobble, 0, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, mossCob, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, mossCob, 0, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlock(x, y, z - 2, brickStair, 2, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, brick, 0, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, brickStair, 3, 2);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, cobble, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, mossCob, 0, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, mossCob, 0, 2);
        world.setBlock(x + 2, y, z - 1, cobble, 0, 2);
        world.setBlock(x + 2, y, z, brickStair, 1, 2);
        world.setBlock(x + 2, y, z + 1, cobble, 0, 2);
        world.setBlock(x + 2, y, z + 2, cobble, 0, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, brick, 0, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, brick, 0, 2);

        // Fifth layer
        y++;

        // First row
        world.setBlock(x - 3, y, z - 3, brick, 0, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z);
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, brick, 0, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, mossCob, 0, 2);
        world.setBlock(x - 2, y, z - 1, cobble, 0, 2);
        world.setBlockToAir(x - 2, y, z);
        world.setBlock(x - 2, y, z + 1, cobble, 0, 2);
        world.setBlock(x - 2, y, z + 2, cobble, 0, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, cobble, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, mossCob, 0, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlockToAir(x, y, z - 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, brick, 0, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlockToAir(x, y, z + 2);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, cobble, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, cobble, 0, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, cobble, 0, 2);
        world.setBlock(x + 2, y, z - 1, cobble, 0, 2);
        world.setBlockToAir(x + 2, y, z);
        world.setBlock(x + 2, y, z + 1, mossCob, 0, 2);
        world.setBlock(x + 2, y, z + 2, cobble, 0, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, brick, 0, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, brick, 0, 2);

        // Sixth layer
        y++;

        // First row
        world.setBlock(x - 3, y, z - 3, brick, 0, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z);
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, brick, 0, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, brick, 1, 2);
        world.setBlock(x - 2, y, z - 1, brick, 1, 2);
        world.setBlock(x - 2, y, z, brick, 1, 2);
        world.setBlock(x - 2, y, z + 1, brick, 1, 2);
        world.setBlock(x - 2, y, z + 2, brick, 1, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, brick, 1, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, brick, 1, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlock(x, y, z - 2, brick, 1, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, brick, 0, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, brick, 1, 2);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, brick, 1, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, brick, 1, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, brick, 1, 2);
        world.setBlock(x + 2, y, z - 1, brick, 1, 2);
        world.setBlock(x + 2, y, z, brick, 1, 2);
        world.setBlock(x + 2, y, z + 1, brick, 1, 2);
        world.setBlock(x + 2, y, z + 2, brick, 1, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, brick, 0, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, brick, 0, 2);

        // Seventh layer
        y++;

        // First row
        world.setBlock(x - 3, y, z - 3, brick, 0, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z);
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, brick, 0, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, brick, 0, 2);
        world.setBlock(x - 2, y, z - 1, cobble, 0, 2);
        world.setBlock(x - 2, y, z, brickStair, 0, 2);
        world.setBlock(x - 2, y, z + 1, cobble, 0, 2);
        world.setBlock(x - 2, y, z + 2, brick, 0, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, cobble, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, cobble, 0, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlock(x, y, z - 2, brickStair, 2, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, brick, 0, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, brickStair, 3, 2);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, cobble, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, cobble, 0, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, brick, 0, 2);
        world.setBlock(x + 2, y, z - 1, mossCob, 0, 2);
        world.setBlock(x + 2, y, z, brickStair, 1, 2);
        world.setBlock(x + 2, y, z + 1, cobble, 0, 2);
        world.setBlock(x + 2, y, z + 2, brick, 0, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, brick, 0, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, brick, 0, 2);

        // Eighth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, brick, 0, 2);
        world.setBlock(x - 2, y, z - 1, mossCob, 0, 2);
        world.setBlockToAir(x - 2, y, z);
        world.setBlock(x - 2, y, z + 1, cobble, 0, 2);
        world.setBlock(x - 2, y, z + 2, brick, 0, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, cobble, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, cobble, 0, 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, brick, 0, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlockToAir(x, y, z + 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, cobble, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, cobble, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, brick, 0, 2);
        world.setBlock(x + 2, y, z - 1, mossCob, 0, 2);
        world.setBlockToAir(x + 2, y, z);
        world.setBlock(x + 2, y, z + 1, cobble, 0, 2);
        world.setBlock(x + 2, y, z + 2, brick, 0, 2);

        // Ninth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, brick, 0, 2);
        world.setBlock(x - 2, y, z - 1, brick, 1, 2);
        world.setBlock(x - 2, y, z, brick, 1, 2);
        world.setBlock(x - 2, y, z + 1, brick, 1, 2);
        world.setBlock(x - 2, y, z + 2, brick, 0, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, brick, 1, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, brick, 1, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, brick, 1, 2);
        world.setBlock(x, y, z - 1, brick, 1, 2);
        world.setBlock(x, y, z, brick, 0, 2);
        world.setBlock(x, y, z + 1, brick, 1, 2);
        world.setBlock(x, y, z + 2, brick, 1, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, brick, 1, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, brick, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, brick, 0, 2);
        world.setBlock(x + 2, y, z - 1, brick, 1, 2);
        world.setBlock(x + 2, y, z, brick, 1, 2);
        world.setBlock(x + 2, y, z + 1, brick, 1, 2);
        world.setBlock(x + 2, y, z + 2, brick, 0, 2);

        // Tenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, brick, 0, 2);
        world.setBlock(x - 2, y, z - 1, cobble, 0, 2);
        world.setBlock(x - 2, y, z, brickStair, 0, 2);
        world.setBlock(x - 2, y, z + 1, cobble, 0, 2);
        world.setBlock(x - 2, y, z + 2, brick, 0, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, mossCob, 0, 2);
        world.setBlockToAir(x - 1, y, z - 1);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, cobble, 0, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, brickStair, 2, 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, brick, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlock(x, y, z + 2, brickStair, 3, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, mossCob, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlockToAir(x + 1, y, z + 1);
        world.setBlock(x + 1, y, z + 2, cobble, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, brick, 0, 2);
        world.setBlock(x + 2, y, z - 1, cobble, 0, 2);
        world.setBlock(x + 2, y, z, brickStair, 1, 2);
        world.setBlock(x + 2, y, z + 1, cobble, 0, 2);
        world.setBlock(x + 2, y, z + 2, brick, 0, 2);

        // Eleventh layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, brick, 0, 2);
        world.setBlock(x - 2, y, z - 1, mossCob, 0, 2);
        world.setBlockToAir(x - 2, y, z);
        world.setBlock(x - 2, y, z + 1, cobble, 0, 2);
        world.setBlock(x - 2, y, z + 2, brick, 0, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, cobble, 0, 2);
        world.setBlockToAir(x - 1, y, z - 1);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, cobble, 0, 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, brick, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlockToAir(x, y, z + 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, mossCob, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlockToAir(x + 1, y, z + 1);
        world.setBlock(x + 1, y, z + 2, cobble, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, brick, 0, 2);
        world.setBlock(x + 2, y, z - 1, cobble, 0, 2);
        world.setBlockToAir(x + 2, y, z);
        world.setBlock(x + 2, y, z + 1, cobble, 0, 2);
        world.setBlock(x + 2, y, z + 2, brick, 0, 2);

        // Twelfth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, brick, 0, 2);
        world.setBlock(x - 2, y, z - 1, brick, 1, 2);
        world.setBlock(x - 2, y, z, brick, 1, 2);
        world.setBlock(x - 2, y, z + 1, brick, 1, 2);
        world.setBlock(x - 2, y, z + 2, brick, 0, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, brick, 1, 2);
        world.setBlock(x - 1, y, z - 1, brick, 1, 2);
        world.setBlock(x - 1, y, z, brick, 1, 2);
        world.setBlock(x - 1, y, z + 1, brick, 1, 2);
        world.setBlock(x - 1, y, z + 2, brick, 1, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, brick, 1, 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, brick, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlock(x, y, z + 2, brick, 1, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, brick, 1, 2);
        world.setBlock(x + 1, y, z - 1, brick, 1, 2);
        world.setBlock(x + 1, y, z, brick, 1, 2);
        world.setBlock(x + 1, y, z + 1, brick, 1, 2);
        world.setBlock(x + 1, y, z + 2, brick, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, brick, 0, 2);
        world.setBlock(x + 2, y, z - 1, brick, 1, 2);
        world.setBlock(x + 2, y, z, brick, 1, 2);
        world.setBlock(x + 2, y, z + 1, brick, 1, 2);
        world.setBlock(x + 2, y, z + 2, brick, 0, 2);

        // Thirteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, brick, 0, 2);
        world.setBlock(x - 2, y, z, brickStair, 0, 2);
        world.setBlock(x - 2, y, z + 1, brick, 0, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, brick, 0, 2);
        world.setBlock(x - 1, y, z - 1, cobble, 0, 2);
        world.setBlock(x - 1, y, z, tnt, 0, 2);
        world.setBlock(x - 1, y, z + 1, mossCob, 0, 2);
        world.setBlock(x - 1, y, z + 2, brick, 0, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, brickStair, 2, 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, sand, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlock(x, y, z + 2, brickStair, 3, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, brick, 0, 2);
        world.setBlock(x + 1, y, z - 1, cobble, 0, 2);
        world.setBlock(x + 1, y, z, tnt, 0, 2);
        world.setBlock(x + 1, y, z + 1, cobble, 0, 2);
        world.setBlock(x + 1, y, z + 2, brick, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, brick, 0, 2);
        world.setBlock(x + 2, y, z, brickStair, 1, 2);
        world.setBlock(x + 2, y, z + 1, brick, 0, 2);

        // Fourteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, brick, 0, 2);
        world.setBlock(x - 2, y, z, cobble, 0, 2);
        world.setBlock(x - 2, y, z + 1, brick, 0, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, brick, 0, 2);
        world.setBlock(x - 1, y, z - 1, mossCob, 0, 2);
        world.setBlock(x - 1, y, z, tnt, 0, 2);
        world.setBlock(x - 1, y, z + 1, cobble, 0, 2);
        world.setBlock(x - 1, y, z + 2, brick, 0, 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, sand, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlockToAir(x, y, z + 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, brick, 0, 2);
        world.setBlock(x + 1, y, z - 1, mossCob, 0, 2);
        world.setBlock(x + 1, y, z, tnt, 0, 2);
        world.setBlock(x + 1, y, z + 1, mossCob, 0, 2);
        world.setBlock(x + 1, y, z + 2, brick, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, brick, 0, 2);
        world.setBlock(x + 2, y, z, mossCob, 0, 2);
        world.setBlock(x + 2, y, z + 1, brick, 0, 2);

        // Fifteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, brick, 0, 2);
        world.setBlock(x - 2, y, z, brick, 1, 2);
        world.setBlock(x - 2, y, z + 1, brick, 0, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, brick, 0, 2);
        world.setBlock(x - 1, y, z - 1, brick, 1, 2);
        world.setBlock(x - 1, y, z, tnt, 0, 2);
        world.setBlock(x - 1, y, z + 1, brick, 1, 2);
        world.setBlock(x - 1, y, z + 2, brick, 0, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, brick, 1, 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, sand, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlock(x, y, z + 2, brick, 1, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, brick, 0, 2);
        world.setBlock(x + 1, y, z - 1, brick, 1, 2);
        world.setBlock(x + 1, y, z, tnt, 0, 2);
        world.setBlock(x + 1, y, z + 1, brick, 1, 2);
        world.setBlock(x + 1, y, z + 2, brick, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, brick, 0, 2);
        world.setBlock(x + 2, y, z, brick, 1, 2);
        world.setBlock(x + 2, y, z + 1, brick, 0, 2);

        // Sixteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, brick, 0, 2);
        world.setBlock(x - 2, y, z, cobble, 0, 2);
        world.setBlock(x - 2, y, z + 1, brick, 0, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, brick, 0, 2);
        world.setBlock(x - 1, y, z - 1, cobble, 0, 2);
        world.setBlock(x - 1, y, z, cobble, 0, 2);
        world.setBlock(x - 1, y, z + 1, cobble, 0, 2);
        world.setBlock(x - 1, y, z + 2, brick, 0, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, mossCob, 0, 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, sand, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlock(x, y, z + 2, mossCob, 0, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, brick, 0, 2);
        world.setBlock(x + 1, y, z - 1, cobble, 0, 2);
        world.setBlock(x + 1, y, z, cobble, 0, 2);
        world.setBlock(x + 1, y, z + 1, mossCob, 0, 2);
        world.setBlock(x + 1, y, z + 2, brick, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, brick, 0, 2);
        world.setBlock(x + 2, y, z, cobble, 0, 2);
        world.setBlock(x + 2, y, z + 1, brick, 0, 2);

        // Seventeenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, brick, 0, 2);
        world.setBlock(x - 2, y, z, cobble, 0, 2);
        world.setBlock(x - 2, y, z + 1, brick, 0, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, brick, 0, 2);
        world.setBlock(x - 1, y, z - 1, cobble, 0, 2);
        world.setBlock(x - 1, y, z, redstone, 0, 2);
        world.setBlock(x - 1, y, z + 1, cobble, 0, 2);
        world.setBlock(x - 1, y, z + 2, brick, 0, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, cobble, 0, 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, sand, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlock(x, y, z + 2, cobble, 0, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, brick, 0, 2);
        world.setBlock(x + 1, y, z - 1, cobble, 0, 2);
        world.setBlock(x + 1, y, z, redstone, 0, 2);
        world.setBlock(x + 1, y, z + 1, cobble, 0, 2);
        world.setBlock(x + 1, y, z + 2, brick, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, brick, 0, 2);
        world.setBlock(x + 2, y, z, cobble, 0, 2);
        world.setBlock(x + 2, y, z + 1, brick, 0, 2);

        // Eighteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, brick, 0, 2);
        world.setBlock(x - 2, y, z, brick, 1, 2);
        world.setBlock(x - 2, y, z + 1, brick, 0, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, brick, 0, 2);
        world.setBlock(x - 1, y, z - 1, plateStone, 0, 2);
        world.setBlock(x - 1, y, z, brick, 0, 2);
        world.setBlock(x - 1, y, z + 1, plateStone, 0, 2);
        world.setBlock(x - 1, y, z + 2, brick, 0, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, brick, 1, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, trapChest, 0, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, brick, 1, 2);

        this.applyChestContents(world, rand, x, y, z, TragicItems.NetherStructureHook);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, brick, 0, 2);
        world.setBlock(x + 1, y, z - 1, plateStone, 0, 2);
        world.setBlock(x + 1, y, z, brick, 0, 2);
        world.setBlock(x + 1, y, z + 1, plateStone, 0, 2);
        world.setBlock(x + 1, y, z + 2, brick, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, brick, 0, 2);
        world.setBlock(x + 2, y, z, brick, 1, 2);
        world.setBlock(x + 2, y, z + 1, brick, 0, 2);

        // Nineteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, brick, 0, 2);
        world.setBlock(x - 2, y, z, mossCob, 0, 2);
        world.setBlock(x - 2, y, z + 1, brick, 0, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, brick, 0, 2);
        world.setBlockToAir(x - 1, y, z - 1);
        world.setBlock(x - 1, y, z, brick, 0, 2);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, brick, 0, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, brickStair, 2, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlockToAir(x, y, z);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, brickStair, 3, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, brick, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlock(x + 1, y, z, brick, 0, 2);
        world.setBlockToAir(x + 1, y, z + 1);
        world.setBlock(x + 1, y, z + 2, brick, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, brick, 0, 2);
        world.setBlock(x + 2, y, z, cobble, 0, 2);
        world.setBlock(x + 2, y, z + 1, brick, 0, 2);

        // Twentieth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, brick, 0, 2);
        world.setBlock(x - 2, y, z, cobble, 0, 2);
        world.setBlock(x - 2, y, z + 1, brick, 0, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, brick, 0, 2);
        world.setBlock(x - 1, y, z - 1, brick, 0, 2);
        world.setBlock(x - 1, y, z, brick, 0, 2);
        world.setBlock(x - 1, y, z + 1, brick, 0, 2);
        world.setBlock(x - 1, y, z + 2, brick, 0, 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlockToAir(x, y, z);
        world.setBlockToAir(x, y, z + 1);
        world.setBlockToAir(x, y, z + 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, brick, 0, 2);
        world.setBlock(x + 1, y, z - 1, brick, 0, 2);
        world.setBlock(x + 1, y, z, brick, 0, 2);
        world.setBlock(x + 1, y, z + 1, brick, 0, 2);
        world.setBlock(x + 1, y, z + 2, brick, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, brick, 0, 2);
        world.setBlock(x + 2, y, z, mossCob, 0, 2);
        world.setBlock(x + 2, y, z + 1, brick, 0, 2);

        // Twenty-first layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, brickStair, 2, 2);
        world.setBlock(x - 2, y, z, brickStair, 0, 2);
        world.setBlock(x - 2, y, z + 1, brickStair, 3, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, brickStair, 0, 2);
        world.setBlock(x - 1, y, z - 1, brick, 0, 2);
        world.setBlock(x - 1, y, z, brick, 0, 2);
        world.setBlock(x - 1, y, z + 1, brick, 0, 2);
        world.setBlock(x - 1, y, z + 2, brickStair, 0, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, brickStair, 2, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlockToAir(x, y, z);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, brickStair, 3, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, brickStair, 1, 2);
        world.setBlock(x + 1, y, z - 1, brick, 0, 2);
        world.setBlock(x + 1, y, z, brick, 0, 2);
        world.setBlock(x + 1, y, z + 1, brick, 0, 2);
        world.setBlock(x + 1, y, z + 2, brickStair, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, brickStair, 2, 2);
        world.setBlock(x + 2, y, z, brickStair, 1, 2);
        world.setBlock(x + 2, y, z + 1, brickStair, 3, 2);

        // Twenty-second layer
        y++;

        // Third row
        world.setBlock(x - 1, y, z - 1, brickStair, 0, 2);
        world.setBlock(x - 1, y, z, brick, 0, 2);
        world.setBlock(x - 1, y, z + 1, brickStair, 0, 2);

        // Fourth row
        world.setBlock(x, y, z - 1, brickStair, 2, 2);
        world.setBlockToAir(x, y, z);
        world.setBlock(x, y, z + 1, brickStair, 3, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 1, brickStair, 1, 2);
        world.setBlock(x + 1, y, z, brick, 0, 2);
        world.setBlock(x + 1, y, z + 1, brickStair, 1, 2);

        // Twenty-third layer
        y++;

        // Third row
        world.setBlock(x - 1, y, z, brick, 0, 2);

        // Fourth row
        world.setBlockToAir(x, y, z);

        // Fifth row
        world.setBlock(x + 1, y, z, brick, 0, 2);

        // Twenty-fourth layer
        y++;

        // Third row
        world.setBlock(x - 1, y, z, brickStair, 0, 2);

        // Fourth row
        world.setBlock(x, y, z, luxuryBlocks[rand.nextInt(luxuryBlocks.length)], 0, 2);

        // Fifth row
        world.setBlock(x + 1, y, z, brickStair, 1, 2);

        // Twenty-fifth layer
        y++;

        // Fourth row
        world.setBlock(x, y, z, slab, 5, 2);
    }

    public void generateVariant3(World world, Random rand, int x, int y, int z) {
        // First layer

        // First row
        world.setBlock(x - 3, y, z - 3, netherBrick, 1, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z); // Starting block of the whole schematic
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, netherBrick, 1, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, netherBrick, 0, 2);
        world.setBlock(x - 2, y, z - 1, netherBrick, 0, 2);
        world.setBlockToAir(x - 2, y, z);
        world.setBlock(x - 2, y, z + 1, netherBrick, 0, 2);
        world.setBlock(x - 2, y, z + 2, netherBrick, 0, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, netherBrick, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, netherBrick, 0, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlockToAir(x, y, z - 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, netherBrick, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlockToAir(x, y, z + 2);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, netherBrick, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, netherBrick, 0, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, netherBrick, 0, 2);
        world.setBlock(x + 2, y, z - 1, netherBrick, 0, 2);
        world.setBlockToAir(x + 2, y, z);
        world.setBlock(x + 2, y, z + 1, netherBrick, 0, 2);
        world.setBlock(x + 2, y, z + 2, netherBrick, 0, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, netherBrick, 1, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, netherBrick, 1, 2);

        // Second layer
        y++;

        // First row
        world.setBlock(x - 3, y, z - 3, netherBrick, 1, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z);
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, netherBrick, 1, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, netherBrick, 0, 2);
        world.setBlock(x - 2, y, z - 1, netherBrick, 0, 2);
        world.setBlockToAir(x - 2, y, z);
        world.setBlock(x - 2, y, z + 1, netherBrick, 0, 2);
        world.setBlock(x - 2, y, z + 2, netherBrick, 0, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, netherBrick, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, netherBrick, 0, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlockToAir(x, y, z - 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, netherBrick, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlockToAir(x, y, z + 2);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, netherBrick, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, netherBrick, 0, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, netherBrick, 0, 2);
        world.setBlock(x + 2, y, z - 1, netherBrick, 0, 2);
        world.setBlockToAir(x + 2, y, z);
        world.setBlock(x + 2, y, z + 1, netherBrick, 0, 2);
        world.setBlock(x + 2, y, z + 2, netherBrick, 0, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, netherBrick, 1, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, netherBrick, 1, 2);

        // Third layer
        y++;

        // First row
        world.setBlock(x - 3, y, z - 3, netherBrick, 1, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z);
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, netherBrick, 1, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, netherBrick, 2, 2);
        world.setBlock(x - 2, y, z - 1, netherBrick, 2, 2);
        world.setBlock(x - 2, y, z, netherBrick, 2, 2);
        world.setBlock(x - 2, y, z + 1, netherBrick, 2, 2);
        world.setBlock(x - 2, y, z + 2, netherBrick, 2, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, netherBrick, 2, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, netherBrick, 2, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlock(x, y, z - 2, netherBrick, 2, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, netherBrick, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, netherBrick, 2, 2);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, netherBrick, 2, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, netherBrick, 2, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, netherBrick, 2, 2);
        world.setBlock(x + 2, y, z - 1, netherBrick, 2, 2);
        world.setBlock(x + 2, y, z, netherBrick, 2, 2);
        world.setBlock(x + 2, y, z + 1, netherBrick, 2, 2);
        world.setBlock(x + 2, y, z + 2, netherBrick, 2, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, netherBrick, 1, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, netherBrick, 1, 2);

        // Fourth layer
        y++;

        // First row
        world.setBlock(x - 3, y, z - 3, netherBrick, 1, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z);
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, netherBrick, 1, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, netherBrick, 0, 2);
        world.setBlock(x - 2, y, z - 1, netherBrick, 0, 2);
        world.setBlock(x - 2, y, z, netherFence, 0, 2);
        world.setBlock(x - 2, y, z + 1, netherBrick, 0, 2);
        world.setBlock(x - 2, y, z + 2, netherBrick, 0, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, netherBrick, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, netherBrick, 0, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlock(x, y, z - 2, netherFence, 2, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, netherBrick, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, netherFence, 3, 2);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, netherBrick, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, netherBrick, 0, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, netherBrick, 0, 2);
        world.setBlock(x + 2, y, z - 1, netherBrick, 0, 2);
        world.setBlock(x + 2, y, z, netherFence, 1, 2);
        world.setBlock(x + 2, y, z + 1, netherBrick, 0, 2);
        world.setBlock(x + 2, y, z + 2, netherBrick, 0, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, netherBrick, 1, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, netherBrick, 1, 2);

        // Fifth layer
        y++;

        // First row
        world.setBlock(x - 3, y, z - 3, netherBrick, 1, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z);
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, netherBrick, 1, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, netherBrick, 0, 2);
        world.setBlock(x - 2, y, z - 1, netherBrick, 0, 2);
        world.setBlock(x - 2, y, z, netherFence, 0, 2);
        world.setBlock(x - 2, y, z + 1, netherBrick, 0, 2);
        world.setBlock(x - 2, y, z + 2, netherBrick, 0, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, netherBrick, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, netherBrick, 0, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlock(x, y, z - 2, netherFence, 0, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, netherBrick, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, netherFence, 0, 2);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, netherBrick, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, netherBrick, 0, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, netherBrick, 0, 2);
        world.setBlock(x + 2, y, z - 1, netherBrick, 0, 2);
        world.setBlock(x + 2, y, z, netherFence, 0, 2);
        world.setBlock(x + 2, y, z + 1, netherBrick, 0, 2);
        world.setBlock(x + 2, y, z + 2, netherBrick, 0, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, netherBrick, 1, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, netherBrick, 1, 2);

        // Sixth layer
        y++;

        // First row
        world.setBlock(x - 3, y, z - 3, netherBrick, 1, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z);
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, netherBrick, 1, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, netherBrick, 2, 2);
        world.setBlock(x - 2, y, z - 1, netherBrick, 2, 2);
        world.setBlock(x - 2, y, z, netherBrick, 2, 2);
        world.setBlock(x - 2, y, z + 1, netherBrick, 2, 2);
        world.setBlock(x - 2, y, z + 2, netherBrick, 2, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, netherBrick, 2, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, netherBrick, 2, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlock(x, y, z - 2, netherBrick, 2, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, netherBrick, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, netherBrick, 2, 2);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, netherBrick, 2, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, netherBrick, 2, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, netherBrick, 2, 2);
        world.setBlock(x + 2, y, z - 1, netherBrick, 2, 2);
        world.setBlock(x + 2, y, z, netherBrick, 2, 2);
        world.setBlock(x + 2, y, z + 1, netherBrick, 2, 2);
        world.setBlock(x + 2, y, z + 2, netherBrick, 2, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, netherBrick, 1, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, netherBrick, 1, 2);

        // Seventh layer
        y++;

        // First row
        world.setBlock(x - 3, y, z - 3, netherBrick, 1, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z);
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, netherBrick, 1, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, netherBrick, 1, 2);
        world.setBlock(x - 2, y, z - 1, netherBrick, 0, 2);
        world.setBlock(x - 2, y, z, netherFence, 0, 2);
        world.setBlock(x - 2, y, z + 1, netherBrick, 0, 2);
        world.setBlock(x - 2, y, z + 2, netherBrick, 1, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, netherBrick, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, netherBrick, 0, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlock(x, y, z - 2, netherFence, 2, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, netherBrick, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, netherFence, 3, 2);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, netherBrick, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, netherBrick, 0, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, netherBrick, 1, 2);
        world.setBlock(x + 2, y, z - 1, netherBrick, 0, 2);
        world.setBlock(x + 2, y, z, netherFence, 1, 2);
        world.setBlock(x + 2, y, z + 1, netherBrick, 0, 2);
        world.setBlock(x + 2, y, z + 2, netherBrick, 1, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, netherBrick, 1, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, netherBrick, 1, 2);

        // Eighth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, netherBrick, 1, 2);
        world.setBlock(x - 2, y, z - 1, netherBrick, 0, 2);
        world.setBlock(x - 2, y, z, netherFence, 0, 2);
        world.setBlock(x - 2, y, z + 1, netherBrick, 0, 2);
        world.setBlock(x - 2, y, z + 2, netherBrick, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, netherBrick, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, netherBrick, 0, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, netherFence, 0, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, netherBrick, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, netherFence, 0, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, netherBrick, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, netherBrick, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, netherBrick, 1, 2);
        world.setBlock(x + 2, y, z - 1, netherBrick, 0, 2);
        world.setBlock(x + 2, y, z, netherFence, 0, 2);
        world.setBlock(x + 2, y, z + 1, netherBrick, 0, 2);
        world.setBlock(x + 2, y, z + 2, netherBrick, 1, 2);

        // Ninth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, netherBrick, 1, 2);
        world.setBlock(x - 2, y, z - 1, netherBrick, 2, 2);
        world.setBlock(x - 2, y, z, netherBrick, 2, 2);
        world.setBlock(x - 2, y, z + 1, netherBrick, 2, 2);
        world.setBlock(x - 2, y, z + 2, netherBrick, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, netherBrick, 2, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, netherBrick, 2, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, netherBrick, 2, 2);
        world.setBlock(x, y, z - 1, netherBrick, 2, 2);
        world.setBlock(x, y, z, netherBrick, 1, 2);
        world.setBlock(x, y, z + 1, netherBrick, 2, 2);
        world.setBlock(x, y, z + 2, netherBrick, 2, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, netherBrick, 2, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, netherBrick, 2, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, netherBrick, 1, 2);
        world.setBlock(x + 2, y, z - 1, netherBrick, 2, 2);
        world.setBlock(x + 2, y, z, netherBrick, 2, 2);
        world.setBlock(x + 2, y, z + 1, netherBrick, 2, 2);
        world.setBlock(x + 2, y, z + 2, netherBrick, 1, 2);

        // Tenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, netherBrick, 1, 2);
        world.setBlock(x - 2, y, z - 1, netherBrick, 0, 2);
        world.setBlock(x - 2, y, z, netherFence, 0, 2);
        world.setBlock(x - 2, y, z + 1, netherBrick, 0, 2);
        world.setBlock(x - 2, y, z + 2, netherBrick, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, netherBrick, 0, 2);
        world.setBlockToAir(x - 1, y, z - 1);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, netherBrick, 0, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, netherFence, 0, 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, netherBrick, 1, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlock(x, y, z + 2, netherFence, 0, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, netherBrick, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlockToAir(x + 1, y, z + 1);
        world.setBlock(x + 1, y, z + 2, netherBrick, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, netherBrick, 1, 2);
        world.setBlock(x + 2, y, z - 1, netherBrick, 0, 2);
        world.setBlock(x + 2, y, z, netherFence, 0, 2);
        world.setBlock(x + 2, y, z + 1, netherBrick, 0, 2);
        world.setBlock(x + 2, y, z + 2, netherBrick, 1, 2);

        // Eleventh layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, netherBrick, 1, 2);
        world.setBlock(x - 2, y, z - 1, netherBrick, 0, 2);
        world.setBlock(x - 2, y, z, netherFence, 0, 2);
        world.setBlock(x - 2, y, z + 1, netherBrick, 0, 2);
        world.setBlock(x - 2, y, z + 2, netherBrick, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, netherBrick, 0, 2);
        world.setBlockToAir(x - 1, y, z - 1);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, netherBrick, 0, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, netherFence, 0, 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, netherBrick, 1, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlock(x, y, z + 2, netherFence, 0, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, netherBrick, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlockToAir(x + 1, y, z + 1);
        world.setBlock(x + 1, y, z + 2, netherBrick, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, netherBrick, 1, 2);
        world.setBlock(x + 2, y, z - 1, netherBrick, 0, 2);
        world.setBlock(x + 2, y, z, netherFence, 0, 2);
        world.setBlock(x + 2, y, z + 1, netherBrick, 0, 2);
        world.setBlock(x + 2, y, z + 2, netherBrick, 1, 2);

        // Twelfth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, netherBrick, 1, 2);
        world.setBlock(x - 2, y, z - 1, netherBrick, 2, 2);
        world.setBlock(x - 2, y, z, netherBrick, 2, 2);
        world.setBlock(x - 2, y, z + 1, netherBrick, 2, 2);
        world.setBlock(x - 2, y, z + 2, netherBrick, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, netherBrick, 2, 2);
        world.setBlock(x - 1, y, z - 1, netherBrick, 2, 2);
        world.setBlock(x - 1, y, z, netherBrick, 2, 2);
        world.setBlock(x - 1, y, z + 1, netherBrick, 2, 2);
        world.setBlock(x - 1, y, z + 2, netherBrick, 2, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, netherBrick, 2, 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, netherBrick, 1, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlock(x, y, z + 2, netherBrick, 2, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, netherBrick, 2, 2);
        world.setBlock(x + 1, y, z - 1, netherBrick, 2, 2);
        world.setBlock(x + 1, y, z, netherBrick, 2, 2);
        world.setBlock(x + 1, y, z + 1, netherBrick, 2, 2);
        world.setBlock(x + 1, y, z + 2, netherBrick, 2, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, netherBrick, 1, 2);
        world.setBlock(x + 2, y, z - 1, netherBrick, 2, 2);
        world.setBlock(x + 2, y, z, netherBrick, 2, 2);
        world.setBlock(x + 2, y, z + 1, netherBrick, 2, 2);
        world.setBlock(x + 2, y, z + 2, netherBrick, 1, 2);

        // Thirteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, netherBrick, 1, 2);
        world.setBlock(x - 2, y, z, netherBrick, 0, 2);
        world.setBlock(x - 2, y, z + 1, netherBrick, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, netherBrick, 1, 2);
        world.setBlock(x - 1, y, z - 1, netherBrick, 0, 2);
        world.setBlock(x - 1, y, z, lava, 0, 2);
        world.setBlock(x - 1, y, z + 1, netherBrick, 0, 2);
        world.setBlock(x - 1, y, z + 2, netherBrick, 1, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, netherFence, 2, 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, nether, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlock(x, y, z + 2, netherFence, 3, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, netherBrick, 1, 2);
        world.setBlock(x + 1, y, z - 1, netherBrick, 0, 2);
        world.setBlock(x + 1, y, z, lava, 0, 2);
        world.setBlock(x + 1, y, z + 1, netherBrick, 0, 2);
        world.setBlock(x + 1, y, z + 2, netherBrick, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, netherBrick, 1, 2);
        world.setBlock(x + 2, y, z, netherBrick, 1, 2);
        world.setBlock(x + 2, y, z + 1, netherBrick, 1, 2);

        // Fourteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, netherBrick, 1, 2);
        world.setBlock(x - 2, y, z, netherBrick, 0, 2);
        world.setBlock(x - 2, y, z + 1, netherBrick, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, netherBrick, 1, 2);
        world.setBlock(x - 1, y, z - 1, netherBrick, 0, 2);
        world.setBlock(x - 1, y, z, lava, 0, 2);
        world.setBlock(x - 1, y, z + 1, netherBrick, 0, 2);
        world.setBlock(x - 1, y, z + 2, netherBrick, 1, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, netherFence, 0, 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, nether, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlock(x, y, z + 2, netherFence, 0, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, netherBrick, 1, 2);
        world.setBlock(x + 1, y, z - 1, netherBrick, 0, 2);
        world.setBlock(x + 1, y, z, lava, 0, 2);
        world.setBlock(x + 1, y, z + 1, netherBrick, 0, 2);
        world.setBlock(x + 1, y, z + 2, netherBrick, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, netherBrick, 1, 2);
        world.setBlock(x + 2, y, z, netherBrick, 0, 2);
        world.setBlock(x + 2, y, z + 1, netherBrick, 1, 2);

        // Fifteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, netherBrick, 1, 2);
        world.setBlock(x - 2, y, z, netherBrick, 2, 2);
        world.setBlock(x - 2, y, z + 1, netherBrick, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, netherBrick, 1, 2);
        world.setBlock(x - 1, y, z - 1, netherBrick, 2, 2);
        world.setBlock(x - 1, y, z, lava, 0, 2);
        world.setBlock(x - 1, y, z + 1, netherBrick, 2, 2);
        world.setBlock(x - 1, y, z + 2, netherBrick, 1, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, netherBrick, 2, 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, nether, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlock(x, y, z + 2, netherBrick, 2, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, netherBrick, 1, 2);
        world.setBlock(x + 1, y, z - 1, netherBrick, 2, 2);
        world.setBlock(x + 1, y, z, lava, 0, 2);
        world.setBlock(x + 1, y, z + 1, netherBrick, 2, 2);
        world.setBlock(x + 1, y, z + 2, netherBrick, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, netherBrick, 1, 2);
        world.setBlock(x + 2, y, z, netherBrick, 2, 2);
        world.setBlock(x + 2, y, z + 1, netherBrick, 1, 2);

        // Sixteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, netherBrick, 1, 2);
        world.setBlock(x - 2, y, z, netherBrick, 0, 2);
        world.setBlock(x - 2, y, z + 1, netherBrick, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, netherBrick, 1, 2);
        world.setBlock(x - 1, y, z - 1, netherBrick, 0, 2);
        world.setBlock(x - 1, y, z, tnt, 0, 2);
        world.setBlock(x - 1, y, z + 1, netherBrick, 0, 2);
        world.setBlock(x - 1, y, z + 2, netherBrick, 1, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, netherBrick, 0, 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, nether, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlock(x, y, z + 2, netherBrick, 0, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, netherBrick, 1, 2);
        world.setBlock(x + 1, y, z - 1, netherBrick, 0, 2);
        world.setBlock(x + 1, y, z, tnt, 0, 2);
        world.setBlock(x + 1, y, z + 1, netherBrick, 0, 2);
        world.setBlock(x + 1, y, z + 2, netherBrick, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, netherBrick, 1, 2);
        world.setBlock(x + 2, y, z, netherBrick, 0, 2);
        world.setBlock(x + 2, y, z + 1, netherBrick, 1, 2);

        // Seventeenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, netherBrick, 1, 2);
        world.setBlock(x - 2, y, z, netherBrick, 0, 2);
        world.setBlock(x - 2, y, z + 1, netherBrick, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, netherBrick, 1, 2);
        world.setBlock(x - 1, y, z - 1, netherBrick, 0, 2);
        world.setBlock(x - 1, y, z, tnt, 0, 2);
        world.setBlock(x - 1, y, z + 1, netherBrick, 0, 2);
        world.setBlock(x - 1, y, z + 2, netherBrick, 1, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, netherBrick, 0, 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, nether, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlock(x, y, z + 2, netherBrick, 0, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, netherBrick, 1, 2);
        world.setBlock(x + 1, y, z - 1, netherBrick, 0, 2);
        world.setBlock(x + 1, y, z, tnt, 0, 2);
        world.setBlock(x + 1, y, z + 1, netherBrick, 0, 2);
        world.setBlock(x + 1, y, z + 2, netherBrick, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, netherBrick, 1, 2);
        world.setBlock(x + 2, y, z, netherBrick, 0, 2);
        world.setBlock(x + 2, y, z + 1, netherBrick, 1, 2);

        // Eighteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, netherBrick, 1, 2);
        world.setBlock(x - 2, y, z, netherBrick, 2, 2);
        world.setBlock(x - 2, y, z + 1, netherBrick, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, netherBrick, 1, 2);
        world.setBlock(x - 1, y, z - 1, plateNether, 0, 2);
        world.setBlock(x - 1, y, z, netherBrick, 1, 2);
        world.setBlock(x - 1, y, z + 1, plateNether, 0, 2);
        world.setBlock(x - 1, y, z + 2, netherBrick, 1, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, netherBrick, 2, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, trapChest, 0, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, netherBrick, 2, 2);

        this.applyChestContents(world, rand, x, y, z, TragicItems.NetherStructureHook);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, netherBrick, 1, 2);
        world.setBlock(x + 1, y, z - 1, plateNether, 0, 2);
        world.setBlock(x + 1, y, z, netherBrick, 1, 2);
        world.setBlock(x + 1, y, z + 1, plateNether, 0, 2);
        world.setBlock(x + 1, y, z + 2, netherBrick, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, netherBrick, 1, 2);
        world.setBlock(x + 2, y, z, netherBrick, 2, 2);
        world.setBlock(x + 2, y, z + 1, netherBrick, 1, 2);

        // Nineteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, netherBrick, 1, 2);
        world.setBlock(x - 2, y, z, netherBrick, 0, 2);
        world.setBlock(x - 2, y, z + 1, netherBrick, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, netherBrick, 1, 2);
        world.setBlockToAir(x - 1, y, z - 1);
        world.setBlock(x - 1, y, z, netherBrick, 1, 2);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, netherBrick, 1, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, netherFence, 0, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlockToAir(x, y, z);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, netherFence, 0, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, netherBrick, 1, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlock(x + 1, y, z, netherBrick, 1, 2);
        world.setBlockToAir(x + 1, y, z + 1);
        world.setBlock(x + 1, y, z + 2, netherBrick, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, netherBrick, 1, 2);
        world.setBlock(x + 2, y, z, netherBrick, 0, 2);
        world.setBlock(x + 2, y, z + 1, netherBrick, 1, 2);

        // Twentieth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, netherBrick, 1, 2);
        world.setBlock(x - 2, y, z, netherBrick, 0, 2);
        world.setBlock(x - 2, y, z + 1, netherBrick, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, netherBrick, 1, 2);
        world.setBlock(x - 1, y, z - 1, netherBrick, 1, 2);
        world.setBlock(x - 1, y, z, netherBrick, 1, 2);
        world.setBlock(x - 1, y, z + 1, netherBrick, 1, 2);
        world.setBlock(x - 1, y, z + 2, netherBrick, 1, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, netherFence, 0, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlockToAir(x, y, z);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, netherFence, 0, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, netherBrick, 1, 2);
        world.setBlock(x + 1, y, z - 1, netherBrick, 1, 2);
        world.setBlock(x + 1, y, z, netherBrick, 1, 2);
        world.setBlock(x + 1, y, z + 1, netherBrick, 1, 2);
        world.setBlock(x + 1, y, z + 2, netherBrick, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, netherBrick, 1, 2);
        world.setBlock(x + 2, y, z, netherBrick, 0, 2);
        world.setBlock(x + 2, y, z + 1, netherBrick, 1, 2);

        // Twenty-first layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, netherStair, 2, 2);
        world.setBlock(x - 2, y, z, netherStair, 0, 2);
        world.setBlock(x - 2, y, z + 1, netherStair, 3, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, netherStair, 0, 2);
        world.setBlock(x - 1, y, z - 1, netherBrick, 1, 2);
        world.setBlock(x - 1, y, z, netherBrick, 1, 2);
        world.setBlock(x - 1, y, z + 1, netherBrick, 1, 2);
        world.setBlock(x - 1, y, z + 2, netherStair, 0, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, netherStair, 2, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlockToAir(x, y, z);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, netherStair, 3, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, netherStair, 1, 2);
        world.setBlock(x + 1, y, z - 1, netherBrick, 1, 2);
        world.setBlock(x + 1, y, z, netherBrick, 1, 2);
        world.setBlock(x + 1, y, z + 1, netherBrick, 1, 2);
        world.setBlock(x + 1, y, z + 2, netherStair, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, netherStair, 2, 2);
        world.setBlock(x + 2, y, z, netherStair, 1, 2);
        world.setBlock(x + 2, y, z + 1, netherStair, 3, 2);

        // Twenty-second layer
        y++;

        // Third row
        world.setBlock(x - 1, y, z - 1, netherStair, 0, 2);
        world.setBlock(x - 1, y, z, netherBrick, 1, 2);
        world.setBlock(x - 1, y, z + 1, netherStair, 0, 2);

        // Fourth row
        world.setBlock(x, y, z - 1, netherStair, 2, 2);
        world.setBlockToAir(x, y, z);
        world.setBlock(x, y, z + 1, netherStair, 3, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 1, netherStair, 1, 2);
        world.setBlock(x + 1, y, z, netherBrick, 1, 2);
        world.setBlock(x + 1, y, z + 1, netherStair, 1, 2);

        // Twenty-third layer
        y++;

        // Third row
        world.setBlock(x - 1, y, z, netherBrick, 1, 2);

        // Fourth row
        world.setBlockToAir(x, y, z);

        // Fifth row
        world.setBlock(x + 1, y, z, netherBrick, 1, 2);

        // Twenty-fourth layer
        y++;

        // Third row
        world.setBlock(x - 1, y, z, netherStair, 0, 2);

        // Fourth row
        world.setBlock(x, y, z, luxuryBlocks[rand.nextInt(luxuryBlocks.length)], 0, 2);

        // Fifth row
        world.setBlock(x + 1, y, z, netherStair, 1, 2);

        // Twenty-fifth layer
        y++;

        // Fourth row
        world.setBlock(x, y, z, slab, 6, 2);
    }

    public void generateVariant4(World world, Random rand, int x, int y, int z) {

        // First layer

        // First row
        world.setBlock(x - 3, y, z - 3, ice, 1, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z); // Starting block of the whole schematic
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, ice, 1, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, ice, 0, 2);
        world.setBlock(x - 2, y, z - 1, ice, 0, 2);
        world.setBlockToAir(x - 2, y, z);
        world.setBlock(x - 2, y, z + 1, ice, 0, 2);
        world.setBlock(x - 2, y, z + 2, ice, 0, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, ice, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, ice, 0, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlockToAir(x, y, z - 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, ice, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlockToAir(x, y, z + 2);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, ice, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, ice, 0, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, ice, 0, 2);
        world.setBlock(x + 2, y, z - 1, ice, 0, 2);
        world.setBlockToAir(x + 2, y, z);
        world.setBlock(x + 2, y, z + 1, ice, 0, 2);
        world.setBlock(x + 2, y, z + 2, ice, 0, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, ice, 1, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, ice, 1, 2);

        // Second layer
        y++;

        // First row
        world.setBlock(x - 3, y, z - 3, ice, 1, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z);
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, ice, 1, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, ice, 0, 2);
        world.setBlock(x - 2, y, z - 1, ice, 0, 2);
        world.setBlockToAir(x - 2, y, z);
        world.setBlock(x - 2, y, z + 1, ice, 0, 2);
        world.setBlock(x - 2, y, z + 2, ice, 0, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, ice, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, ice, 0, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlockToAir(x, y, z - 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, ice, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlockToAir(x, y, z + 2);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, ice, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, ice, 0, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, ice, 0, 2);
        world.setBlock(x + 2, y, z - 1, ice, 0, 2);
        world.setBlockToAir(x + 2, y, z);
        world.setBlock(x + 2, y, z + 1, ice, 0, 2);
        world.setBlock(x + 2, y, z + 2, ice, 0, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, ice, 1, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, ice, 1, 2);

        // Third layer
        y++;

        // First row
        world.setBlock(x - 3, y, z - 3, ice, 1, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z);
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, ice, 1, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, ice, 2, 2);
        world.setBlock(x - 2, y, z - 1, ice, 2, 2);
        world.setBlock(x - 2, y, z, ice, 2, 2);
        world.setBlock(x - 2, y, z + 1, ice, 2, 2);
        world.setBlock(x - 2, y, z + 2, ice, 2, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, ice, 2, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, ice, 2, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlock(x, y, z - 2, ice, 2, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, ice, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, ice, 2, 2);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, ice, 2, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, ice, 2, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, ice, 2, 2);
        world.setBlock(x + 2, y, z - 1, ice, 2, 2);
        world.setBlock(x + 2, y, z, ice, 2, 2);
        world.setBlock(x + 2, y, z + 1, ice, 2, 2);
        world.setBlock(x + 2, y, z + 2, ice, 2, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, ice, 1, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, ice, 1, 2);

        // Fourth layer
        y++;

        // First row
        world.setBlock(x - 3, y, z - 3, ice, 1, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z);
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, ice, 1, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, ice, 0, 2);
        world.setBlock(x - 2, y, z - 1, ice, 0, 2);
        world.setBlock(x - 2, y, z + 1, ice, 0, 2);
        world.setBlock(x - 2, y, z + 2, ice, 0, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, ice, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, ice, 0, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, ice, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, ice, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, ice, 0, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, ice, 0, 2);
        world.setBlock(x + 2, y, z - 1, ice, 0, 2);
        world.setBlock(x + 2, y, z + 1, ice, 0, 2);
        world.setBlock(x + 2, y, z + 2, ice, 0, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, ice, 1, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, ice, 1, 2);

        // Fifth layer
        y++;

        // First row
        world.setBlock(x - 3, y, z - 3, ice, 1, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z);
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, ice, 1, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, ice, 0, 2);
        world.setBlock(x - 2, y, z - 1, ice, 0, 2);
        world.setBlockToAir(x - 2, y, z);
        world.setBlock(x - 2, y, z + 1, ice, 0, 2);
        world.setBlock(x - 2, y, z + 2, ice, 0, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, ice, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, ice, 0, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlockToAir(x, y, z - 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, ice, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlockToAir(x, y, z + 2);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, ice, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, ice, 0, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, ice, 0, 2);
        world.setBlock(x + 2, y, z - 1, ice, 0, 2);
        world.setBlockToAir(x + 2, y, z);
        world.setBlock(x + 2, y, z + 1, ice, 0, 2);
        world.setBlock(x + 2, y, z + 2, ice, 0, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, ice, 1, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, ice, 1, 2);

        // Sixth layer
        y++;

        // First row
        world.setBlock(x - 3, y, z - 3, ice, 1, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z);
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, ice, 1, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, ice, 2, 2);
        world.setBlock(x - 2, y, z - 1, ice, 2, 2);
        world.setBlock(x - 2, y, z, ice, 2, 2);
        world.setBlock(x - 2, y, z + 1, ice, 2, 2);
        world.setBlock(x - 2, y, z + 2, ice, 2, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, ice, 2, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, ice, 2, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlock(x, y, z - 2, ice, 2, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, ice, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, ice, 2, 2);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, ice, 2, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, ice, 2, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, ice, 2, 2);
        world.setBlock(x + 2, y, z - 1, ice, 2, 2);
        world.setBlock(x + 2, y, z, ice, 2, 2);
        world.setBlock(x + 2, y, z + 1, ice, 2, 2);
        world.setBlock(x + 2, y, z + 2, ice, 2, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, ice, 1, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, ice, 1, 2);

        // Seventh layer
        y++;

        // First row
        world.setBlock(x - 3, y, z - 3, ice, 1, 2);
        world.setBlockToAir(x - 3, y, z - 2);
        world.setBlockToAir(x - 3, y, z - 1);
        world.setBlockToAir(x - 3, y, z);
        world.setBlockToAir(x - 3, y, z + 1);
        world.setBlockToAir(x - 3, y, z + 2);
        world.setBlock(x - 3, y, z + 3, ice, 1, 2);

        // Second row
        world.setBlockToAir(x - 2, y, z - 3);
        world.setBlock(x - 2, y, z - 2, ice, 1, 2);
        world.setBlock(x - 2, y, z - 1, ice, 0, 2);
        world.setBlock(x - 2, y, z + 1, ice, 0, 2);
        world.setBlock(x - 2, y, z + 2, ice, 1, 2);
        world.setBlockToAir(x - 2, y, z + 3);

        // Third row
        world.setBlockToAir(x - 1, y, z - 3);
        world.setBlock(x - 1, y, z - 2, ice, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, ice, 0, 2);
        world.setBlockToAir(x - 1, y, z + 3);

        // Fourth row
        world.setBlockToAir(x, y, z - 3);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, ice, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlockToAir(x, y, z + 3);

        // Fifth row
        world.setBlockToAir(x + 1, y, z - 3);
        world.setBlock(x + 1, y, z - 2, ice, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, ice, 0, 2);
        world.setBlockToAir(x + 1, y, z + 3);

        // Sixth row
        world.setBlockToAir(x + 2, y, z - 3);
        world.setBlock(x + 2, y, z - 2, ice, 1, 2);
        world.setBlock(x + 2, y, z - 1, ice, 0, 2);
        world.setBlock(x + 2, y, z + 1, ice, 0, 2);
        world.setBlock(x + 2, y, z + 2, ice, 1, 2);
        world.setBlockToAir(x + 2, y, z + 3);

        // Seventh row
        world.setBlock(x + 3, y, z - 3, ice, 1, 2);
        world.setBlockToAir(x + 3, y, z - 2);
        world.setBlockToAir(x + 3, y, z - 1);
        world.setBlockToAir(x + 3, y, z);
        world.setBlockToAir(x + 3, y, z + 1);
        world.setBlockToAir(x + 3, y, z + 2);
        world.setBlock(x + 3, y, z + 3, ice, 1, 2);

        // Eighth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, ice, 1, 2);
        world.setBlock(x - 2, y, z - 1, ice, 0, 2);
        world.setBlockToAir(x - 2, y, z);
        world.setBlock(x - 2, y, z + 1, ice, 0, 2);
        world.setBlock(x - 2, y, z + 2, ice, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, ice, 0, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, ice, 0, 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, ice, 1, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlockToAir(x, y, z + 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, ice, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, ice, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, ice, 1, 2);
        world.setBlock(x + 2, y, z - 1, ice, 0, 2);
        world.setBlockToAir(x + 2, y, z);
        world.setBlock(x + 2, y, z + 1, ice, 0, 2);
        world.setBlock(x + 2, y, z + 2, ice, 1, 2);

        // Ninth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, ice, 1, 2);
        world.setBlock(x - 2, y, z - 1, ice, 2, 2);
        world.setBlock(x - 2, y, z, ice, 2, 2);
        world.setBlock(x - 2, y, z + 1, ice, 2, 2);
        world.setBlock(x - 2, y, z + 2, ice, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, ice, 2, 2);
        world.setBlock(x - 1, y, z - 1, ladder, 3, 2);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, ice, 2, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, ice, 2, 2);
        world.setBlock(x, y, z - 1, ice, 2, 2);
        world.setBlock(x, y, z, ice, 1, 2);
        world.setBlock(x, y, z + 1, ice, 2, 2);
        world.setBlock(x, y, z + 2, ice, 2, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, ice, 2, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlock(x + 1, y, z + 1, ladder, 2, 2);
        world.setBlock(x + 1, y, z + 2, ice, 2, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, ice, 1, 2);
        world.setBlock(x + 2, y, z - 1, ice, 2, 2);
        world.setBlock(x + 2, y, z, ice, 2, 2);
        world.setBlock(x + 2, y, z + 1, ice, 2, 2);
        world.setBlock(x + 2, y, z + 2, ice, 1, 2);

        // Tenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, ice, 1, 2);
        world.setBlock(x - 2, y, z - 1, ice, 0, 2);
        world.setBlock(x - 2, y, z + 1, ice, 0, 2);
        world.setBlock(x - 2, y, z + 2, ice, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, ice, 0, 2);
        world.setBlockToAir(x - 1, y, z - 1);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, ice, 0, 2);

        // Fourth row
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, ice, 1, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, ice, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlockToAir(x + 1, y, z + 1);
        world.setBlock(x + 1, y, z + 2, ice, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, ice, 1, 2);
        world.setBlock(x + 2, y, z - 1, ice, 0, 2);
        world.setBlock(x + 2, y, z + 1, ice, 0, 2);
        world.setBlock(x + 2, y, z + 2, ice, 1, 2);

        // Eleventh layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, ice, 1, 2);
        world.setBlock(x - 2, y, z - 1, ice, 0, 2);
        world.setBlockToAir(x - 2, y, z);
        world.setBlock(x - 2, y, z + 1, ice, 0, 2);
        world.setBlock(x - 2, y, z + 2, ice, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, ice, 0, 2);
        world.setBlockToAir(x - 1, y, z - 1);
        world.setBlockToAir(x - 1, y, z);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, ice, 0, 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, ice, 1, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlockToAir(x, y, z + 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, ice, 0, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlockToAir(x + 1, y, z);
        world.setBlockToAir(x + 1, y, z + 1);
        world.setBlock(x + 1, y, z + 2, ice, 0, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, ice, 1, 2);
        world.setBlock(x + 2, y, z - 1, ice, 0, 2);
        world.setBlockToAir(x + 2, y, z);
        world.setBlock(x + 2, y, z + 1, ice, 0, 2);
        world.setBlock(x + 2, y, z + 2, ice, 1, 2);

        // Twelfth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 2, ice, 1, 2);
        world.setBlock(x - 2, y, z - 1, ice, 2, 2);
        world.setBlock(x - 2, y, z, ice, 2, 2);
        world.setBlock(x - 2, y, z + 1, ice, 2, 2);
        world.setBlock(x - 2, y, z + 2, ice, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, ice, 2, 2);
        world.setBlock(x - 1, y, z - 1, ice, 2, 2);
        world.setBlock(x - 1, y, z, ice, 2, 2);
        world.setBlock(x - 1, y, z + 1, ice, 2, 2);
        world.setBlock(x - 1, y, z + 2, ice, 2, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, ice, 2, 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, ice, 1, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlock(x, y, z + 2, ice, 2, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, ice, 2, 2);
        world.setBlock(x + 1, y, z - 1, ice, 2, 2);
        world.setBlock(x + 1, y, z, ice, 2, 2);
        world.setBlock(x + 1, y, z + 1, ice, 2, 2);
        world.setBlock(x + 1, y, z + 2, ice, 2, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 2, ice, 1, 2);
        world.setBlock(x + 2, y, z - 1, ice, 2, 2);
        world.setBlock(x + 2, y, z, ice, 2, 2);
        world.setBlock(x + 2, y, z + 1, ice, 2, 2);
        world.setBlock(x + 2, y, z + 2, ice, 1, 2);

        // Thirteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, ice, 1, 2);
        world.setBlock(x - 2, y, z + 1, ice, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, ice, 1, 2);
        world.setBlock(x - 1, y, z - 1, ice, 0, 2);
        world.setBlock(x - 1, y, z, tnt, 0, 2);
        world.setBlock(x - 1, y, z + 1, ice, 0, 2);
        world.setBlock(x - 1, y, z + 2, ice, 1, 2);

        // Fourth row
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, snow, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, ice, 1, 2);
        world.setBlock(x + 1, y, z - 1, ice, 0, 2);
        world.setBlock(x + 1, y, z, tnt, 0, 2);
        world.setBlock(x + 1, y, z + 1, ice, 0, 2);
        world.setBlock(x + 1, y, z + 2, ice, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, ice, 1, 2);
        world.setBlock(x + 2, y, z + 1, ice, 1, 2);

        // Fourteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, ice, 1, 2);
        world.setBlock(x - 2, y, z, ice, 0, 2);
        world.setBlock(x - 2, y, z + 1, ice, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, ice, 1, 2);
        world.setBlock(x - 1, y, z - 1, ice, 0, 2);
        world.setBlock(x - 1, y, z, tnt, 0, 2);
        world.setBlock(x - 1, y, z + 1, ice, 0, 2);
        world.setBlock(x - 1, y, z + 2, ice, 1, 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, snow, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlockToAir(x, y, z + 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, ice, 1, 2);
        world.setBlock(x + 1, y, z - 1, ice, 0, 2);
        world.setBlock(x + 1, y, z, tnt, 0, 2);
        world.setBlock(x + 1, y, z + 1, ice, 0, 2);
        world.setBlock(x + 1, y, z + 2, ice, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, ice, 1, 2);
        world.setBlock(x + 2, y, z, ice, 0, 2);
        world.setBlock(x + 2, y, z + 1, ice, 1, 2);

        // Fifteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, ice, 1, 2);
        world.setBlock(x - 2, y, z, ice, 2, 2);
        world.setBlock(x - 2, y, z + 1, ice, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, ice, 1, 2);
        world.setBlock(x - 1, y, z - 1, ice, 2, 2);
        world.setBlock(x - 1, y, z, tnt, 0, 2);
        world.setBlock(x - 1, y, z + 1, ice, 2, 2);
        world.setBlock(x - 1, y, z + 2, ice, 1, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, ice, 2, 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, snow, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlock(x, y, z + 2, ice, 2, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, ice, 1, 2);
        world.setBlock(x + 1, y, z - 1, ice, 2, 2);
        world.setBlock(x + 1, y, z, tnt, 0, 2);
        world.setBlock(x + 1, y, z + 1, ice, 2, 2);
        world.setBlock(x + 1, y, z + 2, ice, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, ice, 1, 2);
        world.setBlock(x + 2, y, z, ice, 2, 2);
        world.setBlock(x + 2, y, z + 1, ice, 1, 2);

        // Sixteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, ice, 1, 2);
        world.setBlock(x - 2, y, z, ice, 0, 2);
        world.setBlock(x - 2, y, z + 1, ice, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, ice, 1, 2);
        world.setBlock(x - 1, y, z - 1, ice, 0, 2);
        world.setBlock(x - 1, y, z, ice, 0, 2);
        world.setBlock(x - 1, y, z + 1, ice, 0, 2);
        world.setBlock(x - 1, y, z + 2, ice, 1, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, ice, 0, 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, snow, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlock(x, y, z + 2, ice, 0, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, ice, 1, 2);
        world.setBlock(x + 1, y, z - 1, ice, 0, 2);
        world.setBlock(x + 1, y, z, ice, 0, 2);
        world.setBlock(x + 1, y, z + 1, ice, 0, 2);
        world.setBlock(x + 1, y, z + 2, ice, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, ice, 1, 2);
        world.setBlock(x + 2, y, z, ice, 0, 2);
        world.setBlock(x + 2, y, z + 1, ice, 1, 2);

        // Seventeenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, ice, 1, 2);
        world.setBlock(x - 2, y, z, ice, 0, 2);
        world.setBlock(x - 2, y, z + 1, ice, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, ice, 1, 2);
        world.setBlock(x - 1, y, z - 1, ice, 0, 2);
        world.setBlock(x - 1, y, z, redstone, 0, 2);
        world.setBlock(x - 1, y, z + 1, ice, 0, 2);
        world.setBlock(x - 1, y, z + 2, ice, 1, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, ice, 0, 2);
        world.setBlock(x, y, z - 1, ladder, 2, 2);
        world.setBlock(x, y, z, snow, 0, 2);
        world.setBlock(x, y, z + 1, ladder, 3, 2);
        world.setBlock(x, y, z + 2, ice, 0, 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, ice, 1, 2);
        world.setBlock(x + 1, y, z - 1, ice, 0, 2);
        world.setBlock(x + 1, y, z, redstone, 0, 2);
        world.setBlock(x + 1, y, z + 1, ice, 0, 2);
        world.setBlock(x + 1, y, z + 2, ice, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, ice, 1, 2);
        world.setBlock(x + 2, y, z, ice, 0, 2);
        world.setBlock(x + 2, y, z + 1, ice, 1, 2);

        // Eighteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, ice, 1, 2);
        world.setBlock(x - 2, y, z, ice, 2, 2);
        world.setBlock(x - 2, y, z + 1, ice, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, ice, 1, 2);
        world.setBlock(x - 1, y, z - 1, plate, 0, 2);
        world.setBlock(x - 1, y, z, ice, 1, 2);
        world.setBlock(x - 1, y, z + 1, plate, 0, 2);
        world.setBlock(x - 1, y, z + 2, ice, 1, 2);

        // Fourth row
        world.setBlock(x, y, z - 2, ice, 2, 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlock(x, y, z, trapChest, 0, 2);
        world.setBlockToAir(x, y, z + 1);
        world.setBlock(x, y, z + 2, ice, 2, 2);

        this.applyChestContents(world, rand, x, y, z, TragicItems.NetherStructureHook);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, ice, 1, 2);
        world.setBlock(x + 1, y, z - 1, plate, 0, 2);
        world.setBlock(x + 1, y, z, ice, 1, 2);
        world.setBlock(x + 1, y, z + 1, plate, 0, 2);
        world.setBlock(x + 1, y, z + 2, ice, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, ice, 1, 2);
        world.setBlock(x + 2, y, z, ice, 2, 2);
        world.setBlock(x + 2, y, z + 1, ice, 1, 2);

        // Nineteenth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, ice, 1, 2);
        world.setBlock(x - 2, y, z, ice, 0, 2);
        world.setBlock(x - 2, y, z + 1, ice, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, ice, 1, 2);
        world.setBlockToAir(x - 1, y, z - 1);
        world.setBlock(x - 1, y, z, ice, 1, 2);
        world.setBlockToAir(x - 1, y, z + 1);
        world.setBlock(x - 1, y, z + 2, ice, 1, 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 1);
        world.setBlockToAir(x, y, z);
        world.setBlockToAir(x, y, z + 1);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, ice, 1, 2);
        world.setBlockToAir(x + 1, y, z - 1);
        world.setBlock(x + 1, y, z, ice, 1, 2);
        world.setBlockToAir(x + 1, y, z + 1);
        world.setBlock(x + 1, y, z + 2, ice, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, ice, 1, 2);
        world.setBlock(x + 2, y, z, ice, 0, 2);
        world.setBlock(x + 2, y, z + 1, ice, 1, 2);

        // Twentieth layer
        y++;

        // Second row
        world.setBlock(x - 2, y, z - 1, ice, 1, 2);
        world.setBlock(x - 2, y, z, ice, 0, 2);
        world.setBlock(x - 2, y, z + 1, ice, 1, 2);

        // Third row
        world.setBlock(x - 1, y, z - 2, ice, 1, 2);
        world.setBlock(x - 1, y, z - 1, ice, 1, 2);
        world.setBlock(x - 1, y, z, ice, 1, 2);
        world.setBlock(x - 1, y, z + 1, ice, 1, 2);
        world.setBlock(x - 1, y, z + 2, ice, 1, 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 2);
        world.setBlockToAir(x, y, z - 1);
        world.setBlockToAir(x, y, z);
        world.setBlockToAir(x, y, z + 1);
        world.setBlockToAir(x, y, z + 2);

        // Fifth row
        world.setBlock(x + 1, y, z - 2, ice, 1, 2);
        world.setBlock(x + 1, y, z - 1, ice, 1, 2);
        world.setBlock(x + 1, y, z, ice, 1, 2);
        world.setBlock(x + 1, y, z + 1, ice, 1, 2);
        world.setBlock(x + 1, y, z + 2, ice, 1, 2);

        // Sixth row
        world.setBlock(x + 2, y, z - 1, ice, 1, 2);
        world.setBlock(x + 2, y, z, ice, 0, 2);
        world.setBlock(x + 2, y, z + 1, ice, 1, 2);

        // Twenty-first layer
        y++;

        // Third row
        world.setBlock(x - 1, y, z - 1, ice, 1, 2);
        world.setBlock(x - 1, y, z, ice, 1, 2);
        world.setBlock(x - 1, y, z + 1, ice, 1, 2);

        // Fourth row
        world.setBlockToAir(x, y, z - 1);
        world.setBlockToAir(x, y, z);
        world.setBlockToAir(x, y, z + 1);

        world.setBlock(x + 1, y, z - 1, ice, 1, 2);
        world.setBlock(x + 1, y, z, ice, 1, 2);
        world.setBlock(x + 1, y, z + 1, ice, 1, 2);

        // Twenty-second layer
        y++;

        // Third row
        world.setBlock(x - 1, y, z, ice, 1, 2);

        // Fourth row
        world.setBlockToAir(x, y, z);

        // Fifth row
        world.setBlock(x + 1, y, z, ice, 1, 2);

        // Twenty-third layer
        y++;

        // Third row
        world.setBlock(x - 1, y, z, ice, 1, 2);

        // Fourth row
        world.setBlock(x, y, z, luxuryBlocks[rand.nextInt(luxuryBlocks.length)], 0, 2);

        // Fifth row
        world.setBlock(x + 1, y, z, ice, 1, 2);

        // Twenty-fourth layer
        y++;

        // Fourth row
        world.setBlock(x, y, z, ice, 0, 2);

        // Twenty-fifth layer
        y++;

    }
}
