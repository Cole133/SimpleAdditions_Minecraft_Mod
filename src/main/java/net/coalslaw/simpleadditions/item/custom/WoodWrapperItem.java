package net.coalslaw.simpleadditions.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.Map;

public class WoodWrapperItem extends Item {
    private static final Map<Block, Block> WOOD_MAP = Map.of(
            Blocks.STRIPPED_DARK_OAK_LOG, Blocks.DARK_OAK_LOG,
            Blocks.STRIPPED_OAK_LOG, Blocks.OAK_LOG,
            Blocks.STRIPPED_BIRCH_LOG, Blocks.BIRCH_LOG,
            Blocks.STRIPPED_SPRUCE_LOG, Blocks.SPRUCE_LOG,
            Blocks.STRIPPED_ACACIA_LOG, Blocks.ACACIA_LOG
    );
    public WoodWrapperItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        Block clickedBlock = level.getBlockState(pContext.getClickedPos()).getBlock();

        if(WOOD_MAP.containsKey(clickedBlock)){
            if(!level.isClientSide()){
                level.setBlockAndUpdate(pContext.getClickedPos(), WOOD_MAP.get(clickedBlock).defaultBlockState());

                pContext.getItemInHand().hurtAndBreak(1,((ServerLevel) level), ((ServerPlayer) pContext.getPlayer()),
                        item -> pContext.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.playSound(null, pContext.getClickedPos(), SoundEvents.AXE_STRIP, SoundSource.BLOCKS);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if(Screen.hasShiftDown()){
            pTooltipComponents.add(Component.translatable("tooltip.simpleadditions.wood_wrapper.shift_down"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.simpleadditions.wood_wrapper"));
        }

        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
