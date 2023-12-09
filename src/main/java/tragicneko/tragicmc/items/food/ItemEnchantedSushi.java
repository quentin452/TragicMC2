package tragicneko.tragicmc.items.food;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;

public class ItemEnchantedSushi extends ItemFood {

    public ItemEnchantedSushi(int p_i45340_1_, boolean p_i45340_2_) {
        super(p_i45340_1_, p_i45340_2_);
        if (TragicConfig.allowImmunity) this.setPotionEffect(TragicPotion.Immunity.id, 120, 0, 1.0F);
        this.setAlwaysEdible();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.epic;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 16;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack, int pass) {
        return pass == 0;
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        player.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 2400, 4));
        player.addPotionEffect(new PotionEffect(Potion.field_76443_y.id, 2400, 1));
        player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 2400, 2));
        player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 2400, 0));
        if (TragicConfig.allowClarity) player.addPotionEffect(new PotionEffect(TragicPotion.Clarity.id, 2400, 1));
        if (TragicConfig.allowInvulnerability)
            player.addPotionEffect(new PotionEffect(TragicPotion.Invulnerability.id, 60));
        if (TragicConfig.allowAchievements && player instanceof EntityPlayerMP)
            player.triggerAchievement(TragicAchievements.goldenSushi);

        return super.onEaten(stack, world, player);
    }

}
