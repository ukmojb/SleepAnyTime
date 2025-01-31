package com.wdcftgg.sleepanytime.mixins;


import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Field;

@Mixin(value = EntityFishHook.class)
public class MixinEntityFishHook extends Entity {

    @Shadow
    private static final DataParameter<Integer> DATA_HOOKED_ENTITY = EntityDataManager.<Integer>createKey(EntityFishHook.class, DataSerializers.VARINT);

    @Shadow
    private boolean inGround;
    @Shadow
    private int ticksInGround;
    @Shadow
    private EntityPlayer angler;
    @Shadow
    private int ticksInAir;
    @Shadow
    private int ticksCatchable;
    @Shadow
    private int ticksCaughtDelay;
    @Shadow
    private int ticksCatchableDelay;
    @Shadow
    private float fishApproachAngle;
    @Shadow
    public Entity caughtEntity;
    @Shadow
    private int luck;
    @Shadow
    private int lureSpeed;


    public MixinEntityFishHook(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void entityInit() {

    }

    /**
     * @author
     * @reason
     */
    @Inject(method = "onUpdate", at = @At(value = "HEAD", target = "Lnet/minecraft/entity/projectile/EntityFishHook;onUpdate()V"), cancellable = true)
    public void onUpdate()
    {
        super.onUpdate();


        Field nameField = null;
        try {
            Class<?> clazz = Class.forName("net.minecraft.entity.projectile.EntityFishHook");
            nameField = clazz.getDeclaredField("currentState");
            nameField.setAccessible(true);

            System.out.println(nameField.getName());

        } catch (NoSuchFieldException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }



    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {

    }
}
