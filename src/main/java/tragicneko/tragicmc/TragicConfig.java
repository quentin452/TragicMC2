package tragicneko.tragicmc;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.potion.Potion;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class TragicConfig {

    private static final String CAT_MASTER = "Master Configs";
    private static final String CAT_BLANKET = "Blanket Configs";
    private static final String CAT_ACHIEVE = "Achievements";
    private static final String CAT_AMULET = "Amulets";
    private static final String CAT_AMUEFFECT = "Amulet Effects";
    private static final String CAT_DIMENSION = "Dimension";
    private static final String CAT_BIOME = "Dimension Biomes";
    private static final String CAT_DOOM = "Doom";
    private static final String CAT_DOOMSDAYS = "Doomsdays";
    private static final String CAT_WEAPON = "Weapons";
    private static final String CAT_ENCHANT = "Enchantments";
    private static final String CAT_MOBS = "Mobs";
    private static final String CAT_MOBSTATS = "Mob Stats";
    private static final String CAT_MOBSPAWNS = "Mob Spawns";
    private static final String CAT_POTION = "Potions";
    private static final String CAT_VANILLA = "Vanilla Changes";
    private static final String CAT_WORLDGEN = "WorldGen";
    private static final String CAT_MISC = "Miscellaneous";
    private static final String CAT_MODIFIERS = "Attribute Modifiers";
    private static final String CAT_CREATIVE = "Creative Item Options";
    private static final String CAT_CLIENT = "Client-side Only Options";
    private static final String CAT_STRUCTURE = "Structures";
    private static final String CAT_GRIEF = "Griefing Options";
    private static final String CAT_PETS = "Pets";
    private static final String CAT_MOBAI = "Mob AI";

    // options meant for internal use, for toggling via master configs
    public static boolean allowNonMobItems = true, allowNonMobBlocks = true, allowNetwork = true, allowRecipes = true,
        allowSurvivalTab = true;

    public static boolean mobsOnlyMode, hardcoreMode, lightweightMode, barebonesMode;
    public static boolean allowAchievements, allowAmulets, allowDimension, allowDoom, allowEnchantments, allowMobs,
        allowPotions, allowVanillaChanges, allowWorldGen;

    private static boolean[] amuletConfig = new boolean[16];
    public static boolean allowAmuletLeveling, allowAmuletCrafting, requireAmuletSlotUnlock, allowAmuletBossKillCharge,
        allowAmuletModifiers, allowAmuletDeathDrops;
    public static int amuletMaxSlots, amuletOverallRarity, amuletReleaseRarity, amuletModChance, amuletModChance2,
        amuletModChance3, amuletStartSlots;
    private static boolean[] amuletEffects = new boolean[48];
    public static boolean amuPeace, amuYeti, amuClaymation, amuChicken, amuBlacksmith, amuCreeper, amuZombie,
        amuSkeleton, amuIce, amuSnowGolem, amuIronGolem;
    public static boolean amuSpider, amuStin, amuSupernatural, amuFusea, amuLuck, amuKitsune, amuMartyr, amuPiercing,
        amuApis, amuSunken, amuEnderman, amuPolaris, amuLightning;
    public static boolean amuConsumption, amuUndead, amuEnderDragon, amuTime, amuWither, amuOverlord, amuEnyvil;

    public static boolean sphereGenUsesFilter, eraserUsesFilter, spikeGenUsesFilter, voidPitUsesFilter;
    public static String[] sphereFilter = new String[64], eraserFilter = new String[64], spikeFilter = new String[64],
        voidPitFilter = new String[64];
    public static double sphereSize, eraserSize, spikeSize, spikeRegression, spikeCutoff, voidPitSize,
        explosionBaseSize, explosionSizeVariation;

    private static boolean[] dimensionConfig = new boolean[16];
    public static boolean allowSynapse, allowCollision, allowCollisionRespawn, allowSynapseRespawn, keepCollisionLoaded,
        keepSynapseLoaded, allowSynapseVariants;
    public static int collisionID, collisionProviderID, synapseID, synapseProviderID, collisionBiomeSize,
        synapseVariantChance;

    public static int idDecayingHills, idDecayingValley, idDecayingWasteland, idDecayingMountains, idPaintedForest,
        idPaintedPlains, idPaintedHills, idPaintedClearing;
    public static int idAshenMountains, idAshenHills, idAshenBadlands, idStarlitPrarie, idStarlitPlateaus,
        idStarlitCliffs, idStarlitLowlands, idTaintedSpikes;
    public static int idTaintedLowlands, idTaintedRises, idTaintedScarlands, idTaintedIsles, idHallowedHills,
        idHallowedForest, idHallowedPrarie, idHallowedCliffs;
    public static int idScorchedWastelands, idScorchedValley, idScorchedScarlands, idCorrodedSteppe, idCorrodedHeights,
        idCorrodedVeld, idCorrodedRunoff, idCorrodedFallout;
    public static int idFrozenTundra, idFrozenHills, idFrozenDepths, idCrystal, idDarkForest, idDarkForestHills,
        idDarkMarsh, idSynapseDead, idSynapseCorrupt;
    public static int idSynapse;
    public static int decayingHillsW, decayingValleyW, decayingWastelandW, decayingMountainsW, paintedForestW,
        paintedPlainsW, paintedHillsW, paintedClearingW;
    public static int ashenMountainsW, ashenHillsW, ashenBadlandsW, starlitPrarieW, starlitPlateausW, starlitCliffsW,
        starlitLowlandsW, taintedSpikesW, taintedLowlandsW;
    public static int taintedRisesW, taintedScarlandsW, taintedIslesW, hallowedHillsW, hallowedForestW, hallowedPrarieW,
        hallowedCliffsW, scorchedWastelandsW, scorchedValleyW;
    public static int scorchedScarlandsW, corrodedSteppeW, corrodedHeightsW, corrodedVeldW, corrodedRunoffW,
        corrodedFalloutW, frozenTundraW, frozenHillsW, frozenDepthsW;
    public static int crystalW, darkForestW, darkForestHillsW, darkMarshW;

    private static boolean[] doomConfig = new boolean[24];
    public static boolean allowDoomsdays, allowInfluenceDoomsdays, allowOverflowDoomsdays, allowCrisisDoomsdays,
        allowWorldShaperDoomsdays, allowCombinationDoomsdays;
    public static boolean allowNonDoomsdayAbilities, shouldDoomLimitIncrease, allowConsumeRefill, allowDoomPainRecharge,
        allowNaturalRecharge, allowCrucialMoments, allowBacklash;
    public static boolean allowCooldown, allowDoomKillRecharge, allowCooldownDefuse, allowPartnerDoomsdays;
    public static int maxDoomAmount, doomRechargeRate, doomConsumeRarity, cooldownDefuseRarity, consumeRefillAmount,
        defuseRefillAmount, backlashChance, crucialMomentChance;
    public static int doomConsumeAmount, maxDoomStart, doomRechargeAmount, partnerDoomsdayDistance;

    public static boolean[] doomsdayAllow = new boolean[96];
    public static int[] doomsdayCooldown = new int[96];
    public static int[] doomsdayCost = new int[96];
    public static boolean[] doomAbility = new boolean[48];
    public static int[] doomAbilityCost = new int[48];

    public static boolean[] enchantAllow = new boolean[32];
    public static boolean allowDecay, allowSlay, allowAbsolve, allowVampirism, allowLeech, allowConsume, allowDistract,
        allowMultiply, allowCombustion, allowRuneBreak;
    public static boolean allowReach, allowUnbreakable, allowRust, allowVeteran, allowDeathTouch, allowIgnition,
        allowToxicity, allowParalysis, allowElasticity, allowAgility;
    public static boolean allowRuneWalker, allowLuminescence;
    public static int idDecay, idSlay, idAbsolve, idVampirism, idLeech, idConsume, idDistract, idMultiply, idCombustion,
        idRuneBreak, idReach, idUnbreakable, idRust, idVeteran;
    public static int idDeathTouch, idIgnition, idToxicity, idParalysis, idElasticity, idAgility, idRuneWalker,
        idLuminescence;
    public static int wDecay, wSlay, wAbsolve, wVampirism, wLeech, wConsume, wDistract, wMultiply, wCombustion,
        wRuneBreak, wReach, wUnbreakable, wRust, wVeteran;
    public static int wDeathTouch, wIgnition, wToxicity, wParalysis, wElasticity, wAgility, wRuneWalker, wLuminescence;

    public static boolean[] mobConfig = new boolean[16];
    public static boolean allowNormalMobs, allowMiniBosses, allowBosses, allowBossOverworldSpawns, allowExtraBossLoot,
        allowMobTransformation;
    public static boolean allowDynamicHealthScaling, allowNonDimensionMobSpawns, allowGroupBuffs, allowEasyBosses,
        allowMobSounds, bossesDenyFlight;
    public static boolean allowMobInfighting, allowMobIllumination, allowRandomSupportMob;
    public static int commonDropRate, rareDropRate, mobTransformationChance, bossDamageCap, groupBuffChance;
    public static boolean[] mobAllow = new boolean[64];
    public static boolean allowJabba, allowJanna, allowPlague, allowGragul, allowMinotaur, allowInkling, allowRagr,
        allowPumpkinhead, allowTragicNeko, allowTox, allowPox;
    public static boolean allowCryse, allowStarCryse, allowNorVox, allowStarVox, allowPirah, allowStin, allowStinBaby,
        allowKindlingSpirit, allowAbomination, allowErkel;
    public static boolean allowSirv, allowPsygote, allowLockbot, allowNanoSwarm, allowSnowGolem, allowHunter,
        allowHarvester, allowSeeker, allowArchangel, allowIre;
    public static boolean allowFusea, allowRanmas, allowParasmite, allowAvris, allowPyragr, allowBlist, allowThorg;
    public static boolean[] miniBossAllow = new boolean[32];
    public static boolean allowJarra, allowKragul, allowMagmox, allowMegaCryse, allowVoxStellarum, allowGreaterStin,
        allowStinKing, allowStinQueen, allowAegar, allowVolatileFusea, allowAggro;
    public static boolean[] bossAllow = new boolean[24];
    public static boolean allowApis, allowSkultar, allowKitsunakuma, allowEmpariah, allowTimeController, allowPolaris,
        allowEnyvil, allowClaymation, allowOverlord;

    public static double[] jabbaStats, jannaStats, plagueStats, gragulStats, minotaurStats, inklingStats, ragrStats,
        pyragrStats, pumpkinheadStats, tragicNekoStats, toxStats, poxStats, cryseStats;
    public static double[] starCryseStats, norVoxStats, starVoxStats, goldenPirahStats, pirahStats, stinStats,
        stinBabyStats, kindlingSpiritStats, abominationStats, erkelStats, sirvStats, psygoteStats;
    public static double[] lockbotStats, nanoSwarmStats, hunterStats, harvesterStats, seekerStats, archangelStats,
        ireStats, fuseaStats, ranmasStats, parasmiteStats, kurayamiStats, avrisStats, blistStats, thorgStats;
    public static double[] jarraStats, kragulStats, magmoxStats, megaCryseStats, voxStellarumStats, greaterStinStats,
        stinKingStats, stinQueenStats, aegarStats, volatileFuseaStats, aggroStats;
    public static double[] apisStats, skultarStats, kitsunakumaStats, empariahStats, timeControllerStats, polarisStats,
        enyvilStats, claymationStats, overlordCoreStats, overlordCombatStats, overlordCocoonStats;

    public static int jabbaSC, jannaSC, plagueSC, gragulSC, minotaurSC, inklingSC, ragrSC, pyragrSC, pumpkinheadSC,
        tragicNekoSC, toxSC, poxSC, cryseSC, starCryseSC, norVoxSC, starVoxSC;
    public static int pirahSC, stinSC, kindlingSpiritSC, abominationSC, erkelSC, sirvSC, psygoteSC, lockbotSC,
        nanoSwarmSC, snowGolemSC, hunterSC, harvesterSC, seekerSC;
    public static int archangelSC, ireSC, fuseaSC, ranmasSC, parasmiteSC, avrisSC, blistSC, thorgSC, jarraSC, kragulSC,
        magmoxSC, megaCryseSC, voxStellarumSC, greaterStinSC, aegarSC, aggroSC;
    public static int stinKingSC, stinQueenSC, volatileFuseaSC, apisSC, skultarSC, kitsunakumaSC, empariahSC,
        timeControllerSC, polarisSC, enyvilSC, claymationSC;

    public static int[] jabbaGS, jannaGS, plagueGS, gragulGS, minotaurGS, inklingGS, ragrGS, pyragrGS, pumpkinheadGS,
        tragicNekoGS, toxGS, poxGS, cryseGS, starCryseGS, norVoxGS, starVoxGS;
    public static int[] pirahGS, stinGS, kindlingSpiritGS, abominationGS, erkelGS, sirvGS, psygoteGS, lockbotGS,
        nanoSwarmGS, snowGolemGS, hunterGS, harvesterGS, seekerGS;
    public static int[] archangelGS, ireGS, fuseaGS, ranmasGS, parasmiteGS, avrisGS, blistGS, thorgGS, jarraGS,
        kragulGS, magmoxGS, megaCryseGS, voxStellarumGS, greaterStinGS, stinKingGS, stinQueenGS, volatileFuseaGS,
        aegarGS, aggroGS;

    public static boolean jabbaSOV, plagueSOV, gragulSOV, minotaurSOV, inklingSOV, ragrSOV, pumpkinheadSOV,
        tragicNekoSOV, toxSOV, cryseSOV, norVoxSOV;
    public static boolean pirahSOV, stinSOV, kindlingSpiritSOV, abominationSOV, erkelSOV, sirvSOV, psygoteSOV,
        lockbotSOV, nanoSwarmSOV, hunterSOV, harvesterSOV, seekerSOV;
    public static boolean archangelSOV, ireSOV, fuseaSOV, ranmasSOV, parasmiteSOV, avrisSOV, blistSOV, thorgSOV,
        snowGolemSOV, jarraSOV, kragulSOV, magmoxSOV, megaCryseSOV;
    public static boolean greaterStinSOV, stinKingSOV, stinQueenSOV, voxStellarumSOV, aggroSOV, volatileFuseaSOV,
        aegarSOV;
    public static boolean apisSOV, skultarSOV, kitsunakumaSOV, empariahSOV, timeControllerSOV, polarisSOV, enyvilSOV,
        claymationSOV;

    public static int[] jabbaSpawns, plagueSpawns, gragulSpawns, minotaurSpawns, inklingSpawns, ragrSpawns,
        pumpkinheadSpawns, tragicNekoSpawns, toxSpawns, cryseSpawns, norVoxSpawns;
    public static int[] pirahSpawns, stinSpawns, kindlingSpiritSpawns, abominationSpawns, erkelSpawns, sirvSpawns,
        psygoteSpawns, lockbotSpawns, nanoSwarmSpawns, hunterSpawns, harvesterSpawns, seekerSpawns;
    public static int[] archangelSpawns, ireSpawns, fuseaSpawns, ranmasSpawns, parasmiteSpawns, avrisSpawns,
        blistSpawns, thorgSpawns, snowGolemSpawns, jarraSpawns, kragulSpawns, magmoxSpawns, megaCryseSpawns;
    public static int[] greaterStinSpawns, stinKingSpawns, stinQueenSpawns, voxStellarumSpawns, volatileFuseaSpawns,
        aegarSpawns, aggroSpawns;
    public static int[] apisSpawns, skultarSpawns, kitsunakumaSpawns, empariahSpawns, timeControllerSpawns,
        polarisSpawns, enyvilSpawns, claymationSpawns;

    public static boolean jabbaAnger, jabbaProjectiles, plagueCorruption, gragulDamageReduction, gragulPercentageDamage,
        minotaurCharge, inklingInvisibility, inklingTorchBreaking, inklingTeleport;
    public static boolean ragrExplosions, ragrBlockCrushing, pumpkinheadPumpkinSpawn, pumpkinheadHaste,
        pumpkinheadPumpkinbombs, tragicNekoRockets, tragicNekoStickyBombs, tragicNekoClusterBombs;
    public static boolean tragicNekoDeathBomb, tragicNekoCelebration, toxProjectiles, cryseReflection,
        norVoxProjectiles, norVoxRegeneration, pirahGolden, stinTeleport, abominationHelpCall;
    public static boolean erkelMushroomSpawning, sirvHelpCall, psygoteSwapTeleport, psygoteProjectiles,
        psygoteRegeneration, lockbotLockdown, harvesterBuffDebuffEntities, harvesterNanoSwarms, seekerKillbeam;
    public static boolean archangelHolybeam, ireEnergyBurst, fuseaExplosiveDamage, fuseaExplosiveAttack,
        fuseaExplosiveLayers, ranmasCharge, ranmasImpactExplosions, parasmiteLeech, avrisAnnouncements,
        avrisDespawnTime;
    public static boolean kragulSpiritCasts, magmoxLargeFireballs, megaCryseShields, greaterStinCharge, stinKingMortors,
        stinQueenWebBombs, stinQueenBabies, stinQueenWebs, voxStellarumSpinAttack, voxStellarumHealing;
    public static boolean aegarHypermode, aegarShockwave, aegarLasers, aegarMortors, volatileFuseaElementalChange,
        apisSolarBombs, apisChargeAttack, apisExplosiveCharge, apisSuperStomp, apisReflection;
    public static boolean skultarDemeanor, skultarProjectiles, skultarClone, skultarWitheringGas, skultarRegeneration,
        kitsunakumaFireballExempt, kitsunakumaFireballs, kitsunakumaTeleport, kitsunakumaTaunt;
    public static boolean polarisTeleport, polarisInvisibility, polarisAfterImage, polarisNighttimeSet,
        polarisFearGolems, polarisRegeneration, empariahDemeanor, empariahCharge, empariahFrostBreath;
    public static boolean empariahRoar, empariahRockThrowing, empariahSummonAbomination, empariahCallHelp,
        empariahRegeneration, timeControllerRegeneration, timeControllerQuantumLeap, timeControllerFlux;
    public static boolean timeControllerPurge, timeControllerSpaz, timeControllerTimeBombs, timeControllerLuminescence,
        timeControllerTimeAltering, enyvilDarkCrystals, enyvilDarkLightning;
    public static boolean enyvilCrystalLaser, enyvilDarkEnergySpray, enyvilTractorBeam, enyvilThunderstorm,
        enyvilLightningExplosions, enyvilSlam, enyvilDestroyBlocks, enyvilRegeneration;
    public static boolean claymationTransformation, claymationPotionReflection;

    public static boolean[] potionAllow = new boolean[32];
    public static boolean allowFlight, allowAquaSuperiority, allowImmunity, allowResurrection, allowHarmony,
        allowInvulnerability, allowClarity, allowConvergence, allowDivinity;
    public static boolean allowCorruption, allowDisorientation, allowStun, allowFear, allowMalnourish, allowCripple,
        allowSubmission, allowInhibit, allowLeadFoot, allowHacked, allowBurned;
    public static int idFlight, idAquaSuperiority, idImmunity, idResurrection, idHarmony, idInvulnerability, idClarity,
        idConvergence, idDivinity;
    public static int idCorruption, idDisorientation, idStun, idFear, idMalnourish, idCripple, idSubmission, idInhibit,
        idLeadFoot, idHacked, idBurned;

    private static boolean[] vanillaConfig = new boolean[24];
    public static boolean allowVanillaMobBuffs, allowExtraMobEffects, allowAnimalRetribution, allowMobModdedArmor,
        allowRespawnPunishment, allowExtraExplosiveEffects;
    public static boolean allowMobBlindnessDebuff, allowExtraOverworldFlowers, allowOverworldSilverfishGen,
        allowNetherOreGen, allowOverworldOreGen, allowDrudgeGen, allowAnimalGolemCorruption;
    public static boolean allowCowMinotaurCreation, allowIronGolemHitCooldown, allowNauseaRandomMiss,
        allowBlindnessReachDebuff, allowCripplingFall;
    public static int rubyOreRate, sapphireOreRate, mercuryOreRate, tungstenOreRate, drudgeRate, silverfishRate,
        rubyOreVeinSize, sapphireOreVeinSize, mercuryOreVeinSize;
    public static int tungstenOreVeinSize, drudgeVeinSize, silverfishVeinSize, aerisRarity, nauseaMissChance;
    public static double blindnessReachDebuffAmount;

    private static boolean[] worldGenConfig = new boolean[16];
    public static boolean allowVoidPitGen, allowSpikeGen, allowScatteredSurfaceGen, allowStringLightGen,
        allowDarkStoneVariantGen, allowStructureGen, allowInvertedSpikeGen;
    public static boolean allowDigitalSeaGen, allowFruitGen, allowIsleGen, allowFlowerGen;
    public static int structureOverallRarity;
    public static boolean[] structureAllow = new boolean[32];
    public static int[] structureRarity = new int[32];

    public static boolean allowRandomWeaponLore, allowChallengeScrolls, allowMobStatueDrops, allowGeneratorItems,
        allowItemTimeAltering;
    public static boolean allowPvP, allowDefaultLores, allowCorruptionTransfer;
    public static int challengeScrollDropChance, mobStatueDropChance;

    public static boolean allowAnimatedGui, allowArmorModels, allowWeaponModels, allowDivinityColorChange, showDoomGui,
        showAmuletStatusGui, allowDimensionalMusic, allowPotionEffectOverlays;
    public static int guiTransparency, guiTexture, guiX, guiY;

    public static boolean[] griefConfig = new boolean[12];
    public static double[] modifier = new double[32];

    public static void load() {
        Configuration config = TragicMC.getConfig();
        config.load();

        ConfigCategory cat; // The category currently being loaded from the config
        Property prop; // The value currently being parsed
        byte m; // a byte mapping to make it easier for my array hypervisors to work

        cat = config.getCategory(CAT_MASTER);
        cat.setComment(
            "These override all other mod options. If multiple are enabled then the first one that is read is used and the others are disabled.");
        cat.setRequiresMcRestart(true);
        cat.setShowInGui(true);

        prop = config.get(cat.getName(), "mobsOnlyMode", false);
        prop.comment = "Is mobs only mode enabled? This strips the mod down to just the mobs and a few items/blocks related to them.";
        mobsOnlyMode = prop.getBoolean(false);

        prop = config.get(cat.getName(), "hardcoreMode", false);
        prop.comment = "Is hardcode mode enabled? This makes things a bit more difficult than normal.";
        hardcoreMode = prop.getBoolean(false);

        prop = config.get(cat.getName(), "lightweightMode", false);
        prop.comment = "Is lightweight mode enabled? This makes things somewhat easier than normal.";
        lightweightMode = prop.getBoolean(false);

        prop = config.get(cat.getName(), "barebonesMode", false);
        prop.comment = "Is barebones mode enabled? This takes things back to a much simpler time, stripping the mod down to just weapons and mobs.";
        barebonesMode = prop.getBoolean(false);

        cat = config.getCategory(CAT_BLANKET);
        cat.setComment("These disable all options beneath them if set to false.");
        cat.setRequiresMcRestart(true);
        cat.setShowInGui(true);

        prop = config.get(cat.getName(), "allowAchievements", true);
        prop.comment = "Are Achievements and Achievement options allowed?";
        allowAchievements = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowAmulets", true);
        prop.comment = "Are Amulets, Amulet Modifiers, Amulet Guis and Amulet recipes allowed?";
        allowAmulets = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowDimensions", true);
        prop.comment = "Are the mod-exclusive Dimensions and Biomes allowed?";
        allowDimension = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowDoom", true);
        prop.comment = "Are Doom, Doomsdays and non-Doomsday Weapon/Armor abilities allowed?";
        allowDoom = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowEnchantments", true);
        prop.comment = "Are mod-exclusive Weapon and Armor Enchantments allowed?";
        allowEnchantments = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowMobs", true);
        prop.comment = "Are mod-exclusive Mobs and Bosses allowed?";
        allowMobs = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowPotions", true);
        prop.comment = "Are mod-exclusive Potion Effects allowed and can they be used by the mod's Mobs and various effects?";
        allowPotions = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowVanillaChanges", true);
        prop.comment = "Are changes to Vanilla like increasing Vanilla mob health and giving extra abilities to Vanilla mobs allowed?";
        allowVanillaChanges = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowWorldGen", true);
        prop.comment = "Is the mod able to execute any of it's non-Ore WorldGen?";
        allowWorldGen = prop.getBoolean(true);

        cat = config.getCategory(CAT_ACHIEVE);
        cat.setComment("These allow you to toggle specific Achievements, whether they can be received or not.");
        cat.setShowInGui(true);

        cat = config.getCategory(CAT_AMULET);
        cat.setComment("These allow you to toggle various aspects of Amulets.");
        cat.setRequiresMcRestart(true);
        cat.setShowInGui(false);

        prop = config.get(cat.getName(), "allowAmuletLeveling", true);
        prop.comment = "Are amulets able to be leveled up by combining them in a crafting recipe?";
        amuletConfig[m = 0] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowAmuletCrafting", true);
        prop.comment = "Can amulets be crafted via raw materials?";
        amuletConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "requireAmuletSlotUnlock", true);
        prop.comment = "Do you need to use an Amulet Release to unlock a new Amulet slot?";
        amuletConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowAmuletBossKillCharge", true);
        prop.comment = "Will your Amulets repair some durability after killing a Boss?";
        amuletConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowAmuletModifiers", true);
        prop.comment = "Will Amulets have random modifiers that affect their user while equipped?";
        amuletConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowAmuletDeathDrops", true);
        prop.comment = "Do Amulets drop off of you on death?";
        amuletConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "amuletMaxSlots", 3);
        prop.comment = "The maximum amount of slots you can have unlocked.";
        amuletMaxSlots = clamp(prop.getInt(3), 0, 3);

        prop = config.get(cat.getName(), "amuletOverallRarity", 5);
        prop.comment = "The chance of you getting an Amulet in a chest, higher value is higher chance.";
        amuletOverallRarity = clamp(prop.getInt(5), 1, 250);

        prop = config.get(cat.getName(), "amuletReleaseRarity", 5);
        prop.comment = "The chance of you getting an Amulet Release in a chest, higher value is higher chance.";
        amuletReleaseRarity = clamp(prop.getInt(5), 1, 250);

        prop = config.get(cat.getName(), "amuletModChance", 54);
        prop.comment = "Affects the chance of getting at least one Modifier on an Amulet. Lower value is higher chance.";
        amuletModChance = clamp(prop.getInt(54), 1, 100);

        prop = config.get(cat.getName(), "amuletModChance2", 79);
        prop.comment = "Affects the chance of getting a second Modifier on an Amulet. Lower value is higher chance.";
        amuletModChance2 = clamp(prop.getInt(79), 1, 100);

        prop = config.get(cat.getName(), "amuletModChance3", 89);
        prop.comment = "Affects the chance of getting a third Modifier on an Amulet. Lower value is higher chance.";
        amuletModChance3 = clamp(prop.getInt(89), 1, 100);

        prop = config.get(cat.getName(), "amuletStartSlots", 1);
        prop.comment = "The amount of slots you have unlocked at the start.";
        amuletStartSlots = clamp(prop.getInt(1), 0, 3);

        cat = config.getCategory(CAT_AMUEFFECT);
        cat.setComment("Disable or enable certain Amulet Effects.");
        cat.setRequiresMcRestart(true);

        prop = config.get(cat.getName(), "peace", true);
        amuletEffects[m = 0] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "yeti", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "claymation", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "chicken", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "blacksmith", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "creeper", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "zombie", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "skeleton", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "ice", true);
        prop.setDefaultValue(true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "snowGolem", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "ironGolem", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "spider", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "stin", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "supernatural", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "fusea", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "luck", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "kitsune", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "martyr", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "piercing", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "apis", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "sunken", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "enderman", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "polaris", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "lightning", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "consumption", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "undead", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "enderDragon", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "time", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "wither", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "overlord", true);
        amuletEffects[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "enyvil", true);
        amuletEffects[++m] = prop.getBoolean(true);

        cat = config.getCategory(CAT_CREATIVE);
        cat.setComment("Change various aspects of the Creative mode only items in the mod.");
        cat.setRequiresMcRestart(true);

        prop = config.get(cat.getName(), "sphereGenUsesFilter", false);
        prop.comment = "Does the Sphere Generator use your custom block filter?";
        sphereGenUsesFilter = prop.getBoolean(false);

        prop = config
            .get(cat.getName(), "sphereFilter", new String[] { "tnt", "stone", "cobblestone", "TragicMC:darkStone" });
        prop.comment = "The blocks that can be used in the Sphere Generator, must be the Vanilla blockname or must be appended with the mod id for use of modded blocks. This is not meta-sensitive.";
        sphereFilter = prop.getStringList();

        prop = config.get(cat.getName(), "sphereSize", 6.5);
        prop.comment = "The radius of the sphere that the Sphere Generator uses.";
        sphereSize = prop.getDouble(6.5D);

        prop = config.get(cat.getName(), "eraserUsesFilter", false);
        prop.comment = "Does the Sphere Eraser use your custom block filter?";
        eraserUsesFilter = prop.getBoolean(false);

        prop = config.get(cat.getName(), "eraserFilter", new String[] { "air" });
        prop.comment = "The blocks that are ignored by the Sphere Eraser, must be the Vanilla blockname or must be appended with the mod id for use of modded blocks. This is not meta-sensitive.";
        eraserFilter = prop.getStringList();

        prop = config.get(cat.getName(), "eraserSize", 6.5D);
        prop.comment = "The radius of the sphere that the Sphere Eraser uses.";
        eraserSize = prop.getDouble(6.5D);

        prop = config.get(cat.getName(), "spikeGenUsesFilter", false);
        prop.comment = "Does the Spike Generator use your custom block filter?";
        spikeGenUsesFilter = prop.getBoolean(false);

        prop = config.get(cat.getName(), "spikeFilter", new String[] { "TragicMC:darkStone" });
        prop.comment = "The blocks that spikes can be generated out of in the Spike Generator, must be Vanilla blockname or must be appended with mod id for use of modded blocks. This is not meta-sensitive.";
        spikeFilter = prop.getStringList();

        prop = config.get(cat.getName(), "spikeSize", 2.5D);
        prop.comment = "The starting radius of a spike that the Spike Generator uses.";
        spikeSize = prop.getDouble(2.5D);

        prop = config.get(cat.getName(), "spikeRegression", 0.96977745D);
        prop.comment = "The amount the spike will regress as it gets generated higher up into the air, used by the Spike Generator.";
        spikeRegression = prop.getDouble(0.96977745D);

        prop = config.get(cat.getName(), "spikeCutoff", 0.36943755D);
        prop.comment = "The value that the spike will stop generating at, used by the Spike Generator.";
        spikeCutoff = prop.getDouble(0.36943755D);

        prop = config.get(cat.getName(), "voidPitUsesFiler", false);
        prop.comment = "Does the Void Pit Generator ignore any blocks when generated?";
        voidPitUsesFilter = prop.getBoolean(false);

        prop = config.get(cat.getName(), "voidPitFilter", new String[] { "air" });
        prop.comment = "The blocks that void pits can delete during generation via the Void Pit Generator, must be Vanilla blockname or must be appended with mod id for use of modded blocks. This is not meta-sensitive.";
        voidPitFilter = prop.getStringList();

        prop = config.get(cat.getName(), "voidPitSize", 12.5D);
        prop.comment = "The radius that the Void Pit Generator will use.";
        voidPitSize = prop.getDouble(12.5D);

        prop = config.get(cat.getName(), "explosionBaseSize", 3.5D);
        prop.comment = "The base size of the explosions used by the Explosion Generator";
        explosionBaseSize = prop.getDouble(3.5D);

        prop = config.get(cat.getName(), "explosionSizeVariation", 5.0D);
        prop.comment = "The variation applied to the base size of explosions created via the Explosion Generator";
        explosionSizeVariation = prop.getDouble(5.0F);

        cat = config.getCategory(CAT_DIMENSION);
        cat.setComment("Change and toggle aspects of the mod-exclusive Dimensions.");
        cat.setRequiresMcRestart(true);

        prop = config.get(cat.getName(), "allowSynapse", true);
        prop.comment = "Is the Synapse Dimension allowed?";
        dimensionConfig[m = 0] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowCollision", true);
        prop.comment = "Is the Collision Dimension allowed?";
        dimensionConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowCollisionRespawn", true);
        prop.comment = "Can you respawn in the Collision or will you be forced back to the Overworld?";
        dimensionConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowSynapseRespawn", true);
        prop.comment = "Can you respawn in the Synapse or wll you be forced back to the Synapse?";
        dimensionConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "keepCollisionLoaded", true);
        prop.comment = "Will the Collision Dimension remain loaded when no one is there?";
        dimensionConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "keepSynapseLoaded", true);
        prop.comment = "Will the Synapse Dimension remain loaded when no one is there?";
        dimensionConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowSynapseVariants", true);
        prop.comment = "Can the Synapse generate with mini-Biomes?";
        dimensionConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "collisionID", 2);
        prop.comment = "The ID that the Collision Dimension will use.";
        collisionID = prop.getInt(2);

        prop = config.get(cat.getName(), "collisionProviderID", 2);
        prop.comment = "The ID that the Collision's provider will use, if you don't know what this is, keep it the same as the Collision's Dimension ID.";
        collisionProviderID = prop.getInt(2);

        prop = config.get(cat.getName(), "synapseID", 3);
        prop.comment = "The ID that the Synapse Dimension will use.";
        synapseID = prop.getInt(3);

        prop = config.get(cat.getName(), "synapseProviderID", 3);
        prop.comment = "The ID that the Synapse's provider will use, if you don't know what this is, keep it the same as the Synapse's Dimension ID.";
        synapseProviderID = prop.getInt(3);

        prop = config.get(cat.getName(), "collisionBiomeSize", 6);
        prop.comment = "How large the Collision's biomes generate.";
        collisionBiomeSize = prop.getInt(6);

        prop = config.get(cat.getName(), "synapseVariantChance", 128);
        prop.comment = "The chance that a Synapse mini-biome will generate, higher number is lower chance to generate.";
        synapseVariantChance = prop.getInt(128);

        cat = config.getCategory(CAT_BIOME);
        cat.setComment(
            "Set biome ids and generation weights, higher weight is greater chance to generate out of that biome group. Setting a weight to 0 effectively removes a biome from the possible biomes to generate.");
        cat.setRequiresMcRestart(true);

        prop = config.get(cat.getName(), "decayingHillsID", findBiomeID(90));
        idDecayingHills = prop.getInt(findBiomeID(90));

        prop = config.get(cat.getName(), "decayingValleyID", findBiomeID(idDecayingHills + 1));
        idDecayingValley = prop.getInt(findBiomeID(idDecayingHills + 1));

        prop = config.get(cat.getName(), "decayingWastelandID", findBiomeID(idDecayingValley + 1));
        idDecayingWasteland = prop.getInt(findBiomeID(idDecayingValley + 1));

        prop = config.get(cat.getName(), "decayingMountainsID", findBiomeID(idDecayingWasteland + 1));
        idDecayingMountains = prop.getInt(findBiomeID(idDecayingWasteland + 1));

        prop = config.get(cat.getName(), "paintedForestID", findBiomeID(idDecayingMountains + 1));
        idPaintedForest = prop.getInt(findBiomeID(idDecayingMountains + 1));

        prop = config.get(cat.getName(), "paintedPlainsID", findBiomeID(idPaintedForest + 1));
        idPaintedPlains = prop.getInt(findBiomeID(idPaintedForest + 1));

        prop = config.get(cat.getName(), "paintedHillsID", findBiomeID(idPaintedPlains + 1));
        idPaintedHills = prop.getInt(findBiomeID(idPaintedPlains + 1));

        prop = config.get(cat.getName(), "paintedClearingID", findBiomeID(idPaintedHills + 1));
        idPaintedClearing = prop.getInt(findBiomeID(idPaintedHills + 1));

        prop = config.get(cat.getName(), "ashenMountainsID", findBiomeID(idPaintedClearing + 1));
        idAshenMountains = prop.getInt(findBiomeID(idPaintedClearing + 1));

        prop = config.get(cat.getName(), "ashenHillsID", findBiomeID(idAshenMountains + 1));
        idAshenHills = prop.getInt(findBiomeID(idAshenMountains + 1));

        prop = config.get(cat.getName(), "ashenBadlandsID", findBiomeID(idAshenHills + 1));
        idAshenBadlands = prop.getInt(findBiomeID(idAshenHills + 1));

        prop = config.get(cat.getName(), "starlitPrarieID", findBiomeID(idAshenBadlands + 1));
        idStarlitPrarie = prop.getInt(findBiomeID(idAshenBadlands + 1));

        prop = config.get(cat.getName(), "starlitPlateausID", findBiomeID(idStarlitPrarie + 1));
        idStarlitPlateaus = prop.getInt(findBiomeID(idStarlitPrarie + 1));

        prop = config.get(cat.getName(), "starlitCliffsID", findBiomeID(idStarlitPlateaus + 1));
        idStarlitCliffs = prop.getInt(findBiomeID(idStarlitPlateaus + 1));

        prop = config.get(cat.getName(), "starlitLowlandsID", findBiomeID(idStarlitCliffs + 1));
        idStarlitLowlands = prop.getInt(findBiomeID(idStarlitCliffs + 1));

        prop = config.get(cat.getName(), "taintedSpikesID", findBiomeID(idStarlitLowlands + 1));
        idTaintedSpikes = prop.getInt(findBiomeID(idStarlitLowlands + 1));

        prop = config.get(cat.getName(), "taintedLowlandsID", findBiomeID(idTaintedSpikes + 1));
        idTaintedLowlands = prop.getInt(findBiomeID(idTaintedSpikes + 1));

        prop = config.get(cat.getName(), "taintedRisesID", findBiomeID(idTaintedLowlands + 1));
        idTaintedRises = prop.getInt(findBiomeID(idTaintedLowlands + 1));

        prop = config.get(cat.getName(), "taintedScarlandsID", findBiomeID(idTaintedRises + 1));
        idTaintedScarlands = prop.getInt(findBiomeID(idTaintedRises + 1));

        prop = config.get(cat.getName(), "taintedIslesID", findBiomeID(idTaintedScarlands + 1));
        idTaintedIsles = prop.getInt(findBiomeID(idTaintedScarlands + 1));

        prop = config.get(cat.getName(), "synapseID", findBiomeID(idTaintedIsles + 1));
        idSynapse = prop.getInt(findBiomeID(idTaintedIsles + 1));

        prop = config.get(cat.getName(), "hallowedHillsID", findBiomeID(idSynapse + 1));
        idHallowedHills = prop.getInt(findBiomeID(idSynapse + 1));

        prop = config.get(cat.getName(), "hallowedForestID", findBiomeID(idHallowedHills + 1));
        idHallowedForest = prop.getInt(findBiomeID(idHallowedHills + 1));

        prop = config.get(cat.getName(), "hallowedPrarieID", findBiomeID(idHallowedForest + 1));
        idHallowedPrarie = prop.getInt(findBiomeID(idHallowedForest + 1));

        prop = config.get(cat.getName(), "hallowedCliffsID", findBiomeID(idHallowedPrarie + 1));
        idHallowedCliffs = prop.getInt(findBiomeID(idHallowedPrarie + 1));

        prop = config.get(cat.getName(), "scorchedWastelandsID", findBiomeID(idHallowedCliffs + 1));
        idScorchedWastelands = prop.getInt(findBiomeID(idHallowedCliffs + 1));

        prop = config.get(cat.getName(), "scorchedValleyID", findBiomeID(idScorchedWastelands + 1));
        idScorchedValley = prop.getInt(findBiomeID(idScorchedWastelands + 1));

        prop = config.get(cat.getName(), "scorchedScarlandsID", findBiomeID(idScorchedValley + 1));
        idScorchedScarlands = prop.getInt(findBiomeID(idScorchedValley + 1));

        prop = config.get(cat.getName(), "corrodedSteppeID", findBiomeID(idScorchedScarlands + 1));
        idCorrodedSteppe = prop.getInt(findBiomeID(idScorchedScarlands + 1));

        prop = config.get(cat.getName(), "corrodedHeightsID", findBiomeID(idCorrodedSteppe + 1));
        idCorrodedHeights = prop.getInt(findBiomeID(idCorrodedSteppe + 1));

        prop = config.get(cat.getName(), "corrodedVeldID", findBiomeID(idCorrodedHeights + 1));
        idCorrodedVeld = prop.getInt(findBiomeID(idCorrodedHeights + 1));

        prop = config.get(cat.getName(), "corrodedRunoffID", findBiomeID(idCorrodedVeld + 1));
        idCorrodedRunoff = prop.getInt(findBiomeID(idCorrodedVeld + 1));

        prop = config.get(cat.getName(), "corrodedFalloutID", findBiomeID(idCorrodedRunoff + 1));
        idCorrodedFallout = prop.getInt(findBiomeID(idCorrodedRunoff + 1));

        prop = config.get(cat.getName(), "frozenTundraID", findBiomeID(idCorrodedFallout + 1));
        idFrozenTundra = prop.getInt(findBiomeID(idCorrodedFallout + 1));

        prop = config.get(cat.getName(), "frozenHillsID", findBiomeID(idFrozenTundra + 1));
        idFrozenHills = prop.getInt(findBiomeID(idFrozenTundra + 1));

        prop = config.get(cat.getName(), "frozenDepthsID", findBiomeID(idFrozenHills + 1));
        idFrozenDepths = prop.getInt(findBiomeID(idFrozenHills + 1));

        prop = config.get(cat.getName(), "crystalID", findBiomeID(idFrozenDepths + 1));
        idCrystal = prop.getInt(findBiomeID(idFrozenDepths + 1));

        prop = config.get(cat.getName(), "darkForestID", findBiomeID(idCrystal + 1));
        idDarkForest = prop.getInt(findBiomeID(idCrystal + 1));

        prop = config.get(cat.getName(), "darkForestHillsID", findBiomeID(idDarkForest + 1));
        idDarkForestHills = prop.getInt(findBiomeID(idDarkForest + 1));

        prop = config.get(cat.getName(), "darkMarshID", findBiomeID(idDarkForestHills + 1));
        idDarkMarsh = prop.getInt(findBiomeID(idDarkForestHills + 1));

        prop = config.get(cat.getName(), "synapseDeadID", findBiomeID(idDarkMarsh + 1));
        idSynapseDead = prop.getInt(findBiomeID(idDarkMarsh + 1));

        prop = config.get(cat.getName(), "synapseCorruptID", findBiomeID(idSynapseDead + 1));
        idSynapseCorrupt = prop.getInt(findBiomeID(idSynapseDead + 1));

        prop = config.get(cat.getName(), "decayingHillsWeight", 20);
        decayingHillsW = clamp(prop.getInt(20), 0, 250);

        prop = config.get(cat.getName(), "decayingValleyWeight", 5);
        decayingValleyW = clamp(prop.getInt(5), 0, 250);

        prop = config.get(cat.getName(), "decayingWastelandWeight", 20);
        decayingWastelandW = clamp(prop.getInt(20), 0, 250);

        prop = config.get(cat.getName(), "decayingMountainsWeight", 10);
        decayingMountainsW = clamp(prop.getInt(10), 0, 250);

        prop = config.get(cat.getName(), "paintedForestWeight", 30);
        paintedForestW = clamp(prop.getInt(30), 0, 250);

        prop = config.get(cat.getName(), "paintedPlainsWeight", 10);
        paintedPlainsW = clamp(prop.getInt(10), 0, 250);

        prop = config.get(cat.getName(), "paintedHillsWeight", 20);
        paintedHillsW = clamp(prop.getInt(20), 0, 250);

        prop = config.get(cat.getName(), "paintedClearingWeight", 5);
        paintedClearingW = clamp(prop.getInt(5), 0, 250);

        prop = config.get(cat.getName(), "ashenMountainsWeight", 10);
        ashenMountainsW = clamp(prop.getInt(10), 0, 250);

        prop = config.get(cat.getName(), "ashenHillsWeight", 20);
        ashenHillsW = clamp(prop.getInt(20), 0, 250);

        prop = config.get(cat.getName(), "ashenBadlandsWeight", 25);
        ashenBadlandsW = clamp(prop.getInt(25), 0, 250);

        prop = config.get(cat.getName(), "starlitPrarieWeight", 25);
        starlitPrarieW = clamp(prop.getInt(25), 0, 250);

        prop = config.get(cat.getName(), "starlitPlateausWeight", 15);
        starlitPlateausW = clamp(prop.getInt(15), 0, 250);

        prop = config.get(cat.getName(), "starlitCliffsWeight", 10);
        starlitCliffsW = clamp(prop.getInt(10), 0, 250);

        prop = config.get(cat.getName(), "starlitLowlandsWeight", 25);
        starlitLowlandsW = clamp(prop.getInt(25), 0, 250);

        prop = config.get(cat.getName(), "taintedSpikesWeight", 25);
        taintedSpikesW = clamp(prop.getInt(25), 0, 250);

        prop = config.get(cat.getName(), "taintedLowlandsWeight", 10);
        taintedLowlandsW = clamp(prop.getInt(10), 0, 250);

        prop = config.get(cat.getName(), "taintedRisesWeight", 15);
        taintedRisesW = clamp(prop.getInt(15), 0, 250);

        prop = config.get(cat.getName(), "taintedScarlandsWeight", 15);
        taintedScarlandsW = clamp(prop.getInt(15), 0, 250);

        prop = config.get(cat.getName(), "taintedIslesWeight", 5);
        taintedIslesW = clamp(prop.getInt(5), 0, 250);

        prop = config.get(cat.getName(), "hallowedHillsWeight", 10);
        hallowedHillsW = clamp(prop.getInt(10), 0, 250);

        prop = config.get(cat.getName(), "hallowedForestWeight", 25);
        hallowedForestW = clamp(prop.getInt(25), 0, 250);

        prop = config.get(cat.getName(), "hallowedPrarieWeight", 20);
        hallowedPrarieW = clamp(prop.getInt(20), 0, 250);

        prop = config.get(cat.getName(), "hallowedCliffsWeight", 5);
        hallowedCliffsW = clamp(prop.getInt(5), 0, 250);

        prop = config.get(cat.getName(), "scorchedWastelandsWeight", 15);
        scorchedWastelandsW = clamp(prop.getInt(15), 0, 250);

        prop = config.get(cat.getName(), "scorchedValleyWeight", 5);
        scorchedValleyW = clamp(prop.getInt(5), 0, 250);

        prop = config.get(cat.getName(), "scorchedScarlandsWeight", 10);
        scorchedScarlandsW = clamp(prop.getInt(10), 0, 250);

        prop = config.get(cat.getName(), "corrodedSteppeWeight", 25);
        corrodedSteppeW = clamp(prop.getInt(25), 0, 250);

        prop = config.get(cat.getName(), "corrodedHeightsWeight", 15);
        corrodedHeightsW = clamp(prop.getInt(15), 0, 250);

        prop = config.get(cat.getName(), "corrodedVeldWeight", 10);
        corrodedVeldW = clamp(prop.getInt(10), 0, 250);

        prop = config.get(cat.getName(), "corrodedRunoffWeight", 20);
        corrodedRunoffW = clamp(prop.getInt(10), 0, 250);

        prop = config.get(cat.getName(), "corrodedFalloutWeight", 5);
        corrodedFalloutW = clamp(prop.getInt(5), 0, 250);

        prop = config.get(cat.getName(), "frozenTundraWeight", 25);
        frozenTundraW = clamp(prop.getInt(25), 0, 250);

        prop = config.get(cat.getName(), "frozenHillsWeight", 15);
        frozenHillsW = clamp(prop.getInt(15), 0, 250);

        prop = config.get(cat.getName(), "frozenDepthsWeight", 5);
        frozenDepthsW = clamp(prop.getInt(5), 0, 250);

        prop = config.get(cat.getName(), "crystalWeight", 5);
        crystalW = clamp(prop.getInt(5), 0, 250);

        prop = config.get(cat.getName(), "darkForestWeight", 25);
        darkForestW = clamp(prop.getInt(25), 0, 250);

        prop = config.get(cat.getName(), "darkForestHillsWeight", 15);
        darkForestHillsW = clamp(prop.getInt(15), 0, 250);

        prop = config.get(cat.getName(), "darkMarshWeight", 10);
        darkMarshW = clamp(prop.getInt(10), 0, 250);

        cat = config.getCategory(CAT_DOOM);
        cat.setComment("Modify aspects of Doom.");
        cat.setRequiresMcRestart(true);

        prop = config.get(cat.getName(), "allowDoomsdays", true);
        prop.comment = "Are Doomsdays able to be used?";
        doomConfig[m = 0] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowInfluenceDoomsdays", true);
        prop.comment = "Can Influence Doomsdays be used?";
        doomConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowOverflowDoomsdays", true);
        prop.comment = "Can Overflow Doomsdays be used?";
        doomConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowCrisisDoomsdays", true);
        prop.comment = "Can Crisis Doomsdays be used?";
        doomConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowWorldShaperDoomsdays", true);
        prop.comment = "Can World Shaper Doomsdays be used?";
        doomConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowCombinationDoomsdays", true);
        prop.comment = "Can Combination Doomsdays be used?";
        doomConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowNonDoomsdayAbilities", true);
        prop.comment = "Can non-Doomsday Weapon and Armor abilities be used?";
        doomConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowDoomIncrease", true);
        prop.comment = "Can the maximum Doom amount be increased by a Doom Consume?";
        doomConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowConsumeRefill", true);
        prop.comment = "Should Doom Consumes refill your Doom when used?";
        doomConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowDoomPainCharge", true);
        prop.comment = "Should your Doom increase when you take or inflict damage?";
        doomConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowDoomNaturalCharge", true);
        prop.comment = "Should your Doom recharge naturally?";
        doomConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowCrucialMoments", true);
        prop.comment = "Can Doomsdays have a chance to have extra effectiveness?";
        doomConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowBacklash", true);
        prop.comment = "Can Doomsdays have a chance to fail?";
        doomConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowCooldown", true);
        prop.comment = "Should Doomsdays inflict Global cooldown after use?";
        doomConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowDoomKillCharge", true);
        prop.comment = "Should killing enemies recharge your Doom?";
        doomConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowCooldownDefuse", true);
        prop.comment = "Can Cooldown Defuses be used to remove your Global cooldown?";
        doomConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowPartnerDoomsdays", false);
        prop.comment = "Can two people activate a Doomsday combination while near each other? (This hasn't been tested and you should report your results from use)";
        doomConfig[++m] = prop.getBoolean(false);

        prop = config.get(cat.getName(), "maxDoomAmount", 500);
        prop.comment = "The highest Doom amount you can have.";
        maxDoomAmount = clampPositive(prop.getInt(500));

        prop = config.get(cat.getName(), "doomRechargeRate", 1);
        prop.comment = "The speed at which you naturally recharge Doom. Maxes out at 20, which would be one recharge per tick essentially.";
        doomRechargeRate = clamp(prop.getInt(1), 1, 20);

        prop = config.get(cat.getName(), "doomConsumeRarity", 3);
        prop.comment = "The chance of getting a Doom Consume out of a chest, higher value is higher chance.";
        doomConsumeRarity = clamp(prop.getInt(3), 1, 250);

        prop = config.get(cat.getName(), "cooldownDefuseRarity", 5);
        prop.comment = "The chance of getting a Cooldown Defuse out of a chest, higher value is higher chance.";
        cooldownDefuseRarity = clamp(prop.getInt(5), 1, 250);

        prop = config.get(cat.getName(), "doomConsumeRefillAmount", 50);
        prop.comment = "The percentage of Doom that you'll refill upon use of a Doom Consume.";
        consumeRefillAmount = clamp(prop.getInt(50), 1, 100);

        prop = config.get(cat.getName(), "cooldownDefuseRefillAmount", 30);
        prop.comment = "The amount of cooldown that you'll remove upon use of a Cooldown Defuse.";
        defuseRefillAmount = clampPositive(prop.getInt(30));

        prop = config.get(cat.getName(), "backlashChance", 5);
        prop.comment = "The chance that Backlash will occur, higher value is higher chance.";
        backlashChance = clamp(prop.getInt(5), 1, 100);

        prop = config.get(cat.getName(), "crucialMomentChance", 5);
        prop.comment = "The chance that a Crucial Moment will occur, higher value is higher chance.";
        crucialMomentChance = clamp(prop.getInt(5), 1, 100);

        prop = config.get(cat.getName(), "doomConsumeIncreaseAmount", 100);
        prop.comment = "The amount of Max Doom you gain per Doom Consume use.";
        doomConsumeAmount = clampPositive(prop.getInt(100));

        prop = config.get(cat.getName(), "maxDoomStartAmount", 100);
        prop.comment = "The default amount of Max Doom that you start with.";
        maxDoomStart = clampPositive(prop.getInt(100));

        prop = config.get(cat.getName(), "doomRechargeAmount", 1);
        prop.comment = "The amount you recharge per recharge tick, also used by the Doom kill charge.";
        doomRechargeAmount = clampPositive(prop.getInt(1));

        prop = config.get(cat.getName(), "partnerDoomsdayDistance", 12);
        prop.comment = "How far away you can be from someone to activate a Partner Combination Doomsday.";
        partnerDoomsdayDistance = clampPositive(prop.getInt(1));

        cat = config.getCategory(CAT_DOOMSDAYS);
        cat.setComment("Set whether each Doomsday is allowed, as well as set their cooldown and their Doom cost.");
        cat.setRequiresWorldRestart(false);

        prop = config.get(cat.getName(), "decayAllow", true);
        doomsdayAllow[m = 1] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "huntersInstinctAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "toxicityAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "berserkerAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "piercingLightAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "natureDrainAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "poisonBreakAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "snipeAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "rapidFireAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "pulseAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "lightShoveAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "fearAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "harmonizerAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "ravageAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "tormentAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "beastlyImpulsesAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "huntersInstinctAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "suicicalTendenciesAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "reaperLaughAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "realityAlterAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "skullCrusherAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "minerSkillsAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "freezeAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "moonlightSonataAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "flightOfTheValkyriesAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "titanfallAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "bloodlustAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "permafrostAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "purgeAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "lightningRushAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "marionetteAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "mindcrackAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "growthSpurtAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "blizzardAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "asphyxiateAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "fireRainAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "dragonsRoarAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "firestormAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "shotgunAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "guardiansCallAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "hardenAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "sharpenAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "flashAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "septicsAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "kurayamiAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "lifeShareAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "deathMarkAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "paradigmShiftAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "adrenalineAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "escapeAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "giftOfTheGodsAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "gamblerAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "soulstealerAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "parasiteAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "symbiosisAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "timeCollapseAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "magnetizerAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "ambienceAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "dimentiaAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "deleteAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "laserCutterAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "radiantLightAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "dangerZoneAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "supportAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "purifyAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "recallAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "shuffleAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "blinkAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "evacuateAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "medicAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "resurgenceAllow", true);
        doomsdayAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "decayCost", 40);
        doomsdayCost[m = 1] = clampPositive(prop.getInt(40));

        prop = config.get(cat.getName(), "huntersInstinctCost", 60);
        doomsdayCost[++m] = clampPositive(prop.getInt(60));

        prop = config.get(cat.getName(), "toxicityCost", 40);
        doomsdayCost[++m] = clampPositive(prop.getInt(40));

        prop = config.get(cat.getName(), "berserkerCost", 50);
        doomsdayCost[++m] = clampPositive(prop.getInt(50));

        prop = config.get(cat.getName(), "piercingLightCost", 60);
        doomsdayCost[++m] = clampPositive(prop.getInt(60));

        prop = config.get(cat.getName(), "natureDrainCost", 12);
        doomsdayCost[++m] = clampPositive(prop.getInt(12));

        prop = config.get(cat.getName(), "poisonBreakCost", 30);
        doomsdayCost[++m] = clampPositive(prop.getInt(30));

        prop = config.get(cat.getName(), "snipeCost", 90);
        doomsdayCost[++m] = clampPositive(prop.getInt(90));

        prop = config.get(cat.getName(), "rapidFireCost", 8);
        doomsdayCost[++m] = clampPositive(prop.getInt(8));

        prop = config.get(cat.getName(), "pulseCost", 10);
        doomsdayCost[++m] = clampPositive(prop.getInt(10));

        prop = config.get(cat.getName(), "lightShoveCost", 3);
        doomsdayCost[++m] = clampPositive(prop.getInt(3));

        prop = config.get(cat.getName(), "fearCost", 30);
        doomsdayCost[++m] = clampPositive(prop.getInt(30));

        prop = config.get(cat.getName(), "harmonizerCost", 40);
        doomsdayCost[++m] = clampPositive(prop.getInt(40));

        prop = config.get(cat.getName(), "ravageCost", 55);
        doomsdayCost[++m] = clampPositive(prop.getInt(55));

        prop = config.get(cat.getName(), "tormentCost", 45);
        doomsdayCost[++m] = clampPositive(prop.getInt(45));

        prop = config.get(cat.getName(), "beastlyImpulsesCost", 60);
        doomsdayCost[++m] = clampPositive(prop.getInt(60));

        prop = config.get(cat.getName(), "suicidalTendenciesCost", 12);
        doomsdayCost[++m] = clampPositive(prop.getInt(12));

        prop = config.get(cat.getName(), "reaperLaughCost", 16);
        doomsdayCost[++m] = clampPositive(prop.getInt(16));

        prop = config.get(cat.getName(), "realityAlterCost", 40);
        doomsdayCost[++m] = clampPositive(prop.getInt(40));

        prop = config.get(cat.getName(), "skullCrusherCost", 50);
        doomsdayCost[++m] = clampPositive(prop.getInt(50));

        prop = config.get(cat.getName(), "minerSkillsCost", 30);
        doomsdayCost[++m] = clampPositive(prop.getInt(30));

        prop = config.get(cat.getName(), "freezeCost", 30);
        doomsdayCost[++m] = clampPositive(prop.getInt(30));

        prop = config.get(cat.getName(), "moonlightSonataCost", 1);
        doomsdayCost[++m] = clampPositive(prop.getInt(1));

        prop = config.get(cat.getName(), "flightOfTheValkyriesCost", 10);
        doomsdayCost[++m] = clampPositive(prop.getInt(10));

        prop = config.get(cat.getName(), "titanfallCost", 5);
        doomsdayCost[++m] = clampPositive(prop.getInt(5));

        prop = config.get(cat.getName(), "bloodlustCost", 80);
        doomsdayCost[++m] = clampPositive(prop.getInt(80));

        prop = config.get(cat.getName(), "permafrostCost", 6);
        doomsdayCost[++m] = clampPositive(prop.getInt(6));

        prop = config.get(cat.getName(), "purgeCost", 5);
        doomsdayCost[++m] = clampPositive(prop.getInt(5));

        prop = config.get(cat.getName(), "lightningRushCost", 8);
        doomsdayCost[++m] = clampPositive(prop.getInt(8));

        prop = config.get(cat.getName(), "marionetteCost", 3);
        doomsdayCost[++m] = clampPositive(prop.getInt(3));

        prop = config.get(cat.getName(), "mindcrackCost", 45);
        doomsdayCost[++m] = clampPositive(prop.getInt(45));

        prop = config.get(cat.getName(), "growthSpurtCost", 50);
        doomsdayCost[++m] = clampPositive(prop.getInt(50));

        prop = config.get(cat.getName(), "blizzardCost", 10);
        doomsdayCost[++m] = clampPositive(prop.getInt(10));

        prop = config.get(cat.getName(), "asphyxiateCost", 3);
        doomsdayCost[++m] = clampPositive(prop.getInt(3));

        prop = config.get(cat.getName(), "fireRainCost", 8);
        doomsdayCost[++m] = clampPositive(prop.getInt(8));

        prop = config.get(cat.getName(), "dragonsRoarCost", 25);
        doomsdayCost[++m] = clampPositive(prop.getInt(25));

        prop = config.get(cat.getName(), "firestormCost", 10);
        doomsdayCost[++m] = clampPositive(prop.getInt(10));

        prop = config.get(cat.getName(), "shotgunCost", 10);
        doomsdayCost[++m] = clampPositive(prop.getInt(10));

        prop = config.get(cat.getName(), "guardiansCallCost", 75);
        doomsdayCost[++m] = clampPositive(prop.getInt(75));

        prop = config.get(cat.getName(), "hardenCost", 60);
        doomsdayCost[++m] = clampPositive(prop.getInt(60));

        prop = config.get(cat.getName(), "sharpenCost", 75);
        doomsdayCost[++m] = clampPositive(prop.getInt(75));

        prop = config.get(cat.getName(), "flashCost", 10);
        doomsdayCost[++m] = clampPositive(prop.getInt(10));

        prop = config.get(cat.getName(), "septicsCost", 8);
        doomsdayCost[++m] = clampPositive(prop.getInt(8));

        prop = config.get(cat.getName(), "kurayamiCost", 115);
        doomsdayCost[++m] = clampPositive(prop.getInt(115));

        prop = config.get(cat.getName(), "lifeShareCost", 65);
        doomsdayCost[++m] = clampPositive(prop.getInt(65));

        prop = config.get(cat.getName(), "deathMarkCost", 12);
        doomsdayCost[++m] = clampPositive(prop.getInt(12));

        prop = config.get(cat.getName(), "paradigmShiftCost", 50);
        doomsdayCost[++m] = clampPositive(prop.getInt(50));

        prop = config.get(cat.getName(), "adrenalineCost", 33);
        doomsdayCost[++m] = clampPositive(prop.getInt(33));

        prop = config.get(cat.getName(), "escapeCost", 12);
        doomsdayCost[++m] = clampPositive(prop.getInt(12));

        prop = config.get(cat.getName(), "giftOfTheGodsCost", 115);
        doomsdayCost[++m] = clampPositive(prop.getInt(115));

        prop = config.get(cat.getName(), "gamblerCost", 15);
        doomsdayCost[++m] = clampPositive(prop.getInt(15));

        prop = config.get(cat.getName(), "soulstealerCost", 12);
        doomsdayCost[++m] = clampPositive(prop.getInt(12));

        prop = config.get(cat.getName(), "parasiteCost", 16);
        doomsdayCost[++m] = clampPositive(prop.getInt(16));

        prop = config.get(cat.getName(), "symbiosisCost", 18);
        doomsdayCost[++m] = clampPositive(prop.getInt(18));

        prop = config.get(cat.getName(), "timeCollapseCost", 3);
        doomsdayCost[++m] = clampPositive(prop.getInt(3));

        prop = config.get(cat.getName(), "magnetizerCost", 95);
        doomsdayCost[++m] = clampPositive(prop.getInt(95));

        prop = config.get(cat.getName(), "ambienceCost", 1);
        doomsdayCost[++m] = clampPositive(prop.getInt(1));

        prop = config.get(cat.getName(), "dimentiaCost", 99);
        doomsdayCost[++m] = clampPositive(prop.getInt(99));

        prop = config.get(cat.getName(), "deleteCost", 135);
        doomsdayCost[++m] = clampPositive(prop.getInt(135));

        prop = config.get(cat.getName(), "laserCutterCost", 16);
        doomsdayCost[++m] = clampPositive(prop.getInt(16));

        prop = config.get(cat.getName(), "radiantLightCost", 62);
        doomsdayCost[++m] = clampPositive(prop.getInt(62));

        prop = config.get(cat.getName(), "dangerZoneCost", 22);
        doomsdayCost[++m] = clampPositive(prop.getInt(22));

        prop = config.get(cat.getName(), "supportCost", 60);
        doomsdayCost[++m] = clampPositive(prop.getInt(60));

        prop = config.get(cat.getName(), "purifyCost", 42);
        doomsdayCost[++m] = clampPositive(prop.getInt(42));

        prop = config.get(cat.getName(), "recallCost", 82);
        doomsdayCost[++m] = clampPositive(prop.getInt(82));

        prop = config.get(cat.getName(), "shuffleCost", 18);
        doomsdayCost[++m] = clampPositive(prop.getInt(18));

        prop = config.get(cat.getName(), "blinkCost", 15);
        doomsdayCost[++m] = clampPositive(prop.getInt(15));

        prop = config.get(cat.getName(), "evacuateCost", 56);
        doomsdayCost[++m] = clampPositive(prop.getInt(56));

        prop = config.get(cat.getName(), "medicCost", 9);
        doomsdayCost[++m] = clampPositive(prop.getInt(9));

        prop = config.get(cat.getName(), "resurgenceCost", 135);
        doomsdayCost[++m] = clampPositive(prop.getInt(135));

        prop = config.get(cat.getName(), "decayCooldown", 20);
        doomsdayCooldown[m = 1] = clampPositive(prop.getInt(20));

        prop = config.get(cat.getName(), "huntersInstinctCooldown", 25);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(25));

        prop = config.get(cat.getName(), "toxicityCooldown", 15);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(15));

        prop = config.get(cat.getName(), "berserkerCooldown", 15);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(15));

        prop = config.get(cat.getName(), "piercingLightCooldown", 30);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(30));

        prop = config.get(cat.getName(), "natureDrainCooldown", 6);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(6));

        prop = config.get(cat.getName(), "poisonBreakCooldown", 10);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(10));

        prop = config.get(cat.getName(), "snipeCooldown", 55);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(55));

        prop = config.get(cat.getName(), "rapidFireCooldown", 3);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(3));

        prop = config.get(cat.getName(), "pulseCooldown", 6);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(6));

        prop = config.get(cat.getName(), "lightShoveCooldown", 1);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(1));

        prop = config.get(cat.getName(), "fearCooldown", 20);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(20));

        prop = config.get(cat.getName(), "harmonizerCooldown", 30);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(30));

        prop = config.get(cat.getName(), "ravageCooldown", 35);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(35));

        prop = config.get(cat.getName(), "tormentCooldown", 20);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(20));

        prop = config.get(cat.getName(), "beastlyImpulsesCooldown", 50);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(50));

        prop = config.get(cat.getName(), "suicidalTendenciesCooldown", 4);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(4));

        prop = config.get(cat.getName(), "reaperLaughCooldown", 3);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(3));

        prop = config.get(cat.getName(), "realityAlterCooldown", 12);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(12));

        prop = config.get(cat.getName(), "skullCrusherCooldown", 15);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(15));

        prop = config.get(cat.getName(), "minerSkillsCooldown", 20);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(20));

        prop = config.get(cat.getName(), "freezeCooldown", 30);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(30));

        prop = config.get(cat.getName(), "moonlightSonataCooldown", 60);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(60));

        prop = config.get(cat.getName(), "flightOfTheValkyriesCooldown", 10);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(10));

        prop = config.get(cat.getName(), "titanfallCooldown", 10);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(10));

        prop = config.get(cat.getName(), "bloodlustCooldown", 30);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(30));

        prop = config.get(cat.getName(), "permafrostCooldown", 5);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(5));

        prop = config.get(cat.getName(), "purgeCooldown", 4);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(4));

        prop = config.get(cat.getName(), "lightningRushCooldown", 6);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(6));

        prop = config.get(cat.getName(), "marionetteCooldown", 3);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(3));

        prop = config.get(cat.getName(), "mindcrackCooldown", 60);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(60));

        prop = config.get(cat.getName(), "growthSpurtCooldown", 10);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(10));

        prop = config.get(cat.getName(), "blizzardCooldown", 6);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(6));

        prop = config.get(cat.getName(), "asphyxiateCooldown", 3);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(3));

        prop = config.get(cat.getName(), "fireRainCooldown", 5);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(5));

        prop = config.get(cat.getName(), "dragonsRoarCooldown", 15);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(15));

        prop = config.get(cat.getName(), "firestormCooldown", 8);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(8));

        prop = config.get(cat.getName(), "shotgunCooldown", 5);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(5));

        prop = config.get(cat.getName(), "guardiansCallCooldown", 50);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(50));

        prop = config.get(cat.getName(), "hardenCooldown", 4);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(4));

        prop = config.get(cat.getName(), "sharpenCooldown", 6);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(6));

        prop = config.get(cat.getName(), "flashCooldown", 5);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(5));

        prop = config.get(cat.getName(), "septicsCooldown", 6);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(6));

        prop = config.get(cat.getName(), "kurayamiCooldown", 80);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(80));

        prop = config.get(cat.getName(), "lifeShareCooldown", 25);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(25));

        prop = config.get(cat.getName(), "deathMarkCooldown", 10);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(10));

        prop = config.get(cat.getName(), "paradigmShiftCooldown", 0);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(0));

        prop = config.get(cat.getName(), "adrenalineCooldown", 21);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(21));

        prop = config.get(cat.getName(), "escapeCooldown", 38);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(38));

        prop = config.get(cat.getName(), "giftOfTheGodsCooldown", 100);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(100));

        prop = config.get(cat.getName(), "gamblerCooldown", 20);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(20));

        prop = config.get(cat.getName(), "soulstealerCooldown", 8);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(8));

        prop = config.get(cat.getName(), "parasiteCooldown", 12);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(12));

        prop = config.get(cat.getName(), "symbiosisCooldown", 14);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(14));

        prop = config.get(cat.getName(), "timeCollapseCooldown", 2);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(2));

        prop = config.get(cat.getName(), "magnetizerCooldown", 65);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(65));

        prop = config.get(cat.getName(), "ambienceCooldown", 1);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(1));

        prop = config.get(cat.getName(), "dimentiaCooldown", 77);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(77));

        prop = config.get(cat.getName(), "deleteCooldown", 125);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(125));

        prop = config.get(cat.getName(), "laserCutterCooldown", 15);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(15));

        prop = config.get(cat.getName(), "radiantLightCooldown", 65);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(65));

        prop = config.get(cat.getName(), "dangerZoneCooldown", 24);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(24));

        prop = config.get(cat.getName(), "supportCooldown", 16);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(16));

        prop = config.get(cat.getName(), "purifyCooldown", 42);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(42));

        prop = config.get(cat.getName(), "recallCooldown", 22);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(22));

        prop = config.get(cat.getName(), "shuffleCooldown", 8);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(8));

        prop = config.get(cat.getName(), "blinkCooldown", 1);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(1));

        prop = config.get(cat.getName(), "evacuateCooldown", 77);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(77));

        prop = config.get(cat.getName(), "medicCooldown", 9);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(9));

        prop = config.get(cat.getName(), "resurgenceCooldown", 55);
        doomsdayCooldown[++m] = clampPositive(prop.getInt(55));

        cat = config.getCategory(CAT_WEAPON);
        cat.setComment("Modify non-Doomsday abilities of weapons, setting whether they are allowed and their cost.");
        cat.setRequiresWorldRestart(false);

        prop = config.get(cat.getName(), "beastlyClawsComboAllow", true);
        doomAbility[m = 0] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "blindingLightSolarBombAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "blindingLightBurnAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "blindingLightProjectileDeflectAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "butcherCritKnockbackAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "butcherKnockbackResistanceBuffAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "butcherWeaknessDebuffAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "celestialAegisDamageReductionAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "celestialLongbowTeleportAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "dragonFangBurnAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "dragonFangLargeFireballAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "dragonFangExtinguishAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "frozenLightningSlownessDebuffAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "frozenLightningLightningStrikeAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "frozenLightningIcicleAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "gravitySpikeLaunchAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "guiltyThornPoisonStunDebuffAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "harmonyBellHarmonyDebuffAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "harmonyBellHealingAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "mourningStarSelfDestructAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "mourningStarLookExplosionAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "paranoiaFearSubmissionDebuffAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "paranoiaDarkEnergySprayAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "paranoiaSingleDarkEnergyAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "pitchBlackThrowAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "reaperScytheSmallPumpkinbombAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "reaperScytheLargePumpkinbombAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "splinterRandomDirectonHitAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "splinterGroupRandomDirectionHitAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "thardusSlownessDebuffAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "thardusIcicleAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "titanLightningHitAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "titanLightningStrikesAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "titanLightningAbsorbAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "witheringAxeWitherDebuffAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "witherAxeWitherSkullAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "witheringAxeBlueWitherSkullAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "dragonFangFlamethrowerAllow", true);
        doomAbility[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "beastlyClawsComboCost", 0);
        doomAbilityCost[m = 0] = clampPositive(prop.getInt(0));

        prop = config.get(cat.getName(), "blindingLightSolarBombCost", 15);
        doomAbilityCost[++m] = clampPositive(prop.getInt(15));

        prop = config.get(cat.getName(), "blindingLightBurnCost", 10);
        doomAbilityCost[++m] = clampPositive(prop.getInt(10));

        prop = config.get(cat.getName(), "blindingLightProjectileDeflectCost", 5);
        doomAbilityCost[++m] = clampPositive(prop.getInt(5));

        prop = config.get(cat.getName(), "butcherCritKnockbackCost", 1);
        doomAbilityCost[++m] = clampPositive(prop.getInt(1));

        prop = config.get(cat.getName(), "butcherKnockbackResistanceCost", 0);
        doomAbilityCost[++m] = clampPositive(prop.getInt(0));

        prop = config.get(cat.getName(), "butcherWeaknessDebuffCost", 0);
        doomAbilityCost[++m] = clampPositive(prop.getInt(0));

        prop = config.get(cat.getName(), "celestialAegisDamageReductionCost", 0);
        doomAbilityCost[++m] = clampPositive(prop.getInt(0));

        prop = config.get(cat.getName(), "celestialLongbowTeleportCost", 0);
        doomAbilityCost[++m] = clampPositive(prop.getInt(0));

        prop = config.get(cat.getName(), "dragonFangBurnCost", 1);
        doomAbilityCost[++m] = clampPositive(prop.getInt(1));

        prop = config.get(cat.getName(), "dragonFangLargeFireballCost", 10);
        doomAbilityCost[++m] = clampPositive(prop.getInt(10));

        prop = config.get(cat.getName(), "dragonFangExtinguishCost", 1);
        doomAbilityCost[++m] = clampPositive(prop.getInt(1));

        prop = config.get(cat.getName(), "frozenLightningSlownessDebuffCost", 3);
        doomAbilityCost[++m] = clampPositive(prop.getInt(3));

        prop = config.get(cat.getName(), "frozenLightningLightningStrikeCost", 20);
        doomAbilityCost[++m] = clampPositive(prop.getInt(20));

        prop = config.get(cat.getName(), "frozenLightningIcicleCost", 3);
        doomAbilityCost[++m] = clampPositive(prop.getInt(3));

        prop = config.get(cat.getName(), "gravitySpikeLaunchCost", 7);
        doomAbilityCost[++m] = clampPositive(prop.getInt(7));

        prop = config.get(cat.getName(), "guiltyThornPoisonStunDebuffCost", 5);
        doomAbilityCost[++m] = clampPositive(prop.getInt(5));

        prop = config.get(cat.getName(), "harmonyBellHarmonyDebuffCost", 3);
        doomAbilityCost[++m] = clampPositive(prop.getInt(3));

        prop = config.get(cat.getName(), "harmonyBellHealingCost", 1);
        doomAbilityCost[++m] = clampPositive(prop.getInt(1));

        prop = config.get(cat.getName(), "mourningStarSelfDestructCost", 25);
        doomAbilityCost[++m] = clampPositive(prop.getInt(25));

        prop = config.get(cat.getName(), "mourningStarLookExplosionCost", 30);
        doomAbilityCost[++m] = clampPositive(prop.getInt(30));

        prop = config.get(cat.getName(), "paranoiaFearSubmissionDebuffCost", 10);
        doomAbilityCost[++m] = clampPositive(prop.getInt(10));

        prop = config.get(cat.getName(), "paranoiaDarkEnergySprayCost", 15);
        doomAbilityCost[++m] = clampPositive(prop.getInt(15));

        prop = config.get(cat.getName(), "paranoiaSingleDarkEnergyCost", 5);
        doomAbilityCost[++m] = clampPositive(prop.getInt(5));

        prop = config.get(cat.getName(), "pitchBlackThrowCost", 5);
        doomAbilityCost[++m] = clampPositive(prop.getInt(5));

        prop = config.get(cat.getName(), "reaperScytheSmallPumpkinbombCost", 5);
        doomAbilityCost[++m] = clampPositive(prop.getInt(5));

        prop = config.get(cat.getName(), "reaperScytheLargePumpkinbombCost", 15);
        doomAbilityCost[++m] = clampPositive(prop.getInt(15));

        prop = config.get(cat.getName(), "splinterRandomDirectionHitCost", 3);
        doomAbilityCost[++m] = clampPositive(prop.getInt(3));

        prop = config.get(cat.getName(), "splinterGroupRandomDirectionHitCost", 10);
        doomAbilityCost[++m] = clampPositive(prop.getInt(10));

        prop = config.get(cat.getName(), "thardusSlownessDebuffCost", 5);
        doomAbilityCost[++m] = clampPositive(prop.getInt(5));

        prop = config.get(cat.getName(), "thardusIcicleCost", 5);
        doomAbilityCost[++m] = clampPositive(prop.getInt(5));

        prop = config.get(cat.getName(), "titanLightningHitCost", 10);
        doomAbilityCost[++m] = clampPositive(prop.getInt(10));

        prop = config.get(cat.getName(), "titanLightningStrikesCost", 20);
        doomAbilityCost[++m] = clampPositive(prop.getInt(20));

        prop = config.get(cat.getName(), "titanLightningAbsorbCost", 5);
        doomAbilityCost[++m] = clampPositive(prop.getInt(5));

        prop = config.get(cat.getName(), "witheringAxeWitherDebuffCost", 5);
        doomAbilityCost[++m] = clampPositive(prop.getInt(5));

        prop = config.get(cat.getName(), "witheringAxeWitherSkullCost", 5);
        doomAbilityCost[++m] = clampPositive(prop.getInt(5));

        prop = config.get(cat.getName(), "witheringAxeBlueWitherSkullCost", 15);
        doomAbilityCost[++m] = clampPositive(prop.getInt(15));

        prop = config.get(cat.getName(), "dragonFangFlamethrowerCost", 3);
        doomAbilityCost[++m] = clampPositive(prop.getInt(3));

        cat = config.getCategory(CAT_ENCHANT);
        cat.setComment("Disable certain enchantments and set their ids");
        cat.setRequiresMcRestart(true);

        prop = config.get(cat.getName(), "decayAllow", true);
        enchantAllow[m = 0] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "slayAllow", true);
        enchantAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "absolveAllow", true);
        enchantAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "vampirismAllow", true);
        enchantAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "leechAllow", true);
        enchantAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "consumeAllow", true);
        enchantAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "distractAllow", true);
        enchantAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "multiplyAllow", true);
        enchantAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "combustionAllow", true);
        enchantAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "runeBreakAllow", true);
        enchantAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "reachAllow", true);
        enchantAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "unbreakableAllow", true);
        enchantAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "rustAllow", true);
        enchantAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "veteranAllow", true);
        enchantAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "deathTouchAllow", true);
        enchantAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "ignitionAllow", true);
        enchantAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "toxicityAllow", true);
        enchantAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "paralysisAllow", true);
        enchantAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "elasticityAllow", true);
        enchantAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "agilityAllow", true);
        enchantAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "runeWalkerAllow", true);
        enchantAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "luminescenceAllow", true);
        enchantAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "decayID", findEnchantID(64));
        idDecay = prop.getInt(findEnchantID(64));

        prop = config.get(cat.getName(), "slayID", findEnchantID(idDecay + 1));
        idSlay = prop.getInt(findEnchantID(idDecay + 1));

        prop = config.get(cat.getName(), "absolveID", findEnchantID(idSlay + 1));
        idAbsolve = prop.getInt(findEnchantID(idSlay + 1));

        prop = config.get(cat.getName(), "vampirismID", findEnchantID(idAbsolve + 1));
        idVampirism = prop.getInt(findEnchantID(idAbsolve + 1));

        prop = config.get(cat.getName(), "leechID", findEnchantID(idVampirism + 1));
        idLeech = prop.getInt(findEnchantID(idVampirism + 1));

        prop = config.get(cat.getName(), "consumeID", findEnchantID(idLeech + 1));
        idConsume = prop.getInt(findEnchantID(idLeech + 1));

        prop = config.get(cat.getName(), "distractID", findEnchantID(idConsume + 1));
        idDistract = prop.getInt(findEnchantID(idConsume + 1));

        prop = config.get(cat.getName(), "multiplyID", findEnchantID(idDistract + 1));
        idMultiply = prop.getInt(findEnchantID(idDistract + 1));

        prop = config.get(cat.getName(), "combustionID", findEnchantID(idMultiply + 1));
        idCombustion = prop.getInt(findEnchantID(idMultiply + 1));

        prop = config.get(cat.getName(), "runeBreakID", findEnchantID(idCombustion + 1));
        idRuneBreak = prop.getInt(findEnchantID(idCombustion + 1));

        prop = config.get(cat.getName(), "reachID", findEnchantID(idRuneBreak + 1));
        idReach = prop.getInt(findEnchantID(idRuneBreak + 1));

        prop = config.get(cat.getName(), "unbreakableID", findEnchantID(idReach + 1));
        idUnbreakable = prop.getInt(findEnchantID(idReach + 1));

        prop = config.get(cat.getName(), "rustID", findEnchantID(idUnbreakable + 1));
        idRust = prop.getInt(findEnchantID(idUnbreakable + 1));

        prop = config.get(cat.getName(), "veteranID", findEnchantID(idRust + 1));
        idVeteran = prop.getInt(findEnchantID(idRust + 1));

        prop = config.get(cat.getName(), "deathTouchID", findEnchantID(idVeteran + 1));
        idDeathTouch = prop.getInt(findEnchantID(idVeteran + 1));

        prop = config.get(cat.getName(), "ignitionID", findEnchantID(idDeathTouch + 1));
        idIgnition = prop.getInt(findEnchantID(idDeathTouch + 1));

        prop = config.get(cat.getName(), "toxicityID", findEnchantID(idIgnition + 1));
        idToxicity = prop.getInt(findEnchantID(idIgnition + 1));

        prop = config.get(cat.getName(), "paralysisID", findEnchantID(idToxicity + 1));
        idParalysis = prop.getInt(findEnchantID(idToxicity + 1));

        prop = config.get(cat.getName(), "elasticityID", findEnchantID(idParalysis + 1));
        idElasticity = prop.getInt(findEnchantID(idParalysis + 1));

        prop = config.get(cat.getName(), "agilityID", findEnchantID(idElasticity + 1));
        idAgility = prop.getInt(findEnchantID(idElasticity + 1));

        prop = config.get(cat.getName(), "runeWalkerID", findEnchantID(idAgility + 1));
        idRuneWalker = prop.getInt(findEnchantID(idAgility + 1));

        prop = config.get(cat.getName(), "luminescenceID", findEnchantID(idRuneWalker + 1));
        idLuminescence = prop.getInt(findEnchantID(idRuneWalker + 1));

        prop = config.get(cat.getName(), "decayWeight", 5);
        wDecay = prop.getInt(5);

        prop = config.get(cat.getName(), "slayWeight", 5);
        wSlay = prop.getInt(5);

        prop = config.get(cat.getName(), "absolveWeight", 5);
        wAbsolve = prop.getInt(5);

        prop = config.get(cat.getName(), "vampirismWeight", 1);
        wVampirism = prop.getInt(1);

        prop = config.get(cat.getName(), "leechWeight", 1);
        wLeech = prop.getInt(1);

        prop = config.get(cat.getName(), "consumeWeight", 1);
        wConsume = prop.getInt(1);

        prop = config.get(cat.getName(), "distractWeight", 1);
        wDistract = prop.getInt(1);

        prop = config.get(cat.getName(), "multiplyWeight", 1);
        wMultiply = prop.getInt(1);

        prop = config.get(cat.getName(), "combustionWeight", 1);
        wCombustion = prop.getInt(1);

        prop = config.get(cat.getName(), "runeBreakWeight", 5);
        wRuneBreak = prop.getInt(5);

        prop = config.get(cat.getName(), "reachWeight", 5);
        wReach = prop.getInt(5);

        prop = config.get(cat.getName(), "unbreakableWeight", 1);
        wUnbreakable = prop.getInt(1);

        prop = config.get(cat.getName(), "rustWeight", 1);
        wRust = prop.getInt(1);

        prop = config.get(cat.getName(), "veteranWeight", 1);
        wVeteran = prop.getInt(1);

        prop = config.get(cat.getName(), "deathTouchWeight", 2);
        wDeathTouch = prop.getInt(2);

        prop = config.get(cat.getName(), "ignitionWeight", 2);
        wIgnition = prop.getInt(2);

        prop = config.get(cat.getName(), "toxicityWeight", 2);
        wToxicity = prop.getInt(2);

        prop = config.get(cat.getName(), "paralysisWeight", 2);
        wParalysis = prop.getInt(2);

        prop = config.get(cat.getName(), "elasticityWeight", 4);
        wElasticity = prop.getInt(4);

        prop = config.get(cat.getName(), "agilityWeight", 1);
        wAgility = prop.getInt(2);

        prop = config.get(cat.getName(), "runeWalkerWeight", 2);
        wRuneWalker = prop.getInt(2);

        prop = config.get(cat.getName(), "luminescenceWeight", 1);
        wLuminescence = prop.getInt(1);

        cat = config.getCategory(CAT_MOBS);
        cat.setComment("Set various aspects of Mobs.");
        cat.setRequiresMcRestart(true);

        prop = config.get(cat.getName(), "allowNormalMobs", true);
        prop.comment = "Are all of the normal mod-exclusive mobs allowed?";
        mobConfig[m = 0] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowMiniBosses", true);
        prop.comment = "Are all of the Mini-Boss variants of some of the mobs allowed?";
        mobConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowBosses", true);
        prop.comment = "Are all of the Bosses allowed? (Disbling the Overlord disables all of it's forms and disables the Seeker mob)";
        mobConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowBossOverworldSpawns", true);
        prop.comment = "Are Bosses able to spawn in the Overworld?";
        mobConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowExtraBossLoot", true);
        prop.comment = "Can Bosses drop a lot of extra normal loot in addition to their specific drops?";
        mobConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowMobTransformation", true);
        prop.comment = "Can normal mobs transform into their Mini-Boss variants?";
        mobConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowMobDynamicHealthScaling", true);
        prop.comment = "Do the mod-exclusive mobs have their health buffed or debuffed based on difficulty?";
        mobConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowNonDimensionMobSpawns", true);
        prop.comment = "Are the mod-exclusive mobs able to spawn naturally outside of the mod's Dimensions?";
        mobConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowMobGroupBuffs", true);
        prop.comment = "Are the mod-exclusive mobs able to randomly spawn with a potion effect when they spawn naturally in a group?";
        mobConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowEasyBosses", true);
        prop.comment = "Can Bosses be fought on Easy difficulty?";
        mobConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowCustomMobSounds", true);
        prop.comment = "Can the mod-exclusive mobs make their custom sounds? (they will still play the Vanilla sounds if disabled)";
        mobConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "bossesDenyFlight", false);
        prop.comment = "When being near Bosses, do they cancel Flight for the player?";
        mobConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowMobInfighting", true);
        prop.comment = "Can mobs from the mod target other mobs from the mod?";
        mobConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowMobIllumination", true);
        prop.comment = "Can mobs glow via a Luminescence block?";
        mobConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowRandomSupportMob", false);
        prop.comment = "Can Support mobs sometimes spawn and continuously buff other nearby mobs?";
        mobConfig[++m] = prop.getBoolean(false);

        prop = config.get(cat.getName(), "commonMobDropChance", 25);
        prop.comment = "Affects the chances of getting common mob drops from the looting amount you killed mobs with, only affects mod-exclusive entities.";
        commonDropRate = clamp(prop.getInt(25), 1, 200);

        prop = config.get(cat.getName(), "rareMobDropChance", 5);
        prop.comment = "Affects the chances of getting rare mob drops from the looting amount you killed mobs with, only affects mod-exclusive entities.";
        rareDropRate = clamp(prop.getInt(5), 1, 100);

        prop = config.get(cat.getName(), "mobTransformationChance", 3);
        mobTransformationChance = clamp(prop.getInt(3), 1, 100);

        prop = config.get(cat.getName(), "bossDamageCap", 25);
        bossDamageCap = clampPositive(prop.getInt(25));

        prop = config.get(cat.getName(), "groupBuffChance", 25);
        groupBuffChance = clamp(prop.getInt(15), 1, 200);

        cat = config.getCategory(CAT_MOBSTATS);
        cat.setComment("Change mob stats and allowances.");
        cat.setRequiresMcRestart(true);

        prop = config.get(cat.getName(), "jabbaAllow", true);
        mobAllow[m = 0] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "jannaAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "plagueAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "gragulAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "minotaurAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "inklingAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "ragrAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "pumpkinheadAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "tragicNekoAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "toxAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "poxAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "cryseAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "starCryseAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "norVoxAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "starVoxAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "pirahAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "stinAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "stinBabyAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "kindlingSpiritAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "abominationAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "erkelAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "sirvAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "psygoteAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "lockbotAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "nanoSwarmAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "snowGolemAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "hunterAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "harvesterAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "seekerAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "archangelAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "ireAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "fuseaAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "ranmasAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "parasmiteAllow", true);
        mobAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "avrisAllow", true);
        mobAllow[++m] = prop.getBoolean(true);
        /*
         * prop = config.get(cat.getName(), "pyragrAllow", true);
         * mobAllow[++m] = prop.getBoolean(true);
         * prop = config.get(cat.getName(), "blistAllow", true);
         * mobAllow[++m] = prop.getBoolean(true);
         * prop = config.get(cat.getName(), "thorgAllow", true);
         * mobAllow[++m] = prop.getBoolean(true);
         */ // TODO blist, thorg and pyragr allowance

        prop = config.get(cat.getName(), "jarraAllow", true);
        miniBossAllow[m = 0] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "kragulAllow", true);
        miniBossAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "magmoxAllow", true);
        miniBossAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "megaCryseAllow", true);
        miniBossAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "voxStellarumAllow", true);
        miniBossAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "greaterStinAllow", true);
        miniBossAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "stinKingAllow", true);
        miniBossAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "stinQueenAllow", true);
        miniBossAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "aegarAllow", true);
        miniBossAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "volatileFuseaAllow", true);
        miniBossAllow[++m] = prop.getBoolean(true);
        /*
         * prop = config.get(cat.getName(), "aggroAllow", true);
         * miniBossAllow[++m] = prop.getBoolean(true);
         */ // TODO aggro allowance

        prop = config.get(cat.getName(), "apisAllow", true);
        bossAllow[m = 0] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "skultarAllow", true);
        bossAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "kitsunakumaAllow", true);
        bossAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "empariahAllow", true);
        bossAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "timeControllerAllow", true);
        bossAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "polarisAllow", true);
        bossAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "enyvilAllow", true);
        bossAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "claymationAllow", true);
        bossAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "overlordAllow", true);
        bossAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "jabbaStats", new double[] { 40.0, 0.275, 5.5, 32.0, 0.0, 0 });
        jabbaStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "jannaStats", new double[] { 20.0, 0.325, 4.5, 32.0, 0.0, 0 });
        jannaStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "jarraStats", new double[] { 70.0, 0.360, 6.5, 64.0, 0.0, 0 });
        jarraStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "plagueStats", new double[] { 4.0, 0.235, 1.0, 16.0, 0.0, 0 });
        plagueStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "gragulStats", new double[] { 5.0, 0.350, 5.0, 32.0, 0.0, 0 });
        gragulStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "kragulStats", new double[] { 8.0, 0.380, 5.0, 5.0, 32.0, 0 });
        kragulStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "minotaurStats", new double[] { 32.0, 0.350, 7.0, 32.0, 0.5, 6 });
        minotaurStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "inklingStats", new double[] { 16.0, 0.230, 1.0, 32.0, 0.0, 0 });
        inklingStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "ragrStats", new double[] { 65.0, 0.380, 7.0, 32.0, 1.0, 10 });
        ragrStats = verifyStat(prop.getDoubleList());
        /*
         * prop = config.get(cat.getName(), "pyragrStats", new double[] {75.0, 0.380, 8.0, 32.0, 1.0, 16});
         * pyragrStats = verifyStat(prop.getDoubleList()); //TODO hide new mobs from config unless they are going to be
         * in next release
         * prop = config.get(cat.getName(), "aggroStats", new double[] {135.0, 0.380, 14.0, 16.0, 2.0, 16});
         * aggroStats = verifyStat(prop.getDoubleList());
         */

        prop = config.get(cat.getName(), "pumpkinheadStats", new double[] { 60.0, 0.275, 6.0, 32.0, 0.0, 18 });
        pumpkinheadStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "tragicNekoStats", new double[] { 80.0, 0.335, 6.0, 32.0, 0.0, 0 });
        tragicNekoStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "toxStats", new double[] { 40.0, 0.050, 8.0, 64.0, 1.0, 16 });
        toxStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "poxStats", new double[] { 30.0, 0.050, 4.0, 64.0, 0.7, 10 });
        poxStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "magmoxStats", new double[] { 75.0, 0.050, 15.0, 64.0, 1.0, 20 });
        magmoxStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "cryseStats", new double[] { 35.0, 0.285, 4.0, 48.0, 0.0, 4 });
        cryseStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "starCryseStats", new double[] { 55.0, 0.315, 4.0, 48.0, 0.0, 4 });
        starCryseStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "megaCryseStats", new double[] { 50.0, 0.310, 6.0, 48.0, 1.0, 18 });
        megaCryseStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "norVoxStats", new double[] { 30.0, 0.390, 4.0, 32.0, 0.25, 8 });
        norVoxStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "starVoxStats", new double[] { 40.0, 0.390, 4.0, 32.0, 0.25, 16 });
        starVoxStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "voxStellarumStats", new double[] { 150.0, 0.460, 4.0, 64.0, 0.2, 16 });
        voxStellarumStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "pirahStats", new double[] { 10.0, 0.450, 3.0, 16.0, 0.0, 0 });
        pirahStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "goldenPirahStats", new double[] { 25.0, 0.450, 7.5, 16.0, 0.0, 0 });
        goldenPirahStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "stinStats", new double[] { 40.0, 0.246, 10.0, 32.0, 0.5, 6 });
        stinStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "stinBabyStats", new double[] { 16.0, 0.346, 6.0, 32.0, 0.0, 0 });
        stinBabyStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "greaterStinStats", new double[] { 80.0, 0.276, 14.0, 24.0, 1.0, 12 });
        greaterStinStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "stinKingStats", new double[] { 100.0, 0.226, 20.0, 32.0, 2.0, 20 });
        stinKingStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "stinQueenStats", new double[] { 160.0, 0.186, 12.0, 24, 2.0, 10 });
        stinQueenStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "kindlingSpiritStats", new double[] { 8.0, 0.476, 1.0, 16.0, 0.0, 0 });
        kindlingSpiritStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "abominationStats", new double[] { 45.0, 0.276, 7.0, 32.0, 0.5, 4 });
        abominationStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "erkelStats", new double[] { 16.0, 0.476, 1.0, 16.0, 0.0, 0 });
        erkelStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "sirvStats", new double[] { 8.0, 0.375, 14.0, 64.0, 0.5, 0 });
        sirvStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "psygoteStats", new double[] { 52.0, 0.290, 8.0, 32.0, 0.65, 10 });
        psygoteStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "lockbotStats", new double[] { 22.0, 0.0, 1.0, 8.0, 100.0, 8 });
        lockbotStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "nanoSwarmStats", new double[] { 6.0, 0.335, 2.0, 64.0, 0.0, 0 });
        nanoSwarmStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "hunterStats", new double[] { 16.0, 0.236, 4.0, 32.0, 0.0, 0 });
        hunterStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "harvesterStats", new double[] { 56.0, 0.145, 0.0, 16.0, 100.0, 20 });
        harvesterStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "seekerStats", new double[] { 30.0, 0.0, 1.0, 48.0, 100.0, 24 });
        seekerStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "archangelStats", new double[] { 45.0, 0.0, 1.0, 32.0, 0.5, 12 });
        archangelStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "ireStats", new double[] { 25.0, 0.0, 1.0, 16.0, 100.0, 0 });
        ireStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "fuseaStats", new double[] { 10.0, 0.0, 0.0, 16.0, 100.0, 0 });
        fuseaStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "volatileFuseaStats", new double[] { 18.0, 0.0, 0.0, 32.0, 100.0, 0 });
        volatileFuseaStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "ranmasStats", new double[] { 50.0, 0.0, 1.0, 32.0, 100.0, 24 });
        ranmasStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "parasmiteStats", new double[] { 10.0, 0.0, 1.0, 16.0, 0.0, 0 });
        parasmiteStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "kurayamiStats", new double[] { 120.0, 0.420, 12.0, 64.0, 0.4, 10 });
        kurayamiStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "avrisStats", new double[] { 75.0, 0.312, 2.0, 64.0, 0.6, 16 });
        avrisStats = verifyStat(prop.getDoubleList());
        /*
         * prop = config.get(cat.getName(), "blistStats", new double[] {6.0, 0.462, 8.0, 16.0, 2.0, 0});
         * blistStats = verifyStat(prop.getDoubleList());
         * prop = config.get(cat.getName(), "thorgStats", new double[] {17.0, 0.428, 4.0, 32.0, 0.0, 0});
         * thorgStats = verifyStat(prop.getDoubleList());
         */ // TODO blist and thorg stats

        prop = config.get(cat.getName(), "aegarStats", new double[] { 150.0, 0.185, 26.0, 32.0, 2.5, 24 });
        aegarStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "apisStats", new double[] { 160.0, 0.375, 12.0, 32.0, 1.0, 16 });
        apisStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "skultarStats", new double[] { 220.0, 0.350, 16.0, 32.0, 1.0, 20 });
        skultarStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "kitsunakumaStats", new double[] { 80.0, 0.420, 6.0, 64.0, 0.0, 0 });
        kitsunakumaStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "polarisStats", new double[] { 120.0, 0.440, 5.0, 64.0, 0.0, 14 });
        polarisStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "empariahStats", new double[] { 140.0, 0.326, 16.0, 48.0, 2.0, 22 });
        empariahStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "timeControllerStats", new double[] { 350.0, 0.366, 6.0, 64.0, 0.5, 18 });
        timeControllerStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "enyvilStats", new double[] { 450.0, 0.276, 24.0, 48.0, 1.0, 4 });
        enyvilStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "claymationStats", new double[] { 150.0, 0.320, 12.0, 32.0, 1.0, 18 });
        claymationStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "overlordCocoonStats", new double[] { 500.0, 0.226, 24.0, 64.0, 4.5, 0 });
        overlordCocoonStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "overlordCombatStats", new double[] { 500.0, 0.326, 24.0, 64.0, 4.5, 0 });
        overlordCombatStats = verifyStat(prop.getDoubleList());

        prop = config.get(cat.getName(), "overlordCoreStats", new double[] { 1000.0, 0.326, 24.0, 64.0, 4.5, 0 });
        overlordCoreStats = verifyStat(prop.getDoubleList());

        cat = config.getCategory(CAT_MOBSPAWNS);
        cat.setComment(
            "Set spawn chances and group sizes for each mob, can also override the Vanilla/Modded biome spawns.");
        cat.setRequiresMcRestart(true);

        prop = config.get(cat.getName(), "jabbaSpawnChance", 75);
        jabbaSC = clamp(prop.getInt(75), 1, 1000);

        prop = config.get(cat.getName(), "jannaSpawnChance", 50);
        jannaSC = clamp(prop.getInt(50), 1, 1000);

        prop = config.get(cat.getName(), "jarraSpawnChance", 5);
        jarraSC = clamp(prop.getInt(5), 1, 1000);

        prop = config.get(cat.getName(), "plagueSpawnChance", 50);
        plagueSC = clamp(prop.getInt(50), 1, 1000);

        prop = config.get(cat.getName(), "gragulSpawnChance", 25);
        gragulSC = clamp(prop.getInt(25), 1, 1000);

        prop = config.get(cat.getName(), "kragulSpawnChance", 5);
        kragulSC = clamp(prop.getInt(5), 1, 1000);

        prop = config.get(cat.getName(), "minotaurSpawnChance", 75);
        minotaurSC = clamp(prop.getInt(75), 1, 1000);

        prop = config.get(cat.getName(), "inklingSpawnChance", 75);
        inklingSC = clamp(prop.getInt(75), 1, 1000);

        prop = config.get(cat.getName(), "ragrSpawnChance", 25);
        ragrSC = clamp(prop.getInt(25), 1, 1000);
        /*
         * prop = config.get(cat.getName(), "pyragrSpawnChance", 25);
         * pyragrSC = prop.getInt(25);
         * prop = config.get(cat.getName(), "aggroSpawnChance", 5);
         * aggroSC = prop.getInt(5);
         */ // TODO pyragr and aggro spawn chance

        prop = config.get(cat.getName(), "pumpkinheadSpawnChance", 25);
        pumpkinheadSC = clamp(prop.getInt(25), 1, 1000);

        prop = config.get(cat.getName(), "tragicNekoSpawnChance", 50);
        tragicNekoSC = clamp(prop.getInt(50), 1, 1000);

        prop = config.get(cat.getName(), "toxSpawnChance", 50);
        toxSC = clamp(prop.getInt(50), 1, 1000);

        prop = config.get(cat.getName(), "poxSpawnChance", 50);
        poxSC = clamp(prop.getInt(50), 1, 1000);

        prop = config.get(cat.getName(), "magmoxSpawnChance", 5);
        magmoxSC = clamp(prop.getInt(5), 1, 1000);

        prop = config.get(cat.getName(), "cryseSpawnChance", 75);
        cryseSC = clamp(prop.getInt(75), 1, 1000);

        prop = config.get(cat.getName(), "starCryseSpawnChance", 75);
        starCryseSC = clamp(prop.getInt(75), 1, 1000);

        prop = config.get(cat.getName(), "megaCryseSpawnChance", 5);
        megaCryseSC = clamp(prop.getInt(5), 1, 1000);

        prop = config.get(cat.getName(), "norVoxSpawnChance", 25);
        norVoxSC = clamp(prop.getInt(25), 1, 1000);

        prop = config.get(cat.getName(), "starVoxSpawnChance", 25);
        starVoxSC = clamp(prop.getInt(25), 1, 1000);

        prop = config.get(cat.getName(), "voxStellarumSpawnChance", 5);
        voxStellarumSC = clamp(prop.getInt(5), 1, 1000);

        prop = config.get(cat.getName(), "pirahSpawnChance", 25);
        pirahSC = clamp(prop.getInt(25), 1, 1000);

        prop = config.get(cat.getName(), "stinSpawnChance", 50);
        stinSC = clamp(prop.getInt(50), 1, 1000);

        prop = config.get(cat.getName(), "greaterStinSpawnChance", 5);
        greaterStinSC = clamp(prop.getInt(5), 1, 1000);

        prop = config.get(cat.getName(), "stinKingSpawnChance", 5);
        stinKingSC = clamp(prop.getInt(5), 1, 1000);

        prop = config.get(cat.getName(), "stinQueenSpawnChance", 5);
        stinQueenSC = clamp(prop.getInt(5), 1, 1000);

        prop = config.get(cat.getName(), "kindlingSpiritSpawnChance", 15);
        kindlingSpiritSC = clamp(prop.getInt(15), 1, 1000);

        prop = config.get(cat.getName(), "abominationSpawnChance", 25);
        abominationSC = clamp(prop.getInt(25), 1, 1000);

        prop = config.get(cat.getName(), "erkelSpawnChance", 25);
        erkelSC = clamp(prop.getInt(25), 1, 1000);

        prop = config.get(cat.getName(), "sirvSpawnChance", 50);
        sirvSC = clamp(prop.getInt(50), 1, 1000);

        prop = config.get(cat.getName(), "psygoteSpawnChance", 5);
        psygoteSC = clamp(prop.getInt(5), 1, 1000);

        prop = config.get(cat.getName(), "lockbotSpawnChance", 5);
        lockbotSC = clamp(prop.getInt(5), 1, 1000);

        prop = config.get(cat.getName(), "nanoSwarmSpawnChance", 25);
        nanoSwarmSC = clamp(prop.getInt(25), 1, 1000);

        prop = config.get(cat.getName(), "snowGolemSpawnChance", 20);
        snowGolemSC = clamp(prop.getInt(20), 1, 1000);

        prop = config.get(cat.getName(), "hunterSpawnChance", 15);
        hunterSC = clamp(prop.getInt(15), 1, 1000);

        prop = config.get(cat.getName(), "harvesterSpawnChance", 10);
        harvesterSC = clamp(prop.getInt(10), 1, 1000);

        prop = config.get(cat.getName(), "seekerSpawnChance", 5);
        seekerSC = clamp(prop.getInt(5), 1, 1000);

        prop = config.get(cat.getName(), "archangelSpawnChance", 5);
        archangelSC = clamp(prop.getInt(5), 1, 1000);

        prop = config.get(cat.getName(), "ireSpawnChance", 45);
        ireSC = clamp(prop.getInt(45), 1, 1000);

        prop = config.get(cat.getName(), "fuseaSpawnChance", 25);
        fuseaSC = clamp(prop.getInt(25), 1, 1000);

        prop = config.get(cat.getName(), "volatileFuseaSpawnChance", 5);
        volatileFuseaSC = clamp(prop.getInt(5), 1, 1000);

        prop = config.get(cat.getName(), "ranmasSpawnChance", 25);
        ranmasSC = clamp(prop.getInt(25), 1, 1000);

        prop = config.get(cat.getName(), "parasmiteSpawnChance", 25);
        parasmiteSC = clamp(prop.getInt(25), 1, 1000);
        /*
         * prop = config.get(cat.getName(), "blistSpawnChance", 25);
         * blistSC = prop.getInt(25);
         * prop = config.get(cat.getName(), "thorgSpawnChance", 25);
         * thorgSC = prop.getInt(25);
         */ // TODO blist and thorg spawn chance

        prop = config.get(cat.getName(), "aegarSpawnChance", 5);
        aegarSC = clamp(prop.getInt(5), 1, 1000);

        prop = config.get(cat.getName(), "apisSpawnChance", 5);
        apisSC = clamp(prop.getInt(5), 1, 1000);

        prop = config.get(cat.getName(), "skultarSpawnChance", 5);
        skultarSC = clamp(prop.getInt(5), 1, 1000);

        prop = config.get(cat.getName(), "kitsunakumaSpawnChance", 5);
        kitsunakumaSC = clamp(prop.getInt(5), 1, 1000);

        prop = config.get(cat.getName(), "empariahSpawnChance", 5);
        empariahSC = clamp(prop.getInt(5), 1, 1000);

        prop = config.get(cat.getName(), "timeControllerSpawnChance", 5);
        timeControllerSC = clamp(prop.getInt(5), 1, 1000);

        prop = config.get(cat.getName(), "polarisSpawnChance", 5);
        polarisSC = clamp(prop.getInt(5), 1, 1000);

        prop = config.get(cat.getName(), "enyvilSpawnChance", 5);
        enyvilSC = clamp(prop.getInt(5), 1, 1000);

        prop = config.get(cat.getName(), "claymationSpawnChance", 5);
        claymationSC = clamp(prop.getInt(5), 1, 1000);

        prop = config.get(cat.getName(), "jabbaGroupSize", new int[] { 0, 2 });
        jabbaGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "jannaGroupSize", new int[] { 0, 2 });
        jannaGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "plagueGroupSize", new int[] { 2, 3 });
        plagueGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "gragulGroupSize", new int[] { 0, 1 });
        gragulGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "minotaurGroupSize", new int[] { 0, 1 });
        minotaurGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "inklingGroupSize", new int[] { 2, 5 });
        inklingGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "ragrGroupSize", new int[] { 0, 1 });
        ragrGS = verifyGS(prop.getIntList());
        /*
         * prop = config.get(cat.getName(), "pyragrGroupSize", new int[] {0, 1});
         * pyragrGS = verifyGS(prop.getIntList());
         */ // TODO pyragr group size

        prop = config.get(cat.getName(), "pumpkinheadGroupSize", new int[] { 2, 4 });
        pumpkinheadGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "tragicNekoGroupSize", new int[] { 0, 1 });
        tragicNekoGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "toxGroupSize", new int[] { 0, 1 });
        toxGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "poxGroupSize", new int[] { 0, 1 });
        poxGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "cryseGroupSize", new int[] { 0, 2 });
        cryseGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "starCryseGroupSize", new int[] { 1, 3 });
        starCryseGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "norVoxGroupSize", new int[] { 0, 1 });
        norVoxGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "starVoxGroupSize", new int[] { 2, 5 });
        starVoxGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "pirahGroupSize", new int[] { 4, 6 });
        pirahGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "stinGroupSize", new int[] { 1, 2 });
        stinGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "kindlingSpiritGroupSize", new int[] { 0, 1 });
        kindlingSpiritGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "abominationGroupSize", new int[] { 2, 5 });
        abominationGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "erkelGroupSize", new int[] { 1, 2 });
        erkelGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "sirvGroupSize", new int[] { 4, 6 });
        sirvGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "psygoteGroupSize", new int[] { 0, 1 });
        psygoteGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "lockbotGroupSize", new int[] { 0, 1 });
        lockbotGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "nanoSwarmGroupSize", new int[] { 2, 4 });
        nanoSwarmGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "snowGolemGroupSize", new int[] { 0, 1 });
        snowGolemGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "hunterGroupSize", new int[] { 4, 6 });
        hunterGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "harvesterGroupSize", new int[] { 0, 2 });
        harvesterGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "seekerGroupSize", new int[] { 0, 1 });
        seekerGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "archangelGroupSize", new int[] { 0, 1 });
        archangelGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "ireGroupSize", new int[] { 2, 4 });
        ireGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "fuseaGroupSize", new int[] { 0, 2 });
        fuseaGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "ranmasGroupSize", new int[] { 0, 1 });
        ranmasGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "parasmiteGroupSize", new int[] { 0, 1 });
        parasmiteGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "avrisGroupSize", new int[] { 0, 1 });
        avrisGS = verifyGS(prop.getIntList());
        /*
         * prop = config.get(cat.getName(), "blistGroupSize", new int[] {2, 4});
         * blistGS = verifyGS(prop.getIntList());
         * prop = config.get(cat.getName(), "thorgGroupSize", new int[] {1, 3});
         * thorgGS = verifyGS(prop.getIntList());
         */ // TODO blist and thorg group size

        prop = config.get(cat.getName(), "jarraGroupSize", new int[] { 0, 1 });
        jarraGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "kragulGroupSize", new int[] { 0, 1 });
        kragulGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "magmoxGroupSize", new int[] { 0, 1 });
        magmoxGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "megaCryseGroupSize", new int[] { 0, 1 });
        megaCryseGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "voxStellarumGroupSize", new int[] { 0, 1 });
        voxStellarumGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "greaterStinGroupSize", new int[] { 0, 1 });
        greaterStinGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "stinKingGroupSize", new int[] { 0, 1 });
        stinKingGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "stinQueenGroupSize", new int[] { 0, 1 });
        stinQueenGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "voxStellarumGroupSize", new int[] { 0, 1 });
        voxStellarumGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "volatileFuseaGroupSize", new int[] { 0, 1 });
        volatileFuseaGS = verifyGS(prop.getIntList());

        prop = config.get(cat.getName(), "aegarGroupSize", new int[] { 0, 1 });
        aegarGS = verifyGS(prop.getIntList());
        /*
         * prop = config.get(cat.getName(), "aggroGroupSize", new int[] {0, 1});
         * aggroGS = verifyGS(prop.getIntList());
         */ // TODO aggro group size

        prop = config.get(cat.getName(), "jabbaSpawnOverride", false);
        jabbaSOV = prop.getBoolean(false);

        prop = config.get(
            cat.getName(),
            "jabbaSpawnBiomes",
            new int[] { BiomeGenBase.desert.biomeID, BiomeGenBase.desertHills.biomeID, BiomeGenBase.mesa.biomeID,
                BiomeGenBase.hell.biomeID, BiomeGenBase.mesaPlateau.biomeID, BiomeGenBase.mesaPlateau_F.biomeID });
        jabbaSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "plagueSpawnOverride", false);
        plagueSOV = prop.getBoolean(false);

        prop = config.get(
            cat.getName(),
            "plagueSpawnBiomes",
            new int[] { BiomeGenBase.beach.biomeID, BiomeGenBase.birchForest.biomeID,
                BiomeGenBase.birchForestHills.biomeID, BiomeGenBase.coldBeach.biomeID, BiomeGenBase.coldTaiga.biomeID,
                BiomeGenBase.coldTaigaHills.biomeID, BiomeGenBase.deepOcean.biomeID, BiomeGenBase.desert.biomeID,
                BiomeGenBase.desertHills.biomeID, BiomeGenBase.extremeHills.biomeID,
                BiomeGenBase.extremeHillsEdge.biomeID, BiomeGenBase.extremeHillsPlus.biomeID,
                BiomeGenBase.forest.biomeID, BiomeGenBase.forestHills.biomeID, BiomeGenBase.frozenOcean.biomeID,
                BiomeGenBase.frozenRiver.biomeID, BiomeGenBase.hell.biomeID, BiomeGenBase.iceMountains.biomeID,
                BiomeGenBase.icePlains.biomeID, BiomeGenBase.jungle.biomeID, BiomeGenBase.jungleEdge.biomeID,
                BiomeGenBase.jungleHills.biomeID, BiomeGenBase.megaTaiga.biomeID, BiomeGenBase.megaTaigaHills.biomeID,
                BiomeGenBase.mesa.biomeID, BiomeGenBase.mesaPlateau.biomeID, BiomeGenBase.mesaPlateau_F.biomeID,
                BiomeGenBase.mushroomIsland.biomeID, BiomeGenBase.mushroomIslandShore.biomeID,
                BiomeGenBase.ocean.biomeID, BiomeGenBase.plains.biomeID, BiomeGenBase.river.biomeID,
                BiomeGenBase.roofedForest.biomeID, BiomeGenBase.savanna.biomeID, BiomeGenBase.savannaPlateau.biomeID,
                BiomeGenBase.sky.biomeID, BiomeGenBase.stoneBeach.biomeID, BiomeGenBase.swampland.biomeID,
                BiomeGenBase.taiga.biomeID, BiomeGenBase.taigaHills.biomeID });
        plagueSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "gragulSpawnOverride", false);
        gragulSOV = prop.getBoolean(false);

        prop = config.get(
            cat.getName(),
            "gragulSpawnBiomes",
            new int[] { BiomeGenBase.desertHills.biomeID, BiomeGenBase.mesaPlateau.biomeID,
                BiomeGenBase.mesaPlateau_F.biomeID, BiomeGenBase.extremeHills.biomeID,
                BiomeGenBase.extremeHillsPlus.biomeID, BiomeGenBase.megaTaiga.biomeID,
                BiomeGenBase.megaTaigaHills.biomeID, BiomeGenBase.roofedForest.biomeID, BiomeGenBase.swampland.biomeID,
                BiomeGenBase.mushroomIsland.biomeID, BiomeGenBase.mushroomIslandShore.biomeID });
        gragulSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "minotaurSpawnOverride", false);
        minotaurSOV = prop.getBoolean(false);

        prop = config.get(
            cat.getName(),
            "minoturSpawnBiomes",
            new int[] { BiomeGenBase.plains.biomeID, BiomeGenBase.savanna.biomeID, BiomeGenBase.savannaPlateau.biomeID,
                BiomeGenBase.forest.biomeID, BiomeGenBase.forestHills.biomeID, BiomeGenBase.birchForest.biomeID,
                BiomeGenBase.birchForestHills.biomeID, BiomeGenBase.mesa.biomeID, BiomeGenBase.mesaPlateau.biomeID,
                BiomeGenBase.mesaPlateau_F.biomeID, BiomeGenBase.extremeHills.biomeID,
                BiomeGenBase.extremeHillsEdge.biomeID, BiomeGenBase.extremeHillsPlus.biomeID });
        minotaurSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "inklingSpawnOverride", false);
        inklingSOV = prop.getBoolean(false);

        prop = config.get(
            cat.getName(),
            "inklingSpawnBiomes",
            new int[] { BiomeGenBase.beach.biomeID, BiomeGenBase.birchForest.biomeID,
                BiomeGenBase.birchForestHills.biomeID, BiomeGenBase.coldBeach.biomeID, BiomeGenBase.coldTaiga.biomeID,
                BiomeGenBase.coldTaigaHills.biomeID, BiomeGenBase.deepOcean.biomeID, BiomeGenBase.desert.biomeID,
                BiomeGenBase.desertHills.biomeID, BiomeGenBase.extremeHills.biomeID,
                BiomeGenBase.extremeHillsEdge.biomeID, BiomeGenBase.extremeHillsPlus.biomeID,
                BiomeGenBase.forest.biomeID, BiomeGenBase.forestHills.biomeID, BiomeGenBase.frozenOcean.biomeID,
                BiomeGenBase.frozenRiver.biomeID, BiomeGenBase.iceMountains.biomeID, BiomeGenBase.icePlains.biomeID,
                BiomeGenBase.jungle.biomeID, BiomeGenBase.jungleEdge.biomeID, BiomeGenBase.jungleHills.biomeID,
                BiomeGenBase.megaTaiga.biomeID, BiomeGenBase.megaTaigaHills.biomeID, BiomeGenBase.mesa.biomeID,
                BiomeGenBase.mesaPlateau.biomeID, BiomeGenBase.mesaPlateau_F.biomeID,
                BiomeGenBase.mushroomIsland.biomeID, BiomeGenBase.mushroomIslandShore.biomeID,
                BiomeGenBase.ocean.biomeID, BiomeGenBase.plains.biomeID, BiomeGenBase.river.biomeID,
                BiomeGenBase.roofedForest.biomeID, BiomeGenBase.savanna.biomeID, BiomeGenBase.savannaPlateau.biomeID,
                BiomeGenBase.stoneBeach.biomeID, BiomeGenBase.swampland.biomeID, BiomeGenBase.taiga.biomeID,
                BiomeGenBase.taigaHills.biomeID });
        inklingSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "ragrSpawnOverride", false);
        ragrSOV = prop.getBoolean(false);

        prop = config.get(
            cat.getName(),
            "ragrSpawnBiomes",
            new int[] { BiomeGenBase.taiga.biomeID, BiomeGenBase.taigaHills.biomeID, BiomeGenBase.coldTaiga.biomeID,
                BiomeGenBase.coldTaigaHills.biomeID, BiomeGenBase.icePlains.biomeID,
                BiomeGenBase.iceMountains.biomeID });
        ragrSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "pumpkinheadSpawnOverride", false);
        pumpkinheadSOV = prop.getBoolean(false);

        prop = config.get(
            cat.getName(),
            "pumpkinheadSpawnBiomes",
            new int[] { BiomeGenBase.birchForest.biomeID, BiomeGenBase.birchForestHills.biomeID,
                BiomeGenBase.forest.biomeID, BiomeGenBase.forestHills.biomeID, BiomeGenBase.megaTaiga.biomeID,
                BiomeGenBase.megaTaigaHills.biomeID, BiomeGenBase.mushroomIsland.biomeID,
                BiomeGenBase.mushroomIslandShore.biomeID, BiomeGenBase.plains.biomeID,
                BiomeGenBase.roofedForest.biomeID, BiomeGenBase.savanna.biomeID, BiomeGenBase.savannaPlateau.biomeID,
                BiomeGenBase.taiga.biomeID, BiomeGenBase.taigaHills.biomeID });
        pumpkinheadSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "tragicNekoSpawnOverride", false);
        tragicNekoSOV = prop.getBoolean(false);

        prop = config.get(cat.getName(), "tragicNekoSpawnBiomes", new int[] { 0 });
        tragicNekoSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "toxSpawnOverride", false);
        toxSOV = prop.getBoolean(false);

        prop = config.get(
            cat.getName(),
            "toxSpawnBiomes",
            new int[] { BiomeGenBase.roofedForest.biomeID, BiomeGenBase.forest.biomeID,
                BiomeGenBase.forestHills.biomeID, BiomeGenBase.birchForest.biomeID,
                BiomeGenBase.birchForestHills.biomeID, BiomeGenBase.jungle.biomeID, BiomeGenBase.jungleHills.biomeID });
        toxSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "cryseSpawnOverride", false);
        cryseSOV = prop.getBoolean(false);

        prop = config.get(
            cat.getName(),
            "cryseSpawnBiomes",
            new int[] { BiomeGenBase.icePlains.biomeID, BiomeGenBase.iceMountains.biomeID,
                BiomeGenBase.coldTaiga.biomeID, BiomeGenBase.coldTaigaHills.biomeID });
        cryseSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "norVoxSpawnOverride", false);
        norVoxSOV = prop.getBoolean(false);

        prop = config.get(
            cat.getName(),
            "norVoxSpawnBiomes",
            new int[] { BiomeGenBase.birchForest.biomeID, BiomeGenBase.birchForestHills.biomeID,
                BiomeGenBase.deepOcean.biomeID, BiomeGenBase.extremeHills.biomeID,
                BiomeGenBase.extremeHillsEdge.biomeID, BiomeGenBase.extremeHillsPlus.biomeID,
                BiomeGenBase.forest.biomeID, BiomeGenBase.forestHills.biomeID, BiomeGenBase.jungle.biomeID,
                BiomeGenBase.jungleEdge.biomeID, BiomeGenBase.jungleHills.biomeID, BiomeGenBase.megaTaiga.biomeID,
                BiomeGenBase.megaTaigaHills.biomeID, BiomeGenBase.mesa.biomeID, BiomeGenBase.mesaPlateau.biomeID,
                BiomeGenBase.mesaPlateau_F.biomeID, BiomeGenBase.mushroomIsland.biomeID,
                BiomeGenBase.mushroomIslandShore.biomeID, BiomeGenBase.ocean.biomeID, BiomeGenBase.plains.biomeID,
                BiomeGenBase.river.biomeID, BiomeGenBase.roofedForest.biomeID, BiomeGenBase.savanna.biomeID,
                BiomeGenBase.savannaPlateau.biomeID, BiomeGenBase.stoneBeach.biomeID, BiomeGenBase.swampland.biomeID,
                BiomeGenBase.taiga.biomeID, BiomeGenBase.taigaHills.biomeID });
        norVoxSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "pirahSpawnOverride", false);
        pirahSOV = prop.getBoolean(false);

        prop = config.get(
            cat.getName(),
            "pirahSpawnBiomes",
            new int[] { BiomeGenBase.deepOcean.biomeID, BiomeGenBase.ocean.biomeID, BiomeGenBase.river.biomeID });
        pirahSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "stinSpawnOverride", false);
        stinSOV = prop.getBoolean(false);

        prop = config.get(cat.getName(), "stinSpawnBiomes", new int[] { 0 });
        stinSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "kindlingSpiritOverride", false);
        kindlingSpiritSOV = prop.getBoolean(false);

        prop = config.get(
            cat.getName(),
            "kindlingSpiritSpawns",
            new int[] { BiomeGenBase.roofedForest.biomeID, BiomeGenBase.forest.biomeID,
                BiomeGenBase.forestHills.biomeID, BiomeGenBase.birchForest.biomeID,
                BiomeGenBase.birchForestHills.biomeID, BiomeGenBase.jungle.biomeID, BiomeGenBase.jungleHills.biomeID,
                BiomeGenBase.desert.biomeID, BiomeGenBase.desertHills.biomeID, BiomeGenBase.mesa.biomeID });
        kindlingSpiritSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "abominationSpawnOverride", false);
        abominationSOV = prop.getBoolean(false);

        prop = config.get(
            cat.getName(),
            "abominationSpawnBiomes",
            new int[] { BiomeGenBase.icePlains.biomeID, BiomeGenBase.iceMountains.biomeID,
                BiomeGenBase.frozenOcean.biomeID, BiomeGenBase.frozenRiver.biomeID, BiomeGenBase.coldBeach.biomeID,
                BiomeGenBase.coldTaiga.biomeID, BiomeGenBase.coldTaigaHills.biomeID });
        abominationSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "erkelSpawnOverride", false);
        erkelSOV = prop.getBoolean(false);

        prop = config.get(cat.getName(), "erkelSpawnBiomes", new int[] { 0 });
        erkelSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "sirvSpawnOverride", false);
        sirvSOV = prop.getBoolean(false);

        prop = config.get(cat.getName(), "sirvSpawnBiomes", new int[] { 0 });
        sirvSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "psygoteSpawnOverride", false);
        psygoteSOV = prop.getBoolean(false);

        prop = config.get(cat.getName(), "psygoteSpawnBiomes", new int[] { 0 });
        psygoteSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "lockbotSpawnOverride", false);
        lockbotSOV = prop.getBoolean(false);

        prop = config.get(cat.getName(), "lockbotSpawnBiomes", new int[] { 0 });
        lockbotSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "nanoSwarmSpawnOverride", false);
        nanoSwarmSOV = prop.getBoolean(false);

        prop = config.get(cat.getName(), "nanoSwarmSpawnBiomes", new int[] { 0 });
        nanoSwarmSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "hunterSpawnOverride", false);
        hunterSOV = prop.getBoolean(false);

        prop = config.get(cat.getName(), "hunterSpawnBiomes", new int[] { 0 });
        hunterSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "harvesterSpawnOverride", false);
        harvesterSOV = prop.getBoolean(false);

        prop = config.get(cat.getName(), "harvesterSpawnBiomes", new int[] { 0 });
        harvesterSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "seekerSpawnOverride", false);
        seekerSOV = prop.getBoolean(false);

        prop = config.get(cat.getName(), "seekerSpawnBiomes", new int[] { 0 });
        seekerSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "archangelSpawnOverride", false);
        archangelSOV = prop.getBoolean(false);

        prop = config.get(cat.getName(), "archangelSpawnBiomes", new int[] { 0 });
        archangelSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "ireOverride", false);
        ireSOV = prop.getBoolean(false);

        prop = config.get(cat.getName(), "ireSpawnBiomes", new int[] { 0 });
        ireSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "fuseaOverride", false);
        fuseaSOV = prop.getBoolean(false);

        prop = config.get(cat.getName(), "fuseaSpawnBiomes", new int[] { 0 });
        fuseaSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "ranmasSpawnOverride", false);
        ranmasSOV = prop.getBoolean(false);

        prop = config.get(cat.getName(), "ranmasSpawnBiomes", new int[] { 0 });
        ranmasSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "parasmiteSpawnOverride", false);
        parasmiteSOV = prop.getBoolean(false);

        prop = config.get(cat.getName(), "parasmiteSpawnBiomes", new int[] { 0 });
        parasmiteSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "avrisOverride", false);
        avrisSOV = prop.getBoolean(false);

        prop = config.get(cat.getName(), "avrisSpawnBiomes", new int[] { 0 });
        avrisSpawns = prop.getIntList();
        /*
         * prop = config.get(cat.getName(), "blistOverride", false);
         * blistSOV = prop.getBoolean(false);
         * prop = config.get(cat.getName(), "blistSpawnBiomes", new int[] {0});
         * blistSpawns = prop.getIntList();
         * prop = config.get(cat.getName(), "thorgSpawnOverride", false);
         * thorgSOV = prop.getBoolean(false);
         * prop = config.get(cat.getName(), "thorgSpawnBiomes", new int[] {0});
         * thorgSpawns = prop.getIntList();
         */ // TODO blist and thorg spawn overrides

        prop = config.get(cat.getName(), "snowGolemSpawnOverride", false);
        snowGolemSOV = prop.getBoolean(false);

        prop = config.get(
            cat.getName(),
            "snowGolemSpawns",
            new int[] { BiomeGenBase.icePlains.biomeID, BiomeGenBase.iceMountains.biomeID,
                BiomeGenBase.frozenOcean.biomeID, BiomeGenBase.frozenRiver.biomeID, BiomeGenBase.coldBeach.biomeID,
                BiomeGenBase.coldTaiga.biomeID, BiomeGenBase.coldTaigaHills.biomeID });
        snowGolemSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "jarraSpawnOverride", false);
        jarraSOV = prop.getBoolean(false);

        prop = config.get(
            cat.getName(),
            "jarraSpawnBiomes",
            new int[] { BiomeGenBase.hell.biomeID, BiomeGenBase.desert.biomeID, BiomeGenBase.desertHills.biomeID,
                BiomeGenBase.mesa.biomeID, BiomeGenBase.mesaPlateau.biomeID, BiomeGenBase.mesaPlateau_F.biomeID });
        jarraSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "kragulSpawnOverride", false);
        kragulSOV = prop.getBoolean(false);

        prop = config.get(
            cat.getName(),
            "kragulSpawnBiomes",
            new int[] { BiomeGenBase.desertHills.biomeID, BiomeGenBase.mesaPlateau.biomeID,
                BiomeGenBase.mesaPlateau_F.biomeID, BiomeGenBase.extremeHills.biomeID,
                BiomeGenBase.extremeHillsPlus.biomeID, BiomeGenBase.megaTaiga.biomeID,
                BiomeGenBase.megaTaigaHills.biomeID, BiomeGenBase.roofedForest.biomeID, BiomeGenBase.swampland.biomeID,
                BiomeGenBase.mushroomIsland.biomeID, BiomeGenBase.mushroomIslandShore.biomeID });
        kragulSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "magmoxSpawnOverride", false);
        magmoxSOV = prop.getBoolean(false);

        prop = config.get(cat.getName(), "magmoxSpawnBiomes", new int[] { BiomeGenBase.hell.biomeID });
        magmoxSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "megaCryseOverride", false);
        megaCryseSOV = prop.getBoolean(false);

        prop = config.get(
            cat.getName(),
            "megaCryseSpawnBiomes",
            new int[] { BiomeGenBase.icePlains.biomeID, BiomeGenBase.iceMountains.biomeID,
                BiomeGenBase.coldTaiga.biomeID, BiomeGenBase.coldTaigaHills.biomeID });
        megaCryseSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "greaterStinSpawnOverride", false);
        greaterStinSOV = prop.getBoolean(false);

        prop = config.get(cat.getName(), "greaterStinSpawnBiomes", new int[] { 0 });
        greaterStinSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "stinKingSpawnOverride", false);
        stinKingSOV = prop.getBoolean(false);

        prop = config.get(cat.getName(), "stinKingSpawnBiomes", new int[] { 0 });
        stinKingSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "stinQueenSpawnOverride", false);
        stinQueenSOV = prop.getBoolean(false);

        prop = config.get(cat.getName(), "stinQueenSpawnBiomes", new int[] { 0 });
        stinQueenSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "voxStellarumSpawnOverride", false);
        voxStellarumSOV = prop.getBoolean(false);

        prop = config.get(cat.getName(), "voxStellarumSpawnBiomes", new int[] { 0 });
        voxStellarumSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "volatileFuseaSpawnOverride", false);
        volatileFuseaSOV = prop.getBoolean(false);

        prop = config.get(cat.getName(), "volatileFuseaspawnBiomes", new int[] { 0 });
        volatileFuseaSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "aegarSpawnOverride", false);
        aegarSOV = prop.getBoolean(false);

        prop = config.get(cat.getName(), "aegarSpawnBiomes", new int[] { 0 });
        aegarSpawns = prop.getIntList();
        /*
         * prop = config.get(cat.getName(), "aggroSpawnOverride", false);
         * aggroSOV = prop.getBoolean(false);
         * prop = config.get(cat.getName(), "aggroSpawnBiomes", new int[] {0});
         * aggroSpawns = prop.getIntList();
         */ // TODO aggro spawn overrides

        prop = config.get(cat.getName(), "apisSpawnOverride", false);
        apisSOV = prop.getBoolean(false);

        prop = config.get(
            cat.getName(),
            "apisSpawnBiomes",
            new int[] { BiomeGenBase.plains.biomeID, BiomeGenBase.savanna.biomeID, BiomeGenBase.savannaPlateau.biomeID,
                BiomeGenBase.forest.biomeID, BiomeGenBase.forestHills.biomeID, BiomeGenBase.birchForest.biomeID,
                BiomeGenBase.birchForestHills.biomeID, BiomeGenBase.mesa.biomeID, BiomeGenBase.mesaPlateau.biomeID,
                BiomeGenBase.mesaPlateau_F.biomeID, BiomeGenBase.extremeHills.biomeID,
                BiomeGenBase.extremeHillsEdge.biomeID, BiomeGenBase.extremeHillsPlus.biomeID });
        apisSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "skultarSpawnOverride", false);
        skultarSOV = prop.getBoolean(false);

        prop = config.get(
            cat.getName(),
            "skultarSpawnBiomes",
            new int[] { BiomeGenBase.forest.biomeID, BiomeGenBase.forestHills.biomeID, BiomeGenBase.birchForest.biomeID,
                BiomeGenBase.birchForestHills.biomeID });
        skultarSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "kitsunakumaSpawnOverride", false);
        kitsunakumaSOV = prop.getBoolean(false);

        prop = config.get(cat.getName(), "kitsunakumaSpawnBiomes", new int[] { BiomeGenBase.hell.biomeID });
        kitsunakumaSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "empariahSpawnOverride", false);
        empariahSOV = prop.getBoolean(false);

        prop = config.get(
            cat.getName(),
            "empariahSpawnBiomes",
            new int[] { BiomeGenBase.icePlains.biomeID, BiomeGenBase.iceMountains.biomeID,
                BiomeGenBase.frozenOcean.biomeID, BiomeGenBase.frozenRiver.biomeID, BiomeGenBase.coldBeach.biomeID,
                BiomeGenBase.coldTaiga.biomeID, BiomeGenBase.coldTaigaHills.biomeID });
        empariahSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "timeControllerspawnOverride", false);
        timeControllerSOV = prop.getBoolean(false);

        prop = config.get(
            cat.getName(),
            "timeControllerSpawnBiomes",
            new int[] { BiomeGenBase.birchForest.biomeID, BiomeGenBase.birchForestHills.biomeID,
                BiomeGenBase.deepOcean.biomeID, BiomeGenBase.extremeHills.biomeID,
                BiomeGenBase.extremeHillsEdge.biomeID, BiomeGenBase.extremeHillsPlus.biomeID,
                BiomeGenBase.forest.biomeID, BiomeGenBase.forestHills.biomeID, BiomeGenBase.jungle.biomeID,
                BiomeGenBase.jungleEdge.biomeID, BiomeGenBase.jungleHills.biomeID, BiomeGenBase.megaTaiga.biomeID,
                BiomeGenBase.megaTaigaHills.biomeID, BiomeGenBase.mesa.biomeID, BiomeGenBase.mesaPlateau.biomeID,
                BiomeGenBase.mesaPlateau_F.biomeID, BiomeGenBase.mushroomIsland.biomeID,
                BiomeGenBase.mushroomIslandShore.biomeID, BiomeGenBase.ocean.biomeID, BiomeGenBase.plains.biomeID,
                BiomeGenBase.river.biomeID, BiomeGenBase.roofedForest.biomeID, BiomeGenBase.savanna.biomeID,
                BiomeGenBase.savannaPlateau.biomeID, BiomeGenBase.stoneBeach.biomeID, BiomeGenBase.swampland.biomeID,
                BiomeGenBase.taiga.biomeID, BiomeGenBase.taigaHills.biomeID });
        timeControllerSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "polarisSpawnOverride", false);
        polarisSOV = prop.getBoolean(false);

        prop = config.get(
            cat.getName(),
            "polarisSpawnBiomes",
            new int[] { BiomeGenBase.birchForest.biomeID, BiomeGenBase.birchForestHills.biomeID,
                BiomeGenBase.deepOcean.biomeID, BiomeGenBase.extremeHills.biomeID,
                BiomeGenBase.extremeHillsEdge.biomeID, BiomeGenBase.extremeHillsPlus.biomeID,
                BiomeGenBase.forest.biomeID, BiomeGenBase.forestHills.biomeID, BiomeGenBase.jungle.biomeID,
                BiomeGenBase.jungleEdge.biomeID, BiomeGenBase.jungleHills.biomeID, BiomeGenBase.megaTaiga.biomeID,
                BiomeGenBase.megaTaigaHills.biomeID, BiomeGenBase.mesa.biomeID, BiomeGenBase.mesaPlateau.biomeID,
                BiomeGenBase.mesaPlateau_F.biomeID, BiomeGenBase.mushroomIsland.biomeID,
                BiomeGenBase.mushroomIslandShore.biomeID, BiomeGenBase.ocean.biomeID, BiomeGenBase.plains.biomeID,
                BiomeGenBase.river.biomeID, BiomeGenBase.roofedForest.biomeID, BiomeGenBase.savanna.biomeID,
                BiomeGenBase.savannaPlateau.biomeID, BiomeGenBase.stoneBeach.biomeID, BiomeGenBase.swampland.biomeID,
                BiomeGenBase.taiga.biomeID, BiomeGenBase.taigaHills.biomeID });
        polarisSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "enyvilSpawnOverride", false);
        enyvilSOV = prop.getBoolean(false);

        prop = config.get(
            cat.getName(),
            "enyvilSpawnBiomes",
            new int[] { BiomeGenBase.birchForest.biomeID, BiomeGenBase.birchForestHills.biomeID,
                BiomeGenBase.deepOcean.biomeID, BiomeGenBase.extremeHills.biomeID,
                BiomeGenBase.extremeHillsEdge.biomeID, BiomeGenBase.extremeHillsPlus.biomeID,
                BiomeGenBase.forest.biomeID, BiomeGenBase.forestHills.biomeID, BiomeGenBase.jungle.biomeID,
                BiomeGenBase.jungleEdge.biomeID, BiomeGenBase.jungleHills.biomeID, BiomeGenBase.megaTaiga.biomeID,
                BiomeGenBase.megaTaigaHills.biomeID, BiomeGenBase.mesa.biomeID, BiomeGenBase.mesaPlateau.biomeID,
                BiomeGenBase.mesaPlateau_F.biomeID, BiomeGenBase.mushroomIsland.biomeID,
                BiomeGenBase.mushroomIslandShore.biomeID, BiomeGenBase.ocean.biomeID, BiomeGenBase.plains.biomeID,
                BiomeGenBase.river.biomeID, BiomeGenBase.roofedForest.biomeID, BiomeGenBase.savanna.biomeID,
                BiomeGenBase.savannaPlateau.biomeID, BiomeGenBase.stoneBeach.biomeID, BiomeGenBase.swampland.biomeID,
                BiomeGenBase.taiga.biomeID, BiomeGenBase.taigaHills.biomeID });
        enyvilSpawns = prop.getIntList();

        prop = config.get(cat.getName(), "claymationSpawnOverride", false);
        claymationSOV = prop.getBoolean(false);

        prop = config.get(
            cat.getName(),
            "claymationSpawnBiomes",
            new int[] { BiomeGenBase.desert.biomeID, BiomeGenBase.desertHills.biomeID, BiomeGenBase.mesa.biomeID,
                BiomeGenBase.mesaPlateau.biomeID, BiomeGenBase.mesaPlateau_F.biomeID });
        claymationSpawns = prop.getIntList();

        cat = config.getCategory(CAT_POTION);
        cat.setComment("Modify various aspects of Potions.");
        cat.setRequiresMcRestart(true);

        prop = config.get(cat.getName(), "flightAllow", true);
        potionAllow[m = 0] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "aquaSuperiorityAllow", true);
        potionAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "immunityAllow", true);
        potionAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "resurrectionAllow", true);
        potionAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "harmonyAllow", true);
        potionAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "invulnerabilityAllow", true);
        potionAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "clarityAllow", true);
        potionAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "convergenceAllow", true);
        potionAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "divinityAllow", true);
        potionAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "corruptionAllow", true);
        potionAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "disorientationAllow", true);
        potionAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "stunAllow", true);
        potionAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "fearAllow", true);
        potionAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "malnourishAllow", true);
        potionAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "crippleAllow", true);
        potionAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "submissionAllow", true);
        potionAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "inhibitAllow", true);
        potionAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "leadFootAllow", true);
        potionAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "hackedAllow", true);
        potionAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "burnedAllow", true);
        potionAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "flightID", findPotionID(32));
        idFlight = prop.getInt(findPotionID(32));

        prop = config.get(cat.getName(), "aquaSuperiorityID", findPotionID(idFlight + 1));
        idAquaSuperiority = prop.getInt(findPotionID(idFlight + 1));

        prop = config.get(cat.getName(), "immunityID", findPotionID(idAquaSuperiority + 1));
        idImmunity = prop.getInt(findPotionID(idAquaSuperiority + 1));

        prop = config.get(cat.getName(), "resurrectionID", findPotionID(idImmunity + 1));
        idResurrection = prop.getInt(findPotionID(idImmunity + 1));

        prop = config.get(cat.getName(), "harmonyID", findPotionID(idResurrection + 1));
        idHarmony = prop.getInt(findPotionID(idResurrection + 1));

        prop = config.get(cat.getName(), "invulnernabilityID", findPotionID(idHarmony + 1));
        idInvulnerability = prop.getInt(findPotionID(idHarmony + 1));

        prop = config.get(cat.getName(), "clarityID", findPotionID(idInvulnerability + 1));
        idClarity = prop.getInt(findPotionID(idInvulnerability + 1));

        prop = config.get(cat.getName(), "convergenceID", findPotionID(idClarity + 1));
        idConvergence = prop.getInt(findPotionID(idClarity + 1));

        prop = config.get(cat.getName(), "divinityID", findPotionID(idConvergence + 1));
        idDivinity = prop.getInt(findPotionID(idConvergence + 1));

        prop = config.get(cat.getName(), "corruptionID", findPotionID(idDivinity + 1));
        idCorruption = prop.getInt(findPotionID(idDivinity + 1));

        prop = config.get(cat.getName(), "disorientationID", findPotionID(idCorruption + 1));
        idDisorientation = prop.getInt(findPotionID(idCorruption + 1));

        prop = config.get(cat.getName(), "stunID", findPotionID(idDisorientation + 1));
        idStun = prop.getInt(findPotionID(idDisorientation + 1));

        prop = config.get(cat.getName(), "fearID", findPotionID(idStun + 1));
        idFear = prop.getInt(findPotionID(idStun + 1));

        prop = config.get(cat.getName(), "malnourishID", findPotionID(idFear + 1));
        idMalnourish = prop.getInt(findPotionID(idFear + 1));

        prop = config.get(cat.getName(), "crippleID", findPotionID(idMalnourish + 1));
        idCripple = prop.getInt(findPotionID(idMalnourish + 1));

        prop = config.get(cat.getName(), "submissionID", findPotionID(idCripple + 1));
        idSubmission = prop.getInt(findPotionID(idCripple + 1));

        prop = config.get(cat.getName(), "inhibitID", findPotionID(idSubmission + 1));
        idInhibit = prop.getInt(findPotionID(idSubmission + 1));

        prop = config.get(cat.getName(), "leadFootID", findPotionID(idInhibit + 1));
        idLeadFoot = prop.getInt(findPotionID(idInhibit + 1));

        prop = config.get(cat.getName(), "hackedID", findPotionID(idLeadFoot + 1));
        idHacked = prop.getInt(findPotionID(idLeadFoot + 1));

        prop = config.get(cat.getName(), "burnedID", findPotionID(idHacked + 1));
        idBurned = prop.getInt(findPotionID(idHacked + 1));

        cat = config.getCategory(CAT_VANILLA);
        cat.setComment(
            "These toggle the various changes the mod does that explicitly affects Vanilla Minecraft. Ore rate and vein size only affects those ores generated in Vanilla Dimensions.");
        cat.setRequiresMcRestart(true);

        prop = config.get(cat.getName(), "allowMobBuffs", true);
        prop.comment = "Will various Vanilla Mobs gain a Health, Attack Damage, Knockback Resistance or Speed buff?";
        vanillaConfig[m = 0] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowExtraMobEffects", true);
        prop.comment = "Will some mobs gain Potion Effect debuffs and other abilities along with their normal attacks?";
        vanillaConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowAnimalRetribution", true);
        prop.comment = "Can slaying animals sometimes trigger a Lightning strike?";
        vanillaConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowMobModdedArmor", true);
        prop.comment = "Can Vanilla mobs sometimes spawn in with Armor from the mod?";
        vanillaConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowRespawnPunishment", true);
        prop.comment = "Will you get inflicted with negative effects upon respawning after a death?";
        vanillaConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowExtraExplosiveEffects", true);
        prop.comment = "Do explosions inflict extra negative effects on you when hit?";
        vanillaConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowMobBlindnessDebuff", true);
        prop.comment = "Does Blindness reduce the follow range of mobs?";
        vanillaConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowExtraOverworldFlowers", true);
        prop.comment = "Can some of the mod-exclusive flowers generate in Vanilla biomes?";
        vanillaConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowOverworldSilverfishGen", true);
        prop.comment = "Will Silverfish stone generate in lower y-levels in the Overworld?";
        vanillaConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowNetherOreGen", true);
        prop.comment = "Can mod-exclusive ores generate in the Nether?";
        vanillaConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowOverworldOreGen", true);
        prop.comment = "Can mod-exclusive ores generate in the Overworld?";
        vanillaConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowDrudgeGen", true);
        prop.comment = "Can Drudge generate in the Nether?";
        vanillaConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowAnimalAndGolemCorruption", true);
        prop.comment = "Can Animals and Golems become Corrupted?";
        vanillaConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowCowMinotaurCreation", true);
        prop.comment = "Will striking a Cow with Lightning turn it into a Minotaur?";
        vanillaConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowIronGolemCooldown", true);
        prop.comment = "Should Iron Golems have an enforced hit cooldown?";
        vanillaConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowNauseaRandomMiss", false);
        prop.comment = "Should Nausea (Confusion) have a random chance to make you miss a non-projectile, non-magic hit?";
        vanillaConfig[++m] = prop.getBoolean(false);

        prop = config.get(cat.getName(), "allowBlindnessReachDebuff", false);
        prop.comment = "Should Blindness debuff your Reach?";
        vanillaConfig[++m] = prop.getBoolean(false);

        prop = config.get(cat.getName(), "allowCripplingFall", false);
        prop.comment = "Should a fall that damages you inflict Cripple?";
        vanillaConfig[++m] = prop.getBoolean(false);

        prop = config.get(cat.getName(), "rubyOreGenRate", 10);
        rubyOreRate = prop.getInt(10);

        prop = config.get(cat.getName(), "rubyOreVeinSize", 3);
        rubyOreVeinSize = prop.getInt(3);

        prop = config.get(cat.getName(), "sapphireOreGenRate", 10);
        sapphireOreRate = prop.getInt(10);

        prop = config.get(cat.getName(), "sapphireOreVeinSize", 3);
        sapphireOreVeinSize = prop.getInt(3);

        prop = config.get(cat.getName(), "mercuryOreGenRate", 20);
        mercuryOreRate = prop.getInt(20);

        prop = config.get(cat.getName(), "mercuryOreVeinSize", 4);
        mercuryOreVeinSize = prop.getInt(4);

        prop = config.get(cat.getName(), "tungstenOreGenRate", 10);
        tungstenOreRate = prop.getInt(10);

        prop = config.get(cat.getName(), "tungstenOreVeinSize", 3);
        tungstenOreVeinSize = prop.getInt(3);

        prop = config.get(cat.getName(), "drudgeGenRate", 10);
        drudgeRate = prop.getInt(10);

        prop = config.get(cat.getName(), "drudgeVeinSize", 10);
        drudgeVeinSize = prop.getInt(10);

        prop = config.get(cat.getName(), "silverfishStoneGenRate", 12);
        silverfishRate = prop.getInt(12);

        prop = config.get(cat.getName(), "silverfishStoneVeinSize", 3);
        silverfishVeinSize = prop.getInt(3);

        prop = config.get(cat.getName(), "aerisRarity", 5);
        aerisRarity = prop.getInt(5);

        prop = config.get(cat.getName(), "nauseaMissChance", 8);
        nauseaMissChance = clamp(prop.getInt(8), 1, 100);

        prop = config.get(cat.getName(), "blindnessReachDebuffAmount", 0.25);
        blindnessReachDebuffAmount = prop.getDouble(0.25);

        cat = config.getCategory(CAT_WORLDGEN);
        cat.setComment("Change things related to the mod-exclusive Dimensional World Generation.");
        cat.setRequiresMcRestart(true);

        prop = config.get(cat.getName(), "allowVoidPits", true);
        prop.comment = "Should void pits be allowed to generate?";
        worldGenConfig[m = 0] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowSpikes", true);
        prop.comment = "Should large spikes, ice spikes, star crystals and crystal spikes be allowed to generate?";
        worldGenConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowScatteredSurfaceWorldGen", true);
        prop.comment = "Should scattered surface features like mixed dirt, light orbs and cracked permafrost be allowed to generate?";
        worldGenConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowStringLights", true);
        prop.comment = "Should String Lights be allowed to generate?";
        worldGenConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowDarkStoneVariantGen", true);
        prop.comment = "Should Dark Stone have layers generate as a colored variant?";
        worldGenConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowStructureGen", true);
        prop.comment = "Should mod-exclusive Structures be allowed to generate?";
        worldGenConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowRoughTerrainGen", true);
        prop.comment = "Should rough terrain like in the Tainted or Scorched Scarlands be generated?";
        worldGenConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowDigitalSeaGen", true);
        prop.comment = "Should Digital Sea generate in the Synapse?";
        worldGenConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowFruitGen", true);
        prop.comment = "Should Honeydrop, Deathglow and Sky Fruit generate naturally?";
        worldGenConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowIsleGen", true);
        prop.comment = "Should Isles be generated in the Tainted Isles biome?";
        worldGenConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowFlowerGen", true);
        prop.comment = "Should flowers generate in the mod's Dimensions?";
        worldGenConfig[++m] = prop.getBoolean(true);

        cat = config.getCategory(CAT_STRUCTURE);
        cat.setComment(
            "Toggle specific structures and change their rarities. Higher number is higher chance to generate.");
        cat.setRequiresWorldRestart(true);

        prop = config.get(cat.getName(), "structureOverallRarity", 15);
        structureOverallRarity = clamp(prop.getInt(15), 1, 500);

        prop = config.get(cat.getName(), "apisTempleAllow", true);
        structureAllow[m = 0] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "randomTowerAllow", true);
        structureAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "deathCircleAllow", true);
        structureAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "obsidianCavernAllow", true);
        structureAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "kitsunakumaDenAllow", true);
        structureAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "celestialTempleAllow", true);
        structureAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "timeAltarAllow", true);
        structureAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "soulTombAllow", true);
        structureAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "corruptedSpireAllow", true);
        structureAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "empariahCaveAllow", true);
        structureAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "claymationRuinAllow", true);
        structureAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "darkHutAllow", true);
        structureAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "spiderNestAllow", true);
        structureAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "memoryCacheAllow", true);
        structureAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "lightSpireAllow", true);
        structureAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "hackerNetAllow", true);
        structureAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "cubeMazeAllow", true);
        structureAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "outlookAllow", true);
        structureAllow[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "apisTempleRarity", 5);
        structureRarity[m = 0] = clamp(prop.getInt(5), 1, 200);

        prop = config.get(cat.getName(), "randomTowerRarity", 15);
        structureRarity[++m] = clamp(prop.getInt(15), 1, 200);

        prop = config.get(cat.getName(), "deathCircleRarity", 5);
        structureRarity[++m] = clamp(prop.getInt(5), 1, 200);

        prop = config.get(cat.getName(), "obsidianCavernRarity", 10);
        structureRarity[++m] = clamp(prop.getInt(10), 1, 200);

        prop = config.get(cat.getName(), "kitsunakumaDenRarity", 5);
        structureRarity[++m] = clamp(prop.getInt(5), 1, 200);

        prop = config.get(cat.getName(), "celestialTempleRarity", 10);
        structureRarity[++m] = clamp(prop.getInt(10), 1, 200);

        prop = config.get(cat.getName(), "timeAltarRarity", 3);
        structureRarity[++m] = clamp(prop.getInt(3), 1, 200);

        prop = config.get(cat.getName(), "soulTombRarity", 15);
        structureRarity[++m] = clamp(prop.getInt(15), 1, 200);

        prop = config.get(cat.getName(), "corruptedSpireRarity", 0);
        structureRarity[++m] = clamp(prop.getInt(0), 1, 200);

        prop = config.get(cat.getName(), "empariahCaveRarity", 5);
        structureRarity[++m] = clamp(prop.getInt(5), 1, 200);

        prop = config.get(cat.getName(), "claymationRuinRarity", 5);
        structureRarity[++m] = clamp(prop.getInt(5), 1, 200);

        prop = config.get(cat.getName(), "darkHutRarity", 15);
        structureRarity[++m] = clamp(prop.getInt(15), 1, 200);

        prop = config.get(cat.getName(), "spiderNestRarity", 15);
        structureRarity[++m] = clamp(prop.getInt(15), 1, 200);

        prop = config.get(cat.getName(), "memoryCacheRarity", 3);
        structureRarity[++m] = clamp(prop.getInt(3), 1, 200);

        prop = config.get(cat.getName(), "lightSpireRarity", 10);
        structureRarity[++m] = clamp(prop.getInt(10), 1, 200);

        prop = config.get(cat.getName(), "hackerNetRarity", 3);
        structureRarity[++m] = clamp(prop.getInt(3), 1, 200);

        prop = config.get(cat.getName(), "cubeMazeRarity", 25);
        structureRarity[++m] = clamp(prop.getInt(25), 1, 200);

        prop = config.get(cat.getName(), "outlookRarity", 3);
        structureRarity[++m] = clamp(prop.getInt(3), 1, 200);

        cat = config.getCategory(CAT_MISC);
        cat.setComment("Random other options that don't quite fit into other categories.");

        prop = config.get(cat.getName(), "allowRandomWeaponLore", true);
        prop.comment = "Should mod-exclusive weapons and armor come with randomized Lore?";
        allowRandomWeaponLore = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowChallengeScrolls", true);
        prop.comment = "Are Challenge Scrolls enabled?";
        allowChallengeScrolls = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowMobStatueDrops", true);
        prop.comment = "Can certain mobs drop statues?";
        allowMobStatueDrops = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowGeneratorItems", true);
        prop.comment = "Will Creative mode-only generator items be enabled?";
        allowGeneratorItems = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowItemTimeAltering", true);
        prop.comment = "Certain items and blocks may alter in-game time, should this be allowed?";
        allowItemTimeAltering = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowPvP", true);
        prop.comment = "Should Doomsdays and Weapons be able to be used against other players?";
        allowPvP = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowDefaultLores", true);
        prop.comment = "Should the Lores bundled with the mod be used? Perhaps disable them if you wish to only use your own custom Lore.";
        allowDefaultLores = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowCorruptionTransfer", true);
        prop.comment = "Should Corruption transfer between entities?";
        allowCorruptionTransfer = prop.getBoolean(true);

        prop = config.get(cat.getName(), "challengeScrollDropChance", 5);
        prop.comment = "Chance for Challenge Scrolls to drop off of any mob that you kill.";
        challengeScrollDropChance = clamp(prop.getInt(5), 1, 100);

        prop = config.get(cat.getName(), "mobStatueDropChance", 100);
        prop.comment = "Chance for a mob to drop it's corresponding statue. Only certain mobs have these.";
        mobStatueDropChance = clamp(prop.getInt(100), 1, 100);

        cat = config.getCategory(CAT_CLIENT);
        cat.setComment("These are client-side only options, they affect nothing on the server-side.");
        cat.setShowInGui(true);

        prop = config.get(cat.getName(), "allowAnimatedGui", true);
        prop.comment = "Whether or not the Doom GUI is animated.";
        allowAnimatedGui = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowArmorModels", true);
        prop.comment = "Whether or not custom armor models are rendered for armor that has it";
        allowArmorModels = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowWeaponModels", true);
        prop.comment = "Whether or not custom weapon models are used for weapons that have it";
        allowWeaponModels = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowDivinityColorChange", true);
        prop.comment = "Whether the Divinity potion effect has a rainbow-like render overlay";
        allowDivinityColorChange = prop.getBoolean(true);

        prop = config.get(cat.getName(), "showDoomGui", true);
        prop.comment = "Whether the Doom GUI is rendered";
        showDoomGui = prop.getBoolean(true);

        prop = config.get(cat.getName(), "showAmuletStatusGui", true);
        prop.comment = "Whether the Amulet Status GUI is rendered";
        showAmuletStatusGui = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowDimensionalMusic", false);
        prop.comment = "Whether Dimension-specific music is played";
        allowDimensionalMusic = false; // prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowPotionEffectOverlays", true);
        prop.comment = "Whether certain potion effects have an overlay rendered while they are active";
        allowPotionEffectOverlays = prop.getBoolean(true);

        prop = config.get(cat.getName(), "guiTransparency", 100);
        guiTransparency = clamp(prop.getInt(100), 1, 100);

        prop = config.get(cat.getName(), "guiTextureSkinID", 0);
        guiTexture = clamp(prop.getInt(0), 0, 100);

        prop = config.get(cat.getName(), "guiX", 1);
        guiX = clampPositive(prop.getInt(1));

        prop = config.get(cat.getName(), "guiY", 1);
        guiY = clampPositive(prop.getInt(1));

        cat = config.getCategory(CAT_GRIEF);
        cat.setComment("Toggle whether specific Weapon abilities or Doomsdays damage the terrain.");

        prop = config.get(cat.getName(), "allowNatureDrainDestruction", true);
        griefConfig[m = 0] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowRavageDestruction", true);
        griefConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowFrozenLightningDestruction", true);
        griefConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowMourningStarDestruction", true);
        griefConfig[++m] = prop.getBoolean(true);

        prop = config.get(cat.getName(), "allowTitanDestruction", true);
        griefConfig[++m] = prop.getBoolean(true);

        cat = config.getCategory(CAT_MOBAI);
        cat.setComment("Toggle aspects of each mob's AI and also set specific griefing instances.");
        cat.setRequiresWorldRestart(false);

        prop = config.get(cat.getName(), "jabbaAnger", true);
        prop.comment = "Should the Jabba species become angry after being in combat for a while and gain new abilities?";
        jabbaAnger = prop.getBoolean(true);

        prop = config.get(cat.getName(), "jabbaProjectiles", true);
        prop.comment = "Should the Jabba species shoot projectiles at targets when angered?";
        jabbaProjectiles = prop.getBoolean(true);

        prop = config.get(cat.getName(), "plagueCorruption", true);
        prop.comment = "Should Plagues corrupt entities around it?";
        plagueCorruption = prop.getBoolean(true);

        prop = config.get(cat.getName(), "gragulDamageReduction", true);
        prop.comment = "Should the Gragul species take partial health damage instead of normal damage?";
        gragulDamageReduction = prop.getBoolean(true);

        prop = config.get(cat.getName(), "gragulPercentageDamage", true);
        prop.comment = "Should the Gragul species inflict partial health damage instead of normal damage? (They will still do armor piercing damage)";
        gragulPercentageDamage = prop.getBoolean(true);

        prop = config.get(cat.getName(), "minotaurCharge", true);
        prop.comment = "Should Minotaurs charge at their target?";
        minotaurCharge = prop.getBoolean(true);

        prop = config.get(cat.getName(), "inklingInvisibility", true);
        prop.comment = "Should Inklings become invisible when threatened?";
        inklingInvisibility = prop.getBoolean(true);

        prop = config.get(cat.getName(), "inklingTorchBreaking", true);
        prop.comment = "Should Inklings randomly destroy torches when near them?";
        inklingTorchBreaking = prop.getBoolean(true);

        prop = config.get(cat.getName(), "inklingTeleport", true);
        prop.comment = "Should Inklings teleport?";
        inklingTeleport = prop.getBoolean(true);

        prop = config.get(cat.getName(), "ragrExplosions", true);
        prop.comment = "Should Ragrs create explosions when landing?";
        ragrExplosions = prop.getBoolean(true);

        prop = config.get(cat.getName(), "ragrBlockCrushing", true);
        prop.comment = "Should Ragrs crush blocks when landing?";
        ragrBlockCrushing = prop.getBoolean(true);

        prop = config.get(cat.getName(), "pumpkinheadPumpkinSpawn", true);
        prop.comment = "Should Pumpkinheads create a Home Pumpkin upon spawning?";
        pumpkinheadPumpkinSpawn = prop.getBoolean(true);

        prop = config.get(cat.getName(), "pumpkinheadHaste", true);
        prop.comment = "Should Pumpkinheads gain increased stats while they have a Home Pumpkin and are being threatened?";
        pumpkinheadHaste = prop.getBoolean(true);

        prop = config.get(cat.getName(), "pumpkinheadPumpkinbombs", true);
        prop.comment = "Should Pumpkinheads fire Pumpkinbombs when they are low on health and have a Home Pumpkin?";
        pumpkinheadPumpkinbombs = prop.getBoolean(true);

        prop = config.get(cat.getName(), "tragicNekoRockets", true);
        prop.comment = "Should Tragic Nekos fire rockets at people's faces?";
        tragicNekoRockets = prop.getBoolean(true);

        prop = config.get(cat.getName(), "tragicNekoStickyBombs", true);
        prop.comment = "Should Tragic Nekos throw Sticky Bombs into people's faces?";
        tragicNekoStickyBombs = prop.getBoolean(true);

        prop = config.get(cat.getName(), "tragicNekoClusterBomb", true);
        prop.comment = "Should Tragic Nekos throw Cluster Bombs at people's faces?";
        tragicNekoClusterBombs = prop.getBoolean(true);

        prop = config.get(cat.getName(), "tragicNekoDeathBomb", true);
        prop.comment = "Should Tragic Nekos have a chance to drop various bombs on death?";
        tragicNekoDeathBomb = prop.getBoolean(true);

        prop = config.get(cat.getName(), "tragicNekoCelebration", true);
        prop.comment = "Should Tragic Nekos celebrate their birthday?";
        tragicNekoCelebration = prop.getBoolean(true);

        prop = config.get(cat.getName(), "toxProjectiles", true);
        prop.comment = "Should the Tox species fire projectiles? (They still attack on contact)";
        toxProjectiles = prop.getBoolean(true);

        prop = config.get(cat.getName(), "cryseReflection", true);
        prop.comment = "Should the Cryse species reflect damage?";
        cryseReflection = prop.getBoolean(true);

        prop = config.get(cat.getName(), "norVoxProjectiles", true);
        prop.comment = "Should the Nor-Vox species fire projectiles?";
        norVoxProjectiles = prop.getBoolean(true);

        prop = config.get(cat.getName(), "norVoxRegeneration", true);
        prop.comment = "Should the Nor-Vox species regenerate health naturally?";
        norVoxRegeneration = prop.getBoolean(true);

        prop = config.get(cat.getName(), "pirahGolden", true);
        prop.comment = "Should Pirah be randomly spawned as a Golden stronger version?";
        pirahGolden = prop.getBoolean(true);

        prop = config.get(cat.getName(), "stinTeleport", true);
        prop.comment = "Should the Stin species randomly teleport you away when you attack them?";
        stinTeleport = prop.getBoolean(true);

        prop = config.get(cat.getName(), "abominationHelpCall", true);
        prop.comment = "Should Abominations call for help when they are attacked?";
        abominationHelpCall = prop.getBoolean(true);

        prop = config.get(cat.getName(), "erkelMushroomSpawning", true);
        prop.comment = "Should Erkels randomly place Mushrooms?";
        erkelMushroomSpawning = prop.getBoolean(true);

        prop = config.get(cat.getName(), "sirvHelpCall", true);
        prop.comment = "Should Sirvs call for help when they are attacked?";
        sirvHelpCall = prop.getBoolean(true);

        prop = config.get(cat.getName(), "psygoteSwapTeleport", true);
        prop.comment = "Should Psygotes attempt to swap places with their attacker to confuse them?";
        psygoteSwapTeleport = prop.getBoolean(true);

        prop = config.get(cat.getName(), "psygoteProjectiles", true);
        prop.comment = "Should Psygotes fire Dark Mortors at their attacker?";
        psygoteProjectiles = prop.getBoolean(true);

        prop = config.get(cat.getName(), "psygoteRegeneration", true);
        prop.comment = "Should Psygotes regenerate health naturally?";
        psygoteRegeneration = prop.getBoolean(true);

        prop = config.get(cat.getName(), "lockbotLockdown", true);
        prop.comment = "Should Lockbots lock down their target?";
        lockbotLockdown = prop.getBoolean(true);

        prop = config.get(cat.getName(), "harvesterBuffDebuffEntities", true);
        prop.comment = "Should Harvesters buff Synapse mobs and debuff everything else?";
        harvesterBuffDebuffEntities = prop.getBoolean(true);

        prop = config.get(cat.getName(), "harvesterNanoSwarms", true);
        prop.comment = "Should Harvesters spawn Nano Swarms in to defend them?";
        harvesterNanoSwarms = prop.getBoolean(true);

        prop = config.get(cat.getName(), "seekerKillbeam", true);
        prop.comment = "Should Seekers kill you with their gaze?";
        seekerKillbeam = prop.getBoolean(true);

        prop = config.get(cat.getName(), "archangelHolybeam", true);
        prop.comment = "Should Archangels kill you with their Holy Beam of death?";
        archangelHolybeam = prop.getBoolean(true);

        prop = config.get(cat.getName(), "ireEnergyBurst", true);
        prop.comment = "Should Ires shoot an Ire Energy burst at their target?";
        ireEnergyBurst = prop.getBoolean(true);

        prop = config.get(cat.getName(), "fuseaExplosiveDamage", true);
        prop.comment = "Should the Fusea species explode when they take damage?";
        fuseaExplosiveDamage = prop.getBoolean(true);

        prop = config.get(cat.getName(), "fuseaExplosiveAttack", true);
        prop.comment = "Should the Fusea species explode when they attack something?";
        fuseaExplosiveAttack = prop.getBoolean(true);

        prop = config.get(cat.getName(), "fuseaExplosiveLayers", true);
        prop.comment = "Should the Fusea species only take damage when they explode?";
        fuseaExplosiveLayers = prop.getBoolean(true);

        prop = config.get(cat.getName(), "ranmasCharge", true);
        prop.comment = "Should Ranmas hurl themselves at you?";
        ranmasCharge = prop.getBoolean(true);

        prop = config.get(cat.getName(), "ranmasImpactExplosions", true);
        prop.comment = "Should Ranmas create explosions if they impact something at high enough velocity";
        ranmasImpactExplosions = prop.getBoolean(true);

        prop = config.get(cat.getName(), "parasmiteLeech", true);
        prop.comment = "Should Parasmites attempt to latch onto entities to simultaneously buff and hurt them?";
        parasmiteLeech = prop.getBoolean(true);

        prop = config.get(cat.getName(), "avrisAnnouncements", true);
        prop.comment = "Should Avris' announce when they spawn, die or elude attackers?";
        avrisAnnouncements = prop.getBoolean(true);

        prop = config.get(cat.getName(), "avrisDespawnTime", true);
        prop.comment = "Should Avris' despawn after a preset amount of time?";
        avrisDespawnTime = prop.getBoolean(true);

        prop = config.get(cat.getName(), "kragulSpiritCasts", true);
        prop.comment = "Should Kraguls fire Spirit Casts at their target?";
        kragulSpiritCasts = prop.getBoolean(true);

        prop = config.get(cat.getName(), "magmoxLargeFireballs", true);
        prop.comment = "Should Magmox fire Large Fireballs in addition to the smaller projectiles at their target?";
        magmoxLargeFireballs = prop.getBoolean(true);

        prop = config.get(cat.getName(), "megaCryseShields", true);
        prop.comment = "Should Mega Cryse have shields that block all damage (except Magic)?";
        megaCryseShields = prop.getBoolean(true);

        prop = config.get(cat.getName(), "greaterStinCharge", true);
        prop.comment = "Should Greater Stins charge towards their targets?";
        greaterStinCharge = prop.getBoolean(true);

        prop = config.get(cat.getName(), "stinKingMortors", true);
        prop.comment = "Should Stin Kings fire Dark Mortors at their target?";
        stinKingMortors = prop.getBoolean(true);

        prop = config.get(cat.getName(), "stinQueenWebBombs", true);
        prop.comment = "Should Stin Queens fire Web Bombs at their target?";
        stinQueenWebBombs = prop.getBoolean(true);

        prop = config.get(cat.getName(), "stinQueenBabies", true);
        prop.comment = "Should Stin Queens spawn in babies to attack their target?";
        stinQueenBabies = prop.getBoolean(true);

        prop = config.get(cat.getName(), "stinQueenWebs", true);
        prop.comment = "Should Stin Queens trap attackers in webs?";
        stinQueenWebs = prop.getBoolean(true);

        prop = config.get(cat.getName(), "voxStellarumSpinAttack", true);
        prop.comment = "Should Vox Stellarums do a spinning attack?";
        voxStellarumSpinAttack = prop.getBoolean(true);

        prop = config.get(cat.getName(), "voxStellarumHealing", true);
        prop.comment = "Should Vox Stellarums go into a healing phase to regenerate their health quickly?";
        voxStellarumHealing = prop.getBoolean(true);

        prop = config.get(cat.getName(), "aegarHypermode", true);
        prop.comment = "Should Aegars enter Hypermode after their crystal is damaged and gain increased stats?";
        aegarHypermode = prop.getBoolean(true);

        prop = config.get(cat.getName(), "aegarShockwave", true);
        prop.comment = "Should Aegars do a ground shockwave attack?";
        aegarShockwave = prop.getBoolean(true);

        prop = config.get(cat.getName(), "aegarLasers", true);
        prop.comment = "Should Aegars fire a laser at their target?";
        aegarLasers = prop.getBoolean(true);

        prop = config.get(cat.getName(), "aegarMortors", true);
        prop.comment = "Should Aegars fire Crystal Mortors at their target?";
        aegarMortors = prop.getBoolean(true);

        prop = config.get(cat.getName(), "volatileFuseaElementalChange", true);
        prop.comment = "Should Volatile Fuseas change elements based on their surroundings? (they gain projectile attacks from this)";
        volatileFuseaElementalChange = prop.getBoolean(true);

        prop = config.get(cat.getName(), "apisSolarbombs", true);
        prop.comment = "Should Apis fire Solar Bombs at it's target?";
        apisSolarBombs = prop.getBoolean(true);

        prop = config.get(cat.getName(), "apisChargeAttack", true);
        prop.comment = "Should Apis charge towards it's target?";
        apisChargeAttack = prop.getBoolean(true);

        prop = config.get(cat.getName(), "apisExplosiveCharge", true);
        prop.comment = "Should Apis create an explosion when it charges?";
        apisExplosiveCharge = prop.getBoolean(true);

        prop = config.get(cat.getName(), "apisSuperStomp", true);
        prop.comment = "Should Apis do a super stomp attack on nearby targets?";
        apisSuperStomp = prop.getBoolean(true);

        prop = config.get(cat.getName(), "apisReflection", true);
        prop.comment = "Should Apis be able to reflect attacks temporarily?";
        apisReflection = prop.getBoolean(true);

        prop = config.get(cat.getName(), "skultarDemeanor", true);
        prop.comment = "Should Skultar use demeanor to change it's attack state?";
        skultarDemeanor = prop.getBoolean(true);

        prop = config.get(cat.getName(), "skultarProjectiles", true);
        prop.comment = "Should Skultar fire projectiles at it's target?";
        skultarProjectiles = prop.getBoolean(true);

        prop = config.get(cat.getName(), "skultarClone", true);
        prop.comment = "Should Skultar create clones when it's health is low to confuse attackers?";
        skultarClone = prop.getBoolean(true);

        prop = config.get(cat.getName(), "skultarWitheringGas", true);
        prop.comment = "Should Skultar create Withering Gas when it's health is low?";
        skultarWitheringGas = prop.getBoolean(true);

        prop = config.get(cat.getName(), "skultarRegeneration", true);
        prop.comment = "Should Skultar regenerate health naturally?";
        skultarRegeneration = prop.getBoolean(true);

        prop = config.get(cat.getName(), "kitsunakumaFireballExempt", true);
        prop.comment = "Should Kitsunakuma be immune to all damage but Fireball damage?";
        kitsunakumaFireballExempt = prop.getBoolean(true);

        prop = config.get(cat.getName(), "kitsunakumaFireballs", true);
        prop.comment = "Should Kitsunakuma shoot fireballs?";
        kitsunakumaFireballs = prop.getBoolean(true);

        prop = config.get(cat.getName(), "kitsunakumaTeleport", true);
        prop.comment = "Should Kitsunakuma teleport?";
        kitsunakumaTeleport = prop.getBoolean(true);

        prop = config.get(cat.getName(), "kitsunakumaTaunt", true);
        prop.comment = "Should Kitsunakuma taunt?";
        kitsunakumaTaunt = prop.getBoolean(true);

        prop = config.get(cat.getName(), "polarisTeleport", true);
        prop.comment = "Should Polaris teleport?";
        polarisTeleport = prop.getBoolean(true);

        prop = config.get(cat.getName(), "polarisInvisibility", true);
        prop.comment = "Should Polaris go invisible?";
        polarisInvisibility = prop.getBoolean(true);

        prop = config.get(cat.getName(), "polarisAfterImage", true);
        prop.comment = "Should Polaris sometimes leave an after-image (clone) when teleporting?";
        polarisAfterImage = prop.getBoolean(true);

        prop = config.get(cat.getName(), "polarisNighttimeSet", true);
        prop.comment = "Should Polaris automatically set the world to night?";
        polarisNighttimeSet = prop.getBoolean(true);

        prop = config.get(cat.getName(), "polarisFearGolems", true);
        prop.comment = "Should Polaris run away from Golems?";
        polarisFearGolems = prop.getBoolean(true);

        prop = config.get(cat.getName(), "polarisRegeneration", true);
        prop.comment = "Should Polaris regenerate health naturally?";
        polarisRegeneration = prop.getBoolean(true);

        prop = config.get(cat.getName(), "empariahDemeanor", true);
        prop.comment = "Should Empariah use demeanor to determine it's attack patterns?";
        empariahDemeanor = prop.getBoolean(true);

        prop = config.get(cat.getName(), "empariahCharge", true);
        prop.comment = "Should Empariah charge towards it's target?";
        empariahCharge = prop.getBoolean(true);

        prop = config.get(cat.getName(), "empariahFrostBreath", true);
        prop.comment = "Should Empariah use a Frost breath attack?";
        empariahFrostBreath = prop.getBoolean(true);

        prop = config.get(cat.getName(), "empariahRoar", true);
        prop.comment = "Should Empariah use a Roar attack?";
        empariahRoar = prop.getBoolean(true);

        prop = config.get(cat.getName(), "empariahRockThrowing", true);
        prop.comment = "Should Empariah throw rocks at it's target?";
        empariahRockThrowing = prop.getBoolean(true);

        prop = config.get(cat.getName(), "empariahSummonAbomination", true);
        prop.comment = "Should Empariah summon Abominations to help it?";
        empariahSummonAbomination = prop.getBoolean(true);

        prop = config.get(cat.getName(), "empariahCallHelp", true);
        prop.comment = "Should Empariah call for help of nearby Abominations?";
        empariahCallHelp = prop.getBoolean(true);

        prop = config.get(cat.getName(), "empariahRegeneration", true);
        prop.comment = "Should Empariah regenerate health naturally?";
        empariahRegeneration = prop.getBoolean(true);

        prop = config.get(cat.getName(), "timeControllerRegeneration", true);
        prop.comment = "Should Time Controller generate health naturally?";
        timeControllerRegeneration = prop.getBoolean(true);

        prop = config.get(cat.getName(), "timeControllerQuantumLeap", true);
        prop.comment = "Should Time Controller use a Quantum Leap attack that reverts entities to their position from a few seconds ago?";
        timeControllerQuantumLeap = prop.getBoolean(true);

        prop = config.get(cat.getName(), "timeControllerFlux", true);
        prop.comment = "Should Time Controller use a Flux attack that pulls entities towards it and absorbs damage?";
        timeControllerFlux = prop.getBoolean(true);

        prop = config.get(cat.getName(), "timeControllerPurge", true);
        prop.comment = "Should Time Controller use a Purge attack that deflects projectiles and hits entities near it?";
        timeControllerPurge = prop.getBoolean(true);

        prop = config.get(cat.getName(), "timeControllerSpaz", true);
        prop.comment = "Should Time Controller spaz out when it's health is low and teleport around?";
        timeControllerSpaz = prop.getBoolean(true);

        prop = config.get(cat.getName(), "timeControllerTimeBombs", true);
        prop.comment = "Should Time Controller spawn in Time Bombs that affect entities near them?";
        timeControllerTimeBombs = prop.getBoolean(true);

        prop = config.get(cat.getName(), "timeControllerLuminescence", true);
        prop.comment = "Should Time Controller place Light blocks to simulate entity lighting?";
        timeControllerLuminescence = prop.getBoolean(true);

        prop = config.get(cat.getName(), "timeControllerTimeAltering", true);
        prop.comment = "Should Time Controller be able to change the world time?";
        timeControllerTimeAltering = prop.getBoolean(true);

        prop = config.get(cat.getName(), "enyvilDarkCrystals", true);
        prop.comment = "Should Enyvil need Dark Crystals to use it's abilities?";
        enyvilDarkCrystals = prop.getBoolean(true);

        prop = config.get(cat.getName(), "enyvilDarkLightning", true);
        prop.comment = "Should Enyvil shoot Dark Lightning projectiles?";
        enyvilDarkLightning = prop.getBoolean(true);

        prop = config.get(cat.getName(), "enyvilCrystalLaser", true);
        prop.comment = "Should Enyvil be able to use it's Dark Crystals to fire a laser at it's target?";
        enyvilCrystalLaser = prop.getBoolean(true);

        prop = config.get(cat.getName(), "enyvilDarkEnergySpray", true);
        prop.comment = "Should Enyvil be able to fire Dark Energy at it's target?";
        enyvilDarkEnergySpray = prop.getBoolean(true);

        prop = config.get(cat.getName(), "enyvilTractorBeam", true);
        prop.comment = "Should Enyvil be able to Tractor beam far away targets to it?";
        enyvilTractorBeam = prop.getBoolean(true);

        prop = config.get(cat.getName(), "enyvilThunderstorm", true);
        prop.comment = "Should Enyvil be able to summon Lightning down around it?";
        enyvilThunderstorm = prop.getBoolean(true);

        prop = config.get(cat.getName(), "enyvilLightningExplosions", true);
        prop.comment = "Should Enyvil create explosions when it strikes something with Lightning?";
        enyvilLightningExplosions = prop.getBoolean(true);

        prop = config.get(cat.getName(), "enyvilSlam", true);
        prop.comment = "Should Enyvil have a Slam attack?";
        enyvilSlam = prop.getBoolean(true);

        prop = config.get(cat.getName(), "enyvilDestroyBlocks", true);
        prop.comment = "Should Enyvil destroy blocks if it gets stuck?";
        enyvilDestroyBlocks = prop.getBoolean(true);

        prop = config.get(cat.getName(), "enyvilRegeneration", true);
        prop.comment = "Should Enyvil regenerate health naturally while it has a Dark Crystl?";
        enyvilRegeneration = prop.getBoolean(true);

        prop = config.get(cat.getName(), "claymationTransformation", true);
        prop.comment = "Should Claymation transform into other mobs and inherit their abilities?";
        claymationTransformation = prop.getBoolean(true);

        prop = config.get(cat.getName(), "claymationPotionReflection", true);
        prop.comment = "Should Claymation reflect bad potion effects at it's target?";
        claymationPotionReflection = prop.getBoolean(true);

        // TODO overlord configs

        cat = config.getCategory(CAT_MODIFIERS);
        cat.setComment(
            "Set each mob's modifier's actual amount, these can also be used to disable them by setting them to 0 in most cases.");

        prop = config.get(cat.getName(), "claymationSpeedDebuff", -1.0);
        prop.comment = "For any of the Claymation's forms that require it to have it's speed debuffed.";
        modifier[m = 0] = prop.getDouble(-1.0);

        prop = config.get(cat.getName(), "kitsunakumaSpeedDebuff", -0.5);
        prop.comment = "For when the Kitsunakuma is preparing to fire a fireball or taunt.";
        modifier[++m] = prop.getDouble(-0.5);

        prop = config.get(cat.getName(), "timeControllerSpeedBuff", 0.5);
        prop.comment = "For when the Time Controller is in Purge mode.";
        modifier[++m] = prop.getDouble(0.55);

        prop = config.get(cat.getName(), "empariahSpeedDebuff", -0.5);
        prop.comment = "For when the Empariah is about to throw a rock or use it's Frost Breath.";
        modifier[++m] = prop.getDouble(-0.5);

        prop = config.get(cat.getName(), "aegarSpeedBuff", 0.156);
        prop.comment = "For when the Aegar has it's Core destroyed and it is in Hypermode.";
        modifier[++m] = prop.getDouble(0.156);

        prop = config.get(cat.getName(), "megaCryseAttackBuff", 2.0);
        prop.comment = "For when the Mega Cryse has all of it's shields destroyed.";
        modifier[++m] = prop.getDouble(2.0);

        prop = config.get(cat.getName(), "jabbaAttackBuff", 2.5);
        prop.comment = "For when Jabba, Janna or Jarra is below half health.";
        modifier[++m] = prop.getDouble(2.5);

        prop = config.get(cat.getName(), "norVoxSpeedDebuff", -0.5);
        prop.comment = "For when the Nor-Vox, Star-Vox or Vox Stellarum is about to fire a projectile.";
        modifier[++m] = prop.getDouble(-0.5);

        prop = config.get(cat.getName(), "psygoteSpeedDebuff", -0.5);
        prop.comment = "For when the Psygote is about to teleport or fire a projectile.";
        modifier[++m] = prop.getDouble(-0.5);

        prop = config.get(cat.getName(), "tragicNekoSpeedDebuff", -0.5);
        prop.comment = "For when the Tragic Neko is about to fire or throw a projectile.";
        modifier[++m] = prop.getDouble(-0.5);

        prop = config.get(cat.getName(), "dynamicMobHealthBuff", 20.0);
        prop.comment = "Health buff for mod-exclusive mobs on Hard difficulty. Only affects mobs that have over double of this value for their base Max Health value.";
        modifier[++m] = prop.getDouble(20.0);

        prop = config.get(cat.getName(), "dynamicMobHealthDebuff", -20.0);
        prop.comment = "Health debuff for mod-exclusive mobs on Easy difficulty. Only affects mobs that have over double of this value for their base Max Health.";
        modifier[++m] = prop.getDouble(-20.0);

        prop = config.get(cat.getName(), "dynamicBossHealthBuff", 50.0);
        prop.comment = "Health buff for mod-exclusive Bosses on Hard difficulty. Only affects mobs that have over 25% of this value for their base Max Health.";
        modifier[++m] = prop.getDouble(50.0);

        prop = config.get(cat.getName(), "apisSpeedDebuff", -0.5);
        prop.comment = "For when Apis is using it's Stomp attack.";
        modifier[++m] = prop.getDouble(-0.5);

        prop = config.get(cat.getName(), "ghastHealthBuff", 30.0);
        prop.comment = "Vanilla mob buff for Ghasts.";
        modifier[++m] = prop.getDouble(30.0);

        prop = config.get(cat.getName(), "zombieSkeletonCreeperHealthBuff", 10.0);
        prop.comment = "Vanilla mob buff for Skeletons, Creepers and Zombies.";
        modifier[++m] = prop.getDouble(10.0);

        prop = config.get(cat.getName(), "endermanHealthBuff", 20.0);
        prop.comment = "Vanilla mob buff for Endermen.";
        modifier[++m] = prop.getDouble(20.0);

        prop = config.get(cat.getName(), "spiderHealthBuff", 8.0);
        prop.comment = "Vanilla mob buff for Spiders.";
        modifier[++m] = prop.getDouble(8.0);

        prop = config.get(cat.getName(), "mobFollowRangeDebuff", -16.0);
        prop.comment = "Overall mob debuff for when a mob has Blindness.";
        modifier[++m] = prop.getDouble(-16.0);

        prop = config.get(cat.getName(), "hydrationKnockbackResistanceBuff", 1.0);
        prop.comment = "The Knockback Resistance buff that the Hydration Talisman gives you when you're in water or it's raining.";
        modifier[++m] = prop.getDouble(1.0);

        prop = config.get(cat.getName(), "lightningRodAttackBuff", 5.0);
        prop.comment = "The Attack Damage buff that the Lightning Rod Talisman gives you when it's thundering.";
        modifier[++m] = prop.getDouble(5.0);

        prop = config.get(cat.getName(), "moonlightHealthBuff", 10.0);
        prop.comment = "The Health buff that the Moonlight Talisman gives you when it's nighttime and it's not raining or thundering.";
        modifier[++m] = prop.getDouble(10.0);

        prop = config.get(cat.getName(), "synthesisHealthBuff", 10.0);
        prop.comment = "The Health buff that the Synthesis Talisman gives you when it's daytime and not raining or thundering.";
        modifier[++m] = prop.getDouble(10.0);

        prop = config.get(cat.getName(), "butcherKnockbackResistanceBuff", 1.0);
        prop.comment = "The Knockback Resistance buff you gain from holding the Butcher.";
        modifier[++m] = prop.getDouble(1.0);

        prop = config.get(cat.getName(), "overlordArmorHealthBuff", 5.0);
        prop.comment = "The Health buff you gain from wearing any of the Overlord Armor set.";
        modifier[++m] = prop.getDouble(5.0);

        initializeMasterConfigs();
        postProcessConfigs();
        initializeRemainingVariables();

        if (config.hasChanged()) config.save();
    }
    /*
     * private static void load2() //only if necessary, will most likely be
     * {
     * Configuration config = TragicMC.getConfig();
     * ConfigCategory cat;
     * Property prop;
     * byte m;
     * if (config.hasChanged()) config.save();
     * postProcessConfigs();
     * }
     */

    private static void initializeMasterConfigs() {
        if (mobsOnlyMode) {
            TragicMC.logInfo("mobsOnlyMode is enabled, overriding other config options.");
            hardcoreMode = false;
            lightweightMode = false;
            barebonesMode = false;
            setupMobsOnly();
        }

        if (hardcoreMode) {
            TragicMC.logInfo("hardcoreMode is enabled, overriding other config options.");
            lightweightMode = false;
            barebonesMode = false;
            setupHardcoreMode();
        }

        if (lightweightMode) {
            TragicMC.logInfo("lightweightMode is enabled, overriding other config options.");
            barebonesMode = false;
            setupLightweightMode();
        }

        if (barebonesMode) {
            TragicMC.logInfo("barebonesMode is enabled, overriding other config options.");
            setupBarebonesMode();
        }
    }

    private static void postProcessConfigs() {
        byte b;

        if (!allowAmulets) {
            for (b = 0; b < amuletConfig.length; b++) amuletConfig[b] = false;
            for (b = 0; b < amuletEffects.length; b++) amuletEffects[b] = false;
        }

        if (!allowDimension) {
            for (b = 0; b < dimensionConfig.length; b++) dimensionConfig[b] = false;
        }

        if (!allowDoom) {
            for (b = 0; b < doomConfig.length; b++) doomConfig[b] = false;
        }

        if (!doomConfig[0]) // doomsdays
        {
            for (b = 0; b < doomsdayAllow.length; b++) doomsdayAllow[b] = false;
        }

        if (!doomConfig[6]) // non-Doomsday abilities
        {
            for (b = 0; b < doomAbility.length; b++) doomAbility[b] = false;
        }

        if (!allowMobs) {
            for (b = 0; b < mobConfig.length; b++) mobConfig[b] = false;
        }

        if (!mobConfig[0]) // mobs
        {
            for (b = 0; b < mobAllow.length; b++) mobAllow[b] = false;
        }

        if (!mobConfig[1]) // mini-bosses
        {
            for (b = 0; b < miniBossAllow.length; b++) miniBossAllow[b] = false;
        }

        if (!mobConfig[2]) // bosses
        {
            for (b = 0; b < bossAllow.length; b++) bossAllow[b] = false;
        }

        if (!allowPotions) {
            for (b = 0; b < potionAllow.length; b++) potionAllow[b] = false;
        }

        if (!allowVanillaChanges) {
            for (b = 0; b < vanillaConfig.length; b++) vanillaConfig[b] = false;
        }

        if (!allowWorldGen) {
            for (b = 0; b < worldGenConfig.length; b++) worldGenConfig[b] = false;
        }

        if (!worldGenConfig[5]) // structures
        {
            for (b = 0; b < structureAllow.length; b++) structureAllow[b] = false;
        }
    }

    private static void initializeRemainingVariables() {
        byte m;
        allowAmuletLeveling = amuletConfig[m = 0];
        allowAmuletCrafting = amuletConfig[++m];
        requireAmuletSlotUnlock = amuletConfig[++m];
        allowAmuletBossKillCharge = amuletConfig[++m];
        allowAmuletModifiers = amuletConfig[++m];
        allowAmuletDeathDrops = amuletConfig[++m];

        amuPeace = amuletEffects[m = 0];
        amuYeti = amuletEffects[++m];
        amuClaymation = amuletEffects[++m];
        amuChicken = amuletEffects[++m];
        amuBlacksmith = amuletEffects[++m];
        amuCreeper = amuletEffects[++m];
        amuZombie = amuletEffects[++m];
        amuSkeleton = amuletEffects[++m];
        amuIce = amuletEffects[++m];
        amuSnowGolem = amuletEffects[++m];
        amuIronGolem = amuletEffects[++m];
        amuSpider = amuletEffects[++m];
        amuStin = amuletEffects[++m];
        amuSupernatural = amuletEffects[++m];
        amuFusea = amuletEffects[++m];
        amuLuck = amuletEffects[++m];
        amuKitsune = amuletEffects[++m];
        amuMartyr = amuletEffects[++m];
        amuPiercing = amuletEffects[++m];
        amuApis = amuletEffects[++m];
        amuSunken = amuletEffects[++m];
        amuEnderman = amuletEffects[++m];
        amuPolaris = amuletEffects[++m];
        amuLightning = amuletEffects[++m];
        amuConsumption = amuletEffects[++m];
        amuUndead = amuletEffects[++m];
        amuEnderDragon = amuletEffects[++m];
        amuTime = amuletEffects[++m];
        amuWither = amuletEffects[++m];
        amuOverlord = amuletEffects[++m];
        amuEnyvil = amuletEffects[++m];

        allowSynapse = dimensionConfig[m = 0];
        allowCollision = dimensionConfig[++m];
        allowCollisionRespawn = dimensionConfig[++m];
        allowSynapseRespawn = dimensionConfig[++m];
        keepCollisionLoaded = dimensionConfig[++m];
        keepSynapseLoaded = dimensionConfig[++m];
        allowSynapseVariants = dimensionConfig[++m];

        allowDoomsdays = doomConfig[m = 0];
        allowInfluenceDoomsdays = doomConfig[++m];
        allowOverflowDoomsdays = doomConfig[++m];
        allowCrisisDoomsdays = doomConfig[++m];
        allowWorldShaperDoomsdays = doomConfig[++m];
        allowCombinationDoomsdays = doomConfig[++m];
        allowNonDoomsdayAbilities = doomConfig[++m];
        shouldDoomLimitIncrease = doomConfig[++m];
        allowConsumeRefill = doomConfig[++m];
        allowDoomPainRecharge = doomConfig[++m];
        allowNaturalRecharge = doomConfig[++m];
        allowCrucialMoments = doomConfig[++m];
        allowBacklash = doomConfig[++m];
        allowCooldown = doomConfig[++m];
        allowDoomKillRecharge = doomConfig[++m];
        allowCooldownDefuse = doomConfig[++m];
        allowPartnerDoomsdays = doomConfig[++m];

        allowDecay = enchantAllow[m = 0];
        allowSlay = enchantAllow[++m];
        allowAbsolve = enchantAllow[++m];
        allowVampirism = enchantAllow[++m];
        allowLeech = enchantAllow[++m];
        allowConsume = enchantAllow[++m];
        allowDistract = enchantAllow[++m];
        allowMultiply = enchantAllow[++m];
        allowCombustion = enchantAllow[++m];
        allowRuneBreak = enchantAllow[++m];
        allowReach = enchantAllow[++m];
        allowUnbreakable = enchantAllow[++m];
        allowRust = enchantAllow[++m];
        allowVeteran = enchantAllow[++m];
        allowDeathTouch = enchantAllow[++m];
        allowIgnition = enchantAllow[++m];
        allowToxicity = enchantAllow[++m];
        allowParalysis = enchantAllow[++m];
        allowElasticity = enchantAllow[++m];
        allowAgility = enchantAllow[++m];
        allowRuneWalker = enchantAllow[++m];
        allowLuminescence = enchantAllow[++m];

        allowNormalMobs = mobConfig[m = 0];
        allowMiniBosses = mobConfig[++m];
        allowBosses = mobConfig[++m];
        allowBossOverworldSpawns = mobConfig[++m];
        allowExtraBossLoot = mobConfig[++m];
        allowMobTransformation = mobConfig[++m];
        allowDynamicHealthScaling = mobConfig[++m];
        allowNonDimensionMobSpawns = mobConfig[++m];
        allowGroupBuffs = mobConfig[++m];
        allowEasyBosses = mobConfig[++m];
        allowMobSounds = mobConfig[++m];
        bossesDenyFlight = mobConfig[++m];
        allowMobInfighting = mobConfig[++m];
        allowMobIllumination = mobConfig[++m];
        allowRandomSupportMob = mobConfig[++m];

        allowJabba = mobAllow[m = 0];
        allowJanna = mobAllow[++m];
        allowPlague = mobAllow[++m];
        allowGragul = mobAllow[++m];
        allowMinotaur = mobAllow[++m];
        allowInkling = mobAllow[++m];
        allowRagr = mobAllow[++m];
        allowPumpkinhead = mobAllow[++m];
        allowTragicNeko = mobAllow[++m];
        allowTox = mobAllow[++m];
        allowPox = mobAllow[++m];
        allowCryse = mobAllow[++m];
        allowStarCryse = mobAllow[++m];
        allowNorVox = mobAllow[++m];
        allowStarVox = mobAllow[++m];
        allowPirah = mobAllow[++m];
        allowStin = mobAllow[++m];
        allowStinBaby = mobAllow[++m];
        allowKindlingSpirit = mobAllow[++m];
        allowAbomination = mobAllow[++m];
        allowErkel = mobAllow[++m];
        allowSirv = mobAllow[++m];
        allowPsygote = mobAllow[++m];
        allowLockbot = mobAllow[++m];
        allowNanoSwarm = mobAllow[++m];
        allowSnowGolem = mobAllow[++m];
        allowHunter = mobAllow[++m];
        allowHarvester = mobAllow[++m];
        allowSeeker = mobAllow[++m];
        allowArchangel = mobAllow[++m];
        allowIre = mobAllow[++m];
        allowFusea = mobAllow[++m];
        allowRanmas = mobAllow[++m];
        allowAvris = mobAllow[++m];
        allowPyragr = mobAllow[++m];
        allowBlist = mobAllow[++m];
        allowThorg = mobAllow[++m];

        allowJarra = miniBossAllow[m = 0];
        allowKragul = miniBossAllow[++m];
        allowMagmox = miniBossAllow[++m];
        allowMegaCryse = miniBossAllow[++m];
        allowVoxStellarum = miniBossAllow[++m];
        allowGreaterStin = miniBossAllow[++m];
        allowStinKing = miniBossAllow[++m];
        allowStinQueen = miniBossAllow[++m];
        allowAegar = miniBossAllow[++m];
        allowVolatileFusea = miniBossAllow[++m];
        allowAggro = miniBossAllow[++m];

        allowApis = bossAllow[m = 0];
        allowSkultar = bossAllow[++m];
        allowKitsunakuma = bossAllow[++m];
        allowEmpariah = bossAllow[++m];
        allowTimeController = bossAllow[++m];
        allowPolaris = bossAllow[++m];
        allowEnyvil = bossAllow[++m];
        allowClaymation = bossAllow[++m];
        allowOverlord = bossAllow[++m];

        allowFlight = potionAllow[m = 0];
        allowAquaSuperiority = potionAllow[++m];
        allowImmunity = potionAllow[++m];
        allowResurrection = potionAllow[++m];
        allowHarmony = potionAllow[++m];
        allowInvulnerability = potionAllow[++m];
        allowClarity = potionAllow[++m];
        allowConvergence = potionAllow[++m];
        allowDivinity = potionAllow[++m];
        allowCorruption = potionAllow[++m];
        allowDisorientation = potionAllow[++m];
        allowStun = potionAllow[++m];
        allowFear = potionAllow[++m];
        allowMalnourish = potionAllow[++m];
        allowCripple = potionAllow[++m];
        allowSubmission = potionAllow[++m];
        allowInhibit = potionAllow[++m];
        allowLeadFoot = potionAllow[++m];
        allowHacked = potionAllow[++m];
        allowBurned = potionAllow[++m];

        allowVanillaMobBuffs = vanillaConfig[m = 0];
        allowExtraMobEffects = vanillaConfig[++m];
        allowAnimalRetribution = vanillaConfig[++m];
        allowMobModdedArmor = vanillaConfig[++m];
        allowRespawnPunishment = vanillaConfig[++m];
        allowExtraExplosiveEffects = vanillaConfig[++m];
        allowMobBlindnessDebuff = vanillaConfig[++m];
        allowExtraOverworldFlowers = vanillaConfig[++m];
        allowOverworldSilverfishGen = vanillaConfig[++m];
        allowNetherOreGen = vanillaConfig[++m];
        allowOverworldOreGen = vanillaConfig[++m];
        allowDrudgeGen = vanillaConfig[++m];
        allowAnimalGolemCorruption = vanillaConfig[++m];
        allowCowMinotaurCreation = vanillaConfig[++m];
        allowIronGolemHitCooldown = vanillaConfig[++m];
        allowNauseaRandomMiss = vanillaConfig[++m];
        allowBlindnessReachDebuff = vanillaConfig[++m];
        allowCripplingFall = vanillaConfig[++m];

        allowVoidPitGen = worldGenConfig[m = 0];
        allowSpikeGen = worldGenConfig[++m];
        allowScatteredSurfaceGen = worldGenConfig[++m];
        allowStringLightGen = worldGenConfig[++m];
        allowDarkStoneVariantGen = worldGenConfig[++m];
        allowStructureGen = worldGenConfig[++m];
        allowInvertedSpikeGen = worldGenConfig[++m];
        allowDigitalSeaGen = worldGenConfig[++m];
        allowFruitGen = worldGenConfig[++m];
        allowIsleGen = worldGenConfig[++m];
        allowFlowerGen = worldGenConfig[++m];
    }

    private static void setupMobsOnly() {
        // blanket configs
        allowAchievements = false;
        allowAmulets = false;
        allowDimension = false;
        allowDoom = false;
        allowEnchantments = false;
        allowPotions = false;
        allowVanillaChanges = false;
        allowWorldGen = false;

        // internal mod options
        allowNonMobItems = false;
        allowNonMobBlocks = false;
        allowNetwork = false;
        allowRecipes = false;
        allowSurvivalTab = false;
    }

    private static void setupHardcoreMode() {
        // blanket configs
        allowAchievements = true;
        allowAmulets = true;
        allowDimension = true;
        allowDoom = true;
        allowEnchantments = true;
        allowMobs = true;
        allowPotions = true;
        allowVanillaChanges = true;
        allowWorldGen = true;

        // internal mod options
        allowNonMobItems = true;
        allowNonMobBlocks = true;
        allowNetwork = true;
        allowRecipes = true;
        allowSurvivalTab = true;

        // hardcore-exclusive options
        // disable epic amulets, only one active amulet allowed, cannot obtain amulets from chests
        amuletMaxSlots = 1;
        amuletOverallRarity = 0;
        amuletEffects[13] = false; // time
        amuletEffects[18] = false; // wither
        amuletEffects[22] = false; // overlord
        amuletEffects[29] = false; // enyvil

        // simpified doom setup, disable easy mode things
        maxDoomAmount = 10;
        maxDoomStart = 5;
        doomConsumeAmount = 1;
        doomRechargeAmount = 1;
        doomConfig[0] = true; // doomsdays
        doomConfig[10] = false; // natural recharge
        doomConfig[11] = false; // crucial moments
        doomConfig[9] = false; // doom pain recharge
        doomConfig[8] = false; // consume refill
        doomConfig[15] = false; // cooldown defuse
        for (byte b = 0; b < doomsdayCost.length; b++)
            doomsdayCost[b] = doomsdayCost[b] > 100 ? 3 : (doomsdayCost[b] > 50 ? 2 : 1); // basically 1 - 50 is
                                                                                          // simplified to 1, 51 - 100
                                                                                          // is simplified to 2,
                                                                                          // everything higher is 3

        // remove crutch potion effects
        potionAllow[2] = false; // immunity
        potionAllow[3] = false; // resurrection
        potionAllow[4] = false; // harmony
        potionAllow[6] = false; // clarity

        // remove avris, erkel and wisps
        mobAllow[33] = false; // avris
        mobAllow[18] = false; // wisps
        mobAllow[20] = false; // erkel

        // TODO setup mob stat changes for hardcore mode
        modifier[10] += 10.0; // dynamic health scaling increased by 10 for all mobs

        // do not respawn in either dimension
        dimensionConfig[2] = false;
        dimensionConfig[3] = false;

        mobConfig[11] = true; // bosses negate flight to make it more difficult to fight them
        mobConfig[12] = false; // disables mob infighting
    }

    private static void setupLightweightMode() {
        // remove a lot of the risks, backlash, respawn punishment, simplify doom costs to use 5 to 25 with the max
        // values set to the defaults
        // mob stats are balanced more to fit in with vanilla mob amounts (20 health, 4 - 6 attack damage, etc.)
        // bosses are balanced to fit in more with the vanilla bosses (200 health, 6 attack damage, etc.)
        // less negative effects are enabled, plagues have their natural spawns disabled and mobs cannot transform into
        // mini-bosses
        doomConfig[12] = false; // backlash
        doomConfig[13] = false; // cooldown
        doomConfig[7] = false; // doom limit increases
        doomConfig[15] = false; // cooldown defuse allow
        vanillaConfig[0] = false; // vanilla mob buffs
        vanillaConfig[1] = false; // extra mob effects
        vanillaConfig[2] = false; // animal retribution
        vanillaConfig[3] = false; // modded armor
        vanillaConfig[4] = false; // respawn punishment
        vanillaConfig[5] = false; // extra explosive effects
        vanillaConfig[11] = false; // drudge gen
        allowCorruptionTransfer = false;
        allowPvP = false;
        mobAllow[2] = false; // plague
        potionAllow[9] = false; // corruption
        potionAllow[10] = false; // disorientation
        potionAllow[11] = false; // stun
        potionAllow[14] = false; // cripple
        potionAllow[15] = false; // submission
        potionAllow[17] = false; // lead foot
        potionAllow[18] = false; // hacked

        maxDoomAmount = 1000;
        maxDoomStart = 1000;
        consumeRefillAmount = 1000;
        doomConsumeAmount = 0;
        doomRechargeAmount = 5;
        doomRechargeRate = 10;
        for (byte b = 0; b < doomsdayCost.length; b++) doomsdayCost[b] = getCostForLightweight(doomsdayCost[b]); // truncate
                                                                                                                 // each
                                                                                                                 // cost
                                                                                                                 // to
                                                                                                                 // the
                                                                                                                 // nearest
                                                                                                                 // 5
        for (byte b = 0; b < doomAbilityCost.length; b++) doomAbilityCost[b] = 0; // right-click and special abilities
                                                                                  // for weapons don't cost anything

        amuletConfig[2] = false; // require unlocking amulet slots
        amuletConfig[5] = false; // death drops amulets
        amuletMaxSlots = 3; // start with all 3 slots already open
        amuletStartSlots = 3;

        // enable respawn in either dimension
        dimensionConfig[2] = true;
        dimensionConfig[3] = true;

        // TODO setup mob stat changes for lightweight mode
    }

    /**
     * Basically averages each value to go to the nearest 5
     * 
     * @param cost
     * @return
     */
    private static int getCostForLightweight(final int cost) {
        double penta = cost / 5;
        final int p = (int) Math.round(penta);
        return clamp(p * 5, 1, 1000);
    }

    private static void setupBarebonesMode() {
        // removes a lot of the newer stuff this version adds from the original one, doom, amulets, dimensions
        // retains the simplicity of the original by only having mobs, weapons and other items available

        // blanket configs
        allowAchievements = true;
        allowAmulets = false;
        allowDimension = false;
        allowDoom = false;
        allowEnchantments = true;
        allowMobs = true;
        allowPotions = false;
        allowVanillaChanges = false;
        allowWorldGen = false;

        // internal mod options
        allowNonMobItems = true;
        allowNonMobBlocks = true;
        allowNetwork = true;
        allowRecipes = true;
        allowSurvivalTab = true;
    }

    public static void disablePotions() {
        allowPotions = false;
        postProcessConfigs();
        initializeRemainingVariables();
    }

    public static int clampPositive(int value) {
        return value > 0 ? value : 0;
    }

    public static int clamp(final int value, final int min, final int max) {
        return net.minecraft.util.MathHelper.clamp_int(value, min, max);
    }

    private static int[] verifyGS(final int[] array) {
        return verify(array, 2);
    }

    private static double[] verifyStat(final double[] array) {
        return verify(array, 6);
    }

    private static double[] verify(final double[] array, final int amt) {
        if (amt != array.length) {
            throw new IllegalArgumentException(
                "Invalid array length, required length was " + amt + ", array length was " + array.length);
        }

        for (int i = 0; i < amt; i++) {
            if (Double.isNaN(array[i])) {
                throw new IllegalArgumentException("Value of " + array[i] + " was invalid.");
            }
        }
        return array;
    }

    private static int[] verify(final int[] array, final int amt) {
        if (amt != array.length) {
            throw new IllegalArgumentException(
                "Invalid array length, required length was " + amt + ", array length was " + array.length);
        }
        return array;
    }

    private static int findOpenID(final Object[] array, int start, final boolean loop) {
        final int l = array.length;

        if (start < l && array[start] == null) return start;
        else if (start >= l || start < 0) start = 0;

        boolean once = !loop;

        for (int i = 0; i < l; i++) {
            if (!once && start + i >= l) // only check through the entire array once
            {
                start = i = 0;
                once = true;
            } else if (once && start + i >= l) break;

            if (array[start + i] != null) continue;
            return start + i;
        }

        TragicMC.logWarning(
            "No valid values were found for starting id of " + start + ", default to 0, may result in a crash.");
        return 0;
    }

    public static int findBiomeID(int start) {
        return findOpenID(BiomeGenBase.getBiomeGenArray(), start, true);
    }

    public static int findEnchantID(int start) {
        return findOpenID(Enchantment.enchantmentsList, start, true);
    }

    public static int findPotionID(int start) {
        return findOpenID(Potion.potionTypes, start, false);
    }
}
