package tragicneko.tragicmc.items.food;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionHelper;

public class ItemTentacle extends ItemFood {

    public ItemTentacle(int p_i45340_1_, boolean p_i45340_2_) {
        super(p_i45340_1_, p_i45340_2_);
        this.setPotionEffect(Potion.waterBreathing.id, 20, 0, 1.0F);
        this.setPotionEffect(PotionHelper.field_151423_m);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }
}
