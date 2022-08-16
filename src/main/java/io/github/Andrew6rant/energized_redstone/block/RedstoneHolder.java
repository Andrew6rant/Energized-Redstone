package io.github.Andrew6rant.energized_redstone.block;

import io.github.Andrew6rant.energized_redstone.EnergizedRedstone;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LightningRodBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class RedstoneHolder extends Block {
    public RedstoneHolder(Settings settings) {
        super(settings.nonOpaque());
    }

    public static final IntProperty LEVEL = IntProperty.of("level", 0, 9);
    public static final BooleanProperty ROD_CONNECTED = BooleanProperty.of("rod_connected");
    public static final BooleanProperty ENERGIZED = BooleanProperty.of("energized");

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
        builder.add(ROD_CONNECTED);
        builder.add(ENERGIZED);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(LEVEL, 0).with(ROD_CONNECTED, false).with(ENERGIZED, false);
    }

    public VoxelShape getOutlineShape(BlockState blockState, BlockView view, BlockPos pos, ShapeContext context) {
        boolean connected = blockState.get(ROD_CONNECTED);
        if (connected) {
            return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 1.125f, 1f);
        } else {
            return VoxelShapes.fullCube();
        }
    }

    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        int level = blockState.get(LEVEL);
        boolean energized = blockState.get(ENERGIZED);
        if (energized) {
            dropStack(world, pos, new ItemStack(EnergizedRedstone.ENERGIZED_REDSTONE_WIRE, level));
            world.setBlockState(pos, blockState.with(LEVEL, 0).with(ENERGIZED, false));
            player.playSound(SoundEvents.BLOCK_CAKE_ADD_CANDLE, 1, 1);
            return ActionResult.SUCCESS;
        }
        ItemStack stack = player.getStackInHand(hand);

        if (level != 9) {
            if (stack.getItem().equals(Items.REDSTONE)) { // increase level by one if redstone is used
                if (!player.isCreative()) {
                    stack.setCount(stack.getCount() - 1);
                }
                world.setBlockState(pos, blockState.with(LEVEL, level+1));
                player.playSound(SoundEvents.BLOCK_CAKE_ADD_CANDLE, 1, 1);
                return ActionResult.SUCCESS;
            }
            if (stack.getItem().equals(Items.REDSTONE_BLOCK)) { // shortcut for player to fill entire holder at once
                if (!player.isCreative()) {
                    stack.setCount(stack.getCount() - 1);
                }
                dropStack(world, pos, new ItemStack(Items.REDSTONE, level));
                world.setBlockState(pos, blockState.with(LEVEL, 9));
                player.playSound(SoundEvents.BLOCK_CAKE_ADD_CANDLE, 1, 1);
                return ActionResult.SUCCESS;
            }
        }
        if (stack.isEmpty() && level != 0) { // return redstone to player on empty hand
            dropStack(world, pos, new ItemStack(Items.REDSTONE, level));
            world.setBlockState(pos, blockState.with(LEVEL, 0));
            player.playSound(SoundEvents.BLOCK_CAKE_ADD_CANDLE, 1, 1);
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }

    @Override
    public void neighborUpdate(BlockState blockState, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (world.getBlockState(pos.up()).getBlock() instanceof LightningRodBlock) {
            world.setBlockState(pos, blockState.with(ROD_CONNECTED, true));
        } else {
            world.setBlockState(pos, blockState.with(ROD_CONNECTED, false));
        }
    }
}
