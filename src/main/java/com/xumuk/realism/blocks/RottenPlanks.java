package com.xumuk.realism.blocks;

import com.xumuk.realism.basic.block.BasicBlockFalling;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;

public class RottenPlanks extends BasicBlockFalling
{
    public static final PropertyEnum<RottenPlanks.EnumType> VARIANT = PropertyEnum.<RottenPlanks.EnumType>create("variant", RottenPlanks.EnumType.class);
    public static boolean fallInstantly;
    public RottenPlanks(String name, CreativeTabs tab)
    {
        super(Material.WOOD, name, tab);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, RottenPlanks.EnumType.ROTTEN_OAK));
    

    }

    public int damageDropped(IBlockState state)
    {
        return ((RottenPlanks.EnumType)state.getValue(VARIANT)).getMetadata();
    }
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for (RottenPlanks.EnumType blockstone$enumtype : RottenPlanks.EnumType.values())
        {
            items.add(new ItemStack(this, 1, blockstone$enumtype.getMetadata()));
        }
    }
  
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, RottenPlanks.EnumType.byMetadata(meta));
    }

    public int getMetaFromState(IBlockState state)
    {
        return ((RottenPlanks.EnumType)state.getValue(VARIANT)).getMetadata();
    }
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }

    public static enum EnumType implements IStringSerializable
    {
        ROTTEN_OAK(0, "r_oak", true),
        ROTTEN_SPRUCE(1, "r_spruce", true),
        ROTTEN_BIRCH(2, "r_birch", true),
        ROTTEN_JUNGLE(3, "r_jungle", true),
        ROTTEN_ACACIA(4, "r_acacia", true),
        ROTTEN_DARK_OAK(5, "r_dark_oak", true);

        private static final RottenPlanks.EnumType[] META_LOOKUP = new RottenPlanks.EnumType[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;
        private final boolean isNatural;

        private EnumType(int meta, String name, boolean isNatural)
        {
            this(meta, name, name, isNatural);
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

        public static RottenPlanks.EnumType byMetadata(int meta)
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
            for (RottenPlanks.EnumType blockstone$enumtype : values())
            {
                META_LOOKUP[blockstone$enumtype.getMetadata()] = blockstone$enumtype;
            }
        }
    }
}