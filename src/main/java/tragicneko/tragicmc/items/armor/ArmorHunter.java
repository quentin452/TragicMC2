package tragicneko.tragicmc.items.armor;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import tragicneko.tragicmc.doomsday.Doomsday;

public class ArmorHunter extends TragicArmor {

    public ArmorHunter(ArmorMaterial material, int armorType, Doomsday dday) {
        super(material, armorType, dday);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return slot == 2 ? "tragicmc:textures/armor/Hunter2.png" : "tragicmc:textures/armor/Hunter1.png";
    }
}
