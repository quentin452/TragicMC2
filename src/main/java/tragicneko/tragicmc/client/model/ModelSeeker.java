package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSeeker extends ModelBase {

    private ModelRenderer eye;

    public ModelSeeker() {
        textureWidth = 64;
        textureHeight = 32;

        eye = new ModelRenderer(this, 24, 12);
        eye.addBox(-4F, -4F, -4F, 8, 8, 8);
        eye.setRotationPoint(0F, 12F, 0F);

        ModelRenderer shellRight = new ModelRenderer(this, 16, 0);
        shellRight.addBox(-5F, -5F, -2F, 1, 10, 6);
        eye.addChild(shellRight);

        ModelRenderer shellTop = new ModelRenderer(this, 32, 0);
        shellTop.addBox(-4F, -5F, -2F, 8, 1, 6);
        eye.addChild(shellTop);

        ModelRenderer shellLeft = new ModelRenderer(this, 16, 0);
        shellLeft.addBox(4F, -5F, -2F, 1, 10, 6);
        eye.addChild(shellLeft);

        ModelRenderer shellBottom = new ModelRenderer(this, 32, 0);
        shellBottom.addBox(-4F, 4F, -2F, 8, 1, 6);
        eye.addChild(shellBottom);

        ModelRenderer shellBack = new ModelRenderer(this, 0, 20);
        shellBack.addBox(-3F, -3F, 4F, 6, 6, 2);
        eye.addChild(shellBack);

        ModelRenderer circuit = new ModelRenderer(this, 0, 0);
        circuit.addBox(0F, -6F, -3F, 1, 1, 8);
        eye.addChild(circuit);

        ModelRenderer circuit2 = new ModelRenderer(this, 0, 0);
        circuit2.addBox(-1F, -6F, 0F, 1, 1, 1);
        eye.addChild(circuit2);

        ModelRenderer circuit3 = new ModelRenderer(this, 0, 0);
        circuit3.addBox(-2F, -6F, 0F, 1, 1, 5);
        eye.addChild(circuit3);

        ModelRenderer circuit4 = new ModelRenderer(this, 0, 0);
        circuit4.addBox(3F, -6F, 0F, 1, 1, 5);
        eye.addChild(circuit4);

        ModelRenderer circuit5 = new ModelRenderer(this, 0, 0);
        circuit5.addBox(2F, -6F, 4F, 1, 3, 1);
        eye.addChild(circuit5);

        ModelRenderer circuit6 = new ModelRenderer(this, 0, 0);
        circuit6.addBox(-1F, -6F, 4F, 1, 3, 1);
        eye.addChild(circuit6);

        ModelRenderer circuit7 = new ModelRenderer(this, 0, 0);
        circuit7.addBox(4F, -6F, -3F, 1, 1, 4);
        eye.addChild(circuit7);

        ModelRenderer circuit8 = new ModelRenderer(this, 0, 0);
        circuit8.addBox(5F, -6F, 0F, 1, 1, 3);
        eye.addChild(circuit8);

        ModelRenderer circuit9 = new ModelRenderer(this, 0, 0);
        circuit9.addBox(5F, -5F, 2F, 1, 2, 1);
        eye.addChild(circuit9);

        ModelRenderer circuit10 = new ModelRenderer(this, 0, 0);
        circuit10.addBox(-6F, -6F, -1F, 1, 1, 3);
        eye.addChild(circuit10);

        ModelRenderer circuit11 = new ModelRenderer(this, 0, 0);
        circuit11.addBox(-6F, -5F, 1F, 1, 2, 1);
        eye.addChild(circuit11);

        ModelRenderer circuit12 = new ModelRenderer(this, 0, 0);
        circuit12.addBox(-6F, -4F, 2F, 1, 1, 3);
        eye.addChild(circuit12);

        ModelRenderer circuit13 = new ModelRenderer(this, 0, 0);
        circuit13.addBox(-5F, -4F, 4F, 1, 2, 1);
        eye.addChild(circuit13);

        ModelRenderer circuit14 = new ModelRenderer(this, 0, 0);
        circuit14.addBox(-4F, -3F, 4F, 1, 1, 1);
        eye.addChild(circuit14);

        ModelRenderer circuit15 = new ModelRenderer(this, 0, 0);
        circuit15.addBox(5F, -4F, 3F, 1, 1, 2);
        eye.addChild(circuit15);

        ModelRenderer circuit16 = new ModelRenderer(this, 0, 0);
        circuit16.addBox(3F, -4F, 4F, 2, 1, 1);
        eye.addChild(circuit16);

        ModelRenderer circuit17 = new ModelRenderer(this, 0, 0);
        circuit17.addBox(-6F, 0F, 0F, 1, 1, 5);
        eye.addChild(circuit17);

        ModelRenderer circuit18 = new ModelRenderer(this, 0, 0);
        circuit18.addBox(-6F, -1F, 4F, 3, 1, 1);
        eye.addChild(circuit18);

        ModelRenderer circuit19 = new ModelRenderer(this, 0, 0);
        circuit19.addBox(-6F, 2F, 2F, 1, 1, 3);
        eye.addChild(circuit19);

        ModelRenderer circuit20 = new ModelRenderer(this, 0, 0);
        circuit20.addBox(-6F, 3F, 4F, 4, 1, 1);
        eye.addChild(circuit20);

        ModelRenderer circuit21 = new ModelRenderer(this, 0, 0);
        circuit21.addBox(-6F, -1F, -3F, 1, 1, 4);
        eye.addChild(circuit21);

        ModelRenderer circuit22 = new ModelRenderer(this, 0, 0);
        circuit22.addBox(-6F, 2F, 1F, 1, 4, 1);
        eye.addChild(circuit22);

        ModelRenderer circuit23 = new ModelRenderer(this, 0, 0);
        circuit23.addBox(-5F, 5F, -3F, 1, 1, 5);
        eye.addChild(circuit23);

        ModelRenderer circuit24 = new ModelRenderer(this, 0, 0);
        circuit24.addBox(0F, 5F, 0F, 1, 1, 5);
        eye.addChild(circuit24);

        ModelRenderer circuit25 = new ModelRenderer(this, 0, 0);
        circuit25.addBox(0F, 4F, 4F, 3, 1, 1);
        eye.addChild(circuit25);

        ModelRenderer circuit26 = new ModelRenderer(this, 0, 0);
        circuit26.addBox(2F, 3F, 4F, 1, 1, 1);
        eye.addChild(circuit26);

        ModelRenderer circuit27 = new ModelRenderer(this, 0, 0);
        circuit27.addBox(5F, 0F, -1F, 1, 1, 4);
        eye.addChild(circuit27);

        ModelRenderer circuit28 = new ModelRenderer(this, 0, 0);
        circuit28.addBox(5F, -1F, 2F, 1, 1, 3);
        eye.addChild(circuit28);

        ModelRenderer circuit29 = new ModelRenderer(this, 0, 0);
        circuit29.addBox(4F, -3F, 4F, 1, 3, 1);
        eye.addChild(circuit29);

        ModelRenderer circuit30 = new ModelRenderer(this, 0, 0);
        circuit30.addBox(3F, 2F, 4F, 3, 1, 1);
        eye.addChild(circuit30);

        ModelRenderer circuit31 = new ModelRenderer(this, 0, 0);
        circuit31.addBox(5F, 3F, 2F, 1, 1, 3);
        eye.addChild(circuit31);

        ModelRenderer circuit32 = new ModelRenderer(this, 0, 0);
        circuit32.addBox(1F, 5F, 2F, 1, 1, 1);
        eye.addChild(circuit32);

        ModelRenderer circuit33 = new ModelRenderer(this, 0, 0);
        circuit33.addBox(2F, 5F, -3F, 1, 1, 6);
        eye.addChild(circuit33);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        eye.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        this.eye.rotateAngleY = f3 / (180F / (float) Math.PI);
        this.eye.rotateAngleX = f4 / (180F / (float) Math.PI);
    }

}
