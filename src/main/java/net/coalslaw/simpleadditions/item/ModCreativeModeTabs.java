package net.coalslaw.simpleadditions.item;

import net.coalslaw.simpleadditions.SimpleAdditions;
import net.coalslaw.simpleadditions.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SimpleAdditions.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SIMPLE_ADDITIONS_TAB = CREATIVE_MODE_TABS.register("simpleadditions_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PAINITE.get()))
                    .title(Component.translatable("creativetab.simpleadditions.simpleadditions_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.PAINITE.get());
                        output.accept(ModItems.WINDAPPLE.get());
                        output.accept(ModItems.MYTHICALAPPLE.get());

                        output.accept(ModItems.WOOD_WRAPPER.get());

                        output.accept(ModBlocks.PAINITE_ORE.get());
                        output.accept(ModBlocks.PAINITE_BLOCK.get());
                        output.accept(ModBlocks.STONE_TILE.get());
                    }).build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
