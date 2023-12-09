package tragicneko.tragicmc.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.WeightedRandom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import scala.tools.nsc.doc.model.Class;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.items.armor.*;
import tragicneko.tragicmc.items.weapons.*;

import java.io.File;
import java.io.FileReader;
import java.util.*;

import static tragicneko.tragicmc.TragicMC.rand;

public class LoreHelper {

    private static Map<Class<? extends Item>, LoreEntry> loreMap = new HashMap();
    private static Logger logger = LogManager.getLogger(TragicMC.MODID + "/LoreHelper");

    public static void addToLoreMap(Class<? extends Item> clazz, LoreEntry entry) {
        if (loreMap.containsKey(clazz)) TragicMC.logWarning("Duplicate lore entry for the item " + clazz);
        loreMap.put(clazz, entry);
    }

    public static void addToLoreMap(Class<? extends Item> clazz, Lore[] lores, EnchantEntry[][] enchants) {
        if (loreMap.containsKey(clazz)) TragicMC.logWarning("Duplicate lore entry for the item " + clazz);
        loreMap.put(clazz, new LoreEntry(Arrays.asList(lores), enchants));
    }

    public static LoreEntry getLoreEntry(Class<? extends Item> clazz) {
        return loreMap.containsKey(clazz) ? loreMap.get(clazz) : null;
    }

    public static EnumChatFormatting getFormatForRarity(int rarity) {
        return rarity == 0 ? EnumChatFormatting.GRAY
            : (rarity == 1 ? EnumChatFormatting.GOLD
                : (rarity == 2 ? EnumChatFormatting.DARK_GREEN : EnumChatFormatting.DARK_RED));
    }

    public static int getRarityFromStack(ItemStack stack) {
        return stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLoreRarity")
            ? stack.stackTagCompound.getByte("tragicLoreRarity")
            : 0;
    }

    public static String getDescFromStack(ItemStack stack) {
        return stack.hasTagCompound() && stack.stackTagCompound.hasKey("tragicLoreDesc")
            ? stack.stackTagCompound.getString("tragicLoreDesc")
            : null;
    }

    /**
     * Can split any lengthy string (most likely a description) into smaller ones to fit within an item's description,
     * it will split as often as it needs to
     *
     * @param lore
     * @return
     */
    public static List<String> splitDesc(List<String> list, final String lore, final int lineBreak,
        final EnumChatFormatting format) {
        String s = lore;

        if (lore.length() <= lineBreak) {
            list.add(format + lore);
        } else {

            for (int i = lineBreak; i < s.length(); i++) {
                if (s.substring(0, i)
                    .endsWith(" ")) {
                    list.add(
                        format + s.substring(0, i)
                            .trim());
                    s = s.substring(i)
                        .trim();
                    i = lineBreak;
                }
            }

            if (s.length() > 0) list.add(format + s);
        }

        return list;
    }

    public static void registerLoreJson(File config) {
        loadDefaultLores();
        logger.info("Attempting to load Custom Lores from config directory...");
        File fileIn = new File(config, "tragiclores.json");
        /*
         * if (!fileIn.exists()) //If it doesn't exist then uses the one bundled in the mod, this will probably not be
         * implemented (it'll take a lot of work to convert the current lores into a json format)
         * {
         * //logger.
         * info("Failed to parse Custom Lores from Json in the Configuration directory. Defaulting to internal backup."
         * );
         * BufferedReader reader = new BufferedReader(new
         * InputStreamReader(TragicMC.class.getResourceAsStream("/assets/tragicmc/tragiclores.json")));
         * lores = parser.parse(reader);
         * }
         */

        if (fileIn.exists()) {
            logger.info("Proper file was found for custom lores, attempting load of custom lores.");

            try {
                JsonParser parser = new JsonParser();
                JsonElement lores = null;
                lores = parser.parse(new FileReader(fileIn));

                for (JsonElement el : lores.getAsJsonArray()) {
                    JsonObject obj = el.getAsJsonObject();
                    String className = obj.get("classname")
                        .getAsString();
                    try {
                        Class oclass = Class.forName(className);
                        if (!obj.get("lores")
                            .isJsonArray()) {
                            logger.error(
                                "There is an error with the lores array element for class of %s, skipping...",
                                className);
                            continue;
                        }
                        JsonArray array = obj.get("lores")
                            .getAsJsonArray();
                        LoreEntry entry = getLoreEntry(oclass);

                        if (entry == null) {
                            logger.warn(className + " didn't have an entry in the lore map so it was ignored.");
                            continue;
                        }

                        for (JsonElement el2 : array) {
                            JsonObject obj2 = el2.getAsJsonObject();
                            try {
                                int weight = obj2.get("weight")
                                    .getAsInt();
                                String desc = obj2.get("desc")
                                    .getAsString();
                                int rarity = obj2.get("rarity")
                                    .getAsInt();
                                entry.addLore(new Lore(weight, desc, rarity));
                                logger.info("Lore added for item of class " + className);
                            } catch (ClassCastException e) {
                                logger.error(
                                    "Error found while parsing class of %s, problem with an element, skipping lore...",
                                    className); // log and move on for these, they aren't necessary for normal mod
                                                // functions
                                continue;
                            }
                        }
                    } catch (ClassNotFoundException e) {
                        logger.error(
                            "Unable to find class with name of %s while parsing the Custom Lores json file.",
                            className);
                        continue; // log and move on for these, they aren't necessary for normal mod functions
                    }
                }

            } catch (Exception e) {
                logger.warn("Failed to load Custom Lores from Json, this may cause instability with lores!");
                return;
            }
        } else {
            logger.info("tragiclores.json file was not found in config directory, skipping custom lore parsing.");
        }
    }

    public static void loadDefaultLores() {
        // Armor
        addToLoreMap(
            ArmorDark.class,
            new LoreEntry(
                new Lore[] { new Lore(25, "It's dark.", 1), new Lore(20, "Dim.", 1),
                    new Lore(15, "Rather dark out!", 1), new Lore(10, "Hold me.", 1), new Lore(10, "I'm so alone.", 1),
                    new Lore(10, "Cold, dark and alone...", 1), new Lore(5, "Darkness all around me.", 1),
                    new Lore(5, "It's quite dark out tonight, isn't it?", 1), new Lore(15, "Lonely...", 1),
                    new Lore(5, "How can you see in this darkness?", 1), new Lore(25, "Scream!", 2),
                    new Lore(15, "Screaming Bloody Mary!", 2), new Lore(15, "Welcome to my nightmare!", 2),
                    new Lore(10, "Just another slasher...", 2), new Lore(10, "I'll rip you a new one!", 2),
                    new Lore(5, "Oh yes, there will be blood.", 2), new Lore(5, "Let's play a game.", 2),
                    new Lore(10, "Fright night!", 2), new Lore(5, "I see dead people.", 2),
                    new Lore(25, "The Boogeyman is real and you found him!", 3),
                    new Lore(20, "You will die in 7 days.", 3),
                    new Lore(
                        5,
                        "If you don't forward this to 10 people by midnight, a psychopath will come to your house and kill you.",
                        3),
                    new Lore(15, "Come play with us...", 3), new Lore(5, "We all go a little mad sometimes...", 3),
                    new Lore(15, "Victims... aren't we all?", 3), new Lore(20, "Join us... one of us... one of us!", 3),
                    new Lore(10, "One, two, Freddy's coming for you!", 3),
                    new Lore(10, "Three, four, better lock your door!", 3), new Lore(5, "Want to play a game?", 3),
                    new Lore(20, "I know what you did last summer.", 3), new Lore(5, "Jeepers creepers!", 3),
                    new Lore(10, "It rubs the lotion on it's skin.", 3) },
                new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 3) },
                    { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.DeathTouch, 3) },
                    { new EnchantEntry(Enchantment.unbreaking, 7), new EnchantEntry(TragicEnchantments.DeathTouch, 5),
                        new EnchantEntry(Enchantment.respiration, 1) } },
                new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 3) },
                    { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.DeathTouch, 3) },
                    { new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(TragicEnchantments.DeathTouch, 5),
                        new EnchantEntry(TragicEnchantments.Toxicity, 3), new EnchantEntry(Enchantment.thorns, 1) } },
                new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 3) },
                    { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.DeathTouch, 3) },
                    { new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(TragicEnchantments.DeathTouch, 5),
                        new EnchantEntry(TragicEnchantments.Toxicity, 3) } },
                new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 3) },
                    { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.DeathTouch, 3) },
                    { new EnchantEntry(Enchantment.unbreaking, 7), new EnchantEntry(TragicEnchantments.DeathTouch, 5),
                        new EnchantEntry(Enchantment.featherFalling, 1) } }));

        addToLoreMap(
            ArmorHunter.class,
            new LoreEntry(
                new Lore[] { new Lore(25, "Move swiftly.", 1), new Lore(15, "Make haste.", 1),
                    new Lore(15, "Feel the wind on your face!", 1), new Lore(10, "Fast as the wind!", 1),
                    new Lore(5, "Too fast, too furious.", 2), new Lore(25, "Windswept.", 1),
                    new Lore(15, "Feverishly fast.", 1), new Lore(5, "Unrelenting speed.", 1),
                    new Lore(5, "Used Gust! It's not very effective.", 1), new Lore(25, "Watch out for windburn!", 1),
                    new Lore(15, "Used Fly! It's super effective!", 2), new Lore(25, "Like a tsunami!", 2),
                    new Lore(15, "I'm like a bird.", 2), new Lore(15, "I can go the distance!", 2),
                    new Lore(5, "In the eye of the hurricane.", 2),
                    new Lore(15, "Feel the full force of the unburdened wind!", 2),
                    new Lore(5, "Watch as the cold wind slices through you!", 2),
                    new Lore(5, "Wind chill of -40 tonight!", 2),
                    new Lore(25, "Used Sky Attack! Critical Hit! It's super effective!", 3),
                    new Lore(10, "Like the howling wind.", 1), new Lore(5, "Used Whirlwind. The enemy fled.", 1),
                    new Lore(15, "All the force of a great typhoon!", 3),
                    new Lore(25, "Swift as the coursing river!", 3), new Lore(5, "Won't you find me, free bird?", 3),
                    new Lore(5, "He crawls like a worm from a bird!", 3),
                    new Lore(15, "Fly like the wind, Bullseye!", 3),
                    new Lore(10, "Here comes the rooster, no he ain't gonna die!", 3),
                    new Lore(5, "In the eye of the storm.", 3),
                    new Lore(5, "Fly back to school now little starling, fly, fly, fly...", 3) },
                new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 3),
                        new EnchantEntry(Enchantment.projectileProtection, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 5),
                        new EnchantEntry(Enchantment.projectileProtection, 3) } },
                new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 3),
                        new EnchantEntry(Enchantment.projectileProtection, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 5),
                        new EnchantEntry(Enchantment.projectileProtection, 3),
                        new EnchantEntry(TragicEnchantments.Agility, 1) } },
                new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 3),
                        new EnchantEntry(Enchantment.projectileProtection, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 5),
                        new EnchantEntry(Enchantment.projectileProtection, 3),
                        new EnchantEntry(TragicEnchantments.Agility, 1) } },
                new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 3),
                        new EnchantEntry(Enchantment.projectileProtection, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 5),
                        new EnchantEntry(Enchantment.projectileProtection, 3),
                        new EnchantEntry(Enchantment.featherFalling, 1) } }));

        addToLoreMap(
            ArmorLight.class,
            new LoreEntry(
                new Lore[] { new Lore(25, "Don't give up.", 1), new Lore(15, "Overcome.", 1),
                    new Lore(10, "Rise above.", 1), new Lore(5, "Inspire others.", 1),
                    new Lore(15, "Don't ever give up.", 1), new Lore(15, "Brighten your day!", 1),
                    new Lore(10, "Be positive.", 1), new Lore(5, "It's not that bad.", 1),
                    new Lore(10, "Get up, stand up!", 1),
                    new Lore(5, "The night is always darkest just before the dawn.", 3),
                    new Lore(25, "Don't stop believing!", 2), new Lore(15, "Rise above this!", 2),
                    new Lore(15, "Don't worry, it gets better.", 2), new Lore(10, "Live to rise!", 2),
                    new Lore(15, "Seize the day!", 2), new Lore(5, "Carpe diem.", 2),
                    new Lore(10, "Keep your faith.", 1), new Lore(5, "Never give up hope.", 1),
                    new Lore(10, "Everything in it's right place.", 2), new Lore(10, "Everything zen.", 2),
                    new Lore(15, "Inspire and electrify.", 2), new Lore(25, "Let your light shine down!", 3),
                    new Lore(15, "Beacon of hope!", 3), new Lore(10, "Live and let die.", 3),
                    new Lore(5, "Even when your hope is gone, move along, move along just to make it through!", 3),
                    new Lore(5, "Always look on the bright side of life!", 3),
                    new Lore(15, "Dig me out from under what is covering!", 3),
                    new Lore(10, "It's not too late, it's never too late.", 3),
                    new Lore(15, "I can feel you all around me, thickening the air I'm breathing.", 3),
                    new Lore(15, "Turn around, bright eyes!", 3),
                    new Lore(5, "Open up my eager eyes, cuz I'm Mr. Brightside!", 3),
                    new Lore(5, "Welcome to this place, I'll show you everything with arms wide open!", 3) },
                new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 3) },
                    { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(Enchantment.respiration, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(Enchantment.respiration, 3),
                        new EnchantEntry(Enchantment.aquaAffinity, 1) } },
                new EnchantEntry[][] { {},
                    { new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.RuneWalker, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.RuneWalker, 3) },
                    { new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(TragicEnchantments.RuneWalker, 5),
                        new EnchantEntry(TragicEnchantments.Ignition, 1) } },
                new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 3) },
                    { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.RuneWalker, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(TragicEnchantments.RuneWalker, 3),
                        new EnchantEntry(TragicEnchantments.Ignition, 3) } },
                new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 3) },
                    { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.RuneWalker, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(TragicEnchantments.RuneWalker, 3),
                        new EnchantEntry(Enchantment.featherFalling, 1) } }));

        addToLoreMap(
            ArmorMercury.class,
            new LoreEntry(
                new Lore[] { new Lore(25, "Don't touch me.", 1), new Lore(15, "Get away from me.", 1),
                    new Lore(10, "Don't touch!", 1), new Lore(5, "No touchy!", 1),
                    new Lore(15, "Does anyone have disinfectant?", 1), new Lore(15, "Germs!", 1),
                    new Lore(5, "Don't breathe on me!", 1), new Lore(15, "There's germs everywhere...", 2),
                    new Lore(25, "Time for the 4th daily shower!", 2),
                    new Lore(15, "Anyone here ever heard of soap?", 2),
                    new Lore(10, "Just block out all of the filth...", 2),
                    new Lore(15, "Time for the 3rd disinfectant layer!", 2),
                    new Lore(5, "Why is it so dirty outside?", 2), new Lore(15, "Eww, it touched me!", 2),
                    new Lore(5, "I can feel the germs crawling their way into the nape of my neck!", 3),
                    new Lore(25, "Ever heard of hygeine?", 3), new Lore(15, "Too many things in close proximity!", 3),
                    new Lore(5, "Eww don't touch me with your germs!", 3),
                    new Lore(5, "Don't touch me you filthy casual!", 3), new Lore(10, "Die you infectious disease!", 3),
                    new Lore(5, "I despise personal interaction!", 3), new Lore(15, "Quick, give me a wipey!", 3),
                    new Lore(5, "It's a gift... and a curse.", 3),
                    new Lore(15, "Stay away from me with your disease ridden hands!", 2), new Lore(5, "Disgusting.", 1),
                    new Lore(10, "Gross.", 1) },
                new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 2) },
                    { new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(Enchantment.respiration, 1) } },
                new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 2), new EnchantEntry(Enchantment.protection, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(Enchantment.protection, 2),
                        new EnchantEntry(TragicEnchantments.Elasticity, 2) } },
                new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 2), new EnchantEntry(TragicEnchantments.Elasticity, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.Elasticity, 2),
                        new EnchantEntry(TragicEnchantments.Paralysis, 1) } },
                new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 2) }, { new EnchantEntry(Enchantment.unbreaking, 3),
                        new EnchantEntry(TragicEnchantments.Elasticity, 1) } }));

        addToLoreMap(
            ArmorSkull.class,
            new LoreEntry(
                new Lore[] { new Lore(25, "What's that smell?", 1), new Lore(15, "Is that smell... you?", 1),
                    new Lore(15, "Sniff, sniff...", 1), new Lore(10, "You smell that?", 1),
                    new Lore(5, "Smells like rotten eggs...", 1), new Lore(5, "Something smells raunchy...", 1),
                    new Lore(15, "The nose knows!", 1), new Lore(15, "Are you a hobo or something?", 1),
                    new Lore(10, "Why do you smell like you live in a sewer?", 1),
                    new Lore(5, "Something smells fishy.", 1), new Lore(25, "We don't deliver to sewers.", 2),
                    new Lore(15, "Anyone have an air freshener?", 2),
                    new Lore(10, "Let me guess, you're a garbage man.", 2),
                    new Lore(15, "At least it's a minty garbage smell now.", 2),
                    new Lore(5, "There's a faint smell of filth in the air.", 2), new Lore(5, "Well, this stinks.", 2),
                    new Lore(5, "He who smelt it.", 2), new Lore(25, "Smells like Nirvana.", 3),
                    new Lore(5, "Smells like teen spirit.", 3), new Lore(15, "My stench strong.", 3),
                    new Lore(5, "Everyone likes their own product.", 3),
                    new Lore(10, "I put Oscar the Grouch to shame!", 3),
                    new Lore(5, "They call me a garbage player, how did they know?", 3),
                    new Lore(10, "Love is in the air, no wait that's just me.", 3) },
                new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 2) },
                    { new EnchantEntry(Enchantment.unbreaking, 3),
                        new EnchantEntry(TragicEnchantments.DeathTouch, 1) } },
                new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 3) },
                    { new EnchantEntry(Enchantment.unbreaking, 4), new EnchantEntry(TragicEnchantments.DeathTouch, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 5),
                        new EnchantEntry(TragicEnchantments.DeathTouch, 3) } },
                new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 2), new EnchantEntry(TragicEnchantments.DeathTouch, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 3),
                        new EnchantEntry(TragicEnchantments.DeathTouch, 5) } },
                new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 2) }, { new EnchantEntry(Enchantment.unbreaking, 3),
                        new EnchantEntry(TragicEnchantments.DeathTouch, 1) } }));

        addToLoreMap(
            ArmorTungsten.class,
            new LoreEntry(
                new Lore[] { new Lore(25, "Hot stuff.", 0), new Lore(15, "Feels lukewarm.", 0),
                    new Lore(10, "Pretty hot.", 0), new Lore(15, "Warm.", 0), new Lore(10, "Warmer.", 0),
                    new Lore(10, "Hot.", 0), new Lore(5, "Hotter.", 0), new Lore(10, "It's a bit stuffy out.", 0),
                    new Lore(5, "In heat.", 0), new Lore(5, "Do I look hot in this?", 0),
                    new Lore(25, "Just warming up!", 1), new Lore(15, "I look pretty hot in this.", 1),
                    new Lore(10, "I'm on fire!", 1), new Lore(5, "Hot stuff, coming through!", 1),
                    new Lore(15, "I'm pretty heated right now.", 1), new Lore(5, "Spontaneous combustion.", 1),
                    new Lore(10, "First-degree burn.", 1), new Lore(5, "You got burned!", 1),
                    new Lore(25, "Burn it all down to the ground!", 2),
                    new Lore(15, "Things are heating up quickly!", 2), new Lore(10, "It's getting hot in here.", 2),
                    new Lore(15, "Burn baby, burn.", 2), new Lore(10, "Too hot to handle!", 2),
                    new Lore(15, "Second-degree burn.", 2), new Lore(10, "Slow burn...", 2),
                    new Lore(5, "Burnt to ashes.", 2), new Lore(10, "Original fire.", 2),
                    new Lore(5, "Light my fire!", 2), new Lore(10, "Bridges are burning now...", 2),
                    new Lore(25, "I'm hot-blooded! Check it and see, I got a fever of a hundred and three!", 3),
                    new Lore(15, "Third-degree burn.", 3), new Lore(15, "Need some water for that burn?", 3),
                    new Lore(15, "I'm burning, I'm burning, I'm burning for you!", 3),
                    new Lore(5, "Caution: Contents may be hot.", 3),
                    new Lore(5, "Warning: Contents may explode under pressure.", 3),
                    new Lore(
                        15,
                        "I fell in to a burning ring of fire, I went down, down, down, and the flames went higher!",
                        3),
                    new Lore(15, "Through the fire and flames...", 3), new Lore(10, "Shepherd of fire!", 3),
                    new Lore(15, "Scream, aim, fire!", 3), new Lore(5, "We can't wait to burn it to the ground!", 3),
                    new Lore(
                        5,
                        "I don't want to set the world on fire, I just want to start a flame in your heart.",
                        3) },
                new EnchantEntry[][] { { new EnchantEntry(Enchantment.unbreaking, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 2), new EnchantEntry(Enchantment.fireProtection, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(Enchantment.fireProtection, 2) },
                    { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(Enchantment.fireProtection, 3),
                        new EnchantEntry(TragicEnchantments.Ignition, 1) } },
                new EnchantEntry[][] { { new EnchantEntry(Enchantment.unbreaking, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(Enchantment.fireProtection, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(Enchantment.fireProtection, 3),
                        new EnchantEntry(TragicEnchantments.Ignition, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 7), new EnchantEntry(Enchantment.fireProtection, 5),
                        new EnchantEntry(TragicEnchantments.Ignition, 3) } },
                new EnchantEntry[][] { { new EnchantEntry(Enchantment.unbreaking, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 2), new EnchantEntry(TragicEnchantments.Ignition, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.Ignition, 3),
                        new EnchantEntry(Enchantment.fireProtection, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 4), new EnchantEntry(TragicEnchantments.Ignition, 5),
                        new EnchantEntry(Enchantment.fireProtection, 3) } },
                new EnchantEntry[][] { { new EnchantEntry(Enchantment.unbreaking, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 2), new EnchantEntry(Enchantment.fireProtection, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(Enchantment.fireProtection, 2),
                        new EnchantEntry(TragicEnchantments.Ignition, 1) },
                    { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(Enchantment.fireProtection, 3),
                        new EnchantEntry(TragicEnchantments.Ignition, 3) } }));

        addToLoreMap(
            ArmorOverlord.class,
            new LoreEntry(
                new Lore[] { new Lore(
                    15,
                    "And can you offer me proof of your existence? How can you, when neither modern science nor philosophy can explain what life is?",
                    3),
                    new Lore(
                        15,
                        " If we all reacted the same way, we'd be predictable, and there's always more than one way to view a situation.",
                        3),
                    new Lore(
                        25,
                        "There's nothing sadder than a puppet without a ghost, especially the kind with red blood running through them.",
                        3),
                    new Lore(5, "Even a simulated experience or a dream is simultaneous reality and fantasy.", 3),
                    new Lore(15, "If you've got a problem with the world, change yourself.", 3),
                    new Lore(5, "I feel confined, only free to expand myself within boundaries.", 3),
                    new Lore(5, "Your effort to remain what you are is what limits you.", 3),
                    new Lore(15, "I mean, who knows what's inside your head. Have you ever seen your own brain?", 3),
                    new Lore(5, "I thought what I'd do is pretend I was one of those deaf-mutes.", 3),
                    new Lore(15, "Stand alone complex.", 3),
                    new Lore(5, "The law doesn't protect people. People protect the law.", 3),
                    new Lore(15, "The time when our connections to others was the basis of ourselves is long gone.", 3),
                    new Lore(5, "The future is not a straight line. It is filled with many crossroads.", 3),
                    new Lore(25, "When you leave behind your body, what remains is your ghost.", 3),
                    new Lore(5, "KANEDAAAAAAAAA!", 3), new Lore(5, "TESTSUOOOOOOOO!", 3),
                    new Lore(15, "Human curiosity.", 0) },
                new EnchantEntry[][] { {}, {}, {}, { new EnchantEntry(Enchantment.unbreaking, 10),
                    new EnchantEntry(Enchantment.aquaAffinity, 5), new EnchantEntry(Enchantment.respiration, 5),
                    new EnchantEntry(TragicEnchantments.DeathTouch, 5),
                    new EnchantEntry(TragicEnchantments.Elasticity, 3),
                    new EnchantEntry(TragicEnchantments.Ignition, 5), new EnchantEntry(TragicEnchantments.Paralysis, 5),
                    new EnchantEntry(TragicEnchantments.RuneWalker, 5),
                    new EnchantEntry(TragicEnchantments.Toxicity, 5) } },
                new EnchantEntry[][] { {}, {}, {}, { new EnchantEntry(Enchantment.unbreaking, 10),
                    new EnchantEntry(Enchantment.protection, 5), new EnchantEntry(TragicEnchantments.DeathTouch, 5),
                    new EnchantEntry(Enchantment.thorns, 5), new EnchantEntry(TragicEnchantments.Elasticity, 3),
                    new EnchantEntry(TragicEnchantments.Ignition, 5), new EnchantEntry(TragicEnchantments.Paralysis, 5),
                    new EnchantEntry(TragicEnchantments.RuneWalker, 5),
                    new EnchantEntry(TragicEnchantments.Toxicity, 5) } },
                new EnchantEntry[][] { {}, {}, {}, { new EnchantEntry(Enchantment.unbreaking, 10),
                    new EnchantEntry(Enchantment.protection, 5), new EnchantEntry(TragicEnchantments.DeathTouch, 5),
                    new EnchantEntry(Enchantment.thorns, 5), new EnchantEntry(TragicEnchantments.Elasticity, 3),
                    new EnchantEntry(TragicEnchantments.Ignition, 5), new EnchantEntry(TragicEnchantments.Paralysis, 5),
                    new EnchantEntry(TragicEnchantments.RuneWalker, 5),
                    new EnchantEntry(TragicEnchantments.Toxicity, 5) } },
                new EnchantEntry[][] { {}, {}, {}, { new EnchantEntry(Enchantment.unbreaking, 10),
                    new EnchantEntry(Enchantment.protection, 5), new EnchantEntry(Enchantment.featherFalling, 5),
                    new EnchantEntry(TragicEnchantments.DeathTouch, 5),
                    new EnchantEntry(TragicEnchantments.Elasticity, 3),
                    new EnchantEntry(TragicEnchantments.Ignition, 5), new EnchantEntry(TragicEnchantments.Paralysis, 5),
                    new EnchantEntry(TragicEnchantments.RuneWalker, 5),
                    new EnchantEntry(TragicEnchantments.Toxicity, 5) } }));

        // Tools
        addToLoreMap(
            TragicItems.TungstenJack.getClass(),
            new Lore[] { new Lore(25, "Work, work, work!", 1), new Lore(15, "Time for lunch!", 1),
                new Lore(15, "Work all day, sleep all night!", 2), new Lore(5, "Off to work we go!", 2),
                new Lore(10, "Can you dig it?", 1), new Lore(25, "Just keep digging, digging, digging!", 2),
                new Lore(5, "The finest weapons and armor!", 2), new Lore(25, "Diamonds!", 3),
                new Lore(15, "Ooh, emeralds!", 3), new Lore(5, "Forged in the fires of Mount Doom!", 3),
                new Lore(5, "The best blacksmith in Whiterun!", 3),
                new Lore(10, "Can you pick up what I'm putting down?", 1) },
            new EnchantEntry[][] { {},
                { new EnchantEntry(Enchantment.efficiency, 1), new EnchantEntry(Enchantment.fortune, 1) },
                { new EnchantEntry(Enchantment.efficiency, 3), new EnchantEntry(Enchantment.fortune, 2),
                    new EnchantEntry(Enchantment.fireAspect, 1) },
                { new EnchantEntry(Enchantment.efficiency, 5), new EnchantEntry(Enchantment.fortune, 3),
                    new EnchantEntry(Enchantment.fireAspect, 2),
                    new EnchantEntry(TragicEnchantments.Combustion, 1) } });

        addToLoreMap(
            TragicItems.CelestialJack.getClass(),
            new Lore[] { new Lore(25, "Enigmatic.", 0), new Lore(15, "Quite the mystery.", 0),
                new Lore(10, "It's a mystery to us all.", 0), new Lore(25, "To the Mystery Machine!", 1),
                new Lore(15, "I would've gotten away with it if it wasn't for you meddling kids!", 1),
                new Lore(10, "Scooby Snax?", 1), new Lore(5, "Another mystery solved!", 1),
                new Lore(25, "We've got a mystery on our hands!", 2),
                new Lore(15, "It's Old Man Withers from the Amusement Park!", 2), new Lore(10, "Jinkies!", 2),
                new Lore(5, "Whodunit?", 2), new Lore(25, "Let's get out our Handy-Dandy Notebook!", 3),
                new Lore(15, "Elementary, my dear Watson!", 3),
                new Lore(
                    5,
                    "Once you eliminate the impossible, whatever remains, no matter how improbable, must be the truth.",
                    3),
                new Lore(15, "We just found a clue!", 1), new Lore(5, "I live by Harry's code.", 3),
                new Lore(5, "I'm not a psychopath, I'm a high functioning sociopath, do your research.", 3),
                new Lore(5, "Colonel Mustard in the library with a knife!", 3) },
            new EnchantEntry[][] { { new EnchantEntry(Enchantment.unbreaking, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(Enchantment.fortune, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(Enchantment.fortune, 3),
                    new EnchantEntry(TragicEnchantments.Veteran, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(Enchantment.fortune, 5),
                    new EnchantEntry(TragicEnchantments.Veteran, 3),
                    new EnchantEntry(TragicEnchantments.Luminescence, 1) } });

        // Normal Weapons
        addToLoreMap(
            TragicItems.MercuryDagger.getClass(),
            new Lore[] { new Lore(25, "Boring.", 1), new Lore(15, "Nice.", 1), new Lore(5, "Interesting.", 1),
                new Lore(15, "Lame", 1), new Lore(25, "Ha.", 2), new Lore(15, "Awesome.", 2),
                new Lore(10, "That's fascinating.", 2), new Lore(5, "That's nice.", 2),
                new Lore(25, "That's amazing!", 3), new Lore(15, "Fantastic!", 3), new Lore(5, "Okay.", 1),
                new Lore(5, "I'm shuddering with excitement!", 3), new Lore(5, "Ama-zuh-zing!", 3),
                new Lore(5, "So awesome!", 3) },
            new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 2) },
                { new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(Enchantment.sharpness, 1) } });

        addToLoreMap(
            WeaponBeastlyClaws.class,
            new Lore[] { new Lore(25, "That's beastly.", 1), new Lore(15, "Epic.", 1), new Lore(5, "Knockout!", 1),
                new Lore(10, "Roar!", 1), new Lore(15, "Combo!", 1), new Lore(5, "Let's fight!", 1),
                new Lore(5, "Sucker punch!", 1), new Lore(25, "Just getting started!", 2),
                new Lore(20, "Just sharpening my claws!", 2), new Lore(15, "One-two punch!", 2),
                new Lore(5, "You're gonna hear me roar!", 2), new Lore(10, "Punchout!", 2), new Lore(5, "Fight!", 2),
                new Lore(5, "TKO!", 2), new Lore(25, "Hit and Run!", 3), new Lore(5, "Falcon Punch!", 3),
                new Lore(15, "Going Beastmode!", 3), new Lore(2, "C-c-c-combo breaker!", 3),
                new Lore(10, "Limit break!", 3), new Lore(15, "I'll rip you to pieces!", 3),
                new Lore(20, "Tear you to pieces, rip you apart!", 3) },
            new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 2), new EnchantEntry(TragicEnchantments.Slay, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.Slay, 2),
                    new EnchantEntry(TragicEnchantments.Consume, 1) } });

        addToLoreMap(
            WeaponBlindingLight.class,
            new Lore[] { new Lore(25, "You're shining!", 1), new Lore(15, "Shine on!", 1),
                new Lore(5, "Aw, you're glowing~", 1), new Lore(10, "Shine bright like a diamond.", 1),
                new Lore(5, "Just needs some spit shine!", 1), new Lore(15, "Shinedown.", 1),
                new Lore(10, "It's bright, like me!", 1), new Lore(5, "So bright.", 2),
                new Lore(25, "Like a shooting star!", 2), new Lore(15, "Ooh, shiny!", 2),
                new Lore(25, "Shiny, shiny, shiny!", 2), new Lore(5, "Brilliant luster!", 2),
                new Lore(25, "Heaven let your light shine on!", 3),
                new Lore(15, "How do you get just the right amount of shiny?!", 3),
                new Lore(5, "Always look on the bright side of life!", 3), new Lore(5, "Turn on your love light!", 3),
                new Lore(5, "Shine on you crazy diamond!", 3) },
            new EnchantEntry[][] { {}, { new EnchantEntry(TragicEnchantments.Absolve, 1) },
                { new EnchantEntry(TragicEnchantments.Absolve, 3), new EnchantEntry(Enchantment.unbreaking, 1) },
                { new EnchantEntry(TragicEnchantments.Absolve, 5), new EnchantEntry(Enchantment.unbreaking, 1),
                    new EnchantEntry(Enchantment.fireAspect, 1) } });

        addToLoreMap(
            WeaponCelestialAegis.class,
            new Lore[] { new Lore(25, "This is my jam!", 1), new Lore(15, "Sounds to die for!", 1),
                new Lore(5, "The Benny Hill theme song!", 1), new Lore(5, "First things first, I'm the realist!", 1),
                new Lore(20, "Nice tune!", 1), new Lore(20, "What a lovely melody!", 1),
                new Lore(25, "Death in E-Minor!", 2), new Lore(15, "Screams in 6/4 time!", 2),
                new Lore(25, "4/4 at 120 bpm", 2), new Lore(15, "What a lovely death sound you make!", 2),
                new Lore(10, "Such a lovely scream!", 2), new Lore(5, "Shrieks of terror have a nice ambience!", 2),
                new Lore(5, "Let the music take your breath away~", 2), new Lore(25, "I can show you the world!", 3),
                new Lore(25, "I wish I could be part of your world!", 3), new Lore(15, "Be our guest!", 3),
                new Lore(15, "Poor unfortunate souls!", 3), new Lore(10, "Go! Go! Power Rangers!", 3),
                new Lore(10, "Heroes in a half-shell, turtle power!", 3), new Lore(5, "Flight of the Bumblebee!", 3),
                new Lore(5, "The Blue Danube!", 3), new Lore(20, "It's Mambo No. 5!", 3),
                new Lore(15, "Guess who's back, back again!", 3), new Lore(15, "Under the sea!", 3) },
            new EnchantEntry[][] { { new EnchantEntry(Enchantment.unbreaking, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.Absolve, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.Absolve, 3),
                    new EnchantEntry(TragicEnchantments.Consume, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(TragicEnchantments.Absolve, 5),
                    new EnchantEntry(TragicEnchantments.Consume, 3), new EnchantEntry(TragicEnchantments.Reach, 3),
                    new EnchantEntry(Enchantment.looting, 3), new EnchantEntry(TragicEnchantments.Luminescence, 1) } });

        addToLoreMap(
            WeaponCelestialLongbow.class,
            new Lore[] { new Lore(25, "Like meteor showers!", 1), new Lore(10, "Shooting stars!", 1),
                new Lore(15, "Beautiful Starlights!", 1), new Lore(5, "Make a Wish!", 1),
                new Lore(25, "So beautiful!", 2), new Lore(10, "Ooh, a free starman!", 2),
                new Lore(5, "Make a wish!", 2), new Lore(5, "Time for the star festival!", 3),
                new Lore(5, "Meteor Smash!", 3), new Lore(25, "Time for armageddon!", 3),
                new Lore(5, "Guardian of the Galaxy!", 3), new Lore(15, "The Final Starman!?", 3),
                new Lore(5, "Good Morning Starshine!", 3), new Lore(5, "The Earth says, Hello!", 3),
                new Lore(5, "Warm the celestial bodies!", 3) },
            new EnchantEntry[][] { { new EnchantEntry(Enchantment.unbreaking, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(Enchantment.power, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(Enchantment.power, 3),
                    new EnchantEntry(Enchantment.looting, 3) },
                { new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(Enchantment.power, 5),
                    new EnchantEntry(Enchantment.looting, 5), new EnchantEntry(TragicEnchantments.Multiply, 1),
                    new EnchantEntry(Enchantment.infinity, 1),
                    new EnchantEntry(TragicEnchantments.Luminescence, 1) } });

        addToLoreMap(
            WeaponFrozenLightning.class,
            new Lore[] { new Lore(25, "Was that lightning?", 1), new Lore(15, "Ouch, you zapped me!", 1),
                new Lore(5, "Used Spark! It's not very effective...", 2), new Lore(25, "Lightning crashes...", 2),
                new Lore(15, "A storm is brewing!", 2), new Lore(5, "You've been... THUNDERSTRUCK!", 3),
                new Lore(15, "Static shock!", 2), new Lore(5, "I feel shocked.", 3),
                new Lore(25, "Time for a lightning round!", 3), new Lore(5, "Used Volt Tackle! Critical hit!", 3),
                new Lore(10, "Used Thunder! It's super effective!", 3) },
            new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.RuneBreak, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.RuneBreak, 3),
                    new EnchantEntry(TragicEnchantments.Rust, 1),
                    new EnchantEntry(TragicEnchantments.Luminescence, 1) } });

        addToLoreMap(
            WeaponGravitySpike.class,
            new Lore[] { new Lore(15, "Time for a demonstration!", 1), new Lore(5, "e=mc^2", 1),
                new Lore(5, "For Science!", 1), new Lore(15, "The next Einstein!", 2),
                new Lore(5, "Isn't that a Rube Goldberg?", 2), new Lore(25, "Science rules!", 2),
                new Lore(10, "I like 3.14.", 2), new Lore(5, "In SPAAAAAAAAAAAACE!", 3),
                new Lore(25, "Reaching escape velocity!", 3),
                new Lore(15, "It is a dimension as vast as space and as timeless as infinity...", 3),
                new Lore(5, "There is a fifth dimension, beyond that which is known to man.", 3) },
            new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(Enchantment.knockback, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(Enchantment.knockback, 3),
                    new EnchantEntry(TragicEnchantments.Distract, 1) } });

        addToLoreMap(
            WeaponGuiltyThorn.class,
            new Lore[] { new Lore(25, "Kill...", 1), new Lore(15, "Your happiness kills me inside.", 1),
                new Lore(5, "Your pain feeds me.", 1), new Lore(10, "Your hatred fuels my soul.", 2),
                new Lore(25, "I love when you hate me.", 2), new Lore(25, "Die.", 1),
                new Lore(5, "I'm not crazy, I'm the only one thinking clearly right now.", 2),
                new Lore(25, "Your pain = <3", 3), new Lore(15, "Your screams of agony sound so beautiful!", 3),
                new Lore(5, "Some call me sadistic. I just like to have fun at other's expense.", 3),
                new Lore(15, "Don't worry, I'll end your misery!", 3),
                new Lore(5, "You sound better when you're dead!", 3) },
            new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.Leech, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.Leech, 3),
                    new EnchantEntry(Enchantment.sharpness, 3) } });

        addToLoreMap(
            WeaponHarmonyBell.class,
            new Lore[] { new Lore(25, "Ring-a-ding-ding!", 1), new Lore(15, "Peace and Quiet.", 1),
                new Lore(5, "Tranquility.", 1), new Lore(15, "Need some R&R?", 2),
                new Lore(5, "Ding-ding! Dinner is ready!", 2), new Lore(10, "Listen to those glorious chimes!", 3),
                new Lore(25, "Relax.", 2), new Lore(5, "Hell's Bells!", 3),
                new Lore(25, "Fahoo-Fores, Dahoo-Dores!", 3), new Lore(15, "Ding dong, the witch is dead!", 3),
                new Lore(5, "For Whom the Bell Tolls.", 3) },
            new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.Distract, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.Distract, 3),
                    new EnchantEntry(TragicEnchantments.Absolve, 1), new EnchantEntry(Enchantment.knockback, 1) } });

        addToLoreMap(
            WeaponHuntersBow.class,
            new Lore[] { new Lore(15, "On the hunt.", 1), new Lore(5, "Run as fast as you can!", 1),
                new Lore(10, "Tracking...", 1), new Lore(25, "Let the hunt begin.", 2),
                new Lore(5, "Time to join the hunting party!", 3), new Lore(5, "Catch me if you can!", 2),
                new Lore(5, "Conquest!", 2), new Lore(25, "Don't worry, I'm an expert.", 3),
                new Lore(15, "The Hunter becomes the Hunted.", 3), new Lore(5, "The Most Dangerous Game", 3),
                new Lore(15, "Night of the Hunter!", 3) },
            new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 2), new EnchantEntry(Enchantment.punch, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(Enchantment.punch, 2),
                    new EnchantEntry(Enchantment.flame, 1), new EnchantEntry(Enchantment.power, 1) } });

        addToLoreMap(
            WeaponIreParticleCannon.class,
            new Lore[] { new Lore(25, "Accelerate!", 1), new Lore(15, "Fire! Fire! Fire!", 2),
                new Lore(5, "Kill! Kill! Kill!", 3), new Lore(25, "You look pretty shady, dontcha?", 1),
                new Lore(15, "Get away from me you creeper!", 1), new Lore(5, "Take a picture, it'll last longer.", 2),
                new Lore(25, "This is why I don't reply to PMs!", 2), new Lore(15, "Seriously, stop following me!", 1),
                new Lore(5, "Why are you stalking me?", 1), new Lore(5, "Seriously, can you not do that!", 2),
                new Lore(10, "You ought not to have done that!", 2),
                new Lore(5, "Prepare to be particle accelerated!", 3),
                new Lore(15, "I will give you $5 if you could not do that.", 3),
                new Lore(5, "Way to slide into those DMs, buddy.", 3),
                new Lore(5, "I'll never get that image out of my head.", 3) },
            new EnchantEntry[][] { {},
                { new EnchantEntry(Enchantment.unbreaking, 1), new EnchantEntry(Enchantment.knockback, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(Enchantment.knockback, 3) },
                { new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(Enchantment.knockback, 5) } });

        addToLoreMap(
            WeaponMourningStar.class,
            new Lore[] { new Lore(25, "Sleep is for the weak!", 1), new Lore(15, "Dy-no-mite!", 1),
                new Lore(5, "Kaboom.", 1), new Lore(5, "Nuke!", 3), new Lore(25, "For SPARTAAAAAA!", 3),
                new Lore(15, "Just die already!", 2), new Lore(15, "I'm TNT, I'm dynamite!", 3),
                new Lore(25, "I have an explosive temper.", 2), new Lore(5, "Enemy airstrike inbound!", 3) },
            new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.smite, 1) },
                { new EnchantEntry(Enchantment.smite, 3), new EnchantEntry(TragicEnchantments.Consume, 1) },
                { new EnchantEntry(Enchantment.smite, 5), new EnchantEntry(TragicEnchantments.Consume, 3),
                    new EnchantEntry(Enchantment.looting, 1) } });

        addToLoreMap(
            WeaponNekoLauncher.class,
            new Lore[] { new Lore(25, "Oops", 1), new Lore(15, "I meant to do that.", 1),
                new Lore(5, "That was supposed to happen!", 1), new Lore(25, "Tell me where it hurts!", 2),
                new Lore(15, "It's just a flesh wound.", 2), new Lore(5, "Does it hurt when I do this?", 2),
                new Lore(25, "This is why I can't have nice things!", 3),
                new Lore(15, "Some days you just can't get rid of a bomb!", 3), new Lore(5, "Meow~", 3),
                new Lore(5, "Seriously, all of these mess-ups were on purpose!", 2),
                new Lore(10, "It's all going to plan, honestly!", 3) },
            new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(Enchantment.knockback, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(Enchantment.knockback, 3),
                    new EnchantEntry(TragicEnchantments.Distract, 1) } });

        addToLoreMap(
            WeaponPitchBlack.class,
            new Lore[] { new Lore(25, "Black as my soul!", 1), new Lore(15, "Hide in the shadows.", 1),
                new Lore(5, "Darkness is my friend.", 1), new Lore(10, "Perfect Dark Zero.", 1),
                new Lore(25, "Paint it black!", 2), new Lore(15, "Like the black in your eyes.", 2),
                new Lore(5, "Pitch black!", 2), new Lore(15, "Black Hole Sun!", 3),
                new Lore(15, "Welcome to the Black Parade!", 3), new Lore(5, "Blackout! Blood in your eyes!", 3),
                new Lore(5, "I was born in the dark. Molded by it. You merely adopted it.", 3) },
            new EnchantEntry[][] { {},
                { new EnchantEntry(Enchantment.unbreaking, 1), new EnchantEntry(TragicEnchantments.Decay, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.Decay, 3),
                    new EnchantEntry(TragicEnchantments.RuneBreak, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.Decay, 5),
                    new EnchantEntry(TragicEnchantments.RuneBreak, 3), new EnchantEntry(Enchantment.looting, 1) } });

        addToLoreMap(
            WeaponReaperScythe.class,
            new Lore[] { new Lore(25, "Bleed out.", 1), new Lore(15, "Bleed for me.", 1),
                new Lore(5, "Blood is flowing now!", 1), new Lore(5, "It's raining blood.", 1),
                new Lore(25, "Blood is thicker than water.", 2),
                new Lore(15, "No matter how you discriminate we all bleed the same.", 2),
                new Lore(5, "Crimson red, like the blood moon.", 2),
                new Lore(5, "I ate his liver with some fava beans and a nice Chianti.", 3),
                new Lore(15, "Digging deeper just to throw it away!", 3),
                new Lore(25, "Let's paint this town red!", 3) },
            new EnchantEntry[][] { {},
                { new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.Decay, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.Decay, 3),
                    new EnchantEntry(TragicEnchantments.Vampirism, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(TragicEnchantments.Decay, 5),
                    new EnchantEntry(TragicEnchantments.Vampirism, 3) } });

        addToLoreMap(
            WeaponWitheringAxe.class,
            new Lore[] { new Lore(5, "Like Paul Bunyan.", 1), new Lore(15, "Lemme axe you a question!", 1),
                new Lore(15, "Chop chop!", 1), new Lore(25, "Weapon of choice.", 1),
                new Lore(25, "Plaid is the new black.", 2), new Lore(15, "Tree murderer.", 2),
                new Lore(10, "Go ahead, axe me how my day went.", 2),
                new Lore(5, "Your beard looks quite luxurious today.", 2),
                new Lore(25, "He's a lumberjack and he's okay!", 3), new Lore(15, "Chop Suey!?", 3),
                new Lore(5, "Taking you right to the chop block!", 3), new Lore(5, "Treetho's Choppa", 3) },
            new EnchantEntry[][] { {}, { new EnchantEntry(Enchantment.unbreaking, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(Enchantment.sharpness, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(Enchantment.sharpness, 3),
                    new EnchantEntry(Enchantment.efficiency, 1) } });

        // Alpha weapons
        addToLoreMap(
            WeaponSentinel.class,
            new Lore[] { new Lore(15, "The red pill?", 3), new Lore(25, "White rabbit.", 3),
                new Lore(5, "The blue pill?", 3),
                new Lore(20, "How would you know the difference between the dream world and the real world?", 3),
                new Lore(25, "Tumbling down the rabbit hole...", 3), new Lore(5, "Is this the Matrix?", 3),
                new Lore(5, "You are the one.", 3), new Lore(15, "There is no spoon.", 3),
                new Lore(25, "Wonder what's next.", 3), new Lore(5, "I'm not the one.", 3),
                new Lore(
                    5,
                    "You may have spent the last few years looking for me, but I have spent my entire life looking for you.",
                    3),
                new Lore(15, "I don't like the idea that I'm not in control of my life.", 3),
                new Lore(15, "What good is a phone call if you're unable to speak?", 3),
                new Lore(5, "Mr. Anderson, you disappoint me.", 3), new Lore(15, "Free your mind.", 3) },
            new EnchantEntry[][] { {}, {}, {},
                { new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(TragicEnchantments.Absolve, 5),
                    new EnchantEntry(Enchantment.baneOfArthropods, 5), new EnchantEntry(TragicEnchantments.Decay, 5),
                    new EnchantEntry(TragicEnchantments.Slay, 5), new EnchantEntry(Enchantment.smite, 5),
                    new EnchantEntry(TragicEnchantments.Reach, 5) } });

        // Epic weapons
        addToLoreMap(
            WeaponButcher.class,
            new Lore[] { new Lore(25, "Time to eat?", 0), new Lore(15, "I'm so hungry...", 0),
                new Lore(10, "I need food.", 0), new Lore(25, "My stomach's grumbling...", 1),
                new Lore(15, "That looks delicious!", 1), new Lore(10, "My stomach won't stop growling!", 1),
                new Lore(5, "Needs more salt...", 1), new Lore(25, "Preheat oven to 450.", 2),
                new Lore(15, "Bon apetit!", 2), new Lore(5, "I'm having an old friend for dinner!", 2),
                new Lore(5, "Just add a pinch of salt.", 2), new Lore(25, "Add a splash of red wine.", 3),
                new Lore(15, "Bake for twenty minutes or until golden brown.", 3), new Lore(5, "Mmmm... donuts.", 3),
                new Lore(5, "OMNOMNOMNOMNOM", 3),
                new Lore(5, "Everything is edible, even me, but that would be cannibalism, children.", 3),
                new Lore(5, "Add some olive oil and garlic then simmer.", 3) },
            new EnchantEntry[][] {
                { new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.Reach, 3) },
                { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.Reach, 3),
                    new EnchantEntry(TragicEnchantments.Slay, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 7), new EnchantEntry(TragicEnchantments.Reach, 3),
                    new EnchantEntry(TragicEnchantments.Slay, 3), new EnchantEntry(Enchantment.sharpness, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(TragicEnchantments.Reach, 5),
                    new EnchantEntry(TragicEnchantments.Slay, 5), new EnchantEntry(Enchantment.sharpness, 3) } });

        addToLoreMap(
            WeaponDragonFang.class,
            new Lore[] { new Lore(25, "Sasquatch!", 0), new Lore(15, "Is that a nymph?", 0),
                new Lore(10, "Sleeping with Sirens.", 0), new Lore(5, "It's really happening!", 0),
                new Lore(5, "Alrighty then. Picture this if you will.", 0),
                new Lore(25, "I swear, there was a triangle of lights in the sky moving around!", 1),
                new Lore(15, "Was that a jackelope?", 1), new Lore(10, "It's obviously a centaur.", 1),
                new Lore(5, "There's a cold spot here. Ghost?", 1), new Lore(25, "I think I just saw E.T.!", 2),
                new Lore(15, "Dude, I totally just saw Nessie.", 2),
                new Lore(10, "Cartman got abducted by aliens last night!", 2), new Lore(10, "Someone call the MIB", 2),
                new Lore(5, "Can't remember what they said...", 2),
                new Lore(25, "Return the slab or suffer my curse!", 3),
                new Lore(5, "The man in gauze, the man in gauze. King RAAAAAMSAYYY!", 3),
                new Lore(15, "Aliens are nice, they apologized, gave me a nice lollipop and sent me on my way.", 3),
                new Lore(5, "It's the chupacabra!", 3),
                new Lore(5, "A Will-o-the-Wisp started that fire, I know how to cook!", 3),
                new Lore(5, "Probed by alien. Chance of survival: minimal.", 3) },
            new EnchantEntry[][] {
                { new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.Reach, 3) },
                { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.Reach, 3),
                    new EnchantEntry(TragicEnchantments.Slay, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 7), new EnchantEntry(TragicEnchantments.Reach, 3),
                    new EnchantEntry(TragicEnchantments.Slay, 3), new EnchantEntry(Enchantment.fireAspect, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(TragicEnchantments.Reach, 3),
                    new EnchantEntry(TragicEnchantments.Slay, 5), new EnchantEntry(Enchantment.fireAspect, 2) } });

        addToLoreMap(
            WeaponParanoia.class,
            new Lore[] { new Lore(25, "So lonely.", 0), new Lore(15, "Fragile and alone...", 0),
                new Lore(10, "It's calm.", 0), new Lore(5, "I'm so alone...", 0),
                new Lore(25, "If only I had friends...", 1), new Lore(15, "So scary.", 1),
                new Lore(10, "The outside world is so scary.", 1), new Lore(5, "When I wake up, I'm afraid.", 1),
                new Lore(5, "Alone...", 1), new Lore(25, "Feeling a bit paranoid right now...", 2),
                new Lore(15, "Sensational fear of everything!", 2), new Lore(10, "Darkness consumes me", 2),
                new Lore(25, "Lost in the dark of my mind...", 3),
                new Lore(15, "If fear is an animal, I've tamed it.", 3),
                new Lore(10, "If fear is an animal, it may have just swallowed me whole...", 3),
                new Lore(5, "Just because you're paranoid, doesn't mean they're not after you.", 3),
                new Lore(15, "They're all out to get me! Somebody help me!", 3),
                new Lore(5, "Darkness consume me.", 3) },
            new EnchantEntry[][] {
                { new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.Reach, 3) },
                { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.Reach, 3),
                    new EnchantEntry(TragicEnchantments.RuneBreak, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 7), new EnchantEntry(TragicEnchantments.Reach, 3),
                    new EnchantEntry(TragicEnchantments.RuneBreak, 3), new EnchantEntry(TragicEnchantments.Leech, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(TragicEnchantments.Reach, 3),
                    new EnchantEntry(TragicEnchantments.RuneBreak, 5), new EnchantEntry(TragicEnchantments.Leech, 3),
                    new EnchantEntry(Enchantment.looting, 1) } });

        addToLoreMap(
            WeaponSplinter.class,
            new Lore[] { new Lore(25, "Beating around the bush...", 0), new Lore(15, "I see.", 0),
                new Lore(5, "I don't get it...", 0), new Lore(5, "Fool's Gold!", 0),
                new Lore(25, "Beggars can't be choosers!", 1), new Lore(15, "Sorry to burst your bubble!", 1),
                new Lore(10, "Easy as pie!", 1), new Lore(5, "Piece of cake!", 1),
                new Lore(10, "Don't cry over spilled milk!", 1),
                new Lore(25, "Don't count your chickens before they hatch!", 2), new Lore(15, "Jack of all trades!", 2),
                new Lore(10, "Don't count your chickens before they hatch!", 2), new Lore(5, "On cloud nine!", 2),
                new Lore(15, "Sorry to rain on your parade!", 1), new Lore(25, "A dime a dozen!", 3),
                new Lore(15, "Curiosity killed the cat!", 1),
                new Lore(5, "The nail that sticks out the most gets hammered down!", 3),
                new Lore(15, "Read'em and weep!", 3), new Lore(5, "Roll with the punches!", 3),
                new Lore(10, "It's not rocket science!", 3), new Lore(5, "A blessing in disguise!", 3) },
            new EnchantEntry[][] {
                { new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.Reach, 3) },
                { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.Reach, 3),
                    new EnchantEntry(TragicEnchantments.Consume, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 7), new EnchantEntry(TragicEnchantments.Reach, 3),
                    new EnchantEntry(TragicEnchantments.Consume, 3), new EnchantEntry(Enchantment.knockback, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(TragicEnchantments.Reach, 3),
                    new EnchantEntry(TragicEnchantments.Consume, 5), new EnchantEntry(Enchantment.knockback, 3) } });

        addToLoreMap(
            WeaponThardus.class,
            new Lore[] { new Lore(25, "Power beam.", 0), new Lore(15, "Charge beam.", 0),
                new Lore(10, "Morph Ball.", 0), new Lore(5, "Missle.", 0), new Lore(5, "Energy Tank.", 0),
                new Lore(25, "Better than a stun gun!", 1), new Lore(15, "Spazer beam.", 1),
                new Lore(10, "Grapple beam.", 1), new Lore(15, "Zero suit.", 1),
                new Lore(5, "Morph Ball bomb acquired!", 1), new Lore(5, "Speed Booster acquired!", 1),
                new Lore(25, "You're a girl?", 2), new Lore(15, "Metroids. Metroids everywhere.", 2),
                new Lore(5, "Why is there always a Space Pirate?", 2), new Lore(5, "Remember me?", 2),
                new Lore(5, "Space jump acquired!", 2), new Lore(25, "Hyper Beam acquired!", 3),
                new Lore(15, "Hypermode, activate!", 3), new Lore(10, "Phazon beam acquired!", 3),
                new Lore(15, "Plasma beam acquired!", 3), new Lore(15, "Wave beam acquired!", 3),
                new Lore(15, "Screw Attack acquired!", 3), new Lore(5, "Super missle acquired!", 3),
                new Lore(5, "Power Bomb acquired!", 3), new Lore(5, "Speed Booster acquired!", 3),
                new Lore(15, "New area discovered: Tourian", 3),
                new Lore(5, "You have arrived on planet, Tallon IV", 3),
                new Lore(5, "SR388 has been cleared of all Metroid activity.", 3),
                new Lore(5, "The last metroid is in captivity.", 3), new Lore(5, "Power Suit acquired.", 1),
                new Lore(5, "Varia Suit acquired!", 2), new Lore(5, "Gravity Suit acquired!", 2),
                new Lore(5, "Phazon Suit acquired!", 2), new Lore(5, "Kraid has been defeated!", 1),
                new Lore(5, "Ridley has been defeated!", 2), new Lore(5, "Mother Brain has been defeated!", 3) },
            new EnchantEntry[][] {
                { new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.Reach, 3) },
                { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.Reach, 3),
                    new EnchantEntry(TragicEnchantments.RuneBreak, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 7), new EnchantEntry(TragicEnchantments.Reach, 3),
                    new EnchantEntry(TragicEnchantments.RuneBreak, 3), new EnchantEntry(TragicEnchantments.Rust, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(TragicEnchantments.Reach, 3),
                    new EnchantEntry(TragicEnchantments.RuneBreak, 5), new EnchantEntry(TragicEnchantments.Rust, 3),
                    new EnchantEntry(TragicEnchantments.Luminescence, 1) } });

        addToLoreMap(
            WeaponTitan.class,
            new Lore[] { new Lore(25, "Filthy mortal.", 0), new Lore(15, "You're such a mortal.", 0),
                new Lore(10, "Why do you have to be so... mortal?", 0), new Lore(5, "You bore me, mortal.", 0),
                new Lore(25, "So God-like!", 1), new Lore(15, "It's like the Gods have blessed me!", 1),
                new Lore(10, "Thank the Gods!", 1), new Lore(5, "Praise the Gods for this gift!", 1),
                new Lore(5, "Thank God!", 1), new Lore(5, "A God-like aura permeates the air.", 1),
                new Lore(25, "Such God-like abilities!", 2), new Lore(15, "The Gods have become my equal!", 2),
                new Lore(5, "I am a God!", 2), new Lore(10, "Who needs a God when you have me?", 2),
                new Lore(25, "You are an ant to me, mortal.", 3), new Lore(10, "I am no mere mortal!", 2),
                new Lore(5, "Puny God.", 3), new Lore(15, "Poseidon has nothing on me!", 3),
                new Lore(15, "Faster than Hermes!", 3), new Lore(10, "The violence of Ares!", 3),
                new Lore(15, "Wiser than Athena!", 3), new Lore(5, "More creative than Hephaestus!", 3),
                new Lore(15, "More beautiful than Aphrodite!", 3), new Lore(5, "More spectacular than Zeus!", 3) },
            new EnchantEntry[][] {
                { new EnchantEntry(Enchantment.unbreaking, 3), new EnchantEntry(TragicEnchantments.Reach, 3) },
                { new EnchantEntry(Enchantment.unbreaking, 5), new EnchantEntry(TragicEnchantments.Reach, 3),
                    new EnchantEntry(Enchantment.looting, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 7), new EnchantEntry(TragicEnchantments.Reach, 3),
                    new EnchantEntry(Enchantment.looting, 3), new EnchantEntry(TragicEnchantments.Consume, 1) },
                { new EnchantEntry(Enchantment.unbreaking, 10), new EnchantEntry(TragicEnchantments.Reach, 5),
                    new EnchantEntry(Enchantment.looting, 5), new EnchantEntry(TragicEnchantments.Consume, 3) } });

        // Tier 2 weapons
        /*
         * addToLoreMap(WeaponGrievingWidow.class, new Lore[] {new Lore(25, "Jealousy.", 1), new Lore(15, "Hate.", 1),
         * new Lore(15, "Lovesick.", 1), new Lore(10, "Heartbreak.", 1), new Lore(5, "You can't trust any Man.", 1), new
         * Lore(5, "You can't trust any Woman.", 1),
         * new Lore(25, "Loving you is torture...", 2), new Lore(15, "My heart is in pieces.", 2), new Lore(15,
         * "Please lie to make me feel better.", 2), new Lore(15, "I hope it kills you as much as it's killing me.", 2),
         * new Lore(5, "Marriage is being sentenced to endless torture.", 2),
         * new Lore(25, "I still love you with all the pieces of my broken heart.", 3), new Lore(10,
         * "Seeing you with them just eats what's left of my heart.", 2), new Lore(5,
         * "I gave my heart to you, but then you gave it to someone else...", 2), new Lore(5,
         * "As heartless as your wretched lies...", 2),
         * new Lore(15, "There's a hole where my heart used to be.", 3), new Lore(5,
         * "Got a long list of ex-lovers, they'll tell you I'm insane.", 3), new Lore(15,
         * "All that's left is broken pieces of my heart that you trampled all on...", 3), new Lore(10,
         * "The color is all but faded down to this blackened heart.", 3),
         * new Lore(5, "Why did you leave me in this life all alone?!", 3)},
         * new EnchantEntry[][]{{new EnchantEntry(TragicEnchantments.Swiftness, 1), new
         * EnchantEntry(TragicEnchantments.Perforate, 1)}, {new EnchantEntry(TragicEnchantments.Swiftness, 3), new
         * EnchantEntry(TragicEnchantments.Perforate, 3), new EnchantEntry(TragicEnchantments.Consume, 1)},
         * {new EnchantEntry(TragicEnchantments.Swiftness, 5), new EnchantEntry(TragicEnchantments.Perforate, 5), new
         * EnchantEntry(TragicEnchantments.Consume, 3)},
         * {new EnchantEntry(TragicEnchantments.Swiftness, 5), new EnchantEntry(TragicEnchantments.Perforate, 5), new
         * EnchantEntry(TragicEnchantments.Consume, 5), new EnchantEntry(TragicEnchantments.DireHit, 1)}});
         */
    }

    public static class LoreEntry {

        private final ArrayList<Lore> lores = new ArrayList<Lore>();
        private final EnchantEntry[][] enchants;
        private final ArrayList<EnchantEntry[][]> armorEnchants = new ArrayList<EnchantEntry[][]>();

        public LoreEntry(Lore[] lores, EnchantEntry[][] armor, EnchantEntry[][] armor2, EnchantEntry[][] armor3,
            EnchantEntry[][] armor4) {
            this.lores.addAll(Arrays.asList(lores));
            this.enchants = new EnchantEntry[][] { {}, {}, {}, {} };
            this.armorEnchants.add(0, armor);
            this.armorEnchants.add(1, armor2);
            this.armorEnchants.add(2, armor3);
            this.armorEnchants.add(3, armor4);
        }

        public LoreEntry(Collection<Lore> lores, EnchantEntry[][] enchants) {
            this.lores.addAll(lores);
            this.enchants = enchants;
        }

        public void addLore(Lore l) {
            this.lores.add(l);
        }

        /**
         * This is used to pick a completely random lore based off of the weapon/armor's set of lores, this is for brand
         * new weapons/armor or weapons/armor received from a mob
         *
         * @return
         */
        public Lore getRandomLore() {
            try {
                int i = rand.nextInt(100);
                int r = i < 10 ? 3 : (i < 35 ? 2 : (i < 80 ? 1 : 0)); // 10% chance to be epic, 25% to be rare, 45% to
                                                                      // be uncommon, 20% to be common
                List<Lore> alist = new ArrayList();

                for (Lore l : this.lores) {
                    if (l.getRarity() == r) alist.add(l);
                }

                if (alist.isEmpty())
                    return this.lores.size() > 0 && r > 0 ? this.lores.get(rand.nextInt(this.lores.size()))
                        : new Lore(1, "[There are markings that you can't quite make out...]", 0);

                return ((Lore) WeightedRandom.getRandomItem(rand, alist)).get();
            } catch (Exception e) {
                TragicMC.logError("Error getting a random Lore for a weapon", e);
                return null;
            }
        }

        /**
         * Returns a random lore in the given rarity tier, otherwise returns an empty lore
         *
         * @param rarity
         * @param id
         * @return
         */
        public Lore getLoreOfRarity(int rarity) {
            try {
                List<Lore> alist = new ArrayList();

                for (Lore l : this.lores) {
                    if (l.getRarity() == rarity) alist.add(l);
                }

                if (alist.isEmpty())
                    return new Lore(1, "[There are markings that you can't quite make out...]", rarity);

                return ((Lore) WeightedRandom.getRandomItem(rand, alist)).get();
            } catch (Exception e) {
                TragicMC.logError("Error retrieving a specific Lore for a weapon based on rarity", e);
                return null;
            }
        }

        public EnchantEntry[] getEnchantmentsForRarity(int rarity) {
            try {
                rarity = MathHelper.clamp_int(rarity, 0, 3);
                return this.enchants[rarity];
            } catch (Exception e) {
                TragicMC.logError("Error getting enchantments for a weapon", e);
                return null;
            }
        }

        private EnchantEntry[][] getEnchantmentsForArmor(int armorType) {
            return !this.armorEnchants.isEmpty() && this.armorEnchants.size() >= armorType
                ? this.armorEnchants.get(armorType)
                : null;
        }

        public EnchantEntry[] getEnchantmentsForArmor(int rarity, int armorType) {
            EnchantEntry[][] ench = this.getEnchantmentsForArmor(armorType);
            return ench != null ? ench[rarity] : null;
        }
    }

    public static class EnchantEntry extends Tuple<Enchantment, Integer> {

        public EnchantEntry(Enchantment ench, int level) {
            super(ench, level);
        }

        public Enchantment getEnchantment() {
            return this.getLeft();
        }

        public int getEnchantLevel() {
            return this.getRight();
        }
    }

    public static class Lore extends WeightedRandom.Item {

        private final int rarity;
        private final String desc;

        public Lore(int weight, String desc, int rarity) {
            super(weight);
            this.desc = desc;
            this.rarity = MathHelper.clamp_int(rarity, 0, 3);
        }

        public Lore(Lore lore) {
            this(lore.itemWeight, lore.getDesc(), lore.getRarity());
        }

        public String getDesc() {
            return this.desc;
        }

        public int getRarity() {
            return this.rarity;
        }

        public EnumRarity getRarityEnum() {
            return this.rarity == 0 ? EnumRarity.common
                : (this.rarity == 1 ? EnumRarity.uncommon : (this.rarity == 2 ? EnumRarity.rare : EnumRarity.epic));
        }

        /**
         * Returns a copy of this Lore so that it remains in the map
         *
         * @return
         */
        public Lore get() {
            return new Lore(this);
        }
    }
}
