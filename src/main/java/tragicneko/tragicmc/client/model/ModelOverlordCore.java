package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.EntityStatue;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCore;

public class ModelOverlordCore extends ModelBase {

    private ModelRenderer head;
    private ModelRenderer lowerJaw;

    private ModelRenderer pincerLeft;
    private ModelRenderer pincerRight;

    private ModelRenderer neck;
    private ModelRenderer brainSac;
    private ModelRenderer brain;

    private ModelRenderer body;

    private ModelRenderer upperLegFR;
    private ModelRenderer lowerLegFR;
    private ModelRenderer upperLegFL;
    private ModelRenderer lowerLegFL;
    private ModelRenderer upperLegFMR;
    private ModelRenderer lowerLegFMR;
    private ModelRenderer upperLegFML;
    private ModelRenderer lowerLegFML;
    private ModelRenderer legBMR;
    private ModelRenderer legBML;
    private ModelRenderer legBR;
    private ModelRenderer legBL;

    public ModelOverlordCore() {
        textureWidth = 128;
        textureHeight = 128;

        head = new ModelRenderer(this, 0, 50);
        head.addBox(-8F, -8F, -8F, 16, 6, 15);
        head.setRotationPoint(0F, 4F, 0F);

        ModelRenderer headTop = new ModelRenderer(this, 64, 50);
        headTop.addBox(-5F, -11F, -5F, 10, 3, 14);
        head.addChild(headTop);

        ModelRenderer headBottom = new ModelRenderer(this, 0, 72);
        headBottom.addBox(-6F, -2F, -7F, 12, 5, 8);
        head.addChild(headBottom);

        ModelRenderer gillRight = new ModelRenderer(this, 0, 0);
        gillRight.addBox(-7F, -2F, -1F, 1, 4, 1);
        head.addChild(gillRight);

        ModelRenderer gillRight2 = new ModelRenderer(this, 0, 0);
        gillRight2.addBox(-7F, -2F, -3F, 1, 3, 1);
        head.addChild(gillRight2);

        ModelRenderer gillRight3 = new ModelRenderer(this, 0, 0);
        gillRight3.addBox(-7F, -2F, -5F, 1, 2, 1);
        head.addChild(gillRight3);

        ModelRenderer gillLeft = new ModelRenderer(this, 0, 0);
        gillLeft.addBox(6F, -2F, -1F, 1, 4, 1);
        head.addChild(gillLeft);

        ModelRenderer gillLeft2 = new ModelRenderer(this, 0, 0);
        gillLeft2.addBox(6F, -2F, -3F, 1, 3, 1);
        head.addChild(gillLeft2);

        ModelRenderer gillLeft3 = new ModelRenderer(this, 0, 0);
        gillLeft3.addBox(6F, -2F, -5F, 1, 2, 1);
        head.addChild(gillLeft3);

        pincerLeft = new ModelRenderer(this, 80, 0);
        pincerLeft.addBox(-2F, -1F, -6F, 2, 4, 7);
        pincerLeft.setRotationPoint(-5F, 2F, -7F);
        head.addChild(pincerLeft);

        ModelRenderer pincerTipLeft = new ModelRenderer(this, 80, 12);
        pincerTipLeft.addBox(0F, 0F, -7F, 4, 3, 1);
        pincerLeft.addChild(pincerTipLeft);

        pincerRight = new ModelRenderer(this, 80, 0);
        pincerRight.addBox(0F, -1F, -7F, 2, 4, 7);
        pincerRight.setRotationPoint(5F, 2F, -6F);
        head.addChild(pincerRight);

        ModelRenderer pincerTipRight = new ModelRenderer(this, 80, 12);
        pincerTipRight.addBox(-4F, 0F, -8F, 4, 3, 1);
        pincerRight.addChild(pincerTipRight);

        lowerJaw = new ModelRenderer(this, 112, 72);
        lowerJaw.addBox(-2F, 3F, -7F, 4, 1, 3);
        head.addChild(lowerJaw);

        ModelRenderer eye = new ModelRenderer(this, 32, 90);
        eye.addBox(-2F, -2F, -9F, 4, 4, 2);
        head.addChild(eye);

        ModelRenderer eyeSocketLeft = new ModelRenderer(this, 46, 90);
        eyeSocketLeft.addBox(2F, -1F, -8F, 3, 1, 1);
        head.addChild(eyeSocketLeft);

        ModelRenderer eyeSocketRight = new ModelRenderer(this, 46, 90);
        eyeSocketRight.addBox(-5F, -1F, -8F, 3, 1, 1);
        head.addChild(eyeSocketRight);

        ModelRenderer eye2 = new ModelRenderer(this, 32, 98);
        eye2.addBox(1F, -6F, -9F, 3, 3, 1);
        head.addChild(eye2);

        ModelRenderer eye3 = new ModelRenderer(this, 32, 98);
        eye3.addBox(-5F, -5F, -9F, 3, 3, 1);
        head.addChild(eye3);

        ModelRenderer eye4 = new ModelRenderer(this, 32, 98);
        eye4.addBox(5F, -7F, -9F, 2, 2, 1);
        head.addChild(eye4);

        ModelRenderer eye5 = new ModelRenderer(this, 32, 98);
        eye5.addBox(-7F, -6F, -9F, 1, 1, 1);
        head.addChild(eye5);

        ModelRenderer eye6 = new ModelRenderer(this, 32, 98);
        eye6.addBox(-9F, -7F, 0F, 1, 2, 2);
        head.addChild(eye6);

        ModelRenderer eye7 = new ModelRenderer(this, 32, 98);
        eye7.addBox(-1F, -7F, -9F, 1, 1, 1);
        head.addChild(eye7);

        ModelRenderer eye8 = new ModelRenderer(this, 32, 98);
        eye8.addBox(8F, -7F, 0F, 1, 3, 3);
        head.addChild(eye8);

        ModelRenderer eye9 = new ModelRenderer(this, 32, 98);
        eye9.addBox(8F, -4F, -5F, 1, 1, 1);
        head.addChild(eye9);

        ModelRenderer eye10 = new ModelRenderer(this, 32, 98);
        eye10.addBox(-9F, -4F, -3F, 1, 1, 1);
        head.addChild(eye10);

        ModelRenderer eye11 = new ModelRenderer(this, 32, 98);
        eye11.addBox(-9F, -6F, -6F, 1, 2, 2);
        head.addChild(eye11);

        neck = new ModelRenderer(this, 50, 72);
        neck.addBox(-3F, -4F, 1F, 6, 6, 9);
        neck.setRotationPoint(0F, 4F, 0F);

        ModelRenderer abdomen = new ModelRenderer(this, 0, 18);
        abdomen.addBox(-2F, 2F, 5F, 3, 3, 4);
        neck.addChild(abdomen);

        brain = new ModelRenderer(this, 0, 90);
        brain.addBox(-6F, -5F, 0F, 6, 6, 6);
        brain.setRotationPoint(0F, 0F, 11F);

        ModelRenderer brain2 = new ModelRenderer(this, 0, 90);
        brain2.addBox(-1F, -4F, -1F, 6, 6, 6);
        brain.addChild(brain2);

        ModelRenderer brain3 = new ModelRenderer(this, 0, 90);
        brain3.addBox(1F, -5F, 15F, 6, 6, 6);
        brain.addChild(brain3);

        ModelRenderer brain4 = new ModelRenderer(this, 0, 90);
        brain4.addBox(4F, -5F, 18F, 6, 6, 6);
        brain.addChild(brain4);

        ModelRenderer brain5 = new ModelRenderer(this, 0, 90);
        brain5.addBox(0F, -6F, 10F, 6, 6, 6);
        brain.addChild(brain5);

        ModelRenderer brain6 = new ModelRenderer(this, 0, 90);
        brain6.addBox(0F, 0F, 3F, 6, 6, 6);
        brain.addChild(brain6);

        ModelRenderer brain7 = new ModelRenderer(this, 0, 90);
        brain7.addBox(-4F, -7F, 3F, 6, 6, 6);
        brain.addChild(brain7);

        ModelRenderer brain8 = new ModelRenderer(this, 0, 90);
        brain8.addBox(-3F, -1F, 6F, 6, 6, 6);
        brain.addChild(brain8);

        ModelRenderer brain9 = new ModelRenderer(this, 0, 90);
        brain9.addBox(0F, 1F, 9F, 6, 6, 6);
        brain.addChild(brain9);

        ModelRenderer brain10 = new ModelRenderer(this, 0, 90);
        brain10.addBox(-1F, 2F, 0F, 6, 6, 6);
        brain.addChild(brain10);

        ModelRenderer brain11 = new ModelRenderer(this, 0, 90);
        brain11.addBox(-5F, 0F, 13F, 6, 6, 6);
        brain.addChild(brain11);

        ModelRenderer brain12 = new ModelRenderer(this, 0, 90);
        brain12.addBox(-5F, 0F, 1F, 6, 6, 6);
        brain.addChild(brain12);

        brainSac = new ModelRenderer(this, 2, 0);
        brainSac.addBox(-12F, -14F, 10F, 24, 18, 28);
        brainSac.setRotationPoint(0F, 4F, 0F);

        ModelRenderer brainVein = new ModelRenderer(this, 0, 0);
        brainVein.addBox(-9F, -2F, 9F, 1, 7, 1);
        brainSac.addChild(brainVein);

        ModelRenderer brainVein2 = new ModelRenderer(this, 0, 0);
        brainVein2.addBox(-10F, -12F, 9F, 1, 11, 1);
        brainSac.addChild(brainVein2);

        ModelRenderer brainVein3 = new ModelRenderer(this, 0, 0);
        brainVein3.addBox(-9F, -12F, 9F, 10, 1, 1);
        brainSac.addChild(brainVein3);

        ModelRenderer brainVein4 = new ModelRenderer(this, 0, 0);
        brainVein4.addBox(-4F, -15F, 9F, 1, 3, 1);
        brainSac.addChild(brainVein4);

        ModelRenderer brainVein5 = new ModelRenderer(this, 0, 0);
        brainVein5.addBox(-8F, -15F, 9F, 1, 3, 1);
        brainSac.addChild(brainVein5);

        ModelRenderer brainVein6 = new ModelRenderer(this, 0, 0);
        brainVein6.addBox(-13F, 0F, 9F, 4, 1, 1);
        brainSac.addChild(brainVein6);

        ModelRenderer brainVein7 = new ModelRenderer(this, 0, 0);
        brainVein7.addBox(1F, -13F, 9F, 1, 9, 1);
        brainSac.addChild(brainVein7);

        ModelRenderer brainVein8 = new ModelRenderer(this, 0, 0);
        brainVein8.addBox(2F, -5F, 9F, 11, 1, 1);
        brainSac.addChild(brainVein8);

        ModelRenderer brainVein9 = new ModelRenderer(this, 0, 0);
        brainVein9.addBox(6F, -10F, 9F, 1, 5, 1);
        brainSac.addChild(brainVein9);

        ModelRenderer brainVein10 = new ModelRenderer(this, 0, 0);
        brainVein10.addBox(7F, -15F, 9F, 1, 6, 1);
        brainSac.addChild(brainVein10);

        ModelRenderer brainVein11 = new ModelRenderer(this, 0, 0);
        brainVein11.addBox(2F, -13F, 9F, 5, 1, 1);
        brainSac.addChild(brainVein11);

        ModelRenderer brainVein12 = new ModelRenderer(this, 0, 0);
        brainVein12.addBox(7F, -15F, 10F, 1, 1, 8);
        brainSac.addChild(brainVein12);

        ModelRenderer brainVein13 = new ModelRenderer(this, 0, 0);
        brainVein13.addBox(-10F, -15F, 10F, 7, 1, 1);
        brainSac.addChild(brainVein13);

        ModelRenderer brainVein14 = new ModelRenderer(this, 0, 0);
        brainVein14.addBox(-10F, -15F, 11F, 1, 1, 12);
        brainSac.addChild(brainVein14);

        ModelRenderer brainVein15 = new ModelRenderer(this, 0, 0);
        brainVein15.addBox(-5F, -15F, 11F, 1, 1, 6);
        brainSac.addChild(brainVein15);

        ModelRenderer brainVein16 = new ModelRenderer(this, 0, 0);
        brainVein16.addBox(-6F, -15F, 16F, 1, 1, 10);
        brainSac.addChild(brainVein16);

        ModelRenderer brainVein17 = new ModelRenderer(this, 0, 0);
        brainVein17.addBox(-12F, -5F, 9F, 2, 1, 1);
        brainSac.addChild(brainVein17);

        ModelRenderer brainVein18 = new ModelRenderer(this, 0, 0);
        brainVein18.addBox(2F, -15F, 13F, 5, 1, 1);
        brainSac.addChild(brainVein18);

        ModelRenderer brainVein19 = new ModelRenderer(this, 0, 0);
        brainVein19.addBox(1F, -15F, 13F, 1, 1, 9);
        brainSac.addChild(brainVein19);

        ModelRenderer brainVein20 = new ModelRenderer(this, 0, 0);
        brainVein20.addBox(-1F, -15F, 14F, 1, 1, 13);
        brainSac.addChild(brainVein20);

        ModelRenderer brainVein21 = new ModelRenderer(this, 0, 0);
        brainVein21.addBox(0F, -15F, 14F, 1, 1, 1);
        brainSac.addChild(brainVein21);

        ModelRenderer brainVein22 = new ModelRenderer(this, 0, 0);
        brainVein22.addBox(-9F, -15F, 22F, 3, 1, 1);
        brainSac.addChild(brainVein22);

        ModelRenderer brainVein23 = new ModelRenderer(this, 0, 0);
        brainVein23.addBox(2F, -15F, 20F, 8, 1, 1);
        brainSac.addChild(brainVein23);

        ModelRenderer brainVein24 = new ModelRenderer(this, 0, 0);
        brainVein24.addBox(8F, -15F, 17F, 3, 1, 1);
        brainSac.addChild(brainVein24);

        ModelRenderer brainVein25 = new ModelRenderer(this, 0, 0);
        brainVein25.addBox(10F, -15F, 18F, 1, 1, 10);
        brainSac.addChild(brainVein25);

        ModelRenderer brainVein26 = new ModelRenderer(this, 0, 0);
        brainVein26.addBox(0F, -15F, 21F, 1, 1, 1);
        brainSac.addChild(brainVein26);

        ModelRenderer brainVein27 = new ModelRenderer(this, 0, 0);
        brainVein27.addBox(-5F, -15F, 25F, 1, 1, 9);
        brainSac.addChild(brainVein27);

        ModelRenderer brainVein28 = new ModelRenderer(this, 0, 0);
        brainVein28.addBox(0F, -15F, 26F, 1, 1, 8);
        brainSac.addChild(brainVein28);

        ModelRenderer brainVein29 = new ModelRenderer(this, 0, 0);
        brainVein29.addBox(-5F, -15F, 19F, 4, 1, 1);
        brainSac.addChild(brainVein29);

        ModelRenderer brainVein30 = new ModelRenderer(this, 0, 0);
        brainVein30.addBox(4F, -15F, 21F, 1, 1, 12);
        brainSac.addChild(brainVein30);

        ModelRenderer brainVein31 = new ModelRenderer(this, 0, 0);
        brainVein31.addBox(-13F, -15F, 14F, 3, 1, 1);
        brainSac.addChild(brainVein31);

        ModelRenderer brainVein32 = new ModelRenderer(this, 0, 0);
        brainVein32.addBox(-13F, -5F, 9F, 1, 1, 12);
        brainSac.addChild(brainVein32);

        ModelRenderer brainVein33 = new ModelRenderer(this, 0, 0);
        brainVein33.addBox(-13F, -4F, 14F, 1, 3, 1);
        brainSac.addChild(brainVein33);

        ModelRenderer brainVein34 = new ModelRenderer(this, 0, 0);
        brainVein34.addBox(-13F, -15F, 15F, 1, 5, 1);
        brainSac.addChild(brainVein34);

        ModelRenderer brainVein35 = new ModelRenderer(this, 0, 0);
        brainVein35.addBox(-13F, -11F, 16F, 1, 6, 1);
        brainSac.addChild(brainVein35);

        ModelRenderer brainVein36 = new ModelRenderer(this, 0, 0);
        brainVein36.addBox(-13F, -4F, 20F, 1, 1, 10);
        brainSac.addChild(brainVein36);

        ModelRenderer brainVein37 = new ModelRenderer(this, 0, 0);
        brainVein37.addBox(-13F, -1F, 9F, 1, 1, 6);
        brainSac.addChild(brainVein37);

        ModelRenderer brainVein38 = new ModelRenderer(this, 0, 0);
        brainVein38.addBox(-13F, -8F, 20F, 1, 1, 8);
        brainSac.addChild(brainVein38);

        ModelRenderer brainVein39 = new ModelRenderer(this, 0, 0);
        brainVein39.addBox(-13F, -7F, 20F, 1, 2, 1);
        brainSac.addChild(brainVein39);

        ModelRenderer brainVein40 = new ModelRenderer(this, 0, 0);
        brainVein40.addBox(-13F, -2F, 15F, 1, 1, 10);
        brainSac.addChild(brainVein40);

        ModelRenderer brainVein41 = new ModelRenderer(this, 0, 0);
        brainVein41.addBox(-13F, -1F, 24F, 1, 1, 7);
        brainSac.addChild(brainVein41);

        ModelRenderer brainVein42 = new ModelRenderer(this, 0, 0);
        brainVein42.addBox(-13F, -4F, 30F, 1, 3, 1);
        brainSac.addChild(brainVein42);

        ModelRenderer brainVein43 = new ModelRenderer(this, 0, 0);
        brainVein43.addBox(-13F, -7F, 27F, 1, 1, 8);
        brainSac.addChild(brainVein43);

        ModelRenderer brainVein44 = new ModelRenderer(this, 0, 0);
        brainVein44.addBox(-13F, -11F, 26F, 1, 3, 1);
        brainSac.addChild(brainVein44);

        ModelRenderer brainVein45 = new ModelRenderer(this, 0, 0);
        brainVein45.addBox(-13F, -11F, 27F, 1, 1, 12);
        brainSac.addChild(brainVein45);

        ModelRenderer brainVein46 = new ModelRenderer(this, 0, 0);
        brainVein46.addBox(-13F, -15F, 32F, 1, 4, 1);
        brainSac.addChild(brainVein46);

        ModelRenderer brainVein47 = new ModelRenderer(this, 0, 0);
        brainVein47.addBox(-13F, -10F, 34F, 1, 3, 1);
        brainSac.addChild(brainVein47);

        ModelRenderer brainVein48 = new ModelRenderer(this, 0, 0);
        brainVein48.addBox(-13F, -3F, 31F, 1, 1, 8);
        brainSac.addChild(brainVein48);

        ModelRenderer brainVein49 = new ModelRenderer(this, 0, 0);
        brainVein49.addBox(-13F, -1F, 18F, 1, 3, 1);
        brainSac.addChild(brainVein49);

        ModelRenderer brainVein50 = new ModelRenderer(this, 0, 0);
        brainVein50.addBox(-13F, 1F, 19F, 1, 1, 9);
        brainSac.addChild(brainVein50);

        ModelRenderer brainVein51 = new ModelRenderer(this, 0, 0);
        brainVein51.addBox(-13F, 0F, 27F, 1, 1, 1);
        brainSac.addChild(brainVein51);

        ModelRenderer brainVein52 = new ModelRenderer(this, 0, 0);
        brainVein52.addBox(-13F, -13F, 16F, 1, 1, 13);
        brainSac.addChild(brainVein52);

        ModelRenderer brainVein53 = new ModelRenderer(this, 0, 0);
        brainVein53.addBox(-13F, -15F, 28F, 1, 2, 1);
        brainSac.addChild(brainVein53);

        ModelRenderer brainVein54 = new ModelRenderer(this, 0, 0);
        brainVein54.addBox(-12F, -15F, 28F, 1, 1, 5);
        brainSac.addChild(brainVein54);

        ModelRenderer brainVein55 = new ModelRenderer(this, 0, 0);
        brainVein55.addBox(-11F, -15F, 30F, 6, 1, 1);
        brainSac.addChild(brainVein55);

        ModelRenderer brainVein56 = new ModelRenderer(this, 0, 0);
        brainVein56.addBox(1F, -15F, 33F, 6, 1, 1);
        brainSac.addChild(brainVein56);

        ModelRenderer brainVein57 = new ModelRenderer(this, 0, 0);
        brainVein57.addBox(7F, -15F, 21F, 1, 1, 8);
        brainSac.addChild(brainVein57);

        ModelRenderer brainVein58 = new ModelRenderer(this, 0, 0);
        brainVein58.addBox(9F, -15F, 27F, 1, 1, 11);
        brainSac.addChild(brainVein58);

        ModelRenderer brainVein59 = new ModelRenderer(this, 0, 0);
        brainVein59.addBox(8F, -15F, 28F, 1, 1, 1);
        brainSac.addChild(brainVein59);

        ModelRenderer brainVein60 = new ModelRenderer(this, 0, 0);
        brainVein60.addBox(6F, -15F, 34F, 1, 1, 5);
        brainSac.addChild(brainVein60);

        ModelRenderer brainVein61 = new ModelRenderer(this, 0, 0);
        brainVein61.addBox(7F, -15F, 38F, 3, 1, 1);
        brainSac.addChild(brainVein61);

        ModelRenderer brainVein62 = new ModelRenderer(this, 0, 0);
        brainVein62.addBox(-1F, -15F, 33F, 1, 1, 6);
        brainSac.addChild(brainVein62);

        ModelRenderer brainVein63 = new ModelRenderer(this, 0, 0);
        brainVein63.addBox(-4F, -15F, 33F, 1, 1, 6);
        brainSac.addChild(brainVein63);

        ModelRenderer brainVein64 = new ModelRenderer(this, 0, 0);
        brainVein64.addBox(-9F, -15F, 31F, 1, 1, 8);
        brainSac.addChild(brainVein64);

        ModelRenderer brainVein65 = new ModelRenderer(this, 0, 0);
        brainVein65.addBox(12F, -4F, 9F, 1, 1, 10);
        brainSac.addChild(brainVein65);

        ModelRenderer brainVein66 = new ModelRenderer(this, 0, 0);
        brainVein66.addBox(12F, -1F, 9F, 1, 1, 6);
        brainSac.addChild(brainVein66);

        ModelRenderer brainVein67 = new ModelRenderer(this, 0, 0);
        brainVein67.addBox(3F, 0F, 9F, 10, 1, 1);
        brainSac.addChild(brainVein67);

        ModelRenderer brainVein68 = new ModelRenderer(this, 0, 0);
        brainVein68.addBox(12F, -4F, 19F, 1, 6, 1);
        brainSac.addChild(brainVein68);

        ModelRenderer brainVein69 = new ModelRenderer(this, 0, 0);
        brainVein69.addBox(12F, -8F, 14F, 1, 1, 7);
        brainSac.addChild(brainVein69);

        ModelRenderer brainVein70 = new ModelRenderer(this, 0, 0);
        brainVein70.addBox(12F, -11F, 13F, 1, 4, 1);
        brainSac.addChild(brainVein70);

        ModelRenderer brainVein71 = new ModelRenderer(this, 0, 0);
        brainVein71.addBox(12F, -12F, 13F, 1, 1, 6);
        brainSac.addChild(brainVein71);

        ModelRenderer brainVein72 = new ModelRenderer(this, 0, 0);
        brainVein72.addBox(12F, -11F, 18F, 1, 1, 6);
        brainSac.addChild(brainVein72);

        ModelRenderer brainVein73 = new ModelRenderer(this, 0, 0);
        brainVein73.addBox(12F, -15F, 24F, 1, 5, 1);
        brainSac.addChild(brainVein73);

        ModelRenderer brainVein74 = new ModelRenderer(this, 0, 0);
        brainVein74.addBox(11F, -15F, 24F, 1, 1, 1);
        brainSac.addChild(brainVein74);

        ModelRenderer brainVein75 = new ModelRenderer(this, 0, 0);
        brainVein75.addBox(12F, -3F, 17F, 1, 4, 1);
        brainSac.addChild(brainVein75);

        ModelRenderer brainVein76 = new ModelRenderer(this, 0, 0);
        brainVein76.addBox(12F, 1F, 20F, 1, 1, 8);
        brainSac.addChild(brainVein76);

        ModelRenderer brainVein77 = new ModelRenderer(this, 0, 0);
        brainVein77.addBox(12F, 0F, 27F, 1, 1, 12);
        brainSac.addChild(brainVein77);

        ModelRenderer brainVein78 = new ModelRenderer(this, 0, 0);
        brainVein78.addBox(12F, -6F, 20F, 1, 1, 7);
        brainSac.addChild(brainVein78);

        ModelRenderer brainVein79 = new ModelRenderer(this, 0, 0);
        brainVein79.addBox(12F, -3F, 22F, 1, 1, 8);
        brainSac.addChild(brainVein79);

        ModelRenderer brainVein80 = new ModelRenderer(this, 0, 0);
        brainVein80.addBox(12F, -10F, 9F, 1, 1, 4);
        brainSac.addChild(brainVein80);

        ModelRenderer brainVein81 = new ModelRenderer(this, 0, 0);
        brainVein81.addBox(11F, -10F, 9F, 1, 5, 1);
        brainSac.addChild(brainVein81);

        ModelRenderer brainVein82 = new ModelRenderer(this, 0, 0);
        brainVein82.addBox(5F, 1F, 9F, 1, 4, 1);
        brainSac.addChild(brainVein82);

        ModelRenderer brainVein83 = new ModelRenderer(this, 0, 0);
        brainVein83.addBox(12F, -2F, 29F, 1, 1, 3);
        brainSac.addChild(brainVein83);

        ModelRenderer brainVein84 = new ModelRenderer(this, 0, 0);
        brainVein84.addBox(12F, -1F, 31F, 1, 1, 1);
        brainSac.addChild(brainVein84);

        ModelRenderer brainVein85 = new ModelRenderer(this, 0, 0);
        brainVein85.addBox(12F, -7F, 26F, 1, 1, 10);
        brainSac.addChild(brainVein85);

        ModelRenderer brainVein86 = new ModelRenderer(this, 0, 0);
        brainVein86.addBox(12F, -12F, 25F, 1, 1, 12);
        brainSac.addChild(brainVein86);

        ModelRenderer brainVein87 = new ModelRenderer(this, 0, 0);
        brainVein87.addBox(12F, -11F, 33F, 1, 4, 1);
        brainSac.addChild(brainVein87);

        ModelRenderer brainVein88 = new ModelRenderer(this, 0, 0);
        brainVein88.addBox(12F, -6F, 35F, 1, 3, 1);
        brainSac.addChild(brainVein88);

        ModelRenderer brainVein89 = new ModelRenderer(this, 0, 0);
        brainVein89.addBox(12F, -4F, 36F, 1, 1, 3);
        brainSac.addChild(brainVein89);

        ModelRenderer brainVein90 = new ModelRenderer(this, 0, 0);
        brainVein90.addBox(12F, -13F, 36F, 1, 1, 3);
        brainSac.addChild(brainVein90);

        ModelRenderer brainVein91 = new ModelRenderer(this, 0, 0);
        brainVein91.addBox(12F, -11F, 36F, 1, 1, 3);
        brainSac.addChild(brainVein91);

        ModelRenderer brainVein92 = new ModelRenderer(this, 0, 0);
        brainVein92.addBox(12F, -2F, 20F, 1, 1, 3);
        brainSac.addChild(brainVein92);

        ModelRenderer brainVein93 = new ModelRenderer(this, 0, 0);
        brainVein93.addBox(12F, -7F, 20F, 1, 1, 1);
        brainSac.addChild(brainVein93);

        ModelRenderer brainVein94 = new ModelRenderer(this, 0, 0);
        brainVein94.addBox(12F, -9F, 20F, 1, 1, 13);
        brainSac.addChild(brainVein94);

        ModelRenderer brainVein95 = new ModelRenderer(this, 0, 0);
        brainVein95.addBox(12F, 0F, 14F, 1, 1, 1);
        brainSac.addChild(brainVein95);

        ModelRenderer brainVein96 = new ModelRenderer(this, 0, 0);
        brainVein96.addBox(12F, 1F, 14F, 1, 1, 4);
        brainSac.addChild(brainVein96);

        ModelRenderer brainVein97 = new ModelRenderer(this, 0, 0);
        brainVein97.addBox(7F, -4F, 9F, 1, 4, 1);
        brainSac.addChild(brainVein97);

        ModelRenderer brainVein98 = new ModelRenderer(this, 0, 0);
        brainVein98.addBox(0F, -15F, 38F, 1, 6, 1);
        brainSac.addChild(brainVein98);

        ModelRenderer brainVein99 = new ModelRenderer(this, 0, 0);
        brainVein99.addBox(-6F, -10F, 38F, 6, 1, 1);
        brainSac.addChild(brainVein99);

        ModelRenderer brainVein100 = new ModelRenderer(this, 0, 0);
        brainVein100.addBox(-5F, -15F, 38F, 1, 5, 1);
        brainSac.addChild(brainVein100);

        ModelRenderer brainVein101 = new ModelRenderer(this, 0, 0);
        brainVein101.addBox(-6F, -9F, 38F, 1, 5, 1);
        brainSac.addChild(brainVein101);

        ModelRenderer brainVein102 = new ModelRenderer(this, 0, 0);
        brainVein102.addBox(-9F, -14F, 38F, 2, 1, 1);
        brainSac.addChild(brainVein102);

        ModelRenderer brainVein103 = new ModelRenderer(this, 0, 0);
        brainVein103.addBox(-8F, -13F, 38F, 1, 4, 1);
        brainSac.addChild(brainVein103);

        ModelRenderer brainVein104 = new ModelRenderer(this, 0, 0);
        brainVein104.addBox(-13F, -10F, 38F, 5, 1, 1);
        brainSac.addChild(brainVein104);

        ModelRenderer brainVein105 = new ModelRenderer(this, 0, 0);
        brainVein105.addBox(-10F, -9F, 38F, 1, 12, 1);
        brainSac.addChild(brainVein105);

        ModelRenderer brainVein106 = new ModelRenderer(this, 0, 0);
        brainVein106.addBox(-5F, -5F, 38F, 1, 5, 1);
        brainSac.addChild(brainVein106);

        ModelRenderer brainVein107 = new ModelRenderer(this, 0, 0);
        brainVein107.addBox(-9F, 0F, 38F, 5, 1, 1);
        brainSac.addChild(brainVein107);

        ModelRenderer brainVein108 = new ModelRenderer(this, 0, 0);
        brainVein108.addBox(-9F, 2F, 38F, 1, 3, 1);
        brainSac.addChild(brainVein108);

        ModelRenderer brainVein109 = new ModelRenderer(this, 0, 0);
        brainVein109.addBox(-13F, -4F, 38F, 3, 1, 1);
        brainSac.addChild(brainVein109);

        ModelRenderer brainVein110 = new ModelRenderer(this, 0, 0);
        brainVein110.addBox(9F, -12F, 38F, 4, 1, 1);
        brainSac.addChild(brainVein110);

        ModelRenderer brainVein111 = new ModelRenderer(this, 0, 0);
        brainVein111.addBox(8F, -14F, 38F, 1, 3, 1);
        brainSac.addChild(brainVein111);

        ModelRenderer brainVein112 = new ModelRenderer(this, 0, 0);
        brainVein112.addBox(-2F, -9F, 38F, 1, 7, 1);
        brainSac.addChild(brainVein112);

        ModelRenderer brainVein113 = new ModelRenderer(this, 0, 0);
        brainVein113.addBox(-4F, -3F, 38F, 2, 1, 1);
        brainSac.addChild(brainVein113);

        ModelRenderer brainVein114 = new ModelRenderer(this, 0, 0);
        brainVein114.addBox(-1F, -3F, 38F, 1, 8, 1);
        brainSac.addChild(brainVein114);

        ModelRenderer brainVein115 = new ModelRenderer(this, 0, 0);
        brainVein115.addBox(0F, -1F, 38F, 7, 1, 1);
        brainSac.addChild(brainVein115);

        ModelRenderer brainVein116 = new ModelRenderer(this, 0, 0);
        brainVein116.addBox(6F, 0F, 38F, 6, 1, 1);
        brainSac.addChild(brainVein116);

        ModelRenderer brainVein117 = new ModelRenderer(this, 0, 0);
        brainVein117.addBox(8F, -3F, 38F, 5, 1, 1);
        brainSac.addChild(brainVein117);

        ModelRenderer brainVein118 = new ModelRenderer(this, 0, 0);
        brainVein118.addBox(6F, -2F, 38F, 3, 1, 1);
        brainSac.addChild(brainVein118);

        ModelRenderer brainVein119 = new ModelRenderer(this, 0, 0);
        brainVein119.addBox(1F, -11F, 38F, 4, 1, 1);
        brainSac.addChild(brainVein119);

        ModelRenderer brainVein120 = new ModelRenderer(this, 0, 0);
        brainVein120.addBox(10F, -11F, 38F, 1, 6, 1);
        brainSac.addChild(brainVein120);

        ModelRenderer brainVein121 = new ModelRenderer(this, 0, 0);
        brainVein121.addBox(5F, -11F, 38F, 1, 5, 1);
        brainSac.addChild(brainVein121);

        ModelRenderer brainVein122 = new ModelRenderer(this, 0, 0);
        brainVein122.addBox(5F, -6F, 38F, 5, 1, 1);
        brainSac.addChild(brainVein122);

        ModelRenderer brainVein123 = new ModelRenderer(this, 0, 0);
        brainVein123.addBox(9F, -5F, 38F, 1, 2, 1);
        brainSac.addChild(brainVein123);

        ModelRenderer brainVein124 = new ModelRenderer(this, 0, 0);
        brainVein124.addBox(-1F, -7F, 38F, 6, 1, 1);
        brainSac.addChild(brainVein124);

        ModelRenderer brainVein125 = new ModelRenderer(this, 0, 0);
        brainVein125.addBox(0F, 4F, 10F, 6, 1, 1);
        brainSac.addChild(brainVein125);

        ModelRenderer brainVein126 = new ModelRenderer(this, 0, 0);
        brainVein126.addBox(-9F, 4F, 10F, 1, 1, 7);
        brainSac.addChild(brainVein126);

        ModelRenderer brainVein127 = new ModelRenderer(this, 0, 0);
        brainVein127.addBox(0F, 4F, 11F, 1, 1, 8);
        brainSac.addChild(brainVein127);

        ModelRenderer brainVein128 = new ModelRenderer(this, 0, 0);
        brainVein128.addBox(1F, 4F, 18F, 2, 1, 1);
        brainSac.addChild(brainVein128);

        ModelRenderer brainVein129 = new ModelRenderer(this, 0, 0);
        brainVein129.addBox(3F, 4F, 18F, 1, 1, 6);
        brainSac.addChild(brainVein129);

        ModelRenderer brainVein130 = new ModelRenderer(this, 0, 0);
        brainVein130.addBox(3F, 4F, 24F, 10, 1, 1);
        brainSac.addChild(brainVein130);

        ModelRenderer brainVein131 = new ModelRenderer(this, 0, 0);
        brainVein131.addBox(7F, 4F, 25F, 1, 1, 8);
        brainSac.addChild(brainVein131);

        ModelRenderer brainVein132 = new ModelRenderer(this, 0, 0);
        brainVein132.addBox(12F, 2F, 24F, 1, 2, 1);
        brainSac.addChild(brainVein132);

        ModelRenderer brainVein133 = new ModelRenderer(this, 0, 0);
        brainVein133.addBox(5F, 4F, 13F, 8, 1, 1);
        brainSac.addChild(brainVein133);

        ModelRenderer brainVein134 = new ModelRenderer(this, 0, 0);
        brainVein134.addBox(12F, 1F, 13F, 1, 3, 1);
        brainSac.addChild(brainVein134);

        ModelRenderer brainVein135 = new ModelRenderer(this, 0, 0);
        brainVein135.addBox(0F, 4F, 30F, 1, 1, 9);
        brainSac.addChild(brainVein135);

        ModelRenderer brainVein136 = new ModelRenderer(this, 0, 0);
        brainVein136.addBox(-1F, 4F, 25F, 1, 1, 6);
        brainSac.addChild(brainVein136);

        ModelRenderer brainVein137 = new ModelRenderer(this, 0, 0);
        brainVein137.addBox(0F, 4F, 25F, 4, 1, 1);
        brainSac.addChild(brainVein137);

        ModelRenderer brainVein138 = new ModelRenderer(this, 0, 0);
        brainVein138.addBox(6F, 4F, 32F, 1, 1, 5);
        brainSac.addChild(brainVein138);

        ModelRenderer brainVein139 = new ModelRenderer(this, 0, 0);
        brainVein139.addBox(7F, 4F, 38F, 4, 1, 1);
        brainSac.addChild(brainVein139);

        ModelRenderer brainVein140 = new ModelRenderer(this, 0, 0);
        brainVein140.addBox(11F, 1F, 38F, 1, 4, 1);
        brainSac.addChild(brainVein140);

        ModelRenderer brainVein141 = new ModelRenderer(this, 0, 0);
        brainVein141.addBox(6F, 4F, 37F, 2, 1, 1);
        brainSac.addChild(brainVein141);

        ModelRenderer brainVein142 = new ModelRenderer(this, 0, 0);
        brainVein142.addBox(-2F, 4F, 28F, 1, 1, 1);
        brainSac.addChild(brainVein142);

        ModelRenderer brainVein143 = new ModelRenderer(this, 0, 0);
        brainVein143.addBox(-3F, 4F, 28F, 1, 1, 8);
        brainSac.addChild(brainVein143);

        ModelRenderer brainVein144 = new ModelRenderer(this, 0, 0);
        brainVein144.addBox(-9F, 4F, 35F, 6, 1, 1);
        brainSac.addChild(brainVein144);

        ModelRenderer brainVein145 = new ModelRenderer(this, 0, 0);
        brainVein145.addBox(-10F, 4F, 35F, 1, 1, 4);
        brainSac.addChild(brainVein145);

        ModelRenderer brainVein146 = new ModelRenderer(this, 0, 0);
        brainVein146.addBox(4F, 4F, 20F, 6, 1, 1);
        brainSac.addChild(brainVein146);

        ModelRenderer brainVein147 = new ModelRenderer(this, 0, 0);
        brainVein147.addBox(9F, 4F, 19F, 4, 1, 1);
        brainSac.addChild(brainVein147);

        ModelRenderer brainVein148 = new ModelRenderer(this, 0, 0);
        brainVein148.addBox(12F, 2F, 20F, 1, 3, 1);
        brainSac.addChild(brainVein148);

        ModelRenderer brainVein149 = new ModelRenderer(this, 0, 0);
        brainVein149.addBox(-8F, 4F, 15F, 5, 1, 1);
        brainSac.addChild(brainVein149);

        ModelRenderer brainVein150 = new ModelRenderer(this, 0, 0);
        brainVein150.addBox(5F, 4F, 14F, 1, 1, 3);
        brainSac.addChild(brainVein150);

        ModelRenderer brainVein151 = new ModelRenderer(this, 0, 0);
        brainVein151.addBox(6F, 4F, 16F, 4, 1, 1);
        brainSac.addChild(brainVein151);

        ModelRenderer brainVein152 = new ModelRenderer(this, 0, 0);
        brainVein152.addBox(10F, 4F, 16F, 1, 1, 3);
        brainSac.addChild(brainVein152);

        ModelRenderer brainVein153 = new ModelRenderer(this, 0, 0);
        brainVein153.addBox(-4F, 4F, 14F, 4, 1, 1);
        brainSac.addChild(brainVein153);

        ModelRenderer brainVein154 = new ModelRenderer(this, 0, 0);
        brainVein154.addBox(-13F, 4F, 17F, 5, 1, 1);
        brainSac.addChild(brainVein154);

        ModelRenderer brainVein155 = new ModelRenderer(this, 0, 0);
        brainVein155.addBox(-13F, 1F, 17F, 1, 3, 1);
        brainSac.addChild(brainVein155);

        ModelRenderer brainVein156 = new ModelRenderer(this, 0, 0);
        brainVein156.addBox(-6F, 4F, 16F, 1, 1, 9);
        brainSac.addChild(brainVein156);

        ModelRenderer brainVein157 = new ModelRenderer(this, 0, 0);
        brainVein157.addBox(-5F, 4F, 24F, 1, 1, 7);
        brainSac.addChild(brainVein157);

        ModelRenderer brainVein158 = new ModelRenderer(this, 0, 0);
        brainVein158.addBox(-4F, 4F, 30F, 1, 1, 1);
        brainSac.addChild(brainVein158);

        ModelRenderer brainVein159 = new ModelRenderer(this, 0, 0);
        brainVein159.addBox(-8F, 4F, 22F, 2, 1, 1);
        brainSac.addChild(brainVein159);

        ModelRenderer brainVein160 = new ModelRenderer(this, 0, 0);
        brainVein160.addBox(-9F, 4F, 22F, 1, 1, 10);
        brainSac.addChild(brainVein160);

        ModelRenderer brainVein161 = new ModelRenderer(this, 0, 0);
        brainVein161.addBox(-13F, 4F, 31F, 4, 1, 1);
        brainSac.addChild(brainVein161);

        ModelRenderer brainVein162 = new ModelRenderer(this, 0, 0);
        brainVein162.addBox(-13F, 2F, 31F, 1, 1, 8);
        brainSac.addChild(brainVein162);

        ModelRenderer brainVein163 = new ModelRenderer(this, 0, 0);
        brainVein163.addBox(-13F, 3F, 31F, 1, 1, 1);
        brainSac.addChild(brainVein163);

        ModelRenderer brainVein164 = new ModelRenderer(this, 0, 0);
        brainVein164.addBox(-13F, 1F, 38F, 3, 1, 1);
        brainSac.addChild(brainVein164);

        body = new ModelRenderer(this, 84, 72);
        body.addBox(-4F, 1F, -3F, 8, 6, 8);
        body.setRotationPoint(0F, 4F, 0F);

        ModelRenderer knobFR = new ModelRenderer(this, 0, 0);
        knobFR.addBox(-6F, 7F, -3F, 2, 2, 2);
        body.addChild(knobFR);

        ModelRenderer knobFMR = new ModelRenderer(this, 0, 0);
        knobFMR.addBox(-6F, 7F, 1F, 2, 2, 2);
        body.addChild(knobFMR);

        ModelRenderer knobBMR = new ModelRenderer(this, 0, 0);
        knobBMR.addBox(-6F, 5F, 4F, 2, 2, 2);
        body.addChild(knobBMR);

        ModelRenderer knobBR = new ModelRenderer(this, 0, 0);
        knobBR.addBox(-5F, 2F, 5F, 2, 2, 2);
        body.addChild(knobBR);

        ModelRenderer knobFL = new ModelRenderer(this, 0, 0);
        knobFL.addBox(4F, 7F, -3F, 2, 2, 2);
        body.addChild(knobFL);

        ModelRenderer knobFML = new ModelRenderer(this, 0, 0);
        knobFML.addBox(4F, 7F, 1F, 2, 2, 2);
        body.addChild(knobFML);

        ModelRenderer knobBML = new ModelRenderer(this, 0, 0);
        knobBML.addBox(4F, 5F, 4F, 2, 2, 2);
        body.addChild(knobBML);

        ModelRenderer knobBL = new ModelRenderer(this, 0, 0);
        knobBL.addBox(-2F, 2F, 5F, 2, 2, 2);
        body.addChild(knobBL);

        upperLegFR = new ModelRenderer(this, 60, 90);
        upperLegFR.addBox(0F, -1F, -1F, 1, 3, 1);
        upperLegFR.setRotationPoint(-6F, 13F, -2F);
        upperLegFR.rotateAngleX = -0.4833219F;
        upperLegFR.rotateAngleZ = 0.4833219F;

        lowerLegFR = new ModelRenderer(this, 60, 90);
        lowerLegFR.addBox(0F, 2F, -1F, 1, 3, 1);
        lowerLegFR.rotateAngleX = -0.0371787F;
        upperLegFR.addChild(lowerLegFR);

        upperLegFL = new ModelRenderer(this, 60, 90);
        upperLegFL.addBox(-1F, -1F, -1F, 1, 3, 1);
        upperLegFL.setRotationPoint(6F, 13F, -2F);
        upperLegFL.rotateAngleX = -0.4833219F;
        upperLegFL.rotateAngleZ = -0.4833219F;

        lowerLegFL = new ModelRenderer(this, 60, 90);
        lowerLegFL.addBox(-1F, 2F, -1F, 1, 3, 1);
        lowerLegFL.rotateAngleX = -0.0371787F;
        upperLegFL.addChild(lowerLegFL);

        upperLegFMR = new ModelRenderer(this, 60, 90);
        upperLegFMR.addBox(0F, -1F, 0F, 1, 3, 1);
        upperLegFMR.setRotationPoint(-6F, 13F, 1F);
        upperLegFMR.rotateAngleX = -0.2602503F;
        upperLegFMR.rotateAngleZ = -0.2974289F;

        lowerLegFMR = new ModelRenderer(this, 60, 90);
        lowerLegFMR.addBox(0F, 2F, 0F, 1, 3, 1);
        upperLegFMR.addChild(lowerLegFMR);

        upperLegFML = new ModelRenderer(this, 60, 90);
        upperLegFML.addBox(-1F, -1F, 0F, 1, 3, 1);
        upperLegFML.setRotationPoint(6F, 13F, 1F);
        upperLegFML.rotateAngleX = -0.2602503F;
        upperLegFML.rotateAngleZ = -0.2974289F;

        lowerLegFML = new ModelRenderer(this, 60, 90);
        lowerLegFML.addBox(-1F, 2F, 0F, 1, 3, 1);
        upperLegFML.addChild(lowerLegFML);

        legBMR = new ModelRenderer(this, 60, 90);
        legBMR.addBox(0F, 0F, 0F, 1, 3, 1);
        legBMR.setRotationPoint(-6F, 10F, 5F);
        legBMR.rotateAngleX = -0.3346075F;
        legBMR.rotateAngleZ = 0.1487144F;

        legBML = new ModelRenderer(this, 60, 90);
        legBML.addBox(-1F, 0F, 0F, 1, 3, 1);
        legBML.setRotationPoint(6F, 10F, 5F);
        legBML.rotateAngleX = -0.3346075F;
        legBML.rotateAngleZ = -0.1487144F;

        legBR = new ModelRenderer(this, 60, 90);
        legBR.addBox(0F, 0F, 0F, 1, 3, 1);
        legBR.setRotationPoint(-5F, 7F, 6F);
        legBR.rotateAngleX = 0.4461433F;
        legBR.rotateAngleZ = 0.2230717F;

        legBL = new ModelRenderer(this, 60, 90);
        legBL.addBox(-1F, 0F, 0F, 1, 3, 1);
        legBL.setRotationPoint(5F, 7F, 6F);
        legBL.rotateAngleX = 0.4461433F;
        legBL.rotateAngleZ = -0.2230717F;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        brainSac.render(f5);
        brain.render(f5);
        head.render(f5);

        neck.render(f5);
        body.render(f5);

        upperLegFR.render(f5);
        upperLegFL.render(f5);
        upperLegFMR.render(f5);
        upperLegFML.render(f5);

        legBMR.render(f5);
        legBML.render(f5);
        legBR.render(f5);
        legBL.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        if (entity instanceof EntityStatue) return;

        EntityOverlordCore core = (EntityOverlordCore) entity;
        brainSac.rotateAngleY = brain.rotateAngleY = (f3 / (180F / (float) Math.PI)) * 0.35F;
        brainSac.rotateAngleX = brain.rotateAngleX = (f4 / (180F / (float) Math.PI)) * 0.35F;
        brainSac.rotateAngleZ = 0F;
        head.rotateAngleX = 0F;
        brain.rotateAngleZ = 0F;

        upperLegFR.rotateAngleY = upperLegFL.rotateAngleY = upperLegFMR.rotateAngleY = upperLegFML.rotateAngleY = legBMR.rotateAngleY = legBML.rotateAngleY = legBR.rotateAngleY = legBL.rotateAngleY = (f3
            / (180F / (float) Math.PI));
        upperLegFR.rotateAngleZ = upperLegFL.rotateAngleZ = upperLegFMR.rotateAngleZ = upperLegFML.rotateAngleZ = legBMR.rotateAngleZ = legBML.rotateAngleZ = legBR.rotateAngleZ = legBL.rotateAngleZ = (f4
            / (180F / (float) Math.PI));

        if (core.getTransformationTicks() > 0) {
            upperLegFL.rotateAngleZ = this.simplifyAngle(core.ticksExisted, 10.0F) * 0.38F - 0.38F;
            upperLegFR.rotateAngleZ = this.simplifyAngle(core.ticksExisted - 7, 10.0F) * -0.38F + 0.38F;
            upperLegFML.rotateAngleZ = this.simplifyAngle(core.ticksExisted + 4, 10.0F) * 0.21F - 0.21F;
            upperLegFMR.rotateAngleZ = this.simplifyAngle(core.ticksExisted - 2, 10.0F) * -0.21F + 0.21F;
            legBML.rotateAngleZ = this.simplifyAngle(core.ticksExisted + 4, 6.0F) * 0.22F - 0.22F;
            legBMR.rotateAngleZ = this.simplifyAngle(core.ticksExisted + 2, 6.0F) * -0.22F + 0.22F;
            legBR.rotateAngleZ = this.simplifyAngle(core.ticksExisted, 6.0F) * -0.22F + 0.22F;
            legBL.rotateAngleZ = this.simplifyAngle(core.ticksExisted - 3, 6.0F) * 0.22F - 0.22F;

            head.rotateAngleX = -0.23F;

            if (core.getTransformationTicks() >= 20) {
                pincerLeft.rotateAngleY = 0.57F - this.simplifyAngle(core.ticksExisted, 3F) * 0.125F;
                pincerRight.rotateAngleY = -0.57F + this.simplifyAngle(core.ticksExisted, 3F) * 0.125F;

                lowerJaw.rotateAngleX = 0.35F + this.simplifyAngle(core.ticksExisted, 5F) * 0.0125F;
            } else {
                pincerRight.rotateAngleY = -0.47F;
                pincerLeft.rotateAngleY = 0.47F;
                lowerJaw.rotateAngleX = -0.13F;
            }
            return;
        }

        if (core.getHoverTicks() > 0) {
            upperLegFL.rotateAngleZ = this.simplifyAngle(core.getHoverTicks(), 10.0F) * 0.38F - 0.38F;
            upperLegFR.rotateAngleZ = this.simplifyAngle(core.getHoverTicks() - 7, 10.0F) * -0.38F + 0.38F;
            upperLegFML.rotateAngleZ = this.simplifyAngle(core.getHoverTicks() + 4, 10.0F) * 0.21F - 0.21F;
            upperLegFMR.rotateAngleZ = this.simplifyAngle(core.getHoverTicks() - 2, 10.0F) * -0.21F + 0.21F;
            legBML.rotateAngleZ = this.simplifyAngle(core.getHoverTicks() + 4, 6.0F) * 0.22F - 0.22F;
            legBMR.rotateAngleZ = this.simplifyAngle(core.getHoverTicks() + 2, 6.0F) * -0.22F + 0.22F;
            legBR.rotateAngleZ = this.simplifyAngle(core.getHoverTicks(), 6.0F) * -0.22F + 0.22F;
            legBL.rotateAngleZ = this.simplifyAngle(core.getHoverTicks() - 3, 6.0F) * 0.22F - 0.22F;

            pincerLeft.rotateAngleY = this.simplifyAngle(core.getHoverTicks(), 5.0F) * 0.15F + 0.36F;
            pincerRight.rotateAngleY = this.simplifyAngle(core.getHoverTicks(), 5.0F) * -0.15F - 0.36F;

            lowerJaw.rotateAngleX = this.simplifyAngle(core.getHoverTicks(), 5.0F) * -0.15F + 0.25F;
            brainSac.rotateAngleZ = brain.rotateAngleZ = this.simplifyAngle(core.getHoverTicks() + 2, 5.0F) * -0.05F
                + 0.025F;
            brainSac.rotateAngleX = brain.rotateAngleX = this.simplifyAngle(core.getHoverTicks(), 3.0F) * -0.025F
                + 0.025F;
        } else {
            if (core.getDropTicks() > 10 && core.isNearTarget()) {
                upperLegFL.rotateAngleZ = this.simplifyAngle(core.getDropTicks(), 10.0F) * 0.38F - 0.38F;
                upperLegFR.rotateAngleZ = this.simplifyAngle(core.getDropTicks() - 7, 10.0F) * -0.38F + 0.38F;
                upperLegFML.rotateAngleZ = this.simplifyAngle(core.getDropTicks() + 4, 10.0F) * 0.21F - 0.21F;
                upperLegFMR.rotateAngleZ = this.simplifyAngle(core.getDropTicks() - 2, 10.0F) * -0.21F + 0.21F;
                legBML.rotateAngleZ = this.simplifyAngle(core.getDropTicks() + 4, 6.0F) * 0.22F - 0.22F;
                legBMR.rotateAngleZ = this.simplifyAngle(core.getDropTicks() + 2, 6.0F) * -0.22F + 0.22F;
                legBR.rotateAngleZ = this.simplifyAngle(core.getDropTicks(), 6.0F) * -0.22F + 0.22F;
                legBL.rotateAngleZ = this.simplifyAngle(core.getDropTicks() - 3, 6.0F) * 0.22F - 0.22F;

                pincerLeft.rotateAngleY = this.simplifyAngle(core.getDropTicks(), 5.0F) * 0.25F + 0.36F;
                pincerRight.rotateAngleY = this.simplifyAngle(core.getDropTicks(), 5.0F) * -0.25F - 0.36F;

                lowerJaw.rotateAngleX = this.simplifyAngle(core.getDropTicks(), 5.0F) * -0.15F + 0.25F;
                head.rotateAngleX = 1.05F;
            } else if (core.isNearTarget() || core.getHurtTicks() > 0) {
                upperLegFL.rotateAngleZ = this.simplifyAngle(core.ticksExisted, 10.0F) * 0.38F - 0.38F;
                upperLegFR.rotateAngleZ = this.simplifyAngle(core.ticksExisted - 7, 10.0F) * -0.38F + 0.38F;
                upperLegFML.rotateAngleZ = this.simplifyAngle(core.ticksExisted + 4, 10.0F) * 0.21F - 0.21F;
                upperLegFMR.rotateAngleZ = this.simplifyAngle(core.ticksExisted - 2, 10.0F) * -0.21F + 0.21F;
                legBML.rotateAngleZ = this.simplifyAngle(core.ticksExisted + 4, 6.0F) * 0.22F - 0.22F;
                legBMR.rotateAngleZ = this.simplifyAngle(core.ticksExisted + 2, 6.0F) * -0.22F + 0.22F;
                legBR.rotateAngleZ = this.simplifyAngle(core.ticksExisted, 6.0F) * -0.22F + 0.22F;
                legBL.rotateAngleZ = this.simplifyAngle(core.ticksExisted - 3, 6.0F) * 0.22F - 0.22F;

                pincerLeft.rotateAngleY = this.simplifyAngle(core.ticksExisted, 5.0F) * 0.25F + 0.36F;
                pincerRight.rotateAngleY = this.simplifyAngle(core.ticksExisted, 5.0F) * -0.25F - 0.36F;

                lowerJaw.rotateAngleX = this.simplifyAngle(core.getHoverTicks(), 5.0F) * -0.15F + 0.25F;
            }

            if (core.getVulnerableTicks() > 0 || core.getHurtTicks() > 0) {
                brainSac.rotateAngleZ = brain.rotateAngleZ = this.simplifyAngle(core.getVulnerableTicks() + 6, 15.0F)
                    * -0.05F + 0.05F;
                brainSac.rotateAngleX = brain.rotateAngleX = this.simplifyAngle(core.getVulnerableTicks() - 12, 15.0F)
                    * -0.05F + 0.05F;
                brainSac.rotateAngleY = brain.rotateAngleY = this.simplifyAngle(core.getVulnerableTicks(), 15.0F)
                    * -0.05F + 0.05F;
            }
        }
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }

}
