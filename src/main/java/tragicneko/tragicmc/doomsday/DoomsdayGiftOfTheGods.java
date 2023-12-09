package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class DoomsdayGiftOfTheGods extends Doomsday {

    public DoomsdayGiftOfTheGods(int id) {
        super(id, EnumDoomType.WORLDSHAPER);
    }

    @Override
    public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        super.doInitialEffects(effect, doom, player, crucMoment);
        effect.utilityList.add(0, new ItemStack(Items.diamond));
        effect.utilityList.add(1, new ItemStack(TragicItems.CooldownDefuse));
        effect.utilityList.add(2, new ItemStack(TragicItems.Ruby));
        effect.utilityList.add(3, new ItemStack(Items.iron_ingot));
        effect.utilityList.add(4, new ItemStack(Items.coal));
        effect.utilityList.add(5, new ItemStack(TragicItems.Sapphire));
        effect.utilityList.add(6, new ItemStack(Items.golden_apple));
        effect.utilityList.add(7, new ItemStack(Items.golden_apple, 1, 1));
        effect.utilityList.add(8, new ItemStack(Items.emerald));
        effect.utilityList.add(9, new ItemStack(Items.gold_ingot));
        effect.utilityList.add(10, new ItemStack(TragicItems.ObsidianOrb));
        effect.utilityList.add(11, new ItemStack(TragicItems.Sushi));
        effect.utilityList.add(12, new ItemStack(TragicItems.GoldenSushi));
        effect.utilityList.add(13, new ItemStack(Blocks.cake));
        effect.utilityList.add(14, new ItemStack(Blocks.pumpkin));
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        ItemStack stack = (ItemStack) effect.utilityList.get(rand.nextInt(effect.utilityList.size()));
        int amt = crucMoment ? 48 + rand.nextInt(16) : 16 + rand.nextInt(32);
        double y = player.posY - WorldHelper.getDistanceToGround(player);

        for (int i = 0; i < amt; i++) {
            EntityItem item = new EntityItem(player.worldObj);
            item.setEntityItemStack(stack.copy());
            item.setPosition(
                player.posX + (rand.nextDouble() - rand.nextDouble()) * 8.0,
                y + 18 + rand.nextInt(12),
                player.posZ + (rand.nextDouble() - rand.nextDouble()) * 8.0);
            player.worldObj.spawnEntityInWorld(item);
        }
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
        doom.increaseDoom(-doom.getCurrentDoom());
    }

}
