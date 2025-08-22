package com.xiaoyue.tconstruct_js.event;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;

public interface TConEventGroup {

    EventGroup GROUP = EventGroup.of("TConJSEvents");

    EventHandler REGISTRY = GROUP.startup("modifierRegistry", () -> ModifierRegisterEventJS.class);
    EventHandler EQUIPMENT_CHANGE = GROUP.server("equipmentChange", () -> TinkerToolChangeEventJS.class);

}
