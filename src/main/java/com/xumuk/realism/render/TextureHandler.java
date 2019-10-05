package com.xumuk.realism.render;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TextureHandler {
    private static List<TextureAtlasSprite> textureAtlasSprites = new ArrayList<>();
 
    @SubscribeEvent
    public void event(TextureStitchEvent.Pre event) {
        for (TextureAtlasSprite sprite : textureAtlasSprites)
            event.getMap().setTextureEntry(sprite);
    }

    public static void registerSprite(TextureAtlasSprite textureAtlasSprite){
        textureAtlasSprites.add(textureAtlasSprite);
    }
}