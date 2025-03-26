package me.mixces.ornitheanimations.mixin.mechanics.misspenalty;

import me.mixces.ornitheanimations.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.world.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
	@Shadow public HitResult crosshairTarget;

	@Shadow private int attackCooldown;

	@Inject(
		method = "doAttack",
		at = @At("HEAD")
	)
	private void ornitheAnimations$oldMissPenalty(CallbackInfo ci) {
		if (!Config.INSTANCE.getOLD_MISS_PENALTY().get()) {
			return;
		}
		if (crosshairTarget != null && crosshairTarget.type != HitResult.Type.BLOCK) {
			attackCooldown = 0;
		}
	}
}
