package tragicneko.tragicmc.items.food;

import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionHelper;

public class ItemIceCream extends ItemFood {

    public ItemIceCream(int p_i45340_1_, boolean p_i45340_2_) {
        super(p_i45340_1_, p_i45340_2_);
        this.setPotionEffect(Potion.moveSpeed.id, 120, 1, 1.0F);
        this.setContainerItem(Items.bowl);
        this.setPotionEffect(PotionHelper.sugarEffect);
    }
}
