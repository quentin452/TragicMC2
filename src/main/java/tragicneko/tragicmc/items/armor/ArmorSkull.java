package tragicneko.tragicmc.items.armor;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import tragicneko.tragicmc.doomsday.Doomsday;

public class ArmorSkull extends TragicArmor {

    public ArmorSkull(ArmorMaterial material, int armorType, Doomsday dday) {
        super(material, armorType, dday);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return slot == 2 ? "tragicmc:textures/armor/Skull2.png" : "tragicmc:textures/armor/Skull1.png";
    }
}
