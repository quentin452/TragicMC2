package tragicneko.tragicmc.items.amulet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.properties.PropertyAmulets;

import static tragicneko.tragicmc.TragicMC.rand;

public class AmuletBlacksmith extends ItemAmulet {

    public AmuletBlacksmith() {
        super("Blacksmith", EnumAmuletType.NORMAL, 0x949494, 0x696969);
    }

    @Override
    public void onAmuletUpdate(final PropertyAmulets amu, final EntityPlayer player, final World world, final byte slot,
        final byte level) {
        if (TragicConfig.amuBlacksmith) {
            boolean flag = false;

            if (level == 1 && player.ticksExisted % 60 == 0) {
                flag = true;
            } else if (level == 2 && player.ticksExisted % 40 == 0) {
                flag = true;
            } else if (level == 3 && player.ticksExisted % 20 == 0) {
                flag = true;
            }

            if (flag && !world.isRemote) {
                ItemStack[] playerInv = player.inventory.mainInventory;

                for (int i = 0; i < 4 * level; i++) {
                    ItemStack stack = playerInv[rand.nextInt(playerInv.length)];

                    if (stack != null && stack.isItemDamaged()
                        && stack.getItem()
                            .isRepairable()) {
                        Item item = stack.getItem();

                        if (item.getDamage(stack) - level <= 0) {
                            item.setDamage(stack, 0);
                            break;
                        }

                        if (item.getDamage(stack) >= item.getMaxDamage(stack) / 8) {
                            if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem() == stack)
                                continue;

                            item.setDamage(stack, item.getDamage(stack) - level);
                            this.damageAmulet(amu, slot, level);
                        }

                        break;
                    }
                }
            }
        }
    }

}
