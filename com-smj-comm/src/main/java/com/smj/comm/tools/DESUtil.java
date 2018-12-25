package com.smj.comm.tools;


import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DESUtil {
    /**
     * 偏移量,可自行修改
     */
    private static String ivParameter = "z#AD(3sv02!5(9bC";

    /**
     * 密钥
     */
    private static String sKey = "h#45(5dHR(*!9|8@B23c0O6%";

    /**
     * @Title: encryptString
     * @Description: 加密
     */
    public static String encryptString(String sSrc) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        String info = Base64.encodeBase64String(encrypted);
        // 此处使用BASE64做转码
        return info;
    }

    /**
     * @Title: decryptString
     * @Description: 解密
     */
    public static String decryptString(String content) throws Exception {
        try {
            byte[] infoByte = Base64.decodeBase64(content);
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
            byte[] original = cipher.doFinal(infoByte);
            String info = new String(original);
            return info;
        } catch (Exception ex) {
            return "";
        }
    }

}