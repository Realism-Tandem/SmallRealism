package com.xumuk.realism.render.model.row;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.xumuk.realism.render.model.backed.ModelBakedShovel;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ItemLayerModel;
import net.minecraftforge.common.model.IModelState;


public class ModelShovel implements IModel {
    final ResourceLocation texture;

    public ModelShovel(ResourceLocation texture){
        this.texture = texture;
    }

    @Override
    public Collection<ResourceLocation> getTextures() {
        return Lists.newArrayList(texture);
    }

    @Override
    public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        TextureAtlasSprite textureAtlasSprite = bakedTextureGetter.apply(texture);
        ImmutableList<BakedQuad> quads = ItemLayerModel.getQuadsForSprite(0, textureAtlasSprite, format, state.apply(Optional.empty()));
        return new ModelBakedShovel(quads, textureAtlasSprite);
    }
}