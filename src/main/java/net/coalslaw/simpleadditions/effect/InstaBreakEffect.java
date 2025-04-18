package net.coalslaw.simpleadditions.effect;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class InstaBreakEffect extends MobEffect {
    public static LivingEntity currentUser = null;
    public InstaBreakEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (!(pLivingEntity instanceof ServerPlayer player)) return true;
        currentUser = pLivingEntity;

        if (!player.getAbilities().instabuild) {
            player.getAbilities().instabuild = true;
        }

        player.onUpdateAbilities();

        return super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }
    @Override
    public void removeAttributeModifiers(AttributeMap pAttributeMap) {

        if (!(currentUser instanceof ServerPlayer player)) return;

        if (player.getAbilities().instabuild) {
            player.getAbilities().instabuild = false;
        }

        player.onUpdateAbilities();

        super.removeAttributeModifiers(pAttributeMap);
    }
}
