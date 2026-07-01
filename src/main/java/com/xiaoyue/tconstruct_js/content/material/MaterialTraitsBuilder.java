package com.xiaoyue.tconstruct_js.content.material;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class MaterialTraitsBuilder {
    private final JsonArray defaultTraits = new JsonArray();
    private final Map<String, JsonArray> perStatTraits = new HashMap<>();

    public MaterialTraitsBuilder add(String name, int level) {
        JsonObject trait = new JsonObject();
        trait.addProperty("name", name);
        trait.addProperty("level", level);
        defaultTraits.add(trait);
        return this;
    }

    public MaterialTraitsBuilder addAll(Map<String, Integer> traits) {
        traits.forEach((name, level) -> {
            JsonObject trait = new JsonObject();
            trait.addProperty("name", name);
            trait.addProperty("level", level);
            defaultTraits.add(trait);
        });
        return this;
    }

    public MaterialTraitsBuilder perStat(String partId, String traitName, int level) {
        JsonArray traits = perStatTraits.computeIfAbsent(partId, k -> new JsonArray());
        JsonObject trait = new JsonObject();
        trait.addProperty("name", traitName);
        trait.addProperty("level", level);
        traits.add(trait);
        return this;
    }

    public MaterialTraitsBuilder perStatAll(String partId, Map<String, Integer> traits) {
        JsonArray traitsArray = perStatTraits.computeIfAbsent(partId, k -> new JsonArray());
        traits.forEach((name, level) -> {
            JsonObject trait = new JsonObject();
            trait.addProperty("name", name);
            trait.addProperty("level", level);
            traitsArray.add(trait);
        });
        return this;
    }

    public JsonElement build() {
        JsonObject root = new JsonObject();
        if (!defaultTraits.isEmpty()) {
            root.add("default", defaultTraits);
        }
        if (!perStatTraits.isEmpty()) {
            JsonObject perStat = new JsonObject();
            perStatTraits.forEach(perStat::add);
            root.add("perStat", perStat);
        }
        return root;
    }
}