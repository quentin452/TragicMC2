package tragicneko.tragicmc.items.food;

import net.minecraft.item.ItemFood;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.IIcon;

public class ItemExoticFruit extends ItemFood {

    public ItemExoticFruit(int p_i45340_1_, boolean p_i45340_2_) {
        super(p_i45340_1_, p_i45340_2_);
        this.setPotionEffect(Potion.regeneration.id, 4, 6, 1.0F);
        this.setAlwaysEdible();
        this.setPotionEffect(PotionHelper.ghastTearEffect);
    }

    public IIcon getFruitIcon() {
        return this.itemIcon;
    }
}
