package tragicneko.tragicmc.util;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCore;
import tragicneko.tragicmc.entity.boss.*;
import tragicneko.tragicmc.entity.miniboss.*;
import tragicneko.tragicmc.entity.mob.*;

import java.util.HashMap;
import java.util.Map;

import static tragicneko.tragicmc.TragicMC.rand;

public class EntityDropHelper {

    private static Map<Class<? extends EntityLivingBase>, DropEntry> entityDrops = new HashMap();
    private static EntityDrop[] luxuryDrops = new EntityDrop[] { new EntityDrop(60, Items.diamond),
        new EntityDrop(45, Items.emerald), new EntityDrop(75, Items.iron_ingot), new EntityDrop(60, Items.gold_ingot),
        new EntityDrop(75, Items.gold_nugget), new EntityDrop(20, TragicItems.Sapphire),
        new EntityDrop(25, TragicItems.Ruby), new EntityDrop(65, TragicItems.Tungsten),
        new EntityDrop(75, TragicItems.RedMercury), new EntityDrop(10, Blocks.gold_block),
        new EntityDrop(15, Blocks.iron_block), new EntityDrop(10, Blocks.diamond_block),
        new EntityDrop(5, Blocks.emerald_block), new EntityDrop(5, TragicItems.AmuletRelease),
        new EntityDrop(5, TragicItems.AwakeningStone), new EntityDrop(5, TragicItems.DoomConsume),
        new EntityDrop(15, TragicItems.CooldownDefuse), new EntityDrop(1, getEpicWeaponStacks()),
        new EntityDrop(
            8,
            new ItemStack(TragicBlocks.CompactOre, 1, 0),
            new ItemStack(TragicBlocks.CompactOre, 1, 1),
            new ItemStack(TragicBlocks.CompactOre, 1, 2),
            new ItemStack(TragicBlocks.CompactOre, 1, 3),
            new ItemStack(TragicBlocks.CompactOre, 1, 4)),
        new EntityDrop(5, TragicItems.Talisman), new EntityDrop(25, getDoomsdayScrollStacks()),
        new EntityDrop(1, new ItemStack(TragicItems.SilentHellraiser)), new EntityDrop(1, getAmulets(false)),
        new EntityDrop(3, Blocks.beacon) };

    public static void fill() {
        // Normal Mob Drops
        addToDropList(
            EntityJabba.class,
            new EntityDrop[][] { { new EntityDrop(25, Items.magma_cream), new EntityDrop(15, TragicItems.Ash) },
                { new EntityDrop(5, TragicItems.FireOrb), new EntityDrop(5, TragicItems.RedMercury) } },
            new EntityDrop[][] {
                { new EntityDrop(25, Items.slime_ball), new EntityDrop(10, Items.magma_cream),
                    new EntityDrop(15, TragicItems.InterestingResin) },
                { new EntityDrop(5, new ItemStack(TragicItems.Projectile, 1, 11)) } });

        addToDropList(
            EntityPlague.class,
            new EntityDrop[][] { { new EntityDrop(25, TragicItems.Ash), new EntityDrop(5, TragicItems.DarkParticles),
                new EntityDrop(15, TragicItems.Ectoplasm) }, { new EntityDrop(5, TragicItems.DarkParticles) } });

        addToDropList(
            EntityGragul.class,
            new EntityDrop[][] {
                { new EntityDrop(15, TragicItems.Ash), new EntityDrop(25, Items.clay_ball),
                    new EntityDrop(5, Items.coal), new EntityDrop(10, TragicItems.SoulExcess) },
                { new EntityDrop(25, Items.coal), new EntityDrop(5, TragicItems.GravityOrb),
                    new EntityDrop(15, TragicItems.Ectoplasm) } });

        addToDropList(
            EntityMinotaur.class,
            new EntityDrop[][] {
                { new EntityDrop(25, Items.beef), new EntityDrop(5, Items.leather),
                    new EntityDrop(5, TragicItems.Horn) },
                { new EntityDrop(10, TragicItems.Horn), new EntityDrop(5, TragicItems.ToughLeather) } });

        addToDropList(
            EntityInkling.class,
            new EntityDrop[][] {
                { new EntityDrop(25, TragicItems.Ash), new EntityDrop(5, TragicItems.DarkParticles),
                    new EntityDrop(15, TragicItems.Ectoplasm) },
                { new EntityDrop(25, TragicItems.DarkParticles), new EntityDrop(5, Items.coal) } });

        addToDropList(
            EntityRagr.class,
            new EntityDrop[][] {
                { new EntityDrop(25, TragicItems.IcyFur), new EntityDrop(10, TragicItems.CrushedIce),
                    new EntityDrop(15, Items.snowball), new EntityDrop(5, Items.fish) },
                { new EntityDrop(25, TragicItems.ToughLeather), new EntityDrop(15, TragicItems.IceOrb),
                    new EntityDrop(10, TragicItems.Sushi), new EntityDrop(5, TragicItems.GoldenSushi) } },
            new EntityDrop[][] { { new EntityDrop(10, TragicItems.ToughLeather),
                new EntityDrop(25, new ItemStack(TragicItems.Projectile, 1, 1)), new EntityDrop(3, TragicItems.Ash) },
                { new EntityDrop(25, TragicItems.ToughLeather), new EntityDrop(25, TragicItems.FireOrb),
                    new EntityDrop(10, TragicItems.Sushi), new EntityDrop(5, TragicItems.GoldenSushi) } });

        addToDropList(
            EntityPumpkinhead.class,
            new EntityDrop[][] {
                { new EntityDrop(25, Items.pumpkin_pie), new EntityDrop(5, Items.pumpkin_seeds),
                    new EntityDrop(10, Blocks.pumpkin), new EntityDrop(15, TragicItems.InterestingResin),
                    new EntityDrop(5, TragicItems.SoulExcess) },
                { new EntityDrop(15, Blocks.lit_pumpkin), new EntityDrop(15, TragicBlocks.Candle),
                    new EntityDrop(5, TragicItems.DarkParticles), new EntityDrop(25, Blocks.torch) } });

        addToDropList(
            EntityTragicNeko.class,
            new EntityDrop[][] {
                { new EntityDrop(15, Items.iron_ingot), new EntityDrop(25, Items.gunpowder),
                    new EntityDrop(5, Blocks.tnt) },
                { new EntityDrop(15, TragicItems.GoldenSushi),
                    new EntityDrop(5, new ItemStack(Items.golden_apple, 1, 1), new ItemStack(Items.golden_apple, 1, 0)),
                    new EntityDrop(25, Items.diamond),
                    new EntityDrop(
                        5,
                        new ItemStack(TragicItems.Projectile, 1, 5),
                        new ItemStack(TragicItems.Projectile, 1, 6),
                        new ItemStack(TragicItems.Projectile, 1, 7),
                        new ItemStack(TragicItems.Projectile, 1, 8)) } });

        addToDropList(
            EntityTox.class,
            new EntityDrop[][] {
                { new EntityDrop(25, new ItemStack(TragicItems.Projectile, 1, 11)), new EntityDrop(15, Blocks.vine),
                    new EntityDrop(10, TragicItems.Thorns),
                    new EntityDrop(
                        5,
                        new ItemStack(Items.wheat_seeds),
                        new ItemStack(Items.pumpkin_seeds),
                        new ItemStack(Items.melon_seeds)) },
                { new EntityDrop(15, TragicItems.Honeydrop), new EntityDrop(25, Items.slime_ball),
                    new EntityDrop(15, TragicItems.InterestingResin) } },
            new EntityDrop[][] {
                { new EntityDrop(25, new ItemStack(TragicItems.Projectile, 1, 11)),
                    new EntityDrop(25, TragicBlocks.Glowvine), new EntityDrop(5, TragicItems.Thorns),
                    new EntityDrop(
                        10,
                        new ItemStack(Items.wheat_seeds),
                        new ItemStack(Items.pumpkin_seeds),
                        new ItemStack(Items.melon_seeds)),
                    new EntityDrop(15, TragicItems.InterestingResin) },
                { new EntityDrop(15, TragicItems.SkyFruit), new EntityDrop(25, Items.slime_ball) } });

        addToDropList(
            EntityCryse.class,
            new EntityDrop[][] {
                { new EntityDrop(25, TragicItems.CrushedIce), new EntityDrop(15, Items.snowball),
                    new EntityDrop(5, TragicItems.IceOrb) },
                { new EntityDrop(5, TragicItems.IceOrb), new EntityDrop(15, TragicItems.EnchantedTears) } },
            new EntityDrop[][] { { new EntityDrop(5, new ItemStack(TragicBlocks.StarCrystal, 15)),
                new EntityDrop(25, Items.glowstone_dust) }, { new EntityDrop(5, TragicBlocks.StarCrystal) } });

        addToDropList(
            EntityPirah.class,
            new EntityDrop[][] {
                { new EntityDrop(
                    25,
                    new ItemStack(Items.fish, 1, 0),
                    new ItemStack(Items.fish, 1, 1),
                    new ItemStack(Items.fish, 1, 2),
                    new ItemStack(Items.fish, 1, 3)), new EntityDrop(25, Items.dye),
                    new EntityDrop(5, TragicItems.EnchantedTears), new EntityDrop(15, TragicItems.Tentacle) },
                { new EntityDrop(5, TragicItems.EnchantedTears), new EntityDrop(15, TragicItems.AquaOrb) } },
            new EntityDrop[][] {
                { new EntityDrop(
                    25,
                    new ItemStack(Items.fish, 1, 0),
                    new ItemStack(Items.fish, 1, 1),
                    new ItemStack(Items.fish, 1, 2),
                    new ItemStack(Items.fish, 1, 3)), new EntityDrop(25, Items.dye),
                    new EntityDrop(5, TragicItems.EnchantedTears), new EntityDrop(25, TragicItems.Tentacle) },
                { new EntityDrop(5, TragicItems.EnchantedTears), new EntityDrop(15, TragicItems.AquaOrb) } });

        addToDropList(
            EntityNorVox.class,
            new EntityDrop[][] {
                { new EntityDrop(25, TragicItems.Projectile), new EntityDrop(10, Items.glowstone_dust),
                    new EntityDrop(5, TragicItems.ObsidianOrb), new EntityDrop(10, Blocks.stone) },
                { new EntityDrop(15, Items.emerald), new EntityDrop(15, Items.diamond),
                    new EntityDrop(25, TragicItems.Tungsten), new EntityDrop(25, TragicItems.RedMercury),
                    new EntityDrop(5, TragicItems.Ruby), new EntityDrop(5, TragicItems.Sapphire),
                    new EntityDrop(15, Items.experience_bottle) } },
            new EntityDrop[][] {
                { new EntityDrop(25, new ItemStack(TragicBlocks.StarCrystal, 1, 15)),
                    new EntityDrop(10, Items.glowstone_dust), new EntityDrop(5, TragicItems.ObsidianOrb) },
                { new EntityDrop(15, Items.emerald), new EntityDrop(15, Items.diamond),
                    new EntityDrop(25, TragicItems.Tungsten), new EntityDrop(25, TragicItems.RedMercury),
                    new EntityDrop(5, TragicItems.Ruby), new EntityDrop(5, TragicItems.Sapphire),
                    new EntityDrop(15, Items.experience_bottle) } });

        addToDropList(
            EntityStin.class,
            new EntityDrop[][] {
                { new EntityDrop(25, TragicItems.Ash), new EntityDrop(5, TragicItems.DarkParticles),
                    new EntityDrop(15, TragicItems.Chitin) },
                { new EntityDrop(25, TragicItems.DarkParticles), new EntityDrop(5, Items.ender_pearl),
                    new EntityDrop(5, Items.ender_eye) } },
            new EntityDrop[][] { { new EntityDrop(25, TragicItems.Ash), new EntityDrop(5, Items.coal),
                new EntityDrop(3, TragicItems.Chitin) }, { new EntityDrop(5, Items.ender_pearl) } });

        addToDropList(
            EntityWisp.class,
            new EntityDrop[][] {
                { new EntityDrop(25, Items.glowstone_dust), new EntityDrop(10, TragicItems.WispParticles),
                    new EntityDrop(5, TragicItems.SoulExcess) },
                { new EntityDrop(5, TragicItems.WispParticles), new EntityDrop(5, TragicItems.SoulExcess) } });

        addToDropList(
            EntityAbomination.class,
            new EntityDrop[][] {
                { new EntityDrop(25, TragicItems.CrushedIce), new EntityDrop(15, Items.fish),
                    new EntityDrop(10, Items.snowball) },
                { new EntityDrop(25, TragicItems.IcyFur), new EntityDrop(5, TragicItems.IceOrb),
                    new EntityDrop(15, TragicItems.EnchantedTears) } });

        addToDropList(
            EntityErkel.class,
            new EntityDrop[][] {
                { new EntityDrop(25, Blocks.brown_mushroom), new EntityDrop(15, Blocks.red_mushroom),
                    new EntityDrop(5, new ItemStack(TragicItems.Projectile, 1, 11)) },
                { new EntityDrop(5, Items.mushroom_stew),
                    new EntityDrop(25, new ItemStack(TragicItems.Projectile, 1, 11)),
                    new EntityDrop(5, Items.nether_wart), new EntityDrop(15, TragicItems.InterestingResin) } });

        addToDropList(
            EntitySirv.class,
            new EntityDrop[][] { { new EntityDrop(25, Items.clay_ball), new EntityDrop(5, Blocks.clay) },
                { new EntityDrop(25, Blocks.clay), new EntityDrop(5, Blocks.brick_block) } });

        addToDropList(
            EntityPsygote.class,
            new EntityDrop[][] {
                { new EntityDrop(5, getDoomsdayScrollStacks()), new EntityDrop(25, TragicItems.DarkParticles),
                    new EntityDrop(15, Items.ender_pearl), new EntityDrop(15, TragicItems.Ectoplasm),
                    new EntityDrop(5, TragicItems.SoulExcess) },
                { new EntityDrop(15, getDoomsdayScrollStacks()), new EntityDrop(25, Items.ender_eye),
                    new EntityDrop(5, TragicItems.DimensionalKey) } });

        addToDropList(
            EntityNanoSwarm.class,
            new EntityDrop[][] { { new EntityDrop(25, TragicItems.NanoBots) },
                { new EntityDrop(25, TragicItems.NanoBots), new EntityDrop(15, TragicItems.CatalyticCompound) } });

        addToDropList(
            EntityHunter.class,
            new EntityDrop[][] { { new EntityDrop(25, TragicItems.NanoBots) },
                { new EntityDrop(25, TragicItems.NanoBots), new EntityDrop(5, Items.iron_ingot),
                    new EntityDrop(15, TragicItems.CatalyticCompound) } });

        addToDropList(
            EntityHarvester.class,
            new EntityDrop[][] {
                { new EntityDrop(25, TragicItems.NanoBots), new EntityDrop(5, TragicItems.SynapseCrystal),
                    new EntityDrop(10, Items.iron_ingot), new EntityDrop(5, Items.diamond) },
                { new EntityDrop(25, TragicItems.NanoBots), new EntityDrop(10, Items.iron_ingot),
                    new EntityDrop(5, TragicItems.SynapseCrystal), new EntityDrop(5, TragicItems.SynapseLink),
                    new EntityDrop(5, Items.diamond), new EntityDrop(15, TragicItems.CatalyticCompound) } });

        addToDropList(
            EntityLockbot.class,
            new EntityDrop[][] {
                { new EntityDrop(25, TragicItems.NanoBots), new EntityDrop(10, Items.iron_ingot),
                    new EntityDrop(10, TragicItems.GravityOrb) },
                { new EntityDrop(25, TragicItems.NanoBots), new EntityDrop(10, Items.iron_ingot),
                    new EntityDrop(5, TragicItems.SynapseCrystal), new EntityDrop(5, TragicItems.GravityOrb),
                    new EntityDrop(15, TragicItems.CatalyticCompound) } });

        addToDropList(
            EntityIre.class,
            new EntityDrop[][] {
                { new EntityDrop(15, TragicItems.LightParticles), new EntityDrop(10, Items.glowstone_dust),
                    new EntityDrop(5, TragicItems.IreNode) },
                { new EntityDrop(10, TragicItems.LightParticles), new EntityDrop(5, Items.quartz),
                    new EntityDrop(3, TragicItems.WispParticles), new EntityDrop(5, TragicItems.IreNode) } });

        addToDropList(
            EntityArchangel.class,
            new EntityDrop[][] {
                { new EntityDrop(15, TragicItems.LightParticles), new EntityDrop(5, TragicItems.Tungsten) },
                { new EntityDrop(25, TragicItems.LightParticles),
                    new EntityDrop(
                        10,
                        new ItemStack[] { new ItemStack(Items.diamond), new ItemStack(Items.emerald),
                            new ItemStack(TragicItems.Ruby), new ItemStack(TragicItems.Sapphire) }),
                    new EntityDrop(5, TragicItems.ArchangelFeather) } });

        addToDropList(
            EntityFusea.class,
            new EntityDrop[][] { { new EntityDrop(15, Items.gunpowder), new EntityDrop(5, Items.redstone) },
                { new EntityDrop(15, Items.gunpowder), new EntityDrop(5, TragicItems.UnstableIsotope),
                    new EntityDrop(5, Items.redstone), new EntityDrop(15, TragicItems.CatalyticCompound) } });

        addToDropList(
            EntityRanmas.class,
            new EntityDrop[][] {
                { new EntityDrop(25, TragicBlocks.Crystal), new EntityDrop(15, TragicItems.CatalyticCompound) },
                { new EntityDrop(25, TragicBlocks.Crystal) } });

        addToDropList(
            EntityParasmite.class,
            new EntityDrop[][] {
                { new EntityDrop(25, TragicItems.DarkParticles), new EntityDrop(15, TragicItems.BoneMarrow) },
                { new EntityDrop(25, TragicItems.BoneMarrow), new EntityDrop(5, TragicItems.BloodSacrifice),
                    new EntityDrop(5, TragicItems.NourishmentSacrifice) } });

        // addToDropList(EntityBlist.class, new EntityDrop[][] {{}, {}}); //TODO setup drops for the Blist

        // Mini-Boss drops
        addToDropList(
            EntityJarra.class,
            new EntityDrop[][] {
                { new EntityDrop(15, new ItemStack(TragicItems.Projectile, 1, 11)),
                    new EntityDrop(25, TragicItems.Thorns), new EntityDrop(5, Items.magma_cream),
                    new EntityDrop(15, TragicItems.InterestingResin) },
                { new EntityDrop(15, new ItemStack(TragicItems.Projectile, 1, 11)),
                    new EntityDrop(10, TragicItems.Ectoplasm), new EntityDrop(5, TragicItems.Gloopii) } });

        addToDropList(
            EntityKragul.class,
            new EntityDrop[][] {
                { new EntityDrop(15, Items.redstone), new EntityDrop(25, Items.clay_ball),
                    new EntityDrop(5, Blocks.clay), new EntityDrop(25, TragicItems.Ectoplasm),
                    new EntityDrop(10, TragicItems.SoulExcess) },
                { new EntityDrop(5, Items.coal), new EntityDrop(15, TragicItems.GravityOrb),
                    new EntityDrop(5, Blocks.redstone_block), new EntityDrop(5, TragicItems.SoulExcess) } });

        addToDropList(
            EntityMagmox.class,
            new EntityDrop[][] {
                { new EntityDrop(15, Items.magma_cream), new EntityDrop(35, Items.blaze_powder),
                    new EntityDrop(5, Items.blaze_rod),
                    new EntityDrop(
                        5,
                        new ItemStack(TragicItems.Honeydrop),
                        new ItemStack(TragicItems.Deathglow),
                        new ItemStack(TragicItems.Gloopii)),
                    new EntityDrop(5, Items.nether_wart) },
                { new EntityDrop(
                    15,
                    new ItemStack(TragicItems.Honeydrop),
                    new ItemStack(TragicItems.Deathglow),
                    new ItemStack(TragicItems.Gloopii)), new EntityDrop(5, TragicItems.FireOrb) } });

        addToDropList(
            EntityMegaCryse.class,
            new EntityDrop[][] {
                { new EntityDrop(25, TragicItems.CrushedIce), new EntityDrop(15, TragicItems.IceOrb),
                    new EntityDrop(5, Blocks.ice) },
                { new EntityDrop(25, TragicItems.IceOrb), new EntityDrop(15, Blocks.ice),
                    new EntityDrop(5, Blocks.packed_ice), new EntityDrop(25, TragicItems.EnchantedTears) } });

        addToDropList(
            EntityGreaterStin.class,
            new EntityDrop[][] {
                { new EntityDrop(25, Items.ender_pearl), new EntityDrop(15, TragicItems.DarkParticles),
                    new EntityDrop(5, Blocks.coal_block) },
                { new EntityDrop(25, TragicItems.DarkParticles), new EntityDrop(15, Items.ender_eye),
                    new EntityDrop(5, TragicItems.StinHorn) } });

        addToDropList(
            EntityStinKing.class,
            new EntityDrop[][] {
                { new EntityDrop(5, TragicItems.DarkIngot), new EntityDrop(25, TragicItems.DarkParticles),
                    new EntityDrop(5, Blocks.obsidian), new EntityDrop(15, Items.ender_pearl),
                    new EntityDrop(5, TragicItems.Chitin) },
                { new EntityDrop(25, TragicItems.StinHorn), new EntityDrop(5, TragicItems.DarkIngot),
                    new EntityDrop(10, Items.ender_eye) } });

        addToDropList(
            EntityStinQueen.class,
            new EntityDrop[][] {
                { new EntityDrop(5, TragicItems.DarkIngot), new EntityDrop(25, TragicItems.DarkParticles),
                    new EntityDrop(5, Blocks.web), new EntityDrop(15, Items.string),
                    new EntityDrop(5, TragicItems.Chitin) },
                { new EntityDrop(25, Blocks.web), new EntityDrop(5, TragicItems.DarkIngot),
                    new EntityDrop(10, TragicItems.WovenSilk) } });

        addToDropList(
            EntityVoxStellarum.class,
            new EntityDrop[][] {
                { new EntityDrop(25, new ItemStack(TragicBlocks.StarCrystal, 1, 15)),
                    new EntityDrop(10, Items.glowstone_dust), new EntityDrop(5, TragicItems.ObsidianOrb) },
                { new EntityDrop(15, Items.emerald), new EntityDrop(15, Items.diamond),
                    new EntityDrop(5, TragicItems.Tungsten), new EntityDrop(5, TragicItems.RedMercury),
                    new EntityDrop(10, TragicItems.Ruby), new EntityDrop(10, TragicItems.Sapphire),
                    new EntityDrop(15, Items.experience_bottle), new EntityDrop(5, getLuxuryBlocks(true)) } });

        addToDropList(
            EntityAegar.class,
            new EntityDrop[][] {
                { new EntityDrop(5, getLuxuryBlocks(true)), new EntityDrop(25, Items.emerald),
                    new EntityDrop(25, Items.diamond), new EntityDrop(15, TragicItems.Ruby),
                    new EntityDrop(15, TragicItems.Sapphire), new EntityDrop(5, getOreCharms()) },
                { new EntityDrop(10, getLuxuryBlocks(true)), new EntityDrop(5, getOreCharms()),
                    new EntityDrop(25, TragicItems.SynapseCrystal),
                    new EntityDrop(15, TragicItems.CatalyticCompound) } });

        addToDropList(
            EntityVolatileFusea.class,
            new EntityDrop[][] {
                { new EntityDrop(15, Items.gunpowder), new EntityDrop(15, Items.redstone),
                    new EntityDrop(25, TragicItems.UnstableIsotope) },
                { new EntityDrop(5, Items.gunpowder), new EntityDrop(15, TragicItems.UnstableIsotope),
                    new EntityDrop(10, Items.redstone), new EntityDrop(3, Blocks.redstone_block),
                    new EntityDrop(15, TragicItems.CatalyticCompound) } });

        // addToDropList(EntityAggro.class, new EntityDrop[][] {{new EntityDrop(5, Items.nether_wart)}, {new
        // EntityDrop(15, TragicItems.FireOrb)}}); //TODO setup actual drops for the Aggro

        // Boss Drops
        addToDropList(
            EntityApis.class,
            new EntityDrop[][] { { new EntityDrop(25, TragicItems.PureLight) },
                { new EntityDrop(25, TragicItems.PureLight), new EntityDrop(20, TragicItems.Horn),
                    new EntityDrop(15, Items.leather), new EntityDrop(5, TragicItems.ToughLeather),
                    new EntityDrop(20, TragicItems.LightParticles) } });

        addToDropList(
            EntityDeathReaper.class,
            new EntityDrop[][] { { new EntityDrop(25, TragicItems.DeathlyHallow) },
                { new EntityDrop(25, TragicItems.DeathlyHallow), new EntityDrop(15, TragicItems.BoneMarrow),
                    new EntityDrop(15, TragicItems.DarkParticles), new EntityDrop(3, TragicItems.FireOrb),
                    new EntityDrop(20, Items.bone), new EntityDrop(5, Items.blaze_rod),
                    new EntityDrop(5, Items.blaze_powder), new EntityDrop(25, TragicItems.SoulExcess) } });

        addToDropList(
            EntityKitsune.class,
            new EntityDrop[][] { { new EntityDrop(25, TragicItems.KitsuneTail) },
                { new EntityDrop(25, TragicItems.FireOrb), new EntityDrop(15, Items.blaze_powder),
                    new EntityDrop(15, Items.blaze_rod), new EntityDrop(5, Items.fire_charge),
                    new EntityDrop(3, Items.ghast_tear), new EntityDrop(10, TragicItems.Ash) } });

        addToDropList(
            EntityPolaris.class,
            new EntityDrop[][] { { new EntityDrop(25, TragicItems.StarPieces) },
                { new EntityDrop(15, Items.glowstone_dust), new EntityDrop(10, getInvisPotions()),
                    new EntityDrop(15, TragicItems.SoulExcess) } });

        addToDropList(
            EntityTimeController.class,
            new EntityDrop[][] { { new EntityDrop(25, TragicItems.TimeEssence) },
                { new EntityDrop(25, Items.clock), new EntityDrop(15, Items.redstone), new EntityDrop(5, Items.emerald),
                    new EntityDrop(5, Items.diamond), new EntityDrop(3, TragicItems.CelestialSteel),
                    new EntityDrop(5, Blocks.redstone_block) } });

        addToDropList(
            EntityYeti.class,
            new EntityDrop[][] { { new EntityDrop(25, TragicItems.EmpariahClaw) },
                { new EntityDrop(25, TragicItems.IcyFur), new EntityDrop(15, TragicItems.CrushedIce),
                    new EntityDrop(10, Items.fish), new EntityDrop(5, TragicItems.IceOrb) } });

        addToDropList(
            EntityEnyvil.class,
            new EntityDrop[][] { { new EntityDrop(25, TragicItems.PureDarkness) },
                { new EntityDrop(25, TragicItems.DarkParticles), new EntityDrop(5, TragicItems.QuicksilverIngot),
                    new EntityDrop(10, TragicItems.BoneMarrow), new EntityDrop(5, TragicItems.LightningOrb),
                    new EntityDrop(25, TragicItems.Chitin) } });

        addToDropList(
            EntityClaymation.class,
            new EntityDrop[][] { { new EntityDrop(25, TragicItems.LivingClay) },
                { new EntityDrop(5, TragicItems.Talisman), new EntityDrop(10, TragicBlocks.Wax),
                    new EntityDrop(5, TragicItems.EnchantedTears), new EntityDrop(15, Items.clay_ball),
                    new EntityDrop(5, Blocks.clay) } });

        // Alpha Drops
        addToDropList(
            EntityOverlordCore.class,
            new EntityDrop[][] { { new EntityDrop(25, TragicItems.CorruptedEye) },
                { new EntityDrop(5, getDoomsdayScrollStacks()), new EntityDrop(5, TragicItems.CelestialSteel),
                    new EntityDrop(25, TragicItems.SynapseCrystal), new EntityDrop(5, TragicItems.SynapseLink),
                    new EntityDrop(5, TragicItems.CorruptedEssence), new EntityDrop(20, TragicBlocks.SynapseCore),
                    new EntityDrop(10, TragicItems.CorruptedEye), new EntityDrop(3, getAmulets(true)),
                    new EntityDrop(1, TragicItems.CatalyticCompound), new EntityDrop(1, TragicItems.Chitin) } });
    }

    public static void addToDropList(DropEntry entry) {
        if (entityDrops.containsKey(entry.getEntityClass()))
            TragicMC.logWarning("Duplicate drop mapping for " + entry.getEntityClass());
        entityDrops.put(entry.getEntityClass(), entry);
    }

    public static void addToDropList(Class<? extends EntityLivingBase> clazz, EntityDrop[][] drops) {
        if (entityDrops.containsKey(clazz)) TragicMC.logWarning("Duplicate drop mapping for " + clazz);
        entityDrops.put(clazz, new DropEntry(clazz, drops));
    }

    public static void addToDropList(Class<? extends EntityLivingBase> clazz, EntityDrop[][] drops,
        EntityDrop[][] drops2) {
        if (entityDrops.containsKey(clazz)) TragicMC.logWarning("Duplicate drop mapping for " + clazz);
        entityDrops.put(clazz, new DropEntry(clazz, drops, drops2));
    }

    private static ItemStack[] getDoomsdayScrollStacks() {
        ItemStack[] stack = new ItemStack[Doomsday.doomsdayNames.length];
        for (int i = 0; i < Doomsday.doomsdayNames.length; i++) {
            if (Doomsday.doomsdayList[i] != null) stack[i] = new ItemStack(TragicItems.DoomsdayScroll, 1, i);
        }
        return stack;
    }

    private static ItemStack[] getEpicWeaponStacks() {
        ItemStack[] stack = new ItemStack[6];

        stack[0] = new ItemStack(TragicItems.Titan);
        stack[1] = new ItemStack(TragicItems.Thardus);
        stack[2] = new ItemStack(TragicItems.Butcher);
        stack[3] = new ItemStack(TragicItems.DragonFang);
        stack[4] = new ItemStack(TragicItems.Paranoia);
        stack[5] = new ItemStack(TragicItems.Splinter);

        return stack;
    }

    /**
     * Get all of the luxury blocks in an ItemStack array for ease, flag toggles whether modded luxury blocks are
     * included
     */
    private static ItemStack[] getLuxuryBlocks(boolean flag) {
        ItemStack[] stack = new ItemStack[13];

        stack[0] = new ItemStack(Blocks.diamond_block);
        stack[1] = new ItemStack(Blocks.emerald_block);
        stack[2] = new ItemStack(Blocks.coal_block);
        stack[3] = new ItemStack(Blocks.gold_block);
        stack[4] = new ItemStack(Blocks.iron_block);
        stack[5] = new ItemStack(Blocks.lapis_block);
        stack[6] = new ItemStack(Blocks.redstone_block);

        if (flag) {
            for (int i = 0; i < 5; i++) stack[7 + i] = new ItemStack(TragicBlocks.CompactOre, 1, i);
        }

        return stack;
    }

    private static ItemStack[] getOreCharms() {
        ItemStack[] stack = new ItemStack[4];
        stack[0] = new ItemStack(TragicItems.RubyCharm);
        stack[1] = new ItemStack(TragicItems.SapphireCharm);
        stack[2] = new ItemStack(TragicItems.DiamondCharm);
        stack[3] = new ItemStack(TragicItems.EmeraldCharm);
        return stack;
    }

    private static ItemStack[] getInvisPotions() {
        ItemStack[] stack = new ItemStack[4];
        stack[0] = new ItemStack(Items.potionitem, 1, 16446);
        stack[1] = new ItemStack(Items.potionitem, 1, 78);
        stack[2] = new ItemStack(Items.potionitem, 1, 46);
        stack[3] = new ItemStack(Items.potionitem, 1, 16478);
        return stack;
    }

    private static ItemStack[] getAmulets(boolean flag) {
        ItemStack[] stack = new ItemStack[27];

        if (flag) stack[0] = new ItemStack(TragicItems.KitsuneAmulet);
        stack[1] = new ItemStack(TragicItems.YetiAmulet);
        stack[2] = new ItemStack(TragicItems.PeaceAmulet);
        stack[3] = new ItemStack(TragicItems.ClaymationAmulet);
        stack[4] = new ItemStack(TragicItems.ChickenAmulet);
        if (flag) stack[5] = new ItemStack(TragicItems.MartyrAmulet);
        if (flag) stack[6] = new ItemStack(TragicItems.PiercingAmulet);
        stack[7] = new ItemStack(TragicItems.BlacksmithAmulet);
        if (flag) stack[8] = new ItemStack(TragicItems.ApisAmulet);
        stack[9] = new ItemStack(TragicItems.CreeperAmulet);
        stack[10] = new ItemStack(TragicItems.ZombieAmulet);
        stack[11] = new ItemStack(TragicItems.SkeletonAmulet);
        if (flag) stack[12] = new ItemStack(TragicItems.SunkenAmulet);
        stack[13] = new ItemStack(TragicItems.IceAmulet);
        stack[14] = new ItemStack(TragicItems.SnowGolemAmulet);
        stack[15] = new ItemStack(TragicItems.IronGolemAmulet);
        if (flag) stack[16] = new ItemStack(TragicItems.EndermanAmulet);

        stack[17] = new ItemStack(TragicItems.SpiderAmulet);
        stack[18] = new ItemStack(TragicItems.StinAmulet);
        if (flag) stack[19] = new ItemStack(TragicItems.PolarisAmulet);
        if (flag) stack[20] = new ItemStack(TragicItems.LightningAmulet);
        if (flag) stack[21] = new ItemStack(TragicItems.ConsumptionAmulet);
        stack[22] = new ItemStack(TragicItems.SupernaturalAmulet);
        if (flag) stack[23] = new ItemStack(TragicItems.UndeadAmulet);
        if (flag) stack[24] = new ItemStack(TragicItems.EnderDragonAmulet);
        stack[25] = new ItemStack(TragicItems.FuseaAmulet);
        stack[26] = new ItemStack(TragicItems.LuckAmulet);
        return stack;
    }

    public static ItemStack getRandomDropFromEntity(Class clazz) {
        try {
            return entityDrops.containsKey(clazz) ? entityDrops.get(clazz)
                .getRandomDropFromEntity(rand.nextBoolean()) : null;
        } catch (Exception e) {
            TragicMC.logError("There was an error retrieving a mapping for an entity's drops", e);
            return null;
        }
    }

    /**
     * Set flag to true for common, false for rare
     *
     * @param clazz
     * @param flag
     * @return
     */
    public static ItemStack getDropFromEntity(Class clazz, boolean flag) {
        try {
            return entityDrops.containsKey(clazz) ? entityDrops.get(clazz)
                .getRandomDropFromEntity(flag)
                .copy() : null;
        } catch (Exception e) {
            TragicMC.logError("There was an error retrieving a mapping for an entity's drops", e);
            return null;
        }
    }

    public static ItemStack getRandomDropFromVariant(Class clazz) {
        try {
            return entityDrops.containsKey(clazz) ? entityDrops.get(clazz)
                .getRandomDropFromVariant(rand.nextBoolean())
                .copy() : null;
        } catch (Exception e) {
            TragicMC.logError("There was an error retrieving a mapping for an entity's drops", e);
            return null;
        }
    }

    /**
     * Set flag to true for common, false for rare
     *
     * @param clazz
     * @param flag
     * @return
     */
    public static ItemStack getDropFromVariant(Class clazz, boolean flag) {
        try {
            return entityDrops.containsKey(clazz) ? entityDrops.get(clazz)
                .getRandomDropFromVariant(flag) : null;
        } catch (Exception e) {
            TragicMC.logError("There was an error retrieving a mapping for an entity's drops", e);
            return null;
        }
    }

    public static ItemStack getLuxuryDropForBoss() {
        try {
            return ((EntityDrop) WeightedRandom.getRandomItem(rand, luxuryDrops)).getStack()
                .copy();
        } catch (Exception e) {
            TragicMC.logError("There was an error retrieving a mapping for an entity's drops", e);
            return null;
        }
    }

    private static class DropEntry {

        private final Class entityClazz;

        private EntityDrop[] rareDrops;
        private EntityDrop[] commonDrops;

        private EntityDrop[] variantRare;
        private EntityDrop[] variantCommon;

        public DropEntry(Class<? extends EntityLivingBase> clazz, EntityDrop[][] drops) {
            this.entityClazz = clazz;
            this.commonDrops = drops[0];
            this.rareDrops = drops[1];
        }

        public DropEntry(Class clazz, EntityDrop[][] drops, EntityDrop[][] drops2) {
            this(clazz, drops);
            this.variantCommon = drops2[0];
            this.variantRare = drops2[1];
        }

        public Class getEntityClass() {
            return this.entityClazz;
        }

        /**
         * Set flag to true for common drops, false for rare
         *
         * @param flag
         * @return
         */
        public ItemStack getRandomDropFromEntity(boolean flag) {
            return flag ? this.getCommonDropFromEntity() : this.getRareDropFromEntity();
        }

        private ItemStack getCommonDropFromEntity() {
            try {
                return ((EntityDrop) WeightedRandom.getRandomItem(rand, this.commonDrops)).getStack();
            } catch (Exception e) {
                TragicMC.logError("Error doing an entity's common drops", e);
                return null;
            }
        }

        private ItemStack getRareDropFromEntity() {
            try {
                return ((EntityDrop) WeightedRandom.getRandomItem(rand, this.rareDrops)).getStack();
            } catch (Exception e) {
                TragicMC.logError("Error doing an entity's rare drops", e);
                return null;
            }
        }

        /**
         * Set flag to true for common drops, false for rare, this is for variants
         *
         * @param flag
         * @return
         */
        public ItemStack getRandomDropFromVariant(boolean flag) {
            return flag ? this.getCommonDropFromVariant() : this.getRareDropFromVariant();
        }

        private ItemStack getCommonDropFromVariant() {
            try {
                return ((EntityDrop) WeightedRandom.getRandomItem(rand, this.variantCommon)).getStack();
            } catch (Exception e) {
                TragicMC.logError("Error doing an entity's variant common drops", e);
                return null;
            }
        }

        private ItemStack getRareDropFromVariant() {
            try {
                return ((EntityDrop) WeightedRandom.getRandomItem(rand, this.variantRare)).getStack();
            } catch (Exception e) {
                TragicMC.logError("Error doing an entity's variant rare drops", e);
                return null;
            }
        }
    }

    public static class EntityDrop extends WeightedRandom.Item {

        private final ItemStack[] stack;

        public EntityDrop(int weight, Item item) {
            super(weight);
            this.stack = new ItemStack[] { new ItemStack(item) };
        }

        public EntityDrop(int weight, Block block) {
            super(weight);
            this.stack = new ItemStack[] { new ItemStack(block) };
        }

        public EntityDrop(int weight, ItemStack... stack) {
            super(weight);
            this.stack = stack;
        }

        public ItemStack getStack() {
            return this.stack[rand.nextInt(this.stack.length)].copy();
        }
    }
}
