package io.github.Andrew6rant.energized_redstone.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public class EnergizedRedstoneTorch extends RedstoneTorchBlock {
    public EnergizedRedstoneTorch(Settings settings) {
        super(settings);
    }
    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(LIT) && Direction.UP != direction ? 31 : 0;
    }
}
