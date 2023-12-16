package tragicneko.tragicmc.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.network.MessageSound;
import tragicneko.tragicmc.util.EntityDropHelper.EntityDrop;
import tragicneko.tragicmc.util.LoreHelper;
import tragicneko.tragicmc.util.TragicEntityList;

import java.util.List;

public class ItemChallenge extends Item {

    private IIcon scrollIcon, completeIcon;

    public static EntityDrop[] badRewards = new EntityDrop[] { new EntityDrop(15, Items.coal),
        new EntityDrop(5, Items.flint_and_steel), new EntityDrop(5, Items.string), new EntityDrop(5, Items.stick),
        new EntityDrop(5, Items.bone), new EntityDrop(15, Items.bread), new EntityDrop(5, Items.book),
        new EntityDrop(5, Items.bowl), new EntityDrop(5, TragicItems.Ash), new EntityDrop(15, TragicItems.Banana),
        new EntityDrop(10, TragicItems.Sushi), new EntityDrop(5, TragicItems.BoneMarrow),
        new EntityDrop(30, TragicItems.ChallengeScroll) };

    public static EntityDrop[] cheapRewards = new EntityDrop[] { new EntityDrop(1, Items.diamond),
        new EntityDrop(1, Items.emerald), new EntityDrop(5, Items.apple), new EntityDrop(10, Items.iron_ingot),
        new EntityDrop(5, Items.gold_ingot), new EntityDrop(15, TragicItems.Tungsten),
        new EntityDrop(25, TragicItems.BloodSacrifice), new EntityDrop(25, TragicItems.NourishmentSacrifice),
        new EntityDrop(15, TragicItems.RedMercury), new EntityDrop(20, TragicItems.Quicksilver),
        new EntityDrop(5, TragicItems.QuicksilverIngot), new EntityDrop(5, Blocks.obsidian),
        new EntityDrop(15, TragicItems.Deathglow), new EntityDrop(15, TragicItems.Honeydrop),
        new EntityDrop(10, TragicItems.SkyFruit), new EntityDrop(5, TragicItems.Gloopii),
        new EntityDrop(1, getSpawnEggsMiniBoss()), new EntityDrop(15, TragicItems.ChallengeScroll) };

    public static EntityDrop[] rewards = new EntityDrop[] { new EntityDrop(10, Items.diamond),
        new EntityDrop(5, Items.emerald), new EntityDrop(15, TragicItems.AwakeningStone),
        new EntityDrop(30, TragicItems.AmuletRelease), new EntityDrop(25, TragicItems.DoomConsume),
        new EntityDrop(20, TragicItems.CooldownDefuse), new EntityDrop(25, TragicItems.Ruby),
        new EntityDrop(5, Items.iron_ingot), new EntityDrop(25, TragicItems.Sapphire),
        new EntityDrop(5, Items.gold_ingot),
        new EntityDrop(20, new ItemStack(Items.golden_apple, 1, 0), new ItemStack(Items.golden_apple, 1, 1)),
        new EntityDrop(5, TragicItems.CryingObsidianOrb), new EntityDrop(5, TragicItems.BleedingObsidianOrb),
        new EntityDrop(5, TragicItems.DyingObsidianOrb), new EntityDrop(5, TragicItems.ObsidianOrb),
        new EntityDrop(15, TragicItems.GoldenSushi), new EntityDrop(10, TragicItems.Talisman),
        new EntityDrop(5, TragicItems.BloodSacrifice), new EntityDrop(5, TragicItems.NourishmentSacrifice),
        new EntityDrop(15, TragicItems.Deathglow), new EntityDrop(35, TragicItems.Honeydrop),
        new EntityDrop(20, TragicItems.SkyFruit), new EntityDrop(10, TragicItems.Gloopii),
        new EntityDrop(1, getSpawnEggsBoss()), new EntityDrop(3, getSpawnEggsMiniBoss()),
        new EntityDrop(3, getEpicWeapons()), new EntityDrop(3, getEpicWeapons()) };

    private static String[] subNames = new String[] { "inactive", "inProgress", "complete" };

    public ItemChallenge() {
        this.setCreativeTab(TragicMC.Survival);
        this.setMaxDamage(0);
        this.maxStackSize = 1;
        this.setHasSubtypes(true);
        this.setUnlocalizedName("tragicmc.challengeItem");
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        if (stack.getItemDamage() == 0) return EnumRarity.common;
        if (stack.getItemDamage() == 250) return EnumRarity.epic;
        if (!stack.hasTagCompound() || !stack.stackTagCompound.hasKey("challengeID")
            || Challenge.getChallengeFromID(stack.stackTagCompound.getInteger("challengeID")) == null)
            return EnumRarity.common;
        switch (Challenge.getChallengeFromID(stack.stackTagCompound.getInteger("challengeID")).difficulty) {
            case 3:
                return EnumRarity.epic;
            case 2:
                return EnumRarity.rare;
            case 1:
                return EnumRarity.uncommon;
            case 0:
            default:
                return EnumRarity.common;
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        int var = itemstack.getItemDamage() == 250 ? 2 : (itemstack.getItemDamage() != 0 ? 1 : 0);
        return getUnlocalizedName() + "." + subNames[var];
    }

    @Override
    public IIcon getIconFromDamage(int damage) {
        if (damage == 0) return this.scrollIcon;
        if (damage == 250) return this.completeIcon;
        return this.itemIcon;
    }

    @Override
    public void registerIcons(IIconRegister register) {
        super.registerIcons(register);
        this.scrollIcon = register.registerIcon("tragicmc:ChallengeScroll");
        this.completeIcon = register.registerIcon("tragicmc:ChallengeComplete");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (world.isRemote) return stack;

        if (stack.getItemDamage() == 0) {
            try {
                Challenge challenge = Challenge
                    .getChallengeFromID(itemRand.nextInt(Challenge.challengeList.length - 1) + 1);
                while (challenge == null) {
                    challenge = Challenge.getChallengeFromID(itemRand.nextInt(Challenge.challengeList.length - 1) + 1);
                }
                stack.setItemDamage(challenge.challengeID);
                if (!stack.hasTagCompound()) stack.stackTagCompound = new NBTTagCompound();
                stack.stackTagCompound.setInteger("challengeID", challenge.challengeID);
                player.addChatMessage(new ChatComponentText("Challenge accepted!"));
                if (player instanceof EntityPlayerMP) TragicMC.net
                    .sendTo(new MessageSound("tragicmc:random.challengestart", 1.0F, 1.0F), (EntityPlayerMP) player);
                if (TragicConfig.allowAchievements && player instanceof EntityPlayerMP)
                    player.triggerAchievement(TragicAchievements.challengeScroll);
            } catch (Exception e) {
                TragicMC.logError(
                    "Challenge item errored while retreiving a Challenge. Report this! ItemStack was " + stack,
                    e);
                return stack;
            }
        } else if (stack.getItemDamage() == 250) {
            player.addChatMessage(new ChatComponentText("Challenge completed, have a reward!"));
            Challenge challenge = Challenge.getChallengeFromID(stack.stackTagCompound.getInteger("challengeID"));
            int extra = itemRand.nextInt((challenge.difficulty + 1) * 2) + 1;
            ItemStack reward = null;

            for (int i = 0; i < extra && i < 5; i++) {
                EntityItem item = new EntityItem(world);

                try {
                    reward = challenge.difficulty > 2
                        ? ((EntityDrop) WeightedRandom.getRandomItem(itemRand, rewards)).getStack()
                        : (challenge.difficulty > 0
                            ? ((EntityDrop) WeightedRandom.getRandomItem(itemRand, cheapRewards)).getStack()
                            : ((EntityDrop) WeightedRandom.getRandomItem(itemRand, badRewards)).getStack());
                    item.setEntityItemStack(reward.copy());
                    item.setPosition(
                        player.posX + itemRand.nextDouble() - itemRand.nextDouble(),
                        player.posY + 0.6D,
                        player.posZ + itemRand.nextDouble() - itemRand.nextDouble());
                    world.spawnEntityInWorld(item);
                } catch (Exception e) {
                    TragicMC.logError("Challenge errored while rewarding, silently catching error and continuing", e);
                    continue;
                }
                if (i > 3 && itemRand.nextInt(4) == 0) break;
            }
            stack.stackSize--;
        } else {
            if (stack.hasTagCompound()) player.addChatMessage(
                new ChatComponentText(
                    Challenge.getNameFromID(stack.stackTagCompound.getInteger("challengeID")) + " is in progress!"));
        }

        return stack;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {
        if (stack.getItemDamage() == 250) {
            if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("challengeID")) {
                Challenge challenge = Challenge.getChallengeFromID(stack.stackTagCompound.getInteger("challengeID"));
                if (challenge == null) return;
                EnumChatFormatting format = challenge.difficulty == 1 ? EnumChatFormatting.AQUA
                    : (challenge.difficulty == 2 ? EnumChatFormatting.BLUE
                        : (challenge.difficulty == 3 ? EnumChatFormatting.GOLD : EnumChatFormatting.WHITE));
                par2List.add("Challenge: " + format + Challenge.getNameFromID(challenge.challengeID));
                String diff = challenge.difficulty == 0 ? "Easy"
                    : (challenge.difficulty == 1 ? "Medium" : (challenge.difficulty == 2 ? "Hard" : "Harsh"));
                par2List.add("Difficulty: " + format + diff);
            }
            par2List.add(EnumChatFormatting.GOLD + "Challenge complete!");
            par2List.add(EnumChatFormatting.WHITE + "What are you doing reading this, get your reward!");
        } else if (stack.getItemDamage() == 0) {
            par2List.add(EnumChatFormatting.WHITE + "An inactive Challenge Scroll.");
            par2List.add(EnumChatFormatting.RESET + "Right-Click to start a Challenge!");
        } else if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("challengeID")) {
            Challenge challenge = Challenge.getChallengeFromID(stack.stackTagCompound.getInteger("challengeID"));
            if (challenge == null) return;
            EnumChatFormatting format = challenge.difficulty == 1 ? EnumChatFormatting.AQUA
                : (challenge.difficulty == 2 ? EnumChatFormatting.BLUE
                    : (challenge.difficulty == 3 ? EnumChatFormatting.GOLD : EnumChatFormatting.WHITE));
            par2List.add("Challenge: " + format + Challenge.getNameFromID(challenge.challengeID));
            String diff = challenge.difficulty == 0 ? "Easy"
                : (challenge.difficulty == 1 ? "Medium" : (challenge.difficulty == 2 ? "Hard" : "Harsh"));
            par2List.add("Difficulty: " + format + diff);
            LoreHelper.splitDesc(par2List, Challenge.getDesc(challenge.challengeID), 32, EnumChatFormatting.WHITE);
            par2List.add(
                "Progress: " + stack.stackTagCompound.getInteger("challengeProgress") + "/ " + challenge.requirement);
            if (challenge.isLocationBased && stack.stackTagCompound.hasKey("challengeLocation")) par2List
                .add("Proper location: " + (stack.stackTagCompound.getBoolean("challengeLocation") ? "Yes" : "No"));
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag) {
        if (world.isRemote || stack.getItemDamage() == 0 || stack.getItemDamage() == 250 || entity == null) return;

        if (!stack.hasTagCompound()) stack.stackTagCompound = new NBTTagCompound();
        if (!stack.stackTagCompound.hasKey("challengeID"))
            stack.stackTagCompound.setInteger("challengeID", stack.getItemDamage());

        Challenge challenge = Challenge.getChallengeFromID(stack.stackTagCompound.getInteger("challengeID"));

        if (entity instanceof EntityPlayer && challenge != null) {
            EntityPlayer player = (EntityPlayer) entity;
            ItemStack[] inv = player.inventory.mainInventory;
            ItemStack invStack;
            int amt = 0;

            if (challenge.isItemChallenge) {
                for (ItemStack element : inv) {
                    invStack = element;
                    if (invStack != null && challenge.challengeItem != null) {
                        boolean flag2 = !challenge.ignoresMeta
                            && invStack.getItemDamage() == challenge.challengeItem.getItemDamage()
                            || challenge.ignoresMeta;
                        if (invStack.getItem() == challenge.challengeItem.getItem() && flag2) {
                            amt += invStack.stackSize;
                        }
                    }
                }

                stack.stackTagCompound.setInteger("challengeProgress", amt);
            } else {
                if (!stack.stackTagCompound.hasKey("challengeProgress"))
                    stack.stackTagCompound.setInteger("challengeProgress", 0);

                if (challenge.isTimed) {
                    if (player.ticksExisted % 20 == 0) {
                        int pow = stack.stackTagCompound.getInteger("challengeProgress");

                        if (challenge.isMobRush && !player.worldObj.isDaytime() || !challenge.isMobRush) {
                            stack.stackTagCompound.setInteger("challengeProgress", ++pow);
                        }
                    }
                }
            }
        }

        if (stack.stackTagCompound.hasKey("challengeProgress") && challenge != null
            && stack.stackTagCompound.getInteger("challengeProgress") == 0
            && challenge.isLocationBased
            && stack.stackTagCompound.hasKey("challengeLocation")
            && stack.stackTagCompound.getBoolean("challengeLocation")) {
            if (!challenge.isBlockChallenge && !challenge.isItemChallenge
                && !challenge.isMobRush
                && !challenge.isTimed) {
                stack.stackTagCompound.setInteger("challengeProgress", 1);
            }
        }

        if (stack.stackTagCompound.hasKey("challengeProgress") && challenge != null
            && stack.stackTagCompound.getInteger("challengeProgress") >= challenge.requirement) {
            if (challenge.isLocationBased) {
                if (stack.stackTagCompound.hasKey("challengeLocation")
                    && stack.stackTagCompound.getBoolean("challengeLocation")) stack.setItemDamage(250);
            } else {
                stack.setItemDamage(250);
                if (entity instanceof EntityPlayerMP) TragicMC.net
                    .sendTo(new MessageSound("tragicmc:random.challengedone", 1.0F, 1.0F), (EntityPlayerMP) entity);
            }
        }
    }

    public static ItemStack[] getSpawnEggsMiniBoss() {
        ItemStack[] stacks = new ItemStack[] {
            new ItemStack(
                TragicItems.SpawnEgg,
                1,
                TragicEntityList.stringToIDMapping.get("TragicMC.Jarra") == null ? 0
                    : TragicEntityList.stringToIDMapping.get("TragicMC.Jarra")),
            new ItemStack(
                TragicItems.SpawnEgg,
                1,
                TragicEntityList.stringToIDMapping.get("TragicMC.Magmox") == null ? 0
                    : TragicEntityList.stringToIDMapping.get("TragicMC.Magmox")),
            new ItemStack(
                TragicItems.SpawnEgg,
                1,
                TragicEntityList.stringToIDMapping.get("TragicMC.Kragul") == null ? 0
                    : TragicEntityList.stringToIDMapping.get("TragicMC.Kragul")),
            new ItemStack(
                TragicItems.SpawnEgg,
                1,
                TragicEntityList.stringToIDMapping.get("TragicMC.MegaCryse") == null ? 0
                    : TragicEntityList.stringToIDMapping.get("TragicMC.MegaCryse")),
            new ItemStack(
                TragicItems.SpawnEgg,
                1,
                TragicEntityList.stringToIDMapping.get("TragicMC.StinKing") == null ? 0
                    : TragicEntityList.stringToIDMapping.get("TragicMC.StinKing")),
            new ItemStack(
                TragicItems.SpawnEgg,
                1,
                TragicEntityList.stringToIDMapping.get("TragicMC.GreaterStin") == null ? 0
                    : TragicEntityList.stringToIDMapping.get("TragicMC.GreaterStin")),
            new ItemStack(
                TragicItems.SpawnEgg,
                1,
                TragicEntityList.stringToIDMapping.get("TragicMC.StinQueen") == null ? 0
                    : TragicEntityList.stringToIDMapping.get("TragicMC.StinQueen")),
            new ItemStack(
                TragicItems.SpawnEgg,
                1,
                TragicEntityList.stringToIDMapping.get("TragicMC.VoxStellarum") == null ? 0
                    : TragicEntityList.stringToIDMapping.get("TragicMC.VoxStellarum")),
            new ItemStack(
                TragicItems.SpawnEgg,
                1,
                TragicEntityList.stringToIDMapping.get("TragicMC.VolatileFusea") == null ? 0
                    : TragicEntityList.stringToIDMapping.get("TragicMC.VolatileFusea")) };
        return stacks;
    }

    public static ItemStack[] getSpawnEggsBoss() {
        ItemStack[] stacks = new ItemStack[] {
            new ItemStack(
                TragicItems.SpawnEgg,
                1,
                TragicEntityList.stringToIDMapping.get("TragicMC.Apis") == null ? 0
                    : TragicEntityList.stringToIDMapping.get("TragicMC.Apis")),
            new ItemStack(
                TragicItems.SpawnEgg,
                1,
                TragicEntityList.stringToIDMapping.get("TragicMC.Polaris") == null ? 0
                    : TragicEntityList.stringToIDMapping.get("TragicMC.Polaris")),
            new ItemStack(
                TragicItems.SpawnEgg,
                1,
                TragicEntityList.stringToIDMapping.get("TragicMC.Yeti") == null ? 0
                    : TragicEntityList.stringToIDMapping.get("TragicMC.Yeti")),
            new ItemStack(
                TragicItems.SpawnEgg,
                1,
                TragicEntityList.stringToIDMapping.get("TragicMC.TimeController") == null ? 0
                    : TragicEntityList.stringToIDMapping.get("TragicMC.TimeController")),
            new ItemStack(
                TragicItems.SpawnEgg,
                1,
                TragicEntityList.stringToIDMapping.get("TragicMC.Kitsune") == null ? 0
                    : TragicEntityList.stringToIDMapping.get("TragicMC.Kitsune")),
            new ItemStack(
                TragicItems.SpawnEgg,
                1,
                TragicEntityList.stringToIDMapping.get("TragicMC.DeathReaper") == null ? 0
                    : TragicEntityList.stringToIDMapping.get("TragicMC.Enyvil")),
            new ItemStack(
                TragicItems.SpawnEgg,
                1,
                TragicEntityList.stringToIDMapping.get("TragicMC.Enyvil") == null ? 0
                    : TragicEntityList.stringToIDMapping.get("TragicMC.Kitsune")),
            new ItemStack(
                TragicItems.SpawnEgg,
                1,
                TragicEntityList.stringToIDMapping.get("TragicMC.Claymation") == null ? 0
                    : TragicEntityList.stringToIDMapping.get("TragicMC.Claymation")) };
        return stacks;
    }

    public static ItemStack[] getAmulets() {
        return new ItemStack[] { new ItemStack(TragicItems.ApisAmulet),
            new ItemStack(TragicItems.CreeperAmulet), new ItemStack(TragicItems.BlacksmithAmulet),
            new ItemStack(TragicItems.KitsuneAmulet), new ItemStack(TragicItems.ZombieAmulet),
            new ItemStack(TragicItems.SkeletonAmulet), new ItemStack(TragicItems.SunkenAmulet),
            new ItemStack(TragicItems.PeaceAmulet), new ItemStack(TragicItems.ChickenAmulet),
            new ItemStack(TragicItems.ClaymationAmulet), new ItemStack(TragicItems.YetiAmulet)};
    }

    public static ItemStack[] getEpicWeapons() {
        return new ItemStack[] { new ItemStack(TragicItems.Titan), new ItemStack(TragicItems.Paranoia),
            new ItemStack(TragicItems.Butcher), new ItemStack(TragicItems.Thardus),
            new ItemStack(TragicItems.DragonFang), new ItemStack(TragicItems.Splinter),
            new ItemStack(TragicItems.SilentHellraiser) };
    }
}
