package tragicneko.tragicmc.client.model;

import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import tragicneko.tragicmc.entity.boss.EntityTimeController;

public class ModelTimeController extends ModelBase {

    private ModelRenderer center; // cube that is 0x0x0 to just have a center for all of the other blocks to be child
                                  // parts of
    private ModelRenderer cube;
    private ModelRenderer cube2;
    private ModelRenderer cube3;
    private ModelRenderer cube4;
    private ModelRenderer cube5;
    private ModelRenderer cube6;
    private ModelRenderer cube7;
    private ModelRenderer cube8;
    private ModelRenderer cube9;
    private ModelRenderer cube10;
    private ModelRenderer cube11;
    private ModelRenderer cube12;
    private ModelRenderer cube13;
    private ModelRenderer cube14;
    private ModelRenderer cube15;
    private ModelRenderer cube16;
    private ModelRenderer cube17;

    private ModelRenderer[] cubeList = new ModelRenderer[18];

    public ModelTimeController() {
        textureWidth = 64;
        textureHeight = 32;

        center = new ModelRenderer(this, 0, 0);
        center.addBox(0F, 0F, 0F, 0, 0, 0);
        center.setRotationPoint(0F, -7F, 0F);

        cube = new ModelRenderer(this, 0, 0);
        cube.addBox(-3F, -3F, -3F, 6, 6, 6);
        center.addChild(cube);

        cube2 = new ModelRenderer(this, 0, 0);
        cube2.addBox(-14F, -12F, -15F, 8, 8, 8);
        center.addChild(cube2);

        cube3 = new ModelRenderer(this, 0, 0);
        cube3.addBox(-15F, -20F, 4F, 6, 6, 6);
        center.addChild(cube3);

        cube4 = new ModelRenderer(this, 0, 0);
        cube4.addBox(-12F, 8F, -3F, 6, 6, 6);
        center.addChild(cube4);

        cube5 = new ModelRenderer(this, 0, 0);
        cube5.addBox(-3F, -16F, -3F, 6, 6, 6);
        center.addChild(cube5);

        cube6 = new ModelRenderer(this, 0, 0);
        cube6.addBox(-2F, -12F, 12F, 4, 10, 4);
        center.addChild(cube6);

        cube7 = new ModelRenderer(this, 0, 0);
        cube7.addBox(-2F, -7F, -21F, 4, 16, 4);
        center.addChild(cube7);

        cube8 = new ModelRenderer(this, 0, 0);
        cube8.addBox(-2F, -5F, -2F, 4, 10, 4);
        center.addChild(cube8);

        cube9 = new ModelRenderer(this, 0, 0);
        cube9.addBox(-2F, -8F, -2F, 4, 16, 4);
        center.addChild(cube9);

        cube10 = new ModelRenderer(this, 0, 0);
        cube10.addBox(-3F, -3F, -1F, 6, 6, 6);
        center.addChild(cube10);

        cube11 = new ModelRenderer(this, 0, 0);
        cube11.addBox(3F, 2F, -7F, 6, 6, 6);
        center.addChild(cube11);

        cube12 = new ModelRenderer(this, 0, 0);
        cube12.addBox(-3F, -3F, -3F, 6, 6, 6);
        center.addChild(cube12);

        cube13 = new ModelRenderer(this, 0, 0);
        cube13.addBox(1F, 6F, -1F, 6, 6, 6);
        center.addChild(cube13);

        cube14 = new ModelRenderer(this, 0, 0);
        cube14.addBox(0F, -30F, 0F, 6, 6, 6);
        center.addChild(cube14);

        cube15 = new ModelRenderer(this, 0, 0);
        cube15.addBox(0F, 9F, -23F, 6, 6, 6);
        center.addChild(cube15);

        cube16 = new ModelRenderer(this, 0, 0);
        cube16.addBox(-16F, 0F, 0F, 6, 6, 6);
        center.addChild(cube16);

        cube17 = new ModelRenderer(this, 0, 0);
        cube17.addBox(12F, 0F, 0F, 6, 6, 6);
        center.addChild(cube17);

        this.cubeList = new ModelRenderer[] { cube, cube2, cube3, cube4, cube5, cube6, cube7, cube8, cube9, cube10,
            cube11, cube12, cube13, cube14, cube15, cube16, cube17 };
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        center.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        if (!(entity instanceof EntityTimeController)) return;

        EntityTimeController ctrl = (EntityTimeController) entity;

        if (ctrl.ticksExisted % 4 == 0 || ctrl.getFluxTicks() > 0 || ctrl.hurtTime > 0 || ctrl.getSpazTicks() > 0) {
            ModelRenderer cube = this.getRandomCube(entity.worldObj.rand);
            cube.offsetX = entity.worldObj.rand.nextFloat();
            cube.offsetY = entity.worldObj.rand.nextFloat();
            cube.offsetZ = entity.worldObj.rand.nextFloat();
            if (Math.abs(cube.offsetX) > 0.5F) cube.offsetX = 0.0F;
            if (Math.abs(cube.offsetY) > 0.5F) cube.offsetY = 0.0F;
            if (Math.abs(cube.offsetZ) > 0.5F) cube.offsetZ = 0.0F;
        }

        center.offsetY = 0.0F;
        center.offsetX = 0.0F;
        center.offsetZ = 0.0F;
        center.rotateAngleX = this.simplifyAngle(ctrl.ticksExisted, 120.0F) * 0.05F;
        center.rotateAngleY = this.simplifyAngle(ctrl.ticksExisted + 72, 120.0F) * 0.25F;
        center.rotateAngleZ = this.simplifyAngle(ctrl.ticksExisted + 35, 120.0F) * 0.05F;

        if (ctrl.getFluxTicks() > 0) {
            center.rotateAngleY = (float) (Math.sin(Math.PI * this.simplifyAngle(ctrl.ticksExisted, 10.0F)));
            center.rotateAngleX = (float) (Math.sin(Math.PI * this.simplifyAngle(ctrl.ticksExisted + 6, 10.0F)));
            center.rotateAngleZ = (float) (Math.sin(Math.PI * this.simplifyAngle(ctrl.ticksExisted + 2, 10.0F)));
        } else if (ctrl.getLeapTicks() > 0) {
            center.offsetY = this.simplifyAngle(ctrl.ticksExisted, 30.0F) * 0.725F;
            center.offsetX = this.simplifyAngle(ctrl.ticksExisted, 40.0F) * 0.725F;
            center.offsetZ = this.simplifyAngle(ctrl.ticksExisted, 20.0F) * 0.725F;
        } else if (ctrl.getPurgeTicks() > 0) {
            center.rotateAngleY = (float) (this.simplifyAngle(ctrl.ticksExisted, 20.0F) * Math.PI);
        } else if (ctrl.getSpazTicks() > 0) {
            center.rotateAngleZ = (float) (Math.sin(Math.PI * this.simplifyAngle(ctrl.ticksExisted + 2, 10.0F)));
            center.offsetX = ctrl.worldObj.rand.nextFloat() - ctrl.worldObj.rand.nextFloat();
        }
    }

    private float simplifyAngle(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }

    private ModelRenderer getRandomCube(Random rand) {
        return this.cubeList[rand.nextInt(this.cubeList.length)];
    }

}
