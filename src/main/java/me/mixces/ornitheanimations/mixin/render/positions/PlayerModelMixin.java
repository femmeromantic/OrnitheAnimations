package me.mixces.ornitheanimations.mixin.render.positions;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.mixces.ornitheanimations.config.Config;
import net.minecraft.client.render.model.entity.PlayerModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerModel.class)
public abstract class PlayerModelMixin {
	@ModifyExpressionValue(
		method = "translateRightArm",
		at = @At(
			value = "CONSTANT",
			args = "floatValue=1.0F"
		)
	)
	private float ornitheAnimations$alexArmFix(float original) {
		return Config.INSTANCE.getOLD_ITEM_POSITIONS().get() ? original / 2.0F : original;
	}
}
