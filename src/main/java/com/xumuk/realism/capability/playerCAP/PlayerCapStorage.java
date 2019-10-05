package com.xumuk.realism.capability.playerCAP;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
/*
 * The basis of this code comes from modifications Bionisation 2
 * Thanks Thunder for the help
 */
public class PlayerCapStorage implements IStorage<IPlayerCap> {
	@Override
	public NBTBase writeNBT(Capability<IPlayerCap> capability, IPlayerCap instance, EnumFacing side) {
		NBTTagCompound properties = new NBTTagCompound();
		properties.setInteger("waterlevel", instance.getWaterLevel());
		properties.setFloat("tempBody", instance.getTempBody());
		properties.setFloat("playerweight", instance.getWeight());

		return properties;
	}
	@Override
	public void readNBT(Capability<IPlayerCap> capability, IPlayerCap instance, EnumFacing side, NBTBase nbt) {	
		NBTTagCompound properties = (NBTTagCompound)nbt;
		instance.setWaterLevel(properties.getInteger("waterlevel"));
		instance.setTempBody(properties.getFloat("tempBody"));
		instance.setWeight(properties.getFloat("playerweight"));

	}
}
