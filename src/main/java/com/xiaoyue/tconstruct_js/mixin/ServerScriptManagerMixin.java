package com.xiaoyue.tconstruct_js.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.xiaoyue.tconstruct_js.event.api.MaterialDefinitionEventJS;
import com.xiaoyue.tconstruct_js.event.api.TConEventGroup;
import dev.latvian.mods.kubejs.script.data.VirtualKubeJSDataPack;
import dev.latvian.mods.kubejs.server.ServerScriptManager;
import net.minecraft.server.packs.resources.CloseableResourceManager;
import net.minecraft.server.packs.resources.MultiPackResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ServerScriptManager.class, remap = false)
public class ServerScriptManagerMixin {

    @Inject(at = @At(value = "INVOKE", target = "Ldev/latvian/mods/kubejs/event/EventHandler;post(Ldev/latvian/mods/kubejs/script/ScriptTypeHolder;Ldev/latvian/mods/kubejs/event/EventJS;)Ldev/latvian/mods/kubejs/event/EventResult;", ordinal = 1), method = "wrapResourceManager")
    public void tconstruct_js$addEvent(CloseableResourceManager original, CallbackInfoReturnable<MultiPackResourceManager> cir, @Local(name = "virtualDataPackHigh") VirtualKubeJSDataPack dataPack, @Local(name = "wrappedResourceManager") MultiPackResourceManager manager) {
        MaterialDefinitionEventJS event = new MaterialDefinitionEventJS(dataPack, manager);
        TConEventGroup.MATERIAL_DATA.post(event);
    }
}
