package com.xumuk.realism.blocks;

import com.xumuk.realism.basic.block.BasicBlockFalling;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;

public class BlockCobblestones extends BasicBlockFalling
{
    public static final PropertyEnum<BlockCobblestones.EnumType> VARIANT = PropertyEnum.<BlockCobblestones.EnumType>create("variant", BlockCobblestones.EnumType.class);

    public BlockCobblestones(String name, CreativeTabs tab)
    {
        super(Material.ROCK, name, tab);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockCobblestones.EnumType.COBBLE_ANDESITE));
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

    }

    public int damageDropped(IBlockState state)
    {
    	
        return ((BlockCobblestones.EnumType)state.getValue(VARIANT)).getMetadata();
    }

    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for (BlockCobblestones.EnumType blockstone$enumtype : BlockCobblestones.EnumType.values())
        {
            items.add(new ItemStack(this, 1, blockstone$enumtype.getMetadata()));
        }
    }

    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockCobblestones.EnumType.byMetadata(meta));
    }

    public int getMetaFromState(IBlockState state)
    {
        return ((BlockCobblestones.EnumType)state.getValue(VARIANT)).getMetadata();
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }

    public static enum EnumType implements IStringSerializable
    {
        COBBLE_GRANITE(0, "granite", true),
        COBBLE_DIORITE(1,  "diorite", true),
        COBBLE_ANDESITE(2,"andesite", true);

        private static final BlockCobblestones.EnumType[] META_LOOKUP = new BlockCobblestones.EnumType[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;

        private final boolean isNatural;

        private EnumType(int meta, String name, boolean isNatural)
        {
            this(meta,name, name, isNatural);
        }

        private EnumType(int meta, String name, String Uname, boolean isNatural)
        {
            this.meta = meta;
            this.name = name;
            this.unlocalizedName = Uname;
         
            this.isNatural = isNatural;
        }

        public int getMetadata()
        {
            return this.meta;
        }



        public String toString()
        {
            return this.name;
        }

        public static BlockCobblestones.EnumType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName()
        {
            return this.name;
        }

        public String getUnlocalizedName()
        {
            return this.unlocalizedName;
        }

        public boolean isNatural()
        {
            return this.isNatural;
        }

        static
        {
            for (BlockCobblestones.EnumType blockstone$enumtype : values())
            {
                META_LOOKUP[blockstone$enumtype.getMetadata()] = blockstone$enumtype;
            }
        }
    }
}