package com.wdcftgg.sleepanytime.mixins;

import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.WorldProviderHell;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : wdcftgg
 * @create 2023/8/23 0:20
 */

@Mixin(value = WorldProviderHell.class)
public class MixinWorldProviderHell {

    /**
     * @author
     * @reason
     */
    @Overwrite
    public boolean isSurfaceWorld()
    {
        return true;
    }
}
