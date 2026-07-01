package com.xiaoyue.tconstruct_js.content.material;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xiaoyue.tconstruct_js.content.material.recipe.MaterialRecipeBuilder;

import java.util.function.Consumer;

public class MaterialDefinitionBuilder {

    private final String materialLoc;
    private final JsonObject definition = new JsonObject();
    private MaterialRecipeBuilder recipe = null;

    public MaterialDefinitionBuilder(String materialLoc) {
        this.materialLoc = materialLoc;
    }

    public MaterialDefinitionBuilder craftable(boolean craftable) {
        definition.addProperty("craftable", craftable);
        return this;
    }

    public MaterialDefinitionBuilder hidden(boolean hidden) {
        definition.addProperty("hidden", hidden);
        return this;
    }

    public MaterialDefinitionBuilder sortOrder(int sortOrder) {
        definition.addProperty("sortOrder", sortOrder);
        return this;
    }

    public MaterialDefinitionBuilder tier(int tier) {
        definition.addProperty("tier", tier);
        return this;
    }

    public MaterialDefinitionBuilder setCraftOrRepairRecipe(Consumer<MaterialRecipeBuilder> builder) {
        MaterialRecipeBuilder recipe = new MaterialRecipeBuilder();
        recipe.material(materialLoc);
        builder.accept(recipe);
        this.recipe = recipe;
        return this;
    }

    public MaterialRecipeBuilder getRecipe() {
        return recipe;
    }

    public JsonElement build() {
        return definition;
    }

    public String buildString() {
        return build().toString();
    }
}
