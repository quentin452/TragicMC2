package tragicneko.tragicmc.client.model;

import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.EntityKurayami;

public class ModelKurayami extends ModelKitsune2 {

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        if (!(entity instanceof EntityKurayami)) return;

        leftLeg.offsetY = 0.0F;
        rightLeg.offsetY = 0.0F;

        EntityKurayami kitsu = (EntityKurayami) entity;
        int firing = kitsu.getFiringTicks();
        int attack = kitsu.getAttackTime();
        int hurt = kitsu.getHurtTime();

        head.rotateAngleY = f3 / (180F / (float) Math.PI);
        head.rotateAngleX = f4 / (180F / (float) Math.PI);

        leftLeg.rotateAngleX = -1.5F * this.simplifyAngle(f, 20.0F) * f1;
        rightLeg.rotateAngleX = 1.5F * this.simplifyAngle(f, 20.0F) * f1;

        leftArm.rotateAngleX = 0.55F * this.simplifyAngle(f, 20.0F) * f1;
        rightArm.rotateAngleX = -0.55F * this.simplifyAngle(f, 20.0F) * f1;

        tailFR.rotateAngleZ = -0.8922867F + this.simplifyAngle(entity.ticksExisted - 45.0F, 70.0F) * 0.14F;
        tailMR.rotateAngleZ = -0.4833219F + this.simplifyAngle(entity.ticksExisted - 15.0F, 70.0F) * 0.14F;
        tailMid.rotateAngleZ = this.simplifyAngle(entity.ticksExisted, 60.0F) * 0.14F;
        tailML.rotateAngleZ = 0.4833219F + this.simplifyAngle(entity.ticksExisted + 50.0F, 70.0F) * 0.14F;
        tailFL.rotateAngleZ = 0.8922867F + this.simplifyAngle(entity.ticksExisted + 30.0F, 70.0F) * 0.14F;

        tailTipFR.rotateAngleZ = this.simplifyAngle(entity.ticksExisted - 45.0F, 70.0F) * 0.04F;
        tailTipMR.rotateAngleZ = this.simplifyAngle(entity.ticksExisted - 15.0F, 70.0F) * 0.04F;
        tailTipMid.rotateAngleZ = this.simplifyAngle(entity.ticksExisted, 70.0F) * 0.04F;
        tailTipML.rotateAngleZ = this.simplifyAngle(entity.ticksExisted + 50.0F, 70.0F) * 0.04F;
        tailTipFL.rotateAngleZ = this.simplifyAngle(entity.ticksExisted + 30.0F, 70.0F) * 0.04F;

        lowerMouth.rotateAngleX = 0.0F;

        if (attack > 0) {
            if (attack >= 10) {
                leftArm.rotateAngleX = -0.186F + this.simplifyAngle(attack, 10.0F) * -0.865F;
                leftArm.rotateAngleZ = -0.48F + this.simplifyAngle(attack, 10.0F) * -0.125F;
            } else {
                rightArm.rotateAngleX = -0.186F + this.simplifyAngle(attack, 10.0F) * -0.865F;
                rightArm.rotateAngleZ = 0.48F + this.simplifyAngle(attack, 10.0F) * 0.125F;
            }
        } else {
            if (firing > 0) {
                leftArm.rotateAngleX = -1.25F + this.simplifyAngle(firing, 40.0F) * 0.265F;
                rightArm.rotateAngleX = -1.25F + this.simplifyAngle(firing, 40.0F) * 0.265F;

                leftArm.rotateAngleZ = -0.48F + this.simplifyAngle(firing, 40.0F) * -0.225F;
                rightArm.rotateAngleZ = 0.48F + this.simplifyAngle(firing, 40.0F) * 0.225F;

                lowerMouth.rotateAngleX = this.simplifyAngle(firing, 40.0F) * 0.215F;
            } else {

                if (hurt > 90 && hurt <= 100) {
                    leftArm.rotateAngleZ = -0.4858931F + this.simplifyAngle(hurt, 10.0F) * 0.215F;
                    rightArm.rotateAngleZ = 0.4858931F + this.simplifyAngle(hurt, 10.0F) * -0.215F;

                    lowerMouth.rotateAngleX = this.simplifyAngle(hurt, 10.0F) * 0.115F;

                    rightEar.rotateAngleX = -0.4089647F + this.simplifyAngle(hurt, 8.0F) * 0.125F;
                    leftEar.rotateAngleX = -0.4089647F + this.simplifyAngle(hurt, 8.0F) * 0.125F;
                } else {
                    leftArm.rotateAngleZ = -0.1858931F;
                    rightArm.rotateAngleZ = 0.1858931F;
                }
            }
        }
    }
}
