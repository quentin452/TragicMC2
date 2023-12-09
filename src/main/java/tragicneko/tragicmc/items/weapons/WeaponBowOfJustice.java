package tragicneko.tragicmc.items.weapons;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import tragicneko.tragicmc.TragicMC;

public class WeaponBowOfJustice extends ItemBow {

    public WeaponBowOfJustice() {
        super();
        this.setMaxDamage(1);
        this.setCreativeTab(TragicMC.Creative);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.epic;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack, int passes) {
        return true;
    }

    @Override
    public int getItemEnchantability() {
        return 100;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled()) {
            return event.result;
        }

        if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(Items.arrow)) {
            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        }

        return par1ItemStack;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer,
        int par4) {
        int j = this.getMaxItemUseDuration(par1ItemStack) - par4;

        ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, j);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled()) {
            return;
        }

        boolean flag = par3EntityPlayer.capabilities.isCreativeMode;

        float f = 1.0F;

        EntityArrow entityarrow = new EntityArrow(par2World, par3EntityPlayer, f * 2.0F);

        entityarrow.setDamage(Math.abs(Integer.MAX_VALUE / 200));
        entityarrow.motionX *= 1.1;
        entityarrow.motionZ *= 1.1;

        entityarrow.setIsCritical(true);
        entityarrow.setKnockbackStrength(5);
        entityarrow.setFire(100);

        par2World.playSoundAtEntity(
            par3EntityPlayer,
            "random.bow",
            1.0F,
            1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

        entityarrow.canBePickedUp = 2;

        if (!par2World.isRemote) {
            par2World.spawnEntityInWorld(entityarrow);
            if (!flag) par1ItemStack.stackSize--;
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag) {
        if (world.isRemote) return;

        if (!stack.isItemEnchanted()) {
            stack.addEnchantment(Enchantment.looting, 10);
        }
    }
}
