package com.watermelon.luomi.panda.utils;

import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * encrypt
 * Created by luomi on 2016-09-11.
 */
public class MD5Tool {
    protected static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final java.lang.String MD5 = "MD5";
    static MessageDigest messageDigest;

    MD5Tool(){
        try {
            messageDigest = MessageDigest.getInstance(MD5);
        } catch (NoSuchAlgorithmException e) {
            LoggerFactory.getLogger(this.getClass().getName()).error(e.getCause().getMessage());
        }
    }

    static class MD5ToolHolder{
        private static MD5Tool md5ToolHolder = new MD5Tool();//jvm to ensure thread security
    }

    public static MD5Tool getInstance() throws NoSuchAlgorithmException {
        return MD5ToolHolder.md5ToolHolder;
    }

    /**
     * encrypt return encrypted data
     * @param name
     * @return
     */
    public static String encrypt(String... name){
        String[] names = (String[]) Arrays.asList(name).toArray();
        Arrays.sort(names);
        StringBuffer content = new StringBuffer();
        for (String nameContent:names){
            content.append(nameContent);
        }
        return  getMD5Str(content.toString().getBytes());
    }

    static String getMD5Str(byte[] bytes){
        messageDigest.update(bytes);
        return bufferToHex(messageDigest.digest());
    }

    static String bufferToHex(byte[] bytes){
        return bufferToHex(bytes,0,bytes.length);
    }

    static String bufferToHex(byte[] bytes,int start,int end){
        StringBuffer stringbuffer = new StringBuffer(2 * end);
        int k = start + end;
        for (int i = start; i < k; i++) {
            appendHexPair(bytes[i], stringbuffer);
        }
        return stringbuffer.toString();
    }

    static void appendHexPair(byte b,StringBuffer buffer){
        char hexDigit = hexDigits[(b & 0xf0) >> 4];
        char hexDigit1 = hexDigits[b & 0xf];
        buffer.append(hexDigit);
        buffer.append(hexDigit1);
    }
}
