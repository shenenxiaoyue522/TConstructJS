package com.xiaoyue.tconstruct_js.content.material.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.bindings.IngredientWrapper;
import dev.latvian.mods.kubejs.recipe.RecipesEventJS;
import net.minecraft.world.item.crafting.Ingredient;

public class MaterialRecipeBuilder {
    private final JsonObject recipe = new JsonObject();

    public MaterialRecipeBuilder() {
        recipe.addProperty("type", "tconstruct:material");
    }

    public static MaterialRecipeBuilder builder() {
        return new MaterialRecipeBuilder();
    }

    public static MaterialRecipeBuilder create() {
        return new MaterialRecipeBuilder();
    }

    public MaterialRecipeBuilder input(Ingredient ingredient) {
        recipe.add("ingredient", IngredientWrapper.of(ingredient).toJson());
        return this;
    }

    public MaterialRecipeBuilder material(String material) {
        recipe.addProperty("material", material);
        return this;
    }

    public MaterialRecipeBuilder needed(int needed) {
        recipe.addProperty("needed", needed);
        return this;
    }

    public MaterialRecipeBuilder value(int value) {
        recipe.addProperty("value", value);
        return this;
    }

    public JsonObject buildRecipe() {
        return recipe;
    }

    public JsonElement buildRecipeJson() {
        return recipe;
    }

    public void addRecipe(RecipesEventJS event) {
        event.custom(buildRecipe());
    }
}
