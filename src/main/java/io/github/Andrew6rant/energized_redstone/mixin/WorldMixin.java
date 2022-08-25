package io.github.Andrew6rant.energized_redstone.mixin;

import io.github.Andrew6rant.energized_redstone.block.EnergizedRedstoneWireBlock;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.util.math.Direction;

@Mixin(World.class)
public abstract class WorldMixin {
    @Inject(method = "getReceivedRedstonePower", at = @At("HEAD"), cancellable = true)
    public void getReceivedRedstonePower(BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        World world = (World) (Object) this;
        if (world.getBlockState(pos).getBlock() instanceof EnergizedRedstoneWireBlock) {
            int i = 0;
            for (Direction direction : Direction.values()) {
                BlockPos offsetPos = pos.offset(direction);
                int j = world.getEmittedRedstonePower(offsetPos, direction);
                if (world.getBlockState(offsetPos).getBlock() instanceof RedstoneWireBlock) {
                    // subtract one from the power level if Energized Redstone Wire is connected to Vanilla Redstone Wire
                    // This allows power level to go down when a player places alternating Redstone Wire and Energized Redstone Wire
                    j -= 1;
                }
                if (j >= 31) {
                    cir.setReturnValue(31);
                }
                if (j > i) {
                    i = j;
                }
            }
            cir.setReturnValue(i);
        } else {
            int i = 0;
            for (Direction direction : Direction.values()) {
                BlockPos offsetPos = pos.offset(direction);
                int j = world.getEmittedRedstonePower(offsetPos, direction);
                //System.out.println(world.getBlockState(offsetPos).getBlock().getLootTableId().getNamespace().equals("energized_redstone"));
                if (world.getBlockState(offsetPos).getBlock() instanceof EnergizedRedstoneWireBlock) {
                    j -= 1;
                }
                if (j >= 15) {
                    j = 15;
                    cir.setReturnValue(j);
                }
                if (j > i) {
                    i = j;
                }
            }
            cir.setReturnValue(i);
        }
    }
}
