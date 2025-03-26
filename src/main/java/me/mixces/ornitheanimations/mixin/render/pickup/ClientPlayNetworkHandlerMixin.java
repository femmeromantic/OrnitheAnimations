package me.mixces.ornitheanimations.mixin.render.pickup;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.mixces.ornitheanimations.config.Config;
import net.minecraft.client.network.handler.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin {
	@ModifyExpressionValue(
		method = "handleEntityPickup",
		at = @At(
			value = "CONSTANT",
			args = "floatValue=0.5"
		)
	)
	private float ornitheAnimations$oldItemPickup(float original) {
		/* taken from 1.7 */
		return Config.INSTANCE.getOLD_ITEM_PICKUP().get() ? -0.5F : original;
	}
}
