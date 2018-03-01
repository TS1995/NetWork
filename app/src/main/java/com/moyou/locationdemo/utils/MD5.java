package com.moyou.locationdemo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 */
public class MD5 {
    
    /**
     * 描述：MD5加密.
     *
     * @param str
     *            要加密的字符串
     * @return String 加密的字符串
     */
    public static String encrypt(String str) {
        char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
                             '0',
                             '1',
                             '2',
                             '3',
                             '4',
                             '5',
                             '6',
                             '7',
                             '8',
                             '9',
                             'a',
                             'b',
                             'c',
                             'd',
                             'e',
                             'f' };
        try {
            byte[] strTemp = str.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte tmp[] = mdTemp.digest();
            char strs[] = new char[16 * 2];
            int k = 0;
            for (int i = 0; i < 16; i++) {
                byte byte0 = tmp[i];
                strs[k++] = hexDigits[byte0 >>> 4 & 0xf];
                strs[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(strs).toUpperCase();
        }
        catch (Exception e) {
            return null;
        }
    }
    public static String getMD5Code(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] md5Bytes = md5.digest(inStr.getBytes());
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString().toUpperCase();
    }


}
