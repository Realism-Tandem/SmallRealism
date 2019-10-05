package com.xumuk.realism.capability.playerCAP;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
/*
 * The basis of this code comes from modifications Bionisation 2
 * Thanks Thunder for the help
 */
public class PlayerCapProvider implements ICapabilitySerializable<NBTBase> {
	
	@CapabilityInject(IPlayerCap.class)
	public static final Capability<IPlayerCap> LEVEL_CAP = null;
	
	private IPlayerCap instance = LEVEL_CAP.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {	
		
		 return capability == LEVEL_CAP;		 
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		
		 return capability == LEVEL_CAP ? LEVEL_CAP.<T> cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		
		 return LEVEL_CAP.getStorage().writeNBT(LEVEL_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
	
		LEVEL_CAP.getStorage().readNBT(LEVEL_CAP, this.instance, null, nbt);
	}

}