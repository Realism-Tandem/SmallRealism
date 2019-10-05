package com.xumuk.realism.render;

import java.util.Collection;
import java.util.function.Function;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

public class ComplexTexture extends TextureAtlasSprite
{
    private ResourceLocation texture1;
    private ResourceLocation texture2;
    private ImmutableList<ResourceLocation> dependencies;

    public ComplexTexture(ResourceLocation texture1, ResourceLocation texture2) {
        super(texture1.toString().concat("_".concat(texture2.toString())));
        this.texture1 = texture1;
        this.texture2 = texture2;
        dependencies = ImmutableList.of(this.texture1, this.texture2);
    }

    @Override
    public boolean hasCustomLoader(IResourceManager manager, ResourceLocation location) {
        return true;
    }

    @Override
    public Collection<ResourceLocation> getDependencies() {
        return dependencies;
    }

    @Override
    public boolean load(IResourceManager manager, ResourceLocation location, Function<ResourceLocation, TextureAtlasSprite> textureGetter) {
        TextureAtlasSprite sprite = textureGetter.apply(texture1);
        TextureAtlasSprite mappingSprite = textureGetter.apply(texture2);
        width = sprite.getIconWidth();
        height = sprite.getIconHeight();
        int[][] pixels = new int[Minecraft.getMinecraft().gameSettings.mipmapLevels + 1][];
        pixels[0] = new int[width * height];

        int[][] oldPixels = sprite.getFrameTextureData(0);

        int[][] alphaPixels = mappingSprite.getFrameTextureData(0);

        for (int p = 0; p < width * height; p++) {
            int oldPixelAlpha = alphaPixels[0][p] >>> 24;

            if (oldPixelAlpha > 0)

                pixels[0][p] = alphaPixels[0][p];
            else
             
                pixels[0][p] = oldPixels[0][p];
        }
  
        this.clearFramesTextureData();
      
        this.framesTextureData.add(pixels);

        return false;
    }
}