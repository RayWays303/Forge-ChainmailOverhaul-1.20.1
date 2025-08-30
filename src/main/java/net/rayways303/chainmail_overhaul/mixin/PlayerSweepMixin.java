package net.rayways303.chainmail_overhaul.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.rayways303.chainmail_overhaul.item.chainmail.ChainmailUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Player.class)
public abstract class PlayerSweepMixin {
    @Redirect(
            method = "attack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"
            )
    )
    private boolean chainmailReduceSweep(LivingEntity victim, DamageSource source, float amount, Entity mainTarget) {
        double reduction = ChainmailUtils.getChainmailReduction(victim);
        if (victim == mainTarget) {
            return victim.hurt(source, amount); // leave direct hit alone
        }

        if (reduction >= ChainmailUtils.SWEEP_NEGATE_THRESHOLD) {
            // Negate sweep completely
            return false;
        }

        if (reduction > 0) {
            // Partially reduce
            amount = (float)(amount * (1.0 - reduction));
        }

        return victim.hurt(source, amount);
    }
}

