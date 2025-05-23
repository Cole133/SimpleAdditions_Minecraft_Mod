package net.coalslaw.simpleadditions.datagen;

import net.coalslaw.simpleadditions.SimpleAdditions;
import net.coalslaw.simpleadditions.effect.ModEffects;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLangProvider extends LanguageProvider {
    public ModLangProvider(PackOutput output, String locale) {
        super(output, SimpleAdditions.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        addEffect(ModEffects.SLIMY_EFFECT, "Slimy");
        // You can add other translations here if needed
    }
}
