package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayBerserker extends Doomsday {

    public DoomsdayBerserker(int id) {
        super(id, EnumDoomType.OVERFLOW);
    }

    @Override
    public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
        int overflow = this.getOverflow(doom);
        int a = MathHelper.ceiling_double_int(overflow / 10);

        MathHelper.clamp_int(a, 1, 10);
        if (crucMoment) a *= 2;
        TragicMC.logInfo("Good effect is " + a);

        player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 90 * a, a));
        player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 90 * a, a));

        if (crucMoment) a /= 4;
        TragicMC.logInfo("Bad effect is " + a);

        player.addPotionEffect(new PotionEffect(Potion.confusion.id, 70 * a));
        if (TragicConfig.allowSubmission)
            player.addPotionEffect(new PotionEffect(TragicPotion.Submission.id, 60 * a, a));
    }

    @Override
    public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
        int overflow = this.getOverflow(doom);
        int a = MathHelper.ceiling_double_int(overflow / 10);

        MathHelper.clamp_int(a, 1, 10);

        player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 30 * a, 0));
        player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 30 * a, 0));

        player.addPotionEffect(new PotionEffect(Potion.confusion.id, 120 * a, a));
        if (TragicConfig.allowSubmission)
            player.addPotionEffect(new PotionEffect(TragicPotion.Submission.id, 120 * a, a));
    }
}
