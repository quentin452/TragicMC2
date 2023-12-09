package tragicneko.tragicmc.items;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCore;
import tragicneko.tragicmc.entity.boss.EntityApis;
import tragicneko.tragicmc.entity.boss.EntityKitsune;
import tragicneko.tragicmc.entity.boss.EntityTimeController;
import tragicneko.tragicmc.entity.boss.TragicBoss;
import tragicneko.tragicmc.entity.miniboss.TragicMiniBoss;
import tragicneko.tragicmc.entity.mob.*;

public class Challenge {

    public static final Challenge[] challengeList = new Challenge[248];

    public static final Challenge zombieKills = new Challenge(1, false, 50, EntityZombie.class, false).setDifficulty(1);
    public static final Challenge collectWheat = new Challenge(2, true, 48, new ItemStack(Items.wheat));
    public static final Challenge stayAlive = new Challenge(3, false, 1200).setTimed();
    public static final Challenge stayAlive3 = new Challenge(4, false, 3600).setDifficulty(1)
        .setTimed();
    public static final Challenge survive = new Challenge(5, false, 180).setDifficulty(1)
        .setMobRushChallenge()
        .setTimed();
    public static final Challenge endermanKills = new Challenge(6, false, 30, EntityEnderman.class, false)
        .setDifficulty(2);
    public static final Challenge bossKills = new Challenge(7, false, 10, TragicBoss.class, false).setDifficulty(3);
    public static final Challenge collectLeather = new Challenge(8, true, 32, new ItemStack(Items.leather));
    public static final Challenge stayAlive7 = new Challenge(9, false, 8400).setDifficulty(2)
        .setTimed();
    public static final Challenge witherKill = new Challenge(10, false, 1, EntityWither.class, false).setDifficulty(2);
    public static final Challenge findEmerald = new Challenge(11, true, 1, new ItemStack(Items.emerald))
        .setDifficulty(1);
    public static final Challenge findDiamonds = new Challenge(12, true, 6, new ItemStack(Items.diamond))
        .setDifficulty(1);
    public static final Challenge collectAsh = new Challenge(13, true, 64, new ItemStack(TragicItems.Ash));
    public static final Challenge miniBossKills = new Challenge(14, false, 25, TragicMiniBoss.class, false)
        .setDifficulty(2);
    public static final Challenge stinKills = new Challenge(15, false, 25, EntityStin.class, false).setDifficulty(1);
    public static final Challenge tragicNekoKills = new Challenge(16, false, 10, EntityTragicNeko.class, false)
        .setDifficulty(1);
    public static final Challenge collectGoldenApples = new Challenge(
        17,
        true,
        16,
        new ItemStack(Items.golden_apple, 1, 1)).setDifficulty(3);
    public static final Challenge findWisp = new Challenge(18, true, 1, EntityWisp.class, true);
    public static final Challenge findApis = new Challenge(19, true, 1, EntityApis.class, true).setDifficulty(1);
    public static final Challenge findIronGolem = new Challenge(20, true, 1, EntityIronGolem.class, true)
        .setDifficulty(1);
    public static final Challenge findSnowBlock = new Challenge(21, true, 1, Blocks.snow).setDifficulty(1);
    public static final Challenge iceBucketChallenge = new Challenge(22, true, 1, new ItemStack(Items.bucket))
        .setLocationBased(BiomeGenBase.icePlains)
        .setDifficulty(3);
    public static final Challenge findRecord11 = new Challenge(23, true, 1, new ItemStack(Items.record_11))
        .setDifficulty(2);
    public static final Challenge collectFish = new Challenge(24, true, 64, new ItemStack(Items.fish)).setDifficulty(1);
    public static final Challenge collectGunpowder = new Challenge(25, true, 64, new ItemStack(Items.gunpowder))
        .setDifficulty(2);
    public static final Challenge collectRedSand = new Challenge(26, true, 64, new ItemStack(Blocks.sand, 1, 1))
        .setDifficulty(2);
    public static final Challenge bossKill = new Challenge(27, false, 1, TragicBoss.class, false).setDifficulty(2);
    public static final Challenge survive2 = new Challenge(28, false, 360).setDifficulty(2)
        .setMobRushChallenge()
        .setTimed();
    public static final Challenge collectCooldownDefuse = new Challenge(
        29,
        true,
        3,
        new ItemStack(TragicItems.CooldownDefuse)).setDifficulty(1);
    public static final Challenge findStarlitCliffs = new Challenge(30, true, 1, TragicBiome.StarlitCliffs)
        .setDifficulty(3);
    public static final Challenge findMesa = new Challenge(31, true, 1, BiomeGenBase.mesa).setDifficulty(2);
    public static final Challenge findMushroomIsland = new Challenge(32, true, 1, BiomeGenBase.mushroomIsland)
        .setDifficulty(2);
    public static final Challenge findBedrock = new Challenge(33, true, 1, Blocks.bedrock).setDifficulty(1);
    public static final Challenge findWater = new Challenge(34, true, 1, Blocks.water);
    public static final Challenge findAshenGrass = new Challenge(35, true, 1, TragicBlocks.AshenGrass).setDifficulty(1);
    public static final Challenge findRedMushroomBlock = new Challenge(36, true, 1, Blocks.red_mushroom_block)
        .setDifficulty(1);
    public static final Challenge findSand = new Challenge(37, true, 1, Blocks.sand);
    public static final Challenge findMossyCobblestone = new Challenge(38, true, 1, Blocks.mossy_cobblestone)
        .setDifficulty(2);
    public static final Challenge findIronOre = new Challenge(39, true, 1, Blocks.iron_ore).setDifficulty(1);
    public static final Challenge findIceMountains = new Challenge(40, true, 1, BiomeGenBase.iceMountains)
        .setDifficulty(2);
    public static final Challenge findDeepOcean = new Challenge(41, true, 1, BiomeGenBase.deepOcean).setDifficulty(1);
    public static final Challenge findNorVox = new Challenge(42, true, 1, EntityNorVox.class, true).setDifficulty(1);
    public static final Challenge killInklings = new Challenge(43, false, 50, EntityInkling.class, false)
        .setDifficulty(1);
    public static final Challenge findHorses = new Challenge(44, true, 1, EntityHorse.class, true).setDifficulty(1);
    public static final Challenge killTragicMobs = new Challenge(45, false, 100, TragicMob.class, false)
        .setDifficulty(2);
    public static final Challenge findOcelots = new Challenge(46, true, 1, EntityOcelot.class, true).setDifficulty(2);
    public static final Challenge findVillagers = new Challenge(47, true, 1, EntityVillager.class, true)
        .setDifficulty(1);
    public static final Challenge killGhasts = new Challenge(48, false, 15, EntityGhast.class, false);
    public static final Challenge findSlime = new Challenge(49, true, 1, EntitySlime.class, true);
    public static final Challenge killZombiePigmen = new Challenge(50, false, 100, EntityPigZombie.class, false)
        .setDifficulty(2);
    public static final Challenge survive3 = new Challenge(51, false, 720).setDifficulty(3)
        .setMobRushChallenge()
        .setTimed();
    public static final Challenge cliffDiamond = new Challenge(52, true, 1, new ItemStack(Items.diamond))
        .setLocationBased(BiomeGenBase.extremeHills)
        .setDifficulty(1);
    public static final Challenge killVillagers = new Challenge(53, false, 5, EntityVillager.class, false)
        .setDifficulty(1);
    public static final Challenge collectEmeralds = new Challenge(54, true, 16, new ItemStack(Items.emerald))
        .setDifficulty(1);
    public static final Challenge collectIronBlocks = new Challenge(55, true, 64, new ItemStack(Blocks.iron_block))
        .setDifficulty(2);
    public static final Challenge killPirah = new Challenge(56, false, 25, EntityPirah.class, false).setDifficulty(1);
    public static final Challenge killSkeletons = new Challenge(57, false, 50, EntitySkeleton.class, false)
        .setDifficulty(2);
    public static final Challenge findClay = new Challenge(58, true, 1, Blocks.clay);
    public static final Challenge findLeaves = new Challenge(59, true, 1, Blocks.leaves);
    public static final Challenge killKitsune = new Challenge(60, false, 1, EntityKitsune.class, false)
        .setDifficulty(2);
    public static final Challenge killRagr = new Challenge(61, false, 3, EntityRagr.class, false).setDifficulty(2);
    public static final Challenge killWisps = new Challenge(62, false, 10, EntityWisp.class, false).setDifficulty(1);
    public static final Challenge goFishing = new Challenge(63, true, 1, new ItemStack(Items.fishing_rod))
        .setLocationBased(BiomeGenBase.river);
    public static final Challenge goFishing2 = new Challenge(64, true, 1, new ItemStack(Items.fishing_rod))
        .setLocationBased(BiomeGenBase.beach);
    public static final Challenge findLightChestplate = new Challenge(
        65,
        true,
        1,
        new ItemStack(TragicItems.LightPlate)).setDifficulty(2);
    public static final Challenge findAwakeningStone = new Challenge(
        66,
        true,
        1,
        new ItemStack(TragicItems.AwakeningStone)).setDifficulty(2);
    public static final Challenge findDimensionalKey = new Challenge(
        67,
        true,
        1,
        new ItemStack(TragicItems.DimensionalKey)).setDifficulty(3)
            .setIgnoresMeta();
    public static final Challenge findSavanna = new Challenge(68, true, 1, BiomeGenBase.savanna);
    public static final Challenge collectPotatoes = new Challenge(69, true, 64, new ItemStack(Items.potato))
        .setDifficulty(1);
    public static final Challenge collectStatues = new Challenge(70, true, 10, new ItemStack(TragicItems.MobStatue))
        .setDifficulty(3)
        .setIgnoresMeta();
    public static final Challenge findAbomination = new Challenge(71, true, 1, EntityAbomination.class, true)
        .setDifficulty(1);
    public static final Challenge findPlague = new Challenge(72, true, 1, EntityPlague.class, true);
    public static final Challenge findTimeController = new Challenge(73, true, 1, EntityTimeController.class, true)
        .setDifficulty(2);
    public static final Challenge collectSmoothNetherrack = new Challenge(74, true, 32, TragicBlocks.SmoothNetherrack);
    public static final Challenge collectRedFlowers = new Challenge(
        75,
        true,
        32,
        new ItemStack(Blocks.red_flower, 1, 0)).setDifficulty(1);
    public static final Challenge goFishing3 = new Challenge(76, true, 1, new ItemStack(Items.fishing_rod))
        .setLocationBased(BiomeGenBase.frozenRiver)
        .setDifficulty(2);
    public static final Challenge findMobStatue = new Challenge(77, true, 1, new ItemStack(TragicItems.MobStatue, 1, 0))
        .setDifficulty(1)
        .setIgnoresMeta();
    public static final Challenge collectTNT = new Challenge(78, true, 64, new ItemStack(Blocks.tnt)).setDifficulty(3);
    public static final Challenge collectEnderPearls = new Challenge(79, true, 16, new ItemStack(Items.ender_pearl))
        .setDifficulty(1);
    public static final Challenge findEpicLore = new Challenge(80, true, 1, 3).setDifficulty(2);
    public static final Challenge findRareLore = new Challenge(81, true, 1, 2).setDifficulty(1);
    public static final Challenge collectEpicLore = new Challenge(82, true, 5, 3).setDifficulty(3);
    public static final Challenge collectUncommonLore = new Challenge(83, true, 10, 1).setDifficulty(1);
    public static final Challenge wearDiamondArmor = new Challenge(
        84,
        true,
        4,
        new ItemStack[] { new ItemStack(Items.diamond_helmet), new ItemStack(Items.diamond_chestplate),
            new ItemStack(Items.diamond_leggings), new ItemStack(Items.diamond_boots) }).setDifficulty(2);
    public static final Challenge wearPumpkin = new Challenge(
        85,
        true,
        1,
        new ItemStack[] { new ItemStack(Blocks.pumpkin) });
    public static final Challenge wearDarkArmor = new Challenge(
        86,
        true,
        4,
        new ItemStack[] { new ItemStack(TragicItems.DarkHelm), new ItemStack(TragicItems.DarkPlate),
            new ItemStack(TragicItems.DarkLegs), new ItemStack(TragicItems.DarkBoots) }).setDifficulty(3);
    public static final Challenge findEverlastingLight = new Challenge(
        87,
        true,
        1,
        new ItemStack(TragicItems.EverlastingLight)).setDifficulty(2);
    public static final Challenge collectExoticFruit = new Challenge(88, true, 16, new ItemStack(TragicItems.Honeydrop))
        .setDifficulty(1);
    public static final Challenge collectChallengeScrolls = new Challenge(
        89,
        true,
        5,
        new ItemStack(TragicItems.ChallengeScroll)).setDifficulty(2);
    public static final Challenge findEnderChest = new Challenge(90, true, 1, new ItemStack(Blocks.ender_chest))
        .setDifficulty(2);
    public static final Challenge killBats = new Challenge(91, false, 15, EntityBat.class, false).setDifficulty(1);
    public static final Challenge longTermKilling = new Challenge(92, false, 100, EntityCreature.class, false)
        .setDifficulty(1);
    public static final Challenge killPlayer = new Challenge(93, false, 1, EntityPlayer.class, false).setDifficulty(3);
    public static final Challenge findPlayer = new Challenge(94, true, 1, EntityPlayer.class, true).setDifficulty(1);
    public static final Challenge longTermKilling2 = new Challenge(95, false, 500, EntityCreature.class, false)
        .setDifficulty(2);
    public static final Challenge longTermKilling3 = new Challenge(96, false, 1000, EntityCreature.class, false)
        .setDifficulty(3);
    public static final Challenge killAnimals = new Challenge(97, false, 30, EntityAnimal.class, false)
        .setDifficulty(1);
    public static final Challenge findWolf = new Challenge(98, true, 1, EntityWolf.class, true).setDifficulty(1);
    public static final Challenge collectRedstone = new Challenge(99, true, 64, new ItemStack(Items.redstone))
        .setDifficulty(1);
    public static final Challenge killEnderDragon = new Challenge(100, false, 1, EntityDragon.class, false)
        .setDifficulty(2);
    public static final Challenge travelFar = new Challenge(101, false, 1, (double) 1000);
    public static final Challenge travelFar2 = new Challenge(102, false, 1, (double) 5000).setDifficulty(1);
    public static final Challenge travelFar3 = new Challenge(103, false, 1, (double) 10000).setDifficulty(2);
    public static final Challenge travelFar4 = new Challenge(104, false, 1, (double) 100000).setDifficulty(3);
    public static final Challenge findScorchedWasteland = new Challenge(105, true, 1, TragicBiome.ScorchedWastelands)
        .setDifficulty(1);
    public static final Challenge findIreNetCannon = new Challenge(
        106,
        true,
        1,
        new ItemStack(TragicItems.IreNetParticleCannon)).setDifficulty(2);
    public static final Challenge findArchangel = new Challenge(107, true, 1, EntityArchangel.class, true)
        .setDifficulty(1);
    public static final Challenge findRanmas = new Challenge(108, true, 1, EntityRanmas.class, true).setDifficulty(3);
    public static final Challenge findDarkForest = new Challenge(109, true, 1, TragicBiome.DarkForest);
    public static final Challenge killOverlordCore = new Challenge(110, false, 1, EntityOverlordCore.class, false)
        .setDifficulty(3);
    public static final Challenge findSynapse = new Challenge(111, true, 1, TragicBiome.Synapse).setDifficulty(2);
    public static final Challenge collectNanoBots = new Challenge(112, true, 64, new ItemStack(TragicItems.NanoBots))
        .setDifficulty(1);
    public static final Challenge findWingsOfLiberation = new Challenge(
        113,
        true,
        1,
        new ItemStack(TragicItems.WingsOfLiberation)).setDifficulty(2);
    public static final Challenge killFusea = new Challenge(114, false, 10, EntityFusea.class, false).setDifficulty(1);
    public static final Challenge findPermafrost = new Challenge(115, true, 1, TragicBlocks.Permafrost)
        .setDifficulty(1);
    public static final Challenge netherBed = new Challenge(116, true, 1, new ItemStack(Blocks.bed))
        .setLocationBased(BiomeGenBase.hell)
        .setDifficulty(1);
    public static final Challenge darkAeris = new Challenge(117, true, 1, new ItemStack(TragicBlocks.Aeris))
        .setLocationBased(TragicBiome.DarkMarsh)
        .setDifficulty(2);
    public static final Challenge findAshenBadlands = new Challenge(118, true, 1, TragicBiome.AshenBadlands)
        .setDifficulty(1);
    public static final Challenge findHallowedHills = new Challenge(119, true, 1, TragicBiome.HallowedHills)
        .setDifficulty(1);
    public static final Challenge findPsygote = new Challenge(120, true, 1, EntityPsygote.class, true).setDifficulty(1);
    public static final Challenge killHarvesters = new Challenge(121, false, 10, EntityHarvester.class, true)
        .setDifficulty(2);
    public static final Challenge collectConduit = new Challenge(122, true, 64, new ItemStack(TragicBlocks.Conduit))
        .setDifficulty(2);
    public static final Challenge findQuicksand = new Challenge(123, true, 1, TragicBlocks.Quicksand).setDifficulty(1);
    public static final Challenge killHunters = new Challenge(124, false, 30, EntityHunter.class, true)
        .setDifficulty(1);
    public static final Challenge findSoulChest = new Challenge(125, true, 1, TragicBlocks.SoulChest).setDifficulty(3);
    public static final Challenge onABoat = new Challenge(126, true, 1, EntityBoat.class, true)
        .setLocationBased(BiomeGenBase.deepOcean)
        .setDifficulty(1);
    public static final Challenge collectQuartz = new Challenge(127, true, 64, new ItemStack(Blocks.quartz_block));
    public static final Challenge findGuiltyThorn = new Challenge(128, true, 1, new ItemStack(TragicItems.GuiltyThorn))
        .setDifficulty(1);

    public final int challengeID;
    public final boolean savesProgress;
    public final int requirement;

    public Class challengeClass = null;
    public boolean isItemChallenge = false;
    public ItemStack challengeItem = null;
    public int difficulty = 0;
    public boolean isTimed = false;
    public boolean isMobRush = false;
    public boolean isTargetChallenge = false;
    public Block challengeBlock = null;
    public boolean isBlockChallenge = false;
    public boolean isLocationBased = false;
    public BiomeGenBase challengeBiome = null;
    public boolean isLoreChallenge = false;
    public int loreRarity = 0;
    public boolean isArmorChallenge = false;
    public ItemStack[] challengeArmor = null;
    public double challengeRange = 0.0D;
    public boolean ignoresMeta = false;

    public static final String[] challengeNames = new String[] { "null", "zombieKills", "collectWheat", "stayAlive",
        "stayAlive3", "survive", "endermanKills", "bossKills", "collectLeather", "stayAlive7", "witherKill",
        "findEmerald", "findDiamonds", "collectAsh", "miniBossKills", "stinKills", "tragicNekoKills",
        "collectGoldenApples", "findWisp", "findApis", "findIronGolem", "findSnowBlock", "iceBucketChallenge",
        "findRecord11", "collectFish", "collectGunpowder", "collectRedSand", "bossKill", "survive2",
        "collectCooldownDefuse", "findStarlitCliffs", "findMesa", "findMushroomIsland", "findBedrock", "findWater",
        "findAshenGrass", "findRedMushroomBlock", "findSand", "findMossyCobblestone", "findIronOre", "findIceMountains",
        "findDeepOcean", "findNorVox", "killInklings", "findHorses", "killTragicMobs", "findOcelots", "findVillagers",
        "killGhasts", "findSlime", "killZombiePigmen", "survive3", "cliffDiamond", "killVillagers", "collectEmeralds",
        "collectIronBlocks", "killPirah", "killSkeletons", "findClay", "findLeaves", "killKitsune", "killRagr",
        "killWisps", "goFishing", "goFishing2", "findLightChestplate", "findAwakeningStone", "findDimensionalKey",
        "findSavanna", "collectPotatoes", "collectStatues", "findAbomination", "findPlague", "findTimeController",
        "collectSmoothNetherrack", "collectRedFlowers", "goFishing3", "findMobStatue", "collectTNT",
        "collectEnderPearls", "findEpicLore", "findRareLore", "collectEpicLore", "collectUncommonLore",
        "wearDiamondArmor", "wearPumpkin", "wearDarkArmor", "findEverlastingLight", "collectExoticFruit",
        "collectChallengeScrolls", "findEnderChest", "killBats", "longTermKilling", "killPlayer", "findPlayer",
        "longTermKilling2", "longTermKilling3", "killAnimals", "findWolf", "collectRedstone", "killEnderDragon",
        "travelFar", "travelFar2", "travelFar3", "travelFar4", "findScorchedWasteland", "findIreNetCannon",
        "findArchangel", "findRanmas", "findDarkForest", "killOverlordCore", "findSynapse", "collectNanoBots",
        "findWingsOfLiberation", "killFusea", "findPermafrost", "netherBed", "darkAeris", "findAshenBadlands",
        "findHallowedHills", "findPsygote", "killHarvesters", "collectConduit", "findQuicksand", "killHunters",
        "findSoulChest", "onABoat", "collectQuartz", "findGuiltyThorn" };

    public Challenge(int id, boolean flag, int limit) {
        if (challengeList[id] != null)
            throw new IllegalArgumentException("There is already a Challenge that has that id!");
        challengeList[id] = this;
        this.challengeID = id;
        this.savesProgress = flag;
        this.requirement = limit;
    }

    public Challenge(int id, boolean flag, int limit, Class oclass, boolean flag2) {
        this(id, flag, limit);
        this.challengeClass = oclass;
        this.isTargetChallenge = flag2;
    }

    public Challenge(int id, boolean flag, int limit, ItemStack stack) {
        this(id, flag, limit);
        this.isItemChallenge = true;
        if (stack == null) throw new IllegalArgumentException("Itemstack for Challenge is null!");
        this.challengeItem = stack;
    }

    public Challenge(int id, boolean flag, int limit, Block block) {
        this(id, flag, limit);
        this.challengeBlock = block;
        this.isBlockChallenge = true;
    }

    public Challenge(int id, boolean flag, int limit, double range) {
        this(id, flag, limit);
        this.isLocationBased = true;
        this.challengeRange = range;
    }

    public Challenge(int id, boolean flag, int limit, BiomeGenBase biome) {
        this(id, flag, limit);
        this.isLocationBased = true;
        this.challengeBiome = biome;
    }

    public Challenge(int id, boolean flag, int limit, int loreRarity) {
        this(id, flag, limit);
        this.isLoreChallenge = true;
        this.loreRarity = loreRarity;
    }

    public Challenge(int id, boolean flag, int limit, ItemStack[] armor) {
        this(id, flag, limit);
        this.isArmorChallenge = true;
        this.challengeArmor = armor;
    }

    public Challenge setMobRushChallenge() {
        this.isMobRush = true;
        return this;
    }

    public Challenge setDifficulty(int i) {
        this.difficulty = i;
        return this;
    }

    public Challenge setLocationBased(BiomeGenBase biome) {
        this.challengeBiome = biome;
        this.isLocationBased = true;
        return this;
    }

    public Challenge setTimed() {
        this.isTimed = true;
        return this;
    }

    public Challenge setIgnoresMeta() {
        this.ignoresMeta = true;
        return this;
    }

    public static String getNameFromID(int id) {
        return StatCollector.translateToLocal("challenge." + challengeNames[id] + ".name");
    }

    public static String getDesc(int id) {
        return StatCollector.translateToLocal("challenge." + challengeNames[id] + ".desc");
    }

    public static Challenge getChallengeFromID(int id) {
        return challengeList[id];
    }
}
