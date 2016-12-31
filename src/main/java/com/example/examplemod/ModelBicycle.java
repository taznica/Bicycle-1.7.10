package com.example.examplemod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelBicycle extends ModelBase {

    public ModelRenderer box;

    public ModelBicycle() {
        box = new ModelRenderer(this, 0, 0).setTextureSize(64, 64);

        box.setTextureOffset(0, 0);
        box.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
    }

    public void render(Entity entity,float time, float speed, float miscAngle, float yaw, float pitch, float scale) {
        box.render(scale);
    }

}
