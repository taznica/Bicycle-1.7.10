package com.example.examplemod;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBicycle extends EntityAnimal {

    public EntityBicycle(World world) {
        super(world);
    }

    protected void entityInit() {
        super.entityInit();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.0D);
    }

    public boolean canBePushed() {
        return this.riddenByEntity == null;
    }

    protected String getDeathSound() {
        return "game.neutral.die";
    }

    protected Item getDropItem() {
        return Items.apple;
    }

    protected String getHurtSound() {
        return "";
    }

    protected String getLivingSound() {
        return "";
    }

    public boolean interact(EntityPlayer player) {
        if(player.getHeldItem().getItem() != null) {
            return super.interact(player);
        }
        this.func_110237_h(player);
        return true;
    }

    private void func_110237_h(EntityPlayer player) {
        player.rotationYaw = this.rotationYaw;
        player.rotationPitch = this.rotationPitch;

        if (!this.worldObj.isRemote)
        {
            player.mountEntity(this);
        }
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    public void onUpdate() {
        super.onUpdate();

    }

    public void moveEntityWithHeading(float strafe, float forward) {
        if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityLivingBase) {
            this.prevRotationYaw = this.rotationYaw = this.riddenByEntity.rotationYaw;
            this.rotationPitch = this.riddenByEntity.rotationPitch * 0.5F;
            this.setRotation(this.rotationYaw, this.rotationPitch);
            this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
            strafe = ((EntityLivingBase)this.riddenByEntity).moveStrafing * 0.5F;
            forward = ((EntityLivingBase)this.riddenByEntity).moveForward;

            if (forward <= 0.0F) {
                forward *= 0.25F;
            }

            if (this.onGround) {
                strafe = 0.0F;
                forward = 0.0F;
            }

            if (this.onGround) {
//                this.motionY = this.getHorseJumpStrength() * (double)this.jumpPower;
                this.motionY = 2.0D;

                this.isAirBorne = true;

                if (forward > 0.0F)
                {
                    float f2 = MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F);
                    float f3 = MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F);
//                    this.motionX += (double)(-0.4F * f2 * this.jumpPower);
//                    this.motionZ += (double)(0.4F * f3 * this.jumpPower);

                    this.motionX += (double)(-0.4F * f2 * 2.0D);
                    this.motionZ += (double)(0.4F * f3 * 2.0D);
                    this.playSound("mob.horse.jump", 0.4F, 1.0F);
                }

                net.minecraftforge.common.ForgeHooks.onLivingJump(this);
            }

            this.stepHeight = 1.0F;
            this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

            if (!this.worldObj.isRemote)
            {
                this.setAIMoveSpeed((float)this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
                super.moveEntityWithHeading(strafe, forward);
            }



            this.prevLimbSwingAmount = this.limbSwingAmount;
            double d1 = this.posX - this.prevPosX;
            double d0 = this.posZ - this.prevPosZ;
            float f4 = MathHelper.sqrt_double(d1 * d1 + d0 * d0) * 4.0F;

            if (f4 > 1.0F)
            {
                f4 = 1.0F;
            }

            this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
            this.limbSwing += this.limbSwingAmount;
        }
        else
        {
            this.stepHeight = 0.5F;
            this.jumpMovementFactor = 0.02F;
            super.moveEntityWithHeading(strafe, forward);
        }
    }

    public EntityAgeable createChild(EntityAgeable entityAgeable) {
        return new EntityBicycle(entityAgeable.worldObj);
    }


}
