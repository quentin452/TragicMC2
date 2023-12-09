package tragicneko.tragicmc.items;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import scala.tools.nsc.doc.model.Object;

public class RecipeWeapons extends ShapelessOreRecipe {

    public RecipeWeapons(ItemStack output, Object... items) {
        super(output, items);
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting crafting) {
        ItemStack stack = this.getRecipeOutput()
            .copy();
        int loreRarity = 0;
        String lore = null;
        boolean flag = false;

        for (int i = 0; i < crafting.getSizeInventory(); ++i) {
            ItemStack stack2 = crafting.getStackInSlot(i);

            if (stack2 != null && stack2.hasTagCompound()) {
                if (stack2.stackTagCompound.hasKey("tragicLoreRarity")) {
                    int rarity = stack2.stackTagCompound.getInteger("tragicLoreRarity");
                    if (rarity < 1) rarity = 1;

                    if (!flag) {
                        loreRarity = rarity;
                        if (stack2.stackTagCompound.hasKey("tragicLoreDesc"))
                            lore = stack2.stackTagCompound.getString("tragicLoreDesc");
                        flag = true;
                    } else {
                        loreRarity += rarity;
                        break;
                    }
                }
            }
        }

        if (loreRarity > 3) loreRarity = 3;
        if (!stack.hasTagCompound()) stack.stackTagCompound = new NBTTagCompound();
        stack.stackTagCompound.setInteger("tragicLoreRarity", loreRarity);
        if (lore != null) stack.stackTagCompound.setString("tragicLoreDesc", lore);

        return stack;
    }
}
