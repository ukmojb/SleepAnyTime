package com.wdcftgg.sleepanytime;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : wdcftgg
 * @create 2023/8/22 19:11
 */
@Mod(modid = CustomFishing.MODID, useMetadata = true, acceptableRemoteVersions = "*")
public class CustomFishing {
    private static Logger logger;
    public static final String MODID = "customfishing";



    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    }



}
