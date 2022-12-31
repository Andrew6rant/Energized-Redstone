package io.github.Andrew6rant.energized_redstone;

import io.github.Andrew6rant.energized_redstone.block.EnergizedRedstoneWireBlock;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.render.RenderLayer;

import static io.github.Andrew6rant.energized_redstone.EnergizedRedstone.*;

public class EnergizedRedstoneClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ENERGIZED_REDSTONE_WIRE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ENERGIZED_REDSTONE_TORCH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ENERGIZED_REDSTONE_WALL_TORCH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ENERGIZED_REPEATER, RenderLayer.getCutout());
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> EnergizedRedstoneWireBlock.getWireColor(state.get(EnergizedRedstoneWireBlock.POWER)), ENERGIZED_REDSTONE_WIRE);
    }
}
