package com.xumuk.realism.items;

import java.util.Map;

import com.google.common.collect.Maps;
import com.xumuk.realism.basic.BasicItem;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSlice extends Item  {

	

	public ItemSlice(String name, CreativeTabs tab) {
		super();
		this.setHasSubtypes(true);
		this.setRegistryName(name);
		this.setCreativeTab(tab);
		this.setUnlocalizedName(name);
	}
	 public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
	    {
	        if (this.isInCreativeTab(tab))
	        {
	        	 for (StoneTypes type : StoneTypes.values())
	        	    {
	        	        items.add(new ItemStack(this, 1, type.getMeta()));
	        	    }
	        }
	    }

	    public String getUnlocalizedName(ItemStack stack)
	    {
	    	StoneTypes st = StoneTypes.byItemStack(stack);
	        return this.getUnlocalizedName() + "." + st.getName();
	    }
	 public enum StoneTypes
	 {
	
	     DEFAULT("default", 0),
	     GRANITE("granite", 1),
	     DIORITE("diorite", 2),
	     ANDESITE("andesite", 3);
		   private static final Map<Integer, StoneTypes> META_HASH = Maps.<Integer, StoneTypes>newHashMap();
		 public String name;
		 public int meta;


	     private StoneTypes(String name, int meta)
	        {
	    	 this.name=name;
	    	 this.meta=meta;
	    	
	        }
	     public static StoneTypes getByMeta(int meta)
	     {
	         for (StoneTypes type : values())
	         {
	             if (type.ordinal() == meta)
	                 return type;
	         }
	         return null;
	     }
	        public static StoneTypes byMetadata(int meta)
	        {
	        	StoneTypes st = META_HASH.get(Integer.valueOf(meta));
	            return st;
	        }
	        public static StoneTypes byItemStack(ItemStack stack)
	        {
	            return byMetadata(stack.getMetadata());
	        }
	        public String getName()
	        {
	            return this.name;
	        }
	        public int getMeta()
	        {
	            return this.meta;
	        }
	        static
	        {
	            for (StoneTypes st : values())
	            {
	            	META_HASH.put(Integer.valueOf(st.getMeta()), st);
	            }
	        }
	 }

}
