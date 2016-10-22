package com.stu.fastpan.util;

import java.math.BigDecimal;
import java.util.List;


/**
 * @version:	  
 */
public class CommonUtil{
	
	public static int getMaxPage(int total,int pageSize){
		return total/pageSize+((total%pageSize)>0?1:0);
	}
	public static boolean isIntegerValid(Integer value) {
		boolean result = false;
		if(value != null && value > 0) {
			result = true;
		}
		return result;
	}
	public static boolean isListValid(List<?> dataList) {
		boolean result = false;
		if(dataList != null && !dataList.isEmpty()) {
			result = true;
		}
		return result;
	}
	public static boolean isLongValid(Long value) {
		boolean result = false;
		if(value != null && value > 0L) {
			result = true;
		}
		return result;
	}
	public static boolean isBigDecimalValid(BigDecimal value){
		boolean result = false;
		if (value != null && value.compareTo(BigDecimal.ZERO) > 0) {
			result = true;
		}
		return result;
	}
}
