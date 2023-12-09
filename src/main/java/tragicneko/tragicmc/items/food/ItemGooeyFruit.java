package tragicneko.tragicmc.items.food;

import net.minecraft.item.ItemFood;
import net.minecraft.potion.PotionHelper;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;

public class ItemGooeyFruit extends ItemFood {

    public ItemGooeyFruit(int p_i45340_1_, boolean p_i45340_2_) {
        super(p_i45340_1_, p_i45340_2_);
        if (TragicConfig.allowResurrection) this.setPotionEffect(TragicPotion.Resurrection.id, 60, 1, 1.0F);
        this.setAlwaysEdible();
        this.setPotionEffect(PotionHelper.fermentedSpiderEyeEffect);
    }

}
