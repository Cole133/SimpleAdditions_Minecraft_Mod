package net.coalslaw.simpleadditions.datagen;

import net.coalslaw.simpleadditions.SimpleAdditions;
import net.coalslaw.simpleadditions.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SimpleAdditions.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.COPPER_APPLE.get());
        basicItem(ModItems.IRON_APPLE.get());
        basicItem(ModItems.EMERALD_APPLE.get());
        basicItem(ModItems.WIND_APPLE.get());
        basicItem(ModItems.MYTHICAL_APPLE.get());
        basicItem(ModItems.HOUSE_APPLE.get());

        basicItem(ModItems.PAINITE.get());

        basicItem(ModItems.WOOD_WRAPPER.get());
    }
}
