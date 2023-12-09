package tragicneko.tragicmc.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.EntityStatue;

import java.util.List;

public class ItemStatue extends Item {

    public static String[] subNames = new String[] { "apis", "kitsune", "deathReaper", "timeController", "yeti",
        "polaris", "jarra", "kragul", "magmox", "megaCryse", "stinKing", "stinQueen", "greaterStin", "voxStellarum",
        "enyvil", "claymation", "aegar", "overlord", "overlordCombat", "overlordCocoon" };
    private static String[] textureNames = new String[] { "Apis", "Kitsune", "DeathReaper", "TimeController", "Yeti",
        "Polaris", "Jarra", "Kragul", "Magmox", "MegaCryse", "StinKing", "StinQueen", "GreaterStin", "VoxStellarum",
        "Enyvil", "Claymation", "Aegar", "Overlord", "OverlordCombat", "OverlordCocoon" };
    private IIcon[] iconArray = new IIcon[subNames.length];

    public ItemStatue() {
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setCreativeTab(TragicMC.Survival);
        this.setUnlocalizedName("tragicmc.mobStatue");
        this.setMaxStackSize(1);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < subNames.length; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public IIcon getIconFromDamage(int damage) {
        return damage < this.iconArray.length ? this.iconArray[damage] : this.iconArray[this.iconArray.length - 1];
    }

    @Override
    public void registerIcons(IIconRegister register) {
        for (int i = 0; i < subNames.length; i++) {
            this.iconArray[i] = register.registerIcon("tragicmc:Statue" + textureNames[i]);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        int damage = itemstack.getItemDamage();
        return damage < subNames.length ? getUnlocalizedName() + "." + subNames[damage]
            : getUnlocalizedName() + "." + subNames[subNames.length - 1];
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        String s = ("" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
        if (!stack.hasTagCompound() || !stack.getTagCompound()
            .hasKey("textureID")
            && !stack.getTagCompound()
                .hasKey("isAnimated"))
            return s;
        return s + " "
            + StatCollector.translateToLocal(this.getExtraStringName(stack))
            + (this.getAnimated(stack) ? StatCollector.translateToLocal("tragicmc.mobTexture.animated") : "");
    }

    private String getExtraStringName(ItemStack stack) {
        int var = getTextureIDFromStack(stack);

        switch (var) {
            case 1:
                return "tragicmc.mobTexture.iron";
            case 2:
                return "tragicmc.mobTexture.gold";
            case 3:
                return "tragicmc.mobTexture.diamond";
            case 4:
                return "tragicmc.mobTexture.stone";
            case 5:
                return "tragicmc.mobTexture.wood";
            case 6:
                return "tragicmc.mobTexture.emerald";
            case 7:
                return "tragicmc.mobTexture.leaf";
            case 8:
                return "tragicmc.mobTexture.tungsten";
            case 9:
                return "tragicmc.mobTexture.ruby";
            case 10:
                return "tragicmc.mobTexture.sapphire";
            case 11:
                return "tragicmc.mobTexture.redstone";
            case 12:
                return "tragicmc.mobTexture.coal";
            case 13:
                return "tragicmc.mobTexture.lapis";
            case 14:
                return "tragicmc.mobTexture.netherrack";
            case 15:
                return "tragicmc.mobTexture.ender";
            default:
                return "";
        }
    }

    private int getTextureIDFromStack(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound()
            .hasKey("textureID") ? stack.getTagCompound()
                .getInteger("textureID") : 0;
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {
        par2List.add(EnumChatFormatting.GOLD + "It's a statue of a mob!");
        par2List.add("");
        par2List.add("Can be animated with a Synapse Crystal.");
        par2List.add("Can have it's texture changed with various blocks/items.");
        par2List.add("Can reset it's texture with Blaze Powder.");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player, false);

        if (mop == null) return stack;

        int x = mop.blockX;
        int y = mop.blockY;
        int z = mop.blockZ;

        EntityStatue statue;

        if (World.doesBlockHaveSolidTopSurface(world, x, y, z) && !world.isRemote) {
            statue = new EntityStatue(world);

            double x2 = x + 0.5D;
            double y2 = y;
            double z2 = z + 0.5D;
            float rotation = ((MathHelper.floor_float((player.rotationYaw * 8.0F / 360.0F) + 0.5F)) * 45.0F) + 180F;

            switch (mop.sideHit) {
                case 0: // bottom
                    y2 -= 1.0D;
                    break;
                case 1: // top
                    y2 += 1.0D;
                    break;
                case 2: // east
                    z2 -= 1.0D;
                    break;
                case 3: // west
                    z2 += 1.0D;
                    break;
                case 4: // north
                    x2 -= 1.0D;
                    break;
                case 5: // south
                    x2 += 1.0D;
                    break;
            }

            statue.setPosition(x2, y2, z2);
            statue.setRotation(rotation);
            if (!world.getCollidingBoundingBoxes(statue, statue.boundingBox)
                .isEmpty() || world.isAnyLiquid(statue.boundingBox)) return stack;
            statue.setMobID(stack.getItemDamage() % subNames.length);
            statue.setTextureID(this.getTextureIDFromStack(stack));
            statue.setAnimated(this.getAnimated(stack));
            world.spawnEntityInWorld(statue);
            if (!player.capabilities.isCreativeMode) stack.stackSize--;
        }

        return stack;
    }

    private boolean getAnimated(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound()
            .hasKey("isAnimated")
                ? stack.getTagCompound()
                    .getInteger("isAnimated") == 1
                : false;
    }
}
