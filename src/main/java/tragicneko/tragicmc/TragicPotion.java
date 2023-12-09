package tragicneko.tragicmc;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class TragicPotion extends Potion {

    public static Potion Corruption, Disorientation, Stun, Fear, Cripple, Malnourish, Submission, Inhibit, LeadFoot,
        Hacked, Burned; // Frozen, Deafening, Nightmare, Exasperate, EvilPresence, Bleed;
    public static Potion Flight, AquaSuperiority, Immunity, Resurrection, Harmony, Invulnerability, Clarity,
        Convergence, Divinity;

    private ItemStack stackIcon;

    public TragicPotion(int par1, boolean par2, int par3) {
        super(par1, par2, par3);
    }

    public void setIcon(ItemStack icon) {
        this.stackIcon = icon;
    }

    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc) {
        x += 6;
        y += 7;
        net.minecraft.client.renderer.entity.RenderItem itemRender = new net.minecraft.client.renderer.entity.RenderItem();
        if (this.stackIcon == null) this.stackIcon = new ItemStack(Items.apple);
        net.minecraft.client.renderer.RenderHelper.enableStandardItemLighting();
        itemRender.renderItemIntoGUI(mc.fontRenderer, mc.getTextureManager(), this.stackIcon, x, y, false);
        net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
    }

    public static void load() {
        if (TragicConfig.allowCorruption) Corruption = (new TragicPotion(TragicConfig.idCorruption, true, 0x000000)
            .setPotionName("potion.corruption"));
        if (TragicConfig.allowDisorientation)
            Disorientation = (new TragicPotion(TragicConfig.idDisorientation, true, 0x521A55)
                .setPotionName("potion.disorientation"));
        if (TragicConfig.allowStun) Stun = (new TragicPotion(TragicConfig.idStun, true, 0xC2DC7D)
            .setPotionName("potion.stun")
            .func_111184_a(SharedMonsterAttributes.movementSpeed, "d35fe2b0-2aca-4d5e-b3e3-3fe041dbaf15", -3.0D, 2));
        if (TragicConfig.allowFear)
            Fear = (new TragicPotion(TragicConfig.idFear, true, 0x4A4A5F).setPotionName("potion.fear"));
        if (TragicConfig.allowCripple)
            Cripple = (new TragicPotion(TragicConfig.idCripple, true, 0x640000).setPotionName("potion.cripple")
                .func_111184_a(SharedMonsterAttributes.maxHealth, "04df3109-9bca-4517-8855-8064742028e4", -2.0D, 0));
        if (TragicConfig.allowMalnourish) Malnourish = (new TragicPotion(TragicConfig.idMalnourish, true, 0x59701C)
            .setPotionName("potion.malnourish"));
        if (TragicConfig.allowSubmission) Submission = (new TragicPotion(TragicConfig.idSubmission, true, 0xB152B7)
            .setPotionName("potion.submission"));
        if (TragicConfig.allowInhibit)
            Inhibit = (new TragicPotion(TragicConfig.idInhibit, true, 0x232323).setPotionName("potion.inhibit"));
        if (TragicConfig.allowLeadFoot)
            LeadFoot = (new TragicPotion(TragicConfig.idLeadFoot, true, 0x78AB76).setPotionName("potion.leadFoot"));
        if (TragicConfig.allowHacked)
            Hacked = (new TragicPotion(TragicConfig.idHacked, true, 0xC6F6FF).setPotionName("potion.hacked"));
        if (TragicConfig.allowBurned)
            Burned = (new TragicPotion(TragicConfig.idBurned, true, 0xFF0000).setPotionName("potion.burned"));
        // Deafening = new TragicPotion(TragicConfig.idHacked + 1, true, 0x000000).setPotionName("potion.deafening");
        // Nightmare = new TragicPotion(TragicConfig.idHacked + 2, true, 0x000000).setPotionName("potion.nightmare");
        // Exasperate = new TragicPotion(TragicConfig.idHacked + 3, true, 0x000000).setPotionName("potion.exasperate");
        // EvilPresence = new TragicPotion(TragicConfig.idHacked + 4, true,
        // 0x000000).setPotionName("potion.evilPresence");
        // Frozen = new TragicPotion(72, true,
        // 0x000000).setPotionName("potion.frozen").func_111184_a(SharedMonsterAttributes.movementSpeed,
        // "d35fe2b0-2aca-4d5e-b3e3-3fe041dbaf15", -3.0D, 2);
        /*
         * Bleed = new TragicPotion(TragicConfig.idHacked + 5, true, 0x000000) {
         * @Override
         * public void performEffect(EntityLivingBase entity, int amp)
         * {
         * float bleedOut = 0F;
         * PropertyMisc misc = PropertyMisc.get(entity);
         * if (misc != null) bleedOut = misc.bleedOutTime / 120F;
         * entity.attackEntityFrom(DamageHelper.bleed, 1.0F + bleedOut);
         * TragicMC.logInfo("Bleed out time is " + misc.bleedOutTime + ", damage is " + (bleedOut + 1.0F));
         * }
         * @Override
         * public boolean isReady(int dur, int amp)
         * {
         * int k = 40 >> MathHelper.floor_double(amp / 2);
         * return k > 0 ? dur % k == 0 : true;
         * }
         * }.setPotionName("potion.bleed");
         */

        if (TragicConfig.allowFlight)
            Flight = (new TragicPotion(TragicConfig.idFlight, false, 0xFDDC69).setPotionName("potion.flight"));
        if (TragicConfig.allowAquaSuperiority)
            AquaSuperiority = (new TragicPotion(TragicConfig.idAquaSuperiority, true, 0x69B9FD)
                .setPotionName("potion.aquaSuperiority"));
        if (TragicConfig.allowImmunity)
            Immunity = (new TragicPotion(TragicConfig.idImmunity, false, 0xBABABA).setPotionName("potion.immunity"));
        if (TragicConfig.allowResurrection)
            Resurrection = (new TragicPotion(TragicConfig.idResurrection, false, 0x9DFD69)
                .setPotionName("potion.resurrection"));
        if (TragicConfig.allowHarmony)
            Harmony = (new TragicPotion(TragicConfig.idHarmony, false, 0xE67CED).setPotionName("potion.harmony")
                .func_111184_a(SharedMonsterAttributes.followRange, "ac2b9b0e-2eb2-443f-8ca2-a045f36f9e73", -256.0, 2));
        if (TragicConfig.allowInvulnerability)
            Invulnerability = (new TragicPotion(TragicConfig.idInvulnerability, false, 0xD5ECFF)
                .setPotionName("potion.invulnerability")
                .func_111184_a(
                    SharedMonsterAttributes.movementSpeed,
                    "43d74ab6-d058-40e4-9761-70b0b80b8743",
                    -0.2D,
                    2));
        if (TragicConfig.allowClarity)
            Clarity = (new TragicPotion(TragicConfig.idClarity, false, 0xFFFFFF).setPotionName("potion.clarity"));
        if (TragicConfig.allowConvergence) Convergence = (new TragicPotion(TragicConfig.idConvergence, false, 0x9B2525)
            .setPotionName("potion.convergence"));
        if (TragicConfig.allowDivinity)
            Divinity = (new TragicPotion(TragicConfig.idDivinity, false, 0xFFFFFF).setPotionName("potion.divinity"));
    }

    public static void setPotionIcons() {
        if (Corruption != null) ((TragicPotion) Corruption).setIcon(new ItemStack(TragicItems.DarkParticles));
        if (Disorientation != null) ((TragicPotion) Disorientation).setIcon(new ItemStack(Items.dye));
        if (Stun != null) ((TragicPotion) Stun).setIcon(new ItemStack(TragicItems.LightParticles));
        if (Fear != null) ((TragicPotion) Fear).setIcon(new ItemStack(Blocks.pumpkin, 1));
        if (Cripple != null) ((TragicPotion) Cripple).setIcon(new ItemStack(Items.bone));
        if (Malnourish != null) ((TragicPotion) Malnourish).setIcon(new ItemStack(TragicItems.NourishmentSacrifice));
        if (Submission != null) ((TragicPotion) Submission).setIcon(new ItemStack(TragicItems.Thorns));
        if (Inhibit != null) ((TragicPotion) Inhibit).setIcon(new ItemStack(TragicItems.Ash));
        if (LeadFoot != null) ((TragicPotion) LeadFoot).setIcon(new ItemStack(Blocks.anvil));
        if (Hacked != null) ((TragicPotion) Hacked).setIcon(new ItemStack(TragicBlocks.OverlordBarrier));
        if (Burned != null) ((TragicPotion) Burned).setIcon(new ItemStack(Blocks.fire));
        // ((TragicPotion) Deafening).setIcon(new ItemStack(Items.record_11));
        // ((TragicPotion) Nightmare).setIcon(new ItemStack(TragicItems.CorruptedEgg));
        // ((TragicPotion) Exasperate).setIcon(new ItemStack(TragicItems.EnchantedTears));
        // ((TragicPotion) EvilPresence).setIcon(new ItemStack(TragicItems.PureDarkness));
        // ((TragicPotion) Bleed).setIcon(new ItemStack(TragicItems.BloodSacrifice));
        // if (Frozen != null) ((TragicPotion) Frozen).setIcon(new ItemStack(TragicItems.IceOrb));

        if (Flight != null) ((TragicPotion) Flight).setIcon(new ItemStack(Items.feather));
        if (AquaSuperiority != null)
            ((TragicPotion) AquaSuperiority).setIcon(new ItemStack(TragicItems.EnchantedTears));
        if (Immunity != null) ((TragicPotion) Immunity).setIcon(new ItemStack(Items.milk_bucket));
        if (Resurrection != null) ((TragicPotion) Resurrection).setIcon(new ItemStack(TragicItems.BoneMarrow));
        if (Harmony != null) ((TragicPotion) Harmony).setIcon(new ItemStack(TragicItems.Sushi));
        if (Invulnerability != null)
            ((TragicPotion) Invulnerability).setIcon(new ItemStack(TragicItems.AwakeningStone));
        if (Clarity != null) ((TragicPotion) Clarity).setIcon(new ItemStack(Items.ender_eye));
        if (Convergence != null) ((TragicPotion) Convergence).setIcon(new ItemStack(TragicItems.DoomConsume));
        if (Divinity != null) ((TragicPotion) Divinity).setIcon(new ItemStack(TragicItems.SynapseLink));
    }
}
