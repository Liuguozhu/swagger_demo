package org.sidao.common;

import java.net.InetAddress;
import java.util.UUID;

public class UUIDGenerator {
	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",  
        "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",  
        "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",  
        "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",  
        "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
        "W", "X", "Y", "Z" }; 
	/** 
     * 产生一个32位的UUID 
     *  
     * @return 
     */  
  
    public static String generate() {  
        return new StringBuilder(32).append(format(getIP())).append(  
                format(getJVM())).append(format(getHiTime())).append(  
                format(getLoTime())).append(format(getCount())).toString();  
          
    }  
    public static String generateShortUUID() {
    	StringBuffer shortBuffer = new StringBuffer();  
        String uuid = UUID.randomUUID().toString().replace("-", "");  
        for (int i = 0; i < 8; i++) {  
            String str = uuid.substring(i * 4, i * 4 + 4);  
            int x = Integer.parseInt(str, 16);  
            shortBuffer.append(chars[x % 0x3E]);  
        }  
        return shortBuffer.toString(); 
    }
    private static final int IP;  
    static {  
        int ipadd;  
        try {  
            ipadd = toInt(InetAddress.getLocalHost().getAddress());  
        } catch (Exception e) {  
            ipadd = 0;  
        }  
        IP = ipadd;  
    }  
  
    private static short counter = (short) 0;  
  
    private static final int JVM = (int) (System.currentTimeMillis() >>> 8);  
  
    private final static String format(int intval) {  
        String formatted = Integer.toHexString(intval);  
        StringBuilder buf = new StringBuilder("00000000");  
        buf.replace(8 - formatted.length(), 8, formatted);  
        return buf.toString();  
    }  
  
    private final static String format(short shortval) {  
        String formatted = Integer.toHexString(shortval);  
        StringBuilder buf = new StringBuilder("0000");  
        buf.replace(4 - formatted.length(), 4, formatted);  
        return buf.toString();  
    }  
  
    private final static int getJVM() {  
        return JVM;  
    }  
  
    private final static short getCount() {  
        synchronized (UUIDGenerator.class) {  
            if (counter < 0)  
                counter = 0;  
            return counter++;  
        }  
    }  
  
    /** 
     * Unique in a local network 
     */  
    private final static int getIP() {  
        return IP;  
    }  
  
    /** 
     * Unique down to millisecond 
     */  
    private final static short getHiTime() {  
        return (short) (System.currentTimeMillis() >>> 32);  
    }  
  
    private final static int getLoTime() {  
        return (int) System.currentTimeMillis();  
    }  
  
    private final static int toInt(byte[] bytes) {  
        int result = 0;  
        for (int i = 0; i < 4; i++) {  
            result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];  
        }  
        return result;  
    }  
}
