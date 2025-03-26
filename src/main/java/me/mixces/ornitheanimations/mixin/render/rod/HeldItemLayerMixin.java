package me.mixces.ornitheanimations.mixin.render.rod;

import me.mixces.ornitheanimations.config.Config;
import net.minecraft.client.render.entity.layer.HeldItemLayer;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(HeldItemLayer.class)
public abstract class HeldItemLayerMixin {

	@ModifyArg(
		method = "render",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/item/ItemStack;<init>(Lnet/minecraft/item/Item;I)V"
		),
		index = 0
	)
	private Item ornitheAnimations$changeToStick(Item item) {
		return Config.INSTANCE.getREPLACE_CAST_ROD().get() ? Items.STICK : item;
	}
}
