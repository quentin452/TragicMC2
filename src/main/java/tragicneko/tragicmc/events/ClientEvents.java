package tragicneko.tragicmc.events;

import static tragicneko.tragicmc.TragicMC.rand;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovementInputFromOptions;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.blocks.BlockGenericLeaves;
import tragicneko.tragicmc.client.ClientProxy;
import tragicneko.tragicmc.items.amulet.ItemAmulet;
import tragicneko.tragicmc.network.MessageGui;
import tragicneko.tragicmc.network.MessageUseDoomsday;
import tragicneko.tragicmc.properties.PropertyAmulets;
import tragicneko.tragicmc.util.AmuletHelper;

public class ClientEvents extends Gui {

    private static ResourceLocation hackedTexture = new ResourceLocation(
        "tragicmc:textures/environment/collisionSky.png");
    private static ResourceLocation divinityTexture = new ResourceLocation(
        "tragicmc:textures/environment/divinity2.png");

    private static final float[][] rgb = new float[][] { { 1F, 1F, 1F }, { 1F, 0.3F, 0.3F }, { 0.3F, 0.9F, 0.9F },
        { 0.1F, 0.8F, 0.1F }, { 1F, 0.3F, 1F }, { 0.8F, 0.1F, 0.1F }, { 0F, 0.1F, 0.8F }, { 0.8F, 0.3F, 0.5F },
        { 0.6F, 0.3F, 0.9F }, { 0.3F, 0.3F, 0.3F }, { 0.6F, 0.6F, 0.9F }, { 0.1F, 0.1F, 0.1F } };
    private static int counter;
    private static int color;
    private static int buffer;

    private static final String[] sounds = new String[] { "mob.enderdragon.growl", "random.fizz",
        "mob.enderdragon.wings", "mob.endermen.portal", "mob.zombie.hurt", "mob.skeleton.hurt", "random.bow",
        "random.explode", "random.chestopen", "mob.wither.hurt", "mob.wither.idle", "random.door_open",
        "game.hostile.hurt", "creeper.primed", "random.break", "random.wood_click", "mob.endermen.scream",
        "mob.endermen.stare", "tragicmc:mob.psygote.cry", "tragicmc:mob.inkling.giggle", "tragicmc:mob.stin.teleport" };

    private static boolean RECREATE_MI = false;

    @SubscribeEvent
    public void onKeyInput(KeyInputEvent event) {
        if (Minecraft.getMinecraft().inGameHasFocus) {
            EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;

            if (player == null) return;

            if (ClientProxy.openAmuletGui.getIsKeyPressed() && TragicConfig.allowAmulets) {
                TragicMC.net.sendToServer(new MessageGui(ClientProxy.AMULET_GUI_ID));
            }

            if (ClientProxy.useSpecial.getIsKeyPressed() && TragicConfig.allowDoomsdays) {
                TragicMC.net.sendToServer(new MessageUseDoomsday(player.getCurrentEquippedItem()));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onFrozenInput(KeyInputEvent event) {
        EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
        if (player == null) return;
        /*
         * if (player.isPotionActive(TragicPotion.Frozen))
         * {
         * PropertyMisc misc = PropertyMisc.get(player);
         * if (misc == null) return;
         * TragicMC.logInfo("Frozen input received.");
         * if (misc.isFrozen)
         * {
         * misc.frozenInputs--;
         * boolean flag = misc.frozenInputs <= 0 && misc.isFrozen;
         * TragicMC.net.sendToServer(new MessageFrozenInput(flag));
         * misc.isFrozen = !flag;
         * }
         * else
         * {
         * misc.isFrozen = true;
         * misc.frozenInputs = 30 + (20 * player.getActivePotionEffect(TragicPotion.Frozen).getAmplifier());
         * }
         * TragicMC.logInfo("Frozen input left is " + misc.frozenInputs);
         * if (event.isCancelable()) event.setCanceled(true);
         * }
         */
    }

    @SubscribeEvent
    public void onClientUpdate(LivingUpdateEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        BlockGenericLeaves.fancyGraphics = Minecraft.isFancyGraphicsEnabled();
        EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;

        if (player != null && TragicConfig.allowFlight
            && player.isPotionActive(TragicPotion.Flight.id)
            && mc.inGameHasFocus) {
            boolean flag = !TragicConfig.allowStun
                || TragicConfig.allowStun && !player.isPotionActive(TragicPotion.Stun.id);

            if (flag && Keyboard.isCreated()) {
                PotionEffect effect = player.getActivePotionEffect(TragicPotion.Flight);
                effect.getDuration();

                if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
                    player.motionY = player.isSprinting() ? 0.245D : 0.165D;
                } else if (player.isSneaking()) {
                    player.motionY = -0.455D;
                }
            }
        }

        if (player != null && TragicConfig.allowHacked
            && player.isPotionActive(TragicPotion.Hacked.id)
            && rand.nextInt(4) == 0) {
            ItemStack current = player.getCurrentEquippedItem();
            if (current != null && rand.nextInt(1048) == 0 && rand.nextInt(1048) == 42) player.dropOneItem(true);
            if (player.swingProgress == 1.0F) player.swingProgress = 0.0F;
            MovementInput input = new MovementInput();
            if (rand.nextInt(4) == 0) input.jump = true;
            if (rand.nextInt(4) == 0) input.moveForward = rand.nextFloat() * 1.4F - rand.nextFloat() * 1.4F;
            if (rand.nextInt(4) == 0) input.moveStrafe = rand.nextFloat() * 1.4F - rand.nextFloat() * 1.4F;
            if (rand.nextInt(32) == 0) input.sneak = true;
            player.movementInput = input;
            RECREATE_MI = true;
        }

        if (player != null && !(player.movementInput instanceof MovementInputFromOptions)
            && TragicConfig.allowHacked
            && !player.isPotionActive(TragicPotion.Hacked)
            && RECREATE_MI) player.movementInput = new MovementInputFromOptions(mc.gameSettings);

        if (player != null && TragicConfig.allowDisorientation && player.isPotionActive(TragicPotion.Disorientation)) {
            float f = player.getActivePotionEffect(TragicPotion.Disorientation)
                .getAmplifier() * 0.45F + 0.45F;
            player.rotationPitch += (rand.nextFloat() - rand.nextFloat()) * f;
            player.rotationYaw += (rand.nextFloat() - rand.nextFloat()) * f;
        }

        if (player != null && TragicConfig.allowStun && player.isPotionActive(TragicPotion.Stun)) {
            if (mc.inGameHasFocus) Mouse.setGrabbed(true);
            mc.mouseHelper.deltaX = 0;
            mc.mouseHelper.deltaY = 0;
            player.movementInput.jump = false;
            player.movementInput.sneak = false;
        }
        /*
         * if (player != null && player.isPotionActive(TragicPotion.Exasperate))
         * {
         * player.setSprinting(false);
         * player.sprintingTicksLeft = 0;
         * }
         */

        if (player != null && TragicConfig.allowFear && player.isPotionActive(TragicPotion.Fear)) {
            buffer++;
            if (rand.nextInt(256) == 0 && buffer >= 9600) {
                buffer = 0;
                player.playSound(sounds[rand.nextInt(sounds.length)], rand.nextFloat() * 0.3F + 0.7F, 1.0F);
            }
            player.swingItem();
        } else {
            buffer = 0;
        }

        try {
            ClientProxy.musicTicker.update();
        } catch (NullPointerException e) {
            TragicMC.logError("An error ocurred while updating the Music Ticker", e);
        }

        if (player == null) return;
        PropertyAmulets amu = PropertyAmulets.get(player);

        if (amu != null) {
            if (player.ticksExisted % 2 != 0) return;

            byte[] levels = new byte[3];
            ItemAmulet[] amulets = new ItemAmulet[3];

            byte i;

            for (i = 0; i < 3; i++) {
                amulets[i] = amu.getActiveAmulet(i);
                levels[i] = AmuletHelper.getAmuletLevel(amu.getActiveAmuletItemStack(i));
            }

            int same = AmuletHelper.getSameAmulets(amulets[0], amulets[1], amulets[2]);

            if (same == 0) {
                for (i = 0; i < 3; i++) {
                    if (amulets[i] != null) amulets[i].onAmuletUpdate(amu, player, player.worldObj, i, levels[i]);
                }
            } else if (same == 12) {
                if (amulets[0] != null) amulets[0].onAmuletUpdate(
                    amu,
                    player,
                    player.worldObj,
                    (byte) 0,
                    AmuletHelper.getAmuletWithHighestLevel(levels[0], levels[1]));
                if (amulets[2] != null) amulets[2].onAmuletUpdate(amu, player, player.worldObj, (byte) 2, levels[2]);
            } else if (same == 13) {
                if (amulets[0] != null) amulets[0].onAmuletUpdate(
                    amu,
                    player,
                    player.worldObj,
                    (byte) 0,
                    AmuletHelper.getAmuletWithHighestLevel(levels[0], levels[2]));
                if (amulets[1] != null) amulets[2].onAmuletUpdate(amu, player, player.worldObj, (byte) 1, levels[1]);
            } else if (same == 23) {
                if (amulets[1] != null) amulets[1].onAmuletUpdate(
                    amu,
                    player,
                    player.worldObj,
                    (byte) 1,
                    AmuletHelper.getAmuletWithHighestLevel(levels[1], levels[2]));
                if (amulets[0] != null) amulets[0].onAmuletUpdate(amu, player, player.worldObj, (byte) 0, levels[0]);
            } else if (same == 123) {
                if (amulets[0] != null) amulets[0].onAmuletUpdate(
                    amu,
                    player,
                    player.worldObj,
                    (byte) 0,
                    AmuletHelper.getAmuletWithHighestLevel(levels[0], levels[1], levels[2]));
            }
        }
    }

    @SubscribeEvent
    public void renderHackedEffects(RenderGameOverlayEvent event) {
        if (event.type != ElementType.HOTBAR || !TragicConfig.allowPotionEffectOverlays) return;

        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer == null) return;

        boolean flag = TragicConfig.allowHacked && mc.thePlayer.isPotionActive(TragicPotion.Hacked);
        boolean flag2 = TragicConfig.allowDivinity && mc.thePlayer.isPotionActive(TragicPotion.Divinity);
        boolean flag3 = TragicConfig.allowConvergence && mc.thePlayer.isPotionActive(TragicPotion.Convergence);
        boolean flag4 = false; // mc.thePlayer.isPotionActive(TragicPotion.Nightmare);
        boolean flag5 = false; // mc.thePlayer.isPotionActive(TragicPotion.Frozen);
        boolean flag6 = TragicConfig.allowCorruption && mc.thePlayer.isPotionActive(TragicPotion.Corruption);

        if (!flag && !flag2 && !flag3 && !flag4 && !flag5 && !flag6) return;

        mc.renderEngine.bindTexture(flag ? hackedTexture : divinityTexture);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        float f = 2.25F;
        float f1 = flag4 ? 0.525F : 0.325F;
        float f2 = flag6 ? 0.265F : 0.225F;;
        float trans = flag || flag4 || flag6 ? f1 - MathHelper.cos(mc.thePlayer.ticksExisted / f) * f2 : 0.1375F;

        float r = flag3 ? 1F : rgb[color][0];
        float g = flag3 ? 0F : rgb[color][1];
        float b = flag3 ? 0F : rgb[color][2];

        if (flag6) r = g = b = 0F;

        if (flag4) {
            GL11.glColor4f(0.1F, 0.1F, 0.1F, trans);
        } else if (!flag5) {
            GL11.glColor4f(r, g, b, trans);
        } else
            if (flag5) {/*
                         * int a = 30 + (20 * mc.thePlayer.getActivePotionEffect(TragicPotion.Frozen).getAmplifier());
                         * PropertyMisc misc = PropertyMisc.get(mc.thePlayer);
                         * int t = misc != null && misc.isFrozen ? misc.frozenInputs : 0;
                         * GL11.glColor4f(0.848F, 0.888F, 0.995F, (float) t / (float) a);
                         */
            }

        GL11.glDisable(GL11.GL_ALPHA_TEST);

        drawTexturedModalRect(0, 0, 0, 0, mc.displayWidth, mc.displayHeight);

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);

        counter++;
        if (counter > 20) {
            counter = 0;
            color = flag2 && TragicConfig.allowDivinityColorChange ? color + 1 : 0;
            if (color >= rgb.length) color = 0;
        }
    }
}
