package io.github.Andrew6rant.energized_redstone.mixin;

import io.github.Andrew6rant.energized_redstone.block.EnergizedRedstoneWireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.client.render.WorldRenderer.DIRECTIONS;

@Mixin(World.class)
public abstract class WorldMixin {
    @Shadow public abstract BlockState getBlockState(BlockPos pos);
    @Shadow public int getEmittedRedstonePower(BlockPos pos, Direction direction) {
        System.out.println("shadow method, whoops!");
        return 0;
    }
    int getStrongRedstonePower(BlockPos pos, Direction direction) {
        return 0;
    }
    @Inject(method = "getReceivedStrongRedstonePower", at = @At("HEAD"), cancellable = true)
    public void getReceivedStrongRedstonePower(BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if(this.getBlockState(pos).getBlock() instanceof EnergizedRedstoneWireBlock) {

        }
    }
    public int newReceivedStrongRedstonePower(BlockPos pos) {
        int i = 0;
        i = Math.max(i, this.getStrongRedstonePower(pos.down(), Direction.DOWN));
        System.out.println("down: " + i);
        if (i >= 31) {
            return i;
        } else {
            i = Math.max(i, this.getStrongRedstonePower(pos.up(), Direction.UP));
            System.out.println("up: " + i);
            if (i >= 31) {
                return i;
            } else {
                i = Math.max(i, this.getStrongRedstonePower(pos.north(), Direction.NORTH));
                System.out.println("north: " + i);
                if (i >= 31) {
                    return i;
                } else {
                    i = Math.max(i, this.getStrongRedstonePower(pos.south(), Direction.SOUTH));
                    System.out.println("south: " + i);
                    if (i >= 31) {
                        return i;
                    } else {
                        i = Math.max(i, this.getStrongRedstonePower(pos.west(), Direction.WEST));
                        System.out.println("west: " + i);
                        if (i >= 31) {
                            return i;
                        } else {
                            i = Math.max(i, this.getStrongRedstonePower(pos.east(), Direction.EAST));
                            System.out.println("east: " + i);
                            return i >= 31 ? i : i;
                        }
                    }
                }
            }
        }
    }
    @Inject(method = "getReceivedRedstonePower", at = @At("HEAD"), cancellable = true)
    public void getReceivedRedstonePower(BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if(this.getBlockState(pos).getBlock() instanceof EnergizedRedstoneWireBlock) {
            World world = (World) (Object) this;
            System.out.println("Custom wire detected: getReceivedRedstonePower------");
            int i = 0;
            Direction[] var3 = DIRECTIONS;
            int var4 = var3.length;
            for(int var5 = 0; var5 < var4; ++var5) {
                Direction direction = var3[var5];
                int j = this.getEmittedRedstonePower(pos.offset(direction), direction);
                int weakRedstonePower = this.getBlockState(pos).getBlock().getWeakRedstonePower(this.getBlockState(pos), world, pos, direction);
                System.out.println(this.getBlockState(pos).isSolidBlock(world, pos) ? Math.max(i, this.newReceivedStrongRedstonePower(pos)) : i);
                System.out.println("direction = " + direction + " j = " + j+ " weakRedstonePower = " + weakRedstonePower);
                if (j >= 31) {
                    cir.setReturnValue(31);
                }
                if (j > i) {
                    i = j;
                }
            }
            cir.setReturnValue(i);
        }
    }
}