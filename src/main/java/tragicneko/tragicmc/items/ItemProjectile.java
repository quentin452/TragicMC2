package tragicneko.tragicmc.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.projectile.*;

import java.util.List;

public class ItemProjectile extends Item {

    private static final String[] subNames = new String[] { "rock", "lavaRock", "pumpkinbomb", "largePumpkinbomb",
        "poisonBarb", "nekoRocket", "nekoStickyBomb", "nekoClusterBomb", "nekoMiniBomb", "solarBomb", "spiritCast",
        "spore", "banana", "largeRock", "icicle", "timeBomb", "starShard", "darkLightning", "pitchBlack", "darkEnergy",
        "darkMortor", "webBomb", "crystalMortor", "overlordMortor", "ireEnergy" };

    private static final String[] textureNames = new String[] { "Rock", "LavaRock", "Pumpkinbomb", "LargePumpkinbomb",
        "PoisonBarb", "NekoRocket", "NekoStickyBomb", "NekoClusterBomb", "NekoMiniBomb", "SolarBomb", "SpiritCast",
        "Spore", "Banana", "LargeRock", "Icicle", "TimeBomb", "StarShard", "DarkLightning", "PitchBlack", "DarkEnergy",
        "DarkMortor", "WebBomb", "CrystalMortor", "OverlordMortor", "IreEnergy" };

    private static IIcon[] iconArray = new IIcon[subNames.length];

    public ItemProjectile() {
        super();
        this.setCreativeTab(TragicMC.Creative);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setUnlocalizedName("tragicmc.projectile");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (world.isRemote) return stack;

        int i = stack.getItemDamage();

        float f = 1.0F;
        float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
        float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
        double d0 = player.prevPosX + (player.posX - player.prevPosX) * f;
        double d1 = player.prevPosY + (player.posY - player.prevPosY) * f
            + (player.worldObj.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight()); // isRemote
                                                                                                                         // check
                                                                                                                         // to
                                                                                                                         // revert
                                                                                                                         // changes
                                                                                                                         // to
                                                                                                                         // ray
                                                                                                                         // trace
                                                                                                                         // position
                                                                                                                         // due
                                                                                                                         // to
                                                                                                                         // adding
                                                                                                                         // the
                                                                                                                         // eye
                                                                                                                         // height
                                                                                                                         // clientside
                                                                                                                         // and
                                                                                                                         // player
                                                                                                                         // yOffset
                                                                                                                         // differences
        double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * f;
        Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 6.0D;

        Vec3 vec31 = vec3.addVector(f7 * d3, f6 * d3, f8 * d3);
        MovingObjectPosition mop = world.func_147447_a(vec3, vec31, true, false, true);

        double x = mop.hitVec.xCoord - player.posX;
        double y = mop.hitVec.yCoord - player.posY;
        double z = mop.hitVec.zCoord - player.posZ;
        float f9 = 0.12F * 6.0F * 0.9275F;

        Entity entity = null;

        switch (i) {
            case 0:
            case 1:
                entity = new EntityThrowingRock(world, player, i == 1);
                break;
            case 2:
                entity = new EntityPumpkinbomb(world, player);
                break;
            case 3:
                entity = new EntityLargePumpkinbomb(world, player);
                break;
            case 4:
                entity = new EntityPoisonBarb(world, player, x, y, z);
                break;
            case 5:
                entity = new EntityNekoRocket(world, player, x, y, z);
                break;
            case 6:
                entity = new EntityNekoStickyBomb(world, player);
                break;
            case 7:
                entity = new EntityNekoClusterBomb(world, player);
                break;
            case 8:
                entity = new EntityNekoMiniBomb(world, player);
                break;
            case 9:
                entity = new EntitySolarBomb(world, player, x, y, z);
                break;
            case 10:
                entity = new EntitySpiritCast(world, player, x, y, z);
                break;
            case 11:
                entity = new EntitySpore(world, player, x, y, z);
                break;
            case 12:
                entity = new EntityBanana(world, player);
                break;
            case 13:
                entity = new EntityLargeRock(world, player, x, y, z);
                entity.motionY += f9;
                break;
            case 14:
                entity = new EntityIcicle(world, player, x, y, z);
                break;
            case 15:
                entity = new EntityTimeBomb(world, player, x, y, z);
                break;
            case 16:
                entity = new EntityStarShard(world, player, x, y, z);
                break;
            case 17:
                entity = new EntityDarkLightning(world, player, x, y, z);
                break;
            case 18:
                entity = new EntityPitchBlack(world, player);
                break;
            case 19:
                entity = new EntityDarkEnergy(world, player, x, y, z);
                break;
            case 20:
                entity = new EntityDarkMortor(world, player, x, y, z);
                entity.motionY += f9;
                break;
            case 21:
                entity = new EntityWebBomb(world, player, x, y, z);
                entity.motionY += f9;
                break;
            case 22:
                entity = new EntityCrystalMortor(world, player, x, y, z);
                entity.motionY += f9;
                break;
            case 23:
                entity = new EntityOverlordMortor(world, player, x, y, z);
                entity.motionX += itemRand.nextFloat() - itemRand.nextFloat();
                entity.motionY += itemRand.nextFloat() - itemRand.nextFloat();
                entity.motionZ += itemRand.nextFloat() - itemRand.nextFloat();
                break;
            case 24:
                entity = new EntityIreEnergy(world, player, x, y, z);
                break;
        }

        if (entity != null) {
            if (entity instanceof EntityProjectile) entity.posY += player.getEyeHeight();
            world.spawnEntityInWorld(entity);
        }

        if (!player.capabilities.isCreativeMode) --stack.stackSize;

        return stack;
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < subNames.length; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public IIcon getIconFromDamage(int damage) {
        if (damage >= ItemProjectile.iconArray.length) damage = ItemProjectile.iconArray.length - 1;
        return ItemProjectile.iconArray[damage];
    }

    @Override
    public void registerIcons(IIconRegister register) {
        for (int i = 0; i < subNames.length; i++) {
            ItemProjectile.iconArray[i] = register.registerIcon("tragicmc:Projectile" + textureNames[i]);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        int damage = itemstack.getItemDamage();
        if (damage >= subNames.length) damage = subNames.length - 1;

        return getUnlocalizedName() + "." + subNames[damage];
    }
}
