package net.coalslaw.simpleadditions.item;

import net.coalslaw.simpleadditions.SimpleAdditions;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, SimpleAdditions.MOD_ID);

    public static final RegistryObject<Item> PAINITE = ITEMS.register("painite",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MYTHICALAPPLE = ITEMS.register("mythical_apple",
            () -> new Item(new Item.Properties().food(ModFoodProperties.MYTHICALAPPLE)));

    public static final RegistryObject<Item> WINDAPPLE = ITEMS.register("wind_apple",
            () -> new Item(new Item.Properties().food(ModFoodProperties.WINDAPPLE)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
