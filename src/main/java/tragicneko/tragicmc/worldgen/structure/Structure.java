package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.google.common.collect.Sets;

import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.worldgen.schematic.Schematic;

public class Structure extends WorldGenerator {

    protected final Schematic schematic;
    public final int structureId;
    private final String structureName;
    protected final int height;

    public static Structure[] structureList = new Structure[24];
    public static Structure apisTemple = new StructureApisTemple(0, "apisTemple");
    public static Structure towerStructure = new StructureTower(1, "tower");
    public static Structure deathCircle = new StructureDeathCircle(2, "deathCircle");
    public static Structure obsidianCavern = new StructureObsidianCavern(3, "obsidianCavern");
    public static Structure kitsuneDen = new StructureKitsuneDen(4, "kitsuneDen");
    public static Structure celestialTemple = new StructureCelestialTemple(5, "celestialTemple");
    public static Structure timeAltar = new StructureTimeAltar(6, "timeAltar");
    public static Structure soulTomb = new StructureSoulTomb(7, "soulTomb");
    public static Structure corruptedSpire = new StructureCorruptedSpire(8, "corruptedSpire");
    public static Structure empariahCave = new StructureEmpariahCave(9, "empariahCave");
    public static Structure claymationRuin = new StructureClaymationRuin(10, "claymationRuin");
    public static Structure darkHut = new StructureDarkHut(11, "darkHut");
    public static Structure spiderNest = new StructureSpiderNest(12, "spiderNest");
    public static Structure memoryCache = new StructureMemoryCache(13, "memoryCache");
    public static Structure lightSpire = new StructureLightSpire(14, "lightSpire");
    public static Structure hackerNet = new StructureHackerNet(15, "hackerNet");
    public static Structure cubeMaze = new StructureCubeMaze(16, "cubeMaze");
    public static Structure outlook = new StructureOutlook(17, "outlook");

    public static final Set validBlocks = Sets.newHashSet(
        new Block[] { Blocks.grass, Blocks.tallgrass, Blocks.yellow_flower, Blocks.red_flower, Blocks.double_plant,
            Blocks.snow_layer, Blocks.snow, Blocks.stone, Blocks.sand, Blocks.air, Blocks.netherrack,
            TragicBlocks.Quicksand, Blocks.ice, Blocks.water, Blocks.lava, Blocks.dirt, Blocks.gravel, Blocks.clay,
            Blocks.hardened_clay, TragicBlocks.DarkStone, TragicBlocks.DarkSand, TragicBlocks.DeadDirt, Blocks.leaves,
            Blocks.packed_ice, TragicBlocks.AshenGrass, TragicBlocks.BrushedGrass, TragicBlocks.AshenLeaves,
            TragicBlocks.AshenTallGrass, TragicBlocks.DeadBush, TragicBlocks.AshenBush, TragicBlocks.BleachedLeaves,
            TragicBlocks.PaintedLeaves, TragicBlocks.Glowvine, TragicBlocks.DriedGrass, TragicBlocks.PaintedTallGrass,
            TragicBlocks.StarlitGrass, TragicBlocks.StarlitTallGrass, TragicBlocks.StarCrystal,
            TragicBlocks.ErodedStone, TragicBlocks.DarkCobblestone, TragicBlocks.HallowedGrass,
            TragicBlocks.HallowedLeaves, TragicBlocks.HallowedLeafTrim, TragicBlocks.MoltenRock,
            TragicBlocks.ScorchedRock, TragicBlocks.StructureSeed, TragicBlocks.Luminescence, TragicBlocks.ExplosiveGas,
            TragicBlocks.RadiatedGas, TragicBlocks.CorruptedGas, TragicBlocks.WitheringGas, TragicBlocks.WickedVine,
            TragicBlocks.Permafrost, TragicBlocks.IcedDirt, TragicBlocks.IceSpike, TragicBlocks.IceSpikeHanging,
            TragicBlocks.Light, TragicBlocks.FragileLight, TragicBlocks.FragileLightInvis, TragicBlocks.Crystal,
            TragicBlocks.DarkGrass, TragicBlocks.Lichen, TragicBlocks.Moss, TragicBlocks.TragicFlower,
            TragicBlocks.TragicFlower2 });

    public Structure(Schematic sch, int id, String s) {
        this.schematic = sch;
        if (structureList[id] != null)
            throw new IllegalArgumentException("There is a structure using that ID (" + id + ") already!");
        structureList[id] = this;
        this.structureId = id;
        this.height = sch.structureHeight;
        this.structureName = s;
    }

    /**
     * Override to set a variant amount for a particular structure
     * 
     * @return
     */
    public int getVariantSize() {
        return 1;
    }

    /**
     * Whether or not this particular structure should only generate on a solid surface
     * 
     * @return
     */
    public boolean isSurfaceStructure() {
        return false;
    }

    /**
     * Check if the structure is in the correct dimension
     * 
     * @param dim
     * @return
     */
    public boolean isValidDimension(int dim) {
        return true;
    }

    /**
     * Check if the starting coords for the structure are valid, may check a larger area as well
     * 
     * @param world
     * @param x
     * @param y
     * @param z
     * @param rand
     * @return
     */
    public boolean areCoordsValidForGeneration(World world, int x, int y, int z, Random rand) {
        if (!validBlocks.contains(world.getBlock(x, y, z)) || y + this.height >= world.provider.getActualHeight())
            return false;

        if (this.isSurfaceStructure()) {
            if (y <= 50 || !World.doesBlockHaveSolidTopSurface(world, x, y - 1, z)
                || !world.getBlock(x, y - 1, z)
                    .isOpaqueCube()
                || !world.canBlockSeeTheSky(x, y, z)) return false;

            for (int y1 = 0; y1 < this.height; y1++) {
                if (!validBlocks.contains(world.getBlock(x, y + y1 + 1, z))) return false;
            }
        } else {
            for (int y1 = 0; y1 < this.height; y1++) {
                if (!validBlocks.contains(world.getBlock(x, y - y1, z))
                    || !validBlocks.contains(world.getBlock(x - y1, y, z))
                    || !validBlocks.contains(world.getBlock(x + y1, y, z))
                    || !validBlocks.contains(world.getBlock(x, y, z + y1))
                    || !validBlocks.contains(world.getBlock(x, y, z - y1))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Specific checks for particular structures, this should be where to check things like if the structure is allowed
     * in the config or if a valid variant
     * id is set, etc.
     * 
     * @param rand
     * @param variantID
     * @return
     */
    public boolean canGenerate() {
        return TragicConfig.structureAllow[this.structureId];
    }

    /**
     * Returns whether a structure can generate based on the configured rarity value out of the integer value to compare
     * to
     * 
     * @param compare
     * @return
     */
    public boolean getRarity(final int compare) {
        return TragicMC.rand.nextInt(compare) <= TragicConfig.structureRarity[this.structureId];
    }

    public int getHeight() {
        return this.height;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        return generateStructureWithVariant(rand.nextInt(this.getVariantSize()), world, rand, x, y, z);
    }

    /**
     * Must be overridden by each structure to actually generate their schematics, this just does generic preliminary
     * checks, also note that
     * this is how structure seeds generate structures
     * 
     * @param variant
     * @param world
     * @param rand
     * @param x
     * @param y
     * @param z
     * @return
     */
    public boolean generateStructureWithVariant(int variant, World world, Random rand, int x, int y, int z) {
        return !world.isRemote && this.canGenerate();
    }

    public String getLocalizedName() {
        return StatCollector.translateToLocal("tile.tragicmc.structureSeed." + this.structureName + ".name");
    }

    public String getUnlocalizedName() {
        return this.structureName;
    }

    public int getStructureColor() {
        return 0x000000;
    }
}
