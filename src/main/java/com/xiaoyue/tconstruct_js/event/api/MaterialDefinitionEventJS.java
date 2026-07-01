package com.xiaoyue.tconstruct_js.event.api;

import com.xiaoyue.tconstruct_js.content.material.MaterialDefinitionBuilder;
import com.xiaoyue.tconstruct_js.content.material.MaterialStatsBuilder;
import com.xiaoyue.tconstruct_js.content.material.MaterialTraitsBuilder;
import com.xiaoyue.tconstruct_js.content.material.recipe.MaterialRecipeBuilder;
import com.xiaoyue.tconstruct_js.content.material.recipe.MaterialSmeltingRecipeBuilder;
import dev.latvian.mods.kubejs.script.data.DataPackEventJS;
import dev.latvian.mods.kubejs.script.data.VirtualKubeJSDataPack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.MultiPackResourceManager;

import java.util.function.Consumer;

public class MaterialDefinitionEventJS extends DataPackEventJS {
    public MaterialDefinitionEventJS(VirtualKubeJSDataPack d, MultiPackResourceManager rm) {
        super(d, rm);
    }

    public void addMaterialData(String namespace, String material, Consumer<MaterialDefinitionBuilder> definition, Consumer<MaterialStatsBuilder> stats, Consumer<MaterialTraitsBuilder> traits, Consumer<MaterialSmeltingRecipeBuilder> smelting) {
        String materialLoc = new ResourceLocation(namespace, material).toString();
        MaterialDefinitionBuilder definitionBuilder = new MaterialDefinitionBuilder(materialLoc);
        definition.accept(definitionBuilder);
        addJson(new ResourceLocation(namespace, "tinkering/materials/definition/" + material), definitionBuilder.build());
        MaterialStatsBuilder statsBuilder = new MaterialStatsBuilder();
        stats.accept(statsBuilder);
        addJson(new ResourceLocation(namespace, "tinkering/materials/stats/" + material), statsBuilder.build());
        MaterialTraitsBuilder traitsBuilder = new MaterialTraitsBuilder();
        traits.accept(traitsBuilder);
        addJson(new ResourceLocation(namespace, "tinkering/materials/traits/" + material), traitsBuilder.build());
        MaterialRecipeBuilder recipe = definitionBuilder.getRecipe();
        if (recipe != null) {
            addJson(new ResourceLocation(namespace, "recipes/table/" + material), recipe.buildRecipe());
        }
        MaterialSmeltingRecipeBuilder smeltingRecipe = new MaterialSmeltingRecipeBuilder().material(materialLoc);
        smelting.accept(smeltingRecipe);
        addJson(new ResourceLocation(namespace, "recipes/smelting/" + material + "/material_fluid"), smeltingRecipe.buildFluidJson());
        addJson(new ResourceLocation(namespace, "recipes/smelting/" + material + "/material_melting"), smeltingRecipe.buildMeltingJson());
    }

    public void addMaterialData(String name, Consumer<MaterialDefinitionBuilder> definition, Consumer<MaterialStatsBuilder> stats, Consumer<MaterialTraitsBuilder> traits, Consumer<MaterialSmeltingRecipeBuilder> smelting) {
        addMaterialData("kubejs", name, definition, stats, traits, smelting);
    }
}
