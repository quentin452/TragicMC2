package tragicneko.tragicmc.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import tragicneko.tragicmc.items.amulet.ItemAmulet;

public class SlotInactiveAmulet extends Slot {

    public SlotInactiveAmulet(IInventory par1iInventory, int par2, int par3, int par4) {
        super(par1iInventory, par2, par3, par4);
    }

    @Override
    public boolean isItemValid(ItemStack itemstack) {
        return itemstack.getItem() instanceof ItemAmulet;
    }

}
