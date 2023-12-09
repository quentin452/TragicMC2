package tragicneko.tragicmc.dimension;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;

public class TragicTeleporter extends Teleporter {

    protected final WorldServer worldServerInstance;
    protected final Random random;

    public TragicTeleporter(WorldServer par1WorldServer) {
        super(par1WorldServer);
        this.worldServerInstance = par1WorldServer;
        this.random = new Random(par1WorldServer.getSeed());
    }

    @Override
    public void placeInPortal(Entity par1Entity, double par2, double par4, double par6, float par8) {
        if (this.worldServerInstance.provider.dimensionId != 0) {
            int i = this.worldServerInstance.getSpawnPoint().posX;
            int k = this.worldServerInstance.getSpawnPoint().posZ;
            int j = this.worldServerInstance.provider instanceof TragicWorldProvider
                ? this.worldServerInstance.getTopSolidOrLiquidBlock(i, k)
                : this.worldServerInstance.getSpawnPoint().posY;
            byte b0 = 1;
            byte b1 = 0;

            boolean endFlag = this.worldServerInstance.provider.dimensionId == 1
                || this.worldServerInstance.provider.dimensionId == TragicConfig.synapseID;

            if (endFlag) {
                i = this.worldServerInstance.provider.getEntrancePortalLocation().posX;
                j = this.worldServerInstance.provider.getEntrancePortalLocation().posY;
                k = this.worldServerInstance.provider.getEntrancePortalLocation().posZ;
            } else if (par1Entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) par1Entity;
                ChunkCoordinates cc = player.getBedLocation(this.worldServerInstance.provider.dimensionId);

                if (cc != null) {
                    i = cc.posX;
                    j = cc.posY;
                    k = cc.posZ;
                }
            }

            Block spawnBlock = this.worldServerInstance.getBlock(i, j - 1, k);
            boolean lavaFlag = spawnBlock.getMaterial() == Material.lava || spawnBlock.getMaterial() == Material.lava;

            if (worldServerInstance.provider.dimensionId == TragicConfig.collisionID || lavaFlag
                || worldServerInstance.provider.dimensionId == TragicConfig.synapseID) {
                for (int l = -2; l <= 2; ++l) {
                    for (int i1 = -2; i1 <= 2; ++i1) {
                        for (int j1 = -1; j1 < 3; ++j1) {
                            int k1 = i + i1 * b0 + l * b1;
                            int l1 = j + j1;
                            int i2 = k + i1 * b1 - l * b0;
                            boolean flag = j1 < 0;
                            this.worldServerInstance.setBlock(
                                k1,
                                l1,
                                i2,
                                flag ? (endFlag ? Blocks.obsidian : TragicBlocks.DeadDirt) : Blocks.air);
                        }
                    }
                }

                if (lavaFlag) {
                    for (int x = -1; x < 2; x++) {
                        this.worldServerInstance
                            .setBlock(i + x, j, k - 3, endFlag ? Blocks.obsidian : TragicBlocks.DeadDirt);
                        this.worldServerInstance
                            .setBlock(i + x, j, k + 3, endFlag ? Blocks.obsidian : TragicBlocks.DeadDirt);
                        this.worldServerInstance
                            .setBlock(i + 3, j, k + x, endFlag ? Blocks.obsidian : TragicBlocks.DeadDirt);
                        this.worldServerInstance
                            .setBlock(i - 3, j, k + x, endFlag ? Blocks.obsidian : TragicBlocks.DeadDirt);
                    }
                }
            }

            par1Entity.setLocationAndAngles(i, (double) j + 1, k, par1Entity.rotationYaw, par1Entity.rotationPitch);
            par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
            par1Entity.fallDistance = 0.0F;
        } else {
            int i = worldServerInstance.getSpawnPoint().posX;
            int k = worldServerInstance.getSpawnPoint().posZ;
            int j = worldServerInstance.getTopSolidOrLiquidBlock(i, k);

            if (par1Entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) par1Entity;
                ChunkCoordinates cc = player.getBedLocation(0);

                if (cc != null) {
                    player.setLocationAndAngles(
                        cc.posX,
                        cc.posY + 1.5D,
                        cc.posZ,
                        player.rotationYaw,
                        player.rotationPitch);
                } else {
                    player.setLocationAndAngles(i, j, k, player.rotationYaw, player.rotationPitch);
                }

                player.motionX = player.motionY = player.motionZ = 0.0D;
                player.fallDistance = 0.0F;
            } else {
                par1Entity.setLocationAndAngles(i, j, k, par1Entity.rotationYaw, par1Entity.rotationPitch);
                par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
                par1Entity.fallDistance = 0.0F;
            }
        }

    }

}
