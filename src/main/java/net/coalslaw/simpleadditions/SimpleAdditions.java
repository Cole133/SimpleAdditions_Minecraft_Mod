package net.coalslaw.simpleadditions;

import com.mojang.logging.LogUtils;
import net.coalslaw.simpleadditions.block.ModBlocks;
import net.coalslaw.simpleadditions.effect.ModEffects;
import net.coalslaw.simpleadditions.item.ModCreativeModeTabs;
import net.coalslaw.simpleadditions.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// Imports for texture stitching
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(SimpleAdditions.MOD_ID)
public class SimpleAdditions
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "simpleadditions";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public SimpleAdditions(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModEffects.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.PAINITE);
        }

        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.accept(ModBlocks.PAINITE_BLOCK);
            event.accept(ModBlocks.PAINITE_ORE);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }

        @SubscribeEvent
        public static void onTextureStitchPre(TextureStitchEvent.Pre event) {
            if (!event.getAtlas().location().equals(new ResourceLocation("minecraft:textures/atlas/mob_effects.png"))) {
                return; // Only stitch to the mob effects atlas
            }
            // Register custom effect icons
            event.addSprite(new ResourceLocation(SimpleAdditions.MOD_ID, "mob_effect/slimy"));
            event.addSprite(new ResourceLocation(SimpleAdditions.MOD_ID, "mob_effect/flight"));
            event.addSprite(new ResourceLocation(SimpleAdditions.MOD_ID, "mob_effect/instabreak"));
        }
    }
}
