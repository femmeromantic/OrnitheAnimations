package me.mixces.ornitheanimations.util

import me.mixces.ornitheanimations.mixin.accessors.ClientPlayerInteractionManagerAccessor
import me.mixces.ornitheanimations.mixin.accessors.LivingEntityAccessor
import net.minecraft.client.ClientPlayerInteractionManager
import net.minecraft.client.entity.living.player.LocalClientPlayerEntity
import net.minecraft.world.World

// fake swing :)
fun swingHand(player: LocalClientPlayerEntity) {
    val handMultiplier = (player as LivingEntityAccessor).invokeGetMiningSpeedMultiplier() / 2
    if (!player.handSwinging || player.handSwingTicks >= handMultiplier || player.handSwingTicks < 0) {
        player.handSwingTicks = -1
        player.handSwinging = true
    }
}

fun stopMiningBlock(interactionManager: ClientPlayerInteractionManager, world: World, player: LocalClientPlayerEntity) {
    val accessor = (interactionManager as ClientPlayerInteractionManagerAccessor)
    if (accessor.miningProgress > 0 && accessor.isMiningBlock) {
        accessor.isMiningBlock = false
        accessor.miningProgress = 0.0f
        world.updateBlockMiningProgress(player.networkId, accessor.target, -1)
    }
}
