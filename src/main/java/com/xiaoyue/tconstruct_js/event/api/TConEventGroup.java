package com.xiaoyue.tconstruct_js.event.api;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;

public interface TConEventGroup {

    EventGroup GROUP = EventGroup.of("TConJSEvents");

    EventHandler MODIFIER = GROUP.startup("modifierRegistry", () -> ModifierRegisterEventJS.class);
    EventHandler MATERIAL_DATA = GROUP.server("materialDefinition", () -> MaterialDefinitionEventJS.class);
    EventHandler MATERIAL_ASSETS = GROUP.client("materialColorSprite", () -> MaterialColorSpriteEventJS.class);
    EventHandler EQUIPMENT_CHANGE = GROUP.server("equipmentChange", () -> TinkerToolChangeEventJS.class);

}
