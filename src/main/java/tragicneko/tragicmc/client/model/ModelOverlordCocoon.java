package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

import tragicneko.tragicmc.entity.alpha.EntityOverlordCocoon;

/**
 * ModelOverlordCocoon - TragicNeko
 * Created using Tabula 4.1.1
 */
public class ModelOverlordCocoon extends ModelBase {

    public ModelRenderer cocoonBottom;
    public ModelRenderer cocoonFRLayer;
    public ModelRenderer cocoonFLLayer;
    public ModelRenderer cocoonBRLayer;
    public ModelRenderer cocoonBLLayer;
    public ModelRenderer cocoonFRLayer2;
    public ModelRenderer cocoonFRLayer3;
    public ModelRenderer cocoonFRLayer4;
    public ModelRenderer cocoonFRLayer5;
    public ModelRenderer cocoonFRLayer6;
    public ModelRenderer cocoonFRLayer7;
    public ModelRenderer cocoonFRLayer8;
    public ModelRenderer cocoonFRLayer9;
    public ModelRenderer cocoonFLLayer2;
    public ModelRenderer cocoonFLLayer3;
    public ModelRenderer cocoonFLLayer4;
    public ModelRenderer cocoonFLLayer5;
    public ModelRenderer cocoonFLLayer6;
    public ModelRenderer cocoonFLLayer7;
    public ModelRenderer cocoonFLLayer8;
    public ModelRenderer cocoonFLLayer9;
    public ModelRenderer cocoonBRLayer2;
    public ModelRenderer cocoonBRLayer3;
    public ModelRenderer cocoonBRLayer4;
    public ModelRenderer cocoonBRLayer5;
    public ModelRenderer cocoonBRLayer6;
    public ModelRenderer cocoonBRLayer7;
    public ModelRenderer cocoonBRLayer8;
    public ModelRenderer cocoonBRLayer9;
    public ModelRenderer cocoonBLLayer2;
    public ModelRenderer cocoonBLLayer3;
    public ModelRenderer cocoonBLLayer4;
    public ModelRenderer cocoonBLLayer5;
    public ModelRenderer cocoonBLLayer6;
    public ModelRenderer cocoonBLLayer7;
    public ModelRenderer cocoonBLLayer8;
    public ModelRenderer cocoonBLLayer9;

    private static final float offset = 0.1F;

    public ModelOverlordCocoon() {
        this.textureWidth = 128;
        this.textureHeight = 64;

        this.cocoonBottom = new ModelRenderer(this, 0, 0);
        this.cocoonBottom.rotateAngleY = 16.0F;
        this.cocoonBottom.addBox(-8.0F, 6.0F, -8.0F, 16, 2, 16);

        this.cocoonFRLayer = new ModelRenderer(this, 0, 0);
        this.cocoonFRLayer.addBox(-12.0F, 4.0F, -12.0F, 12, 2, 12);
        this.cocoonBottom.addChild(this.cocoonFRLayer);

        this.cocoonFRLayer2 = new ModelRenderer(this, 0, 0);
        this.cocoonFRLayer2.addBox(-14.0F, 1.0F, -14.0F, 14, 3, 14);
        this.cocoonFRLayer.addChild(this.cocoonFRLayer2);

        this.cocoonFRLayer3 = new ModelRenderer(this, 0, 0);
        this.cocoonFRLayer3.addBox(-18.0F, -3.0F, -18.0F, 18, 4, 18);
        this.cocoonFRLayer2.addChild(this.cocoonFRLayer3);

        this.cocoonFRLayer4 = new ModelRenderer(this, 0, 0);
        this.cocoonFRLayer4.addBox(-16.0F, -6.0F, -16.0F, 16, 3, 16);
        this.cocoonFRLayer3.addChild(this.cocoonFRLayer4);

        this.cocoonFRLayer5 = new ModelRenderer(this, 0, 0);
        this.cocoonFRLayer5.addBox(-15.0F, -9.0F, -15.0F, 15, 3, 15);
        this.cocoonFRLayer4.addChild(this.cocoonFRLayer5);

        this.cocoonFRLayer6 = new ModelRenderer(this, 0, 0);
        this.cocoonFRLayer6.addBox(-14.0F, -12.0F, -14.0F, 14, 3, 14);
        this.cocoonFRLayer5.addChild(this.cocoonFRLayer6);

        this.cocoonFRLayer7 = new ModelRenderer(this, 0, 0);
        this.cocoonFRLayer7.addBox(-13.0F, -15.0F, -13.0F, 13, 3, 13);
        this.cocoonFRLayer6.addChild(this.cocoonFRLayer7);

        this.cocoonFRLayer8 = new ModelRenderer(this, 0, 0);
        this.cocoonFRLayer8.addBox(-11.0F, -17.0F, -11.0F, 11, 2, 11);
        this.cocoonFRLayer7.addChild(this.cocoonFRLayer8);

        this.cocoonFRLayer9 = new ModelRenderer(this, 0, 0);
        this.cocoonFRLayer9.addBox(-9.0F, -19.0F, -9.0F, 9, 2, 9);
        this.cocoonFRLayer8.addChild(this.cocoonFRLayer9);

        ModelRenderer cocoonFRLayer10 = new ModelRenderer(this, 0, 0);
        cocoonFRLayer10.addBox(-5.0F, -20.0F, -5.0F, 5, 1, 5);
        this.cocoonFRLayer9.addChild(cocoonFRLayer10);

        ModelRenderer cocoonFRSpike = new ModelRenderer(this, 0, 0);
        cocoonFRSpike.setRotationPoint(0.0F, 0.0F, 0.0F);
        cocoonFRSpike.addBox(-8.0F, -22.0F, -8.0F, 3, 3, 3);
        this.cocoonFRLayer9.addChild(cocoonFRSpike);

        ModelRenderer cocoonFRSpike2 = new ModelRenderer(this, 0, 0);
        cocoonFRSpike2.addBox(-9.0F, -26.0F, -9.0F, 4, 4, 4);
        cocoonFRSpike.addChild(cocoonFRSpike2);

        ModelRenderer cocoonFRSpike3 = new ModelRenderer(this, 0, 0);
        cocoonFRSpike3.addBox(-11.0F, -28.0F, -11.0F, 5, 2, 5);
        cocoonFRSpike.addChild(cocoonFRSpike3);

        this.cocoonFLLayer = new ModelRenderer(this, 0, 0);
        this.cocoonFLLayer.addBox(0.0F, 4.0F, -12.0F, 12, 2, 12);
        this.cocoonBottom.addChild(this.cocoonFLLayer);

        this.cocoonFLLayer2 = new ModelRenderer(this, 0, 0);
        this.cocoonFLLayer2.addBox(0.0F, 1.0F, -14.0F, 14, 3, 14);
        this.cocoonFLLayer.addChild(this.cocoonFLLayer2);

        this.cocoonFLLayer3 = new ModelRenderer(this, 0, 0);
        this.cocoonFLLayer3.addBox(0.0F, -3.0F, -18.0F, 18, 4, 18);
        this.cocoonFLLayer2.addChild(this.cocoonFLLayer3);

        this.cocoonFLLayer4 = new ModelRenderer(this, 0, 0);
        this.cocoonFLLayer4.addBox(0.0F, -6.0F, -16.0F, 16, 3, 16);
        this.cocoonFLLayer3.addChild(this.cocoonFLLayer4);

        this.cocoonFLLayer5 = new ModelRenderer(this, 0, 0);
        this.cocoonFLLayer5.addBox(0.0F, -9.0F, -15.0F, 15, 3, 15);
        this.cocoonFLLayer4.addChild(this.cocoonFLLayer5);

        this.cocoonFLLayer6 = new ModelRenderer(this, 0, 0);
        this.cocoonFLLayer6.addBox(0.0F, -12.0F, -14.0F, 14, 3, 14);
        this.cocoonFLLayer5.addChild(this.cocoonFLLayer6);

        this.cocoonFLLayer7 = new ModelRenderer(this, 0, 0);
        this.cocoonFLLayer7.addBox(0.0F, -15.0F, -13.0F, 13, 3, 13);
        this.cocoonFLLayer6.addChild(this.cocoonFLLayer7);

        this.cocoonFLLayer8 = new ModelRenderer(this, 0, 0);
        this.cocoonFLLayer8.addBox(0.0F, -17.0F, -11.0F, 11, 2, 11);
        this.cocoonFLLayer7.addChild(this.cocoonFLLayer8);

        this.cocoonFLLayer9 = new ModelRenderer(this, 0, 0);
        this.cocoonFLLayer9.addBox(0.0F, -19.0F, -9.0F, 9, 2, 9);
        this.cocoonFLLayer8.addChild(this.cocoonFLLayer9);

        ModelRenderer cocoonFLLayer10 = new ModelRenderer(this, 0, 0);
        cocoonFLLayer10.addBox(0.0F, -20.0F, -5.0F, 5, 1, 5);
        this.cocoonFLLayer9.addChild(cocoonFLLayer10);

        ModelRenderer cocoonFLSpike = new ModelRenderer(this, 0, 0);
        cocoonFLSpike.addBox(5.0F, -22.0F, -8.0F, 3, 3, 3);
        this.cocoonFLLayer9.addChild(cocoonFLSpike);

        ModelRenderer cocoonFLSpike2 = new ModelRenderer(this, 0, 0);
        cocoonFLSpike2.addBox(5.0F, -26.0F, -9.0F, 4, 4, 4);
        cocoonFLSpike.addChild(cocoonFLSpike2);

        ModelRenderer cocoonFLSpike3 = new ModelRenderer(this, 0, 0);
        cocoonFLSpike3.addBox(6.0F, -28.0F, -11.0F, 5, 2, 5);
        cocoonFLSpike.addChild(cocoonFLSpike3);

        this.cocoonBLLayer = new ModelRenderer(this, 0, 0);
        this.cocoonBLLayer.addBox(0.0F, 4.0F, 0.0F, 12, 2, 12);
        this.cocoonBottom.addChild(this.cocoonBLLayer);

        this.cocoonBLLayer2 = new ModelRenderer(this, 0, 0);
        this.cocoonBLLayer2.addBox(0.0F, 1.0F, 0.0F, 14, 3, 14);
        this.cocoonBLLayer.addChild(this.cocoonBLLayer2);

        this.cocoonBLLayer3 = new ModelRenderer(this, 0, 0);
        this.cocoonBLLayer3.addBox(0.0F, -3.0F, 0.0F, 18, 4, 18);
        this.cocoonBLLayer2.addChild(this.cocoonBLLayer3);

        this.cocoonBLLayer4 = new ModelRenderer(this, 0, 0);
        this.cocoonBLLayer4.addBox(0.0F, -6.0F, 0.0F, 16, 3, 16);
        this.cocoonBLLayer3.addChild(this.cocoonBLLayer4);

        this.cocoonBLLayer5 = new ModelRenderer(this, 0, 0);
        this.cocoonBLLayer5.addBox(0.0F, -9.0F, 0.0F, 15, 3, 15);
        this.cocoonBLLayer4.addChild(this.cocoonBLLayer5);

        this.cocoonBLLayer6 = new ModelRenderer(this, 0, 0);
        this.cocoonBLLayer6.addBox(0.0F, -12.0F, 0.0F, 14, 3, 14);
        this.cocoonBLLayer5.addChild(this.cocoonBLLayer6);

        this.cocoonBLLayer7 = new ModelRenderer(this, 0, 0);
        this.cocoonBLLayer7.addBox(0.0F, -15.0F, 0.0F, 13, 3, 13);
        this.cocoonBLLayer6.addChild(this.cocoonBLLayer7);

        this.cocoonBLLayer8 = new ModelRenderer(this, 0, 0);
        this.cocoonBLLayer8.addBox(0.0F, -17.0F, 0.0F, 11, 2, 11);
        this.cocoonBLLayer7.addChild(this.cocoonBLLayer8);

        this.cocoonBLLayer9 = new ModelRenderer(this, 0, 0);
        this.cocoonBLLayer9.addBox(0.0F, -19.0F, 0.0F, 9, 2, 9);
        this.cocoonBLLayer8.addChild(this.cocoonBLLayer9);

        ModelRenderer cocoonBLLayer10 = new ModelRenderer(this, 0, 0);
        cocoonBLLayer10.addBox(0.0F, -20.0F, 0.0F, 5, 1, 5);
        this.cocoonBLLayer9.addChild(cocoonBLLayer10);

        ModelRenderer cocoonBLSpike = new ModelRenderer(this, 0, 0);
        cocoonBLSpike.addBox(5.0F, -22.0F, 5.0F, 3, 3, 3, 0.0F);
        this.cocoonBLLayer9.addChild(cocoonBLSpike);

        ModelRenderer cocoonBLSpike2 = new ModelRenderer(this, 0, 0);
        cocoonBLSpike2.addBox(5.0F, -26.0F, 5.0F, 4, 4, 4, 0.0F);
        cocoonBLSpike.addChild(cocoonBLSpike2);

        ModelRenderer cocoonBLSpike3 = new ModelRenderer(this, 0, 0);
        cocoonBLSpike3.addBox(6.0F, -28.0F, 6.0F, 5, 2, 5, 0.0F);
        cocoonBLSpike.addChild(cocoonBLSpike3);

        this.cocoonBRLayer = new ModelRenderer(this, 0, 0);
        this.cocoonBRLayer.addBox(-12.0F, 4.0F, 0.0F, 12, 2, 12);
        this.cocoonBottom.addChild(this.cocoonBRLayer);

        this.cocoonBRLayer2 = new ModelRenderer(this, 0, 0);
        this.cocoonBRLayer2.addBox(-14.0F, 1.0F, 0.0F, 14, 3, 14);
        this.cocoonBRLayer.addChild(this.cocoonBRLayer2);

        this.cocoonBRLayer3 = new ModelRenderer(this, 0, 0);
        this.cocoonBRLayer3.addBox(-18.0F, -3.0F, 0.0F, 18, 4, 18);
        this.cocoonBRLayer2.addChild(this.cocoonBRLayer3);

        this.cocoonBRLayer4 = new ModelRenderer(this, 0, 0);
        this.cocoonBRLayer4.addBox(-16.0F, -6.0F, 0.0F, 16, 3, 16);
        this.cocoonBRLayer3.addChild(this.cocoonBRLayer4);

        this.cocoonBRLayer5 = new ModelRenderer(this, 0, 0);
        this.cocoonBRLayer5.addBox(-15.0F, -9.0F, 0.0F, 15, 3, 15);
        this.cocoonBRLayer4.addChild(this.cocoonBRLayer5);

        this.cocoonBRLayer6 = new ModelRenderer(this, 0, 0);
        this.cocoonBRLayer6.addBox(-14.0F, -12.0F, 0.0F, 14, 3, 14);
        this.cocoonBRLayer5.addChild(this.cocoonBRLayer6);

        this.cocoonBRLayer7 = new ModelRenderer(this, 0, 0);
        this.cocoonBRLayer7.addBox(-13.0F, -15.0F, 0.0F, 13, 3, 13);
        this.cocoonBRLayer6.addChild(this.cocoonBRLayer7);

        this.cocoonBRLayer8 = new ModelRenderer(this, 0, 0);
        this.cocoonBRLayer8.addBox(-11.0F, -17.0F, 0.0F, 11, 2, 11);
        this.cocoonBRLayer7.addChild(this.cocoonBRLayer8);

        this.cocoonBRLayer9 = new ModelRenderer(this, 0, 0);
        this.cocoonBRLayer9.addBox(-9.0F, -19.0F, 0.0F, 9, 2, 9);
        this.cocoonBRLayer8.addChild(this.cocoonBRLayer9);

        ModelRenderer cocoonBRLayer10 = new ModelRenderer(this, 0, 0);
        cocoonBRLayer10.addBox(-5.0F, -20.0F, 0.0F, 5, 1, 5);
        this.cocoonBRLayer9.addChild(cocoonBRLayer10);

        ModelRenderer cocoonBRSpike = new ModelRenderer(this, 0, 0);
        cocoonBRSpike.addBox(-8.0F, -22.0F, 5.0F, 3, 3, 3);
        this.cocoonBRLayer9.addChild(cocoonBRSpike);

        ModelRenderer cocoonBRSpike2 = new ModelRenderer(this, 0, 0);
        cocoonBRSpike2.addBox(-9.0F, -26.0F, 5.0F, 4, 4, 4);
        cocoonBRSpike.addChild(cocoonBRSpike2);

        ModelRenderer cocoonBRSpike3 = new ModelRenderer(this, 0, 0);
        cocoonBRSpike3.addBox(-11.0F, -28.0F, 6.0F, 5, 2, 5);
        cocoonBRSpike.addChild(cocoonBRSpike3);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.cocoonBottom.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        this.cocoonBottom.offsetY = 0.4F;
        this.cocoonBottom.rotateAngleY = 0F;
        if (!(entity instanceof EntityOverlordCocoon)) return;
        EntityOverlordCocoon cocoon = (EntityOverlordCocoon) entity;
        this.cocoonBottom.offsetY = 1.0F;

        int death = MathHelper.clamp_int(cocoon.deathTime / 10, 0, 19);

        if (death > 0) {
            cocoonFRLayer9.offsetX = -offset;
            cocoonFRLayer9.offsetZ = -offset;
            cocoonFLLayer9.offsetX = offset;
            cocoonFLLayer9.offsetZ = -offset;
            cocoonBRLayer9.offsetX = -offset;
            cocoonBRLayer9.offsetZ = offset;
            cocoonBLLayer9.offsetX = offset;
            cocoonBLLayer9.offsetZ = offset;

            if (death > 1) {
                cocoonFRLayer8.offsetX = -offset;
                cocoonFRLayer8.offsetZ = -offset;
                cocoonFLLayer8.offsetX = offset;
                cocoonFLLayer8.offsetZ = -offset;
                cocoonBRLayer8.offsetX = -offset;
                cocoonBRLayer8.offsetZ = offset;
                cocoonBLLayer8.offsetX = offset;
                cocoonBLLayer8.offsetZ = offset;

                if (death > 2) {
                    cocoonFRLayer7.offsetX = -offset;
                    cocoonFRLayer7.offsetZ = -offset;
                    cocoonFLLayer7.offsetX = offset;
                    cocoonFLLayer7.offsetZ = -offset;
                    cocoonBRLayer7.offsetX = -offset;
                    cocoonBRLayer7.offsetZ = offset;
                    cocoonBLLayer7.offsetX = offset;
                    cocoonBLLayer7.offsetZ = offset;

                    if (death > 3) {
                        cocoonFRLayer6.offsetX = -offset;
                        cocoonFRLayer6.offsetZ = -offset;
                        cocoonFLLayer6.offsetX = offset;
                        cocoonFLLayer6.offsetZ = -offset;
                        cocoonBRLayer6.offsetX = -offset;
                        cocoonBRLayer6.offsetZ = offset;
                        cocoonBLLayer6.offsetX = offset;
                        cocoonBLLayer6.offsetZ = offset;

                        if (death > 4) {
                            cocoonFRLayer5.offsetX = -offset;
                            cocoonFRLayer5.offsetZ = -offset;
                            cocoonFLLayer5.offsetX = offset;
                            cocoonFLLayer5.offsetZ = -offset;
                            cocoonBRLayer5.offsetX = -offset;
                            cocoonBRLayer5.offsetZ = offset;
                            cocoonBLLayer5.offsetX = offset;
                            cocoonBLLayer5.offsetZ = offset;

                            if (death > 5) {
                                cocoonFRLayer4.offsetX = -offset;
                                cocoonFRLayer4.offsetZ = -offset;
                                cocoonFLLayer4.offsetX = offset;
                                cocoonFLLayer4.offsetZ = -offset;
                                cocoonBRLayer4.offsetX = -offset;
                                cocoonBRLayer4.offsetZ = offset;
                                cocoonBLLayer4.offsetX = offset;
                                cocoonBLLayer4.offsetZ = offset;

                                if (death > 6) {
                                    cocoonFRLayer3.offsetX = -offset;
                                    cocoonFRLayer3.offsetZ = -offset;
                                    cocoonFLLayer3.offsetX = offset;
                                    cocoonFLLayer3.offsetZ = -offset;
                                    cocoonBRLayer3.offsetX = -offset;
                                    cocoonBRLayer3.offsetZ = offset;
                                    cocoonBLLayer3.offsetX = offset;
                                    cocoonBLLayer3.offsetZ = offset;

                                    if (death > 7) {
                                        cocoonFRLayer2.offsetX = -offset;
                                        cocoonFRLayer2.offsetZ = -offset;
                                        cocoonFLLayer2.offsetX = offset;
                                        cocoonFLLayer2.offsetZ = -offset;
                                        cocoonBRLayer2.offsetX = -offset;
                                        cocoonBRLayer2.offsetZ = offset;
                                        cocoonBLLayer2.offsetX = offset;
                                        cocoonBLLayer2.offsetZ = offset;

                                        if (death > 8) {
                                            cocoonFRLayer.offsetX = -offset;
                                            cocoonFRLayer.offsetZ = -offset;
                                            cocoonFLLayer.offsetX = offset;
                                            cocoonFLLayer.offsetZ = -offset;
                                            cocoonBRLayer.offsetX = -offset;
                                            cocoonBRLayer.offsetZ = offset;
                                            cocoonBLLayer.offsetX = offset;
                                            cocoonBLLayer.offsetZ = offset;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            cocoonFRLayer9.offsetX = cocoonFRLayer9.offsetZ = cocoonFLLayer9.offsetX = cocoonFLLayer9.offsetZ = 0;
            cocoonBRLayer9.offsetX = cocoonBRLayer9.offsetZ = cocoonBLLayer9.offsetX = cocoonBLLayer9.offsetZ = 0;

            cocoonFRLayer8.offsetX = cocoonFRLayer8.offsetZ = cocoonFLLayer8.offsetX = cocoonFLLayer8.offsetZ = 0;
            cocoonBRLayer8.offsetX = cocoonBRLayer8.offsetZ = cocoonBLLayer8.offsetX = cocoonBLLayer8.offsetZ = 0;

            cocoonFRLayer7.offsetX = cocoonFRLayer7.offsetZ = cocoonFLLayer7.offsetX = cocoonFLLayer7.offsetZ = 0;
            cocoonBRLayer7.offsetX = cocoonBRLayer7.offsetZ = cocoonBLLayer7.offsetX = cocoonBLLayer7.offsetZ = 0;

            cocoonFRLayer6.offsetX = cocoonFRLayer6.offsetZ = cocoonFLLayer6.offsetX = cocoonFLLayer6.offsetZ = 0;
            cocoonBRLayer6.offsetX = cocoonBRLayer6.offsetZ = cocoonBLLayer6.offsetX = cocoonBLLayer6.offsetZ = 0;

            cocoonFRLayer5.offsetX = cocoonFRLayer5.offsetZ = cocoonFLLayer5.offsetX = cocoonFLLayer5.offsetZ = 0;
            cocoonBRLayer5.offsetX = cocoonBRLayer5.offsetZ = cocoonBLLayer5.offsetX = cocoonBLLayer5.offsetZ = 0;

            cocoonFRLayer4.offsetX = cocoonFRLayer4.offsetZ = cocoonFLLayer4.offsetX = cocoonFLLayer4.offsetZ = 0;
            cocoonBRLayer4.offsetX = cocoonBRLayer4.offsetZ = cocoonBLLayer4.offsetX = cocoonBLLayer4.offsetZ = 0;

            cocoonFRLayer3.offsetX = cocoonFRLayer3.offsetZ = cocoonFLLayer3.offsetX = cocoonFLLayer3.offsetZ = 0;
            cocoonBRLayer3.offsetX = cocoonBRLayer3.offsetZ = cocoonBLLayer3.offsetX = cocoonBLLayer3.offsetZ = 0;

            cocoonFRLayer2.offsetX = cocoonFRLayer2.offsetZ = cocoonFLLayer2.offsetX = cocoonFLLayer2.offsetZ = 0;
            cocoonBRLayer2.offsetX = cocoonBRLayer2.offsetZ = cocoonBLLayer2.offsetX = cocoonBLLayer2.offsetZ = 0;

            cocoonFRLayer.offsetX = cocoonFRLayer.offsetZ = cocoonFLLayer.offsetX = cocoonFLLayer.offsetZ = 0;
            cocoonBRLayer.offsetX = cocoonBRLayer.offsetZ = cocoonBLLayer.offsetX = cocoonBLLayer.offsetZ = 0;
        }
    }
}
