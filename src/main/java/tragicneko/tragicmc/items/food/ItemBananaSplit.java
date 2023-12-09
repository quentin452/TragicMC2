package tragicneko.tragicmc.items.food;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemBananaSplit extends ItemFood {

    public ItemBananaSplit(int p_i45340_1_, boolean p_i45340_2_) {
        super(p_i45340_1_, p_i45340_2_);
        this.setPotionEffect(Potion.jump.id, 30, 1, 1.0F);
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 600, 0));
        return super.onEaten(stack, world, player);
    }

}
