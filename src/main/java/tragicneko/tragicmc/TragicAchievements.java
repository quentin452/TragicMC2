package tragicneko.tragicmc;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class TragicAchievements {

    private static final int offsetX = 0; // offsets to fix achievement page glitch if you go too far up or at least not
                                          // make it as likely to occur
    private static final int offsetZ = 8;

    // main achievement line
    public static Achievement thanks = new Achievement(
        "tragicmc.achievement.thanks",
        "tragicmc.thanks",
        offsetX,
        offsetZ,
        TragicItems.NekoNekoWand,
        (Achievement) null).initIndependentStat()
            .setSpecial()
            .registerStat();
    public static Achievement mercury = new Achievement(
        "tragicmc.achievement.mercury",
        "tragicmc.mercury",
        2 + offsetX,
        offsetZ,
        TragicItems.RedMercury,
        thanks).registerStat();
    public static Achievement obsidianOrb = new Achievement(
        "tragicmc.achievement.obsidianOrb",
        "tragicmc.obsidianOrb",
        2 + offsetX,
        -2 + offsetZ,
        TragicItems.BleedingObsidianOrb,
        mercury).registerStat();
    public static Achievement dimensionalKey = new Achievement(
        "tragicmc.achievement.dimensionalKey",
        "tragicmc.dimensionalKey",
        2 + offsetX,
        -4 + offsetZ,
        TragicItems.DimensionalKey,
        obsidianOrb).setSpecial()
            .registerStat();
    public static Achievement collision = new Achievement(
        "tragicmc.achievement.collision",
        "tragicmc.collision",
        2 + offsetX,
        -6 + offsetZ,
        TragicItems.LightParticles,
        dimensionalKey).registerStat();
    public static Achievement aeris = new Achievement(
        "tragicmc.achievement.aeris",
        "tragicmc.aeris",
        2 + offsetX,
        -8 + offsetZ,
        TragicBlocks.Aeris,
        collision).registerStat();
    public static Achievement synapseLink = new Achievement(
        "tragicmc.achievement.synapseLink",
        "tragicmc.synapseLink",
        2 + offsetX,
        -10 + offsetZ,
        TragicItems.SynapseLink,
        aeris).setSpecial()
            .registerStat();
    public static Achievement synapse = new Achievement(
        "tragicmc.achievement.synapse",
        "tragicmc.synapse",
        2 + offsetX,
        -12 + offsetZ,
        TragicBlocks.SynapseCore,
        synapseLink).registerStat();
    public static Achievement overlord = new Achievement(
        "tragicmc.achievement.overlord",
        "tragicmc.overlord",
        2 + offsetX,
        -14 + offsetZ,
        TragicItems.NanoBots,
        synapse).registerStat();
    public static Achievement overlord2 = new Achievement(
        "tragicmc.achievement.overlord2",
        "tragicmc.overlord2",
        4 + offsetX,
        -14 + offsetZ,
        TragicItems.SynapseCrystal,
        overlord).registerStat();
    public static Achievement overlord3 = new Achievement(
        "tragicmc.achievement.overlord3",
        "tragicmc.overlord3",
        6 + offsetX,
        -14 + offsetZ,
        TragicItems.CorruptedEye,
        overlord2).registerStat();
    public static Achievement overlord4 = new Achievement(
        "tragicmc.achievement.overlord4",
        "tragicmc.overlord4",
        8 + offsetX,
        -14 + offsetZ,
        TragicItems.Sentinel,
        overlord3).registerStat()
            .setSpecial();

    // first expansion achievements
    // public static Achievement wildsRelic = new Achievement("tragicmc.achievement.wildsRelic", "tragicmc.wildsRelic",
    // 0, 0, Items.apple, (Achievement) null);
    // public static Achievement wilds = new Achievement("tragicmc.achievement.wilds", "tragicmc.wilds", 0, 0,
    // Items.apple, (Achievement) null);
    // public static Achievement spiritCatcher = new Achievement("tragicmc.achievement.spiritCatcher",
    // "tragicmc.spiritCatcher", 0, 0, Items.apple, (Achievement) null);
    // public static Achievement nerveCenter = new Achievement("tragicmc.achievement.nerveCenter",
    // "tragicmc.nerveCenter", 0, 0, Items.apple, (Achievement) null);
    // public static Achievement admin = new Achievement("tragicmc.achievement.admin", "tragicmc.admin", 0, 0,
    // Items.apple, (Achievement) null);
    // public static Achievement admin2 = new Achievement("tragicmc.achievement.admin2", "tragicmc.admin2", 0, 0,
    // Items.apple, (Achievement) null);
    // public static Achievement admin3 = new Achievement("tragicmc.achievement.admin3", "tragicmc.admin3", 0, 0,
    // Items.apple, (Achievement) null);
    // public static Achievement admin4 = new Achievement("tragicmc.achievement.admin4", "tragicmc.admin4", 0, 0,
    // Items.apple, (Achievement) null);

    // amulet achievements
    public static Achievement tungsten = new Achievement(
        "tragicmc.achievement.tungsten",
        "tragicmc.tungsten",
        4 + offsetX,
        offsetZ,
        TragicItems.Tungsten,
        mercury).registerStat();
    public static Achievement amulet = new Achievement(
        "tragicmc.achievement.amulet",
        "tragicmc.amulet",
        6 + offsetX,
        offsetZ,
        TragicItems.BlacksmithAmulet,
        tungsten).registerStat();
    public static Achievement amuletEquip = new Achievement(
        "tragicmc.achievement.amuletEquip",
        "tragicmc.amuletEquip",
        6 + offsetX,
        -2 + offsetZ,
        TragicItems.CreeperAmulet,
        amulet).registerStat();
    public static Achievement amuletRelease = new Achievement(
        "tragicmc.achievement.amuletRelease",
        "tragicmc.amuletRelease",
        8 + offsetX,
        offsetZ,
        TragicItems.AmuletRelease,
        amulet).registerStat();
    public static Achievement amuletMax = new Achievement(
        "tragicmc.achievement.amuletMax",
        "tragicmc.amuletMax",
        10 + offsetX,
        offsetZ,
        TragicItems.ApisAmulet,
        amuletRelease).registerStat()
            .setSpecial();
    public static Achievement amuletSpecial = new Achievement(
        "tragicmc.achievement.amuletSpecial",
        "tragicmc.amuletSpecial",
        6 + offsetX,
        -4 + offsetZ,
        TragicItems.KitsuneAmulet,
        amuletEquip).registerStat();

    // doom and doomsday achievements
    public static Achievement doom = new Achievement(
        "tragicmc.achievement.doom",
        "tragicmc.doom",
        offsetX,
        -2 + offsetZ,
        TragicItems.GuiltyThorn,
        thanks).registerStat();
    public static Achievement doomsday = new Achievement(
        "tragicmc.achievement.doomsday",
        "tragicmc.doomsday",
        -2 + offsetX,
        -2 + offsetZ,
        TragicItems.Titan,
        doom).registerStat();
    public static Achievement doomsdayCombo = new Achievement(
        "tragicmc.achievement.doomsdayCombo",
        "tragicmc.doomsdayCombo",
        -4 + offsetX,
        -2 + offsetZ,
        TragicItems.CelestialAegis,
        doomsday).registerStat()
            .setSpecial();
    public static Achievement doomCritical = new Achievement(
        "tragicmc.achievement.doomCritical",
        "tragicmc.doomCritical",
        offsetX,
        -4 + offsetZ,
        TragicItems.BeastlyClaws,
        doom).registerStat();
    public static Achievement doomCooldown = new Achievement(
        "tragicmc.achievement.doomCooldown",
        "tragicmc.doomCooldown",
        offsetX,
        -6 + offsetZ,
        TragicItems.CooldownDefuse,
        doomCritical).registerStat();
    public static Achievement doomConsume = new Achievement(
        "tragicmc.achievement.doomConsume",
        "tragicmc.doomConsume",
        -2 + offsetX,
        -4 + offsetZ,
        TragicItems.DoomConsume,
        doomCritical).registerStat();

    // weapon/killing achievements
    public static Achievement weapon = new Achievement(
        "tragicmc.achievement.weapon",
        "tragicmc.weapon",
        2 + offsetX,
        2 + offsetZ,
        TragicItems.MercuryDagger,
        mercury).registerStat();
    public static Achievement kill = new Achievement(
        "tragicmc.achievement.kill",
        "tragicmc.kill",
        2 + offsetX,
        4 + offsetZ,
        TragicItems.Thorns,
        weapon).registerStat();
    public static Achievement killMiniBoss = new Achievement(
        "tragicmc.achievement.killMiniBoss",
        "tragicmc.killMiniBoss",
        4 + offsetX,
        4 + offsetZ,
        TragicItems.FrozenLightning,
        kill).registerStat();
    public static Achievement killBoss = new Achievement(
        "tragicmc.achievement.killBoss",
        "tragicmc.killBoss",
        4 + offsetX,
        6 + offsetZ,
        TragicItems.Thardus,
        killMiniBoss).registerStat();
    // public static Achievement upgradeWeapon; //Upgrade an eligible weapon to a new tier
    // public static Achievement killAscendent; //Kill a mob that has defense against ascendent weaponry

    // armor and basic enchantments achievements
    public static Achievement enchant = new Achievement(
        "tragicmc.achievement.enchant",
        "tragicmc.enchant",
        offsetX,
        4 + offsetZ,
        TragicItems.Splinter,
        kill).registerStat();
    public static Achievement reach = new Achievement(
        "tragicmc.achivement.reach",
        "tragicmc.reach",
        -2 + offsetX,
        4 + offsetZ,
        TragicItems.Butcher,
        enchant).registerStat();
    public static Achievement enchantArmor = new Achievement(
        "tragicmc.achievement.enchantArmor",
        "tragicmc.enchantArmor",
        2 + offsetX,
        6 + offsetZ,
        TragicItems.DarkHelm,
        kill).registerStat();
    public static Achievement fullSuit = new Achievement(
        "tragicmc.achievement.fullSuit",
        "tragicmc.fullSuit",
        offsetX,
        6 + offsetZ,
        TragicItems.MercuryPlate,
        enchantArmor).registerStat();
    public static Achievement haxEngage = new Achievement(
        "tragicmc.achievement.haxEngage",
        "tragicmc.haxEngage",
        -2 + offsetX,
        6 + offsetZ,
        TragicItems.OverlordHelm,
        fullSuit).registerStat()
            .setSpecial();

    // jack and more enchantment achievements
    public static Achievement jack = new Achievement(
        "tragicmc.achievement.jack",
        "tragicmc.jack",
        offsetX,
        2 + offsetZ,
        TragicItems.Jack,
        weapon).registerStat();
    public static Achievement combustion = new Achievement(
        "tragicmc.achievement.combustion",
        "tragicmc.combustion",
        -2 + offsetX,
        2 + offsetZ,
        TragicItems.TungstenJack,
        jack).registerStat();
    public static Achievement veteran = new Achievement(
        "tragicmc.achievement.veteran",
        "tragicmc.veteran",
        -4 + offsetX,
        2 + offsetZ,
        Items.diamond_pickaxe,
        combustion).registerStat();
    public static Achievement luminescence = new Achievement(
        "tragicmc.achievement.luminescence",
        "tragicmc.luminescence",
        -6 + offsetX,
        2 + offsetZ,
        TragicItems.CelestialJack,
        veteran).registerStat();

    // talisman achievements
    public static Achievement talisman = new Achievement(
        "tragicmc.achievement.talisman",
        "tragicmc.talisman",
        -2 + offsetX,
        offsetZ,
        TragicItems.Talisman,
        thanks).registerStat();
    public static Achievement talismanSpecial = new Achievement(
        "tragicmc.achievement.talismanSpecial",
        "tragicmc.talismanSpecial",
        -4 + offsetX,
        offsetZ,
        TragicItems.MoonlightTalisman,
        talisman).registerStat();
    public static Achievement talismanFull = new Achievement(
        "tragicmc.achievement.talismanFull",
        "tragicmc.talismanFull",
        -6 + offsetX,
        offsetZ,
        TragicItems.SynthesisTalisman,
        talismanSpecial).registerStat()
            .setSpecial();

    // random achievements
    public static Achievement goldenSushi = new Achievement(
        "tragicmc.achievement.goldenSushi",
        "tragicmc.goldenSushi",
        -10 + offsetX,
        -14 + offsetZ,
        TragicItems.GoldenSushi,
        null).initIndependentStat()
            .registerStat();
    public static Achievement flight = new Achievement(
        "tragicmc.achievement.flight",
        "tragicmc.flight",
        -8 + offsetX,
        -2 + offsetZ,
        TragicItems.WingsOfLiberation,
        null).initIndependentStat()
            .registerStat();
    public static Achievement disorientation = new Achievement(
        "tragicmc.achievement.disorientation",
        "tragicmc.disorientation",
        -6 + offsetX,
        -14 + offsetZ,
        TragicItems.Ash,
        null).initIndependentStat()
            .registerStat();
    public static Achievement mobStatue = new Achievement(
        "tragicmc.achievement.mobStatue",
        "tragicmc.mobStatue",
        -10 + offsetX,
        -2 + offsetZ,
        TragicItems.MobStatue,
        null).initIndependentStat()
            .registerStat();
    public static Achievement challengeScroll = new Achievement(
        "tragicmc.achievement.challengeScroll",
        "tragicmc.challengeScroll",
        -8 + offsetX,
        -14 + offsetZ,
        TragicItems.ChallengeScroll,
        null).initIndependentStat()
            .registerStat();
    public static Achievement doomsdayScroll = new Achievement(
        "tragicmc.achievement.doomsdayScroll",
        "tragicmc.doomsdayScroll",
        -8 + offsetX,
        -12 + offsetZ,
        TragicItems.DoomsdayScroll,
        null).initIndependentStat()
            .registerStat();
    public static Achievement mineXP = new Achievement(
        "tragicmc.achievement.mineXP",
        "tragicmc.mineXP",
        -8 + offsetX,
        -10 + offsetZ,
        Items.experience_bottle,
        null).initIndependentStat()
            .registerStat();
    public static Achievement loot = new Achievement(
        "tragicmc.achievement.loot",
        "tragicmc.loot",
        -8 + offsetX,
        -8 + offsetZ,
        TragicItems.TungstenBoots,
        null).initIndependentStat()
            .registerStat();
    public static Achievement loot2 = new Achievement(
        "tragicmc.achievement.loot2",
        "tragicmc.loot2",
        -8 + offsetX,
        -6 + offsetZ,
        TragicItems.LightHelm,
        loot).registerStat();
    public static Achievement systemCrash = new Achievement(
        "tragicmc.achievement.systemCrash",
        "tragicmc.systemCrash",
        -6 + offsetX,
        -12 + offsetZ,
        TragicItems.NanoBots,
        null).initIndependentStat()
            .registerStat();
    public static Achievement divinity = new Achievement(
        "tragicmc.achievement.divinity",
        "tragicmc.divinity",
        -8 + offsetX,
        -4 + offsetZ,
        TragicItems.ArchangelFeather,
        null).initIndependentStat()
            .registerStat();
    public static Achievement useOrb = new Achievement(
        "tragicmc.achievement.useOrb",
        "tragicmc.useOrb",
        -10 + offsetX,
        -4 + offsetZ,
        TragicItems.CryingObsidianOrb,
        null).initIndependentStat()
            .registerStat();

    // mob achievements
    public static Achievement tragicNeko = new Achievement(
        "tragicmc.achievement.tragicNeko",
        "tragicmc.tragicNeko",
        -14 + offsetX,
        -14 + offsetZ,
        Blocks.tnt,
        null).initIndependentStat()
            .registerStat()
            .setSpecial();
    public static Achievement pumpkinhead = new Achievement(
        "tragicmc.achievement.pumpkinhead",
        "tragicmc.pumpkinhead",
        -14 + offsetX,
        -12 + offsetZ,
        Blocks.pumpkin,
        null).initIndependentStat()
            .registerStat();
    public static Achievement norVox = new Achievement(
        "tragicmc.achievement.norVox",
        "tragicmc.norVox",
        -14 + offsetX,
        -10 + offsetZ,
        TragicBlocks.DarkStone,
        null).initIndependentStat()
            .registerStat();
    public static Achievement psygote = new Achievement(
        "tragicmc.achievement.psygote",
        "tragicmc.psygote",
        -14 + offsetX,
        -8 + offsetZ,
        TragicItems.DarkParticles,
        null).initIndependentStat()
            .registerStat()
            .setSpecial();
    public static Achievement sirv = new Achievement(
        "tragicmc.achievement.sirv",
        "tragicmc.sirv",
        -14 + offsetX,
        -6 + offsetZ,
        Items.clay_ball,
        null).initIndependentStat()
            .registerStat();
    public static Achievement ragr = new Achievement(
        "tragicmc.achievement.ragr",
        "tragicmc.ragr",
        -14 + offsetX,
        -4 + offsetZ,
        Blocks.fire,
        null).initIndependentStat()
            .registerStat();
    public static Achievement kurayami = new Achievement(
        "tragicmc.achievement.kurayami",
        "tragicmc.kurayami",
        -14 + offsetX,
        -2 + offsetZ,
        TragicItems.KitsuneAmulet,
        null).initIndependentStat()
            .registerStat()
            .setSpecial();
    public static Achievement cantTouchThis = new Achievement(
        "tragicmc.achievement.cantTouchThis",
        "tragicmc.cantTouchThis",
        -10 + offsetX,
        -12 + offsetZ,
        TragicItems.AwakeningStone,
        null).initIndependentStat()
            .registerStat()
            .setSpecial();
    public static Achievement rewind = new Achievement(
        "tragicmc.achievement.rewind",
        "tragicmc.rewind",
        -10 + offsetX,
        -10 + offsetZ,
        TragicItems.LunarPowder,
        null).initIndependentStat()
            .registerStat();
    public static Achievement aegar = new Achievement(
        "tragicmc.achievement.aegar",
        "tragicmc.aegar",
        -12 + offsetX,
        -4 + offsetZ,
        TragicItems.SynapseCrystal,
        null).initIndependentStat()
            .registerStat();
    public static Achievement claymation2in1 = new Achievement(
        "tragicmc.achievement.claymation2in1",
        "tragicmc.claymation2in1",
        -10 + offsetX,
        -8 + offsetZ,
        TragicItems.ClaymationAmulet,
        null).initIndependentStat()
            .registerStat()
            .setSpecial();
    public static Achievement skultarImmune = new Achievement(
        "tragicmc.achievement.skultarImmune",
        "tragicmc.skultarImmune",
        -10 + offsetX,
        -6 + offsetZ,
        TragicItems.SkullHelmet,
        null).initIndependentStat()
            .registerStat();
    public static Achievement minotaurSummon = new Achievement(
        "tragicmc.achievement.minotaurSummon",
        "tragicmc.minotaurSummon",
        -12 + offsetX,
        -2 + offsetZ,
        TragicItems.Horn,
        null).initIndependentStat()
            .registerStat()
            .setSpecial();
    public static Achievement seeker = new Achievement(
        "tragicmc.achievement.seeker",
        "tragicmc.seeker",
        -12 + offsetX,
        -14 + offsetZ,
        TragicItems.BlindingLight,
        null).initIndependentStat()
            .registerStat();
    public static Achievement harvester = new Achievement(
        "tragicmc.achievement.harvester",
        "tragicmc.harvester",
        -12 + offsetX,
        -12 + offsetZ,
        Blocks.iron_block,
        null).initIndependentStat()
            .registerStat();
    public static Achievement lockdown = new Achievement(
        "tragicmc.achievement.lockbot",
        "tragicmc.lockbot",
        -12 + offsetX,
        -10 + offsetZ,
        Blocks.anvil,
        null).initIndependentStat()
            .registerStat();
    public static Achievement fusea = new Achievement(
        "tragicmc.achievement.fusea",
        "tragicmc.fusea",
        -12 + offsetX,
        -8 + offsetZ,
        TragicItems.UnstableIsotope,
        null).initIndependentStat()
            .registerStat();
    public static Achievement ire = new Achievement(
        "tragicmc.achievement.ire",
        "tragicmc.ire",
        -12 + offsetX,
        -6 + offsetZ,
        TragicItems.IreNetParticleCannon,
        null).initIndependentStat()
            .registerStat();
    public static Achievement avris = new Achievement(
        "tragicmc.achievement.avris",
        "tragicmc.avris",
        -6 + offsetX,
        -10 + offsetZ,
        TragicItems.Ruby,
        null);
    // public static Achievement slangLeader; //Kill a Slang Leader without it taking your item, "Beat the devil out of
    // it!"
    // public static Achievement archangel; //Kill an Archangel while it is using it's holy beam on you, "Angel beats"
    // public static Achievement blist; //Kill a Blist without it suiciding, "Step away from that ledge, my friend"
    // public static Achievement thorg; //Kill a Thorg without being hurt by it's projectiles, "Thorg-a-sborg!"
    // public static Achievement trapper; //Kill a Trapper without it grabbing you, "Trapper no trapping!"
    // public static Achievement berserker; //Kill a Berserker after it fully transforms, "Let's go berserk!"
    // public static Achievement phoenix; //Kill all 3 phoenixes within a few seconds of each other, "3 birds, one very
    // big stone"
    // public static Achievement lonelyShina; //Kill every wolf that Shina spawns and kill it while there are none
    // around it, "Lone Wolf"
    // public static Achievement petrivNightmares; //Don't fail a single nightmare phase when you kill Petriv
    // public static Achievement cirelaUntouchable; //Kill C1R-314 without being hit by it

    // boss achievements
    public static Achievement apis = new Achievement(
        "tragicmc.achievement.apis",
        "tragicmc.apis",
        -12 + offsetX,
        2 + offsetZ,
        TragicItems.PureLight,
        null).initIndependentStat()
            .registerStat();
    public static Achievement skultar = new Achievement(
        "tragicmc.achievement.skultar",
        "tragicmc.skultar",
        -12 + offsetX,
        4 + offsetZ,
        TragicItems.DeathlyHallow,
        null).initIndependentStat()
            .registerStat();
    public static Achievement kitsunakuma = new Achievement(
        "tragicmc.achievement.kitsunakuma",
        "tragicmc.kitsunakuma",
        -12 + offsetX,
        6 + offsetZ,
        TragicItems.KitsuneTail,
        null).initIndependentStat()
            .registerStat();
    public static Achievement polaris = new Achievement(
        "tragicmc.achievement.polaris",
        "tragicmc.polaris",
        -12 + offsetX,
        8 + offsetZ,
        TragicItems.StarPieces,
        null).initIndependentStat()
            .registerStat();
    public static Achievement empariah = new Achievement(
        "tragicmc.achievement.empariah",
        "tragicmc.empariah",
        -10 + offsetX,
        2 + offsetZ,
        TragicItems.EmpariahClaw,
        null).initIndependentStat()
            .registerStat();
    public static Achievement timeController = new Achievement(
        "tragicmc.achievement.timeController",
        "tragicmc.timeController",
        -10 + offsetX,
        4 + offsetZ,
        TragicItems.TimeEssence,
        null).initIndependentStat()
            .registerStat();
    public static Achievement enyvil = new Achievement(
        "tragicmc.achievement.enyvil",
        "tragicmc.enyvil",
        -10 + offsetX,
        6 + offsetZ,
        TragicItems.PureDarkness,
        null).initIndependentStat()
            .registerStat();
    public static Achievement claymation = new Achievement(
        "tragicmc.achievement.claymation",
        "tragicmc.claymation",
        -10 + offsetX,
        8 + offsetZ,
        TragicItems.LivingClay,
        null).initIndependentStat()
            .registerStat();
    // public static Achievement shinaShewolf; //"Might've Shina coming!"
    // public static Achievement axyr; //"Axyr if she wants to dance."
    // public static Achievement petriv; //"Nightmares and a cold sweat"
    // public static Achievement amsheer; //"I Amsheer about it."
    // public static Achievement akhora; //"Night of the living dragon"
    // public static Achievement leviathan; //"Deep, blue sea"
    // public static Achievement polypus; //"Pulp fiction"
    // public static Achievement cirela; //"Round and round it goes"

    public static void load() {
        AchievementPage page = new AchievementPage(
            TragicMC.MODID,
            thanks,
            mercury,
            obsidianOrb,
            dimensionalKey,
            collision,
            aeris,
            synapseLink,
            synapse,
            overlord,
            overlord2,
            overlord3,
            overlord4,
            tungsten,
            amulet,
            amuletEquip,
            amuletRelease,
            amuletMax,
            amuletSpecial,
            doom,
            doomsday,
            doomsdayCombo,
            doomCritical,
            doomCooldown,
            doomConsume,
            weapon,
            kill,
            killMiniBoss,
            killBoss,
            enchant,
            reach,
            enchantArmor,
            fullSuit,
            haxEngage,
            jack,
            combustion,
            veteran,
            luminescence,
            talisman,
            talismanSpecial,
            talismanFull,
            goldenSushi,
            flight,
            disorientation,
            mobStatue,
            challengeScroll,
            doomsdayScroll,
            mineXP,
            loot,
            loot2,
            systemCrash,
            divinity,
            useOrb,
            tragicNeko,
            pumpkinhead,
            norVox,
            psygote,
            sirv,
            ragr,
            kurayami,
            cantTouchThis,
            rewind,
            aegar,
            claymation2in1,
            skultarImmune,
            minotaurSummon,
            seeker,
            harvester,
            lockdown,
            fusea,
            ire,
            avris,
            apis,
            skultar,
            kitsunakuma,
            polaris,
            empariah,
            timeController,
            enyvil,
            claymation);
        AchievementPage.registerAchievementPage(page);
    }
}
