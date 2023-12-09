package tragicneko.tragicmc.items.weapons;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;

public class WeaponSilentHellraiser extends TragicWeapon {

    public WeaponSilentHellraiser(ToolMaterial material, Doomsday dday) {
        super(material, dday);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(ItemStack stack, int pass) {
        return false;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int par, boolean flag) {
        super.onUpdate(stack, world, entity, par, flag);
        if (stack.stackTagCompound != null) stack.stackTagCompound.setTag("ench", new NBTTagList());
    }
}
