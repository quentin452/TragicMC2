package tragicneko.tragicmc;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.commons.lang3.ArrayUtils;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.items.ItemAmuletRelease;
import tragicneko.tragicmc.items.ItemBleedingObsidianOrb;
import tragicneko.tragicmc.items.ItemBloodSacrifice;
import tragicneko.tragicmc.items.ItemChallenge;
import tragicneko.tragicmc.items.ItemCooldownDefuse;
import tragicneko.tragicmc.items.ItemCorruptedEgg;
import tragicneko.tragicmc.items.ItemCryingObsidianOrb;
import tragicneko.tragicmc.items.ItemDimensionalKey;
import tragicneko.tragicmc.items.ItemDoomUpgrade;
import tragicneko.tragicmc.items.ItemDoomsdayScroll;
import tragicneko.tragicmc.items.ItemDyingObsidianOrb;
import tragicneko.tragicmc.items.ItemGenerator;
import tragicneko.tragicmc.items.ItemGeneric;
import tragicneko.tragicmc.items.ItemMobEgg;
import tragicneko.tragicmc.items.ItemNekoWand;
import tragicneko.tragicmc.items.ItemNourishmentSacrifice;
import tragicneko.tragicmc.items.ItemProjectile;
import tragicneko.tragicmc.items.ItemSoundExtrapolator;
import tragicneko.tragicmc.items.ItemStatue;
import tragicneko.tragicmc.items.ItemTalisman;
import tragicneko.tragicmc.items.amulet.AmuletBlacksmith;
import tragicneko.tragicmc.items.amulet.AmuletChicken;
import tragicneko.tragicmc.items.amulet.AmuletClaymation;
import tragicneko.tragicmc.items.amulet.AmuletCreeper;
import tragicneko.tragicmc.items.amulet.AmuletEnyvil;
import tragicneko.tragicmc.items.amulet.AmuletIronGolem;
import tragicneko.tragicmc.items.amulet.AmuletKitsune;
import tragicneko.tragicmc.items.amulet.AmuletLuck;
import tragicneko.tragicmc.items.amulet.AmuletPeace;
import tragicneko.tragicmc.items.amulet.AmuletSnowGolem;
import tragicneko.tragicmc.items.amulet.AmuletSpider;
import tragicneko.tragicmc.items.amulet.AmuletSunken;
import tragicneko.tragicmc.items.amulet.AmuletSupernatural;
import tragicneko.tragicmc.items.amulet.AmuletTime;
import tragicneko.tragicmc.items.amulet.AmuletUndead;
import tragicneko.tragicmc.items.amulet.ItemAmulet;
import tragicneko.tragicmc.items.amulet.ItemAmulet.EnumAmuletType;
import tragicneko.tragicmc.items.armor.ArmorDark;
import tragicneko.tragicmc.items.armor.ArmorHunter;
import tragicneko.tragicmc.items.armor.ArmorLight;
import tragicneko.tragicmc.items.armor.ArmorMercury;
import tragicneko.tragicmc.items.armor.ArmorOverlord;
import tragicneko.tragicmc.items.armor.ArmorSkull;
import tragicneko.tragicmc.items.armor.ArmorTungsten;
import tragicneko.tragicmc.items.food.ItemBanana;
import tragicneko.tragicmc.items.food.ItemBananaSplit;
import tragicneko.tragicmc.items.food.ItemBoneMarrow;
import tragicneko.tragicmc.items.food.ItemEnchantedSushi;
import tragicneko.tragicmc.items.food.ItemExoticFruit;
import tragicneko.tragicmc.items.food.ItemFoodSeed;
import tragicneko.tragicmc.items.food.ItemGooeyFruit;
import tragicneko.tragicmc.items.food.ItemIceCream;
import tragicneko.tragicmc.items.food.ItemNastyFruit;
import tragicneko.tragicmc.items.food.ItemRice;
import tragicneko.tragicmc.items.food.ItemSkyFruit;
import tragicneko.tragicmc.items.food.ItemSushi;
import tragicneko.tragicmc.items.food.ItemTentacle;
import tragicneko.tragicmc.items.weapons.ItemEverlastingLight;
import tragicneko.tragicmc.items.weapons.ItemJack;
import tragicneko.tragicmc.items.weapons.ItemScythe;
import tragicneko.tragicmc.items.weapons.TragicWeapon;
import tragicneko.tragicmc.items.weapons.WeaponBeastlyClaws;
import tragicneko.tragicmc.items.weapons.WeaponBlindingLight;
import tragicneko.tragicmc.items.weapons.WeaponBowOfJustice;
import tragicneko.tragicmc.items.weapons.WeaponButcher;
import tragicneko.tragicmc.items.weapons.WeaponCelestialAegis;
import tragicneko.tragicmc.items.weapons.WeaponCelestialLongbow;
import tragicneko.tragicmc.items.weapons.WeaponDragonFang;
import tragicneko.tragicmc.items.weapons.WeaponFrozenLightning;
import tragicneko.tragicmc.items.weapons.WeaponGravitySpike;
import tragicneko.tragicmc.items.weapons.WeaponGuiltyThorn;
import tragicneko.tragicmc.items.weapons.WeaponHarmonyBell;
import tragicneko.tragicmc.items.weapons.WeaponHuntersBow;
import tragicneko.tragicmc.items.weapons.WeaponIreParticleCannon;
import tragicneko.tragicmc.items.weapons.WeaponMourningStar;
import tragicneko.tragicmc.items.weapons.WeaponNekoLauncher;
import tragicneko.tragicmc.items.weapons.WeaponParanoia;
import tragicneko.tragicmc.items.weapons.WeaponPitchBlack;
import tragicneko.tragicmc.items.weapons.WeaponReaperScythe;
import tragicneko.tragicmc.items.weapons.WeaponSentinel;
import tragicneko.tragicmc.items.weapons.WeaponSilentHellraiser;
import tragicneko.tragicmc.items.weapons.WeaponSplinter;
import tragicneko.tragicmc.items.weapons.WeaponSwordOfJustice;
import tragicneko.tragicmc.items.weapons.WeaponThardus;
import tragicneko.tragicmc.items.weapons.WeaponTitan;
import tragicneko.tragicmc.items.weapons.WeaponWitheringAxe;
import tragicneko.tragicmc.properties.PropertyAmulets;

public class TragicItems {

    // Item variables
    public static Item RedMercury, Quicksilver, QuicksilverIngot, Jack;
    public static Item MercuryDagger, MercuryHelm, MercuryPlate, MercuryLegs, MercuryBoots;
    public static Item Tungsten, TungstenJack, TungstenHelm, TungstenPlate, TungstenLegs, TungstenBoots;

    public static Item Scythe, SkullHelmet, SkullPlate, SkullLegs, SkullBoots;
    public static Item HuntersBow, HuntersCap, HuntersTunic, HuntersLegs, HuntersBoots;

    public static Item DarkIngot, DarkHelm, DarkPlate, DarkLegs, DarkBoots, PitchBlack;
    public static Item LightIngot, LightHelm, LightPlate, LightLegs, LightBoots, BlindingLight, EverlastingLight;

    public static Item OverlordHelm, OverlordPlate, OverlordLegs, OverlordBoots;

    public static Item CelestialAegis, CelestialLongbow, CelestialJack, CelestialDiamond, CelestialSteel;
    public static Item GravitySpike, HarmonyBell, MourningStar, BeastlyClaws, GuiltyThorn, NekoLauncher, ReaperScythe,
        WitheringAxe, FrozenLightning;

    public static Item Splinter, Titan, Butcher, Thardus, Paranoia, DragonFang, SilentHellraiser;

    public static Item Sentinel, Password;

    public static Item RainDanceTalisman, SunnyDayTalisman, ThunderstormTalisman, TimeManipulatorTalisman;
    public static Item Talisman, MoonlightTalisman, SynthesisTalisman, HydrationTalisman, LightningRodTalisman;

    public static Item Ruby, Sapphire, RubyCharm, SapphireCharm, DiamondCharm, EmeraldCharm, AwakeningStone,
        ObsidianOrb, CryingObsidianOrb, BleedingObsidianOrb, DyingObsidianOrb;

    public static Item PureLight; // Apis
    public static Item EmpariahClaw; // Empariah
    public static Item DeathlyHallow; // Skultar
    public static Item TimeEssence; // Time Controller
    public static Item PureDarkness; // Enyvil
    public static Item KitsuneTail; // Kitsunakuma
    public static Item LivingClay; // Claymation
    public static Item StarPieces; // Polaris
    public static Item CorruptedEye; // The Overlord

    public static Item LostSoul; // Shina
    public static Item TimeLockedIron; // Axyr
    public static Item NightmareChain; // Petriv
    public static Item VoltageGenerator; // Amsheer
    public static Item AkhoraSkin; // Akhora
    public static Item LeviathanEye; // Leviathan
    public static Item FrozenBulb; // Polypus
    public static Item EnigmaticCore; // Cirela
    public static Item PhoenixFeather; // Phoenixes

    // Doom items
    public static Item DoomConsume, CooldownDefuse, AmuletRelease;

    // Amulets
    public static Item KitsuneAmulet;
    public static Item YetiAmulet;
    public static Item PeaceAmulet;
    public static Item ClaymationAmulet;
    public static Item ChickenAmulet;
    public static Item MartyrAmulet;
    public static Item PiercingAmulet;
    public static Item BlacksmithAmulet;
    public static Item ApisAmulet;
    public static Item CreeperAmulet;
    public static Item ZombieAmulet;
    public static Item SkeletonAmulet;
    public static Item SunkenAmulet;
    public static Item TimeAmulet;
    public static Item IceAmulet;
    public static Item SnowGolemAmulet;
    public static Item IronGolemAmulet;
    public static Item EndermanAmulet;
    public static Item WitherAmulet;
    public static Item SpiderAmulet;
    public static Item StinAmulet;
    public static Item PolarisAmulet;
    public static Item OverlordAmulet;
    public static Item LightningAmulet;
    public static Item ConsumptionAmulet;
    public static Item SupernaturalAmulet;
    public static Item UndeadAmulet;
    public static Item EnderDragonAmulet;
    public static Item FuseaAmulet;
    public static Item EnyvilAmulet;
    public static Item LuckAmulet;

    // Normal items
    public static Item Ectoplasm;
    public static Item Ash;
    public static Item ToughLeather;
    public static Item EnchantedTears;
    public static Item WovenSilk;
    public static Item CrushedIce;
    public static Item DarkParticles;
    public static Item LightParticles;
    public static Item Thorns;
    public static Item BoneMarrow;
    public static Item Horn;
    public static Item NanoBots;
    public static Item CatalyticCompound;
    public static Item InterestingResin;
    public static Item Chitin;
    public static Item SoulExcess;
    public static Item EtherealDistortion;
    public static Item IceOrb, GravityOrb, FireOrb, LightningOrb, AquaOrb;

    public static Item ToxicAmalgation;
    public static Item LunarPowder;
    public static Item ParadoxicalFormula;
    public static Item RadiatedInfusion;
    public static Item ImpossibleReaction;
    public static Item InfallibleMetal;
    public static Item ComplexCircuitry;
    public static Item NauseatingConcoction;
    public static Item CreepyIdol;
    public static Item PurifiedEnergy;
    public static Item Shadowskin;

    // mob-specific drops
    public static Item WispParticles;
    public static Item StinHorn;
    public static Item IcyFur;
    public static Item ArchangelFeather;
    public static Item IreNode;
    public static Item UnstableIsotope;
    public static Item CorruptedEssence;

    public static Item BloodSacrifice;
    public static Item NourishmentSacrifice;
    public static Item SoulSacrifice;

    // Projectile items
    public static Item Projectile;

    // Food items
    public static Item IceCream;
    public static Item Honeydrop;
    public static Item Gloopii;
    public static Item Deathglow;
    public static Item Rice;
    public static Item Sushi;
    public static Item Tentacle;
    public static Item Banana;
    public static Item BananaSplit;
    public static Item GoldenSushi;
    public static Item SkyFruit;

    public static Item HoneydropSeeds;
    public static Item DeathglowSeeds;
    public static Item SkyFruitSeeds;

    public static Item DimensionalKey, DimensionalKeyEnd, DimensionalKeyNether, DimensionalKeySynapse;
    public static Item SynapseLink, SynapseCrystal;

    public static Item BowOfJustice;
    public static Item SwordOfJustice;
    public static Item Generator;
    public static Item NekoNekoWand;
    public static Item SoundExtrapolator;
    public static Item WingsOfLiberation;
    public static Item ParanormalBox; // while in your hands, you can fly through blocks & entities like a ghost, but
                                      // you slowly lose health & hunger
    public static Item IreNetParticleCannon;

    public static Item SpawnEgg;
    public static Item CorruptedEgg;
    public static Item MobStatue;
    public static Item ChallengeScroll;
    public static Item DoomsdayScroll;

    public static ChestGenHooks BossStructureHook;
    public static ChestGenHooks NetherStructureHook;
    public static ChestGenHooks LameChestHook;
    public static ChestGenHooks AwesomeChestHook;

    // Tool Materials
    private static final ToolMaterial toolBasic = EnumHelper.addToolMaterial("BASIC", 1, 115, 6.0F, 0.0F, 1);
    private static final ToolMaterial toolScythe = EnumHelper.addToolMaterial("SCYTHE", 1, 110, 10.0F, 0.0F, 6);
    private static final ToolMaterial toolJack = EnumHelper.addToolMaterial("JACK", 3, 825, 14.0F, 2.0F, 5);
    private static final ToolMaterial toolGravity = EnumHelper.addToolMaterial("GRAVITY", 1, 220, 0.5F, 0.5F, 10);
    private static final ToolMaterial toolHarmony = EnumHelper.addToolMaterial("HARMONY", 0, 330, 0F, -4F, 5);
    private static final ToolMaterial toolLauncher = EnumHelper.addToolMaterial("LAUNCHER", 0, 330, 0F, 0F, 1);
    private static final ToolMaterial toolHunter = EnumHelper.addToolMaterial("HUNTER", 1, 160, 1F, 1F, 4);
    private static final ToolMaterial toolMercury = EnumHelper.addToolMaterial("MERCURY", 2, 160, 3.0F, 3.0F, 12);
    private static final ToolMaterial toolClaws = EnumHelper.addToolMaterial("CLAWS", 2, 350, 4.0F, 4.0F, 2);
    private static final ToolMaterial toolThorn = EnumHelper.addToolMaterial("THORN", 1, 330, 6.0F, 6.0F, 4);
    private static final ToolMaterial toolFrozen = EnumHelper.addToolMaterial("FROZEN", 2, 400, 6.0F, 6.0F, 3);
    private static final ToolMaterial toolWithering = EnumHelper.addToolMaterial("WITHERING", 3, 550, 8.0F, 6.0F, 16);
    private static final ToolMaterial toolDarkness = EnumHelper.addToolMaterial("DARKNESS", 3, 350, 8.0F, 8.0F, 26);
    private static final ToolMaterial toolLight = EnumHelper.addToolMaterial("LIGHT", 3, 333, 8.0F, 8.0F, 14);
    private static final ToolMaterial toolMourning = EnumHelper.addToolMaterial("MOURNING", 3, 440, 9.0F, 9.0F, 14);
    private static final ToolMaterial toolReaper = EnumHelper.addToolMaterial("REAPER", 3, 800, 20.0F, 12.0F, 10);
    private static final ToolMaterial toolCelesJack = EnumHelper.addToolMaterial("CELESJACK", 3, 1280, 14.0F, 4.0F, 4);
    private static final ToolMaterial toolCelestial = EnumHelper
        .addToolMaterial("CELESTIAL", 3, 1080, 14.0F, 14.0F, 24);
    private static final ToolMaterial toolDragon = EnumHelper.addToolMaterial("DRAGON", 3, 860, 14.0F, 14.0F, 18);
    private static final ToolMaterial toolCorruptThorn = EnumHelper
        .addToolMaterial("CORRUPTEDTHORN", 3, 480, 6.0F, 16.0F, 12);
    private static final ToolMaterial toolTragic = EnumHelper.addToolMaterial("TRAGIC", 3, 400, 21.0F, 21.0F, 28);
    private static final ToolMaterial toolHarvester = EnumHelper.addToolMaterial("HARVESTER", 3, 960, 30.0F, 18.0F, 12);
    private static final ToolMaterial toolAwakened = EnumHelper.addToolMaterial("AWAKENED", 3, 1000, 26.0F, 26.0F, 32);
    private static final ToolMaterial toolSentinel = EnumHelper.addToolMaterial("SENTINEL", 4, 9001, 26.0F, 26.0F, 32);
    private static final ToolMaterial toolJustice = EnumHelper
        .addToolMaterial("JUSTICE", 100, 1000, Float.MAX_VALUE, 999999996.0F, 100);

    // Armor Materials
    private static final ArmorMaterial armorSkull = EnumHelper
        .addArmorMaterial("SKULL", 32, new int[] { 1, 2, 1, 1 }, 16); // 5
    private static final ArmorMaterial armorHunter = EnumHelper
        .addArmorMaterial("HUNTER", 29, new int[] { 2, 3, 2, 2 }, 8); // 9
    private static final ArmorMaterial armorMercury = EnumHelper
        .addArmorMaterial("MERCURY", 24, new int[] { 2, 5, 4, 2 }, 22); // 13
    private static final ArmorMaterial armorTungsten = EnumHelper
        .addArmorMaterial("TUNGSTEN", 22, new int[] { 3, 6, 4, 2 }, 10); // 15
    private static final ArmorMaterial armorDark = EnumHelper.addArmorMaterial("DARK", 18, new int[] { 3, 7, 5, 3 }, 6); // 18
    private static final ArmorMaterial armorLight = EnumHelper
        .addArmorMaterial("LIGHT", 26, new int[] { 5, 7, 6, 4 }, 18); // 22
    private static final ArmorMaterial armorOverlord = EnumHelper
        .addArmorMaterial("OVERLORD", 35, new int[] { 5, 8, 7, 4 }, 26); // 24

    public static final Item test = new TragicWeapon(toolAwakened, null).setAscensionLevel(5F)
        .setUnlocalizedName("tragicmc.test")
        .setTextureName("iron_sword")
        .setCreativeTab(TragicMC.Survival); // Silent hellraiser upgrade
    public static final Item test2 = new TragicWeapon(toolHarvester, null).setAscensionLevel(8F)
        .setUnlocalizedName("tragicmc.test2")
        .setTextureName("diamond_sword")
        .setCreativeTab(TragicMC.Survival); // Reaper scythe upgrade
    public static final Item test3 = new TragicWeapon(toolCorruptThorn, null).setAscensionLevel(5F)
        .setUnlocalizedName("tragicmc.test3")
        .setTextureName("stone_sword")
        .setCreativeTab(TragicMC.Survival); // Guilty thorn upgrade

    public static void load() {
        if (!TragicConfig.allowNonMobItems) {
            SpawnEgg = (new ItemMobEgg());
            GameRegistry.registerItem(SpawnEgg, "spawnEgg");

            BowOfJustice = (new WeaponBowOfJustice().setUnlocalizedName("tragicmc.bowOfJustice")
                .setTextureName("bow"));
            GameRegistry.registerItem(BowOfJustice, "bowOfJustice");

            SwordOfJustice = (new WeaponSwordOfJustice(toolJustice).setUnlocalizedName("tragicmc.swordOfJustice")
                .setTextureName("gold_sword"));
            GameRegistry.registerItem(SwordOfJustice, "swordOfJustice");

            NekoNekoWand = (new ItemNekoWand().setUnlocalizedName("tragicmc.nekoNekoWand")
                .setTextureName("tragicmc:NekoNekoWand"));
            GameRegistry.registerItem(NekoNekoWand, "nekoNekoWand");

            Projectile = (new ItemProjectile());
            GameRegistry.registerItem(Projectile, "projectile");
            return;
        }
        // Ore Registrations
        RedMercury = (new ItemGeneric().setUnlocalizedName("tragicmc.redMercury")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:RedMercury"));
        GameRegistry.registerItem(RedMercury, "redMercury");

        Quicksilver = (new ItemGeneric().setUnlocalizedName("tragicmc.quicksilver")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:Quicksilver"));
        GameRegistry.registerItem(Quicksilver, "quicksilver");

        QuicksilverIngot = (new ItemGeneric().setUnlocalizedName("tragicmc.quicksilverIngot")
            .setMaxStackSize(16)
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:QuicksilverIngot"));
        GameRegistry.registerItem(QuicksilverIngot, "quicksilverIngot");

        Tungsten = (new ItemGeneric().setUnlocalizedName("tragicmc.tungsten")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:Tungsten"));
        GameRegistry.registerItem(Tungsten, "tungsten");

        Ruby = (new ItemGeneric().setUnlocalizedName("tragicmc.ruby")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:Ruby"));
        GameRegistry.registerItem(Ruby, "ruby");

        Sapphire = (new ItemGeneric().setUnlocalizedName("tragicmc.sapphire")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:Sapphire"));
        GameRegistry.registerItem(Sapphire, "sapphire");

        // Armor Registrations
        SkullHelmet = (new ArmorSkull(armorSkull, 0, Doomsday.Decay).setUnlocalizedName("tragicmc.skullHelmet")
            .setTextureName("tragicmc:SkullHelmet"));
        GameRegistry.registerItem(SkullHelmet, "skullHelmet");

        SkullPlate = (new ArmorSkull(armorSkull, 1, Doomsday.Decay).setUnlocalizedName("tragicmc.skullPlate")
            .setTextureName("tragicmc:SkullPlate"));
        GameRegistry.registerItem(SkullPlate, "skullPlate");

        SkullLegs = (new ArmorSkull(armorSkull, 2, Doomsday.Decay).setUnlocalizedName("tragicmc.skullLegs")
            .setTextureName("tragicmc:SkullLegs"));
        GameRegistry.registerItem(SkullLegs, "skullLegs");

        SkullBoots = (new ArmorSkull(armorSkull, 3, Doomsday.Decay).setUnlocalizedName("tragicmc.skullBoots")
            .setTextureName("tragicmc:SkullBoots"));
        GameRegistry.registerItem(SkullBoots, "skullBoots");

        HuntersCap = (new ArmorHunter(armorHunter, 0, Doomsday.HuntersInstinct)
            .setUnlocalizedName("tragicmc.huntersCap")
            .setTextureName("tragicmc:HuntersCap"));
        GameRegistry.registerItem(HuntersCap, "huntersCap");

        HuntersTunic = (new ArmorHunter(armorHunter, 1, Doomsday.HuntersInstinct)
            .setUnlocalizedName("tragicmc.huntersTunic")
            .setTextureName("tragicmc:HuntersTunic"));
        GameRegistry.registerItem(HuntersTunic, "huntersTunic");

        HuntersLegs = (new ArmorHunter(armorHunter, 2, Doomsday.HuntersInstinct)
            .setUnlocalizedName("tragicmc.huntersLegs")
            .setTextureName("tragicmc:HuntersLegs"));
        GameRegistry.registerItem(HuntersLegs, "huntersLegs");

        HuntersBoots = (new ArmorHunter(armorHunter, 3, Doomsday.HuntersInstinct)
            .setUnlocalizedName("tragicmc.huntersBoots")
            .setTextureName("tragicmc:HuntersBoots"));
        GameRegistry.registerItem(HuntersBoots, "huntersBoots");

        MercuryHelm = (new ArmorMercury(armorMercury, 0, Doomsday.Toxicity).setUnlocalizedName("tragicmc.mercuryHelm")
            .setTextureName("tragicmc:MercuryHelm"));
        GameRegistry.registerItem(MercuryHelm, "mercuryHelm");

        MercuryPlate = (new ArmorMercury(armorMercury, 1, Doomsday.Toxicity).setUnlocalizedName("tragicmc.mercuryPlate")
            .setTextureName("tragicmc:MercuryPlate"));
        GameRegistry.registerItem(MercuryPlate, "mercuryPlate");

        MercuryLegs = (new ArmorMercury(armorMercury, 2, Doomsday.Toxicity).setUnlocalizedName("tragicmc.mercuryLegs")
            .setTextureName("tragicmc:MercuryLegs"));
        GameRegistry.registerItem(MercuryLegs, "mercuryLegs");

        MercuryBoots = (new ArmorMercury(armorMercury, 3, Doomsday.Toxicity).setUnlocalizedName("tragicmc.mercuryBoots")
            .setTextureName("tragicmc:MercuryBoots"));
        GameRegistry.registerItem(MercuryBoots, "mercuryBoots");

        TungstenHelm = (new ArmorTungsten(armorTungsten, 0, Doomsday.Berserker)
            .setUnlocalizedName("tragicmc.tungstenHelm")
            .setTextureName("tragicmc:TungstenHelm"));
        GameRegistry.registerItem(TungstenHelm, "tungstenHelm");

        TungstenPlate = (new ArmorTungsten(armorTungsten, 1, Doomsday.Berserker)
            .setUnlocalizedName("tragicmc.tungstenPlate")
            .setTextureName("tragicmc:TungstenPlate"));
        GameRegistry.registerItem(TungstenPlate, "tungstenPlate");

        TungstenLegs = (new ArmorTungsten(armorTungsten, 2, Doomsday.Berserker)
            .setUnlocalizedName("tragicmc.tungstenLegs")
            .setTextureName("tragicmc:TungstenLegs"));
        GameRegistry.registerItem(TungstenLegs, "tungstenLegs");

        TungstenBoots = (new ArmorTungsten(armorTungsten, 3, Doomsday.Berserker)
            .setUnlocalizedName("tragicmc.tungstenBoots")
            .setTextureName("tragicmc:TungstenBoots"));
        GameRegistry.registerItem(TungstenBoots, "tungstenBoots");

        LightHelm = (new ArmorLight(armorLight, 0, Doomsday.LightShove).setUnlocalizedName("tragicmc.lightHelm")
            .setTextureName("tragicmc:LightHelm"));
        GameRegistry.registerItem(LightHelm, "lightHelm");

        LightPlate = (new ArmorLight(armorLight, 1, Doomsday.LightShove).setUnlocalizedName("tragicmc.lightPlate")
            .setTextureName("tragicmc:LightPlate"));
        GameRegistry.registerItem(LightPlate, "lightPlate");

        LightLegs = (new ArmorLight(armorLight, 2, Doomsday.LightShove).setUnlocalizedName("tragicmc.lightLegs")
            .setTextureName("tragicmc:LightLegs"));
        GameRegistry.registerItem(LightLegs, "lightLegs");

        LightBoots = (new ArmorLight(armorLight, 3, Doomsday.LightShove).setUnlocalizedName("tragicmc.lightBoots")
            .setTextureName("tragicmc:LightBoots"));
        GameRegistry.registerItem(LightBoots, "lightBoots");

        DarkHelm = (new ArmorDark(armorDark, 0, Doomsday.Fear).setUnlocalizedName("tragicmc.darkHelm")
            .setTextureName("tragicmc:DarkHelm"));
        GameRegistry.registerItem(DarkHelm, "darkHelm");

        DarkPlate = (new ArmorDark(armorDark, 1, Doomsday.Fear).setUnlocalizedName("tragicmc.darkPlate")
            .setTextureName("tragicmc:DarkPlate"));
        GameRegistry.registerItem(DarkPlate, "darkPlate");

        DarkLegs = (new ArmorDark(armorDark, 2, Doomsday.Fear).setUnlocalizedName("tragicmc.darkLegs")
            .setTextureName("tragicmc:DarkLegs"));
        GameRegistry.registerItem(DarkLegs, "darkLegs");

        DarkBoots = (new ArmorDark(armorDark, 3, Doomsday.Fear).setUnlocalizedName("tragicmc.darkBoots")
            .setTextureName("tragicmc:DarkBoots"));
        GameRegistry.registerItem(DarkBoots, "darkBoots");

        OverlordHelm = (new ArmorOverlord(armorOverlord, 0, Doomsday.Harden).setUnlocalizedName("tragicmc.overlordHelm")
            .setTextureName("tragicmc:OverlordHelm"));
        GameRegistry.registerItem(OverlordHelm, "overlordHelm");

        OverlordPlate = (new ArmorOverlord(armorOverlord, 1, Doomsday.Harden)
            .setUnlocalizedName("tragicmc.overlordPlate")
            .setTextureName("tragicmc:OverlordPlate"));
        GameRegistry.registerItem(OverlordPlate, "overlordPlate");

        OverlordLegs = (new ArmorOverlord(armorOverlord, 2, Doomsday.Harden).setUnlocalizedName("tragicmc.overlordLegs")
            .setTextureName("tragicmc:OverlordLegs"));
        GameRegistry.registerItem(OverlordLegs, "overlordLegs");

        OverlordBoots = (new ArmorOverlord(armorOverlord, 3, Doomsday.Harden)
            .setUnlocalizedName("tragicmc.overlordBoots")
            .setTextureName("tragicmc:OverlordBoots"));
        GameRegistry.registerItem(OverlordBoots, "overlordBoots");

        // Weapon Registrations
        MercuryDagger = (new TragicWeapon(toolMercury, Doomsday.PoisonBreak)
            .setUnlocalizedName("tragicmc.mercuryDagger")
            .setTextureName("tragicmc:MercuryDagger"));
        GameRegistry.registerItem(MercuryDagger, "mercuryDagger");

        HuntersBow = (new WeaponHuntersBow().setUnlocalizedName("tragicmc.huntersBow")
            .setMaxStackSize(1)
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:HuntersBow"));
        GameRegistry.registerItem(HuntersBow, "huntersBow");

        PitchBlack = (new WeaponPitchBlack(toolDarkness, Doomsday.NatureDrain).setUnlocalizedName("tragicmc.pitchBlack")
            .setTextureName("tragicmc:PitchBlack"));
        GameRegistry.registerItem(PitchBlack, "pitchBlack");

        BlindingLight = (new WeaponBlindingLight(toolLight, Doomsday.PiercingLight)
            .setUnlocalizedName("tragicmc.blindingLight")
            .setTextureName("tragicmc:BlindingLight"));
        GameRegistry.registerItem(BlindingLight, "blindingLight");

        GravitySpike = (new WeaponGravitySpike(toolGravity, Doomsday.Pulse).setUnlocalizedName("tragicmc.gravitySpike")
            .setTextureName("tragicmc:GravitySpike"));
        GameRegistry.registerItem(GravitySpike, "gravitySpike");

        HarmonyBell = (new WeaponHarmonyBell(toolHarmony, Doomsday.Harmonizer)
            .setUnlocalizedName("tragicmc.harmonyBell")
            .setTextureName("tragicmc:HarmonyBell"));
        GameRegistry.registerItem(HarmonyBell, "harmonyBell");

        MourningStar = (new WeaponMourningStar(toolMourning, Doomsday.Ravage)
            .setUnlocalizedName("tragicmc.mourningStar")
            .setTextureName("tragicmc:MourningStar"));
        GameRegistry.registerItem(MourningStar, "mourningStar");

        BeastlyClaws = (new WeaponBeastlyClaws(toolClaws, Doomsday.BeastlyImpulses)
            .setUnlocalizedName("tragicmc.beastlyClaws")
            .setTextureName("tragicmc:BeastlyClaws"));
        GameRegistry.registerItem(BeastlyClaws, "beastlyClaws");

        GuiltyThorn = (new WeaponGuiltyThorn(toolThorn, Doomsday.Torment).setUnlocalizedName("tragicmc.guiltyThorn")
            .setTextureName("tragicmc:GuiltyThorn"));
        GameRegistry.registerItem(GuiltyThorn, "guiltyThorn");

        NekoLauncher = (new WeaponNekoLauncher(toolLauncher, Doomsday.SuicidalTendencies)
            .setUnlocalizedName("tragicmc.nekoLauncher")
            .setTextureName("tragicmc:NekoLauncher"));
        GameRegistry.registerItem(NekoLauncher, "nekoLauncher");

        ReaperScythe = (new WeaponReaperScythe(toolReaper, Doomsday.ReaperLaugh)
            .setUnlocalizedName("tragicmc.reaperScythe")
            .setTextureName("tragicmc:ReaperScythe"));
        GameRegistry.registerItem(ReaperScythe, "reaperScythe");

        WitheringAxe = (new WeaponWitheringAxe(toolWithering, Doomsday.SkullCrusher)
            .setUnlocalizedName("tragicmc.witheringAxe")
            .setTextureName("tragicmc:WitheringAxe"));
        GameRegistry.registerItem(WitheringAxe, "witheringAxe");

        FrozenLightning = (new WeaponFrozenLightning(toolFrozen, Doomsday.Freeze)
            .setUnlocalizedName("tragicmc.frozenLightning")
            .setTextureName("tragicmc:FrozenLightning"));
        GameRegistry.registerItem(FrozenLightning, "frozenLightning");

        CelestialAegis = (new WeaponCelestialAegis(toolCelestial, Doomsday.MoonlightSonata)
            .setUnlocalizedName("tragicmc.celestialAegis")
            .setTextureName("tragicmc:CelestialAegis"));
        GameRegistry.registerItem(CelestialAegis, "celestialAegis");

        CelestialLongbow = (new WeaponCelestialLongbow().setUnlocalizedName("tragicmc.celestialLongbow")
            .setTextureName("tragicmc:CelestialLongbow"));
        GameRegistry.registerItem(CelestialLongbow, "celestialLongbow");

        SilentHellraiser = (new WeaponSilentHellraiser(toolTragic, null).setUnlocalizedName("tragicmc.silentHellraiser")
            .setTextureName("tragicmc:TragicHellraiser"));
        GameRegistry.registerItem(SilentHellraiser, "silentHellraiser");

        // Epic weapons
        Titan = (new WeaponTitan(Doomsday.Titanfall).setUnlocalizedName("tragicmc.titan")
            .setTextureName("tragicmc:Titan"));
        GameRegistry.registerItem(Titan, "titan");

        Splinter = (new WeaponSplinter(Doomsday.Marionette).setUnlocalizedName("tragicmc.splinter")
            .setTextureName("tragicmc:Splinter"));
        GameRegistry.registerItem(Splinter, "splinter");

        Butcher = (new WeaponButcher(Doomsday.Bloodlust).setUnlocalizedName("tragicmc.butcher")
            .setTextureName("tragicmc:Butcher"));
        GameRegistry.registerItem(Butcher, "butcher");

        Thardus = (new WeaponThardus(Doomsday.Permafrost).setUnlocalizedName("tragicmc.thardus")
            .setTextureName("tragicmc:Thardus"));
        GameRegistry.registerItem(Thardus, "thardus");

        Paranoia = (new WeaponParanoia(Doomsday.Asphyxiate).setUnlocalizedName("tragicmc.paranoia")
            .setTextureName("tragicmc:Paranoia"));
        GameRegistry.registerItem(Paranoia, "paranoia");

        DragonFang = (new WeaponDragonFang(Doomsday.DragonsRoar).setUnlocalizedName("tragicmc.dragonFang")
            .setTextureName("tragicmc:DragonFang"));
        GameRegistry.registerItem(DragonFang, "dragonFang");

        // Big boss weapons
        Sentinel = (new WeaponSentinel(toolSentinel, Doomsday.Sharpen).setUnlocalizedName("tragicmc.sentinel")
            .setTextureName("tragicmc:Sentinel"));
        GameRegistry.registerItem(Sentinel, "sentinel");

        // Tool Registrations
        Scythe = (new ItemScythe(toolScythe, null).setUnlocalizedName("tragicmc.scythe")
            .setTextureName("tragicmc:Scythe"));
        GameRegistry.registerItem(Scythe, "scythe");

        EverlastingLight = (new ItemEverlastingLight().setUnlocalizedName("tragicmc.everlastingLight")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:EverlastingLight"));
        GameRegistry.registerItem(EverlastingLight, "everlastingLight");

        Jack = (new ItemJack(toolBasic, null, 1).setUnlocalizedName("tragicmc.jack")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:BasicJack"));
        GameRegistry.registerItem(Jack, "jack");

        TungstenJack = (new ItemJack(toolJack, Doomsday.MinerSkills, 2) {}.setUnlocalizedName("tragicmc.tungstenJack")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:TungstenJack"));
        GameRegistry.registerItem(TungstenJack, "tungstenJack");

        CelestialJack = (new ItemJack(toolCelesJack, Doomsday.RealityAlter, 3) {}
            .setUnlocalizedName("tragicmc.celestialJack")
            .setTextureName("tragicmc:CelestialJack"));
        GameRegistry.registerItem(CelestialJack, "celestialJack");

        // Normal Item Registrations
        Ectoplasm = (new ItemGeneric().setUnlocalizedName("tragicmc.ectoplasm")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:Ectoplasm"));
        GameRegistry.registerItem(Ectoplasm, "ectoplasm");

        Ash = (new ItemGeneric().setUnlocalizedName("tragicmc.ash")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:Ash"));
        GameRegistry.registerItem(Ash, "ash");

        EnchantedTears = (new ItemGeneric() {

            @SideOnly(Side.CLIENT)
            @Override
            public boolean hasEffect(ItemStack stack, int pass) {
                return pass == 0;
            }
        }.setUnlocalizedName("tragicmc.lifeWater")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:LifeWater"));
        GameRegistry.registerItem(EnchantedTears, "enchantedTears");

        ToughLeather = (new ItemGeneric().setUnlocalizedName("tragicmc.toughLeather")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:ToughLeather"));
        GameRegistry.registerItem(ToughLeather, "toughLeather");

        WovenSilk = (new ItemGeneric().setUnlocalizedName("tragicmc.wovenSilk")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:WovenSilk"));
        GameRegistry.registerItem(WovenSilk, "wovenSilk");

        CrushedIce = (new ItemGeneric().setUnlocalizedName("tragicmc.crushedIce")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:CrushedIce"));
        GameRegistry.registerItem(CrushedIce, "crushedIce");

        LightParticles = (new ItemGeneric().setUnlocalizedName("tragicmc.lightParticles")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:LightParticles"));
        GameRegistry.registerItem(LightParticles, "lightParticles");

        DarkParticles = (new ItemGeneric().setUnlocalizedName("tragicmc.darkParticles")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:DarkParticles"));
        GameRegistry.registerItem(DarkParticles, "darkParticles");

        IceOrb = (new ItemGeneric().setUnlocalizedName("tragicmc.iceOrb")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:IceOrb"));
        GameRegistry.registerItem(IceOrb, "iceOrb");

        GravityOrb = (new ItemGeneric().setUnlocalizedName("tragicmc.gravityOrb")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:GravityOrb"));
        GameRegistry.registerItem(GravityOrb, "gravityOrb");

        FireOrb = (new ItemGeneric().setUnlocalizedName("tragicmc.fireOrb")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:FireOrb"));
        GameRegistry.registerItem(FireOrb, "fireOrb");

        LightningOrb = (new ItemGeneric().setUnlocalizedName("tragicmc.lightningOrb")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:LightningOrb"));
        GameRegistry.registerItem(LightningOrb, "lightningOrb");

        AquaOrb = (new ItemGeneric().setUnlocalizedName("tragicmc.aquaOrb")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:AquaOrb"));
        GameRegistry.registerItem(AquaOrb, "aquaOrb");

        Thorns = (new ItemGeneric().setUnlocalizedName("tragicmc.thorns")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:Thorns"));
        GameRegistry.registerItem(Thorns, "thorns");

        Horn = (new ItemGeneric().setUnlocalizedName("tragicmc.horn")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:Horn"));
        GameRegistry.registerItem(Horn, "horn");

        BoneMarrow = (new ItemBoneMarrow(2, false).setUnlocalizedName("tragicmc.boneMarrow")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:BoneMarrow"));
        GameRegistry.registerItem(BoneMarrow, "boneMarrow");

        LightIngot = (new ItemGeneric().setUnlocalizedName("tragicmc.lightIngot")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:LightIngot"));
        GameRegistry.registerItem(LightIngot, "lightIngot");

        DarkIngot = (new ItemGeneric().setUnlocalizedName("tragicmc.darkIngot")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:DarkIngot"));
        GameRegistry.registerItem(DarkIngot, "darkIngot");

        KitsuneTail = (new ItemGeneric().setUnlocalizedName("tragicmc.kitsuneTail")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:KitsuneTail"));
        GameRegistry.registerItem(KitsuneTail, "kitsuneTail");

        DeathlyHallow = (new ItemGeneric().setUnlocalizedName("tragicmc.reaperSkull")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:DeathlyHallow"));
        GameRegistry.registerItem(DeathlyHallow, "deathlyHallow");

        EmpariahClaw = (new ItemGeneric().setUnlocalizedName("tragicmc.yetiClaw")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:YetiClaw"));
        GameRegistry.registerItem(EmpariahClaw, "yetiClaw");

        StarPieces = (new ItemGeneric().setUnlocalizedName("tragicmc.starPieces")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:StarPieces"));
        GameRegistry.registerItem(StarPieces, "starPieces");

        TimeEssence = (new ItemGeneric().setUnlocalizedName("tragicmc.timeEssence")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:TimeEssence"));
        GameRegistry.registerItem(TimeEssence, "timeEssence");

        PureLight = (new ItemGeneric().setUnlocalizedName("tragicmc.pureLight")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:PureLight"));
        GameRegistry.registerItem(PureLight, "pureLight");

        LunarPowder = (new ItemGeneric().setUnlocalizedName("tragicmc.lunarPowder")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:LunarPowder"));
        GameRegistry.registerItem(LunarPowder, "lunarPowder");

        CelestialDiamond = (new ItemGeneric().setUnlocalizedName("tragicmc.celestialDiamond")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:CelestialDiamond"));
        GameRegistry.registerItem(CelestialDiamond, "celestialDiamond");

        StinHorn = (new ItemGeneric().setUnlocalizedName("tragicmc.stinHorn")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:StinHorn"));
        GameRegistry.registerItem(StinHorn, "stinHorn");

        WispParticles = (new ItemGeneric().setUnlocalizedName("tragicmc.wispParticles")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:WispParticles"));
        GameRegistry.registerItem(WispParticles, "wispParticles");

        IcyFur = (new ItemGeneric().setUnlocalizedName("tragicmc.icyFur")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:IcyFur"));
        GameRegistry.registerItem(IcyFur, "icyFur");

        PureDarkness = (new ItemGeneric().setUnlocalizedName("tragicmc.pureDarkness")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:PureDarkness"));
        GameRegistry.registerItem(PureDarkness, "pureDarkness");

        LivingClay = (new ItemGeneric().setUnlocalizedName("tragicmc.livingClay")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:LivingClay"));
        GameRegistry.registerItem(LivingClay, "livingClay");

        CelestialSteel = (new ItemGeneric().setUnlocalizedName("tragicmc.celestialSteel")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:CelestialSteel"));
        GameRegistry.registerItem(CelestialSteel, "celestialSteel");

        SynapseCrystal = (new ItemGeneric().setUnlocalizedName("tragicmc.synapseCrystal")
            .setMaxStackSize(64)
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:SynapseCrystal"));
        GameRegistry.registerItem(SynapseCrystal, "synapseCrystal");

        CorruptedEye = (new ItemGeneric().setUnlocalizedName("tragicmc.corruptedEye")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:CorruptedEye"));
        GameRegistry.registerItem(CorruptedEye, "corruptedEye");

        CorruptedEssence = (new ItemGeneric().setUnlocalizedName("tragicmc.corruptedEssence")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:CorruptedEssence"));
        GameRegistry.registerItem(CorruptedEssence, "corruptedEssence");

        CorruptedEgg = (new ItemCorruptedEgg());
        GameRegistry.registerItem(CorruptedEgg, "corruptedEgg");

        NanoBots = (new ItemGeneric().setUnlocalizedName("tragicmc.nanoBots")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:NanoBots"));
        GameRegistry.registerItem(NanoBots, "nanoBots");

        UnstableIsotope = (new ItemGeneric().setUnlocalizedName("tragicmc.unstableIsotope")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:UnstableIsotope"));
        GameRegistry.registerItem(UnstableIsotope, "unstableIsotope");

        ArchangelFeather = (new ItemGeneric().setUnlocalizedName("tragicmc.archangelFeather")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:ArchangelFeather"));
        GameRegistry.registerItem(ArchangelFeather, "archangelFeather");

        WingsOfLiberation = (new ItemGeneric() {

            @Override
            public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5) {
                if (par5 && entity instanceof EntityPlayer && TragicConfig.allowFlight) {
                    if (itemstack.getItemDamage() < itemstack.getMaxDamage() && itemstack.stackSize > 0) {
                        if (!entity.onGround && entity.ticksExisted % 20 == 0)
                            itemstack.damageItem(1, (EntityPlayer) entity);

                        entity.fallDistance = 0F;
                        if (!world.isRemote)
                            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(TragicPotion.Flight.id, 5));
                        else world.spawnParticle(
                            "crit",
                            entity.posX + world.rand.nextDouble() - world.rand.nextDouble(),
                            entity.posY + 1.0 + world.rand.nextDouble() - world.rand.nextDouble(),
                            entity.posZ + world.rand.nextDouble() - world.rand.nextDouble(),
                            0,
                            0,
                            0);
                    }
                }
            }
        }.setUnlocalizedName("tragicmc.wingsOfLiberation")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:WingsOfLiberation")
            .setMaxDamage(500));
        GameRegistry.registerItem(WingsOfLiberation, "wingsOfLiberation");

        IreNode = (new ItemGeneric().setUnlocalizedName("tragicmc.ireNode")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:IreNode"));
        GameRegistry.registerItem(IreNode, "ireNode");

        IreNetParticleCannon = (new WeaponIreParticleCannon(toolLauncher, Doomsday.Flash)
            .setUnlocalizedName("tragicmc.ireNetParticleCannon")
            .setTextureName("tragicmc:IreParticleCannon"));
        GameRegistry.registerItem(IreNetParticleCannon, "ireParticleCannon");

        CatalyticCompound = (new ItemGeneric().setUnlocalizedName("tragicmc.catalyticCompound")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:CatalyticCompound"));
        GameRegistry.registerItem(CatalyticCompound, "catalyticCompound");

        InterestingResin = (new ItemGeneric().setUnlocalizedName("tragicmc.interestingResin")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:Sap"));
        GameRegistry.registerItem(InterestingResin, "interestingResin");

        Chitin = (new ItemGeneric().setUnlocalizedName("tragicmc.chitin")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:Chitin"));
        GameRegistry.registerItem(Chitin, "chitin");

        SoulExcess = (new ItemGeneric().setUnlocalizedName("tragicmc.soulExcess")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:SoulExcess"));
        GameRegistry.registerItem(SoulExcess, "soulExcess");

        EtherealDistortion = (new ItemGeneric().setUnlocalizedName("tragicmc.etherealDistortion")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:EtherealDistortion"));
        GameRegistry.registerItem(EtherealDistortion, "etherealDistortion");

        // Catalyst items
        ToxicAmalgation = (new ItemGeneric() {

            @Override
            public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List,
                boolean par4) {
                par2List.add("I wouldn't stare at it too long if I were you...");
            }
        }.setUnlocalizedName("tragicmc.toxicAmalgation")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:ToxicAmalgation"));
        GameRegistry.registerItem(ToxicAmalgation, "toxicAmalgation");

        ParadoxicalFormula = (new ItemGeneric() {

            @Override
            public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List,
                boolean par4) {
                par2List.add("I always tell the truth. This is a lie.");
            }
        }.setUnlocalizedName("tragicmc.paradoxicalFormula")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:ParadoxicalFormula"));
        GameRegistry.registerItem(ParadoxicalFormula, "paradoxicalFormula");

        RadiatedInfusion = (new ItemGeneric() {

            @Override
            public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List,
                boolean par4) {
                par2List.add("So this is what happens when you stare at the sun too long.");
            }
        }.setUnlocalizedName("tragicmc.radiatedInfusion")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:RadiatedInfusion"));
        GameRegistry.registerItem(RadiatedInfusion, "radiatedInfusion");

        ImpossibleReaction = (new ItemGeneric() {

            @Override
            public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List,
                boolean par4) {
                par2List.add("It's just not possible!");
            }
        }.setUnlocalizedName("tragicmc.impossibleReaction")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:ImpossibleReaction"));
        GameRegistry.registerItem(ImpossibleReaction, "impossibleReaction");

        InfallibleMetal = (new ItemGeneric() {

            @Override
            public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List,
                boolean par4) {
                par2List.add("It's so beautiful...");
            }
        }.setUnlocalizedName("tragicmc.infallibleMetal")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:InfallibleMetal"));
        GameRegistry.registerItem(InfallibleMetal, "infallibleMetal");

        ComplexCircuitry = (new ItemGeneric() {

            @Override
            public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List,
                boolean par4) {
                par2List.add("It's a mess of wires, at least to a layman.");
            }
        }.setUnlocalizedName("tragicmc.complexCircuitry")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:ComplexCircuitry"));
        GameRegistry.registerItem(ComplexCircuitry, "complexCircuitry");

        NauseatingConcoction = (new ItemGeneric() {

            @Override
            public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List,
                boolean par4) {
                par2List.add("I don't even want to know what this is...");
                par2List.add("...I think that purple thing just winked at me...");
            }
        }.setUnlocalizedName("tragicmc.nauseatingConcoction")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:NauseatingConcoction"));
        GameRegistry.registerItem(NauseatingConcoction, "nauseatingConcoction");

        CreepyIdol = (new ItemGeneric() {

            @Override
            public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List,
                boolean par4) {
                par2List.add("This is kinda freaky...");
            }
        }.setUnlocalizedName("tragicmc.creepyIdol")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:CreepyIdol"));
        GameRegistry.registerItem(CreepyIdol, "creepyIdol");

        PurifiedEnergy = (new ItemGeneric() {

            @Override
            public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List,
                boolean par4) {
                par2List.add("Unlimited power!");
            }
        }.setUnlocalizedName("tragicmc.purifiedEnergy")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:PurifiedEnergy"));
        GameRegistry.registerItem(PurifiedEnergy, "purifiedEnergy");

        Shadowskin = (new ItemGeneric() {

            @Override
            public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List,
                boolean par4) {
                par2List.add("Caution: Imbued with great evil.");
            }
        }.setUnlocalizedName("tragicmc.shadowskin")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:Shadowskin"));
        GameRegistry.registerItem(Shadowskin, "shadowskin");

        // Food Registrations
        IceCream = (new ItemIceCream(4, false).setUnlocalizedName("tragicmc.iceCream")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:IceCream"));
        GameRegistry.registerItem(IceCream, "iceCream");

        Honeydrop = (new ItemExoticFruit(9, false).setUnlocalizedName("tragicmc.exoticFruit")
            .setMaxStackSize(16)
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:ExoticFruit"));
        GameRegistry.registerItem(Honeydrop, "exoticFruit");

        Gloopii = (new ItemGooeyFruit(6, false).setUnlocalizedName("tragicmc.gooeyFruit")
            .setMaxStackSize(8)
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:GooeyFruit"));
        GameRegistry.registerItem(Gloopii, "gooeyFruit");

        Deathglow = (new ItemNastyFruit(4, false).setUnlocalizedName("tragicmc.nastyFruit")
            .setMaxStackSize(8)
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:NastyFruit"));
        GameRegistry.registerItem(Deathglow, "nastyFruit");

        Rice = (new ItemRice(2, false).setUnlocalizedName("tragicmc.rice")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:Rice"));
        GameRegistry.registerItem(Rice, "rice");

        Sushi = (new ItemSushi(4, true).setUnlocalizedName("tragicmc.sushi")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:Sushi"));
        GameRegistry.registerItem(Sushi, "sushi");

        GoldenSushi = (new ItemEnchantedSushi(8, true).setUnlocalizedName("tragicmc.goldenSushi")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:GoldenSushi"));
        GameRegistry.registerItem(GoldenSushi, "goldenSushi");

        Banana = (new ItemBanana(1, false).setUnlocalizedName("tragicmc.banana")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:ProjectileBanana"));
        GameRegistry.registerItem(Banana, "banana");

        BananaSplit = (new ItemBananaSplit(8, false).setUnlocalizedName("tragicmc.bananaSplit")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:BananaSplit"));
        GameRegistry.registerItem(BananaSplit, "bananaSplit");

        SkyFruit = (new ItemSkyFruit(4, false).setUnlocalizedName("tragicmc.skyFruit")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:SkyFruit"));
        GameRegistry.registerItem(SkyFruit, "skyFruit");

        Tentacle = (new ItemTentacle(3, true).setUnlocalizedName("tragicmc.tentacle")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:Tentacle"));
        GameRegistry.registerItem(Tentacle, "tentacle");

        HoneydropSeeds = (new ItemFoodSeed(TragicBlocks.Honeydrop).setUnlocalizedName("tragicmc.honeydropSeeds")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:HoneydropSeeds"));
        GameRegistry.registerItem(HoneydropSeeds, "honeydropSeeds");

        DeathglowSeeds = (new ItemFoodSeed(TragicBlocks.Deathglow).setUnlocalizedName("tragicmc.deathglowSeeds")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:DeathglowSeeds"));
        GameRegistry.registerItem(DeathglowSeeds, "deathglowSeeds");

        SkyFruitSeeds = (new ItemFoodSeed(TragicBlocks.SkyFruit).setUnlocalizedName("tragicmc.skyFruitSeeds")
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:SkyFruitSeeds"));
        GameRegistry.registerItem(SkyFruitSeeds, "skyFruitSeeds");

        // Special Item Registrations
        RubyCharm = (new ItemGeneric().setUnlocalizedName("tragicmc.rubyCharm")
            .setMaxStackSize(16)
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:RubyCharm"));
        GameRegistry.registerItem(RubyCharm, "rubyCharm");

        SapphireCharm = (new ItemGeneric().setUnlocalizedName("tragicmc.sapphireCharm")
            .setMaxStackSize(16)
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:SapphireCharm"));
        GameRegistry.registerItem(SapphireCharm, "sapphireCharm");

        DiamondCharm = (new ItemGeneric().setUnlocalizedName("tragicmc.diamondCharm")
            .setMaxStackSize(16)
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:DiamondCharm"));
        GameRegistry.registerItem(DiamondCharm, "diamondCharm");

        EmeraldCharm = (new ItemGeneric().setUnlocalizedName("tragicmc.emeraldCharm")
            .setMaxStackSize(16)
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:EmeraldCharm"));
        GameRegistry.registerItem(EmeraldCharm, "emeraldCharm");

        AwakeningStone = (new ItemGeneric().setUnlocalizedName("tragicmc.awakeningStone")
            .setMaxStackSize(1)
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:AwakeningStone"));
        GameRegistry.registerItem(AwakeningStone, "awakeningStone");

        ObsidianOrb = (new ItemGeneric().setUnlocalizedName("tragicmc.obsidianOrb")
            .setMaxStackSize(16)
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:ObsidianOrb"));
        GameRegistry.registerItem(ObsidianOrb, "obsidianOrb");

        CryingObsidianOrb = (new ItemCryingObsidianOrb().setUnlocalizedName("tragicmc.cryingObsidianOrb")
            .setMaxStackSize(8)
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:CryingObsidianOrb"));
        GameRegistry.registerItem(CryingObsidianOrb, "cryingObsidianOrb");

        BleedingObsidianOrb = (new ItemBleedingObsidianOrb().setUnlocalizedName("tragicmc.bleedingObsidianOrb")
            .setMaxStackSize(8)
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:BleedingObsidianOrb"));
        GameRegistry.registerItem(BleedingObsidianOrb, "bleedingObsidianOrb");

        DyingObsidianOrb = (new ItemDyingObsidianOrb().setUnlocalizedName("tragicmc.dyingObsidianOrb")
            .setMaxStackSize(8)
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:DyingObsidianOrb"));
        GameRegistry.registerItem(DyingObsidianOrb, "dyingObsidianOrb");

        // Weather/Time Talismans
        Talisman = (new ItemGeneric().setUnlocalizedName("tragicmc.talisman")
            .setMaxStackSize(16)
            .setCreativeTab(TragicMC.Survival)
            .setTextureName("tragicmc:Talisman"));
        GameRegistry.registerItem(Talisman, "talisman");

        RainDanceTalisman = (new ItemTalisman() {

            @Override
            public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag) {
                if (world.isRemote || !(entity instanceof EntityPlayer)) return;

                if (!world.isRaining()) {
                    if (stack.getItemDamage() < stack.getMaxDamage() && stack.stackSize > 0) {
                        world.rainingStrength = 1.0F;
                        stack.damageItem(5, (EntityPlayer) entity);
                        if (entity instanceof EntityPlayerMP && TragicConfig.allowAchievements)
                            ((EntityPlayerMP) entity).triggerAchievement(TragicAchievements.talismanSpecial);
                    }
                }
            }
        }.setUnlocalizedName("tragicmc.rainDanceTalisman")
            .setTextureName("tragicmc:RainDanceTalisman"));
        GameRegistry.registerItem(RainDanceTalisman, "rainDanceTalisman");

        SunnyDayTalisman = (new ItemTalisman() {

            @Override
            public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag) {
                if (world.isRemote || !(entity instanceof EntityPlayer)) return;

                if (world.isThundering() && stack.getItemDamage() < stack.getMaxDamage() && stack.stackSize > 0) {
                    stack.damageItem(5, (EntityPlayer) entity);
                    world.thunderingStrength = 0.0F;
                    if (entity instanceof EntityPlayerMP && TragicConfig.allowAchievements)
                        ((EntityPlayerMP) entity).triggerAchievement(TragicAchievements.talismanSpecial);
                }

                if (world.isRaining() && stack.getItemDamage() < stack.getMaxDamage() && stack.stackSize > 0) {
                    stack.damageItem(5, (EntityPlayer) entity);
                    world.rainingStrength = 0.0F;
                    if (entity instanceof EntityPlayerMP && TragicConfig.allowAchievements)
                        ((EntityPlayerMP) entity).triggerAchievement(TragicAchievements.talismanSpecial);
                }
            }
        }).setUnlocalizedName("tragicmc.sunnyDayTalisman")
            .setTextureName("tragicmc:SunnyDayTalisman");
        GameRegistry.registerItem(SunnyDayTalisman, "sunnyDayTalisman");

        ThunderstormTalisman = (new ItemTalisman() {

            @Override
            public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag) {
                if (world.isRemote || !(entity instanceof EntityPlayer)) return;

                if (!world.isThundering() && stack.getItemDamage() < stack.getMaxDamage() && stack.stackSize > 0) {
                    world.thunderingStrength = 1.0F;
                    stack.damageItem(5, (EntityPlayer) entity);
                    if (entity instanceof EntityPlayerMP && TragicConfig.allowAchievements)
                        ((EntityPlayer) entity).triggerAchievement(TragicAchievements.talismanSpecial);
                }
            }
        }).setUnlocalizedName("tragicmc.thunderstormTalisman")
            .setTextureName("tragicmc:ThunderstormTalisman");
        GameRegistry.registerItem(ThunderstormTalisman, "thunderstormTalisman");

        TimeManipulatorTalisman = (new ItemTalisman() {

            @Override
            public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag) {
                if (world.isRemote || !(entity instanceof EntityPlayer) || !TragicConfig.allowItemTimeAltering) return;
                if (stack.getItemDamage() >= stack.getMaxDamage() || stack.stackSize <= 0) return;
                int a = flag ? -5 : 5;
                world.setWorldTime(world.getWorldTime() + a);
                stack.damageItem(1, (EntityPlayer) entity);
                if (entity instanceof EntityPlayerMP && TragicConfig.allowAchievements)
                    ((EntityPlayer) entity).triggerAchievement(TragicAchievements.talismanSpecial);
            }
        }.setUnlocalizedName("tragicmc.timeManipulatorTalisman")
            .setTextureName("tragicmc:TimeManipulatorTalisman"));
        GameRegistry.registerItem(TimeManipulatorTalisman, "timeManipulatorTalisman");

        MoonlightTalisman = (new ItemTalisman() {

            @Override
            public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag) {
                if (world.isRemote || !(entity instanceof EntityPlayer)) return;

                EntityPlayer player = (EntityPlayer) entity;

                if (!world.isDaytime() && !world.isRaining()
                    && !world.isThundering()
                    && stack.getItemDamage() < stack.getMaxDamage()
                    && stack.stackSize > 0) {
                    if (player.ticksExisted % 200 == 0) stack.damageItem(1, player);
                    if (player instanceof EntityPlayerMP && TragicConfig.allowAchievements)
                        player.triggerAchievement(TragicAchievements.talismanSpecial);
                }
            }
        }.setUnlocalizedName("tragicmc.moonlightTalisman")
            .setTextureName("tragicmc:MoonlightTalisman"));
        GameRegistry.registerItem(MoonlightTalisman, "moonlightTalisman");

        SynthesisTalisman = (new ItemTalisman() {

            @Override
            public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag) {
                if (world.isRemote || !(entity instanceof EntityPlayer)) return;

                EntityPlayer player = (EntityPlayer) entity;

                if (world.isDaytime() && !world.isRaining()
                    && !world.isThundering()
                    && stack.getItemDamage() < stack.getMaxDamage()
                    && stack.stackSize > 0) {
                    if (player.ticksExisted % 200 == 0) stack.damageItem(1, player);
                    if (player instanceof EntityPlayerMP && TragicConfig.allowAchievements)
                        player.triggerAchievement(TragicAchievements.talismanSpecial);
                }
            }
        }.setUnlocalizedName("tragicmc.synthesisTalisman")
            .setTextureName("tragicmc:SynthesisTalisman"));
        GameRegistry.registerItem(SynthesisTalisman, "synthesisTalisman");

        HydrationTalisman = (new ItemTalisman() {

            @Override
            public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag) {
                if (world.isRemote || !(entity instanceof EntityPlayer)) return;

                EntityPlayer player = (EntityPlayer) entity;

                if ((world.isRaining() || player.isInsideOfMaterial(Material.water))
                    && stack.getItemDamage() < stack.getMaxDamage()
                    && stack.stackSize > 0) {
                    if (player.ticksExisted % 200 == 0) stack.damageItem(1, player);
                    if (player instanceof EntityPlayerMP && TragicConfig.allowAchievements)
                        player.triggerAchievement(TragicAchievements.talismanSpecial);
                }
            }
        }.setUnlocalizedName("tragicmc.hydrationTalisman")
            .setTextureName("tragicmc:HydrationTalisman"));
        GameRegistry.registerItem(HydrationTalisman, "hydrationTalisman");

        LightningRodTalisman = (new ItemTalisman() {

            @Override
            public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag) {
                if (world.isRemote || !(entity instanceof EntityPlayer)) return;

                EntityPlayer player = (EntityPlayer) entity;

                if (world.isThundering() && stack.getItemDamage() < stack.getMaxDamage() && stack.stackSize > 0) {
                    if (player.ticksExisted % 200 == 0) stack.damageItem(1, player);
                    if (player instanceof EntityPlayerMP && TragicConfig.allowAchievements)
                        player.triggerAchievement(TragicAchievements.talismanSpecial);
                }

                player.inventory.markDirty();
            }
        }.setUnlocalizedName("tragicmc.lightningRodTalisman")
            .setTextureName("tragicmc:LightningRodTalisman"));
        GameRegistry.registerItem(LightningRodTalisman, "lightningRodTalisman");

        // Amulet Registrations
        if (TragicConfig.allowAmulets) {
            KitsuneAmulet = (new AmuletKitsune());
            GameRegistry.registerItem(KitsuneAmulet, "kitsuneAmulet");

            PeaceAmulet = (new AmuletPeace());
            GameRegistry.registerItem(PeaceAmulet, "peaceAmulet");

            YetiAmulet = (new ItemAmulet("Yeti", EnumAmuletType.NORMAL, 0xFAFAFA, 0x98B4C1));
            GameRegistry.registerItem(YetiAmulet, "yetiAmulet");

            ClaymationAmulet = (new AmuletClaymation());
            GameRegistry.registerItem(ClaymationAmulet, "claymationAmulet");

            ChickenAmulet = (new AmuletChicken());
            GameRegistry.registerItem(ChickenAmulet, "chickenAmulet");

            MartyrAmulet = (new ItemAmulet("Martyr", EnumAmuletType.CURSED, ItemAmulet.COLOR_BLACK, 0x454545));
            GameRegistry.registerItem(MartyrAmulet, "martyrAmulet");

            PiercingAmulet = (new ItemAmulet("Piercing", EnumAmuletType.CURSED, 0x237878, 0x449999));
            GameRegistry.registerItem(PiercingAmulet, "piercingAmulet");

            BlacksmithAmulet = (new AmuletBlacksmith());
            GameRegistry.registerItem(BlacksmithAmulet, "blacksmithAmulet");

            ApisAmulet = (new ItemAmulet("Apis", EnumAmuletType.CURSED, 0xFFCD82, 0xFFFF82));
            GameRegistry.registerItem(ApisAmulet, "apisAmulet");

            CreeperAmulet = (new AmuletCreeper());
            GameRegistry.registerItem(CreeperAmulet, "creeperAmulet");

            ZombieAmulet = (new ItemAmulet("Zombie", EnumAmuletType.NORMAL, 0x3A8F4A, 0x27C1C9));
            GameRegistry.registerItem(ZombieAmulet, "zombieAmulet");

            SkeletonAmulet = (new ItemAmulet("Skeleton", EnumAmuletType.NORMAL, 0xA0A0A0, 0xC1C1C1));
            GameRegistry.registerItem(SkeletonAmulet, "skeletonAmulet");

            SunkenAmulet = (new AmuletSunken());
            GameRegistry.registerItem(SunkenAmulet, "sunkenAmulet");

            TimeAmulet = (new AmuletTime());
            GameRegistry.registerItem(TimeAmulet, "timeAmulet");

            IceAmulet = (new ItemAmulet("Ice", EnumAmuletType.NORMAL, 0xC4EFFF, 0xA5D0E0));
            GameRegistry.registerItem(IceAmulet, "iceAmulet");

            SnowGolemAmulet = (new AmuletSnowGolem());
            GameRegistry.registerItem(SnowGolemAmulet, "snowGolemAmulet");

            IronGolemAmulet = (new AmuletIronGolem());
            GameRegistry.registerItem(IronGolemAmulet, "ironGolemAmulet");

            EndermanAmulet = (new ItemAmulet("Enderman", EnumAmuletType.CURSED, ItemAmulet.COLOR_BLACK, 0xB547DE));
            GameRegistry.registerItem(EndermanAmulet, "endermanAmulet");

            WitherAmulet = (new ItemAmulet("Wither", EnumAmuletType.EPIC, ItemAmulet.COLOR_BLACK, 0x245238));
            GameRegistry.registerItem(WitherAmulet, "witherAmulet");

            SpiderAmulet = (new AmuletSpider());
            GameRegistry.registerItem(SpiderAmulet, "spiderAmulet");

            StinAmulet = (new ItemAmulet("Stin", EnumAmuletType.NORMAL, 0x464646, 0x878787));
            GameRegistry.registerItem(StinAmulet, "stinAmulet");

            PolarisAmulet = (new ItemAmulet("Polaris", EnumAmuletType.CURSED, 0x565656, 0x4A00BA));
            GameRegistry.registerItem(PolarisAmulet, "polarisAmulet");

            OverlordAmulet = (new ItemAmulet("Overlord", EnumAmuletType.EPIC, 0x212121, 0x92F9D1) {

                @Override
                public void onAmuletUpdate(final PropertyAmulets amu, final EntityPlayer player, final World world,
                    final byte slot, final byte level) {
                    if (TragicConfig.amuOverlord && TragicConfig.allowHacked) {
                        if (player.isPotionActive(TragicPotion.Hacked))
                            player.removePotionEffect(TragicPotion.Hacked.id);
                    }
                }
            });
            GameRegistry.registerItem(OverlordAmulet, "overlordAmulet");

            LightningAmulet = (new ItemAmulet("Lightning", EnumAmuletType.CURSED, 0xFCFCFC, 0xABABAB));
            GameRegistry.registerItem(LightningAmulet, "lightningAmulet");

            ConsumptionAmulet = (new ItemAmulet("Consumption", EnumAmuletType.CURSED, 0xFF0000, 0xB53838));
            GameRegistry.registerItem(ConsumptionAmulet, "consumptionAmulet");

            SupernaturalAmulet = (new AmuletSupernatural());
            GameRegistry.registerItem(SupernaturalAmulet, "supernaturalAmulet");

            UndeadAmulet = (new AmuletUndead());
            GameRegistry.registerItem(UndeadAmulet, "undeadAmulet");

            EnderDragonAmulet = (new ItemAmulet("EnderDragon", EnumAmuletType.CURSED, 0xCC00FA, 0x1A1A1A));
            GameRegistry.registerItem(EnderDragonAmulet, "enderDragonAmulet");

            FuseaAmulet = (new ItemAmulet("Fusea", EnumAmuletType.NORMAL, 0xA0E39D, 0xE4B1E0));
            GameRegistry.registerItem(FuseaAmulet, "fuseaAmulet");

            EnyvilAmulet = (new AmuletEnyvil());
            GameRegistry.registerItem(EnyvilAmulet, "enyvilAmulet");

            LuckAmulet = (new AmuletLuck());
            GameRegistry.registerItem(LuckAmulet, "luckAmulet");

            AmuletRelease = (new ItemAmuletRelease().setUnlocalizedName("tragicmc.amuletRelease")
                .setMaxStackSize(1)
                .setCreativeTab(TragicMC.Survival)
                .setTextureName("tragicmc:AmuletRelease"));
            GameRegistry.registerItem(AmuletRelease, "amuletRelease");
        }

        // Armor and Tool materials
        armorSkull.customCraftingMaterial = Items.bone;
        armorMercury.customCraftingMaterial = RedMercury;
        armorHunter.customCraftingMaterial = ToughLeather;
        armorLight.customCraftingMaterial = LightParticles;
        armorDark.customCraftingMaterial = DarkParticles;

        toolBasic.setRepairItem(new ItemStack(Items.flint));
        toolScythe.setRepairItem(new ItemStack(Items.bone));
        toolMercury.setRepairItem(new ItemStack(RedMercury));
        toolJack.setRepairItem(new ItemStack(Tungsten));
        toolLight.setRepairItem(new ItemStack(LightParticles));
        toolDarkness.setRepairItem(new ItemStack(DarkParticles));
        toolCelestial.setRepairItem(new ItemStack(CelestialSteel));
        toolCelesJack.setRepairItem(new ItemStack(CelestialSteel));

        // Special item registrations

        if (TragicConfig.allowDoom) {
            DoomConsume = (new ItemDoomUpgrade().setUnlocalizedName("tragicmc.doomConsume")
                .setMaxStackSize(1)
                .setCreativeTab(TragicMC.Survival)
                .setTextureName("tragicmc:DoomConsume"));
            GameRegistry.registerItem(DoomConsume, "doomConsume");

            CooldownDefuse = (new ItemCooldownDefuse().setUnlocalizedName("tragicmc.cooldownDefuse")
                .setMaxStackSize(16)
                .setCreativeTab(TragicMC.Survival)
                .setTextureName("tragicmc:CooldownDefuse"));
            GameRegistry.registerItem(CooldownDefuse, "cooldownDefuse");

            BloodSacrifice = (new ItemBloodSacrifice().setUnlocalizedName("tragicmc.bloodSacrifice")
                .setMaxStackSize(1)
                .setCreativeTab(TragicMC.Survival)
                .setTextureName("tragicmc:BloodSacrifice"));
            GameRegistry.registerItem(BloodSacrifice, "bloodSacrifice");

            NourishmentSacrifice = (new ItemNourishmentSacrifice().setUnlocalizedName("tragicmc.nourishmentSacrifice")
                .setMaxStackSize(1)
                .setCreativeTab(TragicMC.Survival)
                .setTextureName("tragicmc:NourishmentSacrifice"));
            GameRegistry.registerItem(NourishmentSacrifice, "nourishmentSacrifice");
        }

        MobStatue = (new ItemStatue());
        GameRegistry.registerItem(MobStatue, "mobStatue");

        if (TragicConfig.allowDimension) {
            DimensionalKey = (new ItemDimensionalKey(TragicConfig.collisionID)
                .setUnlocalizedName("tragicmc.dimensionalKey.collision")
                .setTextureName("tragicmc:DimensionalKey"));
            GameRegistry.registerItem(DimensionalKey, "dimensionalKey");

            DimensionalKeyEnd = (new ItemDimensionalKey(1).setUnlocalizedName("tragicmc.dimensionalKey.end")
                .setMaxStackSize(1)
                .setCreativeTab(TragicMC.Creative)
                .setTextureName("tragicmc:DimensionalKeyEnd"));
            GameRegistry.registerItem(DimensionalKeyEnd, "dimensionalKeyEnd");

            DimensionalKeyNether = (new ItemDimensionalKey(-1).setUnlocalizedName("tragicmc.dimensionalKey.nether")
                .setMaxStackSize(1)
                .setCreativeTab(TragicMC.Creative)
                .setTextureName("tragicmc:DimensionalKeyNether"));
            GameRegistry.registerItem(DimensionalKeyNether, "dimensionalKeyNether");

            DimensionalKeySynapse = (new ItemDimensionalKey(TragicConfig.synapseID)
                .setUnlocalizedName("tragicmc.dimensionalKey.synapse")
                .setMaxStackSize(1)
                .setCreativeTab(TragicMC.Creative)
                .setTextureName("tragicmc:DimensionalKeySynapse"));
            GameRegistry.registerItem(DimensionalKeySynapse, "dimensionalKeySynapse");

            SynapseLink = (new ItemDimensionalKey(TragicConfig.synapseID).setUnlocalizedName("tragicmc.synapseLink")
                .setMaxStackSize(1)
                .setMaxDamage(5)
                .setCreativeTab(TragicMC.Survival)
                .setTextureName("tragicmc:SynapseLink"));
            GameRegistry.registerItem(SynapseLink, "synapseLink");
        }

        if (TragicConfig.allowDoomsdays) {
            DoomsdayScroll = (new ItemDoomsdayScroll().setTextureName("tragicmc:DoomsdayScroll"));
            GameRegistry.registerItem(DoomsdayScroll, "doomsdayScroll");
        }

        // Creative only items
        BowOfJustice = (new WeaponBowOfJustice().setUnlocalizedName("tragicmc.bowOfJustice")
            .setTextureName("bow"));
        GameRegistry.registerItem(BowOfJustice, "bowOfJustice");

        SwordOfJustice = (new WeaponSwordOfJustice(toolJustice).setUnlocalizedName("tragicmc.swordOfJustice")
            .setTextureName("gold_sword"));
        GameRegistry.registerItem(SwordOfJustice, "swordOfJustice");

        if (TragicConfig.allowGeneratorItems) {
            Generator = (new ItemGenerator());
            GameRegistry.registerItem(Generator, "generator");
        }

        NekoNekoWand = (new ItemNekoWand().setUnlocalizedName("tragicmc.nekoNekoWand")
            .setTextureName("tragicmc:NekoNekoWand"));
        GameRegistry.registerItem(NekoNekoWand, "nekoNekoWand");

        SoundExtrapolator = (new ItemSoundExtrapolator().setUnlocalizedName("tragicmc.soundExtrapolator")
            .setTextureName("tragicmc:SoundExtrapolator"));
        GameRegistry.registerItem(SoundExtrapolator, "soundExtrapolator");

        if (TragicConfig.allowMobs) {
            SpawnEgg = (new ItemMobEgg());
            GameRegistry.registerItem(SpawnEgg, "spawnEgg");
        }

        Projectile = (new ItemProjectile());
        GameRegistry.registerItem(Projectile, "projectile");

        // Chest Gens

        WeightedRandomChestContent[] bossStructureContent = new WeightedRandomChestContent[] {
            new WeightedRandomChestContent(new ItemStack(RubyCharm), 1, 1, 5),
            new WeightedRandomChestContent(new ItemStack(SapphireCharm), 1, 1, 5),
            new WeightedRandomChestContent(new ItemStack(EmeraldCharm), 1, 1, 5),
            new WeightedRandomChestContent(new ItemStack(DiamondCharm), 1, 1, 5),
            new WeightedRandomChestContent(new ItemStack(CooldownDefuse), 1, 3, TragicConfig.cooldownDefuseRarity),
            new WeightedRandomChestContent(new ItemStack(Items.diamond, 1), 1, 2, 20),
            new WeightedRandomChestContent(new ItemStack(Blocks.diamond_block, 1), 1, 1, 5),
            new WeightedRandomChestContent(new ItemStack(Items.gold_ingot, 1), 1, 2, 40),
            new WeightedRandomChestContent(new ItemStack(Blocks.gold_block, 1), 1, 1, 10),
            new WeightedRandomChestContent(new ItemStack(Ruby, 1), 1, 2, 25),
            new WeightedRandomChestContent(new ItemStack(Sapphire, 1), 1, 2, 25),
            new WeightedRandomChestContent(new ItemStack(Items.emerald, 1), 1, 2, 30),
            new WeightedRandomChestContent(new ItemStack(Blocks.emerald_block, 1), 1, 1, 5),
            new WeightedRandomChestContent(new ItemStack(Tungsten, 1), 1, 2, 50),
            new WeightedRandomChestContent(new ItemStack(TragicBlocks.CompactOre, 1, 1), 1, 1, 10),
            new WeightedRandomChestContent(new ItemStack(TragicBlocks.CompactOre, 1, 2), 1, 1, 10),
            new WeightedRandomChestContent(new ItemStack(TragicBlocks.CompactOre, 1, 3), 1, 1, 35),
            new WeightedRandomChestContent(new ItemStack(KitsuneAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(PiercingAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(ObsidianOrb, 1), 1, 2, 3),
            new WeightedRandomChestContent(new ItemStack(AwakeningStone, 1), 1, 1, 1),
            new WeightedRandomChestContent(new ItemStack(HuntersBow, 1), 1, 1, 15),
            new WeightedRandomChestContent(new ItemStack(DarkHelm, 1), 1, 1, 7),
            new WeightedRandomChestContent(new ItemStack(DarkPlate, 1), 1, 1, 7),
            new WeightedRandomChestContent(new ItemStack(DarkLegs, 1), 1, 1, 7),
            new WeightedRandomChestContent(new ItemStack(DarkBoots, 1), 1, 1, 7),
            new WeightedRandomChestContent(new ItemStack(LightHelm, 1), 1, 1, 5),
            new WeightedRandomChestContent(new ItemStack(LightPlate, 1), 1, 1, 5),
            new WeightedRandomChestContent(new ItemStack(LightLegs, 1), 1, 1, 5),
            new WeightedRandomChestContent(new ItemStack(LightBoots, 1), 1, 1, 5),
            new WeightedRandomChestContent(new ItemStack(Items.diamond_helmet, 1), 1, 1, 10),
            new WeightedRandomChestContent(new ItemStack(Items.diamond_chestplate, 1), 1, 1, 10),
            new WeightedRandomChestContent(new ItemStack(Items.diamond_pickaxe, 1), 1, 1, 10),
            new WeightedRandomChestContent(new ItemStack(Items.diamond_leggings, 1), 1, 1, 10),
            new WeightedRandomChestContent(new ItemStack(Items.diamond_boots, 1), 1, 1, 10),
            new WeightedRandomChestContent(new ItemStack(Items.diamond_sword, 1), 1, 1, 10),
            new WeightedRandomChestContent(new ItemStack(Items.golden_apple, 1), 1, 1, 15),
            new WeightedRandomChestContent(new ItemStack(Items.golden_apple, 1, 1), 1, 1, 5),
            new WeightedRandomChestContent(new ItemStack(Honeydrop, 1), 1, 4, 25),
            new WeightedRandomChestContent(new ItemStack(Gloopii, 1), 1, 2, 5),
            new WeightedRandomChestContent(new ItemStack(Items.cooked_beef, 3), 2, 5, 65),
            new WeightedRandomChestContent(new ItemStack(Items.cooked_porkchop, 3), 2, 5, 65),
            new WeightedRandomChestContent(new ItemStack(Items.saddle, 1), 1, 2, 60),
            new WeightedRandomChestContent(new ItemStack(SunkenAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(ApisAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(MartyrAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(BlacksmithAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(CreeperAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(ZombieAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(SkeletonAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(SunkenAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(AmuletRelease, 1), 1, 1, TragicConfig.amuletReleaseRarity),
            new WeightedRandomChestContent(new ItemStack(Items.lead, 1 + TragicMC.rand.nextInt(2)), 1, 1, 5),
            new WeightedRandomChestContent(new ItemStack(Items.saddle), 1, 2, 5),
            new WeightedRandomChestContent(new ItemStack(IceAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(SnowGolemAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(IronGolemAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(EndermanAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(GuiltyThorn, 1), 1, 1, 15),
            new WeightedRandomChestContent(new ItemStack(GravitySpike, 1), 1, 1, 15),
            new WeightedRandomChestContent(new ItemStack(FrozenLightning, 1), 1, 1, 15),
            new WeightedRandomChestContent(new ItemStack(Talisman), 1, 1, 5),
            new WeightedRandomChestContent(new ItemStack(BloodSacrifice), 1, 1, 5),
            new WeightedRandomChestContent(new ItemStack(NourishmentSacrifice), 1, 1, 5),
            new WeightedRandomChestContent(new ItemStack(SpiderAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(StinAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(PolarisAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(LightningAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(ConsumptionAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(
                new ItemStack(SupernaturalAmulet, 1),
                0,
                1,
                TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(UndeadAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(EnderDragonAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(FuseaAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(LuckAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity) };

        WeightedRandomChestContent[] scrollArray = new WeightedRandomChestContent[Doomsday.doomsdayNames.length];

        for (int i = 0; i < Doomsday.doomsdayNames.length; i++) {
            if (Doomsday.doomsdayList[i] != null)
                scrollArray[i] = new WeightedRandomChestContent(new ItemStack(DoomsdayScroll, 1, i), 1, 1, 3);
        }

        ArrayUtils.addAll(bossStructureContent, scrollArray);

        WeightedRandomChestContent[] netherStructureContent = new WeightedRandomChestContent[] {
            new WeightedRandomChestContent(new ItemStack(RubyCharm), 1, 1, 10),
            new WeightedRandomChestContent(new ItemStack(SapphireCharm), 1, 1, 10),
            new WeightedRandomChestContent(new ItemStack(EmeraldCharm), 1, 1, 10),
            new WeightedRandomChestContent(new ItemStack(DiamondCharm), 1, 1, 10),
            new WeightedRandomChestContent(new ItemStack(CooldownDefuse), 1, 3, TragicConfig.cooldownDefuseRarity),
            new WeightedRandomChestContent(new ItemStack(Items.diamond, 1), 1, 2, 25),
            new WeightedRandomChestContent(new ItemStack(Blocks.diamond_block, 1), 1, 1, 10),
            new WeightedRandomChestContent(new ItemStack(Ruby, 1), 1, 2, 18),
            new WeightedRandomChestContent(new ItemStack(Sapphire, 1), 1, 2, 18),
            new WeightedRandomChestContent(new ItemStack(Items.emerald, 1), 1, 2, 20),
            new WeightedRandomChestContent(new ItemStack(Blocks.emerald_block, 1), 1, 1, 8),
            new WeightedRandomChestContent(new ItemStack(Tungsten, 1), 1, 2, 70),
            new WeightedRandomChestContent(new ItemStack(TragicBlocks.CompactOre, 1, 1), 1, 1, 30),
            new WeightedRandomChestContent(new ItemStack(TragicBlocks.CompactOre, 1, 2), 1, 1, 30),
            new WeightedRandomChestContent(new ItemStack(TragicBlocks.CompactOre, 1, 3), 1, 1, 50),
            new WeightedRandomChestContent(new ItemStack(KitsuneAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(PiercingAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(ObsidianOrb, 1), 1, 2, 10),
            new WeightedRandomChestContent(new ItemStack(AwakeningStone, 1), 1, 1, 5),
            new WeightedRandomChestContent(new ItemStack(Items.golden_apple, 1), 1, 1, 35),
            new WeightedRandomChestContent(new ItemStack(Items.golden_apple, 1, 1), 1, 1, 15),
            new WeightedRandomChestContent(new ItemStack(SunkenAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(ApisAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(MartyrAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(GoldenSushi, 1), 1, 2, 5),
            new WeightedRandomChestContent(new ItemStack(BlacksmithAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(CreeperAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(ZombieAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(SkeletonAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(AmuletRelease, 1), 1, 1, TragicConfig.amuletReleaseRarity),
            new WeightedRandomChestContent(new ItemStack(Items.lead, 1 + TragicMC.rand.nextInt(2)), 1, 1, 15),
            new WeightedRandomChestContent(new ItemStack(Items.saddle), 1, 2, 15),
            new WeightedRandomChestContent(new ItemStack(IceAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(SnowGolemAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(IronGolemAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(EndermanAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(Talisman), 1, 1, 10),
            new WeightedRandomChestContent(new ItemStack(BloodSacrifice), 1, 1, 10),
            new WeightedRandomChestContent(new ItemStack(NourishmentSacrifice), 1, 1, 10),
            new WeightedRandomChestContent(new ItemStack(SpiderAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(StinAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(PolarisAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(LightningAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(ConsumptionAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(
                new ItemStack(SupernaturalAmulet, 1),
                0,
                1,
                TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(UndeadAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(EnderDragonAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(FuseaAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity),
            new WeightedRandomChestContent(new ItemStack(LuckAmulet, 1), 1, 1, TragicConfig.amuletOverallRarity) };

        scrollArray = new WeightedRandomChestContent[Doomsday.doomsdayNames.length];

        for (int i = 0; i < Doomsday.doomsdayNames.length; i++) {
            if (Doomsday.doomsdayList[i] != null)
                scrollArray[i] = new WeightedRandomChestContent(new ItemStack(DoomsdayScroll, 1, i), 1, 1, 5);
        }

        ArrayUtils.addAll(netherStructureContent, scrollArray);

        WeightedRandomChestContent[] lameChestContent = new WeightedRandomChestContent[] {
            new WeightedRandomChestContent(new ItemStack(Blocks.cobblestone), 3, 5, 50),
            new WeightedRandomChestContent(new ItemStack(Blocks.gravel), 2, 4, 50),
            new WeightedRandomChestContent(new ItemStack(Items.apple), 1, 3, 25),
            new WeightedRandomChestContent(new ItemStack(Ash), 1, 3, 25),
            new WeightedRandomChestContent(new ItemStack(Items.wheat_seeds), 2, 4, 50),
            new WeightedRandomChestContent(new ItemStack(Items.coal), 1, 3, 25),
            new WeightedRandomChestContent(new ItemStack(Items.rotten_flesh), 2, 5, 100),
            new WeightedRandomChestContent(new ItemStack(Items.string), 2, 4, 75),
            new WeightedRandomChestContent(new ItemStack(Items.bone), 1, 2, 25),
            new WeightedRandomChestContent(new ItemStack(Projectile, 1, 10), 1, 3, 10) };

        WeightedRandomChestContent[] awesomeChestContent = new WeightedRandomChestContent[] {
            new WeightedRandomChestContent(
                new ItemStack(TragicItems.DoomConsume),
                0,
                1,
                TragicConfig.doomConsumeRarity),
            new WeightedRandomChestContent(
                new ItemStack(TragicItems.CooldownDefuse),
                1,
                3,
                TragicConfig.cooldownDefuseRarity),
            new WeightedRandomChestContent(
                new ItemStack(TragicItems.AmuletRelease, 1),
                0,
                1,
                TragicConfig.amuletReleaseRarity),
            new WeightedRandomChestContent(new ItemStack(AwakeningStone, 1), 1, 2, 35),
            new WeightedRandomChestContent(new ItemStack(GoldenSushi, 1), 1, 3, 45),
            new WeightedRandomChestContent(new ItemStack(Items.diamond, 1 + TragicMC.rand.nextInt(3)), 1, 3, 50),
            new WeightedRandomChestContent(new ItemStack(Items.emerald, 1 + TragicMC.rand.nextInt(3)), 1, 3, 50),
            new WeightedRandomChestContent(new ItemStack(Ruby, 1 + TragicMC.rand.nextInt(3)), 1, 3, 50),
            new WeightedRandomChestContent(new ItemStack(Sapphire, 1 + TragicMC.rand.nextInt(3)), 1, 3, 50),
            new WeightedRandomChestContent(new ItemStack(Items.golden_apple, 1, 1), 1, 1, 35),
            new WeightedRandomChestContent(new ItemStack(AwakeningStone, 1), 1, 1, 45),
            new WeightedRandomChestContent(new ItemStack(Items.saddle), 1, 2, 25),
            new WeightedRandomChestContent(new ItemStack(RubyCharm), 1, 1, 15),
            new WeightedRandomChestContent(new ItemStack(SapphireCharm), 1, 1, 15),
            new WeightedRandomChestContent(new ItemStack(EmeraldCharm), 1, 1, 15),
            new WeightedRandomChestContent(new ItemStack(DiamondCharm), 1, 1, 15),
            new WeightedRandomChestContent(new ItemStack(Titan), 1, 1, 10),
            new WeightedRandomChestContent(new ItemStack(Splinter), 1, 1, 10),
            new WeightedRandomChestContent(new ItemStack(Thardus), 1, 1, 10),
            new WeightedRandomChestContent(new ItemStack(Paranoia), 1, 1, 10),
            new WeightedRandomChestContent(new ItemStack(Butcher), 1, 1, 10),
            new WeightedRandomChestContent(new ItemStack(DragonFang), 1, 1, 10),
            new WeightedRandomChestContent(new ItemStack(Talisman), 1, 1, 20),
            new WeightedRandomChestContent(new ItemStack(BloodSacrifice), 1, 1, 20),
            new WeightedRandomChestContent(new ItemStack(NourishmentSacrifice), 1, 1, 20) };

        scrollArray = new WeightedRandomChestContent[Doomsday.doomsdayNames.length];

        for (int i = 0; i < Doomsday.doomsdayNames.length; i++) {
            if (Doomsday.doomsdayList[i] != null)
                scrollArray[i] = new WeightedRandomChestContent(new ItemStack(DoomsdayScroll, 1, i), 1, 1, 10);
        }

        ArrayUtils.addAll(awesomeChestContent, scrollArray);

        BossStructureHook = (new ChestGenHooks("TragicMC.BossStructure", bossStructureContent, 3, 7));
        NetherStructureHook = (new ChestGenHooks("TragicMC.NetherBossStructure", netherStructureContent, 5, 8));
        LameChestHook = (new ChestGenHooks("TragicMC.LameChest", lameChestContent, 3, 6));
        AwesomeChestHook = (new ChestGenHooks("TragicMC.AwesomeChest", awesomeChestContent, 6, 10));

        if (TragicConfig.allowCooldownDefuse) {
            ChestGenHooks.addItem(
                ChestGenHooks.BONUS_CHEST,
                new WeightedRandomChestContent(
                    new ItemStack(CooldownDefuse, 1),
                    1,
                    3,
                    TragicConfig.cooldownDefuseRarity));
            ChestGenHooks.addItem(
                ChestGenHooks.DUNGEON_CHEST,
                new WeightedRandomChestContent(
                    new ItemStack(CooldownDefuse, 1),
                    1,
                    3,
                    TragicConfig.cooldownDefuseRarity));
            ChestGenHooks.addItem(
                ChestGenHooks.MINESHAFT_CORRIDOR,
                new WeightedRandomChestContent(
                    new ItemStack(CooldownDefuse, 1),
                    1,
                    3,
                    TragicConfig.cooldownDefuseRarity));
            ChestGenHooks.addItem(
                ChestGenHooks.PYRAMID_DESERT_CHEST,
                new WeightedRandomChestContent(
                    new ItemStack(CooldownDefuse, 1),
                    1,
                    3,
                    TragicConfig.cooldownDefuseRarity));
            ChestGenHooks.addItem(
                ChestGenHooks.PYRAMID_JUNGLE_CHEST,
                new WeightedRandomChestContent(
                    new ItemStack(CooldownDefuse, 1),
                    1,
                    3,
                    TragicConfig.cooldownDefuseRarity));
            ChestGenHooks.addItem(
                ChestGenHooks.STRONGHOLD_LIBRARY,
                new WeightedRandomChestContent(
                    new ItemStack(CooldownDefuse, 1),
                    1,
                    3,
                    TragicConfig.cooldownDefuseRarity));
            ChestGenHooks.addItem(
                ChestGenHooks.VILLAGE_BLACKSMITH,
                new WeightedRandomChestContent(
                    new ItemStack(CooldownDefuse, 1),
                    1,
                    3,
                    TragicConfig.cooldownDefuseRarity));
        }

        OreDictionary.registerOre("gemRuby", Ruby);
        OreDictionary.registerOre("gemSapphire", Sapphire);
        OreDictionary.registerOre("cropRice", Rice);
        OreDictionary.registerOre("foodSushi", Sushi);
        OreDictionary.registerOre("foodIceCream", IceCream);
        OreDictionary.registerOre("foodBanana", Banana);
        OreDictionary.registerOre("foodBananaSplit", BananaSplit);
        OreDictionary.registerOre("ingotTungsten", Tungsten);
        OreDictionary.registerOre("ingotMercury", QuicksilverIngot);

        // Vanilla entries for my mod
        for (int i = 0; i < 5; i++) OreDictionary.registerOre("fish", new ItemStack(Items.fish, 1, i));
        OreDictionary.registerOre("fish", Tentacle);

        OreDictionary.registerOre("oreCharms", RubyCharm);
        OreDictionary.registerOre("oreCharms", SapphireCharm);
        OreDictionary.registerOre("oreCharms", DiamondCharm);
        OreDictionary.registerOre("oreCharms", EmeraldCharm);

        OreDictionary.registerOre("celestialSteelDrops", TimeEssence);
        OreDictionary.registerOre("celestialSteelDrops", LivingClay);
        OreDictionary.registerOre("celestialSteelDrops", PureLight);
        OreDictionary.registerOre("celestialSteelDrops", StarPieces);
        OreDictionary.registerOre("celestialSteelDrops", PureDarkness);
        OreDictionary.registerOre("celestialSteelDrops", EmpariahClaw);
        OreDictionary.registerOre("celestialSteelDrops", KitsuneTail);
        OreDictionary.registerOre("celestialSteelDrops", DeathlyHallow);
    }

    public static void initializeChallengeItem() {
        // Challenge item registration
        ChallengeScroll = (new ItemChallenge().setTextureName("tragicmc:ChallengeInProgress"));
        GameRegistry.registerItem(ChallengeScroll, "challengeScroll");

        BossStructureHook.addItem(new WeightedRandomChestContent(new ItemStack(ChallengeScroll), 1, 1, 5));
        NetherStructureHook.addItem(new WeightedRandomChestContent(new ItemStack(ChallengeScroll), 1, 1, 15));
        AwesomeChestHook.addItem(new WeightedRandomChestContent(new ItemStack(ChallengeScroll), 1, 1, 25));
    }

}
