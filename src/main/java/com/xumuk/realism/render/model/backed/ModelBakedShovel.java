package com.xumuk.realism.render.model.backed;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.util.vector.Vector3f;

import com.xumuk.realism.render.ComplexTexture;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverride;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModelBakedShovel implements IBakedModel {
	List<BakedQuad> quads;
	TextureAtlasSprite textureAtlasSprite;

	public ModelBakedShovel(List<BakedQuad> quads, TextureAtlasSprite textureAtlasSprite2){
		this.quads = quads;
		this.textureAtlasSprite = textureAtlasSprite2;
	}



	@Override
	public ItemCameraTransforms getItemCameraTransforms() {
		ItemTransformVec3f entity = new ItemTransformVec3f(new Vector3f(0f,0,0), new Vector3f(0.005f, 0.15f, 0.04f), new Vector3f(0.55f, 0.55f, 0.55f));
		ItemTransformVec3f thirdPersonRight = new ItemTransformVec3f(new Vector3f(60f,-90,-160), new Vector3f(0.005f, 0.05f, 0.04f), new Vector3f(0.55f, 0.55f, 0.55f));
		ItemTransformVec3f thirdPersonLeft = new ItemTransformVec3f(new Vector3f(100f,-90,150), new Vector3f(0.005f, 0.05f, 0.04f), new Vector3f(0.55f, 0.55f, 0.55f));
		ItemTransformVec3f firstPersonRight = new ItemTransformVec3f(new Vector3f(-60,-90,25), new Vector3f(0.03f, 0.23f, 0.104f), new Vector3f(0.68f, 0.68f, 0.68f));
		ItemTransformVec3f firstPersonLeft = new ItemTransformVec3f(new Vector3f(20,-90,25), new Vector3f(0.03f, 0.23f, 0.104f), new Vector3f(0.68f, 0.68f, 0.68f));
		ItemCameraTransforms itemCameraTransforms = new ItemCameraTransforms(thirdPersonLeft, thirdPersonRight, firstPersonLeft,  firstPersonRight, ItemTransformVec3f.DEFAULT, ItemTransformVec3f.DEFAULT, entity, ItemTransformVec3f.DEFAULT);
		return itemCameraTransforms;
	}
	@Override
	public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
		return quads;
	}
	@Override
	public boolean isAmbientOcclusion() {
		return false;
	}
	@Override
	public boolean isGui3d() {
		return false;
	}
	@Override
	public boolean isBuiltInRenderer() {
		return false;
	}
	@Override
	public TextureAtlasSprite getParticleTexture() {
		return textureAtlasSprite;
	}
	@Override
	public ItemOverrideList getOverrides() {
		return ItemOverrideList.NONE;
	}
	static class ItemOverrideListBlock extends ItemOverrideList{
		static ItemOverrideListBlock INSTANCE = new ItemOverrideListBlock(new ArrayList<>());
		ResourceLocation layer_ss = new ResourceLocation("realism:items/shovel/stone_shovel");
		
		ResourceLocation layer_sand = new ResourceLocation("realism:items/shovel/layers/sand");
		ResourceLocation layer_dirt = new ResourceLocation("realism:items/shovel/layers/dirt");
		ResourceLocation layer_grass = new ResourceLocation("realism:items/shovel/layers/grass");

		public ItemOverrideListBlock(List<ItemOverride> overridesIn) {
			super(overridesIn);
		}

		@Override
		public IBakedModel handleItemState(IBakedModel originalModel, ItemStack stack, World world, EntityLivingBase entity) {
			
			if(originalModel instanceof ModelBakedShovel == false) return originalModel;
			ModelBakedShovel original = (ModelBakedShovel) originalModel;

			final NBTTagCompound nbt = stack.getTagCompound();
			
			ComplexTexture ctSand = new ComplexTexture(layer_ss, layer_dirt);
			
			if(nbt == null) return originalModel;
			if (!stack.isEmpty() && nbt != null && nbt.hasKey("blockName")) {
				final String blockName = nbt.getString("blockName");
				final Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(blockName));
				if (block != null) {
					if(block.getDefaultState().getMaterial() == Material.SAND) {
						
						return original;
					} 
					else 
						if(block.getDefaultState().getMaterial() == Material.GROUND) {


						} 
						else
							if(block.getDefaultState().getMaterial() == Material.GRASS) {


							}

				}
			}
			return original;
		}
	}
}
