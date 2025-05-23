package net.coalslaw.simpleadditions.effect;

import net.coalslaw.simpleadditions.SimpleAdditions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, SimpleAdditions.MOD_ID);

    public static final RegistryObject<MobEffect> FLIGHT_EFFECT = MOB_EFFECTS.register("flight",
            () -> new FlightEffect(MobEffectCategory.NEUTRAL, 0x36ebab));

    public static final RegistryObject<MobEffect> INSTABREAK_EFFECT = MOB_EFFECTS.register("instabreak",
            () -> new InstaBreakEffect(MobEffectCategory.NEUTRAL, 0x36ebab));

    public static final RegistryObject<MobEffect> SLIMY_EFFECT = MOB_EFFECTS.register("slimy",
            () -> new SlimyEffect(MobEffectCategory.NEUTRAL, 0x00FF00)); // Green color for slimy


    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }

}
