package com.smj.comm.tools;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName: Md5Util
 * @Description: 可以指定字符编码的MD5加密工具类
 * @date: 2017年4月19日-下午5:40:00
 */
public class Md5Util {
    public static Log log = LogFactory.getLog(Md5Util.class);

    /**
     * Used building output as Hex
     */
    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * 对字符串进行MD5加密
     *
     * @param text    明文
     * @param charSet 字符编码
     * @return 密文
     */
    public static String encrypt(String text, String charSet) {
        MessageDigest msgDigest = null;
        try {
            msgDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("System doesn't support MD5 algorithm.");
        }
        // charSet为null时，不重新编码
        if (charSet != null) {
            try {
                // 注意该接口是按照utf-8编码形式加密
                msgDigest.update(text.getBytes(charSet));
            } catch (UnsupportedEncodingException e) {
                throw new IllegalStateException("System doesn't support your  EncodingException.");
            }
        }

        byte[] bytes = msgDigest.digest();
        return new String(encodeHex(bytes));
    }

    /**
     * 利用java自带的MD5加密，生成32个字符的16进制(utf-8编码) <br>
     *
     * @param s
     * @return
     */
    public final static String encode(String s) {
        //16进制字符
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            if (s == null) {
                return null;
            }
            byte[] btInput = s.getBytes("utf-8");
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            String jm = new String(str);
            System.out.println(jm);
            return jm;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 对字符串进行MD5加密
     *
     * @param text 明文
     * @return 密文
     */
    public static String encrypt(String text) {
        return encrypt(text, null);
    }

    public static char[] encodeHex(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }
        return out;
    }

    /**
     * 32 位标准 MD5 加密
     *
     * @param plainText
     * @return String
     * @throws
     * @Title: md32
     */
    public static String md32(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();

        } catch (NoSuchAlgorithmException e) {
            // CheckedExceptionHandler.handleException(e);
        }
        return null;
    }

    /**
     * 16 位标准 MD5 加密
     *
     * @param plainText
     * @return String
     * @throws
     * @Title: md16
     */
    public static String md16(String plainText) {
        String result = md32(plainText);
        if (result == null) {
            return null;
        }
        // 16位的加密
        return result.toString().substring(8, 24);
    }

    /**
     * 可逆的加密算法
     *
     * @param inStr
     * @return String
     * @throws
     * @Title: reversibleEncry
     */
    public static String reversibleEncry(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 'b');
        }
        String s = new String(a);
        return s;
    }

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    @SuppressWarnings("restriction")
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    @SuppressWarnings("restriction")
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * 计算字符串的MD5值(UTF-8)
     *
     * @param source
     * @return MD5值
     */
    public static String getMD5(String source) {
        return getMD5(source, null);
    }

    /**
     * 获取密码及密码盐
     *
     * @param password
     * @return
     */
    public static final String CHAR_KEY = "key";
    public static final String CHAR_PASSWORD = "password";

    public static Map<String, String> getPwd(String password) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        Map<String, String> map = new HashMap<String, String>();
        map.put(CHAR_KEY, uuid);
        map.put(CHAR_PASSWORD, Md5Util.getMD5(password.concat(uuid)));
        return map;
    }

    /**
     * 校验密码是否正常
     *
     * @param password
     * @param key
     * @param oldPassword
     * @return
     */
    public static boolean checkPassword(String password, String key, String oldPassword) {
        String pwd = getMD5(password.concat(key));
        if (pwd.equals(oldPassword)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 计算字符串的MD5值(UTF-8)
     *
     * @param source
     * @return MD5值
     */
    public static String getMD5(String source, String update) {
        return getMD5(source, update, Charset.forName("UTF-8"));
    }

    /**
     * 计算32位MD5值
     */
    public static String getMD5(String source, String update, Charset charset) {
        String hashValue = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source.getBytes(charset));
            if (update != null) {
                hashValue = byte2hex(md.digest(update.getBytes(charset)));
            } else {
                hashValue = byte2hex(md.digest());
            }
        } catch (NoSuchAlgorithmException e) {
            log.warn("计算MD5值出错", e);
        }
        return hashValue;
    }

    /**
     * 字节数组转十六进制字符串
     */
    public static String byte2hex(byte[] b) {
        if (b == null) {
            return "";
        }
        String tmp = "";
        StringBuffer stringBuffer = new StringBuffer();
        for (int n = 0; n < b.length; n++) {
            tmp = (Integer.toHexString(b[n] & 0xff));
            if (tmp.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(tmp);
        }
        return stringBuffer.toString().toLowerCase();
    }

    /**
     * 计算文件md5
     */
    public static String getFileMD5(String fileStr) {
        File file = new File(fileStr);
        if (!file.isFile()) {
            log.warn("文件不存在,fileStr:" + fileStr);
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            log.warn("计算md5出错,fileStr:" + fileStr);
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }
}