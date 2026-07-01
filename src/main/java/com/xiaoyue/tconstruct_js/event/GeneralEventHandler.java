package com.xiaoyue.tconstruct_js.event;

import com.xiaoyue.tconstruct_js.event.api.TConEventGroup;
import com.xiaoyue.tconstruct_js.event.api.TinkerToolChangeEventJS;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.events.ToolEquipmentChangeEvent;

import static com.xiaoyue.tconstruct_js.TConstructJS.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GeneralEventHandler {

    @SubscribeEvent
    public static void onToolChange(ToolEquipmentChangeEvent event) {
        var eventJs = new TinkerToolChangeEventJS(event.getContext());
        TConEventGroup.EQUIPMENT_CHANGE.post(eventJs);
    }
}
