package io.github.Andrew6rant.energized_redstone.mixin;

import io.github.Andrew6rant.energized_redstone.block.EnergizedRedstoneWireBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RedstoneWireBlock.class)
public class RedstoneWireBlockMixin {
    // Thanks to Fusion-Flux of Multi-colored-redstone for this modified code snippet
    // https://github.com/Fusion-Flux/Multi-Colored-Redstone/blob/1.18/src/main/java/com/fusionflux/chromaticcurrents/mixin/RedstoneWireMixin.java#L15-L20
    @Inject(at = @At("HEAD"), method = "connectsTo(Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/Direction;)Z",cancellable = true)
    private static void connectsTo(BlockState state, Direction dir, CallbackInfoReturnable<Boolean> cir) {
        if(state.getBlock() instanceof EnergizedRedstoneWireBlock) {
            cir.setReturnValue(false);
        }
    }
}
