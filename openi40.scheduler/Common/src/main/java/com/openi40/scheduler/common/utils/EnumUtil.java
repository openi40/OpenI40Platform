package com.openi40.scheduler.common.utils;

public class EnumUtil {

	static public <T extends Enum> boolean isInList(T status, T list[]) {
		boolean isIn = false;
		if (status != null && list != null && list.length > 0) {
			for (int i = 0; i < list.length; i++) {
				T t = list[i];
				isIn = isIn || t.equals(status);
			}
		}
		return isIn;
	}

}
