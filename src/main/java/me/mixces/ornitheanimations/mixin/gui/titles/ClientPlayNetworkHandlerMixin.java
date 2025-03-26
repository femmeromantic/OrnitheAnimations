package me.mixces.ornitheanimations.mixin.gui.titles;

import me.mixces.ornitheanimations.config.Config;
import net.minecraft.client.network.handler.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.TitlesS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin {
	@Inject(
		method = "handleTitles",
		at = @At("HEAD"),
		cancellable = true
	)
	private void ornitheAnimations$disableTitlesPacket(TitlesS2CPacket packet, CallbackInfo ci) {
		if (Config.INSTANCE.getREMOVE_TITLES().get()) {
			/* 1.7 doesn't have titles */
			ci.cancel();
		}
	}
}
