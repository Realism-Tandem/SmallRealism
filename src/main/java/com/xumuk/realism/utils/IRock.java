package com.xumuk.realism.utils;

public interface IRock {

	/**
	 * 
	 * @param meta
	 * Meta of item
	 * @return
	 * If return is "-1" - ignore color
	 */
	default int getRockColor(int meta) {
		return -1;
	}
}