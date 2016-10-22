package com.stu.fastpan.util;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class DataConverUtil {

	private final static char[] mChars = "0123456789ABCDEF".toCharArray();
	private final static String mHexStr = "0123456789ABCDEF";
	
	/**
	 * 将字符串转换成二进制字符串
	 * 
	 * @param bs
	 * @return
	 */
	public static String strToBinStr(String str) {
        char[] strChar=str.toCharArray();
        String result="";
        for(int i=0;i<strChar.length;i++){
            result +=Integer.toBinaryString(strChar[i]);
        }
        return result;
    }

	/**
	 * 检查16进制字符串是否有效
	 * 
	 * @param sHex
	 *            String 16进制字符串
	 * @return boolean
	 */
	public static boolean checkHexStr(String sHex) {
		String sTmp = sHex.toString().trim().replace(" ", "")
				.toUpperCase(Locale.US);
		int iLen = sTmp.length();

		if (iLen > 1 && iLen % 2 == 0) {
			for (int i = 0; i < iLen; i++)
				if (!mHexStr.contains(sTmp.substring(i, i + 1)))
					return false;
			return true;
		} else
			return false;
	}
	/**
	 * 取字节数组的16进制字符串
	 * 
	 * @param bs
	 * @return
	 */
	public static String getHexStr(byte[] bs) {
		StringBuffer sb = new StringBuffer("");
		if(bs!=null){
			for (int i = 0; i < bs.length; i++) {
				sb.append(getHexStr(bs[i]));
			}
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 取字节的16进制字符串
	 * 
	 * @param bs
	 * @return
	 */
	public static String getHexStr(byte bs) {
		String retStr = "";
		if (Integer.toHexString((int) bs).length() > 1) {
			retStr += Integer.toHexString((int) bs).substring(
					Integer.toHexString((int) bs).length() - 2);
		} else {
			retStr += "0"
					+ Integer.toHexString((int) bs).substring(
							Integer.toHexString((int) bs).length() - 1);
		}
		return retStr;
	}
	/**
	 * 16进制字符串转字节数组
	 */
	public static byte[] getHexByte(String byteStr) {
		if (byteStr.length() % 2 != 0) {
			byteStr = "0" + byteStr;
		}
		byte[] retByte = new byte[byteStr.length() / 2];

		for (int i = 0; i < byteStr.length() / 2; i++) {
			byte[] b = new byte[1];
			b[0] = int2byte(Integer.parseInt(byteStr
					.substring(2 * i, 2 * i + 2), 16))[3];

			retByte[i] = int2byte(Integer.parseInt(byteStr.substring(2 * i,
					2 * i + 2), 16))[3];
		}
		return retByte;
	}
	/**
	 * int转换为字节数组
	 * 
	 * @param n
	 * @return
	 */
	public static byte[] int2byte(int n) {
		byte b[] = new byte[4];
		b[0] = (byte) (n >> 24);
		b[1] = (byte) (n >> 16);
		b[2] = (byte) (n >> 8);
		b[3] = (byte) n;
		return b;
	}
	/**
	 * 字符串转换成十六进制字符串
	 * 
	 * @param str
	 *            String 待转换的ASCII字符串
	 * @return String 每个Byte之间空格分隔，如: [61 6C 6B]
	 */
	public static String str2HexStr(String str,int i) {
		str = str.replace(".", "");
		long j = Long.parseLong(str);
		String s  = Long.toHexString(j);
		for (int k = s.length(); k < i; k++){
			s = "0" + s;
		}
		return s;

	}

	/**
	 * 十六进制字符串转换成 ASCII字符串
	 * 
	 * @param str
	 *            String Byte字符串
	 * @return String 对应的字符串
	 * @throws UnsupportedEncodingException 
	 */
	public static String hexStr2Str(String hexStr) throws UnsupportedEncodingException {
		hexStr = hexStr.toString().trim().replace(" ", "")
				.toUpperCase(Locale.US);
		char[] hexs = hexStr.toCharArray();
		byte[] bytes = new byte[hexStr.length() / 2];
		int iTmp = 0x00;

		for (int i = 0; i < bytes.length; i++) {
			iTmp = mHexStr.indexOf(hexs[2 * i]) << 4;
			iTmp |= mHexStr.indexOf(hexs[2 * i + 1]);
			bytes[i] = (byte) (iTmp & 0xFF);
		}
		String a=new String(bytes);
		return a;
	}

	/**
	 * bytes转换成十六进制字符串
	 * 
	 * @param b
	 *            byte[] byte数组
	 * @param iLen
	 *            int 取前N位处理 N=iLen
	 * @return String 每个Byte值之间空格分隔
	 */
	public static String byte2HexStr(byte[] b, int iLen) {
		StringBuilder sb = new StringBuilder();
		for (int n = 0; n < iLen; n++) {
			sb.append(mChars[(b[n] & 0xFF) >> 4]);
			sb.append(mChars[b[n] & 0x0F]);
			// sb.append(' ');
		}
		return sb.toString().trim().toUpperCase(Locale.US);
	}

	/**
	 * bytes字符串转换为Byte值
	 * 
	 * @param src
	 *            String Byte字符串，每个Byte之间没有分隔符(字符范围:0-9 A-F)
	 * @return byte[]
	 */
	public static byte[] hexStr2Bytes(String src) {
		/* 对输入值进行规范化整理 */
		src = src.trim().replace(" ", "").toUpperCase(Locale.US);
		// 处理值初始化
		int m = 0, n = 0;
		int iLen = src.length() / 2; // 计算长度
		byte[] ret = new byte[iLen]; // 分配存储空间

		for (int i = 0; i < iLen; i++) {
			m = i * 2 + 1;
			n = m + 1;
			ret[i] = (byte) (Integer.decode("0x" + src.substring(i * 2, m)
					+ src.substring(m, n)) & 0xFF);
		}
		return ret;
	}

	/**
	 * String的字符串转换成unicode的String
	 * 
	 * @param strText
	 *            String 全角字符串
	 * @return String 每个unicode之间无分隔符
	 * @throws Exception
	 */
	public static String strToUnicode(String strText) throws Exception {
		char c;
		StringBuilder str = new StringBuilder();
		int intAsc;
		String strHex;
		for (int i = 0; i < strText.length(); i++) {
			c = strText.charAt(i);
			intAsc = (int) c;
			strHex = Integer.toHexString(intAsc);
			if (intAsc > 128)
				str.append("\\u");
			else
				// 低位在前面补00
				str.append("\\u00");
			str.append(strHex);
		}
		return str.toString();
	}

	/**
	 * unicode的String转换成String的字符串
	 * 
	 * @param hex
	 *            String 16进制值字符串 （一个unicode为2byte）
	 * @return String 全角字符串
	 * @see CHexConver.unicodeToString("\\u0068\\u0065\\u006c\\u006c\\u006f")
	 */
	public static String unicodeToString(String hex) {
		int t = hex.length() / 6;
		int iTmp = 0;
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < t; i++) {
			String s = hex.substring(i * 6, (i + 1) * 6);
			// 将16进制的string转为int
			iTmp = (Integer.valueOf(s.substring(2, 4), 16) << 8)
					| Integer.valueOf(s.substring(4), 16);
			// 将int转换为字符
			str.append(new String(Character.toChars(iTmp)));
		}
		return str.toString();
	}

	/**
	 * 字节补位
	 * 
	 * */
	public static String byteHexFillseats(String str) {
		int i = (str.length() / 2) % 8;
		if (i != 0) {
			str += "80";
			for (int j = 1; j < 8 - i; j++)
				str += "00";
		} else {
			str += "8000000000000000";
		}
		return str;
	}

	public static String convertHexToString(String hex) {

		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();

		// 49204c6f7665204a617661 split into two characters 49, 20, 4c...
		for (int i = 0; i < hex.length() - 1; i += 2) {
			// grab the hex in pairs
			String output = hex.substring(i, (i + 2));
			// convert hex to decimal
			int decimal = Integer.parseInt(output, 16);
			// convert the decimal to character
			sb.append((char) decimal);

			temp.append(decimal);
		}
		return sb.toString();
	}

	/**
	 * 
	 * @param b
	 *            byte[]
	 * @return String
	 */
	public static String bytes2HexString(byte[] b) {
		String ret = "";
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex.toUpperCase();
		}
		return ret;
	}

	/**
	 * 字符转ASC
	 * 
	 * @param st
	 * @return
	 */
	public static String getAsc(String st) {
		StringBuilder sb = new StringBuilder();
		byte[] gc = st.getBytes();
		for (int i = 0; i < gc.length; i++) {
			int ascNum = (int) gc[i];
			sb.append(ascNum);
		}
		return sb.toString();
	}

	/**
	 * ASC转字符
	 * 
	 * @param backnum
	 * @return
	 */
	public static String backchar(String backnum) {

		StringBuilder ret = new StringBuilder();
		String strChar = "";
		for (int i = 0; i < backnum.length(); i++) {
			if (i * 2 < backnum.length()) {
				StringBuilder sb = new StringBuilder();
				sb.append(backnum.substring(i * 2, (i + 1) * 2));
				strChar = String
						.valueOf(((char) Integer.parseInt(sb.toString())));
				ret.append(strChar);
			} else {
				break;
			}
		}
		System.out.println(ret.toString());
		return ret.toString();
	}

	/**
	 * 字符转ASC
	 * 
	 * @param st
	 * @return
	 */
	public static int str2Asc(String st) {
		byte[] gc = st.getBytes();
		int ascNum = (int) gc[0];
		return ascNum;
	}

	/**
	 * ASC转字符
	 * 
	 * @param backnum
	 * @return
	 */
	public static char asc2Str(int backnum) {
		char strChar = (char) backnum;
		return strChar;
	}

	/**
	 * Ascii转Hex
	 * @param String str
	 * @return String hex
	 * 
	 */
	public static String asciiToHex(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(
						s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				//log.error("异常信息 {}",e);
			}
		}
		try {
			s = new String(baKeyword,"GBK");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}

	public static void main(String[] args) throws Exception {
		String s = "152146464646";
		
		System.out.println(s.substring(2, s.length()-8));
	}

	/**
	 * Hex字符串转Ascii
	 * @param String str
	 * @return String ascii
	 * @author wl
	 */
	public static String strToAscii(String str){
        StringBuilder sb=new StringBuilder();
        byte[] bs=str.getBytes();
        for(int i=0;i<bs.length;i++)
            sb.append(toHex(bs[i]));
        return sb.toString();
    }
	
	 public static String toHex(int n){
	        StringBuilder sb=new StringBuilder();
	        if(n/16==0){
	            return toHexUtil(n);
	        }else{
	            String t=toHex(n/16);
	            int nn=n%16;
	            sb.append(t).append(toHexUtil(nn));
	        }
	        return sb.toString();
	    }
	 public static String toHexUtil(int n){
	        String rt="";
	    switch(n){
	    case 10:rt+="A";break;
	    case 11:rt+="B";break;
	    case 12:rt+="C";break;
	    case 13:rt+="D";break;
	    case 14:rt+="E";break;
	    case 15:rt+="F";break;
	    default:
	        rt+=n;
	    }
	    return rt;
	}
	 
	 /**
		 * 16进制的ascii码还原为Hex字符串
		 * @param String asciiStr
		 * @return String hex
		 * @author wl
		 */
	 public static String decodeAsciiToHex(String asciiStr)
		{
		 ByteArrayOutputStream baos=new ByteArrayOutputStream(asciiStr.length()/2);
		//将每2位16进制整数组装成一个字节
		String hexString="0123456789ABCDEF";
		 for(int i=0;i<asciiStr.length();i+=2)
		 baos.write((hexString.indexOf(asciiStr.charAt(i))<<4 |hexString.indexOf(asciiStr.charAt(i+1))));
		return new String(baos.toByteArray());
		}
	 
	 /**
		 * 16进制字符串转换成字节数组
		 * @param hexStr  十六进制字符串
		 * @return byte  字节数组 
		 * @author wl 2016-04-21 
		 */
	 public static byte[] hexStrToByte(String hexStr){
		 byte[] temp = new byte[hexStr.length() / 2];
		 for (int i = 0; i < hexStr.length() / 2; i++) {
			 temp[i] = (byte) Integer.parseInt(hexStr.substring(i*2, i*2 + 2),16);
		}
		 return temp;
	 }
	 /**
		 * 字节数组转换成16进制字符串
		 * @param byte  字节数组 
		 * @return hexStr  十六进制字符串 
		 * @author wl 2016-04-21 
		 */
	 public static String byteToHexStr(byte[] byteData){
		 String res  ="";
		 for (int i = 0; i < byteData.length; i++) {
			String t = Integer.toHexString(byteData[i]);
			if (t.length() == 1) {
				res += "0" + t;
			}else if (t.length() > 2){
				res +=t.substring(t.length() - 2, t.length());
			}else{
				res +=t;
			}
		}
		 return res;
	 }
}
