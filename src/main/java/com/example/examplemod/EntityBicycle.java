package com.example.examplemod;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

public class EntityBicycle extends EntityAnimal {

    public EntityBicycle(World world) {
        super(world);
    }

    public EntityAgeable createChild(EntityAgeable entityAgeable) {
        return new EntityBicycle(entityAgeable.worldObj);
    }


}
