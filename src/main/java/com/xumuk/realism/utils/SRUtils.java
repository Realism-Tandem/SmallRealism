package com.xumuk.realism.utils;

import org.apache.logging.log4j.Level;

import com.xumuk.realism.RealismCore;

public class SRUtils {

	public static boolean intToBoolean(int input) {
		if ((input == 0) || (input == 1)) return input != 0;
		else {
			RealismCore.logger.log(Level.WARN, "Argument must be 1 or 0! It's will setting to false.");
			return false;
		}
	}
}