package net.coalslaw.simpleadditions.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class SlimyEffect extends MobEffect {

    public SlimyEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity.horizontalCollision) {
            Vec3 motion = pLivingEntity.getDeltaMovement();
            double bounceFactor = 0.7; // Adjust this value for more or less bounce
            pLivingEntity.setDeltaMovement(motion.x * -bounceFactor, motion.y, motion.z * -bounceFactor);
             // Add a small upward bounce
            if (pLivingEntity.onGround()) {
                 pLivingEntity.setDeltaMovement(pLivingEntity.getDeltaMovement().add(0, 0.42, 0)); // 0.42 is default jump strength
            }

        }

        if (pLivingEntity.verticalCollision && pLivingEntity.onGround()) {
            Vec3 motion = pLivingEntity.getDeltaMovement();
            double bounceFactor = 0.7; // Adjust this value for more or less bounce
            pLivingEntity.setDeltaMovement(motion.x, motion.y * -bounceFactor, motion.z);
            // Make the entity jump if it hits the ground
             pLivingEntity.setDeltaMovement(pLivingEntity.getDeltaMovement().add(0, 0.42, 0)); // 0.42 is default jump strength
        }

        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true; // Apply the effect every tick
    }
}
