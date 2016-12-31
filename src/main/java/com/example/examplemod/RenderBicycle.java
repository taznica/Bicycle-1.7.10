package com.example.examplemod;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderBicycle extends RenderLiving {

    private static final ResourceLocation bicycleTexture = new ResourceLocation("examplemod:textures/entity/bicycle.png");

    public RenderBicycle() {
        super(new ModelBicycle(), 1.0F);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return bicycleTexture;
    }
}
