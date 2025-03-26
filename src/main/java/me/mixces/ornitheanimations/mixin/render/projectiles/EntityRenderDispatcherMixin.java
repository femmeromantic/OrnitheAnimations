package me.mixces.ornitheanimations.mixin.render.projectiles;

import me.mixces.ornitheanimations.config.Config;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.render.TextRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {
	@Shadow
	public float cameraPitch;

	@Inject(
		method = "prepare",
		at = @At(
			value = "FIELD",
			opcode = Opcodes.PUTFIELD,
			target = "Lnet/minecraft/client/render/entity/EntityRenderDispatcher;cameraYaw:F",
			ordinal = 0,
			shift = At.Shift.AFTER
		),
		slice = @Slice(
			from = @At(
				value = "FIELD",
				opcode = Opcodes.GETFIELD,
				target = "Lnet/minecraft/client/options/GameOptions;perspective:I"
			)
		)
	)
	private void ornitheAnimations$fixCameraRotation(World world, TextRenderer textRenderer, Entity camera, Entity targetEntity, GameOptions options, float tickDelta, CallbackInfo ci) {
		if (Config.INSTANCE.getMIRRORED_PROJECTILES().get()) {
			cameraPitch *= -1;
		}
	}
}
