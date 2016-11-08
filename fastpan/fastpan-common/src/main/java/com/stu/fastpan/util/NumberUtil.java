package com.stu.fastpan.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class NumberUtil {
	public static Integer[] parseStrigToIntegerArray(String[] source) {
		Integer[] returnValue = null;
		List<Integer> returnValueList = null;
		if (source != null && source.length > 0) {
			returnValueList = new ArrayList<Integer>();
			for (int i = 0; i < source.length; i++) {
				if (isNumber(source[i])) {
					returnValueList.add(Integer.parseInt(source[i]));
				}
			}
		}
		if (returnValueList != null && returnValueList.size() > 0) {
			returnValue = new Integer[returnValueList.size()];
			returnValueList.toArray(returnValue);
		}
		return returnValue;
	}

	public static int[] parseIntArray(String[] source) {
		int[] returnValue = null;
		if (source != null && source.length > 0) {
			returnValue = new int[source.length];
			for (int i = 0; i < source.length; i++) {
				returnValue[i] = Integer.parseInt(source[i]);
			}
		}
		return returnValue;
	}

	public static int[] parseIntArray(Integer[] source) {
		int[] returnValue = null;
		if (source != null && source.length > 0) {
			returnValue = new int[source.length];
			for (int i = 0; i < source.length; i++) {
				returnValue[i] = source[i].intValue();
			}
		}
		return returnValue;
	}

	public static Integer[] parseIntegerArray(Long[] source) {
		Integer[] returnValue = null;
		if (source != null && source.length > 0) {
			returnValue = new Integer[source.length];
			for (int i = 0; i < source.length; i++) {
				returnValue[i] = source[i].intValue();
			}
		}
		return returnValue;
	}

	public static int[] parseIntArray(Long[] source) {
		int[] returnValue = null;
		if (source != null && source.length > 0) {
			returnValue = new int[source.length];
			for (int i = 0; i < source.length; i++) {
				returnValue[i] = source[i].intValue();
			}
		}
		return returnValue;
	}

	public static Long[] parseLongArray(Integer[] source) {
		Long[] returnValue = null;
		if (source != null && source.length > 0) {
			returnValue = new Long[source.length];
			for (int i = 0; i < source.length; i++) {
				returnValue[i] = Long.valueOf(source[i]);
			}
		}
		return returnValue;
	}

	public static long[] parselongArray(Integer[] source) {
		long[] returnValue = null;
		if (source != null && source.length > 0) {
			returnValue = new long[source.length];
			for (int i = 0; i < source.length; i++) {
				returnValue[i] = Long.valueOf(source[i]);
			}
		}
		return returnValue;
	}

	public static Long[] parseLongArray(int[] source) {
		Long[] returnValue = null;
		if (source != null && source.length > 0) {
			returnValue = new Long[source.length];
			for (int i = 0; i < source.length; i++) {
				returnValue[i] = Long.valueOf(source[i]);
			}
		}
		return returnValue;
	}

	public static Long[] parseLongArray(String[] source) {
		Long[] returnValue = null;
		if (source != null && source.length > 0) {
			returnValue = new Long[source.length];
			for (int i = 0; i < source.length; i++) {
				returnValue[i] = Long.parseLong(source[i]);
			}
		}
		return returnValue;
	}

	public static boolean isNumber(String number) {
		if (number.matches("^[0123456789]+$")) {
			// 是数字
			return true;
		} else {
			// 不是数字
			return false;
		}
	}

	/**
	 * 取随机整数
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int random(int min, int max) {
		return ((int) (min + Math.random() * (max - min + 1)));
	}

	/**
	 * 从整型数组中随机取几个数据
	 * 
	 * @param data
	 *            初始数据
	 * @param count
	 *            数据个数
	 * @return
	 */
	public static Integer[] randomIntData(Integer[] data, int count) {
		Integer[] returnValue = null;
		if (data != null && data.length > 0) {
			if (data.length <= count) {
				returnValue = data;
			} else {
				Random r = new Random();
				returnValue = new Integer[count];
				int index = 0;
				for (int i = 0; i < count; i++) {
					// 刚开始从数组中随机抽取一个
					// 而后将抽取的元素后面的元素向前推进到随机的位置[index位置]
					// 随着循环的继续,逐渐抛弃后面的元素
					index = r.nextInt(data.length - i);
					returnValue[i] = data[index];
					// 元素向前推进到随机[index]的位置
					for (int j = index; j < data.length - i - 1; j++) {
						data[j] = data[j + 1];
					}
				}
			}

		}
		return returnValue;

	}

	/**
	 * 从整型数组中随机取几个数据
	 * 
	 * @param data
	 *            初始数据
	 * @param count
	 *            数据个数
	 * @return
	 */
	public static String[] randomStringData(String[] data, int count) {
		String[] returnValue = null;
		if (data != null && data.length > 0) {
			if (data.length <= count) {
				returnValue = data;
			} else {
				int randlength = data.length - count;
				String[] returndata = new String[count];
				int randstart = random(0, randlength - 1);
				int j = 0;
				for (int i = randstart; i < randstart + count; i++) {
					returndata[j] = data[i];
					j++;
				}
				returnValue = returndata;
			}

		}
		return returnValue;

	}

	public static Long[] parseLongArray(long[] source) {
		Long[] returnValue = null;
		if (source != null && source.length > 0) {
			returnValue = new Long[source.length];
			for (int i = 0; i < source.length; i++) {
				returnValue[i] = Long.valueOf(source[i]);
			}
		}
		return returnValue;
	}

	public static Number getNumber(final Map map, final Object key) {
		if (map != null) {
			Object answer = map.get(key);
			if (answer != null) {
				if (answer instanceof Number) {
					return (Number) answer;

				} else if (answer instanceof String) {
					try {
						String text = (String) answer;
						return NumberFormat.getInstance().parse(text);

					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	public static Integer getInteger(final Map map, final Object key) {
		Number answer = getNumber(map, key);
		if (answer == null) {
			return null;
		} else if (answer instanceof Integer) {
			return (Integer) answer;
		}
		return Integer.valueOf(answer.intValue());
	}

	public static Integer getInteger(Map map, Object key, Integer defaultValue) {
		Integer answer = getInteger(map, key);
		if (answer == null) {
			answer = defaultValue;
		}
		return answer;
	}

	public static int getIntValue(final Map map, final Object key) {
		Integer integerObject = getInteger(map, key);
		if (integerObject == null) {
			return 0;
		}
		return integerObject.intValue();
	}

	public static int getIntValue(final Map map, final Object key,
			int defaultValue) {
		Integer integerObject = getInteger(map, key);
		if (integerObject == null) {
			return defaultValue;
		}
		return integerObject.intValue();
	}

	public static Long getLong(final Map map, final Object key) {
		Number answer = getNumber(map, key);
		if (answer == null) {
			return null;
		} else if (answer instanceof Long) {
			return (Long) answer;
		}
		return Long.valueOf(answer.longValue());
	}

	public static Long getLong(Map map, Object key, Long defaultValue) {
		Long answer = getLong(map, key);
		if (answer == null) {
			answer = defaultValue;
		}
		return answer;
	}

	public static long getLongValue(final Map map, final Object key) {
		Long longObject = getLong(map, key);
		if (longObject == null) {
			return 0L;
		}
		return longObject.longValue();
	}

	public static long getLongValue(final Map map, final Object key,
			long defaultValue) {
		Long longObject = getLong(map, key);
		if (longObject == null) {
			return defaultValue;
		}
		return longObject.longValue();
	}

	public static Integer[] parseIntegerArray(int[] source) {
		Integer[] returnValue = null;
		if (source != null && source.length > 0) {
			returnValue = new Integer[source.length];
			for (int i = 0; i < source.length; i++) {
				returnValue[i] = Integer.valueOf(source[i]);
			}
		}
		return returnValue;
	}

	public static int getIntModValue(Integer num) {
		int mod = 0;
		if (num != null) {
			mod = num % 10;
		}
		return mod;
	}
	
	//获取随机数字字母组合 length为位数
	public static String getRandomCharAndNumr(Integer length) {  
	    String str = "";  
	    Random random = new Random();  
	    for (int i = 0; i < length; i++) {  
	        boolean b = random.nextBoolean();  
	        if (b) { // 字符串  
	            // int choice = random.nextBoolean() ? 65 : 97; 取得65大写字母还是97小写字母  
	            str += (char) (65 + random.nextInt(26));// 取得大写字母  
	        } else { // 数字  
	            str += String.valueOf(random.nextInt(10));  
	        }  
	    }  
	    return str;  
	}
	public static void main(String[] argv) {
		// Integer[] testdata ={1,2,3,4,5,6,7,8};
		// Integer[] test = randomIntData(testdata,3);
		// for(int i=0;i<test.length;i++){
		// System.out.println(test[i]);
		// }
		System.out.println(random(1, 1));
	}
}
