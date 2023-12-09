package tragicneko.tragicmc.items.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import tragicneko.tragicmc.doomsday.Doomsday;

public class ArmorTungsten extends TragicArmor {

    public ArmorTungsten(ArmorMaterial material, int armorType, Doomsday dday) {
        super(material, armorType, dday);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        switch (armorType) {
            case 0:
                this.itemIcon = par1IconRegister.registerIcon("tragicmc:TungstenHelm");
                break;
            case 1:
                this.itemIcon = par1IconRegister.registerIcon("tragicmc:TungstenPlate");
                break;
            case 2:
                this.itemIcon = par1IconRegister.registerIcon("tragicmc:TungstenLegs");
                break;
            case 3:
                this.itemIcon = par1IconRegister.registerIcon("tragicmc:TungstenBoots");
                break;
        }
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return slot == 2 ? "tragicmc:textures/armor/Tungsten2.png" : "tragicmc:textures/armor/Tungsten1.png";
    }
}
