package tragicneko.tragicmc;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import cpw.mods.fml.common.registry.GameRegistry;
import tragicneko.tragicmc.items.RecipeAmulets;
import tragicneko.tragicmc.items.RecipeWeapons;

public class TragicRecipes {

    public static void load() {
        // Smelting
        GameRegistry.addSmelting(TragicItems.RedMercury, new ItemStack(TragicItems.Quicksilver), 1F);
        GameRegistry.addSmelting(TragicBlocks.MercuryOre, new ItemStack(TragicItems.RedMercury), 2F);
        GameRegistry.addSmelting(TragicBlocks.TungstenOre, new ItemStack(TragicItems.Tungsten), 3.5F);
        GameRegistry
            .addSmelting(new ItemStack(TragicBlocks.TragicOres, 1, 0), new ItemStack(TragicItems.RedMercury), 2F);
        GameRegistry.addSmelting(TragicBlocks.DarkCobblestone, new ItemStack(TragicBlocks.DarkStone, 1, 0), 1F);
        GameRegistry.addSmelting(TragicBlocks.LightCobblestone, new ItemStack(TragicBlocks.LightStone, 1, 0), 1F);
        GameRegistry.addSmelting(TragicBlocks.AshenWood, new ItemStack(Items.coal, 1, 1), 0.5F);
        GameRegistry.addSmelting(TragicBlocks.BleachedWood, new ItemStack(Items.coal, 1, 1), 0.5F);
        GameRegistry.addSmelting(TragicBlocks.HallowedWood, new ItemStack(Items.coal, 1, 1), 0.5F);
        GameRegistry.addSmelting(TragicBlocks.PaintedWood, new ItemStack(Items.coal, 1, 1), 0.5F);
        GameRegistry.addSmelting(TragicBlocks.Darkwood, new ItemStack(Items.coal, 1, 1), 0.5F);

        // Crafting Recipes
        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.QuicksilverIngot, 1),
            " x ",
            "xxx",
            " x ",
            'x',
            TragicItems.Quicksilver);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.MercuryDagger, 1),
            " x",
            "y ",
            'x',
            TragicItems.RedMercury,
            'y',
            TragicItems.Quicksilver);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.Jack, 1),
            "yyy",
            " xy",
            "x y",
            'x',
            TragicItems.Quicksilver,
            'y',
            Items.flint);

        GameRegistry.addShapedRecipe(new ItemStack(TragicItems.SkullHelmet, 1), " x ", "x x", 'x', Items.bone);

        GameRegistry.addShapedRecipe(new ItemStack(TragicItems.SkullPlate, 1), "x x", "xxx", 'x', Items.bone);

        GameRegistry.addShapedRecipe(new ItemStack(TragicItems.SkullLegs, 1), "xxx", "x x", 'x', Items.bone);

        GameRegistry.addShapedRecipe(new ItemStack(TragicItems.SkullBoots, 1), "x x", "x x", 'x', Items.bone);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.OverlordHelm, 1),
            "yxy",
            "x x",
            'x',
            TragicItems.CorruptedEssence,
            'y',
            TragicItems.NanoBots);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.OverlordPlate, 1),
            "x x",
            "yxy",
            "xyx",
            'x',
            TragicItems.CorruptedEssence,
            'y',
            TragicItems.NanoBots);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.OverlordLegs, 1),
            "xyx",
            "yxy",
            "x x",
            'x',
            TragicItems.CorruptedEssence,
            'y',
            TragicItems.NanoBots);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.OverlordBoots, 1),
            "xyx",
            "yxy",
            'x',
            TragicItems.CorruptedEssence,
            'y',
            TragicItems.NanoBots);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.WingsOfLiberation, 1),
            "xyx",
            "wzw",
            "xyx",
            'x',
            TragicItems.ArchangelFeather,
            'y',
            TragicItems.WispParticles,
            'w',
            TragicItems.UnstableIsotope,
            'z',
            Blocks.dragon_egg);

        GameRegistry.addShapedRecipe(
            new ItemStack(Blocks.tnt, 8),
            "yxy",
            "xyx",
            "yxy",
            'x',
            Items.gunpowder,
            'y',
            TragicItems.UnstableIsotope);

        GameRegistry.addShapedRecipe(
            new ItemStack(Blocks.tnt, 16),
            "xyx",
            "yxy",
            "xyx",
            'x',
            TragicItems.UnstableIsotope,
            'y',
            TragicItems.LunarPowder);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.CorruptedEssence, 3),
            "xxx",
            "xyx",
            "xxx",
            'x',
            TragicItems.EnchantedTears,
            'y',
            TragicItems.CorruptedEye);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.CorruptedEgg, 9),
            " x ",
            "xyx",
            " x ",
            'x',
            TragicItems.CorruptedEssence,
            'y',
            Items.egg);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.SynapseCore, 1),
            "zxz",
            "xyx",
            "zxz",
            'y',
            TragicItems.SynapseCrystal,
            'z',
            TragicItems.InfallibleMetal,
            'x',
            TragicItems.NanoBots);

        GameRegistry.addShapedRecipe(new ItemStack(TragicItems.Scythe, 1), "xxx", " x ", "x  ", 'x', Items.bone);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.MobStatue, 1, 0),
            "xyx",
            "yzy",
            "xyx",
            'x',
            TragicItems.LivingClay,
            'y',
            TragicItems.PureLight,
            'z',
            Blocks.redstone_block);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.MobStatue, 1, 1),
            "xyx",
            "yzy",
            "xyx",
            'x',
            TragicItems.LivingClay,
            'y',
            TragicItems.KitsuneTail,
            'z',
            Blocks.redstone_block);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.MobStatue, 1, 2),
            "xyx",
            "yzy",
            "xyx",
            'x',
            TragicItems.LivingClay,
            'y',
            TragicItems.DeathlyHallow,
            'z',
            Blocks.redstone_block);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.MobStatue, 1, 3),
            "xyx",
            "yzy",
            "xyx",
            'x',
            TragicItems.LivingClay,
            'y',
            TragicItems.TimeEssence,
            'z',
            Blocks.redstone_block);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.MobStatue, 1, 4),
            "xyx",
            "yzy",
            "xyx",
            'x',
            TragicItems.LivingClay,
            'y',
            TragicItems.EmpariahClaw,
            'z',
            Blocks.redstone_block);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.MobStatue, 1, 5),
            "xyx",
            "yzy",
            "xyx",
            'x',
            TragicItems.LivingClay,
            'y',
            TragicItems.StarPieces,
            'z',
            Blocks.redstone_block);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.MobStatue, 1, 6),
            "xyx",
            "yzy",
            "xyx",
            'x',
            TragicItems.LivingClay,
            'y',
            new ItemStack(TragicItems.Projectile, 1, 11),
            'z',
            Blocks.redstone_block);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.MobStatue, 1, 7),
            "xyx",
            "yzy",
            "xyx",
            'x',
            TragicItems.LivingClay,
            'y',
            Blocks.hardened_clay,
            'z',
            Blocks.redstone_block);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.MobStatue, 1, 8),
            "xyx",
            "yzy",
            "xyx",
            'x',
            TragicItems.LivingClay,
            'y',
            Items.magma_cream,
            'z',
            Blocks.redstone_block);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.MobStatue, 1, 9),
            "xyx",
            "yzy",
            "xyx",
            'x',
            TragicItems.LivingClay,
            'y',
            TragicItems.CrushedIce,
            'z',
            Blocks.redstone_block);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.MobStatue, 1, 10),
            "xyx",
            "yzy",
            "xyx",
            'x',
            TragicItems.LivingClay,
            'y',
            TragicItems.StinHorn,
            'z',
            Blocks.redstone_block);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.MobStatue, 1, 11),
            "xyx",
            "yzy",
            "xyx",
            'x',
            TragicItems.LivingClay,
            'y',
            TragicItems.DarkParticles,
            'z',
            Blocks.redstone_block);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.MobStatue, 1, 12),
            "xyx",
            "yzy",
            "xyx",
            'x',
            TragicItems.LivingClay,
            'y',
            TragicItems.Ash,
            'z',
            Blocks.redstone_block);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.MobStatue, 1, 13),
            "xyx",
            "yzy",
            "xyx",
            'x',
            TragicItems.LivingClay,
            'y',
            TragicBlocks.StarCrystal,
            'z',
            Blocks.redstone_block);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.MobStatue, 1, 14),
            "xyx",
            "yzy",
            "xyx",
            'x',
            TragicItems.LivingClay,
            'y',
            TragicItems.PureDarkness,
            'z',
            Blocks.redstone_block);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.MobStatue, 1, 15),
            "xyx",
            "yzy",
            "xyx",
            'x',
            TragicItems.LivingClay,
            'y',
            Items.blaze_powder,
            'z',
            Blocks.redstone_block);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.MobStatue, 1, 16),
            "xyx",
            "yzy",
            "xyx",
            'x',
            TragicItems.LivingClay,
            'y',
            TragicItems.SynapseCrystal,
            'z',
            Blocks.redstone_block);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.MobStatue, 1, 17),
            "xyx",
            "yzy",
            "xyx",
            'x',
            TragicItems.LivingClay,
            'y',
            TragicItems.CorruptedEye,
            'z',
            Blocks.redstone_block);

        // To switch between the Overlord forms
        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicItems.MobStatue, 1, 18),
            new ItemStack(TragicItems.MobStatue, 1, 17),
            TragicItems.LivingClay,
            TragicItems.CorruptedEssence);

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicItems.MobStatue, 1, 19),
            new ItemStack(TragicItems.MobStatue, 1, 18),
            TragicItems.LivingClay,
            TragicItems.CorruptedEssence);

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicItems.MobStatue, 1, 17),
            new ItemStack(TragicItems.MobStatue, 1, 19),
            TragicItems.LivingClay,
            TragicItems.CorruptedEssence);

        GameRegistry
            .addShapedRecipe(new ItemStack(TragicItems.Talisman, 1), " x ", "xxx", " x ", 'x', TragicItems.LivingClay);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.SunnyDayTalisman, 1),
            "xyx",
            "zwz",
            "yzy",
            'w',
            TragicItems.Talisman,
            'x',
            Blocks.redstone_block,
            'y',
            TragicItems.LunarPowder,
            'z',
            TragicItems.FireOrb);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.RainDanceTalisman, 1),
            "xyx",
            "zwz",
            "yzy",
            'w',
            TragicItems.Talisman,
            'x',
            Blocks.lapis_block,
            'y',
            TragicItems.LunarPowder,
            'z',
            TragicItems.AquaOrb);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.ThunderstormTalisman, 1),
            "xyx",
            "zwz",
            "yzy",
            'w',
            TragicItems.Talisman,
            'x',
            Blocks.gold_block,
            'y',
            TragicItems.LunarPowder,
            'z',
            TragicItems.LightningOrb);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.TimeManipulatorTalisman, 1),
            "xyx",
            "zwz",
            "yzy",
            'w',
            TragicItems.Talisman,
            'x',
            TragicItems.CelestialDiamond,
            'y',
            TragicItems.LunarPowder,
            'z',
            TragicItems.GravityOrb);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.SynthesisTalisman, 1),
            "xyx",
            "zwz",
            "yzy",
            'w',
            TragicItems.Talisman,
            'x',
            Blocks.emerald_block,
            'y',
            TragicItems.LunarPowder,
            'z',
            new ItemStack(TragicItems.Projectile, 1, 11));

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.HydrationTalisman, 1),
            "xyx",
            "zwz",
            "yzy",
            'w',
            TragicItems.Talisman,
            'x',
            Blocks.diamond_block,
            'y',
            TragicItems.LunarPowder,
            'z',
            TragicItems.EnchantedTears);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.LightningRodTalisman, 1),
            "xyx",
            "zwz",
            "yzy",
            'w',
            TragicItems.Talisman,
            'x',
            Blocks.coal_block,
            'y',
            TragicItems.LunarPowder,
            'z',
            TragicItems.Horn);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.MoonlightTalisman, 1),
            "xyx",
            "zwz",
            "yzy",
            'w',
            TragicItems.Talisman,
            'x',
            Blocks.iron_block,
            'y',
            TragicItems.LunarPowder,
            'z',
            TragicItems.WispParticles);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.DiamondCharm, 1),
            "xyx",
            "yxy",
            "xyx",
            'x',
            Items.diamond,
            'y',
            TragicItems.Tungsten);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.EmeraldCharm, 1),
            "xyx",
            "yxy",
            "xyx",
            'x',
            Items.emerald,
            'y',
            TragicItems.Tungsten);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.RubyCharm, 1),
            "xyx",
            "yxy",
            "xyx",
            'x',
            TragicItems.Ruby,
            'y',
            TragicItems.Tungsten);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.SapphireCharm, 1),
            "xyx",
            "yxy",
            "xyx",
            'x',
            TragicItems.Sapphire,
            'y',
            TragicItems.Tungsten);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.ObsidianOrb, 6),
            "xxx",
            "xyx",
            "xxx",
            'x',
            Blocks.obsidian,
            'y',
            TragicItems.QuicksilverIngot);

        GameRegistry.addRecipe(
            new ShapelessOreRecipe(
                new ItemStack(TragicItems.CelestialSteel, 1),
                "celestialSteelDrops",
                "celestialSteelDrops",
                "celestialSteelDrops",
                TragicItems.CelestialDiamond));

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.StructureSeed, 1, 8),
            "abc",
            "dye",
            "fgh",
            'a',
            TragicItems.PureLight,
            'b',
            TragicItems.LivingClay,
            'c',
            TragicItems.PureDarkness,
            'd',
            TragicItems.KitsuneTail,
            'e',
            TragicItems.DeathlyHallow,
            'f',
            TragicItems.TimeEssence,
            'g',
            TragicItems.EmpariahClaw,
            'h',
            TragicItems.StarPieces,
            'y',
            new ItemStack(TragicBlocks.Aeris, 0, 2));

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.CelestialAegis, 1),
            " x ",
            " x ",
            "yxy",
            'x',
            TragicItems.CelestialSteel,
            'y',
            TragicItems.CelestialDiamond);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.CelestialLongbow, 1),
            "wyx",
            "yxz",
            "wyx",
            'w',
            TragicItems.EmpariahClaw,
            'x',
            TragicItems.CelestialSteel,
            'y',
            TragicItems.CelestialDiamond,
            'z',
            TragicItems.WovenSilk);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.CelestialJack, 1),
            "xxy",
            " xx",
            "y x",
            'x',
            TragicItems.CelestialSteel,
            'y',
            TragicItems.CelestialDiamond);

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicBlocks.BoneBlock, 16),
            TragicItems.DeathlyHallow,
            TragicItems.BoneMarrow);

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicBlocks.BoneBlock, 16, 1),
            TragicItems.DeathlyHallow,
            TragicItems.BoneMarrow,
            Items.rotten_flesh);

        GameRegistry
            .addShapedRecipe(new ItemStack(TragicBlocks.StarCrystal, 16, 15), "xx", "xx", 'x', TragicItems.StarPieces);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.TimeDisruptionCube, 1),
            "xx",
            "xx",
            'x',
            TragicItems.TimeEssence);

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicItems.AwakeningStone, 1),
            TragicItems.EmeraldCharm,
            TragicItems.DiamondCharm,
            TragicItems.ObsidianOrb,
            TragicItems.RubyCharm,
            TragicItems.SapphireCharm);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.CryingObsidianOrb, 3),
            "yzy",
            "zxz",
            "yzy",
            'y',
            new ItemStack(Items.dye, 1, 4),
            'z',
            Blocks.obsidian,
            'x',
            TragicItems.ObsidianOrb);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.BleedingObsidianOrb, 3),
            "yzy",
            "zxz",
            "yzy",
            'y',
            Items.redstone,
            'z',
            Blocks.obsidian,
            'x',
            TragicItems.ObsidianOrb);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.DyingObsidianOrb, 3),
            "yzy",
            "zxz",
            "yzy",
            'y',
            Items.emerald,
            'z',
            Blocks.obsidian,
            'x',
            TragicItems.ObsidianOrb);

        GameRegistry.addShapedRecipe(new ItemStack(Items.coal, 1), "xx", "xx", 'x', TragicItems.Ash);

        // Skeleton spawn egg
        GameRegistry.addShapelessRecipe(
            new ItemStack(Items.spawn_egg, 1, 51),
            TragicItems.EnchantedTears,
            TragicItems.Ash,
            Items.bone);

        // Creeper spawn egg
        GameRegistry.addShapelessRecipe(
            new ItemStack(Items.spawn_egg, 1, 50),
            TragicItems.EnchantedTears,
            TragicItems.Ash,
            Items.gunpowder);

        // Zombie spawn egg
        GameRegistry.addShapelessRecipe(
            new ItemStack(Items.spawn_egg, 1, 54),
            TragicItems.EnchantedTears,
            TragicItems.Ash,
            Items.rotten_flesh);

        // Slime spawn egg
        GameRegistry.addShapelessRecipe(
            new ItemStack(Items.spawn_egg, 1, 55),
            TragicItems.EnchantedTears,
            TragicItems.Ash,
            Items.slime_ball);

        // Spider spawn egg
        GameRegistry.addShapelessRecipe(
            new ItemStack(Items.spawn_egg, 1, 52),
            TragicItems.EnchantedTears,
            TragicItems.Ash,
            Items.spider_eye);

        // Cow spawn egg
        GameRegistry.addShapelessRecipe(
            new ItemStack(Items.spawn_egg, 1, 92),
            TragicItems.EnchantedTears,
            TragicItems.Ash,
            Items.leather);

        // Pig spawn egg
        GameRegistry.addShapelessRecipe(
            new ItemStack(Items.spawn_egg, 1, 90),
            TragicItems.EnchantedTears,
            TragicItems.Ash,
            Items.carrot);

        // Chicken spawn egg
        GameRegistry.addShapelessRecipe(
            new ItemStack(Items.spawn_egg, 1, 93),
            TragicItems.EnchantedTears,
            TragicItems.Ash,
            Items.egg);

        // Mooshroom spawn egg
        GameRegistry.addShapelessRecipe(
            new ItemStack(Items.spawn_egg, 1, 96),
            TragicItems.EnchantedTears,
            TragicItems.Ash,
            Blocks.red_mushroom);
        GameRegistry.addShapelessRecipe(
            new ItemStack(Items.spawn_egg, 1, 96),
            TragicItems.EnchantedTears,
            TragicItems.Ash,
            Blocks.brown_mushroom);

        // Villager spawn egg
        GameRegistry.addShapelessRecipe(
            new ItemStack(Items.spawn_egg, 1, 120),
            TragicItems.EnchantedTears,
            TragicItems.Ash,
            Items.emerald);

        // Horse spawn egg
        GameRegistry.addShapelessRecipe(
            new ItemStack(Items.spawn_egg, 1, 100),
            TragicItems.EnchantedTears,
            TragicItems.Ash,
            Items.saddle);

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicItems.ToughLeather, 1),
            Items.leather,
            TragicItems.WovenSilk,
            Items.leather);

        GameRegistry
            .addShapedRecipe(new ItemStack(TragicItems.ToughLeather, 1), "xxx", " x ", "xxx", 'x', Items.rotten_flesh);

        GameRegistry.addShapedRecipe(new ItemStack(TragicItems.WovenSilk, 1), "xxx", 'x', Items.string);

        GameRegistry
            .addShapedRecipe(new ItemStack(TragicItems.HuntersCap, 1), " x ", "x x", 'x', TragicItems.ToughLeather);

        GameRegistry
            .addShapedRecipe(new ItemStack(TragicItems.HuntersTunic, 1), "x x", "xxx", 'x', TragicItems.ToughLeather);

        GameRegistry
            .addShapedRecipe(new ItemStack(TragicItems.HuntersLegs, 1), "xxx", "x x", 'x', TragicItems.ToughLeather);

        GameRegistry
            .addShapedRecipe(new ItemStack(TragicItems.HuntersBoots, 1), "x x", "x x", 'x', TragicItems.ToughLeather);

        GameRegistry
            .addShapedRecipe(new ItemStack(TragicItems.MercuryHelm, 1), "xxx", "x x", 'x', TragicItems.RedMercury);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.MercuryPlate, 1),
            "x x",
            "xxx",
            "xxx",
            'x',
            TragicItems.RedMercury);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.MercuryLegs, 1),
            "xxx",
            "x x",
            "x x",
            'x',
            TragicItems.RedMercury);

        GameRegistry
            .addShapedRecipe(new ItemStack(TragicItems.MercuryBoots, 1), "x x", "x x", 'x', TragicItems.RedMercury);

        GameRegistry
            .addShapedRecipe(new ItemStack(TragicItems.TungstenHelm, 1), "xxx", "x x", 'x', TragicItems.Tungsten);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.TungstenPlate, 1),
            "x x",
            "xxx",
            "xxx",
            'x',
            TragicItems.Tungsten);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.TungstenLegs, 1),
            "xxx",
            "x x",
            "x x",
            'x',
            TragicItems.Tungsten);

        GameRegistry
            .addShapedRecipe(new ItemStack(TragicItems.TungstenBoots, 1), "x x", "x x", 'x', TragicItems.Tungsten);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.HuntersBow, 1),
            " x",
            "zy",
            " x",
            'x',
            Items.stick,
            'y',
            TragicItems.WovenSilk,
            'z',
            TragicItems.ToughLeather);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.IreNetParticleCannon, 1),
            "zxy",
            "zyz",
            "yxz",
            'z',
            TragicItems.InfallibleMetal,
            'y',
            TragicItems.ComplexCircuitry,
            'x',
            TragicItems.PurifiedEnergy);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.NekoLauncher, 1),
            "zxy",
            "zyz",
            "yxz",
            'z',
            TragicItems.InfallibleMetal,
            'y',
            TragicItems.ComplexCircuitry,
            'x',
            TragicItems.Shadowskin);

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicItems.LightIngot, 1),
            TragicItems.LightParticles,
            TragicItems.Tungsten);

        GameRegistry
            .addShapedRecipe(new ItemStack(TragicItems.LightHelm, 1), "xxx", "x x", 'x', TragicItems.LightIngot);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.LightPlate, 1),
            "x x",
            "xxx",
            "xxx",
            'x',
            TragicItems.LightIngot);

        GameRegistry
            .addShapedRecipe(new ItemStack(TragicItems.LightLegs, 1), "xxx", "x x", "x x", 'x', TragicItems.LightIngot);

        GameRegistry
            .addShapedRecipe(new ItemStack(TragicItems.LightBoots, 1), "x x", "x x", 'x', TragicItems.LightIngot);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.BlindingLight, 1),
            "xzx",
            "xyx",
            " x ",
            'x',
            TragicItems.LightIngot,
            'y',
            Items.diamond,
            'z',
            TragicItems.PureLight);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.BlindingLight, 1),
            "xzx",
            "xyx",
            " x ",
            'x',
            TragicItems.RadiatedInfusion,
            'y',
            Items.diamond,
            'z',
            TragicItems.PurifiedEnergy);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.EverlastingLight, 1),
            "xyx",
            "yzy",
            "xyx",
            'x',
            TragicItems.PureLight,
            'y',
            Blocks.glass,
            'z',
            TragicItems.IreNode);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.EverlastingLight, 1),
            "xyx",
            "yzy",
            "xyx",
            'x',
            TragicItems.RadiatedInfusion,
            'y',
            Blocks.glass,
            'z',
            Items.nether_star);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.Candle, 3),
            "y",
            "x",
            "x",
            'y',
            TragicItems.WovenSilk,
            'x',
            TragicBlocks.Wax,
            'x',
            TragicBlocks.Wax);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.DarkIngot, 1),
            "yxy",
            "xyx",
            "yxy",
            'y',
            TragicItems.DarkParticles,
            'x',
            TragicItems.QuicksilverIngot);

        GameRegistry.addShapedRecipe(new ItemStack(TragicItems.DarkHelm, 1), "xxx", "x x", 'x', TragicItems.DarkIngot);

        GameRegistry
            .addShapedRecipe(new ItemStack(TragicItems.DarkPlate, 1), "x x", "xxx", "xxx", 'x', TragicItems.DarkIngot);

        GameRegistry
            .addShapedRecipe(new ItemStack(TragicItems.DarkLegs, 1), "xxx", "x x", "x x", 'x', TragicItems.DarkIngot);

        GameRegistry.addShapedRecipe(new ItemStack(TragicItems.DarkBoots, 1), "x x", "x x", 'x', TragicItems.DarkIngot);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.PitchBlack, 1),
            "zxz",
            "xyx",
            "zxz",
            'x',
            TragicItems.DarkIngot,
            'y',
            TragicItems.ObsidianOrb,
            'z',
            TragicItems.PureDarkness);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.PitchBlack, 1),
            "zxz",
            "xyx",
            "zxz",
            'x',
            TragicItems.DarkIngot,
            'y',
            TragicItems.InfallibleMetal,
            'z',
            TragicItems.ImpossibleReaction);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.IceCream, 1),
            " x ",
            "xyx",
            'x',
            TragicItems.CrushedIce,
            'y',
            Items.bowl);

        GameRegistry
            .addShapedRecipe(new ItemStack(TragicItems.Rice, 1), " x ", "xyx", 'x', Items.wheat, 'y', Items.bowl);

        GameRegistry.addRecipe(
            new ShapedOreRecipe(new ItemStack(TragicItems.Sushi, 6), "xxx", "yyy", 'x', "fish", 'y', "cropRice"));

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.GoldenSushi, 3),
            "xxx",
            "xyx",
            "xxx",
            'x',
            Blocks.gold_block,
            'y',
            TragicItems.Sushi);

        GameRegistry.addShapedRecipe(
            new ItemStack(Blocks.ender_chest, 1),
            "xxx",
            "xyx",
            "xxx",
            'x',
            Blocks.obsidian,
            'y',
            TragicItems.PureDarkness);

        GameRegistry.addShapedRecipe(new ItemStack(Blocks.ice, 3), "xxx", "xxx", "xxx", 'x', TragicItems.CrushedIce);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.CompactOre, 1, 3),
            "xxx",
            "xxx",
            "xxx",
            'x',
            TragicItems.RedMercury);

        GameRegistry
            .addShapelessRecipe(new ItemStack(TragicItems.RedMercury, 9), new ItemStack(TragicBlocks.CompactOre, 1, 3));

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.CompactOre, 1, 4),
            "xxx",
            "xxx",
            "xxx",
            'x',
            TragicItems.QuicksilverIngot);

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicItems.QuicksilverIngot, 9),
            new ItemStack(TragicBlocks.CompactOre, 1, 4));

        GameRegistry
            .addShapedRecipe(new ItemStack(TragicBlocks.CompactOre, 1, 0), "xxx", "xxx", "xxx", 'x', TragicItems.Ruby);

        GameRegistry
            .addShapelessRecipe(new ItemStack(TragicItems.Ruby, 9), new ItemStack(TragicBlocks.CompactOre, 1, 0));

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.CompactOre, 1, 1),
            "xxx",
            "xxx",
            "xxx",
            'x',
            TragicItems.Sapphire);

        GameRegistry
            .addShapelessRecipe(new ItemStack(TragicItems.Sapphire, 9), new ItemStack(TragicBlocks.CompactOre, 1, 1));

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.CompactOre, 1, 2),
            "xxx",
            "xxx",
            "xxx",
            'x',
            TragicItems.Tungsten);

        GameRegistry
            .addShapelessRecipe(new ItemStack(TragicItems.Tungsten, 9), new ItemStack(TragicBlocks.CompactOre, 1, 2));

        GameRegistry
            .addShapedRecipe(new ItemStack(TragicBlocks.PotatoBlock, 1), "xxx", "xxx", "xxx", 'x', Items.potato);

        GameRegistry
            .addShapedRecipe(new ItemStack(TragicBlocks.CarrotBlock, 1), "xxx", "xxx", "xxx", 'x', Items.carrot);

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicBlocks.Wax, 1),
            TragicItems.EnchantedTears,
            TragicItems.EnchantedTears,
            Items.clay_ball,
            Items.clay_ball);

        GameRegistry.addShapedRecipe(new ItemStack(TragicItems.Projectile, 3), "xx", "xx", 'x', Blocks.gravel);

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicItems.Projectile, 3, 1),
            TragicItems.Projectile,
            TragicItems.FireOrb);

        GameRegistry.addShapedRecipe(
            new ItemStack(Items.clay_ball, 3),
            " x ",
            "xyx",
            " x ",
            'x',
            Blocks.gravel,
            'y',
            TragicItems.EnchantedTears);

        GameRegistry.addShapedRecipe(
            new ItemStack(Blocks.cobblestone, 1),
            "xxx",
            "xxx",
            "xxx",
            'x',
            new ItemStack(TragicItems.Projectile, 1, 0));

        GameRegistry.addShapedRecipe(
            new ItemStack(Blocks.gravel, 1),
            "xx",
            "xx",
            'x',
            new ItemStack(TragicItems.Projectile, 1, 0));

        GameRegistry.addShapedRecipe(
            new ItemStack(Blocks.netherrack, 1),
            "xxx",
            "xxx",
            "xxx",
            'x',
            new ItemStack(TragicItems.Projectile, 1, 1));

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.FrozenNetherrack, 8),
            "xxx",
            "xyx",
            "xxx",
            'x',
            Blocks.netherrack,
            'y',
            TragicItems.IceOrb);

        GameRegistry
            .addShapedRecipe(new ItemStack(TragicBlocks.NetherBrickPressurePlate, 1), "xx", 'x', Blocks.nether_brick);

        GameRegistry
            .addShapedRecipe(new ItemStack(TragicBlocks.SandstonePressurePlate, 1), "xx", 'x', Blocks.sandstone);

        GameRegistry.addRecipe(
            new ShapedOreRecipe(
                new ItemStack(TragicItems.GuiltyThorn, 1),
                "yzy",
                "yzy",
                "xzx",
                'x',
                TragicItems.NauseatingConcoction,
                'y',
                TragicItems.ToxicAmalgation,
                'z',
                TragicItems.QuicksilverIngot));

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicItems.CooldownDefuse),
            TragicItems.ImpossibleReaction,
            TragicItems.RadiatedInfusion,
            TragicItems.PurifiedEnergy);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.Glowvine, 1),
            "xyx",
            "zyz",
            "xyx",
            'x',
            Items.glowstone_dust,
            'y',
            Blocks.vine,
            'z',
            new ItemStack(Items.dye, 1, 4));

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.WickedVine, 1),
            "xyx",
            "zyz",
            "xyx",
            'x',
            Items.glowstone_dust,
            'y',
            Blocks.vine,
            'z',
            new ItemStack(TragicItems.Projectile, 1, 11));

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.DarkVine, 1),
            "xyx",
            "zyz",
            "xyx",
            'x',
            Items.glowstone_dust,
            'y',
            Blocks.vine,
            'z',
            TragicItems.DarkParticles);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.FrozenLightning, 1),
            "vyv",
            "xwx",
            " x ",
            'v',
            TragicItems.LightningOrb,
            'w',
            TragicItems.PurifiedEnergy,
            'x',
            TragicItems.IceOrb,
            'y',
            TragicItems.ParadoxicalFormula);

        GameRegistry
            .addShapedRecipe(new ItemStack(TragicBlocks.DarkSandstone, 4, 0), "xx", "xx", 'x', TragicBlocks.DarkSand);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.DarkSandstone, 4, 1),
            "xx",
            "xx",
            'x',
            new ItemStack(TragicBlocks.DarkSandstone, 1, 0));

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.DarkSandstone, 4, 2),
            "xx",
            "xx",
            'x',
            new ItemStack(TragicBlocks.DarkSandstone, 1, 1));

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.DarkSandstone, 4, 3),
            "xx",
            "xx",
            'x',
            new ItemStack(TragicBlocks.DarkSandstone, 1, 2));

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.DarkSandstone, 4, 4),
            "xxx",
            "xxx",
            "xxx",
            'x',
            new ItemStack(TragicBlocks.DarkSandstone, 1, 2));

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.DarkSandstone, 4, 5),
            "xyx",
            "yzy",
            "xyx",
            'x',
            new ItemStack(TragicBlocks.DarkSandstone, 1, 1),
            'y',
            TragicItems.DarkParticles,
            'z',
            Items.ender_eye);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.Corsin, 4, 2),
            "xx",
            "xx",
            'x',
            new ItemStack(TragicBlocks.Corsin, 1, 0));

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.Corsin, 4, 3),
            "xx",
            "xx",
            'x',
            new ItemStack(TragicBlocks.Corsin, 1, 1));

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.Corsin, 4, 6),
            "xy",
            "yx",
            'x',
            new ItemStack(TragicBlocks.Corsin, 1, 1),
            'y',
            new ItemStack(TragicBlocks.Corsin, 1, 0));

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.Corsin, 4, 5),
            "xx",
            "xx",
            'x',
            new ItemStack(TragicBlocks.Corsin, 1, 2));

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.Corsin, 4, 5),
            "xx",
            "xx",
            'x',
            new ItemStack(TragicBlocks.Corsin, 1, 3));

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.Corsin, 4, 8),
            "xxx",
            "xyx",
            "xxx",
            'x',
            new ItemStack(TragicBlocks.Corsin, 1, 5),
            'y',
            TragicItems.WispParticles);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.Corsin, 4, 7),
            "xyx",
            "yzy",
            "xyx",
            'x',
            new ItemStack(TragicBlocks.Corsin, 1, 5),
            'y',
            TragicItems.WispParticles,
            'z',
            TragicItems.UnstableIsotope);

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicBlocks.Corsin, 3, 1),
            Items.water_bucket,
            new ItemStack(TragicBlocks.Corsin, 1, 0),
            new ItemStack(TragicBlocks.Corsin, 1, 0),
            new ItemStack(TragicBlocks.Corsin, 1, 0));

        // Amulets
        if (TragicConfig.allowAmuletCrafting) {
            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.ChickenAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                Items.feather,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.PeaceAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                TragicItems.Sushi,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.ClaymationAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                TragicItems.LivingClay,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.CreeperAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                Items.gunpowder,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.SkeletonAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                TragicItems.BoneMarrow,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.ZombieAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                Items.rotten_flesh,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.ApisAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                TragicItems.LightParticles,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.KitsuneAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                TragicItems.KitsuneTail,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.SunkenAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                TragicItems.AquaOrb,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.PiercingAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                TragicItems.Thorns,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.BlacksmithAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                Items.flint,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.MartyrAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                Items.ghast_tear,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.YetiAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                TragicItems.EmpariahClaw,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.TimeAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                TragicItems.TimeEssence,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.IceAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                TragicItems.IceOrb,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.SnowGolemAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                Items.snowball,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.IronGolemAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                Blocks.iron_block,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.EndermanAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                Items.ender_pearl,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.WitherAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                Items.nether_star,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.SpiderAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                Items.spider_eye,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.StinAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                TragicItems.StinHorn,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.PolarisAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                TragicItems.StarPieces,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.OverlordAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                TragicItems.CorruptedEssence,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.LightningAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                TragicItems.LightningOrb,
                'z',
                TragicItems.AwakeningStone);

            if (TragicConfig.allowDoom) {
                GameRegistry.addShapedRecipe(
                    new ItemStack(TragicItems.ConsumptionAmulet, 1),
                    "xyx",
                    "yzy",
                    "xyx",
                    'x',
                    TragicItems.Tungsten,
                    'y',
                    TragicItems.DoomConsume,
                    'z',
                    TragicItems.AwakeningStone);
            }

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.SupernaturalAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                TragicItems.Ectoplasm,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.UndeadAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                TragicItems.BoneMarrow,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.EnderDragonAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                Blocks.dragon_egg,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.FuseaAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                TragicItems.UnstableIsotope,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.EnyvilAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                TragicItems.PureDarkness,
                'z',
                TragicItems.AwakeningStone);

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicItems.LuckAmulet, 1),
                "xyx",
                "yzy",
                "xyx",
                'x',
                TragicItems.Tungsten,
                'y',
                Items.experience_bottle,
                'z',
                TragicItems.AwakeningStone);
        }

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicBlocks.Quicksand, 3, 0),
            Blocks.sand,
            TragicItems.EnchantedTears,
            TragicItems.LivingClay);

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicBlocks.Quicksand, 3, 1),
            Blocks.dirt,
            TragicItems.EnchantedTears,
            TragicItems.LivingClay);

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicBlocks.Quicksand, 3, 2),
            Blocks.netherrack,
            TragicItems.EnchantedTears,
            TragicItems.LivingClay);

        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.lit_pumpkin, 1), TragicBlocks.Candle, Blocks.pumpkin);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.TungstenJack, 1),
            "xxx",
            " xx",
            "x x",
            'x',
            TragicItems.Tungsten);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.WitheringAxe, 1),
            "xx ",
            " yx",
            "x x",
            'x',
            TragicItems.DarkIngot,
            'y',
            Items.nether_star);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.WitheringAxe, 1),
            "xyz",
            " zy",
            "y x",
            'x',
            TragicItems.DarkIngot,
            'y',
            TragicItems.InfallibleMetal,
            'z',
            TragicItems.ImpossibleReaction);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.ReaperScythe, 1),
            "xxz",
            " y ",
            "y  ",
            'x',
            TragicItems.DarkIngot,
            'y',
            TragicItems.StinHorn,
            'z',
            TragicItems.DeathlyHallow);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.ReaperScythe, 1),
            "xxy",
            " z ",
            "z  ",
            'x',
            TragicItems.InfallibleMetal,
            'y',
            TragicItems.CreepyIdol,
            'z',
            TragicItems.Shadowskin);

        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 3, 15), TragicItems.Horn);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.MourningStar, 1),
            "xxx",
            "xyx",
            "zxx",
            'x',
            TragicItems.StinHorn,
            'y',
            TragicItems.DeathlyHallow,
            'z',
            TragicItems.DarkIngot);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.MourningStar, 1),
            "xxx",
            "xyx",
            "zxx",
            'z',
            TragicItems.DarkIngot,
            'y',
            TragicItems.CreepyIdol,
            'x',
            TragicItems.InfallibleMetal);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.BeastlyClaws, 1),
            "xxx",
            "xyx",
            "zyz",
            'x',
            TragicItems.EmpariahClaw,
            'y',
            TragicItems.Tungsten,
            'z',
            TragicItems.IcyFur);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.BeastlyClaws, 1),
            "xxx",
            "xyx",
            " y ",
            'x',
            TragicItems.InfallibleMetal,
            'y',
            TragicItems.Quicksilver);

        GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 9), TragicItems.IcyFur, Items.shears);

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicItems.ToughLeather, 3),
            TragicItems.IcyFur,
            TragicItems.WovenSilk,
            TragicItems.IcyFur);

        GameRegistry.addShapelessRecipe(
            new ItemStack(Items.glowstone_dust, 16),
            TragicItems.StarPieces,
            TragicItems.WispParticles);

        GameRegistry.addShapelessRecipe(new ItemStack(Items.redstone, 3), TragicItems.WispParticles);

        GameRegistry.addShapedRecipe(
            new ItemStack(Items.experience_bottle, 3),
            " x ",
            "xyx",
            " x ",
            'x',
            Blocks.glass,
            'y',
            TragicItems.WispParticles);

        GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.DarkParticles, 3), TragicItems.StinHorn);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.TimeDisruptionCube, 1),
            "xyx",
            "yzy",
            "xyx",
            'x',
            TragicItems.CelestialSteel,
            'y',
            TragicItems.LunarPowder,
            'z',
            TragicItems.ObsidianOrb);

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicItems.CelestialDiamond, 1),
            TragicItems.LunarPowder,
            TragicItems.LunarPowder,
            TragicItems.SoulExcess,
            Items.diamond);

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicItems.DimensionalKey),
            TragicItems.DyingObsidianOrb,
            TragicItems.CryingObsidianOrb,
            TragicItems.BleedingObsidianOrb,
            Items.nether_star);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.TragicObsidian, 3, 0),
            "xx",
            "xx",
            'x',
            TragicItems.CryingObsidianOrb);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.TragicObsidian, 3, 1),
            "xx",
            "xx",
            'x',
            TragicItems.BleedingObsidianOrb);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.TragicObsidian, 3, 2),
            "xx",
            "xx",
            'x',
            TragicItems.DyingObsidianOrb);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.DarkSand, 8),
            "xxx",
            "xyx",
            "xxx",
            'x',
            Blocks.sand,
            'y',
            TragicItems.DarkParticles);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.SmoothNetherrack, 8, 0),
            "xxx",
            "xyx",
            "xxx",
            'x',
            Blocks.netherrack,
            'y',
            TragicItems.LightParticles);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.SmoothNetherrack, 4, 2),
            "xx",
            "xx",
            'x',
            new ItemStack(TragicBlocks.SmoothNetherrack, 1, 0));

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.SmoothNetherrack, 4, 1),
            "xx",
            "xx",
            'x',
            new ItemStack(TragicBlocks.SmoothNetherrack, 1, 2));

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.SmoothNetherrack, 3, 3),
            "x x",
            " x ",
            "x x",
            'x',
            new ItemStack(TragicBlocks.SmoothNetherrack, 1, 1));

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.SmoothNetherrack, 4, 4),
            "xxx",
            "xyx",
            "xxx",
            'x',
            new ItemStack(TragicBlocks.SmoothNetherrack, 1, 0),
            'y',
            TragicItems.KitsuneTail);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.SmoothNetherrack, 4, 5),
            "xxx",
            "xyx",
            "xxx",
            'x',
            new ItemStack(TragicBlocks.SmoothNetherrack, 1, 0),
            'y',
            Items.lava_bucket);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.DarkenedQuartz, 4, 0),
            "xyx",
            "yxy",
            "xyx",
            'x',
            Items.quartz,
            'y',
            TragicItems.DarkParticles);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.DarkenedQuartz, 4, 1),
            "xx",
            "xx",
            'x',
            TragicBlocks.DarkenedQuartz);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.DarkenedQuartz, 2, 2),
            "x",
            "x",
            "x",
            'x',
            new ItemStack(TragicBlocks.DarkenedQuartz, 1, 1));

        for (byte i = 0; i < 8; i++) {
            GameRegistry.addShapedRecipe(
                new ItemStack(TragicBlocks.DarkStone, 4, i + 7),
                "xx",
                "xx",
                'x',
                new ItemStack(TragicBlocks.DarkStone, 1, i));
        }

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.GravitySpike, 1),
            "xyx",
            "zxz",
            " x ",
            'x',
            TragicItems.DarkIngot,
            'y',
            TragicItems.GravityOrb,
            'z',
            TragicItems.RadiatedInfusion);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.HarmonyBell, 1),
            " y ",
            "xxx",
            "xxx",
            'x',
            TragicItems.LightParticles,
            'y',
            TragicItems.KitsuneTail);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicItems.HarmonyBell, 1),
            " y ",
            "xyx",
            "xxx",
            'x',
            TragicItems.InfallibleMetal,
            'y',
            TragicItems.RadiatedInfusion);

        GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.DarkParticles, 8), TragicItems.PureDarkness);

        GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.LightParticles, 8), TragicItems.PureLight);

        GameRegistry
            .addShapelessRecipe(new ItemStack(Items.ender_eye, 1), TragicItems.PureDarkness, TragicItems.ObsidianOrb);

        GameRegistry.addShapelessRecipe(new ItemStack(Items.nether_star, 1), TragicItems.PureLight, Items.emerald);

        GameRegistry.addShapelessRecipe(new ItemStack(TragicBlocks.PaintedPlanks, 4), TragicBlocks.PaintedWood);

        GameRegistry.addShapelessRecipe(new ItemStack(TragicBlocks.BleachedPlanks, 4), TragicBlocks.BleachedWood);

        GameRegistry.addShapelessRecipe(new ItemStack(TragicBlocks.AshenPlanks, 4), TragicBlocks.AshenWood);

        GameRegistry
            .addShapedRecipe(new ItemStack(TragicBlocks.CelledBlock, 6), "xx", "xx", 'x', TragicBlocks.ErodedStone);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.CelledBlock, 6),
            "xxx",
            "xxx",
            "xxx",
            'x',
            TragicItems.NanoBots);

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicItems.NourishmentSacrifice),
            TragicItems.NauseatingConcoction,
            TragicItems.NauseatingConcoction,
            TragicItems.SoulExcess,
            TragicItems.ParadoxicalFormula);

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicItems.BloodSacrifice),
            TragicItems.CreepyIdol,
            TragicItems.CreepyIdol,
            TragicItems.SoulExcess,
            TragicItems.ParadoxicalFormula);

        // Intermediate item recipes
        GameRegistry.addRecipe(
            new ShapelessOreRecipe(
                new ItemStack(TragicItems.ToxicAmalgation),
                new ItemStack(TragicItems.Projectile, 1, 11),
                TragicItems.Thorns,
                "materialVine",
                TragicItems.InterestingResin));

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicItems.LunarPowder),
            Items.glowstone_dust,
            TragicItems.SoulExcess,
            TragicItems.Ectoplasm,
            TragicItems.WispParticles,
            TragicItems.StarPieces);

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicItems.ImpossibleReaction),
            TragicItems.LightParticles,
            TragicItems.DarkParticles,
            TragicItems.StinHorn,
            TragicItems.ArchangelFeather);

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicItems.RadiatedInfusion),
            TragicItems.Tungsten,
            TragicItems.UnstableIsotope,
            TragicItems.FireOrb,
            TragicItems.LightParticles);

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicItems.ParadoxicalFormula),
            TragicItems.CrushedIce,
            new ItemStack(TragicItems.Projectile, 1, 1),
            TragicItems.Ectoplasm,
            TragicItems.EnchantedTears);

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicItems.InfallibleMetal),
            TragicItems.Tungsten,
            TragicItems.Quicksilver,
            Items.iron_ingot,
            TragicItems.Chitin);

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicItems.ComplexCircuitry),
            TragicItems.NanoBots,
            TragicItems.CatalyticCompound,
            TragicItems.IreNode,
            TragicItems.LightningOrb);

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicItems.NauseatingConcoction),
            TragicItems.Ectoplasm,
            TragicItems.BoneMarrow,
            Items.slime_ball,
            Items.spider_eye,
            TragicItems.Ash);

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicItems.CreepyIdol),
            TragicItems.Chitin,
            TragicItems.Horn,
            TragicItems.InterestingResin,
            Items.clay_ball,
            TragicItems.SoulExcess);

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicItems.PurifiedEnergy),
            TragicItems.EnchantedTears,
            TragicItems.CatalyticCompound,
            TragicItems.LightningOrb);

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicItems.Shadowskin),
            TragicItems.DarkParticles,
            TragicItems.WovenSilk,
            TragicItems.Ash,
            TragicItems.ToughLeather);

        // Cobblestone recipes
        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.DarkCobblestone, 8, 1),
            "xxx",
            "xyx",
            "xxx",
            'x',
            TragicBlocks.DarkCobblestone,
            'y',
            Items.redstone);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.DarkCobblestone, 8, 2),
            "xxx",
            "xyx",
            "xxx",
            'x',
            TragicBlocks.DarkCobblestone,
            'y',
            new ItemStack(TragicItems.Projectile, 1, 11));

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.DarkCobblestone, 8, 3),
            "xxx",
            "xyx",
            "xxx",
            'x',
            TragicBlocks.DarkCobblestone,
            'y',
            TragicItems.DarkParticles);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.LightCobblestone, 8),
            "xxx",
            "xyx",
            "xxx",
            'x',
            TragicBlocks.DarkCobblestone,
            'y',
            TragicItems.WispParticles);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.LightCobblestone, 8),
            "xxx",
            "xyx",
            "xxx",
            'x',
            TragicBlocks.DarkCobblestone,
            'y',
            TragicItems.LightParticles);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.LightCobblestone, 8, 1),
            "xxx",
            "xyx",
            "xxx",
            'x',
            TragicBlocks.LightCobblestone,
            'y',
            TragicItems.LightningOrb);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.LightCobblestone, 8, 2),
            "xxx",
            "xyx",
            "xxx",
            'x',
            TragicBlocks.LightCobblestone,
            'y',
            TragicItems.WispParticles);

        GameRegistry.addShapedRecipe(
            new ItemStack(TragicBlocks.LightCobblestone, 8, 2),
            "xxx",
            "xyx",
            "xxx",
            'x',
            TragicBlocks.LightCobblestone,
            'y',
            TragicItems.LightParticles);

        GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.HoneydropSeeds), TragicItems.Honeydrop);

        GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.DeathglowSeeds), TragicItems.Deathglow);

        GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.SkyFruitSeeds), TragicItems.SkyFruit);

        // Flower-to-Dye recipes
        GameRegistry
            .addShapelessRecipe(new ItemStack(Items.dye, 1, 12), new ItemStack(TragicBlocks.TragicFlower, 1, 0));

        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 9), new ItemStack(TragicBlocks.TragicFlower, 1, 1));

        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 1), new ItemStack(TragicBlocks.TragicFlower, 1, 2));

        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 7), new ItemStack(TragicBlocks.TragicFlower, 1, 3));

        GameRegistry
            .addShapelessRecipe(new ItemStack(Items.dye, 1, 12), new ItemStack(TragicBlocks.TragicFlower, 1, 4));

        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 1), new ItemStack(TragicBlocks.TragicFlower, 1, 5));

        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 9), new ItemStack(TragicBlocks.TragicFlower, 1, 6));

        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 1), new ItemStack(TragicBlocks.TragicFlower, 1, 7));

        GameRegistry
            .addShapelessRecipe(new ItemStack(Items.dye, 1, 12), new ItemStack(TragicBlocks.TragicFlower, 1, 8));

        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 5), new ItemStack(TragicBlocks.TragicFlower, 1, 9));

        GameRegistry
            .addShapelessRecipe(new ItemStack(Items.dye, 1, 9), new ItemStack(TragicBlocks.TragicFlower, 1, 10));

        GameRegistry
            .addShapelessRecipe(new ItemStack(Items.dye, 1, 7), new ItemStack(TragicBlocks.TragicFlower, 1, 11));

        GameRegistry
            .addShapelessRecipe(new ItemStack(Items.dye, 1, 14), new ItemStack(TragicBlocks.TragicFlower, 1, 12));

        GameRegistry
            .addShapelessRecipe(new ItemStack(Items.dye, 1, 6), new ItemStack(TragicBlocks.TragicFlower, 1, 13));

        GameRegistry.addShapelessRecipe(
            new ItemStack(Items.glowstone_dust, 1),
            new ItemStack(TragicBlocks.TragicFlower, 1, 14));

        GameRegistry
            .addShapelessRecipe(new ItemStack(Items.dye, 1, 4), new ItemStack(TragicBlocks.TragicFlower, 1, 15));

        GameRegistry
            .addShapelessRecipe(new ItemStack(Items.dye, 1, 2), new ItemStack(TragicBlocks.TragicFlower2, 1, 0));

        GameRegistry
            .addShapelessRecipe(new ItemStack(Items.dye, 1, 13), new ItemStack(TragicBlocks.TragicFlower2, 1, 1));

        GameRegistry
            .addShapelessRecipe(new ItemStack(Items.dye, 1, 8), new ItemStack(TragicBlocks.TragicFlower2, 1, 2));

        GameRegistry
            .addShapelessRecipe(new ItemStack(Items.dye, 1, 5), new ItemStack(TragicBlocks.TragicFlower2, 1, 3));

        GameRegistry
            .addShapelessRecipe(new ItemStack(Items.dye, 1, 11), new ItemStack(TragicBlocks.TragicFlower2, 1, 4));

        GameRegistry
            .addShapelessRecipe(new ItemStack(Items.dye, 1, 12), new ItemStack(TragicBlocks.TragicFlower2, 1, 5));

        GameRegistry
            .addShapelessRecipe(new ItemStack(Items.dye, 1, 12), new ItemStack(TragicBlocks.TragicFlower2, 1, 6));

        GameRegistry
            .addShapelessRecipe(new ItemStack(Items.dye, 1, 12), new ItemStack(TragicBlocks.TragicFlower2, 1, 7));

        GameRegistry
            .addShapelessRecipe(new ItemStack(Items.dye, 1, 14), new ItemStack(TragicBlocks.TragicFlower2, 1, 8));

        GameRegistry
            .addShapelessRecipe(new ItemStack(Items.glowstone_dust), new ItemStack(TragicBlocks.TragicFlower2, 1, 9));

        GameRegistry
            .addShapelessRecipe(new ItemStack(Items.fire_charge), new ItemStack(TragicBlocks.TragicFlower2, 1, 10));

        GameRegistry.addShapelessRecipe(
            new ItemStack(TragicItems.DarkParticles),
            new ItemStack(TragicBlocks.TragicFlower2, 1, 11));

        GameRegistry
            .addShapelessRecipe(new ItemStack(TragicItems.NanoBots), new ItemStack(TragicBlocks.TragicFlower2, 1, 12));

        GameRegistry
            .addShapelessRecipe(new ItemStack(Items.dye, 1, 13), new ItemStack(TragicBlocks.TragicFlower2, 1, 13));

        GameRegistry
            .addShapelessRecipe(new ItemStack(Items.dye, 1, 6), new ItemStack(TragicBlocks.TragicFlower2, 1, 14));

        GameRegistry
            .addShapelessRecipe(new ItemStack(Items.blaze_powder), new ItemStack(TragicBlocks.TragicFlower2, 1, 15));

        // Star Crystal dye recipes
        for (int i = 0; i < 15; i++) {
            GameRegistry.addShapedRecipe(
                new ItemStack(TragicBlocks.StarCrystal, 4, i),
                "xyx",
                "yxy",
                "xyx",
                'x',
                new ItemStack(Items.dye, 1, i),
                'y',
                new ItemStack(TragicBlocks.StarCrystal, 1, 15));

            GameRegistry.addShapedRecipe(
                new ItemStack(TragicBlocks.StarCrystal, 4, i),
                "xxx",
                "xyx",
                "xxx",
                'x',
                new ItemStack(Items.dye, 1, i),
                'y',
                TragicItems.WispParticles);
        }

        // Celled Lamp dye recipes
        for (int i = 0; i < 15; i++) {
            GameRegistry.addShapedRecipe(
                new ItemStack(TragicBlocks.CelledLamp, 8, i),
                "yyy",
                "yxy",
                "yyy",
                'x',
                new ItemStack(Items.dye, 1, i),
                'y',
                TragicBlocks.CelledBlock);
        }

        // Amulet level-up recipes
        if (TragicConfig.allowAmuletLeveling) {
            RecipeSorter.register(
                "tragicmc:amuletleveling",
                RecipeAmulets.class,
                RecipeSorter.Category.SHAPELESS,
                "after:forge:shapelessore");

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.BlacksmithAmulet, 1),
                    TragicItems.BlacksmithAmulet,
                    TragicItems.BlacksmithAmulet,
                    "oreCharms"));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.BlacksmithAmulet, 1),
                    TragicItems.BlacksmithAmulet,
                    TragicItems.AmuletRelease));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.CreeperAmulet, 1),
                    TragicItems.CreeperAmulet,
                    TragicItems.CreeperAmulet,
                    "oreCharms"));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.CreeperAmulet, 1),
                    TragicItems.CreeperAmulet,
                    TragicItems.AmuletRelease));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.ClaymationAmulet, 1),
                    TragicItems.ClaymationAmulet,
                    TragicItems.ClaymationAmulet,
                    "oreCharms"));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.ClaymationAmulet, 1),
                    TragicItems.ClaymationAmulet,
                    TragicItems.AmuletRelease));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.SkeletonAmulet, 1),
                    TragicItems.SkeletonAmulet,
                    TragicItems.SkeletonAmulet,
                    "oreCharms"));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.SkeletonAmulet, 1),
                    TragicItems.SkeletonAmulet,
                    TragicItems.AmuletRelease));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.ChickenAmulet, 1),
                    TragicItems.ChickenAmulet,
                    TragicItems.ChickenAmulet,
                    "oreCharms"));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.ChickenAmulet, 1),
                    TragicItems.ChickenAmulet,
                    TragicItems.AmuletRelease));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.PeaceAmulet, 1),
                    TragicItems.PeaceAmulet,
                    TragicItems.PeaceAmulet,
                    "oreCharms"));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.PeaceAmulet, 1),
                    TragicItems.PeaceAmulet,
                    TragicItems.AmuletRelease));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.YetiAmulet, 1),
                    TragicItems.YetiAmulet,
                    TragicItems.YetiAmulet,
                    "oreCharms"));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.YetiAmulet, 1),
                    TragicItems.YetiAmulet,
                    TragicItems.AmuletRelease));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.ZombieAmulet, 1),
                    TragicItems.ZombieAmulet,
                    TragicItems.ZombieAmulet,
                    "oreCharms"));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.ZombieAmulet, 1),
                    TragicItems.ZombieAmulet,
                    TragicItems.AmuletRelease));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.IceAmulet, 1),
                    TragicItems.IceAmulet,
                    TragicItems.IceAmulet,
                    "oreCharms"));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.IceAmulet, 1),
                    TragicItems.IceAmulet,
                    TragicItems.AmuletRelease));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.SnowGolemAmulet, 1),
                    TragicItems.SnowGolemAmulet,
                    TragicItems.SnowGolemAmulet,
                    "oreCharms"));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.SnowGolemAmulet, 1),
                    TragicItems.SnowGolemAmulet,
                    TragicItems.AmuletRelease));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.IronGolemAmulet, 1),
                    TragicItems.IronGolemAmulet,
                    TragicItems.IronGolemAmulet,
                    "oreCharms"));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.IronGolemAmulet, 1),
                    TragicItems.IronGolemAmulet,
                    TragicItems.AmuletRelease));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.SpiderAmulet, 1),
                    TragicItems.SpiderAmulet,
                    TragicItems.SpiderAmulet,
                    "oreCharms"));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.SpiderAmulet, 1),
                    TragicItems.SpiderAmulet,
                    TragicItems.AmuletRelease));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.StinAmulet, 1),
                    TragicItems.StinAmulet,
                    TragicItems.StinAmulet,
                    "oreCharms"));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.StinAmulet, 1),
                    TragicItems.StinAmulet,
                    TragicItems.AmuletRelease));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.SupernaturalAmulet, 1),
                    TragicItems.SupernaturalAmulet,
                    TragicItems.SupernaturalAmulet,
                    "oreCharms"));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.SupernaturalAmulet, 1),
                    TragicItems.SupernaturalAmulet,
                    TragicItems.AmuletRelease));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.FuseaAmulet, 1),
                    TragicItems.FuseaAmulet,
                    TragicItems.FuseaAmulet,
                    "oreCharms"));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.FuseaAmulet, 1),
                    TragicItems.FuseaAmulet,
                    TragicItems.AmuletRelease));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.LuckAmulet, 1),
                    TragicItems.LuckAmulet,
                    TragicItems.LuckAmulet,
                    "oreCharms"));

            GameRegistry.addRecipe(
                new RecipeAmulets(
                    new ItemStack(TragicItems.LuckAmulet, 1),
                    TragicItems.LuckAmulet,
                    TragicItems.AmuletRelease));
        }

        // Weapon fusing recipes
        RecipeSorter.register(
            "tragicmc:weaponcombining",
            RecipeWeapons.class,
            RecipeSorter.Category.SHAPELESS,
            "after:forge:shapelessore");

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.MercuryDagger),
                new ItemStack(TragicItems.MercuryDagger),
                new ItemStack(TragicItems.MercuryDagger),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.HuntersBow),
                new ItemStack(TragicItems.HuntersBow),
                new ItemStack(TragicItems.HuntersBow),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.PitchBlack),
                new ItemStack(TragicItems.PitchBlack),
                new ItemStack(TragicItems.PitchBlack),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.BlindingLight),
                new ItemStack(TragicItems.BlindingLight),
                new ItemStack(TragicItems.BlindingLight),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.GravitySpike),
                new ItemStack(TragicItems.GravitySpike),
                new ItemStack(TragicItems.GravitySpike),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.HarmonyBell),
                new ItemStack(TragicItems.HarmonyBell),
                new ItemStack(TragicItems.HarmonyBell),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.MourningStar),
                new ItemStack(TragicItems.MourningStar),
                new ItemStack(TragicItems.MourningStar),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.BeastlyClaws),
                new ItemStack(TragicItems.BeastlyClaws),
                new ItemStack(TragicItems.BeastlyClaws),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.GuiltyThorn),
                new ItemStack(TragicItems.GuiltyThorn),
                new ItemStack(TragicItems.GuiltyThorn),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.NekoLauncher),
                new ItemStack(TragicItems.NekoLauncher),
                new ItemStack(TragicItems.NekoLauncher),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.ReaperScythe),
                new ItemStack(TragicItems.ReaperScythe),
                new ItemStack(TragicItems.ReaperScythe),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.CelestialJack),
                new ItemStack(TragicItems.CelestialJack),
                new ItemStack(TragicItems.CelestialJack),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.WitheringAxe),
                new ItemStack(TragicItems.WitheringAxe),
                new ItemStack(TragicItems.WitheringAxe),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.FrozenLightning),
                new ItemStack(TragicItems.FrozenLightning),
                new ItemStack(TragicItems.FrozenLightning),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.CelestialLongbow),
                new ItemStack(TragicItems.CelestialLongbow),
                new ItemStack(TragicItems.CelestialLongbow),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.CelestialAegis),
                new ItemStack(TragicItems.CelestialAegis),
                new ItemStack(TragicItems.CelestialAegis),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.Titan),
                new ItemStack(TragicItems.Titan),
                new ItemStack(TragicItems.Titan),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.Splinter),
                new ItemStack(TragicItems.Splinter),
                new ItemStack(TragicItems.Splinter),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.Butcher),
                new ItemStack(TragicItems.Butcher),
                new ItemStack(TragicItems.Butcher),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.Thardus),
                new ItemStack(TragicItems.Thardus),
                new ItemStack(TragicItems.Thardus),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.Paranoia),
                new ItemStack(TragicItems.Paranoia),
                new ItemStack(TragicItems.Paranoia),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.DragonFang),
                new ItemStack(TragicItems.DragonFang),
                new ItemStack(TragicItems.DragonFang),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.TungstenJack),
                new ItemStack(TragicItems.TungstenJack),
                new ItemStack(TragicItems.TungstenJack),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.SkullHelmet),
                new ItemStack(TragicItems.SkullHelmet),
                new ItemStack(TragicItems.SkullHelmet),
                "oreCharms"));
        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.SkullPlate),
                new ItemStack(TragicItems.SkullPlate),
                new ItemStack(TragicItems.SkullPlate),
                "oreCharms"));
        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.SkullLegs),
                new ItemStack(TragicItems.SkullLegs),
                new ItemStack(TragicItems.SkullLegs),
                "oreCharms"));
        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.SkullBoots),
                new ItemStack(TragicItems.SkullBoots),
                new ItemStack(TragicItems.SkullBoots),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.HuntersCap),
                new ItemStack(TragicItems.HuntersCap),
                new ItemStack(TragicItems.HuntersCap),
                "oreCharms"));
        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.HuntersTunic),
                new ItemStack(TragicItems.HuntersTunic),
                new ItemStack(TragicItems.HuntersTunic),
                "oreCharms"));
        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.HuntersLegs),
                new ItemStack(TragicItems.HuntersLegs),
                new ItemStack(TragicItems.HuntersLegs),
                "oreCharms"));
        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.HuntersBoots),
                new ItemStack(TragicItems.HuntersBoots),
                new ItemStack(TragicItems.HuntersBoots),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.MercuryHelm),
                new ItemStack(TragicItems.MercuryHelm),
                new ItemStack(TragicItems.MercuryHelm),
                "oreCharms"));
        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.MercuryPlate),
                new ItemStack(TragicItems.MercuryPlate),
                new ItemStack(TragicItems.MercuryPlate),
                "oreCharms"));
        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.MercuryLegs),
                new ItemStack(TragicItems.MercuryLegs),
                new ItemStack(TragicItems.MercuryLegs),
                "oreCharms"));
        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.MercuryBoots),
                new ItemStack(TragicItems.MercuryBoots),
                new ItemStack(TragicItems.MercuryBoots),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.TungstenHelm),
                new ItemStack(TragicItems.TungstenHelm),
                new ItemStack(TragicItems.TungstenHelm),
                "oreCharms"));
        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.TungstenPlate),
                new ItemStack(TragicItems.TungstenPlate),
                new ItemStack(TragicItems.TungstenPlate),
                "oreCharms"));
        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.TungstenLegs),
                new ItemStack(TragicItems.TungstenLegs),
                new ItemStack(TragicItems.TungstenLegs),
                "oreCharms"));
        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.TungstenBoots),
                new ItemStack(TragicItems.TungstenBoots),
                new ItemStack(TragicItems.TungstenBoots),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.LightHelm),
                new ItemStack(TragicItems.LightHelm),
                new ItemStack(TragicItems.LightHelm),
                "oreCharms"));
        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.LightPlate),
                new ItemStack(TragicItems.LightPlate),
                new ItemStack(TragicItems.LightPlate),
                "oreCharms"));
        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.LightLegs),
                new ItemStack(TragicItems.LightLegs),
                new ItemStack(TragicItems.LightLegs),
                "oreCharms"));
        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.LightBoots),
                new ItemStack(TragicItems.LightBoots),
                new ItemStack(TragicItems.LightBoots),
                "oreCharms"));

        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.DarkHelm),
                new ItemStack(TragicItems.DarkHelm),
                new ItemStack(TragicItems.DarkHelm),
                "oreCharms"));
        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.DarkPlate),
                new ItemStack(TragicItems.DarkPlate),
                new ItemStack(TragicItems.DarkPlate),
                "oreCharms"));
        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.DarkLegs),
                new ItemStack(TragicItems.DarkLegs),
                new ItemStack(TragicItems.DarkLegs),
                "oreCharms"));
        GameRegistry.addRecipe(
            new RecipeWeapons(
                new ItemStack(TragicItems.DarkBoots),
                new ItemStack(TragicItems.DarkBoots),
                new ItemStack(TragicItems.DarkBoots),
                "oreCharms"));
    }
}
