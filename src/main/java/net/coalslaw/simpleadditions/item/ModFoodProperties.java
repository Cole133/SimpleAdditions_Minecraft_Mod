package net.coalslaw.simpleadditions.item;

import net.coalslaw.simpleadditions.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {

    public static final FoodProperties COPPERAPPLE = new FoodProperties.Builder().nutrition(10).saturationModifier(1.9F)
            .effect(new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 1), 1.0F)
            .alwaysEdible()
            .build();

    public static final FoodProperties IRONAPPLE = new FoodProperties.Builder().nutrition(10).saturationModifier(1.9F)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 0), 1.0F)
            .alwaysEdible()
            .build();

    public static final FoodProperties EMERALDAPPLE = new FoodProperties.Builder().nutrition(10).saturationModifier(1.9F)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 0), 1.0F)
            .effect(new MobEffectInstance(MobEffects.LUCK, 1200, 1), 1.0F)
            .alwaysEdible()
            .build();

    public static final FoodProperties WINDAPPLE = new FoodProperties.Builder().nutrition(10).saturationModifier(1.9F)
            .effect(new MobEffectInstance(ModEffects.FLIGHT_EFFECT.getHolder().get(), 1000, 0), 1.0F)
            .alwaysEdible()
            .build();

    public static final FoodProperties MYTHICALAPPLE = new FoodProperties.Builder().nutrition(10).saturationModifier(1.9F)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 600, 2), 1.0F)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 8000, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 8000, 0), 1.0F)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 5000, 4), 1.0F)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 8000, 2), 1.0F)
            .effect(new MobEffectInstance(ModEffects.FLIGHT_EFFECT.getHolder().get(), 200, 0), 1.0F)
            .alwaysEdible()
            .build();

}
