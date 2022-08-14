package io.github.Andrew6rant.energized_redstone;

import io.github.Andrew6rant.energized_redstone.block.RedstoneHolder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class EnergizedRedstone implements ModInitializer {

    public static final RedstoneHolder REDSTONE_HOLDER = new RedstoneHolder(FabricBlockSettings.of(Material.STONE).strength(1.5f, 1.5f).nonOpaque());

    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, new Identifier("energized_redstone", "redstone_holder"), REDSTONE_HOLDER);
        Registry.register(Registry.ITEM, new Identifier("energized_redstone", "redstone_holder"), new BlockItem(REDSTONE_HOLDER, new FabricItemSettings().group(ItemGroup.REDSTONE)));
    }

    public static void energize(World world, BlockPos pos) {
        BlockPos newPos = pos.down();
        BlockState blockState = world.getBlockState(newPos);
        if (blockState.getBlock() == REDSTONE_HOLDER) {
            int level = blockState.get(RedstoneHolder.LEVEL);
            if (level != 0) { // only energize if there is redstone in holder
                world.setBlockState(newPos, REDSTONE_HOLDER.getDefaultState().with(RedstoneHolder.ENERGIZED, true).with(RedstoneHolder.LEVEL, level).with(RedstoneHolder.ROD_CONNECTED, blockState.get(RedstoneHolder.ROD_CONNECTED)));
            }
        }
    }
}
