package com.slife.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 * <功能详细描述>
 *
 * @author  chenlujun
 * @version  [版本号, 2014年10月1日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class MD5Tool {

    /**
     * 生成md5编码字符串.
     * @param str 源字符串
     * @param charset 编码方式
     * @return
     *
     */
    public static String MD5(String str,String charset) {
        if (str == null)
            return null;
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        MessageDigest md5MessageDigest;
        byte[] md5Bytes;
        char md5Chars[];
        byte[] strBytes;
        try {
            strBytes = str.getBytes(charset);
            md5MessageDigest = MessageDigest.getInstance("MD5");
            md5MessageDigest.update(strBytes);
            md5Bytes = md5MessageDigest.digest();
            int j = md5Bytes.length;
            md5Chars = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte md5Byte = md5Bytes[i];
                md5Chars[k++] = hexDigits[md5Byte >>> 4 & 0xf];
                md5Chars[k++] = hexDigits[md5Byte & 0xf];
            }
            return new String(md5Chars);
        } catch (NoSuchAlgorithmException e) {
            //Log.output(e.toString(), Log.STD_ERR);
            return null;
        } catch (UnsupportedEncodingException e) {
            //Log.output(e.toString(), Log.STD_ERR);
            return null;
        } finally {
            md5MessageDigest = null;
            strBytes = null;
            md5Bytes = null;
        }
    }

    //**********************  just for sms authentication **************************
    public static String MD5Digit32(String pwd) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(pwd.getBytes(Charset.defaultCharset()));
            byte messageDigest[] = digest.digest();

            return toHexString(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }

    private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F' };

    public static String toHexString(byte[] b) {
        //String to  byte
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }
        return sb.toString();
    }
}