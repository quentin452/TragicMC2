package tragicneko.tragicmc.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import scala.tools.nsc.doc.model.Class;
import scala.tools.nsc.doc.model.Object;
import tragicneko.tragicmc.TragicMC;

import java.util.List;

public class ItemCorruptedEgg extends Item {

    public ItemCorruptedEgg() {
        super();
        this.setCreativeTab(TragicMC.Survival);
        this.setUnlocalizedName("tragicmc.corruptedEgg");
        this.setTextureName("tragicmc:CorruptedEgg");
    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4,
        int par5, int par6, int par7, float par8, float par9, float par10) {
        if (par3World.isRemote) {
            return true;
        } else {
            Block block = par3World.getBlock(par4, par5, par6);
            par4 += Facing.offsetsXForSide[par7];
            par5 += Facing.offsetsYForSide[par7];
            par6 += Facing.offsetsZForSide[par7];
            double d0 = 0.0D;

            if (par7 == 1 && block.getRenderType() == 11) {
                d0 = 0.5D;
            }

            Entity entity = spawnCreature(par3World, par4 + 0.5D, par5 + d0, par6 + 0.5D);

            if (entity != null) {
                if (entity instanceof EntityLivingBase && par1ItemStack.hasDisplayName()) {
                    ((EntityLiving) entity).setCustomNameTag(par1ItemStack.getDisplayName());
                }

                if (!par2EntityPlayer.capabilities.isCreativeMode) {
                    --par1ItemStack.stackSize;
                }
            }

            return true;
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par2World.isRemote) {
            return par1ItemStack;
        } else {
            MovingObjectPosition movingobjectposition = this
                .getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);

            if (movingobjectposition == null) {
                return par1ItemStack;
            } else {
                if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                    int i = movingobjectposition.blockX;
                    int j = movingobjectposition.blockY;
                    int k = movingobjectposition.blockZ;

                    if (!par2World.canMineBlock(par3EntityPlayer, i, j, k)) {
                        return par1ItemStack;
                    }

                    if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack)) {
                        return par1ItemStack;
                    }

                    if (par2World.getBlock(i, j, k) instanceof BlockLiquid) {
                        Entity entity = spawnCreature(par2World, i, j, k);

                        if (entity != null) {
                            if (entity instanceof EntityLivingBase && par1ItemStack.hasDisplayName()) {
                                ((EntityLiving) entity).setCustomNameTag(par1ItemStack.getDisplayName());
                            }

                            if (!par3EntityPlayer.capabilities.isCreativeMode) {
                                --par1ItemStack.stackSize;
                            }
                        }
                    }
                }

                return par1ItemStack;
            }
        }
    }

    public static Entity spawnCreature(World par0World, double par2, double par4, double par6) {
        List list = par0World.getBiomeGenForCoords((int) par2, (int) par6)
            .getSpawnableList(EnumCreatureType.monster);
        if (list.isEmpty()) {
            return null;
        } else {
            Entity entity = null;
            BiomeGenBase.SpawnListEntry entry = (SpawnListEntry) WeightedRandom.getRandomItem(itemRand, list);
            try {
                Class oclass = entry.entityClass;

                if (oclass != null) {
                    entity = (Entity) oclass.getConstructor(new Class[] { World.class })
                        .newInstance(new Object[] { par0World });
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            if (entity != null) {
                EntityLiving entityliving = (EntityLiving) entity;
                entity.setLocationAndAngles(
                    par2,
                    par4,
                    par6,
                    MathHelper.wrapAngleTo180_float(par0World.rand.nextFloat() * 360.0F),
                    0.0F);
                entityliving.rotationYawHead = entityliving.rotationYaw;
                entityliving.renderYawOffset = entityliving.rotationYaw;
                entityliving.onSpawnWithEgg((IEntityLivingData) null);
                par0World.spawnEntityInWorld(entity);
                entityliving.playLivingSound();
            }

            return entity;
        }
    }
}
