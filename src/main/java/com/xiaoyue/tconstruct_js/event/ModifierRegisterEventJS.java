package com.xiaoyue.tconstruct_js.event;

import com.xiaoyue.tconstruct_js.content.ModifierBuilder;
import dev.latvian.mods.kubejs.KubeJS;
import dev.latvian.mods.kubejs.event.StartupEventJS;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierId;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ModifierRegisterEventJS extends StartupEventJS {
    public final Map<ModifierId, Modifier> MODIFIER_MAP = new HashMap<>();

    public void createNew(String id, Consumer<ModifierBuilder> consumer) {
        ModifierBuilder builder = new ModifierBuilder();
        consumer.accept(builder);
        MODIFIER_MAP.put(new ModifierId(KubeJS.appendModId(id)), builder.createModifier());
    }

    public void createEmpty(String id) {
        MODIFIER_MAP.put(new ModifierId(KubeJS.appendModId(id)), new Modifier());
    }
}
