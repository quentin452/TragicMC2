package tragicneko.tragicmc.items.food;

import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

public class ItemBoneMarrow extends ItemFood {

    public ItemBoneMarrow(int p_i45340_1_, boolean p_i45340_2_) {
        super(p_i45340_1_, p_i45340_2_);
        this.setPotionEffect(Potion.wither.id, 10, 0, 0.5F);
        this.setContainerItem(Items.bowl);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.drink;
    }

}
