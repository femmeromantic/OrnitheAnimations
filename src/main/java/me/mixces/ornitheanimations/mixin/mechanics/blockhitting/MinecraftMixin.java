package me.mixces.ornitheanimations.mixin.mechanics.blockhitting;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.mixces.ornitheanimations.config.Config;
import me.mixces.ornitheanimations.util.SwingUtilsKt;
import net.minecraft.client.ClientPlayerInteractionManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.living.player.LocalClientPlayerEntity;
import net.minecraft.client.entity.particle.ParticleManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

	@Shadow public LocalClientPlayerEntity player;

	@Shadow public HitResult crosshairTarget;

	@Shadow private int attackCooldown;

	@Shadow public ClientWorld world;

	@Shadow public ParticleManager particleManager;

	@Shadow public ClientPlayerInteractionManager interactionManager;

	@Inject(
		method = "tickBlockMining",
		at = @At("HEAD")
	)
	private void ornitheAnimations$fakeSwingDuringBlockhit(boolean holdingAttack, CallbackInfo ci) {
		if (!Config.INSTANCE.getBLOCK_HITTING().get()) {
			return;
		}
		if (attackCooldown <= 0 && player.isUsingItem() && holdingAttack &&
			crosshairTarget != null && crosshairTarget.type == HitResult.Type.BLOCK) {
			BlockPos blockPos = crosshairTarget.getPos();
			if (!world.isAir(blockPos)) {
				SwingUtilsKt.stopMiningBlock(interactionManager, world, player);
				particleManager.addBlockMiningParticles(blockPos, crosshairTarget.face);
				SwingUtilsKt.swingHand(player);
			}
		}
	}

	@ModifyExpressionValue(
		method = "doUse",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/ClientPlayerInteractionManager;isMiningBlock()Z"
		)
	)
	private boolean ornitheAnimations$disableIsHittingCheck(boolean original) {
		return !Config.INSTANCE.getBLOCK_HITTING().get() && original;
	}
}
