package com.xumuk.realism.capability.playerCAP;


/*
 * The basis of this code comes from modifications Bionisation 2
 * Thanks Thunder for the help
 */
public class PlayerCap  implements IPlayerCap{
	//It was all in one place to write and equated to 100, but I like it more
	public float tempBody = 36.6F;
	public final float maxTempBody = 42F;



	public float playerWeight = 70F;
	public float maxPlayerWeight = 180;
	public int waterLevel = 100;
	public final int maxlevel = 100;

	public int ticker = 0;
	//water
	@Override
	public void addWaterLevel(int level) {
		int line = waterLevel + level;
		if(line <= maxlevel) waterLevel = line;
		else waterLevel = maxlevel;		
	}
	@Override
	public void reduceWaterLevel(int level) {
		int line = waterLevel - level; 
		if(line >= 0) waterLevel = line;
		else waterLevel = 0;	

	}

	@Override
	public int getWaterLevel() {
		return waterLevel;
	}

	@Override
	public void setWaterLevel(int level) {
		if(level >= 0 && level <= maxlevel)
			waterLevel = level;	
	}
	public int getTicker() {
		return ticker;
	}

	public void setTicker(int value) {
		if(value < 0 || value > 240000) return;
		ticker = value;
	}

	public void incrementTicker(){
		if(ticker >= 240000) ticker = 0;
		else
			ticker++;
	}
	public void copyCapabilities(IPlayerCap level) {
		waterLevel = level.getWaterLevel();
	}



	@Override
	public void addTempBody(float temp) {
		float line = tempBody + temp;
		if(line <= maxTempBody) tempBody = line;
		else tempBody = maxTempBody;	

	}
	@Override
	public void reduceTempBody(float temp) {
		float line = tempBody - temp; 
		if(line >= 0) tempBody = line;
		else tempBody = 0;

	}
	@Override
	public float getTempBody() {
		return tempBody;
	}
	@Override
	public void setTempBody(float temp) {
		if(temp >= 0 && temp <= maxTempBody)
			tempBody = temp;		
	}
	
	
	
	@Override
	public void addWeight(float weight) {
		float add = playerWeight + weight;
		if(add <= 0)return;
		if(weight > 0 && weight <= maxPlayerWeight)
			playerWeight = add;
	}
	@Override
	public void reduceWeight(float weight) {
		float reduce = playerWeight - weight;
		if(reduce <= 0)return;
		if(weight > 0 && weight <= maxPlayerWeight) 
			playerWeight = reduce;
	}
	@Override
	public float getWeight() {
		return playerWeight;
	}
	@Override
	public void setWeight(float weight) {
		if(weight > 0 && weight <= maxPlayerWeight)
			playerWeight = weight;
	}


}
