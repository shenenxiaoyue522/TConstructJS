package com.xiaoyue.tconstruct_js.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.xiaoyue.tconstruct_js.event.api.MaterialColorSpriteEventJS;
import com.xiaoyue.tconstruct_js.event.api.TConEventGroup;
import dev.latvian.mods.kubejs.client.GeneratedClientResourcePack;
import dev.latvian.mods.kubejs.generator.AssetJsonGenerator;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.kubejs.script.data.GeneratedData;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(value = GeneratedClientResourcePack.class, remap = false)
public class GeneratedClientResourcePackMixin {

    @Inject(at = @At(value = "INVOKE", target = "Ldev/latvian/mods/kubejs/client/GenerateClientAssetsEventJS;<init>(Ldev/latvian/mods/kubejs/generator/AssetJsonGenerator;)V"), method = "generate")
    public void tconstruct_js$addEvent(Map<ResourceLocation, GeneratedData> map, CallbackInfo ci, @Local(name = "generator") AssetJsonGenerator gen) {
        MaterialColorSpriteEventJS event = new MaterialColorSpriteEventJS(gen);
        TConEventGroup.MATERIAL_ASSETS.post(ScriptType.CLIENT, event);
    }
}
