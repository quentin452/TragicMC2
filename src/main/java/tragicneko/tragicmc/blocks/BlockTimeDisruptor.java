package tragicneko.tragicmc.blocks;

import static net.minecraft.init.Blocks.air;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.blocks.tileentity.TileEntityTimeDisruptor;
import tragicneko.tragicmc.entity.boss.EntityApis;
import tragicneko.tragicmc.entity.boss.EntityClaymation;
import tragicneko.tragicmc.entity.boss.EntityDeathReaper;
import tragicneko.tragicmc.entity.boss.EntityEnyvil;
import tragicneko.tragicmc.entity.boss.EntityKitsune;
import tragicneko.tragicmc.entity.boss.EntityPolaris;
import tragicneko.tragicmc.entity.boss.EntityTimeController;
import tragicneko.tragicmc.entity.boss.EntityYeti;
import tragicneko.tragicmc.entity.boss.TragicBoss;

public class BlockTimeDisruptor extends BlockContainer {

    public BlockTimeDisruptor() {
        super(Material.iron);
        this.setCreativeTab(TragicMC.Survival);
        this.setBlockTextureName("tragicmc:TimeDisruptor");
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityTimeDisruptor();
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);

        Block block = null;
        if (world.getBlock(x, y - 1, z) == Blocks.quartz_block && world.getBlock(x, y - 2, z) == Blocks.quartz_block)
            block = Blocks.quartz_block;
        else if (world.getBlock(x, y - 1, z) == TragicBlocks.DarkenedQuartz
            && world.getBlock(x, y - 2, z) == TragicBlocks.DarkenedQuartz) block = TragicBlocks.DarkenedQuartz;
        else if (world.getBlock(x, y - 1, z) == Blocks.hardened_clay
            && world.getBlock(x, y - 2, z) == Blocks.hardened_clay) block = Blocks.hardened_clay;
        else if (world.getBlock(x, y - 1, z) == Blocks.sandstone && world.getBlock(x, y - 2, z) == Blocks.sandstone)
            block = Blocks.sandstone;
        else if (world.getBlock(x, y - 1, z) == Blocks.ice && world.getBlock(x, y - 2, z) == Blocks.ice)
            block = Blocks.ice;
        else if (world.getBlock(x, y - 1, z) == TragicBlocks.StarCrystal
            && world.getBlock(x, y - 2, z) == TragicBlocks.StarCrystal) block = TragicBlocks.StarCrystal;
        else if (world.getBlock(x, y - 1, z) == TragicBlocks.BoneBlock
            && world.getBlock(x, y - 2, z) == TragicBlocks.BoneBlock) block = TragicBlocks.BoneBlock;
        else if (world.getBlock(x, y - 1, z) == Blocks.nether_brick
            && world.getBlock(x, y - 2, z) == Blocks.nether_brick) block = Blocks.nether_brick;

        if (block == null) return;
        boolean flag = world.getBlock(x - 1, y - 1, z) == block && world.getBlock(x + 1, y - 1, z) == block
            && world.getBlock(x, y - 1, z - 1) == block
            && world.getBlock(x, y - 1, z + 1) == block;

        if (flag) {
            TragicBoss boss = null;
            if (block == Blocks.quartz_block && TragicConfig.allowTimeController)
                boss = new EntityTimeController(world);
            else if (block == TragicBlocks.DarkenedQuartz && TragicConfig.allowEnyvil) boss = new EntityEnyvil(world);
            else if (block == Blocks.hardened_clay && TragicConfig.allowClaymation) boss = new EntityClaymation(world);
            else if (block == Blocks.sandstone && TragicConfig.allowApis) boss = new EntityApis(world);
            else if (block == Blocks.ice && TragicConfig.allowEmpariah) boss = new EntityYeti(world);
            else if (block == TragicBlocks.StarCrystal && TragicConfig.allowPolaris) boss = new EntityPolaris(world);
            else if (block == TragicBlocks.BoneBlock && TragicConfig.allowSkultar) boss = new EntityDeathReaper(world);
            else if (block == Blocks.nether_brick && TragicConfig.allowKitsunakuma) boss = new EntityKitsune(world);

            if (boss == null) return;

            world.setBlock(x, y, z, air, 0, 2);
            world.setBlock(x, y - 1, z, air, 0, 2);
            world.setBlock(x, y - 2, z, air, 0, 2);
            world.setBlock(x - 1, y - 1, z, air, 0, 2);
            world.setBlock(x + 1, y - 1, z, air, 0, 2);
            world.setBlock(x, y - 1, z - 1, air, 0, 2);
            world.setBlock(x, y - 1, z + 1, air, 0, 2);

            boss.setLocationAndAngles(x + 0.5D, y - 1.95D, z + 0.5D, 0.0F, 0.0F);
            EntityPlayer player = boss.worldObj.getClosestVulnerablePlayerToEntity(boss, 16.0D);
            if (player != null) boss.setTarget(player);
            world.spawnEntityInWorld(boss);

            for (int l = 0; l < 120; ++l) world.spawnParticle(
                "hugeexplosion",
                x + world.rand.nextDouble(),
                y - 2 + world.rand.nextDouble() * 3.9D,
                z + world.rand.nextDouble(),
                0.0D,
                0.0D,
                0.0D);

            world.notifyBlockChange(x, y, z, air);
            world.notifyBlockChange(x, y - 1, z, air);
            world.notifyBlockChange(x, y - 2, z, air);
            world.notifyBlockChange(x - 1, y - 1, z, air);
            world.notifyBlockChange(x + 1, y - 1, z, air);
            world.notifyBlockChange(x, y - 1, z - 1, air);
            world.notifyBlockChange(x, y - 1, z + 1, air);
        }
    }
}
