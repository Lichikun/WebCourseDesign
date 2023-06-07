package com.example.petshop.common.utils;

import java.security.MessageDigest;

public class MD5Utils {
    /**
     * MD5加密 生成32位md5码
     * @param inStr
     * @return 返回32位md5码
    */
    private static String md5Encode(String inStr) throws Exception {
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());

        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
    public static String getEncode(String psd,String salt) throws Exception{
        return md5Encode(md5Encode(psd)+salt);
    }
    /**
     * 测试主函数
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        String str = new String("123");
        System.out.println("原始：" + str);
        System.out.println("MD5后：" + getEncode(str,"666"));
    }

}
