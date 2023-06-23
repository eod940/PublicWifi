package org.example;

import okhttp3.OkHttpClient;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        OkHttpClient client = new OkHttpClient();
        System.out.println(client.x509TrustManager());
    }
}