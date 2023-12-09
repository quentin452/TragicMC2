package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import cpw.mods.fml.common.IWorldGenerator;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class StructureWorldGen implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
        IChunkProvider chunkProvider) {
        if (world.isRemote || !world.getWorldInfo()
            .isMapFeaturesEnabled()
            || random.nextInt(500) > TragicConfig.structureOverallRarity
            || (world.provider.dimensionId == TragicConfig.collisionID
                || world.provider.dimensionId == TragicConfig.synapseID) && !TragicConfig.allowDimension)
            return;

        int x = (chunkX * 16) + random.nextInt(16);
        int y = random.nextInt(118) + random.nextInt(118) + 10;
        int z = (chunkZ * 16) + random.nextInt(16);

        int top = world.getTopSolidOrLiquidBlock(x, z);
        ArrayList<Structure> cands = new ArrayList<Structure>();

        for (Structure s : Structure.structureList) {
            if (s != null && s.isValidDimension(world.provider.dimensionId)) {
                if (s.isSurfaceStructure() && s.areCoordsValidForGeneration(world, x, top, z, random)
                    || s.areCoordsValidForGeneration(world, x, y, z, random)) {
                    cands.add(s);
                }
            }
        }

        if (cands.isEmpty()) return;

        Collections.shuffle(cands, random);

        for (Structure s : cands) {
            if (s.generate(world, random, x, s.isSurfaceStructure() ? top : y, z)) {
                TragicMC.logInfo(
                    s.getLocalizedName() + " succesfully generated at "
                        + x
                        + ", "
                        + (s.isSurfaceStructure() ? top : y)
                        + ", "
                        + z);
                break;
            }
        }
    }
}
