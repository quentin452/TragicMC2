package tragicneko.tragicmc.items;

import net.minecraft.item.Item;
import tragicneko.tragicmc.TragicMC;

public class ItemTalisman extends Item {

    public ItemTalisman() {
        this.setCreativeTab(TragicMC.Survival);
        this.setMaxDamage(200);
        this.setMaxStackSize(1);
    }
}
