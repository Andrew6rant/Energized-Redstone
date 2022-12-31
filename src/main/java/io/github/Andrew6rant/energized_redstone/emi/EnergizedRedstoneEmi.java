package io.github.Andrew6rant.energized_redstone.emi;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiStack;
import io.github.Andrew6rant.energized_redstone.EnergizedRedstone;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.recipe.EmiRecipeSorting;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class EnergizedRedstoneEmi implements EmiPlugin {
    public static final Identifier MY_SPRITE_SHEET = new Identifier("energized_redstone", "textures/gui/emi_simplified_textures.png");

    public static final EmiStack REDSTONE_HOLDER = EmiStack.of(EnergizedRedstone.REDSTONE_HOLDER);
    //public static final EmiRecipeCategory REDSTONE_HOLDER_CATEGORY = new EmiRecipeCategory(new Identifier("energized_redstone", "redstone_holder"), MY_SPRITE_SHEET);
    public static final EmiRecipeCategory REDSTONE_HOLDER_CATEGORY
            = new EmiRecipeCategory(Identifier.of("energized_redstone", "redstone_holder"), new EmiTexture(MY_SPRITE_SHEET, 0, 0, 16, 16));

    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(REDSTONE_HOLDER_CATEGORY);
        registry.addWorkstation(REDSTONE_HOLDER_CATEGORY, REDSTONE_HOLDER);
    }
}
