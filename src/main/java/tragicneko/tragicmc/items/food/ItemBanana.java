package tragicneko.tragicmc.items.food;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import tragicneko.tragicmc.entity.projectile.EntityBanana;

public class ItemBanana extends ItemFood {

    public ItemBanana(int p_i45340_1_, boolean p_i45340_2_) {
        super(p_i45340_1_, p_i45340_2_);
        this.setPotionEffect(Potion.jump.id, 15, 1, 1.0F);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        player.worldObj.spawnEntityInWorld(new EntityBanana(player.worldObj, player));

        if (!player.capabilities.isCreativeMode) {
            stack.stackSize--;
        }

        return super.onLeftClickEntity(stack, player, entity);

    }

}
