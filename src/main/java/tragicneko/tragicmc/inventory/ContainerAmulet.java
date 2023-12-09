package tragicneko.tragicmc.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import tragicneko.tragicmc.items.amulet.ItemAmulet;
import tragicneko.tragicmc.properties.PropertyAmulets;

public class ContainerAmulet extends Container {

    private static final int amuletStart = 1, amuletEnd = amuletStart + 2, invStart = amuletEnd + 1,
        invEnd = invStart + 25;

    private final InventoryAmulet invAmulet;

    public ContainerAmulet(EntityPlayer player, InventoryPlayer invPlayer, InventoryAmulet invAmulet) {

        PropertyAmulets amulets = PropertyAmulets.get(player);
        int slotsOpen = amulets.getSlotsOpen();
        this.invAmulet = invAmulet;

        addSlotToContainer(new SlotActiveAmulet(invAmulet, 0, 49, 15, player, false));

        boolean flag = true;

        if (slotsOpen > 2) {
            flag = false;
        }
        addSlotToContainer(new SlotActiveAmulet(invAmulet, 2, 113, 15, player, flag));

        if (slotsOpen > 1) {
            flag = false;
        }
        addSlotToContainer(new SlotActiveAmulet(invAmulet, 1, 81, -1, player, flag));

        int s = 3;
        int j = 45;

        // First row of slots
        for (int i = 0; i < 5; i++) {
            addSlotToContainer(new SlotInactiveAmulet(invAmulet, s++, j + (i * 16) + (i * 2), 43));
        }

        // Second row of slots
        for (int i = 0; i < 5; i++) {
            addSlotToContainer(new SlotInactiveAmulet(invAmulet, s++, j + (i * 16) + (i * 2), 61));
        }

        // Third row of slots
        for (int i = 0; i < 5; i++) {
            addSlotToContainer(new SlotInactiveAmulet(invAmulet, s++, j + (i * 16) + (i * 2), 79));
        }

        // Fourth row of slots
        for (int i = 0; i < 5; i++) {
            addSlotToContainer(new SlotInactiveAmulet(invAmulet, s++, j + (i * 16) + (i * 2), 97));
        }

        // Fifth row of slots
        for (int i = 0; i < 5; i++) {
            addSlotToContainer(new SlotInactiveAmulet(invAmulet, s++, j + (i * 16) + (i * 2), 115));
        }

        // For player hotbar
        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(invPlayer, i, 17 + (i * 16), 135));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1) {
        return true;
    }

    @Override
    protected boolean mergeItemStack(ItemStack par1ItemStack, int par2, int par3, boolean par4) {
        boolean flag1 = false;
        int k = par2;

        Slot slot;
        ItemStack itemstack1;

        if (par1ItemStack.stackSize > 0) {
            if (par4) {
                k = par3 - 1;
            } else {
                k = par2;
            }

            while (!par4 && k < par3 || par4 && k >= par2) {
                slot = (Slot) this.inventorySlots.get(k);
                itemstack1 = slot.getStack();

                if (itemstack1 == null && !(slot instanceof SlotActiveAmulet)
                    || itemstack1 == null && slot instanceof SlotActiveAmulet
                        && !((SlotActiveAmulet) slot).isLocked()) {
                    slot.putStack(par1ItemStack.copy());
                    slot.onSlotChanged();
                    par1ItemStack.stackSize = 0;
                    flag1 = true;
                    break;
                }

                if (par4) {
                    --k;
                } else {
                    ++k;
                }
            }
        } else {
            this.invAmulet.markDirty();
        }

        return flag1;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 < invStart || par2 > invEnd) {
                if (!this.mergeItemStack(itemstack1, 3, 28, true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else {
                if (itemstack1.getItem() instanceof ItemAmulet) {
                    if (!this.mergeItemStack(itemstack1, 0, 28, false)) {
                        return null;
                    }
                }
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }

}
