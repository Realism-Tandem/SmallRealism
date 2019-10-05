package com.xumuk.realism.capability.worldCAP;

import com.xumuk.realism.capability.worldCAP.Date.Season;

public interface IDate
{
	
    void addDay(byte day);

    void addMonth(byte month);
    void addYear(int year);

    void remDay(byte day);
  
    void remMonth(byte month);


    void setDay(byte day);

    void setMonth(byte month);
    void setYear(int year);

    
    byte getDay();
 
    byte getMonth();
    int getYear();
    
    boolean setEnableSnow(boolean isSnow);
    boolean getEnableSnow();
	public Season getSeason();
}