package com.xumuk.realism.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class SRLogManager extends LogManager {

	public boolean log(Level error, String str) {
		getLogger("[SR]").log(error, str);
		return true;
	}
}