package tragicneko.tragicmc.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.boss.TragicBoss;
import tragicneko.tragicmc.util.TragicEntityList;
import tragicneko.tragicmc.util.TragicEntityList.EnumEggType;

import java.util.Iterator;
import java.util.List;

public class ItemMobEgg extends Item {

    @SideOnly(Side.CLIENT)
    private IIcon theIcon;

    private IIcon normalMobIcon;
    private IIcon normalMobIconOverlay;

    private IIcon petMobIcon;
    private IIcon petMobIconOverlay;

    private IIcon miniBossIcon;
    private IIcon miniBossIconOverlay;

    private IIcon bigBossIcon;
    private IIcon bigBossIconOverlay;

    public ItemMobEgg() {
        this.setHasSubtypes(true);
        this.setCreativeTab(TragicMC.Creative);
        this.setUnlocalizedName("tragicmc.mobEgg");
        this.setTextureName("tragicmc:SpawnEgg");
    }

    @Override
    public String getItemStackDisplayName(ItemStack par1ItemStack) {
        String s = ("" + StatCollector.translateToLocal(this.getUnlocalizedName() + ".name")).trim();
        String s1 = TragicEntityList.getStringFromID(par1ItemStack.getItemDamage());

        if (s1 != null) {
            s = s + " " + StatCollector.translateToLocal("entity." + s1 + ".name");
        }

        return s;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
        TragicEntityList.EntityEggInfo entityegginfo = (TragicEntityList.EntityEggInfo) TragicEntityList.entityEggs
            .get(Integer.valueOf(par1ItemStack.getItemDamage()));
        return entityegginfo != null ? (par2 == 0 ? entityegginfo.primaryColor : entityegginfo.secondaryColor)
            : 16777215;
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
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

            Entity entity = spawnCreature(
                par3World,
                par1ItemStack.getItemDamage(),
                par4 + 0.5D,
                par5 + d0,
                par6 + 0.5D);

            if (entity != null) {
                if (entity instanceof EntityLivingBase && par1ItemStack.hasDisplayName()) {
                    ((EntityLiving) entity).setCustomNameTag(par1ItemStack.getDisplayName());
                }

                if (par3World.difficultySetting.getDifficultyId() < 2 && par2EntityPlayer instanceof EntityPlayerMP
                    && entity instanceof TragicBoss
                    && !TragicConfig.allowEasyBosses) {
                    par2EntityPlayer.addChatMessage(
                        new ChatComponentText(
                            "Boss was spawned on too low of a difficulty. Raise difficulty level to fight it."));
                }

                if (!par2EntityPlayer.capabilities.isCreativeMode) {
                    --par1ItemStack.stackSize;
                }
            }

            return true;
        }
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
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
                        Entity entity = spawnCreature(par2World, par1ItemStack.getItemDamage(), i, j, k);

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

    /**
     * Spawns the creature specified by the egg's type in the location specified by the last three parameters.
     * Parameters: world, entityID, x, y, z.
     */
    public static Entity spawnCreature(World par0World, int par1, double par2, double par4, double par6) {
        if (!TragicEntityList.entityEggs.containsKey(Integer.valueOf(par1))) {
            return null;
        } else {
            Entity entity = null;

            for (int j = 0; j < 1; ++j) {
                entity = TragicEntityList.createEntityByID(par1, par0World);

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
                    if (!par0World.isRemote) par0World.spawnEntityInWorld(entity);
                }
            }

            return entity;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    /**
     * Gets an icon index based on an item's damage value and the given render pass
     */
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamageForRenderPass(int par1, int par2) {
        TragicEntityList.EntityEggInfo entityegginfo = (TragicEntityList.EntityEggInfo) TragicEntityList.entityEggs
            .get(Integer.valueOf(par1));

        if (entityegginfo != null && entityegginfo.eggType == EnumEggType.BOSS) {
            return par2 > 0 ? this.theIcon : super.getIconFromDamageForRenderPass(par1, par2);
        }

        if (entityegginfo != null && entityegginfo.eggType == EnumEggType.PET) {
            return par2 > 0 ? this.petMobIcon : this.petMobIconOverlay;
        }

        if (entityegginfo != null && entityegginfo.eggType == EnumEggType.MINIBOSS) {
            return par2 > 0 ? this.miniBossIcon : this.miniBossIconOverlay;
        }

        if (entityegginfo != null && entityegginfo.eggType == EnumEggType.ALPHA) {
            return par2 > 0 ? this.bigBossIcon : this.bigBossIconOverlay;
        }

        return par2 > 0 ? this.normalMobIconOverlay : this.normalMobIcon;
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_) {
        Iterator iterator = TragicEntityList.entityEggs.values()
            .iterator();

        while (iterator.hasNext()) {
            TragicEntityList.EntityEggInfo entityegginfo = (TragicEntityList.EntityEggInfo) iterator.next();

            p_150895_3_.add(new ItemStack(p_150895_1_, 1, entityegginfo.spawnedID));

        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        super.registerIcons(par1IconRegister);
        this.theIcon = par1IconRegister.registerIcon(this.getIconString() + "_overlay");
        this.normalMobIcon = par1IconRegister.registerIcon(this.getIconString() + "2");
        this.normalMobIconOverlay = par1IconRegister.registerIcon(this.getIconString() + "2_overlay");
        this.petMobIcon = par1IconRegister.registerIcon(this.getIconString() + "3");
        this.petMobIconOverlay = par1IconRegister.registerIcon(this.getIconString() + "3_overlay");
        this.miniBossIcon = par1IconRegister.registerIcon(this.getIconString() + "4");
        this.miniBossIconOverlay = par1IconRegister.registerIcon(this.getIconString() + "4_overlay");
        this.bigBossIcon = par1IconRegister.registerIcon(this.getIconString() + "5");
        this.bigBossIconOverlay = par1IconRegister.registerIcon(this.getIconString() + "5_overlay");
    }
}
