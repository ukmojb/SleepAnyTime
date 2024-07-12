package com.wdcftgg.sleepanytime.mixins;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : wdcftgg
 * @create 2023/8/22 19:36
 */

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.player.SleepingTimeCheckEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = EntityPlayer.class)
public class MixinEntityPlayer {

    @Shadow
    protected boolean sleeping;

    @Shadow
    public BlockPos bedLocation;

    @Shadow
    private int sleepTimer;




    /**
     * @author
     * @reason
     */
    @Inject(method = "trySleep", at = @At(value = "RETURN", target = "Lnet/minecraft/entity/player/EntityPlayer;trySleep(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/entity/player/EntityPlayer$SleepResult;"), cancellable = true)
    public void trySleep(BlockPos bedLocation, CallbackInfoReturnable<EntityPlayer.SleepResult> cir)
    {
        if (cir.getReturnValue() == EntityPlayer.SleepResult.NOT_POSSIBLE_HERE && sleepTimer != 100) {
            cir.setReturnValue(EntityPlayer.SleepResult.OK);

            this.sleeping = true;
            this.sleepTimer = 0;
            this.bedLocation = bedLocation;
        }
    }



}

