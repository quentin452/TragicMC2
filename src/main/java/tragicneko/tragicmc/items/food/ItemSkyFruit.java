package tragicneko.tragicmc.items.food;

import net.minecraft.item.ItemFood;
import net.minecraft.util.IIcon;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;

public class ItemSkyFruit extends ItemFood {

    public ItemSkyFruit(int p_i45340_1_, boolean p_i45340_2_) {
        super(p_i45340_1_, p_i45340_2_);
        if (TragicConfig.allowFlight) this.setPotionEffect(TragicPotion.Flight.id, 120, 0, 1.0F);
        this.setAlwaysEdible();
    }

    public IIcon getFruitIcon() {
        return this.itemIcon;
    }
}
