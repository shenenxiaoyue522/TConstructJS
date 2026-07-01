package com.xiaoyue.tconstruct_js.content.material;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.rhino.mod.util.JsonUtils;

public class MaterialColorBuilder {

    private final JsonObject color = new JsonObject();
    private final JsonObject generator = new JsonObject();
    private final JsonObject transformer = new JsonObject();
    private final JsonObject colorMapping = new JsonObject();
    private final JsonArray palette = new JsonArray();
    private final JsonArray supportedStats = new JsonArray();

    public MaterialColorBuilder() {
        color.addProperty("color", "FFFFFFFF");
        color.add("fallbacks", new JsonArray());
        String[] defaultStats = {
                "tconstruct:head",
                "tconstruct:handle",
                "tconstruct:binding",
                "tconstruct:repair_kit",
                "tconstruct:limb",
                "tconstruct:grip",
                "tconstruct:shield_core",
                "tconstruct:arrow_shaft"
        };
        for (String stat : defaultStats) {
            supportedStats.add(stat);
        }
        transformer.addProperty("type", "tconstruct:recolor_sprite");
        colorMapping.addProperty("type", "tconstruct:grey_to_color");
        transformer.add("color_mapping", colorMapping);
        generator.add("supported_stats", supportedStats);
        generator.add("transformer", transformer);
        color.add("generator", generator);
    }

    public MaterialColorBuilder color(String hexColor) {
        color.addProperty("color", hexColor);
        return this;
    }

    public MaterialColorBuilder fallback(String material) {
        JsonArray fallbackArray = color.getAsJsonArray("fallbacks");
        fallbackArray.add(material);
        return this;
    }

    public MaterialColorBuilder fallbacks(String... materials) {
        JsonArray fallbackArray = color.getAsJsonArray("fallbacks");
        for (String material : materials) {
            fallbackArray.add(material);
        }
        return this;
    }

    public MaterialColorBuilder supportedStat(String stat) {
        supportedStats.add(stat);
        generator.add("supported_stats", supportedStats);
        return this;
    }

    public MaterialColorBuilder supportedStats(String... stats) {
        for (String stat : stats) {
            supportedStats.add(stat);
        }
        generator.add("supported_stats", supportedStats);
        return this;
    }

    public MaterialColorBuilder setSupportedStats(String... stats) {
        for (JsonElement stat : supportedStats) {
            supportedStats.remove(stat);
        }
        for (String stat : stats) {
            supportedStats.add(stat);
        }
        generator.add("supported_stats", supportedStats);
        return this;
    }

    public MaterialColorBuilder addPalette(int grey, String colorHex) {
        JsonObject entry = new JsonObject();
        entry.addProperty("color", colorHex);
        entry.addProperty("grey", grey);
        palette.add(entry);
        colorMapping.add("palette", palette);
        return this;
    }

    public MaterialColorBuilder addPaletteFromArray(String... colors) {
        int step = 255 / (colors.length - 1);
        for (int i = 0; i < colors.length; i++) {
            int grey = i * step;
            if (i == colors.length - 1) grey = 255;
            addPalette(grey, colors[i]);
        }
        return this;
    }

    public MaterialColorBuilder transformerType(String type) {
        transformer.addProperty("type", type);
        return this;
    }

    public MaterialColorBuilder colorMappingType(String type) {
        colorMapping.addProperty("type", type);
        transformer.add("color_mapping", colorMapping);
        return this;
    }

    public MaterialColorBuilder extraTransformer(String key, Object value) {
        if (value instanceof Number) {
            transformer.addProperty(key, (Number) value);
        } else if (value instanceof String) {
            transformer.addProperty(key, (String) value);
        } else if (value instanceof Boolean) {
            transformer.addProperty(key, (Boolean) value);
        } else {
            transformer.add(key, JsonUtils.of(value.toString()));
        }
        generator.add("transformer", transformer);
        return this;
    }

    public JsonElement build() {
        return color;
    }
}
