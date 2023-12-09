package tragicneko.tragicmc.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import tragicneko.tragicmc.items.amulet.ItemAmulet;
import tragicneko.tragicmc.items.amulet.ItemAmulet.AmuletModifier;

import java.util.UUID;

import static tragicneko.tragicmc.TragicMC.rand;

public class AmuletHelper {

    private static IAttribute[] attributes = new IAttribute[] { AmuletModifier.jumpHeight, AmuletModifier.luck,
        AmuletModifier.reach, AmuletModifier.resistance, AmuletModifier.knockbackResistance,
        AmuletModifier.attackDamage, AmuletModifier.maxHealth, AmuletModifier.movementSpeed };

    public static UUID[] uuids = new UUID[] { UUID.fromString("8d325bc1-9b36-457f-a984-50cfed8331ed"),
        UUID.fromString("1f5a801c-312a-4b26-aa7b-3170e63df540"),
        UUID.fromString("829ea197-31bf-4360-912e-93f6775fedc1"),
        UUID.fromString("f1695e8a-87f1-4491-bc02-90f3671d299e"),
        UUID.fromString("b41f1b8d-a0e7-42ee-acb4-a0f9c198626f"),
        UUID.fromString("237ab9b5-459c-4dcb-bcb8-4e8068ce9135"),
        UUID.fromString("b06dedf7-fd45-442c-93b4-8fa70bc64d51"),
        UUID.fromString("1ab86156-baf5-4906-bb07-314a0e7faa59") };

    /**
     * Gets the highest amulet level of the 3 integers passed in, if par3 is 0, only checks for the first two integers
     * passed in
     *
     * @param par1
     * @param par2
     * @param par3
     * @return
     */
    public static byte getAmuletWithHighestLevel(byte par1, byte par2, byte par3) {
        if (par3 != 0) {
            if (par1 > par2 && par1 > par3) {
                return par1;
            }

            if (par2 > par1 && par2 > par3) {
                return par2;
            }

            if (par3 > par1 && par3 > par2) {
                return par3;
            }

            if (par1 == par2 && par1 == par3) {
                return par1;
            }
        } else {
            if (par1 > par2) {
                return par1;
            }

            if (par2 > par1) {
                return par2;
            }

            if (par1 == par2) {
                return par1;
            }
        }
        return (byte) ((par1 * par2 * par3) / 3);
    }

    /**
     * For getting the highest amulet level out of 2 amulets, will always return one of them if they are equal
     *
     * @param par1
     * @param par2
     * @return
     */
    public static byte getAmuletWithHighestLevel(byte par1, byte par2) {
        return getAmuletWithHighestLevel(par1, par2, (byte) 0);
    }

    /**
     * Returns stacked percentages if all 3 amulets equipped are of the same kind, pass in the 3 percentages of
     * successful effect use and returns the calculated
     * stacked efficiency of the 3, first integer should be the highest amulet level, third should be lowest
     *
     * @param par1
     * @param par2
     * @param par3
     * @return
     */
    public static double getStackedEfficiency(int par1, int par2, int par3) {
        double d0 = par1 / 100;
        double d1 = par2 / 100;
        double d2 = par3 / 100;

        double d3 = (1 - d0) * d1;
        double d4 = (1 - (d0 + d3)) * d2;

        return d0 + d3 + d4;
    }

    /**
     * Pass in the 3 amulets to check, returns an integer based on the amulets that are the same, 123 is all of them, 12
     * is the first two, etc., now does null
     * check automatically and returns proper integer
     *
     * @param amu1
     * @param amu2
     * @param amu3
     * @return
     */
    public static int getSameAmulets(ItemAmulet amu1, ItemAmulet amu2, ItemAmulet amu3) {
        if (amu1 == null) {
            if (amu2 != null && amu3 != null) {
                if (amu2 == amu3) {
                    return 23;
                }
            }
            return 0;
        }

        if (amu2 == null) {
            if (amu1 != null && amu3 != null) {
                if (amu1 == amu3) {
                    return 13;
                }
            }
            return 0;
        }

        if (amu3 == null) {
            if (amu1 != null && amu2 != null) {
                if (amu1 == amu2) {
                    return 12;
                }
            }
            return 0;
        }

        if (amu1 == amu2 && amu1 == amu3) {
            return 123;
        }

        if (amu1 == amu2 && amu1 != amu3) {
            return 12;
        }

        if (amu1 == amu3 && amu1 != amu2) {
            return 13;
        }

        if (amu2 == amu3 && amu1 != amu3) {
            return 23;
        }

        return 0;
    }

    public static byte getAmuletLevel(ItemStack stack) {
        return stack != null && stack.hasTagCompound()
            && stack.getTagCompound()
                .hasKey("amuletLevel") ? stack.getTagCompound()
                    .getByte("amuletLevel") : 0;
    }

    public static AttributeModifier getRandomModifier(IAttribute attr) {
        double d0 = 0.0D;
        UUID uuid = null;

        if (attr == SharedMonsterAttributes.attackDamage) {
            d0 = 0.25 + rand.nextInt(20) * 0.25;
            uuid = uuids[5];
        } else if (attr == SharedMonsterAttributes.knockbackResistance) {
            d0 = 0.2 + rand.nextInt(20) * 0.2;
            uuid = uuids[4];
        } else if (attr == SharedMonsterAttributes.maxHealth) {
            d0 = 0.5 + rand.nextInt(10) * 0.5;
            uuid = uuids[6];
        } else if (attr == SharedMonsterAttributes.movementSpeed) {
            d0 = 0.02 + rand.nextInt(20) * 0.02;
            uuid = uuids[7];
        } else if (attr == AmuletModifier.reach) {
            d0 = 0.05 + rand.nextInt(20) * 0.05;
            uuid = uuids[2];
        } else if (attr == AmuletModifier.jumpHeight) {
            d0 = 0.25 + rand.nextInt(10) * 0.25;
            uuid = uuids[0];
        } else if (attr == AmuletModifier.resistance) {
            d0 = 1 + rand.nextInt(10);
            uuid = uuids[3];
        } else if (attr == AmuletModifier.luck) {
            d0 = 0.01 + rand.nextInt(75) * 0.01;
            uuid = uuids[1];
        }

        d0 = attr.clampValue(d0);

        return uuid == null ? null : new AttributeModifier(uuid, attr.getAttributeUnlocalizedName(), d0, 0);
    }

    public static IAttribute getRandomAttribute() {
        return attributes[rand.nextInt(attributes.length)];
    }

    public static NBTTagCompound writeAttributeModifierToNBT(String atrName, AttributeModifier modif) {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setString("Name", modif.getName());
        nbttagcompound.setDouble("Amount", modif.getAmount());
        nbttagcompound.setInteger("Operation", modif.getOperation());
        nbttagcompound.setLong(
            "UUIDMost",
            modif.getID()
                .getMostSignificantBits());
        nbttagcompound.setLong(
            "UUIDLeast",
            modif.getID()
                .getLeastSignificantBits());
        nbttagcompound.setString("AttributeName", atrName);
        return nbttagcompound;
    }

    public static IAttribute getAttributeFromMap(BaseAttributeMap map, String s) {
        return getAttributeInstanceFromMap(map, s) != null ? getAttributeInstanceFromMap(map, s).getAttribute() : null;
    }

    public static IAttribute getAttributeFromEntity(EntityLivingBase entity, String s) {
        return getAttributeFromMap(entity.getAttributeMap(), s);
    }

    public static IAttributeInstance getAttributeInstanceFromMap(BaseAttributeMap map, String s) {
        return map != null ? map.getAttributeInstanceByName(s) : null;
    }
}
