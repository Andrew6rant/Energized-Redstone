package io.github.Andrew6rant.energized_redstone.potion;

import io.github.Andrew6rant.energized_redstone.EnergizedRedstone;
import me.emafire003.dev.potionrecipes.BrewingRecipeRegister;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExtendedPotions {
    // thanks to emafire003 for the example potion library code from GlowfulWorld
    public static Potion LONGER_NIGHT_VISION = register("longer_night_vision", new Potion("night_vision", new StatusEffectInstance(StatusEffects.NIGHT_VISION, 14400)));
    public static Potion LONGER_INVISIBILITY = register("longer_invisibility", new Potion("invisibility", new StatusEffectInstance(StatusEffects.INVISIBILITY, 14400)));
    public static Potion LONGER_LEAPING = register("longer_leaping", new Potion("leaping", new StatusEffectInstance(StatusEffects.JUMP_BOOST, 14400)));
    public static Potion LONGER_FIRE_RESISTANCE = register("longer_fire_resistance", new Potion("fire_resistance", new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 14400)));
    public static Potion LONGER_SWIFTNESS = register("longer_swiftness", new Potion("swiftness", new StatusEffectInstance(StatusEffects.SPEED, 14400)));
    public static Potion LONGER_SLOWNESS = register("longer_slowness", new Potion("slowness", new StatusEffectInstance(StatusEffects.SLOWNESS, 7200)));
    public static Potion LONGER_TURTLE_MASTER = register("longer_turtle_master", new Potion("turtle_master", new StatusEffectInstance(StatusEffects.SLOWNESS, 1600, 3), new StatusEffectInstance(StatusEffects.RESISTANCE, 1600, 2)));
    public static Potion LONGER_WATER_BREATHING = register("longer_water_breathing", new Potion("water_breathing", new StatusEffectInstance(StatusEffects.WATER_BREATHING, 14400)));
    public static Potion LONGER_POISON = register("longer_poison", new Potion("poison", new StatusEffectInstance(StatusEffects.POISON, 3600)));
    public static Potion LONGER_REGENERATION = register("longer_regeneration", new Potion("regeneration", new StatusEffectInstance(StatusEffects.REGENERATION, 3600)));
    public static Potion LONGER_STRENGTH = register("longer_strength", new Potion("strength", new StatusEffectInstance(StatusEffects.STRENGTH, 14400)));
    public static Potion LONGER_WEAKNESS = register("longer_weakness", new Potion("weakness", new StatusEffectInstance(StatusEffects.WEAKNESS, 7200)));
    public static Potion LONGER_SLOW_FALLING = register("longer_slow_falling", new Potion("slow_falling", new StatusEffectInstance(StatusEffects.SLOW_FALLING, 7200)));

    private static Potion register(String name, Potion potion) {
        return Registry.register(Registry.POTION, new Identifier("energized_redstone", name), potion);
    }

    public static void registerPotionRecipes() {
        BrewingRecipeRegister.registerPotionRecipe(Potions.NIGHT_VISION, EnergizedRedstone.ENERGIZED_REDSTONE_WIRE.asItem(), LONGER_NIGHT_VISION);
        BrewingRecipeRegister.registerPotionRecipe(Potions.INVISIBILITY, EnergizedRedstone.ENERGIZED_REDSTONE_WIRE.asItem(), LONGER_INVISIBILITY);
        BrewingRecipeRegister.registerPotionRecipe(Potions.LEAPING, EnergizedRedstone.ENERGIZED_REDSTONE_WIRE.asItem(), LONGER_LEAPING);
        BrewingRecipeRegister.registerPotionRecipe(Potions.FIRE_RESISTANCE, EnergizedRedstone.ENERGIZED_REDSTONE_WIRE.asItem(), LONGER_FIRE_RESISTANCE);
        BrewingRecipeRegister.registerPotionRecipe(Potions.SWIFTNESS, EnergizedRedstone.ENERGIZED_REDSTONE_WIRE.asItem(), LONGER_SWIFTNESS);
        BrewingRecipeRegister.registerPotionRecipe(Potions.SLOWNESS, EnergizedRedstone.ENERGIZED_REDSTONE_WIRE.asItem(), LONGER_SLOWNESS);
        BrewingRecipeRegister.registerPotionRecipe(Potions.TURTLE_MASTER, EnergizedRedstone.ENERGIZED_REDSTONE_WIRE.asItem(), LONGER_TURTLE_MASTER);
        BrewingRecipeRegister.registerPotionRecipe(Potions.WATER_BREATHING, EnergizedRedstone.ENERGIZED_REDSTONE_WIRE.asItem(), LONGER_WATER_BREATHING);
        BrewingRecipeRegister.registerPotionRecipe(Potions.POISON, EnergizedRedstone.ENERGIZED_REDSTONE_WIRE.asItem(), LONGER_POISON);
        BrewingRecipeRegister.registerPotionRecipe(Potions.REGENERATION, EnergizedRedstone.ENERGIZED_REDSTONE_WIRE.asItem(), LONGER_REGENERATION);
        BrewingRecipeRegister.registerPotionRecipe(Potions.STRENGTH, EnergizedRedstone.ENERGIZED_REDSTONE_WIRE.asItem(), LONGER_STRENGTH);
        BrewingRecipeRegister.registerPotionRecipe(Potions.WEAKNESS, EnergizedRedstone.ENERGIZED_REDSTONE_WIRE.asItem(), LONGER_WEAKNESS);
        BrewingRecipeRegister.registerPotionRecipe(Potions.SLOW_FALLING, EnergizedRedstone.ENERGIZED_REDSTONE_WIRE.asItem(), LONGER_SLOW_FALLING);
    }
}
