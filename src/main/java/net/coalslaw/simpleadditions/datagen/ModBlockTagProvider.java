package net.coalslaw.simpleadditions.datagen;

import net.coalslaw.simpleadditions.SimpleAdditions;
import net.coalslaw.simpleadditions.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, SimpleAdditions.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.PAINITE_BLOCK.get())
                .add(ModBlocks.PAINITE_ORE.get())
                .add(ModBlocks.STONE_TILE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.PAINITE_BLOCK.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.PAINITE_ORE.get());
    }
}
