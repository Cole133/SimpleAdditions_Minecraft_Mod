package net.coalslaw.simpleadditions.item.custom;

import net.coalslaw.simpleadditions.SimpleAdditions;
import net.coalslaw.simpleadditions.item.ModFoodProperties;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;

public class HouseAppleItem extends Item {
    public HouseAppleItem() {
        super(new Properties()
                .food(ModFoodProperties.HOUSEAPPLE)
        );
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity eater) {
        if (!world.isClientSide() && eater instanceof ServerPlayer serverPlayer) {
            ServerLevel serverWorld = (ServerLevel) world;

            ResourceLocation id = ResourceLocation.fromNamespaceAndPath(SimpleAdditions.MOD_ID, "house");

            StructureTemplateManager mgr = serverWorld.getStructureManager();
            Optional<StructureTemplate> opt = mgr.get(id);

            if (opt.isPresent()) {
                StructureTemplate template = opt.get();

                BlockPos pastePos = serverPlayer.blockPosition().offset(2, 2, 0);

                StructurePlaceSettings settings = new StructurePlaceSettings()
                        .addProcessor(new BlockIgnoreProcessor(List.of(Blocks.STRUCTURE_VOID)));

                template.placeInWorld(serverWorld, pastePos, pastePos, settings, serverWorld.getRandom(), 2);
            }

        }

        return super.finishUsingItem(stack, world, eater);
    }
}
