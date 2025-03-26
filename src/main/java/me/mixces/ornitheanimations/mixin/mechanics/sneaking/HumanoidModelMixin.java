package me.mixces.ornitheanimations.mixin.mechanics.sneaking;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import me.mixces.ornitheanimations.config.Config;
import net.minecraft.client.render.model.entity.HumanoidModel;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(HumanoidModel.class)
public abstract class HumanoidModelMixin {
	@WrapOperation(
		method = "render",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/entity/Entity;isSneaking()Z"
		)
	)
	private boolean ornitheAnimations$disableSneakTranslation(Entity instance, Operation<Boolean> original) {
		return !Config.INSTANCE.getSMOOTH_SNEAKING().get() && original.call(instance);
	}
}
