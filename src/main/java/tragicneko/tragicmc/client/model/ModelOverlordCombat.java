package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.alpha.EntityOverlordCombat;

public class ModelOverlordCombat extends ModelBase {

    private ModelRenderer body;
    private ModelRenderer head;

    private ModelRenderer legFR;
    private ModelRenderer legFMR;
    private ModelRenderer legBMR;
    private ModelRenderer legBR;
    private ModelRenderer legJointFMR;
    private ModelRenderer legJointFR;
    private ModelRenderer legJointBMR;
    private ModelRenderer legJointBR;

    private ModelRenderer legFL;
    private ModelRenderer legFML;
    private ModelRenderer legBML;
    private ModelRenderer legBL;
    private ModelRenderer legJointFL;
    private ModelRenderer legJointFML;
    private ModelRenderer legJointBML;
    private ModelRenderer legJointBL;

    private ModelRenderer armLeft;
    private ModelRenderer armRight;
    private ModelRenderer saberRight;
    private ModelRenderer saberLeft;

    public ModelOverlordCombat() {
        textureWidth = 128;
        textureHeight = 128;

        // Main Body parts
        body = new ModelRenderer(this, 0, 64);
        body.addBox(-11F, -6F, -11F, 22, 12, 22);
        body.setRotationPoint(0F, -4F, 0F);

        ModelRenderer bodyUnder = new ModelRenderer(this, 4, 0);
        bodyUnder.addBox(-7F, 6F, -7F, 14, 3, 14);
        body.addChild(bodyUnder);

        ModelRenderer bodyBottom = new ModelRenderer(this, 38, 24);
        bodyBottom.addBox(-3F, 9F, -3F, 6, 3, 6);
        body.addChild(bodyBottom);

        ModelRenderer bodyLegPanelRight = new ModelRenderer(this, 74, 32);
        bodyLegPanelRight.addBox(-12F, -5F, -8F, 1, 10, 16);
        body.addChild(bodyLegPanelRight);

        ModelRenderer bodyLegPanelLeft = new ModelRenderer(this, 74, 32);
        bodyLegPanelLeft.addBox(11F, -5F, -8F, 1, 10, 16);
        body.addChild(bodyLegPanelLeft);

        ModelRenderer bodyTop = new ModelRenderer(this, 0, 40);
        bodyTop.addBox(-9F, -9F, -9F, 18, 3, 18);
        body.addChild(bodyTop);

        ModelRenderer bodyTop2 = new ModelRenderer(this, 0, 24);
        bodyTop2.addBox(-6F, -11F, -6F, 12, 2, 12);
        body.addChild(bodyTop2);

        ModelRenderer bodyFrontPanel = new ModelRenderer(this, 74, 72);
        bodyFrontPanel.addBox(-8F, -4F, -14F, 16, 8, 3);
        body.addChild(bodyFrontPanel);

        ModelRenderer bodyBackPanel = new ModelRenderer(this, 74, 72);
        bodyBackPanel.addBox(-8F, -4F, 11F, 16, 8, 2);
        body.addChild(bodyBackPanel);

        // Main head parts
        head = new ModelRenderer(this, 60, 16);
        head.addBox(-4F, -15F, -4F, 8, 4, 8);
        body.addChild(head);

        ModelRenderer eye = new ModelRenderer(this, 94, 24);
        eye.addBox(-1F, -14F, -5F, 2, 2, 1);
        head.addChild(eye);

        ModelRenderer headTop = new ModelRenderer(this, 60, 0);
        headTop.addBox(-5F, -17F, -5F, 10, 2, 10);
        head.addChild(headTop);

        ModelRenderer headTop2 = new ModelRenderer(this, 94, 0);
        headTop2.addBox(-4F, -19F, -4F, 8, 2, 7);
        head.addChild(headTop2);

        ModelRenderer headTop3 = new ModelRenderer(this, 94, 16);
        headTop3.addBox(-2F, -21F, -2F, 4, 2, 4);
        head.addChild(headTop3);

        ModelRenderer circuit = new ModelRenderer(this, 0, 0);
        circuit.addBox(0F, -7F, -12F, 1, 3, 1);
        body.addChild(circuit);

        ModelRenderer circuit2 = new ModelRenderer(this, 0, 0);
        circuit2.addBox(-1F, -7F, -12F, 1, 1, 3);
        body.addChild(circuit2);

        ModelRenderer circuit3 = new ModelRenderer(this, 0, 0);
        circuit3.addBox(0F, -10F, -10F, 1, 4, 1);
        body.addChild(circuit3);

        ModelRenderer circuit4 = new ModelRenderer(this, 0, 0);
        circuit4.addBox(-4F, -10F, -10F, 4, 1, 1);
        body.addChild(circuit4);

        ModelRenderer circuit5 = new ModelRenderer(this, 0, 0);
        circuit5.addBox(-4F, -10F, -9F, 1, 1, 3);
        body.addChild(circuit5);

        ModelRenderer circuit6 = new ModelRenderer(this, 0, 0);
        circuit6.addBox(-3F, -12F, -7F, 1, 3, 1);
        body.addChild(circuit6);

        // Part of head
        ModelRenderer circuit7 = new ModelRenderer(this, 0, 0);
        circuit7.addBox(-3F, -16F, -6F, 2, 1, 1);
        head.addChild(circuit7);

        ModelRenderer circuit8 = new ModelRenderer(this, 0, 0);
        circuit8.addBox(-2F, -18F, -6F, 1, 2, 1);
        head.addChild(circuit8);

        ModelRenderer circuit9 = new ModelRenderer(this, 0, 0);
        circuit9.addBox(-2F, -19F, -5F, 1, 2, 1);
        head.addChild(circuit9);

        ModelRenderer circuit10 = new ModelRenderer(this, 0, 0);
        circuit10.addBox(-3F, -20F, -5F, 1, 2, 1);
        head.addChild(circuit10);

        ModelRenderer circuit11 = new ModelRenderer(this, 0, 0);
        circuit11.addBox(-3F, -20F, -4F, 1, 1, 3);
        head.addChild(circuit11);
        // Part of head

        ModelRenderer circuit12 = new ModelRenderer(this, 0, 0);
        circuit12.addBox(5F, -5F, -12F, 1, 1, 1);
        body.addChild(circuit12);

        ModelRenderer circuit13 = new ModelRenderer(this, 0, 0);
        circuit13.addBox(4F, -6F, -12F, 5, 1, 1);
        body.addChild(circuit13);

        ModelRenderer circuit14 = new ModelRenderer(this, 0, 0);
        circuit14.addBox(8F, -7F, -12F, 1, 1, 3);
        body.addChild(circuit14);

        ModelRenderer circuit15 = new ModelRenderer(this, 0, 0);
        circuit15.addBox(8F, -8F, -10F, 2, 1, 1);
        body.addChild(circuit15);

        ModelRenderer circuit16 = new ModelRenderer(this, 0, 0);
        circuit16.addBox(9F, -8F, -9F, 1, 1, 5);
        body.addChild(circuit16);

        ModelRenderer circuit17 = new ModelRenderer(this, 0, 0);
        circuit17.addBox(9F, -10F, -5F, 1, 2, 1);
        body.addChild(circuit17);

        ModelRenderer circuit18 = new ModelRenderer(this, 0, 0);
        circuit18.addBox(6F, -10F, -5F, 3, 1, 1);
        body.addChild(circuit18);

        ModelRenderer circuit19 = new ModelRenderer(this, 0, 0);
        circuit19.addBox(6F, -12F, -6F, 1, 3, 1);
        body.addChild(circuit19);

        ModelRenderer circuit20 = new ModelRenderer(this, 0, 0);
        circuit20.addBox(5F, -12F, -5F, 2, 1, 1);
        body.addChild(circuit20);

        // Part of head
        ModelRenderer circuit21 = new ModelRenderer(this, 0, 0);
        circuit21.addBox(5F, -18F, -4F, 1, 3, 1);
        head.addChild(circuit21);

        ModelRenderer circuit22 = new ModelRenderer(this, 0, 0);
        circuit22.addBox(4F, -20F, -5F, 1, 3, 1);
        head.addChild(circuit22);

        ModelRenderer circuit23 = new ModelRenderer(this, 0, 0);
        circuit23.addBox(4F, -18F, -4F, 1, 1, 1);
        head.addChild(circuit23);

        ModelRenderer circuit24 = new ModelRenderer(this, 0, 0);
        circuit24.addBox(1F, -20F, -4F, 4, 1, 1);
        head.addChild(circuit24);

        ModelRenderer circuit25 = new ModelRenderer(this, 0, 0);
        circuit25.addBox(1F, -22F, -3F, 1, 3, 1);
        head.addChild(circuit25);
        // Part of head

        ModelRenderer circuit26 = new ModelRenderer(this, 0, 0);
        circuit26.addBox(1F, -9F, -10F, 4, 1, 1);
        body.addChild(circuit26);

        ModelRenderer circuit27 = new ModelRenderer(this, 0, 0);
        circuit27.addBox(4F, -8F, -10F, 1, 2, 1);
        body.addChild(circuit27);

        ModelRenderer circuit28 = new ModelRenderer(this, 0, 0);
        circuit28.addBox(4F, -7F, -12F, 1, 1, 2);
        body.addChild(circuit28);

        ModelRenderer circuit29 = new ModelRenderer(this, 0, 0);
        circuit29.addBox(-1F, -12F, -7F, 1, 3, 1);
        body.addChild(circuit29);

        ModelRenderer circuit30 = new ModelRenderer(this, 0, 0);
        circuit30.addBox(-1F, -10F, -9F, 1, 1, 2);
        body.addChild(circuit30);

        ModelRenderer circuit31 = new ModelRenderer(this, 0, 0);
        circuit31.addBox(-1F, -12F, -6F, 1, 1, 1);
        body.addChild(circuit31);

        ModelRenderer circuit32 = new ModelRenderer(this, 0, 0);
        circuit32.addBox(-9F, -5F, -12F, 1, 3, 1);
        body.addChild(circuit32);

        ModelRenderer circuit33 = new ModelRenderer(this, 0, 0);
        circuit33.addBox(-10F, -7F, -12F, 1, 3, 1);
        body.addChild(circuit33);

        ModelRenderer circuit34 = new ModelRenderer(this, 0, 0);
        circuit34.addBox(-10F, -7F, -11F, 1, 1, 5);
        body.addChild(circuit34);

        ModelRenderer circuit35 = new ModelRenderer(this, 0, 0);
        circuit35.addBox(-7F, -7F, -12F, 1, 3, 1);
        body.addChild(circuit35);

        ModelRenderer circuit36 = new ModelRenderer(this, 0, 0);
        circuit36.addBox(-9F, -7F, -11F, 3, 1, 1);
        body.addChild(circuit36);

        ModelRenderer circuit37 = new ModelRenderer(this, 0, 0);
        circuit37.addBox(-10F, -9F, -7F, 1, 2, 1);
        body.addChild(circuit37);

        ModelRenderer circuit38 = new ModelRenderer(this, 0, 0);
        circuit38.addBox(-10F, -10F, -7F, 1, 1, 4);
        body.addChild(circuit38);

        ModelRenderer circuit39 = new ModelRenderer(this, 0, 0);
        circuit39.addBox(-9F, -10F, -4F, 2, 1, 1);
        body.addChild(circuit39);

        ModelRenderer circuit40 = new ModelRenderer(this, 0, 0);
        circuit40.addBox(-7F, -10F, -5F, 1, 1, 2);
        body.addChild(circuit40);

        ModelRenderer circuit41 = new ModelRenderer(this, 0, 0);
        circuit41.addBox(-7F, -12F, -5F, 1, 2, 1);
        body.addChild(circuit41);

        ModelRenderer circuit42 = new ModelRenderer(this, 0, 0);
        circuit42.addBox(-6F, -12F, -5F, 1, 1, 3);
        body.addChild(circuit42);

        // Part of head
        ModelRenderer circuit43 = new ModelRenderer(this, 0, 0);
        circuit43.addBox(-6F, -18F, -3F, 1, 3, 1);
        head.addChild(circuit43);

        ModelRenderer circuit44 = new ModelRenderer(this, 0, 0);
        circuit44.addBox(-5F, -18F, -3F, 1, 1, 5);
        head.addChild(circuit44);

        ModelRenderer circuit45 = new ModelRenderer(this, 0, 0);
        circuit45.addBox(-5F, -20F, 1F, 1, 2, 1);
        head.addChild(circuit45);

        ModelRenderer circuit46 = new ModelRenderer(this, 0, 0);
        circuit46.addBox(-5F, -20F, 0F, 3, 1, 1);
        head.addChild(circuit46);
        // Part of head

        ModelRenderer circuit47 = new ModelRenderer(this, 0, 0);
        circuit47.addBox(-12F, -7F, 0F, 1, 2, 1);
        body.addChild(circuit47);

        ModelRenderer circuit48 = new ModelRenderer(this, 0, 0);
        circuit48.addBox(-11F, -7F, -2F, 1, 1, 3);
        body.addChild(circuit48);

        ModelRenderer circuit49 = new ModelRenderer(this, 0, 0);
        circuit49.addBox(-10F, -10F, -2F, 1, 4, 1);
        body.addChild(circuit49);

        ModelRenderer circuit50 = new ModelRenderer(this, 0, 0);
        circuit50.addBox(-10F, -10F, -1F, 3, 1, 1);
        body.addChild(circuit50);

        ModelRenderer circuit51 = new ModelRenderer(this, 0, 0);
        circuit51.addBox(-8F, -10F, -3F, 1, 1, 2);
        body.addChild(circuit51);

        ModelRenderer circuit52 = new ModelRenderer(this, 0, 0);
        circuit52.addBox(-12F, -6F, 4F, 1, 1, 3);
        body.addChild(circuit52);

        ModelRenderer circuit53 = new ModelRenderer(this, 0, 0);
        circuit53.addBox(-12F, -7F, 6F, 3, 1, 1);
        body.addChild(circuit53);

        ModelRenderer circuit54 = new ModelRenderer(this, 0, 0);
        circuit54.addBox(-10F, -10F, 5F, 1, 4, 1);
        body.addChild(circuit54);

        ModelRenderer circuit55 = new ModelRenderer(this, 0, 0);
        circuit55.addBox(-9F, -10F, 0F, 1, 1, 6);
        body.addChild(circuit55);

        ModelRenderer circuit56 = new ModelRenderer(this, 0, 0);
        circuit56.addBox(-8F, -10F, 4F, 1, 1, 1);
        body.addChild(circuit56);

        ModelRenderer circuit57 = new ModelRenderer(this, 0, 0);
        circuit57.addBox(-7F, -12F, 4F, 1, 3, 1);
        body.addChild(circuit57);

        ModelRenderer circuit58 = new ModelRenderer(this, 0, 0);
        circuit58.addBox(-7F, -12F, 5F, 4, 1, 1);
        body.addChild(circuit58);

        // Part of head
        ModelRenderer circuit59 = new ModelRenderer(this, 0, 0);
        circuit59.addBox(-3F, -22F, 0F, 1, 2, 1);
        head.addChild(circuit59);

        ModelRenderer circuit60 = new ModelRenderer(this, 0, 0);
        circuit60.addBox(-4F, -18F, 5F, 1, 3, 1);
        head.addChild(circuit60);

        ModelRenderer circuit61 = new ModelRenderer(this, 0, 0);
        circuit61.addBox(-3F, -18F, 3F, 1, 1, 3);
        head.addChild(circuit61);

        ModelRenderer circuit62 = new ModelRenderer(this, 0, 0);
        circuit62.addBox(-5F, -19F, 2F, 1, 1, 2);
        head.addChild(circuit62);

        ModelRenderer circuit63 = new ModelRenderer(this, 0, 0);
        circuit63.addBox(-4F, -19F, 3F, 2, 1, 1);
        head.addChild(circuit63);
        // Part of head

        ModelRenderer circuit64 = new ModelRenderer(this, 0, 0);
        circuit64.addBox(-10F, -2F, 11F, 2, 1, 1);
        body.addChild(circuit64);

        ModelRenderer circuit65 = new ModelRenderer(this, 0, 0);
        circuit65.addBox(-12F, -1F, 11F, 3, 1, 1);
        body.addChild(circuit65);

        ModelRenderer circuit66 = new ModelRenderer(this, 0, 0);
        circuit66.addBox(-10F, -7F, 11F, 1, 5, 1);
        body.addChild(circuit66);

        ModelRenderer circuit67 = new ModelRenderer(this, 0, 0);
        circuit67.addBox(-9F, -7F, 11F, 5, 1, 1);
        body.addChild(circuit67);

        ModelRenderer circuit68 = new ModelRenderer(this, 0, 0);
        circuit68.addBox(-5F, -7F, 9F, 1, 1, 2);
        body.addChild(circuit68);

        ModelRenderer circuit69 = new ModelRenderer(this, 0, 0);
        circuit69.addBox(-7F, -8F, 9F, 3, 1, 1);
        body.addChild(circuit69);

        ModelRenderer circuit70 = new ModelRenderer(this, 0, 0);
        circuit70.addBox(-7F, -10F, 9F, 1, 2, 1);
        body.addChild(circuit70);

        ModelRenderer circuit71 = new ModelRenderer(this, 0, 0);
        circuit71.addBox(-5F, -10F, 6F, 1, 1, 3);
        body.addChild(circuit71);

        ModelRenderer circuit72 = new ModelRenderer(this, 0, 0);
        circuit72.addBox(-7F, -10F, 8F, 2, 1, 1);
        body.addChild(circuit72);

        ModelRenderer circuit73 = new ModelRenderer(this, 0, 0);
        circuit73.addBox(-5F, -12F, 6F, 1, 2, 1);
        body.addChild(circuit73);

        ModelRenderer circuit74 = new ModelRenderer(this, 0, 0);
        circuit74.addBox(-12F, -2F, 8F, 1, 1, 4);
        body.addChild(circuit74);

        ModelRenderer circuit75 = new ModelRenderer(this, 0, 0);
        circuit75.addBox(-1F, -5F, 11F, 3, 1, 1);
        body.addChild(circuit75);

        ModelRenderer circuit76 = new ModelRenderer(this, 0, 0);
        circuit76.addBox(-1F, -7F, 11F, 1, 2, 1);
        body.addChild(circuit76);

        ModelRenderer circuit77 = new ModelRenderer(this, 0, 0);
        circuit77.addBox(-4F, -7F, 10F, 5, 1, 1);
        body.addChild(circuit77);

        ModelRenderer circuit78 = new ModelRenderer(this, 0, 0);
        circuit78.addBox(0F, -10F, 9F, 1, 4, 1);
        body.addChild(circuit78);

        ModelRenderer circuit79 = new ModelRenderer(this, 0, 0);
        circuit79.addBox(0F, -10F, 8F, 3, 1, 1);
        body.addChild(circuit79);

        ModelRenderer circuit80 = new ModelRenderer(this, 0, 0);
        circuit80.addBox(2F, -10F, 7F, 6, 1, 1);
        body.addChild(circuit80);

        ModelRenderer circuit81 = new ModelRenderer(this, 0, 0);
        circuit81.addBox(7F, -10F, 5F, 1, 1, 2);
        body.addChild(circuit81);

        ModelRenderer circuit82 = new ModelRenderer(this, 0, 0);
        circuit82.addBox(6F, -10F, 4F, 2, 1, 1);
        body.addChild(circuit82);

        ModelRenderer circuit83 = new ModelRenderer(this, 0, 0);
        circuit83.addBox(6F, -12F, 4F, 1, 2, 1);
        body.addChild(circuit83);

        ModelRenderer circuit84 = new ModelRenderer(this, 0, 0);
        circuit84.addBox(3F, -12F, 5F, 4, 1, 1);
        body.addChild(circuit84);

        // Part of head
        ModelRenderer circuit85 = new ModelRenderer(this, 0, 0);
        circuit85.addBox(3F, -17F, 5F, 1, 2, 1);
        head.addChild(circuit85);

        ModelRenderer circuit86 = new ModelRenderer(this, 0, 0);
        circuit86.addBox(4F, -18F, 5F, 1, 2, 1);
        head.addChild(circuit86);

        ModelRenderer circuit87 = new ModelRenderer(this, 0, 0);
        circuit87.addBox(2F, -18F, 4F, 3, 1, 1);
        head.addChild(circuit87);

        ModelRenderer circuit88 = new ModelRenderer(this, 0, 0);
        circuit88.addBox(2F, -20F, 3F, 1, 3, 1);
        head.addChild(circuit88);

        ModelRenderer circuit89 = new ModelRenderer(this, 0, 0);
        circuit89.addBox(0F, -20F, 2F, 3, 1, 1);
        head.addChild(circuit89);

        ModelRenderer circuit90 = new ModelRenderer(this, 0, 0);
        circuit90.addBox(0F, -22F, 2F, 1, 2, 1);
        head.addChild(circuit90);
        // Part of head

        ModelRenderer circuit91 = new ModelRenderer(this, 0, 0);
        circuit91.addBox(11F, -7F, 0F, 1, 1, 6);
        body.addChild(circuit91);

        ModelRenderer circuit92 = new ModelRenderer(this, 0, 0);
        circuit92.addBox(11F, -6F, 0F, 1, 1, 1);
        body.addChild(circuit92);

        ModelRenderer circuit93 = new ModelRenderer(this, 0, 0);
        circuit93.addBox(11F, -6F, 4F, 1, 1, 1);
        body.addChild(circuit93);

        ModelRenderer circuit94 = new ModelRenderer(this, 0, 0);
        circuit94.addBox(9F, -7F, 5F, 2, 1, 1);
        body.addChild(circuit94);

        ModelRenderer circuit95 = new ModelRenderer(this, 0, 0);
        circuit95.addBox(9F, -10F, 6F, 1, 4, 1);
        body.addChild(circuit95);

        ModelRenderer circuit96 = new ModelRenderer(this, 0, 0);
        circuit96.addBox(8F, -10F, 5F, 2, 1, 1);
        body.addChild(circuit96);

        ModelRenderer circuit97 = new ModelRenderer(this, 0, 0);
        circuit97.addBox(11F, -7F, -5F, 1, 2, 1);
        body.addChild(circuit97);

        ModelRenderer circuit98 = new ModelRenderer(this, 0, 0);
        circuit98.addBox(10F, -7F, -5F, 1, 1, 4);
        body.addChild(circuit98);

        ModelRenderer circuit99 = new ModelRenderer(this, 0, 0);
        circuit99.addBox(9F, -10F, -2F, 1, 4, 1);
        body.addChild(circuit99);

        ModelRenderer circuit100 = new ModelRenderer(this, 0, 0);
        circuit100.addBox(8F, -10F, -4F, 1, 1, 5);
        body.addChild(circuit100);

        ModelRenderer circuit101 = new ModelRenderer(this, 0, 0);
        circuit101.addBox(6F, -10F, 0F, 2, 1, 1);
        body.addChild(circuit101);

        ModelRenderer circuit102 = new ModelRenderer(this, 0, 0);
        circuit102.addBox(6F, -12F, -1F, 1, 3, 1);
        body.addChild(circuit102);

        ModelRenderer circuit103 = new ModelRenderer(this, 0, 0);
        circuit103.addBox(5F, -13F, -1F, 1, 2, 1);
        body.addChild(circuit103);

        // Part of head
        ModelRenderer circuit104 = new ModelRenderer(this, 0, 0);
        circuit104.addBox(4F, -20F, 0F, 1, 3, 1);
        head.addChild(circuit104);

        ModelRenderer circuit105 = new ModelRenderer(this, 0, 0);
        circuit105.addBox(5F, -18F, 0F, 1, 2, 1);
        head.addChild(circuit105);

        ModelRenderer circuit106 = new ModelRenderer(this, 0, 0);
        circuit106.addBox(2F, -20F, -1F, 3, 1, 1);
        head.addChild(circuit106);

        ModelRenderer circuit107 = new ModelRenderer(this, 0, 0);
        circuit107.addBox(2F, -20F, 0F, 1, 1, 2);
        head.addChild(circuit107);
        // Part of head

        ModelRenderer circuit108 = new ModelRenderer(this, 0, 0);
        circuit108.addBox(8F, -3F, -12F, 2, 1, 1);
        body.addChild(circuit108);

        ModelRenderer circuit109 = new ModelRenderer(this, 0, 0);
        circuit109.addBox(9F, -2F, -12F, 1, 4, 1);
        body.addChild(circuit109);

        ModelRenderer circuit110 = new ModelRenderer(this, 0, 0);
        circuit110.addBox(8F, 1F, -12F, 1, 1, 1);
        body.addChild(circuit110);

        ModelRenderer circuit111 = new ModelRenderer(this, 0, 0);
        circuit111.addBox(10F, -2F, -12F, 2, 1, 1);
        body.addChild(circuit111);

        ModelRenderer circuit112 = new ModelRenderer(this, 0, 0);
        circuit112.addBox(11F, -4F, -11F, 1, 3, 1);
        body.addChild(circuit112);

        ModelRenderer circuit113 = new ModelRenderer(this, 0, 0);
        circuit113.addBox(11F, -4F, -10F, 1, 1, 2);
        body.addChild(circuit113);

        ModelRenderer circuit114 = new ModelRenderer(this, 0, 0);
        circuit114.addBox(-12F, 0F, -12F, 4, 1, 1);
        body.addChild(circuit114);

        ModelRenderer circuit115 = new ModelRenderer(this, 0, 0);
        circuit115.addBox(-12F, 0F, -11F, 1, 3, 1);
        body.addChild(circuit115);

        ModelRenderer circuit116 = new ModelRenderer(this, 0, 0);
        circuit116.addBox(-12F, 2F, -10F, 1, 1, 2);
        body.addChild(circuit116);

        ModelRenderer circuit117 = new ModelRenderer(this, 0, 0);
        circuit117.addBox(6F, -5F, 11F, 1, 1, 1);
        body.addChild(circuit117);

        ModelRenderer circuit118 = new ModelRenderer(this, 0, 0);
        circuit118.addBox(6F, -6F, 11F, 4, 1, 1);
        body.addChild(circuit118);

        ModelRenderer circuit119 = new ModelRenderer(this, 0, 0);
        circuit119.addBox(9F, -5F, 11F, 1, 3, 1);
        body.addChild(circuit119);

        ModelRenderer circuit120 = new ModelRenderer(this, 0, 0);
        circuit120.addBox(10F, -3F, 11F, 1, 4, 1);
        body.addChild(circuit120);

        ModelRenderer circuit121 = new ModelRenderer(this, 0, 0);
        circuit121.addBox(8F, 0F, 11F, 2, 1, 1);
        body.addChild(circuit121);

        ModelRenderer circuit122 = new ModelRenderer(this, 0, 0);
        circuit122.addBox(11F, -1F, 9F, 1, 1, 3);
        body.addChild(circuit122);

        ModelRenderer circuit123 = new ModelRenderer(this, 0, 0);
        circuit123.addBox(11F, -1F, 8F, 1, 2, 1);
        body.addChild(circuit123);

        ModelRenderer circuit124 = new ModelRenderer(this, 0, 0);
        circuit124.addBox(7F, -7F, 9F, 1, 1, 3);
        body.addChild(circuit124);

        ModelRenderer circuit125 = new ModelRenderer(this, 0, 0);
        circuit125.addBox(6F, -10F, 9F, 1, 3, 1);
        body.addChild(circuit125);

        ModelRenderer circuit126 = new ModelRenderer(this, 0, 0);
        circuit126.addBox(7F, -8F, 9F, 1, 1, 1);
        body.addChild(circuit126);

        ModelRenderer circuit127 = new ModelRenderer(this, 0, 0);
        circuit127.addBox(6F, -10F, 8F, 1, 1, 1);
        body.addChild(circuit127);

        // Legs

        // FR
        legFR = new ModelRenderer(this, 92, 86);
        legFR.addBox(-18F, 0F, 0F, 6, 2, 2);
        legFR.rotateAngleY = -0.5089647F;
        legFR.rotateAngleZ = 0.1115358F;
        body.addChild(legFR);

        legJointFR = new ModelRenderer(this, 92, 92);
        legJointFR.addBox(-22F, -3F, -1F, 4, 6, 4);
        legFR.addChild(legJointFR);

        ModelRenderer legMidFR = new ModelRenderer(this, 72, 100);
        legMidFR.addBox(-21.5F, 3F, -0.5F, 3, 7, 3);
        legJointFR.addChild(legMidFR);

        ModelRenderer legThickFR = new ModelRenderer(this, 92, 104);
        legThickFR.addBox(-24F, 10F, -2F, 6, 16, 6);
        legMidFR.addChild(legThickFR);

        ModelRenderer legTipFR = new ModelRenderer(this, 72, 112);
        legTipFR.addBox(-22F, 26F, 0F, 2, 4, 2);
        legThickFR.addChild(legTipFR);

        // FMR
        legFMR = new ModelRenderer(this, 92, 86);
        legFMR.addBox(-18F, 0F, 0F, 6, 2, 2);
        legFMR.rotateAngleY = -0.2115358F;
        legFMR.rotateAngleZ = 0.1115358F;
        body.addChild(legFMR);

        legJointFMR = new ModelRenderer(this, 92, 92);
        legJointFMR.addBox(-22F, -3F, -1F, 4, 6, 4);
        legFMR.addChild(legJointFMR);

        ModelRenderer legMidFMR = new ModelRenderer(this, 72, 100);
        legMidFMR.addBox(-21.5F, 3F, -0.5F, 3, 7, 3);
        legJointFMR.addChild(legMidFMR);

        ModelRenderer legThickFMR = new ModelRenderer(this, 92, 104);
        legThickFMR.addBox(-24F, 10F, -2F, 6, 16, 6);
        legMidFMR.addChild(legThickFMR);

        ModelRenderer legTipFMR = new ModelRenderer(this, 72, 112);
        legTipFMR.addBox(-22F, 26F, 0F, 2, 4, 2);
        legThickFMR.addChild(legTipFMR);

        // BMR
        legBMR = new ModelRenderer(this, 92, 86);
        legBMR.addBox(-18F, 0F, 0F, 6, 2, 2);
        legBMR.rotateAngleY = 0.1487144F;
        legBMR.rotateAngleZ = 0.1115358F;
        body.addChild(legBMR);

        legJointBMR = new ModelRenderer(this, 92, 92);
        legJointBMR.addBox(-22F, -3F, -1F, 4, 6, 4);
        legBMR.addChild(legJointBMR);

        ModelRenderer legMidBMR = new ModelRenderer(this, 72, 100);
        legMidBMR.addBox(-21.5F, 3F, -0.5F, 3, 7, 3);
        legJointBMR.addChild(legMidBMR);

        ModelRenderer legThickBMR = new ModelRenderer(this, 92, 104);
        legThickBMR.addBox(-24F, 10F, -2F, 6, 16, 6);
        legMidBMR.addChild(legThickBMR);

        ModelRenderer legTipBMR = new ModelRenderer(this, 72, 112);
        legTipBMR.addBox(-22F, 26F, 0F, 2, 4, 2);
        legThickBMR.addChild(legTipBMR);

        // BR
        legBR = new ModelRenderer(this, 92, 86);
        legBR.addBox(-19F, 0F, 0F, 6, 2, 2);
        legBR.rotateAngleY = 0.5089647F;
        legBR.rotateAngleZ = 0.0371786F;
        body.addChild(legBR);

        legJointBR = new ModelRenderer(this, 92, 92);
        legJointBR.addBox(-23F, -3F, -1F, 4, 6, 4);
        legBR.addChild(legJointBR);

        ModelRenderer legMidBR = new ModelRenderer(this, 72, 100);
        legMidBR.addBox(-22.5F, 3F, -0.5F, 3, 7, 3);
        legJointBR.addChild(legMidBR);

        ModelRenderer legThickBR = new ModelRenderer(this, 92, 104);
        legThickBR.addBox(-23F, 10F, -2F, 6, 16, 6);
        legMidBR.addChild(legThickBR);

        ModelRenderer legTipBR = new ModelRenderer(this, 72, 112);
        legTipBR.addBox(-22F, 26F, 0F, 2, 4, 2);
        legThickBR.addChild(legTipBR);

        // FL
        legFL = new ModelRenderer(this, 92, 86);
        legFL.addBox(12F, 0F, 0F, 6, 2, 2);
        legFL.rotateAngleY = 0.5089647F;
        legFL.rotateAngleZ = -0.1115358F;
        body.addChild(legFL);

        legJointFL = new ModelRenderer(this, 92, 92);
        legJointFL.addBox(18F, -3F, -1F, 4, 6, 4);
        legFL.addChild(legJointFL);

        ModelRenderer legMidFL = new ModelRenderer(this, 72, 100);
        legMidFL.addBox(18.5F, 3F, -0.5F, 3, 7, 3);
        legJointFL.addChild(legMidFL);

        ModelRenderer legThickFL = new ModelRenderer(this, 92, 104);
        legThickFL.addBox(18F, 10F, -2F, 6, 16, 6);
        legMidFL.addChild(legThickFL);

        ModelRenderer legTipFL = new ModelRenderer(this, 72, 112);
        legTipFL.addBox(20F, 26F, 0F, 2, 4, 2);
        legThickFL.addChild(legTipFL);

        // FML
        legFML = new ModelRenderer(this, 92, 86);
        legFML.addBox(12F, 0F, 0F, 6, 2, 2);
        legFML.rotateAngleX = 0.2115358F;
        legFML.rotateAngleZ = -0.1115358F;
        body.addChild(legFML);

        legJointFML = new ModelRenderer(this, 92, 92);
        legJointFML.addBox(18F, -3F, -1F, 4, 6, 4);
        legFML.addChild(legJointFML);

        ModelRenderer legMidFML = new ModelRenderer(this, 72, 100);
        legMidFML.addBox(18.5F, 3F, -0.5F, 3, 7, 3);
        legJointFML.addChild(legMidFML);

        ModelRenderer legThickFML = new ModelRenderer(this, 92, 104);
        legThickFML.addBox(18F, 10F, -2F, 6, 16, 6);
        legMidFML.addChild(legThickFML);

        ModelRenderer legTipFML = new ModelRenderer(this, 72, 111);
        legTipFML.addBox(20F, 26F, 0F, 2, 4, 2);
        legThickFML.addChild(legTipFML);

        // BML
        legBML = new ModelRenderer(this, 92, 86);
        legBML.addBox(12F, 0F, 0F, 6, 2, 2);
        legBML.rotateAngleY = -0.1487144F;
        legBML.rotateAngleZ = -0.1115358F;
        body.addChild(legBML);

        legJointBML = new ModelRenderer(this, 92, 92);
        legJointBML.addBox(18F, -3F, -1F, 4, 6, 4);
        legBML.addChild(legJointBML);

        ModelRenderer legMidBML = new ModelRenderer(this, 72, 100);
        legMidBML.addBox(18.5F, 3F, -0.5F, 3, 7, 3);
        legJointBML.addChild(legMidBML);

        ModelRenderer legThickBML = new ModelRenderer(this, 92, 104);
        legThickBML.addBox(18F, 10F, -2F, 6, 16, 6);
        legMidBML.addChild(legThickBML);

        ModelRenderer legTipBML = new ModelRenderer(this, 72, 112);
        legTipBML.addBox(20F, 26F, 0F, 2, 4, 2);
        legThickBML.addChild(legTipBML);

        // BL
        legBL = new ModelRenderer(this, 92, 86);
        legBL.addBox(12F, 0F, 0F, 6, 2, 2);
        legBL.rotateAngleY = -0.5089647F;
        legBL.rotateAngleZ = -0.0371786F;
        body.addChild(legBL);

        legJointBL = new ModelRenderer(this, 92, 92);
        legJointBL.addBox(18F, -3F, -1F, 4, 6, 4);
        legBL.addChild(legJointBL);

        ModelRenderer legMidBL = new ModelRenderer(this, 72, 100);
        legMidBL.addBox(18.5F, 3F, -0.5F, 3, 7, 3);
        legJointBL.addChild(legMidBL);

        ModelRenderer legThickBL = new ModelRenderer(this, 92, 104);
        legThickBL.addBox(18F, 10F, -2F, 6, 16, 6);
        legMidBL.addChild(legThickBL);

        ModelRenderer legTipBL = new ModelRenderer(this, 72, 112);
        legTipBL.addBox(20F, 26F, 0F, 2, 4, 2);
        legThickBL.addChild(legTipBL);

        // Arms
        ModelRenderer armRightNob = new ModelRenderer(this, 0, 48);
        armRightNob.addBox(-9F, 4F, -14F, 3, 3, 3);
        body.addChild(armRightNob);

        armRight = new ModelRenderer(this, 0, 64);
        armRight.addBox(-8F, 5F, -19F, 1, 1, 5);
        armRightNob.addChild(armRight);

        ModelRenderer armRightThick = new ModelRenderer(this, 0, 100);
        armRightThick.addBox(-9F, 4F, -28F, 3, 3, 9);
        armRight.addChild(armRightThick);

        ModelRenderer armRightUpperBit = new ModelRenderer(this, 0, 74);
        armRightUpperBit.addBox(-8F, 3F, -28F, 1, 1, 2);
        armRightThick.addChild(armRightUpperBit);

        ModelRenderer armRightLowerBit = new ModelRenderer(this, 0, 74);
        armRightLowerBit.addBox(-8F, 7F, -28F, 1, 1, 2);
        armRightThick.addChild(armRightLowerBit);

        ModelRenderer armLeftNob = new ModelRenderer(this, 0, 48);
        armLeftNob.addBox(7F, 4F, -14F, 3, 3, 3);
        body.addChild(armLeftNob);

        armLeft = new ModelRenderer(this, 0, 64);
        armLeft.addBox(8F, 5F, -19F, 1, 1, 5);
        armLeftNob.addChild(armLeft);

        ModelRenderer armLeftThick = new ModelRenderer(this, 0, 100);
        armLeftThick.addBox(7F, 4F, -28F, 3, 3, 9);
        armLeft.addChild(armLeftThick);

        ModelRenderer armLeftUpperBit = new ModelRenderer(this, 0, 74);
        armLeftUpperBit.addBox(8F, 3F, -28F, 1, 1, 2);
        armLeftThick.addChild(armLeftUpperBit);

        ModelRenderer armLeftLowerBit = new ModelRenderer(this, 0, 74);
        armLeftLowerBit.addBox(8F, 7F, -28F, 1, 1, 2);
        armLeftThick.addChild(armLeftLowerBit);

        // Sabers
        saberRight = new ModelRenderer(this, 12, 110);
        saberRight.addBox(-8F, 4F, -42F, 1, 3, 14);
        armRight.addChild(saberRight);

        ModelRenderer saberRightUpper = new ModelRenderer(this, 30, 102);
        saberRightUpper.addBox(-8F, 3F, -54F, 1, 1, 18);
        saberRight.addChild(saberRightUpper);

        ModelRenderer saberRightLower = new ModelRenderer(this, 32, 100);
        saberRightLower.addBox(-8F, 5F, -48F, 1, 1, 6);
        saberRight.addChild(saberRightLower);

        saberLeft = new ModelRenderer(this, 12, 110);
        saberLeft.addBox(8F, 4F, -42F, 1, 3, 14);
        armLeft.addChild(saberLeft);

        ModelRenderer saberLeftUpper = new ModelRenderer(this, 30, 102);
        saberLeftUpper.addBox(8F, 3F, -54F, 1, 1, 18);
        saberLeft.addChild(saberLeftUpper);

        ModelRenderer saberLeftLower = new ModelRenderer(this, 32, 100);
        saberLeftLower.addBox(8F, 5F, -48F, 1, 1, 6);
        saberLeft.addChild(saberLeftLower);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        if (!(entity instanceof EntityOverlordCombat)) {
            saberRight.showModel = saberLeft.showModel = false;
            body.render(f5);
            return;
        }

        EntityOverlordCombat combat = (EntityOverlordCombat) entity;
        saberRight.showModel = saberLeft.showModel = combat.getAttackTime() > 0;
        body.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        if (!(entity instanceof EntityOverlordCombat)) return;

        EntityOverlordCombat combat = (EntityOverlordCombat) entity;

        armRight.rotateAngleX = armLeft.rotateAngleX = armRight.rotateAngleZ = armLeft.rotateAngleZ = armRight.rotateAngleY = armLeft.rotateAngleY = 0F;
        armRight.offsetX = 0F;
        armLeft.offsetX = 0F;

        if (combat.getHurtTime() > 0 || combat.getReflectionTicks() > 0 || combat.getChargeTicks() > 0) {
            head.offsetY = 0.3F;
            head.rotateAngleY = head.rotateAngleX = 0F;
        } else {
            head.offsetY = 0F;
            head.rotateAngleY = f3 / (180F / (float) Math.PI);
            head.rotateAngleX = f4 / (180F / (float) Math.PI);
        }

        body.offsetY = 0.02F + this.simplifyAngle(combat.ticksExisted, 45.0F) * -0.02F;

        if (f != 0F) {
            legFR.rotateAngleX = -0.08F + this.simplifyAngle(f, 20.0F) * 0.08F;
            legFR.rotateAngleZ = 0.1115358F + 0.15F + this.simplifyAngle(f, 20.0F) * -0.15F;

            legFL.rotateAngleX = -0.08F + this.simplifyAngle(f - 1, 20.0F) * 0.08F;
            legFL.rotateAngleZ = -0.1115358F - 0.15F + this.simplifyAngle(f - 1, 20.0F) * 0.15F;

            legFMR.rotateAngleX = -0.08F + this.simplifyAngle(f - 10, 20.0F) * 0.08F;
            legFMR.rotateAngleZ = 0.1115358F + 0.15F + this.simplifyAngle(f - 10, 20.0F) * -0.15F;

            legFML.rotateAngleX = -0.08F + this.simplifyAngle(f - 11, 20.0F) * 0.08F;
            legFML.rotateAngleZ = -0.1115358F - 0.15F + this.simplifyAngle(f - 11, 20.0F) * 0.15F;

            legBMR.rotateAngleX = -0.08F + this.simplifyAngle(f - 17, 20.0F) * 0.08F;
            legBMR.rotateAngleZ = 0.1115358F + 0.15F + this.simplifyAngle(f - 17, 20.0F) * -0.15F;

            legBML.rotateAngleX = -0.08F + this.simplifyAngle(f - 18, 20.0F) * 0.08F;
            legBML.rotateAngleZ = -0.1115358F - 0.15F + this.simplifyAngle(f - 18, 20.0F) * 0.15F;

            legBR.rotateAngleX = -0.08F + this.simplifyAngle(f - 22, 20.0F) * 0.08F;
            legBR.rotateAngleZ = 0.0371786F + 0.15F + this.simplifyAngle(f - 22, 20.0F) * -0.15F;

            legBL.rotateAngleX = -0.08F + this.simplifyAngle(f - 20, 20.0F) * 0.08F;
            legBL.rotateAngleZ = -0.0371786F - 0.15F + this.simplifyAngle(f - 20, 20.0F) * 0.15F;
        } else {
            legFR.rotateAngleX = legFL.rotateAngleX = legFMR.rotateAngleX = legFML.rotateAngleX = legBML.rotateAngleX = legBMR.rotateAngleX = legBR.rotateAngleX = legBL.rotateAngleX = 0F;
            legFR.rotateAngleZ = legFMR.rotateAngleZ = legBMR.rotateAngleZ = 0.1115358F;
            legFL.rotateAngleZ = legFML.rotateAngleZ = legBML.rotateAngleZ = -0.1115358F;
            legBL.rotateAngleZ = -0.0371786F;
            legBR.rotateAngleZ = 0.0371786F;
        }

        if (!combat.onGround) {
            legFR.rotateAngleX = -0.08F + this.simplifyAngle(combat.ticksExisted, 15.0F) * 0.08F;
            legFR.rotateAngleZ = 0.1115358F + 0.15F + this.simplifyAngle(combat.ticksExisted, 15.0F) * -0.15F;

            legFL.rotateAngleX = -0.08F + this.simplifyAngle(combat.ticksExisted - 1, 15.0F) * 0.08F;
            legFL.rotateAngleZ = -0.1115358F - 0.15F + this.simplifyAngle(combat.ticksExisted - 1, 15.0F) * 0.15F;

            legFMR.rotateAngleX = -0.08F + this.simplifyAngle(combat.ticksExisted - 10, 15.0F) * 0.08F;
            legFMR.rotateAngleZ = 0.1115358F + 0.15F + this.simplifyAngle(combat.ticksExisted - 10, 15.0F) * -0.15F;

            legFML.rotateAngleX = -0.08F + this.simplifyAngle(combat.ticksExisted - 11, 15.0F) * 0.08F;
            legFML.rotateAngleZ = -0.1115358F - 0.15F + this.simplifyAngle(combat.ticksExisted - 11, 15.0F) * 0.15F;

            legBMR.rotateAngleX = -0.08F + this.simplifyAngle(combat.ticksExisted - 17, 15.0F) * 0.08F;
            legBMR.rotateAngleZ = 0.1115358F + 0.15F + this.simplifyAngle(combat.ticksExisted - 17, 15.0F) * -0.15F;

            legBML.rotateAngleX = -0.08F + this.simplifyAngle(combat.ticksExisted - 18, 15.0F) * 0.08F;
            legBML.rotateAngleZ = -0.1115358F - 0.15F + this.simplifyAngle(combat.ticksExisted - 18, 15.0F) * 0.15F;

            legBR.rotateAngleX = -0.08F + this.simplifyAngle(combat.ticksExisted - 22, 15.0F) * 0.08F;
            legBR.rotateAngleZ = 0.0371786F + 0.15F + this.simplifyAngle(combat.ticksExisted - 22, 15.0F) * -0.15F;

            legBL.rotateAngleX = -0.08F + this.simplifyAngle(combat.ticksExisted - 20, 15.0F) * 0.08F;
            legBL.rotateAngleZ = -0.0371786F - 0.15F + this.simplifyAngle(combat.ticksExisted - 20, 15.0F) * 0.15F;
        }

        if (combat.getAttackTime() > 0) {
            armRight.rotateAngleX = -0.125F + this.simplifyAngle(combat.getAttackTime(), 20.0F) * 0.125F;
            armLeft.rotateAngleX = -0.125F + this.simplifyAngle(combat.getAttackTime(), 20.0F) * 0.125F;

            armRight.rotateAngleY = 0.285F + this.simplifyAngle(combat.getAttackTime(), 10.0F) * -0.285F;
            armLeft.rotateAngleY = -0.285F + this.simplifyAngle(combat.getAttackTime(), 10.0F) * 0.285F;

            armRight.offsetX = 0.2F + this.simplifyAngle(combat.getAttackTime(), 10.0F) * -0.2F;
            armLeft.offsetX = -0.2F + this.simplifyAngle(combat.getAttackTime(), 10.0F) * 0.2F;
        } else {
            if (combat.getGrappleTicks() > 0) {
                legFR.rotateAngleX = legFL.rotateAngleX = legFMR.rotateAngleX = legFML.rotateAngleX = legBML.rotateAngleX = legBMR.rotateAngleX = legBR.rotateAngleX = legBL.rotateAngleX = 0F;
                legFR.rotateAngleZ = legFMR.rotateAngleZ = legBMR.rotateAngleZ = 0.1115358F;
                legFL.rotateAngleZ = legFML.rotateAngleZ = legBML.rotateAngleZ = -0.1115358F;
                legBL.rotateAngleZ = -0.0371786F;
                legBR.rotateAngleZ = 0.0371786F;

                armRight.rotateAngleX = 0.45F;
                armLeft.rotateAngleX = 0.45F;

                armRight.offsetY = armLeft.offsetY = -0.3F;
            } else {
                armRight.offsetY = armLeft.offsetY = 0F;
            }

            if (combat.getLeapTicks() > 0) {
                if (combat.onGround) {
                    body.offsetY = 0.2F;

                    legFR.rotateAngleX = legFL.rotateAngleX = legFMR.rotateAngleX = legFML.rotateAngleX = legBML.rotateAngleX = legBMR.rotateAngleX = legBR.rotateAngleX = legBL.rotateAngleX = 0F;
                    legFR.rotateAngleZ = legFMR.rotateAngleZ = legBMR.rotateAngleZ = 0.2115358F;
                    legFL.rotateAngleZ = legFML.rotateAngleZ = legBML.rotateAngleZ = -0.2115358F;
                    legBL.rotateAngleZ = -0.1371786F;
                    legBR.rotateAngleZ = 0.1371786F;
                }
            }
        }

        if (combat.getUnstableTicks() > 0) {
            body.offsetX = (combat.getRNG()
                .nextFloat()
                - combat.getRNG()
                    .nextFloat())
                * 0.5F;
            body.offsetY = (combat.getRNG()
                .nextFloat()
                - combat.getRNG()
                    .nextFloat())
                * 0.5F;
            body.offsetZ = (combat.getRNG()
                .nextFloat()
                - combat.getRNG()
                    .nextFloat())
                * 0.5F;
        } else {
            body.offsetX = body.offsetZ = 0F;
        }

        if (combat.getAttackTime() > 0) {
            armRight.rotateAngleX = -0.085F + this.simplifyAngle(combat.getHurtTime(), 40.0F) * 0.085F;
            armLeft.rotateAngleX = -0.085F + this.simplifyAngle(combat.getHurtTime(), 40.0F) * 0.085F;

            armRight.rotateAngleZ = 0.15F + this.simplifyAngle(combat.getHurtTime(), 40.0F) * -0.15F;
            armLeft.rotateAngleZ = -0.15F + this.simplifyAngle(combat.getHurtTime(), 40.0F) * 0.15F;
        }

    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }
}
