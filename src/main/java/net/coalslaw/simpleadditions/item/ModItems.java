package net.coalslaw.simpleadditions.item;

import net.coalslaw.simpleadditions.SimpleAdditions;
import net.coalslaw.simpleadditions.item.custom.HouseAppleItem;
import net.coalslaw.simpleadditions.item.custom.WoodWrapperItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.coalslaw.simpleadditions.item.custom.HouseAppleItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, SimpleAdditions.MOD_ID);

    public static final RegistryObject<Item> PAINITE = ITEMS.register("painite",
            () -> new Item(new Item.Properties()));

    /*
        All Custom Apples
     */
    public static final RegistryObject<Item> COPPER_APPLE = ITEMS.register("copper_apple",
            () -> new Item(new Item.Properties().food(ModFoodProperties.COPPERAPPLE)));

    public static final RegistryObject<Item> IRON_APPLE = ITEMS.register("iron_apple",
            () -> new Item(new Item.Properties().food(ModFoodProperties.IRONAPPLE)));

    public static final RegistryObject<Item> EMERALD_APPLE = ITEMS.register("emerald_apple",
            () -> new Item(new Item.Properties().food(ModFoodProperties.EMERALDAPPLE)));

    public static final RegistryObject<Item> WIND_APPLE = ITEMS.register("wind_apple",
            () -> new Item(new Item.Properties().food(ModFoodProperties.WINDAPPLE)));

    public static final RegistryObject<Item> MYTHICAL_APPLE = ITEMS.register("mythical_apple",
            () -> new Item(new Item.Properties().food(ModFoodProperties.MYTHICALAPPLE)));

    public static final RegistryObject<Item> WOOD_WRAPPER = ITEMS.register("wood_wrapper",
            () -> new WoodWrapperItem(new Item.Properties().durability(164)));

    public static final RegistryObject<Item> HOUSE_APPLE  = ITEMS.register("house_apple",
                 HouseAppleItem::new);

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
