package com.xiaoyue.tconstruct_js.event.api;

import com.xiaoyue.tconstruct_js.content.material.MaterialColorBuilder;
import dev.latvian.mods.kubejs.client.GenerateClientAssetsEventJS;
import dev.latvian.mods.kubejs.generator.AssetJsonGenerator;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

public class MaterialColorSpriteEventJS extends GenerateClientAssetsEventJS {
    public MaterialColorSpriteEventJS(AssetJsonGenerator gen) {
        super(gen);
    }

    public void addMaterialSprite(String namespace, String material, Consumer<MaterialColorBuilder> builder) {
        MaterialColorBuilder colorBuilder = new MaterialColorBuilder();
        builder.accept(colorBuilder);
        add(new ResourceLocation(namespace, "tinkering/materials/" + material), colorBuilder.build());
    }

    public void addMaterialSprite(String material, Consumer<MaterialColorBuilder> builder) {
        MaterialColorBuilder colorBuilder = new MaterialColorBuilder();
        builder.accept(colorBuilder);
        add(new ResourceLocation("kubejs", "tinkering/materials/" + material), colorBuilder.build());
    }
}
