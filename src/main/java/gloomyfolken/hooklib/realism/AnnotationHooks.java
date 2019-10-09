package gloomyfolken.hooklib.realism;

import java.util.Random;

import javax.annotation.Nullable;

import com.xumuk.realism.RegBlocks;
import com.xumuk.realism.ReplaceVanillaBlocks;
import com.xumuk.realism.blocks.RottenPlanks;
import com.xumuk.realism.capability.worldCAP.Date.Season;
import com.xumuk.realism.capability.worldCAP.DateProvider;
import com.xumuk.realism.capability.worldCAP.IDate;

import gloomyfolken.hooklib.asm.Hook;
import gloomyfolken.hooklib.asm.ReturnCondition;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AnnotationHooks {


	@Hook(targetMethod="<init>", returnCondition = ReturnCondition.ALWAYS)
	public static void init(BlockStoneBrick bg) {
		bg.setTickRandomly(true);
		bg.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

	}

	@Hook(returnCondition = ReturnCondition.ALWAYS, isMandatory = true)
	public static void registerBlock(Block block, int id, ResourceLocation res, Block blockToRegister) {

		if(id == 4) {
			Block.REGISTRY.register(id, res, ReplaceVanillaBlocks.COBBLESTONE);
		}
		else
			Block.REGISTRY.register(id, res, blockToRegister);
	}

	@Hook(createMethod = true, returnCondition = ReturnCondition.ALWAYS)
	public static void updateTick(BlockStoneBrick bg, World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		IDate date = worldIn.getCapability(DateProvider.DATE, null);
		if (!worldIn.isRemote)
		{
			for(EnumFacing f :  EnumFacing.VALUES) {
				if(worldIn.getBlockState(pos.offset(f)).getMaterial() == Material.WATER ) {
					if(worldIn.rand.nextInt(3) == 2) {
						if(worldIn.getBlockState(pos) == state.withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.DEFAULT)) {
							if(worldIn.rand.nextInt(3) == 1) {
								worldIn.setBlockState(pos, state.withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY));
							} else 
								worldIn.setBlockState(pos, state.withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CRACKED));
						}
					}
					if(worldIn.rand.nextInt(4) == 2) {
						if(worldIn.getBlockState(pos) == state.withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CRACKED) ||worldIn.getBlockState(pos) == state.withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY)) {
							worldIn.setBlockState(pos, Blocks.COBBLESTONE.getDefaultState());
						}
					}
				}
			}

		}
	}

	@Hook(createMethod = true, returnCondition = ReturnCondition.ALWAYS)
	public static void onBlockAdded(BlockDirt bl,World worldIn, BlockPos pos, IBlockState state)
	{
		worldIn.scheduleUpdate(pos, Blocks.DIRT, CheckFallable.tickRate(worldIn));
	}

	@Hook(createMethod = true, returnCondition = ReturnCondition.ALWAYS)
	public static void updateTick(BlockDirt bl,World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote)
		{
			CheckFallable.checkFallableDown(world, pos);
			CheckFallable.checkFallableWest(world, pos);
			CheckFallable.checkFallableEast(world, pos);
			CheckFallable.checkFallableNorth(world, pos);
			CheckFallable.checkFallableSouth(world, pos);
		}
	}
	@Hook(createMethod = true, returnCondition = ReturnCondition.ALWAYS)
	public static void onEntityWalk(BlockPane pb,World worldIn, BlockPos pos, Entity entityIn) {
		worldIn.destroyBlock(pos, false);
	}
	public static final AxisAlignedBB NULL_AABB = null;
	@Hook(createMethod = true, returnCondition = ReturnCondition.ALWAYS)
	public static AxisAlignedBB getCollisionBoundingBox(BlockLeaves bl,IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static void updateTick(BlockFalling bl,World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote)
		{
			CheckFallable.checkFallableDown(world, pos);
			CheckFallable.checkFallableWest(world, pos);
			CheckFallable.checkFallableEast(world, pos);
			CheckFallable.checkFallableNorth(world, pos);
			CheckFallable.checkFallableSouth(world, pos);
		}
	}
	@Hook(createMethod = true, returnCondition = ReturnCondition.ALWAYS)
	public static void onEntityCollidedWithBlock(BlockLeaves bl,World world, BlockPos pos, IBlockState state, Entity entity) {
		entity.motionX *= 0.5D;
		entity.motionZ *= 0.5D;
	}
	



	@Hook(targetMethod="<init>", returnCondition = ReturnCondition.ALWAYS)
	public static void init(BlockStone bg) {
		bg.setTickRandomly(true);
		bg.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}
	@Hook(createMethod = true)
	public static void updateTick(BlockStone bg, World world, BlockPos pos, IBlockState state, Random rand)
	{

		if (!world.isRemote)
		{

			if(world.isAirBlock(pos.down()) && world.rand.nextInt(140) == 2) {
				world.setBlockState(pos, Blocks.COBBLESTONE.getDefaultState());


			}
		}
	}

	@Hook(targetMethod="<init>", returnCondition = ReturnCondition.ALWAYS)
	public static void init(BlockPlanks bg) {
		bg.setTickRandomly(true);
		bg.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}
	@Hook(createMethod = true)
	public static void updateTick(BlockPlanks bg, World world, BlockPos pos, IBlockState state, Random rand)
	{

		if (!world.isRemote)
		{
			for(EnumFacing f :  EnumFacing.VALUES) {
				if(world.getBlockState(pos.offset(f)).getMaterial() == Material.WATER ) {
					switch(state.getValue(BlockPlanks.VARIANT)) {
					case OAK:
						world.setBlockState(pos, RegBlocks.ROTTING_PLANKS.getDefaultState().withProperty(RottenPlanks.VARIANT, RottenPlanks.EnumType.ROTTEN_OAK));
					default:
						break;
					case SPRUCE:
						world.setBlockState(pos, RegBlocks.ROTTING_PLANKS.getDefaultState().withProperty(RottenPlanks.VARIANT, RottenPlanks.EnumType.ROTTEN_SPRUCE));
						break;
					case BIRCH:
						world.setBlockState(pos, RegBlocks.ROTTING_PLANKS.getDefaultState().withProperty(RottenPlanks.VARIANT, RottenPlanks.EnumType.ROTTEN_BIRCH));
						break;
					case JUNGLE:
						world.setBlockState(pos, RegBlocks.ROTTING_PLANKS.getDefaultState().withProperty(RottenPlanks.VARIANT, RottenPlanks.EnumType.ROTTEN_JUNGLE));
						break;
					case ACACIA:  
						world.setBlockState(pos, RegBlocks.ROTTING_PLANKS.getDefaultState().withProperty(RottenPlanks.VARIANT, RottenPlanks.EnumType.ROTTEN_ACACIA));
						break;
					case DARK_OAK:  
						world.setBlockState(pos, RegBlocks.ROTTING_PLANKS.getDefaultState().withProperty(RottenPlanks.VARIANT, RottenPlanks.EnumType.ROTTEN_DARK_OAK));
						break; 

					}


				}
			}
		}
	}
	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static void updateTick(BlockGrass bg, World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		IDate date = worldIn.getCapability(DateProvider.DATE, null);
		if (!worldIn.isRemote)
		{

			if (!worldIn.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
			if (worldIn.getLightFromNeighbors(pos.up()) < 4 && worldIn.getBlockState(pos.up()).getLightOpacity(worldIn, pos.up()) > 2)
			{
				worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState());
			}
			else
			{
				if (worldIn.getLightFromNeighbors(pos.up()) >= 9)
				{
					for (int i = 0; i < 4; ++i)
					{
						BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);

						if (blockpos.getY() >= 0 && blockpos.getY() < 256 && !worldIn.isBlockLoaded(blockpos))
						{
							return;
						}

						IBlockState iblockstate = worldIn.getBlockState(blockpos.up());
						IBlockState iblockstate1 = worldIn.getBlockState(blockpos);

						if (iblockstate1.getBlock() == Blocks.DIRT && iblockstate1.getValue(BlockDirt.VARIANT) == BlockDirt.DirtType.DIRT && worldIn.getLightFromNeighbors(blockpos.up()) >= 4 && iblockstate.getLightOpacity(worldIn, pos.up()) <= 2)
						{
							worldIn.setBlockState(blockpos, Blocks.GRASS.getDefaultState());
						}
					}
				}
				if (worldIn.getLightFromNeighbors(pos.up()) >= 5)
				{
					//	 ������� ����� ������� ����� ������ � ����� ������(��� � �����, ������ � �������� ���)
					/* 	int randSapl = worldIn.rand.nextInt(4);
                  	if(worldIn.isAirBlock(pos.up()) && date.getSeason() != Season.WINTER && worldIn.rand.nextInt(1300) == 232) {
                		worldIn.setBlockState(pos.up(), Blocks.SAPLING.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.byMetadata(randSapl)));

                	}*/
					if(worldIn.isAirBlock(pos.up()) && date.getSeason() != Season.WINTER && worldIn.rand.nextInt(600) == 532) {
						worldIn.setBlockState(pos.up(), Blocks.TALLGRASS.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS));

					}
					if(worldIn.isAirBlock(pos.up()) && date.getSeason() != Season.WINTER && worldIn.isAirBlock(pos.up(2)) && worldIn.rand.nextInt(1100) == 123) {
						int rand3 = worldIn.rand.nextInt(6);
						worldIn.setBlockState(pos.up(), Blocks.DOUBLE_PLANT.getDefaultState().withProperty(BlockDoublePlant.HALF,BlockDoublePlant.EnumBlockHalf.LOWER).withProperty(BlockDoublePlant.VARIANT, BlockDoublePlant.EnumPlantType.byMetadata(rand3)));
						worldIn.setBlockState(pos.up(2), Blocks.DOUBLE_PLANT.getDefaultState().withProperty(BlockDoublePlant.HALF,BlockDoublePlant.EnumBlockHalf.UPPER).withProperty(BlockDoublePlant.VARIANT, BlockDoublePlant.EnumPlantType.byMetadata(rand3)));
					}

				}
			}
		}
	}

}
