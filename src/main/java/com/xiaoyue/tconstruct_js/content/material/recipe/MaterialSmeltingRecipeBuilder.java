package com.xiaoyue.tconstruct_js.content.material.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.recipe.RecipesEventJS;

public class MaterialSmeltingRecipeBuilder {

    private String material;
    private String fluid;
    private String fluidTag;
    private int fluidAmount = 90;
    private int temperature = 800;

    public MaterialSmeltingRecipeBuilder material(String material) {
        this.material = material;
        return this;
    }

    public static MaterialSmeltingRecipeBuilder builder() {
        return new MaterialSmeltingRecipeBuilder();
    }

    public MaterialSmeltingRecipeBuilder fluid(String fluidId) {
        if (fluidId.contains("#")) {
            this.fluidTag = fluidId;
        } else {
            this.fluid = fluidId;
        }
        return this;
    }

    public MaterialSmeltingRecipeBuilder amount(int amount) {
        this.fluidAmount = amount;
        return this;
    }

    public MaterialSmeltingRecipeBuilder temperature(int temp) {
        this.temperature = temp;
        return this;
    }

    public void addSmeltingRecipe(RecipesEventJS event) {
        JsonObject meltingRecipe = buildMeltingRecipe();
        event.custom(meltingRecipe);
        JsonObject fluidRecipe = buildFluidRecipe();
        event.custom(fluidRecipe);
    }

    public JsonElement buildMeltingJson() {
        return buildMeltingRecipe();
    }

    public JsonElement buildFluidJson() {
        return buildFluidRecipe();
    }

    private JsonObject buildMeltingRecipe() {
        JsonObject recipe = new JsonObject();
        recipe.addProperty("type", "tconstruct:material_melting");
        recipe.addProperty("input", material);
        JsonObject result = new JsonObject();
        result.addProperty("amount", fluidAmount);
        if (fluidTag != null) {
            result.addProperty("tag", fluidTag);
        } else {
            result.addProperty("fluid", fluid);
        }
        recipe.add("result", result);
        recipe.addProperty("temperature", temperature);
        return recipe;
    }

    private JsonObject buildFluidRecipe() {
        JsonObject recipe = new JsonObject();
        recipe.addProperty("type", "tconstruct:material_fluid");
        JsonObject fluid = new JsonObject();
        fluid.addProperty("amount", fluidAmount);
        fluid.addProperty("fluid", this.fluid);
        recipe.add("fluid", fluid);
        recipe.addProperty("output", material);
        recipe.addProperty("temperature", temperature);
        return recipe;
    }
}
