package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelRenderer;

public class ModelKragul extends ModelGragul {

    public ModelKragul() {
        super();
        ModelRenderer rightHorn = new ModelRenderer(this, 16, 4);
        rightHorn.addBox(-2F, 2.5F, 0F, 1, 2, 1);
        this.body.addChild(rightHorn);

        ModelRenderer leftHorn = new ModelRenderer(this, 16, 4);
        leftHorn.addBox(1F, 2.5F, 0F, 1, 2, 1);
        this.body.addChild(leftHorn);
    }

}
