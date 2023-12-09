package tragicneko.tragicmc;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

import tragicneko.tragicmc.enchantment.EnchantmentAgility;
import tragicneko.tragicmc.enchantment.EnchantmentArmorExtra;
import tragicneko.tragicmc.enchantment.EnchantmentCombustion;
import tragicneko.tragicmc.enchantment.EnchantmentDamageBoost;
import tragicneko.tragicmc.enchantment.EnchantmentLuminescence;
import tragicneko.tragicmc.enchantment.EnchantmentMultiply;
import tragicneko.tragicmc.enchantment.EnchantmentRange;
import tragicneko.tragicmc.enchantment.EnchantmentRuneWalker;
import tragicneko.tragicmc.enchantment.EnchantmentUnbreakable;
import tragicneko.tragicmc.enchantment.EnchantmentVeteran;
import tragicneko.tragicmc.enchantment.EnchantmentWeaponExtra;

public class TragicEnchantments {

    public static Enchantment Decay, Absolve, Slay, Vampirism, Leech, Consume, Distract, Combustion, Multiply,
        RuneBreak, Reach, Unbreakable, Rust, Veteran;
    public static Enchantment Ignition, Paralysis, Toxicity, Elasticity, DeathTouch, Agility, RuneWalker, Luminescence;

    public static void load() {
        if (TragicConfig.allowDecay) Decay = (new EnchantmentDamageBoost(TragicConfig.idDecay, TragicConfig.wDecay, 0));
        if (TragicConfig.allowAbsolve)
            Absolve = (new EnchantmentDamageBoost(TragicConfig.idAbsolve, TragicConfig.wAbsolve, 1));
        if (TragicConfig.allowSlay) Slay = (new EnchantmentDamageBoost(TragicConfig.idSlay, TragicConfig.wSlay, 2));
        if (TragicConfig.allowVampirism)
            Vampirism = (new EnchantmentWeaponExtra(TragicConfig.idVampirism, TragicConfig.wVampirism, 0));
        if (TragicConfig.allowLeech) Leech = (new EnchantmentWeaponExtra(TragicConfig.idLeech, TragicConfig.wLeech, 1));
        if (TragicConfig.allowConsume)
            Consume = (new EnchantmentWeaponExtra(TragicConfig.idConsume, TragicConfig.wConsume, 2));
        if (TragicConfig.allowDistract)
            Distract = (new EnchantmentWeaponExtra(TragicConfig.idDistract, TragicConfig.wDistract, 3));
        if (TragicConfig.allowCombustion) Combustion = (new EnchantmentCombustion(
            TragicConfig.idCombustion,
            TragicConfig.wCombustion,
            EnumEnchantmentType.digger));
        if (TragicConfig.allowMultiply) Multiply = (new EnchantmentMultiply(
            TragicConfig.idMultiply,
            TragicConfig.wMultiply,
            EnumEnchantmentType.bow));
        if (TragicConfig.allowRuneBreak)
            RuneBreak = (new EnchantmentDamageBoost(TragicConfig.idRuneBreak, TragicConfig.wRuneBreak, 3));
        if (TragicConfig.allowReach)
            Reach = (new EnchantmentRange(TragicConfig.idReach, TragicConfig.wReach, EnumEnchantmentType.weapon));
        if (TragicConfig.allowUnbreakable) Unbreakable = (new EnchantmentUnbreakable(
            TragicConfig.idUnbreakable,
            TragicConfig.wUnbreakable,
            EnumEnchantmentType.breakable));
        if (TragicConfig.allowRust) Rust = (new EnchantmentWeaponExtra(TragicConfig.idRust, TragicConfig.wRust, 4));
        if (TragicConfig.allowVeteran) Veteran = (new EnchantmentVeteran(
            TragicConfig.idVeteran,
            TragicConfig.wVeteran,
            EnumEnchantmentType.digger));

        if (TragicConfig.allowIgnition)
            Ignition = (new EnchantmentArmorExtra(TragicConfig.idIgnition, TragicConfig.wIgnition, 0));
        if (TragicConfig.allowParalysis)
            Paralysis = (new EnchantmentArmorExtra(TragicConfig.idParalysis, TragicConfig.wParalysis, 1));
        if (TragicConfig.allowToxicity)
            Toxicity = (new EnchantmentArmorExtra(TragicConfig.idToxicity, TragicConfig.wToxicity, 2));
        if (TragicConfig.allowElasticity)
            Elasticity = (new EnchantmentArmorExtra(TragicConfig.idElasticity, TragicConfig.wElasticity, 3));
        if (TragicConfig.allowDeathTouch)
            DeathTouch = (new EnchantmentArmorExtra(TragicConfig.idDeathTouch, TragicConfig.wDeathTouch, 4));
        if (TragicConfig.allowAgility) Agility = (new EnchantmentAgility(
            TragicConfig.idAgility,
            TragicConfig.wAgility,
            EnumEnchantmentType.armor));
        if (TragicConfig.allowRuneWalker) RuneWalker = (new EnchantmentRuneWalker(
            TragicConfig.idRuneWalker,
            TragicConfig.wRuneWalker,
            EnumEnchantmentType.armor));
        if (TragicConfig.allowLuminescence) Luminescence = (new EnchantmentLuminescence(
            TragicConfig.idLuminescence,
            TragicConfig.wLuminescence,
            EnumEnchantmentType.all));
    }
}
