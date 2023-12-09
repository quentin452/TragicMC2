package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLockbot extends ModelBase {

    private ModelRenderer body;

    public ModelLockbot() {
        textureWidth = 64;
        textureHeight = 32;

        body = new ModelRenderer(this, 6, 0);
        body.addBox(-4F, -4F, -4F, 8, 8, 8);
        body.setRotationPoint(0F, 20F, 0F);

        ModelRenderer circuit = new ModelRenderer(this, 0, 0);
        circuit.addBox(-5F, -6F, 0F, 1, 10, 1);
        body.addChild(circuit);

        ModelRenderer circuit2 = new ModelRenderer(this, 0, 0);
        circuit2.addBox(0F, -7F, 4F, 1, 9, 1);
        body.addChild(circuit2);

        ModelRenderer circuit3 = new ModelRenderer(this, 0, 0);
        circuit3.addBox(-2F, -5F, -5F, 1, 5, 1);
        body.addChild(circuit3);

        ModelRenderer circuit4 = new ModelRenderer(this, 0, 0);
        circuit4.addBox(-1F, -1F, -5F, 1, 5, 1);
        body.addChild(circuit4);

        ModelRenderer circuit5 = new ModelRenderer(this, 0, 0);
        circuit5.addBox(4F, -2F, 0F, 1, 6, 1);
        body.addChild(circuit5);

        ModelRenderer circuit6 = new ModelRenderer(this, 0, 0);
        circuit6.addBox(4F, -5F, -1F, 1, 4, 1);
        body.addChild(circuit6);

        ModelRenderer circuit7 = new ModelRenderer(this, 0, 0);
        circuit7.addBox(-1F, 1F, 4F, 1, 3, 1);
        body.addChild(circuit7);

        ModelRenderer circuit8 = new ModelRenderer(this, 0, 0);
        circuit8.addBox(0F, -6F, -5F, 1, 6, 1);
        body.addChild(circuit8);

        ModelRenderer circuit9 = new ModelRenderer(this, 0, 0);
        circuit9.addBox(-2F, -2F, 4F, 1, 4, 1);
        body.addChild(circuit9);

        ModelRenderer circuit10 = new ModelRenderer(this, 0, 0);
        circuit10.addBox(-5F, -5F, -3F, 1, 5, 1);
        body.addChild(circuit10);

        ModelRenderer circuit11 = new ModelRenderer(this, 0, 0);
        circuit11.addBox(-5F, -1F, -4F, 1, 1, 1);
        body.addChild(circuit11);

        ModelRenderer circuit12 = new ModelRenderer(this, 0, 0);
        circuit12.addBox(-5F, -1F, -5F, 1, 5, 1);
        body.addChild(circuit12);

        ModelRenderer circuit13 = new ModelRenderer(this, 0, 0);
        circuit13.addBox(-5F, -2F, 1F, 1, 1, 1);
        body.addChild(circuit13);

        ModelRenderer circuit14 = new ModelRenderer(this, 0, 0);
        circuit14.addBox(-5F, -2F, 2F, 1, 5, 1);
        body.addChild(circuit14);

        ModelRenderer circuit15 = new ModelRenderer(this, 0, 0);
        circuit15.addBox(-5F, 2F, 3F, 1, 2, 1);
        body.addChild(circuit15);

        ModelRenderer circuit16 = new ModelRenderer(this, 0, 0);
        circuit16.addBox(-3F, -5F, 4F, 1, 4, 1);
        body.addChild(circuit16);

        ModelRenderer circuit17 = new ModelRenderer(this, 0, 0);
        circuit17.addBox(3F, -3F, -5F, 1, 4, 1);
        body.addChild(circuit17);

        ModelRenderer circuit18 = new ModelRenderer(this, 0, 0);
        circuit18.addBox(4F, -3F, -5F, 1, 1, 1);
        body.addChild(circuit18);

        ModelRenderer circuit19 = new ModelRenderer(this, 0, 0);
        circuit19.addBox(4F, -8F, -4F, 1, 6, 1);
        body.addChild(circuit19);

        ModelRenderer circuit20 = new ModelRenderer(this, 0, 0);
        circuit20.addBox(4F, -6F, 2F, 1, 4, 1);
        body.addChild(circuit20);

        ModelRenderer circuit21 = new ModelRenderer(this, 0, 0);
        circuit21.addBox(4F, 0F, -5F, 1, 4, 1);
        body.addChild(circuit21);

        ModelRenderer circuit22 = new ModelRenderer(this, 0, 0);
        circuit22.addBox(4F, -3F, 3F, 1, 4, 1);
        body.addChild(circuit22);

        ModelRenderer circuit23 = new ModelRenderer(this, 0, 0);
        circuit23.addBox(4F, 0F, 2F, 1, 4, 1);
        body.addChild(circuit23);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        body.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}
