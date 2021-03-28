package me.londiuh.playerlog.mixin;

import me.londiuh.playerlog.PlayerLog;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin {
    @Inject(method = "interactItem", at = @At("HEAD"))
    private void interactItem(ServerPlayerEntity player, World world, ItemStack stack, Hand hand, CallbackInfoReturnable<ActionResult> ci) {
        PlayerLog.logger.info(
            "[PlayerLog] {} interacted with item {} at {}",
            player.getEntityName(),
            stack.getItem(),
            player.getPos()
            
        );
    }
    @Inject(method = "interactBlock", at = @At("HEAD"))
    private void interactBlock(ServerPlayerEntity player, World world, ItemStack stack, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> ci) {
        PlayerLog.logger.info(
            "[PlayerLog] {} interacted with {} at {} using {}",
            player.getEntityName(),
            world.getBlockState(hitResult.getBlockPos()).getBlock(),
            hitResult.getBlockPos(),
            stack.getItem()
        );
    }
}
