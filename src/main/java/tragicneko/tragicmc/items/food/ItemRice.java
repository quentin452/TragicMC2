package tragicneko.tragicmc.items.food;

import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ItemRice extends ItemFood {

    public ItemRice(int p_i45340_1_, boolean p_i45340_2_) {
        super(p_i45340_1_, p_i45340_2_);
        this.setContainerItem(Items.bowl);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 16;
    }
}
