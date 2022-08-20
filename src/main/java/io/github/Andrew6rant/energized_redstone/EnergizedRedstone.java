package io.github.Andrew6rant.energized_redstone;

import com.google.common.collect.Iterables;
import io.github.Andrew6rant.energized_redstone.block.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.WallStandingBlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class EnergizedRedstone implements ModInitializer {

    public static final RedstoneHolder REDSTONE_HOLDER = new RedstoneHolder(FabricBlockSettings.of(Material.STONE).strength(1.5f, 1.5f).nonOpaque());
    public static final EnergizedRedstoneBlock ENERGIZED_REDSTONE_BLOCK = new EnergizedRedstoneBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_BLOCK));
    public static final EnergizedRedstoneWireBlock ENERGIZED_REDSTONE_WIRE = new EnergizedRedstoneWireBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_WIRE).nonOpaque());
    public static final EnergizedRedstoneTorch ENERGIZED_REDSTONE_TORCH = new EnergizedRedstoneTorch(FabricBlockSettings.copyOf(Blocks.REDSTONE_TORCH).nonOpaque().noCollision());
    public static final EnergizedRedstoneWallTorch ENERGIZED_REDSTONE_WALL_TORCH = new EnergizedRedstoneWallTorch(FabricBlockSettings.copyOf(Blocks.REDSTONE_WALL_TORCH).nonOpaque().noCollision());

    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, new Identifier("energized_redstone", "redstone_holder"), REDSTONE_HOLDER);
        Registry.register(Registry.ITEM, new Identifier("energized_redstone", "redstone_holder"), new BlockItem(REDSTONE_HOLDER, new FabricItemSettings().group(ItemGroup.REDSTONE)));
        Registry.register(Registry.BLOCK, new Identifier("energized_redstone", "energized_redstone_block"), ENERGIZED_REDSTONE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("energized_redstone", "energized_redstone_block"), new BlockItem(ENERGIZED_REDSTONE_BLOCK, new FabricItemSettings().group(ItemGroup.REDSTONE)));
        Registry.register(Registry.BLOCK, new Identifier("energized_redstone", "energized_redstone_wire"), ENERGIZED_REDSTONE_WIRE);
        Registry.register(Registry.ITEM, new Identifier("energized_redstone", "energized_redstone_wire"), new BlockItem(ENERGIZED_REDSTONE_WIRE, new FabricItemSettings().group(ItemGroup.REDSTONE)));
        Registry.register(Registry.BLOCK, new Identifier("energized_redstone", "energized_redstone_torch"), ENERGIZED_REDSTONE_TORCH);
        Registry.register(Registry.BLOCK, new Identifier("energized_redstone", "energized_redstone_wall_torch"), ENERGIZED_REDSTONE_WALL_TORCH);
        Registry.register(Registry.ITEM, new Identifier("energized_redstone", "energized_redstone_torch"), new WallStandingBlockItem(ENERGIZED_REDSTONE_TORCH, ENERGIZED_REDSTONE_WALL_TORCH, new FabricItemSettings().group(ItemGroup.REDSTONE)));
    }

    public static void energize(World world, BlockPos pos) {
        BlockPos newPos = pos.down();
        BlockState blockState = world.getBlockState(newPos);
        if (blockState.getBlock() == REDSTONE_HOLDER) {
            Iterable<BlockPos> iterable = BlockPos.iterate(newPos.south(1).west(2), newPos.north(1).east(2));
            Iterable<BlockPos> iterable2 = BlockPos.iterate(newPos.south(2).west(1), newPos.south(2).east(1));
            Iterable<BlockPos> iterable3 = BlockPos.iterate(newPos.north(2).west(1), newPos.north(2).east(1));
            Iterable<BlockPos> totalIterable = Iterables.concat(iterable, iterable2, iterable3);
            for (BlockPos mutable : totalIterable) {
                BlockState state = world.getBlockState(mutable);
                if (state.getBlock() == REDSTONE_HOLDER) {
                    int level = state.get(RedstoneHolder.LEVEL);
                    if (level != 0) { // only energize if there is redstone in holder
                        world.setBlockState(mutable, REDSTONE_HOLDER.getDefaultState().with(RedstoneHolder.ENERGIZED, true).with(RedstoneHolder.LEVEL, level).with(RedstoneHolder.ROD_CONNECTED, state.get(RedstoneHolder.ROD_CONNECTED)));
                    }
                }
            }
        }
    }
}
