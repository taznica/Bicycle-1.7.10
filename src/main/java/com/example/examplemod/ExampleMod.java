package com.example.examplemod;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.renderer.entity.RenderChicken;
import net.minecraft.client.renderer.entity.RenderHorse;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
public class ExampleMod
{
    public static final String MODID = "examplemod";
    public static final String VERSION = "1.0";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        registerBicycle();
    }

    private void registerBicycle() {
        EntityRegistry.registerGlobalEntityID(EntityBicycle.class, "bicycle", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 0xFFFFFF);
        RenderingRegistry.registerEntityRenderingHandler(EntityBicycle.class, new RenderBicycle());
    }
}
