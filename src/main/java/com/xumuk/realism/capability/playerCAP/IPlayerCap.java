package com.xumuk.realism.capability.playerCAP;

import java.util.List;
/*
 * The basis of this code comes from modifications Bionisation 2
 * Thanks Thunder for the help
 */
public interface IPlayerCap {
	//Water Bar
	public void addWaterLevel(int level);
	public void reduceWaterLevel(int level);
	public int getWaterLevel();
	public void setWaterLevel(int level);
	
	public void addTempBody(float temp);
	public void reduceTempBody(float temp);
	public float getTempBody();
	public void setTempBody(float temp);
	

	
	public void setTicker(int value);
	public int getTicker();
	public void incrementTicker();
	public void copyCapabilities(IPlayerCap indicator);
	
	public void addWeight(float weight);
	public void reduceWeight(float weight);
	public float getWeight();
	public void setWeight(float weight);

}

	