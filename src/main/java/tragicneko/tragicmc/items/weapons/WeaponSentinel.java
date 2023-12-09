package tragicneko.tragicmc.items.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;

public class WeaponSentinel extends TragicWeapon {

    public WeaponSentinel(ToolMaterial material, Doomsday dday) {
        super(material, dday);
        this.doomsday2 = Doomsday.GuardiansCall;
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int par, boolean flag) {
        if (!stack.hasTagCompound()) stack.stackTagCompound = new NBTTagCompound();
        if (!stack.stackTagCompound.hasKey("tragicLoreRarity"))
            stack.stackTagCompound.setInteger("tragicLoreRarity", 3);
        super.onUpdate(stack, world, entity, par, flag);
    }
}
