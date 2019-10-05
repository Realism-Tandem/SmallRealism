package gloomyfolken.hooklib.realism;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CheckFallable {
	public static void onStartFalling(EntityFallingBlock fallingEntity)
	{
	}
	
	public static void checkFallableDown(World world, BlockPos pos)
	{	
		IBlockState state = world.getBlockState(pos);

		if (world.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
		{
			if(!world.isRemote) {
				if(world.isAirBlock(pos.down())) {
					EntityFallingBlock efb = new EntityFallingBlock(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, world.getBlockState(pos));
					onStartFalling(efb);
					world.spawnEntity(efb);
				}
			}
		}
	}
	public static void checkFallableSouth(World world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);

		if (world.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
		{
			if(!world.isRemote) {
				if(world.isAirBlock(pos.down())){
					return;
				}
				if(world.isAirBlock(pos.south())){
					EntityFallingBlock efb = new EntityFallingBlock(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, world.getBlockState(pos.south()));
					onStartFalling(efb);
					world.spawnEntity(efb);
				}
				if( world.isAirBlock(new BlockPos(pos.getX(),pos.getY() - 1,pos.getZ() + 1))) {

					world.setBlockState(new BlockPos(pos.getX(),pos.getY(),pos.getZ() + 1), state);
					world.setBlockToAir(pos);
				}
			}
		}
	}
	public static void checkFallableNorth(World world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);

		if (world.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
		{
			if(!world.isRemote) {
				if(world.isAirBlock(pos.down())){
					return;
				}
				if(world.isAirBlock(pos.north())){
					EntityFallingBlock efb = new EntityFallingBlock(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, world.getBlockState(pos.north()));
					onStartFalling(efb);
					world.spawnEntity(efb);
				}
				if( world.isAirBlock(new BlockPos(pos.getX(),pos.getY() - 1,pos.getZ() - 1))) {

					world.setBlockState(new BlockPos(pos.getX(),pos.getY(),pos.getZ() - 1), state);
					world.setBlockToAir(pos);
				}
			}
		}
	}
	public static void checkFallableWest(World world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);

		if (world.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
		{
			if(!world.isRemote) {
				if(world.isAirBlock(pos.down())){
					return;
				}
				if(world.isAirBlock(pos.west())){
					EntityFallingBlock efb = new EntityFallingBlock(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, world.getBlockState(pos.west()));
					onStartFalling(efb);
					world.spawnEntity(efb);
				}
				if( world.isAirBlock(new BlockPos(pos.getX() + 1,pos.getY() - 1,pos.getZ()))) {

					world.setBlockState(new BlockPos(pos.getX() + 1,pos.getY(),pos.getZ()), state);
					world.setBlockToAir(pos);
				}
			}
		}
	}
	public static int tickRate(World worldIn)
	{
		return 2;
	}
	public static void checkFallableEast(World world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);

		if (world.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
		{
			if(!world.isRemote) {
				if(world.isAirBlock(pos.down())){
					return;
				}
				if(world.isAirBlock(pos.east())){
					EntityFallingBlock efb = new EntityFallingBlock(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, world.getBlockState(pos.east()));
					onStartFalling(efb);
					world.spawnEntity(efb);
				}
				if( world.isAirBlock(new BlockPos(pos.getX() - 1,pos.getY() - 1,pos.getZ()))) {

					world.setBlockState(new BlockPos(pos.getX() - 1,pos.getY(),pos.getZ()), state);
					world.setBlockToAir(pos);
				}
			}
		}
	}
}
