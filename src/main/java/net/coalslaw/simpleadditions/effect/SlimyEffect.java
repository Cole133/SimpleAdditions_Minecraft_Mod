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
        Vec3 currentMotion = pLivingEntity.getDeltaMovement();
        double newMotionX = currentMotion.x();
        double newMotionY = currentMotion.y();
        double newMotionZ = currentMotion.z();

        // 1. Horizontal bounce logic
        if (pLivingEntity.horizontalCollision) {
            double bounceFactor = 0.7;
            // Check which axis the collision is on to correctly reverse motion
            // This is a simplified check; more complex logic might be needed for perfect corner behavior
            if (Math.abs(pLivingEntity.getDeltaMovement().x()) > 0.05 && pLivingEntity.getX() - pLivingEntity.xo > 0 != (pLivingEntity.getDeltaMovement().x() > 0)) { // Collision on X axis
                newMotionX = currentMotion.x() * -bounceFactor;
            }
            if (Math.abs(pLivingEntity.getDeltaMovement().z()) > 0.05 && pLivingEntity.getZ() - pLivingEntity.zo > 0 != (pLivingEntity.getDeltaMovement().z() > 0)) { // Collision on Z axis
                newMotionZ = currentMotion.z() * -bounceFactor;
            }
        }

        // 2. Ground interaction (constant bop + scaled fall bounce)
        if (pLivingEntity.onGround()) {
            // Apply the small constant bop first
            // This ensures a bop even if fallDistance is 0
            double bopStrength = 0.3D; // Adjust this for desired 'bop' height
            newMotionY = bopStrength;

            // If fallen, add scaled bounce on top of the bop
            if (pLivingEntity.fallDistance > 0.1F) {
                // Additive bounce strength, capped.
                // The base bop already provides some upward motion.
                double fallBounceStrength = Math.min(pLivingEntity.fallDistance * 0.7D, 1.2D); // Cap additive bounce
                newMotionY += fallBounceStrength; // Add to the existing bop
                pLivingEntity.fallDistance = 0; // Reset fall distance
            }
        }

        pLivingEntity.setDeltaMovement(newMotionX, newMotionY, newMotionZ);

        return super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true; // Apply the effect every tick
    }
}