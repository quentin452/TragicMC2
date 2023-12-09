package tragicneko.tragicmc.dimension;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.client.ClientProxy;

public class SynapseWorldProvider extends WorldProvider {

    public SynapseWorldProvider() {
        this.dimensionId = TragicConfig.synapseID;
        this.hasNoSky = true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IRenderHandler getSkyRenderer() {
        return ClientProxy.synapseSkyRenderer;
    }

    @Override
    public void registerWorldChunkManager() {
        this.worldChunkMgr = new WorldChunkManagerHell(TragicBiome.Synapse, 0.0F);
    }

    @Override
    public IChunkProvider createChunkGenerator() {
        return new SynapseChunkProvider(this.worldObj, this.worldObj.getSeed());
    }

    @Override
    public String getSaveFolder() {
        return "Synapse";
    }

    @Override
    public float calculateCelestialAngle(long p_76563_1_, float p_76563_3_) {
        return 0.0F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float[] calcSunriseSunsetColors(float p_76560_1_, float p_76560_2_) {
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float p_76562_1_, float p_76562_2_) {
        int i = 10518688;
        float f2 = MathHelper.cos(p_76562_1_ * (float) Math.PI * 2.0F) * 2.0F + 0.5F;

        if (f2 < 0.0F) {
            f2 = 0.0F;
        }

        if (f2 > 1.0F) {
            f2 = 1.0F;
        }

        float f3 = (i >> 16 & 255) / 255.0F;
        float f4 = (i >> 8 & 255) / 255.0F;
        float f5 = (i & 255) / 255.0F;
        f3 *= f2 * 0.0F + 0.15F;
        f4 *= f2 * 0.0F + 0.15F;
        f5 *= f2 * 0.0F + 0.15F;
        return Vec3.createVectorHelper(f3, f4, f5);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isSkyColored() {
        return false;
    }

    @Override
    public boolean canRespawnHere() {
        return TragicConfig.allowSynapseRespawn;
    }

    @Override
    public boolean isSurfaceWorld() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getCloudHeight() {
        return 8.0F;
    }

    @Override
    public boolean canCoordinateBeSpawn(int p_76566_1_, int p_76566_2_) {
        return this.worldObj.getTopBlock(p_76566_1_, p_76566_2_)
            .getMaterial()
            .blocksMovement();
    }

    @Override
    public int getAverageGroundLevel() {
        return 50;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int p_76568_1_, int p_76568_2_) {
        return true;
    }

    @Override
    public String getDimensionName() {
        return "Synapse";
    }

    @Override
    public ChunkCoordinates getEntrancePortalLocation() {
        return new ChunkCoordinates(50, 50, 50);
    }
}
