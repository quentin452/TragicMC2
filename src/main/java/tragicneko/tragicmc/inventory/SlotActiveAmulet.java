package tragicneko.tragicmc.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import tragicneko.tragicmc.items.amulet.ItemAmulet;

public class SlotActiveAmulet extends Slot {

    private final boolean isLocked;

    public SlotActiveAmulet(IInventory par1iInventory, int par2, int par3, int par4, EntityPlayer player,
        boolean locked) {
        super(par1iInventory, par2, par3, par4);
        this.isLocked = locked;
    }

    @Override
    public boolean isItemValid(ItemStack itemstack) {
        if (this.isLocked) {
            return false;
        }
        return itemstack.getItem() instanceof ItemAmulet;
    }

    public boolean isLocked() {
        return this.isLocked;
    }

}
