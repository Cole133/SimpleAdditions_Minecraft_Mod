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
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        // Horizontal bounce logic (can remain similar)
        if (pLivingEntity.horizontalCollision) {
            Vec3 motion = pLivingEntity.getDeltaMovement();
            double bounceFactor = 0.7; 
            // Dampen horizontal velocity on collision
            pLivingEntity.setDeltaMovement(motion.x * -bounceFactor, motion.y, motion.z * -bounceFactor);
        }

        // Vertical bounce logic (scaled with fall distance)
        if (pLivingEntity.verticalCollision && pLivingEntity.onGround()) {
            if (pLivingEntity.fallDistance > 0.1F) { // Only bounce if fallen a bit
                Vec3 motion = pLivingEntity.getDeltaMovement();
                // Calculate bounce strength based on fall distance
                // Vanilla slime blocks negate fall damage and bounce proportionally.
                // Max bounce height for slime blocks is achieved at around 3 blocks fall distance.
                // Let's try to replicate a similar feel, but cap it to prevent extreme bounces.
                double bounceStrength = Math.min(pLivingEntity.fallDistance * 0.8D, 1.5D); // Cap bounce at 1.5 blocks equiv.
                
                pLivingEntity.setDeltaMovement(motion.x, bounceStrength, motion.z);
                
                // Reset fall distance after bounce
                pLivingEntity.fallDistance = 0; 
            }
        }

        return super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true; // Apply the effect every tick
    }
}
