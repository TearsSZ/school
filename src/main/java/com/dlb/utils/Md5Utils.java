package com.dlb.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {
    
    public static String getMD5_16bits(String str) throws Exception {
        return getMD5_32bits(str).substring(8, 24);
    }
    
    public static String getMD5_32bits(String str) throws Exception {
        if(str == null || str.equals("")){
            throw new Exception("md5加密内容不能为空");
        }
        MessageDigest md=null;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md.digest(str.getBytes());
            StringBuilder hexValue = new StringBuilder();
            for (byte md5Byte : md5Bytes) {
                int val = ((int) md5Byte) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            str = hexValue.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return str;
    }

}