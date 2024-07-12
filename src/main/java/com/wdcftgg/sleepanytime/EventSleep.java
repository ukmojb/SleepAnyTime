package com.wdcftgg.sleepanytime;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.SleepingLocationCheckEvent;
import net.minecraftforge.event.entity.player.SleepingTimeCheckEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nullable;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : wdcftgg
 * @create 2023/8/22 20:42
 */
@Mod.EventBusSubscriber
public class EventSleep {

    private static long sleeptime = 0;

    @SubscribeEvent
    public void onLivingUpdateEvent(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            if (!player.world.isRemote && !player.isSneaking()) {
                if (player.getSleepTimer() >= 100){
                    player.bedLocation = null;
                    player.world.setWorldTime(sleeptime + 10000);
                    player.wakeUpPlayer(true, true, player.world.provider.isSurfaceWorld());
                }
                if(player.bedLocation != null && player.getSleepTimer() <= 0){
                    player.trySleep(player.bedLocation);
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.RightClickBlock event) {
        EntityPlayer player = event.getEntityPlayer();
        if (!player.world.isRemote){
            if (event.getHand() != EnumHand.MAIN_HAND)
                return;

            IBlockState worldBlock = event.getWorld().getBlockState(event.getPos());
            if (worldBlock.getBlock().equals(Blocks.BED)){
                sleeptime = player.world.getWorldTime();
                player.bedLocation = event.getPos();
            }else{
                player.bedLocation = null;
            }
        }
    }

    @SubscribeEvent
    public static void onSleepingTimeCheckEvent(SleepingTimeCheckEvent event) {
        event.setResult(Event.Result.ALLOW);
    }

    @SubscribeEvent
    public static void onSleepingLocationCheckEvent(SleepingLocationCheckEvent event) {
        event.setResult(Event.Result.ALLOW);
    }

    @Nullable
    private static EntityPlayer getPlayerInBed(World worldIn, BlockPos pos)
    {
        for (EntityPlayer entityplayer : worldIn.playerEntities)
        {
            if (entityplayer.isPlayerSleeping() && entityplayer.bedLocation.equals(pos))
            {
                return entityplayer;
            }
        }

        return null;
    }
}
