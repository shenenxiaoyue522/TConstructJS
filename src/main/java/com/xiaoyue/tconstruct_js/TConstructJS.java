package com.xiaoyue.tconstruct_js;

import com.mojang.logging.LogUtils;
import com.xiaoyue.tconstruct_js.event.ModifierRegisterEventJS;
import com.xiaoyue.tconstruct_js.event.TConEventGroup;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import slimeknights.tconstruct.library.modifiers.ModifierManager;

@Mod(TConstructJS.MODID)
public class TConstructJS {
    public static final String MODID = "tconstruct_js";
    public static final Logger LOGGER = LogUtils.getLogger();

    public TConstructJS() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::initModifier);
    }

    public static ResourceLocation loc(String s) {
        return new ResourceLocation(MODID, s);
    }

    public void initModifier(ModifierManager.ModifierRegistrationEvent event) {
        var eventJs = new ModifierRegisterEventJS();
        TConEventGroup.REGISTRY.post(eventJs);
        for (var entry : eventJs.MODIFIER_MAP.entrySet()) {
            event.registerStatic(entry.getKey(), entry.getValue());
        }
    }
}
