package com.example.petshop.common.utils;

public class test {
    public static void main(String[] args) {
        String us = "yaokanghe";
        JwtTokenProvider jwt = new JwtTokenProvider();
        Boolean tokeconfirm =
                jwt.validateToken("eyJhbGciOiJIUz" +
                        "I1NiJ9.eyJzdWIiOiJ5YW9rYW5naGUi" +
                        "LCJpYXQiOjE2ODY0NjYwMTEsImV4cCI6MTY4NjQ2NjAyMX0" +
                        ".CvvvaEpakuXGMZLikfAIju1wrgF9VXjdZ-NKiAz3NcA");

        MD5Utils md5 = new MD5Utils();

        System.out.println(
                tokeconfirm
        );
    }
}
